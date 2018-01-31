package com.platform.dao;

import com.platform.entity.CouponCodesEntity;

import java.util.Map;

/**
 * Created by zuimeideshiguang on 18/1/29.
 */
public interface CouponCodesDao extends BaseDao<CouponCodesEntity> {

    int  queryCount(Map<String, Object> map);


}
