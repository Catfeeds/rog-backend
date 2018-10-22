package com.rograndec.feijiayun.chain.business.purchase.ret.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/9/15 15:22
 */

public class RequestPurchaseReturnParamVO {

    @ApiModelProperty(value = "查询类型（0/待入库；1/购进退出出库待复核、已完成）",required = true)
    private Integer type;

    @ApiModelProperty(value = "企业id）")
    private Long enterpriseId;

    @ApiModelProperty(value = "开始日期）")
    private String startDate;

    @ApiModelProperty(value = "结束日期）")
    private String endDate;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码")
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称")
    private String supplierName;

    @ApiModelProperty(value = "状态(81-待出库；83-待复核；1-已完成)",required = true)
    private Integer status;

    private List<Integer> statusList;

    /**
     * 退货人员名称
     */
    @ApiModelProperty(value = "退货人员名称")
    private String returnManName;

    @ApiModelProperty(value = "页码",required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "每页显示的数量",required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "日期排序 (0/倒序；1/正序;默认0)")
    private Integer dateOrder;

    @ApiModelProperty(value = "单号(0/倒序；1/正序；默认0)")
    private Integer codeOrder;

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


    /**
     * 购进退出单号
     */
    @ApiModelProperty(value = "购进退出单号")
    private String code;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getReturnManName() {
        return returnManName;
    }

    public void setReturnManName(String returnManName) {
        this.returnManName = returnManName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public Integer getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Integer dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Integer getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(Integer codeOrder) {
        this.codeOrder = codeOrder;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }
}