package com.rograndec.feijiayun.chain.business.storage.inventory.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 功能描述：
 * Created by ST on 2017/10/6 15:39
 */

public class InventoryShelfSimpleVO {

    @ApiModelProperty("盘点明细单id")
    private Long id;

    @ApiModelProperty("商品id")
    private Long goodsId;

    @ApiModelProperty("批号ID")
    private Long lotId;

    @ApiModelProperty("批号")
    private String lotNumber;

    @ApiModelProperty("货位id")
    private Long shelfId;

    @ApiModelProperty("货位明细id")
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

    public Long getInventoryShelfId() {
        return inventoryShelfId;
    }

    public void setInventoryShelfId(Long inventoryShelfId) {
        this.inventoryShelfId = inventoryShelfId;
    }
}