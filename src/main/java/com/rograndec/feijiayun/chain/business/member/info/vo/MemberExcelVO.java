package com.rograndec.feijiayun.chain.business.member.info.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MemberExcelVO implements Serializable {
    /**
     * 会员卡类型名称
     */
    @ApiModelProperty(value = "会员卡类型名称")
    private String cardTypeName;

    /**
     * 会员卡号
     */
    @ApiModelProperty(value = "会员卡号")
    private String cardCode;

    /**
     * 发卡时间
     */
    @ApiModelProperty(value = "发卡时间--新增时不用传")
    private String sendCardTime;

    /**
     * 发卡人姓名
     */
    @ApiModelProperty(value = "发卡人姓名--新增时不用传")
    private String sendCardManName;

    /**
     * 发卡门店名称
     */
    @ApiModelProperty(value = "发卡门店名称--新增时不用传")
    private String sendCardStorerName;

    /**
     * 累积积分
     */
    @ApiModelProperty(value = "累积积分")
    private String totalIntegral;

    /**
     * 当前积分
     */
    @ApiModelProperty(value = "当前积分")
    private String currentIntegral;

    /**
     * 累积储值
     */
    @ApiModelProperty(value = "累积储值")
    private String totalStoredAmount;

    /**
     * 当前储值
     */
    @ApiModelProperty(value = "当前储值")
    private String currentStoredAmount;

    /**
     * 会员姓名
     */
    @ApiModelProperty(value = "会员姓名")
    private String memberName;

    /**
     * 性别（0-男；1-女；2-其它）
     */
    @ApiModelProperty(value = "性别（0-男；1-女；2-其它）")
    private String sex;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private String birthDate;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idNum;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobilePhone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 微信
     */
    @ApiModelProperty(value = "微信")
    private String wechatNum;

    /**
     * QQ
     */
    @ApiModelProperty(value = "QQ")
    private String qqNum;

    /**
     * 住址
     */
    @ApiModelProperty(value = "住址")
    private String adderss;

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getSendCardTime() {
        return sendCardTime;
    }

    public void setSendCardTime(String sendCardTime) {
        this.sendCardTime = sendCardTime;
    }

    public String getSendCardManName() {
        return sendCardManName;
    }

    public void setSendCardManName(String sendCardManName) {
        this.sendCardManName = sendCardManName;
    }

    public String getSendCardStorerName() {
        return sendCardStorerName;
    }

    public void setSendCardStorerName(String sendCardStorerName) {
        this.sendCardStorerName = sendCardStorerName;
    }

    public String getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(String totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public String getCurrentIntegral() {
        return currentIntegral;
    }

    public void setCurrentIntegral(String currentIntegral) {
        this.currentIntegral = currentIntegral;
    }

    public String getTotalStoredAmount() {
        return totalStoredAmount;
    }

    public void setTotalStoredAmount(String totalStoredAmount) {
        this.totalStoredAmount = totalStoredAmount;
    }

    public String getCurrentStoredAmount() {
        return currentStoredAmount;
    }

    public void setCurrentStoredAmount(String currentStoredAmount) {
        this.currentStoredAmount = currentStoredAmount;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAdderss() {
        return adderss;
    }

    public void setAdderss(String adderss) {
        this.adderss = adderss;
    }

    @Override
    public String toString() {
        return "MemberExcelVO{" +
                "cardTypeName='" + cardTypeName + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", sendCardTime='" + sendCardTime + '\'' +
                ", sendCardManName='" + sendCardManName + '\'' +
                ", sendCardStorerName='" + sendCardStorerName + '\'' +
                ", totalIntegral='" + totalIntegral + '\'' +
                ", currentIntegral='" + currentIntegral + '\'' +
                ", totalStoredAmount='" + totalStoredAmount + '\'' +
                ", currentStoredAmount='" + currentStoredAmount + '\'' +
                ", memberName='" + memberName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", idNum='" + idNum + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", wechatNum='" + wechatNum + '\'' +
                ", qqNum='" + qqNum + '\'' +
                ", adderss='" + adderss + '\'' +
                '}';
    }
}
