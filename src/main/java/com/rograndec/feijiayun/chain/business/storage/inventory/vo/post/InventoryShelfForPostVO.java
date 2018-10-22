package com.rograndec.feijiayun.chain.business.storage.inventory.vo.post;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_inventory_detail
 * 
 * 
 * @author ST
 * 
 * 2017-09-29
 */
public class InventoryShelfForPostVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

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
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;


    @ApiModelProperty("生产日期")
    private Date productDate;

    @ApiModelProperty("有效期")
    private Date validDate;

    @ApiModelProperty("批号ID")
    private Long lotId;

    @ApiModelProperty("批号")
    private String lotNumber;

    @ApiModelProperty("货位id")
    private Long shelfId;

    @ApiModelProperty("货位名称")
    private String shelfName;


    @ApiModelProperty("货位质量状态描述")
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

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}