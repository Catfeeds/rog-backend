package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_prepay_invoice
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
@ApiModel
public class PrepayInvoiceInStoreResponseVO implements Serializable {

    /**
     * 单据id
     */
    @ApiModelProperty(value = "单据id")
    private Long orderShelfId;

    /**
     * 单据类型
     */
    @ApiModelProperty(value = "单据类型")
    private Integer orderType;

    /**
     * 配货类型（0-总部配送；1-分店间调剂；2-直调配送）
     */
    @ApiModelProperty(value = "配货类型（0-总部配送；1-分店间调剂；2-直调配送）")
    private Integer distrType;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date orderDate;

    /**
     * 单号
     */
    @ApiModelProperty(value = "单号")
    private String code;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期至
     */
    @ApiModelProperty(required = true, value = "有效期至")
    private Date validUntil;

    /**
     * 入库数量
     */
    @ApiModelProperty(value = "入库数量")
    private BigDecimal quantity;

    /**
     * 未清数量
     */
    @ApiModelProperty(value = "未清数量")
    private BigDecimal unclearQuantity;

    /**
     * 已清数量
     */
    @ApiModelProperty(value = "已清数量")
    private BigDecimal clearQuantity;

    /**
     *已核销数量
     */
    @ApiModelProperty(value = "已核销数量")
    private BigDecimal verificationQuantity;

    /**
     *未核销数量
     */
    @ApiModelProperty(value = "未核销数量")
    private BigDecimal unverificationQuantity;

    @ApiModelProperty(value = "最大开票数量")
    private BigDecimal maxQuantity;

    /**
     * 本次对账数量
     */
    @ApiModelProperty(value = "本次对账数量")
    private BigDecimal accountQuantity = BigDecimal.ZERO;

    /**
     * 单价
     */
    @ApiModelProperty(required = true, value = "单价")
    private BigDecimal unitPrice = BigDecimal.ZERO;


    /**
     *税率ID
     */
    @ApiModelProperty(required = true, value = "税率ID")
    private Long taxRateId = 0L;

    /**
     * 税率
     */
    @ApiModelProperty(required = true, value = "最新采购税率")
    private BigDecimal taxRate  = BigDecimal.ZERO;

    /**
     * 不含税实际单价
     */
    @ApiModelProperty(value = "不含税实际单价")
    private BigDecimal notaxRealPrice;

    /**
     * 本次对账金额
     */
    @ApiModelProperty(value = "本次对账金额")
    private BigDecimal accountAmount = BigDecimal.ZERO;

    /**
     * 本次对账不含税金额
     */
    @ApiModelProperty(value = "本次对账不含税金额")
    private BigDecimal accountNotaxAmount = BigDecimal.ZERO;

    /**
     * 本次对账税额
     */
    @ApiModelProperty(value = "本次对账税额")
    private BigDecimal accountTaxAmount = BigDecimal.ZERO;

    /**
     * 经营方式（0-购销；1-实销实结）
     */
    private Integer managementMode;

    public Long getOrderShelfId() {
        return orderShelfId;
    }

    public void setOrderShelfId(Long orderShelfId) {
        this.orderShelfId = orderShelfId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnclearQuantity() {
        return unclearQuantity;
    }

    public void setUnclearQuantity(BigDecimal unclearQuantity) {
        this.unclearQuantity = unclearQuantity;
    }

    public BigDecimal getClearQuantity() {
        return clearQuantity;
    }

    public void setClearQuantity(BigDecimal clearQuantity) {
        this.clearQuantity = clearQuantity;
    }

    public BigDecimal getAccountQuantity() {
        return accountQuantity;
    }

    public void setAccountQuantity(BigDecimal accountQuantity) {
        this.accountQuantity = accountQuantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    public BigDecimal getAccountNotaxAmount() {
        return accountNotaxAmount;
    }

    public void setAccountNotaxAmount(BigDecimal accountNotaxAmount) {
        this.accountNotaxAmount = accountNotaxAmount;
    }

    public BigDecimal getAccountTaxAmount() {
        return accountTaxAmount;
    }

    public void setAccountTaxAmount(BigDecimal accountTaxAmount) {
        this.accountTaxAmount = accountTaxAmount;
    }

    public Integer getManagementMode() {
        return managementMode;
    }

    public void setManagementMode(Integer managementMode) {
        this.managementMode = managementMode;
    }

    public BigDecimal getVerificationQuantity() {
        return verificationQuantity;
    }

    public void setVerificationQuantity(BigDecimal verificationQuantity) {
        this.verificationQuantity = verificationQuantity;
    }

    public BigDecimal getUnverificationQuantity() {
        return unverificationQuantity;
    }

    public void setUnverificationQuantity(BigDecimal unverificationQuantity) {
        this.unverificationQuantity = unverificationQuantity;
    }

    public BigDecimal getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(BigDecimal maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}