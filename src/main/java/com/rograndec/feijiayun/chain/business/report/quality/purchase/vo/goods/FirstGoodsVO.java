package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods;

import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsBusinessVO;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsInstructionsVO;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsQualificationConfigVO;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsStorageVO;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowActionDetail;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 添加 修改 的商品VO
 * Created by ST on 2017/9/4.
 */
public class FirstGoodsVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value="商品主键id")
    private Long id;


    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value="品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private Integer businessVariety;

    /**
     * 品种类别
     */
    @ApiModelProperty(value="品种类别")
    private String businessVarietyName;

    /**
     * 商品分类ID
     */
    @ApiModelProperty(value="商品分类ID")
    private Long categoryId;

    @ApiModelProperty(value="商品分类名称")
    private String categoryName;

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
    @ApiModelProperty(value="商品属性（0-成药；1-中药材；2-中药饮片）")
    private Integer goodsAttribute;

    /**
     * 商品属性名
     */
    @ApiModelProperty(value="商品属性名")
    private String goodsAttributeName;

    /**
     * 商品属性-是否为处方药，0：非处方药，1：处方药
     */
    @ApiModelProperty(value="商品属性-是否为处方药，0：非处方药，1：处方药")
    private Integer prescriptionDrug;

    /**
     * 非处方药类别：0-甲类；1-乙类；
     */
    @ApiModelProperty(value="非处方药类别：0-甲类；1-乙类；")
    private Integer otcType;

    /**
     *
     */
    @ApiModelProperty(value="品种类别为3-化妆品，商品属性为1-特殊用途化妆品时，化妆品类别［0-育发；1-染发；2-烫发；3-脱毛；4-美乳；5-健美；6-除臭；7-祛斑；8-防嗮］")
    private Integer cosmetics;

    /**
     * 检索码（型如“通用名检索码—商品名检索码”）
     */
    @ApiModelProperty(value="检索码（型如“通用名检索码—商品名检索码”）")
    private String pinyinCode;


    /**
     * MPH编码
     */
    @ApiModelProperty(value="MPH编码")
    private String mphCode;

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
     * 剂型ID
     */
    @ApiModelProperty(value="标准库ID")
    private Long dosageId;

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
     * 库存计量单位ID
     */
    @ApiModelProperty(value=" 库存计量单位ID")
    private Long unitId;

    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(value="库存计量单位名称")
    private String unitName;

    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value="国产/进口（0-国产；1-进口）")
    private Integer domesticImport;

    /**
     * 国产/进口
     */
    @ApiModelProperty(value="国产/进口")
    private String domesticImportName;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value=" 生产厂商ID")
    private Long manufacturerId;

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
    private Date validUntil;

    /**
     * 经营范围ID
     */
    @ApiModelProperty(value="经营范围ID")
    private Long managementScopeId;

    /**
     * 经营范围名称
     */
    @ApiModelProperty(value=" 经营范围名称")
    private String managementScopeName;

    /**
     * 验收分类ID
     */
    @ApiModelProperty(value="验收分类ID")
    private Long checkTypeId;

    /**
     * 验收分类名称
     */
    @ApiModelProperty(value="验收分类名称")
    private String checkTypeName;

    /**
     * 保质期
     */
    @ApiModelProperty(value="保质期")
    private Integer qualityPeriod;

    /**
     * 保质期单位（0-日；1-月；2-年）
     */
    @ApiModelProperty(value="保质期单位（0-日；1-月；2-年）")
    private Integer qualityPeriodUnit;

    /**
     * 保质期
     */
    @ApiModelProperty(value="保质期")
    private String qualityPeriodUnitName;

    /**
     *
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value="特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private Integer specialDrug;

    /**
     *
     * 特殊管理药品
     */
    @ApiModelProperty(value="特殊管理药品")
    private String specialDrugName;

    /**
     * 精神药品分类（0-一类；1-二类）
     */
    @ApiModelProperty(value="精神药品分类（0-一类；1-二类）")
    private Integer spiritDrugType;

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value="专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private Integer inChargeDrug;

    /**
     * 专管药品
     */
    @ApiModelProperty(value="专管药品")
    private String inChargeDrugName;
    /**
     *  含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）
     */
    @ApiModelProperty(value="含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
    private Integer formulationType;
    /**
     * 限购数量
     */
    @ApiModelProperty(value="限购数量")
    private BigDecimal limitQuantity;

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty(value=" 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private Integer storageTemp;

    /**
     * 贮藏温度
     */
    @ApiModelProperty(value=" 贮藏温度")
    private String storageTempName;



    /**
     * 贮藏条件名称
     */
    @ApiModelProperty(value="贮藏条件名称")
    private String storageConditionName;

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
    private Integer medicalInsurance;

    /**
     * 医保类别（0-国家医保；1-地方医保）
     */
    @ApiModelProperty(value="医保类别（0-国家医保；1-地方医保）")
    private Integer medicalInsuranceType;

    /**
     * 医保号
     */
    @ApiModelProperty(value="医保号")
    private String medicalInsuranceCode;

    /**
     * 首营品种（0-否；1-是）
     */
    @ApiModelProperty(value="首营品种（0-否；1-是）")
    private Integer first;

    /**
     * 首营品种编号
     */
    @ApiModelProperty(value="首营品种编号")
    private String firstGoodsCode;

    /**
     * 申请人ID
     */
    @ApiModelProperty(value="申请人ID")
    private Long applicantId;

    /**
     * 申请人员编码
     */
    @ApiModelProperty(value="申请人员编码")
    private String applicantCode;

    /**
     * 申请人员
     */
    @ApiModelProperty(value="申请人员")
    private String applicantName;

    /**
     * 申请日期
     */
    @ApiModelProperty(value="申请日期")
    private Date applicationTime;

    /**
     * 申请意见
     */
    @ApiModelProperty(value="申请意见")
    private String applicationOpinion;

    /**
     * 商品性质（0-普通商品；1-拆零商品；2-组装商品）
     */
    @ApiModelProperty(value="商品性质（0-普通商品；1-拆零商品；2-组装商品）")
    private Integer goodsNature;

    /**
     * 配置标识（0-必备；1-可选）
     */
    @ApiModelProperty(value="配置标识（0-必备；1-可选）")
    private Integer configurationFlag;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value=" 状态（0-禁用；1-启用）")
    private Integer status;

    /**
     * 标记（0-作废；1-正常）
     */
    @ApiModelProperty(value="标记（0-作废；1-正常）")
    private Integer validFlag;

    /**
     * 审核状态（12-待审核；22-审核通过；23-审核被驳回）
     */
    @ApiModelProperty(value="审核状态（12-待审核；22-审核通过；23-审核被驳回）")
    private Integer approveStatus;

    /**
     * 商品图片ID
     */
    @ApiModelProperty(value="商品图片ID")
    private String pictureIds;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 审批信息
     */
    @ApiModelProperty(value="审批信息")
    private List<ApprovalFlowActionDetail> approvalFlowActionDetails;

    @ApiModelProperty(value="商品业务信息")
    private GoodsBusinessVO goodsBusinessVO;

    @ApiModelProperty(value="商品说明书")
    private GoodsInstructionsVO goodsInstructionsVO;

    @ApiModelProperty(value="商品储存和养护信息")
    private GoodsStorageVO storageMaintenanceVO;

   //商品资质信息
    @ApiModelProperty(value="商品资质信息")
    private List<GoodsQualificationConfigVO> goodsQualificationConfigVOList;


    public List<GoodsQualificationConfigVO> getGoodsQualificationConfigVOList() {
        return goodsQualificationConfigVOList;
    }

    public void setGoodsQualificationConfigVOList(List<GoodsQualificationConfigVO> goodsQualificationConfigVOList) {
        this.goodsQualificationConfigVOList = goodsQualificationConfigVOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getMphCode() {
        return mphCode;
    }

    public void setMphCode(String mphCode) {
        this.mphCode = mphCode;
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

    public String getPictureIds() {
        return pictureIds;
    }

    public void setPictureIds(String pictureIds) {
        this.pictureIds = pictureIds;
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

    public BigDecimal getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(BigDecimal limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public Integer getStorageTemp() {
        return storageTemp;
    }

    public void setStorageTemp(Integer storageTemp) {
        this.storageTemp = storageTemp;
    }


    public String getStorageConditionName() {
        return storageConditionName;
    }

    public void setStorageConditionName(String storageConditionName) {
        this.storageConditionName = storageConditionName;
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

    public Integer getGoodsNature() {
        return goodsNature;
    }

    public void setGoodsNature(Integer goodsNature) {
        this.goodsNature = goodsNature;
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

    public GoodsBusinessVO getGoodsBusinessVO() {
        return goodsBusinessVO;
    }

    public void setGoodsBusinessVO(GoodsBusinessVO goodsBusinessVO) {
        this.goodsBusinessVO = goodsBusinessVO;
    }

    public GoodsStorageVO getStorageMaintenanceVO() {
        return storageMaintenanceVO;
    }

    public void setStorageMaintenanceVO(GoodsStorageVO storageMaintenanceVO) {
        this.storageMaintenanceVO = storageMaintenanceVO;
    }

    public GoodsInstructionsVO getGoodsInstructionsVO() {
        return goodsInstructionsVO;
    }

    public void setGoodsInstructionsVO(GoodsInstructionsVO goodsInstructionsVO) {
        this.goodsInstructionsVO = goodsInstructionsVO;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(Integer cosmetics) {
        this.cosmetics = cosmetics;
    }

    public List<ApprovalFlowActionDetail> getApprovalFlowActionDetails() {
        return approvalFlowActionDetails;
    }

    public void setApprovalFlowActionDetails(List<ApprovalFlowActionDetail> approvalFlowActionDetails) {
        this.approvalFlowActionDetails = approvalFlowActionDetails;
    }

    public String getBusinessVarietyName() {
        return businessVarietyName;
    }

    public void setBusinessVarietyName(String businessVarietyName) {
        this.businessVarietyName = businessVarietyName;
    }

    public String getGoodsAttributeName() {
        return goodsAttributeName;
    }

    public void setGoodsAttributeName(String goodsAttributeName) {
        this.goodsAttributeName = goodsAttributeName;
    }

    public String getDomesticImportName() {
        return domesticImportName;
    }

    public void setDomesticImportName(String domesticImportName) {
        this.domesticImportName = domesticImportName;
    }

    public String getQualityPeriodUnitName() {
        return qualityPeriodUnitName;
    }

    public void setQualityPeriodUnitName(String qualityPeriodUnitName) {
        this.qualityPeriodUnitName = qualityPeriodUnitName;
    }

    public String getSpecialDrugName() {
        return specialDrugName;
    }

    public void setSpecialDrugName(String specialDrugName) {
        this.specialDrugName = specialDrugName;
    }

    public String getInChargeDrugName() {
        return inChargeDrugName;
    }

    public void setInChargeDrugName(String inChargeDrugName) {
        this.inChargeDrugName = inChargeDrugName;
    }

    public String getStorageTempName() {
        return storageTempName;
    }

    public void setStorageTempName(String storageTempName) {
        this.storageTempName = storageTempName;
    }

    @Override
    public String toString() {
        return "FirstGoodsVO{" +
                ", id=" + id +
                ", businessVariety=" + businessVariety +
                ", businessVarietyName='" + businessVarietyName + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", code='" + code + '\'' +
                ", barcode='" + barcode + '\'' +
                ", genericName='" + genericName + '\'' +
                ", name='" + name + '\'' +
                ", goodsAttribute=" + goodsAttribute +
                ", goodsAttributeName='" + goodsAttributeName + '\'' +
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
                ", domesticImportName='" + domesticImportName + '\'' +
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
                ", qualityPeriodUnitName='" + qualityPeriodUnitName + '\'' +
                ", specialDrug=" + specialDrug +
                ", specialDrugName='" + specialDrugName + '\'' +
                ", spiritDrugType=" + spiritDrugType +
                ", inChargeDrug=" + inChargeDrug +
                ", inChargeDrugName='" + inChargeDrugName + '\'' +
                ", formulationType=" + formulationType +
                ", limitQuantity=" + limitQuantity +
                ", storageTemp=" + storageTemp +
                ", storageTempName='" + storageTempName + '\'' +
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
                ", status=" + status +
                ", validFlag=" + validFlag +
                ", approveStatus=" + approveStatus +
                ", pictureIds='" + pictureIds + '\'' +
                ", remark='" + remark + '\'' +
                ", approvalFlowActionDetails=" + approvalFlowActionDetails +
                ", goodsBusinessVO=" + goodsBusinessVO +
                ", goodsInstructionsVO=" + goodsInstructionsVO +
                ", storageMaintenanceVO=" + storageMaintenanceVO +
                ", goodsQualificationConfigVOList=" + goodsQualificationConfigVOList +
                '}';
    }
}