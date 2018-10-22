package com.rograndec.feijiayun.chain.business.retail.payment.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PaymentListVO", description = "零售管理-零售缴款-缴费详情列表对象")
public class PaymentListVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value = "支付方式ID")
	private Long payTypeId;
	
	@ApiModelProperty(required = true, value = "支付方式")
	private String payTypeName;
	
	@ApiModelProperty(required = true, value = "应缴金额")
	private BigDecimal payAmount;
	
	@ApiModelProperty(required = true, value = "实缴金额")
	private BigDecimal realAmount;
	
	@ApiModelProperty(required = true, value = "缴款差额")
	private BigDecimal diffAmount;
	
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

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public BigDecimal getDiffAmount() {
		return diffAmount;
	}

	public void setDiffAmount(BigDecimal diffAmount) {
		this.diffAmount = diffAmount;
	}
	
}
