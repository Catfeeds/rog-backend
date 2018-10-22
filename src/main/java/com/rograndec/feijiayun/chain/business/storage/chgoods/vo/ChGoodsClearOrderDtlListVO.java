package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ChGoodsClearOrderDtlListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "清斗ID")
    private Long id;

    @ApiModelProperty(required = true, value = "清斗单号")
    private String code;

    @ApiModelProperty(required = true, value = "清斗日期")
    private String clearDate;

    @ApiModelProperty(required = true, value = "清斗人员")
    private String clearManName;

    @ApiModelProperty(required = true, value = "复核人员")
    private String auditManName;

    @ApiModelProperty(required = true, value = "备注")
    private String remark;

    @ApiModelProperty(required = true, value = "流通监管码")
    private String flowCode;

    List<ChGoodsClearOrderDtlShelfListVO> chGoodsClearOrderDtlShelfListVO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClearDate() {
        return clearDate;
    }

    public void setClearDate(String clearDate) {
        this.clearDate = clearDate;
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

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public List<ChGoodsClearOrderDtlShelfListVO> getChGoodsClearOrderDtlShelfListVO() {
        return chGoodsClearOrderDtlShelfListVO;
    }

    public void setChGoodsClearOrderDtlShelfListVO(List<ChGoodsClearOrderDtlShelfListVO> chGoodsClearOrderDtlShelfListVO) {
        this.chGoodsClearOrderDtlShelfListVO = chGoodsClearOrderDtlShelfListVO;
    }

    @Override
    public String toString() {
        return "ChGoodsClearOrderDtlListVO[" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", clearDate=" + clearDate +
                ", clearManName='" + clearManName + '\'' +
                ", auditManName='" + auditManName + '\'' +
                ", remark='" + remark + '\'' +
                ", flowCode='" + flowCode + '\'' +
                ", chGoodsClearOrderDtlShelfListVO=" + chGoodsClearOrderDtlShelfListVO +
                ']';
    }
}
