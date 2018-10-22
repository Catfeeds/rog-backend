package com.rograndec.feijiayun.chain.business.retail.prescription.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述： 处方登记操作传递的参数
 * Created by ST on 2017/9/23 17:24
 */

public class RequestOperateParamVO {

    @ApiModelProperty(value = "登记单id" ,required = true)
    private Long id;

    @ApiModelProperty(value = "审批意见" )
    private String auditOpinion;

    @ApiModelProperty(value = "审批人id")
    private Long auditorId;

    @ApiModelProperty(value = "审批结果（0-拒绝；1-同意）")
    private Integer auditResult;


    /**
     * 调剂人ID
     */
    @ApiModelProperty(value = "调剂人ID")
    private Long swapManId;



    /**
     * 核对人ID
     */
    @ApiModelProperty(value = "核对人ID")
    private Long checkerId;



    /**
     * 发药人ID
     */
    @ApiModelProperty(value = "发药人ID")
    private Long sendId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    public Integer getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
    }

    public Long getSwapManId() {
        return swapManId;
    }

    public void setSwapManId(Long swapManId) {
        this.swapManId = swapManId;
    }

    public Long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }
}