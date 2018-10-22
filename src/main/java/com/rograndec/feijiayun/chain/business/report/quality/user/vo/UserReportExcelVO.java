package com.rograndec.feijiayun.chain.business.report.quality.user.vo;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.ShelfMoveVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class UserReportExcelVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

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
     * 角色集合
     */
    @ApiModelProperty(value = "角色集合")
    private String roleIds;

    /**
     * 受限品种集合（可多选：0-药品；1-食品；2-化妆品；3-医疗器械；4-其它）
     */
    @ApiModelProperty(value = "受限品种集合（可多选：0-药品；1-食品；2-化妆品；3-医疗器械；4-其它）")
    private String limitVariety;

    /**
     * 受限品种范围ID集合
     */
    @ApiModelProperty(value = "受限品种范围ID集合")
    private String limitVarietyRange;

    /**
     * 毕业时间
     */
    @ApiModelProperty(value = "毕业时间")
    private String graduationTime;

    /**
     * 毕业院校
     */
    @ApiModelProperty(value = "毕业院校")
    private String graduationUniversity;

    /**
     * 学历ID
     */
    @ApiModelProperty(value = "学历")
    private String educationName;

    /**
     * 专业ID
     */
    @ApiModelProperty(value = "专业")
    private Long majorId;

    /**
     * 专业名称
     */
    @ApiModelProperty(value = "专业名称")
    private String majorName;

    /**
     * 参加工作时间
     */
    @ApiModelProperty(value = "参加工作时间")
    private String workingHours;

    /**
     * 入职时间
     */
    @ApiModelProperty(value = "入职时间")
    private String inductionTime;

    /**
     * 转正时间
     */
    @ApiModelProperty(value = "转正时间")
    private String fullTime;

    /**
     * 上岗证号
     */
    @ApiModelProperty(value = "上岗证号")
    private String certificateNum;

    /**
     * 用工性质（0-兼职；1-全职）
     */
    @ApiModelProperty(value = "用工性质（0-兼职；1-全职）")
    private String useNature;

    /**
     * 状态（0-离职；1-在职）
     */
    @ApiModelProperty(value = "状态（0-离职；1-在职）")
    private String status;

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

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getLimitVariety() {
        return limitVariety;
    }

    public void setLimitVariety(String limitVariety) {
        this.limitVariety = limitVariety;
    }

    public String getLimitVarietyRange() {
        return limitVarietyRange;
    }

    public void setLimitVarietyRange(String limitVarietyRange) {
        this.limitVarietyRange = limitVarietyRange;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getGraduationUniversity() {
        return graduationUniversity;
    }

    public void setGraduationUniversity(String graduationUniversity) {
        this.graduationUniversity = graduationUniversity;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getInductionTime() {
        return inductionTime;
    }

    public void setInductionTime(String inductionTime) {
        this.inductionTime = inductionTime;
    }

    public String getFullTime() {
        return fullTime;
    }

    public void setFullTime(String fullTime) {
        this.fullTime = fullTime;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getUseNature() {
        return useNature;
    }

    public void setUseNature(String useNature) {
        this.useNature = useNature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserReportExcelVO{" +
                "id=" + id +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", loginAccount='" + loginAccount + '\'' +
                ", deptIds='" + deptIds + '\'' +
                ", positionIds='" + positionIds + '\'' +
                ", roleIds='" + roleIds + '\'' +
                ", limitVariety='" + limitVariety + '\'' +
                ", limitVarietyRange='" + limitVarietyRange + '\'' +
                ", graduationTime='" + graduationTime + '\'' +
                ", graduationUniversity='" + graduationUniversity + '\'' +
                ", majorId=" + majorId +
                ", majorName='" + majorName + '\'' +
                ", workingHours='" + workingHours + '\'' +
                ", inductionTime='" + inductionTime + '\'' +
                ", fullTime='" + fullTime + '\'' +
                ", certificateNum='" + certificateNum + '\'' +
                ", useNature='" + useNature + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
