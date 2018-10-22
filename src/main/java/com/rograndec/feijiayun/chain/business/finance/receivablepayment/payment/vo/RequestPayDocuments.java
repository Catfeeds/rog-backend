package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class RequestPayDocuments implements Serializable{

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码",required = true)
    private Integer pageNo;

    /**
     * 显示条数
     */
    @ApiModelProperty(value = "显示条数",required = true)
    private Integer pageSize;

    /**
     * 付款单位类型
     */
    @ApiModelProperty(value = "付款单位类型0-供货单位；1-购货单位")
    private Integer companyType;

    /**
     * 付款单位ID
     */
    @ApiModelProperty(value = "付款单位Id")
    private Long companyId;

    /**
     * 起始日期
     */
    @ApiModelProperty(value = "起始日期")
    private Date startDate;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期")
    private Date endDate;

    @ApiModelProperty(value = "按某一列排序(paymentDate或者code)")
    private String order;

    @ApiModelProperty(value = "排序方式（升序：asc,降序：desc）")
    private String sort;

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

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "RequestPayDocuments{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", companyType=" + companyType +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", order='" + order + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }
}
