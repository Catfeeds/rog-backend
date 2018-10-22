package com.rograndec.feijiayun.chain.business.report.quality.distr.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * dongdong.zhang
 */
public class RequestDistrReturnInVO extends BaseRequestParamVO{
    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     * 配进入库单号
     */
    @ApiModelProperty(value = "入库单号")
    private String code;
    
    /**
     * 入库人员名称
     */
    @ApiModelProperty(value = "入库人员")
    private String storageManName;
    
    /**
     * 流通监管码
     */
    @ApiModelProperty(value = "流通监管码")
    private String flowCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStorageManName() {
		return storageManName;
	}

	public void setStorageManName(String storageManName) {
		this.storageManName = storageManName;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}
    
    
}