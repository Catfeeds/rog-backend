package com.rograndec.feijiayun.chain.business.retail.dailysettle.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_sale_daily_settle_detail
 * 
 * 
 * @author Asze
 * 
 * 2017-09-19
 */
public class DailySettleDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 基础单据交班单）明细ID
     */
    @ApiModelProperty(value = "基础单据交班单）明细ID")
    private Long baseOrderDtlId;

    /**
     * 基础单据（交班单）ID
     */
    @ApiModelProperty(value = "基础单据（交班单）ID")
    private Long baseOrderId;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * saas_sale_daily_settle_detail
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 基础单据交班单）明细ID
     * @return base_order_dtl_id 基础单据交班单）明细ID
     */
    public Long getBaseOrderDtlId() {
        return baseOrderDtlId;
    }

    /**
     * 基础单据交班单）明细ID
     * @param baseOrderDtlId 基础单据交班单）明细ID
     */
    public void setBaseOrderDtlId(Long baseOrderDtlId) {
        this.baseOrderDtlId = baseOrderDtlId;
    }

    /**
     * 基础单据（交班单）ID
     * @return base_order_id 基础单据（交班单）ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 基础单据（交班单）ID
     * @param baseOrderId 基础单据（交班单）ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 金额
     * @return amount 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 金额
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 不含税金额
     * @return notax_amount 不含税金额
     */
    public BigDecimal getNotaxAmount() {
        return notaxAmount;
    }

    /**
     * 不含税金额
     * @param notaxAmount 不含税金额
     */
    public void setNotaxAmount(BigDecimal notaxAmount) {
        this.notaxAmount = notaxAmount;
    }

    /**
     * 税额
     * @return tax_amount 税额
     */
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * 税额
     * @param taxAmount 税额
     */
    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", baseOrderDtlId=").append(baseOrderDtlId);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", amount=").append(amount);
        sb.append(", notaxAmount=").append(notaxAmount);
        sb.append(", taxAmount=").append(taxAmount);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailySettleDetailVO that = (DailySettleDetailVO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (baseOrderDtlId != null ? !baseOrderDtlId.equals(that.baseOrderDtlId) : that.baseOrderDtlId != null)
            return false;
        if (baseOrderId != null ? !baseOrderId.equals(that.baseOrderId) : that.baseOrderId != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (notaxAmount != null ? !notaxAmount.equals(that.notaxAmount) : that.notaxAmount != null) return false;
        if (taxAmount != null ? !taxAmount.equals(that.taxAmount) : that.taxAmount != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return remark != null ? remark.equals(that.remark) : that.remark == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (baseOrderDtlId != null ? baseOrderDtlId.hashCode() : 0);
        result = 31 * result + (baseOrderId != null ? baseOrderId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (notaxAmount != null ? notaxAmount.hashCode() : 0);
        result = 31 * result + (taxAmount != null ? taxAmount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}