package com.rograndec.feijiayun.chain.business.basic.equipment.entity;

import java.io.Serializable;
import java.util.Date;

import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_equipment_maintance
 * 
 * 
 * @author dudy
 * 
 * 2017-10-13
 */
public class EquipmentMaintance implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
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
    @ApiModelProperty(value = "类型ID")
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
     * 设备ID
     */
    @ApiModelProperty(value = "设备ID")
    private Long equipmentId;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String equipmentCode;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String equipmentName;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startDate;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endDate;

    /**
     * 设备状况（0-良好；1-维修；2-作废）
     */
    @ApiModelProperty(value = "设备状况（0-良好；1-维修；2-作废）")
    private Integer equipmentStatus;

    /**
     * 操作类型（0-检查；1-清洁；2-维护）
     */
    @ApiModelProperty(value = "操作类型（0-检查；1-清洁；2-维护）")
    private Integer operateType;

    /**
     * 操作人员ID
     */
    @ApiModelProperty(value = "操作人员ID")
    private Long operatorId;

    /**
     * 操作人员编码
     */
    @ApiModelProperty(value = "操作人员编码")
    private String operatorCode;

    /**
     * 操作人员名称
     */
    @ApiModelProperty(value = "操作人员名称")
    private String operatorName;

    /**
     * 操作内容
     */
    @ApiModelProperty(value = "操作内容")
    private String operateContent;

    /**
     * 操作结果
     */
    @ApiModelProperty(value = "操作结果")
    private String operateResult;

    /**
     * 处置措施
     */
    @ApiModelProperty(value = "处置措施")
    private String measures;

    /**
     * 附件ID
     */
    @ApiModelProperty(value = "附件ID")
    private Long fileId;

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

    @ApiModelProperty(value = "开始时间格式化")
    private String startDateStr;
    @ApiModelProperty(value = "结束时间格式化")
    private String endDateStr;
    @ApiModelProperty(value = "设备状况格式化")
    private String equipmentStatusStr;
    @ApiModelProperty(value = "操作类型格式化")
    private String operateTypeStr;

	public String getStartDateStr() {
		return DateUtils.DateToString(this.startDate,"yyyy-MM-dd");
	}

	public String getEndDateStr() {
		return DateUtils.DateToString(this.endDate,"yyyy-MM-dd");
	}

	public String getEquipmentStatusStr() {
		if(this.equipmentStatus==null){
			return "";
		}
		if(this.equipmentStatus==0){
			return "良好";
		}
		if(this.equipmentStatus==1){
			return "维修";
		}
		if(this.equipmentStatus==2){
			return "作废";
		}
		return equipmentStatusStr;
	}

	public String getOperateTypeStr() {
		if(this.operateType==null){
			return "";
		}
		if(this.operateType==0){
			return "检查";
		}
		if(this.operateType==1){
			return "清洁";
		}
		if(this.operateType==2){
			return "维护";
		}
		return operateTypeStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public void setEquipmentStatusStr(String equipmentStatusStr) {
		this.equipmentStatusStr = equipmentStatusStr;
	}

	public void setOperateTypeStr(String operateTypeStr) {
		this.operateTypeStr = operateTypeStr;
	}

	/**
     * saas_equipment_maintance
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 企业（组织机构编码）
     * @return enterprise_code 企业（组织机构编码）
     */
    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    /**
     * 企业（组织机构编码）
     * @param enterpriseCode 企业（组织机构编码）
     */
    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode == null ? null : enterpriseCode.trim();
    }

    /**
     * 企业（组织机构）名称
     * @return enterprise_name 企业（组织机构）名称
     */
    public String getEnterpriseName() {
        return enterpriseName;
    }

    /**
     * 企业（组织机构）名称
     * @param enterpriseName 企业（组织机构）名称
     */
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 类型ID
     * @return type_id 类型ID
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * 类型ID
     * @param typeId 类型ID
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * 类型编码
     * @return type_code 类型编码
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 类型编码
     * @param typeCode 类型编码
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    /**
     * 类型名称
     * @return type_name 类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 类型名称
     * @param typeName 类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * 设备ID
     * @return equipment_id 设备ID
     */
    public Long getEquipmentId() {
        return equipmentId;
    }

    /**
     * 设备ID
     * @param equipmentId 设备ID
     */
    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * 编码
     * @return equipment_code 编码
     */
    public String getEquipmentCode() {
        return equipmentCode;
    }

    /**
     * 编码
     * @param equipmentCode 编码
     */
    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode == null ? null : equipmentCode.trim();
    }

    /**
     * 名称
     * @return equipment_name 名称
     */
    public String getEquipmentName() {
        return equipmentName;
    }

    /**
     * 名称
     * @param equipmentName 名称
     */
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName == null ? null : equipmentName.trim();
    }

    /**
     * 开始时间
     * @return start_date 开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 开始时间
     * @param startDate 开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 结束时间
     * @return end_date 结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 结束时间
     * @param endDate 结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 设备状况（0-良好；1-维修；2-作废）
     * @return equipment_status 设备状况（0-良好；1-维修；2-作废）
     */
    public Integer getEquipmentStatus() {
        return equipmentStatus;
    }

    /**
     * 设备状况（0-良好；1-维修；2-作废）
     * @param equipmentStatus 设备状况（0-良好；1-维修；2-作废）
     */
    public void setEquipmentStatus(Integer equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    /**
     * 操作类型（0-检查；1-清洁；2-维护）
     * @return operate_type 操作类型（0-检查；1-清洁；2-维护）
     */
    public Integer getOperateType() {
        return operateType;
    }

    /**
     * 操作类型（0-检查；1-清洁；2-维护）
     * @param operateType 操作类型（0-检查；1-清洁；2-维护）
     */
    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    /**
     * 操作人员ID
     * @return operator_id 操作人员ID
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * 操作人员ID
     * @param operatorId 操作人员ID
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 操作人员编码
     * @return operator_code 操作人员编码
     */
    public String getOperatorCode() {
        return operatorCode;
    }

    /**
     * 操作人员编码
     * @param operatorCode 操作人员编码
     */
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode == null ? null : operatorCode.trim();
    }

    /**
     * 操作人员名称
     * @return operator_name 操作人员名称
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 操作人员名称
     * @param operatorName 操作人员名称
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    /**
     * 操作内容
     * @return operate_content 操作内容
     */
    public String getOperateContent() {
        return operateContent;
    }

    /**
     * 操作内容
     * @param operateContent 操作内容
     */
    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent == null ? null : operateContent.trim();
    }

    /**
     * 操作结果
     * @return operate_result 操作结果
     */
    public String getOperateResult() {
        return operateResult;
    }

    /**
     * 操作结果
     * @param operateResult 操作结果
     */
    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult == null ? null : operateResult.trim();
    }

    /**
     * 处置措施
     * @return measures 处置措施
     */
    public String getMeasures() {
        return measures;
    }

    /**
     * 处置措施
     * @param measures 处置措施
     */
    public void setMeasures(String measures) {
        this.measures = measures == null ? null : measures.trim();
    }

    /**
     * 附件ID
     * @return file_id 附件ID
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 附件ID
     * @param fileId 附件ID
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
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
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", equipmentCode=").append(equipmentCode);
        sb.append(", equipmentName=").append(equipmentName);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", equipmentStatus=").append(equipmentStatus);
        sb.append(", operateType=").append(operateType);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorCode=").append(operatorCode);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", operateContent=").append(operateContent);
        sb.append(", operateResult=").append(operateResult);
        sb.append(", measures=").append(measures);
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
}