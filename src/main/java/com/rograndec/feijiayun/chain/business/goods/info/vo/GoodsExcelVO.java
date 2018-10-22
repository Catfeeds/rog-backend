package com.rograndec.feijiayun.chain.business.goods.info.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by ST on 2017/9/7.
 */
public class GoodsExcelVO implements Serializable {


    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value="品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private String businessVariety;

    /**
     * 商品分类
     */
    @ApiModelProperty(value="商品分类")
    private String category;

    /**
     * 商品编码
     */
    @ApiModelProperty(value="商品编码")
    private String code;

    /**
     * 条形码
     */
    @ApiModelProperty(value=" 条形码")
    private String barcode;

    /**
     * 通用名称
     */
    @ApiModelProperty(value="通用名称")
    private String genericName;

    /**
     * 商品名称
     */
    @ApiModelProperty(value="商品名称")
    private String name;

    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片）
     */
    @ApiModelProperty(value="商品属性（0-成药；1-中药材；2-中药饮片）-- 商品属性-是否为处方药，0：非处方药，1：处方药--非处方药类别：0-甲类；1-乙类；")

    private String goodsAttribute;


    /**
     * 原商品编码
     */
    @ApiModelProperty(value="原商品编码")
    private String oldCode;

    /**
     * 国家本位码
     */
    @ApiModelProperty(value="国家本位码")
    private String nationalStandardCode;


    /**
     * 剂型名称
     */
    @ApiModelProperty(value="剂型名称")
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(value="规格")
    private String specification;


    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(value="库存计量单位名称")
    private String unitName;

    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value="国产/进口（0-国产；1-进口）")
    private String domesticImport;


    /**
     * 生产厂商
     */
    @ApiModelProperty(value="生产厂商")
    private String manufacturer;

    /**
     * 产地
     */
    @ApiModelProperty(value="产地")
    private String place;

    /**
     * 批准文号
     */
    @ApiModelProperty(value="批准文号")
    private String approvalNumber;

    /**
     * 有效期至
     */
    @ApiModelProperty(value="有效期至")
    private String validUntil;


    /**
     * 经营范围名称
     */
    @ApiModelProperty(value=" 经营范围名称")
    private String managementScopeName;


    /**
     * 验收分类名称
     */
    @ApiModelProperty(value="验收分类名称")
    private String checkTypeName;

    /**
     * 保质期
     */
    @ApiModelProperty(value="保质期")
    private String qualityPeriod;

    /**
     * 保质期单位（0-日；1-月；2-年）
     */
    @ApiModelProperty(value="保质期单位（0-日；1-月；2-年）")
    private String qualityPeriodUnit;

    /**
     *
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value="特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品-精神药品分类（0-一类；1-二类））")
    private String specialDrug;


    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value="专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）-含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
    private String inChargeDrug;

    /**
     * 限购数量
     */
    @ApiModelProperty(value="限购数量")
    private String limitQuantity;

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty(value=" 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private String storageTemp;

    /**
     * 首营品种（0-否；1-是）
     */
    @ApiModelProperty(value="营品种（0-否；1-是）")
    private String first;


    /**
     * 注册商标
     */
    @ApiModelProperty(value="注册商标")
    private String registeredTrademark;

    /**
     * 品牌
     */
    @ApiModelProperty(value="品牌")
    private String brand;

    /**
     * 季节
     */
    @ApiModelProperty(value="季节")
    private String season;

    /**
     * 等级
     */
    @ApiModelProperty(value="等级")
    private String grade;

    /**
     * 是否为医保药品（0-否；1-是）
     */
    @ApiModelProperty(value="是否为医保药品（0-否；1-是）")
    private String  medicalInsurance;

    /**
     * 医保类别（0-国家医保；1-地方医保）
     */
    @ApiModelProperty(value="医保类别（0-国家医保；1-地方医保）")
    private String  medicalInsuranceType;

    /**
     * 医保号
     */
    @ApiModelProperty(value="医保号")
    private String medicalInsuranceCode;




    /**
     * 商品性质（0-普通商品；1-拆零商品；2-组装商品）
     */
    @ApiModelProperty(value="商品性质（0-普通商品；1-拆零商品；2-组装商品）")
    private String  goodsNature;

    /**
     * 配置标识（0-必备；1-可选）
     */
    @ApiModelProperty(value="配置标识（0-必备；1-可选）")
    private String  configurationFlag;

    /**
     * 配货标识（0-普通；1-首推）
     */
    @ApiModelProperty(value="配货标识（（0-普通；1-首推")
    private String  distrFlag;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value=" 状态（0-禁用；1-启用）")
    private String status;

    /**
     * 标记（0-作废；1-正常）
     */
    @ApiModelProperty(value="标记（0-作废；1-正常）")
    private String validFlag;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 申请人员
     */
    @ApiModelProperty(value="申请人员")
    private String applicantName;

    /**
     * 申请日期
     */
    @ApiModelProperty(value="申请日期")
    private String applicationTime;

    /**
     * 申请意见
     */
    @ApiModelProperty(value="申请意见")
    private String applicationOpinion;

/////////////////////////商品业务信息/////////////////////////////////

    /**
     * 进项税（%）
     */
    @ApiModelProperty(value="进项税（%）")
    private String purchaseTaxRate;

    /**
     * 销项税（%）
     */
    @ApiModelProperty(value="销项税（%）")
    private String saleTaxRate;

    /**
     * 经营方式（0-购销；1-实销实结）
     */
    @ApiModelProperty(value="经营方式（0-购销；1-实销实结）")
    private String managementMode;

    /**
     * 积分商品（0-否；1-是）
     */
    @ApiModelProperty(value=" 积分商品（0-否；1-是）")
    private String integralGoods;

    /**
     * 积分倍数
     */
    @ApiModelProperty(value="积分倍数")
    private String integralMultiple;

    /**
     * 特价商品（0-否；1-是）
     */
    @ApiModelProperty(value=" 特价商品（0-否；1-是）")
    private String bargainGoods;

    /**
     * 提成商品（0-否；1-是）
     */
    @ApiModelProperty(value="提成商品（0-否；1-是）")
    private String commissionGoods;

    /**
     * 零售基价
     */
    @ApiModelProperty(value="零售基价")
    private String retailPrice;



    /**
     * 配货基价
     */
    @ApiModelProperty(value="配货基价")
    private String distrPrice;

    /**
     *  配货税率（%）
     */
    @ApiModelProperty(value="配货税率")
    private String distrTaxRate;

    //销售定价策略：0总部统一定价,1允许门店自主定价.总部添加的商品默
//    认总部统一定价， 加盟店添加的商品默认允许门店自主定价且只读

    private String pricingPolicy;


    /////////////////////////储藏和/////////////////////////////////
    /**
     * 中包装数量
     */
    @ApiModelProperty(value="中包装数量")
    private String inbagQuantity;

    /**
     * 大包装数量
     */
    @ApiModelProperty(value="大包装数量")
    private String bigbagQuantity;



    /**
     * 最小采购批量
     */
    @ApiModelProperty(value="最小采购批量")
    private String minimumPurchasingBatch;

    /**
     * 最小配货批量
     */
    @ApiModelProperty(value="最小配货批量")
    private String minimumDistributionBatch;

    /**
     * 近效期
     */
    @ApiModelProperty(value="近效期")
    private String nearEffectPeriod;

    /**
     * 近效期单位（0-天；1-月；2-年）
     */
    @ApiModelProperty(value="近效期单位（0-天；1-月；2-年）")
    private String nearEffectPeriodUnit;

    /**
     * 滞销周期
     */
    @ApiModelProperty(value="滞销周期")
    private String unsalableCycle;

    /**
     * 滞销周期单位（0-天；1-月；2-年）
     */
    @ApiModelProperty(value="滞销周期单位（0-天；1-月；2-年）")
    private String unsalableCycleUnit;

    /**
     * 养护类型（0-常规养护；1-重点养护）
     */
    @ApiModelProperty(value="养护类型（0-常规养护；1-重点养护）")
    private String maintenanceType;

    /**
     * 养护周期
     */
    @ApiModelProperty(value="养护周期")
    private String maintenanceCycle;

    /**
     * 养护周期单位（0-天；1-月；2-年）
     */
    @ApiModelProperty(value="养护周期单位（0-天；1-月；2-年）")
    private String maintenanceCycleUnit;

    /**
     * 配送方式（0-配送中心配送；1-委托配送）
     */
    @ApiModelProperty(value="配送方式（0-配送中心配送；1-委托配送）")
    private String deliveryMode;

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public String getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(String businessVariety) {
        this.businessVariety = businessVariety;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoodsAttribute() {
        return goodsAttribute;
    }

    public void setGoodsAttribute(String goodsAttribute) {
        this.goodsAttribute = goodsAttribute;
    }

    public String getOldCode() {
        return oldCode;
    }

    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }

    public String getNationalStandardCode() {
        return nationalStandardCode;
    }

    public void setNationalStandardCode(String nationalStandardCode) {
        this.nationalStandardCode = nationalStandardCode;
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDomesticImport() {
        return domesticImport;
    }

    public void setDomesticImport(String domesticImport) {
        this.domesticImport = domesticImport;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public String getManagementScopeName() {
        return managementScopeName;
    }

    public void setManagementScopeName(String managementScopeName) {
        this.managementScopeName = managementScopeName;
    }

    public String getCheckTypeName() {
        return checkTypeName;
    }

    public void setCheckTypeName(String checkTypeName) {
        this.checkTypeName = checkTypeName;
    }

    public String getQualityPeriod() {
        return qualityPeriod;
    }

    public void setQualityPeriod(String qualityPeriod) {
        this.qualityPeriod = qualityPeriod;
    }

    public String getQualityPeriodUnit() {
        return qualityPeriodUnit;
    }

    public void setQualityPeriodUnit(String qualityPeriodUnit) {
        this.qualityPeriodUnit = qualityPeriodUnit;
    }

    public String getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(String specialDrug) {
        this.specialDrug = specialDrug;
    }

    public String getInChargeDrug() {
        return inChargeDrug;
    }

    public void setInChargeDrug(String inChargeDrug) {
        this.inChargeDrug = inChargeDrug;
    }

    public String getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(String limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public String getStorageTemp() {
        return storageTemp;
    }

    public void setStorageTemp(String storageTemp) {
        this.storageTemp = storageTemp;
    }

    public String getRegisteredTrademark() {
        return registeredTrademark;
    }

    public void setRegisteredTrademark(String registeredTrademark) {
        this.registeredTrademark = registeredTrademark;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(String medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public String getMedicalInsuranceType() {
        return medicalInsuranceType;
    }

    public void setMedicalInsuranceType(String medicalInsuranceType) {
        this.medicalInsuranceType = medicalInsuranceType;
    }

    public String getMedicalInsuranceCode() {
        return medicalInsuranceCode;
    }

    public void setMedicalInsuranceCode(String medicalInsuranceCode) {
        this.medicalInsuranceCode = medicalInsuranceCode;
    }

    public String getGoodsNature() {
        return goodsNature;
    }

    public void setGoodsNature(String goodsNature) {
        this.goodsNature = goodsNature;
    }

    public String getConfigurationFlag() {
        return configurationFlag;
    }

    public void setConfigurationFlag(String configurationFlag) {
        this.configurationFlag = configurationFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getApplicationOpinion() {
        return applicationOpinion;
    }

    public void setApplicationOpinion(String applicationOpinion) {
        this.applicationOpinion = applicationOpinion;
    }

    public String getPurchaseTaxRate() {
        return purchaseTaxRate;
    }

    public void setPurchaseTaxRate(String purchaseTaxRate) {
        this.purchaseTaxRate = purchaseTaxRate;
    }

    public String getSaleTaxRate() {
        return saleTaxRate;
    }

    public void setSaleTaxRate(String saleTaxRate) {
        this.saleTaxRate = saleTaxRate;
    }

    public String getManagementMode() {
        return managementMode;
    }

    public void setManagementMode(String managementMode) {
        this.managementMode = managementMode;
    }

    public String getIntegralGoods() {
        return integralGoods;
    }

    public void setIntegralGoods(String integralGoods) {
        this.integralGoods = integralGoods;
    }

    public String getIntegralMultiple() {
        return integralMultiple;
    }

    public void setIntegralMultiple(String integralMultiple) {
        this.integralMultiple = integralMultiple;
    }

    public String getBargainGoods() {
        return bargainGoods;
    }

    public void setBargainGoods(String bargainGoods) {
        this.bargainGoods = bargainGoods;
    }

    public String getCommissionGoods() {
        return commissionGoods;
    }

    public void setCommissionGoods(String commissionGoods) {
        this.commissionGoods = commissionGoods;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getDistrPrice() {
        return distrPrice;
    }

    public void setDistrPrice(String distrPrice) {
        this.distrPrice = distrPrice;
    }

    public String getDistrTaxRate() {
        return distrTaxRate;
    }

    public void setDistrTaxRate(String distrTaxRate) {
        this.distrTaxRate = distrTaxRate;
    }

    public String getInbagQuantity() {
        return inbagQuantity;
    }

    public void setInbagQuantity(String inbagQuantity) {
        this.inbagQuantity = inbagQuantity;
    }

    public String getBigbagQuantity() {
        return bigbagQuantity;
    }

    public void setBigbagQuantity(String bigbagQuantity) {
        this.bigbagQuantity = bigbagQuantity;
    }

    public String getMinimumPurchasingBatch() {
        return minimumPurchasingBatch;
    }

    public void setMinimumPurchasingBatch(String minimumPurchasingBatch) {
        this.minimumPurchasingBatch = minimumPurchasingBatch;
    }

    public String getMinimumDistributionBatch() {
        return minimumDistributionBatch;
    }

    public void setMinimumDistributionBatch(String minimumDistributionBatch) {
        this.minimumDistributionBatch = minimumDistributionBatch;
    }

    public String getNearEffectPeriod() {
        return nearEffectPeriod;
    }

    public void setNearEffectPeriod(String nearEffectPeriod) {
        this.nearEffectPeriod = nearEffectPeriod;
    }

    public String getNearEffectPeriodUnit() {
        return nearEffectPeriodUnit;
    }

    public void setNearEffectPeriodUnit(String nearEffectPeriodUnit) {
        this.nearEffectPeriodUnit = nearEffectPeriodUnit;
    }

    public String getUnsalableCycle() {
        return unsalableCycle;
    }

    public void setUnsalableCycle(String unsalableCycle) {
        this.unsalableCycle = unsalableCycle;
    }

    public String getUnsalableCycleUnit() {
        return unsalableCycleUnit;
    }

    public void setUnsalableCycleUnit(String unsalableCycleUnit) {
        this.unsalableCycleUnit = unsalableCycleUnit;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getMaintenanceCycle() {
        return maintenanceCycle;
    }

    public void setMaintenanceCycle(String maintenanceCycle) {
        this.maintenanceCycle = maintenanceCycle;
    }

    public String getMaintenanceCycleUnit() {
        return maintenanceCycleUnit;
    }

    public void setMaintenanceCycleUnit(String maintenanceCycleUnit) {
        this.maintenanceCycleUnit = maintenanceCycleUnit;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getDistrFlag() {
        return distrFlag;
    }

    public void setDistrFlag(String distrFlag) {
        this.distrFlag = distrFlag;
    }

    public String getPricingPolicy() {
        return pricingPolicy;
    }

    public void setPricingPolicy(String pricingPolicy) {
        this.pricingPolicy = pricingPolicy;
    }
}