package com.platform.entity.stat;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单商品Vo
 *
 * @author bjsonghongxu
 * @create 2018-07-19 18:01
 **/
public class OrderGoodsVo implements Serializable {

    ///订单id
    private Integer orderid;
    ///商品id
    private Integer goodsid;
    ///订单商品价格
    private BigDecimal price;
    //零售价
    private BigDecimal marketprice;
    //成本价
    private BigDecimal costprice;
    //商品名称
    private String title;
    //商品缩率图
    private  String thumb;
    //类型
    private Integer type;
    ///
    private String goodssn;
    ////
    private String productsn;

    ///件数
    private Integer total;


    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(BigDecimal marketprice) {
        this.marketprice = marketprice;
    }

    public BigDecimal getCostprice() {
        return costprice;
    }

    public void setCostprice(BigDecimal costprice) {
        this.costprice = costprice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getGoodssn() {
        return goodssn;
    }

    public void setGoodssn(String goodssn) {
        this.goodssn = goodssn;
    }

    public String getProductsn() {
        return productsn;
    }

    public void setProductsn(String productsn) {
        this.productsn = productsn;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
