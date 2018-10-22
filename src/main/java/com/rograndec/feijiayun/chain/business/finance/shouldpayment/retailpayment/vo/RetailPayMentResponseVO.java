package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class RetailPayMentResponseVO implements Serializable{
    private static final long serialVersionUID = -2467262176981396982L;

    @ApiModelProperty(value = "应缴现金合计")
    private BigDecimal cashTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "实缴现金合计")
    private BigDecimal realCashTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "现金差额合计")
    private BigDecimal diffCashTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "应缴银行存款合计")
    private BigDecimal bankAmountTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "应缴其它货币资金合计")
    private BigDecimal otherAmountTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "缴款单据集合")
    private List<RetailPaymentVO> retailPaymentVOS;

    public BigDecimal getCashTotal() {
        return cashTotal;
    }

    public void setCashTotal(BigDecimal cashTotal) {
        this.cashTotal = cashTotal;
    }

    public BigDecimal getRealCashTotal() {
        return realCashTotal;
    }

    public void setRealCashTotal(BigDecimal realCashTotal) {
        this.realCashTotal = realCashTotal;
    }

    public BigDecimal getDiffCashTotal() {
        return diffCashTotal;
    }

    public void setDiffCashTotal(BigDecimal diffCashTotal) {
        this.diffCashTotal = diffCashTotal;
    }

    public BigDecimal getBankAmountTotal() {
        return bankAmountTotal;
    }

    public void setBankAmountTotal(BigDecimal bankAmountTotal) {
        this.bankAmountTotal = bankAmountTotal;
    }

    public BigDecimal getOtherAmountTotal() {
        return otherAmountTotal;
    }

    public void setOtherAmountTotal(BigDecimal otherAmountTotal) {
        this.otherAmountTotal = otherAmountTotal;
    }

    public List<RetailPaymentVO> getRetailPaymentVOS() {
        return retailPaymentVOS;
    }

    public void setRetailPaymentVOS(List<RetailPaymentVO> retailPaymentVOS) {
        this.retailPaymentVOS = retailPaymentVOS;
    }
}
