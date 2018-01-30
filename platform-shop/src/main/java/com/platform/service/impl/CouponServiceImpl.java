package com.platform.service.impl;

import com.platform.dao.*;
import com.platform.entity.*;
import com.platform.service.CouponService;
import com.platform.utils.R;
import com.platform.utils.RedeemCodeUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.SequencedHashMap;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-19 12:53:26
 */
@Service("couponService")
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponDao couponDao;
    @Autowired
    private UserCouponDao userCouponDao;
    @Autowired
    private CouponGoodsDao couponGoodsDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CouponCodesDao couponCodesDao;

    @Override
    public CouponEntity queryObject(Integer id) {
        return couponDao.queryObject(id);
    }

    @Override
    public List<CouponEntity> queryList(Map<String, Object> map) {
        return couponDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return couponDao.queryTotal(map);
    }

    @Override
    public int save(CouponEntity coupon) {
        couponDao.save(coupon);
        createCouponCodes(coupon);//创建优惠码
        return 1;
    }

    @Override
    public int update(CouponEntity coupon) {
        couponDao.update(coupon);
        createCouponCodes(coupon);//修改 , 增量
        return 1;
    }

    @Override
    public int delete(Integer id) {
        int rs  = 0;
        if (!checkSended(id)){
            couponDao.delete(id);
            rs  = 1;
        }
        return rs;
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        int rs  = 0;
        boolean d = false;
        for (Integer id : ids){
            if (checkSended(id)){
                d = true;
            }
        }
         if (!d){
             couponDao.deleteBatch(ids);
             rs  = 1;
         }
        return rs;
    }

    private boolean checkSended(Integer id){
        Map<String, Object> map = new HashedMap();
        map.put("couponId",id);
        return  couponCodesDao.queryTotal(map) > 0;
    }

    @Override
    public R publish(Map<String, Object> params) {
        // 发放方式 0：按订单发放 1：按用户发放 2:商品转发送券 3：按商品发放 4:新用户注册  5：线下发放 6评价好评红包（固定或随机红包）
        Integer sendType = MapUtils.getInteger(params, "sendType");
        Integer couponId = MapUtils.getInteger(params, "couponId");
        if (null == sendType) {
            return R.error("发放方式不能为空");
        }
        if (null == couponId) {
            return R.error("优惠券不能为空");
        }

        if (1 == sendType) {
            String userIds = MapUtils.getString(params, "userIds"); // 下发用户逗号分割
            if (StringUtils.isEmpty(userIds)) {
                return R.error("用户不能为空");
            }
            Map<String, Object> map = new HashedMap();
            map.put("couponId", couponId);
            map.put("isSend", 0);
            map.put("offset", 1);
            map.put("limit", 1);
            int num = couponCodesDao.queryCount(map);
            if (num < userIds.split(",").length){
                return R.error("优惠券张数不足！");
            }

            //是否发送短信通知
            boolean sendSms = "true".equals(MapUtils.getString(params, "sendSms"));
            for (String strUserId : userIds.split(",")) {
                if (StringUtils.isEmpty(strUserId)) {
                    continue;
                }
                Integer userId = Integer.valueOf(strUserId);
                UserCouponEntity userCouponVo = new UserCouponEntity();
                sendCouponCode(userCouponVo,couponId);
                userCouponVo.setUserId(userId);
                userCouponVo.setCouponId(couponId);
                userCouponVo.setAddTime(new Date());
                userCouponDao.save(userCouponVo);

                if (sendSms) {
                    UserEntity userEntity = userDao.queryObject(userId);
                    // todo 发送短信

                }
            }
        } else if (3 == sendType) {
            String goodsIds = MapUtils.getString(params, "goodsIds"); // 下发商品逗号分割
            if (StringUtils.isEmpty(goodsIds)) {
                return R.error("商品Id不能为空");
            }
            for (String goodsId : goodsIds.split(",")) {
                if (StringUtils.isEmpty(goodsId)) {
                    continue;
                }
                CouponGoodsEntity couponGoodsVo = new CouponGoodsEntity();
                couponGoodsVo.setCouponId(couponId);
                couponGoodsVo.setGoodsId(Integer.valueOf(goodsId));
                couponGoodsDao.save(couponGoodsVo);
            }
        } else {
            return R.error("此类优惠券不支持手动发放");
        }
        return R.ok("发放成功");
    }

    //发放优惠码
    private void sendCouponCode( UserCouponEntity userCouponVo, Integer id){
        Map<String, Object> map = new HashedMap();
        map.put("couponId", id);
        map.put("isSend", 0);
        List<CouponCodesEntity> codesEntityList = couponCodesDao.queryList(map);
        if(null != codesEntityList && codesEntityList.size() > 0) {
            userCouponVo.setCouponNumber(codesEntityList.get(0).getCouponNumber());
            userCouponVo.setCouponCodeId(codesEntityList.get(0).getId());

            CouponCodesEntity cce1 = codesEntityList.get(0);
            cce1.setIsSend(1);
            couponCodesDao.update(cce1);
        }

    }



    ///创建优惠码
    private void createCouponCodes(CouponEntity coupon){
        int newSheetNum = coupon.getMaxSheet();
        Map<String, Object> map = new HashedMap();
        map.put("couponId", coupon.getId());
        int num = couponCodesDao.queryCount(map);
        if (newSheetNum > num){
            int addNum = newSheetNum - num;
            CouponCodesEntity cce = null;
            for (int i = 0 ; i < addNum ; i ++){
                cce = new CouponCodesEntity();
                cce.setCouponId(coupon.getId());
                cce.setCouponNumber(RedeemCodeUtils.createBigSmallLetterStrOrNumberRadom(32));
                cce.setIsSend((coupon.getSendType() != null && coupon.getSendType().intValue() == 5) ? 1 : 0);
                couponCodesDao.save(cce);
            }
        }
    }
}
