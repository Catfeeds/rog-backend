package com.rograndec.feijiayun.chain.business.basic.user.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class HealthCheckVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    
    /**
     * 编码
     * */
    @ApiModelProperty(value="编码")
    private String code;
    
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
    @ApiModelProperty(value = "检查类型（0-岗前检查；1-年度检查）")
    private Integer checkType;
    
    /**
     * 检查类型（0-岗前检查；1-检查类型）
     */
    @ApiModelProperty(value = "检查类型描述")
    private String checkTypeDesc;

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
     * 状态（0-待培训；1-已培训）
     */
    @ApiModelProperty(value = "状态（0-待培训；1-已培训）")
    private Integer status;
    
    @ApiModelProperty(value = "状态描述")
    private String statusDesc;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "健康检查明细")
    private List<HealthCheckDetailVO> healthCheckDetailVOList;
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
     * 检查类型（0-岗前检查；1-年度检查）
     * @return check_type 检查类型（0-岗前检查；1-年度检查）
     */
    public Integer getCheckType() {
        return checkType;
    }

    /**
     * 检查类型（0-岗前检查；1-检查类型）
     * @param checkType 检查类型（0-岗前检查；1-年度检查）
     */
    public void setCheckType(Integer checkType) {
    	if(checkType!=null){
    		if(checkType.equals(0)){
    			this.checkTypeDesc="岗前检查";
    		}else if(checkType.equals(1)){
    			this.checkTypeDesc="年度检查";
    		}
    	}
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

	public String getCheckTypeDesc() {
		return checkTypeDesc;
	}

	public void setCheckTypeDesc(String checkTypeDesc) {
		this.checkTypeDesc = checkTypeDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public List<HealthCheckDetailVO> getHealthCheckDetailVOList() {
		return healthCheckDetailVOList;
	}

	public void setHealthCheckDetailVOList(List<HealthCheckDetailVO> healthCheckDetailVOList) {
		this.healthCheckDetailVOList = healthCheckDetailVOList;
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
	
}