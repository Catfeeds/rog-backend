package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SelectBoundSupplyVO implements Serializable {

    @ApiModelProperty(value = "供应商ID")
    private Long supplyId;

    @ApiModelProperty(value = "供应商编码")
    private String supplyCode;

    @ApiModelProperty(value = "供应商企业名称")
    private String supplyName;

    @ApiModelProperty(value = "供应商销售人员")
    private String supplySalerName;

    public Long getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Long supplyId) {
        this.supplyId = supplyId;
    }

    public String getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(String supplyCode) {
        this.supplyCode = supplyCode;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getSupplySalerName() {
        return supplySalerName;
    }

    public void setSupplySalerName(String supplySalerName) {
        this.supplySalerName = supplySalerName;
    }

    @Override
    public String toString() {
        return "SelectBoundSupplyVO[" +
                "supplyId=" + supplyId +
                ", supplyCode='" + supplyCode + '\'' +
                ", supplyName='" + supplyName + '\'' +
                ", supplySalerName='" + supplySalerName + '\'' +
                ']';
    }
}
