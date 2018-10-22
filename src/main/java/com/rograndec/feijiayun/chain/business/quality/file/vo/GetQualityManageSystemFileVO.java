package com.rograndec.feijiayun.chain.business.quality.file.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class GetQualityManageSystemFileVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 文件类型（0-质量管理制度；1-部门职责；2-岗位职责；3-操作规程；4-档案；5-报告；6-记录和凭证）
     */
    @ApiModelProperty(value = "文件类型（0-质量管理制度；1-部门职责；2-岗位职责；3-操作规程；4-档案；5-报告；6-记录和凭证）")
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
     * 颁发部门ID
     */
    @ApiModelProperty(value = "颁发部门ID")
    private Long conferDeptIdTwo;

    /**
     * 颁发部门ID
     */
    @ApiModelProperty(value = "颁发部门ID")
    private Long[] conferDeptId;

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
    private Long[] receiveDeptIds;

    /**
     * 分发（接收）部门ID集合
     */
    @ApiModelProperty(value = "分发（接收）部门ID集合")
    private String receiveDeptIdsTwo;

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
     * 状态（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）
     */
    @ApiModelProperty(value = "状态（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）")
    private Integer status;

    /**
     * 状态名称（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）
     */
    @ApiModelProperty(value = "状态名称（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）")
    private String statusName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
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

    public Long[] getConferDeptId() {
        return conferDeptId;
    }

    public void setConferDeptId(Long[] conferDeptId) {
        this.conferDeptId = conferDeptId;
    }

    public String getConferDeptCode() {
        return conferDeptCode;
    }

    public void setConferDeptCode(String conferDeptCode) {
        this.conferDeptCode = conferDeptCode;
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

    public Long[] getReceiveDeptIds() {
        return receiveDeptIds;
    }

    public void setReceiveDeptIds(Long[] receiveDeptIds) {
        this.receiveDeptIds = receiveDeptIds;
    }

    public String getReceiveDeptIdsName() {
        return receiveDeptIdsName;
    }

    public void setReceiveDeptIdsName(String receiveDeptIdsName) {
        this.receiveDeptIdsName = receiveDeptIdsName;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileIdName() {
        return fileIdName;
    }

    public void setFileIdName(String fileIdName) {
        this.fileIdName = fileIdName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getConferDeptIdTwo() {
        return conferDeptIdTwo;
    }

    public void setConferDeptIdTwo(Long conferDeptIdTwo) {
        this.conferDeptIdTwo = conferDeptIdTwo;
    }

    public String getReceiveDeptIdsTwo() {
        return receiveDeptIdsTwo;
    }

    public void setReceiveDeptIdsTwo(String receiveDeptIdsTwo) {
        this.receiveDeptIdsTwo = receiveDeptIdsTwo;
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

    @Override
    public String toString() {
        return "GetQualityManageSystemFileVO[" +
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
                ", conferDeptIdTwo=" + conferDeptIdTwo +
                ", conferDeptId=" + Arrays.toString(conferDeptId) +
                ", conferDeptCode='" + conferDeptCode + '\'' +
                ", conferDeptName='" + conferDeptName + '\'' +
                ", storeMan='" + storeMan + '\'' +
                ", receiveDeptIds=" + Arrays.toString(receiveDeptIds) +
                ", receiveDeptIdsTwo='" + receiveDeptIdsTwo + '\'' +
                ", receiveDeptIdsName='" + receiveDeptIdsName + '\'' +
                ", fileId=" + fileId +
                ", fileIdName='" + fileIdName + '\'' +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", remark='" + remark + '\'' +
                ", operateDate=" + operateDate +
                ", operator='" + operator + '\'' +
                ", operateRemark='" + operateRemark + '\'' +
                ']';
    }
}
