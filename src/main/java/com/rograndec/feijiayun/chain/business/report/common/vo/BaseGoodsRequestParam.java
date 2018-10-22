package com.rograndec.feijiayun.chain.business.report.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <配送相关单据请求参数-父类>
 *
 * @Author: Zhengbin.jin 金正斌
 * @Email: Zhengbin.jin@rograndec.com
 * @2017/10/19 10:18
 */
@ApiModel(value = "BaseGoodsRequestParam", description = "配送相关单据请求参数")
public class BaseGoodsRequestParam implements Serializable{

	@ApiModelProperty(value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
	private Integer businessVariety;

	@ApiModelProperty(value = "商品编码/条形码/商品名称/通用名称/批准文号")
	private String param;

	@ApiModelProperty(required = false, value = "组织机构类型")
	private Integer chainType;

	@ApiModelProperty(required = false, value = "组织机构编码")
	private String enterpriseCode;

	@ApiModelProperty(required = false, value = "组织机构名称")
	private String enterpriseName;

	@ApiModelProperty(value = "商品分类ID")
	private Integer categoryId;

	@ApiModelProperty(value = "国产/进口（0-国产；1-进口）")
	private Integer domesticImport;

	@ApiModelProperty(value = "贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
	private Integer storageTemp;

	@ApiModelProperty(value = "贮藏条件名称")
	private String storageConditionName;

	@ApiModelProperty(value = "注册商标")
	private String registeredTrademark;

	@ApiModelProperty(value = "品牌")
	private String brand;

	@ApiModelProperty(value = "商品属性（0-成药；1-中药材；2-中药饮片）")
	private Integer goodsAttribute;

	@ApiModelProperty("商品属性-是否为处方药，0：非处方药，1：处方药")
	private Integer prescriptionDrug;

	@ApiModelProperty(value = "非处方药类别：0-甲类；1-乙类；")
	private Integer otcType;

	@ApiModelProperty(value = "特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
	private Integer specialDrug;

	@ApiModelProperty(value = "精神药品分类（0-一类；1-二类）")
	private String spiritDrugType;

	@ApiModelProperty(value = "专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
	private Integer inChargeDrug;

	@ApiModelProperty(value = "含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
	private String formulationType;

	@ApiModelProperty(value = "经营范围ID")
	private Integer managementScopeId;

	public String getSpiritDrugType() {
		return spiritDrugType;
	}

	public void setSpiritDrugType(String spiritDrugType) {
		this.spiritDrugType = spiritDrugType;
	}

	public String getFormulationType() {
		return formulationType;
	}

	public void setFormulationType(String formulationType) {
		this.formulationType = formulationType;
	}

	public Integer getBusinessVariety() {
		return businessVariety;
	}

	public void setBusinessVariety(Integer businessVariety) {
		this.businessVariety = businessVariety;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
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

	public Integer getManagementScopeId() {
		return managementScopeId;
	}

	public void setManagementScopeId(Integer managementScopeId) {
		this.managementScopeId = managementScopeId;
	}
}
