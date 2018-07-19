package com.platform.entity.stat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单统计Vo
 *
 * @author bjsonghongxu
 * @create 2018-07-19 15:22
 **/
public class OrderStatVo implements Serializable {
    //订单id
    private Integer id;
    //分销商id
    private Integer agentid;
    //订单编码
    private String ordersn;
    //价格
    private BigDecimal price;
    //商品价格
    private BigDecimal goodsprice;
    //
    private BigDecimal discountprice;
    //订单状态
    private Integer status;
    //支付方式
    private Integer paytype;
    //创建时间
    private Integer createtime;
    //完成时间
    private Integer finishtime;
    //支付时间
    private Integer paytime;

    //分销商姓名
    private String realname;
    //分销商电话
    private String mobile;
    //分销商昵称
    private String nickname;
    //头像
    private String avatar;
    //等级
    private Integer level;
    //商家名称
    private String merchname;

    ////订单商品相关
    private List<OrderGoodsVo>  orderGoodsVoList;


    ////金额分成相关

    //平台佣金
    private BigDecimal platformCommission;
    //一级代理商
    private BigDecimal commission1;
    //二级代理商
    private BigDecimal commission2;
    //三级代理商
    private BigDecimal commission3;
    //销售佣金
    private BigDecimal salerCommission;
    //省佣金
    private BigDecimal provinceCommission;
    //市佣金
    private BigDecimal cityCommission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public String getOrdersn() {
        return ordersn;
    }

    public void setOrdersn(String ordersn) {
        this.ordersn = ordersn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(BigDecimal goodsprice) {
        this.goodsprice = goodsprice;
    }

    public BigDecimal getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(BigDecimal discountprice) {
        this.discountprice = discountprice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public Integer getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Integer finishtime) {
        this.finishtime = finishtime;
    }

    public Integer getPaytime() {
        return paytime;
    }

    public void setPaytime(Integer paytime) {
        this.paytime = paytime;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getMerchname() {
        return merchname;
    }

    public void setMerchname(String merchname) {
        this.merchname = merchname;
    }

    public List<OrderGoodsVo> getOrderGoodsVoList() {
        return orderGoodsVoList;
    }

    public void setOrderGoodsVoList(List<OrderGoodsVo> orderGoodsVoList) {
        this.orderGoodsVoList = orderGoodsVoList;
    }

    public BigDecimal getPlatformCommission() {
        return platformCommission;
    }

    public void setPlatformCommission(BigDecimal platformCommission) {
        this.platformCommission = platformCommission;
    }

    public BigDecimal getCommission1() {
        return commission1;
    }

    public void setCommission1(BigDecimal commission1) {
        this.commission1 = commission1;
    }

    public BigDecimal getCommission2() {
        return commission2;
    }

    public void setCommission2(BigDecimal commission2) {
        this.commission2 = commission2;
    }

    public BigDecimal getCommission3() {
        return commission3;
    }

    public void setCommission3(BigDecimal commission3) {
        this.commission3 = commission3;
    }

    public BigDecimal getSalerCommission() {
        return salerCommission;
    }

    public void setSalerCommission(BigDecimal salerCommission) {
        this.salerCommission = salerCommission;
    }

    public BigDecimal getProvinceCommission() {
        return provinceCommission;
    }

    public void setProvinceCommission(BigDecimal provinceCommission) {
        this.provinceCommission = provinceCommission;
    }

    public BigDecimal getCityCommission() {
        return cityCommission;
    }

    public void setCityCommission(BigDecimal cityCommission) {
        this.cityCommission = cityCommission;
    }
}
