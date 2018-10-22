package com.rograndec.feijiayun.chain.business.system.opening.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 会计期间
 * 
 * 
 * @author rograndec
 * 
 * 2018-01-04
 */
public class AccountingPeriodVO implements Serializable {


    /**
     * 会计月度（1～12）
     */
    @ApiModelProperty(value = "会计月度（1～12）")
    private Integer month;

    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    private Date startDate;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期")
    private Date endDate;


    /**
     * 状态（0-已激活；1-已结账）
     */
    @ApiModelProperty(value = "状态（0-已激活；1-已结账）")
    private Integer status;


    /**
     * saas_accounting_period_detail
     */
    private static final long serialVersionUID = 1L;



    /**
     * 会计月度（1～12）
     * @return month 会计月度（1～12）
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * 会计月度（1～12）
     * @param month 会计月度（1～12）
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * 开始日期
     * @return start_date 开始日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 开始日期
     * @param startDate 开始日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 结束日期
     * @return end_date 结束日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 结束日期
     * @param endDate 结束日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }



    /**
     * 状态（0-已激活；1-已结账）
     * @return status 状态（0-已激活；1-已结账）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-已激活；1-已结账）
     * @param status 状态（0-已激活；1-已结账）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }



    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", month=").append(month);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}