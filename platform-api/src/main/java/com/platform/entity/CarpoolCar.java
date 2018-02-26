package com.platform.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zuimeideshiguang on 18/2/13.
 * 车辆信息
 */
@Table(name = "carpool_car")
public class CarpoolCar implements Serializable {

    @Id
    private Integer id; // '自增id',

    private Integer userId; // '发布人id',
    private Integer carType; // '车辆类型  1 轿车 2 SUV  3 出租车',
    private Integer carColor; // '车辆颜色 1 白色 2 黑色 3 红色  4 银色  5 灰色  6蓝色 7 其他',
    private String carNo; // '车牌号',
    private String carBrand; //品牌
    private String otherType; // 其他类型
    private String otherColor; //其他颜色
    private BigDecimal carPrice;//车辆价格
    private Date expirationTime;//驾照到期时间

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

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Integer getCarColor() {
        return carColor;
    }

    public void setCarColor(Integer carColor) {
        this.carColor = carColor;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getOtherType() {
        return otherType;
    }

    public void setOtherType(String otherType) {
        this.otherType = otherType;
    }

    public String getOtherColor() {
        return otherColor;
    }

    public void setOtherColor(String otherColor) {
        this.otherColor = otherColor;
    }

    public BigDecimal getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(BigDecimal carPrice) {
        this.carPrice = carPrice;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }
}
