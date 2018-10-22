package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dudy on 2017/9/27.
 */
@ApiModel(value = "SaleOrderRequestVO",description = "停售/解停列表请求VO")
public class SaleOrderRequestVO  implements Serializable{


    @ApiModelProperty("当前页码(必传)")
    private Integer  pageNo;

    @ApiModelProperty("显示条数(必传)")
    private Integer pageSize;

    @ApiModelProperty("开始时间")
    private String startDate;

    @ApiModelProperty("结束时间")
    private  String endDate;

    @ApiModelProperty("停售/解停人员")
    private String manName;

    @ApiModelProperty("停售/解停单号")
    private String code;

    @ApiModelProperty(value = "停售通知来源（0-主管单位通知；1-陈列检查；2-药品养护；3-商品锁定）\n" +
            "解停通知来源 (0-主管单位通知；1-药品处理）")
    private Integer orderFrom;

    @ApiModelProperty("按某一列排序(日期：date,单号:code )")
    private String order;

    @ApiModelProperty("排序方式（升序：asc,降序：desc）")
    private String sort;

    /**
     * 数据库查询转换用
     */
    @ApiModelProperty("不需要传")
    private  Date start;

    /**
     * 数据库查询转换用
     */
    @ApiModelProperty("不需要传")
    private  Date end;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "不需要传")
    private Long enterpriseId;

    @ApiModelProperty(value = "不需要传")
    private Integer pageStart;

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
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

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
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

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
