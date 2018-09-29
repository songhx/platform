package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户提现记录
 *
 * @author bjsonghongxu
 * @create 2018-02-05 15:37
 **/
public class UserWithdrawVo implements Serializable {

    private Integer id; // '主键id',
    private Integer userId; // '会员id',
    private String userName; // '真实姓名',
    private String nickName; // '微信昵称',
    private String mobile; // '手机号码',
    private String weixinOpenid; // 'openid',
    private Double withdrawMoney; // '申请取现金额',
    private Date createTime;
    private Date updateTime;
    private Integer status; // '状态  0 申请中  1 已发放  2拒绝 ',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWeixinOpenid() {
        return weixinOpenid;
    }

    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid;
    }

    public Double getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(Double withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
