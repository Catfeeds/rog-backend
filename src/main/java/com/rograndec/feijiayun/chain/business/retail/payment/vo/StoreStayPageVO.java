package com.rograndec.feijiayun.chain.business.retail.payment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "StoreStayPageVO", description = "零售管理-零售缴款-门店-待缴款分页")
public class StoreStayPageVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value = "交班日期")
	private String shiftDate;
	
	@ApiModelProperty(required = true, value = "款台编码")
	private String standCode;
	
	@ApiModelProperty(required = true, value = "班组名称")
	private String teamName;
	
	@ApiModelProperty(required = true, value = "收款人员")
	private String payeeName;
	
	@ApiModelProperty(required = true, value = "开班时间")
	private Date openingTime;
	
	@ApiModelProperty(required = true, value = "交班时间")
	private Date shiftTime;
	
	@ApiModelProperty(required = true, value = "应收金额")
	private BigDecimal realAmountTotal;

}
