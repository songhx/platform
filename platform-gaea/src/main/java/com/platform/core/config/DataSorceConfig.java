package com.platform.core.config;

import java.io.Serializable;

/**
 * 数据源配置
 *
 * @author bjsonghongxu
 * @create 2018-07-20 17:49
 **/
public class DataSorceConfig implements Serializable {

    //数据库类型 0 MYSQL 1 ORCLE
    private int type;
    //数据库驱动
    private String driver;
    //数据库地址
    private String url;
    //用户名
    private String name;
    //密码
    private String password;
    //初始大小，兆
    private int initialSize = 5; //默认5兆
    //maxActive
    private int maxActive = 30; //默认30

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }
}
