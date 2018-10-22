package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

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
public class PrepayInvoiceQueryParamVO implements Serializable {
    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码")
    private String supplierCode;
    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称")
    private String supplierName;
    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 开票人员名称
     */
    @ApiModelProperty(value = "开票人员名称")
    private String billManName;

    /**
     * 状态（0-待付款；1-部分付款；2-已付款；3-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待付款；1-部分付款；2-已付款；3-已冲销）")
    private Integer status;

    /**
     * 对账状态（0-待对账；1-部分对账；2-已对账；3-已冲销）
     */
    @ApiModelProperty(value = "对账状态（0-待对账；1-部分对账；2-已对账；3-已冲销）")
    private Integer accountStatus;

    @ApiModelProperty(value = "按某一列排序")
    private String orderName;
    @ApiModelProperty(value = "排序方式（升序：asc,降序：desc）")
    private String orderType;
    @ApiModelProperty(value = "开始时间", required = false)
    private String  startDate;
    @ApiModelProperty(value = "结束时间", required = false)
    private String endDate;

    public PrepayInvoiceQueryParamVO() {
    }

    public PrepayInvoiceQueryParamVO(String supplierCode, String supplierName, String code, String billManName, Integer status, Integer accountStatus, String orderName, String orderType, String startDate, String endDate) {
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.code = code;
        this.billManName = billManName;
        this.status = status;
        this.accountStatus = accountStatus;
        this.orderName = orderName;
        this.orderType = orderType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBillManName() {
        return billManName;
    }

    public void setBillManName(String billManName) {
        this.billManName = billManName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}