package com.rograndec.feijiayun.chain.inf.pos.sale.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class POSReceiptVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月18日 下午3:44:56 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "应收金额")
	private BigDecimal amount;
	
	@ApiModelProperty(value = "实收金额")
	private BigDecimal realAmount;
	
	@ApiModelProperty(value = "找零金额")
	private BigDecimal diffAmount;
	
	private List<POSReceiptDetailVo> detailList;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public BigDecimal getDiffAmount() {
		return diffAmount;
	}

	public void setDiffAmount(BigDecimal diffAmount) {
		this.diffAmount = diffAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<POSReceiptDetailVo> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<POSReceiptDetailVo> detailList) {
		this.detailList = detailList;
	}
	
}
