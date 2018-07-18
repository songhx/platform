package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 实体
 * 表名 ims_ewei_shop_order
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-18 18:07:31
 */
public class ImsEweiShopOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //
    private Integer uniacid;
    //
    private String openid;
    //
    private Integer agentid;
    //
    private String ordersn;
    //
    private BigDecimal price;
    //
    private BigDecimal goodsprice;
    //
    private BigDecimal discountprice;
    //
    private Integer status;
    //
    private Integer paytype;
    //
    private String transid;
    //
    private String remark;
    //
    private Integer addressid;
    //
    private BigDecimal dispatchprice;
    //
    private Integer dispatchid;
    //
    private Integer createtime;
    //
    private Integer dispatchtype;
    //
    private String carrier;
    //
    private Integer refundid;
    //
    private Integer iscomment;
    //
    private Integer creditadd;
    //
    private Integer deleted;
    //
    private Integer userdeleted;
    //
    private Integer finishtime;
    //
    private Integer paytime;
    //
    private String expresscom;
    //
    private String expresssn;
    //
    private String express;
    //
    private Integer sendtime;
    //
    private Integer fetchtime;
    //
    private Integer cash;
    //
    private Integer canceltime;
    //
    private Integer cancelpaytime;
    //
    private Integer refundtime;
    //
    private Integer isverify;
    //
    private Integer verified;
    //
    private String verifyopenid;
    //
    private String verifycode;
    //
    private Integer verifytime;
    //
    private Integer verifystoreid;
    //
    private BigDecimal deductprice;
    //
    private Integer deductcredit;
    //
    private BigDecimal deductcredit2;
    //
    private BigDecimal deductenough;
    //
    private Integer virtual;
    //
    private String virtualInfo;
    //
    private String virtualStr;
    //
    private String address;
    //
    private Integer sysdeleted;
    //
    private Integer ordersn2;
    //
    private BigDecimal changeprice;
    //
    private BigDecimal changedispatchprice;
    //
    private BigDecimal oldprice;
    //
    private BigDecimal olddispatchprice;
    //
    private Integer isvirtual;
    //
    private Integer couponid;
    //
    private BigDecimal couponprice;
    //
    private String diyformdata;
    //
    private String diyformfields;
    //
    private Integer diyformid;
    //
    private Integer storeid;
    //
    private Integer printstate;
    //
    private Integer printstate2;
    //
    private String addressSend;
    //
    private Integer refundstate;
    //
    private String closereason;
    //
    private String remarksaler;
    //
    private String remarkclose;
    //
    private String remarksend;
    //
    private Integer ismr;
    //
    private BigDecimal isdiscountprice;
    //
    private Integer isvirtualsend;
    //
    private String virtualsendInfo;
    //
    private String verifyinfo;
    //
    private Integer verifytype;
    //
    private String verifycodes;
    //
    private String invoicename;
    //
    private Integer merchid;
    //
    private Integer ismerch;
    //
    private Integer parentid;
    //
    private Integer isparent;
    //
    private BigDecimal grprice;
    //
    private Integer merchshow;
    //
    private BigDecimal merchdeductenough;
    //
    private Integer couponmerchid;
    //
    private Integer isglobonus;
    //
    private Integer merchapply;
    //
    private Integer isabonus;
    //
    private Integer isborrow;
    //
    private String borrowopenid;
    //
    private BigDecimal merchisdiscountprice;
    //
    private Integer apppay;
    //
    private BigDecimal coupongoodprice;
    //
    private BigDecimal buyagainprice;
    //
    private Integer authorid;
    //
    private Integer isauthor;
    //
    private Integer ispackage;
    //
    private Integer packageid;
    //
    private BigDecimal taskdiscountprice;
    //
    private BigDecimal seckilldiscountprice;
    //
    private Integer verifyendtime;
    //
    private Integer willcancelmessage;
    //
    private Integer sendtype;
    //
    private BigDecimal lotterydiscountprice;
    //
    private Integer contype;
    //
    private Integer wxid;
    //
    private String wxcardid;
    //
    private String wxcode;
    //
    private String dispatchkey;
    //
    private Integer quickid;
    //
    private Integer istrade;
    //
    private Integer isnewstore;
    //
    private Integer liveid;
    //
    private String ordersnTrade;
    //
    private Integer tradestatus;
    //
    private Integer tradepaytype;
    //
    private Integer tradepaytime;
    //
    private BigDecimal dowpayment;
    //
    private BigDecimal betweenprice;
    //
    private Integer isshare;
    //
    private String officcode;
    //
    private String wxappPrepayId;
    //
    private Integer cashtime;
    //
    private Integer iswxappcreate;
    //
    private String randomCode;
    //
    private String printTemplate;
    //
    private Integer cityExpressState;
    //
    private Integer isCashier;
    //
    private BigDecimal commissionmoney;
    //
    private Integer iscycelbuy;
    //
    private Integer cycelbuyPredictTime;
    //
    private String cycelbuyPeriodic;
    //
    private String invoiceImg;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：
     */
    public void setUniacid(Integer uniacid) {
        this.uniacid = uniacid;
    }

    /**
     * 获取：
     */
    public Integer getUniacid() {
        return uniacid;
    }
    /**
     * 设置：
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取：
     */
    public String getOpenid() {
        return openid;
    }
    /**
     * 设置：
     */
    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    /**
     * 获取：
     */
    public Integer getAgentid() {
        return agentid;
    }
    /**
     * 设置：
     */
    public void setOrdersn(String ordersn) {
        this.ordersn = ordersn;
    }

    /**
     * 获取：
     */
    public String getOrdersn() {
        return ordersn;
    }
    /**
     * 设置：
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取：
     */
    public BigDecimal getPrice() {
        return price;
    }
    /**
     * 设置：
     */
    public void setGoodsprice(BigDecimal goodsprice) {
        this.goodsprice = goodsprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getGoodsprice() {
        return goodsprice;
    }
    /**
     * 设置：
     */
    public void setDiscountprice(BigDecimal discountprice) {
        this.discountprice = discountprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getDiscountprice() {
        return discountprice;
    }
    /**
     * 设置：
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置：
     */
    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    /**
     * 获取：
     */
    public Integer getPaytype() {
        return paytype;
    }
    /**
     * 设置：
     */
    public void setTransid(String transid) {
        this.transid = transid;
    }

    /**
     * 获取：
     */
    public String getTransid() {
        return transid;
    }
    /**
     * 设置：
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 设置：
     */
    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    /**
     * 获取：
     */
    public Integer getAddressid() {
        return addressid;
    }
    /**
     * 设置：
     */
    public void setDispatchprice(BigDecimal dispatchprice) {
        this.dispatchprice = dispatchprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getDispatchprice() {
        return dispatchprice;
    }
    /**
     * 设置：
     */
    public void setDispatchid(Integer dispatchid) {
        this.dispatchid = dispatchid;
    }

    /**
     * 获取：
     */
    public Integer getDispatchid() {
        return dispatchid;
    }
    /**
     * 设置：
     */
    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取：
     */
    public Integer getCreatetime() {
        return createtime;
    }
    /**
     * 设置：
     */
    public void setDispatchtype(Integer dispatchtype) {
        this.dispatchtype = dispatchtype;
    }

    /**
     * 获取：
     */
    public Integer getDispatchtype() {
        return dispatchtype;
    }
    /**
     * 设置：
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    /**
     * 获取：
     */
    public String getCarrier() {
        return carrier;
    }
    /**
     * 设置：
     */
    public void setRefundid(Integer refundid) {
        this.refundid = refundid;
    }

    /**
     * 获取：
     */
    public Integer getRefundid() {
        return refundid;
    }
    /**
     * 设置：
     */
    public void setIscomment(Integer iscomment) {
        this.iscomment = iscomment;
    }

    /**
     * 获取：
     */
    public Integer getIscomment() {
        return iscomment;
    }
    /**
     * 设置：
     */
    public void setCreditadd(Integer creditadd) {
        this.creditadd = creditadd;
    }

    /**
     * 获取：
     */
    public Integer getCreditadd() {
        return creditadd;
    }
    /**
     * 设置：
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取：
     */
    public Integer getDeleted() {
        return deleted;
    }
    /**
     * 设置：
     */
    public void setUserdeleted(Integer userdeleted) {
        this.userdeleted = userdeleted;
    }

    /**
     * 获取：
     */
    public Integer getUserdeleted() {
        return userdeleted;
    }
    /**
     * 设置：
     */
    public void setFinishtime(Integer finishtime) {
        this.finishtime = finishtime;
    }

    /**
     * 获取：
     */
    public Integer getFinishtime() {
        return finishtime;
    }
    /**
     * 设置：
     */
    public void setPaytime(Integer paytime) {
        this.paytime = paytime;
    }

    /**
     * 获取：
     */
    public Integer getPaytime() {
        return paytime;
    }
    /**
     * 设置：
     */
    public void setExpresscom(String expresscom) {
        this.expresscom = expresscom;
    }

    /**
     * 获取：
     */
    public String getExpresscom() {
        return expresscom;
    }
    /**
     * 设置：
     */
    public void setExpresssn(String expresssn) {
        this.expresssn = expresssn;
    }

    /**
     * 获取：
     */
    public String getExpresssn() {
        return expresssn;
    }
    /**
     * 设置：
     */
    public void setExpress(String express) {
        this.express = express;
    }

    /**
     * 获取：
     */
    public String getExpress() {
        return express;
    }
    /**
     * 设置：
     */
    public void setSendtime(Integer sendtime) {
        this.sendtime = sendtime;
    }

    /**
     * 获取：
     */
    public Integer getSendtime() {
        return sendtime;
    }
    /**
     * 设置：
     */
    public void setFetchtime(Integer fetchtime) {
        this.fetchtime = fetchtime;
    }

    /**
     * 获取：
     */
    public Integer getFetchtime() {
        return fetchtime;
    }
    /**
     * 设置：
     */
    public void setCash(Integer cash) {
        this.cash = cash;
    }

    /**
     * 获取：
     */
    public Integer getCash() {
        return cash;
    }
    /**
     * 设置：
     */
    public void setCanceltime(Integer canceltime) {
        this.canceltime = canceltime;
    }

    /**
     * 获取：
     */
    public Integer getCanceltime() {
        return canceltime;
    }
    /**
     * 设置：
     */
    public void setCancelpaytime(Integer cancelpaytime) {
        this.cancelpaytime = cancelpaytime;
    }

    /**
     * 获取：
     */
    public Integer getCancelpaytime() {
        return cancelpaytime;
    }
    /**
     * 设置：
     */
    public void setRefundtime(Integer refundtime) {
        this.refundtime = refundtime;
    }

    /**
     * 获取：
     */
    public Integer getRefundtime() {
        return refundtime;
    }
    /**
     * 设置：
     */
    public void setIsverify(Integer isverify) {
        this.isverify = isverify;
    }

    /**
     * 获取：
     */
    public Integer getIsverify() {
        return isverify;
    }
    /**
     * 设置：
     */
    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    /**
     * 获取：
     */
    public Integer getVerified() {
        return verified;
    }
    /**
     * 设置：
     */
    public void setVerifyopenid(String verifyopenid) {
        this.verifyopenid = verifyopenid;
    }

    /**
     * 获取：
     */
    public String getVerifyopenid() {
        return verifyopenid;
    }
    /**
     * 设置：
     */
    public void setVerifycode(String verifycode) {
        this.verifycode = verifycode;
    }

    /**
     * 获取：
     */
    public String getVerifycode() {
        return verifycode;
    }
    /**
     * 设置：
     */
    public void setVerifytime(Integer verifytime) {
        this.verifytime = verifytime;
    }

    /**
     * 获取：
     */
    public Integer getVerifytime() {
        return verifytime;
    }
    /**
     * 设置：
     */
    public void setVerifystoreid(Integer verifystoreid) {
        this.verifystoreid = verifystoreid;
    }

    /**
     * 获取：
     */
    public Integer getVerifystoreid() {
        return verifystoreid;
    }
    /**
     * 设置：
     */
    public void setDeductprice(BigDecimal deductprice) {
        this.deductprice = deductprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getDeductprice() {
        return deductprice;
    }
    /**
     * 设置：
     */
    public void setDeductcredit(Integer deductcredit) {
        this.deductcredit = deductcredit;
    }

    /**
     * 获取：
     */
    public Integer getDeductcredit() {
        return deductcredit;
    }
    /**
     * 设置：
     */
    public void setDeductcredit2(BigDecimal deductcredit2) {
        this.deductcredit2 = deductcredit2;
    }

    /**
     * 获取：
     */
    public BigDecimal getDeductcredit2() {
        return deductcredit2;
    }
    /**
     * 设置：
     */
    public void setDeductenough(BigDecimal deductenough) {
        this.deductenough = deductenough;
    }

    /**
     * 获取：
     */
    public BigDecimal getDeductenough() {
        return deductenough;
    }
    /**
     * 设置：
     */
    public void setVirtual(Integer virtual) {
        this.virtual = virtual;
    }

    /**
     * 获取：
     */
    public Integer getVirtual() {
        return virtual;
    }
    /**
     * 设置：
     */
    public void setVirtualInfo(String virtualInfo) {
        this.virtualInfo = virtualInfo;
    }

    /**
     * 获取：
     */
    public String getVirtualInfo() {
        return virtualInfo;
    }
    /**
     * 设置：
     */
    public void setVirtualStr(String virtualStr) {
        this.virtualStr = virtualStr;
    }

    /**
     * 获取：
     */
    public String getVirtualStr() {
        return virtualStr;
    }
    /**
     * 设置：
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置：
     */
    public void setSysdeleted(Integer sysdeleted) {
        this.sysdeleted = sysdeleted;
    }

    /**
     * 获取：
     */
    public Integer getSysdeleted() {
        return sysdeleted;
    }
    /**
     * 设置：
     */
    public void setOrdersn2(Integer ordersn2) {
        this.ordersn2 = ordersn2;
    }

    /**
     * 获取：
     */
    public Integer getOrdersn2() {
        return ordersn2;
    }
    /**
     * 设置：
     */
    public void setChangeprice(BigDecimal changeprice) {
        this.changeprice = changeprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getChangeprice() {
        return changeprice;
    }
    /**
     * 设置：
     */
    public void setChangedispatchprice(BigDecimal changedispatchprice) {
        this.changedispatchprice = changedispatchprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getChangedispatchprice() {
        return changedispatchprice;
    }
    /**
     * 设置：
     */
    public void setOldprice(BigDecimal oldprice) {
        this.oldprice = oldprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getOldprice() {
        return oldprice;
    }
    /**
     * 设置：
     */
    public void setOlddispatchprice(BigDecimal olddispatchprice) {
        this.olddispatchprice = olddispatchprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getOlddispatchprice() {
        return olddispatchprice;
    }
    /**
     * 设置：
     */
    public void setIsvirtual(Integer isvirtual) {
        this.isvirtual = isvirtual;
    }

    /**
     * 获取：
     */
    public Integer getIsvirtual() {
        return isvirtual;
    }
    /**
     * 设置：
     */
    public void setCouponid(Integer couponid) {
        this.couponid = couponid;
    }

    /**
     * 获取：
     */
    public Integer getCouponid() {
        return couponid;
    }
    /**
     * 设置：
     */
    public void setCouponprice(BigDecimal couponprice) {
        this.couponprice = couponprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getCouponprice() {
        return couponprice;
    }
    /**
     * 设置：
     */
    public void setDiyformdata(String diyformdata) {
        this.diyformdata = diyformdata;
    }

    /**
     * 获取：
     */
    public String getDiyformdata() {
        return diyformdata;
    }
    /**
     * 设置：
     */
    public void setDiyformfields(String diyformfields) {
        this.diyformfields = diyformfields;
    }

    /**
     * 获取：
     */
    public String getDiyformfields() {
        return diyformfields;
    }
    /**
     * 设置：
     */
    public void setDiyformid(Integer diyformid) {
        this.diyformid = diyformid;
    }

    /**
     * 获取：
     */
    public Integer getDiyformid() {
        return diyformid;
    }
    /**
     * 设置：
     */
    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    /**
     * 获取：
     */
    public Integer getStoreid() {
        return storeid;
    }
    /**
     * 设置：
     */
    public void setPrintstate(Integer printstate) {
        this.printstate = printstate;
    }

    /**
     * 获取：
     */
    public Integer getPrintstate() {
        return printstate;
    }
    /**
     * 设置：
     */
    public void setPrintstate2(Integer printstate2) {
        this.printstate2 = printstate2;
    }

    /**
     * 获取：
     */
    public Integer getPrintstate2() {
        return printstate2;
    }
    /**
     * 设置：
     */
    public void setAddressSend(String addressSend) {
        this.addressSend = addressSend;
    }

    /**
     * 获取：
     */
    public String getAddressSend() {
        return addressSend;
    }
    /**
     * 设置：
     */
    public void setRefundstate(Integer refundstate) {
        this.refundstate = refundstate;
    }

    /**
     * 获取：
     */
    public Integer getRefundstate() {
        return refundstate;
    }
    /**
     * 设置：
     */
    public void setClosereason(String closereason) {
        this.closereason = closereason;
    }

    /**
     * 获取：
     */
    public String getClosereason() {
        return closereason;
    }
    /**
     * 设置：
     */
    public void setRemarksaler(String remarksaler) {
        this.remarksaler = remarksaler;
    }

    /**
     * 获取：
     */
    public String getRemarksaler() {
        return remarksaler;
    }
    /**
     * 设置：
     */
    public void setRemarkclose(String remarkclose) {
        this.remarkclose = remarkclose;
    }

    /**
     * 获取：
     */
    public String getRemarkclose() {
        return remarkclose;
    }
    /**
     * 设置：
     */
    public void setRemarksend(String remarksend) {
        this.remarksend = remarksend;
    }

    /**
     * 获取：
     */
    public String getRemarksend() {
        return remarksend;
    }
    /**
     * 设置：
     */
    public void setIsmr(Integer ismr) {
        this.ismr = ismr;
    }

    /**
     * 获取：
     */
    public Integer getIsmr() {
        return ismr;
    }
    /**
     * 设置：
     */
    public void setIsdiscountprice(BigDecimal isdiscountprice) {
        this.isdiscountprice = isdiscountprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getIsdiscountprice() {
        return isdiscountprice;
    }
    /**
     * 设置：
     */
    public void setIsvirtualsend(Integer isvirtualsend) {
        this.isvirtualsend = isvirtualsend;
    }

    /**
     * 获取：
     */
    public Integer getIsvirtualsend() {
        return isvirtualsend;
    }
    /**
     * 设置：
     */
    public void setVirtualsendInfo(String virtualsendInfo) {
        this.virtualsendInfo = virtualsendInfo;
    }

    /**
     * 获取：
     */
    public String getVirtualsendInfo() {
        return virtualsendInfo;
    }
    /**
     * 设置：
     */
    public void setVerifyinfo(String verifyinfo) {
        this.verifyinfo = verifyinfo;
    }

    /**
     * 获取：
     */
    public String getVerifyinfo() {
        return verifyinfo;
    }
    /**
     * 设置：
     */
    public void setVerifytype(Integer verifytype) {
        this.verifytype = verifytype;
    }

    /**
     * 获取：
     */
    public Integer getVerifytype() {
        return verifytype;
    }
    /**
     * 设置：
     */
    public void setVerifycodes(String verifycodes) {
        this.verifycodes = verifycodes;
    }

    /**
     * 获取：
     */
    public String getVerifycodes() {
        return verifycodes;
    }
    /**
     * 设置：
     */
    public void setInvoicename(String invoicename) {
        this.invoicename = invoicename;
    }

    /**
     * 获取：
     */
    public String getInvoicename() {
        return invoicename;
    }
    /**
     * 设置：
     */
    public void setMerchid(Integer merchid) {
        this.merchid = merchid;
    }

    /**
     * 获取：
     */
    public Integer getMerchid() {
        return merchid;
    }
    /**
     * 设置：
     */
    public void setIsmerch(Integer ismerch) {
        this.ismerch = ismerch;
    }

    /**
     * 获取：
     */
    public Integer getIsmerch() {
        return ismerch;
    }
    /**
     * 设置：
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * 获取：
     */
    public Integer getParentid() {
        return parentid;
    }
    /**
     * 设置：
     */
    public void setIsparent(Integer isparent) {
        this.isparent = isparent;
    }

    /**
     * 获取：
     */
    public Integer getIsparent() {
        return isparent;
    }
    /**
     * 设置：
     */
    public void setGrprice(BigDecimal grprice) {
        this.grprice = grprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getGrprice() {
        return grprice;
    }
    /**
     * 设置：
     */
    public void setMerchshow(Integer merchshow) {
        this.merchshow = merchshow;
    }

    /**
     * 获取：
     */
    public Integer getMerchshow() {
        return merchshow;
    }
    /**
     * 设置：
     */
    public void setMerchdeductenough(BigDecimal merchdeductenough) {
        this.merchdeductenough = merchdeductenough;
    }

    /**
     * 获取：
     */
    public BigDecimal getMerchdeductenough() {
        return merchdeductenough;
    }
    /**
     * 设置：
     */
    public void setCouponmerchid(Integer couponmerchid) {
        this.couponmerchid = couponmerchid;
    }

    /**
     * 获取：
     */
    public Integer getCouponmerchid() {
        return couponmerchid;
    }
    /**
     * 设置：
     */
    public void setIsglobonus(Integer isglobonus) {
        this.isglobonus = isglobonus;
    }

    /**
     * 获取：
     */
    public Integer getIsglobonus() {
        return isglobonus;
    }
    /**
     * 设置：
     */
    public void setMerchapply(Integer merchapply) {
        this.merchapply = merchapply;
    }

    /**
     * 获取：
     */
    public Integer getMerchapply() {
        return merchapply;
    }
    /**
     * 设置：
     */
    public void setIsabonus(Integer isabonus) {
        this.isabonus = isabonus;
    }

    /**
     * 获取：
     */
    public Integer getIsabonus() {
        return isabonus;
    }
    /**
     * 设置：
     */
    public void setIsborrow(Integer isborrow) {
        this.isborrow = isborrow;
    }

    /**
     * 获取：
     */
    public Integer getIsborrow() {
        return isborrow;
    }
    /**
     * 设置：
     */
    public void setBorrowopenid(String borrowopenid) {
        this.borrowopenid = borrowopenid;
    }

    /**
     * 获取：
     */
    public String getBorrowopenid() {
        return borrowopenid;
    }
    /**
     * 设置：
     */
    public void setMerchisdiscountprice(BigDecimal merchisdiscountprice) {
        this.merchisdiscountprice = merchisdiscountprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getMerchisdiscountprice() {
        return merchisdiscountprice;
    }
    /**
     * 设置：
     */
    public void setApppay(Integer apppay) {
        this.apppay = apppay;
    }

    /**
     * 获取：
     */
    public Integer getApppay() {
        return apppay;
    }
    /**
     * 设置：
     */
    public void setCoupongoodprice(BigDecimal coupongoodprice) {
        this.coupongoodprice = coupongoodprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getCoupongoodprice() {
        return coupongoodprice;
    }
    /**
     * 设置：
     */
    public void setBuyagainprice(BigDecimal buyagainprice) {
        this.buyagainprice = buyagainprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getBuyagainprice() {
        return buyagainprice;
    }
    /**
     * 设置：
     */
    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    /**
     * 获取：
     */
    public Integer getAuthorid() {
        return authorid;
    }
    /**
     * 设置：
     */
    public void setIsauthor(Integer isauthor) {
        this.isauthor = isauthor;
    }

    /**
     * 获取：
     */
    public Integer getIsauthor() {
        return isauthor;
    }
    /**
     * 设置：
     */
    public void setIspackage(Integer ispackage) {
        this.ispackage = ispackage;
    }

    /**
     * 获取：
     */
    public Integer getIspackage() {
        return ispackage;
    }
    /**
     * 设置：
     */
    public void setPackageid(Integer packageid) {
        this.packageid = packageid;
    }

    /**
     * 获取：
     */
    public Integer getPackageid() {
        return packageid;
    }
    /**
     * 设置：
     */
    public void setTaskdiscountprice(BigDecimal taskdiscountprice) {
        this.taskdiscountprice = taskdiscountprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getTaskdiscountprice() {
        return taskdiscountprice;
    }
    /**
     * 设置：
     */
    public void setSeckilldiscountprice(BigDecimal seckilldiscountprice) {
        this.seckilldiscountprice = seckilldiscountprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getSeckilldiscountprice() {
        return seckilldiscountprice;
    }
    /**
     * 设置：
     */
    public void setVerifyendtime(Integer verifyendtime) {
        this.verifyendtime = verifyendtime;
    }

    /**
     * 获取：
     */
    public Integer getVerifyendtime() {
        return verifyendtime;
    }
    /**
     * 设置：
     */
    public void setWillcancelmessage(Integer willcancelmessage) {
        this.willcancelmessage = willcancelmessage;
    }

    /**
     * 获取：
     */
    public Integer getWillcancelmessage() {
        return willcancelmessage;
    }
    /**
     * 设置：
     */
    public void setSendtype(Integer sendtype) {
        this.sendtype = sendtype;
    }

    /**
     * 获取：
     */
    public Integer getSendtype() {
        return sendtype;
    }
    /**
     * 设置：
     */
    public void setLotterydiscountprice(BigDecimal lotterydiscountprice) {
        this.lotterydiscountprice = lotterydiscountprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getLotterydiscountprice() {
        return lotterydiscountprice;
    }
    /**
     * 设置：
     */
    public void setContype(Integer contype) {
        this.contype = contype;
    }

    /**
     * 获取：
     */
    public Integer getContype() {
        return contype;
    }
    /**
     * 设置：
     */
    public void setWxid(Integer wxid) {
        this.wxid = wxid;
    }

    /**
     * 获取：
     */
    public Integer getWxid() {
        return wxid;
    }
    /**
     * 设置：
     */
    public void setWxcardid(String wxcardid) {
        this.wxcardid = wxcardid;
    }

    /**
     * 获取：
     */
    public String getWxcardid() {
        return wxcardid;
    }
    /**
     * 设置：
     */
    public void setWxcode(String wxcode) {
        this.wxcode = wxcode;
    }

    /**
     * 获取：
     */
    public String getWxcode() {
        return wxcode;
    }
    /**
     * 设置：
     */
    public void setDispatchkey(String dispatchkey) {
        this.dispatchkey = dispatchkey;
    }

    /**
     * 获取：
     */
    public String getDispatchkey() {
        return dispatchkey;
    }
    /**
     * 设置：
     */
    public void setQuickid(Integer quickid) {
        this.quickid = quickid;
    }

    /**
     * 获取：
     */
    public Integer getQuickid() {
        return quickid;
    }
    /**
     * 设置：
     */
    public void setIstrade(Integer istrade) {
        this.istrade = istrade;
    }

    /**
     * 获取：
     */
    public Integer getIstrade() {
        return istrade;
    }
    /**
     * 设置：
     */
    public void setIsnewstore(Integer isnewstore) {
        this.isnewstore = isnewstore;
    }

    /**
     * 获取：
     */
    public Integer getIsnewstore() {
        return isnewstore;
    }
    /**
     * 设置：
     */
    public void setLiveid(Integer liveid) {
        this.liveid = liveid;
    }

    /**
     * 获取：
     */
    public Integer getLiveid() {
        return liveid;
    }
    /**
     * 设置：
     */
    public void setOrdersnTrade(String ordersnTrade) {
        this.ordersnTrade = ordersnTrade;
    }

    /**
     * 获取：
     */
    public String getOrdersnTrade() {
        return ordersnTrade;
    }
    /**
     * 设置：
     */
    public void setTradestatus(Integer tradestatus) {
        this.tradestatus = tradestatus;
    }

    /**
     * 获取：
     */
    public Integer getTradestatus() {
        return tradestatus;
    }
    /**
     * 设置：
     */
    public void setTradepaytype(Integer tradepaytype) {
        this.tradepaytype = tradepaytype;
    }

    /**
     * 获取：
     */
    public Integer getTradepaytype() {
        return tradepaytype;
    }
    /**
     * 设置：
     */
    public void setTradepaytime(Integer tradepaytime) {
        this.tradepaytime = tradepaytime;
    }

    /**
     * 获取：
     */
    public Integer getTradepaytime() {
        return tradepaytime;
    }
    /**
     * 设置：
     */
    public void setDowpayment(BigDecimal dowpayment) {
        this.dowpayment = dowpayment;
    }

    /**
     * 获取：
     */
    public BigDecimal getDowpayment() {
        return dowpayment;
    }
    /**
     * 设置：
     */
    public void setBetweenprice(BigDecimal betweenprice) {
        this.betweenprice = betweenprice;
    }

    /**
     * 获取：
     */
    public BigDecimal getBetweenprice() {
        return betweenprice;
    }
    /**
     * 设置：
     */
    public void setIsshare(Integer isshare) {
        this.isshare = isshare;
    }

    /**
     * 获取：
     */
    public Integer getIsshare() {
        return isshare;
    }
    /**
     * 设置：
     */
    public void setOfficcode(String officcode) {
        this.officcode = officcode;
    }

    /**
     * 获取：
     */
    public String getOfficcode() {
        return officcode;
    }
    /**
     * 设置：
     */
    public void setWxappPrepayId(String wxappPrepayId) {
        this.wxappPrepayId = wxappPrepayId;
    }

    /**
     * 获取：
     */
    public String getWxappPrepayId() {
        return wxappPrepayId;
    }
    /**
     * 设置：
     */
    public void setCashtime(Integer cashtime) {
        this.cashtime = cashtime;
    }

    /**
     * 获取：
     */
    public Integer getCashtime() {
        return cashtime;
    }
    /**
     * 设置：
     */
    public void setIswxappcreate(Integer iswxappcreate) {
        this.iswxappcreate = iswxappcreate;
    }

    /**
     * 获取：
     */
    public Integer getIswxappcreate() {
        return iswxappcreate;
    }
    /**
     * 设置：
     */
    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    /**
     * 获取：
     */
    public String getRandomCode() {
        return randomCode;
    }
    /**
     * 设置：
     */
    public void setPrintTemplate(String printTemplate) {
        this.printTemplate = printTemplate;
    }

    /**
     * 获取：
     */
    public String getPrintTemplate() {
        return printTemplate;
    }
    /**
     * 设置：
     */
    public void setCityExpressState(Integer cityExpressState) {
        this.cityExpressState = cityExpressState;
    }

    /**
     * 获取：
     */
    public Integer getCityExpressState() {
        return cityExpressState;
    }
    /**
     * 设置：
     */
    public void setIsCashier(Integer isCashier) {
        this.isCashier = isCashier;
    }

    /**
     * 获取：
     */
    public Integer getIsCashier() {
        return isCashier;
    }
    /**
     * 设置：
     */
    public void setCommissionmoney(BigDecimal commissionmoney) {
        this.commissionmoney = commissionmoney;
    }

    /**
     * 获取：
     */
    public BigDecimal getCommissionmoney() {
        return commissionmoney;
    }
    /**
     * 设置：
     */
    public void setIscycelbuy(Integer iscycelbuy) {
        this.iscycelbuy = iscycelbuy;
    }

    /**
     * 获取：
     */
    public Integer getIscycelbuy() {
        return iscycelbuy;
    }
    /**
     * 设置：
     */
    public void setCycelbuyPredictTime(Integer cycelbuyPredictTime) {
        this.cycelbuyPredictTime = cycelbuyPredictTime;
    }

    /**
     * 获取：
     */
    public Integer getCycelbuyPredictTime() {
        return cycelbuyPredictTime;
    }
    /**
     * 设置：
     */
    public void setCycelbuyPeriodic(String cycelbuyPeriodic) {
        this.cycelbuyPeriodic = cycelbuyPeriodic;
    }

    /**
     * 获取：
     */
    public String getCycelbuyPeriodic() {
        return cycelbuyPeriodic;
    }
    /**
     * 设置：
     */
    public void setInvoiceImg(String invoiceImg) {
        this.invoiceImg = invoiceImg;
    }

    /**
     * 获取：
     */
    public String getInvoiceImg() {
        return invoiceImg;
    }
}
