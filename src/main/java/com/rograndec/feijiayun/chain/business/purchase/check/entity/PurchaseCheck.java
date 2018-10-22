package com.rograndec.feijiayun.chain.business.purchase.check.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PurchaseCheck implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID", required = true)
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID", required = true)
    private Long parentId;

    /**
     * 单据类型（5213）
     */
    @ApiModelProperty(value = "单据类型（5213）", required = true)
    private Integer orderType;

    /**
     * 单据（验收单）编号
     */
    @ApiModelProperty(value = "单据（验收单）编号", required = true)
    private String code;

    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期", required = true)
    private Date checkDate;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID", required = true)
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型", required = true)
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码", required = true)
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期", required = true)
    private Date baseOrderDate;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID", required = true)
    private Long supplierId;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码", required = true)
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称", required = true)
    private String supplierName;

    /**
     * 供货单位销售人员ID
     */
    @ApiModelProperty(value = "供货单位销售人员ID", required = true)
    private Long supplierSalerId;

    /**
     * 供货单位销售人员编码
     */
    @ApiModelProperty(value = "供货单位销售人员编码", required = true)
    private String supplierSalerCode;

    /**
     * 供货单位销售人员名称
     */
    @ApiModelProperty(value = "供货单位销售人员名称", required = true)
    private String supplierSalerName;

    /**
     * 供货单位销售人员联系电话
     */
    @ApiModelProperty(value = "供货单位销售人员联系电话", required = true)
    private String supplierSalerPhone;

    /**
     * 验收人员ID
     */
    @ApiModelProperty(value = "验收人员ID", required = true)
    private Long checkerId;

    /**
     * 验收人员编码
     */
    @ApiModelProperty(value = "验收人员编码", required = true)
    private String checkerCode;

    /**
     * 验收人员名称
     */
    @ApiModelProperty(value = "验收人员名称", required = true)
    private String checkerName;

    /**
     * 验收人员2ID
     */
    @ApiModelProperty(value = "验收人员2ID", required = true)
    private Long secondCheckerId;

    /**
     * 验收人员2编码
     */
    @ApiModelProperty(value = "验收人员2编码", required = true)
    private String secondCheckerCode;

    /**
     * 验收人员2名称
     */
    @ApiModelProperty(value = "验收人员2名称", required = true)
    private String secondCheckerName;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计", required = true)
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量", required = true)
    private Integer varietiesQuantity;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID", required = true)
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码", required = true)
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称", required = true)
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID", required = true)
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码", required = true)
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称", required = true)
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

    /**
     * 采购订单ID
     */
    @ApiModelProperty(value = "采购订单ID", required = true)
    private Long purchaseOrderId;

    private List<PurchaseCheckDetail> purchaseCheckDetailList;

    /**
     * saas_purchase_check
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
     * 单据类型（5213）
     * @return order_type 单据类型（5213）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5213）
     * @param orderType 单据类型（5213）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 单据（验收单）编号
     * @return code 单据（验收单）编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 单据（验收单）编号
     * @param code 单据（验收单）编号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 验收日期
     * @return check_date 验收日期
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 验收日期
     * @param checkDate 验收日期
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
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
     * 供货单位ID
     * @return supplier_id 供货单位ID
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * 供货单位ID
     * @param supplierId 供货单位ID
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * 供货单位编码
     * @return supplier_code 供货单位编码
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * 供货单位编码
     * @param supplierCode 供货单位编码
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode == null ? null : supplierCode.trim();
    }

    /**
     * 供货单位名称
     * @return supplier_name 供货单位名称
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * 供货单位名称
     * @param supplierName 供货单位名称
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    /**
     * 供货单位销售人员ID
     * @return supplier_saler_id 供货单位销售人员ID
     */
    public Long getSupplierSalerId() {
        return supplierSalerId;
    }

    /**
     * 供货单位销售人员ID
     * @param supplierSalerId 供货单位销售人员ID
     */
    public void setSupplierSalerId(Long supplierSalerId) {
        this.supplierSalerId = supplierSalerId;
    }

    /**
     * 供货单位销售人员编码
     * @return supplier_saler_code 供货单位销售人员编码
     */
    public String getSupplierSalerCode() {
        return supplierSalerCode;
    }

    /**
     * 供货单位销售人员编码
     * @param supplierSalerCode 供货单位销售人员编码
     */
    public void setSupplierSalerCode(String supplierSalerCode) {
        this.supplierSalerCode = supplierSalerCode == null ? null : supplierSalerCode.trim();
    }

    /**
     * 供货单位销售人员名称
     * @return supplier_saler_name 供货单位销售人员名称
     */
    public String getSupplierSalerName() {
        return supplierSalerName;
    }

    /**
     * 供货单位销售人员名称
     * @param supplierSalerName 供货单位销售人员名称
     */
    public void setSupplierSalerName(String supplierSalerName) {
        this.supplierSalerName = supplierSalerName == null ? null : supplierSalerName.trim();
    }

    /**
     * 供货单位销售人员联系电话
     * @return Tis upplier_saler_phone 供货单位销售人员联系电话
     */
    public String getSupplierSalerPhone() {
        return supplierSalerPhone;
    }

    /**
     * 供货单位销售人员联系电话
     * @param supplierSalerPhone 供货单位销售人员联系电话
     */
    public void setSupplierSalerPhone(String supplierSalerPhone) {
        this.supplierSalerPhone = supplierSalerPhone == null ? null : supplierSalerPhone.trim();
    }

    /**
     * 验收人员ID
     * @return checker_id 验收人员ID
     */
    public Long getCheckerId() {
        return checkerId;
    }

    /**
     * 验收人员ID
     * @param checkerId 验收人员ID
     */
    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    /**
     * 验收人员编码
     * @return checker_code 验收人员编码
     */
    public String getCheckerCode() {
        return checkerCode;
    }

    /**
     * 验收人员编码
     * @param checkerCode 验收人员编码
     */
    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode == null ? null : checkerCode.trim();
    }

    /**
     * 验收人员名称
     * @return checker_name 验收人员名称
     */
    public String getCheckerName() {
        return checkerName;
    }

    /**
     * 验收人员名称
     * @param checkerName 验收人员名称
     */
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName == null ? null : checkerName.trim();
    }

    /**
     * 验收人员2ID
     * @return second_checker_id 验收人员2ID
     */
    public Long getSecondCheckerId() {
        return secondCheckerId;
    }

    /**
     * 验收人员2ID
     * @param secondCheckerId 验收人员2ID
     */
    public void setSecondCheckerId(Long secondCheckerId) {
        this.secondCheckerId = secondCheckerId;
    }

    /**
     * 验收人员2编码
     * @return second_checker_code 验收人员2编码
     */
    public String getSecondCheckerCode() {
        return secondCheckerCode;
    }

    /**
     * 验收人员2编码
     * @param secondCheckerCode 验收人员2编码
     */
    public void setSecondCheckerCode(String secondCheckerCode) {
        this.secondCheckerCode = secondCheckerCode == null ? null : secondCheckerCode.trim();
    }

    /**
     * 验收人员2名称
     * @return second_checker_name 验收人员2名称
     */
    public String getSecondCheckerName() {
        return secondCheckerName;
    }

    /**
     * 验收人员2名称
     * @param secondCheckerName 验收人员2名称
     */
    public void setSecondCheckerName(String secondCheckerName) {
        this.secondCheckerName = secondCheckerName == null ? null : secondCheckerName.trim();
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
     * 采购订单ID
     * @return purchase_order_id 采购订单ID
     */
    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    /**
     * 采购订单ID
     * @param purchaseOrderId 采购订单ID
     */
    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public List<PurchaseCheckDetail> getPurchaseCheckDetailList() {
        return purchaseCheckDetailList;
    }

    public void setPurchaseCheckDetailList(List<PurchaseCheckDetail> purchaseCheckDetailList) {
        this.purchaseCheckDetailList = purchaseCheckDetailList;
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
        sb.append(", code=").append(code);
        sb.append(", checkDate=").append(checkDate);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", supplierCode=").append(supplierCode);
        sb.append(", supplierName=").append(supplierName);
        sb.append(", supplierSalerId=").append(supplierSalerId);
        sb.append(", supplierSalerCode=").append(supplierSalerCode);
        sb.append(", supplierSalerName=").append(supplierSalerName);
        sb.append(", supplierSalerPhone=").append(supplierSalerPhone);
        sb.append(", checkerId=").append(checkerId);
        sb.append(", checkerCode=").append(checkerCode);
        sb.append(", checkerName=").append(checkerName);
        sb.append(", secondCheckerId=").append(secondCheckerId);
        sb.append(", secondCheckerCode=").append(secondCheckerCode);
        sb.append(", secondCheckerName=").append(secondCheckerName);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
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
        sb.append(", purchaseOrderId=").append(purchaseOrderId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}