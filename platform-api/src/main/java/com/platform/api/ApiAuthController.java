package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.constants.CommonConstant;
import com.platform.entity.CarpoolUser;
import com.platform.service.ApiCarpoolUserService;
import com.qiniu.util.StringUtils;
import com.platform.annotation.IgnoreAuth;
import com.platform.entity.FullUserInfo;
import com.platform.entity.UserInfo;
import com.platform.entity.UserVo;
import com.platform.service.ApiUserService;
import com.platform.service.TokenService;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiUserUtils;
import com.platform.util.CommonUtil;
import com.platform.utils.CharUtil;
import com.platform.utils.R;
import com.platform.validator.Assert;
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
    private ApiUserService userService;
    @Autowired
    private ApiCarpoolUserService apiCarpoolUserService;
    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @RequestMapping("login")
    public R login(String mobile, String password) {
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        //用户登录
        long userId = userService.login(mobile, password);

        //生成token
        Map<String, Object> map = tokenService.createToken(userId);

        return R.ok(map);
    }

    /**
     * 登录
     */
    @IgnoreAuth
    @RequestMapping("login_by_weixin")
    public Object loginByWeixin() {
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
        //
        UserInfo userInfo = fullUserInfo.getUserInfo();

        //获取openid
//        String requestUrl = ApiUserUtils.getWebAccess(code);//通过自定义工具类组合出小程序需要的登录凭证 code
//        logger.info("》》》组合token为：" + requestUrl);
//        JSONObject sessionData = CommonUtil.httpsRequest(requestUrl, "GET", null);
//
//        if (null == sessionData || StringUtils.isNullOrEmpty(sessionData.getString("openid"))) {
//            return toResponsFail("登录失败");
//        }
//        //验证用户信息完整性
//        String sha1 = CommonUtil.getSha1(fullUserInfo.getRawData() + sessionData.getString("session_key"));
//        if (!fullUserInfo.getSignature().equals(sha1)) {
//            return toResponsFail("登录失败");
//        }
        String openid = "oacP90FDeUdnFMZkwZ274fEWnWqE";
//        if(!code.equals("the code is a mock one")){
//           // openid = sessionData.getString("openid");
//        }
        Date nowTime = new Date();
        UserVo userVo = userService.queryByOpenId(openid);
        if (null == userVo) {
            userVo = new UserVo();
            userVo.setUsername("微信用户" + CharUtil.getRandomString(12));
            userVo.setPassword(openid);
            userVo.setRegister_time(nowTime);
            userVo.setRegister_ip(this.getClientIp());
            userVo.setLast_login_ip(userVo.getRegister_ip());
            userVo.setLast_login_time(userVo.getRegister_time());
            userVo.setWeixin_openid(openid);
            userVo.setAvatar(userInfo.getAvatarUrl());
            userVo.setGender(userInfo.getGender()); // //性别 0：未知、1：男、2：女
            userVo.setNickname(userInfo.getNickName());
            userService.save(userVo);
        } else {
            userInfo.setMobile(org.apache.commons.lang.StringUtils.isNotBlank(userVo.getMobile()) ? userVo.getMobile() : "绑定手机号码");
            userInfo.setUserId(Long.valueOf(String.valueOf(userVo.getUserId())));
            userVo.setLast_login_ip(this.getClientIp());
            userVo.setLast_login_time(nowTime);
            userService.update(userVo);
        }

        Map<String, Object> tokenMap = tokenService.createToken(userVo.getUserId());
        String token = MapUtils.getString(tokenMap, "token");

        if (null == userInfo || StringUtils.isNullOrEmpty(token)) {
            return toResponsFail("登录失败");
        }

        resultObj.put("token", token);
        resultObj.put("userInfo", userInfo);
        resultObj.put("userId", userVo.getUserId());
        return toResponsSuccess(resultObj);
    }


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
        }else {
            userInfo.setUserId(Long.parseLong(String.valueOf(carpoolUser.getId())));
            userInfo.setAvatarUrl(carpoolUser.getAvatar());
            userInfo.setNickName(carpoolUser.getNickName());
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
