package com.rograndec.feijiayun.chain.business.keytable.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_finance_voucher
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-12
 */
public class FinanceVoucher implements Serializable {
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
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private Date orderDate;

    /**
     * 单据类型
     */
    @ApiModelProperty(value = "单据类型")
    private Integer orderType;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String orderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期")
    private Date baseOrderDate;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型")
    private Integer baseOrderType;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID")
    private Long baseOrderId;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码")
    private String baseOrderCode;

    /**
     * 基础单据明细ID
     */
    @ApiModelProperty(value = "基础单据明细ID")
    private Long baseDtlId;

    /**
     * 基础单据操作人员ID
     */
    @ApiModelProperty(value = "基础单据操作人员ID")
    private Long baseOperatorId;

    /**
     * 基础单据操作人员编码
     */
    @ApiModelProperty(value = "基础单据操作人员编码")
    private String baseOperatorCode;

    /**
     * 基础单据操作人员名称
     */
    @ApiModelProperty(value = "基础单据操作人员名称")
    private String baseOperatorName;

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
     * saas_finance_voucher
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
     * 单据日期
     * @return order_date 单据日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * 单据日期
     * @param orderDate 单据日期
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 单据类型
     * @return order_type 单据类型
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型
     * @param orderType 单据类型
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 单据编码
     * @return order_code 单据编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 单据编码
     * @param orderCode 单据编码
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * 基础单据日期
     * @return base_order_date 基础单据日期
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 基础单据日期
     * @param baseOrderDate 基础单据日期
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    /**
     * 基础单据类型
     * @return base_order_type 基础单据类型
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 基础单据类型
     * @param baseOrderType 基础单据类型
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 基础单据ID
     * @return base_order_id 基础单据ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 基础单据ID
     * @param baseOrderId 基础单据ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 基础单据编码
     * @return base_order_code 基础单据编码
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 基础单据编码
     * @param baseOrderCode 基础单据编码
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 基础单据明细ID
     * @return base_dtl_id 基础单据明细ID
     */
    public Long getBaseDtlId() {
        return baseDtlId;
    }

    /**
     * 基础单据明细ID
     * @param baseDtlId 基础单据明细ID
     */
    public void setBaseDtlId(Long baseDtlId) {
        this.baseDtlId = baseDtlId;
    }

    /**
     * 基础单据操作人员ID
     * @return base_operator_id 基础单据操作人员ID
     */
    public Long getBaseOperatorId() {
        return baseOperatorId;
    }

    /**
     * 基础单据操作人员ID
     * @param baseOperatorId 基础单据操作人员ID
     */
    public void setBaseOperatorId(Long baseOperatorId) {
        this.baseOperatorId = baseOperatorId;
    }

    /**
     * 基础单据操作人员编码
     * @return base_operator_code 基础单据操作人员编码
     */
    public String getBaseOperatorCode() {
        return baseOperatorCode;
    }

    /**
     * 基础单据操作人员编码
     * @param baseOperatorCode 基础单据操作人员编码
     */
    public void setBaseOperatorCode(String baseOperatorCode) {
        this.baseOperatorCode = baseOperatorCode == null ? null : baseOperatorCode.trim();
    }

    /**
     * 基础单据操作人员名称
     * @return base_operator_name 基础单据操作人员名称
     */
    public String getBaseOperatorName() {
        return baseOperatorName;
    }

    /**
     * 基础单据操作人员名称
     * @param baseOperatorName 基础单据操作人员名称
     */
    public void setBaseOperatorName(String baseOperatorName) {
        this.baseOperatorName = baseOperatorName == null ? null : baseOperatorName.trim();
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
        sb.append(", orderDate=").append(orderDate);
        sb.append(", orderType=").append(orderType);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseDtlId=").append(baseDtlId);
        sb.append(", baseOperatorId=").append(baseOperatorId);
        sb.append(", baseOperatorCode=").append(baseOperatorCode);
        sb.append(", baseOperatorName=").append(baseOperatorName);
        sb.append(", debitAmount=").append(debitAmount);
        sb.append(", creditAmount=").append(creditAmount);
        sb.append(", balance=").append(balance);
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