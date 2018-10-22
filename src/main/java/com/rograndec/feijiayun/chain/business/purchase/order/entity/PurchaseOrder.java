package com.rograndec.feijiayun.chain.business.purchase.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PurchaseOrder implements Serializable {
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
     * 订单类型（5211）
     */
    private Integer orderType;

    /**
     * 采购方式（0-MPH采购；1-标准采购）
     */
    private Integer orderMode;

    /**
     * 订单日期
     */
    private Date orderDate;

    /**
     * 订单单号
     */
    private String code;

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
     * 供货位单位编码
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
     * 采购人ID
     */
    private Long purchaserId;

    /**
     * 采购人编码
     */
    private String purchaserCode;

    /**
     * 采购人名称
     */
    private String purchaserName;

    /**
     * 预计到货日期
     */
    private Date arrivalTime;

    /**
     * 单据状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

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
     * saas_purchase_order
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
     * 订单类型（5211）
     * @return order_type 订单类型（5211）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 订单类型（5211）
     * @param orderType 订单类型（5211）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 采购方式（0-MPH采购；1-标准采购）
     * @return order_mode 采购方式（0-MPH采购；1-标准采购）
     */
    public Integer getOrderMode() {
        return orderMode;
    }

    /**
     * 采购方式（0-MPH采购；1-标准采购）
     * @param orderMode 采购方式（0-MPH采购；1-标准采购）
     */
    public void setOrderMode(Integer orderMode) {
        this.orderMode = orderMode;
    }

    /**
     * 订单日期
     * @return order_date 订单日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * 订单日期
     * @param orderDate 订单日期
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 订单单号
     * @return code 订单单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 订单单号
     * @param code 订单单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
     * 供货位单位编码
     * @return supplier_code 供货位单位编码
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * 供货位单位编码
     * @param supplierCode 供货位单位编码
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
     * 采购人ID
     * @return purchaser_id 采购人ID
     */
    public Long getPurchaserId() {
        return purchaserId;
    }

    /**
     * 采购人ID
     * @param purchaserId 采购人ID
     */
    public void setPurchaserId(Long purchaserId) {
        this.purchaserId = purchaserId;
    }

    /**
     * 采购人编码
     * @return purchaser_code 采购人编码
     */
    public String getPurchaserCode() {
        return purchaserCode;
    }

    /**
     * 采购人编码
     * @param purchaserCode 采购人编码
     */
    public void setPurchaserCode(String purchaserCode) {
        this.purchaserCode = purchaserCode == null ? null : purchaserCode.trim();
    }

    /**
     * 采购人名称
     * @return purchaser_name 采购人名称
     */
    public String getPurchaserName() {
        return purchaserName;
    }

    /**
     * 采购人名称
     * @param purchaserName 采购人名称
     */
    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName == null ? null : purchaserName.trim();
    }

    /**
     * 预计到货日期
     * @return arrival_time 预计到货日期
     */
    public Date getArrivalTime() {
        return arrivalTime;
    }

    /**
     * 预计到货日期
     * @param arrivalTime 预计到货日期
     */
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * 单据状态
     * @return status 单据状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 单据状态
     * @param status 单据状态
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
        sb.append(", orderMode=").append(orderMode);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", code=").append(code);
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
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", wholeDiscount=").append(wholeDiscount);
        sb.append(", wholeDiscountAmount=").append(wholeDiscountAmount);
        sb.append(", realAmountTotal=").append(realAmountTotal);
        sb.append(", notaxRealAmountTotal=").append(notaxRealAmountTotal);
        sb.append(", taxAmountTotal=").append(taxAmountTotal);
        sb.append(", purchaserId=").append(purchaserId);
        sb.append(", purchaserCode=").append(purchaserCode);
        sb.append(", purchaserName=").append(purchaserName);
        sb.append(", arrivalTime=").append(arrivalTime);
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