package com.rograndec.feijiayun.chain.business.quality.review.vo;

import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ReviewRequestVO extends CommonParamSupplierAndGoods implements Serializable{


    @ApiModelProperty("当前页码(必传)")
    private Integer  pageNo;

    @ApiModelProperty("显示条数(必传)")
    private Integer pageSize;

    @ApiModelProperty("检索关键字")
    private String param;

    @ApiModelProperty("按某一列排序(日期：date,单号:code )")
    private String order;

    @ApiModelProperty("排序方式（升序：asc,降序：desc）")
    private String sort;

    @ApiModelProperty("不需要传")
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


    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }


}
