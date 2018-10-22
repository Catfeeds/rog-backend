package com.rograndec.feijiayun.chain.business.quality.system.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_quality_manage_system
 * 
 * 
 * @author Asze
 * 
 * 2017-10-18
 */
public class QualityManageSystem implements Serializable {
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
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 文件类型（0-质量策划；1-质量控制；2-质量保证；3-质量改进；4-质量风险）
     */
    @ApiModelProperty(value = "文件类型（0-质量策划；1-质量控制；2-质量保证；3-质量改进；4-质量风险）")
    private Integer fileType;

    /**
     * 文件编号
     */
    @ApiModelProperty(value = "文件编号")
    private String fileCode;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    /**
     * 起草人员
     */
    @ApiModelProperty(value = "起草人员")
    private String draftMan;

    /**
     * 审核人员
     */
    @ApiModelProperty(value = "审核人员")
    private String auditMan;

    /**
     * 批准人员
     */
    @ApiModelProperty(value = "批准人员")
    private String approvalMan;

    /**
     * 起草日期
     */
    @ApiModelProperty(value = "起草日期")
    private Date draftDate;

    /**
     * 批准日期
     */
    @ApiModelProperty(value = "批准日期")
    private Date approvalDate;

    /**
     * 执行日期
     */
    @ApiModelProperty(value = "执行日期")
    private Date executeDate;

    /**
     * 变更日期
     */
    @ApiModelProperty(value = "变更日期")
    private Date changeTime;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private String versionCode;

    /**
     * 附件ID
     */
    @ApiModelProperty(value = "附件ID")
    private Long fileId;

    /**
     * 状态（0-生效；1-失效；2-废止）
     */
    @ApiModelProperty(value = "状态（0-生效；1-失效；2-废止）")
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
     * 更新时间（变更日期）
     */
    @ApiModelProperty(value = "更新时间（变更日期）")
    private Date updateTime;

    /**
     * saas_quality_manage_system
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
     * 文件类型（0-质量策划；1-质量控制；2-质量保证；3-质量改进；4-质量风险）
     * @return file_type 文件类型（0-质量策划；1-质量控制；2-质量保证；3-质量改进；4-质量风险）
     */
    public Integer getFileType() {
        return fileType;
    }

    /**
     * 文件类型（0-质量策划；1-质量控制；2-质量保证；3-质量改进；4-质量风险）
     * @param fileType 文件类型（0-质量策划；1-质量控制；2-质量保证；3-质量改进；4-质量风险）
     */
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    /**
     * 文件编号
     * @return file_code 文件编号
     */
    public String getFileCode() {
        return fileCode;
    }

    /**
     * 文件编号
     * @param fileCode 文件编号
     */
    public void setFileCode(String fileCode) {
        this.fileCode = fileCode == null ? null : fileCode.trim();
    }

    /**
     * 文件名称
     * @return file_name 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 文件名称
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 起草人员
     * @return draft_man 起草人员
     */
    public String getDraftMan() {
        return draftMan;
    }

    /**
     * 起草人员
     * @param draftMan 起草人员
     */
    public void setDraftMan(String draftMan) {
        this.draftMan = draftMan == null ? null : draftMan.trim();
    }

    /**
     * 审核人员
     * @return audit_man 审核人员
     */
    public String getAuditMan() {
        return auditMan;
    }

    /**
     * 审核人员
     * @param auditMan 审核人员
     */
    public void setAuditMan(String auditMan) {
        this.auditMan = auditMan == null ? null : auditMan.trim();
    }

    /**
     * 批准人员
     * @return approval_man 批准人员
     */
    public String getApprovalMan() {
        return approvalMan;
    }

    /**
     * 批准人员
     * @param approvalMan 批准人员
     */
    public void setApprovalMan(String approvalMan) {
        this.approvalMan = approvalMan == null ? null : approvalMan.trim();
    }

    /**
     * 起草日期
     * @return draft_date 起草日期
     */
    public Date getDraftDate() {
        return draftDate;
    }

    /**
     * 起草日期
     * @param draftDate 起草日期
     */
    public void setDraftDate(Date draftDate) {
        this.draftDate = draftDate;
    }

    /**
     * 批准日期
     * @return approval_date 批准日期
     */
    public Date getApprovalDate() {
        return approvalDate;
    }

    /**
     * 批准日期
     * @param approvalDate 批准日期
     */
    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    /**
     * 执行日期
     * @return execute_date 执行日期
     */
    public Date getExecuteDate() {
        return executeDate;
    }

    /**
     * 执行日期
     * @param executeDate 执行日期
     */
    public void setExecuteDate(Date executeDate) {
        this.executeDate = executeDate;
    }

    /**
     * 变更日期
     * @return change_time 变更日期
     */
    public Date getChangeTime() {
        return changeTime;
    }

    /**
     * 变更日期
     * @param changeTime 变更日期
     */
    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    /**
     * 版本号
     * @return version_code 版本号
     */
    public String getVersionCode() {
        return versionCode;
    }

    /**
     * 版本号
     * @param versionCode 版本号
     */
    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode == null ? null : versionCode.trim();
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
     * 状态（0-生效；1-失效；2-废止）
     * @return status 状态（0-生效；1-失效；2-废止）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-生效；1-失效；2-废止）
     * @param status 状态（0-生效；1-失效；2-废止）
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
     * 更新时间（变更日期）
     * @return update_time 更新时间（变更日期）
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间（变更日期）
     * @param updateTime 更新时间（变更日期）
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
        sb.append(", parentId=").append(parentId);
        sb.append(", fileType=").append(fileType);
        sb.append(", fileCode=").append(fileCode);
        sb.append(", fileName=").append(fileName);
        sb.append(", draftMan=").append(draftMan);
        sb.append(", auditMan=").append(auditMan);
        sb.append(", approvalMan=").append(approvalMan);
        sb.append(", draftDate=").append(draftDate);
        sb.append(", approvalDate=").append(approvalDate);
        sb.append(", executeDate=").append(executeDate);
        sb.append(", changeTime=").append(changeTime);
        sb.append(", versionCode=").append(versionCode);
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