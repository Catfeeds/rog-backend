package com.rograndec.feijiayun.chain.business.goods.info.vo;

import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 商品查询的参数VO
 * Created by ST on 2017/9/4.
 */
public class RequestGoodsVO extends CommonParamSupplierAndGoods{

    @ApiModelProperty(value = "0-商品信息中查询商品搜索，1-销售管理中商品搜索；默认是0")
    private Integer type;

    @ApiModelProperty(value = "商品id")
    private Long goodsId;
    @ApiModelProperty("快速搜索的条件（编码/条形码/商品名称/通用名称/批准文号）")
    private String param;

    @ApiModelProperty(value = "当前页",required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "每页显示数据",required = true)
    private Integer pageSize;



    @ApiModelProperty("品种类别")
    private Long businessVariety;

    /**
     * 验收分类ID
     */
    @ApiModelProperty("验收分类ID")
    private Long checkTypeId;

    /**
     * 验收分类名称
     */
    private String checkTypeName;


    /**
     * 养护类型（0-常规养护；1-重点养护）
     */
    @ApiModelProperty("养护类型（0-常规养护；1-重点养护）")
    private Integer maintenanceType;

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
     * 商品分类ID
     */
    @ApiModelProperty("商品分类ID")
    private Long categoryId;


    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty("国产/进口（0-国产；1-进口）")
    private Integer domesticImport;


    /**
     * 贮藏区域（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty("贮藏区域（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private Integer storageTemp;


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
     * 积分商品（0-否；1-是）
     */
    @ApiModelProperty("积分商品（0-否；1-是）")
    private Integer integralGoods;

    /**
     * 经营方式（0-购销；1-实销实结）
     */
    @ApiModelProperty("经营方式（0-购销；1-实销实结）")
    private Integer managementMode;
    /**
     * 特价商品（0-否；1-是）
     */
    @ApiModelProperty("特价商品（0-否；1-是）")
    private Integer bargainGoods;

    /**
     * 提成商品（0-否；1-是）
     */
    @ApiModelProperty("提成商品（0-否；1-是）")
    private Integer commissionGoods;
    /**
     * 配送方式（0-配送中心配送；1-委托配送）
     */
    @ApiModelProperty("配送方式（0-配送中心配送；1-委托配送）")
    private Integer deliveryMode;
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
     * 医保类别（0-国家医保；1-地方医保）
     */
    private Integer medicalInsuranceType;

    /**
     * 经营范围ID
     */
    @ApiModelProperty("经营范围ID")
    private Long managementScopeId;

    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty("特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private Integer specialDrug;

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty("专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private Integer inChargeDrug;

    /**
     * 商品性质（0-普通商品；1-拆零商品；2-组装商品）
     */
    @ApiModelProperty("商品性质（0-普通商品；1-拆零商品；2-组装商品）")
    private Integer goodsNature;

    /**
     * 配置标识（0-必备；1-可选）
     */
    @ApiModelProperty("配置标识（0-必备；1-可选）")
    private Integer configurationFlag;

    /**
     * 首营品种（0-否；1-是）
     */
    @ApiModelProperty("首营品种（0-否；1-是）")
    private Integer first;




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

    @ApiModelProperty("审核状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回）  ")
    private Integer statusRecom;

    @ApiModelProperty(value="含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
    private Integer formulationType;


    public Long getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(Long businessVariety) {
        this.businessVariety = businessVariety;
    }

    public Integer getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(Integer maintenanceType) {
        this.maintenanceType = maintenanceType;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDomesticImport() {
        return domesticImport;
    }

    public void setDomesticImport(Integer domesticImport) {
        this.domesticImport = domesticImport;
    }

    public Integer getStorageTemp() {
        return storageTemp;
    }

    public void setStorageTemp(Integer storageTemp) {
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

    public Integer getIntegralGoods() {
        return integralGoods;
    }

    public void setIntegralGoods(Integer integralGoods) {
        this.integralGoods = integralGoods;
    }

    public Integer getManagementMode() {
        return managementMode;
    }

    public void setManagementMode(Integer managementMode) {
        this.managementMode = managementMode;
    }

    public Integer getBargainGoods() {
        return bargainGoods;
    }

    public void setBargainGoods(Integer bargainGoods) {
        this.bargainGoods = bargainGoods;
    }

    public Integer getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Integer deliveryMode) {
        this.deliveryMode = deliveryMode;
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

    public Integer getMedicalInsuranceType() {
        return medicalInsuranceType;
    }

    public void setMedicalInsuranceType(Integer medicalInsuranceType) {
        this.medicalInsuranceType = medicalInsuranceType;
    }

    public Long getManagementScopeId() {
        return managementScopeId;
    }

    public void setManagementScopeId(Long managementScopeId) {
        this.managementScopeId = managementScopeId;
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

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getGoodsNature() {
        return goodsNature;
    }

    public void setGoodsNature(Integer goodsNature) {
        this.goodsNature = goodsNature;
    }

    public String getCheckTypeName() {
        return checkTypeName;
    }

    public void setCheckTypeName(String checkTypeName) {
        this.checkTypeName = checkTypeName;
    }

    public Long getCheckTypeId() {
        return checkTypeId;
    }

    public void setCheckTypeId(Long checkTypeId) {
        this.checkTypeId = checkTypeId;
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

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStatusRecom() {
        return statusRecom;
    }

    public void setStatusRecom(Integer statusRecom) {
        this.statusRecom = statusRecom;
    }

    public Integer getFormulationType() {
        return formulationType;
    }

    public void setFormulationType(Integer formulationType) {
        this.formulationType = formulationType;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCommissionGoods() {
        return commissionGoods;
    }

    public void setCommissionGoods(Integer commissionGoods) {
        this.commissionGoods = commissionGoods;
    }

    public Integer getType() {
        if(type == null) return 0;
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}