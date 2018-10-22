package com.rograndec.feijiayun.chain.business.basic.user.vo;

import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.*;

/**
 * Created by zhaiwei on 2017/9/4.
 */
@ApiModel
public class UserExcelVO implements Serializable {

    /**
     * userId
     */
    @ApiModelProperty(value = "用户id ,修改时需要传入,新增时不需要", required = false)
    private Long id;
    /**
     * 企业ID
     */
    @ApiModelProperty(value = "组织机构id", required = true)
    private Long enterpriseId;

    @ApiModelProperty(value = "组织机构name", required = true)
    private String enterpriseName;

    /**
     * 编码 (根据init接口的初始化业务规则判断是否需要传入)
     */
    @ApiModelProperty(value = "编码", required = false)
    private String code;

    /**
     * 检索码
     */
    @ApiModelProperty(value = "检索码", required = false)
    private String pinyin;

    /**
     * 员工名称
     */
    @ApiModelProperty(value = "员工名称", required = true)
    private String name;

    /**
     * 用户类型集合（0-系统管理员；1-云系统用户；2-POS用户；3-云系统用户和POS用户）
     */
    @ApiModelProperty(value = "用户类型集合描述")
    private String userTypeDesc;

    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String loginAacount;

    @ApiModelProperty(value = " 状态描述", required = true)
    private String statusDesc;

    /**
     * 部门ID集合
     */

    @ApiModelProperty(value = "部门ID集合描述")
    private String deptsDesc;

    /**
     * 岗位ID集合
     */

    @ApiModelProperty(value = "岗位ID集合描述")
    private String positionsDesc;
    /**
     * 角色ID集合
     */

    @ApiModelProperty(value = "角色ID集合描述")
    private String rolesDesc;

    /**
     * 受限品种集合（可多选：0-药品；1-食品；2-化妆品；3-医疗器械；4-其它）
     */

    @ApiModelProperty(value = "受限品种集合描述")
    private String limitVarietysDesc;

    /**
     * 受限品种范围ID集合
     */

    @ApiModelProperty(value = "受限品种范围ID集合描述")
    private String limitVarietyRangeDesc;


    /**
     * 性别（0-男；1-女；2-其它）
     */
    @ApiModelProperty(value = "性别（0-男；1-女；2-其它）", required = false)
    private Integer sex;

    @ApiModelProperty(value = "性别描述", required = false)
    private String sexDesc;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号", required = false)
    private String idNum;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期", required = false)
    private Date birthDate;

    @ApiModelProperty(value = "出生日期描述", required = false)
    private String birthDateDesc;

    /**
     * 婚姻状况（0-未婚；1-已婚）
     */
    @ApiModelProperty(value = "婚姻状况（0-未婚；1-已婚）", required = false)
    private Integer maritalStatus;

    @ApiModelProperty(value = "婚姻状况描述", required = false)
    private String maritalStatusDesc;

    /**
     * 民族ID
     */
    @ApiModelProperty(value = "民族ID", required = false)
    private Long nationId;

    @ApiModelProperty(value = "民族ID", required = false)
    private String nationDesc;

    /**
     * 政治面貌
     */
    @ApiModelProperty(value = "政治面貌", required = false)
    private String politicalOutlook;

    /**
     * 籍贯
     */
    @ApiModelProperty(value = "籍贯", required = false)
    private String originPlace;

    /**
     * 住址
     */
    @ApiModelProperty(value = "住址", required = false)
    private String adderss;

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
     * 手机
     */
    @ApiModelProperty(value = "手机", required = false)
    private String mobilePhone;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", required = false)
    private String telephone;

    /**
     * 微信
     */
    @ApiModelProperty(value = "微信", required = false)
    private String wechatNum;

    /**
     * QQ
     */
    @ApiModelProperty(value = "QQ", required = false)
    private String qqNum;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", required = false)
    private String email;


    public static List<UserExcelVO> getUserExcelVO(List<UserReturnVO> userReturnVOS ){

        List<UserExcelVO> userExcelVOS = new ArrayList<>();

        for(UserReturnVO usv : userReturnVOS){
            UserExcelVO userExcelVO = new UserExcelVO();
            userExcelVO.setId(usv.getId());
            userExcelVO.setEnterpriseId(usv.getEnterpriseId());
            userExcelVO.setEnterpriseName(usv.getName());
            userExcelVO.setCode(usv.getCode());
            userExcelVO.setPinyin(usv.getPinyin());
            userExcelVO.setName(usv.getName());
            userExcelVOS.add(userExcelVO);
        }

        return userExcelVOS;
    }


    public static void getUserAdminExcelVO(UserAdministrationReturnVO usv,UserExcelVO userExcelVO ){
        userExcelVO.setUserTypeDesc(usv.getUserTypeDesc());
        userExcelVO.setLoginAacount(usv.getLoginAacount());

        String deptsDesc = StringSplit.StringAppendSymbol(usv.getDeptsDesc());
        userExcelVO.setDeptsDesc(deptsDesc);

        String posDesc = StringSplit.StringAppendSymbol(usv.getPositionsDesc());
        userExcelVO.setPositionsDesc(posDesc);

        String rolesDesc = StringSplit.StringAppendSymbol(usv.getRoleDesc());

        userExcelVO.setRolesDesc(rolesDesc);


        String limitVarietysDesc = StringSplit.StringAppendSymbol(usv.getLimitVarietysDesc());
        userExcelVO.setLimitVarietysDesc(limitVarietysDesc);

        userExcelVO.setGraduationTime(usv.getGraduationTime());

        userExcelVO.setGraduationTimeDesc(
                DateUtils.getDate(usv.getGraduationTimeDesc())
        );
        userExcelVO.setGraduationUniversity(usv.getGraduationUniversity());
        userExcelVO.setEducationId(usv.getEducationId());
        userExcelVO.setEducationName(usv.getEducationName());
        userExcelVO.setMajorId(usv.getMajorId());
        userExcelVO.setMajorName(usv.getMajorName());

        userExcelVO.setWorkingHours(usv.getWorkingHours());
        userExcelVO.setWorkingHoursDesc(
                DateUtils.getDate( usv.getWorkingHoursDesc())
        );

        userExcelVO.setInductionTime(usv.getInductionTime());
        userExcelVO.setInductionTimeDesc(DateUtils.getDate(usv.getInductionTimeDesc()));

        userExcelVO.setFullTime(usv.getFullTime());
        userExcelVO.setFullTimeDesc(DateUtils.getDate(usv.getFullTimeDesc()));

        userExcelVO.setCertificateNum(usv.getCertificateNum());
        userExcelVO.setUseNature(usv.getUseNature());
        userExcelVO.setUseNatureDesc(usv.getUseNatureDesc());
        String limitVarietyRangeDesc = StringSplit.StringAppendSymbol(usv.getLimitVarietyRangeDesc());
        userExcelVO.setLimitVarietyRangeDesc(limitVarietyRangeDesc);
        userExcelVO.setStatusDesc(usv.getStatusDesc());

    }


    public static void getUserPersonalExcelVO(UserPersonalReturnVO userPersonal,UserExcelVO userExcelVO){

        userExcelVO.setSex(userPersonal.getSex());
        userExcelVO.setSexDesc(userPersonal.getSexDesc());

        userExcelVO.setIdNum(userPersonal.getIdNum());

        userExcelVO.setBirthDate(userPersonal.getBirthDate());
        userExcelVO.setBirthDateDesc(DateUtils.getDate(userPersonal.getBirthDateDesc()));

        userExcelVO.setMaritalStatus(userPersonal.getMaritalStatus());

        userExcelVO.setMaritalStatusDesc(userPersonal.getMaritalStatusDesc());

        userExcelVO.setNationId(userPersonal.getNationId());
        userExcelVO.setNationDesc(userPersonal.getNationDesc());

        userExcelVO.setPoliticalOutlook(userPersonal.getPoliticalOutlook());
        userExcelVO.setOriginPlace(userPersonal.getOriginPlace());
        userExcelVO.setAdderss(userPersonal.getAdderss());
        userExcelVO.setMobilePhone(userPersonal.getMobilePhone().toString());
        userExcelVO.setTelephone(userPersonal.getTelephone());
        userExcelVO.setWechatNum(userPersonal.getWechatNum());
        userExcelVO.setQqNum(userPersonal.getQqNum());
        userExcelVO.setEmail(userPersonal.getEmail());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserTypeDesc() {
        return userTypeDesc;
    }

    public void setUserTypeDesc(String userTypeDesc) {
        this.userTypeDesc = userTypeDesc;
    }

    public String getLoginAacount() {
        return loginAacount;
    }

    public void setLoginAacount(String loginAacount) {
        this.loginAacount = loginAacount;
    }

    public String getDeptsDesc() {
        return deptsDesc;
    }

    public void setDeptsDesc(String deptsDesc) {
        this.deptsDesc = deptsDesc;
    }

    public String getPositionsDesc() {
        return positionsDesc;
    }

    public void setPositionsDesc(String positionsDesc) {
        this.positionsDesc = positionsDesc;
    }

    public String getRolesDesc() {
        return rolesDesc;
    }

    public void setRolesDesc(String rolesDesc) {
        this.rolesDesc = rolesDesc;
    }

    public String getLimitVarietysDesc() {
        return limitVarietysDesc;
    }

    public void setLimitVarietysDesc(String limitVarietysDesc) {
        this.limitVarietysDesc = limitVarietysDesc;
    }

    public String getLimitVarietyRangeDesc() {
        return limitVarietyRangeDesc;
    }

    public void setLimitVarietyRangeDesc(String limitVarietyRangeDesc) {
        this.limitVarietyRangeDesc = limitVarietyRangeDesc;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSexDesc() {
        return sexDesc;
    }

    public void setSexDesc(String sexDesc) {
        this.sexDesc = sexDesc;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDateDesc() {
        return birthDateDesc;
    }

    public void setBirthDateDesc(String birthDateDesc) {
        this.birthDateDesc = birthDateDesc;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatusDesc() {
        return maritalStatusDesc;
    }

    public void setMaritalStatusDesc(String maritalStatusDesc) {
        this.maritalStatusDesc = maritalStatusDesc;
    }

    public Long getNationId() {
        return nationId;
    }

    public void setNationId(Long nationId) {
        this.nationId = nationId;
    }

    public String getNationDesc() {
        return nationDesc;
    }

    public void setNationDesc(String nationDesc) {
        this.nationDesc = nationDesc;
    }

    public String getPoliticalOutlook() {
        return politicalOutlook;
    }

    public void setPoliticalOutlook(String politicalOutlook) {
        this.politicalOutlook = politicalOutlook;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getAdderss() {
        return adderss;
    }

    public void setAdderss(String adderss) {
        this.adderss = adderss;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWechatNum() {
        return wechatNum;
    }

    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
