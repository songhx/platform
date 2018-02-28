package com.platform.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.platform.constants.CarpoolConstant;
import com.platform.constants.CommonConstant;
import com.platform.constants.TemplateMessageConstant;
import com.platform.dto.CarpoolOrderVo;
import com.platform.entity.*;
import com.platform.service.*;
import com.platform.thread.TokenThread;
import com.platform.util.ApiBaseAction;
import com.platform.utils.DateUtils;
import com.platform.utils.GEOUtils;
import com.platform.vo.RequestPageParameter;
import com.platform.weixin.WeixinConfig;
import com.platform.weixin.templateMessage.WechatTemplateMsg;
import com.platform.weixin.templateMessage.WechatTemplateMsgUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by zuimeideshiguang on 18/2/13.
 * 预约处理控制器
 */
@RestController
@RequestMapping("/api/car/order")
public class ApiCarpoolOrderController extends ApiBaseAction {

   @Autowired
   private ApiCarpoolOrderService apiCarpoolOrderService;


    /**
     * 预约或者抢单
     * @param carpoolOrder
     * @return
     */
    @RequestMapping("order")
    public Object order(@RequestBody  CarpoolOrderVo carpoolOrder) {

        ///校验
        if (null == carpoolOrder.getPublishId()){
            return  toResponsFail("行程不存在！");
        }
        if (null == carpoolOrder.getOrderUserId()){
            return  toResponsFail("用户在系统中不存在，请先登录！");
        }
        //字符串转时间
        if (StringUtils.isNotBlank(carpoolOrder.getDepartureTimeStr())){carpoolOrder.setDepartureTime(DateUtils.strToDate(carpoolOrder.getDepartureTimeStr()));}
        try {
            apiCarpoolOrderService.order(carpoolOrder);
            return toResponsSuccess("预订成功！");
        }catch (Exception e){
            //saveLogs(loginUser,carpoolOrder.getId(),carpoolOrder.getOrderUserName() + "预订失败！");
            return  toResponsFail("预订失败！");
        }

    }


    /**
     * 确认或拒绝
     * @param carpoolOrder
     * @return
     */
    @RequestMapping("confirmOrRefuse")
    public Object confirmOrRefuseOrder(@RequestBody  CarpoolOrderVo carpoolOrder) {

        ///校验
        if (null == carpoolOrder.getId()){
            return  toResponsFail("参数错误！");
        }
        try {
            apiCarpoolOrderService.confirmOrRefuseOrder(carpoolOrder);
            return toResponsSuccess("操作成功！");
        }catch (Exception e){
            //saveLogs(loginUser,carpoolOrder.getId(),carpoolOrder.getOrderUserName() + "预订失败！");
            return  toResponsFail("操作失败！");
        }
    }

    /**
     * 取消预约
     * @param carpoolOrder
     * @return
     */
    @RequestMapping("cancel")
    public Object cancelOrder(@RequestBody  CarpoolOrderVo carpoolOrder) {

        ///校验
        if (null == carpoolOrder.getId()){
            return  toResponsFail("参数错误！");
        }
        try {
            apiCarpoolOrderService.cancelOrder(carpoolOrder);
            return toResponsSuccess("操作成功！");
        }catch (Exception e){
            return  toResponsFail("操作失败！");
        }
    }


    /**
     * 拼车记录
     * @param carpoolOrder
     * @return
     */
    @RequestMapping("list")
    public Object list(@RequestBody  CarpoolOrderVo carpoolOrder) {


         if (null == carpoolOrder.getOrderUserId()){
            return  toResponsFail("用户在系统中不存在，请先登录！");
        }
        PageHelper.startPage(carpoolOrder.getStart(), carpoolOrder.getLimit(), true, false); //设置分页

        carpoolOrder.setDataStatus(CommonConstant.USEABLE_STATUS); //可用
        List<CarpoolOrder> list = apiCarpoolOrderService.select(carpoolOrder);

        PageInfo<CarpoolOrder> pageInfo = new PageInfo<CarpoolOrder>(list);

        Map<String, Object> returnMap = new HashMap<>();
        //设置返回参数
        returnMap.put("totalPage",pageInfo.getPages());
        returnMap.put("list", list);

        return toResponsSuccess(returnMap);
    }


}
