package com.platform.service.impl;

import com.platform.constants.CarpoolConstant;
import com.platform.constants.CommonConstant;
import com.platform.constants.TemplateMessageConstant;
import com.platform.dao.CarpoolOrderMapper;
import com.platform.dao.CarpoolPublishMapper;
import com.platform.dao.CarpoolUserMapper;
import com.platform.dto.CarpoolOrderVo;
import com.platform.entity.*;
import com.platform.service.ApiCarpoolOrderService;
import com.platform.service.CarpoolOrderLogService;
import com.platform.service.CarpoolUserFormidService;
import com.platform.thread.TokenThread;
import com.platform.utils.DateUtils;
import com.platform.utils.GEOUtils;
import com.platform.utils.StringUtils;
import com.platform.weixin.WeixinConfig;
import com.platform.weixin.templateMessage.WechatTemplateMsg;
import com.platform.weixin.templateMessage.WechatTemplateMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by zuimeideshiguang on 18/2/12.
 */
@Service("apiCarpoolOrderService")
public class ApiCarpoolOrderServiceImpl extends BasicSetServiceImpl<CarpoolOrder> implements ApiCarpoolOrderService {


    @Autowired
    private CarpoolOrderMapper carpoolOrderMapper;
    @Autowired
    private CarpoolPublishMapper carpoolPublishMapper;
    @Autowired
    private CarpoolUserMapper carpoolUserMapper;
    @Autowired
    private CarpoolUserFormidService carpoolUserFormidService;
    @Autowired
    private CarpoolOrderLogService carpoolOrderLogService;

    @Override
    public void order(CarpoolOrderVo carpoolOrder) {
        Date time = new Date();
        carpoolOrder.setCreateTime(time);
        carpoolOrder.setUpdateTime(time);
        carpoolOrder.setStartPointGeo(GEOUtils.cateGeoCode(carpoolOrder.getStartPointLongitude(),carpoolOrder.getStartPointLatitude()));
        carpoolOrder.setDestinationGeo(GEOUtils.cateGeoCode(carpoolOrder.getDestinationLongitude(),carpoolOrder.getDestinationLatitude()));
        carpoolOrder.setDataStatus(CommonConstant.USEABLE_STATUS);
        carpoolOrder.setStatus(CarpoolConstant.ORDERING_STATUS);
        carpoolOrder.setOperatorId(carpoolOrder.getOrderUserId());
        carpoolOrder.setOperatorName(carpoolOrder.getOrderUserName());
        int rs = carpoolOrderMapper.insertSelective(carpoolOrder);
        if (rs > 0 && null != carpoolOrder.getUserType() && carpoolOrder.getUserType().intValue() == 1){ // 修改发布信息
            CarpoolPublish carpoolPublish = new CarpoolPublish();
            carpoolPublish.setId(carpoolOrder.getPublishId());
            carpoolPublish.setDataStatus(CommonConstant.USEABLE_STATUS);
            CarpoolPublish publish = carpoolPublishMapper.selectOne(carpoolPublish);
            if (publish != null){
                if (publish.getPassengerNum() - 1 < 0){
                    throw new RuntimeException("位置已被预订完了");
                }
                carpoolPublish.setPassengerNum(publish.getPassengerNum() - carpoolOrder.getPassengerNum() );
                carpoolPublishMapper.updateByPrimaryKeySelective(carpoolPublish);
                saveLogs(carpoolOrder,carpoolOrder.getOrderUserName() + "预订成功！");

                /**
                 * 给发布人发送拼车请求消息
                 */
                String tmplId = carpoolOrder.getUserType().intValue() == 1 ? TemplateMessageConstant.CARPOOL_REQUEST_TMPL_ID : ""; // 模板消息id
                sendTemplateMsg(carpoolOrder.getPublishuserId() , tmplId,"", createMsgData(carpoolOrder));
            }

        }
    }

    ///预约消息封装
    private TreeMap<String,TreeMap<String,String>> createMsgData(CarpoolOrderVo carpoolOrder){
        TreeMap<String,TreeMap<String,String>> params = new TreeMap<String,TreeMap<String,String>>();
        List<TreeMap<String,String>> list = new ArrayList<>();
        if (carpoolOrder.getUserType().intValue() == 1){
            list.add(WechatTemplateMsg.item(carpoolOrder.getOrderUserName(), "#000000")); //乘客姓名
            list.add(WechatTemplateMsg.item(DateUtils.format(carpoolOrder.getDepartureTime(),DateUtils.DATE_TIME_PATTERN), "#000000")); //搭乘时间
            list.add(WechatTemplateMsg.item(carpoolOrder.getStartPoint(), "#000000"));//搭乘地点
            list.add(WechatTemplateMsg.item(carpoolOrder.getMobile(), "#000000"));//联系电话
            list.add(WechatTemplateMsg.item(String.valueOf(carpoolOrder.getPassengerNum()), "#000000"));//乘车人数
        }else{
            list.add(WechatTemplateMsg.item(carpoolOrder.getOrderUserName(), "#000000")); //司机姓名
            list.add(WechatTemplateMsg.item(DateUtils.format(carpoolOrder.getDepartureTime(),DateUtils.DATE_TIME_PATTERN), "#000000")); //出发时间
            list.add(WechatTemplateMsg.item(carpoolOrder.getMobile(), "#000000"));//联系电话
            list.add(WechatTemplateMsg.item("请尽快联系乘客,保证出行信息无误", "#000000"));//温馨提示
        }
        if(list.size() < 0) return  null;
        for (int i = 0 ; i < list.size() ; i++ ){
            params.put("keyword"+(i+1),list.get(i));
        }

        return params;
    }

    @Override
    public void confirmOrRefuseOrder(CarpoolOrderVo carpoolOrder) {
        Date time = new Date();
        carpoolOrder.setUpdateTime(time);
        carpoolOrderMapper.updateByPrimaryKeySelective(carpoolOrder);
        String msg = carpoolOrder.getStatus().intValue() == CarpoolConstant.FINISHED_STATUS ? "确认" : "拒绝";
        saveLogs(carpoolOrder,carpoolOrder.getOrderUserName() + msg + "预订！");
        /**
         * 给发布人发送拼车请求消息
         */
        ///清空条件查询预约单内容
        carpoolOrder.setUpdateTime(null);
        carpoolOrder.setStatus(null);
        carpoolOrder.setRefuseReason(null);
        CarpoolOrder order = carpoolOrderMapper.selectOne(carpoolOrder);
        String tmplId = (carpoolOrder.getStatus().intValue() == CarpoolConstant.FINISHED_STATUS ? TemplateMessageConstant.CARPOOL_SUCCESS_TMPL_ID : TemplateMessageConstant.CARPOOL_FAIL_TMPL_ID);
        sendTemplateMsg(carpoolOrder.getPublishuserId() ,tmplId,"", confirmOrRefuseMsgData(order));
    }




    ///预约消息封装
    private TreeMap<String,TreeMap<String,String>> confirmOrRefuseMsgData(CarpoolOrder carpoolOrder){
        TreeMap<String,TreeMap<String,String>> params = new TreeMap<String,TreeMap<String,String>>();
        List<TreeMap<String,String>> list = new ArrayList<>();
        CarpoolPublish carpoolPublish = new CarpoolPublish();
        carpoolPublish.setId(carpoolOrder.getPublishId());
        CarpoolPublish publish = carpoolPublishMapper.selectOne(carpoolPublish);
        if (publish != null){
            CarpoolUser user = new CarpoolUser();
            user.setId(publish.getPublishUserId());
            CarpoolUser carpoolUser = carpoolUserMapper.selectOne(user);
            if (carpoolUser != null){
                if (carpoolOrder.getStatus().intValue() == CarpoolConstant.FINISHED_STATUS ){
                    list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolUser.getNickName(),"--"), "#000000")); //司机姓名
                    list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolUser.getMobile(),"--"), "#000000"));//司机电话
                    list.add(WechatTemplateMsg.item(StringUtils.NullToString(publish.getCarNo(),"--"), "#000000"));//车牌号码
                    list.add(WechatTemplateMsg.item(DateUtils.format(carpoolOrder.getDepartureTime(),DateUtils.DATE_TIME_PATTERN), "#000000"));//出发时间
                    list.add(WechatTemplateMsg.item(String.valueOf(carpoolOrder.getStartPoint()), "#000000"));//出发地点
                }else{
                    list.add(WechatTemplateMsg.item(carpoolOrder.getStartPoint() +"-"+ carpoolOrder.getDestination(), "#000000")); //当前行程
                    list.add(WechatTemplateMsg.item(DateUtils.format(carpoolOrder.getDepartureTime(),DateUtils.DATE_TIME_PATTERN), "#000000"));//出发时间
                    list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolOrder.getRefuseReason(),"--"), "#000000"));//失败原因
                    list.add(WechatTemplateMsg.item("很抱歉，请选择其他拼车信息", "#000000"));//温馨提示
                }
            }
        }
        if(list.size() < 0) return  null;
        for (int i = 0 ; i < list.size() ; i++ ){
            params.put("keyword"+(i+1),list.get(i));
        }
        return params;
    }


    @Override
    public void cancelOrder(CarpoolOrderVo carpoolOrder) {
        CarpoolOrder co = new CarpoolOrder();
        co.setId(carpoolOrder.getId());
        CarpoolOrder order = carpoolOrderMapper.selectOne(carpoolOrder);
        Integer status = order.getStatus();
        if (order != null){
            Date time = new Date();
            carpoolOrder.setUpdateTime(time);
            int rs = carpoolOrderMapper.updateByPrimaryKeySelective(carpoolOrder);

            ///预约成功的单子需要给对方发消息
            if (rs > 0 && status.intValue() == CarpoolConstant.ORDER_SUCCESS_STATUS ){
                sendTemplateMsg(carpoolOrder.getPublishuserId() ,TemplateMessageConstant.CARPOOL_ORDER_CANCEL_TMPL_ID,"", confirmOrRefuseMsgData(order));
            }
        }


    }

    ///预约取消通知封装
    private TreeMap<String,TreeMap<String,String>> cancelMsgData(CarpoolOrder carpoolOrder){
        TreeMap<String,TreeMap<String,String>> params = new TreeMap<String,TreeMap<String,String>>();
        List<TreeMap<String,String>> list = new ArrayList<>();
        CarpoolPublish carpoolPublish = new CarpoolPublish();
        carpoolPublish.setId(carpoolOrder.getPublishId());
        CarpoolPublish publish = carpoolPublishMapper.selectOne(carpoolPublish);
        if (publish != null){
            CarpoolUser user = new CarpoolUser();
            user.setId(publish.getPublishUserId());
            CarpoolUser carpoolUser = carpoolUserMapper.selectOne(user);
            if (carpoolUser != null){
                list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolUser.getNickName(),"--"), "#000000")); //姓名
                list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolOrder.getMobile(),"--"), "#000000"));//电话
                list.add(WechatTemplateMsg.item(carpoolOrder.getStartPoint() +"-"+ carpoolOrder.getDestination(), "#000000"));// 预约项目
                list.add(WechatTemplateMsg.item(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN), "#000000"));//取消时间
                list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolOrder.getCancelReason(),"--"), "#000000"));//取消原因
            }
        }
        if(list.size() < 0) return  null;
        for (int i = 0 ; i < list.size() ; i++ ){
            params.put("keyword"+(i+1),list.get(i));
        }
        return params;
    }

    //记录预约记录
    private void saveLogs(CarpoolOrder carpoolOrder , String content ){
        CarpoolOrderLog log = new CarpoolOrderLog();
        log.setContent(content);
        log.setOrderId(carpoolOrder.getId());
        log.setOperatorId(carpoolOrder.getOrderUserId());
        log.setOperatorName(carpoolOrder.getOrderUserName());
        carpoolOrderLogService.insertSelective(log);
    }

    public void sendTemplateMsg(Integer userId , String tmplId , String page,  TreeMap<String,TreeMap<String,String>> params){
        CarpoolUserFormid formid = new CarpoolUserFormid();
        formid.setUserId(userId); //发布人
        List<CarpoolUserFormid> formidList = carpoolUserFormidService.select(formid);
        if (null != formidList && formidList.size() > 0){
            CarpoolUserFormid userFormid = formidList.get(0);
            WechatTemplateMsg msg = new WechatTemplateMsg();
            msg.setTouser(userFormid.getWxOpenid());
            msg.setColor("");
            msg.setForm_id(userFormid.getFormId());
            msg.setTemplate_id(tmplId); // 发送模板消息
            msg.setPage(page);
            msg.setEmphasis_keyword("");
            msg.setData(params);

            WechatTemplateMsgUtil.sendTemplateMsg(TokenThread.accessToken.getAccessToken(),WechatTemplateMsgUtil.createTemplateMsgJson(msg));
        }
    }
}
