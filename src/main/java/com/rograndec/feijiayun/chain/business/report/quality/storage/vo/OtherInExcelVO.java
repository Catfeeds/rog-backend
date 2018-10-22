package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.DomesticImport;
import com.rograndec.feijiayun.chain.common.constant.InType;
import com.rograndec.feijiayun.chain.common.constant.StorageTemp;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class OtherInExcelVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 组织机构编码
     */
    @ApiModelProperty(value = "组织机构编码")
    private String enterpriseCode;

    /**
     * 组织机构名称
     */
    @ApiModelProperty(value = "组织机构名称")
    private String enterpriseName;

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期")
    private String inDate;

    /**
     * 入库单号
     */
    @ApiModelProperty(value = "入库单号")
    private String code;

    /**
     * 入库人员名称
     */
    @ApiModelProperty(value = "入库人员名称")
    private String inManName;

    /**
     * 入库类型（0-获赠；1-报溢；2-领用退回；3-其它）
     */
    @ApiModelProperty(value = "入库类型（0-获赠；1-报溢；2-领用退回；3-其它）")
    private String inType;

    /**
     * 来源单位名称
     */
    @ApiModelProperty(value = "来源单位名称")
    private String srcUnitName;

    /**
     * 流通监管码
     */
    @ApiModelProperty(value = "流通监管码")
    private String flowCode;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    @ApiModelProperty(value="条形码")
    private String barcode;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;

    @ApiModelProperty(value="单位ID")
    private Long unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

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
    private String goodsPlace;

    @ApiModelProperty(value="批准文号")
    private String approvalNumber;

    @ApiModelProperty(value="批号")
    private String lotNum;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private String productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private String validDate;

    /**
     * 源货位ID
     */
    @ApiModelProperty(value = "源货位ID")
    private Long shelfId;

    /**
     * 源货位名称
     */
    @ApiModelProperty(value = "源货位名称")
    private String shelfName;

    /**
     * 源货位质量状况
     */
    @ApiModelProperty(value = "源货位质量状况")
    private String shelfStatusDesc;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量" ,required = true)
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价" ,required = true)
    private BigDecimal unitPrice;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额" ,required = true)
    private BigDecimal amount;

    /**
     * 进项税ID
     */
    @ApiModelProperty(value = "进项税ID" ,required = true)
    private Long taxRateId;

    /**
     * 进项税
     */
    @ApiModelProperty(value = "进项税")
    private String taxRate;

    /**
     * 不含税单价
     */
    @ApiModelProperty(value = "不含税单价" )
    private BigDecimal notaxPrice;

    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额" )
    private BigDecimal notaxAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额" )
    private BigDecimal taxAmount;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态
     */
    @ApiModelProperty(value = "0-禁用 1- 启用")
    private String status;

    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value="品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private String businessVariety;

    /**
     * 商品分类ID
     */
    @ApiModelProperty(value="商品分类ID")
    private Long categoryId;

    @ApiModelProperty(value="商品分类名称")
    private String categoryName;

    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片）
     */
    @ApiModelProperty(value="商品属性（0-成药；1-中药材；2-中药饮片）")
    private String goodsAttribute;

    @ApiModelProperty(value="商品属性-----用于前台显示")
    private String goodsAttributeDetail;

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
     * 品种类别为3-化妆品，商品属性为1-特殊用途化妆品时，化妆品类别［0-育发；1-染发；2-烫发；3-脱毛；4-美乳；5-健美；6-除臭；7-祛斑；8-防嗮］
     */
    @ApiModelProperty(value="品种类别为3-化妆品，商品属性为1-特殊用途化妆品时，化妆品类别［0-育发；1-染发；2-烫发；3-脱毛；4-美乳；5-健美；6-除臭；7-祛斑；8-防嗮］")
    private Integer cosmetics;
    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value="国产/进口（0-国产；1-进口）")
    private String domesticImport;

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
     *
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value="特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private String specialDrug;

    @ApiModelProperty(value="特殊管理药品---前台显示")
    private String specialDrugDetail;

    /**
     * 精神药品分类（0-一类；1-二类）
     */
    @ApiModelProperty(value="精神药品分类（0-一类；1-二类）")
    private Integer spiritDrugType;

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value="专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private String inChargeDrug;

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value="专管药品---前台显示")
    private String inChargeDrugDetail;

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
    private String storageTemp;



    /**
     * 贮藏条件名称
     */
    @ApiModelProperty(value="贮藏条件名称")
    private String storageConditionName;

    /**
     * 保质期
     */
    @ApiModelProperty(value="保质期")
    private String safeTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInManName() {
        return inManName;
    }

    public void setInManName(String inManName) {
        this.inManName = inManName;
    }

    public String getInType() {
        return inType;
    }

    public void setInType(String inType) {
        this.inType = inType;
    }

    public String getSrcUnitName() {
        return srcUnitName;
    }

    public void setSrcUnitName(String srcUnitName) {
        this.srcUnitName = srcUnitName;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
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

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
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

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getNotaxPrice() {
        return notaxPrice;
    }

    public void setNotaxPrice(BigDecimal notaxPrice) {
        this.notaxPrice = notaxPrice;
    }

    public BigDecimal getNotaxAmount() {
        return notaxAmount;
    }

    public void setNotaxAmount(BigDecimal notaxAmount) {
        this.notaxAmount = notaxAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(String businessVariety) {
        this.businessVariety = businessVariety;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public String getGoodsAttributeDetail() {
        return goodsAttributeDetail;
    }

    public void setGoodsAttributeDetail(String goodsAttributeDetail) {
        this.goodsAttributeDetail = goodsAttributeDetail;
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

    public Integer getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(Integer cosmetics) {
        this.cosmetics = cosmetics;
    }

    public String getDomesticImport() {
        return domesticImport;
    }

    public void setDomesticImport(String domesticImport) {
        this.domesticImport = domesticImport;
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

    public String getGoodsAttribute() {
        return goodsAttribute;
    }

    public void setGoodsAttribute(String goodsAttribute) {
        this.goodsAttribute = goodsAttribute;
    }

    public String getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(String specialDrug) {
        this.specialDrug = specialDrug;
    }

    public String getInChargeDrug() {
        return inChargeDrug;
    }

    public void setInChargeDrug(String inChargeDrug) {
        this.inChargeDrug = inChargeDrug;
    }

    public String getSpecialDrugDetail() {
        return specialDrugDetail;
    }

    public void setSpecialDrugDetail(String specialDrugDetail) {
        this.specialDrugDetail = specialDrugDetail;
    }

    public Integer getSpiritDrugType() {
        return spiritDrugType;
    }

    public void setSpiritDrugType(Integer spiritDrugType) {
        this.spiritDrugType = spiritDrugType;
    }



    public String getInChargeDrugDetail() {
        return inChargeDrugDetail;
    }

    public void setInChargeDrugDetail(String inChargeDrugDetail) {
        this.inChargeDrugDetail = inChargeDrugDetail;
    }

    public Integer getFormulationType() {
        return formulationType;
    }

    public void setFormulationType(Integer formulationType) {
        this.formulationType = formulationType;
    }

    public BigDecimal getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(BigDecimal limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public String getStorageTemp() {
        return storageTemp;
    }

    public void setStorageTemp(String storageTemp) {
        this.storageTemp = storageTemp;
    }


    public String getStorageConditionName() {
        return storageConditionName;
    }

    public void setStorageConditionName(String storageConditionName) {
        this.storageConditionName = storageConditionName;
    }

    public String getSafeTime() {
        return safeTime;
    }

    public void setSafeTime(String safeTime) {
        this.safeTime = safeTime;
    }

    @Override
    public String toString() {
        return "OtherInExcelPageVO{" +
                "id=" + id +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", inDate='" + inDate + '\'' +
                ", code='" + code + '\'' +
                ", inManName='" + inManName + '\'' +
                ", inType='" + inType + '\'' +
                ", srcUnitName='" + srcUnitName + '\'' +
                ", flowCode='" + flowCode + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", barcode='" + barcode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageId=" + dosageId +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", lotNum='" + lotNum + '\'' +
                ", productDate='" + productDate + '\'' +
                ", validDate='" + validDate + '\'' +
                ", shelfId=" + shelfId +
                ", shelfName='" + shelfName + '\'' +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                ", taxRateId=" + taxRateId +
                ", taxRate='" + taxRate + '\'' +
                ", notaxPrice=" + notaxPrice +
                ", notaxAmount=" + notaxAmount +
                ", taxAmount=" + taxAmount +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", businessVariety='" + businessVariety + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", goodsAttribute=" + goodsAttribute +
                ", goodsAttributeDetail='" + goodsAttributeDetail + '\'' +
                ", prescriptionDrug=" + prescriptionDrug +
                ", otcType=" + otcType +
                ", cosmetics=" + cosmetics +
                ", domesticImport=" + domesticImport +
                ", managementScopeId=" + managementScopeId +
                ", managementScopeName='" + managementScopeName + '\'' +
                ", specialDrug=" + specialDrug +
                ", specialDrugDetail='" + specialDrugDetail + '\'' +
                ", spiritDrugType=" + spiritDrugType +
                ", inChargeDrug=" + inChargeDrug +
                ", inChargeDrugDetail='" + inChargeDrugDetail + '\'' +
                ", formulationType=" + formulationType +
                ", limitQuantity=" + limitQuantity +
                ", storageTemp=" + storageTemp +

                ", storageConditionName='" + storageConditionName + '\'' +
                ", safeTime='" + safeTime + '\'' +
                '}';
    }

    public static OtherInExcelVO convertTOExcel(OtherInReportPageVO otherInVO) {
        OtherInExcelVO excelVO = new OtherInExcelVO();
        excelVO.setId(otherInVO.getId());
        excelVO.setEnterpriseCode(otherInVO.getEnterpriseCode());
        excelVO.setEnterpriseName(otherInVO.getEnterpriseName());
        excelVO.setInDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(otherInVO.getInDate()));
        excelVO.setCode(otherInVO.getCode());
        excelVO.setInManName(otherInVO.getInManName());
        excelVO.setInType(InType.getName(otherInVO.getInType()));
        excelVO.setSrcUnitName(otherInVO.getSrcUnitName());
        excelVO.setFlowCode(otherInVO.getFlowCode());
        excelVO.setGoodsCode(otherInVO.getGoodsCode());
        excelVO.setBarcode(otherInVO.getBarcode());

        excelVO.setGoodsName(otherInVO.getGoodsName());
        excelVO.setGoodsGenericName(otherInVO.getGoodsGenericName());
        excelVO.setDosageName(otherInVO.getDosageName());
        excelVO.setGoodsSpecification(otherInVO.getGoodsSpecification());
        excelVO.setUnitName(otherInVO.getUnitName());
        excelVO.setManufacturer(otherInVO.getManufacturer());
        excelVO.setGoodsPlace(otherInVO.getGoodsPlace());
        excelVO.setApprovalNumber(otherInVO.getApprovalNumber());
        excelVO.setLotNum(otherInVO.getLotNum());
        excelVO.setProductDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(otherInVO.getProductDate()));
        excelVO.setValidDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(otherInVO.getValidDate()));
        excelVO.setShelfName(otherInVO.getShelfName());
        excelVO.setShelfStatusDesc(otherInVO.getShelfStatusDesc());
        excelVO.setQuantity(otherInVO.getQuantity());
        excelVO.setUnitPrice(otherInVO.getUnitPrice());
        excelVO.setAmount(otherInVO.getAmount());
        excelVO.setTaxRate(otherInVO.getTaxRate() == null ? "数据错误" : otherInVO.getTaxRate() + "%");
        excelVO.setNotaxPrice(otherInVO.getNotaxPrice());
        excelVO.setNotaxAmount(otherInVO.getNotaxAmount());
        excelVO.setTaxAmount(otherInVO.getTaxAmount());
        excelVO.setRemark(otherInVO.getRemark());
        excelVO.setStatus(otherInVO.getStatus() == 0 ? "禁用" : "启用");
        //0-药品；1-医疗器械；2-食品；3-化妆品；4-其它
        excelVO.setBusinessVariety(BusinessVariety.getName(otherInVO.getBusinessVariety()));
        excelVO.setCategoryName(otherInVO.getCategoryName());
        //0-成药；1-中药材；2-中药饮片
        excelVO.setGoodsAttribute(otherInVO.getGoodsAttributeDetail());
        excelVO.setDomesticImport(DomesticImport.getName(otherInVO.getDomesticImport()));
        excelVO.setManagementScopeName(otherInVO.getManagementScopeName());
        excelVO.setSpecialDrug(otherInVO.getSpecialDrugDetail());
        excelVO.setInChargeDrug(otherInVO.getInChargeDrugDetail());
        excelVO.setLimitQuantity(otherInVO.getLimitQuantity());
        excelVO.setStorageTemp(StorageTemp.getName(otherInVO.getStorageTemp()));
        excelVO.setStorageConditionName(otherInVO.getStorageConditionName());
        excelVO.setSafeTime(otherInVO.getQualityPeriod() + (otherInVO.getQualityPeriodUnit() == 0 ? "日" : (otherInVO.getQualityPeriodUnit() == 1 ? "月" : (otherInVO.getQualityPeriodUnit() == 2 ? "年" : "")))  + "");
        return excelVO;
    }
}
