package com.rograndec.feijiayun.chain.business.storage.move.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 其他出库选择商品vo
 */
public class OtherOutGoodsPageVO implements Serializable {

    @ApiModelProperty(value = "商品id")
    private Long goodsId; // 商品id
    @ApiModelProperty(value = "商品编码")
    private String code; // 商品编码
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName; // 商品通用名称
    @ApiModelProperty(value = "商品剂型id")
    private Long dosageId; // 商品剂型id
    @ApiModelProperty(value = "商品剂型")
    private String dosageName; // 商品剂型
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification; // 商品规格
    @ApiModelProperty(value = "商品单位id")
    private Long unitId; // 商品单位id
    @ApiModelProperty(value = "商品单位")
    private String unitName; // 商品单位
    @ApiModelProperty(value = "商品生产厂商id")
    private Long manufacturerId; // 商品生产厂商id
    @ApiModelProperty(value = "商品生产厂商")
    private String manufacturer; // 商品生产厂商
    @ApiModelProperty(value = "产地")
    private String place; // 产地
    @ApiModelProperty(value = "有效期")
    private Date validDate; // 有效期
    @ApiModelProperty(value = "生产日期")
    private Date productDate; // 生产日期
    @ApiModelProperty(value = "批号id")
    private Long lotId; // 批号id
    @ApiModelProperty(value = "批号")
    private String lotNumber; // 批号
    @ApiModelProperty(value = "货位id")
    private Long shelfId; // 货位id
    @ApiModelProperty(value = "货位号")
    private String shelfName; // 货位号
    @ApiModelProperty(value = "库存id")
    private Long stockId; // 库存id
    @ApiModelProperty(value = "货位质量状况")
    private String shelfStatusDesc; // 货位质量状况
    @ApiModelProperty(value = "库存可用量")
    private BigDecimal usableQuantity; // 库存可用量
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice; // 单价
    @ApiModelProperty(value = "金额")
    private BigDecimal amount; // 金额
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate; // 税率
    @ApiModelProperty(value = "税率id")
    private Long taxRateId; // 税率id
    @ApiModelProperty(value = "不含税实际单价")
    private BigDecimal notaxRealPrice; // 不含税实际单价
    @ApiModelProperty(value = "不含税实际金额")
    private BigDecimal notaxRealAmount; // 不含税实际金额
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount; // 税额
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity = new BigDecimal(1);


    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public Long getDosageId() {
        return dosageId;
    }

    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
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

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
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

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
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

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public BigDecimal getNotaxRealPrice() {
        return notaxRealPrice;
    }

    public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
        this.notaxRealPrice = notaxRealPrice;
    }

    public BigDecimal getNotaxRealAmount() {
        return notaxRealAmount;
    }

    public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
        this.notaxRealAmount = notaxRealAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUsableQuantity() {
        return usableQuantity;
    }

    public void setUsableQuantity(BigDecimal usableQuantity) {
        this.usableQuantity = usableQuantity;
    }
}
