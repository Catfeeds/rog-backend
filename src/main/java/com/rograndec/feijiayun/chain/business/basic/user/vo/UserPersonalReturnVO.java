package com.rograndec.feijiayun.chain.business.basic.user.vo;

import com.rograndec.feijiayun.chain.business.basic.user.entity.Nation;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserPersonal;
import com.rograndec.feijiayun.chain.common.constant.MaritalStatus;
import com.rograndec.feijiayun.chain.common.constant.SexType;
import com.rograndec.feijiayun.chain.common.file.entity.File;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel
public class UserPersonalReturnVO implements Serializable {

    /**
     * user 个人信息id
     */
    @ApiModelProperty(value = "user 个人信息id ,修改时需要传入,新增时不需要", required = false)
    private Long id;


    /**
     * 性别（0-男；1-女；2-其它）
     */
    @ApiModelProperty(value = "性别（0-男；1-女；2-其它）", required = false)
    private Integer sex;

    @ApiModelProperty(value = "性别描述", required = false)
    private String sexDesc;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

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
     * 手机
     */
    @ApiModelProperty(value = "手机", required = false)
    private Long mobilePhone;

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

    /**
     * 照片附件ID
     */
    @ApiModelProperty(value = "照片附件ID", required = false)
    private Long photoId;
    /**
     * 照片附件ID
     */
    @ApiModelProperty(value = "照片附件名称", required = false)
    private String photoName;

    public static UserPersonalReturnVO getUserPersonalReturnVO4Personal(UserPersonal userPersonal, Nation nation, File file){
        UserPersonalReturnVO userPersonalReturnVO = new UserPersonalReturnVO();

        userPersonalReturnVO.setId(userPersonal.getId());
        userPersonalReturnVO.setUserId(userPersonal.getUserId());
        userPersonalReturnVO.setSex(userPersonal.getSex());
        if(null != userPersonal.getSex())
            userPersonalReturnVO.setSexDesc( SexType.getSexType4Code(userPersonal.getSex()).getName());

        userPersonalReturnVO.setIdNum(userPersonal.getIdNum());



        userPersonalReturnVO.setBirthDate(userPersonal.getBirthDate());
        if(null != userPersonal.getBirthDate())
            userPersonalReturnVO.setBirthDateDesc( DateUtils.DateToString(userPersonal.getBirthDate(),DateUtils.FMT_DATE_TIME));

        userPersonalReturnVO.setMaritalStatus(userPersonal.getMaritalStatus());

        if(null != userPersonal.getMaritalStatus())
            userPersonalReturnVO.setMaritalStatusDesc(MaritalStatus.getMaritalStatus4Code(userPersonal.getMaritalStatus()).getName());

        userPersonalReturnVO.setNationId(userPersonal.getNationId());
        if(null != userPersonal.getNationId())
            userPersonalReturnVO.setNationDesc(null == nation ? "" : nation.getName());

        userPersonalReturnVO.setPoliticalOutlook(userPersonal.getPoliticalOutlook());
        userPersonalReturnVO.setOriginPlace(userPersonal.getOriginPlace());
        userPersonalReturnVO.setAdderss(userPersonal.getAdderss());
        userPersonalReturnVO.setMobilePhone(Long.parseLong(userPersonal.getMobilePhone()));
        userPersonalReturnVO.setTelephone(userPersonal.getTelephone());
        userPersonalReturnVO.setWechatNum(userPersonal.getWechatNum());
        userPersonalReturnVO.setQqNum(userPersonal.getQqNum());
        userPersonalReturnVO.setEmail(userPersonal.getEmail());
        userPersonalReturnVO.setPhotoId(userPersonal.getPhotoId());
        if(null != file){
            userPersonalReturnVO.setPhotoName(file.getFileName());

        }

        return userPersonalReturnVO;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Long getNationId() {
        return nationId;
    }

    public void setNationId(Long nationId) {
        this.nationId = nationId;
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

    public Long getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Long mobilePhone) {
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

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSexDesc() {
        return sexDesc;
    }

    public void setSexDesc(String sexDesc) {
        this.sexDesc = sexDesc;
    }

    public String getBirthDateDesc() {
        return birthDateDesc;
    }

    public void setBirthDateDesc(String birthDateDesc) {
        this.birthDateDesc = birthDateDesc;
    }

    public String getMaritalStatusDesc() {
        return maritalStatusDesc;
    }

    public void setMaritalStatusDesc(String maritalStatusDesc) {
        this.maritalStatusDesc = maritalStatusDesc;
    }

    public String getNationDesc() {
        return nationDesc;
    }

    public void setNationDesc(String nationDesc) {
        this.nationDesc = nationDesc;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}