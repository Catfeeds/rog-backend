package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ChGoodsClearOrderListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "装斗ID")
    private Long id;

    @ApiModelProperty(required = true, value = "日期")
    private String clearDate;

    @ApiModelProperty(required = true, value = "单号")
    private String code;

    @ApiModelProperty(required = true, value = "清斗人员")
    private String clearManName;

    @ApiModelProperty(required = true, value = "复核人员")
    private String auditManName;

    @ApiModelProperty(required = true, value = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClearDate() {
        return clearDate;
    }

    public void setClearDate(String clearDate) {
        this.clearDate = clearDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClearManName() {
        return clearManName;
    }

    public void setClearManName(String clearManName) {
        this.clearManName = clearManName;
    }

    public String getAuditManName() {
        return auditManName;
    }

    public void setAuditManName(String auditManName) {
        this.auditManName = auditManName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ChGoodsClearOrderListVO[" +
                "id=" + id +
                ", clearDate=" + clearDate +
                ", code='" + code + '\'' +
                ", clearManName='" + clearManName + '\'' +
                ", auditManName='" + auditManName + '\'' +
                ", remark='" + remark + '\'' +
                ']';
    }
}
