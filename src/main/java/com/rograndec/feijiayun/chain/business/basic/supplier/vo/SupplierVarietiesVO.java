package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SupplierVarietiesVO implements Serializable {
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
     * 标准库商品ID
     */
    private Long normGoodsId;

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
     * 最新采购价
     */
    private BigDecimal lastPurPrice;

    /**
     * 最新成本价
     */
    private BigDecimal lastCostPrice;


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
     * 标准库商品ID
     * @return norm_goods_id 标准库商品ID
     */
    public Long getNormGoodsId() {
        return normGoodsId;
    }

    /**
     * 标准库商品ID
     * @param normGoodsId 标准库商品ID
     */
    public void setNormGoodsId(Long normGoodsId) {
        this.normGoodsId = normGoodsId;
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

    public BigDecimal getLastPurPrice() {
        return lastPurPrice;
    }

    public void setLastPurPrice(BigDecimal lastPurPrice) {
        this.lastPurPrice = lastPurPrice;
    }

    public BigDecimal getLastCostPrice() {
        return lastCostPrice;
    }

    public void setLastCostPrice(BigDecimal lastCostPrice) {
        this.lastCostPrice = lastCostPrice;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "SupplierVarietiesVO{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", enterpriseId=" + enterpriseId +
                ", normGoodsId=" + normGoodsId +
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
                ", lastPurPrice=" + lastPurPrice +
                ", lastCostPrice=" + lastCostPrice +
                '}';
    }
}