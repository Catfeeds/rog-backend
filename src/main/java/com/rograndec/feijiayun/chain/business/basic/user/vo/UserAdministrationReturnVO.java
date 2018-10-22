package com.rograndec.feijiayun.chain.business.basic.user.vo;

import com.rograndec.feijiayun.chain.business.basic.user.constant.UseNatureEum;
import com.rograndec.feijiayun.chain.business.basic.user.constant.UserStatusEum;
import com.rograndec.feijiayun.chain.business.basic.user.constant.UserTypeEum;
import com.rograndec.feijiayun.chain.business.basic.user.entity.EducationMajor;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import com.rograndec.feijiayun.chain.common.constant.LimitVarietyType;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel
public class UserAdministrationReturnVO implements Serializable {

    /**
     * user 行政信息id
     */
    @ApiModelProperty(value = "user 行政信息id ,修改时需要传入,新增时不需要", required = false)
    private Long id;

    @ApiModelProperty(value = "登录账号是否可以修改 0 : 不可以修改 ; 1 : 可以修改")
    private Integer checkUpdateLoginAcount = 0;
    /**
     * 用户类型集合（0-系统管理员；1-云系统用户；2-POS用户；3-云系统用户和POS用户）
     */
    @ApiModelProperty(value = "用户类型集合描述")
    private String userTypeDesc;

    @ApiModelProperty(value = "用户类型集合")
    private List<Long> userType;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String loginAacount;


    /**
     * 部门ID集合
     */

    @ApiModelProperty(value = "部门ID集合描述")
    private List<String> deptsDesc;

    @ApiModelProperty(value = "部门ID集合")
    private List<Long> deptIds;
    /**
     * 岗位ID集合
     */

    @ApiModelProperty(value = "岗位ID集合描述")
    private List<String> positionsDesc;

    @ApiModelProperty(value = "岗位ID集合")
    private List<Long> positionIds;
    /**
     * 角色ID集合
     */

    @ApiModelProperty(value = "角色ID集合描述")
    private List<String> roleDesc;

    @ApiModelProperty(value = "角色ID集合描述")
    private List<Long>  roleIds;
    
    /**
     * 柜组（货区）ID集合
     */
    @ApiModelProperty(value = "柜组（货区）ID集合")
    private List<Long> cargoAreaIds;
    
    /**
     * 柜组（货区）ID集合描述
     */

    @ApiModelProperty(value = "柜组（货区）集合描述")
    private String cargoAreaDesc;
    
    /**
     * 班组ID
     */
    @ApiModelProperty(value = "班组ID")
    private Long teamId;
    
    /**
     *班组ID描述
     */

    @ApiModelProperty(value = "班组ID描述")
    private String teamDesc;

    /**
     * 受限品种集合（可多选：0-药品；1-食品；2-化妆品；3-医疗器械；4-其它）
     */

    @ApiModelProperty(value = "受限品种集合描述")
    private List<String> limitVarietysDesc;

    @ApiModelProperty(value = "受限品种集合")
    private List<Long> limitVarietyIds;

    /**
     * 受限品种范围ID集合
     */

    @ApiModelProperty(value = "受限品种范围ID集合描述")
    private List<String> limitVarietyRangeDesc;

    @ApiModelProperty(value = "受限品种范围ID集合描述")
    private List<Long> limitVarietyRange;
    /**
     * 毕业时间
     */
    @ApiModelProperty(value = "毕业时间", required = false)
    private Date graduationTime;

    @ApiModelProperty(value = "毕业时间,描述", required = false)
    private String graduationTimeDesc;
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

    @ApiModelProperty(value = "学历name", required = false)
    private String educationName;

    /**
     * 专业
     */
    @ApiModelProperty(value = "专业", required = false)
    private Long majorId;

    @ApiModelProperty(value = "专业name", required = false)
    private String majorName;

    /**
     * 参加工作时间
     */
    @ApiModelProperty(value = "参加工作时间", required = false)
    private Date workingHours;

    /**
     * 参加工作时间
     */
    @ApiModelProperty(value = "参加工作时间描述", required = false)
    private String workingHoursDesc;
    /**
     * 入职时间
     */
    @ApiModelProperty(value = "入职时间", required = false)
    private Date inductionTime;

    /**
     * 入职时间
     */
    @ApiModelProperty(value = "入职时间描述", required = false)
    private String inductionTimeDesc;

    /**
     * 转正时间
     */
    @ApiModelProperty(value = "转正时间", required = false)
    private Date fullTime;

    /**
     * 转正时间
     */
    @ApiModelProperty(value = "转正时间描述", required = false)
    private String fullTimeDesc;

    /**
     * 上岗证号
     */
    @ApiModelProperty(value = "上岗证号", required = false)
    private String certificateNum;

    /**
     * 用工性质（0-兼职；1-全职）
     */
    @ApiModelProperty(value = " 用工性质（0-兼职；1-全职）", required = true)
    private Integer useNature;

    @ApiModelProperty(value = " 用工性质描述", required = true)
    private String useNatureDesc;

    /**
     * 员工简介
     */
    private String userAbout;

    /**
     * 状态（0-离职；1-在职）
     */
    @ApiModelProperty(value = " 状态（0-离职；1-在职", required = true)
    private Integer status;

    @ApiModelProperty(value = " 状态描述", required = true)
    private String statusDesc;

    /**
     * 备注
     */
    @ApiModelProperty(value = " 简介", required = false)
    private String remark;


        public static UserAdministrationReturnVO getUserAdministrationReturnVO4UserAdmin(UserAdministration userAdministration
                , List<Department> departments, List<Position> positions, List<SysRole> roles
                , EducationMajor education, EducationMajor major, List<BusinessScope> businessScopes){

            UserAdministrationReturnVO userAdministrationReturnVO = new UserAdministrationReturnVO();


            //设置柜组和班组
            userAdministrationReturnVO.setCargoAreaIds(StringSplit.strSplit(userAdministration.getCargoAreaIds()));
            userAdministrationReturnVO.setTeamId(userAdministration.getTeamId());

            userAdministrationReturnVO.setId(userAdministration.getId());
            userAdministrationReturnVO.setUserId(userAdministration.getUserId());
            String userTypeStrs = userAdministration.getUserType();
            List<Long> ust = StringSplit.strSplit(userTypeStrs);
            userAdministrationReturnVO.setUserType(ust);
            for(Long ut : ust){
                UserTypeEum userTypeEum = UserTypeEum.getUserTypeEum4Code(ut.intValue());
                if(StringUtils.isEmpty(userAdministrationReturnVO.getUserTypeDesc())){
                    userAdministrationReturnVO.setUserTypeDesc(userTypeEum.getValue());
                }else {
                    userAdministrationReturnVO.setUserTypeDesc(userAdministrationReturnVO.getUserTypeDesc() + "," + userTypeEum.getValue());
                }
            }
            userAdministrationReturnVO.setLoginAacount(userAdministration.getLoginAccount());

            String deptStrs = userAdministration.getDeptIds();
            List<Long> depts = StringSplit.strSplit(deptStrs);
            userAdministrationReturnVO.setDeptIds(depts);
            List<String> deptNames = new ArrayList<>();
            userAdministrationReturnVO.setDeptsDesc(deptNames);


            for(Department dept : departments){
                if(depts.contains(dept.getId())){
                    deptNames.add(dept.getName());
                }
            }




            String positionStrs = userAdministration.getPositionIds();
            List<Long> pons = StringSplit.strSplit(positionStrs);
            userAdministrationReturnVO.setPositionIds(pons);
            List<String> ponsNames = new ArrayList<>();
            userAdministrationReturnVO.setPositionsDesc(ponsNames);
            for(Position position : positions){
                if(pons.contains(position.getId())){
                    ponsNames.add(position.getName());
                }
            }

            String roleIds = userAdministration.getRoleIds();
            List<Long> rs = StringSplit.strSplit(roleIds);
            userAdministrationReturnVO.setRoleIds(rs);
            List<String> roleNames = new ArrayList<>();
            userAdministrationReturnVO.setRoleDesc(roleNames);
            for(SysRole sysRole : roles){
                if(rs.contains(sysRole.getId())){
                    roleNames.add(sysRole.getName());
                }
            }


            String limitVarietyStrs = userAdministration.getLimitVariety();
            List<Long> llrs = StringSplit.strSplit(limitVarietyStrs);
            userAdministrationReturnVO.setLimitVarietyIds(llrs);
            List<String> llrsNames = new ArrayList<>();
            userAdministrationReturnVO.setLimitVarietysDesc(llrsNames);
            for(Long ls : llrs){
                LimitVarietyType type = LimitVarietyType.getLimitVarietyType4Code(ls.intValue());
                llrsNames.add(type.getName());
//                if(StringUtils.isEmpty(userAdministrationReturnVO.getLimitVarietysDesc())){
//                    userAdministrationReturnVO.setLimitVarietysDesc(type.getName());
//                }else {
//                    userAdministrationReturnVO.setLimitVarietysDesc(userAdministrationReturnVO.getLimitVarietysDesc() + "," + type.getName());
//                }
            }



            userAdministrationReturnVO.setGraduationTime(userAdministration.getGraduationTime());

            userAdministrationReturnVO.setGraduationTimeDesc(
                    DateUtils.DateToString(userAdministration.getGraduationTime(),DateUtils.FMT_DATE_TIME)
            );
            userAdministrationReturnVO.setGraduationUniversity(userAdministration.getGraduationUniversity());
            userAdministrationReturnVO.setEducationId(userAdministration.getEducationId());
            if(education != null){
                userAdministrationReturnVO.setEducationName(education.getName());
            }
            userAdministrationReturnVO.setMajorId(userAdministration.getMajorId());
            if(major != null){
                userAdministrationReturnVO.setMajorName(major.getName());
            }



            userAdministrationReturnVO.setWorkingHours(userAdministration.getWorkingHours());
            userAdministrationReturnVO.setWorkingHoursDesc(
                    DateUtils.DateToString(userAdministration.getWorkingHours(),DateUtils.FMT_DATE_TIME)
            );

            userAdministrationReturnVO.setInductionTime(userAdministration.getInductionTime());
            if(null != userAdministration.getInductionTime()){
                userAdministrationReturnVO.setInductionTimeDesc(
                        DateUtils.DateToString(userAdministration.getInductionTime(),DateUtils.FMT_DATE_TIME)
                );
            }

            userAdministrationReturnVO.setFullTime(userAdministration.getFullTime());
            if(null != userAdministration.getFullTime()) {
                userAdministrationReturnVO.setFullTimeDesc(
                        DateUtils.DateToString(userAdministration.getFullTime(), DateUtils.FMT_DATE_TIME)
                );
            }




            userAdministrationReturnVO.setCertificateNum(userAdministration.getCertificateNum());
            userAdministrationReturnVO.setUseNature(userAdministration.getUseNature());


            userAdministrationReturnVO.setUseNatureDesc(
                    StringUtils.isEmpty(userAdministration.getUseNature()) ? null :
                            UseNatureEum.getValue(userAdministration.getUseNature())
            );

            userAdministrationReturnVO.setUserAbout(userAdministration.getUserAbout());
            userAdministrationReturnVO.setStatus(userAdministration.getStatus());
            userAdministrationReturnVO.setStatusDesc(
                    UserStatusEum.getUserStatusEum4Code(userAdministration.getStatus()).getValue()
            );

            userAdministrationReturnVO.setRemark(userAdministration.getRemark());

            String limitVarietyRangeStrs = userAdministration.getLimitVarietyRange();
            List<Long> llrss = StringSplit.strSplit(limitVarietyRangeStrs);
            userAdministrationReturnVO.setLimitVarietyRange(llrss);
            List<String> llrssNames = new ArrayList<>();
            //userAdministrationReturnVO.setLimitVarietysDesc(llrsNames);
            userAdministrationReturnVO.setLimitVarietyRangeDesc(llrssNames);


            for(Long ls : llrss){
                for(BusinessScope businessScope : businessScopes){
                    if(ls.equals(businessScope.getId())){
                        llrssNames.add(businessScope.getName());
//                        if(StringUtils.isEmpty(userAdministrationReturnVO.getLimitVarietyRangeDesc())){
//                            userAdministrationReturnVO.setLimitVarietyRangeDesc(businessScope.getName());
//                        }else {
//                            userAdministrationReturnVO.setLimitVarietyRangeDesc(userAdministrationReturnVO.getLimitVarietyRangeDesc()
//                                    + "," + businessScope.getName());
//                        }
                    }
                }
            }


            return userAdministrationReturnVO;
        }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserTypeDesc() {
        return userTypeDesc;
    }

    public void setUserTypeDesc(String userTypeDesc) {
        this.userTypeDesc = userTypeDesc;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginAacount() {
        return loginAacount;
    }

    public void setLoginAacount(String loginAacount) {
        this.loginAacount = loginAacount;
    }

    public List<String> getDeptsDesc() {
        return deptsDesc;
    }

    public void setDeptsDesc(List<String> deptsDesc) {
        this.deptsDesc = deptsDesc;
    }

    public List<Long> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Long> deptIds) {
        this.deptIds = deptIds;
    }

    public List<String> getPositionsDesc() {
        return positionsDesc;
    }

    public void setPositionsDesc(List<String> positionsDesc) {
        this.positionsDesc = positionsDesc;
    }

    public List<Long> getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(List<Long> positionIds) {
        this.positionIds = positionIds;
    }

    public List<String> getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(List<String> roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public List<String> getLimitVarietysDesc() {
        return limitVarietysDesc;
    }

    public void setLimitVarietysDesc(List<String> limitVarietysDesc) {
        this.limitVarietysDesc = limitVarietysDesc;
    }

    public List<Long> getLimitVarietyIds() {
        return limitVarietyIds;
    }

    public void setLimitVarietyIds(List<Long> limitVarietyIds) {
        this.limitVarietyIds = limitVarietyIds;
    }

    public List<String> getLimitVarietyRangeDesc() {
        return limitVarietyRangeDesc;
    }

    public void setLimitVarietyRangeDesc(List<String> limitVarietyRangeDesc) {
        this.limitVarietyRangeDesc = limitVarietyRangeDesc;
    }

    public List<Long> getLimitVarietyRange() {
        return limitVarietyRange;
    }

    public void setLimitVarietyRange(List<Long> limitVarietyRange) {
        this.limitVarietyRange = limitVarietyRange;
    }

    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getGraduationTimeDesc() {
        return graduationTimeDesc;
    }

    public void setGraduationTimeDesc(String graduationTimeDesc) {
        this.graduationTimeDesc = graduationTimeDesc;
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

    public Date getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Date workingHours) {
        this.workingHours = workingHours;
    }

    public String getWorkingHoursDesc() {
        return workingHoursDesc;
    }

    public void setWorkingHoursDesc(String workingHoursDesc) {
        this.workingHoursDesc = workingHoursDesc;
    }

    public Date getInductionTime() {
        return inductionTime;
    }

    public void setInductionTime(Date inductionTime) {
        this.inductionTime = inductionTime;
    }

    public String getInductionTimeDesc() {
        return inductionTimeDesc;
    }

    public void setInductionTimeDesc(String inductionTimeDesc) {
        this.inductionTimeDesc = inductionTimeDesc;
    }

    public Date getFullTime() {
        return fullTime;
    }

    public void setFullTime(Date fullTime) {
        this.fullTime = fullTime;
    }

    public String getFullTimeDesc() {
        return fullTimeDesc;
    }

    public void setFullTimeDesc(String fullTimeDesc) {
        this.fullTimeDesc = fullTimeDesc;
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

    public String getUseNatureDesc() {
        return useNatureDesc;
    }

    public void setUseNatureDesc(String useNatureDesc) {
        this.useNatureDesc = useNatureDesc;
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

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Long> getUserType() {
        return userType;
    }

    public void setUserType(List<Long> userType) {
        this.userType = userType;
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

    public Integer getCheckUpdateLoginAcount() {
        return checkUpdateLoginAcount;
    }

    public void setCheckUpdateLoginAcount(Integer checkUpdateLoginAcount) {
        this.checkUpdateLoginAcount = checkUpdateLoginAcount;
    }

	public String getCargoAreaDesc() {
		return cargoAreaDesc;
	}

	public void setCargoAreaDesc(String cargoAreaDesc) {
		this.cargoAreaDesc = cargoAreaDesc;
	}

	public String getTeamDesc() {
		return teamDesc;
	}

	public void setTeamDesc(String teamDesc) {
		this.teamDesc = teamDesc;
	}
    
}