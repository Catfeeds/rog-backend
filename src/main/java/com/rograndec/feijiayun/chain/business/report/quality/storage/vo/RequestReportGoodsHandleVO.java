package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsRequestParam;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <获取商品处理报表请求参数>
 * xingjian.lan
 */
@ApiModel(value = "RequestReportGoodsHandleVO", description = "药品处理报表")
public class RequestReportGoodsHandleVO extends BaseGoodsRequestParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "每页显示数据", required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "状态  0-全部 34-已取消 92-待处理 33-已处理", required = true)
    private Integer status;

    @ApiModelProperty(value = "查询开始时间", required = false)
    private String startDate;

    @ApiModelProperty(value = "查询结束时间", required = false)
    private String endDate;

    @ApiModelProperty(value = "处理结果", required = false)
    private Integer handleResult;

    @ApiModelProperty(value = "处理单号", required = false)
    private String code;

    @ApiModelProperty(value = "处理人员", required = false)
    private String handleManName;

    @ApiModelProperty(required = false, value = "企业id")
    private Long enterpriseId;

    @ApiModelProperty(required = false, value = "按日期排序0-升序 1-降序")
    private Integer sortDate;
    @ApiModelProperty(required = false, value = "按单号排序0-升序 1-降序")
    private Integer sortCode;
    private String sort;

    /**
     * 搜索类型，1-搜索总部及总部下所有门店信息，2-仅当前企业信息
     */
    private Integer type;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(Integer handleResult) {
        this.handleResult = handleResult;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHandleManName() {
        return handleManName;
    }

    public void setHandleManName(String handleManName) {
        this.handleManName = handleManName;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}