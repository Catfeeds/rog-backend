package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.rograndec.feijiayun.chain.business.storage.move.constant.OtherOutType;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.DomesticImport;
import com.rograndec.feijiayun.chain.common.constant.StorageTemp;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class OtherOutReportExcelVO implements Serializable{

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
     * 出库日期
     */
    @ApiModelProperty(value = "出库日期")
    private String outDate;

    /**
     * 出库单号
     */
    @ApiModelProperty(value = "出库单号")
    private String code;

    /**
     * 出库人员名称
     */
    @ApiModelProperty(value = "出库人员名称")
    private String outManName;

    /**
     * 复核人员名称
     */
    @ApiModelProperty(value = "复核人员名称")
    private String auditManName;

    /**
     * 出库类型（0-获赠；1-报溢；2-领用退回；3-其它）
     */
    @ApiModelProperty(value = "0-赠送；1-报损；2-内部领用；3-抽检出库；4-其它")
    private String outType;

    /**
     * 流向单位名称
     */
    @ApiModelProperty(value = "来源单位名称")
    private String flowUnitName;

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

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOutManName() {
        return outManName;
    }

    public void setOutManName(String outManName) {
        this.outManName = outManName;
    }

    public String getAuditManName() {
        return auditManName;
    }

    public void setAuditManName(String auditManName) {
        this.auditManName = auditManName;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public String getFlowUnitName() {
        return flowUnitName;
    }

    public void setFlowUnitName(String flowUnitName) {
        this.flowUnitName = flowUnitName;
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

    public String getGoodsAttribute() {
        return goodsAttribute;
    }

    public void setGoodsAttribute(String goodsAttribute) {
        this.goodsAttribute = goodsAttribute;
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

    public String getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(String specialDrug) {
        this.specialDrug = specialDrug;
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

    public String getInChargeDrug() {
        return inChargeDrug;
    }

    public void setInChargeDrug(String inChargeDrug) {
        this.inChargeDrug = inChargeDrug;
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
        return "OtherOutReportExcelVO{" +
                "id=" + id +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", outDate='" + outDate + '\'' +
                ", code='" + code + '\'' +
                ", outManName='" + outManName + '\'' +
                ", auditManName='" + auditManName + '\'' +
                ", outType='" + outType + '\'' +
                ", flowUnitName='" + flowUnitName + '\'' +
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
                ", goodsAttribute='" + goodsAttribute + '\'' +
                ", goodsAttributeDetail='" + goodsAttributeDetail + '\'' +
                ", prescriptionDrug=" + prescriptionDrug +
                ", otcType=" + otcType +
                ", cosmetics=" + cosmetics +
                ", domesticImport='" + domesticImport + '\'' +
                ", managementScopeId=" + managementScopeId +
                ", managementScopeName='" + managementScopeName + '\'' +
                ", specialDrug='" + specialDrug + '\'' +
                ", specialDrugDetail='" + specialDrugDetail + '\'' +
                ", spiritDrugType=" + spiritDrugType +
                ", inChargeDrug='" + inChargeDrug + '\'' +
                ", inChargeDrugDetail='" + inChargeDrugDetail + '\'' +
                ", formulationType=" + formulationType +
                ", limitQuantity=" + limitQuantity +
                ", storageTemp='" + storageTemp + '\'' +
                ", storageConditionName='" + storageConditionName + '\'' +
                ", safeTime='" + safeTime + '\'' +
                '}';
    }

    public static OtherOutReportExcelVO convertTOExcel(OtherOutReportPageVO otherOutVO) {
        OtherOutReportExcelVO excelVO = new OtherOutReportExcelVO();
        excelVO.setId(otherOutVO.getId());
        excelVO.setEnterpriseCode(otherOutVO.getEnterpriseCode());
        excelVO.setEnterpriseName(otherOutVO.getEnterpriseName());
        excelVO.setOutDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(otherOutVO.getOutDate()));
        excelVO.setCode(otherOutVO.getCode());
        excelVO.setOutManName(otherOutVO.getOutManName());
        excelVO.setAuditManName(otherOutVO.getAuditManName());
        excelVO.setOutType(OtherOutType.getValue(otherOutVO.getOutType()));
        excelVO.setFlowUnitName(otherOutVO.getFlowUnitName());
        excelVO.setFlowCode(otherOutVO.getFlowCode());
        excelVO.setGoodsCode(otherOutVO.getGoodsCode());
        excelVO.setBarcode(otherOutVO.getBarcode());

        excelVO.setGoodsName(otherOutVO.getGoodsName());
        excelVO.setGoodsGenericName(otherOutVO.getGoodsGenericName());
        excelVO.setDosageName(otherOutVO.getDosageName());
        excelVO.setGoodsSpecification(otherOutVO.getGoodsSpecification());
        excelVO.setUnitName(otherOutVO.getUnitName());
        excelVO.setManufacturer(otherOutVO.getManufacturer());
        excelVO.setGoodsPlace(otherOutVO.getGoodsPlace());
        excelVO.setApprovalNumber(otherOutVO.getApprovalNumber());
        excelVO.setLotNum(otherOutVO.getLotNum());
        excelVO.setProductDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(otherOutVO.getProductDate()));
        excelVO.setValidDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(otherOutVO.getValidDate()));
        excelVO.setShelfName(otherOutVO.getShelfName());
        excelVO.setShelfStatusDesc(otherOutVO.getShelfStatusDesc());
        excelVO.setQuantity(otherOutVO.getQuantity());
        excelVO.setUnitPrice(otherOutVO.getUnitPrice());
        excelVO.setAmount(otherOutVO.getAmount());
        excelVO.setTaxRate(otherOutVO.getTaxRate() == null ? "数据错误" : otherOutVO.getTaxRate() + "%");
        excelVO.setNotaxPrice(otherOutVO.getNotaxPrice());
        excelVO.setNotaxAmount(otherOutVO.getNotaxAmount());
        excelVO.setTaxAmount(otherOutVO.getTaxAmount());
        excelVO.setRemark(otherOutVO.getRemark());
        excelVO.setStatus(otherOutVO.getStatus() == 0 ? "禁用" : "启用");
        //0-药品；1-医疗器械；2-食品；3-化妆品；4-其它
        excelVO.setBusinessVariety(BusinessVariety.getName(otherOutVO.getBusinessVariety()));
        excelVO.setCategoryName(otherOutVO.getCategoryName());
        //0-成药；1-中药材；2-中药饮片
        excelVO.setGoodsAttribute(otherOutVO.getGoodsAttributeDetail());
        excelVO.setDomesticImport(DomesticImport.getName(otherOutVO.getDomesticImport()));
        excelVO.setManagementScopeName(otherOutVO.getManagementScopeName());
        excelVO.setSpecialDrug(otherOutVO.getSpecialDrugDetail());
        excelVO.setInChargeDrug(otherOutVO.getInChargeDrugDetail());
        excelVO.setLimitQuantity(otherOutVO.getLimitQuantity());
        excelVO.setStorageTemp(StorageTemp.getName(otherOutVO.getStorageTemp()));
        excelVO.setStorageConditionName(otherOutVO.getStorageConditionName());
        excelVO.setSafeTime(otherOutVO.getQualityPeriod() + (otherOutVO.getQualityPeriodUnit() == 0 ? "日" : (otherOutVO.getQualityPeriodUnit() == 1 ? "月" : (otherOutVO.getQualityPeriodUnit() == 2 ? "年" : "")))  + "");
        return excelVO;
    }
}
