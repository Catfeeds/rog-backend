package com.rograndec.feijiayun.chain.business.finance.commission.model;

import java.math.BigDecimal;

public class SaleCommissionRange {

    private Long id;

    private Long setId;

    /**
     * 提成基数 开始
     */
    private BigDecimal start;

    private BigDecimal end;

    /**
     * 提成比例/金额
     */
    private BigDecimal ratio;

    public BigDecimal getStart() {
        return start;
    }

    public void setStart(BigDecimal start) {
        this.start = start;
    }

    public BigDecimal getEnd() {
        return end;
    }

    public void setEnd(BigDecimal end) {
        this.end = end;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSetId() {
        return setId;
    }

    public void setSetId(Long setId) {
        this.setId = setId;
    }
}
