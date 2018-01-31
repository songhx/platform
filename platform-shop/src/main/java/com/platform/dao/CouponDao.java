package com.platform.dao;

import com.platform.entity.CouponEntity;

import java.util.Map;

/**
 * Dao
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-19 12:53:26
 */
public interface CouponDao extends BaseDao<CouponEntity> {

    int  updateCouponSheet(Map<String, Object> map);
}
