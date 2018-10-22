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
public class InventoryDetailForRegisterVO implements Serializable {
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

    @ApiModelProperty(value = "盘点货位明细id")
    private Long inventoryShelfId;


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

    public Long getInventoryShelfId() {
        return inventoryShelfId;
    }

    public void setInventoryShelfId(Long inventoryShelfId) {
        this.inventoryShelfId = inventoryShelfId;
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