package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * 
 * saas_prepay_invoice
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
@ApiModel
public class PaymentInvoiceResponseTotalVO implements Serializable {

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal = BigDecimal.ZERO;
    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxAmountTotal = BigDecimal.ZERO;
    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "列表数据集合")
    private List<PaymentInvoiceResponseVO> paymentInvoices = Collections.emptyList();


    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public List<PaymentInvoiceResponseVO> getPaymentInvoices() {
        return paymentInvoices;
    }

    public void setPaymentInvoices(List<PaymentInvoiceResponseVO> paymentInvoices) {
        this.paymentInvoices = paymentInvoices;
    }
}