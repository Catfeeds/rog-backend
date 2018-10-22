package com.rograndec.feijiayun.chain.inf.pos.shift.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "POSAddShiftVO", description = "POS开班")
public class POSAddShiftVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="开班人员账号不能为空！")
	@ApiModelProperty(value = "开班人员账号")
	private String openingAccount;
	
	@NotNull(message="款员不能为空！")
	@ApiModelProperty(value = "款员Id")
	private Long payeeId;
	
	@ApiModelProperty(value = "款员编码")
	private String payeeCode;
	
	//默认当前登录人员
	@ApiModelProperty(value = "款员名称")
	private String payeeName;
	
	@NotNull(message="开班时间不能为空！")
	@ApiModelProperty(value = "开班时间,日期格式：yyyy-MM-dd HH:mm:ss")
	private Date openingTime;
	
	@NotNull(message="备用金不能为空！")
	@ApiModelProperty(value = "接收备用金")
	private BigDecimal acceptSpareMoney;

	public String getOpeningAccount() {
		return openingAccount;
	}

	public void setOpeningAccount(String openingAccount) {
		this.openingAccount = openingAccount;
	}
	
	public Long getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Long payeeId) {
		this.payeeId = payeeId;
	}

	public String getPayeeCode() {
		return payeeCode;
	}

	public void setPayeeCode(String payeeCode) {
		this.payeeCode = payeeCode;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public Date getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}

	public BigDecimal getAcceptSpareMoney() {
		return acceptSpareMoney;
	}

	public void setAcceptSpareMoney(BigDecimal acceptSpareMoney) {
		this.acceptSpareMoney = acceptSpareMoney;
	}
	
}
