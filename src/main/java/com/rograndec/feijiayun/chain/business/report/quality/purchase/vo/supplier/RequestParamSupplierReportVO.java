package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier;

import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class RequestParamSupplierReportVO extends CommonParamSupplierAndGoods implements Serializable{


    @ApiModelProperty("页码")
    private Integer pageNo;

    @ApiModelProperty("每页显示数量")
    private Integer pageSize;


    @ApiModelProperty("搜索条件，门店编码、门店名称")
    private String  param ;

    @ApiModelProperty(value = "企业性质排序0/倒序；1/正序;默认1")
    private Integer natureOrder = 1;


    @ApiModelProperty(value = "机构编码0/倒序；1/正序;默认1")
    private Integer supplierCodeOrder = 1;

    private Long ownerId;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getNatureOrder() {
        return natureOrder;
    }

    public void setNatureOrder(Integer natureOrder) {
        this.natureOrder = natureOrder;
    }

    public Integer getSupplierCodeOrder() {
        return supplierCodeOrder;
    }

    public void setSupplierCodeOrder(Integer supplierCodeOrder) {
        this.supplierCodeOrder = supplierCodeOrder;
    }
}