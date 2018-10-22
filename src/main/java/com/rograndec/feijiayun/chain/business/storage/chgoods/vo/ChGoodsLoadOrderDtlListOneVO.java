package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ChGoodsLoadOrderDtlListOneVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "装斗明细ID")
    private Long id;

    @ApiModelProperty(required = true, value = "商品编码")
    private String goodsCode;

    @ApiModelProperty(required = true, value = "通用名称")
    private String goodsGenericName;

    @ApiModelProperty(required = true, value = "剂型")
    private String dosageName;

    @ApiModelProperty(required = true, value = "规格")
    private String goodsSpecification;

    @ApiModelProperty(required = true, value = "单位")
    private String unitName;

    @ApiModelProperty(required = true, value = "生产厂商")
    private String manufacturer;

    @ApiModelProperty(required = true, value = "产地")
    private String place;

    @ApiModelProperty(required = true, value = "批号")
    private String lotNumber;

    @ApiModelProperty(required = true, value = "生产日期")
    private String productDate;

    @ApiModelProperty(required = true, value = "有效期至")
    private String validUntil;

    @ApiModelProperty(required = true, value = "源货位")
    private String srcShelfName;

    @ApiModelProperty(required = true, value = "目标货位质量状态")
    private String targetShelfStatusDesc;

    @ApiModelProperty(required = true, value = "源货位质量状态")
    private String srcShelfStatusDesc;

    @ApiModelProperty(required = true, value = "目标货位")
    private String targetShelfName;

    @ApiModelProperty(required = true, value = "数量")
    private BigDecimal quantity;

    @ApiModelProperty(required = true, value = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public String getSrcShelfName() {
        return srcShelfName;
    }

    public void setSrcShelfName(String srcShelfName) {
        this.srcShelfName = srcShelfName;
    }

    public String getTargetShelfStatusDesc() {
        return targetShelfStatusDesc;
    }

    public void setTargetShelfStatusDesc(String targetShelfStatusDesc) {
        this.targetShelfStatusDesc = targetShelfStatusDesc;
    }

    public String getSrcShelfStatusDesc() {
        return srcShelfStatusDesc;
    }

    public void setSrcShelfStatusDesc(String srcShelfStatusDesc) {
        this.srcShelfStatusDesc = srcShelfStatusDesc;
    }

    public String getTargetShelfName() {
        return targetShelfName;
    }

    public void setTargetShelfName(String targetShelfName) {
        this.targetShelfName = targetShelfName;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ChGoodsLoadOrderDtlListOneVO[" +
                "id=" + id +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", unitName='" + unitName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", place='" + place + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validUntil=" + validUntil +
                ", srcShelfName='" + srcShelfName + '\'' +
                ", targetShelfStatusDesc='" + targetShelfStatusDesc + '\'' +
                ", srcShelfStatusDesc='" + srcShelfStatusDesc + '\'' +
                ", targetShelfName='" + targetShelfName + '\'' +
                ", quantity=" + quantity +
                ", remark='" + remark + '\'' +
                ']';
    }
}
