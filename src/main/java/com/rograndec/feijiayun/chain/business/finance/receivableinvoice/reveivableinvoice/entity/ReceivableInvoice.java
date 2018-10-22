package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_receivable_invoice
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class ReceivableInvoice implements Serializable {
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
     * 单据类型（2302）
     */
    @ApiModelProperty(value = "单据类型（2302）")
    private Integer orderType;

    /**
     * 开票日期
     */
    @ApiModelProperty(value = "开票日期")
    private Date billDate;

    /**
     * 开票人员ID
     */
    @ApiModelProperty(value = "开票人员ID")
    private Long billManId;

    /**
     * 开票人员编码
     */
    @ApiModelProperty(value = "开票人员编码")
    private String billManCode;

    /**
     * 开票人员名称
     */
    @ApiModelProperty(value = "开票人员名称")
    private String billManName;

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
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

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
     * 金额差额合计
     */
    @ApiModelProperty(value = "金额差额合计")
    private BigDecimal diffAmountTotal;

    /**
     * 不含税金额差额合计
     */
    @ApiModelProperty(value = "不含税金额差额合计")
    private BigDecimal diffNotaxAmountTotal;

    /**
     * 税额差额合计
     */
    @ApiModelProperty(value = "税额差额合计")
    private BigDecimal diffTaxAmountTotal;

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
     * 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待收款；1-部分收款；2-已收款；3-已冲销）")
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
     * 0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税
     */
    @ApiModelProperty(value = "0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税")
    private Integer financeAccountType;

    /**
     * saas_receivable_invoice
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
     * 单据类型（2302）
     * @return order_type 单据类型（2302）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（2302）
     * @param orderType 单据类型（2302）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 开票日期
     * @return bill_date 开票日期
     */
    public Date getBillDate() {
        return billDate;
    }

    /**
     * 开票日期
     * @param billDate 开票日期
     */
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    /**
     * 开票人员ID
     * @return bill_man_id 开票人员ID
     */
    public Long getBillManId() {
        return billManId;
    }

    /**
     * 开票人员ID
     * @param billManId 开票人员ID
     */
    public void setBillManId(Long billManId) {
        this.billManId = billManId;
    }

    /**
     * 开票人员编码
     * @return bill_man_code 开票人员编码
     */
    public String getBillManCode() {
        return billManCode;
    }

    /**
     * 开票人员编码
     * @param billManCode 开票人员编码
     */
    public void setBillManCode(String billManCode) {
        this.billManCode = billManCode == null ? null : billManCode.trim();
    }

    /**
     * 开票人员名称
     * @return bill_man_name 开票人员名称
     */
    public String getBillManName() {
        return billManName;
    }

    /**
     * 开票人员名称
     * @param billManName 开票人员名称
     */
    public void setBillManName(String billManName) {
        this.billManName = billManName == null ? null : billManName.trim();
    }

    /**
     * 购货单位ID
     * @return purchase_unit_id 购货单位ID
     */
    public Long getPurchaseUnitId() {
        return purchaseUnitId;
    }

    /**
     * 购货单位ID
     * @param purchaseUnitId 购货单位ID
     */
    public void setPurchaseUnitId(Long purchaseUnitId) {
        this.purchaseUnitId = purchaseUnitId;
    }

    /**
     * 购货单位编码
     * @return purchase_unit_code 购货单位编码
     */
    public String getPurchaseUnitCode() {
        return purchaseUnitCode;
    }

    /**
     * 购货单位编码
     * @param purchaseUnitCode 购货单位编码
     */
    public void setPurchaseUnitCode(String purchaseUnitCode) {
        this.purchaseUnitCode = purchaseUnitCode == null ? null : purchaseUnitCode.trim();
    }

    /**
     * 购货单位名称
     * @return purchase_unit_name 购货单位名称
     */
    public String getPurchaseUnitName() {
        return purchaseUnitName;
    }

    /**
     * 购货单位名称
     * @param purchaseUnitName 购货单位名称
     */
    public void setPurchaseUnitName(String purchaseUnitName) {
        this.purchaseUnitName = purchaseUnitName == null ? null : purchaseUnitName.trim();
    }

    /**
     * 数量合计
     * @return quantity_total 数量合计
     */
    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    /**
     * 数量合计
     * @param quantityTotal 数量合计
     */
    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    /**
     * 品种数量
     * @return varieties_quantity 品种数量
     */
    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    /**
     * 品种数量
     * @param varietiesQuantity 品种数量
     */
    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
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
     * 金额差额合计
     * @return diff_amount_total 金额差额合计
     */
    public BigDecimal getDiffAmountTotal() {
        return diffAmountTotal;
    }

    /**
     * 金额差额合计
     * @param diffAmountTotal 金额差额合计
     */
    public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
        this.diffAmountTotal = diffAmountTotal;
    }

    /**
     * 不含税金额差额合计
     * @return diff_notax_amount_total 不含税金额差额合计
     */
    public BigDecimal getDiffNotaxAmountTotal() {
        return diffNotaxAmountTotal;
    }

    /**
     * 不含税金额差额合计
     * @param diffNotaxAmountTotal 不含税金额差额合计
     */
    public void setDiffNotaxAmountTotal(BigDecimal diffNotaxAmountTotal) {
        this.diffNotaxAmountTotal = diffNotaxAmountTotal;
    }

    /**
     * 税额差额合计
     * @return diff_tax_amount_total 税额差额合计
     */
    public BigDecimal getDiffTaxAmountTotal() {
        return diffTaxAmountTotal;
    }

    /**
     * 税额差额合计
     * @param diffTaxAmountTotal 税额差额合计
     */
    public void setDiffTaxAmountTotal(BigDecimal diffTaxAmountTotal) {
        this.diffTaxAmountTotal = diffTaxAmountTotal;
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
     * 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     * @return status 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     * @param status 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
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
        sb.append(", billDate=").append(billDate);
        sb.append(", billManId=").append(billManId);
        sb.append(", billManCode=").append(billManCode);
        sb.append(", billManName=").append(billManName);
        sb.append(", purchaseUnitId=").append(purchaseUnitId);
        sb.append(", purchaseUnitCode=").append(purchaseUnitCode);
        sb.append(", purchaseUnitName=").append(purchaseUnitName);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", notaxAmountTotal=").append(notaxAmountTotal);
        sb.append(", taxAmountTotal=").append(taxAmountTotal);
        sb.append(", diffAmountTotal=").append(diffAmountTotal);
        sb.append(", diffNotaxAmountTotal=").append(diffNotaxAmountTotal);
        sb.append(", diffTaxAmountTotal=").append(diffTaxAmountTotal);
        sb.append(", clearAmountTotal=").append(clearAmountTotal);
        sb.append(", unclearAmountTotal=").append(unclearAmountTotal);
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
        sb.append(", financeAccountType=").append(financeAccountType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}