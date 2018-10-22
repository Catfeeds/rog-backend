package com.rograndec.feijiayun.chain.business.report.quality.distr.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * dongdong.zhang
 */
public class RequestDistrReturnCheckVO extends BaseRequestParamVO{
    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     * 配后退回验收单号
     */
    @ApiModelProperty(value = "验收单号")
    private String code;

    /**
     * 验收人员名称
     */
    @ApiModelProperty(value = "验收人员")
    private String checkerName;

    /**
     * 第二验收人员名称
     */
    @ApiModelProperty(value = "第二验收人员2")
    private String secondCheckerName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getSecondCheckerName() {
		return secondCheckerName;
	}

	public void setSecondCheckerName(String secondCheckerName) {
		this.secondCheckerName = secondCheckerName;
	}
    
    
}