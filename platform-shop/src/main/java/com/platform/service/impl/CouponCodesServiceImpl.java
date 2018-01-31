package com.platform.service.impl;

import com.platform.dao.CouponCodesDao;
import com.platform.entity.CouponCodesEntity;
import com.platform.service.CouponCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zuimeideshiguang on 18/1/29.
 */
@Service("couponCodesService")
public class CouponCodesServiceImpl implements CouponCodesService {
    @Autowired
    private CouponCodesDao couponCodesDao;

    @Override
    public CouponCodesEntity queryObject(Integer id) {
        return couponCodesDao.queryObject(id);
    }

    @Override
    public List<CouponCodesEntity> queryList(Map<String, Object> map) {
        return couponCodesDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return couponCodesDao.queryTotal(map);
    }

    @Override
    public int save(CouponCodesEntity couponCodesEntity) {
        return couponCodesDao.save(couponCodesEntity);
    }

    @Override
    public int update(CouponCodesEntity couponCodesEntity) {
        return couponCodesDao.update(couponCodesEntity);
    }

    @Override
    public int delete(Integer id) {
        return couponCodesDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return couponCodesDao.deleteBatch(ids);
    }


}
