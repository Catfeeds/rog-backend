package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReceiveDetailVO implements Serializable{

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
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

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

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    @Override
    public String toString() {
        return "ReceiveDetailVO{" +
                "baseOrderDate=" + baseOrderDate +
                ", baseOrderId=" + baseOrderId +
                ", baseOrderCode='" + baseOrderCode + '\'' +
                ", baseOrderType=" + baseOrderType +
                ", orderTypeName='" + orderTypeName + '\'' +
                ", amount=" + amount +
                ", clearAmount=" + clearAmount +
                ", unclearAmount=" + unclearAmount +
                ", receivableAmount=" + receivableAmount +
                ", discountAmount=" + discountAmount +
                ", realAmount=" + realAmount +
                ", unclearBalance=" + unclearBalance +
                ", lineNum=" + lineNum +
                ", remark='" + remark + '\'' +
                '}';
    }
}
