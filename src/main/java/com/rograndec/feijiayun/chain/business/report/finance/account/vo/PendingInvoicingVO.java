package com.rograndec.feijiayun.chain.business.report.finance.account.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangyu
 * @create 2018-01-11
 */
@ApiModel(value = "PendingInvoicingVO", description = "财务管理-应付/应收账款-应付/应收待开票单据显示")
public class PendingInvoicingVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "供货(购货)单位编码")
    private String invoiceCode;
    @ApiModelProperty(value = "供货(购货)单位名称")
    private String invoiceName;
    @ApiModelProperty(value = "单据日期")
    private String orderDate;
    @ApiModelProperty(value = "单据编号")
    private String orderCode;
    @ApiModelProperty(value = "单据类型")
    private String orderType;
    @ApiModelProperty(value = "单据金额")
    private BigDecimal orderAmount;
    @ApiModelProperty(value = "已清金额")
    private BigDecimal clearAmount;
    @ApiModelProperty(value = "未清金额")
    private BigDecimal unClearAmount;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getClearAmount() {
        return clearAmount;
    }

    public void setClearAmount(BigDecimal clearAmount) {
        this.clearAmount = clearAmount;
    }

    public BigDecimal getUnClearAmount() {
        return unClearAmount;
    }

    public void setUnClearAmount(BigDecimal unClearAmount) {
        this.unClearAmount = unClearAmount;
    }
}
