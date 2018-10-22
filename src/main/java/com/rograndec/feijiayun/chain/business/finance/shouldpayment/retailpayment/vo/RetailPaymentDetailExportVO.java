package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_retail_payment_item
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class RetailPaymentDetailExportVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 缴款单ID
     */
    @ApiModelProperty(value = "缴款单ID")
    private Long docId;

    /**
     * 日结单据ID
     */
    @ApiModelProperty(value = "日结单据ID")
    private Long settleId;

    /**
     * 日结单编码
     */
    @ApiModelProperty(value = "日结单编码")
    private String settleCode;

    /**
     * 日结单类型
     */
    @ApiModelProperty(value = "日结单类型")
    private Integer settleOrderType;

    /**
     * 日结日期
     */
    @ApiModelProperty(value = "日结日期")
    private Date settleDate;

    /**
     * 日结人员ID
     */
    @ApiModelProperty(value = "日结人员ID")
    private Long settleManId;

    /**
     * 日结人员编码
     */
    @ApiModelProperty(value = "日结人员编码")
    private String settleManCode;

    /**
     * 日结人员姓名
     */
    @ApiModelProperty(value = "日结人员姓名")
    private String settleManName;

    /**
     * 交班人员ID
     */
    @ApiModelProperty(value = "交班人员ID")
    private Long shiftManId;

    /**
     * 交班人编码
     */
    @ApiModelProperty(value = "交班人编码")
    private String shiftManCode;

    /**
     * 交班人名称
     */
    @ApiModelProperty(value = "交班人名称")
    private String shiftManName;

    /**
     * 应收现金
     */
    @ApiModelProperty(value = "应收现金")
    private BigDecimal payableCash;

    /**
     * 实收现金
     */
    @ApiModelProperty(value = "实收现金")
    private BigDecimal cash;

    /**
     * 现金差异
     */
    @ApiModelProperty(value = "现金差异")
    private BigDecimal diffCash;

    /**
     * 应收金额
     */
    @ApiModelProperty(value = "应收金额")
    private BigDecimal amount;

    /**
     * 实收金额
     */
    @ApiModelProperty(value = "实收金额")
    private BigDecimal realAmount;

    /**
     * 差异金额
     */
    @ApiModelProperty(value = "差异金额")
    private BigDecimal diffAmount;

    /**
     * 缴款方式
     */
    @ApiModelProperty(value = "缴款方式")
    List<RetailPaymentPaydtlVO> retailPaymentPaydtlVOS;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * saas_retail_payment_item
     */
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Long getSettleId() {
        return settleId;
    }

    public void setSettleId(Long settleId) {
        this.settleId = settleId;
    }

    public String getSettleCode() {
        return settleCode;
    }

    public void setSettleCode(String settleCode) {
        this.settleCode = settleCode;
    }

    public Integer getSettleOrderType() {
        return settleOrderType;
    }

    public void setSettleOrderType(Integer settleOrderType) {
        this.settleOrderType = settleOrderType;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public Long getShiftManId() {
        return shiftManId;
    }

    public void setShiftManId(Long shiftManId) {
        this.shiftManId = shiftManId;
    }

    public String getShiftManCode() {
        return shiftManCode;
    }

    public void setShiftManCode(String shiftManCode) {
        this.shiftManCode = shiftManCode;
    }

    public String getShiftManName() {
        return shiftManName;
    }

    public void setShiftManName(String shiftManName) {
        this.shiftManName = shiftManName;
    }

    public BigDecimal getPayableCash() {
        return payableCash;
    }

    public void setPayableCash(BigDecimal payableCash) {
        this.payableCash = payableCash;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getDiffCash() {
        return diffCash;
    }

    public void setDiffCash(BigDecimal diffCash) {
        this.diffCash = diffCash;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(BigDecimal diffAmount) {
        this.diffAmount = diffAmount;
    }

    public List<RetailPaymentPaydtlVO> getRetailPaymentPaydtlVOS() {
        return retailPaymentPaydtlVOS;
    }

    public void setRetailPaymentPaydtlVOS(List<RetailPaymentPaydtlVO> retailPaymentPaydtlVOS) {
        this.retailPaymentPaydtlVOS = retailPaymentPaydtlVOS;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getSettleManId() {
        return settleManId;
    }

    public void setSettleManId(Long settleManId) {
        this.settleManId = settleManId;
    }

    public String getSettleManCode() {
        return settleManCode;
    }

    public void setSettleManCode(String settleManCode) {
        this.settleManCode = settleManCode;
    }

    public String getSettleManName() {
        return settleManName;
    }

    public void setSettleManName(String settleManName) {
        this.settleManName = settleManName;
    }

    @Override
    public String toString() {
        return "RetailPaymentDetailExportVO{" +
                "id=" + id +
                ", docId=" + docId +
                ", settleId=" + settleId +
                ", settleCode='" + settleCode + '\'' +
                ", settleOrderType=" + settleOrderType +
                ", settleDate=" + settleDate +
                ", settleManId=" + settleManId +
                ", settleManCode='" + settleManCode + '\'' +
                ", settleManName='" + settleManName + '\'' +
                ", shiftManId=" + shiftManId +
                ", shiftManCode='" + shiftManCode + '\'' +
                ", shiftManName='" + shiftManName + '\'' +
                ", payableCash=" + payableCash +
                ", cash=" + cash +
                ", diffCash=" + diffCash +
                ", amount=" + amount +
                ", realAmount=" + realAmount +
                ", diffAmount=" + diffAmount +
                ", retailPaymentPaydtlVOS=" + retailPaymentPaydtlVOS +
                ", remark='" + remark + '\'' +
                '}';
    }
}