package com.rograndec.feijiayun.chain.business.report.finance.stock.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 库存明细账: 第四层 月份层
 */
public class StockInOutMonthVO implements Serializable {

    @ApiModelProperty("是否显示本月合计")
    private Boolean showMonthTotal = Boolean.FALSE;// 是否显示本月合计


    @ApiModelProperty("本年累计")
    private StockYearMonthTotal stockYearTotal;


    @ApiModelProperty("库存明细")
    private List<StockInOutVO> inOutVOList;// 本月明细账

    @ApiModelProperty("月份")
    private Integer month;

    @ApiModelProperty("本月合计")
    private StockYearMonthTotal stockMonthTotal;

    @ApiModelProperty("起始时间，后台用")
    private Date startDate;

    @ApiModelProperty("起始时间，后台用")
    private Date endDate;

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

    public StockYearMonthTotal getStockYearTotal() {
        return stockYearTotal;
    }

    public void setStockYearTotal(StockYearMonthTotal stockYearTotal) {
        this.stockYearTotal = stockYearTotal;
    }

    public Boolean getShowMonthTotal() {
        return showMonthTotal;
    }

    public void setShowMonthTotal(Boolean showMonthTotal) {
        this.showMonthTotal = showMonthTotal;
    }

    public List<StockInOutVO> getInOutVOList() {
        return inOutVOList;
    }

    public void setInOutVOList(List<StockInOutVO> inOutVOList) {
        this.inOutVOList = inOutVOList;
    }

    public StockYearMonthTotal getStockMonthTotal() {
        return stockMonthTotal;
    }

    public void setStockMonthTotal(StockYearMonthTotal stockMonthTotal) {
        this.stockMonthTotal = stockMonthTotal;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
