package com.rograndec.feijiayun.chain.business.storage.move.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class OtherInStasticVO implements Serializable{

    private BigDecimal stasticAmountTotal;

    private BigDecimal stasticNotaxAmountTotal;

    private BigDecimal stasticTaxAmountTotal;

    public BigDecimal getStasticAmountTotal() {
        return stasticAmountTotal;
    }

    public void setStasticAmountTotal(BigDecimal stasticAmountTotal) {
        this.stasticAmountTotal = stasticAmountTotal;
    }

    public BigDecimal getStasticNotaxAmountTotal() {
        return stasticNotaxAmountTotal;
    }

    public void setStasticNotaxAmountTotal(BigDecimal stasticNotaxAmountTotal) {
        this.stasticNotaxAmountTotal = stasticNotaxAmountTotal;
    }

    public BigDecimal getStasticTaxAmountTotal() {
        return stasticTaxAmountTotal;
    }

    public void setStasticTaxAmountTotal(BigDecimal stasticTaxAmountTotal) {
        this.stasticTaxAmountTotal = stasticTaxAmountTotal;
    }
}
