package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_retail_payment
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class RetailPayment implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 单据类型（6242）
     */
    @ApiModelProperty(value = "单据类型（6242）")
    private Integer orderType;

    /**
     * 缴款日期
     */
    @ApiModelProperty(value = "缴款日期")
    private Date paymentDate;

    /**
     * 缴款单号
     */
    @ApiModelProperty(value = "缴款单号")
    private String code;

    /**
     * 缴款人员ID
     */
    @ApiModelProperty(value = "缴款人员ID")
    private Long paymentManId;

    /**
     * 缴款人员编码
     */
    @ApiModelProperty(value = "缴款人员编码")
    private String paymentManCode;

    /**
     * 缴款人员名称
     */
    @ApiModelProperty(value = "缴款人员名称")
    private String paymentManName;

    /**
     * 应缴现金合计
     */
    @ApiModelProperty(value = "应缴现金合计")
    private BigDecimal cashTotal;

    /**
     * 实缴现金合计
     */
    @ApiModelProperty(value = "实缴现金合计")
    private BigDecimal realCashTotal;

    /**
     * 现金差额合计
     */
    @ApiModelProperty(value = "现金差额合计")
    private BigDecimal diffCashTotal;

    /**
     * 应缴银行存款合计
     */
    @ApiModelProperty(value = "应缴银行存款合计")
    private BigDecimal bankAmountTotal;

    /**
     * 应缴其它货币资金合计
     */
    @ApiModelProperty(value = "应缴其它货币资金合计")
    private BigDecimal otherAmountTotal;

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
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * saas_retail_payment
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
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 单据类型（6242）
     * @return order_type 单据类型（6242）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（6242）
     * @param orderType 单据类型（6242）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 缴款日期
     * @return payment_date 缴款日期
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * 缴款日期
     * @param paymentDate 缴款日期
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * 缴款单号
     * @return code 缴款单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 缴款单号
     * @param code 缴款单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 缴款人员ID
     * @return payment_man_id 缴款人员ID
     */
    public Long getPaymentManId() {
        return paymentManId;
    }

    /**
     * 缴款人员ID
     * @param paymentManId 缴款人员ID
     */
    public void setPaymentManId(Long paymentManId) {
        this.paymentManId = paymentManId;
    }

    /**
     * 缴款人员编码
     * @return payment_man_code 缴款人员编码
     */
    public String getPaymentManCode() {
        return paymentManCode;
    }

    /**
     * 缴款人员编码
     * @param paymentManCode 缴款人员编码
     */
    public void setPaymentManCode(String paymentManCode) {
        this.paymentManCode = paymentManCode == null ? null : paymentManCode.trim();
    }

    /**
     * 缴款人员名称
     * @return payment_man_name 缴款人员名称
     */
    public String getPaymentManName() {
        return paymentManName;
    }

    /**
     * 缴款人员名称
     * @param paymentManName 缴款人员名称
     */
    public void setPaymentManName(String paymentManName) {
        this.paymentManName = paymentManName == null ? null : paymentManName.trim();
    }

    /**
     * 应缴现金合计
     * @return cash_total 应缴现金合计
     */
    public BigDecimal getCashTotal() {
        return cashTotal;
    }

    /**
     * 应缴现金合计
     * @param cashTotal 应缴现金合计
     */
    public void setCashTotal(BigDecimal cashTotal) {
        this.cashTotal = cashTotal;
    }

    /**
     * 实缴现金合计
     * @return real_cash_total 实缴现金合计
     */
    public BigDecimal getRealCashTotal() {
        return realCashTotal;
    }

    /**
     * 实缴现金合计
     * @param realCashTotal 实缴现金合计
     */
    public void setRealCashTotal(BigDecimal realCashTotal) {
        this.realCashTotal = realCashTotal;
    }

    /**
     * 现金差额合计
     * @return diff_cash_total 现金差额合计
     */
    public BigDecimal getDiffCashTotal() {
        return diffCashTotal;
    }

    /**
     * 现金差额合计
     * @param diffCashTotal 现金差额合计
     */
    public void setDiffCashTotal(BigDecimal diffCashTotal) {
        this.diffCashTotal = diffCashTotal;
    }

    /**
     * 应缴银行存款合计
     * @return bank_amount_total 应缴银行存款合计
     */
    public BigDecimal getBankAmountTotal() {
        return bankAmountTotal;
    }

    /**
     * 应缴银行存款合计
     * @param bankAmountTotal 应缴银行存款合计
     */
    public void setBankAmountTotal(BigDecimal bankAmountTotal) {
        this.bankAmountTotal = bankAmountTotal;
    }

    /**
     * 应缴其它货币资金合计
     * @return other_amount_total 应缴其它货币资金合计
     */
    public BigDecimal getOtherAmountTotal() {
        return otherAmountTotal;
    }

    /**
     * 应缴其它货币资金合计
     * @param otherAmountTotal 应缴其它货币资金合计
     */
    public void setOtherAmountTotal(BigDecimal otherAmountTotal) {
        this.otherAmountTotal = otherAmountTotal;
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
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", orderType=").append(orderType);
        sb.append(", paymentDate=").append(paymentDate);
        sb.append(", code=").append(code);
        sb.append(", paymentManId=").append(paymentManId);
        sb.append(", paymentManCode=").append(paymentManCode);
        sb.append(", paymentManName=").append(paymentManName);
        sb.append(", cashTotal=").append(cashTotal);
        sb.append(", realCashTotal=").append(realCashTotal);
        sb.append(", diffCashTotal=").append(diffCashTotal);
        sb.append(", bankAmountTotal=").append(bankAmountTotal);
        sb.append(", otherAmountTotal=").append(otherAmountTotal);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}