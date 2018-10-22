package com.rograndec.feijiayun.chain.business.finance.set.period.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
 
/**
 * @author dongdong.zhang
 * 
 * 2018-01-04
 */
@ApiModel
public class RequestAccountingPeriodDetailVO implements Serializable {
	
	@ApiModelProperty(value = "会计年度详情修改时必填id")
	private Long id;

    /**
     * 会计月度（1～12）
     */
	/*@ApiModelProperty(value = "会计月度（1～12）")
    @NotNull(message = "会计月度不能为空")
    @Min(value = 1)
    @Max(value = 12)
    private Integer month;*/

    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    @NotNull(message = "开始日期不能为空")
    private Date startDate;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期")
    @NotNull(message = "结束日期不能为空")
    private Date endDate;


    /**
     * 状态（0-已激活；1-已结账）
     */
    @ApiModelProperty(value = "状态（0-已激活；1-已结账）")
    @NotNull(message = "状态不能为空")
    private Integer status;
    
    /**
     * 修改标识
     */
    @ApiModelProperty(value = "修改标识")
    private boolean flag;

    /**
     * saas_accounting_period_detail
     */
    private static final long serialVersionUID = 1L;


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
     * 状态（0-已激活；1-已结账）
     * @return status 状态（0-已激活；1-已结账）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-已激活；1-已结账）
     * @param status 状态（0-已激活；1-已结账）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}