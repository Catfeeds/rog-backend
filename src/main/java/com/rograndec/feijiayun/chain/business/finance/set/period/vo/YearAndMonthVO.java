package com.rograndec.feijiayun.chain.business.finance.set.period.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * 2018-01-18
 */
public class YearAndMonthVO implements Serializable {

    /**
     * 会计年度
     */
    @ApiModelProperty(value = "会计年度")
    private String year;
    
    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    private String startDate;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


}