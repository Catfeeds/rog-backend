package com.rograndec.feijiayun.chain.business.basic.user.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_health_check
 * 
 * 
 * @author kexinhao
 * 
 * 2017-10-12
 */
public class HealthCheck implements Serializable {
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
     * 企业Code
     */
    @ApiModelProperty(value = "企业编码")
    private String enterpriseCode;
    
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;
    /**
     * 编码
     * */
    @ApiModelProperty(value="编码")
    private String code;
    
    /**
     * 单据类型
     */
    @ApiModelProperty(value = "单据类型")
    private Integer orderType;
    
    /**
     * 计划日期
     */
    @ApiModelProperty(value = "计划日期")
    private Date planDate;

    /**
     * 计划人员ID
     */
    @ApiModelProperty(value = "计划人员ID")
    private Long planManId;

    /**
     * 计划人员编码
     */
    @ApiModelProperty(value = "计划人员编码")
    private String planManCode;

    /**
     * 计划人员名称
     */
    @ApiModelProperty(value = "计划人员名称")
    private String planManName;

    /**
     * 计划年度
     */
    @ApiModelProperty(value = "计划年度")
    private Integer planYear;

    /**
     * 检查类型（0-岗前检查；1-检查类型）
     */
    @ApiModelProperty(value = "检查类型（0-岗前检查；1-检查类型）")
    private Integer checkType;

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
     * 检查机构
     */
    @ApiModelProperty(value = "检查机构")
    private String checkOrg;

    /**
     * 检查地点
     */
    @ApiModelProperty(value = "检查地点")
    private String checkPlace;

    /**
     * 状态（0-待检查；1-已检查）
     */
    @ApiModelProperty(value = "状态（0-待检查；1-已检查）")
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
     * 修改人ID
     */
    @ApiModelProperty(value = "修改人ID")
    private Long modifierId;

    /**
     * 修改人编码
     */
    @ApiModelProperty(value = "修改人编码")
    private String modifierCode;

    /**
     * 修改人名称
     */
    @ApiModelProperty(value = "修改人名称")
    private String modifierName;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * saas_health_check
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
     * 计划日期
     * @return plan_date 计划日期
     */
    public Date getPlanDate() {
        return planDate;
    }

    /**
     * 计划日期
     * @param planDate 计划日期
     */
    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    /**
     * 计划人员ID
     * @return plan_man_id 计划人员ID
     */
    public Long getPlanManId() {
        return planManId;
    }

    /**
     * 计划人员ID
     * @param planManId 计划人员ID
     */
    public void setPlanManId(Long planManId) {
        this.planManId = planManId;
    }

    /**
     * 计划人员编码
     * @return plan_man_code 计划人员编码
     */
    public String getPlanManCode() {
        return planManCode;
    }

    /**
     * 计划人员编码
     * @param planManCode 计划人员编码
     */
    public void setPlanManCode(String planManCode) {
        this.planManCode = planManCode == null ? null : planManCode.trim();
    }

    /**
     * 计划人员名称
     * @return plan_man_name 计划人员名称
     */
    public String getPlanManName() {
        return planManName;
    }

    /**
     * 计划人员名称
     * @param planManName 计划人员名称
     */
    public void setPlanManName(String planManName) {
        this.planManName = planManName == null ? null : planManName.trim();
    }

    /**
     * 计划年度
     * @return plan_year 计划年度
     */
    public Integer getPlanYear() {
        return planYear;
    }

    /**
     * 计划年度
     * @param planYear 计划年度
     */
    public void setPlanYear(Integer planYear) {
        this.planYear = planYear;
    }

    /**
     * 检查类型（0-岗前检查；1-检查类型）
     * @return check_type 检查类型（0-岗前检查；1-检查类型）
     */
    public Integer getCheckType() {
        return checkType;
    }

    /**
     * 检查类型（0-岗前检查；1-检查类型）
     * @param checkType 检查类型（0-岗前检查；1-检查类型）
     */
    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
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
     * 检查机构
     * @return check_org 检查机构
     */
    public String getCheckOrg() {
        return checkOrg;
    }

    /**
     * 检查机构
     * @param checkOrg 检查机构
     */
    public void setCheckOrg(String checkOrg) {
        this.checkOrg = checkOrg == null ? null : checkOrg.trim();
    }

    /**
     * 检查地点
     * @return check_place 检查地点
     */
    public String getCheckPlace() {
        return checkPlace;
    }

    /**
     * 检查地点
     * @param checkPlace 检查地点
     */
    public void setCheckPlace(String checkPlace) {
        this.checkPlace = checkPlace == null ? null : checkPlace.trim();
    }

    /**
     * 状态（0-待培训；1-已培训）
     * @return status 状态（0-待培训；1-已培训）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-待培训；1-已培训）
     * @param status 状态（0-待培训；1-已培训）
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
     * 修改人ID
     * @return modifier_id 修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 修改人ID
     * @param modifierId 修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 修改人编码
     * @return modifier_code 修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 修改人编码
     * @param modifierCode 修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 修改人名称
     * @return modifier_name 修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 修改人名称
     * @param modifierName 修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 修改时间
     * @return update_time 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
        sb.append(", code=").append(code);
        sb.append(", orderType=").append(orderType);
        sb.append(", parentId=").append(parentId);
        sb.append(", enterpriseCode=").append(enterpriseCode);
        sb.append(", enterpriseName=").append(enterpriseName);
        sb.append(", planDate=").append(planDate);
        sb.append(", planManId=").append(planManId);
        sb.append(", planManCode=").append(planManCode);
        sb.append(", planManName=").append(planManName);
        sb.append(", planYear=").append(planYear);
        sb.append(", checkType=").append(checkType);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", checkOrg=").append(checkOrg);
        sb.append(", checkPlace=").append(checkPlace);
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