package com.rograndec.feijiayun.chain.business.aftersale.adverse.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ReportRequestVO implements Serializable{

    @ApiModelProperty("当前页码(必传)")
    private Integer  pageNo;

    @ApiModelProperty("显示条数(必传)")
    private Integer pageSize;

    @ApiModelProperty("开始时间")
    private String startDate;

    @ApiModelProperty("结束时间")
    private String endDate;


    @ApiModelProperty("组织机构类型（0-总部；1-自营店；2-加盟店）")
    private Integer chainType;

    @ApiModelProperty("组织机构编码")
    private String enterpriseCode;

    @ApiModelProperty("组织机构名称")
    private String enterpriseName;

    @ApiModelProperty(value = "单号")
    private String code;

    @ApiModelProperty(value = "报告人姓名")
    private String reportManName;

    @ApiModelProperty(value = "是否首次报告（0-首次报告；1-跟踪报告）")
    private Integer firstReport;


    @ApiModelProperty(value = "报告类型（0-新的；1-严重；2-一般）")
    private Integer reportType;


    @ApiModelProperty("按某一列排序(日期：date,单号:code )")
    private String order;

    @ApiModelProperty("排序方式（升序：asc,降序：desc）")
    private String sort;

    @ApiModelProperty("不需要传")
    private Long enterpriseId;

    @ApiModelProperty("不需要传")
    private Date start;

    @ApiModelProperty("不需要传")
    private Date end;

    @ApiModelProperty("后台用:1-获取总部及门店所有的，2-获取当前企业的")
    private Integer Type;

    @ApiModelProperty("后台用分页起始索引")
    private Integer pageStart;

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }


    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReportManName() {
        return reportManName;
    }

    public void setReportManName(String reportManName) {
        this.reportManName = reportManName;
    }

    public Integer getFirstReport() {
        return firstReport;
    }

    public void setFirstReport(Integer firstReport) {
        this.firstReport = firstReport;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }


    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
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
