package com.rograndec.feijiayun.chain.inf.pos.report.vo;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleParamVO", description = "POS报表搜索参数")
public class SaleParamVO {
	
	@NotEmpty(message="开始时间不能为空")
	@ApiModelProperty(required = true, value = "开始时间,yyyy-MM-dd")
	private String startDate;
	
	@NotEmpty(message="结束时间不能为空")
	@ApiModelProperty(required = true, value = "结束时间,yyyy-MM-dd")
	private String endDate;
	
	@ApiModelProperty(value = "按企业id查询数据",hidden=true)
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
	
	
}
