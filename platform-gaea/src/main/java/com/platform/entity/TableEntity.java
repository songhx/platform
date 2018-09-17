package com.platform.entity;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 表属性实体
 *
 * @author bjsonghongxu
 * @create 2018-07-20 17:43
 **/
public class TableEntity implements Serializable {

    //表的名称
    private String tableName;
    //表的备注
    private String comments;
    //表的engine
    private String engine;
    //表创建时间
    private Date createTime;

    //主键列
    @Transient
    private ColumnEntity pkColumn;

    //类名(第一个字母大写)
    @Transient
    private String className;
    //类名(第一个字母小写)
    @Transient
    private String  aliasName;

    //业务前缀
    private String tablePrefix;

    //表字段
    private List<ColumnEntity> columnEntityList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public ColumnEntity getPkColumn() {
        return pkColumn;
    }

    public void setPkColumn(ColumnEntity pkColumn) {
        this.pkColumn = pkColumn;
    }

    public List<ColumnEntity> getColumnEntityList() {
        return columnEntityList;
    }

    public void setColumnEntityList(List<ColumnEntity> columnEntityList) {
        this.columnEntityList = columnEntityList;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    @Override
    public String toString() {
        return "TableEntity{" +
                "tableName='" + tableName + '\'' +
                ", comments='" + comments + '\'' +
                ", engine='" + engine + '\'' +
                ", createTime=" + createTime +
                ", pkColumn=" + pkColumn +
                ", className='" + className + '\'' +
                ", aliasName='" + aliasName + '\'' +
                ", tablePrefix='" + tablePrefix + '\'' +
                '}';
    }
}
