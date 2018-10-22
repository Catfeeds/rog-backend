package com.rograndec.feijiayun.chain.business.basic.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel
public class UserAdministrationVO implements Serializable {

    /**
     * user 行政信息id
     */
    @ApiModelProperty(value = "user 行政信息id ,修改时需要传入,新增时不需要", required = false)
    private Long id;
    /**
     * 用户类型集合（0-系统管理员；1-云系统用户；2-POS用户；3-云系统用户和POS用户）
     */
    @ApiModelProperty(value = "用户类型集合", required = true)
    @NotNull(message = "用户类型集合不能为空")
    @Size(min = 1,message = "用户类型集合不能为空")
    private List<Long> userType;

    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号", required = true)
    @NotNull(message = "登录账号不能为空")
    @Size(min = 1,message = "登录账号不能为空")
    private String loginAacount;

//    /**
//     * 登录密码
//     */
//    @ApiModelProperty(value = "登录密码", required = true)
//    @NotNull(message = "登录密码不能为空")
//    @Size(min = 1,message = "登录密码不能为空")
//    private String password;
//
//    /**
//     * 密码确认
//     */
//    @ApiModelProperty(value = "密码确认", required = true)
//    @NotNull(message = "密码确认不能为空")
//    @Size(min = 1,message = "密码确认不能为空")
//    private String passwordConfirm;

    /**
     * 部门ID集合
     */
    @ApiModelProperty(value = "部门ID集合", required = true)
    @NotNull(message = "部门ID集合不能为空")
    @Size(min = 1,message = "部门ID集合不能为空")
    private List<Long> deptIds;

    /**
     * 岗位ID集合
     */
    @ApiModelProperty(value = "岗位ID集合", required = true)
    @NotNull(message = "岗位ID集合不能为空")
    @Size(min = 1,message = "岗位ID集合不能为空")
    private List<Long> positionIds;

    /**
     * 角色ID集合
     */
    @ApiModelProperty(value = "角色ID集合", required = true)
    @NotNull(message = "角色ID集合不能为空")
    @Size(min = 1,message = "角色ID集合不能为空")
    private List<Long> roleIds;
    
    /**
     * 当用户类型为POS用户、部门为门店、岗位为店员、角色为营业人员和收款人员时，在角色行下方插入柜组、班组选项（柜组为多选，班组为单选）
     * 柜组（货区）ID集合
     */
    @ApiModelProperty(value = "柜组（货区）ID集合，注：添加或修改时当符合pos条件的时候必填", required = false)
    private List<Long> cargoAreaIds;
    
    /**
     * 当用户类型为POS用户、部门为门店、岗位为店员、角色为营业人员和收款人员时，在角色行下方插入柜组、班组选项（柜组为多选，班组为单选）
     * 班组ID
     */
    @ApiModelProperty(value = "班组ID，注：添加或修改时当符合pos条件的时候必填", required = false)
    private Long teamId;

    /**
     * 受限品种集合（可多选：0-药品；1-食品；2-化妆品；3-医疗器械；4-其它）
     */
    @ApiModelProperty(value = "受限品种集合", required = false)
    private List<Long> limitVariety;

    /**
     * 受限品种范围ID集合
     */
    @ApiModelProperty(value = "受限品种范围ID集合", required = false)
    private List<Long> limitVarietyRange;

    /**
     * 毕业时间
     */
    @ApiModelProperty(value = "毕业时间", required = false)
    private Date graduationTime;

    /**
     * 毕业院校
     */
    @ApiModelProperty(value = "毕业院校", required = false)
    private String graduationUniversity;

    /**
     * 学历ID
     */
    @ApiModelProperty(value = "学历", required = false)
    private Long educationId;

    /**
     * 专业
     */
    @ApiModelProperty(value = "专业", required = false)
    private Long majorId;

    /**
     * 参加工作时间
     */
    @ApiModelProperty(value = "参加工作时间", required = false)
    private Date workingHours;

    /**
     * 入职时间
     */
    @ApiModelProperty(value = "入职时间", required = false)
    private Date inductionTime;

    /**
     * 转正时间
     */
    @ApiModelProperty(value = "转正时间", required = false)
    private Date fullTime;

    /**
     * 上岗证号
     */
    @ApiModelProperty(value = "上岗证号", required = false)
    private String certificateNum = "";

    /**
     * 用工性质（0-兼职；1-全职）
     */
    @ApiModelProperty(value = " 用工性质（0-兼职；1-全职）", required = true)
    @NotNull(message = "用工性质不能为空")
    @Min(value = 0,message = "用工性质值不正确")
    @Max(value = 1,message = "用工性质值不正确")
    private Integer useNature;

    /**
     * 员工简介
     */
    @ApiModelProperty(value = " 员工简介", required = false)
    private String userAbout;

    /**
     * 状态（0-离职；1-在职）
     */
    @ApiModelProperty(value = " 状态（0-离职；1-在职", required = true)
    @NotNull(message = "状态不能为空")
    @Min(value = 0,message = "状态值不正确")
    @Max(value = 1,message = "状态值不正确")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = " 备注", required = false)
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getUserType() {
        return userType;
    }

    public void setUserType(List<Long> userType) {
        this.userType = userType;
    }

    public String getLoginAacount() {
        return loginAacount;
    }

    public void setLoginAacount(String loginAacount) {
        this.loginAacount = loginAacount;
    }

    public List<Long> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Long> deptIds) {
        this.deptIds = deptIds;
    }

    public List<Long> getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(List<Long> positionIds) {
        this.positionIds = positionIds;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public List<Long> getLimitVariety() {
        return limitVariety;
    }

    public void setLimitVariety(List<Long> limitVariety) {
        this.limitVariety = limitVariety;
    }

    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getGraduationUniversity() {
        return graduationUniversity;
    }

    public void setGraduationUniversity(String graduationUniversity) {
        this.graduationUniversity = graduationUniversity;
    }

    public Long getEducationId() {
        return educationId;
    }

    public void setEducationId(Long educationId) {
        this.educationId = educationId;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public Date getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Date workingHours) {
        this.workingHours = workingHours;
    }

    public Date getInductionTime() {
        return inductionTime;
    }

    public void setInductionTime(Date inductionTime) {
        this.inductionTime = inductionTime;
    }

    public Date getFullTime() {
        return fullTime;
    }

    public void setFullTime(Date fullTime) {
        this.fullTime = fullTime;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public Integer getUseNature() {
        return useNature;
    }

    public void setUseNature(Integer useNature) {
        this.useNature = useNature;
    }

    public String getUserAbout() {
        return userAbout;
    }

    public void setUserAbout(String userAbout) {
        this.userAbout = userAbout;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Long> getLimitVarietyRange() {
        return limitVarietyRange;
    }

    public void setLimitVarietyRange(List<Long> limitVarietyRange) {
        this.limitVarietyRange = limitVarietyRange;
    }

	public List<Long> getCargoAreaIds() {
		return cargoAreaIds;
	}

	public void setCargoAreaIds(List<Long> cargoAreaIds) {
		this.cargoAreaIds = cargoAreaIds;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
    
}