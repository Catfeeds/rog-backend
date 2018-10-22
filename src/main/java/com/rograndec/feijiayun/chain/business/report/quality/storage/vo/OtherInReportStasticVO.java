package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class OtherInReportStasticVO implements Serializable{

    private BigDecimal quantity;

    private BigDecimal amount;

    private BigDecimal noTaxAmount;

    private BigDecimal taxAmount;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getNoTaxAmount() {
        return noTaxAmount;
    }

    public void setNoTaxAmount(BigDecimal noTaxAmount) {
        this.noTaxAmount = noTaxAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }
}
