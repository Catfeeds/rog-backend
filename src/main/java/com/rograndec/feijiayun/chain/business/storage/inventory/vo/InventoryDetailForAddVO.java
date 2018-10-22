package com.rograndec.feijiayun.chain.business.storage.inventory.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * saas_inventory_detail
 * 
 * 
 * @author ST
 * 
 * 2017-09-29
 */
public class InventoryDetailForAddVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 单据（盘点单）ID
     */
    @ApiModelProperty(value = "单据（盘点单）ID")
    private Long invId;

//    /**
//     * 单据（盘点单）类型
//     */
//    @ApiModelProperty(value = "单据（盘点单）类型")
//    private Integer orderType;
//
//    /**
//     * 单据（盘点单）编码
//     */
//    @ApiModelProperty(value = "单据（盘点单）编码")
//    private String invCode;
//
//    /**
//     * 单据（盘点单）日期
//     */
//    @ApiModelProperty(value = "单据（盘点单）日期")
//    private Date invDate;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

//    /**
//     * 商品编码
//     */
//    @ApiModelProperty(value = "商品编码")
//    private String goodsCode;
//
//    /**
//     * 条形码
//     */
//    @ApiModelProperty(value = "条形码")
//    private String barcode;
//
//    /**
//     * 商品名称
//     */
//    @ApiModelProperty(value = "商品名称")
//    private String goodsName;
//
//    /**
//     * 商品通用名称
//     */
//    @ApiModelProperty(value = "商品通用名称")
//    private String goodsGenericName;
//
//    /**
//     * 剂型ID
//     */
//    @ApiModelProperty(value = "剂型ID")
//    private Long dosageId;
//
//    /**
//     * 剂型名称
//     */
//    @ApiModelProperty(value = "剂型名称")
//    private String dosageName;
//
//    /**
//     * 单位ID
//     */
//    @ApiModelProperty(value = "单位ID")
//    private Long unitId;
//
//    /**
//     * 单位名称
//     */
//    @ApiModelProperty(value = "单位名称")
//    private String unitName;
//
//    /**
//     * 商品规格
//     */
//    @ApiModelProperty(value = "商品规格")
//    private String goodsSpecification;
//
//    /**
//     * 生产厂商ID
//     */
//    @ApiModelProperty(value = "生产厂商ID")
//    private Long manufacturerId;
//
//    /**
//     * 生产厂商
//     */
//    @ApiModelProperty(value = "生产厂商")
//    private String manufacturer;
//
//    /**
//     * 商品产地
//     */
//    @ApiModelProperty(value = "商品产地")
//    private String goodsPlace;
//
//    /**
//     * 批准文号
//     */
//    @ApiModelProperty(value = "批准文号")
//    private String approvalNumber;

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

//    /**
//     * 单价
//     */
//    @ApiModelProperty(value = "单价")
//    private BigDecimal unitPrice;

//    /**
//     * 账面金额
//     */
//    @ApiModelProperty(value = "账面金额")
//    private BigDecimal amount;
//
//    /**
//     * 实盘金额
//     */
//    @ApiModelProperty(value = "实盘金额")
//    private BigDecimal realAmount;
//
//    /**
//     * 损益金额
//     */
//    @ApiModelProperty(value = "损益金额")
//    private BigDecimal diffAmount;
//
//    /**
//     * 税率ID
//     */
//    @ApiModelProperty(value = "税率ID")
//    private Long taxRateId;
//
//    /**
//     * 税率
//     */
//    @ApiModelProperty(value = "税率")
//    private BigDecimal taxRate;
//
//    /**
//     * 不含税单价
//     */
//    @ApiModelProperty(value = "不含税单价")
//    private BigDecimal notaxPrice;
//
//    /**
//     * 不含税账面金额
//     */
//    @ApiModelProperty(value = "不含税账面金额")
//    private BigDecimal notaxAmount;
//
//    /**
//     * 不含税实盘金额
//     */
//    @ApiModelProperty(value = "不含税实盘金额")
//    private BigDecimal realNotaxAmount;
//
//    /**
//     * 不含税损益金额
//     */
//    @ApiModelProperty(value = "不含税损益金额")
//    private BigDecimal diffNotaxAmount;
//
//    /**
//     * 账面税额
//     */
//    @ApiModelProperty(value = "账面税额")
//    private BigDecimal taxAmount;
//
//    /**
//     * 实盘税额
//     */
//    @ApiModelProperty(value = "实盘税额")
//    private BigDecimal realTaxAmount;
//
//    /**
//     * 损益税额
//     */
//    @ApiModelProperty(value = "损益税额")
//    private BigDecimal diffTaxAmount;
//
//    /**
//     * 零售单价
//     */
//    @ApiModelProperty(value = "零售单价")
//    private BigDecimal retailPrice;
//
//    /**
//     * 账面零售金额
//     */
//    @ApiModelProperty(value = "账面零售金额")
//    private BigDecimal retailAmount;
//
//    /**
//     * 实盘零售金额
//     */
//    @ApiModelProperty(value = "实盘零售金额")
//    private BigDecimal realRetailAmount;
//
//    /**
//     * 损益零售金额
//     */
//    @ApiModelProperty(value = "损益零售金额")
//    private BigDecimal diffRetailAmount;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

//    /**
//     * 状态
//     */
//    @ApiModelProperty(value = "状态")
//    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty("批号ID")
    private Long lotId;

    @ApiModelProperty("批号")
    private String lotNumber;

    @ApiModelProperty("货位id")
    private Long shelfId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}