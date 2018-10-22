package com.rograndec.feijiayun.chain.business.basic.equipment.vo;

import com.rograndec.feijiayun.chain.business.basic.equipment.constant.EquipmentStatusType;
import com.rograndec.feijiayun.chain.common.constant.OperationTypeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class EquipmentMaintanceVO implements Serializable{

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

    @ApiModelProperty(value = "设备状况名称")
    private String equipmentStatusName;


    /**
     * 操作类型（0-检查；1-清洁；2-维护）
     */
    @ApiModelProperty(value = "操作类型（0-检查；1-清洁；2-维护）")
    private Integer operateType;

    @ApiModelProperty(value = "操作类型名称")
    private String operateTypeName;

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


    @ApiModelProperty(value = "附件名称")
    private String fileName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(Integer equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    public String getOperateResult() {
        return operateResult;
    }

    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEquipmentStatusName() {

        if(equipmentStatus == null){
            return "";
        }
        return EquipmentStatusType.getValue(equipmentStatus);
    }

    public void setEquipmentStatusName(String equipmentStatusName) {
        this.equipmentStatusName = equipmentStatusName;
    }

    public String getOperateTypeName() {

        if (operateType == null){
            return "";
        }
        return OperationTypeEnum.getName(operateType);
    }

    public void setOperateTypeName(String operateTypeName) {
        this.operateTypeName = operateTypeName;
    }
}
