package com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RequestParamOrgReportVO implements Serializable{
    @ApiModelProperty("起始页")
    private Integer start;

    @ApiModelProperty("页码")
    private Integer pageNo;

    @ApiModelProperty("每页显示数量")
    private Integer pageSize;


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

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


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

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}