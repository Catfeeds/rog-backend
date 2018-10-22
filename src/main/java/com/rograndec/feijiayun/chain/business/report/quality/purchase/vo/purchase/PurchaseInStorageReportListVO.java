package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class PurchaseInStorageReportListVO implements Serializable {

    @ApiModelProperty("页码")
    private Integer pageNo;

    @ApiModelProperty("每页显示数量")
    private Integer pageSize;

    @ApiModelProperty(value = "排序参数,就是列名")
    private String orderName;

    @ApiModelProperty("不需要传")
    private Integer pageStart;

    @ApiModelProperty(value = "排序方式,ASC或者DESC")
    private String orderType;

    @ApiModelProperty(value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private Integer businessVariety;

    @ApiModelProperty(value = "查询输入信息")
    private String key;

    @ApiModelProperty(value = "入库日期开始日期")
    private String startDate;

    @ApiModelProperty(value = "入库日期结束日期")
    private String endDate;

    @ApiModelProperty(value = "供货单位编码")
    private String supplierCode;

    @ApiModelProperty(value = "供货单位名称")
    private String supplierName;

    @ApiModelProperty(value = "入库单号")
    private String code;

    @ApiModelProperty(value = "入库人员")
    private String storageManName;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "商品分类")
    private Integer categoryId;

    @ApiModelProperty(value = "国产/进口（0-国产；1-进口）")
    private Integer domesticImport;

    @ApiModelProperty(value = "贮藏温度")
    private Integer storageTemp;

    @ApiModelProperty(value = "贮藏条件")
    private String storageConditionName;

    @ApiModelProperty(value = "注册商标")
    private String registeredTrademark;

    @ApiModelProperty(value = "品牌")
    private String brand;

    @ApiModelProperty(value = "商品属性（0-成药；1-中药材；2-中药饮片）")
    private Integer goodsAttribute;

    @ApiModelProperty(value = "商品属性-是否为处方药，0：非处方药，1：处方药'")
    private Integer prescriptionDrug;

    @ApiModelProperty(value = "'非处方药类别：0-甲类；1-乙类；'")
    private Integer otcType;

    @ApiModelProperty(value = "经营范围")
    private Integer managementScopeId;

    @ApiModelProperty(value = "特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private Integer specialDrug;

    @ApiModelProperty(value = "精神药品分类（0-一类；1-二类）")
    private Integer spiritDrugType;

    @ApiModelProperty(value = "专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private Integer inChargeDrug;

    @ApiModelProperty(value = "含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
    private Integer formulationType;

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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(Integer businessVariety) {
        this.businessVariety = businessVariety;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStorageManName() {
        return storageManName;
    }

    public void setStorageManName(String storageManName) {
        this.storageManName = storageManName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getManagementScopeId() {
        return managementScopeId;
    }

    public void setManagementScopeId(Integer managementScopeId) {
        this.managementScopeId = managementScopeId;
    }

    public Integer getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
    }

    public Integer getSpiritDrugType() {
        return spiritDrugType;
    }

    public void setSpiritDrugType(Integer spiritDrugType) {
        this.spiritDrugType = spiritDrugType;
    }

    public Integer getInChargeDrug() {
        return inChargeDrug;
    }

    public void setInChargeDrug(Integer inChargeDrug) {
        this.inChargeDrug = inChargeDrug;
    }

    public Integer getFormulationType() {
        return formulationType;
    }

    public void setFormulationType(Integer formulationType) {
        this.formulationType = formulationType;
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }
}
