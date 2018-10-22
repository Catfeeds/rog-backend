package com.rograndec.feijiayun.chain.business.finance.set.period.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
 
/**
 * 
 * saas_accounting_period_detail
 * 
 * 
 * @author rograndec
 * 
 * 2018-01-04
 */
public class AccountingPeriodDetail implements Serializable {
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
     * 会计年度
     */
    @ApiModelProperty(value = "会计年度")
    private Integer year;

    /**
     * 会计月度（1～12）
     */
    @ApiModelProperty(value = "会计月度（1～12）")
    private Integer month;

    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    private Date startDate;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期")
    private Date endDate;

    /**
     * 结账日期
     */
    @ApiModelProperty(value = "结账日期")
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
     * 状态（0-已激活；1-已结账）
     */
    @ApiModelProperty(value = "状态（0-已激活；1-已结账）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

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
     *状态（0-已激活；1-已结账）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-已激活；1-已结账）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	/**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", periodId=").append(periodId);
        sb.append(", month=").append(month);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", accountDate=").append(accountDate);
        sb.append(", accountManId=").append(accountManId);
        sb.append(", accountManCode=").append(accountManCode);
        sb.append(", accountManName=").append(accountManName);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}