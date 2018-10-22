package com.rograndec.feijiayun.chain.business.member.integralexchange.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dudy on 2017/9/24.
 */
public class IntegralExchangeReturnVO implements Serializable {


    @ApiModelProperty(value = "积分兑换明细")
    private List<IntegralExchangePageVO>  exchangeList;

    /**
     * 当前积分
     */
    @ApiModelProperty(value = "当前积分总和")
    private BigDecimal currentIntegralTotal;

    /**
     * 扣除积分
     */
    @ApiModelProperty(value = "扣除积分总和")
    private BigDecimal deductIntegralTotal;

    /**
     * 剩余积分
     */
    @ApiModelProperty(value = "剩余积分总和")
    private BigDecimal remainderIntegralTotal;


    public List<IntegralExchangePageVO> getExchangeList() {
        return exchangeList;
    }

    public void setExchangeList(List<IntegralExchangePageVO> exchangeList) {
        this.exchangeList = exchangeList;
    }

    public BigDecimal getCurrentIntegralTotal() {
        return currentIntegralTotal;
    }

    public void setCurrentIntegralTotal(BigDecimal currentIntegralTotal) {
        this.currentIntegralTotal = currentIntegralTotal;
    }

    public BigDecimal getDeductIntegralTotal() {
        return deductIntegralTotal;
    }

    public void setDeductIntegralTotal(BigDecimal deductIntegralTotal) {
        this.deductIntegralTotal = deductIntegralTotal;
    }

    public BigDecimal getRemainderIntegralTotal() {
        return remainderIntegralTotal;
    }

    public void setRemainderIntegralTotal(BigDecimal remainderIntegralTotal) {
        this.remainderIntegralTotal = remainderIntegralTotal;
    }
}
