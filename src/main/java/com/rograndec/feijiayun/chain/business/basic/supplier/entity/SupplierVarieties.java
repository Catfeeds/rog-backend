package com.rograndec.feijiayun.chain.business.basic.supplier.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SupplierVarieties implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 供货单位ID
     */
    private Long supplierId;

    /**
     * 企业（总部）ID
     */
    private Long enterpriseId;


    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 供货单位商品编号
     */
    private String supplierGoodsCode;

    /**
     * 经营方式（0-购销；1-实销实结）
     */
    private Integer managementMode;

    /**
     * 含税协议价
     */
    private BigDecimal agreementPrice;

    /**
     * 税率ID
     */
    private Long taxRateId;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 唯一供货单位（0-否；1-是）
     */
    private Integer soleSupplier;

    /**
     * 状态（0-禁用；1-启用）
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
     * 最新采购价
     */
    private BigDecimal lastPurPrice;

    /**
     * 最新采购税率ID
     */
    private Long lastPurTaxRateId;

    /**
     * 最新采购税率
     */
    private BigDecimal lastPurTaxRate;

    /**
     * saas_supplier_business_varieties
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
     * 企业（总部）ID
     * @return enterprise_id 企业（总部）ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业（总部）ID
     * @param enterpriseId 企业（总部）ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 商品编码
     * @return goods_code 商品编码
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 商品编码
     * @param goodsCode 商品编码
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     * 商品名称
     * @return goods_name 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 商品名称
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 供货单位商品编号
     * @return supplier_goods_code 供货单位商品编号
     */
    public String getSupplierGoodsCode() {
        return supplierGoodsCode;
    }

    /**
     * 供货单位商品编号
     * @param supplierGoodsCode 供货单位商品编号
     */
    public void setSupplierGoodsCode(String supplierGoodsCode) {
        this.supplierGoodsCode = supplierGoodsCode == null ? null : supplierGoodsCode.trim();
    }

    /**
     * 经营方式（0-购销；1-实销实结）
     * @return management_mode 经营方式（0-购销；1-实销实结）
     */
    public Integer getManagementMode() {
        return managementMode;
    }

    /**
     * 经营方式（0-购销；1-实销实结）
     * @param managementMode 经营方式（0-购销；1-实销实结）
     */
    public void setManagementMode(Integer managementMode) {
        this.managementMode = managementMode;
    }

    /**
     * 含税协议价
     * @return agreement_price 含税协议价
     */
    public BigDecimal getAgreementPrice() {
        return agreementPrice;
    }

    /**
     * 含税协议价
     * @param agreementPrice 含税协议价
     */
    public void setAgreementPrice(BigDecimal agreementPrice) {
        this.agreementPrice = agreementPrice;
    }

    /**
     * 税率ID
     * @return tax_rate_id 税率ID
     */
    public Long getTaxRateId() {
        return taxRateId;
    }

    /**
     * 税率ID
     * @param taxRateId 税率ID
     */
    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    /**
     * 税率
     * @return tax_rate 税率
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * 税率
     * @param taxRate 税率
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * 唯一供货单位（0-否；1-是）
     * @return sole_supplier 唯一供货单位（0-否；1-是）
     */
    public Integer getSoleSupplier() {
        return soleSupplier;
    }

    /**
     * 唯一供货单位（0-否；1-是）
     * @param soleSupplier 唯一供货单位（0-否；1-是）
     */
    public void setSoleSupplier(Integer soleSupplier) {
        this.soleSupplier = soleSupplier;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @return status 状态（0-禁用；1-启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @param status 状态（0-禁用；1-启用）
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

    public BigDecimal getLastPurPrice() {
        return lastPurPrice;
    }

    public void setLastPurPrice(BigDecimal lastPurPrice) {
        this.lastPurPrice = lastPurPrice;
    }

    public Long getLastPurTaxRateId() {
        return lastPurTaxRateId;
    }

    public void setLastPurTaxRateId(Long lastPurTaxRateId) {
        this.lastPurTaxRateId = lastPurTaxRateId;
    }

    public BigDecimal getLastPurTaxRate() {
        return lastPurTaxRate;
    }

    public void setLastPurTaxRate(BigDecimal lastPurTaxRate) {
        this.lastPurTaxRate = lastPurTaxRate;
    }


    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "SupplierVarieties{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", enterpriseId=" + enterpriseId +
                ", goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", supplierGoodsCode='" + supplierGoodsCode + '\'' +
                ", managementMode=" + managementMode +
                ", agreementPrice=" + agreementPrice +
                ", taxRateId=" + taxRateId +
                ", taxRate=" + taxRate +
                ", soleSupplier=" + soleSupplier +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", createrId=" + createrId +
                ", createrCode='" + createrCode + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createTime=" + createTime +
                ", modifierId=" + modifierId +
                ", modifierCode='" + modifierCode + '\'' +
                ", modifierName='" + modifierName + '\'' +
                ", updateTime=" + updateTime +
                ", lastPurPrice=" + lastPurPrice +
                ", lastPurTaxRateId=" + lastPurTaxRateId +
                ", lastPurTaxRate=" + lastPurTaxRate +
                '}';
    }
}