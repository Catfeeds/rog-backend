package com.rograndec.feijiayun.chain.business.retail.payment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "StoreStayViewPageVO", description = "零售管理-零售缴款-门店-查看待缴款分页")
public class StoreStayViewPageVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value = "款台编码")
	private String standCode;
	
	@ApiModelProperty(required = true, value = "班组名称")
	private String teamName;
	
	@ApiModelProperty(required = true, value = "收款人员")
	private String payeeName;
	
	@ApiModelProperty(required = true, value = "销售时间")
	private Date saleDate;
	
	@ApiModelProperty(required = true, value = "销售单号")
	private String saleCode;
	
	@ApiModelProperty(required = true, value = "应收金额")
	private BigDecimal realAmountTotal;

}
