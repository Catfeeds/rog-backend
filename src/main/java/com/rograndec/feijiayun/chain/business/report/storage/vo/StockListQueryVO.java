package com.rograndec.feijiayun.chain.business.report.storage.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class StockListQueryVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月24日 下午7:45:53 
	 * @version 1.0   
	 */
	
	@ApiModelProperty(required = true, value = "页码")
	private Integer pageNo;
	
	@ApiModelProperty(required = true, value = "每页显示的记录数")
	private Integer pageSize;
	
	@ApiModelProperty(required = false, value = "按某一列排序")
	private String order;
	
	@ApiModelProperty(required = false, value = "排序方式（升序：asc,降序：desc）")
	private String sort;
	
	@ApiModelProperty(required = false, value = "key-输入框值")
	private String key;
	
	@ApiModelProperty(value = "经营状态（2-出售中；3-已售罄；4-已下架；9-全部）")
	private Integer manageStatus;
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "门店ID")
	private Long storeId;
	
	@ApiModelProperty(value = "门店名称")
	private String storeName;
	
	@ApiModelProperty(value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private Integer businessVariety;
	
	@ApiModelProperty(value = "商品属性（0-成药；1-中药材；2-中药饮片）")
	private Integer goodsAttribute;
	
	@ApiModelProperty(value = "品种类别为0-药品，商品属性为0-成药时，prescription_drug含义为是否为处方药［0：非处方药，1：处方药］")
	private Integer prescriptionDrug;
	
	@ApiModelProperty(value = "商品分类ID")
	private Long categoryId;
	
	@ApiModelProperty(value = "商品性质（0-普通商品；1-拆零商品；2-组装商品）")
	private Integer goodsNature;
	
	@ApiModelProperty(value = "国产/进口（0-国产；1-进口）")
	private Integer domesticImport;
	
	@ApiModelProperty(value = "贮藏区域（0-常温；1-阴凉；2-冷藏；3-冷冻）")
	private Integer storageTemp;
	
	@ApiModelProperty(value = "养护类型（0-常规养护；1-重点养护）")
	private Integer maintenanceType;
	
	@ApiModelProperty(value = "是否为医保药品（0-否；1-是")
	private Integer medicalInsurance;
	
	//经营范围
	@ApiModelProperty(value = "经营范围")
	private Long managementScopeId;
	
	@ApiModelProperty(value = "特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
	private Integer specialDrug;
	
	@ApiModelProperty(value = "专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；")
	private Integer inChargeDrug;
	
	@ApiModelProperty(value = "配置标识（0-必备；1-可选）")
	private Integer configurationFlag;
	
	@ApiModelProperty(value = "经营方式（0-购销；1-实销实结）")
	private Integer managementMode;
	
	@ApiModelProperty(value = "注册商标")
	private String registeredTrademark;

	@ApiModelProperty(value = "品牌")
	private String brand;
	
	@ApiModelProperty(value = "等级")
	private String grade;
	
	@ApiModelProperty(value = "库区")
	private Long areaId;
	
	@ApiModelProperty(value = "货区")
	private Long cargoAreaId;
	
	@ApiModelProperty(value = "货位ID")
	private Long shelfId;
	
	@ApiModelProperty(value = "批号")
	private String lotNum;
	
	
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getBusinessVariety() {
		return businessVariety;
	}

	public void setBusinessVariety(Integer businessVariety) {
		this.businessVariety = businessVariety;
	}

	public Integer getGoodsAttribute() {
		return goodsAttribute;
	}

	public void setGoodsAttribute(Integer goodsAttribute) {
		this.goodsAttribute = goodsAttribute;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getGoodsNature() {
		return goodsNature;
	}

	public void setGoodsNature(Integer goodsNature) {
		this.goodsNature = goodsNature;
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

	public Integer getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(Integer maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	public Integer getMedicalInsurance() {
		return medicalInsurance;
	}

	public void setMedicalInsurance(Integer medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
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

	public Integer getConfigurationFlag() {
		return configurationFlag;
	}

	public void setConfigurationFlag(Integer configurationFlag) {
		this.configurationFlag = configurationFlag;
	}

	public Integer getManagementMode() {
		return managementMode;
	}

	public void setManagementMode(Integer managementMode) {
		this.managementMode = managementMode;
	}

	public Integer getManageStatus() {
		return manageStatus;
	}

	public void setManageStatus(Integer manageStatus) {
		this.manageStatus = manageStatus;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getPrescriptionDrug() {
		return prescriptionDrug;
	}

	public void setPrescriptionDrug(Integer prescriptionDrug) {
		this.prescriptionDrug = prescriptionDrug;
	}

	public Long getManagementScopeId() {
		return managementScopeId;
	}

	public void setManagementScopeId(Long managementScopeId) {
		this.managementScopeId = managementScopeId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getCargoAreaId() {
		return cargoAreaId;
	}

	public void setCargoAreaId(Long cargoAreaId) {
		this.cargoAreaId = cargoAreaId;
	}

	public Long getShelfId() {
		return shelfId;
	}

	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}

	public String getLotNum() {
		return lotNum;
	}

	public void setLotNum(String lotNum) {
		this.lotNum = lotNum;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
