package com.rograndec.feijiayun.chain.business.report.finance.stock.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @program: chain-backend
 * @description: 库存余额TotalVO
 * @author: dongyang.du
 * @create: 2018-01-11 11:51
 **/
public class StockBalanceTotalVO implements Serializable{

    @ApiModelProperty(value = "余额数量总和")
    private BigDecimal quantityTotal;

    @ApiModelProperty(value = "余额金额总和")
    private BigDecimal realAmountTotal;

    @ApiModelProperty(value = "列表List")
    List<StockBalanceResultVO> stockBalanceResultVOList;


    public List<StockBalanceResultVO> getStockBalanceResultVOList() {
        return stockBalanceResultVOList;
    }

    public void setStockBalanceResultVOList(List<StockBalanceResultVO> stockBalanceResultVOList) {
        this.stockBalanceResultVOList = stockBalanceResultVOList;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }
}
