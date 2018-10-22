package com.rograndec.feijiayun.chain.business.retail.shift.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import com.rograndec.feijiayun.chain.common.Page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "ResultPageVo", description = "page分页对象拓展vo")
public class ResultPageVo  extends Page implements Serializable{
	@ApiModelProperty(value = "存储支付方式总钱数map")
	private  Map<String,BigDecimal> sumMap;
	@ApiModelProperty(value = "固定字段汇总map")
	private  Map<String,BigDecimal> FieldMap;

	public ResultPageVo(Integer pageNo,Integer pageSize) {
		super(pageNo,pageSize);
	}

	public ResultPageVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Map<String,BigDecimal> getSumMap() {
		return sumMap;
	}

	public void setSumMap(Map<String,BigDecimal> sumMap) {
		
		this.sumMap = sumMap;
	}

	public Map<String, BigDecimal> getFieldMap() {
		return FieldMap;
	}

	public void setFieldMap(Map<String, BigDecimal> fieldMap) {
		FieldMap = fieldMap;
	}
}
