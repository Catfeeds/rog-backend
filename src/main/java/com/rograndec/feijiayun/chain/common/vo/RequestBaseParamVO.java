package com.rograndec.feijiayun.chain.common.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：查询处方登记列表参数
 * Created by ST on 2017/9/22 14:54
 */

public class RequestBaseParamVO {

    @ApiModelProperty("页码")
    private Integer pageNo;

    @ApiModelProperty("每页显示数量")
    private Integer pageSize;

    private Integer start;

    @ApiModelProperty("日期排序 0/倒序；1/正序;默认0")
    private Integer dateOrder = 0;



    @ApiModelProperty(value = "开始日期")
    private String startDate;

    @ApiModelProperty(value = "结束日期")
    private String endDate;

    //企业id
    private Long enterpriseId;

    //企业父id
    private Long parentId;





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
        if("".equals(startDate)) return null;
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        if("".equals(endDate)) return null;
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getDateOrder() {
        if(dateOrder == null){
            return 0;
        }
        return dateOrder;
    }

    public void setDateOrder(Integer dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}