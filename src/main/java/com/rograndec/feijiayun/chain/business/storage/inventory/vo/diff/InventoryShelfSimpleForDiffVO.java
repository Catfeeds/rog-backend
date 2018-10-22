package com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 功能描述：
 * Created by ST on 2017/10/6 15:39
 */

public class InventoryShelfSimpleForDiffVO {

    @ApiModelProperty("盘点明细单id")
    private Long id;


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


    public Long getInventoryShelfId() {
        return inventoryShelfId;
    }

    public void setInventoryShelfId(Long inventoryShelfId) {
        this.inventoryShelfId = inventoryShelfId;
    }
}