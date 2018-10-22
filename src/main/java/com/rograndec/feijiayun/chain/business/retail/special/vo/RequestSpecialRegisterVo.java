package com.rograndec.feijiayun.chain.business.retail.special.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <零售管理-专管登记-列表查询param>
 *
 * @Author: Zhengbin.jin 金正斌
 * @Email: Zhengbin.jin@rograndec.com
 * @2017/9/22 18:11
 */
public class RequestSpecialRegisterVo {
    @ApiModelProperty(value = "用于分页")
    private Integer start;
    @ApiModelProperty(value = "每页记录数", required = true)
    private Integer pageSize;
    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNo;
    @ApiModelProperty(value = "登记单状态,-1-已取消 0-全部 92-待收款 33-已完成", required = true)
    private Integer status;
    @ApiModelProperty(value = "查询日期起始", required = false)
    private String startDate;
    @ApiModelProperty(value = "查询日期结束", required = false)
    private String endDate;
    @ApiModelProperty(value = "专管单号", required = false)
    private String code;
    @ApiModelProperty(value = "登记人员", required = false)
    private String registerManName;
    @ApiModelProperty(value = "购药者姓名", required = false)
    private String consumerName;
    @ApiModelProperty(required = false, value = "企业id")
    private Long enterpriseId;
    @ApiModelProperty(required = false, value = "按登记日期排序0-升序 1-降序")
    private Integer sortDate;
    @ApiModelProperty(required = false, value = "按登记单号排序0-升序 1-降序")
    private Integer sortCode;

    private String sort;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegisterManName() {
        return registerManName;
    }

    public void setRegisterManName(String registerManName) {
        this.registerManName = registerManName;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
