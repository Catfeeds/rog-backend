package com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class GetActivityEntrepriseVO implements Serializable {


    @ApiModelProperty(value = "活动ID", required = true)
    private Long id;

    @ApiModelProperty(value = "活动名称", required = true)
    private String name;

    @ApiModelProperty(value = "Mph供应商名称", required = true)
    private String mphSupplierName;

    @ApiModelProperty(value = "开始时间", required = true)
    private Date startTime;

    @ApiModelProperty(value = "结束时间", required = true)
    private Date endTime;

    @ApiModelProperty(value = "'Mph供应商商城logo", required = true)
    private String mphSupplierLogo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMphSupplierName() {
        return mphSupplierName;
    }

    public void setMphSupplierName(String mphSupplierName) {
        this.mphSupplierName = mphSupplierName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getMphSupplierLogo() {
        return mphSupplierLogo;
    }

    public void setMphSupplierLogo(String mphSupplierLogo) {
        this.mphSupplierLogo = mphSupplierLogo;
    }

    @Override
    public String toString() {
        return "GetActivityEntrepriseVO[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mphSupplierName='" + mphSupplierName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", mphSupplierLogo='" + mphSupplierLogo + '\'' +
                ']';
    }
}
