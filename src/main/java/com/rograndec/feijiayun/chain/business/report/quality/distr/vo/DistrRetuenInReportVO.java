package com.rograndec.feijiayun.chain.business.report.quality.distr.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dongdong.zhang
 */
@ApiModel
public class DistrRetuenInReportVO implements Serializable {
    /**
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 
     */
    @ApiModelProperty("配后退回入库明细ID")
    private Long id;

    /**
     * 配进入库单号
     */
    @ApiModelProperty(value = "配进入库单号")
    private String returnInCode;

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date returnInDate;
    
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
     * 入库人员ID
     */
    @ApiModelProperty(value = "入库人员ID")
    private Long storageManId;

    /**
     * 入库人员名称
     */
    @ApiModelProperty(value = "入库人员")
    private String storageManName;
    
    /**
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型ID")
    private Integer distrType;
    /**
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型")
    private String distrTypeName;

    /**
     * 流通监管码
     */
    @ApiModelProperty(value = "流通监管码")
    private String flowCode;
    
    /**
     * 基础单据编码（配后退回验收单号）
     */
    @ApiModelProperty(value = "配后退回验收单号")
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
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位")
    private String shelfName;

    /**
     * 货位质量状态描述
     */
    @ApiModelProperty(value = "质量状况")
    private String shelfStatusDesc;
    
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    @ApiModelProperty(value = "折扣")
    private BigDecimal lineDiscount;

    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 行优惠（整单优惠分摊到行的金额）
     */
    @ApiModelProperty(value = "优惠分摊")
    private BigDecimal lineDiscountAmount;

    /**
     * 实际单价（实际金额/数量）
     */
    @ApiModelProperty(value = "实际单价")
    private BigDecimal realPrice;

    /**
     * 实际金额
     */
    @ApiModelProperty(value = "实际金额")
    private BigDecimal realAmount;

    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;

    /**
     * 税率
     */
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;

    /**
     * 不含税实际单价
     */
    @ApiModelProperty(value = "不含税单价")
    private BigDecimal notaxRealPrice;

    /**
     * 不含税实际金额
     */
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;
    
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

	public Long getStorageManId() {
		return storageManId;
	}

	public void setStorageManId(Long storageManId) {
		this.storageManId = storageManId;
	}

	public String getStorageManName() {
		return storageManName;
	}

	public void setStorageManName(String storageManName) {
		this.storageManName = storageManName;
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

	public Long getTaxRateId() {
		return taxRateId;
	}

	public void setTaxRateId(Long taxRateId) {
		this.taxRateId = taxRateId;
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

	public String getReturnInCode() {
		return returnInCode;
	}

	public void setReturnInCode(String returnInCode) {
		this.returnInCode = returnInCode;
	}

	public Date getReturnInDate() {
		return returnInDate;
	}

	public void setReturnInDate(Date returnInDate) {
		this.returnInDate = returnInDate;
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

	public Integer getDistrType() {
		return distrType;
	}

	public void setDistrType(Integer distrType) {
		this.distrType = distrType;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getDistrTypeName() {
		return distrTypeName;
	}

	public void setDistrTypeName(String distrTypeName) {
		this.distrTypeName = distrTypeName;
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