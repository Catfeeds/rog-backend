package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class SaveChGoodsClearOrderHeadVO implements Serializable {

    @ApiModelProperty(required = true, value = "清斗日期")
    private Date clearDate;

    @ApiModelProperty(required = true, value = "清斗人员ID")
    private Long clearManId;

    @ApiModelProperty(required = true, value = "复核人员ID")
    private Long auditManId;

    @ApiModelProperty(required = true, value = "流通监管码")
    private String flowCode;

    @ApiModelProperty(required = false, value = "备注")
    private String remark;

    public Date getClearDate() {
        return clearDate;
    }

    public void setClearDate(Date clearDate) {
        this.clearDate = clearDate;
    }

    public Long getClearManId() {
        return clearManId;
    }

    public void setClearManId(Long clearManId) {
        this.clearManId = clearManId;
    }

    public Long getAuditManId() {
        return auditManId;
    }

    public void setAuditManId(Long auditManId) {
        this.auditManId = auditManId;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SaveChGoodsClearOrderHeadVO[" +
                "clearDate=" + clearDate +
                ", clearManId=" + clearManId +
                ", auditManId=" + auditManId +
                ", remark=" + remark +
                ", flowCode='" + flowCode + '\'' +
                ']';
    }
}
