package com.rograndec.feijiayun.chain.business.report.quality.user.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * @author dongdong.zhan
 * 2017-10-18
 */
public class ResponseHealthCheckVO implements Serializable {
	
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
     * 组织机构编码
     */
    @ApiModelProperty(value = "组织机构编码（门店不显示即可）")
    private String enterpriseCode;

    /**
     * 组织机构名称
     */
    @ApiModelProperty(value = "组织机构名称（门店不显示即可）")
    private String enterpriseName;

    
    /**
     * 编码
     * */
    @ApiModelProperty(value="检查编码")
    private String code;
    
    /**
     * 计划日期
     */
    @ApiModelProperty(value = "日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date planDate;

    /**
     * 计划人员名称
     */
    @ApiModelProperty(value = "计划人员")
    private String planManName;

    /**
     * 计划年度
     */
    @ApiModelProperty(value = "检查年度")
    private Integer planYear;

    /**
     * 检查类型（0-岗前检查；1-年度检查）
     */
    @ApiModelProperty(value = "检查类型ID（0-岗前检查；1-年度检查）")
    private Integer checkType;
    
    /**
     * 检查类型（0-岗前检查；1-年度检查）
     */
    @ApiModelProperty(value = "检查类型")
    private String checkTypeName;

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

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getCheckTypeName() {
		return checkTypeName;
	}

	public void setCheckTypeName(String checkTypeName) {
		this.checkTypeName = checkTypeName;
	}
	
	
}