package com.rograndec.feijiayun.chain.business.report.quality.user.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

public class RequestTraimPlanVO  implements Serializable {

	/**
     */
    private static final long serialVersionUID = 1L;
   
    @ApiModelProperty("日期范围-开始时间")
    private Date startTime;
    @ApiModelProperty("日期范围-结束时间")
    private Date endTime;
    @ApiModelProperty("组织结构类型")
    private Integer chainType;
    @ApiModelProperty("组织机构编码")
    private String enterpriseCode;
    @ApiModelProperty("组织结构名称")
    private String enterpriseName;
    @ApiModelProperty("计划编号")
    private String code;
    @ApiModelProperty("计划人员")
    private String plannerName;
    @ApiModelProperty("计划年度")
    private Long planYear;
    @ApiModelProperty("计划标题")
    private String planTitle;
    @ApiModelProperty("培训类型（0-岗前培训；1-继续培训）")
    private Long trainType;
    @ApiModelProperty("培训内容（0-法律法规；1-药品专业知识及技能；2-质量管理制度；3-职责及岗位操作规程）")
    private Long trainContent;
    @ApiModelProperty("按组织机构编码排序    0-倒序，1-顺序,默认为0")
    private Integer enterpriseCodeOrder=0;
    @ApiModelProperty("按时间排序  0-倒序，1-顺序,默认为0")
    private Integer planDateOrder=0;
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getChainType() {
		return chainType;
	}
	public void setChainType(Integer chainType) {
		this.chainType = chainType;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPlannerName() {
		return plannerName;
	}
	public void setPlannerName(String plannerName) {
		this.plannerName = plannerName;
	}
	public Long getPlanYear() {
		return planYear;
	}
	public void setPlanYear(Long planYear) {
		this.planYear = planYear;
	}
	public String getPlanTitle() {
		return planTitle;
	}
	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}
	public Long getTrainType() {
		return trainType;
	}
	public void setTrainType(Long trainType) {
		this.trainType = trainType;
	}
	public Long getTrainContent() {
		return trainContent;
	}
	public void setTrainContent(Long trainContent) {
		this.trainContent = trainContent;
	}
	public Integer getEnterpriseCodeOrder() {
		return enterpriseCodeOrder;
	}
	public void setEnterpriseCodeOrder(Integer enterpriseCodeOrder) {
		enterpriseCodeOrder=enterpriseCodeOrder==null?0:enterpriseCodeOrder;
		this.enterpriseCodeOrder = enterpriseCodeOrder;
	}
	public Integer getPlanDateOrder() {
		return planDateOrder;
	}
	public void setPlanDateOrder(Integer planDateOrder) {
		planDateOrder=planDateOrder==null?0:planDateOrder;
		this.planDateOrder = planDateOrder;
	}
    
}
