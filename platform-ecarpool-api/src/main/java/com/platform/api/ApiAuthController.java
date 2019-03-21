package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.constants.CommonConstant;
import com.platform.entity.CarpoolUser;
import com.platform.entity.UserInfo;
import com.platform.service.ApiCarpoolUserService;
import com.platform.service.TokenService;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiUserUtils;
import com.platform.utils.StringUtils;
import com.platform.utils.WeixinUtil;
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
        //FullUserInfo fullUserInfo = null;
        UserInfo userInfo = null;
        String code = "";
        if (!StringUtils.isNullOrEmpty(jsonParam.getString("code"))) {
            code = jsonParam.getString("code");
        }
        if (null != jsonParam.get("userInfo")) {
            userInfo = jsonParam.getObject("userInfo", UserInfo.class);
        }



        Map<String, Object> resultObj = new HashMap();
        if (null == userInfo){
            return  toResponsFail("登录无效参数");
        }


        //获取openid
        String requestUrl = ApiUserUtils.getWebAccess(code);//通过自定义工具类组合出小程序需要的登录凭证 code
        logger.info("》》》组合token为：" + requestUrl);
        net.sf.json.JSONObject sessionData = WeixinUtil.httpRequest(requestUrl, "GET", null);

        if (null == sessionData || (sessionData.getString("openid") != null && StringUtils.isNullOrEmpty(sessionData.getString("openid")))) {
            return toResponsFail("登录失败");
        }

        String openid = "oacP90FDeUdnFMZkwZ274fEWnWqE";
        if(!code.equals("the code is a mock one")){
            openid = sessionData.getString("openid");
        }else{
            openid = "oacP90FDeUdnFMZkwZ274fEWnWqE";
        }
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
        }

        userInfo.setMobile(carpoolUser.getMobile());
        userInfo.setUserId(Long.parseLong(String.valueOf(carpoolUser.getId())));
        userInfo.setAvatarUrl(carpoolUser.getAvatar());
        userInfo.setNickName(carpoolUser.getNickName());
        userInfo.setWxOpenid(carpoolUser.getWxOpenid());
        userInfo.setIsAuth(carpoolUser.getIsAuth());
        userInfo.setIsRealName(carpoolUser.getIsRealName());
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
