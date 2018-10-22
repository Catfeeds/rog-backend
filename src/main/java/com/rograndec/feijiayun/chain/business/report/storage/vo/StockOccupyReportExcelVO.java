package com.rograndec.feijiayun.chain.business.report.storage.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class StockOccupyReportExcelVO implements Serializable{

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
     * List信息
     */
    @ApiModelProperty(value = "List信息")
    private List<StockOccupyExcelPageVO> list;

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

    public List<StockOccupyExcelPageVO> getList() {
        return list;
    }

    public void setList(List<StockOccupyExcelPageVO> list) {
        this.list = list;
    }
}
