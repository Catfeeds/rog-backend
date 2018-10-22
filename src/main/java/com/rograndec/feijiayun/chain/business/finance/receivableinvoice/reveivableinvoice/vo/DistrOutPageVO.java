package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DistrOutPageVO implements Serializable {

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
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private Date baseOrderDate;

    /**
     * 单据类型
     */
    @ApiModelProperty(value = "单据类型")
    private Integer baseOrderType;

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
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 条形码
     */
    @ApiModelProperty(value = "条形码")
    private String barcode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地")
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

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
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;

    /**
     * 货位
     */
    @ApiModelProperty(value = "货位")
    private String shelfName;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    /**
     * 原始单据单价
     */
    @ApiModelProperty(value = "原始单据单价")
    private BigDecimal baseOrderUnitPrice;

    /**
     * 原始单据单价
     */
    @ApiModelProperty(value = "原始单据单价-只用于列表显示")
    private BigDecimal baseOrderUnitPriceDisplay;

    /**
     * 折扣
     */
    @ApiModelProperty(value = "折扣")
    private BigDecimal lineDiscount;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    /**
     * 原始单据金额
     */
    @ApiModelProperty(value = "原始单据金额")
    private BigDecimal baseOrderAmount;

    /**
     * 整单折扣
     */
    @ApiModelProperty(value = "整单折扣")
    private BigDecimal wholeDiscount;

    /**
     * 整单折扣金额
     */
    @ApiModelProperty(value = "整单折扣金额")
    private BigDecimal wholeDiscountAmount;

    /**
     * 整单优惠分摊
     */
    @ApiModelProperty(value = "整单优惠分摊")
    private BigDecimal lineDiscountAmount;

    /**
     * 总额
     */
    @ApiModelProperty(value = "总额")
    private BigDecimal realAmount;

    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;

    /**
     * 税率
     */
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;

    /**
     * 原始单据税率
     */
    @ApiModelProperty(value = "原始单据税率")
    private BigDecimal baseOrderTaxRate;

    /**
     * 不含税单价
     */
    @ApiModelProperty(value = "不含税单价")
    private BigDecimal notaxPrice;

    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    /**
     * 原始单据不含税单价
     */
    @ApiModelProperty(value = "原始单据不含税单价")
    private BigDecimal baseOrderNotaxPrice;

    /**
     * 原始单据不含税金额
     */
    @ApiModelProperty(value = "原始单据不含税金额")
    private BigDecimal baseOrderNotaxAmount;

    /**
     * 原始单据税额
     */
    @ApiModelProperty(value = "原始单据税额")
    private BigDecimal baseOrderTaxAmount;

    /**
     * 已清数量
     */
    @ApiModelProperty(value = "已清数量")
    private BigDecimal clearQuantity;

    /**
     * 未清数量
     */
    @ApiModelProperty(value = "未清数量")
    private BigDecimal unclearQuantity;

    /**
     * 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待收款；1-部分收款；2-已收款；3-已冲销）")
    private Integer status;

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

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
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

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
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

    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    public BigDecimal getLineDiscountAmount() {
        return lineDiscountAmount;
    }

    public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
        this.lineDiscountAmount = lineDiscountAmount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
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

    public BigDecimal getNotaxPrice() {
        return notaxPrice;
    }

    public void setNotaxPrice(BigDecimal notaxPrice) {
        this.notaxPrice = notaxPrice;
    }

    public BigDecimal getNotaxAmount() {
        return notaxAmount;
    }

    public void setNotaxAmount(BigDecimal notaxAmount) {
        this.notaxAmount = notaxAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getClearQuantity() {
        return clearQuantity;
    }

    public void setClearQuantity(BigDecimal clearQuantity) {
        this.clearQuantity = clearQuantity;
    }

    public BigDecimal getUnclearQuantity() {
        return unclearQuantity;
    }

    public void setUnclearQuantity(BigDecimal unclearQuantity) {
        this.unclearQuantity = unclearQuantity;
    }

    public BigDecimal getBaseOrderAmount() {
        return baseOrderAmount;
    }

    public void setBaseOrderAmount(BigDecimal baseOrderAmount) {
        this.baseOrderAmount = baseOrderAmount;
    }

    public BigDecimal getBaseOrderNotaxPrice() {
        return baseOrderNotaxPrice;
    }

    public void setBaseOrderNotaxPrice(BigDecimal baseOrderNotaxPrice) {
        this.baseOrderNotaxPrice = baseOrderNotaxPrice;
    }

    public BigDecimal getBaseOrderNotaxAmount() {
        return baseOrderNotaxAmount;
    }

    public void setBaseOrderNotaxAmount(BigDecimal baseOrderNotaxAmount) {
        this.baseOrderNotaxAmount = baseOrderNotaxAmount;
    }

    public BigDecimal getBaseOrderTaxAmount() {
        return baseOrderTaxAmount;
    }

    public void setBaseOrderTaxAmount(BigDecimal baseOrderTaxAmount) {
        this.baseOrderTaxAmount = baseOrderTaxAmount;
    }

    public BigDecimal getBaseOrderUnitPrice() {
        return baseOrderUnitPrice;
    }

    public void setBaseOrderUnitPrice(BigDecimal baseOrderUnitPrice) {
        this.baseOrderUnitPrice = baseOrderUnitPrice;
    }

    public BigDecimal getBaseOrderTaxRate() {
        return baseOrderTaxRate;
    }

    public void setBaseOrderTaxRate(BigDecimal baseOrderTaxRate) {
        this.baseOrderTaxRate = baseOrderTaxRate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getBaseOrderUnitPriceDisplay() {
        return baseOrderUnitPriceDisplay;
    }

    public void setBaseOrderUnitPriceDisplay(BigDecimal baseOrderUnitPriceDisplay) {
        this.baseOrderUnitPriceDisplay = baseOrderUnitPriceDisplay;
    }

    @Override
    public String toString() {
        return "DistrOutPageVO[" +
                "baseOrderId=" + baseOrderId +
                ", baseOrderCode='" + baseOrderCode + '\'' +
                ", baseOrderDate=" + baseOrderDate +
                ", baseOrderType=" + baseOrderType +
                ", baseDtlId=" + baseDtlId +
                ", baseShelfDtlId=" + baseShelfDtlId +
                ", goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", barcode='" + barcode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageId=" + dosageId +
                ", dosageName='" + dosageName + '\'' +
                ", unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", shelfName='" + shelfName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", baseOrderUnitPriceDisplay=" + baseOrderUnitPriceDisplay +
                ", lineDiscount=" + lineDiscount +
                ", amount=" + amount +
                ", baseOrderAmount=" + baseOrderAmount +
                ", wholeDiscount=" + wholeDiscount +
                ", wholeDiscountAmount=" + wholeDiscountAmount +
                ", lineDiscountAmount=" + lineDiscountAmount +
                ", realAmount=" + realAmount +
                ", taxRateId=" + taxRateId +
                ", taxRate=" + taxRate +
                ", notaxPrice=" + notaxPrice +
                ", notaxAmount=" + notaxAmount +
                ", taxAmount=" + taxAmount +
                ", baseOrderTaxRate=" + baseOrderTaxRate +
                ", baseOrderNotaxPrice=" + baseOrderNotaxPrice +
                ", baseOrderNotaxAmount=" + baseOrderNotaxAmount +
                ", baseOrderTaxAmount=" + baseOrderTaxAmount +
                ", baseOrderUnitPrice=" + baseOrderUnitPrice +
                ", clearQuantity=" + clearQuantity +
                ", status=" + status +
                ", unclearQuantity=" + unclearQuantity +
                ']';
    }
}
