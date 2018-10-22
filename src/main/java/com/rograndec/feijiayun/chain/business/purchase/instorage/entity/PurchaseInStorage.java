package com.rograndec.feijiayun.chain.business.purchase.instorage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PurchaseInStorage implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 单据类型（5214）
     */
    private Integer orderType;

    /**
     * 单据（入库单）编号
     */
    private String code;

    /**
     * 入库日期
     */
    private Date inStorageDate;

    /**
     * 基础单据ID
     */
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    private Date baseOrderDate;

    /**
     * 供货单位ID
     */
    private Long supplierId;

    /**
     * 供货单位编码
     */
    private String supplierCode;

    /**
     * 供货单位名称
     */
    private String supplierName;

    /**
     * 供货单位销售人员ID
     */
    private Long supplierSalerId;

    /**
     * 供货单位销售人员编码
     */
    private String supplierSalerCode;

    /**
     * 供货单位销售人员名称
     */
    private String supplierSalerName;

    /**
     * 供货单位销售人员联系电话
     */
    private String supplierSalerPhone;

    /**
     * 入库人员ID
     */
    private Long storageManId;

    /**
     * 入库人员编码
     */
    private String storageManCode;

    /**
     * 入库人员名称
     */
    private String storageManName;

    /**
     * 数量合计
     */
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    private Integer varietiesQuantity;

    /**
     * 金额合计（整单优惠前的金额合计）
     */
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    private BigDecimal wholeDiscountAmount;

    /**
     * 实际金额合计
     */
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    private BigDecimal taxAmountTotal;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否被引用(配货单) 0-没有，1被调用
     */
    private Integer isUse;

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码
     */
    private String createrCode;

    /**
     * 创建人名称
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人ID
     */
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    private String modifierName;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 流通监管码
     */
    private String flowCode;

    /**
     * 采购订单ID
     */
    private Long purchaseOrderId;

    public static List<Long> getSupplierIds(List<PurchaseInStorage> purchaseInStorages){

        Set<Long> collect = purchaseInStorages.stream().map(PurchaseInStorage::getSupplierId).collect(Collectors.toSet());

        return collect.stream().collect(Collectors.toList());

    }

    /**
     * saas_purchase_in_storage
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
     * 单据类型（5214）
     * @return order_type 单据类型（5214）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5214）
     * @param orderType 单据类型（5214）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 单据（入库单）编号
     * @return code 单据（入库单）编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 单据（入库单）编号
     * @param code 单据（入库单）编号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 入库日期
     * @return in_storage_date 入库日期
     */
    public Date getInStorageDate() {
        return inStorageDate;
    }

    /**
     * 入库日期
     * @param inStorageDate 入库日期
     */
    public void setInStorageDate(Date inStorageDate) {
        this.inStorageDate = inStorageDate;
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

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

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
     * @return supplier_saler_phone 供货单位销售人员联系电话
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
     * 入库人员ID
     * @return storage_man_id 入库人员ID
     */
    public Long getStorageManId() {
        return storageManId;
    }

    /**
     * 入库人员ID
     * @param storageManId 入库人员ID
     */
    public void setStorageManId(Long storageManId) {
        this.storageManId = storageManId;
    }

    /**
     * 入库人员编码
     * @return storage_man_code 入库人员编码
     */
    public String getStorageManCode() {
        return storageManCode;
    }

    /**
     * 入库人员编码
     * @param storageManCode 入库人员编码
     */
    public void setStorageManCode(String storageManCode) {
        this.storageManCode = storageManCode == null ? null : storageManCode.trim();
    }

    /**
     * 入库人员名称
     * @return storage_man_name 入库人员名称
     */
    public String getStorageManName() {
        return storageManName;
    }

    /**
     * 入库人员名称
     * @param storageManName 入库人员名称
     */
    public void setStorageManName(String storageManName) {
        this.storageManName = storageManName == null ? null : storageManName.trim();
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
     * 金额合计（整单优惠前的金额合计）
     * @return amount_total 金额合计（整单优惠前的金额合计）
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @param amountTotal 金额合计（整单优惠前的金额合计）
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 整单折扣（%）
     * @return whole_discount 整单折扣（%）
     */
    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    /**
     * 整单折扣（%）
     * @param wholeDiscount 整单折扣（%）
     */
    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    /**
     * 整单优惠金额
     * @return whole_discount_amount 整单优惠金额
     */
    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    /**
     * 整单优惠金额
     * @param wholeDiscountAmount 整单优惠金额
     */
    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    /**
     * 实际金额合计
     * @return real_amount_total 实际金额合计
     */
    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    /**
     * 实际金额合计
     * @param realAmountTotal 实际金额合计
     */
    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_real_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxRealAmountTotal 不含税金额合计
     */
    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
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
     * 流通监管码
     * @param flowCode 流通监管码
     */
    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }


    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
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
        sb.append(", inStorageDate=").append(inStorageDate);
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
        sb.append(", storageManId=").append(storageManId);
        sb.append(", storageManCode=").append(storageManCode);
        sb.append(", storageManName=").append(storageManName);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", wholeDiscount=").append(wholeDiscount);
        sb.append(", wholeDiscountAmount=").append(wholeDiscountAmount);
        sb.append(", realAmountTotal=").append(realAmountTotal);
        sb.append(", notaxRealAmountTotal=").append(notaxRealAmountTotal);
        sb.append(", taxAmountTotal=").append(taxAmountTotal);
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
        sb.append(", flowCode=").append(flowCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}