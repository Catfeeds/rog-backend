package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo;

import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderResponseVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class VerificationFormCountVO implements Serializable {
    /**
     * 金额合计
     */
	@ApiModelProperty(value = "金额合计）")
    private BigDecimal amountTotal;
    /**
     * 不含税金额合计
     */
	@ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;
    /**
     * 税额合计
     */
	@ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;
	
	/**
	 * 核销单列表
	 * */
	@ApiModelProperty(value = "核销单列表")
	private List<VerificationFormVO> verificationFormListVO;
	public BigDecimal getAmountTotal() {
		return amountTotal;
	}
	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}
	public BigDecimal getNotaxRealAmountTotal() {
		return notaxRealAmountTotal;
	}
	public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
		this.notaxRealAmountTotal = notaxRealAmountTotal;
	}
	public BigDecimal getTaxAmountTotal() {
		return taxAmountTotal;
	}
	public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
		this.taxAmountTotal = taxAmountTotal;
	}

	public List<VerificationFormVO> getVerificationFormListVO() {
		return verificationFormListVO;
	}

	public void setVerificationFormListVO(List<VerificationFormVO> verificationFormListVO) {
		this.verificationFormListVO = verificationFormListVO;
	}
}