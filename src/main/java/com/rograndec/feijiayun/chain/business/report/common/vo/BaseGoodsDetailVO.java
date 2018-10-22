package com.rograndec.feijiayun.chain.business.report.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.GoodsAttribute2DrugsA;
import com.rograndec.feijiayun.chain.common.constant.InChargeDrug;
import com.rograndec.feijiayun.chain.common.constant.SpecialCompoundPreparations;
import com.rograndec.feijiayun.chain.common.constant.SpecialCompoundPreparationsType;
import com.rograndec.feijiayun.chain.common.constant.SpecialDrugsAll;
import com.rograndec.feijiayun.chain.common.constant.SpiritDrugsType;
import com.rograndec.feijiayun.chain.common.constant.StorageTemp;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品详细信息
 * xingjian.lan
 */
public class BaseGoodsDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @ApiModelProperty(value = "剂型ID",hidden=true)
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID",hidden=true)
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
    @ApiModelProperty(value = "生产厂商ID",hidden=true)
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地")
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）",hidden=true)
    private Integer businessVariety;

    /**
     * 品种类别名称（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value = "品种类别")
    private String businessVarietyName;

    /**
     * 商品分类
     */
    @ApiModelProperty(value = "商品分类")
    private String categoryName;

    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片）
     */
    @ApiModelProperty(value = "商品属性（0-成药；1-中药材；2-中药饮片）",hidden=true)
    private Integer goodsAttribute;

    /**
     * 商品属性名称（0-成药；1-中药材；2-中药饮片）
     */
    @ApiModelProperty(value = "商品属性")
    private String goodsAttributeName;

    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value = "国产/进口（0-国产；1-进口",hidden=true)
    private Integer domesticImport;

    /**
     * 国产/进口描述（0-国产；1-进口）
     */
    @ApiModelProperty(value = "国产/进口")
    private String domesticImportDesc;

    /**
     * 经营范围名称
     */
    @ApiModelProperty(value = "经营范围名称")
    private String managementScopeName;

    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value = "特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）",hidden=true)
    private Integer specialDrug;
    
    /**
     * 精神药品分类（0-一类；1-二类）
     */
    @ApiModelProperty(value="精神药品分类（0-一类；1-二类）")
    private Integer spiritDrugType;

    /**
     * 特殊管理药品名称：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value = "特殊管理药品")
    private String specialDrugName;

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value = "专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）",hidden=true)
    private Integer inChargeDrug;
    
    /**
     *  含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）
     */
    @ApiModelProperty(value="含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
    private Integer formulationType;

    /**
     * 专管药品名称（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value = "专管药品名称")
    private String inChargeDrugName;

    /**
     * 限购数量
     */
    @ApiModelProperty(value = "限购数量")
    private BigDecimal limitQuantity;

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty(value = "贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）",hidden=true)
    private Integer storageTemp;

    /**
     * 贮藏温度描述（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty(value = "贮藏温度描述")
    private String storageTempDesc;

    /**
     * 贮藏条件名称
     */
    @ApiModelProperty(value = "贮藏条件名称")
    private String storageConditionName;

    /**
     * 保质期
     */
    @ApiModelProperty(value = " 保质期")
    private Integer qualityPeriod;

    /**
     * 保质期单位（0-日；1-月；2-年）
     */
    @ApiModelProperty(value = "保质期单位（0-日；1-月；2-年）",hidden=true)
    private Integer qualityPeriodUnit;

    /**
     * 保质期描述
     */
    @ApiModelProperty(value = " 保质期描述")
    private String qualityPeriodDesc;

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

    public Integer getGoodsAttribute() {
        return goodsAttribute;
    }

    public void setGoodsAttribute(Integer goodsAttribute) {
        this.goodsAttribute = goodsAttribute;
    }

    public Integer getDomesticImport() {
        return domesticImport;
    }

    public void setDomesticImport(Integer domesticImport) {
        this.domesticImport = domesticImport;
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

    public String getBusinessVarietyName() {
        if (businessVariety == null) {
            return null;
        }
        return BusinessVariety.getName(businessVariety);
    }

    public void setBusinessVarietyName(String businessVarietyName) {
        this.businessVarietyName = businessVarietyName;
    }

    public String getGoodsAttributeName() {
        if (goodsAttribute == null) {
            return null;
        }
        return GoodsAttribute2DrugsA.getName(goodsAttribute);
    }

    public void setGoodsAttributeName(String goodsAttributeName) {
        this.goodsAttributeName = goodsAttributeName;
    }

    public String getSpecialDrugName() {
    	 StringBuilder specilDrug = new StringBuilder();
         if (specialDrug != null){
             specilDrug.append(SpecialDrugsAll.getName(specialDrug) == null?"":SpecialDrugsAll.getName(specialDrug));
             if(specialDrug == SpecialDrugsAll.SPIRIT_DRUGS.getCode()){
                 if(spiritDrugType != null && spiritDrugType != -1){
                	 if(SpiritDrugsType.getName(spiritDrugType) != null) {
                		 specilDrug.append("-");
                		 specilDrug.append(SpiritDrugsType.getName(spiritDrugType));
                	 }
                 }
             }
             return specilDrug.toString();
         }else{
            return null;
         }
    }

    public void setSpecialDrugName(String specialDrugName) {
        this.specialDrugName = specialDrugName;
    }

    public String getInChargeDrugName() {
    	 StringBuilder sb = new StringBuilder();
         if(inChargeDrug != null){
             sb.append(InChargeDrug.getName(inChargeDrug) == null?"":InChargeDrug.getName(inChargeDrug));
             if (inChargeDrug == InChargeDrug.SPECIAL.getCode()){
                 if(formulationType != null && formulationType != -1){
                	 if(SpecialCompoundPreparationsType.getName(formulationType) != null) {
                		 sb.append("-");
                		 sb.append(SpecialCompoundPreparationsType.getName(formulationType));
                	 }
                 }
             }
             return sb.toString();
         }else {
        	 return null;
         }
    }

    public void setInChargeDrugName(String inChargeDrugName) {
        this.inChargeDrugName = inChargeDrugName;
    }

    public String getStorageTempDesc() {
        if (storageTemp == null) {
            return null;
        }
        return StorageTemp.getName(storageTemp);
    }

    public void setStorageTempDesc(String storageTempDesc) {
        this.storageTempDesc = storageTempDesc;
    }

    public String getQualityPeriodDesc() {
        String unitDesc = "";
        if (qualityPeriodUnit == null) {
            return unitDesc;
        }
        switch (qualityPeriodUnit) {
            case 0:
                unitDesc = "日";
                break;
            case 1:
                unitDesc = "月";
                break;
            case 2:
                unitDesc = "年";
                break;
            default:
                break;
        }
        return qualityPeriod + "" + unitDesc;
    }

    public void setQualityPeriodDesc(String qualityPeriodDesc) {
        this.qualityPeriodDesc = qualityPeriodDesc;
    }

    public String getDomesticImportDesc() {
        if (domesticImport == null) {
            return "";
        }
        switch (domesticImport) {
            case 0:
                return "国产";
            case 1:
                return "进口";
            default:
                break;
        }
        return "";
    }

    public void setDomesticImportDesc(String domesticImportDesc) {
        this.domesticImportDesc = domesticImportDesc;
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

}