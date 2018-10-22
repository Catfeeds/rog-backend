package com.rograndec.feijiayun.chain.business.basic.user.entity;

import com.rograndec.feijiayun.chain.business.auth.register.entity.Register;
import com.rograndec.feijiayun.chain.business.basic.user.constant.UserTypeEum;
import com.rograndec.feijiayun.chain.business.basic.user.vo.AddUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.UserAdministrationVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import com.rograndec.feijiayun.chain.utils.user.PasswordUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class UserAdministration implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 上级企业ID
     */
    private Long parentId;


    /**
     * 企业ID
     */
    private Long enterpriseId;


    /**
     * 用户类型集合（0-系统管理员；1-云系统用户；2-POS用户；3-云系统用户和POS用户）
     */
    @ApiModelProperty(value = "用户类型集合（0-系统管理员；1-云系统用户；2-POS用户；3-云系统用户和POS用户）")
    private String userType;


    /**
     * 登录账号
     */
    private String loginAccount;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 密码确认
     */
    private String passwordConfirm;

    /**
     * 部门ID集合
     */
    private String deptIds;

    /**
     * 岗位ID集合
     */
    private String positionIds;

    /**
     * 角色ID集合
     */
    private String roleIds;

    /**
     * 受限品种集合（可多选：0-药品；1-食品；2-化妆品；3-医疗器械；4-其它）
     */
    private String limitVariety;

    /**
     * 毕业时间
     */
    private Date graduationTime;

    /**
     * 毕业院校
     */
    private String graduationUniversity;

    /**
     * 学历ID
     */
    private Long educationId;

    /**
     * 专业
     */
    private Long majorId;

    /**
     * 参加工作时间
     */
    private Date workingHours;

    /**
     * 入职时间
     */
    private Date inductionTime;

    /**
     * 转正时间
     */
    private Date fullTime;

    /**
     * 上岗证号
     */
    private String certificateNum;

    /**
     * 用工性质（0-兼职；1-全职）
     */
    private Integer useNature;

    /**
     * 员工简介
     */
    private String userAbout;

    /**
     * 状态（0-离职；1-在职）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码
     */
    private String createrCode;

    /**
     * 创建人名称
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人ID
     */
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    private String modifierName;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 受限品种范围ID集合
     */
    private String limitVarietyRange;
    /**
     * 柜组（货区）ID集合
     */
    private String cargoAreaIds;
    
    /**
     * 当用户类型为POS用户、部门为门店、岗位为店员、角色为营业人员和收款人员时，在角色行下方插入柜组、班组选项（柜组为多选，班组为单选）
     * 班组ID
     */
    private Long teamId;


    public static UserAdministration initUserAdministration(Register register
            ,User registerUser
    ){

        UserAdministration userAdministration = new UserAdministration();

        /**
         * 用户ID
         */
        userAdministration.setUserId(registerUser.getId());
        /**
         * 上级企业ID
         */
        userAdministration.setParentId(registerUser.getParentId());


        /**
         * 企业ID
         */
        userAdministration.setEnterpriseId(registerUser.getEnterpriseId());


        /**
         * 用户类型集合（0-系统管理员；1-云系统用户；2-POS用户；3-云系统用户和POS用户）
         */
        userAdministration.setUserType(UserTypeEum.SYSTEM_USER.getCode()+"");

        /**
         * 登录账号
         */
        userAdministration.setLoginAccount(register.getLoginAccount());

        /**
         * 登录密码
         */
        userAdministration.setPassword(register.getPassword());

        /**
         * 密码确认
         */
        userAdministration.setPasswordConfirm(register.getPasswordConfirm());

        /**
         * 用工性质（0-兼职；1-全职）
         */
        userAdministration.setUseNature(1);

        /**
         * 状态（0-离职；1-在职）
         */
        userAdministration.setStatus(EnableStatus.ENABLE.getStatus());
        userAdministration.setDeptIds("0");
        userAdministration.setPositionIds("0");
        userAdministration.setRoleIds("0");

        userAdministration.setCreaterId(0L);
        userAdministration.setCreaterCode("0000");
        userAdministration.setCreaterName("0000");
        userAdministration.setCreateTime(new Date());
        userAdministration.setModifierId(0L);
        userAdministration.setModifierCode("0000");
        userAdministration.setModifierName("0000");
        userAdministration.setUpdateTime(new Date());

        return userAdministration;
    }

    public static UserAdministration initOtherUserAdministration(User registerUser){

        UserAdministration userAdministration = new UserAdministration();

        /**
         * 用户ID
         */
        userAdministration.setUserId(registerUser.getId());

        /**
         * 上级企业ID
         */
        userAdministration.setParentId(registerUser.getParentId());


        /**
         * 企业ID
         */
        userAdministration.setEnterpriseId(registerUser.getEnterpriseId());


        /**
         * 用户类型集合（0-系统管理员；1-云系统用户；2-POS用户；3-云系统用户和POS用户）
         */
        userAdministration.setUserType(UserTypeEum.CLOUD_USER.getCode()+"");

        /**
         * 登录账号
         */
       /* String loginAccount = PinYinUtils.getFirstSpell(registerUser.getName());*/
        userAdministration.setLoginAccount(String.valueOf(Instant.now().toEpochMilli()));

        /**
         * 密码确认
         */
        userAdministration.setPassword("00000000");

        /**
         * 密码确认
         */
        userAdministration.setPasswordConfirm("00000000");

        /**
         *  用工性质（0-兼职；1-全职）
         */
        userAdministration.setUseNature(1);

        /**
         * 状态（0-离职；1-在职）
         */
        userAdministration.setStatus(EnableStatus.ENABLE.getStatus());
        userAdministration.setDeptIds("0");
        userAdministration.setPositionIds("0");
        userAdministration.setRoleIds("0");

        userAdministration.setCreaterId(0L);
        userAdministration.setCreaterCode("0000");
        userAdministration.setCreaterName("0000");
        userAdministration.setCreateTime(new Date());
        userAdministration.setModifierId(0L);
        userAdministration.setModifierCode("0000");
        userAdministration.setModifierName("0000");
        userAdministration.setUpdateTime(new Date());

        return userAdministration;
    }

    public static UserAdministration getUserAdministration4AddUserVO(AddUserVO addUserVO, UserVO userVO, User user, Enterprise enterprise, boolean isAdd){

        UserAdministrationVO userAdministrationVO = addUserVO.getUserAdministrationDTO();
        UserAdministration userAdministration = new UserAdministration();
        userAdministration.setId(userAdministrationVO.getId());
        userAdministration.setUserId(user.getId());
        userAdministration.setEnterpriseId(enterprise.getId());
        userAdministration.setParentId(enterprise.getParentId());

        List<Long> userTypes = userAdministrationVO.getUserType();

//        JSONArray userJson = (JSONArray) JSONArray.toJSON(userTypes);
        String userJson = StringSplit.StringAppendSymbol(userTypes);
        userAdministration.setUserType(userJson);

        userAdministration.setLoginAccount(userAdministrationVO.getLoginAacount());
        userAdministration.setPassword(PasswordUtils.getDefultPassword());
        userAdministration.setPasswordConfirm(PasswordUtils.getDefultPassword());

       /* userAdministration.setPassword(PasswordUtils.getDefultPassword());
        userAdministration.setPasswordConfirm(PasswordUtils.getDefultPassword());*/

        String deptIds = StringSplit.StringAppendSymbol(userAdministrationVO.getDeptIds());
        userAdministration.setDeptIds(deptIds);

        String positionIds = StringSplit.StringAppendSymbol(userAdministrationVO.getPositionIds());
        userAdministration.setPositionIds(positionIds);

        String roleIds = StringSplit.StringAppendSymbol(userAdministrationVO.getRoleIds());
        userAdministration.setRoleIds(roleIds);
        //设置柜组id集合
        String cargoAreaIds = StringSplit.StringAppendSymbol(userAdministrationVO.getCargoAreaIds());
        userAdministration.setCargoAreaIds(cargoAreaIds);
        //设置班组id
        userAdministration.setTeamId(userAdministrationVO.getTeamId());

        List<Long> limitVariety = userAdministrationVO.getLimitVariety();
        userAdministration.setLimitVariety(  StringSplit.StringAppendSymbol(limitVariety));
        List<Long> limitVarietyRange = userAdministrationVO.getLimitVarietyRange();
        userAdministration.setLimitVarietyRange(StringSplit.StringAppendSymbol(limitVarietyRange));
        userAdministration.setGraduationUniversity(userAdministrationVO.getGraduationUniversity());
        userAdministration.setEducationId(userAdministrationVO.getEducationId());
        userAdministration.setMajorId(userAdministrationVO.getMajorId());

        // 毕业时间，参加工作时间，入职时间，转正时间 统一转换为yyyy-MM-dd 00:00:00便于修改记录判断 zhangyu 2018-2-1 10:26:59
        userAdministration.setGraduationTime(DateUtils.timeToDate(userAdministrationVO.getGraduationTime()));
        userAdministration.setWorkingHours(DateUtils.timeToDate(userAdministrationVO.getWorkingHours()));
        userAdministration.setInductionTime(DateUtils.timeToDate(userAdministrationVO.getInductionTime()));
        userAdministration.setFullTime(DateUtils.timeToDate(userAdministrationVO.getFullTime()));

        userAdministration.setCertificateNum(userAdministrationVO.getCertificateNum());
        userAdministration.setUseNature(userAdministrationVO.getUseNature());
        userAdministration.setUserAbout(userAdministrationVO.getUserAbout());
        userAdministration.setStatus(userAdministrationVO.getStatus());
        userAdministration.setRemark(userAdministrationVO.getRemark());

        if(isAdd){
            userAdministration.setCreaterId(userVO.getUserId());
            userAdministration.setCreaterCode(userVO.getUserCode());
            userAdministration.setCreaterName(userVO.getUserName());
            userAdministration.setCreateTime(new Date());
            userAdministration.setModifierId(userVO.getUserId());
            userAdministration.setModifierCode(userVO.getUserCode());
            userAdministration.setModifierName(userVO.getUserName());
            userAdministration.setUpdateTime(new Date());
        }else {
            userAdministration.setModifierId(userVO.getUserId());
            userAdministration.setModifierCode(userVO.getUserCode());
            userAdministration.setModifierName(userVO.getUserName());
            userAdministration.setUpdateTime(new Date());
        }

        return userAdministration;

    }

    public static List<Long> getDeptIds2List(UserAdministration userAdministration){

        String deptStrs = userAdministration.getDeptIds();
        List<Long> ids = StringSplit.strSplit(deptStrs);

        return ids;
    }

    public static List<Long> getPositionIds2List(UserAdministration userAdministration){

        String positionIds = userAdministration.getPositionIds();
        List<Long> ids = StringSplit.strSplit(positionIds);

        return ids;
    }

    public static List<Long> getRoleIds2List(UserAdministration userAdministration){

        String roleIds = userAdministration.getRoleIds();
        List<Long> ids = StringSplit.strSplit(roleIds);

        return ids;
    }
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
     * 用户ID
     * @return user_id 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户ID
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
     * 用户类型集合（0-系统管理员；1-云系统用户；2-POS用户；3-云系统用户和POS用户）
     * @return user_type 用户类型集合（0-系统管理员；1-云系统用户；2-POS用户；3-云系统用户和POS用户）
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 用户类型集合（0-系统管理员；1-云系统用户；2-POS用户；3-云系统用户和POS用户）
     * @param userType 用户类型集合（0-系统管理员；1-云系统用户；2-POS用户；3-云系统用户和POS用户）
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * 登录账号
     * @return login_account 登录账号
     */
    public String getLoginAccount() {
        return loginAccount;
    }

    /**
     * 登录账号
     */
    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount == null ? null : loginAccount.trim();
    }

    /**
     * 登录密码
     * @return password 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 登录密码
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 密码确认
     * @return password_confirm 密码确认
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * 密码确认
     * @param passwordConfirm 密码确认
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm == null ? null : passwordConfirm.trim();
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
     * 角色ID集合
     * @return role_ids 角色ID集合
     */
    public String getRoleIds() {
        return roleIds;
    }

    /**
     * 角色ID集合
     * @param roleIds 角色ID集合
     */
    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds == null ? null : roleIds.trim();
    }

    /**
     * 受限品种集合（可多选：0-药品；1-食品；2-化妆品；3-医疗器械；4-其它）
     * @return limit_variety 受限品种集合（可多选：0-药品；1-食品；2-化妆品；3-医疗器械；4-其它）
     */
    public String getLimitVariety() {
        return limitVariety;
    }

    /**
     * 受限品种集合（可多选：0-药品；1-食品；2-化妆品；3-医疗器械；4-其它）
     * @param limitVariety 受限品种集合（可多选：0-药品；1-食品；2-化妆品；3-医疗器械；4-其它）
     */
    public void setLimitVariety(String limitVariety) {
        this.limitVariety = limitVariety == null ? null : limitVariety.trim();
    }

    /**
     * 毕业时间
     * @return graduation_time 毕业时间
     */
    public Date getGraduationTime() {
        return graduationTime;
    }

    /**
     * 毕业时间
     * @param graduationTime 毕业时间
     */
    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }

    /**
     * 毕业院校
     * @return graduation_university 毕业院校
     */
    public String getGraduationUniversity() {
        return graduationUniversity;
    }

    /**
     * 毕业院校
     * @param graduationUniversity 毕业院校
     */
    public void setGraduationUniversity(String graduationUniversity) {
        this.graduationUniversity = graduationUniversity == null ? null : graduationUniversity.trim();
    }

    /**
     * 学历ID
     * @return education_id 学历ID
     */
    public Long getEducationId() {
        return educationId;
    }

    /**
     * 学历ID
     * @param educationId 学历ID
     */
    public void setEducationId(Long educationId) {
        this.educationId = educationId;
    }

    /**
     * 专业
     * @return major_id 专业
     */
    public Long getMajorId() {
        return majorId;
    }

    /**
     * 专业
     * @param majorId 专业
     */
    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    /**
     * 参加工作时间
     * @return working_hours 参加工作时间
     */
    public Date getWorkingHours() {
        return workingHours;
    }

    /**
     * 参加工作时间
     * @param workingHours 参加工作时间
     */
    public void setWorkingHours(Date workingHours) {
        this.workingHours = workingHours;
    }

    /**
     * 入职时间
     * @return induction_time 入职时间
     */
    public Date getInductionTime() {
        return inductionTime;
    }

    /**
     * 入职时间
     * @param inductionTime 入职时间
     */
    public void setInductionTime(Date inductionTime) {
        this.inductionTime = inductionTime;
    }

    /**
     * 转正时间
     * @return full_time 转正时间
     */
    public Date getFullTime() {
        return fullTime;
    }

    /**
     * 转正时间
     * @param fullTime 转正时间
     */
    public void setFullTime(Date fullTime) {
        this.fullTime = fullTime;
    }

    /**
     * 上岗证号
     * @return certificate_num 上岗证号
     */
    public String getCertificateNum() {
        return certificateNum;
    }

    /**
     * 上岗证号
     * @param certificateNum 上岗证号
     */
    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum == null ? null : certificateNum.trim();
    }

    /**
     * 用工性质（0-兼职；1-全职）
     * @return use_nature 用工性质（0-兼职；1-全职）
     */
    public Integer getUseNature() {
        return useNature;
    }

    /**
     * 用工性质（0-兼职；1-全职）
     * @param useNature 用工性质（0-兼职；1-全职）
     */
    public void setUseNature(Integer useNature) {
        this.useNature = useNature;
    }

    /**
     * 员工简介
     * @return user_about 员工简介
     */
    public String getUserAbout() {
        return userAbout;
    }

    /**
     * 员工简介
     * @param userAbout 员工简介
     */
    public void setUserAbout(String userAbout) {
        this.userAbout = userAbout == null ? null : userAbout.trim();
    }

    /**
     * 状态（0-离职；1-在职）
     * @return status 状态（0-离职；1-在职）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-离职；1-在职）
     * @param status 状态（0-离职；1-在职）
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
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 受限品种范围ID集合
     * @return limit_variety_range 受限品种范围ID集合
     */
    public String getLimitVarietyRange() {
        return limitVarietyRange;
    }

    /**
     * 受限品种范围ID集合
     * @param limitVarietyRange 受限品种范围ID集合
     */
    public void setLimitVarietyRange(String limitVarietyRange) {
        this.limitVarietyRange = limitVarietyRange == null ? null : limitVarietyRange.trim();
    }
    
	public String getCargoAreaIds() {
		return cargoAreaIds;
	}

	public void setCargoAreaIds(String cargoAreaIds) {
		this.cargoAreaIds = cargoAreaIds;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
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
        sb.append(", userId=").append(userId);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", userType=").append(userType);
        sb.append(", loginAccount=").append(loginAccount);
        sb.append(", password=").append(password);
        sb.append(", passwordConfirm=").append(passwordConfirm);
        sb.append(", deptIds=").append(deptIds);
        sb.append(", positionIds=").append(positionIds);
        sb.append(", roleIds=").append(roleIds);
        sb.append(", limitVariety=").append(limitVariety);
        sb.append(", graduationTime=").append(graduationTime);
        sb.append(", graduationUniversity=").append(graduationUniversity);
        sb.append(", educationId=").append(educationId);
        sb.append(", majorId=").append(majorId);
        sb.append(", workingHours=").append(workingHours);
        sb.append(", inductionTime=").append(inductionTime);
        sb.append(", fullTime=").append(fullTime);
        sb.append(", certificateNum=").append(certificateNum);
        sb.append(", useNature=").append(useNature);
        sb.append(", userAbout=").append(userAbout);
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
        sb.append(", limitVarietyRange=").append(limitVarietyRange);
        sb.append("]");
        return sb.toString();
    }
}