package com.rograndec.feijiayun.chain.business.storage.inventory.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_inventory_shelf
 * 
 * 
 * @author ST
 * 
 * 2017-09-29
 */
public class InventoryShelfForAddVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 明细ID
     */
    @ApiModelProperty(value = "明细ID")
    private Long dtlId;

    /**
     * 单据（盘点单）ID
     */
    @ApiModelProperty(value = "单据（盘点单）ID")
    private Long invId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID")
    private Long lotId;

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
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 货位质量状态描述
     */
    @ApiModelProperty(value = "货位质量状态描述")
    private String shelfStatusDesc;

    /**
     * 账面数量
     */
    @ApiModelProperty(value = "账面数量")
    private BigDecimal quantity;

    /**
     * 实盘数量
     */
    @ApiModelProperty(value = "实盘数量")
    private BigDecimal invQuantity;

    /**
     * 损益数量
     */
    @ApiModelProperty(value = "损益数量")
    private BigDecimal diffQuantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    /**
     * 账面金额
     */
    @ApiModelProperty(value = "账面金额")
    private BigDecimal amount;

    /**
     * 实盘金额
     */
    @ApiModelProperty(value = "实盘金额")
    private BigDecimal realAmount;

    /**
     * 损益金额
     */
    @ApiModelProperty(value = "损益金额")
    private BigDecimal diffAmount;

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
     * 不含税单价
     */
    @ApiModelProperty(value = "不含税单价")
    private BigDecimal notaxPrice;

    /**
     * 不含税账面金额
     */
    @ApiModelProperty(value = "不含税账面金额")
    private BigDecimal notaxAmount;

    /**
     * 不含税实盘金额
     */
    @ApiModelProperty(value = "不含税实盘金额")
    private BigDecimal realNotaxAmount;

    /**
     * 不含税损益金额
     */
    @ApiModelProperty(value = "不含税损益金额")
    private BigDecimal diffNotaxAmount;

    /**
     * 账面税额
     */
    @ApiModelProperty(value = "账面税额")
    private BigDecimal taxAmount;

    /**
     * 实盘税额
     */
    @ApiModelProperty(value = "实盘税额")
    private BigDecimal realTaxAmount;

    /**
     * 损益税额
     */
    @ApiModelProperty(value = "损益税额")
    private BigDecimal diffTaxAmount;

    /**
     * 零售单价
     */
    @ApiModelProperty(value = "零售单价")
    private BigDecimal retailPrice;

    /**
     * 账面零售金额
     */
    @ApiModelProperty(value = "账面零售金额")
    private BigDecimal retailAmount;

    /**
     * 实盘零售金额
     */
    @ApiModelProperty(value = "实盘零售金额")
    private BigDecimal realRetailAmount;

    /**
     * 损益零售金额
     */
    @ApiModelProperty(value = "损益零售金额")
    private BigDecimal diffRetailAmount;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDtlId() {
        return dtlId;
    }

    public void setDtlId(Long dtlId) {
        this.dtlId = dtlId;
    }

    public Long getInvId() {
        return invId;
    }

    public void setInvId(Long invId) {
        this.invId = invId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getInvQuantity() {
        return invQuantity;
    }

    public void setInvQuantity(BigDecimal invQuantity) {
        this.invQuantity = invQuantity;
    }

    public BigDecimal getDiffQuantity() {
        return diffQuantity;
    }

    public void setDiffQuantity(BigDecimal diffQuantity) {
        this.diffQuantity = diffQuantity;
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

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(BigDecimal diffAmount) {
        this.diffAmount = diffAmount;
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

    public BigDecimal getRealNotaxAmount() {
        return realNotaxAmount;
    }

    public void setRealNotaxAmount(BigDecimal realNotaxAmount) {
        this.realNotaxAmount = realNotaxAmount;
    }

    public BigDecimal getDiffNotaxAmount() {
        return diffNotaxAmount;
    }

    public void setDiffNotaxAmount(BigDecimal diffNotaxAmount) {
        this.diffNotaxAmount = diffNotaxAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getRealTaxAmount() {
        return realTaxAmount;
    }

    public void setRealTaxAmount(BigDecimal realTaxAmount) {
        this.realTaxAmount = realTaxAmount;
    }

    public BigDecimal getDiffTaxAmount() {
        return diffTaxAmount;
    }

    public void setDiffTaxAmount(BigDecimal diffTaxAmount) {
        this.diffTaxAmount = diffTaxAmount;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getRetailAmount() {
        return retailAmount;
    }

    public void setRetailAmount(BigDecimal retailAmount) {
        this.retailAmount = retailAmount;
    }

    public BigDecimal getRealRetailAmount() {
        return realRetailAmount;
    }

    public void setRealRetailAmount(BigDecimal realRetailAmount) {
        this.realRetailAmount = realRetailAmount;
    }

    public BigDecimal getDiffRetailAmount() {
        return diffRetailAmount;
    }

    public void setDiffRetailAmount(BigDecimal diffRetailAmount) {
        this.diffRetailAmount = diffRetailAmount;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}