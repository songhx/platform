package com.platform.entity;

import java.io.Serializable;


/**
 * @author bjsonghongxu
 * @date 2017-08-15 08:03:41
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    //
    private String avatarUrl;
    //
    private String city;
    //
    private Integer gender;
    //
    private String nickName;
    //
    private String province;

    //手机号码
    private String mobile;

    private String wxOpenid;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }
}
