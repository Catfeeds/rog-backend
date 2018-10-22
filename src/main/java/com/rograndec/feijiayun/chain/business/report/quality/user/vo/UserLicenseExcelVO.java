package com.rograndec.feijiayun.chain.business.report.quality.user.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class UserLicenseExcelVO implements Serializable{

    /**
     * 组织机构编码
     */
    @ApiModelProperty(value = "组织机构编码")
    private String enterpriseCode;

    /**
     * 组织机构名称
     */
    @ApiModelProperty(value = "组织机构名称")
    private String enterpriseName;

    /**
     * 员工编码
     */
    @ApiModelProperty(value = "员工编码")
    private String code;

    /**
     * 员工名称
     */
    @ApiModelProperty(value = "员工名称")
    private String name;

    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String loginAccount;

    /**
     * 部门集合
     */
    @ApiModelProperty(value = "部门集合")
    private String deptIds;

    /**
     * 岗位集合
     */
    @ApiModelProperty(value = "岗位集合")
    private String positionIds;

    /**
     * 资质描述
     */
    @ApiModelProperty(value = " 资质描述", required = false)
    private String qualificationName;

    /**
     * 等级
     */
    @ApiModelProperty(value = " 等级", required = false)
    private String grade;

    /**
     * 资格证书号
     */
    @ApiModelProperty(value = " 资格证书号", required = false)
    private String qualificationCode;

    /**
     * 注册证书号
     */
    @ApiModelProperty(value = " 注册证书号", required = false)
    private String registerCode;

    /**
     * 适用地区
     */
    @ApiModelProperty(value = " 适用地区", required = false)
    private String region;

    /**
     * 适用类别
     */
    @ApiModelProperty(value = " 适用类别", required = false)
    private String category;

    /**
     * 适用范围
     */
    @ApiModelProperty(value = " 适用范围", required = false)
    private String range;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
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

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getQualificationCode() {
        return qualificationCode;
    }

    public void setQualificationCode(String qualificationCode) {
        this.qualificationCode = qualificationCode;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "UserLicenseExcelVO{" +
                "enterpriseCode='" + enterpriseCode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", loginAccount='" + loginAccount + '\'' +
                ", deptIds='" + deptIds + '\'' +
                ", positionIds='" + positionIds + '\'' +
                ", qualificationName='" + qualificationName + '\'' +
                ", grade='" + grade + '\'' +
                ", qualificationCode='" + qualificationCode + '\'' +
                ", registerCode='" + registerCode + '\'' +
                ", region='" + region + '\'' +
                ", category='" + category + '\'' +
                ", range='" + range + '\'' +
                '}';
    }
}
