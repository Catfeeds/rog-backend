package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsRequestParam;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <获取商品销毁报表请求参数>
 * xingjian.lan
 */
@ApiModel(value = "RequestReportGoodsDestroyVO", description = "药品销毁报表")
public class RequestReportGoodsDestroyVO extends BaseGoodsRequestParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "每页显示数据", required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "查询开始时间", required = false)
    private String startDate;

    @ApiModelProperty(value = "查询结束时间", required = false)
    private String endDate;

    @ApiModelProperty(value = "销毁单号", required = false)
    private String code;

    @ApiModelProperty(value = "销毁人员", required = false)
    private String destroyManName;

    /**
     * 销毁原因
     */
    @ApiModelProperty(value = "销毁原因（0-过期；1-失效；2-破损；3-残损；4-变色；5-发霉；6-虫蛀；7-其它）")
    private Integer destroyReason;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getDestroyManName() {
        return destroyManName;
    }

    public void setDestroyManName(String destroyManName) {
        this.destroyManName = destroyManName;
    }

    public Integer getDestroyReason() {
        return destroyReason;
    }

    public void setDestroyReason(Integer destroyReason) {
        this.destroyReason = destroyReason;
    }
}