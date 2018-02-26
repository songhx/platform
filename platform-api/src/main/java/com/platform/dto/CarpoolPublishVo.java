package com.platform.dto;

import com.platform.entity.CarpoolPublish;

import java.io.Serializable;

/**
 * Created by zuimeideshiguang on 18/2/26.
 */
public class CarpoolPublishVo extends CarpoolPublish implements Serializable {
    private Double custLongitude;
    private Double custLatitude;
    private Integer start; // 当前页
    private Integer limit; // 每页条数

    public Double getCustLongitude() {
        return custLongitude;
    }

    public void setCustLongitude(Double custLongitude) {
        this.custLongitude = custLongitude;
    }

    public Double getCustLatitude() {
        return custLatitude;
    }

    public void setCustLatitude(Double custLatitude) {
        this.custLatitude = custLatitude;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
