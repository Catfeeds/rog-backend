package com.rograndec.feijiayun.chain.business.retail.pricing.vo;

import java.io.Serializable;

import com.rograndec.feijiayun.chain.common.vo.PageVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: SalePricingVO
 * @Description:  零售管理-划价单
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 15:56:13
 */
@ApiModel(value = "SalePricingParamVO", description = "划价单查询列表参数")
public class SalePricingParamVO extends PageVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 状态（92-待支付,33-已完成,34-已取消）
     */
	@ApiModelProperty(required = true, value = "状态（92-待支付,33-已完成,34-已取消）")
	private Integer status;
	
	@ApiModelProperty(required = true, value = "起始时间")
	private String startTime;
	
	@ApiModelProperty(required = true, value = "结束时间")
	private String endTime;
	
	/**
     * 划价单号
     */
	@ApiModelProperty(required = true, value = "划价单号")
	private String code;
	
	/**
     * 创建人名称
     */
    @ApiModelProperty(value = "划价人员")
    private String createrName;

	
	/**
     * 购药者姓名
     */
	@ApiModelProperty(required = false, value = "购药者姓名")
	private String consumerName;
	
	

	/**
	 * 划价单号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 划价单号
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 购药者姓名
	 */
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	
	/**
	 * 购药者姓名
	 */
	public String getConsumerName() {
		return consumerName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}	


}