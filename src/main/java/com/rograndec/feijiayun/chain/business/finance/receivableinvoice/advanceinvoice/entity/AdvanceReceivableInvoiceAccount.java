package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_advance_receivable_invoice_account
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-11
 */
public class AdvanceReceivableInvoiceAccount implements Serializable {
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
     * 预收发票ID
     */
    @ApiModelProperty(value = "预收发票ID")
    private Long invoiceId;

    /**
     * 预收发票明细ID
     */
    @ApiModelProperty(value = "预收发票明细ID")
    private Long invoiceDtlId;

    /**
     * 出库单ID
     */
    @ApiModelProperty(value = "出库单ID")
    private Long baseOrderId;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String baseOrderCode;

    /**
     * 出库类型
     */
    @ApiModelProperty(value = "出库类型")
    private Integer baseOrderType;

    /**
     * 出库日期
     */
    @ApiModelProperty(value = "出库日期")
    private Date baseOrderDate;

    /**
     * 出库商品明细ID
     */
    @ApiModelProperty(value = "出库商品明细ID")
    private Long baseDtlId;

    /**
     * 出库货位明细ID
     */
    @ApiModelProperty(value = "出库货位明细ID")
    private Long baseShelfDtlId;

    /**
     * 本次对账数量
     */
    @ApiModelProperty(value = "本次对账数量")
    private BigDecimal accountQuantity;

    /**
     * 本次对账金额
     */
    @ApiModelProperty(value = "本次对账金额")
    private BigDecimal accountAmount;

    /**
     * 本次对账不含税金额
     */
    @ApiModelProperty(value = "本次对账不含税金额")
    private BigDecimal accountNotaxAmount;

    /**
     * 本次对账税额
     */
    @ApiModelProperty(value = "本次对账税额")
    private BigDecimal accountTaxAmount;

    /**
     * 金额差额
     */
    @ApiModelProperty(value = "金额差额")
    private BigDecimal diffAmount;

    /**
     * 不含税金额差额
     */
    @ApiModelProperty(value = "不含税金额差额")
    private BigDecimal diffNotaxAmount;

    /**
     * 税额差额
     */
    @ApiModelProperty(value = "税额差额")
    private BigDecimal diffTaxAmount;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

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
     * saas_advance_receivable_invoice_account
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
     * 预收发票ID
     * @return invoice_id 预收发票ID
     */
    public Long getInvoiceId() {
        return invoiceId;
    }

    /**
     * 预收发票ID
     * @param invoiceId 预收发票ID
     */
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * 预收发票明细ID
     * @return invoice_dtl_id 预收发票明细ID
     */
    public Long getInvoiceDtlId() {
        return invoiceDtlId;
    }

    /**
     * 预收发票明细ID
     * @param invoiceDtlId 预收发票明细ID
     */
    public void setInvoiceDtlId(Long invoiceDtlId) {
        this.invoiceDtlId = invoiceDtlId;
    }

    /**
     * 出库单ID
     * @return base_order_id 出库单ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 出库单ID
     * @param baseOrderId 出库单ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 单据编码
     * @return base_order_code 单据编码
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 单据编码
     * @param baseOrderCode 单据编码
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 出库类型
     * @return base_order_type 出库类型
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 出库类型
     * @param baseOrderType 出库类型
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 出库日期
     * @return base_order_date 出库日期
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 出库日期
     * @param baseOrderDate 出库日期
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    /**
     * 出库商品明细ID
     * @return base_dtl_id 出库商品明细ID
     */
    public Long getBaseDtlId() {
        return baseDtlId;
    }

    /**
     * 出库商品明细ID
     * @param baseDtlId 出库商品明细ID
     */
    public void setBaseDtlId(Long baseDtlId) {
        this.baseDtlId = baseDtlId;
    }

    /**
     * 出库货位明细ID
     * @return base_shelf_dtl_id 出库货位明细ID
     */
    public Long getBaseShelfDtlId() {
        return baseShelfDtlId;
    }

    /**
     * 出库货位明细ID
     * @param baseShelfDtlId 出库货位明细ID
     */
    public void setBaseShelfDtlId(Long baseShelfDtlId) {
        this.baseShelfDtlId = baseShelfDtlId;
    }

    /**
     * 本次对账数量
     * @return account_quantity 本次对账数量
     */
    public BigDecimal getAccountQuantity() {
        return accountQuantity;
    }

    /**
     * 本次对账数量
     * @param accountQuantity 本次对账数量
     */
    public void setAccountQuantity(BigDecimal accountQuantity) {
        this.accountQuantity = accountQuantity;
    }

    /**
     * 本次对账金额
     * @return account_amount 本次对账金额
     */
    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    /**
     * 本次对账金额
     * @param accountAmount 本次对账金额
     */
    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    /**
     * 本次对账不含税金额
     * @return account_notax_amount 本次对账不含税金额
     */
    public BigDecimal getAccountNotaxAmount() {
        return accountNotaxAmount;
    }

    /**
     * 本次对账不含税金额
     * @param accountNotaxAmount 本次对账不含税金额
     */
    public void setAccountNotaxAmount(BigDecimal accountNotaxAmount) {
        this.accountNotaxAmount = accountNotaxAmount;
    }

    /**
     * 本次对账税额
     * @return account_tax_amount 本次对账税额
     */
    public BigDecimal getAccountTaxAmount() {
        return accountTaxAmount;
    }

    /**
     * 本次对账税额
     * @param accountTaxAmount 本次对账税额
     */
    public void setAccountTaxAmount(BigDecimal accountTaxAmount) {
        this.accountTaxAmount = accountTaxAmount;
    }

   
    public BigDecimal getDiffAmount() {
		return diffAmount;
	}

	public void setDiffAmount(BigDecimal diffAmount) {
		this.diffAmount = diffAmount;
	}

	public BigDecimal getDiffNotaxAmount() {
		return diffNotaxAmount;
	}

	public void setDiffNotaxAmount(BigDecimal diffNotaxAmount) {
		this.diffNotaxAmount = diffNotaxAmount;
	}

	public BigDecimal getDiffTaxAmount() {
		return diffTaxAmount;
	}

	public void setDiffTaxAmount(BigDecimal diffTaxAmount) {
		this.diffTaxAmount = diffTaxAmount;
	}

	/**
     * 行号
     * @return line_num 行号
     */
    public Integer getLineNum() {
        return lineNum;
    }

    /**
     * 行号
     * @param lineNum 行号
     */
    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
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
        sb.append(", invoiceId=").append(invoiceId);
        sb.append(", invoiceDtlId=").append(invoiceDtlId);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", baseDtlId=").append(baseDtlId);
        sb.append(", baseShelfDtlId=").append(baseShelfDtlId);
        sb.append(", accountQuantity=").append(accountQuantity);
        sb.append(", accountAmount=").append(accountAmount);
        sb.append(", accountNotaxAmount=").append(accountNotaxAmount);
        sb.append(", accountTaxAmount=").append(accountTaxAmount);
        sb.append(", lineNum=").append(lineNum);
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