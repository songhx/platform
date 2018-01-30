package com.platform.entity;

import java.io.Serializable;

/**
 * Created by zuimeideshiguang on 18/1/29.
 * 优惠券码表
 */
public class CouponCodesEntity implements Serializable {

    private Integer id; //  '自增主键',
    private Integer couponId; //  '卡圈id',
    private String couponNumber; //  '兑换码',
    private Integer isSend; //  '是否派送 0 否 1 是 （线下方式默认已派送）',
    private Integer status; //  '状态  0 正常  1 已使用 2 过期',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
