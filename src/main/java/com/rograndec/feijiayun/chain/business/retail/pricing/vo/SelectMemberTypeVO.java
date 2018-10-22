package com.rograndec.feijiayun.chain.business.retail.pricing.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: SalePricingVO
 * @Description:  零售管理-划价单 - 选择会员
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 15:56:13
 */
@ApiModel(value = "SelectMemberTypeVO", description = "零售管理-划价单-选择会员")
public class SelectMemberTypeVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "会员ID")
	private Long id;
	@ApiModelProperty(required = true, value = "会员卡号")
	private String cardCode;
	@ApiModelProperty(required = true, value = "会员姓名")
	private String memberName;
	@ApiModelProperty(required = true, value = "会员手机号")
	private String mobilePhone;
	@ApiModelProperty(required = true, value = "价格策略（0-零售价；1-会员价）")
	private int priceStrategy;
	@ApiModelProperty(required = true, value = "价格策略（0-零售价；1-会员价）")
	private String priceStrategyStr;
	@ApiModelProperty(required = true, value = "折让策略")
	private BigDecimal discountStrategy;
	
	
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
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public int getPriceStrategy() {
		return priceStrategy;
	}
	public void setPriceStrategy(int priceStrategy) {
		this.priceStrategy = priceStrategy;
	}
	public BigDecimal getDiscountStrategy() {
		return discountStrategy;
	}
	public void setDiscountStrategy(BigDecimal discountStrategy) {
		this.discountStrategy = discountStrategy;
	}
	public String getPriceStrategyStr() {
		return priceStrategyStr;
	}
	public void setPriceStrategyStr(String priceStrategyStr) {
		this.priceStrategyStr = priceStrategyStr;
	}
	
	
	
	
	

}