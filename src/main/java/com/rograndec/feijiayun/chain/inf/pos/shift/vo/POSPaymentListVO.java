package com.rograndec.feijiayun.chain.inf.pos.shift.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "POSPaymentListVO", description = "POS交接班支付")
public class POSPaymentListVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value = "支付方式ID")
	private Long payTypeId;
	
	@ApiModelProperty(required = true, value = "支付方式")
	private String payTypeName;
	
	@ApiModelProperty(required = true, value = "金额")
	private BigDecimal payAmount;

	public Long getPayTypeId() {
		return payTypeId;
	}

	public void setPayTypeId(Long payTypeId) {
		this.payTypeId = payTypeId;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	
}
