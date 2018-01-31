package com.platform.service;

import com.platform.dao.ApiCouponCodesMapper;

import com.platform.entity.CouponCodesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zuimeideshiguang on 18/1/29.
 */
@Service
public class ApiCouponCodesService  {
    @Autowired
    private ApiCouponCodesMapper apiCouponCodesMapper;

    public CouponCodesVo queryObject(Integer id) {
        return apiCouponCodesMapper.queryObject(id);
    }

    public List<CouponCodesVo> queryList(Map<String, Object> map) {
        return apiCouponCodesMapper.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return apiCouponCodesMapper.queryTotal(map);
    }


    public int save(CouponCodesVo couponCodesEntity) {
        return apiCouponCodesMapper.save(couponCodesEntity);
    }

    public int update(CouponCodesVo couponCodesEntity) {
        return apiCouponCodesMapper.update(couponCodesEntity);
    }

    public int delete(Integer id) {
        return apiCouponCodesMapper.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return apiCouponCodesMapper.deleteBatch(ids);
    }


}
