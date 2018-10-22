package com.rograndec.feijiayun.chain.business.report.retail.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class QuerySaleYVO implements Serializable {

    @ApiModelProperty(value = "X轴")
    private String Xname;

    @ApiModelProperty(value = "数值")
    private BigDecimal data;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    public String getXname() {
        return Xname;
    }

    public void setXname(String xname) {
        Xname = xname;
    }

    public BigDecimal getData() {
        return data;
    }

    public void setData(BigDecimal data) {
        this.data = data;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "QuerySaleYVO[" +
                "Xname='" + Xname + '\'' +
                ", data=" + data +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ']';
    }
}
