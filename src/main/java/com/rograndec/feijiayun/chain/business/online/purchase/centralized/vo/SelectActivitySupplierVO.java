package com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class SelectActivitySupplierVO implements Serializable {

    @ApiModelProperty(value = "商业公司标准库Id")
    private Long mphSupplierId;

    @ApiModelProperty(value = "商业公司名称")
    private String mphSupplierName;

    List<SelectActivityGoodsVO> selectActivityGoodsVO;

    public Long getMphSupplierId() {
        return mphSupplierId;
    }

    public void setMphSupplierId(Long mphSupplierId) {
        this.mphSupplierId = mphSupplierId;
    }

    public String getMphSupplierName() {
        return mphSupplierName;
    }

    public void setMphSupplierName(String mphSupplierName) {
        this.mphSupplierName = mphSupplierName;
    }

    public List<SelectActivityGoodsVO> getSelectActivityGoodsVO() {
        return selectActivityGoodsVO;
    }

    public void setSelectActivityGoodsVO(List<SelectActivityGoodsVO> selectActivityGoodsVO) {
        this.selectActivityGoodsVO = selectActivityGoodsVO;
    }

    @Override
    public String toString() {
        return "SelectActivitySupplierVO[" +
                "mphSupplierId=" + mphSupplierId +
                ", mphSupplierName='" + mphSupplierName + '\'' +
                ", selectActivityGoodsVO=" + selectActivityGoodsVO +
                ']';
    }
}
