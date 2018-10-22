package com.rograndec.feijiayun.chain.business.distr.branch.vo.transfer;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class DistrInReturnSearchParam implements Serializable {


    @ApiModelProperty(value = "页码")
    private Integer pageNo;


    @ApiModelProperty(value = "每页显示的记录数")
    private Integer pageSize;

    /**
     * 配货单位编码
     */
    @ApiModelProperty(value = "配货单位编码")
    private String distrUnitCode;

    /**
     * 配货单位名称
     */
    @ApiModelProperty(value = "配货单位名称")
    private String distrUnitName;

    /**
     * 配进退出出库单号
     */
    @ApiModelProperty(value = "配进退出出库单号")
    private String code;
    /**
     * 配货类型
     */
    @ApiModelProperty(value = "配货类型")
    private Integer distrType;

    /**
     * 出库人员名称
     */
    @ApiModelProperty(value = "退货人员")
    private String returnManName;


    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "按某一列排序")
    private String orderName;
    @ApiModelProperty(value = "排序方式（升序：asc,降序：desc）")
    private String orderType;

    public String getDistrUnitCode() {
        return distrUnitCode;
    }

    public void setDistrUnitCode(String distrUnitCode) {
        this.distrUnitCode = distrUnitCode;
    }

    public String getDistrUnitName() {
        return distrUnitName;
    }

    public void setDistrUnitName(String distrUnitName) {
        this.distrUnitName = distrUnitName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getReturnManName() {
        return returnManName;
    }

    public void setReturnManName(String returnManName) {
        this.returnManName = returnManName;
    }
}
