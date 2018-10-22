package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel
public class PerpayInvoiceAutoAccountDetailParamVO {


    @ApiModelProperty(value = "业务订单货位行id , 单个对账需要,自动对账不需要传递")
    private Long orderShelfId;

    @ApiModelProperty(value = "对账数量 , 单个对账需要,自动对账不需要传递")
    private BigDecimal accountQuantity  = BigDecimal.ZERO;

    @ApiModelProperty(value = "行号 , 单个对账需要,自动对账不需要传递")
    private Integer lineNum;

    @ApiModelProperty(value = "本次对账金额")
    private BigDecimal accountAmount = BigDecimal.ZERO; // 本次对账金额
    @ApiModelProperty(value = "本次对账不含税金额")
    private BigDecimal accountNotaxAmount = BigDecimal.ZERO; // 本次对账不含税金额
    @ApiModelProperty(value = "本次对账税额")
    private BigDecimal accountTaxAmount = BigDecimal.ZERO ;// 本次对账税额

    public Long getOrderShelfId() {
        return orderShelfId;
    }

    public void setOrderShelfId(Long orderShelfId) {
        this.orderShelfId = orderShelfId;
    }

    public BigDecimal getAccountQuantity() {
        return accountQuantity;
    }

    public void setAccountQuantity(BigDecimal accountQuantity) {
        this.accountQuantity = accountQuantity;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    public BigDecimal getAccountNotaxAmount() {
        return accountNotaxAmount;
    }

    public void setAccountNotaxAmount(BigDecimal accountNotaxAmount) {
        this.accountNotaxAmount = accountNotaxAmount;
    }

    public BigDecimal getAccountTaxAmount() {
        return accountTaxAmount;
    }

    public void setAccountTaxAmount(BigDecimal accountTaxAmount) {
        this.accountTaxAmount = accountTaxAmount;
    }
}
