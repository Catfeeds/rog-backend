package com.rograndec.feijiayun.chain.business.common.vo;

import java.io.Serializable;

public class AccountingPeriodValidity implements Serializable {

    private Long beginTime;

    private Long endTime;

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
