package com.rograndec.feijiayun.chain.business.retail.dailysettle.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_sale_daily_settle
 * 
 * 
 * @author Asze
 * 
 * 2017-09-19
 */
public class DailySettleVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 日结日期
     */
    @ApiModelProperty(value = "日结日期")
    private Date settleDate;

    /**
     * 日结单号
     */
    @ApiModelProperty(value = "日结单号")
    private String code;

    /**
     * 基础单据（交班单）ID
     */
    @ApiModelProperty(value = "基础单据（交班单）ID")
    private Long baseOrderId;

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
     * 日结人员名称
     */
    @ApiModelProperty(value = "日结人员名称")
    private String settleManName;

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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * saas_sale_daily_settle
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
     * 日结日期
     * @return settle_date 日结日期
     */
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 日结日期
     * @param settleDate 日结日期
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 日结单号
     * @return code 日结单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 日结单号
     * @param code 日结单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
     * 日结人员ID
     * @return settle_man_id 日结人员ID
     */
    public Long getSettleManId() {
        return settleManId;
    }

    /**
     * 日结人员ID
     * @param settleManId 日结人员ID
     */
    public void setSettleManId(Long settleManId) {
        this.settleManId = settleManId;
    }

    /**
     * 日结人员编码
     * @return settle_man_code 日结人员编码
     */
    public String getSettleManCode() {
        return settleManCode;
    }

    /**
     * 日结人员编码
     * @param settleManCode 日结人员编码
     */
    public void setSettleManCode(String settleManCode) {
        this.settleManCode = settleManCode == null ? null : settleManCode.trim();
    }

    /**
     * 日结人员名称
     * @return settle_man_name 日结人员名称
     */
    public String getSettleManName() {
        return settleManName;
    }

    /**
     * 日结人员名称
     * @param settleManName 日结人员名称
     */
    public void setSettleManName(String settleManName) {
        this.settleManName = settleManName == null ? null : settleManName.trim();
    }

    /**
     * 金额合计
     * @return amount_total 金额合计
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计
     * @param amountTotal 金额合计
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxAmountTotal 不含税金额合计
     */
    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    /**
     * 税额合计
     * @return tax_amount_total 税额合计
     */
    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    /**
     * 税额合计
     * @param taxAmountTotal 税额合计
     */
    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
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
        sb.append(", settleDate=").append(settleDate);
        sb.append(", code=").append(code);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", settleManId=").append(settleManId);
        sb.append(", settleManCode=").append(settleManCode);
        sb.append(", settleManName=").append(settleManName);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", notaxAmountTotal=").append(notaxAmountTotal);
        sb.append(", taxAmountTotal=").append(taxAmountTotal);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}