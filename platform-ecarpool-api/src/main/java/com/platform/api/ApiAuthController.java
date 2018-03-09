package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.constants.CommonConstant;
import com.platform.entity.CarpoolUser;
import com.platform.entity.FullUserInfo;
import com.platform.entity.UserInfo;
import com.platform.service.ApiCarpoolUserService;
import com.platform.service.TokenService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.StringUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * API登录授权
 *
 * @author bjsonghongxu
 * @email 939961241@qq.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/api/auth")
public class ApiAuthController extends ApiBaseAction {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private ApiCarpoolUserService apiCarpoolUserService;
    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @RequestMapping("carpoolLogin")
    public Object carpoolLogin() {
        JSONObject jsonParam = this.getJsonRequest();
        FullUserInfo fullUserInfo = null;
        String code = "";
        if (!StringUtils.isNullOrEmpty(jsonParam.getString("code"))) {
            code = jsonParam.getString("code");
        }
        if (null != jsonParam.get("userInfo")) {
            fullUserInfo = jsonParam.getObject("userInfo", FullUserInfo.class);
        }



        Map<String, Object> resultObj = new HashMap();
        UserInfo userInfo = fullUserInfo.getUserInfo();
        if (null == userInfo){
            return  toResponsFail("登录无效参数");
        }


        String openid = "oacP90FDeUdnFMZkwZ274fEWnWqE";
//        if(!code.equals("the code is a mock one")){
//           // openid = sessionData.getString("openid");
//        }
        Date time = new Date();
        CarpoolUser cu = new CarpoolUser();
        cu.setDataStatus(CommonConstant.USEABLE_STATUS);
        cu.setWxOpenid(openid);
        CarpoolUser carpoolUser = apiCarpoolUserService.selectOne(cu);
        if (null == carpoolUser) {
            carpoolUser = new CarpoolUser();
            carpoolUser.setWxOpenid(openid);
            carpoolUser.setDataStatus(CommonConstant.USEABLE_STATUS);
            carpoolUser.setAvatar(userInfo.getAvatarUrl());
            carpoolUser.setCreateTime(time);
            carpoolUser.setNickName(userInfo.getNickName());
            carpoolUser.setUpdateTime(time);
            apiCarpoolUserService.insert(carpoolUser);
            userInfo.setWxOpenid(openid);
            userInfo.setUserId(Long.valueOf(String.valueOf(carpoolUser.getId())));
        }else {
            userInfo.setUserId(Long.parseLong(String.valueOf(carpoolUser.getId())));
            userInfo.setAvatarUrl(carpoolUser.getAvatar());
            userInfo.setNickName(carpoolUser.getNickName());
            userInfo.setWxOpenid(carpoolUser.getWxOpenid());
        }

        Map<String, Object> tokenMap = tokenService.createToken(carpoolUser.getId());
        String token = MapUtils.getString(tokenMap, "token");

        if (StringUtils.isNullOrEmpty(token)) {
            return toResponsFail("登录失败");
        }

        resultObj.put("wxOpendId",code);
        resultObj.put("token", token);
        resultObj.put("userInfo", userInfo);
        resultObj.put("userId", carpoolUser.getId());
        return toResponsSuccess(resultObj);
    }
}
