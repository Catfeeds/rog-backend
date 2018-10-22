package com.rograndec.feijiayun.chain.business.quality.file.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_quality_manage_system_file
 * 
 * 
 * @author Asze
 * 
 * 2017-10-19
 */
public class QualityManageSystemFile implements Serializable {
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
     * 文件类型（0-质量管理制度；1-部门职责；2-岗位职责；3-操作规程；4-档案；5-报告；6-记录和凭证）
     */
    @ApiModelProperty(value = "文件类型（0-质量管理制度；1-部门职责；2-岗位职责；3-操作规程；4-档案；5-报告；6-记录和凭证）")
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
     * 种类
     */
    @ApiModelProperty(value = "种类")
    private String type;

    /**
     * 目的
     */
    @ApiModelProperty(value = "目的")
    private String purpose;

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
     * 颁发部门ID
     */
    @ApiModelProperty(value = "颁发部门ID")
    private Long conferDeptId;

    /**
     * 颁发部门编码
     */
    @ApiModelProperty(value = "颁发部门编码")
    private String conferDeptCode;

    /**
     * 颁发部门名称
     */
    @ApiModelProperty(value = "颁发部门名称")
    private String conferDeptName;

    /**
     * 保管人员
     */
    @ApiModelProperty(value = "保管人员")
    private String storeMan;

    /**
     * 分发（接收）部门ID集合
     */
    @ApiModelProperty(value = "分发（接收）部门ID集合")
    private String receiveDeptIds;

    /**
     * 附件ID
     */
    @ApiModelProperty(value = "附件ID")
    private Long fileId;

    /**
     * 状态（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）
     */
    @ApiModelProperty(value = "状态（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）")
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

    /**
     * 操作日期
     */
    @ApiModelProperty(value = "操作日期")
    private Date operateDate;

    /**
     * 操作人员
     */
    @ApiModelProperty(value = "操作人员")
    private String operator;

    /**
     * 操作备注
     */
    @ApiModelProperty(value = "操作备注")
    private String operateRemark;

    /**
     * saas_quality_manage_system_file
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
     * 文件类型（0-质量管理制度；1-部门职责；2-岗位职责；3-操作规程；4-档案；5-报告；6-记录和凭证）
     * @return file_type 文件类型（0-质量管理制度；1-部门职责；2-岗位职责；3-操作规程；4-档案；5-报告；6-记录和凭证）
     */
    public Integer getFileType() {
        return fileType;
    }

    /**
     * 文件类型（0-质量管理制度；1-部门职责；2-岗位职责；3-操作规程；4-档案；5-报告；6-记录和凭证）
     * @param fileType 文件类型（0-质量管理制度；1-部门职责；2-岗位职责；3-操作规程；4-档案；5-报告；6-记录和凭证）
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
     * 种类
     * @return type 种类
     */
    public String getType() {
        return type;
    }

    /**
     * 种类
     * @param type 种类
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 目的
     * @return purpose 目的
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * 目的
     * @param purpose 目的
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
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
     * 颁发部门ID
     * @return confer_dept_id 颁发部门ID
     */
    public Long getConferDeptId() {
        return conferDeptId;
    }

    /**
     * 颁发部门ID
     * @param conferDeptId 颁发部门ID
     */
    public void setConferDeptId(Long conferDeptId) {
        this.conferDeptId = conferDeptId;
    }

    /**
     * 颁发部门编码
     * @return confer_dept_code 颁发部门编码
     */
    public String getConferDeptCode() {
        return conferDeptCode;
    }

    /**
     * 颁发部门编码
     * @param conferDeptCode 颁发部门编码
     */
    public void setConferDeptCode(String conferDeptCode) {
        this.conferDeptCode = conferDeptCode == null ? null : conferDeptCode.trim();
    }

    /**
     * 颁发部门名称
     * @return confer_dept_name 颁发部门名称
     */
    public String getConferDeptName() {
        return conferDeptName;
    }

    /**
     * 颁发部门名称
     * @param conferDeptName 颁发部门名称
     */
    public void setConferDeptName(String conferDeptName) {
        this.conferDeptName = conferDeptName == null ? null : conferDeptName.trim();
    }

    /**
     * 保管人员
     * @return store_man 保管人员
     */
    public String getStoreMan() {
        return storeMan;
    }

    /**
     * 保管人员
     * @param storeMan 保管人员
     */
    public void setStoreMan(String storeMan) {
        this.storeMan = storeMan == null ? null : storeMan.trim();
    }

    /**
     * 分发（接收）部门ID集合
     * @return receive_dept_ids 分发（接收）部门ID集合
     */
    public String getReceiveDeptIds() {
        return receiveDeptIds;
    }

    /**
     * 分发（接收）部门ID集合
     * @param receiveDeptIds 分发（接收）部门ID集合
     */
    public void setReceiveDeptIds(String receiveDeptIds) {
        this.receiveDeptIds = receiveDeptIds == null ? null : receiveDeptIds.trim();
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
     * 状态（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）
     * @return status 状态（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）
     * @param status 状态（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）
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

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark;
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
        sb.append(", type=").append(type);
        sb.append(", purpose=").append(purpose);
        sb.append(", draftMan=").append(draftMan);
        sb.append(", auditMan=").append(auditMan);
        sb.append(", approvalMan=").append(approvalMan);
        sb.append(", draftDate=").append(draftDate);
        sb.append(", approvalDate=").append(approvalDate);
        sb.append(", executeDate=").append(executeDate);
        sb.append(", changeTime=").append(changeTime);
        sb.append(", versionCode=").append(versionCode);
        sb.append(", conferDeptId=").append(conferDeptId);
        sb.append(", conferDeptCode=").append(conferDeptCode);
        sb.append(", conferDeptName=").append(conferDeptName);
        sb.append(", storeMan=").append(storeMan);
        sb.append(", receiveDeptIds=").append(receiveDeptIds);
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
        sb.append(", operateDate=").append(operateDate);
        sb.append(", operator=").append(operator);
        sb.append(", operateRemark=").append(operateRemark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}