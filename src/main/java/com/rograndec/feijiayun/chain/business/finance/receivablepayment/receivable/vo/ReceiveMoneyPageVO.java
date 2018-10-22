package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 收款VO对象
 */
public class ReceiveMoneyPageVO implements Serializable{
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private Date receivableDate;
    /**
     * 编码
     */
    @ApiModelProperty(value = "单号")
    private String code;

    /**
     * 付款单位类型
     */
    @ApiModelProperty(value = "付款单位类型 0-供货单位；1-购货单位")
    private Integer companyType;

    /**
     * 付款单位编码
     */
    @ApiModelProperty(value = "付款单位编码")
    private String companyCode;

    /**
     * 付款单位名称
     */
    @ApiModelProperty(value = "付款单位名称")
    private String companyName;

    /**
     * 收款人员
     */
    @ApiModelProperty(value = "收款人员")
    private String receivableManName;

    /**
     * 应收金额
     */
    @ApiModelProperty(value = "应收金额")
    private BigDecimal receivableAmountTotal;

    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discountAmountTotal;

    /**
     * 实收金额
     */
    @ApiModelProperty(value = "实收金额")
    private BigDecimal realAmountTotal;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态0-待收款；1-已完成；2-已冲销")
    private Integer status;

    public Date getReceivableDate() {
        return receivableDate;
    }

    public void setReceivableDate(Date receivableDate) {
        this.receivableDate = receivableDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getReceivableManName() {
        return receivableManName;
    }

    public void setReceivableManName(String receivableManName) {
        this.receivableManName = receivableManName;
    }

    public BigDecimal getReceivableAmountTotal() {
        return receivableAmountTotal;
    }

    public void setReceivableAmountTotal(BigDecimal receivableAmountTotal) {
        this.receivableAmountTotal = receivableAmountTotal;
    }

    public BigDecimal getDiscountAmountTotal() {
        return discountAmountTotal;
    }

    public void setDiscountAmountTotal(BigDecimal discountAmountTotal) {
        this.discountAmountTotal = discountAmountTotal;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
