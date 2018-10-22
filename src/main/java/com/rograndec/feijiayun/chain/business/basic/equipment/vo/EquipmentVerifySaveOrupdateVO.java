package com.rograndec.feijiayun.chain.business.basic.equipment.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: EquipmentVerifySaveOrupdateVO
 * @Description:  设施设备-验证-Rest接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-16 13:18:19
 */
@ApiModel(value = "EquipmentVerifySaveOrupdateVO", description = "设施设备-验证")
public class EquipmentVerifySaveOrupdateVO implements Serializable {
	
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
     * 设备ID
     */
	@NotNull(message="设备ID不能为空!")
	@ApiModelProperty(required = true, value = "设备ID")
	private Long equipmentId;
	
	/**
     * 编码
     */
	@NotNull(message="编码不能为空!")
	@ApiModelProperty(required = true, value = "编码")
	private String equipmentCode;
	
	/**
     * 名称
     */
	@NotNull(message="名称不能为空!")
	@ApiModelProperty(required = true, value = "名称")
	private String equipmentName;
	
	/**
     * 开始时间
     */
	@NotNull(message="开始时间不能为空!")
	@ApiModelProperty(required = true, value = "开始时间")
	private Date startDate;
	
	/**
     * 结束时间
     */
	@NotNull(message="结束时间不能为空!")
	@ApiModelProperty(required = true, value = "结束时间")
	private Date endDate;
	
	/**
     * 设备状况（0-良好；1-维修；2-作废）
     */
	@NotNull(message="设备状况（0-良好；1-维修；2-作废）不能为空!")
	@ApiModelProperty(required = true, value = "设备状况（0-良好；1-维修；2-作废）")
	private Integer equipmentStatus;
	
	/**
     * 验证实施小组
     */
	@ApiModelProperty(required = false, value = "验证实施小组")
	private String actualizeGroup;
	
	/**
     * 验证方案编号
     */
	@ApiModelProperty(required = false, value = "验证方案编号")
	private String programNumber;
	
	/**
     * 验证报告编号
     */
	@ApiModelProperty(required = false, value = "验证报告编号")
	private String reportNumber;
	
	/**
     * 验证报告起草人员
     */
	@ApiModelProperty(required = false, value = "验证报告起草人员")
	private String draftMan;
	
	/**
     * 验证报告审核人员
     */
	@ApiModelProperty(required = false, value = "验证报告审核人员")
	private String auditMan;
	
	/**
     * 验证报告审核日期
     */
	@ApiModelProperty(required = false, value = "验证报告审核日期")
	private Date auditDate;
	
	/**
     * 验证报告批准日期
     */
	@ApiModelProperty(required = false, value = "验证报告批准日期")
	private Date approvalDate;
	
	/**
     * 评价
     */
	@ApiModelProperty(required = false, value = "评价")
	private String evaluate;
	
	/**
     * 偏差处理
     */
	@ApiModelProperty(required = false, value = "偏差处理")
	private String deviationHandle;
	
	/**
     * 验证结论
     */
	@ApiModelProperty(required = false, value = "验证结论")
	private String conclusion;
	
	/**
     * 预防措施
     */
	@ApiModelProperty(required = false, value = "预防措施")
	private String measures;
	
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
	 * 设备ID
	 */
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	/**
	 * 设备ID
	 */
	public Long getEquipmentId() {
		return equipmentId;
	}
	
	/**
	 * 编码
	 */
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	
	/**
	 * 编码
	 */
	public String getEquipmentCode() {
		return equipmentCode;
	}
	
	/**
	 * 名称
	 */
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	/**
	 * 名称
	 */
	public String getEquipmentName() {
		return equipmentName;
	}
	
	/**
	 * 开始时间
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 开始时间
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * 结束时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * 结束时间
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * 设备状况（0-良好；1-维修；2-作废）
	 */
	public void setEquipmentStatus(Integer equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}
	
	/**
	 * 设备状况（0-良好；1-维修；2-作废）
	 */
	public Integer getEquipmentStatus() {
		return equipmentStatus;
	}
	
	/**
	 * 验证实施小组
	 */
	public void setActualizeGroup(String actualizeGroup) {
		this.actualizeGroup = actualizeGroup;
	}
	
	/**
	 * 验证实施小组
	 */
	public String getActualizeGroup() {
		return actualizeGroup;
	}
	
	/**
	 * 验证方案编号
	 */
	public void setProgramNumber(String programNumber) {
		this.programNumber = programNumber;
	}
	
	/**
	 * 验证方案编号
	 */
	public String getProgramNumber() {
		return programNumber;
	}
	
	/**
	 * 验证报告编号
	 */
	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}
	
	/**
	 * 验证报告编号
	 */
	public String getReportNumber() {
		return reportNumber;
	}
	
	/**
	 * 验证报告起草人员
	 */
	public void setDraftMan(String draftMan) {
		this.draftMan = draftMan;
	}
	
	/**
	 * 验证报告起草人员
	 */
	public String getDraftMan() {
		return draftMan;
	}
	
	/**
	 * 验证报告审核人员
	 */
	public void setAuditMan(String auditMan) {
		this.auditMan = auditMan;
	}
	
	/**
	 * 验证报告审核人员
	 */
	public String getAuditMan() {
		return auditMan;
	}
	
	/**
	 * 验证报告审核日期
	 */
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
	/**
	 * 验证报告审核日期
	 */
	public Date getAuditDate() {
		return auditDate;
	}
	
	/**
	 * 验证报告批准日期
	 */
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	
	/**
	 * 验证报告批准日期
	 */
	public Date getApprovalDate() {
		return approvalDate;
	}
	
	/**
	 * 评价
	 */
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	
	/**
	 * 评价
	 */
	public String getEvaluate() {
		return evaluate;
	}
	
	/**
	 * 偏差处理
	 */
	public void setDeviationHandle(String deviationHandle) {
		this.deviationHandle = deviationHandle;
	}
	
	/**
	 * 偏差处理
	 */
	public String getDeviationHandle() {
		return deviationHandle;
	}
	
	/**
	 * 验证结论
	 */
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
	/**
	 * 验证结论
	 */
	public String getConclusion() {
		return conclusion;
	}
	
	/**
	 * 预防措施
	 */
	public void setMeasures(String measures) {
		this.measures = measures;
	}
	
	/**
	 * 预防措施
	 */
	public String getMeasures() {
		return measures;
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