package com.rograndec.feijiayun.chain.business.aftersale.recall.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 翻页查询参数实体
 */
public class RecallPlanPageParamVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    private Date startDate;

    /**
     * 截止日期
     */
    @ApiModelProperty(value = "截止日期")
    private Date endDate;

    /**
     * 召回单号
     */
    @ApiModelProperty(value = "召回单号")
    private String code;

    /**
     * 通知人员
     */
    @ApiModelProperty(value = "通知人员")
    private String planManName;

    /**
     * 召回单位
     */
    @ApiModelProperty(value = "召回单位")
    private String recallCompany;

    /**
     * 召回级别
     */
    @ApiModelProperty(value = "召回级别")
    private Integer recallLevel;

    /**
     * 待排序字段
     */
    @ApiModelProperty(value = "待排序字段")
    private String orderName;

    /**
     * 排序方式
     */
    @ApiModelProperty(value = "排序方式")
    private String orderType;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNo;

    /**
     * 页容量
     */
    @ApiModelProperty(value = "页容量", required = true)
    private Integer pageSize;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlanManName() {
        return planManName;
    }

    public void setPlanManName(String planManName) {
        this.planManName = planManName;
    }

    public String getRecallCompany() {
        return recallCompany;
    }

    public void setRecallCompany(String recallCompany) {
        this.recallCompany = recallCompany;
    }

    public Integer getRecallLevel() {
        return recallLevel;
    }

    public void setRecallLevel(Integer recallLevel) {
        this.recallLevel = recallLevel;
    }

    public String getOrderName() {
        if("planDate".equals(orderName))
            orderName = "plan_date";
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

    @Override
    public String toString() {
        return "RecallPlanPageParamVO{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", code='" + code + '\'' +
                ", planManName='" + planManName + '\'' +
                ", recallCompany='" + recallCompany + '\'' +
                ", recallLevel=" + recallLevel +
                ", orderName='" + orderName + '\'' +
                ", orderType='" + orderType + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
