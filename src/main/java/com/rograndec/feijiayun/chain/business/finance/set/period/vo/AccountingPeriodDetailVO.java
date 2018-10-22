package com.rograndec.feijiayun.chain.business.finance.set.period.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.common.constant.status.PeriodStatus;
import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_accounting_period_detail
 * 
 * 
 * @author rograndec
 * 
 * 2018-01-04
 */
public class AccountingPeriodDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

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
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 会计期间总单ID
     * @return period_id 会计期间总单ID
     */
    public Long getPeriodId() {
        return periodId;
    }

    /**
     * 会计期间总单ID
     * @param periodId 会计期间总单ID
     */
    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    /**
     * 会计月度（1～12）
     * @return month 会计月度（1～12）
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * 会计月度（1～12）
     * @param month 会计月度（1～12）
     */
    public void setMonth(Integer month) {
        this.month = month;
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
     * 结账日期
     * @return account_date 结账日期
     */
    public Date getAccountDate() {
        return accountDate;
    }

    /**
     * 结账日期
     * @param accountDate 结账日期
     */
    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    /**
     * 结账人员ID
     * @return account_man_id 结账人员ID
     */
    public Long getAccountManId() {
        return accountManId;
    }

    /**
     * 结账人员ID
     * @param accountManId 结账人员ID
     */
    public void setAccountManId(Long accountManId) {
        this.accountManId = accountManId;
    }

    /**
     * 结账人员编码
     * @return account_man_code 结账人员编码
     */
    public String getAccountManCode() {
        return accountManCode;
    }

    /**
     * 结账人员编码
     * @param accountManCode 结账人员编码
     */
    public void setAccountManCode(String accountManCode) {
        this.accountManCode = accountManCode == null ? null : accountManCode.trim();
    }

    /**
     * 结账人员名称
     * @return account_man_name 结账人员名称
     */
    public String getAccountManName() {
        return accountManName;
    }

    /**
     * 结账人员名称
     * @param accountManName 结账人员名称
     */
    public void setAccountManName(String accountManName) {
        this.accountManName = accountManName == null ? null : accountManName.trim();
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

}