package com.platform.service.impl;

import com.platform.entity.CreditInfo;
import com.platform.entity.vo.CreditQueryVo;
import com.platform.service.ICreditInfoService;
import com.platform.util.CommonUtil;
import com.platform.utils.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author bjsonghongxu
 * @create 2018-12-21 16:43
 **/
@Service
public class CreditInfoServiceImpl  extends BasicSetServiceImpl<CreditInfo> implements ICreditInfoService {

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



        }catch (Exception e) {

        }





        ///封装入库信息

        return CommonUtil.toResponsObject(0,"",null);
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
        return CommonUtil.toResponsObject(0,"",null);
    }
}
