package com.rograndec.feijiayun.chain.business.basic.user.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class CheckWaitTrainPlanVO implements Serializable {

    /**
     * 培训计划ID
     */
    @ApiModelProperty(value = "培训计划ID", required = false)
    private Long id;

    /**
     * 组织机构ID
     */
    @ApiModelProperty(value = "组织机构ID", required = false)
    private Long enterpriseId;

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
     * 编号
     */
    @ApiModelProperty(value = "编号", required = false)
    private String code;

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
     * 日期
     */
    @ApiModelProperty(value = "日期", required = false)
    private Date planDate;

    /**
     * 计划人员
     */
    @ApiModelProperty(value = "计划人员", required = false)
    private Long plannerId;

    /**
     * 计划人员
     */
    @ApiModelProperty(value = "计划人员", required = false)
    private String plannerName;

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



    /**
     * 培训目的
     */
    @ApiModelProperty(value = "培训目的", required = false)
    private String trainGoal;

    /**
     * 培训方式ID
     */
    @ApiModelProperty(value = "培训方式ID", required = false)
    private Integer trainMethod;

    /**
     * 培训方式
     */
    @ApiModelProperty(value = "培训方式", required = false)
    private String trainMethodName;

    /**
     * 培训机构
     */
    @ApiModelProperty(value = "培训机构", required = false)
    private String trainOrg;

    /**
     * 培训地点
     */
    @ApiModelProperty(value = "培训地点", required = false)
    private String trainPlace;

    /**
     * 讲师
     */
    @ApiModelProperty(value = "讲师", required = false)
    private String lecturer;

    /**
     * 课时
     */
    @ApiModelProperty(value = "课时", required = false)
    private BigDecimal lessonHour;

    /**
     * 参与部门ID集合
     */
    @ApiModelProperty(value = "参与部门ID集合", required = false)
    private String deptIds;

    /**
     * 参与部门ID数组
     */
    @ApiModelProperty(value = "参与部门ID数组", required = false)
    private Long[] deptIdsArray;

    /**
     * 参与部门
     */
    @ApiModelProperty(value = "参与部门", required = false)
    private String deptIdsNames;

    /**
     * 参与岗位ID集合
     */
    @ApiModelProperty(value = "参与岗位ID集合", required = false)
    private String positionIds;

    /**
     * 参与岗位ID数组
     */
    @ApiModelProperty(value = "参与岗位ID数组", required = false)
    private Long[] positionIdsArray;


    /**
     * 参与岗位
     */
    @ApiModelProperty(value = "参与岗位", required = false)
    private String positionIdsNames;

    /**
     * 参与人员ID集合
     */
    @ApiModelProperty(value = "参与人员ID集合", required = false)
    private String userIds;

    /**
     * 参与人员ID数组
     */
    @ApiModelProperty(value = "参与人员ID数组", required = false)
    private Long[] userIdsArray;

    /**
     * 参与人员
     */
    @ApiModelProperty(value = "参与人员", required = false)
    private String userIdsName;

    /**
     * 状态Id
     */
    @ApiModelProperty(value = "状态Id", required = false)
    private Integer status;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = false)
    private String statusName;

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

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public String getPlannerName() {
        return plannerName;
    }

    public void setPlannerName(String plannerName) {
        this.plannerName = plannerName;
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

    public String getTrainGoal() {
        return trainGoal;
    }

    public void setTrainGoal(String trainGoal) {
        this.trainGoal = trainGoal;
    }

    public Integer getTrainMethod() {
        return trainMethod;
    }

    public void setTrainMethod(Integer trainMethod) {
        this.trainMethod = trainMethod;
    }

    public String getTrainMethodName() {
        return trainMethodName;
    }

    public void setTrainMethodName(String trainMethodName) {
        this.trainMethodName = trainMethodName;
    }

    public String getTrainOrg() {
        return trainOrg;
    }

    public void setTrainOrg(String trainOrg) {
        this.trainOrg = trainOrg;
    }

    public String getTrainPlace() {
        return trainPlace;
    }

    public void setTrainPlace(String trainPlace) {
        this.trainPlace = trainPlace;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public BigDecimal getLessonHour() {
        return lessonHour;
    }

    public void setLessonHour(BigDecimal lessonHour) {
        this.lessonHour = lessonHour;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public String getDeptIdsNames() {
        return deptIdsNames;
    }

    public void setDeptIdsNames(String deptIdsNames) {
        this.deptIdsNames = deptIdsNames;
    }

    public String getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(String positionIds) {
        this.positionIds = positionIds;
    }

    public String getPositionIdsNames() {
        return positionIdsNames;
    }

    public void setPositionIdsNames(String positionIdsNames) {
        this.positionIdsNames = positionIdsNames;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getUserIdsName() {
        return userIdsName;
    }

    public void setUserIdsName(String userIdsName) {
        this.userIdsName = userIdsName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(Long plannerId) {
        this.plannerId = plannerId;
    }

    public Long[] getDeptIdsArray() {
        return deptIdsArray;
    }

    public void setDeptIdsArray(Long[] deptIdsArray) {
        this.deptIdsArray = deptIdsArray;
    }

    public Long[] getPositionIdsArray() {
        return positionIdsArray;
    }

    public void setPositionIdsArray(Long[] positionIdsArray) {
        this.positionIdsArray = positionIdsArray;
    }

    public Long[] getUserIdsArray() {
        return userIdsArray;
    }

    public void setUserIdsArray(Long[] userIdsArray) {
        this.userIdsArray = userIdsArray;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    @Override
    public String toString() {
        return "CheckWaitTrainPlanVO{" +
                "id=" + id +
                ", enterpriseId='" + enterpriseId + '\'' +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", code='" + code + '\'' +
                ", planYear=" + planYear +
                ", planTitle='" + planTitle + '\'' +
                ", planDate=" + planDate +
                ", plannerId=" + plannerId +
                ", plannerName='" + plannerName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", trainType=" + trainType +
                ", trainTypeName='" + trainTypeName + '\'' +
                ", trainContent=" + trainContent +
                ", trainContentName='" + trainContentName + '\'' +
                ", trainGoal='" + trainGoal + '\'' +
                ", trainMethod=" + trainMethod +
                ", trainMethodName='" + trainMethodName + '\'' +
                ", trainOrg='" + trainOrg + '\'' +
                ", trainPlace='" + trainPlace + '\'' +
                ", lecturer='" + lecturer + '\'' +
                ", lessonHour=" + lessonHour +
                ", deptIdsArray=" + Arrays.toString(deptIdsArray) +
                ", deptIdsNames='" + deptIdsNames + '\'' +
                ", positionIdsArray=" + Arrays.toString(positionIdsArray) +
                ", positionIdsNames='" + positionIdsNames + '\'' +
                ", userIdsArray=" + Arrays.toString(userIdsArray) +
                ", userIdsName='" + userIdsName + '\'' +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
