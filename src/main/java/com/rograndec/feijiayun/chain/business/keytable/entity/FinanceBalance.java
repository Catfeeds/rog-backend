package com.rograndec.feijiayun.chain.business.keytable.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_finance_balance
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-12
 */
public class FinanceBalance implements Serializable {
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
     * 企业类型（0-总部；1-自营店；2-加盟店）
     */
    @ApiModelProperty(value = "企业类型（0-总部；1-自营店；2-加盟店）")
    private Integer chainType;

    /**
     * 科目编码
     */
    @ApiModelProperty(value = "科目编码")
    private String accountCode;

    /**
     * 科目名称
     */
    @ApiModelProperty(value = "科目名称")
    private String accountName;

    /**
     * 下级科目类型（0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税）
     */
    @ApiModelProperty(value = "下级科目类型（0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税）")
    private Integer subAccountType;

    /**
     * 下级科目ID
     */
    @ApiModelProperty(value = "下级科目ID")
    private Long subAccountId;

    /**
     * 下级科目编码
     */
    @ApiModelProperty(value = "下级科目编码")
    private String subAccountCode;

    /**
     * 下级科目名称
     */
    @ApiModelProperty(value = "下级科目名称")
    private String subAccountName;

    /**
     * 借方金额
     */
    @ApiModelProperty(value = "借方金额")
    private BigDecimal debitAmount;

    /**
     * 贷方金额
     */
    @ApiModelProperty(value = "贷方金额")
    private BigDecimal creditAmount;

    /**
     * 余额
     */
    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

    /**
     * 出入库金额，默认0
     */
    @ApiModelProperty(value = "出入库金额，默认0")
    private BigDecimal inOutAmount;

    /**
     * 开票金额，默认0
     */
    @ApiModelProperty(value = "开票金额，默认0")
    private BigDecimal billAmount;

    /**
     * 欠票金额，默认0
     */
    @ApiModelProperty(value = "欠票金额，默认0")
    private BigDecimal oweBillAmount;

    /**
     * 收付款金额，默认0
     */
    @ApiModelProperty(value = "收付款金额，默认0")
    private BigDecimal receivePayAmount;

    /**
     * 欠款金额，默认0
     */
    @ApiModelProperty(value = "欠款金额，默认0")
    private BigDecimal oweAmount;

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
     * saas_finance_balance
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
     * 企业类型（0-总部；1-自营店；2-加盟店）
     * @return chain_type 企业类型（0-总部；1-自营店；2-加盟店）
     */
    public Integer getChainType() {
        return chainType;
    }

    /**
     * 企业类型（0-总部；1-自营店；2-加盟店）
     * @param chainType 企业类型（0-总部；1-自营店；2-加盟店）
     */
    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    /**
     * 科目编码
     * @return account_code 科目编码
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * 科目编码
     * @param accountCode 科目编码
     */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode == null ? null : accountCode.trim();
    }

    /**
     * 科目名称
     * @return account_name 科目名称
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 科目名称
     * @param accountName 科目名称
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     * 下级科目类型（0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税）
     * @return sub_account_type 下级科目类型（0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税）
     */
    public Integer getSubAccountType() {
        return subAccountType;
    }

    /**
     * 下级科目类型（0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税）
     * @param subAccountType 下级科目类型（0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税）
     */
    public void setSubAccountType(Integer subAccountType) {
        this.subAccountType = subAccountType;
    }

    /**
     * 下级科目ID
     * @return sub_account_id 下级科目ID
     */
    public Long getSubAccountId() {
        return subAccountId;
    }

    /**
     * 下级科目ID
     * @param subAccountId 下级科目ID
     */
    public void setSubAccountId(Long subAccountId) {
        this.subAccountId = subAccountId;
    }

    /**
     * 下级科目编码
     * @return sub_account_code 下级科目编码
     */
    public String getSubAccountCode() {
        return subAccountCode;
    }

    /**
     * 下级科目编码
     * @param subAccountCode 下级科目编码
     */
    public void setSubAccountCode(String subAccountCode) {
        this.subAccountCode = subAccountCode == null ? null : subAccountCode.trim();
    }

    /**
     * 下级科目名称
     * @return sub_account_name 下级科目名称
     */
    public String getSubAccountName() {
        return subAccountName;
    }

    /**
     * 下级科目名称
     * @param subAccountName 下级科目名称
     */
    public void setSubAccountName(String subAccountName) {
        this.subAccountName = subAccountName == null ? null : subAccountName.trim();
    }

    /**
     * 借方金额
     * @return debit_amount 借方金额
     */
    public BigDecimal getDebitAmount() {
        return debitAmount;
    }

    /**
     * 借方金额
     * @param debitAmount 借方金额
     */
    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

    /**
     * 贷方金额
     * @return credit_amount 贷方金额
     */
    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    /**
     * 贷方金额
     * @param creditAmount 贷方金额
     */
    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    /**
     * 余额
     * @return balance 余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 余额
     * @param balance 余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 出入库金额，默认0
     * @return in_out_amount 出入库金额，默认0
     */
    public BigDecimal getInOutAmount() {
        return inOutAmount;
    }

    /**
     * 出入库金额，默认0
     * @param inOutAmount 出入库金额，默认0
     */
    public void setInOutAmount(BigDecimal inOutAmount) {
        this.inOutAmount = inOutAmount;
    }

    /**
     * 开票金额，默认0
     * @return bill_amount 开票金额，默认0
     */
    public BigDecimal getBillAmount() {
        return billAmount;
    }

    /**
     * 开票金额，默认0
     * @param billAmount 开票金额，默认0
     */
    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    /**
     * 欠票金额，默认0
     * @return owe_bill_amount 欠票金额，默认0
     */
    public BigDecimal getOweBillAmount() {
        return oweBillAmount;
    }

    /**
     * 欠票金额，默认0
     * @param oweBillAmount 欠票金额，默认0
     */
    public void setOweBillAmount(BigDecimal oweBillAmount) {
        this.oweBillAmount = oweBillAmount;
    }

    /**
     * 收付款金额，默认0
     * @return receive_pay_amount 收付款金额，默认0
     */
    public BigDecimal getReceivePayAmount() {
        return receivePayAmount;
    }

    /**
     * 收付款金额，默认0
     * @param receivePayAmount 收付款金额，默认0
     */
    public void setReceivePayAmount(BigDecimal receivePayAmount) {
        this.receivePayAmount = receivePayAmount;
    }

    /**
     * 欠款金额，默认0
     * @return owe_amount 欠款金额，默认0
     */
    public BigDecimal getOweAmount() {
        return oweAmount;
    }

    /**
     * 欠款金额，默认0
     * @param oweAmount 欠款金额，默认0
     */
    public void setOweAmount(BigDecimal oweAmount) {
        this.oweAmount = oweAmount;
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
        sb.append(", chainType=").append(chainType);
        sb.append(", accountCode=").append(accountCode);
        sb.append(", accountName=").append(accountName);
        sb.append(", subAccountType=").append(subAccountType);
        sb.append(", subAccountId=").append(subAccountId);
        sb.append(", subAccountCode=").append(subAccountCode);
        sb.append(", subAccountName=").append(subAccountName);
        sb.append(", debitAmount=").append(debitAmount);
        sb.append(", creditAmount=").append(creditAmount);
        sb.append(", balance=").append(balance);
        sb.append(", inOutAmount=").append(inOutAmount);
        sb.append(", billAmount=").append(billAmount);
        sb.append(", oweBillAmount=").append(oweBillAmount);
        sb.append(", receivePayAmount=").append(receivePayAmount);
        sb.append(", oweAmount=").append(oweAmount);
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