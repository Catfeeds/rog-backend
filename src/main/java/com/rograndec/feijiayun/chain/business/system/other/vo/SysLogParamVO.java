package com.rograndec.feijiayun.chain.business.system.other.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by ST on 2017/8/26.
 */

//@ApiModel(value = "sysLogParamVO", description = "系统日志查询条件")
public class SysLogParamVO {

    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;
    @ApiModelProperty(value = "开始时间",required = true)
    private String startDate;
    @ApiModelProperty(value = "结束时间",required = true)
    private String endDate;


    @ApiModelProperty("当前页码(必传)")
    private Integer  pageNo;

    @ApiModelProperty("显示条数(必传)")
    private Integer pageSize;

    @ApiModelProperty("后台用")
    private Date start;

    @ApiModelProperty("后台用")
    private Date end;

    @ApiModelProperty("固定值(loginTime)，可不传")
    private String  order = "login_time";

    @ApiModelProperty("排序方式（升序：asc,降序：desc）")
    private String sort;

    public String getOrder() {
        if(order == null){
            return "login_time";
        }
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        if(sort == null){
            return "desc";
        }
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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
}