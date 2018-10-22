package com.rograndec.feijiayun.chain.business.basic.user.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_train_plan_detail
 * 
 * 
 * @author Asze
 * 
 * 2017-10-12
 */
public class TrainPlanDetail implements Serializable {
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
     * 计划ID
     */
    @ApiModelProperty(value = "计划ID")
    private Long planId;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    private String deptIds;

    /**
     * 岗位ID
     */
    @ApiModelProperty(value = "岗位ID")
    private String positionIds;

    /**
     * 参与人员ID
     */
    @ApiModelProperty(value = "参与人员ID")
    private Long userId;

    /**
     * 培训课时
     */
    @ApiModelProperty(value = "培训课时")
    private BigDecimal trainLessonHour;

    /**
     * 培训表现
     */
    @ApiModelProperty(value = "培训表现")
    private String trainPerformance;

    /**
     * 考核项目
     */
    @ApiModelProperty(value = "考核项目")
    private String examineItem;

    /**
     * 考核方式（0-开卷；1-闭卷）
     */
    @ApiModelProperty(value = "考核方式（0-开卷；1-闭卷）")
    private Integer examineMethod;

    /**
     * 考核开始时间
     */
    @ApiModelProperty(value = "考核开始时间")
    private Date examineStartTime;

    /**
     * 考核结束时间
     */
    @ApiModelProperty(value = "考核结束时间")
    private Date examineEndTime;

    /**
     * 考核结果（0-不合格；1-合格）
     */
    @ApiModelProperty(value = "考核结果（0-不合格；1-合格）")
    private Integer examineResult;

    /**
     * 采取措施
     */
    @ApiModelProperty(value = "采取措施")
    private String measures;

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
     * saas_train_plan_detail
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
     * 计划ID
     * @return plan_id 计划ID
     */
    public Long getPlanId() {
        return planId;
    }

    /**
     * 计划ID
     * @param planId 计划ID
     */
    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public String getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(String positionIds) {
        this.positionIds = positionIds;
    }

    /**
     * 参与人员ID
     * @return user_id 参与人员ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 参与人员ID
     * @param userId 参与人员ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 培训课时
     * @return train_lesson_hour 培训课时
     */
    public BigDecimal getTrainLessonHour() {
        return trainLessonHour;
    }

    /**
     * 培训课时
     * @param trainLessonHour 培训课时
     */
    public void setTrainLessonHour(BigDecimal trainLessonHour) {
        this.trainLessonHour = trainLessonHour;
    }

    /**
     * 培训表现
     * @return train_performance 培训表现
     */
    public String getTrainPerformance() {
        return trainPerformance;
    }

    /**
     * 培训表现
     * @param trainPerformance 培训表现
     */
    public void setTrainPerformance(String trainPerformance) {
        this.trainPerformance = trainPerformance == null ? null : trainPerformance.trim();
    }

    /**
     * 考核项目
     * @return examine_item 考核项目
     */
    public String getExamineItem() {
        return examineItem;
    }

    /**
     * 考核项目
     * @param examineItem 考核项目
     */
    public void setExamineItem(String examineItem) {
        this.examineItem = examineItem == null ? null : examineItem.trim();
    }

    /**
     * 考核方式（0-开卷；1-闭卷）
     * @return examine_method 考核方式（0-开卷；1-闭卷）
     */
    public Integer getExamineMethod() {
        return examineMethod;
    }

    /**
     * 考核方式（0-开卷；1-闭卷）
     * @param examineMethod 考核方式（0-开卷；1-闭卷）
     */
    public void setExamineMethod(Integer examineMethod) {
        this.examineMethod = examineMethod;
    }

    /**
     * 考核开始时间
     * @return examine_start_time 考核开始时间
     */
    public Date getExamineStartTime() {
        return examineStartTime;
    }

    /**
     * 考核开始时间
     * @param examineStartTime 考核开始时间
     */
    public void setExamineStartTime(Date examineStartTime) {
        this.examineStartTime = examineStartTime;
    }

    /**
     * 考核结束时间
     * @return examine_end_time 考核结束时间
     */
    public Date getExamineEndTime() {
        return examineEndTime;
    }

    /**
     * 考核结束时间
     * @param examineEndTime 考核结束时间
     */
    public void setExamineEndTime(Date examineEndTime) {
        this.examineEndTime = examineEndTime;
    }

    /**
     * 考核结果（0-不合格；1-合格）
     * @return examine_result 考核结果（0-不合格；1-合格）
     */
    public Integer getExamineResult() {
        return examineResult;
    }

    /**
     * 考核结果（0-不合格；1-合格）
     * @param examineResult 考核结果（0-不合格；1-合格）
     */
    public void setExamineResult(Integer examineResult) {
        this.examineResult = examineResult;
    }

    /**
     * 采取措施
     * @return measures 采取措施
     */
    public String getMeasures() {
        return measures;
    }

    /**
     * 采取措施
     * @param measures 采取措施
     */
    public void setMeasures(String measures) {
        this.measures = measures == null ? null : measures.trim();
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
        sb.append(", parentId=").append(parentId);
        sb.append(", planId=").append(planId);
        sb.append(", deptIds=").append(deptIds);
        sb.append(", positionIds=").append(positionIds);
        sb.append(", userId=").append(userId);
        sb.append(", trainLessonHour=").append(trainLessonHour);
        sb.append(", trainPerformance=").append(trainPerformance);
        sb.append(", examineItem=").append(examineItem);
        sb.append(", examineMethod=").append(examineMethod);
        sb.append(", examineStartTime=").append(examineStartTime);
        sb.append(", examineEndTime=").append(examineEndTime);
        sb.append(", examineResult=").append(examineResult);
        sb.append(", measures=").append(measures);
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