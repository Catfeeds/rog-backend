package com.rograndec.feijiayun.chain.business.report.finance.stock.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @program: chain-backend
 * @description: 库从总账 月份层级 按商品
 * @author: dongyang.du
 * @create: 2018-01-14 18:05
 **/
public class StockLedgerMonthByGoodsVO implements Serializable{


    @ApiModelProperty("年份")
    private Integer year;

    @ApiModelProperty("月份")
    private Integer month;

    @ApiModelProperty
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @ApiModelProperty
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;


    @ApiModelProperty("商品层级")
    private List<StockLedgerGoodsVO> goodsVOList;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public List<StockLedgerGoodsVO> getGoodsVOList() {
        return goodsVOList;
    }

    public void setGoodsVOList(List<StockLedgerGoodsVO> goodsVOList) {
        this.goodsVOList = goodsVOList;
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
}
