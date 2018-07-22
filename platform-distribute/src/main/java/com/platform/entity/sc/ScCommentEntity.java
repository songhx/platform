package com.platform.entity.sc;

import java.io.Serializable;
import java.util.Date;


/**
 * 评论表实体
 * 表名 sc_comment
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-21 23:29:44
 */
public class ScCommentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private Integer id;
    //信息id
    private Integer infoId;
    //头像
    private String avatar;
    //昵称
    private String name;
    //评论内容
    private String content;
    //点赞数
    private Integer agreeNum;
    //创建时间
    private Date createTime;
    //数据状态 0 正常 1 删除
    private Integer dataStatus;

    /**
     * 设置：主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：信息id
     */
    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    /**
     * 获取：信息id
     */
    public Integer getInfoId() {
        return infoId;
    }
    /**
     * 设置：头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取：头像
     */
    public String getAvatar() {
        return avatar;
    }
    /**
     * 设置：昵称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：昵称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：评论内容
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置：点赞数
     */
    public void setAgreeNum(Integer agreeNum) {
        this.agreeNum = agreeNum;
    }

    /**
     * 获取：点赞数
     */
    public Integer getAgreeNum() {
        return agreeNum;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 设置：数据状态 0 正常 1 删除
     */
    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    /**
     * 获取：数据状态 0 正常 1 删除
     */
    public Integer getDataStatus() {
        return dataStatus;
    }
}
