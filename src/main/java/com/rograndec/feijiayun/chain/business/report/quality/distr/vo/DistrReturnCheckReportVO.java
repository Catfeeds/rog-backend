package com.rograndec.feijiayun.chain.business.report.quality.distr.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author dongdong.zhang
 */
@ApiModel
public class DistrReturnCheckReportVO implements Serializable {
    /**
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 
     */
    @ApiModelProperty("配后退回验收明细ID")
    private Long id;
    
    /**
     * 配进验收单号
     */
    @ApiModelProperty(value = "配进验收单号")
    private String checkCode;

    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date checkDate;

    /**
     * 要货单位ID
     */
    @ApiModelProperty(value = "要货单位ID")
    private Long requestUnitId;

    /**
     * 要货单位编码
     */
    @ApiModelProperty(value = "要货单位编码")
    private String requestUnitCode;

    /**
     * 要货单位名称
     */
    @ApiModelProperty(value = "要货单位名称")
    private String requestUnitName;
    
    /**
     * 验收人员ID
     */
    @ApiModelProperty(value = "验收人员ID")
    private Long checkerId;

    /**
     * 验收人员名称
     */
    @ApiModelProperty(value = "验收人员名称")
    private String checkerName;

    /**
     * 第二验收人员ID
     */
    @ApiModelProperty(value = "第二验收人员ID")
    private Long secondCheckerId;

    /**
     * 第二验收人员名称
     */
    @ApiModelProperty(value = "第二验收人员名称")
    private String secondCheckerName;
    
    /**
     * 基础单据编码(配后退回收货单号)
     */
    @ApiModelProperty(value = "配后退回收货单号")
    private String baseOrderCode;

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

    @ApiModelProperty(value = "条形码")
    private String barcode;
    
    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "通用名称")
    private String goodsGenericName;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型")
    private String dosageName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "规格")
    private String goodsSpecification;
    
    /**
     * 库存计量单位ID
     */
    @ApiModelProperty(value=" 库存计量单位ID")
    private Long unitId;

    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(value="单位")
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
     * 商品产地
     */
    @ApiModelProperty(value = "产地")
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;
    

    /**
     * 批号id
     */
    @ApiModelProperty(value = "批号Id")
    private Long lotId;
    
    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期至")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validUntil;
    
    
    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量")
    private BigDecimal receiveQuantity;
    
    /**
     * 检验项目ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "检验项目ID集合，多个用逗号分隔")
    private String checkProjectIds;

    /**
     * 检验项目Name集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "检验项目")
    private String checkProjectNames;

    /**
     * 抽样数量
     */
    @ApiModelProperty(value = "抽样数量")
    private BigDecimal samplingQuantity;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量")
    private BigDecimal qualifiedQuantity;

    /**
     * 验收结论ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "验收结论ID集合，多个用逗号分隔")
    private String conclusionIds;

    /**
     * 验收结论Name集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "验收结论")
    private String conclusionNames;

    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量")
    private BigDecimal unqualifiedQuantity;
    /**
     * 验收不合格原因ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "验收不合格原因ID集合，多个用逗号分隔")
    private String unqualifiedReasonIds;

    /**
     * 验收不合格原因，多个用逗号分隔
     */
    @ApiModelProperty(value = "不合格原因")
    private String unqualifiedReasonNames;

    /**
     * 处置措施ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "处置措施ID集合，多个用逗号分隔")
    private String measuresIds;
    
    /**
     * 处置措施Name集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "处置措施")
    private String measuresNames;
    
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态ID")
    private Integer status;
    
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String statusName;
    
    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value="品种类型ID")
    private Integer businessVariety;
    
    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value="品种类型")
    private String businessVarietyName="";
    
    /**
     * 商品分类ID
     */
    @ApiModelProperty(value="商品分类ID")
    private Long categoryId;

    @ApiModelProperty(value="商品分类")
    private String categoryName="";
    
    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片
     */
    @ApiModelProperty(value = "商品属性ID（0-成药；1-中药材；2-中药饮片)")
    private Integer goodsAttribute;
    
    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片
     */
    @ApiModelProperty(value = "商品属性")
    private String goodsAttributeName="";
    
    /**
     * 商品属性是否为处方药，0：非处方药，1：处方药
     */
    @ApiModelProperty(value = "商品属性ID ( 是否为处方药，0：非处方药，1：处方药)")
    private Integer prescriptionDrug;
    
    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片
     */
    @ApiModelProperty(value = "商品属性ID(非处方药类别：0-甲类；1-乙类)")
    private Integer otcType;
    
    /**
     * 品种类别为3-化妆品，商品属性为1-特殊用途化妆品时，化妆品类别［0-育发；1-染发；2-烫发；3-脱毛；4-美乳；5-健美；6-除臭；7-祛斑；8-防嗮］
     */
    @ApiModelProperty(value="品种类别为3-化妆品，商品属性为1-特殊用途化妆品时，化妆品类别［0-育发；1-染发；2-烫发；3-脱毛；4-美乳；5-健美；6-除臭；7-祛斑；8-防嗮］")
    private Integer cosmetics;
    
    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value="国产/进口ID")
    private Integer domesticImport;
    
    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value="国产/进口")
    private String domesticImportName="";
    
    /**
     * 经营范围ID
     */
    @ApiModelProperty(value="经营范围ID")
    private Long managemenScopeId;
    
    /**
     * 经营范围
     */
    @ApiModelProperty(value="经营范围")
    private String managementScopeName="";
    
    /**
    *
    * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
    */
   @ApiModelProperty(value="特殊管理药品ID（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
   private Integer specialDrug;
   /**
    * 精神药品分类（0-一类；1-二类）
    */
   @ApiModelProperty("精神药品分类（0-一类；1-二类）")
   private Integer spiritDrugType;
   
   /**
   *
   * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
   */
   @ApiModelProperty(value="特殊管理药品")
   private String specialDrugName="";
   
   
   /**
    * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
    */
   @ApiModelProperty(value="专管药品ID（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
   private Integer inChargeDrug;
   /**
    *  含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）
    */
   @ApiModelProperty("含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
   private Integer formulationType;
   /**
    * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
    */
   @ApiModelProperty(value="专管药品")
   private String inChargeDrugName="";
   
   /**
    * 限购数量
    */
   @ApiModelProperty(value="限购数量")
   private BigDecimal limitQuantity;

   /**
    * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
    */
   @ApiModelProperty(value=" 贮藏温度ID（0-常温；1-阴凉；2-冷藏；3-冷冻）")
   private Integer storageTemp;
   
   /**
    * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
    */
   @ApiModelProperty(value=" 贮藏温度")
   private String storageTempName="";


   /**
    * 贮藏条件名称
    */
   @ApiModelProperty(value="贮藏条件")
   private String storageConditionName="";
   
   /**
    * 保质期
    */
   @ApiModelProperty(value = "")
   private Integer qualityPeriod;
   
   /**
    * 保质期
    */
   @ApiModelProperty(value = "保质期")
   private String qualityPeriodUnit="";
   

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
	
	public Long getLotId() {
		return lotId;
	}
	
	public void setLotId(Long lotId) {
		this.lotId = lotId;
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
	
	public Date getValidUntil() {
		return validUntil;
	}
	
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
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
	
	public String getDomesticImportName() {
		return domesticImportName;
	}
	
	public void setDomesticImportName(String domesticImportName) {
		this.domesticImportName = domesticImportName;
	}
	
	public Long getManagemenScopeId() {
		return managemenScopeId;
	}
	
	public void setManagemenScopeId(Long managemenScopeId) {
		this.managemenScopeId = managemenScopeId;
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

	public String getGoodsAttributeName() {
		return goodsAttributeName;
	}

	public void setGoodsAttributeName(String goodsAttributeName) {
		this.goodsAttributeName = goodsAttributeName;
	}

	public String getSpecialDrugName() {
		return specialDrugName;
	}

	public void setSpecialDrugName(String specialDrugName) {
		this.specialDrugName = specialDrugName;
	}

	public String getInChargeDrugName() {
		return inChargeDrugName;
	}

	public void setInChargeDrugName(String inChargeDrugName) {
		this.inChargeDrugName = inChargeDrugName;
	}

	public String getStorageTempName() {
		return storageTempName;
	}

	public void setStorageTempName(String storageTempName) {
		this.storageTempName = storageTempName;
	}

	public Integer getQualityPeriod() {
		return qualityPeriod;
	}

	public void setQualityPeriod(Integer qualityPeriod) {
		this.qualityPeriod = qualityPeriod;
	}

	public String getQualityPeriodUnit() {
		return qualityPeriodUnit;
	}

	public void setQualityPeriodUnit(String qualityPeriodUnit) {
		this.qualityPeriodUnit = qualityPeriodUnit;
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

	public String getBaseOrderCode() {
		return baseOrderCode;
	}

	public void setBaseOrderCode(String baseOrderCode) {
		this.baseOrderCode = baseOrderCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Long getRequestUnitId() {
		return requestUnitId;
	}

	public void setRequestUnitId(Long requestUnitId) {
		this.requestUnitId = requestUnitId;
	}

	public String getRequestUnitCode() {
		return requestUnitCode;
	}

	public void setRequestUnitCode(String requestUnitCode) {
		this.requestUnitCode = requestUnitCode;
	}

	public String getRequestUnitName() {
		return requestUnitName;
	}

	public void setRequestUnitName(String requestUnitName) {
		this.requestUnitName = requestUnitName;
	}

	public Long getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(Long checkerId) {
		this.checkerId = checkerId;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public Long getSecondCheckerId() {
		return secondCheckerId;
	}

	public void setSecondCheckerId(Long secondCheckerId) {
		this.secondCheckerId = secondCheckerId;
	}

	public String getSecondCheckerName() {
		return secondCheckerName;
	}

	public void setSecondCheckerName(String secondCheckerName) {
		this.secondCheckerName = secondCheckerName;
	}

	public BigDecimal getReceiveQuantity() {
		return receiveQuantity;
	}

	public void setReceiveQuantity(BigDecimal receiveQuantity) {
		this.receiveQuantity = receiveQuantity;
	}

	public BigDecimal getSamplingQuantity() {
		return samplingQuantity;
	}

	public void setSamplingQuantity(BigDecimal samplingQuantity) {
		this.samplingQuantity = samplingQuantity;
	}

	public BigDecimal getQualifiedQuantity() {
		return qualifiedQuantity;
	}

	public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
		this.qualifiedQuantity = qualifiedQuantity;
	}

	public String getConclusionIds() {
		return conclusionIds;
	}

	public void setConclusionIds(String conclusionIds) {
		this.conclusionIds = conclusionIds;
	}

	public BigDecimal getUnqualifiedQuantity() {
		return unqualifiedQuantity;
	}

	public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
		this.unqualifiedQuantity = unqualifiedQuantity;
	}

	public String getUnqualifiedReasonIds() {
		return unqualifiedReasonIds;
	}

	public void setUnqualifiedReasonIds(String unqualifiedReasonIds) {
		this.unqualifiedReasonIds = unqualifiedReasonIds;
	}

	public String getMeasuresIds() {
		return measuresIds;
	}

	public void setMeasuresIds(String measuresIds) {
		this.measuresIds = measuresIds;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCheckProjectIds() {
		return checkProjectIds;
	}

	public void setCheckProjectIds(String checkProjectIds) {
		this.checkProjectIds = checkProjectIds;
	}

	public String getCheckProjectNames() {
		return checkProjectNames;
	}

	public void setCheckProjectNames(String checkProjectNames) {
		this.checkProjectNames = checkProjectNames;
	}

	public String getConclusionNames() {
		return conclusionNames;
	}

	public void setConclusionNames(String conclusionNames) {
		this.conclusionNames = conclusionNames;
	}

	public String getUnqualifiedReasonNames() {
		return unqualifiedReasonNames;
	}

	public void setUnqualifiedReasonNames(String unqualifiedReasonNames) {
		this.unqualifiedReasonNames = unqualifiedReasonNames;
	}

	public String getMeasuresNames() {
		return measuresNames;
	}

	public void setMeasuresNames(String measuresNames) {
		this.measuresNames = measuresNames;
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

	public Integer getCosmetics() {
		return cosmetics;
	}

	public void setCosmetics(Integer cosmetics) {
		this.cosmetics = cosmetics;
	}
	
}