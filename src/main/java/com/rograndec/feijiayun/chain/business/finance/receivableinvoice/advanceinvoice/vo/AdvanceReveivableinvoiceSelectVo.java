package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo;

import java.io.Serializable;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoice;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoiceInfo;

import io.swagger.annotations.ApiModelProperty;

public class AdvanceReveivableinvoiceSelectVo implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 发票信息
	 */
	@ApiModelProperty(value = "发票信息")
	private AdvanceReceivableInvoice advanceReceivableInvoice;
	/**
	 * 发票详情
	 */
	@ApiModelProperty(value = "发票详情")
	private AdvanceReceivableInvoiceDetailListVo advanceReceivableInvoiceDetailListVo;

	/**
	 * 开票信息
	 */
	@ApiModelProperty(value = "开票信息")
	private  AdvanceReceivableInvoiceInfo advanceReceivableInvoiceInfo;
	public AdvanceReceivableInvoice getAdvanceReceivableInvoice() {
		return advanceReceivableInvoice;
	}
	public void setAdvanceReceivableInvoice(AdvanceReceivableInvoice advanceReceivableInvoice) {
		this.advanceReceivableInvoice = advanceReceivableInvoice;
	}

	public AdvanceReceivableInvoiceInfo getAdvanceReceivableInvoiceInfo() {
		return advanceReceivableInvoiceInfo;
	}
	public void setAdvanceReceivableInvoiceInfo(AdvanceReceivableInvoiceInfo advanceReceivableInvoiceInfo) {
		this.advanceReceivableInvoiceInfo = advanceReceivableInvoiceInfo;
	}
	public AdvanceReceivableInvoiceDetailListVo getAdvanceReceivableInvoiceDetailListVo() {
		return advanceReceivableInvoiceDetailListVo;
	}
	public void setAdvanceReceivableInvoiceDetailListVo(
			AdvanceReceivableInvoiceDetailListVo advanceReceivableInvoiceDetailListVo) {
		this.advanceReceivableInvoiceDetailListVo = advanceReceivableInvoiceDetailListVo;
	}
}
