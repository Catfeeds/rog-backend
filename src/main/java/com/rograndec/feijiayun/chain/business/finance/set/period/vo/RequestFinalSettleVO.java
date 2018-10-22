package com.rograndec.feijiayun.chain.business.finance.set.period.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;


import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_accounting_period
 * 
 * 
 * @author rograndec
 * 
 * 2018-01-11
 */
public class RequestFinalSettleVO implements Serializable {

    
    /**
     * saas_accounting_period
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 详情单据主键ID
     */
    @ApiModelProperty(value = "详情单据主键ID")
    @NotNull(message = "详情单据主键ID必填")
    private Long id;

    /**
     * 结账日期
     */
    @ApiModelProperty(value = "结账日期")
    @NotNull(message = "结账日期必填")
    private Date accountDate;

    /**
     * 结账人员ID
     */
    @ApiModelProperty(value = "结账人员ID")
    @NotNull(message = "结账人员必填")
    private Long accountManId;
    
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
}