package com.rograndec.feijiayun.chain.business.report.finance.stock.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 库存明细 年 月 合计 公用类
 */
public class StockYearMonthTotal {

    @ApiModelProperty(value="发出数量")
    private BigDecimal outQuantityTotal;

    @ApiModelProperty(value="发出金额")
    private BigDecimal outAmountTotal;

    @ApiModelProperty(value="收入数量")
    private BigDecimal inQuantityTotal;

    @ApiModelProperty(value="收入金额")
    private BigDecimal inAmountTotal;


    public BigDecimal getOutQuantityTotal() {
        return outQuantityTotal;
    }

    public void setOutQuantityTotal(BigDecimal outQuantityTotal) {
        this.outQuantityTotal = outQuantityTotal;
    }

    public BigDecimal getOutAmountTotal() {
        return outAmountTotal;
    }

    public void setOutAmountTotal(BigDecimal outAmountTotal) {
        this.outAmountTotal = outAmountTotal;
    }

    public BigDecimal getInQuantityTotal() {
        return inQuantityTotal;
    }

    public void setInQuantityTotal(BigDecimal inQuantityTotal) {
        this.inQuantityTotal = inQuantityTotal;
    }

    public BigDecimal getInAmountTotal() {
        return inAmountTotal;
    }

    public void setInAmountTotal(BigDecimal inAmountTotal) {
        this.inAmountTotal = inAmountTotal;
    }

}
