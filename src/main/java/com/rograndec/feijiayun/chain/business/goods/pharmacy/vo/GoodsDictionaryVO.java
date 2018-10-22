package com.rograndec.feijiayun.chain.business.goods.pharmacy.vo;

import com.rograndec.feijiayun.chain.common.constant.GoodsAttribute2DrugsA;
import com.rograndec.feijiayun.chain.common.constant.SpecialCompoundPreparations;
import com.rograndec.feijiayun.chain.common.constant.SpecialDrugsAll;
import com.rograndec.feijiayun.chain.common.constant.StorageTemp;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by xingjian.lan on 2017/10/19.
 */

@ApiModel(value = "GoodsDictionaryVO", description = "药学-药品词典药品信息")
public class GoodsDictionaryVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long id;

    /**
     * 标准库ID
     */
    @ApiModelProperty(value = "标准库ID")
    private Long standardLibraryId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String code;

    /**
     * 条形码
     */
    @ApiModelProperty(value = "条形码")
    private String barcode;

    /**
     * 通用名称
     */
    @ApiModelProperty(value = "通用名称")
    private String genericName;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String name;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String specification;

    /**
     * 库存计量单位ID
     */
    @ApiModelProperty(value = "库存计量单位ID")
    private Long unitId;

    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(value = "库存计量单位名称")
    private String unitName;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 产地
     */
    @ApiModelProperty(value = "产地")
    private String place;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;


    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片）
     */
    @ApiModelProperty(value = "商品属性（0-成药；1-中药材；2-中药饮片）")
    private Integer goodsAttribute;

    /**
     * 商品属性名称（0-成药；1-中药材；2-中药饮片）
     */
    private String goodsAttributeName;

    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value = "国产/进口（0-国产；1-进口）")
    private Integer domesticImport;

    /**
     * 国产/进口描述（0-国产；1-进口）
     */
    private String domesticImportDesc;

    /**
     * 经营范围名称
     */
    @ApiModelProperty(value = "经营范围名称")
    private String managementScopeName;

    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value = "特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private Integer specialDrug;

    /**
     * 特殊管理药品名称：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value = "剂型名称")
    private String specialDrugName;

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value = "专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private Integer inChargeDrug;

    /**
     * 专管药品名称（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    private String inChargeDrugName;

    /**
     * 限购数量
     */
    @ApiModelProperty(value = "限购数量")
    private BigDecimal limitQuantity;

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty(value = "贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private Integer storageTemp;

    /**
     * 贮藏温度描述（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    private String storageTempDesc;

    /**
     * 贮藏条件名称
     */
    @ApiModelProperty(value = "贮藏条件名称")
    private String storageConditionName;

    /**
     * 保质期
     */
    @ApiModelProperty(value = "保质期")
    private Integer qualityPeriod;

    /**
     * 保质期单位（0-日；1-月；2-年）
     */
    @ApiModelProperty(value = "保质期单位（0-日；1-月；2-年）")
    private Integer qualityPeriodUnit;

    /**
     * 保质期描述
     */
    private String qualityPeriodDesc;

    /**
     * 注册商标
     */
    @ApiModelProperty(value = "注册商标")
    private String registeredTrademark;

    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌")
    private String brand;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStandardLibraryId() {
        return standardLibraryId;
    }

    public void setStandardLibraryId(Long standardLibraryId) {
        this.standardLibraryId = standardLibraryId;
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
        if (limitQuantity != null) {
            limitQuantity.intValue();
        }
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
        if (specialDrug == null) {
            return null;
        }
        return SpecialDrugsAll.getName(specialDrug);
    }

    public void setSpecialDrugName(String specialDrugName) {
        this.specialDrugName = specialDrugName;
    }

    public String getInChargeDrugName() {
        if (inChargeDrug == null) {
            return null;
        }
        return SpecialCompoundPreparations.getName(inChargeDrug);
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
        if(null != qualityPeriodUnit) {
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
        }
        return qualityPeriod + "" + unitDesc;
    }

    public void setQualityPeriodDesc(String qualityPeriodDesc) {
        this.qualityPeriodDesc = qualityPeriodDesc;
    }

    public String getDomesticImportDesc() {
    	if(null != domesticImport) {
    		switch (domesticImport) {
            case 0:
                return "国产";
            case 1:
                return "进口";
            default:
                break;
        }
    	}
        return "";
    }

    public void setDomesticImportDesc(String domesticImportDesc) {
        this.domesticImportDesc = domesticImportDesc;
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
}
