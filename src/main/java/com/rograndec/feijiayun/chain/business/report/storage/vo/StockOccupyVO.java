package com.rograndec.feijiayun.chain.business.report.storage.vo;

import com.rograndec.feijiayun.chain.common.Page;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class StockOccupyVO implements Serializable{
    /**
     * 库存数量
     */
    @ApiModelProperty(value = "库存数量")
    private BigDecimal quantity;

    /**
     * 占用数量
     */
    @ApiModelProperty(value = "占用数量")
    private BigDecimal occupyQuantity;

    /**
     * 可用数量
     */
    @ApiModelProperty(value = "可用数量")
    private BigDecimal usableQuantity;

    /**
     * page信息
     */
    @ApiModelProperty(value = "page信息")
    private Page<List<StockOccupyPageVO>> page;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getOccupyQuantity() {
        return occupyQuantity;
    }

    public void setOccupyQuantity(BigDecimal occupyQuantity) {
        this.occupyQuantity = occupyQuantity;
    }

    public BigDecimal getUsableQuantity() {
        return usableQuantity;
    }

    public void setUsableQuantity(BigDecimal usableQuantity) {
        this.usableQuantity = usableQuantity;
    }

    public Page<List<StockOccupyPageVO>> getPage() {
        return page;
    }

    public void setPage(Page<List<StockOccupyPageVO>> page) {
        this.page = page;
    }
}
