package com.rograndec.feijiayun.chain.business.quality.system.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class SaveQualityManageSystemVO implements Serializable {

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

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
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

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SaveQualityManageSystemVO[" +
                "fileType=" + fileType +
                ", fileCode='" + fileCode + '\'' +
                ", fileName='" + fileName + '\'' +
                ", draftMan='" + draftMan + '\'' +
                ", auditMan='" + auditMan + '\'' +
                ", approvalMan='" + approvalMan + '\'' +
                ", draftDate=" + draftDate +
                ", approvalDate=" + approvalDate +
                ", executeDate=" + executeDate +
                ", changeTime=" + changeTime +
                ", versionCode='" + versionCode + '\'' +
                ", fileId=" + fileId +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ']';
    }
}
