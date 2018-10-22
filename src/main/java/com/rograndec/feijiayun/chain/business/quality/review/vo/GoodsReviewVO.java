package com.rograndec.feijiayun.chain.business.quality.review.vo;

import com.rograndec.feijiayun.chain.common.constant.GoodsQualityPeriodUnit;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GoodsReviewVO implements Serializable {


    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 条形码
     */
    @ApiModelProperty(value = "条形码")
    private String barcode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value = "后台用")
    private Integer businessVariety;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 产地
     */
    @ApiModelProperty(value = "产地")
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    /**
     * 有效期至
     */
    @ApiModelProperty(value = "有效期至")
    private Date validUntil;

    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value = "国产/进口描述")
    private String domesticImportDesc;


    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value = "后台用")
    private Integer domesticImport;

    /**
     * 经营范围名称
     */
    @ApiModelProperty(value = "经营范围名称")
    private String managementScopeName;

    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value = "特殊管理药品描述")
    private String specialDrugDesc;

    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value = "后台用")
    private Integer specialDrug;

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value = "专管药品描述")
    private String inChargeDrugDesc;


    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value = "后台用")
    private Integer inChargeDrug;
    /**
     * 限购数量
     */
    @ApiModelProperty(value = "限购数量")
    private BigDecimal limitQuantity;

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty(value = "贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private String storageTempDesc;

    /**
     *
     */
    @ApiModelProperty(value = "后台用")
    private Integer storageTemp;

    /**
     * 贮藏条件名称
     */

    @ApiModelProperty(value = "贮藏条件名称")
    private String storageConditionName;

    /**
     * 保质期描述
     */
    @ApiModelProperty(value = "保质期描述")
    private String qualityPeriodDesc;

    /**
     * 商品属性描述
     */
    @ApiModelProperty(value = "商品属性描述")
    private String goodsAttributeDesc;


    @ApiModelProperty(value = "后台用")
    private Integer goodsAttribute;

    /**
     * 商品属性-是否为处方药，0：非处方药，1：处方药
     */
    @ApiModelProperty(value = "后台用")
    private Integer prescriptionDrug;

    /**
     * 非处方药类别：0-甲类；1-乙类；
     */
    @ApiModelProperty(value = "后台用")
    private Integer otcType;

    /**
     * 品种类别为3-化妆品，商品属性为1-特殊用途化妆品时，化妆品类别［0-育发；1-染发；2-烫发；3-脱毛；4-美乳；5-健美；6-除臭；7-祛斑；8-防嗮］
     */
    @ApiModelProperty(value = "后台用")
    private Integer cosmetics;

    /**
     * 保质期
     */
    @ApiModelProperty(value = "后台用")
    private Integer qualityPeriod;

    /**
     * 保质期单位
     */
    @ApiModelProperty(value = "后台用")
    private Integer qualityPeriodUnit;


    @ApiModelProperty(value = "后台用")
    private Integer spiritDrugType;

    /**
     *  含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）
     */
    private Integer formulationType;


    public Integer getSpiritDrugType() {
        return spiritDrugType;
    }

    public void setSpiritDrugType(Integer spiritDrugType) {
        this.spiritDrugType = spiritDrugType;
    }

    public Integer getFormulationType() {
        return formulationType;
    }

    public void setFormulationType(Integer formulationType) {
        this.formulationType = formulationType;
    }

    public Integer getQualityPeriod() {
        return qualityPeriod;
    }

    public void setQualityPeriod(Integer qualityPeriod) {
        this.qualityPeriod = qualityPeriod;
    }

    public Integer getPrescriptionDrug() {
        return prescriptionDrug;
    }

    public void setPrescriptionDrug(Integer prescriptionDrug) {
        this.prescriptionDrug = prescriptionDrug;
    }

    public Integer getOtcType() {
        return otcType;
    }

    public void setOtcType(Integer otcType) {
        this.otcType = otcType;
    }

    public Integer getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(Integer cosmetics) {
        this.cosmetics = cosmetics;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public Long getDosageId() {
        return dosageId;
    }

    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public String getDomesticImportDesc() {
        return domesticImportDesc;
    }

    public void setDomesticImportDesc(String domesticImportDesc) {
        this.domesticImportDesc = domesticImportDesc;
    }

    public String getManagementScopeName() {
        return managementScopeName;
    }

    public void setManagementScopeName(String managementScopeName) {
        this.managementScopeName = managementScopeName;
    }

    public String getSpecialDrugDesc() {
        return specialDrugDesc;
    }

    public void setSpecialDrugDesc(String specialDrugDesc) {
        this.specialDrugDesc = specialDrugDesc;
    }

    public String getInChargeDrugDesc() {
        return inChargeDrugDesc;
    }

    public void setInChargeDrugDesc(String inChargeDrugDesc) {
        this.inChargeDrugDesc = inChargeDrugDesc;
    }

    public BigDecimal getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(BigDecimal limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public String getStorageTempDesc() {
        return storageTempDesc;
    }

    public void setStorageTempDesc(String storageTempDesc) {
        this.storageTempDesc = storageTempDesc;
    }

    public String getStorageConditionName() {
        return storageConditionName;
    }

    public void setStorageConditionName(String storageConditionName) {
        this.storageConditionName = storageConditionName;
    }

    public String getQualityPeriodDesc() {
        if (qualityPeriod == null){
            return "";
        }
        return qualityPeriod + GoodsQualityPeriodUnit.getName(qualityPeriodUnit);
    }

    public void setQualityPeriodDesc(String qualityPeriodDesc) {
        this.qualityPeriodDesc = qualityPeriodDesc;
    }

    public String getGoodsAttributeDesc() {
        return goodsAttributeDesc;
    }

    public void setGoodsAttributeDesc(String goodsAttributeDesc) {
        this.goodsAttributeDesc = goodsAttributeDesc;
    }

    public Integer getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(Integer businessVariety) {
        this.businessVariety = businessVariety;
    }

    public Integer getDomesticImport() {
        return domesticImport;
    }

    public void setDomesticImport(Integer domesticImport) {
        this.domesticImport = domesticImport;
    }

    public Integer getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
    }

    public Integer getInChargeDrug() {
        return inChargeDrug;
    }

    public void setInChargeDrug(Integer inChargeDrug) {
        this.inChargeDrug = inChargeDrug;
    }

    public Integer getStorageTemp() {
        return storageTemp;
    }

    public void setStorageTemp(Integer storageTemp) {
        this.storageTemp = storageTemp;
    }

    public Integer getGoodsAttribute() {
        return goodsAttribute;
    }

    public void setGoodsAttribute(Integer goodsAttribute) {
        this.goodsAttribute = goodsAttribute;
    }

    public Integer getQualityPeriodUnit() {
        return qualityPeriodUnit;
    }

    public void setQualityPeriodUnit(Integer qualityPeriodUnit) {
        this.qualityPeriodUnit = qualityPeriodUnit;
    }
}
