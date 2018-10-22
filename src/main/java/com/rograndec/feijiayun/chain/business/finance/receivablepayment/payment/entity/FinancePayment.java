package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_finance_payment
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-04
 */
public class FinancePayment implements Serializable {
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
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 单据类型（2501）
     */
    @ApiModelProperty(value = "单据类型（2501）")
    private Integer orderType;

    /**
     * 付款日期
     */
    @ApiModelProperty(value = "付款日期")
    private Date paymentDate;

    /**
     * 付款人员ID
     */
    @ApiModelProperty(value = "付款人员ID")
    private Long paymentManId;

    /**
     * 付款人员编码
     */
    @ApiModelProperty(value = "付款人员编码")
    private String paymentManCode;

    /**
     * 付款人员名称
     */
    @ApiModelProperty(value = "付款人员名称")
    private String paymentManName;

    /**
     * 收款单位类型（0-供货单位；1-购货单位）
     */
    @ApiModelProperty(value = "收款单位类型（0-供货单位；1-购货单位）")
    private Integer companyType;

    /**
     * 收款单位类型（0-供货单位；1-购货单位）
     */
    @ApiModelProperty(value = "收款单位类型（0-供货单位；1-购货单位）")
    private Integer financeAccountType;

    /**
     * 收款单位ID
     */
    @ApiModelProperty(value = "收款单位ID")
    private Long companyId;

    /**
     * 收款单位编码
     */
    @ApiModelProperty(value = "收款单位编码")
    private String companyCode;

    /**
     * 收款单位名称
     */
    @ApiModelProperty(value = "收款单位名称")
    private String companyName;

    /**
     * 单据金额合计
     */
    @ApiModelProperty(value = "单据金额合计")
    private BigDecimal amountTotal;

    /**
     * 已清金额合计
     */
    @ApiModelProperty(value = "已清金额合计")
    private BigDecimal clearAmountTotal;

    /**
     * 未清金额合计
     */
    @ApiModelProperty(value = "未清金额合计")
    private BigDecimal unclearAmountTotal;

    /**
     * 应付金额合计
     */
    @ApiModelProperty(value = "应付金额合计")
    private BigDecimal paymentAmountTotal;

    /**
     * 优惠金额合计
     */
    @ApiModelProperty(value = "优惠金额合计")
    private BigDecimal discountAmountTotal;

    /**
     * 实付金额合计
     */
    @ApiModelProperty(value = "实付金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 转账日期
     */
    @ApiModelProperty(value = "转账日期")
    private Date transferDate;

    /**
     * 转账银行ID
     */
    @ApiModelProperty(value = "转账银行ID")
    private Long bankId;

    /**
     * 转账银行名称
     */
    @ApiModelProperty(value = "转账银行名称")
    private String bankName;

    /**
     * 转账银行账号
     */
    @ApiModelProperty(value = "转账银行账号")
    private String bankAccount;

    /**
     * 银行支付金额
     */
    @ApiModelProperty(value = "银行支付金额")
    private BigDecimal bankAmountTotal;

    /**
     * 现金支付金额
     */
    @ApiModelProperty(value = "现金支付金额")
    private BigDecimal cashAmountTotal;

    /**
     * 未清余额合计
     */
    @ApiModelProperty(value = "未清余额合计")
    private BigDecimal unclearBalanceTotal;

    /**
     * 状态（0-待付款；1-已完成；2-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待付款；1-已完成；2-已冲销）")
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
     * saas_finance_payment
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
     * 单据编码
     * @return code 单据编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 单据编码
     * @param code 单据编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 单据类型（2501）
     * @return order_type 单据类型（2501）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（2501）
     * @param orderType 单据类型（2501）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 付款日期
     * @return payment_date 付款日期
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * 付款日期
     * @param paymentDate 付款日期
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * 付款人员ID
     * @return payment_man_id 付款人员ID
     */
    public Long getPaymentManId() {
        return paymentManId;
    }

    /**
     * 付款人员ID
     * @param paymentManId 付款人员ID
     */
    public void setPaymentManId(Long paymentManId) {
        this.paymentManId = paymentManId;
    }

    /**
     * 付款人员编码
     * @return payment_man_code 付款人员编码
     */
    public String getPaymentManCode() {
        return paymentManCode;
    }

    /**
     * 付款人员编码
     * @param paymentManCode 付款人员编码
     */
    public void setPaymentManCode(String paymentManCode) {
        this.paymentManCode = paymentManCode == null ? null : paymentManCode.trim();
    }

    /**
     * 付款人员名称
     * @return payment_man_name 付款人员名称
     */
    public String getPaymentManName() {
        return paymentManName;
    }

    /**
     * 付款人员名称
     * @param paymentManName 付款人员名称
     */
    public void setPaymentManName(String paymentManName) {
        this.paymentManName = paymentManName == null ? null : paymentManName.trim();
    }

    /**
     * 收款单位类型（0-供货单位；1-购货单位）
     * @return company_type 收款单位类型（0-供货单位；1-购货单位）
     */
    public Integer getCompanyType() {
        return companyType;
    }

    /**
     * 收款单位类型（0-供货单位；1-购货单位）
     * @param companyType 收款单位类型（0-供货单位；1-购货单位）
     */
    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    /**
     * 收款单位ID
     * @return company_id 收款单位ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 收款单位ID
     * @param companyId 收款单位ID
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 收款单位编码
     * @return company_code 收款单位编码
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * 收款单位编码
     * @param companyCode 收款单位编码
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    /**
     * 收款单位名称
     * @return company_name 收款单位名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 收款单位名称
     * @param companyName 收款单位名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 单据金额合计
     * @return amount_total 单据金额合计
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 单据金额合计
     * @param amountTotal 单据金额合计
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 已清金额合计
     * @return clear_amount_total 已清金额合计
     */
    public BigDecimal getClearAmountTotal() {
        return clearAmountTotal;
    }

    /**
     * 已清金额合计
     * @param clearAmountTotal 已清金额合计
     */
    public void setClearAmountTotal(BigDecimal clearAmountTotal) {
        this.clearAmountTotal = clearAmountTotal;
    }

    /**
     * 未清金额合计
     * @return unclear_amount_total 未清金额合计
     */
    public BigDecimal getUnclearAmountTotal() {
        return unclearAmountTotal;
    }

    /**
     * 未清金额合计
     * @param unclearAmountTotal 未清金额合计
     */
    public void setUnclearAmountTotal(BigDecimal unclearAmountTotal) {
        this.unclearAmountTotal = unclearAmountTotal;
    }

    /**
     * 应付金额合计
     * @return payment_amount_total 应付金额合计
     */
    public BigDecimal getPaymentAmountTotal() {
        return paymentAmountTotal;
    }

    /**
     * 应付金额合计
     * @param paymentAmountTotal 应付金额合计
     */
    public void setPaymentAmountTotal(BigDecimal paymentAmountTotal) {
        this.paymentAmountTotal = paymentAmountTotal;
    }

    /**
     * 优惠金额合计
     * @return discount_amount_total 优惠金额合计
     */
    public BigDecimal getDiscountAmountTotal() {
        return discountAmountTotal;
    }

    /**
     * 优惠金额合计
     * @param discountAmountTotal 优惠金额合计
     */
    public void setDiscountAmountTotal(BigDecimal discountAmountTotal) {
        this.discountAmountTotal = discountAmountTotal;
    }

    /**
     * 实付金额合计
     * @return real_amount_total 实付金额合计
     */
    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    /**
     * 实付金额合计
     * @param realAmountTotal 实付金额合计
     */
    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    /**
     * 转账日期
     * @return transfer_date 转账日期
     */
    public Date getTransferDate() {
        return transferDate;
    }

    /**
     * 转账日期
     * @param transferDate 转账日期
     */
    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    /**
     * 转账银行ID
     * @return bank_id 转账银行ID
     */
    public Long getBankId() {
        return bankId;
    }

    /**
     * 转账银行ID
     * @param bankId 转账银行ID
     */
    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    /**
     * 转账银行名称
     * @return bank_name 转账银行名称
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 转账银行名称
     * @param bankName 转账银行名称
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     * 转账银行账号
     * @return bank_account 转账银行账号
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * 转账银行账号
     * @param bankAccount 转账银行账号
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    /**
     * 银行支付金额
     * @return bank_amount_total 银行支付金额
     */
    public BigDecimal getBankAmountTotal() {
        return bankAmountTotal;
    }

    /**
     * 银行支付金额
     * @param bankAmountTotal 银行支付金额
     */
    public void setBankAmountTotal(BigDecimal bankAmountTotal) {
        this.bankAmountTotal = bankAmountTotal;
    }

    /**
     * 现金支付金额
     * @return cash_amount_total 现金支付金额
     */
    public BigDecimal getCashAmountTotal() {
        return cashAmountTotal;
    }

    /**
     * 现金支付金额
     * @param cashAmountTotal 现金支付金额
     */
    public void setCashAmountTotal(BigDecimal cashAmountTotal) {
        this.cashAmountTotal = cashAmountTotal;
    }

    /**
     * 未清余额合计
     * @return unclear_balance_total 未清余额合计
     */
    public BigDecimal getUnclearBalanceTotal() {
        return unclearBalanceTotal;
    }

    /**
     * 未清余额合计
     * @param unclearBalanceTotal 未清余额合计
     */
    public void setUnclearBalanceTotal(BigDecimal unclearBalanceTotal) {
        this.unclearBalanceTotal = unclearBalanceTotal;
    }

    /**
     * 状态（0-待付款；1-已完成；2-已冲销）
     * @return status 状态（0-待付款；1-已完成；2-已冲销）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-待付款；1-已完成；2-已冲销）
     * @param status 状态（0-待付款；1-已完成；2-已冲销）
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

    public Integer getFinanceAccountType() {
        return financeAccountType;
    }

    public void setFinanceAccountType(Integer financeAccountType) {
        this.financeAccountType = financeAccountType;
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
        sb.append(", code=").append(code);
        sb.append(", orderType=").append(orderType);
        sb.append(", paymentDate=").append(paymentDate);
        sb.append(", paymentManId=").append(paymentManId);
        sb.append(", paymentManCode=").append(paymentManCode);
        sb.append(", paymentManName=").append(paymentManName);
        sb.append(", companyType=").append(companyType);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyCode=").append(companyCode);
        sb.append(", companyName=").append(companyName);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", clearAmountTotal=").append(clearAmountTotal);
        sb.append(", unclearAmountTotal=").append(unclearAmountTotal);
        sb.append(", paymentAmountTotal=").append(paymentAmountTotal);
        sb.append(", discountAmountTotal=").append(discountAmountTotal);
        sb.append(", realAmountTotal=").append(realAmountTotal);
        sb.append(", transferDate=").append(transferDate);
        sb.append(", bankId=").append(bankId);
        sb.append(", bankName=").append(bankName);
        sb.append(", bankAccount=").append(bankAccount);
        sb.append(", bankAmountTotal=").append(bankAmountTotal);
        sb.append(", cashAmountTotal=").append(cashAmountTotal);
        sb.append(", unclearBalanceTotal=").append(unclearBalanceTotal);
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