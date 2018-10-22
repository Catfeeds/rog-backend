package com.rograndec.feijiayun.chain.business.finance.set.period.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.common.constant.status.PeriodStatus;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * @author dongdong.zhang
 * 2018-01-04
 */
public class AccountingPeriodFreshVO implements Serializable {
	
	 /**
     * 主键ID
     */
    @ApiModelProperty(value = "是否从此处开始更改      0-否；1-是")
    private Integer begin;
	
	
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    
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
    @NotNull(message = "开始日期不能为空")
    private Date startDate;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "结束日期不能为空")
    private Date endDate;

    /**
     * 状态（0-未激活，1-已结账）
     */
    @ApiModelProperty(value = "状态（0-未激活，1-已结账）")
    private Integer status;
    
    /**
     * 状态（0-未激活，1-已结账）
     */
    @ApiModelProperty(value = "状态（0-未激活，1-已结账）")
    private String statusName;
    
    /**
     * 判断是否可编辑或是删除
     */
    @ApiModelProperty(value = "判断是否可编辑或是删除")
    private boolean updateFlag = false;

    /**
     * saas_accounting_period_detail
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
     * 开始日期
     * @return start_date 开始日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 开始日期
     * @param startDate 开始日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 结束日期
     * @return end_date 结束日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 结束日期
     * @param endDate 结束日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 状态（0-未激活，1-已结账）
     * @return status 状态（0-未激活，1-已结账）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-未激活，1-已结账）
     * @param status 状态（0-未激活，1-已结账）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getStatusName() {
		return PeriodStatus.getName(status);
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public Integer getBegin() {
		return begin;
	}

	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}