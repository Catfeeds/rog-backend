package com.rograndec.feijiayun.chain.business.report.member.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: MemberRankingVO   
 * @Description: 会员排行
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月19日 上午11:05:29
 */
@ApiModel(value = "MemberBirthDateVO", description = "会员生日列表")
public class MemberBirthDateVO {
	
	@ApiModelProperty(value = "会员卡类型")
	private String cardTypeName;
	@ApiModelProperty(value = "级别")
	private String levelName;
	@ApiModelProperty(value = "会员卡号")
	private String cardCode;
	@ApiModelProperty(value = "会员姓名")
	private String memberName;
	@ApiModelProperty(value = "手机")
	private String mobilePhone;
	@ApiModelProperty(value = "性别")
	private String sexName;
	@ApiModelProperty(value = "性别",hidden=true)
	private Integer sex;
	@ApiModelProperty(value = "生日")
	private String birthDate;
	@ApiModelProperty(value = "证件号码")
	private String idNum;
	@ApiModelProperty(value = "邮箱")
	private String email;
	@ApiModelProperty(value = "微信")
	private String wechatNum;
	@ApiModelProperty(value = "QQ")
	private String qqNum;
	@ApiModelProperty(value = "累计积分")
	private BigDecimal totalIntegral;
	@ApiModelProperty(value = "当前积分")
	private BigDecimal currentIntegral;
	@ApiModelProperty(value = "累计储值")
	private BigDecimal totalStoredAmount;
	@ApiModelProperty(value = "储值余额")
	private BigDecimal currentStoredAmount;
	
	public String getCardTypeName() {
		return cardTypeName;
	}
	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
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
	public BigDecimal getTotalIntegral() {
		return totalIntegral;
	}
	public void setTotalIntegral(BigDecimal totalIntegral) {
		this.totalIntegral = totalIntegral;
	}
	public BigDecimal getCurrentIntegral() {
		return currentIntegral;
	}
	public void setCurrentIntegral(BigDecimal currentIntegral) {
		this.currentIntegral = currentIntegral;
	}
	public BigDecimal getTotalStoredAmount() {
		return totalStoredAmount;
	}
	public void setTotalStoredAmount(BigDecimal totalStoredAmount) {
		this.totalStoredAmount = totalStoredAmount;
	}
	public BigDecimal getCurrentStoredAmount() {
		return currentStoredAmount;
	}
	public void setCurrentStoredAmount(BigDecimal currentStoredAmount) {
		this.currentStoredAmount = currentStoredAmount;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	
}
