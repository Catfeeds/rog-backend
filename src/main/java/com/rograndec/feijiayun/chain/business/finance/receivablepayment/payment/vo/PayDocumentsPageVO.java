package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayDocumentsPageVO implements Serializable{

    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private Date baseOrderDate;

    /**
     * 单据ID
     */
    @ApiModelProperty(value = "单据ID")
    private Long baseOrderId;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String baseOrderCode;

    /**
     * 单据类型
     */
    @ApiModelProperty(value = "单据类型")
    private Integer baseOrderType;

    /**
     * 单据类型名称
     */
    @ApiModelProperty(value = "单据类型名称")
    private String orderTypeName;

    /**
     * 单据金额
     */
    @ApiModelProperty(value = "单据金额")
    private BigDecimal amount;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID")
    private Long supplier;

    /**
     * 已清金额合计
     */
    @ApiModelProperty(value = "已清金额合计")
    private BigDecimal clearAmount;

    /**
     * 未清金额合计
     */
    @ApiModelProperty(value = "未清金额合计")
    private BigDecimal unclearAmount;

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getClearAmount() {
        return clearAmount;
    }

    public void setClearAmount(BigDecimal clearAmount) {
        this.clearAmount = clearAmount;
    }

    public BigDecimal getUnclearAmount() {
        return unclearAmount;
    }

    public void setUnclearAmount(BigDecimal unclearAmount) {
        this.unclearAmount = unclearAmount;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public Long getSupplier() {
        return supplier;
    }

    public void setSupplier(Long supplier) {
        this.supplier = supplier;
    }
}
