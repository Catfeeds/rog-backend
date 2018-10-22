package com.rograndec.feijiayun.chain.business.report.member.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: MemberIntegralRecordVO   
 * @Description: 会员报表-积分兑换
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月16日 下午2:01:44
 */
@ApiModel(value = "MemberStoredAmountDtlVO", description = "储值明细列表")
public class MemberStoredAmountDtlVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年10月16日 下午3:46:38 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "会员卡号")
	private String cardCode;
	@ApiModelProperty(value = "会员姓名")
	private String memberName;
	@ApiModelProperty(value = "手机")
	private String mobilePhone;
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "日期")
	private Date showDate;
	@ApiModelProperty(value = "操作类型")
	private String changeType;
	@ApiModelProperty(value = "单号")
	private String showCode;
	@ApiModelProperty(value = "操作人员")
	private String createrName;
	@ApiModelProperty(value = "门店编码")
	private String enterpriseCode;
	@ApiModelProperty(value = "门店名称")
	private String enterpriseName;
	
    
    @ApiModelProperty(value = "增减储值")
    private BigDecimal changeStoredAmount;
    @ApiModelProperty(value = "储值余额")
    private BigDecimal currentStoredAmount;

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

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public String getShowCode() {
		return showCode;
	}

	public void setShowCode(String showCode) {
		this.showCode = showCode;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public BigDecimal getChangeStoredAmount() {
		return changeStoredAmount;
	}

	public void setChangeStoredAmount(BigDecimal changeStoredAmount) {
		this.changeStoredAmount = changeStoredAmount;
	}

	public BigDecimal getCurrentStoredAmount() {
		return currentStoredAmount;
	}

	public void setCurrentStoredAmount(BigDecimal currentStoredAmount) {
		this.currentStoredAmount = currentStoredAmount;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

}
