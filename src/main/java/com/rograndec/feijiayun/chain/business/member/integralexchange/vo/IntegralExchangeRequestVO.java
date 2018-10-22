package com.rograndec.feijiayun.chain.business.member.integralexchange.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by dudy on 2017/9/24.
 */
@ApiModel(value = "IntegralExchangeRequestVO", description = "请求对象")
public class IntegralExchangeRequestVO implements Serializable {



    @ApiModelProperty("当前页码(必传)")
    private Integer  pageNo;

    @ApiModelProperty("显示条数(必传)")
    private Integer pageSize;

    @ApiModelProperty("开始时间")
    private String startDate;

    @ApiModelProperty("结束时间")
    private  String endDate;

    @ApiModelProperty("兑换单号")
    private String code;

    @ApiModelProperty("按某一列排序(日期：exchangeDate,单号:code )")
    private String order;

    @ApiModelProperty("排序方式（升序：asc,降序：desc）")
    private String sort;


    @ApiModelProperty(value = "兑换人员名称")
    private String exchangeManName;


    @ApiModelProperty(value = "会员卡号")
    private String memberCardCode;


    private Long enterpriseId;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getExchangeManName() {
        return exchangeManName;
    }

    public void setExchangeManName(String exchangeManName) {
        this.exchangeManName = exchangeManName;
    }

    public String getMemberCardCode() {
        return memberCardCode;
    }

    public void setMemberCardCode(String memberCardCode) {
        this.memberCardCode = memberCardCode;
    }
}
