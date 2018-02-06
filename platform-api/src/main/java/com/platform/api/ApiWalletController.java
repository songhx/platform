package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.entity.*;
import com.platform.service.*;
import com.platform.util.ApiBaseAction;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 钱包业务
 *
 * @author bjsonghongxu
 * @create 2018-02-05 14:56
 **/
@RestController
@RequestMapping("/api/wallet")
public class ApiWalletController extends ApiBaseAction {

    @Autowired
    private ApiUserService userService;

    @Autowired
    private ApiUserWithdrawService apiUserWithdrawService;

    @Autowired
    private ApiCouponService apiCouponService;

    @Autowired
    private ApiUserCouponService apiUserCouponService;

    @Autowired
    private ApiCouponCodesService apiCouponCodesService;


    /**
     * 折现操作
     * @param loginUser
     * @return
     */
    @RequestMapping("discount")
    public Object discount(@LoginUser UserVo loginUser) {
        JSONObject jsonObject = this.getJsonRequest();
        Integer id = null;
        Integer coupon_id = null;
        Integer coupon_code_id = null;
        if (jsonObject != null){
            id = jsonObject.getInteger("id");
            coupon_id = jsonObject.getInteger("coupon_id");
            coupon_code_id = jsonObject.getInteger("coupon_code_id");
        }
        if (loginUser.getUserId() == null){
            return toResponsFail("折现操作失败，用户不存在！");
        }
        if (coupon_id == null || coupon_code_id == null){
            return toResponsFail("折现操作失败，参数错误！");
        }
        try {
            //验证该卡券是否可以折现
            CouponVo couponVo = apiCouponService.queryObject(coupon_id);
            CouponCodesVo codesVo = apiCouponCodesService.queryObject(coupon_code_id);
            //卡券存在且兑换码未被使用
            if (null != couponVo && couponVo.getIsDiscount().intValue() == 0 &&  codesVo != null && codesVo.getStatus().intValue() == 0){
                ///增加账户余额
                Map<String,Object> map = new HashedMap();
                map.put("addBalance","addBalance");
                map.put("money",calDiscount(couponVo.getType_money().doubleValue(),couponVo.getDiscountRate()) );
                map.put("userId",loginUser.getUserId());
                int rs = userService.updateUserWallet(map);
                if (rs > 0){
                    UserCouponVo ucv = new UserCouponVo();
                    ucv.setId(id);
                    ucv.setIsUsed(1);
                    ucv.setUsed_time(new Date());
                    apiUserCouponService.update(ucv);

                    CouponCodesVo couponCodesVo = new CouponCodesVo();
                    couponCodesVo.setCouponId(codesVo.getId());
                    couponCodesVo.setStatus(1); //标注该码被使用
                    apiCouponCodesService.update(couponCodesVo);

                    return toResponsMsgSuccess("恭喜您，折现成功！");

                }else{
                    return toResponsFail("折现操作失败，网络出现错误！");
                }

            }else {
                return toResponsFail("折现操作失败，该礼品卡不存在或不能折现！");
            }

        }catch (Exception e){
            return toResponsFail("折现操作失败，网络出现错误！");
        }
    }

    private double calDiscount(Double money , Double discountRate ){
        double discount = 0.00;
        if (money != null && discountRate != null){
            discount = Double.valueOf(new java.text.DecimalFormat("#.00").format(money * discountRate / 100));
        }
        return  discount;
    }


    /**
     * 取现申请
     * @param loginUser
     * @param money
     * @return
     */
    @RequestMapping("withDraw")
    public Object withDraw(@LoginUser UserVo loginUser, Double money) {
        JSONObject jsonObject = this.getJsonRequest();
        if(jsonObject != null){
            money = jsonObject.getDouble("money");
        }
        if (loginUser.getUserId() == null){
            return toResponsFail("提现申请失败，用户不存在！");
        }
        UserVo user = userService.queryObject(loginUser.getUserId());
        if (user == null){
            return toResponsFail("提现申请失败，用户不存在！");
        }

        if (user.getBalance() == null || (user.getBalance() != null &&  user.getBalance().doubleValue() - money.doubleValue() < 0)){
            return toResponsFail("账户余额不足！");
        }
        if (null == money || (money != null && money.intValue() <=0 )){
            return toResponsFail("提现申请失败，提现金额不符合要求！");
        }
        try {

            ///冻结申请提现金额
            Map<String,Object> map = new HashedMap();
            map.put("reduceBalance","reduceBalance");
            map.put("addFreeze","addFreeze");
            map.put("money",money);
            map.put("userId",loginUser.getUserId());
            int rs = userService.updateUserWallet(map);

            if (rs > 0){
                Date time = new Date();
                //记录提现申请
                UserWithdrawVo userWithdrawVo = new UserWithdrawVo();
                userWithdrawVo.setMobile(user.getMobile());
                userWithdrawVo.setNickName(user.getNickname());
                userWithdrawVo.setUserId(Integer.parseInt(String.valueOf(user.getUserId())));
                userWithdrawVo.setWeixinOpenid(user.getWeixin_openid());
                userWithdrawVo.setUpdateTime(time);
                userWithdrawVo.setCreateTime(time);
                userWithdrawVo.setStatus(0);
                userWithdrawVo.setWithdrawMoney(money);

                apiUserWithdrawService.save(userWithdrawVo);
                return toResponsMsgSuccess("提现申请成功！");
            }else{
                return toResponsFail("提现申请失败，网络出现错误！");
            }

        }catch (Exception e){
            return toResponsFail("提现申请失败，网络出现错误！");
        }


    }


    /**
     * 提现申请列表
     * @param loginUser
     * @return
     */
    @RequestMapping("withDrawList")
    public Object withDrawList(@LoginUser UserVo loginUser) {
        if (loginUser.getUserId() != null){
            return toResponsFail("提现申请失败，用户不存在！");
        }
        Map<String , Object> map = new HashedMap();
        map.put("userId",loginUser.getUserId());
        List<UserWithdrawVo> userWithdrawVoList = apiUserWithdrawService.queryList(map);
        return  toResponsSuccessForSelect(userWithdrawVoList);

    }


}
