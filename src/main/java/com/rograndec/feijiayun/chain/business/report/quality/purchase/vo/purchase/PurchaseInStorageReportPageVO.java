package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PurchaseInStorageReportPageVO implements Serializable {

    /**
     * 单据（入库单）编号
     */
    @ApiModelProperty(value = "单据（入库单）编号", required = true)
    private String code;

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期", required = true)
    private Date inStorageDate;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码", required = true)
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称", required = true)
    private String supplierName;

    /**
     * 供货单位销售人员名称
     */
    @ApiModelProperty(value = "供货单位销售人员名称", required = true)
    private String supplierSalerName;

    /**
     * 供货单位销售人员联系电话
     */
    @ApiModelProperty(value = "供货单位销售人员联系电话", required = true)
    private String supplierSalerPhone;

    /**
     * 入库人员名称
     */
    @ApiModelProperty(value = "入库人员名称", required = true)
    private String storageManName;

    /**
     * 采购订单单号
     */
    @ApiModelProperty(value = "采购订单单号", required = true)
    private String purchaseOrderCode;

    /**
     * 采购验收单号(基础单据编码)
     */
    @ApiModelProperty(value = "采购验收单号(基础单据编码)", required = true)
    private String baseOrderCode;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码", required = true)
    private String goodsCode;

    /**
     * 条形码
     */
    @ApiModelProperty(value = "条形码", required = true)
    private String barcode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", required = true)
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称", required = true)
    private String goodsGenericName;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称", required = true)
    private String dosageName;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称", required = true)
    private String unitName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格", required = true)
    private String goodsSpecification;

    /**
     * 生产企业
     */
    @ApiModelProperty(value = "生产企业", required = true)
    private String manufacturer;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地", required = true)
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号", required = true)
    private String approvalNumber;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号", required = true)
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期", required = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期", required = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validDate;

    /**
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID", required = true)
    private Integer shelfId;

    /**
     * 货位
     */
    @ApiModelProperty(value = "货位", required = true)
    private String shelfName;

    /**
     * 质量状况
     */
    @ApiModelProperty(value = "质量状况", required = true)
    private String shelfStatusDesc;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量", required = true)
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价", required = true)
    private BigDecimal unitPrice;

    /**
     * 折扣
     */
    @ApiModelProperty(value = "折扣", required = true)
    private BigDecimal lineDiscount;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额", required = true)
    private BigDecimal amount;

    /**
     * 整单折扣
     */
    @ApiModelProperty(value = "整单折扣", required = true)
    private BigDecimal wholeDiscount;

    /**
     * 优惠分摊
     */
    @ApiModelProperty(value = "优惠分摊", required = true)
    private BigDecimal lineDiscountAmount;


    /**
     * 实际单价
     */
    @ApiModelProperty(value = "实际单价", required = true)
    private BigDecimal realPrice;

    /**
     * 实际金额
     */
    @ApiModelProperty(value = "实际金额", required = true)
    private BigDecimal realAmount;

    /**
     * 税率
     */
    @ApiModelProperty(value = "税率", required = true)
    private BigDecimal taxRate;

    /**
     * 不含税单价
     */
    @ApiModelProperty(value = "不含税单价", required = true)
    private BigDecimal notaxRealPrice;

    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额", required = true)
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额", required = true)
    private BigDecimal taxAmount;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    private Integer status;

    /**
     * 状态名称
     */
    @ApiModelProperty(value = "状态名称", required = true)
    private String statusName;


    /**
     * 品种类别ID（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value = "品种类别ID（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）", required = true)
    private Integer businessVariety;

    /**
     * 品种类别名称
     */
    @ApiModelProperty(value = "品种类别名称", required = true)
    private String businessVarietyName;

    /**
     * 商品分类
     */
    @ApiModelProperty(value = "商品分类", required = true)
    private String categoryName;

    /**
     * 列表展现商品属性
     */
    @ApiModelProperty(value = "列表展现商品属性", required = true)
    private String goodsAttributeAll;

    /**
     * 商品属性ID（0-成药；1-中药材；2-中药饮片）
     */
    @ApiModelProperty(value = "商品属性ID（0-成药；1-中药材；2-中药饮片）", required = true)
    private Integer goodsAttribute;

    /**
     * 商品属性名称
     */
    @ApiModelProperty(value = "商品属性名称", required = true)
    private String goodsAttributeName;

    /**
     * 商品属性-是否为处方药，0：非处方药，1：处方药
     */
    @ApiModelProperty(value = "商品属性-是否为处方药，0：非处方药，1：处方药", required = true)
    private Integer prescriptionDrug;

    /**
     * 商品属性-是否为处方药 名称
     */
    @ApiModelProperty(value = "商品属性-是否为处方药 名称", required = true)
    private String prescriptionDrugName;

    /**
     * 非处方药类别：0-甲类；1-乙类
     */
    @ApiModelProperty(value = "非处方药类别：0-甲类；1-乙类", required = true)
    private Integer otcType;

    /**
     * 非处方药类别 名称
     */
    @ApiModelProperty(value = "非处方药类别 名称", required = true)
    private String otcTypeName;

    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value = "国产/进口（0-国产；1-进口）", required = true)
    private Integer domesticImport;

    /**
     * 国产/进口 名称
     */
    @ApiModelProperty(value = "国产/进口 名称", required = true)
    private String domesticImportName;

    /**
     * 经营范围名称
     */
    @ApiModelProperty(value = "经营范围名称", required = true)
    private String managementScopeName;

    /**
     * 列表展现特殊管理药品
     */
    @ApiModelProperty(value = "列表展现特殊管理药品", required = true)
    private String specialDrugAll;

    /**
     * 特殊管理药品ID：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value = "特殊管理药品ID：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）", required = true)
    private Integer specialDrug;

    /**
     * 特殊管理药品名称
     */
    @ApiModelProperty(value = "特殊管理药品名称", required = true)
    private String specialDrugName;

    /**
     * 精神药品分类ID（0-一类；1-二类）
     */
    @ApiModelProperty(value = "精神药品分类ID（0-一类；1-二类）", required = true)
    private Integer spiritDrugType;

    /**
     * 精神药品分类名称
     */
    @ApiModelProperty(value = "精神药品分类名称", required = true)
    private String spiritDrugTypeName;

    /**
     * 列表展现专管药品
     */
    @ApiModelProperty(value = "列表展现专管药品", required = true)
    private String inChargeDrugAll;

    /**
     * 专管药品ID（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value = "专管药品ID（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）", required = true)
    private Integer inChargeDrug;

    /**
     * 专管药品名称
     */
    @ApiModelProperty(value = "专管药品名称", required = true)
    private String inChargeDrugName;

    /**
     * 含特殊药品复方制剂类型ID（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）
     */
    @ApiModelProperty(value = "含特殊药品复方制剂类型ID（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）", required = true)
    private Integer formulationType;

    /**
     * 含特殊药品复方制剂类型名称
     */
    @ApiModelProperty(value = "含特殊药品复方制剂类型名称", required = true)
    private String formulationTypeName;

    /**
     * 限购数量
     */
    @ApiModelProperty(value = "限购数量", required = true)
    private BigDecimal limitQuantity;

    /**
     * 贮藏温度ID（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty(value = "贮藏温度ID（0-常温；1-阴凉；2-冷藏；3-冷冻）", required = true)
    private Integer storageTemp;

    /**
     * 贮藏温度名称
     */
    @ApiModelProperty(value = "贮藏温度名称", required = true)
    private String storageTempName;

    /**
     * 贮藏条件名称
     */
    @ApiModelProperty(value = "贮藏条件名称", required = true)
    private String storageConditionName;

    /**
     * 列表展现保质期
     */
    @ApiModelProperty(value = "列表展现保质期", required = true)
    private String qualityPeriodAll;

    /**
     * 保质期
     */
    @ApiModelProperty(value = "保质期", required = true)
    private Integer qualityPeriod;

    /**
     * 保质期单位ID（0-日；1-月；2-年）
     */
    @ApiModelProperty(value = "保质期单位ID（0-日；1-月；2-年）", required = true)
    private Integer qualityPeriodUnit;

    /**
     * 保质期单位名称
     */
    @ApiModelProperty(value = "保质期单位名称", required = true)
    private String qualityPeriodUnitName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getInStorageDate() {
        return inStorageDate;
    }

    public void setInStorageDate(Date inStorageDate) {
        this.inStorageDate = inStorageDate;
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

    public String getSupplierSalerName() {
        return supplierSalerName;
    }

    public void setSupplierSalerName(String supplierSalerName) {
        this.supplierSalerName = supplierSalerName;
    }

    public String getSupplierSalerPhone() {
        return supplierSalerPhone;
    }

    public void setSupplierSalerPhone(String supplierSalerPhone) {
        this.supplierSalerPhone = supplierSalerPhone;
    }

    public String getStorageManName() {
        return storageManName;
    }

    public void setStorageManName(String storageManName) {
        this.storageManName = storageManName;
    }

    public String getPurchaseOrderCode() {
        return purchaseOrderCode;
    }

    public void setPurchaseOrderCode(String purchaseOrderCode) {
        this.purchaseOrderCode = purchaseOrderCode;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
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

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
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

    public Integer getShelfId() {
        return shelfId;
    }

    public void setShelfId(Integer shelfId) {
        this.shelfId = shelfId;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineDiscount() {
        return lineDiscount;
    }

    public void setLineDiscount(BigDecimal lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getLineDiscountAmount() {
        return lineDiscountAmount;
    }

    public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
        this.lineDiscountAmount = lineDiscountAmount;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getNotaxRealPrice() {
        return notaxRealPrice;
    }

    public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
        this.notaxRealPrice = notaxRealPrice;
    }

    public BigDecimal getNotaxRealAmount() {
        return notaxRealAmount;
    }

    public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
        this.notaxRealAmount = notaxRealAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(Integer businessVariety) {
        this.businessVariety = businessVariety;
    }

    public String getBusinessVarietyName() {
        return businessVarietyName;
    }

    public void setBusinessVarietyName(String businessVarietyName) {
        this.businessVarietyName = businessVarietyName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getGoodsAttributeAll() {
        return goodsAttributeAll;
    }

    public void setGoodsAttributeAll(String goodsAttributeAll) {
        this.goodsAttributeAll = goodsAttributeAll;
    }

    public Integer getGoodsAttribute() {
        return goodsAttribute;
    }

    public void setGoodsAttribute(Integer goodsAttribute) {
        this.goodsAttribute = goodsAttribute;
    }

    public String getGoodsAttributeName() {
        return goodsAttributeName;
    }

    public void setGoodsAttributeName(String goodsAttributeName) {
        this.goodsAttributeName = goodsAttributeName;
    }

    public Integer getPrescriptionDrug() {
        return prescriptionDrug;
    }

    public void setPrescriptionDrug(Integer prescriptionDrug) {
        this.prescriptionDrug = prescriptionDrug;
    }

    public String getPrescriptionDrugName() {
        return prescriptionDrugName;
    }

    public void setPrescriptionDrugName(String prescriptionDrugName) {
        this.prescriptionDrugName = prescriptionDrugName;
    }

    public Integer getOtcType() {
        return otcType;
    }

    public void setOtcType(Integer otcType) {
        this.otcType = otcType;
    }

    public String getOtcTypeName() {
        return otcTypeName;
    }

    public void setOtcTypeName(String otcTypeName) {
        this.otcTypeName = otcTypeName;
    }

    public Integer getDomesticImport() {
        return domesticImport;
    }

    public void setDomesticImport(Integer domesticImport) {
        this.domesticImport = domesticImport;
    }

    public String getDomesticImportName() {
        return domesticImportName;
    }

    public void setDomesticImportName(String domesticImportName) {
        this.domesticImportName = domesticImportName;
    }

    public String getManagementScopeName() {
        return managementScopeName;
    }

    public void setManagementScopeName(String managementScopeName) {
        this.managementScopeName = managementScopeName;
    }

    public String getSpecialDrugAll() {
        return specialDrugAll;
    }

    public void setSpecialDrugAll(String specialDrugAll) {
        this.specialDrugAll = specialDrugAll;
    }

    public Integer getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
    }

    public String getSpecialDrugName() {
        return specialDrugName;
    }

    public void setSpecialDrugName(String specialDrugName) {
        this.specialDrugName = specialDrugName;
    }

    public Integer getSpiritDrugType() {
        return spiritDrugType;
    }

    public void setSpiritDrugType(Integer spiritDrugType) {
        this.spiritDrugType = spiritDrugType;
    }

    public String getSpiritDrugTypeName() {
        return spiritDrugTypeName;
    }

    public void setSpiritDrugTypeName(String spiritDrugTypeName) {
        this.spiritDrugTypeName = spiritDrugTypeName;
    }

    public String getInChargeDrugAll() {
        return inChargeDrugAll;
    }

    public void setInChargeDrugAll(String inChargeDrugAll) {
        this.inChargeDrugAll = inChargeDrugAll;
    }

    public Integer getInChargeDrug() {
        return inChargeDrug;
    }

    public void setInChargeDrug(Integer inChargeDrug) {
        this.inChargeDrug = inChargeDrug;
    }

    public String getInChargeDrugName() {
        return inChargeDrugName;
    }

    public void setInChargeDrugName(String inChargeDrugName) {
        this.inChargeDrugName = inChargeDrugName;
    }

    public Integer getFormulationType() {
        return formulationType;
    }

    public void setFormulationType(Integer formulationType) {
        this.formulationType = formulationType;
    }

    public String getFormulationTypeName() {
        return formulationTypeName;
    }

    public void setFormulationTypeName(String formulationTypeName) {
        this.formulationTypeName = formulationTypeName;
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

    public String getStorageTempName() {
        return storageTempName;
    }

    public void setStorageTempName(String storageTempName) {
        this.storageTempName = storageTempName;
    }

    public String getStorageConditionName() {
        return storageConditionName;
    }

    public void setStorageConditionName(String storageConditionName) {
        this.storageConditionName = storageConditionName;
    }

    public String getQualityPeriodAll() {
        return qualityPeriodAll;
    }

    public void setQualityPeriodAll(String qualityPeriodAll) {
        this.qualityPeriodAll = qualityPeriodAll;
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

    public String getQualityPeriodUnitName() {
        return qualityPeriodUnitName;
    }

    public void setQualityPeriodUnitName(String qualityPeriodUnitName) {
        this.qualityPeriodUnitName = qualityPeriodUnitName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "PurchaseInStorageReportPageVO[" +
                "code='" + code + '\'' +
                ", inStorageDate=" + inStorageDate +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierSalerName='" + supplierSalerName + '\'' +
                ", supplierSalerPhone='" + supplierSalerPhone + '\'' +
                ", storageManName='" + storageManName + '\'' +
                ", purchaseOrderCode='" + purchaseOrderCode + '\'' +
                ", baseOrderCode='" + baseOrderCode + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", barcode='" + barcode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", unitName='" + unitName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", shelfId=" + shelfId +
                ", shelfName='" + shelfName + '\'' +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", statusName=" + statusName +
                ", lineDiscount=" + lineDiscount +
                ", amount=" + amount +
                ", wholeDiscount=" + wholeDiscount +
                ", lineDiscountAmount=" + lineDiscountAmount +
                ", realPrice=" + realPrice +
                ", realAmount=" + realAmount +
                ", taxRate=" + taxRate +
                ", notaxRealPrice=" + notaxRealPrice +
                ", notaxRealAmount=" + notaxRealAmount +
                ", taxAmount=" + taxAmount +
                ", status=" + status +
                ", businessVariety=" + businessVariety +
                ", businessVarietyName='" + businessVarietyName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", goodsAttributeAll='" + goodsAttributeAll + '\'' +
                ", goodsAttribute=" + goodsAttribute +
                ", goodsAttributeName='" + goodsAttributeName + '\'' +
                ", prescriptionDrug=" + prescriptionDrug +
                ", prescriptionDrugName='" + prescriptionDrugName + '\'' +
                ", otcType=" + otcType +
                ", otcTypeName='" + otcTypeName + '\'' +
                ", domesticImport=" + domesticImport +
                ", domesticImportName='" + domesticImportName + '\'' +
                ", managementScopeName='" + managementScopeName + '\'' +
                ", specialDrugAll='" + specialDrugAll + '\'' +
                ", specialDrug=" + specialDrug +
                ", specialDrugName='" + specialDrugName + '\'' +
                ", spiritDrugType=" + spiritDrugType +
                ", spiritDrugTypeName='" + spiritDrugTypeName + '\'' +
                ", inChargeDrugAll='" + inChargeDrugAll + '\'' +
                ", inChargeDrug=" + inChargeDrug +
                ", inChargeDrugName='" + inChargeDrugName + '\'' +
                ", formulationType=" + formulationType +
                ", formulationTypeName='" + formulationTypeName + '\'' +
                ", limitQuantity=" + limitQuantity +
                ", storageTemp=" + storageTemp +
                ", storageTempName='" + storageTempName + '\'' +
                ", storageConditionName='" + storageConditionName + '\'' +
                ", qualityPeriodAll='" + qualityPeriodAll + '\'' +
                ", qualityPeriod=" + qualityPeriod +
                ", qualityPeriodUnit=" + qualityPeriodUnit +
                ", qualityPeriodUnitName='" + qualityPeriodUnitName + '\'' +
                ']';
    }
}
