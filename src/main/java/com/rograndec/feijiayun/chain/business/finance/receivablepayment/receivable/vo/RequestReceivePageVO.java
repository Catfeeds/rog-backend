package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo;


import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Date;

public class RequestReceivePageVO implements Serializable{


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
     * 起始日期
     */
    @ApiModelProperty(value = "起始日期")
    private Date startDate;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期")
    private Date endDate;

    /**
     * 付款单位类型
     */
    @ApiModelProperty(value = "付款单位类型0-供货单位；1-购货单位")
    private Integer companyType;

    /**
     * 付款单位编码
     */
    @ApiModelProperty(value = "付款单位编码")
    private String companyCode;

    /**
     * 付款单位名称
     */
    @ApiModelProperty(value = "付款单位名称")
    private String companyName;

    /**
     * 收款单号
     */
    @ApiModelProperty(value = "收款单号")
    private String code;

    /**
     * 收款人员
     */
    @ApiModelProperty(value = "收款人员")
    private String receivableManName;

    /**
     * 单据状态
     */
    @ApiModelProperty(value = "单据状态0-待收款；1-已完成；2-已冲销")
    private Integer status;

    @ApiModelProperty(value = "按某一列排序(receivableDate或者code)")
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

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReceivableManName() {
        return receivableManName;
    }

    public void setReceivableManName(String receivableManName) {
        this.receivableManName = receivableManName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
