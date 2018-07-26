package com.platform.entity.member;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 实体
 * 表名 ims_ewei_shop_member
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-26 21:55:37
 */
public class ImsEweiShopMemberEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //
    private Integer uniacid;
    //
    private Integer uid;
    //
    private String groupid;
    //
    private Integer level;
    //
    private Integer agentid;
    //
    private String openid;
    //
    private String realname;
    //
    private String mobile;
    //
    private String pwd;
    //
    private String weixin;
    //
    private String content;
    //
    private Integer createtime;
    //
    private Integer agenttime;
    //
    private Integer status;
    //
    private Integer isagent;
    //
    private Integer clickcount;
    //
    private Integer agentlevel;
    //
    private String noticeset;
    //
    private String nickname;
    //
    private BigDecimal credit1;
    //
    private BigDecimal credit2;
    //
    private String birthyear;
    //
    private String birthmonth;
    //
    private String birthday;
    //
    private Integer gender;
    //
    private String avatar;
    //
    private String province;
    //
    private String city;
    //
    private String area;
    //
    private Integer childtime;
    //
    private Integer inviter;
    //
    private Integer agentnotupgrade;
    //
    private Integer agentselectgoods;
    //
    private Integer agentblack;
    //
    private Integer fixagentid;
    //
    private Integer diymemberid;
    //
    private String diymemberfields;
    //
    private String diymemberdata;
    //
    private Integer diymemberdataid;
    //
    private Integer diycommissionid;
    //
    private String diycommissionfields;
    //
    private String diycommissiondata;
    //
    private Integer diycommissiondataid;
    //
    private Integer isblack;
    //
    private String username;
    //
    private BigDecimal commissionTotal;
    //
    private Integer endtime2;
    //
    private Integer ispartner;
    //
    private Integer partnertime;
    //
    private Integer partnerstatus;
    //
    private Integer partnerblack;
    //
    private Integer partnerlevel;
    //
    private Integer partnernotupgrade;
    //
    private Integer diyglobonusid;
    //
    private String diyglobonusdata;
    //
    private String diyglobonusfields;
    //
    private Integer isaagent;
    //
    private Integer aagentlevel;
    //
    private Integer aagenttime;
    //
    private Integer aagentstatus;
    //
    private Integer aagentblack;
    //
    private Integer aagentnotupgrade;
    //
    private Integer aagenttype;
    //
    private String aagentprovinces;
    //
    private String aagentcitys;
    //
    private String aagentareas;
    //
    private Integer diyaagentid;
    //
    private String diyaagentdata;
    //
    private String diyaagentfields;
    //
    private String salt;
    //
    private Integer mobileverify;
    //
    private Integer mobileuser;
    //
    private String carrierMobile;
    //
    private Integer isauthor;
    //
    private Integer authortime;
    //
    private Integer authorstatus;
    //
    private Integer authorblack;
    //
    private Integer authorlevel;
    //
    private Integer authornotupgrade;
    //
    private Integer diyauthorid;
    //
    private String diyauthordata;
    //
    private String diyauthorfields;
    //
    private Integer authorid;
    //
    private String comefrom;
    //
    private String openidQq;
    //
    private String openidWx;
    //
    private Integer diymaxcredit;
    //
    private Integer maxcredit;
    //
    private String datavalue;
    //
    private String openidWa;
    //
    private String nicknameWechat;
    //
    private String avatarWechat;
    //
    private Integer updateaddress;
    //
    private String membercardid;
    //
    private String membercardcode;
    //
    private String membershipnumber;
    //
    private Integer membercardactive;
    //
    private BigDecimal commission;
    //
    private BigDecimal commissionPay;
    //
    private String idnumber;
    //
    private Integer wxcardupdatetime;
    //
    private Integer hasnewcoupon;

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
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取：
     */
    public Integer getUid() {
        return uid;
    }
    /**
     * 设置：
     */
    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    /**
     * 获取：
     */
    public String getGroupid() {
        return groupid;
    }
    /**
     * 设置：
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取：
     */
    public Integer getLevel() {
        return level;
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
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * 获取：
     */
    public String getRealname() {
        return realname;
    }
    /**
     * 设置：
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置：
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 获取：
     */
    public String getPwd() {
        return pwd;
    }
    /**
     * 设置：
     */
    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    /**
     * 获取：
     */
    public String getWeixin() {
        return weixin;
    }
    /**
     * 设置：
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：
     */
    public String getContent() {
        return content;
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
    public void setAgenttime(Integer agenttime) {
        this.agenttime = agenttime;
    }

    /**
     * 获取：
     */
    public Integer getAgenttime() {
        return agenttime;
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
    public void setIsagent(Integer isagent) {
        this.isagent = isagent;
    }

    /**
     * 获取：
     */
    public Integer getIsagent() {
        return isagent;
    }
    /**
     * 设置：
     */
    public void setClickcount(Integer clickcount) {
        this.clickcount = clickcount;
    }

    /**
     * 获取：
     */
    public Integer getClickcount() {
        return clickcount;
    }
    /**
     * 设置：
     */
    public void setAgentlevel(Integer agentlevel) {
        this.agentlevel = agentlevel;
    }

    /**
     * 获取：
     */
    public Integer getAgentlevel() {
        return agentlevel;
    }
    /**
     * 设置：
     */
    public void setNoticeset(String noticeset) {
        this.noticeset = noticeset;
    }

    /**
     * 获取：
     */
    public String getNoticeset() {
        return noticeset;
    }
    /**
     * 设置：
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取：
     */
    public String getNickname() {
        return nickname;
    }
    /**
     * 设置：
     */
    public void setCredit1(BigDecimal credit1) {
        this.credit1 = credit1;
    }

    /**
     * 获取：
     */
    public BigDecimal getCredit1() {
        return credit1;
    }
    /**
     * 设置：
     */
    public void setCredit2(BigDecimal credit2) {
        this.credit2 = credit2;
    }

    /**
     * 获取：
     */
    public BigDecimal getCredit2() {
        return credit2;
    }
    /**
     * 设置：
     */
    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    /**
     * 获取：
     */
    public String getBirthyear() {
        return birthyear;
    }
    /**
     * 设置：
     */
    public void setBirthmonth(String birthmonth) {
        this.birthmonth = birthmonth;
    }

    /**
     * 获取：
     */
    public String getBirthmonth() {
        return birthmonth;
    }
    /**
     * 设置：
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取：
     */
    public String getBirthday() {
        return birthday;
    }
    /**
     * 设置：
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取：
     */
    public Integer getGender() {
        return gender;
    }
    /**
     * 设置：
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取：
     */
    public String getAvatar() {
        return avatar;
    }
    /**
     * 设置：
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取：
     */
    public String getProvince() {
        return province;
    }
    /**
     * 设置：
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取：
     */
    public String getCity() {
        return city;
    }
    /**
     * 设置：
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取：
     */
    public String getArea() {
        return area;
    }
    /**
     * 设置：
     */
    public void setChildtime(Integer childtime) {
        this.childtime = childtime;
    }

    /**
     * 获取：
     */
    public Integer getChildtime() {
        return childtime;
    }
    /**
     * 设置：
     */
    public void setInviter(Integer inviter) {
        this.inviter = inviter;
    }

    /**
     * 获取：
     */
    public Integer getInviter() {
        return inviter;
    }
    /**
     * 设置：
     */
    public void setAgentnotupgrade(Integer agentnotupgrade) {
        this.agentnotupgrade = agentnotupgrade;
    }

    /**
     * 获取：
     */
    public Integer getAgentnotupgrade() {
        return agentnotupgrade;
    }
    /**
     * 设置：
     */
    public void setAgentselectgoods(Integer agentselectgoods) {
        this.agentselectgoods = agentselectgoods;
    }

    /**
     * 获取：
     */
    public Integer getAgentselectgoods() {
        return agentselectgoods;
    }
    /**
     * 设置：
     */
    public void setAgentblack(Integer agentblack) {
        this.agentblack = agentblack;
    }

    /**
     * 获取：
     */
    public Integer getAgentblack() {
        return agentblack;
    }
    /**
     * 设置：
     */
    public void setFixagentid(Integer fixagentid) {
        this.fixagentid = fixagentid;
    }

    /**
     * 获取：
     */
    public Integer getFixagentid() {
        return fixagentid;
    }
    /**
     * 设置：
     */
    public void setDiymemberid(Integer diymemberid) {
        this.diymemberid = diymemberid;
    }

    /**
     * 获取：
     */
    public Integer getDiymemberid() {
        return diymemberid;
    }
    /**
     * 设置：
     */
    public void setDiymemberfields(String diymemberfields) {
        this.diymemberfields = diymemberfields;
    }

    /**
     * 获取：
     */
    public String getDiymemberfields() {
        return diymemberfields;
    }
    /**
     * 设置：
     */
    public void setDiymemberdata(String diymemberdata) {
        this.diymemberdata = diymemberdata;
    }

    /**
     * 获取：
     */
    public String getDiymemberdata() {
        return diymemberdata;
    }
    /**
     * 设置：
     */
    public void setDiymemberdataid(Integer diymemberdataid) {
        this.diymemberdataid = diymemberdataid;
    }

    /**
     * 获取：
     */
    public Integer getDiymemberdataid() {
        return diymemberdataid;
    }
    /**
     * 设置：
     */
    public void setDiycommissionid(Integer diycommissionid) {
        this.diycommissionid = diycommissionid;
    }

    /**
     * 获取：
     */
    public Integer getDiycommissionid() {
        return diycommissionid;
    }
    /**
     * 设置：
     */
    public void setDiycommissionfields(String diycommissionfields) {
        this.diycommissionfields = diycommissionfields;
    }

    /**
     * 获取：
     */
    public String getDiycommissionfields() {
        return diycommissionfields;
    }
    /**
     * 设置：
     */
    public void setDiycommissiondata(String diycommissiondata) {
        this.diycommissiondata = diycommissiondata;
    }

    /**
     * 获取：
     */
    public String getDiycommissiondata() {
        return diycommissiondata;
    }
    /**
     * 设置：
     */
    public void setDiycommissiondataid(Integer diycommissiondataid) {
        this.diycommissiondataid = diycommissiondataid;
    }

    /**
     * 获取：
     */
    public Integer getDiycommissiondataid() {
        return diycommissiondataid;
    }
    /**
     * 设置：
     */
    public void setIsblack(Integer isblack) {
        this.isblack = isblack;
    }

    /**
     * 获取：
     */
    public Integer getIsblack() {
        return isblack;
    }
    /**
     * 设置：
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：
     */
    public String getUsername() {
        return username;
    }
    /**
     * 设置：
     */
    public void setCommissionTotal(BigDecimal commissionTotal) {
        this.commissionTotal = commissionTotal;
    }

    /**
     * 获取：
     */
    public BigDecimal getCommissionTotal() {
        return commissionTotal;
    }
    /**
     * 设置：
     */
    public void setEndtime2(Integer endtime2) {
        this.endtime2 = endtime2;
    }

    /**
     * 获取：
     */
    public Integer getEndtime2() {
        return endtime2;
    }
    /**
     * 设置：
     */
    public void setIspartner(Integer ispartner) {
        this.ispartner = ispartner;
    }

    /**
     * 获取：
     */
    public Integer getIspartner() {
        return ispartner;
    }
    /**
     * 设置：
     */
    public void setPartnertime(Integer partnertime) {
        this.partnertime = partnertime;
    }

    /**
     * 获取：
     */
    public Integer getPartnertime() {
        return partnertime;
    }
    /**
     * 设置：
     */
    public void setPartnerstatus(Integer partnerstatus) {
        this.partnerstatus = partnerstatus;
    }

    /**
     * 获取：
     */
    public Integer getPartnerstatus() {
        return partnerstatus;
    }
    /**
     * 设置：
     */
    public void setPartnerblack(Integer partnerblack) {
        this.partnerblack = partnerblack;
    }

    /**
     * 获取：
     */
    public Integer getPartnerblack() {
        return partnerblack;
    }
    /**
     * 设置：
     */
    public void setPartnerlevel(Integer partnerlevel) {
        this.partnerlevel = partnerlevel;
    }

    /**
     * 获取：
     */
    public Integer getPartnerlevel() {
        return partnerlevel;
    }
    /**
     * 设置：
     */
    public void setPartnernotupgrade(Integer partnernotupgrade) {
        this.partnernotupgrade = partnernotupgrade;
    }

    /**
     * 获取：
     */
    public Integer getPartnernotupgrade() {
        return partnernotupgrade;
    }
    /**
     * 设置：
     */
    public void setDiyglobonusid(Integer diyglobonusid) {
        this.diyglobonusid = diyglobonusid;
    }

    /**
     * 获取：
     */
    public Integer getDiyglobonusid() {
        return diyglobonusid;
    }
    /**
     * 设置：
     */
    public void setDiyglobonusdata(String diyglobonusdata) {
        this.diyglobonusdata = diyglobonusdata;
    }

    /**
     * 获取：
     */
    public String getDiyglobonusdata() {
        return diyglobonusdata;
    }
    /**
     * 设置：
     */
    public void setDiyglobonusfields(String diyglobonusfields) {
        this.diyglobonusfields = diyglobonusfields;
    }

    /**
     * 获取：
     */
    public String getDiyglobonusfields() {
        return diyglobonusfields;
    }
    /**
     * 设置：
     */
    public void setIsaagent(Integer isaagent) {
        this.isaagent = isaagent;
    }

    /**
     * 获取：
     */
    public Integer getIsaagent() {
        return isaagent;
    }
    /**
     * 设置：
     */
    public void setAagentlevel(Integer aagentlevel) {
        this.aagentlevel = aagentlevel;
    }

    /**
     * 获取：
     */
    public Integer getAagentlevel() {
        return aagentlevel;
    }
    /**
     * 设置：
     */
    public void setAagenttime(Integer aagenttime) {
        this.aagenttime = aagenttime;
    }

    /**
     * 获取：
     */
    public Integer getAagenttime() {
        return aagenttime;
    }
    /**
     * 设置：
     */
    public void setAagentstatus(Integer aagentstatus) {
        this.aagentstatus = aagentstatus;
    }

    /**
     * 获取：
     */
    public Integer getAagentstatus() {
        return aagentstatus;
    }
    /**
     * 设置：
     */
    public void setAagentblack(Integer aagentblack) {
        this.aagentblack = aagentblack;
    }

    /**
     * 获取：
     */
    public Integer getAagentblack() {
        return aagentblack;
    }
    /**
     * 设置：
     */
    public void setAagentnotupgrade(Integer aagentnotupgrade) {
        this.aagentnotupgrade = aagentnotupgrade;
    }

    /**
     * 获取：
     */
    public Integer getAagentnotupgrade() {
        return aagentnotupgrade;
    }
    /**
     * 设置：
     */
    public void setAagenttype(Integer aagenttype) {
        this.aagenttype = aagenttype;
    }

    /**
     * 获取：
     */
    public Integer getAagenttype() {
        return aagenttype;
    }
    /**
     * 设置：
     */
    public void setAagentprovinces(String aagentprovinces) {
        this.aagentprovinces = aagentprovinces;
    }

    /**
     * 获取：
     */
    public String getAagentprovinces() {
        return aagentprovinces;
    }
    /**
     * 设置：
     */
    public void setAagentcitys(String aagentcitys) {
        this.aagentcitys = aagentcitys;
    }

    /**
     * 获取：
     */
    public String getAagentcitys() {
        return aagentcitys;
    }
    /**
     * 设置：
     */
    public void setAagentareas(String aagentareas) {
        this.aagentareas = aagentareas;
    }

    /**
     * 获取：
     */
    public String getAagentareas() {
        return aagentareas;
    }
    /**
     * 设置：
     */
    public void setDiyaagentid(Integer diyaagentid) {
        this.diyaagentid = diyaagentid;
    }

    /**
     * 获取：
     */
    public Integer getDiyaagentid() {
        return diyaagentid;
    }
    /**
     * 设置：
     */
    public void setDiyaagentdata(String diyaagentdata) {
        this.diyaagentdata = diyaagentdata;
    }

    /**
     * 获取：
     */
    public String getDiyaagentdata() {
        return diyaagentdata;
    }
    /**
     * 设置：
     */
    public void setDiyaagentfields(String diyaagentfields) {
        this.diyaagentfields = diyaagentfields;
    }

    /**
     * 获取：
     */
    public String getDiyaagentfields() {
        return diyaagentfields;
    }
    /**
     * 设置：
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取：
     */
    public String getSalt() {
        return salt;
    }
    /**
     * 设置：
     */
    public void setMobileverify(Integer mobileverify) {
        this.mobileverify = mobileverify;
    }

    /**
     * 获取：
     */
    public Integer getMobileverify() {
        return mobileverify;
    }
    /**
     * 设置：
     */
    public void setMobileuser(Integer mobileuser) {
        this.mobileuser = mobileuser;
    }

    /**
     * 获取：
     */
    public Integer getMobileuser() {
        return mobileuser;
    }
    /**
     * 设置：
     */
    public void setCarrierMobile(String carrierMobile) {
        this.carrierMobile = carrierMobile;
    }

    /**
     * 获取：
     */
    public String getCarrierMobile() {
        return carrierMobile;
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
    public void setAuthortime(Integer authortime) {
        this.authortime = authortime;
    }

    /**
     * 获取：
     */
    public Integer getAuthortime() {
        return authortime;
    }
    /**
     * 设置：
     */
    public void setAuthorstatus(Integer authorstatus) {
        this.authorstatus = authorstatus;
    }

    /**
     * 获取：
     */
    public Integer getAuthorstatus() {
        return authorstatus;
    }
    /**
     * 设置：
     */
    public void setAuthorblack(Integer authorblack) {
        this.authorblack = authorblack;
    }

    /**
     * 获取：
     */
    public Integer getAuthorblack() {
        return authorblack;
    }
    /**
     * 设置：
     */
    public void setAuthorlevel(Integer authorlevel) {
        this.authorlevel = authorlevel;
    }

    /**
     * 获取：
     */
    public Integer getAuthorlevel() {
        return authorlevel;
    }
    /**
     * 设置：
     */
    public void setAuthornotupgrade(Integer authornotupgrade) {
        this.authornotupgrade = authornotupgrade;
    }

    /**
     * 获取：
     */
    public Integer getAuthornotupgrade() {
        return authornotupgrade;
    }
    /**
     * 设置：
     */
    public void setDiyauthorid(Integer diyauthorid) {
        this.diyauthorid = diyauthorid;
    }

    /**
     * 获取：
     */
    public Integer getDiyauthorid() {
        return diyauthorid;
    }
    /**
     * 设置：
     */
    public void setDiyauthordata(String diyauthordata) {
        this.diyauthordata = diyauthordata;
    }

    /**
     * 获取：
     */
    public String getDiyauthordata() {
        return diyauthordata;
    }
    /**
     * 设置：
     */
    public void setDiyauthorfields(String diyauthorfields) {
        this.diyauthorfields = diyauthorfields;
    }

    /**
     * 获取：
     */
    public String getDiyauthorfields() {
        return diyauthorfields;
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
    public void setComefrom(String comefrom) {
        this.comefrom = comefrom;
    }

    /**
     * 获取：
     */
    public String getComefrom() {
        return comefrom;
    }
    /**
     * 设置：
     */
    public void setOpenidQq(String openidQq) {
        this.openidQq = openidQq;
    }

    /**
     * 获取：
     */
    public String getOpenidQq() {
        return openidQq;
    }
    /**
     * 设置：
     */
    public void setOpenidWx(String openidWx) {
        this.openidWx = openidWx;
    }

    /**
     * 获取：
     */
    public String getOpenidWx() {
        return openidWx;
    }
    /**
     * 设置：
     */
    public void setDiymaxcredit(Integer diymaxcredit) {
        this.diymaxcredit = diymaxcredit;
    }

    /**
     * 获取：
     */
    public Integer getDiymaxcredit() {
        return diymaxcredit;
    }
    /**
     * 设置：
     */
    public void setMaxcredit(Integer maxcredit) {
        this.maxcredit = maxcredit;
    }

    /**
     * 获取：
     */
    public Integer getMaxcredit() {
        return maxcredit;
    }
    /**
     * 设置：
     */
    public void setDatavalue(String datavalue) {
        this.datavalue = datavalue;
    }

    /**
     * 获取：
     */
    public String getDatavalue() {
        return datavalue;
    }
    /**
     * 设置：
     */
    public void setOpenidWa(String openidWa) {
        this.openidWa = openidWa;
    }

    /**
     * 获取：
     */
    public String getOpenidWa() {
        return openidWa;
    }
    /**
     * 设置：
     */
    public void setNicknameWechat(String nicknameWechat) {
        this.nicknameWechat = nicknameWechat;
    }

    /**
     * 获取：
     */
    public String getNicknameWechat() {
        return nicknameWechat;
    }
    /**
     * 设置：
     */
    public void setAvatarWechat(String avatarWechat) {
        this.avatarWechat = avatarWechat;
    }

    /**
     * 获取：
     */
    public String getAvatarWechat() {
        return avatarWechat;
    }
    /**
     * 设置：
     */
    public void setUpdateaddress(Integer updateaddress) {
        this.updateaddress = updateaddress;
    }

    /**
     * 获取：
     */
    public Integer getUpdateaddress() {
        return updateaddress;
    }
    /**
     * 设置：
     */
    public void setMembercardid(String membercardid) {
        this.membercardid = membercardid;
    }

    /**
     * 获取：
     */
    public String getMembercardid() {
        return membercardid;
    }
    /**
     * 设置：
     */
    public void setMembercardcode(String membercardcode) {
        this.membercardcode = membercardcode;
    }

    /**
     * 获取：
     */
    public String getMembercardcode() {
        return membercardcode;
    }
    /**
     * 设置：
     */
    public void setMembershipnumber(String membershipnumber) {
        this.membershipnumber = membershipnumber;
    }

    /**
     * 获取：
     */
    public String getMembershipnumber() {
        return membershipnumber;
    }
    /**
     * 设置：
     */
    public void setMembercardactive(Integer membercardactive) {
        this.membercardactive = membercardactive;
    }

    /**
     * 获取：
     */
    public Integer getMembercardactive() {
        return membercardactive;
    }
    /**
     * 设置：
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    /**
     * 获取：
     */
    public BigDecimal getCommission() {
        return commission;
    }
    /**
     * 设置：
     */
    public void setCommissionPay(BigDecimal commissionPay) {
        this.commissionPay = commissionPay;
    }

    /**
     * 获取：
     */
    public BigDecimal getCommissionPay() {
        return commissionPay;
    }
    /**
     * 设置：
     */
    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    /**
     * 获取：
     */
    public String getIdnumber() {
        return idnumber;
    }
    /**
     * 设置：
     */
    public void setWxcardupdatetime(Integer wxcardupdatetime) {
        this.wxcardupdatetime = wxcardupdatetime;
    }

    /**
     * 获取：
     */
    public Integer getWxcardupdatetime() {
        return wxcardupdatetime;
    }
    /**
     * 设置：
     */
    public void setHasnewcoupon(Integer hasnewcoupon) {
        this.hasnewcoupon = hasnewcoupon;
    }

    /**
     * 获取：
     */
    public Integer getHasnewcoupon() {
        return hasnewcoupon;
    }
}
