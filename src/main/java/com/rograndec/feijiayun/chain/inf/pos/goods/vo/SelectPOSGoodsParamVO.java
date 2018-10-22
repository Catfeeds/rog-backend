package com.rograndec.feijiayun.chain.inf.pos.goods.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SelectPOSGoodsParamVO", description = "POS选择商品分页对象")
public class SelectPOSGoodsParamVO{
	
	@ApiModelProperty(required = false, value = "检索条件:商品编码/条形码/检索码/名称/通用名称/批准文号")
	private String param;
	
	@ApiModelProperty(value = "按企业id查询数据",hidden=true)
	private Long enterpriseId;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	

}
