package com.rograndec.feijiayun.chain.business.basic.user.vo.param;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Created by zhaiwei on 2017/8/23.
 */
@ApiModel
public class UsersParam implements Serializable {

    private Long enterprise;
    private Long dept;
    private Long position;
    private Long role;
    private Long education;
    private Long major;
    private Long useNature;
    private Long status;
    private String queryStr;
    private Integer approveStatus;

    public Long getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Long enterprise) {
        this.enterprise = enterprise;
    }

    public Long getDept() {
        return dept;
    }

    public void setDept(Long dept) {
        this.dept = dept;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getEducation() {
        return education;
    }

    public void setEducation(Long education) {
        this.education = education;
    }

    public Long getMajor() {
        return major;
    }

    public void setMajor(Long major) {
        this.major = major;
    }

    public Long getUseNature() {
        return useNature;
    }

    public void setUseNature(Long useNature) {
        this.useNature = useNature;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getQueryStr() {
        return queryStr;
    }

    public void setQueryStr(String queryStr) {
        this.queryStr = queryStr;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public UsersParam() {
    }

    public UsersParam(Long enterprise, Long dept, Long position, Long role, Long education, Long major, Long useNature, Long status, String queryStr, Integer approveStatus) {
        this.enterprise = enterprise;
        this.dept = dept;
        this.position = position;
        this.role = role;
        this.education = education;
        this.major = major;
        this.useNature = useNature;
        this.status = status;
        this.queryStr = queryStr;
        this.approveStatus = approveStatus;
    }
}
