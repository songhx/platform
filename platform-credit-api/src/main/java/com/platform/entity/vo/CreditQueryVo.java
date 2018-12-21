package com.platform.entity.vo;

import java.io.Serializable;

/**
 * 资质查询Vo
 *
 * @author bjsonghongxu
 * @create 2018-12-21 17:03
 **/
public class CreditQueryVo implements Serializable {

    private String pid; // 商户 id，找志远 IT 申请
    private String biz_no; // 商户单号，更换字段名称
    private String version; // 版本号 1.0
    private String card_id; // 身份证号
    private String phone_number; // 手机号 （非必填）
    private String user_name; // 姓名
    private String attachment_count; // 附件数量
    private String attachment; // 附件 | 分割 例如 http://a.com/123.jpg|http://a.com/234.png
    private String attachment_type; // 附件类型|分割 0:正面照，1:反面照 ，2:查询授权书，3:手持身份证与授权书合影 例如0|1
    private String notice_url; // 有结果后通知商户的地址 新增使用方法
    private String sign_type; // 签名类型 MD5
    private String sign; // 签名串，签名方法见二
    private String credit_query_id; // 征信查询申请 id
    private String credit_query_sg_id; //申请业务标识id


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getBiz_no() {
        return biz_no;
    }

    public void setBiz_no(String biz_no) {
        this.biz_no = biz_no;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAttachment_count() {
        return attachment_count;
    }

    public void setAttachment_count(String attachment_count) {
        this.attachment_count = attachment_count;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getAttachment_type() {
        return attachment_type;
    }

    public void setAttachment_type(String attachment_type) {
        this.attachment_type = attachment_type;
    }

    public String getNotice_url() {
        return notice_url;
    }

    public void setNotice_url(String notice_url) {
        this.notice_url = notice_url;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCredit_query_id() {
        return credit_query_id;
    }

    public void setCredit_query_id(String credit_query_id) {
        this.credit_query_id = credit_query_id;
    }

    public String getCredit_query_sg_id() {
        return credit_query_sg_id;
    }

    public void setCredit_query_sg_id(String credit_query_sg_id) {
        this.credit_query_sg_id = credit_query_sg_id;
    }

    @Override
    public String toString() {
        return "CreditQueryVo{" +
                "pid='" + pid + '\'' +
                ", biz_no='" + biz_no + '\'' +
                ", version='" + version + '\'' +
                ", card_id='" + card_id + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", user_name='" + user_name + '\'' +
                ", attachment_count='" + attachment_count + '\'' +
                ", attachment='" + attachment + '\'' +
                ", attachment_type='" + attachment_type + '\'' +
                ", notice_url='" + notice_url + '\'' +
                ", sign_type='" + sign_type + '\'' +
                ", sign='" + sign + '\'' +
                ", credit_query_id='" + credit_query_id + '\'' +
                ", credit_query_sg_id='" + credit_query_sg_id + '\'' +
                '}';
    }
}
