package com.rograndec.feijiayun.chain.business.report.quality.distr.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * dongdong.zhang
 */
public class RequestDistrReturnNoticeVO extends BaseRequestParamVO{
    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     * 配后退回通知单号
     */
    @ApiModelProperty(value = "配后退回单号")
    private String code;
    
    /**
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getDistrType() {
		return distrType;
	}

	public void setDistrType(Integer distrType) {
		this.distrType = distrType;
	}
	
}