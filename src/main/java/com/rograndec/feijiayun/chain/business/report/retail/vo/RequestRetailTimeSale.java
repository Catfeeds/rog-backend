package com.rograndec.feijiayun.chain.business.report.retail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value = "RequestRetailTimeSale", description = "门店-销售报表-时段销售")
public class RequestRetailTimeSale implements Serializable{

	@NotNull(message = "类型不能为空")
	@ApiModelProperty(value = "类型, 1-按金额,2-按客流", required = true)
	private Integer saleType;

	@ApiModelProperty(value = "销售日期",required = false)
	private String saleDate;

	@ApiModelProperty(value = "base64加密的图片流用于导出",required = false)
	private String pic;

	@ApiModelProperty(value = "无需传值")
	private String startDate;
	@ApiModelProperty(value = "无需传值")
	private String endDate;
	@ApiModelProperty(value = "无需传值")
	private Long enterpriseId;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getSaleType() {
		return saleType;
	}

	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
}
