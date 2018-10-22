package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.DomesticImport;
import com.rograndec.feijiayun.chain.common.constant.StorageTemp;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class GoodsClearReportExcelVO implements Serializable{
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
     * 清斗日期
     */
    @ApiModelProperty(value = "清斗日期")
    private String clearDate;

    /**
     * 清斗单号
     */
    @ApiModelProperty(value = "清斗单号")
    private String code;

    /**
     * 清斗人员名称
     */
    @ApiModelProperty(value = "装斗人员名称")
    private String clearManName;

    /**
     * 复核人员名称
     */
    @ApiModelProperty(value = "复核人员名称")
    private String auditManName;

    /**
     * 流通监管码
     */
    @ApiModelProperty(value = "流通监管码")
    private String flowCode;


    @ApiModelProperty(value = "商品ID")
    private Long goodsId;
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
     * 货位ID
     */
    @ApiModelProperty(value = "源货位ID")
    private Long shelfId;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 货位质量状况
     */
    @ApiModelProperty(value = "货位质量状况")
    private String shelfStatusDesc;


    /**
     * 数量
     */
    @ApiModelProperty(value = "数量" ,required = true)
    private BigDecimal quantity;


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

    public String getClearDate() {
        return clearDate;
    }

    public void setClearDate(String clearDate) {
        this.clearDate = clearDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClearManName() {
        return clearManName;
    }

    public void setClearManName(String clearManName) {
        this.clearManName = clearManName;
    }

    public String getAuditManName() {
        return auditManName;
    }

    public void setAuditManName(String auditManName) {
        this.auditManName = auditManName;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

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
        return "GoodsClearReportExcelVO{" +
                "id=" + id +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", clearDate='" + clearDate + '\'' +
                ", code='" + code + '\'' +
                ", clearManName='" + clearManName + '\'' +
                ", auditManName='" + auditManName + '\'' +
                ", flowCode='" + flowCode + '\'' +
                ", goodsId=" + goodsId +
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

    public static GoodsClearReportExcelVO convertTOExcel(GoodClearReportPageVO vo) {
        GoodsClearReportExcelVO excelVO = new GoodsClearReportExcelVO();
        excelVO.setId(vo.getId());
        excelVO.setEnterpriseCode(vo.getEnterpriseCode());
        excelVO.setEnterpriseName(vo.getEnterpriseName());
        excelVO.setClearDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo.getClearDate()));
        excelVO.setCode(vo.getCode());
        excelVO.setClearManName(vo.getClearManName());
        excelVO.setAuditManName(vo.getAuditManName());
        excelVO.setFlowCode(vo.getFlowCode());
        excelVO.setGoodsCode(vo.getGoodsCode());
        excelVO.setBarcode(vo.getBarcode());
        excelVO.setGoodsName(vo.getGoodsName());
        excelVO.setGoodsGenericName(vo.getGoodsGenericName());
        excelVO.setDosageName(vo.getDosageName());
        excelVO.setGoodsSpecification(vo.getGoodsSpecification());
        excelVO.setUnitName(vo.getUnitName());
        excelVO.setManufacturer(vo.getManufacturer());
        excelVO.setGoodsPlace(vo.getGoodsPlace());
        excelVO.setApprovalNumber(vo.getApprovalNumber());
        excelVO.setLotNum(vo.getLotNum());
        excelVO.setProductDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo.getProductDate()));
        excelVO.setValidDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo.getValidDate()));
        excelVO.setShelfName(vo.getShelfName());
        excelVO.setShelfStatusDesc(vo.getShelfStatusDesc());
        excelVO.setQuantity(vo.getQuantity());
        excelVO.setRemark(vo.getRemark());
        excelVO.setStatus(vo.getStatus() == 0 ? "禁用" : "启用");
        //0-药品；1-医疗器械；2-食品；3-化妆品；4-其它
        excelVO.setBusinessVariety(BusinessVariety.getName(vo.getBusinessVariety()));
        excelVO.setCategoryName(vo.getCategoryName());
        //0-成药；1-中药材；2-中药饮片
        excelVO.setGoodsAttribute(vo.getGoodsAttributeDetail());
        excelVO.setDomesticImport(DomesticImport.getName(vo.getDomesticImport()));
        excelVO.setManagementScopeName(vo.getManagementScopeName());
        excelVO.setSpecialDrug(vo.getSpecialDrugDetail());
        excelVO.setInChargeDrug(vo.getInChargeDrugDetail());
        excelVO.setLimitQuantity(vo.getLimitQuantity());
        excelVO.setStorageTemp(StorageTemp.getName(vo.getStorageTemp()));
        excelVO.setStorageConditionName(vo.getStorageConditionName());
        excelVO.setSafeTime(vo.getQualityPeriod() + (vo.getQualityPeriodUnit() == 0 ? "日" : (vo.getQualityPeriodUnit() == 1 ? "月" : (vo.getQualityPeriodUnit() == 2 ? "年" : "")))  + "");
        return excelVO;
    }
}
