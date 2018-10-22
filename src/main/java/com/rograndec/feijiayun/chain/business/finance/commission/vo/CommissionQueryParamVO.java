package com.rograndec.feijiayun.chain.business.finance.commission.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

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
public class CommissionQueryParamVO implements Serializable {
    /**
     * 提成单号
     */
    @ApiModelProperty(value = "提成单号")
    private String code;
    /**
     * 提成人员名称
     */
    @ApiModelProperty(value = "提成人员名称")
    private String commissionManName;
    /**
     * 营业人员名称
     */
    @ApiModelProperty(value = "营业人员名称")
    private String clerkName;

    /**
     * 状态（1-已完成；2-已冲销）
     */
    @ApiModelProperty(value = "状态（1-已完成；2-已冲销）")
    private Integer status;

    /**
     * 门店编码
     */
    @ApiModelProperty(value = "门店编码")
    private String branchCode;

    /**
     * 门店名称
     */
    @ApiModelProperty(value = "门店名称")
    private String branchName;

    @ApiModelProperty(value = "按某一列排序")
    private String orderName;
    @ApiModelProperty(value = "排序方式（升序：asc,降序：desc）")
    private String orderType;
    @ApiModelProperty(value = "开始时间", required = false)
    private Date  startDate;
    @ApiModelProperty(value = "结束时间", required = false)
    private Date endDate;

    public CommissionQueryParamVO() {
    }

    public CommissionQueryParamVO(String code, String commissionManName, String clerkName, Integer status, String branchCode, String branchName, String orderName, String orderType, Date startDate, Date endDate) {
        this.code = code;
        this.commissionManName = commissionManName;
        this.clerkName = clerkName;
        this.status = status;
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.orderName = orderName;
        this.orderType = orderType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCommissionManName() {
        return commissionManName;
    }

    public void setCommissionManName(String commissionManName) {
        this.commissionManName = commissionManName;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}