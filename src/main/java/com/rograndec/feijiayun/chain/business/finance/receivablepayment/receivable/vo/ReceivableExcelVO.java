package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReceivableExcelVO implements Serializable{
    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private String baseOrderDate;


    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String baseOrderCode;

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
     * 已清金额合计
     */
    @ApiModelProperty(value = "已清金额")
    private BigDecimal clearAmount;

    /**
     * 未清金额合计
     */
    @ApiModelProperty(value = "未清金额")
    private BigDecimal unclearAmount;

    /**
     * 本次应收金额
     */
    @ApiModelProperty(value = "本次收款金额")
    private BigDecimal receivableAmount;

    /**
     * 本次优惠金额
     */
    @ApiModelProperty(value = "本次优惠金额")
    private BigDecimal discountAmount;

    /**
     * 本次实收金额
     */
    @ApiModelProperty(value = "本次实收金额")
    private BigDecimal realAmount;

    /**
     * 未清余额
     */
    @ApiModelProperty(value = "未清余额")
    private BigDecimal unclearBalance;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public String getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(String baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
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

    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getUnclearBalance() {
        return unclearBalance;
    }

    public void setUnclearBalance(BigDecimal unclearBalance) {
        this.unclearBalance = unclearBalance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
