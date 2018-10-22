package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import com.rograndec.feijiayun.chain.common.constant.Nature;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by madong on 2017/9/2.
 */
public class SupplierVarietiesExportVO implements Serializable {
    private static final long serialVersionUID = -9093854646106231897L;
    @ApiModelProperty(value = "主键ID")
    private Long id;
    //--supplier表
    @ApiModelProperty(value = "供货单位id")
    private Long supplierId;//供货单位id
    @ApiModelProperty(value = "供货单位编码")
    private String supplierCode;//供货单位编码
    @ApiModelProperty(value = "供货单位名称")
    private String supplierName;//供货单位名称
    @ApiModelProperty(value = "企业性质")
    private String nature;


    //--end supplier表
    //--goods表
    @ApiModelProperty(value = "商品id")
    private Long goodsId;//商品id
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;//商品编码
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "通用名称")
    private String genericName;//通用名称
    @ApiModelProperty(value = "条形码")
    private String barcode;
    @ApiModelProperty(value = "unit_name 单位")
    private String goodsUnitName;//unit_name 单位
    @ApiModelProperty(value = "剂型")
    private String dosageName;//剂型
    @ApiModelProperty(value = "规格")
    private String specification;//规格
    @ApiModelProperty(value = "生产厂家")
    private String manufacturer;//生产厂家
    @ApiModelProperty(value = "产地")
    private String goodsPlace;//产地
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;
    //--end goods表
    @ApiModelProperty(value = "经营方式 0购销 1 实销实结")
    private Integer managementMode;//经营方式 0购销 1 实销实结
    @ApiModelProperty(value = "经营方式")
    private String managementModeName;//经营方式
    @ApiModelProperty(value = "协议含税单价")
    private BigDecimal agreementPrice;//协议含税单价

    @ApiModelProperty(value = "协议不含税单价")
    private BigDecimal notaxAgreementPrice;//协议不含税单价

    @ApiModelProperty(value = "供应单位商品编号")
    private String supplierGoodsCode;//供应单位商品编号
    @ApiModelProperty(value = "税率id")
    private Long taxRateId;//税率id
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;//税率
    @ApiModelProperty(value = "唯一供货单位 0-否；1-是")
    private Integer soleSupplier;//唯一供货单位 0-否；1-是
    @ApiModelProperty(value = "唯一供货单位")
    private String soleSupplierName;//唯一供货单位
    @ApiModelProperty(value = "最新采购单价")
    private BigDecimal lastPurPrice;//最进采购单价
    @ApiModelProperty(value = "最新采购税率ID")
    private Long lastPurTaxRateId;//最新采购税率ID
    @ApiModelProperty(value = "最新采购税率")
    private BigDecimal lastPurTaxRate;//最新采购税率
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;    //企业ID
    @ApiModelProperty(value = "是否所有者，0否1是 (控制可编辑)")
    private Integer isOwner;    //是否所有者

    /**
     * 结算单位是否是加盟店（0-否  1-是）
     */
    @ApiModelProperty(required = true, value = "结算单位是否是加盟店（0-否  1-是）")
    private Integer franchisedStoreFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsUnitName() {
        return goodsUnitName;
    }

    public void setGoodsUnitName(String goodsUnitName) {
        this.goodsUnitName = goodsUnitName;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getManagementMode() {
        return managementMode;
    }
    public String getManagementModeName() {
        return managementMode==null?"":managementMode==0?"购销":"实销实结";
    }

    public void setManagementMode(Integer managementMode) {
        this.managementMode = managementMode;
    }

    public void setManagementModeName(String managementModeName) {
        this.managementModeName = managementModeName;
    }

    public BigDecimal getAgreementPrice() {
        return agreementPrice;
    }

    public void setAgreementPrice(BigDecimal agreementPrice) {
        this.agreementPrice = agreementPrice;
    }

    public String getSupplierGoodsCode() {
        return supplierGoodsCode;
    }

    public void setSupplierGoodsCode(String supplierGoodsCode) {
        this.supplierGoodsCode = supplierGoodsCode;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public Integer getSoleSupplier() {
        return soleSupplier;
    }

    public void setSoleSupplier(Integer soleSupplier) {
        this.soleSupplier = soleSupplier;
    }

    public String getSoleSupplierName() {
        return soleSupplier==null?"":soleSupplier==0?"否":"是";
    }

    public void setSoleSupplierName(String soleSupplierName) {
        this.soleSupplierName = soleSupplierName;
    }

    public BigDecimal getLastPurPrice() {
        return lastPurPrice;
    }

    public void setLastPurPrice(BigDecimal lastPurPrice) {
        this.lastPurPrice = lastPurPrice;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public String getNature() {
        if(nature != null){
            return Nature.getName(Integer.valueOf(nature));
        }
        return "";
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public BigDecimal getNotaxAgreementPrice() {
        if(agreementPrice != null && taxRate != null){
            BigDecimal divide = agreementPrice.divide(new BigDecimal(1).add(taxRate.divide(new BigDecimal(100))), 2, 6);
            return divide;
        }
        return BigDecimal.ZERO;
    }

    public void setNotaxAgreementPrice(BigDecimal notaxAgreementPrice) {
        this.notaxAgreementPrice = notaxAgreementPrice;
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

    public Integer getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(Integer isOwner) {
        this.isOwner = isOwner;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getFranchisedStoreFlag() {
        return franchisedStoreFlag;
    }

    public void setFranchisedStoreFlag(Integer franchisedStoreFlag) {
        this.franchisedStoreFlag = franchisedStoreFlag;
    }

    @Override
    public String toString() {
        return "SupplierVarietiesExportVO{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", nature='" + nature + '\'' +
                ", goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", genericName='" + genericName + '\'' +
                ", barcode='" + barcode + '\'' +
                ", goodsUnitName='" + goodsUnitName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", specification='" + specification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", managementMode=" + managementMode +
                ", managementModeName='" + managementModeName + '\'' +
                ", agreementPrice=" + agreementPrice +
                ", notaxAgreementPrice=" + notaxAgreementPrice +
                ", supplierGoodsCode='" + supplierGoodsCode + '\'' +
                ", taxRateId=" + taxRateId +
                ", taxRate=" + taxRate +
                ", soleSupplier=" + soleSupplier +
                ", soleSupplierName='" + soleSupplierName + '\'' +
                ", lastPurPrice=" + lastPurPrice +
                ", lastPurTaxRateId=" + lastPurTaxRateId +
                ", lastPurTaxRate=" + lastPurTaxRate +
                ", franchisedStoreFlag=" + franchisedStoreFlag +
                '}';
    }
}
