package com.rograndec.feijiayun.chain.business.basic.user.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_train_plan
 * 
 * 
 * @author Asze
 * 
 * 2017-10-12
 */
public class TrainPlan implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业（组织机构）ID
     */
    @ApiModelProperty(value = "企业（组织机构）ID")
    private Long enterpriseId;

    /**
     * 企业（组织机构编码）
     */
    @ApiModelProperty(value = "企业（组织机构编码）")
    private String enterpriseCode;

    /**
     * 企业（组织机构）名称
     */
    @ApiModelProperty(value = "企业（组织机构）名称")
    private String enterpriseName;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 计划单据号
     */
    @ApiModelProperty(value = "计划单据号")
    private String code;

    /**
     * 单据类型（3141）
     */
    @ApiModelProperty(value = "单据类型（3141）")
    private Integer orderType;

    /**
     * 计划人员ID
     */
    @ApiModelProperty(value = "计划人员ID")
    private Long plannerId;

    /**
     * 计划人员编码
     */
    @ApiModelProperty(value = "计划人员编码")
    private String plannerCode;

    /**
     * 计划人员名称
     */
    @ApiModelProperty(value = "计划人员名称")
    private String plannerName;

    /**
     * 计划时间
     */
    @ApiModelProperty(value = "计划时间")
    private Date planDate;

    /**
     * 计划标题
     */
    @ApiModelProperty(value = "计划标题")
    private String planTitle;

    /**
     * 计划年度
     */
    @ApiModelProperty(value = "计划年度")
    private Integer planYear;

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
     * 培训类型（0-岗前培训；1-继续培训）
     */
    @ApiModelProperty(value = "培训类型（0-岗前培训；1-继续培训）")
    private Integer trainType;

    /**
     * 培训内容（0-法律法规；1-药品专业知识及技能；2-质量管理制度；3-职责及岗位操作规程）
     */
    @ApiModelProperty(value = "培训内容（0-法律法规；1-药品专业知识及技能；2-质量管理制度；3-职责及岗位操作规程）")
    private Integer trainContent;

    /**
     * 培训目的
     */
    @ApiModelProperty(value = "培训目的")
    private String trainGoal;

    /**
     * 培训方式（0-远程培训；1-现场培训）
     */
    @ApiModelProperty(value = "培训方式（0-远程培训；1-现场培训）")
    private Integer trainMethod;

    /**
     * 培训机构
     */
    @ApiModelProperty(value = "培训机构")
    private String trainOrg;

    /**
     * 培训地点
     */
    @ApiModelProperty(value = "培训地点")
    private String trainPlace;

    /**
     * 讲师
     */
    @ApiModelProperty(value = "讲师")
    private String lecturer;

    /**
     * 课时
     */
    @ApiModelProperty(value = "课时")
    private BigDecimal lessonHour;

    /**
     * 部门ID集合
     */
    @ApiModelProperty(value = "部门ID集合")
    private String deptIds;

    /**
     * 岗位ID集合
     */
    @ApiModelProperty(value = "岗位ID集合")
    private String positionIds;

    /**
     * 参与人员ID集合
     */
    @ApiModelProperty(value = "参与人员ID集合")
    private String userIds;

    /**
     * 状态（0-待培训；1-已培训）
     */
    @ApiModelProperty(value = "状态（0-待培训；1-已培训）")
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
     * saas_train_plan
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
     * 企业（组织机构）ID
     * @return enterprise_id 企业（组织机构）ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业（组织机构）ID
     * @param enterpriseId 企业（组织机构）ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 企业（组织机构编码）
     * @return enterprise_code 企业（组织机构编码）
     */
    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    /**
     * 企业（组织机构编码）
     * @param enterpriseCode 企业（组织机构编码）
     */
    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode == null ? null : enterpriseCode.trim();
    }

    /**
     * 企业（组织机构）名称
     * @return enterprise_name 企业（组织机构）名称
     */
    public String getEnterpriseName() {
        return enterpriseName;
    }

    /**
     * 企业（组织机构）名称
     * @param enterpriseName 企业（组织机构）名称
     */
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
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
     * 计划单据号
     * @return code 计划单据号
     */
    public String getCode() {
        return code;
    }

    /**
     * 计划单据号
     * @param code 计划单据号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 单据类型（3141）
     * @return order_type 单据类型（3141）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（3141）
     * @param orderType 单据类型（3141）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 计划人员ID
     * @return planner_id 计划人员ID
     */
    public Long getPlannerId() {
        return plannerId;
    }

    /**
     * 计划人员ID
     * @param plannerId 计划人员ID
     */
    public void setPlannerId(Long plannerId) {
        this.plannerId = plannerId;
    }

    /**
     * 计划人员编码
     * @return planner_code 计划人员编码
     */
    public String getPlannerCode() {
        return plannerCode;
    }

    /**
     * 计划人员编码
     * @param plannerCode 计划人员编码
     */
    public void setPlannerCode(String plannerCode) {
        this.plannerCode = plannerCode == null ? null : plannerCode.trim();
    }

    /**
     * 计划人员名称
     * @return planner_name 计划人员名称
     */
    public String getPlannerName() {
        return plannerName;
    }

    /**
     * 计划人员名称
     * @param plannerName 计划人员名称
     */
    public void setPlannerName(String plannerName) {
        this.plannerName = plannerName == null ? null : plannerName.trim();
    }

    /**
     * 计划时间
     * @return plan_date 计划时间
     */
    public Date getPlanDate() {
        return planDate;
    }

    /**
     * 计划时间
     * @param planDate 计划时间
     */
    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    /**
     * 计划标题
     * @return plan_title 计划标题
     */
    public String getPlanTitle() {
        return planTitle;
    }

    /**
     * 计划标题
     * @param planTitle 计划标题
     */
    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle == null ? null : planTitle.trim();
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
     * 培训类型（0-岗前培训；1-继续培训）
     * @return train_type 培训类型（0-岗前培训；1-继续培训）
     */
    public Integer getTrainType() {
        return trainType;
    }

    /**
     * 培训类型（0-岗前培训；1-继续培训）
     * @param trainType 培训类型（0-岗前培训；1-继续培训）
     */
    public void setTrainType(Integer trainType) {
        this.trainType = trainType;
    }

    /**
     * 培训内容（0-法律法规；1-药品专业知识及技能；2-质量管理制度；3-职责及岗位操作规程）
     * @return train_content 培训内容（0-法律法规；1-药品专业知识及技能；2-质量管理制度；3-职责及岗位操作规程）
     */
    public Integer getTrainContent() {
        return trainContent;
    }

    /**
     * 培训内容（0-法律法规；1-药品专业知识及技能；2-质量管理制度；3-职责及岗位操作规程）
     * @param trainContent 培训内容（0-法律法规；1-药品专业知识及技能；2-质量管理制度；3-职责及岗位操作规程）
     */
    public void setTrainContent(Integer trainContent) {
        this.trainContent = trainContent;
    }

    /**
     * 培训目的
     * @return train_goal 培训目的
     */
    public String getTrainGoal() {
        return trainGoal;
    }

    /**
     * 培训目的
     * @param trainGoal 培训目的
     */
    public void setTrainGoal(String trainGoal) {
        this.trainGoal = trainGoal == null ? null : trainGoal.trim();
    }

    /**
     * 培训方式（0-远程培训；1-现场培训）
     * @return train_method 培训方式（0-远程培训；1-现场培训）
     */
    public Integer getTrainMethod() {
        return trainMethod;
    }

    /**
     * 培训方式（0-远程培训；1-现场培训）
     * @param trainMethod 培训方式（0-远程培训；1-现场培训）
     */
    public void setTrainMethod(Integer trainMethod) {
        this.trainMethod = trainMethod;
    }

    /**
     * 培训机构
     * @return train_org 培训机构
     */
    public String getTrainOrg() {
        return trainOrg;
    }

    /**
     * 培训机构
     * @param trainOrg 培训机构
     */
    public void setTrainOrg(String trainOrg) {
        this.trainOrg = trainOrg == null ? null : trainOrg.trim();
    }

    /**
     * 培训地点
     * @return train_place 培训地点
     */
    public String getTrainPlace() {
        return trainPlace;
    }

    /**
     * 培训地点
     * @param trainPlace 培训地点
     */
    public void setTrainPlace(String trainPlace) {
        this.trainPlace = trainPlace == null ? null : trainPlace.trim();
    }

    /**
     * 讲师
     * @return lecturer 讲师
     */
    public String getLecturer() {
        return lecturer;
    }

    /**
     * 讲师
     * @param lecturer 讲师
     */
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer == null ? null : lecturer.trim();
    }

    /**
     * 课时
     * @return lesson_hour 课时
     */
    public BigDecimal getLessonHour() {
        return lessonHour;
    }

    /**
     * 课时
     * @param lessonHour 课时
     */
    public void setLessonHour(BigDecimal lessonHour) {
        this.lessonHour = lessonHour;
    }

    /**
     * 部门ID集合
     * @return dept_ids 部门ID集合
     */
    public String getDeptIds() {
        return deptIds;
    }

    /**
     * 部门ID集合
     * @param deptIds 部门ID集合
     */
    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds == null ? null : deptIds.trim();
    }

    /**
     * 岗位ID集合
     * @return position_ids 岗位ID集合
     */
    public String getPositionIds() {
        return positionIds;
    }

    /**
     * 岗位ID集合
     * @param positionIds 岗位ID集合
     */
    public void setPositionIds(String positionIds) {
        this.positionIds = positionIds == null ? null : positionIds.trim();
    }

    /**
     * 参与人员ID集合
     * @return user_ids 参与人员ID集合
     */
    public String getUserIds() {
        return userIds;
    }

    /**
     * 参与人员ID集合
     * @param userIds 参与人员ID集合
     */
    public void setUserIds(String userIds) {
        this.userIds = userIds == null ? null : userIds.trim();
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
        sb.append(", enterpriseCode=").append(enterpriseCode);
        sb.append(", enterpriseName=").append(enterpriseName);
        sb.append(", parentId=").append(parentId);
        sb.append(", code=").append(code);
        sb.append(", orderType=").append(orderType);
        sb.append(", plannerId=").append(plannerId);
        sb.append(", plannerCode=").append(plannerCode);
        sb.append(", plannerName=").append(plannerName);
        sb.append(", planDate=").append(planDate);
        sb.append(", planTitle=").append(planTitle);
        sb.append(", planYear=").append(planYear);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", trainType=").append(trainType);
        sb.append(", trainContent=").append(trainContent);
        sb.append(", trainGoal=").append(trainGoal);
        sb.append(", trainMethod=").append(trainMethod);
        sb.append(", trainOrg=").append(trainOrg);
        sb.append(", trainPlace=").append(trainPlace);
        sb.append(", lecturer=").append(lecturer);
        sb.append(", lessonHour=").append(lessonHour);
        sb.append(", deptIds=").append(deptIds);
        sb.append(", positionIds=").append(positionIds);
        sb.append(", userIds=").append(userIds);
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