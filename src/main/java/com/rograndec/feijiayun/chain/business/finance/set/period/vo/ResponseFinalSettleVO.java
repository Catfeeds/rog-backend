package com.rograndec.feijiayun.chain.business.finance.set.period.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_accounting_period
 * 
 * 
 * @author rograndec
 * 
 * 2018-01-04
 */
public class ResponseFinalSettleVO implements Serializable {
    /**
     * 详情单据主键ID
     */
    @ApiModelProperty(value = "详情单据主键ID")
    private Long id;

    /**
     * 会计年度
     */
    @ApiModelProperty(value = "会计年度")
    private Integer year;

    /**
     * 会计期间总单ID
     */
    @ApiModelProperty(value = "会计期间总单ID")
    private Long periodId;

    /**
     * 会计月度（1～12）
     */
    @ApiModelProperty(value = "会计月度（1～12）")
    private Integer month;

    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    /**
     * 结账日期
     */
    @ApiModelProperty(value = "结账日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date accountDate;

    /**
     * 结账人员ID
     */
    @ApiModelProperty(value = "结账人员ID")
    private Long accountManId;

    /**
     * 结账人员编码
     */
    @ApiModelProperty(value = "结账人员编码")
    private String accountManCode;

    /**
     * 结账人员名称
     */
    @ApiModelProperty(value = "结账人员名称")
    private String accountManName;
    
    /**
     * saas_accounting_period
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * 会计年度
     * @return year 会计年度
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 会计年度
     * @param year 会计年度
     */
    public void setYear(Integer year) {
        this.year = year;
    }

	public Long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
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

	public Date getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}

	public Long getAccountManId() {
		return accountManId;
	}

	public void setAccountManId(Long accountManId) {
		this.accountManId = accountManId;
	}

	public String getAccountManCode() {
		return accountManCode;
	}

	public void setAccountManCode(String accountManCode) {
		this.accountManCode = accountManCode;
	}

	public String getAccountManName() {
		return accountManName;
	}

	public void setAccountManName(String accountManName) {
		this.accountManName = accountManName;
	}

}