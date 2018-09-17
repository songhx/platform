package com.platform.entity;

import java.io.Serializable;

/**
 * 字段属性信息实体
 *
 * @author bjsonghongxu
 * @create 2018-07-20 17:36
 **/
public class ColumnEntity implements Serializable {
    //列名
    private String columnName;
    //列名类型
    private String dataType;
    //列名备注
    private String comments;
    //列key
    private String columnKey;

    //属性名称
    private String attrname;
    //属性名称(设置get/set使用)
    private String attrName;
    //属性类型
    private String attrType;


    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrname() {
        return attrname;
    }

    public void setAttrname(String attrname) {
        this.attrname = attrname;
    }
}
