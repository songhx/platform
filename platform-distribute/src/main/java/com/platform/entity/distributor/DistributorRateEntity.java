package com.platform.entity.distributor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 分销商分配率配置表实体
 * 表名 distributor_rate
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-08-09 22:37:03
 */
public class DistributorRateEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //标准率
    private BigDecimal standarRate;
    //销售占比率
    private BigDecimal slaerRate;
    //升级代理率
    private BigDecimal provinceRate;
    //
    private BigDecimal cityRate;
    //一级分销商比例1
    private BigDecimal distributorLv1S1;
    //一级分销商比例2
    private BigDecimal distributorLv1S2;
    //一级分销商比例3
    private BigDecimal distributorLv1S3;
    //二级分销商比例1
    private BigDecimal distributorLv2S1;
    //二级分销商比例2
    private BigDecimal distributorLv2S2;
    //二级分销商比例3
    private BigDecimal distributorLv2S3;
    //三级分销商比例1
    private BigDecimal distributorLv3S1;
    //三级分销商比例2
    private BigDecimal distributorLv3S2;
    //三级分销商比例3
    private BigDecimal distributorLv3S3;
    //启用状态 0 未启用 1 启用
    private Integer status;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：标准率
     */
    public void setStandarRate(BigDecimal standarRate) {
        this.standarRate = standarRate;
    }

    /**
     * 获取：标准率
     */
    public BigDecimal getStandarRate() {
        return standarRate;
    }
    /**
     * 设置：销售占比率
     */
    public void setSlaerRate(BigDecimal slaerRate) {
        this.slaerRate = slaerRate;
    }

    /**
     * 获取：销售占比率
     */
    public BigDecimal getSlaerRate() {
        return slaerRate;
    }
    /**
     * 设置：升级代理率
     */
    public void setProvinceRate(BigDecimal provinceRate) {
        this.provinceRate = provinceRate;
    }

    /**
     * 获取：升级代理率
     */
    public BigDecimal getProvinceRate() {
        return provinceRate;
    }
    /**
     * 设置：
     */
    public void setCityRate(BigDecimal cityRate) {
        this.cityRate = cityRate;
    }

    /**
     * 获取：
     */
    public BigDecimal getCityRate() {
        return cityRate;
    }
    /**
     * 设置：一级分销商比例1
     */
    public void setDistributorLv1S1(BigDecimal distributorLv1S1) {
        this.distributorLv1S1 = distributorLv1S1;
    }

    /**
     * 获取：一级分销商比例1
     */
    public BigDecimal getDistributorLv1S1() {
        return distributorLv1S1;
    }
    /**
     * 设置：一级分销商比例2
     */
    public void setDistributorLv1S2(BigDecimal distributorLv1S2) {
        this.distributorLv1S2 = distributorLv1S2;
    }

    /**
     * 获取：一级分销商比例2
     */
    public BigDecimal getDistributorLv1S2() {
        return distributorLv1S2;
    }
    /**
     * 设置：一级分销商比例3
     */
    public void setDistributorLv1S3(BigDecimal distributorLv1S3) {
        this.distributorLv1S3 = distributorLv1S3;
    }

    /**
     * 获取：一级分销商比例3
     */
    public BigDecimal getDistributorLv1S3() {
        return distributorLv1S3;
    }
    /**
     * 设置：二级分销商比例1
     */
    public void setDistributorLv2S1(BigDecimal distributorLv2S1) {
        this.distributorLv2S1 = distributorLv2S1;
    }

    /**
     * 获取：二级分销商比例1
     */
    public BigDecimal getDistributorLv2S1() {
        return distributorLv2S1;
    }
    /**
     * 设置：二级分销商比例2
     */
    public void setDistributorLv2S2(BigDecimal distributorLv2S2) {
        this.distributorLv2S2 = distributorLv2S2;
    }

    /**
     * 获取：二级分销商比例2
     */
    public BigDecimal getDistributorLv2S2() {
        return distributorLv2S2;
    }
    /**
     * 设置：二级分销商比例3
     */
    public void setDistributorLv2S3(BigDecimal distributorLv2S3) {
        this.distributorLv2S3 = distributorLv2S3;
    }

    /**
     * 获取：二级分销商比例3
     */
    public BigDecimal getDistributorLv2S3() {
        return distributorLv2S3;
    }
    /**
     * 设置：三级分销商比例1
     */
    public void setDistributorLv3S1(BigDecimal distributorLv3S1) {
        this.distributorLv3S1 = distributorLv3S1;
    }

    /**
     * 获取：三级分销商比例1
     */
    public BigDecimal getDistributorLv3S1() {
        return distributorLv3S1;
    }
    /**
     * 设置：三级分销商比例2
     */
    public void setDistributorLv3S2(BigDecimal distributorLv3S2) {
        this.distributorLv3S2 = distributorLv3S2;
    }

    /**
     * 获取：三级分销商比例2
     */
    public BigDecimal getDistributorLv3S2() {
        return distributorLv3S2;
    }
    /**
     * 设置：三级分销商比例3
     */
    public void setDistributorLv3S3(BigDecimal distributorLv3S3) {
        this.distributorLv3S3 = distributorLv3S3;
    }

    /**
     * 获取：三级分销商比例3
     */
    public BigDecimal getDistributorLv3S3() {
        return distributorLv3S3;
    }
    /**
     * 设置：启用状态 0 未启用 1 启用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：启用状态 0 未启用 1 启用
     */
    public Integer getStatus() {
        return status;
    }
}
