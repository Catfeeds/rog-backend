package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * saas_payment_invoice_detail
 * 
 * 
 * @author zhaiwei
 * 
 * 2018-01-10
 */
@ApiModel
public class PaymentInvoiceDetailSaveVO implements Serializable {


    /**
     * 单据ID
     */
    @ApiModelProperty(value = "单据ID")
    private Long inId;


    /**
     * 单据商品明细ID
     */
    @ApiModelProperty(value = "单据商品明细ID")
    private Long dtlId;

    /**
     * 单据货位明细ID
     */
    @ApiModelProperty(value = "单据货位明细ID")
    private Long shelfDtlId;


    /**
     * 数量
     */
    @ApiModelProperty(value = "对账数量")
    private BigDecimal accountQuantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;


    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public static List<Long> getTaxRateIds(List<PaymentInvoiceDetailSaveVO> paymentInvoiceDetailSaveVOS){

        List<Long> collect = paymentInvoiceDetailSaveVOS.stream().map(PaymentInvoiceDetailSaveVO::getTaxRateId).collect(Collectors.toList());

        Set<Long> sets = collect.stream().collect(Collectors.toSet());

        return sets.stream().collect(Collectors.toList());
    }

    public static List<Long> getBaseShelfDtlIds(List<PaymentInvoiceDetailSaveVO> paymentInvoiceDetailSaveVOS){

        List<Long> collect = paymentInvoiceDetailSaveVOS.stream().map(PaymentInvoiceDetailSaveVO::getShelfDtlId).collect(Collectors.toList());

        Set<Long> sets = collect.stream().collect(Collectors.toSet());

        return sets.stream().collect(Collectors.toList());
    }

    public static List<Long> getBaseDtlIds(List<PaymentInvoiceDetailSaveVO> paymentInvoiceDetailSaveVOS){

        List<Long> collect = paymentInvoiceDetailSaveVOS.stream().map(PaymentInvoiceDetailSaveVO::getDtlId).collect(Collectors.toList());

        Set<Long> sets = collect.stream().collect(Collectors.toSet());

        return sets.stream().collect(Collectors.toList());
    }

    public static List<Long> getBaseIds(List<PaymentInvoiceDetailSaveVO> paymentInvoiceDetailSaveVOS){

        List<Long> collect = paymentInvoiceDetailSaveVOS.stream().map(PaymentInvoiceDetailSaveVO::getInId).collect(Collectors.toList());

        Set<Long> sets = collect.stream().collect(Collectors.toSet());

        return sets.stream().collect(Collectors.toList());
    }


    public Long getInId() {
        return inId;
    }

    public void setInId(Long inId) {
        this.inId = inId;
    }

    public Long getDtlId() {
        return dtlId;
    }

    public void setDtlId(Long dtlId) {
        this.dtlId = dtlId;
    }

    public Long getShelfDtlId() {
        return shelfDtlId;
    }

    public void setShelfDtlId(Long shelfDtlId) {
        this.shelfDtlId = shelfDtlId;
    }


    public BigDecimal getAccountQuantity() {
        return accountQuantity;
    }

    public void setAccountQuantity(BigDecimal accountQuantity) {
        this.accountQuantity = accountQuantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}