package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SaveChGoodsLoadOrderHeadVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "装斗日期")
    private Date loadDate;

    @ApiModelProperty(required = true, value = "装斗人员ID")
    private Long loadManId;

    @ApiModelProperty(required = true, value = "复核人员ID")
    private Long auditManId;

    @ApiModelProperty(required = false, value = "备注")
    private String remark;

    public Date getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }

    public Long getLoadManId() {
        return loadManId;
    }

    public void setLoadManId(Long loadManId) {
        this.loadManId = loadManId;
    }

    public Long getAuditManId() {
        return auditManId;
    }

    public void setAuditManId(Long auditManId) {
        this.auditManId = auditManId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SaveChGoodsLoadOrderHeadVO[" +
                "loadDate=" + loadDate +
                ", loadManId=" + loadManId +
                ", auditManId=" + auditManId +
                ", remark=" + remark +
                ']';
    }
}
