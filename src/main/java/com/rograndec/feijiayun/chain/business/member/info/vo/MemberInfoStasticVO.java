package com.rograndec.feijiayun.chain.business.member.info.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MemberInfoStasticVO implements Serializable{

    private BigDecimal stasticTotalIntegral;

    private BigDecimal stasticCurrentIntegral;

    private BigDecimal stasticTotalStoredAmount;

    private BigDecimal stasticCurrentStoredAmount;

    public BigDecimal getStasticTotalIntegral() {
        return stasticTotalIntegral;
    }

    public void setStasticTotalIntegral(BigDecimal stasticTotalIntegral) {
        this.stasticTotalIntegral = stasticTotalIntegral;
    }

    public BigDecimal getStasticCurrentIntegral() {
        return stasticCurrentIntegral;
    }

    public void setStasticCurrentIntegral(BigDecimal stasticCurrentIntegral) {
        this.stasticCurrentIntegral = stasticCurrentIntegral;
    }

    public BigDecimal getStasticTotalStoredAmount() {
        return stasticTotalStoredAmount;
    }

    public void setStasticTotalStoredAmount(BigDecimal stasticTotalStoredAmount) {
        this.stasticTotalStoredAmount = stasticTotalStoredAmount;
    }

    public BigDecimal getStasticCurrentStoredAmount() {
        return stasticCurrentStoredAmount;
    }

    public void setStasticCurrentStoredAmount(BigDecimal stasticCurrentStoredAmount) {
        this.stasticCurrentStoredAmount = stasticCurrentStoredAmount;
    }
}
