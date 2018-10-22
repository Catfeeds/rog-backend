package com.rograndec.feijiayun.chain.business.system.approval.vo;

import io.swagger.annotations.ApiModelProperty;

public class ApprovalFlowUpdateCheckVO {
	
	/**
	 * 检查状态 （true-通过；false-不允许修改） 
	 */
	@ApiModelProperty(value="检查状态 （true-通过；false-不允许修改）", required=true)
	private boolean status;
	
	/**
	 * 检查结果，如：有未完成的关联流程，不允许修改
	 */
	@ApiModelProperty(value="检查结果，如：有未完成的关联流程，不允许修改", required=true)
	private String msg;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
