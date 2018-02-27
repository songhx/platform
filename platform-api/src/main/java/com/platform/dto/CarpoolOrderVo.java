package com.platform.dto;

import com.platform.entity.CarpoolOrder;

import java.io.Serializable;

/**
 * @author bjsonghongxu
 * @create 2018-02-27 17:12
 **/
public class CarpoolOrderVo extends CarpoolOrder implements Serializable {
    private String  departureTimeStr;


    public String getDepartureTimeStr() {
        return departureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        this.departureTimeStr = departureTimeStr;
    }
}
