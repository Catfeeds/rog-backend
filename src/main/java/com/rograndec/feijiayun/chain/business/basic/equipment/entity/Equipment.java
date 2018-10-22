package com.rograndec.feijiayun.chain.business.basic.equipment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * saas_equipment
 *
 * @author Administrator
 * <p>
 * 2017-10-13
 */
public class Equipment implements Serializable {
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID")
	private Long id;

	/**
	 * 企业（组织机构）ID
	 */
	@NotNull(message = "组织机构id不能为空")
	@ApiModelProperty(required = true,value = "企业（组织机构）ID")
	private Long enterpriseId;

	/**
	 * 企业（组织机构编码）
	 */
	@ApiModelProperty(value = "企业（组织机构编码）")
	private String enterpriseCode;

	/**
	 * 企业（组织机构）名称
	 */
	@ApiModelProperty(value = "企业（组织机构）名称")
	private String enterpriseName;

	/**
	 * 上级企业ID
	 */
	@ApiModelProperty(value = "上级企业ID")
	private Long parentId;

	/**
	 * 类型ID
	 */
	@NotNull(message = "类型id不能为空")
	@ApiModelProperty(required = true, value = "类型ID")
	private Long typeId;

	/**
	 * 类型编码
	 */
	@ApiModelProperty(value = "类型编码")
	private String typeCode;

	/**
	 * 类型名称
	 */
	@ApiModelProperty(value = "类型名称")
	private String typeName;

	/**
	 * 编码
	 */
	@NotNull(message = "编码不能为空")
	@ApiModelProperty(required = true,value = "编码")
	private String code;

	/**
	 * 名称
	 */
	@NotNull(message = "名称不能为空")
	@ApiModelProperty(required = true,value = "名称")
	private String name;

	/**
	 * 规格型号
	 */
	@ApiModelProperty(value = "规格型号")
	private String specificationModel;

	/**
	 * 生产厂商
	 */
	@ApiModelProperty(value = "生产厂商")
	private String manufacturer;

	/**
	 * 出厂编号
	 */
	@ApiModelProperty(value = "出厂编号")
	private String factoryNumber;

	/**
	 * 分类编号
	 */
	@ApiModelProperty(value = "分类编号")
	private String classifyNumber;

	/**
	 * 测量范围
	 */
	@ApiModelProperty(value = "测量范围")
	private String measureRange;

	/**
	 * 精度等级
	 */
	@ApiModelProperty(value = "精度等级")
	private String accuracyClass;

	/**
	 * 生产日期
	 */
	@ApiModelProperty(value = "生产日期")
	private Date productDate;

	/**
	 * 购置日期
	 */
	@ApiModelProperty(value = "购置日期")
	private Date purchaseDate;

	/**
	 * 购置人员
	 */
	@ApiModelProperty(value = "购置人员")
	private String purchaser;

	/**
	 * 购置数量
	 */
	@ApiModelProperty(value = "购置数量")
	private Integer purchaseQuantity;

	/**
	 * 购置单价
	 */
	@ApiModelProperty(value = "购置单价")
	private BigDecimal purchasePrice;

	/**
	 * 购置金额
	 */
	@ApiModelProperty(value = "购置金额")
	private BigDecimal purchaseAmount;

	/**
	 * 使用年限
	 */
	@ApiModelProperty(value = "使用年限")
	private Integer yearLimit;

	/**
	 * 使用年限单位
	 */
	@ApiModelProperty(value = "使用年限单位（0-年；1-月；2-日）")
	private Integer yearLimitUnit;

	/**
	 * 用途
	 */
	@ApiModelProperty(value = "用途")
	private String purpose;

	/**
	 * 配置地点
	 */
	@ApiModelProperty(value = "配置地点")
	private String configSite;

	/**
	 * 所属部门ID
	 */
	@ApiModelProperty(value = "所属部门ID")
	private Long deptId;

	/**
	 * 所属部门编码
	 */
	@ApiModelProperty(value = "所属部门编码")
	private String deptCode;

	/**
	 * 所属部门名称
	 */
	@ApiModelProperty(value = "所属部门名称")
	private String deptName;

	/**
	 * 检查周期
	 */
	@ApiModelProperty(value = "检查周期")
	private Integer checkPeriod;

	/**
	 * 检查周期单位（0-年；1-月；2-日）
	 */
	@ApiModelProperty(value = "检查周期单位（0-年；1-月；2-日）")
	private Integer checkPeriodUnit;

	/**
	 * 清洁周期
	 */
	@ApiModelProperty(value = "清洁周期")
	private Integer cleanPeriod;

	/**
	 * 清洁周期单位（0-年；1-月；2-日）
	 */
	@ApiModelProperty(value = "清洁周期单位（0-年；1-月；2-日）")
	private Integer cleanPeriodUnit;

	/**
	 * 维护周期
	 */
	@ApiModelProperty(value = "维护周期")
	private Integer maintaincePeriod;

	/**
	 * 维护周期单位（0-年；1-月；2-日）
	 */
	@ApiModelProperty(value = "维护周期单位（0-年；1-月；2-日）")
	private Integer maintaincePeriodUnit;

	/**
	 * 校准周期
	 */
	@ApiModelProperty(value = "校准周期")
	private Integer calibrationPeriod;

	/**
	 * 校准周期单位（0-年；1-月；2-日）
	 */
	@ApiModelProperty(value = "校准周期单位（0-年；1-月；2-日）")
	private Integer calibrationPeriodUnit;

	/**
	 * 检定周期
	 */
	@ApiModelProperty(value = "检定周期")
	private Integer docimasyPeriod;

	/**
	 * 检定周期单位（0-年；1-月；2-日）
	 */
	@ApiModelProperty(value = "检定周期单位（0-年；1-月；2-日）")
	private Integer docimasyPeriodUnit;

	/**
	 * 验证周期
	 */
	@ApiModelProperty(value = "验证周期")
	private Integer validatePeriod;

	/**
	 * 验证周期单位（0-年；1-月；2-日）
	 */
	@ApiModelProperty(value = "验证周期单位（0-年；1-月；2-日）")
	private Integer validatePeriodUnit;

	/**
	 * 负责人ID
	 */
	@ApiModelProperty(value = "负责人ID")
	private Long chargeManId;

	/**
	 * 负责人编码
	 */
	@ApiModelProperty(value = "负责人编码")
	private String chargeManCode;

	/**
	 * 负责任名称
	 */
	@ApiModelProperty(value = "负责任名称")
	private String chargeManName;

	/**
	 * 附件ID
	 */
	@ApiModelProperty(value = "附件ID")
	private Long fileId;

	@ApiModelProperty(value = "附件名称")
	private String fileName;

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	private Integer status;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;

	/**
	 * 创建人ID
	 */
	@ApiModelProperty(value = "创建人ID")
	private Long createrId;

	/**
	 * 创建人编码
	 */
	@ApiModelProperty(value = "创建人编码")
	private String createrCode;

	/**
	 * 创建人名称
	 */
	@ApiModelProperty(value = "创建人名称")
	private String createrName;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	/**
	 * 最后修改人ID
	 */
	@ApiModelProperty(value = "最后修改人ID")
	private Long modifierId;

	/**
	 * 最后修改人编码
	 */
	@ApiModelProperty(value = "最后修改人编码")
	private String modifierCode;

	/**
	 * 最后修改人名称
	 */
	@ApiModelProperty(value = "最后修改人名称")
	private String modifierName;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;

	@ApiModelProperty(value = "生产日期格式化")
	private String productDateStr;
	@ApiModelProperty(value = "购置日期格式化")
	private String purchaseDateStr;
	@ApiModelProperty(value = "使用年限格式化")
	private String yearLimitStr;
	@ApiModelProperty(value = "检查周期格式化")
	private String checkPeriodStr;
	@ApiModelProperty(value = "清洁周期格式化")
	private String cleanPeriodStr;
	@ApiModelProperty(value = "校准周期格式化")
	private String calibrationPeriodStr;
	@ApiModelProperty(value = "检定周期格式化")
	private String docimasyPeriodStr;
	@ApiModelProperty(value = "验证周期格式化")
	private String validatePeriodStr;
	@ApiModelProperty(value = "养护周期格式化")
	private String maintaincePeriodStr;

	public String getProductDateStr() {
		return DateUtils.DateToString(this.productDate,"yyyy-MM-dd");
	}

	public String getPurchaseDateStr() {
		return DateUtils.DateToString(this.purchaseDate,"yyyy-MM-dd");
	}

	public String getYearLimitStr() {
		if(this.yearLimitUnit==null){
			return "";
		}
		if(this.yearLimit==null){
			return "";
		}
		if(yearLimitUnit==0){
			return this.yearLimit+"年";
		}
		if(yearLimitUnit==1){
			return this.yearLimit+"月";
		}
		if(yearLimitUnit==2){
			return this.yearLimit+"日";
		}
		return yearLimitStr;
	}

	public String getCheckPeriodStr() {
		if(this.checkPeriod==null){
			return "";
		}
		if(this.checkPeriodUnit==null){
			return "";
		}
		if(checkPeriodUnit==0){
			return this.checkPeriod+"年";
		}
		if(checkPeriodUnit==1){
			return this.checkPeriod+"月";
		}
		if(checkPeriodUnit==2){
			return this.checkPeriod+"日";
		}
		return checkPeriodStr;
	}

	public String getCleanPeriodStr() {
		if(this.cleanPeriod==null){
			return "";
		}
		if(this.cleanPeriodUnit==null){
			return "";
		}
		if(cleanPeriodUnit==0){
			return this.cleanPeriod+"年";
		}
		if(cleanPeriodUnit==1){
			return this.cleanPeriod+"月";
		}
		if(cleanPeriodUnit==2){
			return this.cleanPeriod+"日";
		}
		return cleanPeriodStr;
	}

	public String getCalibrationPeriodStr() {
		if(this.calibrationPeriod==null){
			return "";
		}
		if(this.calibrationPeriodUnit==null){
			return "";
		}
		if(calibrationPeriodUnit==0){
			return this.calibrationPeriod+"年";
		}
		if(calibrationPeriodUnit==1){
			return this.calibrationPeriod+"月";
		}
		if(calibrationPeriodUnit==2){
			return this.calibrationPeriod+"日";
		}
		return calibrationPeriodStr;
	}

	public String getDocimasyPeriodStr() {
		if(this.docimasyPeriod==null){
			return "";
		}
		if(this.docimasyPeriodUnit==null){
			return "";
		}
		if(docimasyPeriodUnit==0){
			return this.docimasyPeriod+"年";
		}
		if(docimasyPeriodUnit==1){
			return this.docimasyPeriod+"月";
		}
		if(docimasyPeriodUnit==2){
			return this.docimasyPeriod+"日";
		}
		return docimasyPeriodStr;
	}

	public String getValidatePeriodStr() {
		if(this.validatePeriod==null){
			return "";
		}
		if(this.validatePeriodUnit==null){
			return "";
		}
		if(validatePeriodUnit==0){
			return this.validatePeriod+"年";
		}
		if(validatePeriodUnit==1){
			return this.validatePeriod+"月";
		}
		if(validatePeriodUnit==2){
			return this.validatePeriod+"日";
		}
		return validatePeriodStr;
	}
	public String getMaintaincePeriodStr() {
		if(this.maintaincePeriod==null){
			return "";
		}
		if(this.maintaincePeriodUnit==null){
			return "";
		}
		if(maintaincePeriodUnit==0){
			return this.maintaincePeriod+"年";
		}
		if(maintaincePeriodUnit==1){
			return this.maintaincePeriod+"月";
		}
		if(maintaincePeriodUnit==2){
			return this.maintaincePeriod+"日";
		}
		return maintaincePeriodStr;
	}

	public void setProductDateStr(String productDateStr) {
		this.productDateStr = productDateStr;
	}

	public void setPurchaseDateStr(String purchaseDateStr) {
		this.purchaseDateStr = purchaseDateStr;
	}

	public void setYearLimitStr(String yearLimitStr) {
		this.yearLimitStr = yearLimitStr;
	}

	public void setCheckPeriodStr(String checkPeriodStr) {
		this.checkPeriodStr = checkPeriodStr;
	}

	public void setCleanPeriodStr(String cleanPeriodStr) {
		this.cleanPeriodStr = cleanPeriodStr;
	}

	public void setCalibrationPeriodStr(String calibrationPeriodStr) {
		this.calibrationPeriodStr = calibrationPeriodStr;
	}

	public void setDocimasyPeriodStr(String docimasyPeriodStr) {
		this.docimasyPeriodStr = docimasyPeriodStr;
	}

	public void setValidatePeriodStr(String validatePeriodStr) {
		this.validatePeriodStr = validatePeriodStr;
	}

	public void setMaintaincePeriodStr(String maintaincePeriodStr) {
		this.maintaincePeriodStr = maintaincePeriodStr;
	}

	/**
	 * saas_equipment
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 *
	 * @return id 主键ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 主键ID
	 *
	 * @param id 主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 企业（组织机构）ID
	 *
	 * @return enterprise_id 企业（组织机构）ID
	 */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/**
	 * 企业（组织机构）ID
	 *
	 * @param enterpriseId 企业（组织机构）ID
	 */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/**
	 * 企业（组织机构编码）
	 *
	 * @return enterprise_code 企业（组织机构编码）
	 */
	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	/**
	 * 企业（组织机构编码）
	 *
	 * @param enterpriseCode 企业（组织机构编码）
	 */
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode == null ? null : enterpriseCode.trim();
	}

	/**
	 * 企业（组织机构）名称
	 *
	 * @return enterprise_name 企业（组织机构）名称
	 */
	public String getEnterpriseName() {
		return enterpriseName;
	}

	/**
	 * 企业（组织机构）名称
	 *
	 * @param enterpriseName 企业（组织机构）名称
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
	}

	/**
	 * 上级企业ID
	 *
	 * @return parent_id 上级企业ID
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 上级企业ID
	 *
	 * @param parentId 上级企业ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 类型ID
	 *
	 * @return type_id 类型ID
	 */
	public Long getTypeId() {
		return typeId;
	}

	/**
	 * 类型ID
	 *
	 * @param typeId 类型ID
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * 类型编码
	 *
	 * @return type_code 类型编码
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * 类型编码
	 *
	 * @param typeCode 类型编码
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode == null ? null : typeCode.trim();
	}

	/**
	 * 类型名称
	 *
	 * @return type_name 类型名称
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * 类型名称
	 *
	 * @param typeName 类型名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName == null ? null : typeName.trim();
	}

	/**
	 * 编码
	 *
	 * @return code 编码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 编码
	 *
	 * @param code 编码
	 */
	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	/**
	 * 名称
	 *
	 * @return name 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称
	 *
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * 规格型号
	 *
	 * @return specification_model 规格型号
	 */
	public String getSpecificationModel() {
		return specificationModel;
	}

	/**
	 * 规格型号
	 *
	 * @param specificationModel 规格型号
	 */
	public void setSpecificationModel(String specificationModel) {
		this.specificationModel = specificationModel == null ? null : specificationModel.trim();
	}

	/**
	 * 生产厂商
	 *
	 * @return manufacturer 生产厂商
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * 生产厂商
	 *
	 * @param manufacturer 生产厂商
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer == null ? null : manufacturer.trim();
	}

	/**
	 * 出厂编号
	 *
	 * @return factory_number 出厂编号
	 */
	public String getFactoryNumber() {
		return factoryNumber;
	}

	/**
	 * 出厂编号
	 *
	 * @param factoryNumber 出厂编号
	 */
	public void setFactoryNumber(String factoryNumber) {
		this.factoryNumber = factoryNumber == null ? null : factoryNumber.trim();
	}

	/**
	 * 分类编号
	 *
	 * @return classify_number 分类编号
	 */
	public String getClassifyNumber() {
		return classifyNumber;
	}

	/**
	 * 分类编号
	 *
	 * @param classifyNumber 分类编号
	 */
	public void setClassifyNumber(String classifyNumber) {
		this.classifyNumber = classifyNumber == null ? null : classifyNumber.trim();
	}

	/**
	 * 测量范围
	 *
	 * @return measure_range 测量范围
	 */
	public String getMeasureRange() {
		return measureRange;
	}

	/**
	 * 测量范围
	 *
	 * @param measureRange 测量范围
	 */
	public void setMeasureRange(String measureRange) {
		this.measureRange = measureRange == null ? null : measureRange.trim();
	}

	/**
	 * 精度等级
	 *
	 * @return accuracy_class 精度等级
	 */
	public String getAccuracyClass() {
		return accuracyClass;
	}

	/**
	 * 精度等级
	 *
	 * @param accuracyClass 精度等级
	 */
	public void setAccuracyClass(String accuracyClass) {
		this.accuracyClass = accuracyClass == null ? null : accuracyClass.trim();
	}

	/**
	 * 生产日期
	 *
	 * @return product_date 生产日期
	 */
	public Date getProductDate() {
		return productDate;
	}

	/**
	 * 生产日期
	 *
	 * @param productDate 生产日期
	 */
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	/**
	 * 购置日期
	 *
	 * @return purchase_date 购置日期
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * 购置日期
	 *
	 * @param purchaseDate 购置日期
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * 购置人员
	 *
	 * @return purchaser 购置人员
	 */
	public String getPurchaser() {
		return purchaser;
	}

	/**
	 * 购置人员
	 *
	 * @param purchaser 购置人员
	 */
	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser == null ? null : purchaser.trim();
	}

	/**
	 * 购置数量
	 *
	 * @return purchase_quantity 购置数量
	 */
	public Integer getPurchaseQuantity() {
		return purchaseQuantity;
	}

	/**
	 * 购置数量
	 *
	 * @param purchaseQuantity 购置数量
	 */
	public void setPurchaseQuantity(Integer purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	/**
	 * 购置单价
	 *
	 * @return purchase_price 购置单价
	 */
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * 购置单价
	 *
	 * @param purchasePrice 购置单价
	 */
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * 购置金额
	 *
	 * @return purchase_amount 购置金额
	 */
	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}

	/**
	 * 购置金额
	 *
	 * @param purchaseAmount 购置金额
	 */
	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	/**
	 * 使用年限
	 *
	 * @return year_limit 使用年限
	 */
	public Integer getYearLimit() {
		return yearLimit;
	}

	/**
	 * 使用年限
	 *
	 * @param yearLimit 使用年限
	 */
	public void setYearLimit(Integer yearLimit) {
		this.yearLimit = yearLimit;
	}

	/**
	 * 使用年限单位
	 *
	 * @return year_limit_unit 使用年限单位
	 */
	public Integer getYearLimitUnit() {
		return yearLimitUnit;
	}

	/**
	 * 使用年限单位
	 *
	 * @param yearLimitUnit 使用年限单位
	 */
	public void setYearLimitUnit(Integer yearLimitUnit) {
		this.yearLimitUnit = yearLimitUnit;
	}

	/**
	 * 用途
	 *
	 * @return purpose 用途
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * 用途
	 *
	 * @param purpose 用途
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose == null ? null : purpose.trim();
	}

	/**
	 * 配置地点
	 *
	 * @return config_site 配置地点
	 */
	public String getConfigSite() {
		return configSite;
	}

	/**
	 * 配置地点
	 *
	 * @param configSite 配置地点
	 */
	public void setConfigSite(String configSite) {
		this.configSite = configSite == null ? null : configSite.trim();
	}

	/**
	 * 所属部门ID
	 *
	 * @return dept_id 所属部门ID
	 */
	public Long getDeptId() {
		return deptId;
	}

	/**
	 * 所属部门ID
	 *
	 * @param deptId 所属部门ID
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	/**
	 * 所属部门编码
	 *
	 * @return dept_code 所属部门编码
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * 所属部门编码
	 *
	 * @param deptCode 所属部门编码
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode == null ? null : deptCode.trim();
	}

	/**
	 * 所属部门名称
	 *
	 * @return dept_name 所属部门名称
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * 所属部门名称
	 *
	 * @param deptName 所属部门名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName == null ? null : deptName.trim();
	}

	/**
	 * 检查周期
	 *
	 * @return check_period 检查周期
	 */
	public Integer getCheckPeriod() {
		return checkPeriod;
	}

	/**
	 * 检查周期
	 *
	 * @param checkPeriod 检查周期
	 */
	public void setCheckPeriod(Integer checkPeriod) {
		this.checkPeriod = checkPeriod;
	}

	/**
	 * 检查周期单位（0-年；1-月；2-日）
	 *
	 * @return check_period_unit 检查周期单位（0-年；1-月；2-日）
	 */
	public Integer getCheckPeriodUnit() {
		return checkPeriodUnit;
	}

	/**
	 * 检查周期单位（0-年；1-月；2-日）
	 *
	 * @param checkPeriodUnit 检查周期单位（0-年；1-月；2-日）
	 */
	public void setCheckPeriodUnit(Integer checkPeriodUnit) {
		this.checkPeriodUnit = checkPeriodUnit;
	}

	/**
	 * 清洁周期
	 *
	 * @return clean_period 清洁周期
	 */
	public Integer getCleanPeriod() {
		return cleanPeriod;
	}

	/**
	 * 清洁周期
	 *
	 * @param cleanPeriod 清洁周期
	 */
	public void setCleanPeriod(Integer cleanPeriod) {
		this.cleanPeriod = cleanPeriod;
	}

	/**
	 * 清洁周期单位（0-年；1-月；2-日）
	 *
	 * @return clean_period_unit 清洁周期单位（0-年；1-月；2-日）
	 */
	public Integer getCleanPeriodUnit() {
		return cleanPeriodUnit;
	}

	/**
	 * 清洁周期单位（0-年；1-月；2-日）
	 *
	 * @param cleanPeriodUnit 清洁周期单位（0-年；1-月；2-日）
	 */
	public void setCleanPeriodUnit(Integer cleanPeriodUnit) {
		this.cleanPeriodUnit = cleanPeriodUnit;
	}

	/**
	 * 维护周期
	 *
	 * @return maintaince_period 维护周期
	 */
	public Integer getMaintaincePeriod() {
		return maintaincePeriod;
	}

	/**
	 * 维护周期
	 *
	 * @param maintaincePeriod 维护周期
	 */
	public void setMaintaincePeriod(Integer maintaincePeriod) {
		this.maintaincePeriod = maintaincePeriod;
	}

	/**
	 * 维护周期单位（0-年；1-月；2-日）
	 *
	 * @return maintaince_period_unit 维护周期单位（0-年；1-月；2-日）
	 */
	public Integer getMaintaincePeriodUnit() {
		return maintaincePeriodUnit;
	}

	/**
	 * 维护周期单位（0-年；1-月；2-日）
	 *
	 * @param maintaincePeriodUnit 维护周期单位（0-年；1-月；2-日）
	 */
	public void setMaintaincePeriodUnit(Integer maintaincePeriodUnit) {
		this.maintaincePeriodUnit = maintaincePeriodUnit;
	}

	/**
	 * 校准周期
	 *
	 * @return calibration_period 校准周期
	 */
	public Integer getCalibrationPeriod() {
		return calibrationPeriod;
	}

	/**
	 * 校准周期
	 *
	 * @param calibrationPeriod 校准周期
	 */
	public void setCalibrationPeriod(Integer calibrationPeriod) {
		this.calibrationPeriod = calibrationPeriod;
	}

	/**
	 * 校准周期单位（0-年；1-月；2-日）
	 *
	 * @return calibration_period_unit 校准周期单位（0-年；1-月；2-日）
	 */
	public Integer getCalibrationPeriodUnit() {
		return calibrationPeriodUnit;
	}

	/**
	 * 校准周期单位（0-年；1-月；2-日）
	 *
	 * @param calibrationPeriodUnit 校准周期单位（0-年；1-月；2-日）
	 */
	public void setCalibrationPeriodUnit(Integer calibrationPeriodUnit) {
		this.calibrationPeriodUnit = calibrationPeriodUnit;
	}

	/**
	 * 检定周期
	 *
	 * @return docimasy_period 检定周期
	 */
	public Integer getDocimasyPeriod() {
		return docimasyPeriod;
	}

	/**
	 * 检定周期
	 *
	 * @param docimasyPeriod 检定周期
	 */
	public void setDocimasyPeriod(Integer docimasyPeriod) {
		this.docimasyPeriod = docimasyPeriod;
	}

	/**
	 * 检定周期单位（0-年；1-月；2-日）
	 *
	 * @return docimasy_period_unit 检定周期单位（0-年；1-月；2-日）
	 */
	public Integer getDocimasyPeriodUnit() {
		return docimasyPeriodUnit;
	}

	/**
	 * 检定周期单位（0-年；1-月；2-日）
	 *
	 * @param docimasyPeriodUnit 检定周期单位（0-年；1-月；2-日）
	 */
	public void setDocimasyPeriodUnit(Integer docimasyPeriodUnit) {
		this.docimasyPeriodUnit = docimasyPeriodUnit;
	}

	/**
	 * 验证周期
	 *
	 * @return validate_period 验证周期
	 */
	public Integer getValidatePeriod() {
		return validatePeriod;
	}

	/**
	 * 验证周期
	 *
	 * @param validatePeriod 验证周期
	 */
	public void setValidatePeriod(Integer validatePeriod) {
		this.validatePeriod = validatePeriod;
	}

	/**
	 * 验证周期单位（0-年；1-月；2-日）
	 *
	 * @return validate_period_unit 验证周期单位（0-年；1-月；2-日）
	 */
	public Integer getValidatePeriodUnit() {
		return validatePeriodUnit;
	}

	/**
	 * 验证周期单位（0-年；1-月；2-日）
	 *
	 * @param validatePeriodUnit 验证周期单位（0-年；1-月；2-日）
	 */
	public void setValidatePeriodUnit(Integer validatePeriodUnit) {
		this.validatePeriodUnit = validatePeriodUnit;
	}

	/**
	 * 负责人ID
	 *
	 * @return charge_man_id 负责人ID
	 */
	public Long getChargeManId() {
		return chargeManId;
	}

	/**
	 * 负责人ID
	 *
	 * @param chargeManId 负责人ID
	 */
	public void setChargeManId(Long chargeManId) {
		this.chargeManId = chargeManId;
	}

	/**
	 * 负责人编码
	 *
	 * @return charge_man_code 负责人编码
	 */
	public String getChargeManCode() {
		return chargeManCode;
	}

	/**
	 * 负责人编码
	 *
	 * @param chargeManCode 负责人编码
	 */
	public void setChargeManCode(String chargeManCode) {
		this.chargeManCode = chargeManCode == null ? null : chargeManCode.trim();
	}

	/**
	 * 负责任名称
	 *
	 * @return charge_man_name 负责任名称
	 */
	public String getChargeManName() {
		return chargeManName;
	}

	/**
	 * 负责任名称
	 *
	 * @param chargeManName 负责任名称
	 */
	public void setChargeManName(String chargeManName) {
		this.chargeManName = chargeManName == null ? null : chargeManName.trim();
	}

	/**
	 * 附件ID
	 *
	 * @return file_id 附件ID
	 */
	public Long getFileId() {
		return fileId;
	}

	/**
	 * 附件ID
	 *
	 * @param fileId 附件ID
	 */
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	/**
	 * 状态
	 *
	 * @return status 状态
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 状态
	 *
	 * @param status 状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 备注
	 *
	 * @return remark 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 *
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * 创建人ID
	 *
	 * @return creater_id 创建人ID
	 */
	public Long getCreaterId() {
		return createrId;
	}

	/**
	 * 创建人ID
	 *
	 * @param createrId 创建人ID
	 */
	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	/**
	 * 创建人编码
	 *
	 * @return creater_code 创建人编码
	 */
	public String getCreaterCode() {
		return createrCode;
	}

	/**
	 * 创建人编码
	 *
	 * @param createrCode 创建人编码
	 */
	public void setCreaterCode(String createrCode) {
		this.createrCode = createrCode == null ? null : createrCode.trim();
	}

	/**
	 * 创建人名称
	 *
	 * @return creater_name 创建人名称
	 */
	public String getCreaterName() {
		return createrName;
	}

	/**
	 * 创建人名称
	 *
	 * @param createrName 创建人名称
	 */
	public void setCreaterName(String createrName) {
		this.createrName = createrName == null ? null : createrName.trim();
	}

	/**
	 * 创建时间
	 *
	 * @return create_time 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 *
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 最后修改人ID
	 *
	 * @return modifier_id 最后修改人ID
	 */
	public Long getModifierId() {
		return modifierId;
	}

	/**
	 * 最后修改人ID
	 *
	 * @param modifierId 最后修改人ID
	 */
	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}

	/**
	 * 最后修改人编码
	 *
	 * @return modifier_code 最后修改人编码
	 */
	public String getModifierCode() {
		return modifierCode;
	}

	/**
	 * 最后修改人编码
	 *
	 * @param modifierCode 最后修改人编码
	 */
	public void setModifierCode(String modifierCode) {
		this.modifierCode = modifierCode == null ? null : modifierCode.trim();
	}

	/**
	 * 最后修改人名称
	 *
	 * @return modifier_name 最后修改人名称
	 */
	public String getModifierName() {
		return modifierName;
	}

	/**
	 * 最后修改人名称
	 *
	 * @param modifierName 最后修改人名称
	 */
	public void setModifierName(String modifierName) {
		this.modifierName = modifierName == null ? null : modifierName.trim();
	}

	/**
	 * 更新时间
	 *
	 * @return update_time 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 *
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @mbg.generated
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", enterpriseId=").append(enterpriseId);
		sb.append(", enterpriseCode=").append(enterpriseCode);
		sb.append(", enterpriseName=").append(enterpriseName);
		sb.append(", parentId=").append(parentId);
		sb.append(", typeId=").append(typeId);
		sb.append(", typeCode=").append(typeCode);
		sb.append(", typeName=").append(typeName);
		sb.append(", code=").append(code);
		sb.append(", name=").append(name);
		sb.append(", specificationModel=").append(specificationModel);
		sb.append(", manufacturer=").append(manufacturer);
		sb.append(", factoryNumber=").append(factoryNumber);
		sb.append(", classifyNumber=").append(classifyNumber);
		sb.append(", measureRange=").append(measureRange);
		sb.append(", accuracyClass=").append(accuracyClass);
		sb.append(", productDate=").append(productDate);
		sb.append(", purchaseDate=").append(purchaseDate);
		sb.append(", purchaser=").append(purchaser);
		sb.append(", purchaseQuantity=").append(purchaseQuantity);
		sb.append(", purchasePrice=").append(purchasePrice);
		sb.append(", purchaseAmount=").append(purchaseAmount);
		sb.append(", yearLimit=").append(yearLimit);
		sb.append(", yearLimitUnit=").append(yearLimitUnit);
		sb.append(", purpose=").append(purpose);
		sb.append(", configSite=").append(configSite);
		sb.append(", deptId=").append(deptId);
		sb.append(", deptCode=").append(deptCode);
		sb.append(", deptName=").append(deptName);
		sb.append(", checkPeriod=").append(checkPeriod);
		sb.append(", checkPeriodUnit=").append(checkPeriodUnit);
		sb.append(", cleanPeriod=").append(cleanPeriod);
		sb.append(", cleanPeriodUnit=").append(cleanPeriodUnit);
		sb.append(", maintaincePeriod=").append(maintaincePeriod);
		sb.append(", maintaincePeriodUnit=").append(maintaincePeriodUnit);
		sb.append(", calibrationPeriod=").append(calibrationPeriod);
		sb.append(", calibrationPeriodUnit=").append(calibrationPeriodUnit);
		sb.append(", docimasyPeriod=").append(docimasyPeriod);
		sb.append(", docimasyPeriodUnit=").append(docimasyPeriodUnit);
		sb.append(", validatePeriod=").append(validatePeriod);
		sb.append(", validatePeriodUnit=").append(validatePeriodUnit);
		sb.append(", chargeManId=").append(chargeManId);
		sb.append(", chargeManCode=").append(chargeManCode);
		sb.append(", chargeManName=").append(chargeManName);
		sb.append(", fileId=").append(fileId);
		sb.append(", status=").append(status);
		sb.append(", remark=").append(remark);
		sb.append(", createrId=").append(createrId);
		sb.append(", createrCode=").append(createrCode);
		sb.append(", createrName=").append(createrName);
		sb.append(", createTime=").append(createTime);
		sb.append(", modifierId=").append(modifierId);
		sb.append(", modifierCode=").append(modifierCode);
		sb.append(", modifierName=").append(modifierName);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}