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
@ApiModel(value = "MemberIntegralDtlVO", description = "积分明细列表")
public class MemberIntegralDtlVO implements Serializable{

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
	
    
    @ApiModelProperty(value = "增减积分")
    private BigDecimal changeIntegral;
    @ApiModelProperty(value = "积分余额")
    private BigDecimal currentIntegral;

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

	public BigDecimal getChangeIntegral() {
		return changeIntegral;
	}

	public void setChangeIntegral(BigDecimal changeIntegral) {
		this.changeIntegral = changeIntegral;
	}

	public BigDecimal getCurrentIntegral() {
		return currentIntegral;
	}

	public void setCurrentIntegral(BigDecimal currentIntegral) {
		this.currentIntegral = currentIntegral;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

}
