package com.platform.service.impl;

import com.platform.dao.CreditInfoMapper;
import com.platform.entity.CreditInfo;
import com.platform.entity.vo.CreditQueryVo;
import com.platform.service.ICreditInfoService;
import com.platform.util.CommonUtil;
import com.platform.utils.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bjsonghongxu
 * @create 2018-12-21 16:43
 **/
@Service
public class CreditInfoServiceImpl  extends BasicSetServiceImpl<CreditInfo> implements ICreditInfoService {

    @Autowired
    private CreditInfoMapper creditInfoMapper;

    @Override
    public Map<String, Object> query(CreditQueryVo vo) {
        ///请求参数校验
        if (!validateQuery(vo)) {
            return CommonUtil.toResponsObject(-1,"缺少参数",null);
        }

        try {
            Map<String, String> map = PropertiesUtil.getInstance("/platform.properties");
            String pid = map.get("open.api.credit.pid");
            String version = map.get("open.api.credit.version");

            ///调用第三方接口
            vo.setPid(pid);
            vo.setVersion(version);
            vo.setSign_type("MD5");


            ///如果调用成功，记录请求日志，并返回给下游用户
            String credit_query_id = "";
            CreditInfo credit = new CreditInfo(vo.getPid(),
                    vo.getBiz_no(),
                    vo.getVersion(),
                    vo.getCard_id(),
                    vo.getPhone_number(),
                    vo.getUser_name(),
                    vo.getAttachment_count(),
                    vo.getAttachment(),
                    vo.getAttachment_type(),
                    vo.getNotice_url(),
                    vo.getSign_type(),
                    vo.getSign(),
                    credit_query_id);

            creditInfoMapper.insertSelective(credit);
            Map<String,Object> data = new HashMap<>();
            data.put("biz_no",vo.getBiz_no());
            data.put("credit_query_id",credit_query_id);
            data.put("credit_query_sg_id",credit.getId());
            return CommonUtil.toResponsObject(0,"查询征信请求成功！",null);

        }catch (Exception e) {
            e.printStackTrace();
            return CommonUtil.toResponsObject(-1,"由于网络原因查询失败！",null);
        }

    }

    /**
     * 校验参数
     * @param vo
     * @return
     */
    private boolean validateQuery(CreditQueryVo vo) {
        boolean v = true;
        if (StringUtils.isBlank(vo.getAttachment_type())) {
            v = false;
        }
        if (StringUtils.isBlank(vo.getAttachment_count())) {
            v = false;
        }
        if (StringUtils.isNotBlank(vo.getAttachment_count())
                && Integer.parseInt(vo.getAttachment_count()) > 0
                && StringUtils.isBlank(vo.getAttachment())) {
            v = false;
        }
        if (StringUtils.isBlank(vo.getCard_id())) {
            v = false;
        }
        if (StringUtils.isBlank(vo.getUser_name())) {
            v = false;
        }

        return v;
    }

    @Override
    public Map<String, Object> info(CreditQueryVo vo) {
        ///请求参数校验
        if (!validate(vo)) {
            return CommonUtil.toResponsObject(-1,"缺少参数",null);
        }

        try {


            ///如果调用成功，记录请求日志，并返回给下游用户
            String html = "";
            CreditInfo credit = new CreditInfo(Integer.parseInt(vo.getCredit_query_sg_id()),html);

            creditInfoMapper.updateByPrimaryKeySelective(credit);

            Map<String,Object> data = new HashMap<>();

            data.put("credit_query_id",vo.getCredit_query_id());
            data.put("html",html);

            return CommonUtil.toResponsObject(0,"查询征信信息成功！",null);

        }catch (Exception e) {
            e.printStackTrace();
            return CommonUtil.toResponsObject(-1,"由于网络原因查询失败！",null);
        }
    }

    //校验参数
    private  boolean validate(CreditQueryVo vo) {
        boolean v = true;
        if (StringUtils.isBlank(vo.getCredit_query_id())) {
            v = false;
        }
        if (StringUtils.isBlank(vo.getCredit_query_sg_id())) {
            v = false;
        }

        return v;
    }
}
