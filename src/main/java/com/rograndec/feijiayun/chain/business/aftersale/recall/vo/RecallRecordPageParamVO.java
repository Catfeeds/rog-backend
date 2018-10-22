package com.rograndec.feijiayun.chain.business.aftersale.recall.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 翻页查询参数实体
 */
public class RecallRecordPageParamVO implements Serializable {

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
     * 召回人员
     */
    @ApiModelProperty(value = "召回人员")
    private String recallManName;

    /**
     * 召回处理
     */
    @ApiModelProperty(value = "召回处理")
    private Integer handleMeasures;

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

    public String getRecallManName() {
        return recallManName;
    }

    public void setRecallManName(String recallManName) {
        this.recallManName = recallManName;
    }

    public Integer getHandleMeasures() {
        return handleMeasures;
    }

    public void setHandleMeasures(Integer handleMeasures) {
        this.handleMeasures = handleMeasures;
    }

    public String getOrderName() {
        if("recallDate".equals(orderName))
            orderName = "recall_date";
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
        return "RecallRecordPageParamVO{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", code='" + code + '\'' +
                ", recallManName='" + recallManName + '\'' +
                ", handleMeasures=" + handleMeasures +
                ", orderName='" + orderName + '\'' +
                ", orderType='" + orderType + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
