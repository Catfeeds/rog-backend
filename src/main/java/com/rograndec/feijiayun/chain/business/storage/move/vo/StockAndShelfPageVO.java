package com.rograndec.feijiayun.chain.business.storage.move.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * saas_other_out_shelf
 * 
 * 
 * @author dudy
 * 
 * 2017-09-27
 */
public class StockAndShelfPageVO implements Serializable {

    @ApiModelProperty(value = "货位id" )
    private Long shelfId;
    @ApiModelProperty(value = "库存id" )
    private Long stockId;
    @ApiModelProperty(value = "货位" )
    private String shelfName;
    @ApiModelProperty(value = "库存可用数量" )
    private BigDecimal usableQuantity;
    @ApiModelProperty(value = "货位质量状态描述" )
    private String shelfStatusDesc;

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public BigDecimal getUsableQuantity() {
        return usableQuantity;
    }

    public void setUsableQuantity(BigDecimal usableQuantity) {
        this.usableQuantity = usableQuantity;
    }

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }
}