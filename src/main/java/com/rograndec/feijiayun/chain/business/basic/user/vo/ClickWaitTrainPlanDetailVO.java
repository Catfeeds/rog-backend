package com.rograndec.feijiayun.chain.business.basic.user.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClickWaitTrainPlanDetailVO implements Serializable {

    /**
     * 部门ID集合
     */
    @ApiModelProperty(value = "部门ID集合", required = false)
    private String deptIds;

    /**
     * 部门集合
     */
    @ApiModelProperty(value = "部门集合", required = false)
    private String deptNames;

    /**
     * 岗位ID集合
     */
    @ApiModelProperty(value = "岗位ID集合", required = false)
    private String positionIds;

    /**
     * 岗位集合
     */
    @ApiModelProperty(value = "岗位集合", required = false)
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

    @Override
    public String toString() {
        return "ClickWaitTrainPlanDetailVO[" +
                "deptIds='" + deptIds + '\'' +
                ", deptNames='" + deptNames + '\'' +
                ", positionIds='" + positionIds + '\'' +
                ", positionNames='" + positionNames + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", trainLessonHour='" + trainLessonHour + '\'' +
                ']';
    }
}
