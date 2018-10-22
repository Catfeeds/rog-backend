package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class DistrReturnNoticeStasticVO implements Serializable{

    private BigDecimal stasticRealAmountTotal;

    private BigDecimal stasticNotaxRealAmountTotal;

    private BigDecimal stasticTaxAmountTotal;

    public BigDecimal getStasticRealAmountTotal() {
        return stasticRealAmountTotal;
    }

    public void setStasticRealAmountTotal(BigDecimal stasticRealAmountTotal) {
        this.stasticRealAmountTotal = stasticRealAmountTotal;
    }

    public BigDecimal getStasticNotaxRealAmountTotal() {
        return stasticNotaxRealAmountTotal;
    }

    public void setStasticNotaxRealAmountTotal(BigDecimal stasticNotaxRealAmountTotal) {
        this.stasticNotaxRealAmountTotal = stasticNotaxRealAmountTotal;
    }

    public BigDecimal getStasticTaxAmountTotal() {
        return stasticTaxAmountTotal;
    }

    public void setStasticTaxAmountTotal(BigDecimal stasticTaxAmountTotal) {
        this.stasticTaxAmountTotal = stasticTaxAmountTotal;
    }
}
