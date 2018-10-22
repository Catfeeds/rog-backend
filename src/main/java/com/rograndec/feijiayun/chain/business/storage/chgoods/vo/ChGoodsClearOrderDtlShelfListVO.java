package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ChGoodsClearOrderDtlShelfListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "中药饮片清斗明细ID")
    private Long id;

    @ApiModelProperty(required = true, value = "商品ID")
    private Long goodsId;

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

    @ApiModelProperty(required = true, value = "数量")
    private BigDecimal quantity;

    @ApiModelProperty(required = true, value = "备注")
    private String remark;

    List<ChGoodsClearOrderDtlShelfListOneVO> chGoodsClearOrderDtlShelfListOneVO;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public List<ChGoodsClearOrderDtlShelfListOneVO> getChGoodsClearOrderDtlShelfListOneVO() {
        return chGoodsClearOrderDtlShelfListOneVO;
    }

    public void setChGoodsClearOrderDtlShelfListOneVO(List<ChGoodsClearOrderDtlShelfListOneVO> chGoodsClearOrderDtlShelfListOneVO) {
        this.chGoodsClearOrderDtlShelfListOneVO = chGoodsClearOrderDtlShelfListOneVO;
    }

    @Override
    public String toString() {
        return "ChGoodsClearOrderDtlShelfListVO[" +
                "id=" + id +
                ", goodsId='" + goodsId + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", unitName='" + unitName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", quantity='" + quantity + '\'' +
                ", place='" + place + '\'' +
                ", remark='" + remark + '\'' +
                ", chGoodsClearOrderDtlShelfListOneVO=" + chGoodsClearOrderDtlShelfListOneVO +
                ']';
    }
}
