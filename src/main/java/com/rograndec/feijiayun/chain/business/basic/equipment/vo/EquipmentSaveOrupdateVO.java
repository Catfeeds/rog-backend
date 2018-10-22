package com.rograndec.feijiayun.chain.business.basic.equipment.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: EquipmentSaveOrupdateVO
 * @Description:  设施设备-Rest接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-13 13:26:02
 */
@ApiModel(value = "EquipmentSaveOrupdateVO", description = "设施设备")
public class EquipmentSaveOrupdateVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;
	
	/**
     * 企业（组织机构编码）
     */
	@NotNull(message="企业（组织机构编码）不能为空!")
	@ApiModelProperty(required = true, value = "企业（组织机构编码）")
	private String enterpriseCode;
	
	/**
     * 企业（组织机构）名称
     */
	@NotNull(message="企业（组织机构）名称不能为空!")
	@ApiModelProperty(required = true, value = "企业（组织机构）名称")
	private String enterpriseName;
	
	/**
     * 上级企业ID
     */
	@ApiModelProperty(required = false, value = "上级企业ID")
	private Long parentId;
	
	/**
     * 类型ID
     */
	@NotNull(message="类型ID不能为空!")
	@ApiModelProperty(required = true, value = "类型ID")
	private Long typeId;
	
	/**
     * 类型编码
     */
	@NotNull(message="类型编码不能为空!")
	@ApiModelProperty(required = true, value = "类型编码")
	private String typeCode;
	
	/**
     * 类型名称
     */
	@NotNull(message="类型名称不能为空!")
	@ApiModelProperty(required = true, value = "类型名称")
	private String typeName;
	
	/**
     * 编码
     */
	@NotNull(message="编码不能为空!")
	@ApiModelProperty(required = true, value = "编码")
	private String code;
	
	/**
     * 名称
     */
	@NotNull(message="名称不能为空!")
	@ApiModelProperty(required = true, value = "名称")
	private String name;
	
	/**
     * 规格型号
     */
	@ApiModelProperty(required = false, value = "规格型号")
	private String specificationModel;
	
	/**
     * 生产厂商
     */
	@ApiModelProperty(required = false, value = "生产厂商")
	private String manufacturer;
	
	/**
     * 出厂编号
     */
	@ApiModelProperty(required = false, value = "出厂编号")
	private String factoryNumber;
	
	/**
     * 分类编号
     */
	@ApiModelProperty(required = false, value = "分类编号")
	private String classifyNumber;
	
	/**
     * 测量范围
     */
	@ApiModelProperty(required = false, value = "测量范围")
	private String measureRange;
	
	/**
     * 精度等级
     */
	@ApiModelProperty(required = false, value = "精度等级")
	private String accuracyClass;
	
	/**
     * 生产日期
     */
	@ApiModelProperty(required = false, value = "生产日期")
	private Date productDate;
	
	/**
     * 购置日期
     */
	@ApiModelProperty(required = false, value = "购置日期")
	private Date purchaseDate;
	
	/**
     * 购置人员
     */
	@ApiModelProperty(required = false, value = "购置人员")
	private String purchaser;
	
	/**
     * 购置数量
     */
	@ApiModelProperty(required = false, value = "购置数量")
	private Integer purchaseQuantity;
	
	/**
     * 购置单价
     */
	@ApiModelProperty(required = false, value = "购置单价")
	private BigDecimal purchasePrice;
	
	/**
     * 购置金额
     */
	@ApiModelProperty(required = false, value = "购置金额")
	private BigDecimal purchaseAmount;
	
	/**
     * 使用年限
     */
	@ApiModelProperty(required = false, value = "使用年限")
	private Integer yearLimit;
	
	/**
     * 使用年限单位
     */
	@ApiModelProperty(required = false, value = "使用年限单位")
	private Integer yearLimitUnit;
	
	/**
     * 用途
     */
	@ApiModelProperty(required = false, value = "用途")
	private String purpose;
	
	/**
     * 配置地点
     */
	@ApiModelProperty(required = false, value = "配置地点")
	private String configSite;
	
	/**
     * 所属部门ID
     */
	@ApiModelProperty(required = false, value = "所属部门ID")
	private Long deptId;
	
	/**
     * 所属部门编码
     */
	@ApiModelProperty(required = false, value = "所属部门编码")
	private String deptCode;
	
	/**
     * 所属部门名称
     */
	@ApiModelProperty(required = false, value = "所属部门名称")
	private String deptName;
	
	/**
     * 检查周期
     */
	@ApiModelProperty(required = false, value = "检查周期")
	private Integer checkPeriod;
	
	/**
     * 检查周期单位（0-年；1-月；2-日）
     */
	@ApiModelProperty(required = false, value = "检查周期单位（0-年；1-月；2-日）")
	private Integer checkPeriodUnit;
	
	/**
     * 清洁周期
     */
	@ApiModelProperty(required = false, value = "清洁周期")
	private Integer cleanPeriod;
	
	/**
     * 清洁周期单位（0-年；1-月；2-日）
     */
	@ApiModelProperty(required = false, value = "清洁周期单位（0-年；1-月；2-日）")
	private Integer cleanPeriodUnit;
	
	/**
     * 维护周期
     */
	@ApiModelProperty(required = false, value = "维护周期")
	private Integer maintaincePeriod;
	
	/**
     * 维护周期单位（0-年；1-月；2-日）
     */
	@ApiModelProperty(required = false, value = "维护周期单位（0-年；1-月；2-日）")
	private Integer maintaincePeriodUnit;
	
	/**
     * 校准周期
     */
	@ApiModelProperty(required = false, value = "校准周期")
	private Integer calibrationPeriod;
	
	/**
     * 校准周期单位（0-年；1-月；2-日）
     */
	@ApiModelProperty(required = false, value = "校准周期单位（0-年；1-月；2-日）")
	private Integer calibrationPeriodUnit;
	
	/**
     * 检定周期
     */
	@ApiModelProperty(required = false, value = "检定周期")
	private Integer docimasyPeriod;
	
	/**
     * 检定周期单位（0-年；1-月；2-日）
     */
	@ApiModelProperty(required = false, value = "检定周期单位（0-年；1-月；2-日）")
	private Integer docimasyPeriodUnit;
	
	/**
     * 验证周期
     */
	@ApiModelProperty(required = false, value = "验证周期")
	private Integer validatePeriod;
	
	/**
     * 验证周期单位（0-年；1-月；2-日）
     */
	@ApiModelProperty(required = false, value = "验证周期单位（0-年；1-月；2-日）")
	private Integer validatePeriodUnit;
	
	/**
     * 负责人ID
     */
	@ApiModelProperty(required = false, value = "负责人ID")
	private Long chargeManId;
	
	/**
     * 负责人编码
     */
	@ApiModelProperty(required = false, value = "负责人编码")
	private String chargeManCode;
	
	/**
     * 负责任名称
     */
	@ApiModelProperty(required = false, value = "负责任名称")
	private String chargeManName;
	
	/**
     * 附件ID
     */
	@ApiModelProperty(required = false, value = "附件ID")
	private Long fileId;
	
	/**
     * 状态
     */
	@NotNull(message="状态不能为空!")
	@ApiModelProperty(required = true, value = "状态")
	private Integer status;
	
	/**
     * 备注
     */
	@ApiModelProperty(required = false, value = "备注")
	private String remark;
	

	/**
	 * 主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 主键ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 企业（组织机构编码）
	 */
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}
	
	/**
	 * 企业（组织机构编码）
	 */
	public String getEnterpriseCode() {
		return enterpriseCode;
	}
	
	/**
	 * 企业（组织机构）名称
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	
	/**
	 * 企业（组织机构）名称
	 */
	public String getEnterpriseName() {
		return enterpriseName;
	}
	
	/**
	 * 上级企业ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 上级企业ID
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * 类型ID
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	
	/**
	 * 类型ID
	 */
	public Long getTypeId() {
		return typeId;
	}
	
	/**
	 * 类型编码
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	/**
	 * 类型编码
	 */
	public String getTypeCode() {
		return typeCode;
	}
	
	/**
	 * 类型名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	/**
	 * 类型名称
	 */
	public String getTypeName() {
		return typeName;
	}
	
	/**
	 * 编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 编码
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 规格型号
	 */
	public void setSpecificationModel(String specificationModel) {
		this.specificationModel = specificationModel;
	}
	
	/**
	 * 规格型号
	 */
	public String getSpecificationModel() {
		return specificationModel;
	}
	
	/**
	 * 生产厂商
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	/**
	 * 生产厂商
	 */
	public String getManufacturer() {
		return manufacturer;
	}
	
	/**
	 * 出厂编号
	 */
	public void setFactoryNumber(String factoryNumber) {
		this.factoryNumber = factoryNumber;
	}
	
	/**
	 * 出厂编号
	 */
	public String getFactoryNumber() {
		return factoryNumber;
	}
	
	/**
	 * 分类编号
	 */
	public void setClassifyNumber(String classifyNumber) {
		this.classifyNumber = classifyNumber;
	}
	
	/**
	 * 分类编号
	 */
	public String getClassifyNumber() {
		return classifyNumber;
	}
	
	/**
	 * 测量范围
	 */
	public void setMeasureRange(String measureRange) {
		this.measureRange = measureRange;
	}
	
	/**
	 * 测量范围
	 */
	public String getMeasureRange() {
		return measureRange;
	}
	
	/**
	 * 精度等级
	 */
	public void setAccuracyClass(String accuracyClass) {
		this.accuracyClass = accuracyClass;
	}
	
	/**
	 * 精度等级
	 */
	public String getAccuracyClass() {
		return accuracyClass;
	}
	
	/**
	 * 生产日期
	 */
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	
	/**
	 * 生产日期
	 */
	public Date getProductDate() {
		return productDate;
	}
	
	/**
	 * 购置日期
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	/**
	 * 购置日期
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	
	/**
	 * 购置人员
	 */
	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}
	
	/**
	 * 购置人员
	 */
	public String getPurchaser() {
		return purchaser;
	}
	
	/**
	 * 购置数量
	 */
	public void setPurchaseQuantity(Integer purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	
	/**
	 * 购置数量
	 */
	public Integer getPurchaseQuantity() {
		return purchaseQuantity;
	}
	
	/**
	 * 购置单价
	 */
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	/**
	 * 购置单价
	 */
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	
	/**
	 * 购置金额
	 */
	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	
	/**
	 * 购置金额
	 */
	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}
	
	/**
	 * 使用年限
	 */
	public void setYearLimit(Integer yearLimit) {
		this.yearLimit = yearLimit;
	}
	
	/**
	 * 使用年限
	 */
	public Integer getYearLimit() {
		return yearLimit;
	}
	
	/**
	 * 使用年限单位
	 */
	public void setYearLimitUnit(Integer yearLimitUnit) {
		this.yearLimitUnit = yearLimitUnit;
	}
	
	/**
	 * 使用年限单位
	 */
	public Integer getYearLimitUnit() {
		return yearLimitUnit;
	}
	
	/**
	 * 用途
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	/**
	 * 用途
	 */
	public String getPurpose() {
		return purpose;
	}
	
	/**
	 * 配置地点
	 */
	public void setConfigSite(String configSite) {
		this.configSite = configSite;
	}
	
	/**
	 * 配置地点
	 */
	public String getConfigSite() {
		return configSite;
	}
	
	/**
	 * 所属部门ID
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	
	/**
	 * 所属部门ID
	 */
	public Long getDeptId() {
		return deptId;
	}
	
	/**
	 * 所属部门编码
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	/**
	 * 所属部门编码
	 */
	public String getDeptCode() {
		return deptCode;
	}
	
	/**
	 * 所属部门名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	/**
	 * 所属部门名称
	 */
	public String getDeptName() {
		return deptName;
	}
	
	/**
	 * 检查周期
	 */
	public void setCheckPeriod(Integer checkPeriod) {
		this.checkPeriod = checkPeriod;
	}
	
	/**
	 * 检查周期
	 */
	public Integer getCheckPeriod() {
		return checkPeriod;
	}
	
	/**
	 * 检查周期单位（0-年；1-月；2-日）
	 */
	public void setCheckPeriodUnit(Integer checkPeriodUnit) {
		this.checkPeriodUnit = checkPeriodUnit;
	}
	
	/**
	 * 检查周期单位（0-年；1-月；2-日）
	 */
	public Integer getCheckPeriodUnit() {
		return checkPeriodUnit;
	}
	
	/**
	 * 清洁周期
	 */
	public void setCleanPeriod(Integer cleanPeriod) {
		this.cleanPeriod = cleanPeriod;
	}
	
	/**
	 * 清洁周期
	 */
	public Integer getCleanPeriod() {
		return cleanPeriod;
	}
	
	/**
	 * 清洁周期单位（0-年；1-月；2-日）
	 */
	public void setCleanPeriodUnit(Integer cleanPeriodUnit) {
		this.cleanPeriodUnit = cleanPeriodUnit;
	}
	
	/**
	 * 清洁周期单位（0-年；1-月；2-日）
	 */
	public Integer getCleanPeriodUnit() {
		return cleanPeriodUnit;
	}
	
	/**
	 * 维护周期
	 */
	public void setMaintaincePeriod(Integer maintaincePeriod) {
		this.maintaincePeriod = maintaincePeriod;
	}
	
	/**
	 * 维护周期
	 */
	public Integer getMaintaincePeriod() {
		return maintaincePeriod;
	}
	
	/**
	 * 维护周期单位（0-年；1-月；2-日）
	 */
	public void setMaintaincePeriodUnit(Integer maintaincePeriodUnit) {
		this.maintaincePeriodUnit = maintaincePeriodUnit;
	}
	
	/**
	 * 维护周期单位（0-年；1-月；2-日）
	 */
	public Integer getMaintaincePeriodUnit() {
		return maintaincePeriodUnit;
	}
	
	/**
	 * 校准周期
	 */
	public void setCalibrationPeriod(Integer calibrationPeriod) {
		this.calibrationPeriod = calibrationPeriod;
	}
	
	/**
	 * 校准周期
	 */
	public Integer getCalibrationPeriod() {
		return calibrationPeriod;
	}
	
	/**
	 * 校准周期单位（0-年；1-月；2-日）
	 */
	public void setCalibrationPeriodUnit(Integer calibrationPeriodUnit) {
		this.calibrationPeriodUnit = calibrationPeriodUnit;
	}
	
	/**
	 * 校准周期单位（0-年；1-月；2-日）
	 */
	public Integer getCalibrationPeriodUnit() {
		return calibrationPeriodUnit;
	}
	
	/**
	 * 检定周期
	 */
	public void setDocimasyPeriod(Integer docimasyPeriod) {
		this.docimasyPeriod = docimasyPeriod;
	}
	
	/**
	 * 检定周期
	 */
	public Integer getDocimasyPeriod() {
		return docimasyPeriod;
	}
	
	/**
	 * 检定周期单位（0-年；1-月；2-日）
	 */
	public void setDocimasyPeriodUnit(Integer docimasyPeriodUnit) {
		this.docimasyPeriodUnit = docimasyPeriodUnit;
	}
	
	/**
	 * 检定周期单位（0-年；1-月；2-日）
	 */
	public Integer getDocimasyPeriodUnit() {
		return docimasyPeriodUnit;
	}
	
	/**
	 * 验证周期
	 */
	public void setValidatePeriod(Integer validatePeriod) {
		this.validatePeriod = validatePeriod;
	}
	
	/**
	 * 验证周期
	 */
	public Integer getValidatePeriod() {
		return validatePeriod;
	}
	
	/**
	 * 验证周期单位（0-年；1-月；2-日）
	 */
	public void setValidatePeriodUnit(Integer validatePeriodUnit) {
		this.validatePeriodUnit = validatePeriodUnit;
	}
	
	/**
	 * 验证周期单位（0-年；1-月；2-日）
	 */
	public Integer getValidatePeriodUnit() {
		return validatePeriodUnit;
	}
	
	/**
	 * 负责人ID
	 */
	public void setChargeManId(Long chargeManId) {
		this.chargeManId = chargeManId;
	}
	
	/**
	 * 负责人ID
	 */
	public Long getChargeManId() {
		return chargeManId;
	}
	
	/**
	 * 负责人编码
	 */
	public void setChargeManCode(String chargeManCode) {
		this.chargeManCode = chargeManCode;
	}
	
	/**
	 * 负责人编码
	 */
	public String getChargeManCode() {
		return chargeManCode;
	}
	
	/**
	 * 负责任名称
	 */
	public void setChargeManName(String chargeManName) {
		this.chargeManName = chargeManName;
	}
	
	/**
	 * 负责任名称
	 */
	public String getChargeManName() {
		return chargeManName;
	}
	
	/**
	 * 附件ID
	 */
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
	/**
	 * 附件ID
	 */
	public Long getFileId() {
		return fileId;
	}
	
	/**
	 * 状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 状态
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 备注
	 */
	public String getRemark() {
		return remark;
	}
	

}