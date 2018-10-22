package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class DistrSendReqPlanVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 单据类型（5401）
     */
    @ApiModelProperty(value = "单据类型（5401）")
    private Integer orderType;

    /**
     * 要货计划单号
     */
    @ApiModelProperty(value = "要货计划单号")
    private String code;

    /**
     * 计划日期
     */
    @ApiModelProperty(value = "计划日期")
    private Date planDate;

    /**
     * 计划人员名称
     */
    @ApiModelProperty(value = "计划人员名称")
    private String plannerName;


    @ApiModelProperty(value = "要货类型（0-总部配送；3-分店间调剂）")
    private Integer requestType;

    /**
     * 要货单位编码
     */
    @ApiModelProperty(value = "要货单位编码")
    private String enterpriseCode;

    /**
     * 要货单位名称
     */
    @ApiModelProperty(value = "要货单位名称")
    private String enterpriseName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
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

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}