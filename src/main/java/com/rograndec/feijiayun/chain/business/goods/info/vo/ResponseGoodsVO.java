package com.rograndec.feijiayun.chain.business.goods.info.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品信息查询list返回实体VO
 * Created by ST on 2017/9/5.
 */
public class ResponseGoodsVO implements Serializable{

    @ApiModelProperty("商品id")
    private Long id;
    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty("品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private Integer businessVariety;

    /**
     * 商品分类ID
     */
    @ApiModelProperty("商品分类ID")
    private Long categoryId;

    /**
     * 商品分类名称
     */
    @ApiModelProperty("商品分类名称")
    private String categoryName;

    /**
     * 商品编码
     */
    @ApiModelProperty("商品编码")
    private String code;

    /**
     * 条形码
     */
    @ApiModelProperty("条形码")
    private String barcode;

    /**
     * 通用名称
     */
    @ApiModelProperty("通用名称")
    private String genericName;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String name;

    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片）
     */
    @ApiModelProperty("商品属性（0-成药；1-中药材；2-中药饮片）")
    private Integer goodsAttribute;

    /**
     * 商品属性-是否为处方药，0：非处方药，1：处方药
     */
    @ApiModelProperty("商品属性-是否为处方药，0：非处方药，1：处方药")
    private Integer prescriptionDrug;

    /**
     * 非处方药类别：0-甲类；1-乙类；
     */
    @ApiModelProperty("非处方药类别：0-甲类；1-乙类；")
    private Integer otcType;

    /**
     * 检索码（型如“通用名检索码—商品名检索码”）
     */
    @ApiModelProperty(" 检索码（型如“通用名检索码—商品名检索码”）")
    private String pinyinCode;


    /**
     * 原商品编码
     */
    @ApiModelProperty("原商品编码")
    private String oldCode;

    /**
     * 国家本位码
     */
    @ApiModelProperty("国家本位码")
    private String nationalStandardCode;

    /**
     * 剂型ID
     */
    @ApiModelProperty("剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty("剂型名称")
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty("规格")
    private String specification;

    /**
     * 库存计量单位ID
     */
    @ApiModelProperty("库存计量单位ID")
    private Long unitId;

    /**
     * 库存计量单位名称
     */
    @ApiModelProperty("库存计量单位名称")
    private String unitName;

    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty("国产/进口（0-国产；1-进口）")
    private Integer domesticImport;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty("生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty("生产厂商")
    private String manufacturer;

    /**
     * 产地
     */
    @ApiModelProperty("产地")
    private String place;

    /**
     * 批准文号
     */
    @ApiModelProperty("批准文号")
    private String approvalNumber;

    /**
     * 有效期至
     */
    @ApiModelProperty("有效期至")
    private Date validUntil;

    /**
     * 经营范围ID
     */
    @ApiModelProperty("经营范围ID")
    private Long managementScopeId;

    /**
     * 经营范围名称
     */
    @ApiModelProperty("经营范围名称")
    private String managementScopeName;

    /**
     * 验收分类ID
     */
    @ApiModelProperty(" 验收分类ID")
    private Long checkTypeId;

    /**
     * 验收分类名称
     */
    @ApiModelProperty("验收分类名称")
    private String checkTypeName;

    /**
     * 保质期
     */
    @ApiModelProperty("保质期")
    private Integer qualityPeriod;

    /**
     * 保质期单位（0-日；1-月；2-年）
     */
    @ApiModelProperty("保质期单位（0-日；1-月；2-年）")
    private Integer qualityPeriodUnit;

    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty("特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private Integer specialDrug;

    /**
     * 精神药品分类（0-一类；1-二类）
     */
    @ApiModelProperty(" 精神药品分类（0-一类；1-二类）")
    private Integer spiritDrugType;


    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty("专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private Integer inChargeDrug;

    /**
     *  含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）
     */
    @ApiModelProperty("含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
    private Integer formulationType;




    /**
     * 注册商标
     */
    @ApiModelProperty("注册商标")
    private String registeredTrademark;

    /**
     * 品牌
     */
    @ApiModelProperty("品牌")
    private String brand;

    /**
     * 季节
     */
    @ApiModelProperty("季节")
    private String season;

    /**
     * 等级
     */
    @ApiModelProperty("等级")
    private String grade;

    /**
     * 是否为医保药品（0-否；1-是）
     */
    @ApiModelProperty("是否为医保药品（0-否；1-是）")
    private Integer medicalInsurance;

    /**
     * 医保类别（0-国家医保；1-地方医保）
     */
    @ApiModelProperty("医保类别（0-国家医保；1-地方医保）")
    private Integer medicalInsuranceType;

    /**
     * 医保号
     */
    @ApiModelProperty("医保号")
    private String medicalInsuranceCode;

    /**
     * 首营品种（0-否；1-是）
     */
    @ApiModelProperty("首营品种（0-否；1-是）")
    private Integer first;

    /**
     * 首营品种编号
     */
    @ApiModelProperty("首营品种编号")
    private String firstGoodsCode;

    /**
     * 申请人ID
     */
    @ApiModelProperty("申请人ID")
    private Long applicantId;

    /**
     * 申请人员编码
     */
    @ApiModelProperty("申请人员编码")
    private String applicantCode;

    /**
     * 申请人员
     */
    @ApiModelProperty("申请人员")
    private String applicantName;

    /**
     * 申请日期
     */
    @ApiModelProperty("申请日期")
    private Date applicationTime;

    /**
     * 申请意见
     */
    @ApiModelProperty("申请意见")
    private String applicationOpinion;



    /**
     * 配置标识（0-必备；1-可选）
     */
    @ApiModelProperty("配置标识（0-必备；1-可选）")
    private Integer configurationFlag;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty("状态（0-禁用；1-启用）")
    private Integer status;

    /**
     * 标记（0-作废；1-正常）
     */
    @ApiModelProperty("标记（0-作废；1-正常）")
    private Integer validFlag;

    /**
     * 审核状态（0-全部；1-正常；2-待审核；3-审核被驳回）
     */
    @ApiModelProperty("审核状态（0-全部；1-正常；2-待审核；3-审核被驳回）")
    private Integer approveStatus;



    /**
     * 进项税（%）
     */
    @ApiModelProperty("进项税（%）")
    private BigDecimal purchaseTaxRate;

    /**
     * 销售税（%）
     */
    @ApiModelProperty("销售税（%）")
    private BigDecimal saleTaxRate;

    /**
     * 经营方式（0-购销；1-实销实结）
     */
    @ApiModelProperty("经营方式（0-购销；1-实销实结）")
    private Integer managementMode;

    /**
     * 积分商品（0-否；1-是）
     */
    @ApiModelProperty("积分商品（0-否；1-是）")
    private Integer integralGoods;

    /**
     * 积分倍数
     */
    @ApiModelProperty("积分倍数")
    private BigDecimal integralMultiple;

    /**
     * 特价商品（0-否；1-是）
     */
    @ApiModelProperty("特价商品（0-否；1-是）")
    private Integer bargainGoods;



    /**
     * 零售基价
     */
    @ApiModelProperty("零售基价")
    private BigDecimal retailPrice;

    /**
     * 会员基价
     */
    @ApiModelProperty("会员基价")
    private BigDecimal memberPrice;

    /**
     * 配货基价
     */
    @ApiModelProperty("配货基价")
    private BigDecimal distrPrice;




    /**
     * 中包装数量
     */
    @ApiModelProperty("中包装数量")
    private BigDecimal inbagQuantity;

    /**
     * 大包装数量
     */
    @ApiModelProperty("大包装数量")
    private BigDecimal bigbagQuantity;



    /**
     * 配送方式（0-配送中心配送；1-委托配送）
     */
    @ApiModelProperty("配送方式（0-配送中心配送；1-委托配送）")
    private Integer deliveryMode;



    /**
     * 最小采购批量
     */
    @ApiModelProperty("最小采购批量")
    private BigDecimal minimumPurchasingBatch;

    /**
     * 最小配货批量
     */
    @ApiModelProperty("最小配货批量")
    private BigDecimal minimumDistributionBatch;

    /**
     * 近效期
     */
    @ApiModelProperty("近效期")
    private Integer nearEffectPeriod;

    /**
     * 近效期单位（0-天；1-月；2-年）
     */
    @ApiModelProperty(" 近效期单位（0-天；1-月；2-年）")
    private Integer nearEffectPeriodUnit;

    /**
     * 滞销周期
     */
    @ApiModelProperty("滞销周期")
    private Integer unsalableCycle;

    /**
     * 滞销周期单位（0-天；1-月；2-年）
     */
    @ApiModelProperty("滞销周期单位（0-天；1-月；2-年）")
    private Integer unsalableCycleUnit;

    /**
     * 养护类型（0-常规养护；1-重点养护）
     */
    @ApiModelProperty("养护类型（0-常规养护；1-重点养护）")
    private Integer maintenanceType;

    /**
     * 养护周期
     */
    @ApiModelProperty("养护周期")
    private Integer maintenanceCycle;

    /**
     * 养护周期单位（0-天；1-月；2-年）
     */
    @ApiModelProperty("养护周期单位（0-天；1-月；2-年）")
    private Integer maintenanceCycleUnit;

    /**
     * 养护标准（养护措施ID集合）
     */
    @ApiModelProperty("养护标准（养护措施ID集合）")
    private String maintenanceMeasureIds;


    /**
     * 限购数量
     */
    @ApiModelProperty("限购数量")
    private BigDecimal limitQuantity;

    /**
     * 贮藏条件名称
     */
    @ApiModelProperty("贮藏条件名称")
    private String storageConditionName;

    /**
     * 商品性质（0-普通商品；1-拆零商品；2-组装商品）
     */
    @ApiModelProperty("商品性质（0-普通商品；1-拆零商品；2-组装商品）")
    private Integer goodsNature;











    public Integer getGoodsNature() {
        return goodsNature;
    }

    public void setGoodsNature(Integer goodsNature) {
        this.goodsNature = goodsNature;
    }

    public String getStorageConditionName() {
        return storageConditionName;
    }

    public void setStorageConditionName(String storageConditionName) {
        this.storageConditionName = storageConditionName;
    }


    public Integer getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(Integer businessVariety) {
        this.businessVariety = businessVariety;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getGoodsAttribute() {
        return goodsAttribute;
    }

    public void setGoodsAttribute(Integer goodsAttribute) {
        this.goodsAttribute = goodsAttribute;
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

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
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

    public Integer getDomesticImport() {
        return domesticImport;
    }

    public void setDomesticImport(Integer domesticImport) {
        this.domesticImport = domesticImport;
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

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public Long getManagementScopeId() {
        return managementScopeId;
    }

    public void setManagementScopeId(Long managementScopeId) {
        this.managementScopeId = managementScopeId;
    }

    public String getManagementScopeName() {
        return managementScopeName;
    }

    public void setManagementScopeName(String managementScopeName) {
        this.managementScopeName = managementScopeName;
    }

    public Long getCheckTypeId() {
        return checkTypeId;
    }

    public void setCheckTypeId(Long checkTypeId) {
        this.checkTypeId = checkTypeId;
    }

    public String getCheckTypeName() {
        return checkTypeName;
    }

    public void setCheckTypeName(String checkTypeName) {
        this.checkTypeName = checkTypeName;
    }

    public Integer getQualityPeriod() {
        return qualityPeriod;
    }

    public void setQualityPeriod(Integer qualityPeriod) {
        this.qualityPeriod = qualityPeriod;
    }

    public Integer getQualityPeriodUnit() {
        return qualityPeriodUnit;
    }

    public void setQualityPeriodUnit(Integer qualityPeriodUnit) {
        this.qualityPeriodUnit = qualityPeriodUnit;
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

    public Integer getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(Integer medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public Integer getMedicalInsuranceType() {
        return medicalInsuranceType;
    }

    public void setMedicalInsuranceType(Integer medicalInsuranceType) {
        this.medicalInsuranceType = medicalInsuranceType;
    }

    public String getMedicalInsuranceCode() {
        return medicalInsuranceCode;
    }

    public void setMedicalInsuranceCode(String medicalInsuranceCode) {
        this.medicalInsuranceCode = medicalInsuranceCode;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public String getFirstGoodsCode() {
        return firstGoodsCode;
    }

    public void setFirstGoodsCode(String firstGoodsCode) {
        this.firstGoodsCode = firstGoodsCode;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicantCode() {
        return applicantCode;
    }

    public void setApplicantCode(String applicantCode) {
        this.applicantCode = applicantCode;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getApplicationOpinion() {
        return applicationOpinion;
    }

    public void setApplicationOpinion(String applicationOpinion) {
        this.applicationOpinion = applicationOpinion;
    }

    public Integer getConfigurationFlag() {
        return configurationFlag;
    }

    public void setConfigurationFlag(Integer configurationFlag) {
        this.configurationFlag = configurationFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public BigDecimal getPurchaseTaxRate() {
        return purchaseTaxRate;
    }

    public void setPurchaseTaxRate(BigDecimal purchaseTaxRate) {
        this.purchaseTaxRate = purchaseTaxRate;
    }

    public BigDecimal getSaleTaxRate() {
        return saleTaxRate;
    }

    public void setSaleTaxRate(BigDecimal saleTaxRate) {
        this.saleTaxRate = saleTaxRate;
    }

    public Integer getManagementMode() {
        return managementMode;
    }

    public void setManagementMode(Integer managementMode) {
        this.managementMode = managementMode;
    }

    public Integer getIntegralGoods() {
        return integralGoods;
    }

    public void setIntegralGoods(Integer integralGoods) {
        this.integralGoods = integralGoods;
    }

    public BigDecimal getIntegralMultiple() {
        return integralMultiple;
    }

    public void setIntegralMultiple(BigDecimal integralMultiple) {
        this.integralMultiple = integralMultiple;
    }

    public Integer getBargainGoods() {
        return bargainGoods;
    }

    public void setBargainGoods(Integer bargainGoods) {
        this.bargainGoods = bargainGoods;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(BigDecimal memberPrice) {
        this.memberPrice = memberPrice;
    }

    public BigDecimal getDistrPrice() {
        return distrPrice;
    }

    public void setDistrPrice(BigDecimal distrPrice) {
        this.distrPrice = distrPrice;
    }

    public BigDecimal getInbagQuantity() {
        return inbagQuantity;
    }

    public void setInbagQuantity(BigDecimal inbagQuantity) {
        this.inbagQuantity = inbagQuantity;
    }

    public BigDecimal getBigbagQuantity() {
        return bigbagQuantity;
    }

    public void setBigbagQuantity(BigDecimal bigbagQuantity) {
        this.bigbagQuantity = bigbagQuantity;
    }

    public Integer getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Integer deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public BigDecimal getMinimumPurchasingBatch() {
        return minimumPurchasingBatch;
    }

    public void setMinimumPurchasingBatch(BigDecimal minimumPurchasingBatch) {
        this.minimumPurchasingBatch = minimumPurchasingBatch;
    }

    public BigDecimal getMinimumDistributionBatch() {
        return minimumDistributionBatch;
    }

    public void setMinimumDistributionBatch(BigDecimal minimumDistributionBatch) {
        this.minimumDistributionBatch = minimumDistributionBatch;
    }

    public Integer getNearEffectPeriod() {
        return nearEffectPeriod;
    }

    public void setNearEffectPeriod(Integer nearEffectPeriod) {
        this.nearEffectPeriod = nearEffectPeriod;
    }

    public Integer getNearEffectPeriodUnit() {
        return nearEffectPeriodUnit;
    }

    public void setNearEffectPeriodUnit(Integer nearEffectPeriodUnit) {
        this.nearEffectPeriodUnit = nearEffectPeriodUnit;
    }

    public Integer getUnsalableCycle() {
        return unsalableCycle;
    }

    public void setUnsalableCycle(Integer unsalableCycle) {
        this.unsalableCycle = unsalableCycle;
    }

    public Integer getUnsalableCycleUnit() {
        return unsalableCycleUnit;
    }

    public void setUnsalableCycleUnit(Integer unsalableCycleUnit) {
        this.unsalableCycleUnit = unsalableCycleUnit;
    }

    public Integer getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(Integer maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public Integer getMaintenanceCycle() {
        return maintenanceCycle;
    }

    public void setMaintenanceCycle(Integer maintenanceCycle) {
        this.maintenanceCycle = maintenanceCycle;
    }

    public Integer getMaintenanceCycleUnit() {
        return maintenanceCycleUnit;
    }

    public void setMaintenanceCycleUnit(Integer maintenanceCycleUnit) {
        this.maintenanceCycleUnit = maintenanceCycleUnit;
    }

    public String getMaintenanceMeasureIds() {
        return maintenanceMeasureIds;
    }

    public void setMaintenanceMeasureIds(String maintenanceMeasureIds) {
        this.maintenanceMeasureIds = maintenanceMeasureIds;
    }




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
    public BigDecimal getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(BigDecimal limitQuantity) {
        this.limitQuantity = limitQuantity;
    }
}