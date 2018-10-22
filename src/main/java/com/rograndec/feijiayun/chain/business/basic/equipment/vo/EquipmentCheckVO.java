package com.rograndec.feijiayun.chain.business.basic.equipment.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: EquipmentCheckVO
 * @Description:  设施设备-校准和检定
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-16 13:18:06
 */
@ApiModel(value = "EquipmentCheckVO", description = "设施设备-校准和检定")
public class EquipmentCheckVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;
	
	/**
     * 企业ID
     */
	@ApiModelProperty(required = true, value = "企业ID")
	private Long enterpriseId;
	
	/**
     * 企业（组织机构编码）
     */
	@ApiModelProperty(required = true, value = "企业（组织机构编码）")
	private String enterpriseCode;
	
	/**
     * 企业（组织机构）名称
     */
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
	@ApiModelProperty(required = true, value = "类型ID")
	private Long typeId;
	
	/**
     * 类型编码
     */
	@ApiModelProperty(required = true, value = "类型编码")
	private String typeCode;
	
	/**
     * 类型名称
     */
	@ApiModelProperty(required = true, value = "类型名称")
	private String typeName;
	
	/**
     * 设备ID
     */
	@ApiModelProperty(required = true, value = "设备ID")
	private Long equipmentId;
	
	/**
     * 编码
     */
	@ApiModelProperty(required = true, value = "编码")
	private String equipmentCode;
	
	/**
     * 名称
     */
	@ApiModelProperty(required = true, value = "名称")
	private String equipmentName;
	
	/**
     * 开始时间
     */
	@ApiModelProperty(required = true, value = "开始时间")
	private Date startDate;
	
	/**
     * 结束时间
     */
	@ApiModelProperty(required = true, value = "结束时间")
	private Date endDate;
	
	/**
     * 设备状况（0-良好；1-维修；2-作废）
     */
	@ApiModelProperty(required = true, value = "设备状况（0-良好；1-维修；2-作废）")
	private Integer equipmentStatus;
	
	/**
     * 操作类型（0-校准；1-检定）
     */
	@ApiModelProperty(required = false, value = "操作类型（0-校准；1-检定）")
	private Integer operateType;
	
	/**
     * 操作机构
     */
	@ApiModelProperty(required = false, value = "操作机构")
	private String operateOrg;
	
	/**
     * 操作人员
     */
	@ApiModelProperty(required = false, value = "操作人员")
	private String operator;
	
	/**
     * 操作项目
     */
	@ApiModelProperty(required = false, value = "操作项目")
	private String operatePoject;
	
	/**
     * 技术要求
     */
	@ApiModelProperty(required = false, value = "技术要求")
	private String technologyMan;
	
	/**
     * 操作结论
     */
	@ApiModelProperty(required = false, value = "操作结论")
	private String conclusion;
	
	/**
     * 处理措施
     */
	@ApiModelProperty(required = false, value = "处理措施")
	private String measures;
	
	/**
     * 附件ID
     */
	@ApiModelProperty(required = false, value = "附件ID")
	private Long fileId;
	
	/**
     * 状态
     */
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
	 * 企业ID
	 */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	/**
	 * 企业ID
	 */
	public Long getEnterpriseId() {
		return enterpriseId;
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
	 * 操作类型（0-校准；1-检定）
	 */
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
	
	/**
	 * 操作类型（0-校准；1-检定）
	 */
	public Integer getOperateType() {
		return operateType;
	}
	
	/**
	 * 操作机构
	 */
	public void setOperateOrg(String operateOrg) {
		this.operateOrg = operateOrg;
	}
	
	/**
	 * 操作机构
	 */
	public String getOperateOrg() {
		return operateOrg;
	}
	
	/**
	 * 操作人员
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	/**
	 * 操作人员
	 */
	public String getOperator() {
		return operator;
	}
	
	/**
	 * 操作项目
	 */
	public void setOperatePoject(String operatePoject) {
		this.operatePoject = operatePoject;
	}
	
	/**
	 * 操作项目
	 */
	public String getOperatePoject() {
		return operatePoject;
	}
	
	/**
	 * 技术要求
	 */
	public void setTechnologyMan(String technologyMan) {
		this.technologyMan = technologyMan;
	}
	
	/**
	 * 技术要求
	 */
	public String getTechnologyMan() {
		return technologyMan;
	}
	
	/**
	 * 操作结论
	 */
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
	/**
	 * 操作结论
	 */
	public String getConclusion() {
		return conclusion;
	}
	
	/**
	 * 处理措施
	 */
	public void setMeasures(String measures) {
		this.measures = measures;
	}
	
	/**
	 * 处理措施
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