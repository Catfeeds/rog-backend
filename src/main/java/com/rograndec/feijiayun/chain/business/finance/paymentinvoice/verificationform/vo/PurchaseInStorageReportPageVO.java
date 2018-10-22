package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PurchaseInStorageReportPageVO implements Serializable {

    /**
     * 单据（入库单）编号
     */
    @ApiModelProperty(value = "单据（入库单）编号", required = true)
    private String code;

    public BigDecimal getVerificationQuantity() {
        return verificationQuantity;
    }

    public void setVerificationQuantity(BigDecimal verificationQuantity) {
        this.verificationQuantity = verificationQuantity;
    }

    public BigDecimal getAutoVerificationQuantity() {
        return autoVerificationQuantity;
    }

    public void setAutoVerificationQuantity(BigDecimal autoVerificationQuantity) {
        this.autoVerificationQuantity = autoVerificationQuantity;
    }

    @ApiModelProperty(value = "自动核销的数量")
    private BigDecimal autoVerificationQuantity;

    @ApiModelProperty(value = "已经核对的数量")
    private BigDecimal verificationQuantity;

    @ApiModelProperty(value = "未核对的数量")
    private BigDecimal unverificationQuantity;

    public BigDecimal getUnverificationQuantity() {
        return unverificationQuantity;
    }

    public void setUnverificationQuantity(BigDecimal unverificationQuantity) {
        this.unverificationQuantity = unverificationQuantity;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期", required = true)
    private Date inStorageDate;

    /**
     * 单据ID
     */
    @ApiModelProperty(value = "单据ID")
    private Long baseOrderId;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String baseOrderCode;

    /**
     * 单据类型
     */
    @ApiModelProperty(value = "单据类型")
    private Integer baseOrderType;

    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private Date baseOrderDate;

    /**
     * 单据商品明细ID
     */
    @ApiModelProperty(value = "单据商品明细ID")
    private Long baseDtlId;

    /**
     * 单据货位明细ID
     */
    @ApiModelProperty(value = "单据货位明细ID")
    private Long baseShelfDtlId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号", required = true)
    private String lotNumber;


    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期", required = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期", required = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validDate;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    /**
     * 数量
     */
    @ApiModelProperty(value = "入库数量", required = true)
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价", required = true)
    private BigDecimal unitPrice;


    /**
     * 实际单价
     */
    @ApiModelProperty(value = "实际单价", required = true)
    private BigDecimal realPrice;

    /**
     * 实际金额
     */
    @ApiModelProperty(value = "实际金额", required = true)
    private BigDecimal realAmount;

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    public Long getBaseDtlId() {
        return baseDtlId;
    }

    public void setBaseDtlId(Long baseDtlId) {
        this.baseDtlId = baseDtlId;
    }

    public Long getBaseShelfDtlId() {
        return baseShelfDtlId;
    }

    public void setBaseShelfDtlId(Long baseShelfDtlId) {
        this.baseShelfDtlId = baseShelfDtlId;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    /**
     * 税率ID

     */
    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;

    /**
     * 税率
     */
    @ApiModelProperty(value = "税率", required = true)
    private BigDecimal taxRate;

    /**
     * 不含税单价
     */
    @ApiModelProperty(value = "不含税单价", required = true)
    private BigDecimal notaxRealPrice;

    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额", required = true)
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额", required = true)
    private BigDecimal taxAmount;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额", required = true)
    private BigDecimal amount;

    /**
     * 整单折扣
     */
    @ApiModelProperty(value = "整单折扣", required = true)
    private BigDecimal wholeDiscount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getInStorageDate() {
        return inStorageDate;
    }

    public void setInStorageDate(Date inStorageDate) {
        this.inStorageDate = inStorageDate;
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

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
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
}
