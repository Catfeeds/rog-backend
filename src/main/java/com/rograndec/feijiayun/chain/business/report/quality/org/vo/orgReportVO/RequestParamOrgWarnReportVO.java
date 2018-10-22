package com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class RequestParamOrgWarnReportVO {


    @ApiModelProperty("搜索条件，门店编码、门店名称")
    private String  param ;

    @ApiModelProperty("分组ID")
    private Long groupId;

    @ApiModelProperty(value = "药店类型 1/自营店 2/加盟店 3/全部")
    private Integer chainType;


    @ApiModelProperty(value = "药店类型排序0/倒序；1/正序;默认1")
    private Integer chainTypeOrder = 1;

    @ApiModelProperty(value = "分组排序0/倒序；1/正序;默认1")
    private Integer groupCodeOrder = 1;

    @ApiModelProperty(value = "组织机构编码0/倒序；1/正序;默认1")
    private Integer enterpriseCodeOrder = 1;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getChainTypeOrder() {
        return chainTypeOrder == null ? 1 : chainTypeOrder;
    }

    public void setChainTypeOrder(Integer chainTypeOrder) {
        this.chainTypeOrder = chainTypeOrder;
    }

    public Integer getEnterpriseCodeOrder() {
        return enterpriseCodeOrder == null ? 1 : enterpriseCodeOrder;
    }

    public void setEnterpriseCodeOrder(Integer enterpriseCodeOrder) {
        this.enterpriseCodeOrder = enterpriseCodeOrder;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupCodeOrder() {
        return groupCodeOrder == null ? 1 : groupCodeOrder;
    }

    public void setGroupCodeOrder(Integer groupCodeOrder) {
        this.groupCodeOrder = groupCodeOrder;
    }

    public Long getGroupId() {
        return groupId;
    }

    public Integer getChainType() {
        return chainType == null ? 3 : chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

}