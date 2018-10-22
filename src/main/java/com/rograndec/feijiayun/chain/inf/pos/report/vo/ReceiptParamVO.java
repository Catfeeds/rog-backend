package com.rograndec.feijiayun.chain.inf.pos.report.vo;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ReceiptParamVO", description = "收款信息请求参数")
public class ReceiptParamVO {
	
	@NotNull(message="销售单主键ID不能为空")
	@ApiModelProperty(value = "销售单主键ID")
	private Long saleId;

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}
	
}
