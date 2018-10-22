package com.rograndec.feijiayun.chain.business.report.finance.stock.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 库存明细账: 第三层 年份层
 */
public class StockInOutYearVO implements Serializable{

    @ApiModelProperty("月份层级列表")
    private List<StockInOutMonthVO> stockInOutMonthVOList;


    @ApiModelProperty("年份")
    private Integer year;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    public List<StockInOutMonthVO> getStockInOutMonthVOList() {
        return stockInOutMonthVOList;
    }

    public void setStockInOutMonthVOList(List<StockInOutMonthVO> stockInOutMonthVOList) {
        this.stockInOutMonthVOList = stockInOutMonthVOList;
    }

}
