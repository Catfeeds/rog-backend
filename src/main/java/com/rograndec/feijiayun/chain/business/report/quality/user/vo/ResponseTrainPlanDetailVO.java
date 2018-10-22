package com.rograndec.feijiayun.chain.business.report.quality.user.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author dongdong.zhang
 *
 */
public class ResponseTrainPlanDetailVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     * 培训计划明细ID
     */
    @ApiModelProperty(value = "培训计划明细ID", required = false)
    private Long id;

    /**
     * 部门ID集合
     */
    @ApiModelProperty(value = "部门ID集合", required = false)
    private String deptIds;

    /**
     * 部门集合
     */
    @ApiModelProperty(value = "部门", required = false)
    private String deptNames;

    /**
     * 岗位ID集合
     */
    @ApiModelProperty(value = "岗位ID集合", required = false)
    private String positionIds;

    /**
     * 岗位集合
     */
    @ApiModelProperty(value = "岗位", required = false)
    private String positionNames;

    /**
     * 员工ID
     */
    @ApiModelProperty(value = "员工ID", required = false)
    private Long userId;

    /**
     * 员工
     */
    @ApiModelProperty(value = "员工", required = false)
    private String userName;

    /**
     * 培训课时
     */
    @ApiModelProperty(value = "培训课时", required = false)
    private BigDecimal trainLessonHour;

    /**
     * 培训表现
     */
    @ApiModelProperty(value = "培训表现", required = false)
    private String trainPerformance;

    /**
     * 考核项目
     */
    @ApiModelProperty(value = "考核项目", required = false)
    private String examineItem;

    /**
     * 考核方式ID
     */
    @ApiModelProperty(value = "考核方式ID", required = false)
    private Integer examineMethod;

    /**
     * 考核方式
     */
    @ApiModelProperty(value = "考核方式", required = false)
    private String examineMethodName;

   /* *//**
     * 考核开始时间
     *//*
    @ApiModelProperty(value = "考核开始时间", required = false)
    private Date examineStartTime;

    *//**
     * 考核结束时间
     *//*
    @ApiModelProperty(value = "考核结束时间", required = false)
    private Date examineEndTime;*/
    
    /**
     * 考核结束时间
     */
    @ApiModelProperty(value = "考核时间", required = false)
    private String examineTime;

    /**
     * 考核结果ID
     */
    @ApiModelProperty(value = "考核结果ID", required = false)
    private Integer examineResult;
    

    /**
     * 考核结果
     */
    @ApiModelProperty(value = "考核结果", required = false)
    private String examineResultName;

    /**
     * 采取措施
     */
    @ApiModelProperty(value = "采取措施", required = false)
    private String measures;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = false)
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public String getDeptNames() {
        return deptNames;
    }

    public void setDeptNames(String deptNames) {
        this.deptNames = deptNames;
    }

    public String getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(String positionIds) {
        this.positionIds = positionIds;
    }

    public String getPositionNames() {
        return positionNames;
    }

    public void setPositionNames(String positionNames) {
        this.positionNames = positionNames;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getTrainLessonHour() {
        return trainLessonHour;
    }

    public void setTrainLessonHour(BigDecimal trainLessonHour) {
        this.trainLessonHour = trainLessonHour;
    }

    public String getTrainPerformance() {
        return trainPerformance;
    }

    public void setTrainPerformance(String trainPerformance) {
        this.trainPerformance = trainPerformance;
    }

    public String getExamineItem() {
        return examineItem;
    }

    public void setExamineItem(String examineItem) {
        this.examineItem = examineItem;
    }

    public Integer getExamineMethod() {
        return examineMethod;
    }

    public void setExamineMethod(Integer examineMethod) {
        this.examineMethod = examineMethod;
    }

    public String getExamineMethodName() {
        return examineMethodName;
    }

    public void setExamineMethodName(String examineMethodName) {
        this.examineMethodName = examineMethodName;
    }

  /*  public Date getExamineStartTime() {
        return examineStartTime;
    }

    public void setExamineStartTime(Date examineStartTime) {
        this.examineStartTime = examineStartTime;
    }

    public Date getExamineEndTime() {
        return examineEndTime;
    }

    public void setExamineEndTime(Date examineEndTime) {
        this.examineEndTime = examineEndTime;
    }*/
    
    public Integer getExamineResult() {
        return examineResult;
    }

    public String getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(String examineTime) {
		this.examineTime = examineTime;
	}

	public void setExamineResult(Integer examineResult) {
        this.examineResult = examineResult;
    }

    public String getExamineResultName() {
        return examineResultName;
    }

    public void setExamineResultName(String examineResultName) {
        this.examineResultName = examineResultName;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
