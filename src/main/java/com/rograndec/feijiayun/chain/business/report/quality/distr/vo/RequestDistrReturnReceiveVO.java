package com.rograndec.feijiayun.chain.business.report.quality.distr.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * dongdong.zhang
 */
public class RequestDistrReturnReceiveVO extends BaseRequestParamVO{
    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     * 配后退回收货单号
     */
    @ApiModelProperty(value = "配后退回收货单号")
    private String code;

    /**
     * 收货人员1
     */
    @ApiModelProperty(value = "收货人员")
    private String receiverName;

    /**
     * 收货人员2
     */
    @ApiModelProperty(value = "收货人员2")
    private String secondReceiverName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getSecondReceiverName() {
		return secondReceiverName;
	}

	public void setSecondReceiverName(String secondReceiverName) {
		this.secondReceiverName = secondReceiverName;
	}
    
    
}