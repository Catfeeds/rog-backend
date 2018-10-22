package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_distr_req_plan
 * 
 * 
 * @author dongdong.zhang
 * 
 * 2017-11-29
 */
public class DistrReqPlanOutVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 供货或调出单位ID
     */
    @ApiModelProperty(value = "供货或调出单位ID")
    private Long outboundUnitId;

    /**
     * 供货或调出单位编码
     */
    @ApiModelProperty(value = "供货或调出单位编码")
    private String outboundUnitCode;

    /**
     * 供货或调出单位名称
     */
    @ApiModelProperty(value = "供货或调出单位名称")
    private String outboundUnitName;

	public Long getOutboundUnitId() {
		return outboundUnitId;
	}

	public void setOutboundUnitId(Long outboundUnitId) {
		this.outboundUnitId = outboundUnitId;
	}

	public String getOutboundUnitCode() {
		return outboundUnitCode;
	}

	public void setOutboundUnitCode(String outboundUnitCode) {
		this.outboundUnitCode = outboundUnitCode;
	}

	public String getOutboundUnitName() {
		return outboundUnitName;
	}

	public void setOutboundUnitName(String outboundUnitName) {
		this.outboundUnitName = outboundUnitName;
	}

}