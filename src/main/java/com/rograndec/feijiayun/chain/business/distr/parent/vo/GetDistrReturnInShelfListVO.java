package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GetDistrReturnInShelfListVO implements Serializable {

    /**
     * 配后退回入库单ID
     */
    @ApiModelProperty(required = true, value = "配后退回入库单ID")
    private Long returnInId;

    /**
     * 配后退回入库单编号
     */
    @ApiModelProperty(required = true, value = "配后退回入库单编号")
    private String returnInCode;

    /**
     * 行号
     */
    @ApiModelProperty(required = true, value = "行号")
    private Integer lineNum;

    /**
     * 商品ID
     */
    @ApiModelProperty(required = true, value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(required = true, value = "商品编码")
    private String goodsCode;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(required = true, value = "商品通用名称")
    private String goodsGenericName;

    /**
     * 剂型名称
     */
    @ApiModelProperty(required = true, value = "剂型名称")
    private String dosageName;

    /**
     * 单位名称
     */
    @ApiModelProperty(required = true, value = "单位名称")
    private String unitName;

    /**
     * 商品规格
     */
    @ApiModelProperty(required = true, value = "商品规格")
    private String goodsSpecification;

    /**
     * 生产厂商
     */
    @ApiModelProperty(required = true, value = "生产厂商")
    private String manufacturer;

    /**
     * 批号
     */
    @ApiModelProperty(required = true, value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(required = true, value = "生产日期")
    private Date productDate;

    /**
     * 有效期至
     */
    @ApiModelProperty(required = true, value = "有效期至")
    private Date validDate;

    /**
     * 入库数量
     */
    @ApiModelProperty(required = true, value = "入库数量")
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(required = true, value = "单价")
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    @ApiModelProperty(required = true, value = "行折扣（%）")
    private BigDecimal lineDiscount;

    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(required = true, value = "金额（整单优惠前金额）")
    private BigDecimal amount;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(required = true, value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 行优惠（整单优惠分摊到行的金额）
     */
    @ApiModelProperty(required = true, value = "行优惠（整单优惠分摊到行的金额）")
    private BigDecimal lineDiscountAmount;

    /**
     * 进项税id
     */
    @ApiModelProperty(required = true, value = "进项税id")
    private Long taxRateId;

    /**
     * 进项税
     */
    @ApiModelProperty(required = true, value = "进项税")
    private BigDecimal taxRate;

    /**
     * 不含税实际单价
     */
    @ApiModelProperty(required = true, value = "不含税实际单价")
    private BigDecimal notaxRealPrice;

    /**
     * 不含税实际金额
     */
    @ApiModelProperty(required = true, value = "不含税实际金额")
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
    @ApiModelProperty(required = true, value = "税额")
    private BigDecimal taxAmount;

    public Long getReturnInId() {
        return returnInId;
    }

    public void setReturnInId(Long returnInId) {
        this.returnInId = returnInId;
    }

    public String getReturnInCode() {
        return returnInCode;
    }

    public void setReturnInCode(String returnInCode) {
        this.returnInCode = returnInCode;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineDiscount() {
        return lineDiscount;
    }

    public void setLineDiscount(BigDecimal lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getLineDiscountAmount() {
        return lineDiscountAmount;
    }

    public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
        this.lineDiscountAmount = lineDiscountAmount;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
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

    @Override
    public String toString() {
        return "GetDistrReturnInShelfListVO[" +
                "returnInId=" + returnInId +
                ", returnInCode='" + returnInCode + '\'' +
                ", lineNum=" + lineNum +
                ", goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", unitName='" + unitName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", lineDiscount=" + lineDiscount +
                ", amount=" + amount +
                ", wholeDiscount=" + wholeDiscount +
                ", lineDiscountAmount=" + lineDiscountAmount +
                ", taxRateId=" + taxRateId +
                ", taxRate=" + taxRate +
                ", notaxRealPrice=" + notaxRealPrice +
                ", notaxRealAmount=" + notaxRealAmount +
                ", taxAmount=" + taxAmount +
                ']';
    }
}
