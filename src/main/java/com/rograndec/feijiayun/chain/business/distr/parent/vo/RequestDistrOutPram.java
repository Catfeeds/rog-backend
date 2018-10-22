package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: RequestDistrOutPram
 * @Description: 总部-配货出库-配货出库单
 * @date 2017-10-07 15:57:42
 */
@ApiModel(value = "RequestDistrOutPram", description = "总部-配货出库-配货出库单")
public class RequestDistrOutPram implements Serializable {

    @NotNull(message = "每页显示数据不能为空")
    @ApiModelProperty(value = "每页显示数据", required = true)
    private Integer pageSize;

    @NotNull(message = "页码不能为空")
    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "查询开始时间", required = false)
    private String startDate;

    @ApiModelProperty(value = "查询结束时间", required = false)
    private String endDate;
    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    @ApiModelProperty(required = true, value = "状态 31-待出库 32-待复核 33-已完成")
    private Integer status;

    /**
     * 配货出库单号
     */
    @ApiModelProperty(required = false, value = "配货出库单号")
    private String code;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(required = false, value = "配货单号")
    private String baseOrderCode;

    @ApiModelProperty(required = false, value = "要货单位ID")
    private Long requestUnitId;

    /**
     * 要货单位编码
     */
    @ApiModelProperty(required = false, value = "要货单位编码")
    private String requestUnitCode;

    /**
     * 要货单位名称
     */
    @ApiModelProperty(required = false, value = "要货单位名称")
    private String requestUnitName;


    /**
     * 配货人员名称
     */
    @ApiModelProperty(required = false, value = "配货人员名称")
    private String sendManName;


    @ApiModelProperty(required = false, value = "配送类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;


    /**
     * 出库人员名称
     */
    @ApiModelProperty(required = false, value = "出库人员名称")
    private String outManName;
    /**
     * 复核人员名称
     */
    @ApiModelProperty(required = false, value = "复核人员名称")
    private String checkerName;

    @ApiModelProperty(required = false,value = "企业id")
    private Long enterpriseId;

    @ApiModelProperty(required = false, value = "按日期排序0-升序 1-降序")
    private Integer sortDate;
    @ApiModelProperty(required = false, value = "按单号排序0-升序 1-降序")
    private Integer sortCode;
    private String sort;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getSortDate() {
        return sortDate;
    }

    public void setSortDate(Integer sortDate) {
        this.sortDate = sortDate;
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public String getRequestUnitCode() {
        return requestUnitCode;
    }

    public void setRequestUnitCode(String requestUnitCode) {
        this.requestUnitCode = requestUnitCode;
    }

    public String getRequestUnitName() {
        return requestUnitName;
    }

    public void setRequestUnitName(String requestUnitName) {
        this.requestUnitName = requestUnitName;
    }

    public String getSendManName() {
        return sendManName;
    }

    public void setSendManName(String sendManName) {
        this.sendManName = sendManName;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public String getOutManName() {
        return outManName;
    }

    public void setOutManName(String outManName) {
        this.outManName = outManName;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public Long getRequestUnitId() {
        return requestUnitId;
    }

    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }
}