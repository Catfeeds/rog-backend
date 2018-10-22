package com.rograndec.feijiayun.chain.business.report.finance.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.rograndec.feijiayun.chain.common.constant.OrderRule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: AgingVO   
 * @Description: 应付/应收账款-应付/应收账龄分析
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月15日 下午3:57:02
 */
@ApiModel(value = "AgingVO", description = "财务管理-应付/应收账龄-应付/应收账龄分析显示")
public class AgingVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2018年1月15日 下午3:56:53 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "是否合并单元格")
	private boolean merge;
	
	@ApiModelProperty(value = "单位编码")
	private String supplierCode;
	
	@ApiModelProperty(value = "单位名称")
	private String supplierName;
	
	@ApiModelProperty(value = "单据日期")
	private String orderDate;
	
	@ApiModelProperty(value = "单据编号")
	private String orderCode;
	
	@ApiModelProperty(value = "单据类型")
	private Integer orderType;
	
	@ApiModelProperty(value = "单据类型名称")
	private String orderTypeName;
	
	@ApiModelProperty(value = "单据金额")
	private BigDecimal orderAmountTotal;
	
	@ApiModelProperty(value = "已清金额")
	private BigDecimal clearAmountTotal;
	
	@ApiModelProperty(value = "未清金额")
	private BigDecimal unclearAmountTotal;
	
	@ApiModelProperty(value = "账期-(0-30)")
	private BigDecimal accountPeriodA=BigDecimal.ZERO;
	
	@ApiModelProperty(value = "账期-(31-60)")
	private BigDecimal accountPeriodB=BigDecimal.ZERO;
	
	@ApiModelProperty(value = "账期-(61-90)")
	private BigDecimal accountPeriodC=BigDecimal.ZERO;
	
	@ApiModelProperty(value = "账期-(91-120)")
	private BigDecimal accountPeriodD=BigDecimal.ZERO;
	
	@ApiModelProperty(value = "账期-(120+)")
	private BigDecimal accountPeriodE=BigDecimal.ZERO;
	

	public boolean isMerge() {
		return merge;
	}

	public void setMerge(boolean merge) {
		this.merge = merge;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

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

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getOrderTypeName() {
		if(orderType != null) {
			orderTypeName = OrderRule.getName(orderType);
		}
		return orderTypeName;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public BigDecimal getOrderAmountTotal() {
		return orderAmountTotal;
	}

	public void setOrderAmountTotal(BigDecimal orderAmountTotal) {
		this.orderAmountTotal = orderAmountTotal;
	}

	public BigDecimal getClearAmountTotal() {
		return clearAmountTotal;
	}

	public void setClearAmountTotal(BigDecimal clearAmountTotal) {
		this.clearAmountTotal = clearAmountTotal;
	}

	public BigDecimal getUnclearAmountTotal() {
		return unclearAmountTotal;
	}

	public void setUnclearAmountTotal(BigDecimal unclearAmountTotal) {
		this.unclearAmountTotal = unclearAmountTotal;
	}

	public BigDecimal getAccountPeriodA() {
		return accountPeriodA;
	}

	public void setAccountPeriodA(BigDecimal accountPeriodA) {
		this.accountPeriodA = accountPeriodA;
	}

	public BigDecimal getAccountPeriodB() {
		return accountPeriodB;
	}

	public void setAccountPeriodB(BigDecimal accountPeriodB) {
		this.accountPeriodB = accountPeriodB;
	}

	public BigDecimal getAccountPeriodC() {
		return accountPeriodC;
	}

	public void setAccountPeriodC(BigDecimal accountPeriodC) {
		this.accountPeriodC = accountPeriodC;
	}

	public BigDecimal getAccountPeriodD() {
		return accountPeriodD;
	}

	public void setAccountPeriodD(BigDecimal accountPeriodD) {
		this.accountPeriodD = accountPeriodD;
	}

	public BigDecimal getAccountPeriodE() {
		return accountPeriodE;
	}

	public void setAccountPeriodE(BigDecimal accountPeriodE) {
		this.accountPeriodE = accountPeriodE;
	}

	
}
