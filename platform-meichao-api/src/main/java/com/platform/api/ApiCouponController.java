package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.entity.*;
import com.platform.service.ApiCouponCodesService;
import com.platform.annotation.LoginUser;
import com.platform.service.ApiCouponService;
import com.platform.service.ApiUserCouponService;
import com.platform.service.ApiUserService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.CharUtil;
import com.platform.utils.StringUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * API优惠券管理
 *
 * @author bjsonghongxu
 * @email 939961241@qq.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/api/coupon")
public class ApiCouponController extends ApiBaseAction {
    @Autowired
    private ApiUserService apiUserService;
    @Autowired
    private ApiCouponService apiCouponService;
    @Autowired
    private ApiUserCouponService apiUserCouponService;
    @Autowired
    private ApiCouponCodesService apiCouponCodesService;

    /**
     * 获取优惠券列表
     */
    @RequestMapping("list")
    public Object list(@LoginUser UserVo loginUser) {
        JSONObject jsonParam = getJsonRequest();
        Map param = new HashMap();
        param.put("user_id", loginUser.getUserId());
        if(null != jsonParam){
            if (null != jsonParam.getInteger("isUsed")){
                param.put("isUsed", jsonParam.getInteger("isUsed"));
            }

            if (null != jsonParam.getInteger("isTransmit")){
                param.put("isTransmit", jsonParam.getInteger("isTransmit"));
            }

        }
        List<CouponVo> couponVos = apiCouponService.queryUserCoupons(param);
        return toResponsSuccess(couponVos);
    }

    /**
     * 卡券领取界面查询卡券信息
     * @return
     */
    @RequestMapping("info")
    public Object info() {
        JSONObject jsonParam = getJsonRequest();
        if(null != jsonParam){
            Integer id = jsonParam.getInteger("id");
            Map param = new HashMap();
            param.put("user_coupon_id", id);
            List<CouponVo> couponVos = apiCouponService.queryUserCoupons(param);
            if (null != couponVos && couponVos.size() > 0){
                CouponVo couponVo = couponVos.get(0);
                if (couponVo.getIsTransmit().intValue() == 2){ //转送中
                    return toResponsSuccess(couponVo);
                }else {
                    return toResponsFail(couponVo.getName() + "已被领取~");
                }
            }else{
                return toResponsFail("卡券不存在或已被领取~");
            }
        }else{
            return toResponsFail("非法请求~");
        }

    }

    /**
     *  开始转送
     * @param loginUser
     * @return
     */
    @RequestMapping("send")
    public Object send(@LoginUser UserVo loginUser) {
        JSONObject jsonParam = getJsonRequest();
        if (loginUser.getUserId() == null) {
            return toResponsFail("转增操作失败，用户不存在！");
        }
        if (null != jsonParam) {
            Integer id = jsonParam.getInteger("id");
            Map param = new HashMap();
            param.put("user_coupon_id", id);
            List<CouponVo> couponVos = apiCouponService.queryUserCoupons(param);
            if (null != couponVos && couponVos.size() > 0) {
                CouponVo couponVo = couponVos.get(0);
                if (couponVo.getIsTransmit().intValue() == 0) { //转送中
                    ///修改原有用户的转送状态为转送中
                    UserCouponVo ucv = new UserCouponVo();
                    ucv.setId(id);
                    ucv.setIsTransmit(2);
                    apiUserCouponService.update(ucv);

                    return toResponsSuccess("成功");

                } else {
                    return toResponsFail(couponVo.getName() + "无效卡券~");
                }
            } else {
                return toResponsFail("卡券不存在或已被领取~");
            }
        } else {
            return toResponsFail("非法请求~");
        }
    }


    /**
     * 领取
     */
    @RequestMapping("receive")
    public Object receive(@LoginUser UserVo loginUser) {
        JSONObject jsonParam = getJsonRequest();
        if (loginUser.getUserId() == null) {
            return toResponsFail("领取礼品卡失败，用户不存在！");
        }
        if (null != jsonParam) {
            Integer id = jsonParam.getInteger("id");
            Map param = new HashMap();
            param.put("user_coupon_id", id);
            List<CouponVo> couponVos = apiCouponService.queryUserCoupons(param);
            if (null != couponVos && couponVos.size() > 0) {
                CouponVo couponVo = couponVos.get(0);
                if (null != couponVo.getIsTransmit() && couponVo.getIsTransmit().intValue() == 2) { //转送中

                    ///为接受用户创建卡券信息
                    UserCouponVo userCouponVo = new UserCouponVo();
                    userCouponVo.setCoupon_id(couponVo.getId());
                    userCouponVo.setCoupon_code_id(couponVo.getCouponCodeId());
                    userCouponVo.setCoupon_number(couponVo.getCoupon_number());
                    userCouponVo.setUser_id(loginUser.getUserId());
                    userCouponVo.setAdd_time(new Date());
                    apiUserCouponService.save(userCouponVo);

                    ///修改原有用户的转送状态为已转送
                    UserCouponVo ucv = new UserCouponVo();
                    ucv.setId(id);
                    ucv.setIsTransmit(1);
                    apiUserCouponService.update(ucv);

                    return toResponsSuccess("转送成功");

                } else {
                    return toResponsFail(couponVo.getName() + "已被领取~");
                }
            } else {
                return toResponsFail("卡券不存在或已被领取~");
            }
        } else {
            return toResponsFail("非法请求~");
        }

    }



    /**
     * 兑换优惠券
     */
    @RequestMapping("exchange")
    public Object exchange(@LoginUser UserVo loginUser) {
        JSONObject jsonParam = getJsonRequest();
        //1.优惠码，不能为空
        String coupon_number = jsonParam.getString("coupon_number");
        if (StringUtils.isNullOrEmpty(coupon_number)) {
            return toResponsFail("兑换码不能为空");
        }

        //2.检查该优惠码，是否符合要求
        Map<String, Object> map = new HashedMap();
        map.put("couponNumber",coupon_number);
        map.put("isSend",1);
        List<CouponCodesVo> codesVoList =  apiCouponCodesService.queryList(map);

        if (null != codesVoList && codesVoList.size() > 0){
            CouponCodesVo couponCodesVo = codesVoList.get(0);
            if (couponCodesVo.getStatus().intValue() == 1){
                return toResponsFail("当前兑换码已被使用");
            }else   if (couponCodesVo.getStatus().intValue() == 2){
                return toResponsFail("当前兑换码已过期");
            }else {
                // 3.系统中未被使用的兑换码
                Map param = new HashMap();
                param.put("coupon_number", coupon_number);
                List<UserCouponVo> couponVos = apiUserCouponService.queryList(param);
                if (null != couponVos && couponVos.size() > 0) {
                    return toResponsFail("当前优惠码已经兑换");
                }else{
                    //4.未被任何人兑换过，则兑换成功
                    UserCouponVo userCouponVo = new UserCouponVo();
                    userCouponVo.setCoupon_id(couponCodesVo.getCouponId());
                    userCouponVo.setCoupon_code_id(couponCodesVo.getId());
                    userCouponVo.setCoupon_number(couponCodesVo.getCouponNumber());
                    userCouponVo.setUser_id(loginUser.getUserId());
                    userCouponVo.setAdd_time(new Date());
                    apiUserCouponService.save(userCouponVo);
                    return toResponsSuccess("兑换成功");
                }
            }
        }else{
            return toResponsFail("当前兑换码无效");
        }


    }

    /**
     * 　　填写手机号码，领券
     */
    @RequestMapping("newuser")
    public Object newuser(@LoginUser UserVo loginUser) {
        JSONObject jsonParam = getJsonRequest();
        //
        String phone = jsonParam.getString("phone");
        String smscode = jsonParam.getString("smscode");
        // 校验短信码
        SmsLogVo smsLogVo = apiUserService.querySmsCodeByUserId(loginUser.getUserId());
        if (null != smsLogVo && !smsLogVo.getSms_code().equals(smscode)) {
            return toResponsFail("短信校验失败");
        }
        // 更新手机号码
        if (!StringUtils.isNullOrEmpty(phone)) {
            if (phone.equals(loginUser.getMobile())) {
                loginUser.setMobile(phone);
                apiUserService.update(loginUser);
            }
        }
        // 判断是否是新用户
        if (!StringUtils.isNullOrEmpty(loginUser.getMobile())) {
            return toResponsFail("当前优惠券只能新用户领取");
        }
        // 是否领取过了
        Map params = new HashMap();
        params.put("user_id", loginUser.getUserId());
        params.put("send_type", 4);
        List<CouponVo> couponVos = apiCouponService.queryUserCoupons(params);
        if (null != couponVos && couponVos.size() > 0) {
            return toResponsFail("已经领取过，不能重复领取");
        }
        // 领取
        Map couponParam = new HashMap();
        couponParam.put("send_type", 4);
        CouponVo newCouponConfig = apiCouponService.queryMaxUserEnableCoupon(couponParam);
        if (null != newCouponConfig) {
            UserCouponVo userCouponVo = new UserCouponVo();
            userCouponVo.setAdd_time(new Date());
            userCouponVo.setCoupon_id(newCouponConfig.getId());
            userCouponVo.setCoupon_number(CharUtil.getRandomString(12));
            userCouponVo.setUser_id(loginUser.getUserId());
            apiUserCouponService.save(userCouponVo);
            return toResponsSuccess(userCouponVo);
        } else {
            return toResponsFail("领取失败");
        }
    }

    /**
     * 　　转发领取红包
     */
    @RequestMapping("transActivit")
    public Object transActivit(@LoginUser UserVo loginUser, String sourceKey, Long referrer) {
        JSONObject jsonParam = getJsonRequest();
        // 是否领取过了
        Map params = new HashMap();
        params.put("user_id", loginUser.getUserId());
        params.put("send_type", 2);
        params.put("source_key", sourceKey);
        List<CouponVo> couponVos = apiCouponService.queryUserCoupons(params);
        if (null != couponVos && couponVos.size() > 0) {
            return toResponsObject(2, "已经领取过", couponVos);
        }
        // 领取
        Map couponParam = new HashMap();
        couponParam.put("send_type", 2);
        CouponVo newCouponConfig = apiCouponService.queryMaxUserEnableCoupon(couponParam);
        if (null != newCouponConfig) {
            UserCouponVo userCouponVo = new UserCouponVo();
            userCouponVo.setAdd_time(new Date());
            userCouponVo.setCoupon_id(newCouponConfig.getId());
            userCouponVo.setCoupon_number(CharUtil.getRandomString(12));
            userCouponVo.setUser_id(loginUser.getUserId());
            userCouponVo.setSource_key(sourceKey);
            userCouponVo.setReferrer(referrer);
            apiUserCouponService.save(userCouponVo);
            //
            List<UserCouponVo> userCouponVos = new ArrayList();
            userCouponVos.add(userCouponVo);
            //
            params = new HashMap();
            params.put("user_id", loginUser.getUserId());
            params.put("send_type", 2);
            params.put("source_key", sourceKey);
            couponVos = apiCouponService.queryUserCoupons(params);
            return toResponsSuccess(couponVos);
        } else {
            return toResponsFail("领取失败");
        }
    }
}
