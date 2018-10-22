package com.rograndec.feijiayun.chain.inf.pos.shift.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "POSCommitShiftDetailVO", description = "POS交班提交销售单数据")
public class POSCommitShiftDetailVO implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@ApiModelProperty(value = "销售单主键id",hidden=true)
	private Long baseOrderId;
	
	@ApiModelProperty(value = "销售单编码")
	private String baseOrderCode;

	@ApiModelProperty(value = "销售单日期,yyyy-MM-dd HH:mm:ss",hidden=true)
	private Date baseOrderDate;

	public Long getBaseOrderId() {
		return baseOrderId;
	}

	public void setBaseOrderId(Long baseOrderId) {
		this.baseOrderId = baseOrderId;
	}

	public String getBaseOrderCode() {
		return baseOrderCode;
	}

	public void setBaseOrderCode(String baseOrderCode) {
		this.baseOrderCode = baseOrderCode;
	}

	public Date getBaseOrderDate() {
		return baseOrderDate;
	}

	public void setBaseOrderDate(Date baseOrderDate) {
		this.baseOrderDate = baseOrderDate;
	}
	
	
	
}
