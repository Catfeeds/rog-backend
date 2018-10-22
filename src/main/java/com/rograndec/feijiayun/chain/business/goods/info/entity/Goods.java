package com.rograndec.feijiayun.chain.business.goods.info.entity;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Goods implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 标准库ID
     */
    private Long standardLibraryId;

    /**
     * 企业（总部）ID
     */
    private Long enterpriseId;


    //企业类型ID（0-总部；1-自营店；2-加盟店）
    private Integer chainType;

    //所有企业ID
    private Long ownerId;

    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    private Integer businessVariety;

    /**
     * 商品分类ID
     */
    private Long categoryId;

    /**
     * 商品编码
     */
    private String code;

    /**
     * 条形码
     */
    private String barcode;

    /**
     * 通用名称
     */
    private String genericName;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片）
     */
    private Integer goodsAttribute;

    /**
     * 商品属性-是否为处方药，0：非处方药，1：处方药
     */
    private Integer prescriptionDrug;

    /**
     * 非处方药类别：0-甲类；1-乙类；
     */
    private Integer otcType;

    /**
     * 品种类别为3-化妆品，商品属性为1-特殊用途化妆品时，化妆品类别［0-育发；1-染发；2-烫发；3-脱毛；4-美乳；5-健美；6-除臭；7-祛斑；8-防嗮］
     */
    private Integer cosmetics;

    /**
     * 检索码（型如“通用名检索码—商品名检索码”）
     */
    private String pinyinCode;

    /**
     * MPH编码
     */
    private String mphCode;

    /**
     * 原商品编码
     */
    private String oldCode;

    /**
     * 国家本位码
     */
    private String nationalStandardCode;

    /**
     * 剂型ID
     */
    private Long dosageId;

    /**
     * 剂型名称
     */
    private String dosageName;

    /**
     * 规格
     */
    private String specification;

    /**
     * 库存计量单位ID
     */
    private Long unitId;

    /**
     * 库存计量单位名称
     */
    private String unitName;

    /**
     * 国产/进口（0-国产；1-进口）
     */
    private Integer domesticImport;

    /**
     * 生产厂商ID
     */
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    private String manufacturer;

    /**
     * 产地
     */
    private String place;

    /**
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 有效期至
     */
    private Date validUntil;

    /**
     * 经营范围ID
     */
    private Long managementScopeId;

    /**
     * 经营范围名称
     */
    private String managementScopeName;

    /**
     * 验收分类ID
     */
    private Long checkTypeId;

    /**
     * 验收分类名称
     */
    private String checkTypeName;

    /**
     * 保质期
     */
    private Integer qualityPeriod;

    /**
     * 保质期单位（0-日；1-月；2-年）
     */
    private Integer qualityPeriodUnit;

    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    private Integer specialDrug;

    /**
     * 精神药品分类（0-一类；1-二类）
     */
    private Integer spiritDrugType;



    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    private Integer inChargeDrug;
    /**
     *  含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）
     */
    private Integer formulationType;
    /**
     * 限购数量
     */
    private BigDecimal limitQuantity;

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    private Integer storageTemp;



    /**
     * 贮藏条件名称
     */
    private String storageConditionName;

    /**
     * 注册商标
     */
    private String registeredTrademark;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 季节
     */
    private String season;

    /**
     * 等级
     */
    private String grade;

    /**
     * 是否为医保药品（0-否；1-是）
     */
    private Integer medicalInsurance;

    /**
     * 医保类别（0-国家医保；1-地方医保）
     */
    private Integer medicalInsuranceType;

    /**
     * 医保号
     */
    private String medicalInsuranceCode;

    /**
     * 首营品种（0-否；1-是）
     */
    private Integer first;

    /**
     * 首营品种编号
     */
    private String firstGoodsCode;

    /**
     * 申请人ID
     */
    private Long applicantId;

    /**
     * 申请人员编码
     */
    private String applicantCode;

    /**
     * 申请人员
     */
    private String applicantName;

    /**
     * 申请日期
     */
    private Date applicationTime;

    /**
     * 申请意见
     */
    private String applicationOpinion;

    /**
     * 商品性质（0-普通商品；1-拆零商品；2-组装商品）
     */
    private Integer goodsNature;

    /**
     * 配置标识（0-必备；1-可选）
     */
    private Integer configurationFlag;

    /**
     * 配货标识（0-普通；1-首推），默认为0-普通
     */
    private Integer distrFlag;

    /**
     * 状态（0-禁用；1-启用）
     */
    private Integer status;

    /**
     * 标记（0-作废；1-正常）
     */
    private Integer validFlag;

    @ApiModelProperty(value = "审核状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回；-1-审核中）")
    private Integer approveStatus;



    /**
     * 商品图片ID
     */
    private String pictureIds;

    /**
     * 商品备注
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

    public static Map<Long,Goods> getGoodsMap(List<Goods> goods){

        return goods.stream().collect(Collectors.toMap(Goods::getId,g -> g));
    }

    public static Map<String,Goods> getGoodsCodeMap(List<Goods> goods){

        if(CollectionUtils.isEmpty(goods)) return Collections.emptyMap();
        Map<String,Goods> map = new HashMap<>();
        for(Goods g : goods){
            map.put(g.getCode(),g);
        }

        return map;
    }

    public static Map<String,Goods> getGoodsIdsMap(List<Goods> goods){

        if(CollectionUtils.isEmpty(goods)) return Collections.emptyMap();
        Map<String,Goods> map = new HashMap<>();
        for(Goods g : goods){
            map.put(g.getId()+"",g);
        }

        return map;
    }


    public static List<String> getGoodsCodes(List<Goods> goodses){

        List<String> collect = goodses.stream().map(goods -> goods.getCode()).collect(Collectors.toList());

        return collect;

    }

    public static List<Long> getGoodsCheckTypeIds(List<Goods> goodses){

        List<Long> collect = goodses.stream().map(goods -> goods.getCheckTypeId()).collect(Collectors.toList());

        return collect;

    }

    public static List<Long> getGoodsIds(List<Goods> goodses){

        List<Long> ids = new ArrayList<>();
        for(Goods goods :  goodses){
            ids.add(goods.getId());
        }

        return ids;
    }

    /**
     * saas_goods
     */

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
     * 标准库ID
     * @return standard_library_id 标准库ID
     */
    public Long getStandardLibraryId() {
        return standardLibraryId;
    }

    /**
     * 标准库ID
     * @param standardLibraryId 标准库ID
     */
    public void setStandardLibraryId(Long standardLibraryId) {
        this.standardLibraryId = standardLibraryId;
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
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     * @return business_variety 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    public Integer getBusinessVariety() {
        return businessVariety;
    }

    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     * @param businessVariety 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    public void setBusinessVariety(Integer businessVariety) {
        this.businessVariety = businessVariety;
    }

    /**
     * 商品分类ID
     * @return category_id 商品分类ID
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 商品分类ID
     * @param categoryId 商品分类ID
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 商品编码
     * @return code 商品编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 商品编码
     * @param code 商品编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 条形码
     * @return barcode 条形码
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * 条形码
     * @param barcode 条形码
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    /**
     * 通用名称
     * @return generic_name 通用名称
     */
    public String getGenericName() {
        return genericName;
    }

    /**
     * 通用名称
     * @param genericName 通用名称
     */
    public void setGenericName(String genericName) {
        this.genericName = genericName == null ? null : genericName.trim();
    }

    /**
     * 商品名称
     * @return name 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 商品名称
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片）
     * @return goods_attribute 商品属性（0-成药；1-中药材；2-中药饮片）
     */
    public Integer getGoodsAttribute() {
        return goodsAttribute;
    }

    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片）
     * @param goodsAttribute 商品属性（0-成药；1-中药材；2-中药饮片）
     */
    public void setGoodsAttribute(Integer goodsAttribute) {
        this.goodsAttribute = goodsAttribute;
    }

    /**
     * 商品属性-是否为处方药，0：非处方药，1：处方药
     * @return prescription_drug 商品属性-是否为处方药，0：非处方药，1：处方药
     */
    public Integer getPrescriptionDrug() {
        return prescriptionDrug;
    }

    /**
     * 商品属性-是否为处方药，0：非处方药，1：处方药
     * @param prescriptionDrug 商品属性-是否为处方药，0：非处方药，1：处方药
     */
    public void setPrescriptionDrug(Integer prescriptionDrug) {
        this.prescriptionDrug = prescriptionDrug;
    }

    /**
     * 非处方药类别：0-甲类；1-乙类；
     * @return otc_type 非处方药类别：0-甲类；1-乙类；
     */
    public Integer getOtcType() {
        return otcType;
    }

    /**
     * 非处方药类别：0-甲类；1-乙类；
     * @param otcType 非处方药类别：0-甲类；1-乙类；
     */
    public void setOtcType(Integer otcType) {
        this.otcType = otcType;
    }

    /**
     * 检索码（型如“通用名检索码—商品名检索码”）
     * @return pinyin_code 检索码（型如“通用名检索码—商品名检索码”）
     */
    public String getPinyinCode() {
        return pinyinCode;
    }

    /**
     * 检索码（型如“通用名检索码—商品名检索码”）
     * @param pinyinCode 检索码（型如“通用名检索码—商品名检索码”）
     */
    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode == null ? null : pinyinCode.trim();
    }



    /**
     * MPH编码
     * @return mph_code MPH编码
     */
    public String getMphCode() {
        return mphCode;
    }

    /**
     * MPH编码
     * @param mphCode MPH编码
     */
    public void setMphCode(String mphCode) {
        this.mphCode = mphCode == null ? null : mphCode.trim();
    }

    /**
     * 原商品编码
     * @return old_code 原商品编码
     */
    public String getOldCode() {
        return oldCode;
    }

    /**
     * 原商品编码
     * @param oldCode 原商品编码
     */
    public void setOldCode(String oldCode) {
        this.oldCode = oldCode == null ? null : oldCode.trim();
    }

    /**
     * 国家本位码
     * @return national_standard_code 国家本位码
     */
    public String getNationalStandardCode() {
        return nationalStandardCode;
    }

    /**
     * 国家本位码
     * @param nationalStandardCode 国家本位码
     */
    public void setNationalStandardCode(String nationalStandardCode) {
        this.nationalStandardCode = nationalStandardCode == null ? null : nationalStandardCode.trim();
    }

    /**
     * 剂型ID
     * @return dosage_id 剂型ID
     */
    public Long getDosageId() {
        return dosageId;
    }

    /**
     * 剂型ID
     * @param dosageId 剂型ID
     */
    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    /**
     * 剂型名称
     * @return dosage_name 剂型名称
     */
    public String getDosageName() {
        return dosageName;
    }

    /**
     * 剂型名称
     * @param dosageName 剂型名称
     */
    public void setDosageName(String dosageName) {
        this.dosageName = dosageName == null ? null : dosageName.trim();
    }

    /**
     * 规格
     * @return specification 规格
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * 规格
     * @param specification 规格
     */
    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    /**
     * 库存计量单位ID
     * @return unit_id 库存计量单位ID
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * 库存计量单位ID
     * @param unitId 库存计量单位ID
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * 库存计量单位名称
     * @return unit_name 库存计量单位名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 库存计量单位名称
     * @param unitName 库存计量单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    /**
     * 国产/进口（0-国产；1-进口）
     * @return domestic_import 国产/进口（0-国产；1-进口）
     */
    public Integer getDomesticImport() {
        return domesticImport;
    }

    /**
     * 国产/进口（0-国产；1-进口）
     * @param domesticImport 国产/进口（0-国产；1-进口）
     */
    public void setDomesticImport(Integer domesticImport) {
        this.domesticImport = domesticImport;
    }

    /**
     * 生产厂商ID
     * @return manufacturer_id 生产厂商ID
     */
    public Long getManufacturerId() {
        return manufacturerId;
    }

    /**
     * 生产厂商ID
     * @param manufacturerId 生产厂商ID
     */
    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * 生产厂商
     * @return manufacturer 生产厂商
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 生产厂商
     * @param manufacturer 生产厂商
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    /**
     * 产地
     * @return place 产地
     */
    public String getPlace() {
        return place;
    }

    /**
     * 产地
     * @param place 产地
     */
    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    /**
     * 批准文号
     * @return approval_number 批准文号
     */
    public String getApprovalNumber() {
        return approvalNumber;
    }

    /**
     * 批准文号
     * @param approvalNumber 批准文号
     */
    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber == null ? null : approvalNumber.trim();
    }

    /**
     * 有效期至
     * @return valid_until 有效期至
     */
    public Date getValidUntil() {
        return validUntil;
    }

    /**
     * 有效期至
     * @param validUntil 有效期至
     */
    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    /**
     * 经营范围ID
     * @return management_scope_id 经营范围ID
     */
    public Long getManagementScopeId() {
        return managementScopeId;
    }

    /**
     * 经营范围ID
     * @param managementScopeId 经营范围ID
     */
    public void setManagementScopeId(Long managementScopeId) {
        this.managementScopeId = managementScopeId;
    }

    /**
     * 经营范围名称
     * @return management_scope_name 经营范围名称
     */
    public String getManagementScopeName() {
        return managementScopeName;
    }

    /**
     * 经营范围名称
     * @param managementScopeName 经营范围名称
     */
    public void setManagementScopeName(String managementScopeName) {
        this.managementScopeName = managementScopeName == null ? null : managementScopeName.trim();
    }

    /**
     * 验收分类ID
     * @return check_type_id 验收分类ID
     */
    public Long getCheckTypeId() {
        return checkTypeId;
    }

    /**
     * 验收分类ID
     * @param checkTypeId 验收分类ID
     */
    public void setCheckTypeId(Long checkTypeId) {
        this.checkTypeId = checkTypeId;
    }

    /**
     * 验收分类名称
     * @return check_type_name 验收分类名称
     */
    public String getCheckTypeName() {
        return checkTypeName;
    }

    /**
     * 验收分类名称
     * @param checkTypeName 验收分类名称
     */
    public void setCheckTypeName(String checkTypeName) {
        this.checkTypeName = checkTypeName == null ? null : checkTypeName.trim();
    }

    /**
     * 保质期
     * @return quality_period 保质期
     */
    public Integer getQualityPeriod() {
        return qualityPeriod;
    }

    /**
     * 保质期
     * @param qualityPeriod 保质期
     */
    public void setQualityPeriod(Integer qualityPeriod) {
        this.qualityPeriod = qualityPeriod;
    }

    /**
     * 保质期单位（0-日；1-月；2-年）
     * @return quality_period_unit 保质期单位（0-日；1-月；2-年）
     */
    public Integer getQualityPeriodUnit() {
        return qualityPeriodUnit;
    }

    /**
     * 保质期单位（0-日；1-月；2-年）
     * @param qualityPeriodUnit 保质期单位（0-日；1-月；2-年）
     */
    public void setQualityPeriodUnit(Integer qualityPeriodUnit) {
        this.qualityPeriodUnit = qualityPeriodUnit;
    }

    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     * @return special_drug 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    public Integer getSpecialDrug() {
        return specialDrug;
    }

    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     * @param specialDrug 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
    }

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     * @return in_charge_drug 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    public Integer getInChargeDrug() {
        return inChargeDrug;
    }

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     * @param inChargeDrug 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    public void setInChargeDrug(Integer inChargeDrug) {
        this.inChargeDrug = inChargeDrug;
    }

    /**
     * 限购数量
     * @return limit_quantity 限购数量
     */
    public BigDecimal getLimitQuantity() {
        return limitQuantity;
    }

    /**
     * 限购数量
     * @param limitQuantity 限购数量
     */
    public void setLimitQuantity(BigDecimal limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     * @return storage_temp 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    public Integer getStorageTemp() {
        return storageTemp;
    }

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     * @param storageTemp 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    public void setStorageTemp(Integer storageTemp) {
        this.storageTemp = storageTemp;
    }



    /**
     * 贮藏条件名称
     * @return storage_condition_name 贮藏条件名称
     */
    public String getStorageConditionName() {
        return storageConditionName;
    }

    /**
     * 贮藏条件名称
     * @param storageConditionName 贮藏条件名称
     */
    public void setStorageConditionName(String storageConditionName) {
        this.storageConditionName = storageConditionName == null ? null : storageConditionName.trim();
    }

    /**
     * 注册商标
     * @return registered_trademark 注册商标
     */
    public String getRegisteredTrademark() {
        return registeredTrademark;
    }

    /**
     * 注册商标
     * @param registeredTrademark 注册商标
     */
    public void setRegisteredTrademark(String registeredTrademark) {
        this.registeredTrademark = registeredTrademark == null ? null : registeredTrademark.trim();
    }

    /**
     * 品牌
     * @return brand 品牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 品牌
     * @param brand 品牌
     */
    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    /**
     * 季节
     * @return season 季节
     */
    public String getSeason() {
        return season;
    }

    /**
     * 季节
     * @param season 季节
     */
    public void setSeason(String season) {
        this.season = season == null ? null : season.trim();
    }

    /**
     * 等级
     * @return grade 等级
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 等级
     * @param grade 等级
     */
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    /**
     * 是否为医保药品（0-否；1-是）
     * @return medical_insurance 是否为医保药品（0-否；1-是）
     */
    public Integer getMedicalInsurance() {
        return medicalInsurance;
    }

    /**
     * 是否为医保药品（0-否；1-是）
     * @param medicalInsurance 是否为医保药品（0-否；1-是）
     */
    public void setMedicalInsurance(Integer medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    /**
     * 医保类别（0-国家医保；1-地方医保）
     * @return medical_insurance _type 医保类别（0-国家医保；1-地方医保）
     */
    public Integer getMedicalInsuranceType() {
        return medicalInsuranceType;
    }

    /**
     * 医保类别（0-国家医保；1-地方医保）
     * @param medicalInsuranceType 医保类别（0-国家医保；1-地方医保）
     */
    public void setMedicalInsuranceType(Integer medicalInsuranceType) {
        this.medicalInsuranceType = medicalInsuranceType;
    }

    /**
     * 医保号
     * @return medical_insurance_code 医保号
     */
    public String getMedicalInsuranceCode() {
        return medicalInsuranceCode;
    }

    /**
     * 医保号
     * @param medicalInsuranceCode 医保号
     */
    public void setMedicalInsuranceCode(String medicalInsuranceCode) {
        this.medicalInsuranceCode = medicalInsuranceCode == null ? null : medicalInsuranceCode.trim();
    }

    /**
     * 首营品种（0-否；1-是）
     * @return first 首营品种（0-否；1-是）
     */
    public Integer getFirst() {
        return first;
    }

    /**
     * 首营品种（0-否；1-是）
     * @param first 首营品种（0-否；1-是）
     */
    public void setFirst(Integer first) {
        this.first = first;
    }

    /**
     * 首营品种编号
     * @return first_goods_code 首营品种编号
     */
    public String getFirstGoodsCode() {
        return firstGoodsCode;
    }

    /**
     * 首营品种编号
     * @param firstGoodsCode 首营品种编号
     */
    public void setFirstGoodsCode(String firstGoodsCode) {
        this.firstGoodsCode = firstGoodsCode == null ? null : firstGoodsCode.trim();
    }

    /**
     * 申请人ID
     * @return applicant_id 申请人ID
     */
    public Long getApplicantId() {
        return applicantId;
    }

    /**
     * 申请人ID
     * @param applicantId 申请人ID
     */
    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    /**
     * 申请人员编码
     * @return applicant_code 申请人员编码
     */
    public String getApplicantCode() {
        return applicantCode;
    }

    /**
     * 申请人员编码
     * @param applicantCode 申请人员编码
     */
    public void setApplicantCode(String applicantCode) {
        this.applicantCode = applicantCode == null ? null : applicantCode.trim();
    }

    /**
     * 申请人员
     * @return applicant_name 申请人员
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     * 申请人员
     * @param applicantName 申请人员
     */
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName == null ? null : applicantName.trim();
    }

    /**
     * 申请日期
     * @return application_time 申请日期
     */
    public Date getApplicationTime() {
        return applicationTime;
    }

    /**
     * 申请日期
     * @param applicationTime 申请日期
     */
    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    /**
     * 申请意见
     * @return application_opinion 申请意见
     */
    public String getApplicationOpinion() {
        return applicationOpinion;
    }

    /**
     * 申请意见
     * @param applicationOpinion 申请意见
     */
    public void setApplicationOpinion(String applicationOpinion) {
        this.applicationOpinion = applicationOpinion == null ? null : applicationOpinion.trim();
    }

    /**
     * 商品性质（0-普通商品；1-拆零商品；2-组装商品）
     * @return goods_nature 商品性质（0-普通商品；1-拆零商品；2-组装商品）
     */
    public Integer getGoodsNature() {
        return goodsNature;
    }

    /**
     * 商品性质（0-普通商品；1-拆零商品；2-组装商品）
     * @param goodsNature 商品性质（0-普通商品；1-拆零商品；2-组装商品）
     */
    public void setGoodsNature(Integer goodsNature) {
        this.goodsNature = goodsNature;
    }

    /**
     * 配置标识（0-必备；1-可选）
     * @return configuration_flag 配置标识（0-必备；1-可选）
     */
    public Integer getConfigurationFlag() {
        return configurationFlag;
    }

    /**
     * 配置标识（0-必备；1-可选）
     * @param configurationFlag 配置标识（0-必备；1-可选）
     */
    public void setConfigurationFlag(Integer configurationFlag) {
        this.configurationFlag = configurationFlag;
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
     * 标记（0-作废；1-正常）
     * @return valid_flag 标记（0-作废；1-正常）
     */
    public Integer getValidFlag() {
        return validFlag;
    }

    /**
     * 标记（0-作废；1-正常）
     * @param validFlag 标记（0-作废；1-正常）
     */
    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }


    public String getPictureIds() {
        return pictureIds;
    }

    public void setPictureIds(String pictureIds) {
        this.pictureIds = pictureIds;
    }

    /**
     * 商品备注
     * @return remark 商品备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 商品备注
     * @param remark 商品备注
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

    public Integer getFormulationType() {
        return formulationType;
    }

    public void setFormulationType(Integer formulationType) {
        this.formulationType = formulationType;
    }

    public Integer getSpiritDrugType() {
        return spiritDrugType;
    }

    public void setSpiritDrugType(Integer spiritDrugType) {
        this.spiritDrugType = spiritDrugType;
    }

    /**
     *
     * @mbg.generated
     */


    public Integer getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(Integer cosmetics) {
        this.cosmetics = cosmetics;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goods goods = (Goods) o;

        return id.equals(goods.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Integer getDistrFlag() {
        return distrFlag;
    }

    public void setDistrFlag(Integer distrFlag) {
        this.distrFlag = distrFlag;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", standardLibraryId=" + standardLibraryId +
                ", enterpriseId=" + enterpriseId +
                ", chainType=" + chainType +
                ", ownerId=" + ownerId +
                ", businessVariety=" + businessVariety +
                ", categoryId=" + categoryId +
                ", code='" + code + '\'' +
                ", barcode='" + barcode + '\'' +
                ", genericName='" + genericName + '\'' +
                ", name='" + name + '\'' +
                ", goodsAttribute=" + goodsAttribute +
                ", prescriptionDrug=" + prescriptionDrug +
                ", otcType=" + otcType +
                ", cosmetics=" + cosmetics +
                ", pinyinCode='" + pinyinCode + '\'' +
                ", mphCode='" + mphCode + '\'' +
                ", oldCode='" + oldCode + '\'' +
                ", nationalStandardCode='" + nationalStandardCode + '\'' +
                ", dosageId=" + dosageId +
                ", dosageName='" + dosageName + '\'' +
                ", specification='" + specification + '\'' +
                ", unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", domesticImport=" + domesticImport +
                ", manufacturerId=" + manufacturerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", place='" + place + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", validUntil=" + validUntil +
                ", managementScopeId=" + managementScopeId +
                ", managementScopeName='" + managementScopeName + '\'' +
                ", checkTypeId=" + checkTypeId +
                ", checkTypeName='" + checkTypeName + '\'' +
                ", qualityPeriod=" + qualityPeriod +
                ", qualityPeriodUnit=" + qualityPeriodUnit +
                ", specialDrug=" + specialDrug +
                ", spiritDrugType=" + spiritDrugType +
                ", inChargeDrug=" + inChargeDrug +
                ", formulationType=" + formulationType +
                ", limitQuantity=" + limitQuantity +
                ", storageTemp=" + storageTemp +
                ", storageConditionName='" + storageConditionName + '\'' +
                ", registeredTrademark='" + registeredTrademark + '\'' +
                ", brand='" + brand + '\'' +
                ", season='" + season + '\'' +
                ", grade='" + grade + '\'' +
                ", medicalInsurance=" + medicalInsurance +
                ", medicalInsuranceType=" + medicalInsuranceType +
                ", medicalInsuranceCode='" + medicalInsuranceCode + '\'' +
                ", first=" + first +
                ", firstGoodsCode='" + firstGoodsCode + '\'' +
                ", applicantId=" + applicantId +
                ", applicantCode='" + applicantCode + '\'' +
                ", applicantName='" + applicantName + '\'' +
                ", applicationTime=" + applicationTime +
                ", applicationOpinion='" + applicationOpinion + '\'' +
                ", goodsNature=" + goodsNature +
                ", configurationFlag=" + configurationFlag +
                ", distrFlag=" + distrFlag +
                ", status=" + status +
                ", validFlag=" + validFlag +
                ", approveStatus=" + approveStatus +
                ", pictureIds='" + pictureIds + '\'' +
                ", remark='" + remark + '\'' +
                ", createrId=" + createrId +
                ", createrCode='" + createrCode + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createTime=" + createTime +
                ", modifierId=" + modifierId +
                ", modifierCode='" + modifierCode + '\'' +
                ", modifierName='" + modifierName + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}