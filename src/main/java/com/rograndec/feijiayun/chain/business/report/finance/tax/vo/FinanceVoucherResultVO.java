package com.rograndec.feijiayun.chain.business.report.finance.tax.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class FinanceVoucherResultVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2018年1月2日 下午6:49:19 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, value = "凭证日期")
	private String orderDate;
	
	@ApiModelProperty(required = false, value = "凭证号码")
	private String orderCode;
	
	@ApiModelProperty(required = false, value = "单据类型")
	private Integer baseOrderType;
	
	@ApiModelProperty(required = false, value = "单据类型")
	private String baseOrderTypeName;

	@ApiModelProperty(required = false, value = "单据号码")
	private String baseOrderCode;
	
	@ApiModelProperty(required = false, value = "操作人员")
	private String baseOperatorName;
	
	@ApiModelProperty(value = "摘要")
	private String remark;
	
	private List<FinanceVoucherDetailResultVO> detailList;

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getBaseOrderType() {
		return baseOrderType;
	}

	public void setBaseOrderType(Integer baseOrderType) {
		this.baseOrderType = baseOrderType;
	}

	public String getBaseOrderTypeName() {
		return baseOrderTypeName;
	}

	public void setBaseOrderTypeName(String baseOrderTypeName) {
		this.baseOrderTypeName = baseOrderTypeName;
	}

	public String getBaseOrderCode() {
		return baseOrderCode;
	}

	public void setBaseOrderCode(String baseOrderCode) {
		this.baseOrderCode = baseOrderCode;
	}

	public String getBaseOperatorName() {
		return baseOperatorName;
	}

	public void setBaseOperatorName(String baseOperatorName) {
		this.baseOperatorName = baseOperatorName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<FinanceVoucherDetailResultVO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<FinanceVoucherDetailResultVO> detailList) {
		this.detailList = detailList;
	}
	
}
