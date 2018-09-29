package com.platform.dao;

import com.platform.entity.CouponCodesVo;

import java.util.Map;

/**
 * Created by zuimeideshiguang on 18/1/29.
 */
public interface ApiCouponCodesMapper extends BaseDao<CouponCodesVo> {

    int  queryCount(Map<String, Object> map);


}
