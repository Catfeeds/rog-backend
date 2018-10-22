package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class DistrReqPlanPageVO implements Serializable {

    @ApiModelProperty(value = "要货计划D")
    private Long id;

    @ApiModelProperty(value = "日期")
    private Date planDate;

    @ApiModelProperty(value = "单号")
    private String code;

    @ApiModelProperty(value = "配货单位编码")
    private String distrUnitCode;

    @ApiModelProperty(value = "配货单位名称")
    private String distrUnitName;

    @ApiModelProperty(value = "供货单位编码")
    private String outboundUnitCode;

    @ApiModelProperty(value = "供货单位名称")
    private String outboundUnitName;

    @ApiModelProperty(value = "要货人员")
    private String plannerName;

    @ApiModelProperty(value = "配送类型ID")
    private Integer requestType;

    @ApiModelProperty(value = "配送类型")
    private String requestTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOutboundUnitCode() {
        return outboundUnitCode;
    }

    public void setOutboundUnitCode(String outboundUnitCode) {
        this.outboundUnitCode = outboundUnitCode;
    }

    public String getOutboundUnitName() {
        return outboundUnitName;
    }

    public void setOutboundUnitName(String outboundUnitName) {
        this.outboundUnitName = outboundUnitName;
    }

    public String getPlannerName() {
        return plannerName;
    }

    public void setPlannerName(String plannerName) {
        this.plannerName = plannerName;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public String getRequestTypeName() {
        return requestTypeName;
    }

    public void setRequestTypeName(String requestTypeName) {
        this.requestTypeName = requestTypeName;
    }

    public String getDistrUnitCode() {
        return distrUnitCode;
    }

    public void setDistrUnitCode(String distrUnitCode) {
        this.distrUnitCode = distrUnitCode;
    }

    public String getDistrUnitName() {
        return distrUnitName;
    }

    public void setDistrUnitName(String distrUnitName) {
        this.distrUnitName = distrUnitName;
    }

    @Override
    public String toString() {
        return "DistrReqPlanPageVO[" +
                "id=" + id +
                ", planDate=" + planDate +
                ", code='" + code + '\'' +
                ", distrUnitCode=" + distrUnitCode +
                ", distrUnitName=" + distrUnitName +
                ", outboundUnitCode='" + outboundUnitCode + '\'' +
                ", outboundUnitName='" + outboundUnitName + '\'' +
                ", plannerName='" + plannerName + '\'' +
                ", requestType=" + requestType +
                ", requestTypeName='" + requestTypeName + '\'' +
                ']';
    }
}
