package com.rograndec.feijiayun.chain.inf.pos.member.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: SelectPOSMemberVO   
 * @Description: POS会员VO
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月6日 上午10:58:26
 */
@ApiModel(value = "SelectPOSMemberVO", description = "POS选择会员")
public class SelectPOSMemberVO implements Serializable{
	
	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年10月6日 上午11:34:28 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键ID")
    private Long id;
	
	@ApiModelProperty(value = "会员卡号")
	private String cardCode;
	
	@ApiModelProperty(value = "会员姓名")
	private String memberName;
	
	@ApiModelProperty(value = "当前积分")
	private BigDecimal currentIntegral;
	
	@ApiModelProperty(value = "当前储值")
	private BigDecimal currentStoredAmount;
	
	@ApiModelProperty(value = "微信")
	private String wechatNum;
	
	@ApiModelProperty(value = "QQ")
	private String qqNum;
	
	@ApiModelProperty(value = "手机")
	private String mobilePhone;
	
	@ApiModelProperty(value = "会员卡号类型（0-会员卡；1-手机号）")
	private String cardCodeType;
	
	@ApiModelProperty(value = "会员卡类型名称")
	private String cardTypeName;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "发卡时间")
	private Date sendCardTime;
	
	@ApiModelProperty(value = "状态0-正常；1-未发卡；2-挂失；3-注销")
	private Integer status;
	
	@ApiModelProperty(value = "密码")
	private String password;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public BigDecimal getCurrentIntegral() {
		return currentIntegral;
	}
	public void setCurrentIntegral(BigDecimal currentIntegral) {
		this.currentIntegral = currentIntegral;
	}
	public BigDecimal getCurrentStoredAmount() {
		return currentStoredAmount;
	}
	public void setCurrentStoredAmount(BigDecimal currentStoredAmount) {
		this.currentStoredAmount = currentStoredAmount;
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
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getCardCodeType() {
		return cardCodeType;
	}
	public void setCardCodeType(String cardCodeType) {
		this.cardCodeType = cardCodeType;
	}
	public String getCardTypeName() {
		return cardTypeName;
	}
	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}
	public Date getSendCardTime() {
		return sendCardTime;
	}
	public void setSendCardTime(Date sendCardTime) {
		this.sendCardTime = sendCardTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
