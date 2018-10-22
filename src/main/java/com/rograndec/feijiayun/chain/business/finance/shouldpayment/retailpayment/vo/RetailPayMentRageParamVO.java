package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class RetailPayMentRageParamVO implements Serializable{
    private static final long serialVersionUID = -1123220374598169384L;

    private Long enterpriseId;

    private Long parentId;

    @ApiModelProperty(value = "门店编码")
    private String enterpriseCode;

    @ApiModelProperty(value = "门店名称")
    private String enterpriseName;

    @ApiModelProperty(value = "缴款单号")
    private String code;

    @ApiModelProperty(value = "状态（0-待缴款；1-已缴款；2-已冲销）")
    private Integer status;

    @ApiModelProperty(value = "缴款人员")
    private String paymentManName;

    @ApiModelProperty(value = "开始日期")
    private Date startDate;

    @ApiModelProperty(value = "结束日期")
    private Date endDate;

    @ApiModelProperty(value = "排序字段")
    private String orderName;

    @ApiModelProperty(value = "排序方式")
    private String orderType;

    @ApiModelProperty(value = "页码")
    private Integer pageNo;

    @ApiModelProperty(value = "页容量")
    private Integer pageSize;

    private Integer start;

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        if(StringUtils.isBlank(enterpriseCode)){
            enterpriseCode = null;
        }
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        if(StringUtils.isBlank(enterpriseName)){
            enterpriseName = null;
        }
        this.enterpriseName = enterpriseName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if(StringUtils.isBlank(code)){
            code = null;
        }
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPaymentManName() {
        return paymentManName;
    }

    public void setPaymentManName(String paymentManName) {
        if(StringUtils.isBlank(paymentManName)){
            paymentManName = null;
        }
        this.paymentManName = paymentManName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        if(StringUtils.isBlank(orderName)){
            orderName = null;
        }
        this.orderName = orderName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        if(StringUtils.isBlank(orderType)){
            orderType = null;
        }
        this.orderType = orderType;
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

    public Integer getStart() {
        if(this.pageNo == null || this.pageSize == null){
            start = 0;
        }else {
            start = (this.pageNo-1)*this.pageSize;
        }
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

}
