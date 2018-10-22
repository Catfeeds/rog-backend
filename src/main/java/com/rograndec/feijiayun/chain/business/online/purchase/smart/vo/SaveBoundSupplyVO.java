package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SaveBoundSupplyVO implements Serializable {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "MPH供应商ID")
    private Long mphSupplierId;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getMphSupplierId() {
        return mphSupplierId;
    }

    public void setMphSupplierId(Long mphSupplierId) {
        this.mphSupplierId = mphSupplierId;
    }

    @Override
    public String toString() {
        return "SaveBoundSupplyVO[" +
                "supplierId=" + supplierId +
                ", mphSupplierId=" + mphSupplierId +
                ']';
    }
}
