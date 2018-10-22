package com.rograndec.feijiayun.chain.business.index.pending.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ApprovalFlowPendingVO implements Serializable {
    @ApiModelProperty(value = "审批流id")
    private Long flowId;
    @ApiModelProperty(value = "审批角色id")
    private Long roleId;
    @ApiModelProperty(value = "审批操作id")
    private Long flowActionId;
    @ApiModelProperty(value = "上次审批时间")
    private Date approveDate;
    @ApiModelProperty(value = "数据id")
    private Long dataId;
    @ApiModelProperty(value = "数据编码")
    private String code;
    @ApiModelProperty(value = "审批内容")
    private String content;
    @ApiModelProperty(value = "审批内容描述")
    private String contentDesc;
    @ApiModelProperty(value = "审批阶段")
    private String approvalStage;

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getFlowActionId() {
        return flowActionId;
    }

    public void setFlowActionId(Long flowActionId) {
        this.flowActionId = flowActionId;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getApprovalStage() {
        return approvalStage;
    }

    public void setApprovalStage(String approvalStage) {
        this.approvalStage = approvalStage;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }
}