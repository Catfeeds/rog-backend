package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoice;

import io.swagger.annotations.ApiModelProperty;

public class AdvanceReveivableInvoiceListVo extends AdvanceReceivableInvoice{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "状态名称")
	private String statusName;
	@ApiModelProperty(value = "对账状态名称")
	private String accountStatusName;
	@ApiModelProperty(value = "是否可以对账")
	private boolean account;
	@ApiModelProperty(value = "是否可以更新")
	private boolean update;
	@ApiModelProperty(value = "是否可以冲销")
	private boolean cancel;
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getAccountStatusName() {
		return accountStatusName;
	}
	public void setAccountStatusName(String accountStatusName) {
		this.accountStatusName = accountStatusName;
	}
	public boolean isAccount() {
		return account;
	}
	public void setAccount(boolean account) {
		this.account = account;
	}
	public boolean isUpdate() {
		return update;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}
	public boolean isCancel() {
		return cancel;
	}
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
	

}
