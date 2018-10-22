package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReveivableInvoicePageVO implements Serializable {

    /**
     * 应收发票主键ID
     */
    @ApiModelProperty(value = "应收发票主键ID")
    private Long id;

    /**
     * 开票日期
     */
    @ApiModelProperty(value = "开票日期")
    private Date billDate;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 购货单位ID
     */
    @ApiModelProperty(value = "购货单位ID")
    private Long purchaseUnitId;

    /**
     * 购货单位编码
     */
    @ApiModelProperty(value = "购货单位编码")
    private String purchaseUnitCode;

    /**
     * 购货单位名称
     */
    @ApiModelProperty(value = "购货单位名称")
    private String purchaseUnitName;

    /**
     * 开票人员名称
     */
    @ApiModelProperty(value = "开票人员名称")
    private String billManName;

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待收款；1-部分收款；2-已收款；3-已冲销）")
    private Integer status;

    /**
     * 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待收款；1-部分收款；2-已收款；3-已冲销）")
    private String statusName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getPurchaseUnitId() {
        return purchaseUnitId;
    }

    public void setPurchaseUnitId(Long purchaseUnitId) {
        this.purchaseUnitId = purchaseUnitId;
    }

    public String getPurchaseUnitCode() {
        return purchaseUnitCode;
    }

    public void setPurchaseUnitCode(String purchaseUnitCode) {
        this.purchaseUnitCode = purchaseUnitCode;
    }

    public String getPurchaseUnitName() {
        return purchaseUnitName;
    }

    public void setPurchaseUnitName(String purchaseUnitName) {
        this.purchaseUnitName = purchaseUnitName;
    }

    public String getBillManName() {
        return billManName;
    }

    public void setBillManName(String billManName) {
        this.billManName = billManName;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "ReveivableInvoicePageVO[" +
                "id=" + id +
                ", billDate=" + billDate +
                ", code='" + code + '\'' +
                ", purchaseUnitId=" + purchaseUnitId +
                ", purchaseUnitCode='" + purchaseUnitCode + '\'' +
                ", purchaseUnitName='" + purchaseUnitName + '\'' +
                ", billManName='" + billManName + '\'' +
                ", amountTotal=" + amountTotal +
                ", notaxAmountTotal=" + notaxAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", remark='" + remark + '\'' +
                ']';
    }
}
