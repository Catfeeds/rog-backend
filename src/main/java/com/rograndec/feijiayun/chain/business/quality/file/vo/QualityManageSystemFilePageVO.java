package com.rograndec.feijiayun.chain.business.quality.file.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class QualityManageSystemFilePageVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 文件类型名称（0-质量管理制度；1-部门职责；2-岗位职责；3-操作规程；4-档案；5-报告；6-记录和凭证）
     */
    @ApiModelProperty(value = "文件类型名称（0-质量管理制度；1-部门职责；2-岗位职责；3-操作规程；4-档案；5-报告；6-记录和凭证）")
    private Integer fileType;

    /**
     * 文件类型名称（0-质量管理制度；1-部门职责；2-岗位职责；3-操作规程；4-档案；5-报告；6-记录和凭证）
     */
    @ApiModelProperty(value = "文件类型名称（0-质量管理制度；1-部门职责；2-岗位职责；3-操作规程；4-档案；5-报告；6-记录和凭证）")
    private String fileTypeName;

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
     * 分发（接收）部门名称集合
     */
    @ApiModelProperty(value = "分发（接收）部门名称集合")
    private String receiveDeptIdsName;

    /**
     * 附件ID
     */
    @ApiModelProperty(value = "附件ID")
    private Long fileId;

    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称")
    private String fileIdName;

    /**
     * 状态名称（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）
     */
    @ApiModelProperty(value = "状态名称（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）")
    private Integer status;

    /**
     * 状态名称（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）
     */
    @ApiModelProperty(value = "状态名称（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）")
    private String statusName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDraftMan() {
        return draftMan;
    }

    public void setDraftMan(String draftMan) {
        this.draftMan = draftMan;
    }

    public String getAuditMan() {
        return auditMan;
    }

    public void setAuditMan(String auditMan) {
        this.auditMan = auditMan;
    }

    public String getApprovalMan() {
        return approvalMan;
    }

    public void setApprovalMan(String approvalMan) {
        this.approvalMan = approvalMan;
    }

    public Date getDraftDate() {
        return draftDate;
    }

    public void setDraftDate(Date draftDate) {
        this.draftDate = draftDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(Date executeDate) {
        this.executeDate = executeDate;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getConferDeptName() {
        return conferDeptName;
    }

    public void setConferDeptName(String conferDeptName) {
        this.conferDeptName = conferDeptName;
    }

    public String getStoreMan() {
        return storeMan;
    }

    public void setStoreMan(String storeMan) {
        this.storeMan = storeMan;
    }

    public String getReceiveDeptIds() {
        return receiveDeptIds;
    }

    public void setReceiveDeptIds(String receiveDeptIds) {
        this.receiveDeptIds = receiveDeptIds;
    }

    public String getReceiveDeptIdsName() {
        return receiveDeptIdsName;
    }

    public void setReceiveDeptIdsName(String receiveDeptIdsName) {
        this.receiveDeptIdsName = receiveDeptIdsName;
    }

    public String getFileIdName() {
        return fileIdName;
    }

    public void setFileIdName(String fileIdName) {
        this.fileIdName = fileIdName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "QualityManageSystemFilePageVO[" +
                "id=" + id +
                ", fileType=" + fileType +
                ", fileTypeName='" + fileTypeName + '\'' +
                ", fileCode='" + fileCode + '\'' +
                ", fileName='" + fileName + '\'' +
                ", type='" + type + '\'' +
                ", purpose='" + purpose + '\'' +
                ", draftMan='" + draftMan + '\'' +
                ", auditMan='" + auditMan + '\'' +
                ", approvalMan='" + approvalMan + '\'' +
                ", draftDate=" + draftDate +
                ", approvalDate=" + approvalDate +
                ", executeDate=" + executeDate +
                ", changeTime=" + changeTime +
                ", versionCode='" + versionCode + '\'' +
                ", conferDeptName='" + conferDeptName + '\'' +
                ", storeMan='" + storeMan + '\'' +
                ", receiveDeptIds='" + receiveDeptIds + '\'' +
                ", receiveDeptIdsName='" + receiveDeptIdsName + '\'' +
                ", fileId=" + fileId +
                ", fileIdName='" + fileIdName + '\'' +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ']';
    }
}
