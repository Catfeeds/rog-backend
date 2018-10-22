package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class SelectSmartSupplierVO implements Serializable {

    //商业公司标准库Id
    @ApiModelProperty(value = "商业公司标准库Id")
    private Long mphSupplierId;
    //商业公司名称
    @ApiModelProperty(value = "商业公司名称")
    private String mphSupplierName;

    @ApiModelProperty(value = "起配金额")
    private BigDecimal matchingAmount;

    @ApiModelProperty(value = "是否设置最少采购金额，0：未设置，1：设置")
    private int isMinPurchased;

    @JsonProperty("goods_list")
    private List<SelectSmartGoodsVO> selectSmartGoodsVO;

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

    public List<SelectSmartGoodsVO> getSelectSmartGoodsVO() {
        return selectSmartGoodsVO;
    }

    public void setSelectSmartGoodsVO(List<SelectSmartGoodsVO> selectSmartGoodsVO) {
        this.selectSmartGoodsVO = selectSmartGoodsVO;
    }

    public BigDecimal getMatchingAmount() {
        return matchingAmount;
    }

    public void setMatchingAmount(BigDecimal matchingAmount) {
        this.matchingAmount = matchingAmount;
    }

    public int getIsMinPurchased() {
        return isMinPurchased;
    }

    public void setIsMinPurchased(int isMinPurchased) {
        this.isMinPurchased = isMinPurchased;
    }

    @Override
    public String toString() {
        return "SelectSmartSupplierVO[" +
                "mphSupplierId=" + mphSupplierId +
                ", mphSupplierName='" + mphSupplierName + '\'' +
                ", matchingAmount=" + matchingAmount +
                ", selectSmartGoodsVO=" + selectSmartGoodsVO +
                ", isMinPurchased=" + isMinPurchased +
                ']';
    }
}
