package com.platform.core.enums;

import java.io.Serializable;

/**
 * 业务枚举
 *
 * @author bjsonghongxu
 * @create 2018-07-20 18:16
 **/
public interface BusinessEnum extends Serializable {

    ///数据库枚举
    enum DBEnum implements BusinessEnum{
        MYSQL(0,"MYSQL"), ORACEL(1,"ORACEL");
        private int key;
        private String value;

        DBEnum(int key, String value) {
            this.key  = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    enum DBPKEnum implements BusinessEnum{
        MYSQLPK("PRI"), ORACELPK("P");
        private String key;

        DBPKEnum(String key) {
            this.key  = key;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }



    }
}
