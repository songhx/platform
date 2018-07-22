package com.platform.entity.sc;

import java.io.Serializable;
import java.util.Date;


/**
 * 圈子信息表实体
 * 表名 sc_info
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-21 23:29:44
 */
public class ScInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private Integer id;
    //头像
    private String avatar;
    //微信昵称
    private String name;
    //信息类型 1->全部;41->视频;10->图片;29->文本;31->声音
    private Integer type;
    //文本内容
    private String content;
    //图片路径，最多支持九张
    private String imgUrl;
    //视频路径
    private String videoUrl;
    //资源宽度
    private Double width;
    //资源高度
    private Double height;
    //音频路径
    private String voiceUrl;
    //作者
    private String author;
    //音频背景图
    private String bimgUrl;
    //点赞数
    private Integer agreeNum;
    //踩数
    private Integer dissNum;
    //转发数
    private Integer repostNum;
    //评论数
    private Integer commentNum;
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
     * 设置：微信昵称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：微信昵称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：信息类型 1->全部;41->视频;10->图片;29->文本;31->声音
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：信息类型 1->全部;41->视频;10->图片;29->文本;31->声音
     */
    public Integer getType() {
        return type;
    }
    /**
     * 设置：文本内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：文本内容
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置：图片路径，最多支持九张
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取：图片路径，最多支持九张
     */
    public String getImgUrl() {
        return imgUrl;
    }
    /**
     * 设置：视频路径
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * 获取：视频路径
     */
    public String getVideoUrl() {
        return videoUrl;
    }
    /**
     * 设置：资源宽度
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    /**
     * 获取：资源宽度
     */
    public Double getWidth() {
        return width;
    }
    /**
     * 设置：资源高度
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * 获取：资源高度
     */
    public Double getHeight() {
        return height;
    }
    /**
     * 设置：音频路径
     */
    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    /**
     * 获取：音频路径
     */
    public String getVoiceUrl() {
        return voiceUrl;
    }
    /**
     * 设置：作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取：作者
     */
    public String getAuthor() {
        return author;
    }
    /**
     * 设置：音频背景图
     */
    public void setBimgUrl(String bimgUrl) {
        this.bimgUrl = bimgUrl;
    }

    /**
     * 获取：音频背景图
     */
    public String getBimgUrl() {
        return bimgUrl;
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
     * 设置：踩数
     */
    public void setDissNum(Integer dissNum) {
        this.dissNum = dissNum;
    }

    /**
     * 获取：踩数
     */
    public Integer getDissNum() {
        return dissNum;
    }
    /**
     * 设置：转发数
     */
    public void setRepostNum(Integer repostNum) {
        this.repostNum = repostNum;
    }

    /**
     * 获取：转发数
     */
    public Integer getRepostNum() {
        return repostNum;
    }
    /**
     * 设置：评论数
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * 获取：评论数
     */
    public Integer getCommentNum() {
        return commentNum;
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
