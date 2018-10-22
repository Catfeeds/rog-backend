package com.rograndec.feijiayun.chain.business.common.vo;

public class OrderAccountingPeriodValidity extends AccountingPeriodValidity{

    private Long onlyBeginTime;

    private Long onlyEndTime;

    public Long getOnlyBeginTime() {
        return onlyBeginTime;
    }

    public void setOnlyBeginTime(Long onlyBeginTime) {
        this.onlyBeginTime = onlyBeginTime;
    }

    public Long getOnlyEndTime() {
        return onlyEndTime;
    }

    public void setOnlyEndTime(Long onlyEndTime) {
        this.onlyEndTime = onlyEndTime;
    }
}
