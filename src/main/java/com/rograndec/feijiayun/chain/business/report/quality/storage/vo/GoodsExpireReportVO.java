package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * 过期商品报表
 * @author kexinhao
 * 2017-09-28
 */
public class GoodsExpireReportVO implements Serializable {
	    @ApiModelProperty(value = "组织机构编码")
	    private String enterpriseCode;
	    @ApiModelProperty(value = "组织机构名称")
	    private String enterpriseName;
	    @ApiModelProperty(value = "商品编码")
	    private String goodsCode;
	    @ApiModelProperty(value = "条形码")
	    private String barcode;
	    @ApiModelProperty(value = "通用名称")
		private String goodsGenericName;
	    @ApiModelProperty(value = "商品名称")
	    private String goodsName;
		@ApiModelProperty(value = "剂型名称")
		private String dosageName;
		@ApiModelProperty(value = "规格")
		private String goodsSpecification;
	    @ApiModelProperty(value="计量单位名称")
	    private String unitName;
		@ApiModelProperty(value = "生产厂商名称")
		private String manufacturer;
	    @ApiModelProperty(value="产地")
	    private String goodsPlace;
	    @ApiModelProperty(value = "批准文号")
	    private String approvalNumber;
	    @ApiModelProperty(value = "批号")
	    private String lotNumber;
	    @ApiModelProperty(value = "生产日期")
	    private Date productDate;
	    @ApiModelProperty(value = "有效期")
	    private Date validDate;
	    @ApiModelProperty(value = "货位名称")
	    private String shelfName;
	    @ApiModelProperty(value = "货位质量状态描述")
	    private String shelfStatusDesc;
	    @ApiModelProperty(value = "数量")
	    private BigDecimal quantity;
	    @ApiModelProperty(value = "品种类型")
	    private Integer businessVariety;
	@ApiModelProperty(value = "品种类型")
	private String businessVarietyName;
	    @ApiModelProperty(value = "商品分类")
	    private String categoryName;
	    @ApiModelProperty(value = "商品属性(0-成药；1-中药材；2-中药饮片)")   
	    private String goodsAttribute;
	    @ApiModelProperty(value = "国产/进口|0-国产|1-进口")
	    private Integer domesticImport;
	    @ApiModelProperty(value = "国产/进口描述")
	    private String domesticImportDesc;
	    @ApiModelProperty(value = "经营范围")  
	    private String managementScopeName;
	    @ApiModelProperty(value = "特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
	    private Integer specialDrug;
	@ApiModelProperty(value="精神药品分类（0-一类；1-二类）")
	private Integer spiritDrugType;
	    @ApiModelProperty(value = "特殊管理药品描述")
	    private String  specialDrugDesc;
	    @ApiModelProperty(value = "专管药品0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素")
	    private Integer inChargeDrug;
	@ApiModelProperty(value="含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
	private Integer formulationType;
	    @ApiModelProperty(value = "专管药品描述")
	    private String inChargeDrugDesc;
	    @ApiModelProperty(value = "限购数量")
	    private Integer limitQuantity;
	    @ApiModelProperty(value = "储藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
	    private Integer storageTemp;
	    @ApiModelProperty(value = "储藏温度描述")
	    private String storageTempDesc;
	    @ApiModelProperty(value = "储藏条件")
	    private String storageConditionName;
	    @ApiModelProperty(value = "保质期")
	    private Integer qualityPeriod;
	    @ApiModelProperty(value = "保质期单位")
	    private Integer qualityPeriodUnit;
	    @ApiModelProperty(value = "保质期单位描述")
	    private String qualityPeriodUnitDesc;
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
		public String getGoodsGenericName() {
			return goodsGenericName;
		}
		public void setGoodsGenericName(String goodsGenericName) {
			this.goodsGenericName = goodsGenericName;
		}
		public String getGoodsName() {
			return goodsName;
		}
		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}
		public String getDosageName() {
			return dosageName;
		}
		public void setDosageName(String dosageName) {
			this.dosageName = dosageName;
		}
		public String getGoodsSpecification() {
			return goodsSpecification;
		}
		public void setGoodsSpecification(String goodsSpecification) {
			this.goodsSpecification = goodsSpecification;
		}
		public String getUnitName() {
			return unitName;
		}
		public void setUnitName(String unitName) {
			this.unitName = unitName;
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
		public String getLotNumber() {
			return lotNumber;
		}
		public void setLotNumber(String lotNumber) {
			this.lotNumber = lotNumber;
		}
		public Date getProductDate() {
			return productDate;
		}
		public void setProductDate(Date productDate) {
			this.productDate = productDate;
		}
		public Date getValidDate() {
			return validDate;
		}
		public void setValidDate(Date validDate) {
			this.validDate = validDate;
		}
		public String getShelfName() {
			return shelfName;
		}
		public void setShelfName(String shelfName) {
			this.shelfName = shelfName;
		}
		public String getShelfStatusDesc() {
			return shelfStatusDesc;
		}
		public void setShelfStatusDesc(String shelfStatusDesc) {
			this.shelfStatusDesc = shelfStatusDesc;
		}
		public BigDecimal getQuantity() {
			return quantity;
		}
		public void setQuantity(BigDecimal quantity) {
			this.quantity = quantity;
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

	public String getBusinessVarietyName() {
		return businessVarietyName;
	}

	public void setBusinessVarietyName(String businessVarietyName) {
		this.businessVarietyName = businessVarietyName;
	}
	//		public String getBusinessVariety() {
//			return businessVariety;
//		}
//		public void setBusinessVariety(String businessVariety) {
//			if(businessVariety!=null){
//				if(businessVariety.equals(0)){
//					this.businessVariety="药品";
//				}else if(businessVariety.equals(1)){
//					this.businessVariety="医疗器械";
//				}else if(businessVariety.equals(2)){
//					this.businessVariety="食品";
//				}else if(businessVariety.equals(3)){
//					this.businessVariety="化妆品";
//				}else if(businessVariety.equals(4)){
//					this.businessVariety="其它";
//				}
//			}
//		}

	public Integer getBusinessVariety() {
		return businessVariety;
	}

	public void setBusinessVariety(Integer businessVariety) {
		this.businessVariety = businessVariety;
	}

	public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public String getGoodsAttribute() {
			return goodsAttribute;
		}
		public void setGoodsAttribute(String goodsAttribute) {
			this.goodsAttribute = goodsAttribute;
		}
		public Integer getDomesticImport() {
			return domesticImport;
		}
		public void setDomesticImport(Integer domesticImport) {
			if(domesticImport!=null){
				if(domesticImport.equals(0)){
					this.domesticImportDesc="国产";
				}else if(domesticImport.equals(1)){
					this.domesticImportDesc="进口";
				}
			}
			this.domesticImport = domesticImport;
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
		public Integer getSpecialDrug() {
			return specialDrug;
		}
		public void setSpecialDrug(Integer specialDrug) {
			if(specialDrug!=null){
				if(specialDrug.equals(0)){
					this.specialDrugDesc="精神药品";
				}else if(specialDrug.equals(1)){
					this.specialDrugDesc="麻醉药品";
				}else if(specialDrug.equals(2)){
					this.specialDrugDesc="医疗用毒性药品";
				}else if(specialDrug.equals(3)){
					this.specialDrugDesc="放射性药品";
				}
			}
			this.specialDrug = specialDrug;
		}
		public String getSpecialDrugDesc() {
			return specialDrugDesc;
		}
		public void setSpecialDrugDesc(String specialDrugDesc) {
			this.specialDrugDesc = specialDrugDesc;
		}
		public Integer getInChargeDrug() {
			return inChargeDrug;
		}
		public void setInChargeDrug(Integer inChargeDrug) {
			if(inChargeDrug!=null){
				if(inChargeDrug.equals(0)){
					this.inChargeDrugDesc="含特殊药品复方制剂";
				}else if(inChargeDrug.equals(1)){
					this.inChargeDrugDesc="蛋白同化制剂";
				}else if(inChargeDrug.equals(2)){
					this.inChargeDrugDesc="肽类激素";
				}
			}
			this.inChargeDrug = inChargeDrug;
		}
		public String getInChargeDrugDesc() {
			return inChargeDrugDesc;
		}
		public void setInChargeDrugDesc(String inChargeDrugDesc) {
			this.inChargeDrugDesc = inChargeDrugDesc;
		}
		public Integer getLimitQuantity() {
			return limitQuantity;
		}
		public void setLimitQuantity(Integer limitQuantity) {
			this.limitQuantity = limitQuantity;
		}
		public Integer getStorageTemp() {
			return storageTemp;
		}
		public void setStorageTemp(Integer storageTemp) {
			if(storageTemp!=null){
				if(storageTemp.equals(0)){
					this.storageTempDesc="常温";
				}else if(storageTemp.equals(1)){
					this.storageTempDesc="阴凉";
				}else if(storageTemp.equals(2)){
					this.storageTempDesc="冷藏";
				}else if(storageTemp.equals(3)){
					this.storageTempDesc="冷冻";
				}
			}
			this.storageTemp = storageTemp;
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
			if(qualityPeriodUnit!=null){
				if(qualityPeriodUnit.equals(0)){
					this.qualityPeriodUnitDesc=this.qualityPeriod+"日";
				}else if(qualityPeriodUnit.equals(1)){
					this.qualityPeriodUnitDesc=this.qualityPeriod+"月";
				}else if(qualityPeriodUnit.equals(2)){
					this.qualityPeriodUnitDesc=this.qualityPeriod+"年";
				}
			}
			this.qualityPeriodUnit = qualityPeriodUnit;
		}
		
		public String getQualityPeriodUnitDesc() {
			return qualityPeriodUnitDesc;
		}
		public void setQualityPeriodUnitDesc(String qualityPeriodUnitDesc) {
			this.qualityPeriodUnitDesc = qualityPeriodUnitDesc;
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
	    
}   