package com.rograndec.feijiayun.chain.business.report.retail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel(value = "RetailTimeSaleVo", description = "门店-销售报表-时段销售")
public class RetailTimeSaleVo implements Serializable{

	@ApiModelProperty(value = "x轴坐标")
	List<String> xLine;
	@ApiModelProperty(value = "y轴坐标")
	List<BigDecimal> yLine;

	public List<String> getxLine() {
		return xLine;
	}

	public void setxLine(List<String> xLine) {
		this.xLine = xLine;
	}

	public List<BigDecimal> getyLine() {
		return yLine;
	}

	public void setyLine(List<BigDecimal> yLine) {
		this.yLine = yLine;
	}
}
