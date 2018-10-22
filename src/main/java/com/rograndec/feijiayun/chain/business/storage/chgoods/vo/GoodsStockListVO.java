package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GoodsStockListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "库存表主键ID")
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

    @ApiModelProperty(required = true, value = "批号ID")
    private Long lotId;

    @ApiModelProperty(required = true, value = "批号")
    private String lotNumber;

    @ApiModelProperty(required = true, value = "生产日期")
    private Date productDate;

    @ApiModelProperty(required = true, value = "有效期至")
    private Date validDate;

    @ApiModelProperty(required = true, value = "货位ID")
    private Long shelfId;

    @ApiModelProperty(required = true, value = "货位")
    private String shelfName;

    @ApiModelProperty(required = true, value = "货位质量状况")
    private String shelfStatusDesc;

    @ApiModelProperty(required = true, value = "数量")
    private BigDecimal quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    @Override
    public String toString() {
        return "GoodsStockListVO[" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", unitName='" + unitName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", place='" + place + '\'' +
                ", lotId='" + lotId + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", shelfId='" + shelfId + '\'' +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                ", shelfName='" + shelfName + '\'' +
                ", quantity=" + quantity +
                ']';
    }
}
