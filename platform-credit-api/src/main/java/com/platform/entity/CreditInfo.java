package com.platform.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 征信信息表
 *
 * @author bjsonghongxu
 * @create 2018-12-21 16:35
 **/
@Table(name = "credit_info")
public class CreditInfo implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id; // '自增id',

    private String pid; // 商户 id，找志远 IT 申请
    private String bizNo; // 商户单号，更换字段名称
    private String version; // 版本号 1.0
    private String cardId; // 身份证号
    private String phoneNumber; // 手机号 （非必填）
    private String userName; // 姓名
    private String attachmentCount; // 附件数量
    private String attachment; // 附件 | 分割 例如 http://a.com/123.jpg|http://a.com/234.png
    private String attachmentType; // 附件类型|分割 0:正面照，1:反面照 ，2:查询授权书，3:手持身份证与授权书合影 例如0|1
    private String noticeUrl; // 有结果后通知商户的地址 新增使用方法
    private String signType; // 签名类型 MD5
    private String sign; // 签名串，签名方法见二
    private String creditQueryId; // 征信查询申请 id
    private String html; //征信查询结果

    public CreditInfo() {
    }

    public CreditInfo(Integer id , String html) {
        this.id = id;
        this.html = html;
    }

    public CreditInfo(String pid, String bizNo, String version, String cardId,
                      String phoneNumber, String userName, String attachmentCount,
                      String attachment, String attachmentType, String noticeUrl,
                      String signType, String sign, String creditQueryId) {
        this.pid = pid;
        this.bizNo = bizNo;
        this.version = version;
        this.cardId = cardId;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.attachmentCount = attachmentCount;
        this.attachment = attachment;
        this.attachmentType = attachmentType;
        this.noticeUrl = noticeUrl;
        this.signType = signType;
        this.sign = sign;
        this.creditQueryId = creditQueryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAttachmentCount() {
        return attachmentCount;
    }

    public void setAttachmentCount(String attachmentCount) {
        this.attachmentCount = attachmentCount;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCreditQueryId() {
        return creditQueryId;
    }

    public void setCreditQueryId(String creditQueryId) {
        this.creditQueryId = creditQueryId;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return "CreditInfo{" +
                "id=" + id +
                ", pid='" + pid + '\'' +
                ", bizNo='" + bizNo + '\'' +
                ", version='" + version + '\'' +
                ", cardId='" + cardId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", attachmentCount='" + attachmentCount + '\'' +
                ", attachment='" + attachment + '\'' +
                ", attachmentType='" + attachmentType + '\'' +
                ", noticeUrl='" + noticeUrl + '\'' +
                ", signType='" + signType + '\'' +
                ", sign='" + sign + '\'' +
                ", creditQueryId='" + creditQueryId + '\'' +
                ", html='" + html + '\'' +
                '}';
    }
}
