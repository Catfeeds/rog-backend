package com.rograndec.feijiayun.chain.business.basic.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@ApiModel
public class UserPersonalVO implements Serializable {

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

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号", required = false)
    private String idNum = "";

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期", required = false)
    private Date birthDate;

    /**
     * 婚姻状况（0-未婚；1-已婚）
     */
    @ApiModelProperty(value = "婚姻状况（0-未婚；1-已婚）", required = false)
    private Integer maritalStatus;

    /**
     * 民族ID
     */
    @ApiModelProperty(value = "民族ID", required = false)
    private Long nationId;

    /**
     * 政治面貌
     */
    @ApiModelProperty(value = "政治面貌", required = false)
    private String politicalOutlook = "";

    /**
     * 籍贯
     */
    @ApiModelProperty(value = "籍贯", required = false)
    private String originPlace = "";

    /**
     * 住址
     */
    @ApiModelProperty(value = "住址", required = false)
    private String adderss = "";

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机", required = true)
    @NotNull(message = "手机号不能为空")
    private String mobilePhone;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", required = false)
    private String telephone = "";

    /**
     * 微信
     */
    @ApiModelProperty(value = "微信", required = false)
    private String wechatNum = "";

    /**
     * QQ
     */
    @ApiModelProperty(value = "QQ", required = false)
    private String qqNum = "";

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", required = true)
    @NotNull(message = "邮箱不能为空")
    private String email;

    /**
     * 照片附件ID
     */
    @ApiModelProperty(value = "照片附件ID", required = false)
    private Long photoId;

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
}