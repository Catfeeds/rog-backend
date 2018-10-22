package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class PerpayInvoiceAutoAccountParamVO {

    @ApiModelProperty(value = "单个对账时传递预付发票明细行id")
    private Long id;

    @ApiModelProperty(value = "开票数量")
    private BigDecimal quantity = BigDecimal.ZERO; // 开票数量
    @ApiModelProperty(value = "已对账数量")
    private BigDecimal clearQuantity =  BigDecimal.ZERO; // 已对账数量
    @ApiModelProperty(value = "未对账数量")
    private BigDecimal unclearQuantity =  BigDecimal.ZERO; // 未对账数量
    @ApiModelProperty(value = "本次对账数量")
    private BigDecimal timeCheckAccountQty =  BigDecimal.ZERO; // 本次对账数量 会变化
    @ApiModelProperty(value = "本次对账金额")
    private BigDecimal timeCheckAccountAmount =  BigDecimal.ZERO ;// 本次对账金额
    @ApiModelProperty(value = "本次对账不含税金额")
    private BigDecimal timeCheckAccountNotaxAmount =  BigDecimal.ZERO; // 本次对账不含税金额
    @ApiModelProperty(value = "本次对账税额")
    private BigDecimal timeCheckAccountTaxAmount =  BigDecimal.ZERO ;// 本次对账税额
    @ApiModelProperty(value = "金额差额")
    private BigDecimal diffAmount =  BigDecimal.ZERO; // 金额差额
    @ApiModelProperty(value = "不含税金额差额")
    private BigDecimal diffNotaxAmount =  BigDecimal.ZERO; // 不含税金额差额
    @ApiModelProperty(value = "税额差额")
    private BigDecimal diffTaxAmount =  BigDecimal.ZERO; // 税额差额

    @ApiModelProperty(value = "业务订单货位行 , 单个对账需要")
    private List<PerpayInvoiceAutoAccountDetailParamVO>  perpayInvoiceAccountDetailParamVOS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PerpayInvoiceAutoAccountDetailParamVO> getPerpayInvoiceAccountDetailParamVOS() {
        return perpayInvoiceAccountDetailParamVOS;
    }

    public void setPerpayInvoiceAccountDetailParamVOS(List<PerpayInvoiceAutoAccountDetailParamVO> perpayInvoiceAccountDetailParamVOS) {
        this.perpayInvoiceAccountDetailParamVOS = perpayInvoiceAccountDetailParamVOS;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getClearQuantity() {
        return clearQuantity;
    }

    public void setClearQuantity(BigDecimal clearQuantity) {
        this.clearQuantity = clearQuantity;
    }

    public BigDecimal getUnclearQuantity() {
        return unclearQuantity;
    }

    public void setUnclearQuantity(BigDecimal unclearQuantity) {
        this.unclearQuantity = unclearQuantity;
    }

    public BigDecimal getTimeCheckAccountQty() {
        return timeCheckAccountQty;
    }

    public void setTimeCheckAccountQty(BigDecimal timeCheckAccountQty) {
        this.timeCheckAccountQty = timeCheckAccountQty;
    }

    public BigDecimal getTimeCheckAccountAmount() {
        return timeCheckAccountAmount;
    }

    public void setTimeCheckAccountAmount(BigDecimal timeCheckAccountAmount) {
        this.timeCheckAccountAmount = timeCheckAccountAmount;
    }

    public BigDecimal getTimeCheckAccountNotaxAmount() {
        return timeCheckAccountNotaxAmount;
    }

    public void setTimeCheckAccountNotaxAmount(BigDecimal timeCheckAccountNotaxAmount) {
        this.timeCheckAccountNotaxAmount = timeCheckAccountNotaxAmount;
    }

    public BigDecimal getTimeCheckAccountTaxAmount() {
        return timeCheckAccountTaxAmount;
    }

    public void setTimeCheckAccountTaxAmount(BigDecimal timeCheckAccountTaxAmount) {
        this.timeCheckAccountTaxAmount = timeCheckAccountTaxAmount;
    }

    public BigDecimal getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(BigDecimal diffAmount) {
        this.diffAmount = diffAmount;
    }

    public BigDecimal getDiffNotaxAmount() {
        return diffNotaxAmount;
    }

    public void setDiffNotaxAmount(BigDecimal diffNotaxAmount) {
        this.diffNotaxAmount = diffNotaxAmount;
    }

    public BigDecimal getDiffTaxAmount() {
        return diffTaxAmount;
    }

    public void setDiffTaxAmount(BigDecimal diffTaxAmount) {
        this.diffTaxAmount = diffTaxAmount;
    }
}
