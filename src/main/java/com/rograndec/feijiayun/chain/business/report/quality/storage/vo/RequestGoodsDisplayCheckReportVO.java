package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
 
/**
 * 
 * 陈列检查
 * 
 * 
 * @author kexinhao
 * 
 * 2017-09-28
 */
public class RequestGoodsDisplayCheckReportVO implements Serializable {
	  @ApiModelProperty(value = "页码")
	  private int pageNo;
      @ApiModelProperty(value = "每页显示的记录数")
      private int pageSize;
      @ApiModelProperty(value = "商品编码/条形码/商品名称/通用名称/批准文号")
      private String param;
		@ApiModelProperty(value = "检查日期（从）")
		private Date startDate;
		@ApiModelProperty(value = "检查日期（至）")
		private Date endDate;
		@ApiModelProperty(value = "组织机构类型|0-总部；1-自营店；2-加盟店")
		private String chainType;
		@ApiModelProperty(value = "组织机构编码")
		private String enterpriseCode;
		@ApiModelProperty(value = "组织结构名称")
		private String enterpriseName;
		@ApiModelProperty(value = "检查单号")
		private String code;
		@ApiModelProperty(value = "检查人员") 
		private String checkerName;
		@ApiModelProperty(value = "药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）")
		private String goodsType;
		@ApiModelProperty(value = "检查类型（0-重点养护；1-常规养护）")
		private String maintanceType;
		@ApiModelProperty(value = "状态（31-计划中|32-检查中|33-已完成|34-已取消）")
		private String status;
		@ApiModelProperty(value = "商品分类")
		private String categoryId;
		@ApiModelProperty(value = "国产/进口（0-国产；1-进口）")
		private String domesticImport;
		@ApiModelProperty(value = "储藏条件") 
		private String storageConditionName;
		@ApiModelProperty(value = "储藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）") 
		private String storageTemp;
		@ApiModelProperty(value = "注册商标")
		private String registeredTrademark;
		@ApiModelProperty(value = "商品属性（0-成药；1-中药材；2-中药饮片）") 
		private String goodsAttribute;
		@ApiModelProperty(value = "品牌") 
		private String brand;
		@ApiModelProperty(value = "商品为成药时是否为处方药（0：非处方药，1：处方药）")
		private String prescriptionDrug;
		@ApiModelProperty(value = "为非处方药时甲类还是乙类（0-甲类；1-乙类）") 
		private String otcType;
		@ApiModelProperty(value = "经营范围")
		private String managementScopeId;
		@ApiModelProperty(value = "特殊管理药品（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）") 
		private String specialDrug;
		@ApiModelProperty(value = "精神药品分类（0-一类；1-二类）") 
		private String spiritDrugType;
		@ApiModelProperty(value = "专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
		private String inChargeDrug;
		@ApiModelProperty(value = "含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
		private String formulationType;
		@ApiModelProperty(value = "排序字段")
		private String sortField;                                        					
		@ApiModelProperty(value = "排序方式（升序：asc;降序：desc）")
		private String sort;
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public String getParam() {
			return param;
		}
		public void setParam(String param) {
			this.param = param;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		public String getChainType() {
			return chainType;
		}
		public void setChainType(String chainType) {
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
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getCheckerName() {
			return checkerName;
		}
		public void setCheckerName(String checkerName) {
			this.checkerName = checkerName;
		}
		public String getGoodsType() {
			return goodsType;
		}
		public void setGoodsType(String goodsType) {
			this.goodsType = goodsType;
		}
		public String getMaintanceType() {
			return maintanceType;
		}
		public void setMaintanceType(String maintanceType) {
			this.maintanceType = maintanceType;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}
		public String getDomesticImport() {
			return domesticImport;
		}
		public void setDomesticImport(String domesticImport) {
			this.domesticImport = domesticImport;
		}

	public String getStorageConditionName() {
		return storageConditionName;
	}

	public void setStorageConditionName(String storageConditionName) {
		this.storageConditionName = storageConditionName;
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
		public String getGoodsAttribute() {
			return goodsAttribute;
		}
		public void setGoodsAttribute(String goodsAttribute) {
			this.goodsAttribute = goodsAttribute;
		}
		public String getPrescriptionDrug() {
			return prescriptionDrug;
		}
		public void setPrescriptionDrug(String prescriptionDrug) {
			this.prescriptionDrug = prescriptionDrug;
		}
		public String getOtcType() {
			return otcType;
		}
		public void setOtcType(String otcType) {
			this.otcType = otcType;
		}
		public String getManagementScopeId() {
			return managementScopeId;
		}
		public void setManagementScopeId(String managementScopeId) {
			this.managementScopeId = managementScopeId;
		}
		public String getSpecialDrug() {
			return specialDrug;
		}
		public void setSpecialDrug(String specialDrug) {
			this.specialDrug = specialDrug;
		}
		public String getSpiritDrugType() {
			return spiritDrugType;
		}
		public void setSpiritDrugType(String spiritDrugType) {
			this.spiritDrugType = spiritDrugType;
		}
		public String getInChargeDrug() {
			return inChargeDrug;
		}
		public void setInChargeDrug(String inChargeDrug) {
			this.inChargeDrug = inChargeDrug;
		}
		public String getFormulationType() {
			return formulationType;
		}
		public void setFormulationType(String formulationType) {
			this.formulationType = formulationType;
		}
		public String getSortField() {
			return sortField;
		}
		public void setSortField(String sortField) {
			this.sortField = sortField;
		}
		public String getSort() {
			return sort;
		}
		public void setSort(String sort) {
			this.sort = sort;
		}
		public String getBrand() {
			return brand;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		
}   