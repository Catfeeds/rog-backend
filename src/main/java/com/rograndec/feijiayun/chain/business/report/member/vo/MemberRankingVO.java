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
@ApiModel(value = "MemberRankingVO", description = "会员排行列表")
public class MemberRankingVO {
	
	@ApiModelProperty(value = "会员卡号")
	private String cardCode;
	@ApiModelProperty(value = "会员姓名")
	private String memberName;
	@ApiModelProperty(value = "手机")
	private String mobilePhone;
	
	@ApiModelProperty(value = "消费金额")
	private BigDecimal saleAmount = BigDecimal.ZERO;
	@ApiModelProperty(value = "累计数据")
	private BigDecimal totalData = BigDecimal.ZERO;
	@ApiModelProperty(value = "当前数据")
	private BigDecimal currentData = BigDecimal.ZERO;
	
	
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
	public BigDecimal getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}
	public BigDecimal getTotalData() {
		return totalData;
	}
	public void setTotalData(BigDecimal totalData) {
		this.totalData = totalData;
	}
	public BigDecimal getCurrentData() {
		return currentData;
	}
	public void setCurrentData(BigDecimal currentData) {
		this.currentData = currentData;
	}
	
	
	
}
