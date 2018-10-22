package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SaveReceivableVoucherDetailVO implements Serializable {

    /**
     * 应收贷项凭证细表主键ID
     */
    @ApiModelProperty(value = "应收贷项凭证细表主键ID(更新时必传字段)")
    private Long id;

    /**
     * 应收贷项凭证总单ID
     */
    @ApiModelProperty(value = "应收贷项凭证总单ID")
    private Long voucherId;

    /**
     * 单据ID
     */
    @ApiModelProperty(value = "单据ID(新增必传)")
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
    @ApiModelProperty(value = "单据类型(新增必传)")
    private Integer baseOrderType;

    /**
     * 单据商品明细ID
     */
    @ApiModelProperty(value = "单据商品明细ID(新增必传)")
    private Long baseDtlId;

    /**
     * 单据货位明细ID
     */
    @ApiModelProperty(value = "单据货位明细ID(新增必传)")
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
     * 发票数量
     */
    @ApiModelProperty(value = "发票数量(新增必传)")
    private BigDecimal quantity;

    /**
     * 发票单价
     */
    @ApiModelProperty(value = "发票单价(新增必传)")
    private BigDecimal unitPrice;

    /**
     * 原始单据单价
     */
    @ApiModelProperty(value = "原始单据单价(新增必传)")
    private BigDecimal baseOrderUnitPrice;

    /**
     * 发票金额
     */
    @ApiModelProperty(value = "发票金额")
    private BigDecimal amount;

    /**
     * 原始单据金额
     */
    @ApiModelProperty(value = "原始单据金额")
    private BigDecimal baseOrderAmount;

    /**
     * 发票税率ID
     */
    @ApiModelProperty(value = "发票税率ID(新增必传)")
    private Long taxRateId;

    /**
     * 发票税率
     */
    @ApiModelProperty(value = "发票税率")
    private BigDecimal taxRate;

    /**
     * 原始单据税率
     */
    @ApiModelProperty(value = "原始单据税率")
    private BigDecimal baseOrderTaxRate;

    /**
     * 发票不含税单价
     */
    @ApiModelProperty(value = "发票不含税单价")
    private BigDecimal notaxPrice;

    /**
     * 发票不含税金额
     */
    @ApiModelProperty(value = "发票不含税金额")
    private BigDecimal notaxAmount;

    /**
     * 发票税额
     */
    @ApiModelProperty(value = "发票税额")
    private BigDecimal taxAmount;

    /**
     * 发票金额差额
     */
    @ApiModelProperty(value = "发票金额差额")
    private BigDecimal diffAmount;

    /**
     * 发票不含税金额差额
     */
    @ApiModelProperty(value = "发票不含税金额差额")
    private BigDecimal diffNotaxAmount;

    /**
     * 发票税额差额
     */
    @ApiModelProperty(value = "发票税额差额")
    private BigDecimal diffTaxAmount;

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
     * 行号
     */
    @ApiModelProperty(value = "行号(新增必传)")
    private Integer lineNum;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public BigDecimal getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(BigDecimal diffAmount) {
        this.diffAmount = diffAmount;
    }

    public BigDecimal getDiffNotaxAmount() {
        return diffNotaxAmount;
    }

    public void setDiffNotaxAmount(BigDecimal diffNotaxAmount) {
        this.diffNotaxAmount = diffNotaxAmount;
    }

    public BigDecimal getDiffTaxAmount() {
        return diffTaxAmount;
    }

    public void setDiffTaxAmount(BigDecimal diffTaxAmount) {
        this.diffTaxAmount = diffTaxAmount;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getBaseOrderUnitPrice() {
        return baseOrderUnitPrice;
    }

    public void setBaseOrderUnitPrice(BigDecimal baseOrderUnitPrice) {
        this.baseOrderUnitPrice = baseOrderUnitPrice;
    }

    public BigDecimal getBaseOrderAmount() {
        return baseOrderAmount;
    }

    public void setBaseOrderAmount(BigDecimal baseOrderAmount) {
        this.baseOrderAmount = baseOrderAmount;
    }

    public BigDecimal getBaseOrderTaxRate() {
        return baseOrderTaxRate;
    }

    public void setBaseOrderTaxRate(BigDecimal baseOrderTaxRate) {
        this.baseOrderTaxRate = baseOrderTaxRate;
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

    @Override
    public String toString() {
        return "SaveReceivableVoucherDetailVO[" +
                "id=" + id +
                ", voucherId=" + voucherId +
                ", baseOrderId=" + baseOrderId +
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
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", baseOrderUnitPrice=" + baseOrderUnitPrice +
                ", amount=" + amount +
                ", baseOrderAmount=" + baseOrderAmount +
                ", taxRateId=" + taxRateId +
                ", taxRate=" + taxRate +
                ", baseOrderTaxRate=" + baseOrderTaxRate +
                ", notaxPrice=" + notaxPrice +
                ", notaxAmount=" + notaxAmount +
                ", taxAmount=" + taxAmount +
                ", diffAmount=" + diffAmount +
                ", diffNotaxAmount=" + diffNotaxAmount +
                ", diffTaxAmount=" + diffTaxAmount +
                ", baseOrderNotaxPrice=" + baseOrderNotaxPrice +
                ", baseOrderNotaxAmount=" + baseOrderNotaxAmount +
                ", baseOrderTaxAmount=" + baseOrderTaxAmount +
                ", lineNum=" + lineNum +
                ", remark='" + remark + '\'' +
                ']';
    }
}
