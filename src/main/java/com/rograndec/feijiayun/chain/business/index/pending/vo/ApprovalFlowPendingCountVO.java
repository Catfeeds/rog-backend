package com.rograndec.feijiayun.chain.business.index.pending.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ApprovalFlowPendingCountVO implements Serializable {
    @ApiModelProperty(value = "审批内容")
    private String content;
    @ApiModelProperty(value = "审批内容描述")
    private String contentDesc;
    @ApiModelProperty(value = "审批数量")
    private Integer count;
    @ApiModelProperty(value = "审批类型")
    private String approvalFlowType = "待审批";

    @ApiModelProperty(value = "审批描述")
    private String approvalFlowDesc;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getApprovalFlowType() {
        return approvalFlowType;
    }

    public void setApprovalFlowType(String approvalFlowType) {
        this.approvalFlowType = approvalFlowType;
    }

    public String getApprovalFlowDesc() {
        return this.contentDesc+"，"+this.count+"条";
    }

    public void setApprovalFlowDesc(String approvalFlowDesc) {
        this.approvalFlowDesc = approvalFlowDesc;
    }
}