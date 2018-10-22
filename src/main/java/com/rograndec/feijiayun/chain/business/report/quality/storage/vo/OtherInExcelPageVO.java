package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OtherInExcelPageVO implements Serializable{

    /**
     * 数量
     */
    @ApiModelProperty(value="数量")
    private BigDecimal quantity;

    @ApiModelProperty(value="金额")
    private BigDecimal amount;

    @ApiModelProperty(value="不含税金额")
    private BigDecimal noTaxAmount;

    @ApiModelProperty(value="税额")
    private BigDecimal taxAmount;

    @ApiModelProperty(value="EXCEL数据结合")
    private List<OtherInExcelVO> list;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getNoTaxAmount() {
        return noTaxAmount;
    }

    public void setNoTaxAmount(BigDecimal noTaxAmount) {
        this.noTaxAmount = noTaxAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public List<OtherInExcelVO> getList() {
        return list;
    }

    public void setList(List<OtherInExcelVO> list) {
        this.list = list;
    }
}
