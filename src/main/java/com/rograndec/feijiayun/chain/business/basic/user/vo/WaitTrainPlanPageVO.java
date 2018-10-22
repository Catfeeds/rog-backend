package com.rograndec.feijiayun.chain.business.basic.user.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class WaitTrainPlanPageVO implements Serializable {

    /**
     * 培训计划ID
     */
    @ApiModelProperty(value = "培训计划ID", required = false)
    private Long id;

    /**
     * 组织机构编码
     */
    @ApiModelProperty(value = "组织机构编码", required = false)
    private String enterpriseCode;

    /**
     * 组织机构名称
     */
    @ApiModelProperty(value = "组织机构名称", required = false)
    private String enterpriseName;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期", required = false)
    private Date planDate;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号", required = false)
    private String code;

    /**
     * 计划人员
     */
    @ApiModelProperty(value = "计划人员", required = false)
    private String plannerName;

    /**
     * 年度
     */
    @ApiModelProperty(value = "年度", required = false)
    private Integer planYear;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", required = false)
    private String planTitle;

    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期", required = false)
    private Date startDate;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期", required = false)
    private Date endDate;

    /**
     * 培训类型ID
     */
    @ApiModelProperty(value = "培训类型ID", required = false)
    private Integer trainType;

    /**
     * 培训类型
     */
    @ApiModelProperty(value = "培训类型", required = false)
    private String trainTypeName;

    /**
     * 培训内容ID
     */
    @ApiModelProperty(value = "培训内容ID", required = false)
    private Integer trainContent;

    /**
     * 培训内容
     */
    @ApiModelProperty(value = "培训内容", required = false)
    private String trainContentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
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

    public Integer getPlanYear() {
        return planYear;
    }

    public void setPlanYear(Integer planYear) {
        this.planYear = planYear;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getTrainType() {
        return trainType;
    }

    public void setTrainType(Integer trainType) {
        this.trainType = trainType;
    }

    public String getTrainTypeName() {
        return trainTypeName;
    }

    public void setTrainTypeName(String trainTypeName) {
        this.trainTypeName = trainTypeName;
    }

    public Integer getTrainContent() {
        return trainContent;
    }

    public void setTrainContent(Integer trainContent) {
        this.trainContent = trainContent;
    }

    public String getTrainContentName() {
        return trainContentName;
    }

    public void setTrainContentName(String trainContentName) {
        this.trainContentName = trainContentName;
    }

    @Override
    public String toString() {
        return "WaitTrainPlanPageVO[" +
                "id=" + id +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", planDate=" + planDate +
                ", code='" + code + '\'' +
                ", plannerName='" + plannerName + '\'' +
                ", planYear=" + planYear +
                ", planTitle='" + planTitle + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", trainType=" + trainType +
                ", trainTypeName='" + trainTypeName + '\'' +
                ", trainContent=" + trainContent +
                ", trainContentName='" + trainContentName + '\'' +
                ']';
    }
}
