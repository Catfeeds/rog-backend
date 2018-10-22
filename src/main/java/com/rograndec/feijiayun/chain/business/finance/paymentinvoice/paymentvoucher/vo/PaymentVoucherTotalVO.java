package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * saas_payment_voucher
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class PaymentVoucherTotalVO implements Serializable {

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;


    private List<PaymentVoucherListVO> paymentVoucherListVOList;

    public BigDecimal getAmountTotal() {
        if(amountTotal == null) return BigDecimal.ZERO;
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getNotaxAmountTotal() {
        if(notaxAmountTotal == null) return BigDecimal.ZERO;
        return notaxAmountTotal;
    }

    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        if(taxAmountTotal == null) return BigDecimal.ZERO;
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public List<PaymentVoucherListVO> getPaymentVoucherListVOList() {
        return paymentVoucherListVOList;
    }

    public void setPaymentVoucherListVOList(List<PaymentVoucherListVO> paymentVoucherListVOList) {
        this.paymentVoucherListVOList = paymentVoucherListVOList;
    }
}