package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品列表
 * @author 孙帮祥
 * */
import io.swagger.annotations.ApiModelProperty;

public class DistrInReturnShelfVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "配进退出详shelf情单id")
	private Long id;
	
	@ApiModelProperty(value = "配进退出详shelf情单货区作业类型  0-存储作业区域；1-辅助作业区域；2-存储设备）")
	private Integer jobType;
	
	@ApiModelProperty(value = "配进退出详shelf情单货位job_type=0（存储作业区）时，job_area=0，含义为 合格品区；job_area=1，含义为 待处理区；job_area=2，含义为 不合格品区；")
	private Integer jobArea;
	
	@ApiModelProperty(value = "配进退出详shelf情单可退数量")
	private BigDecimal canReturnQuantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getJobType() {
		return jobType;
	}

	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}

	public Integer getJobArea() {
		return jobArea;
	}

	public void setJobArea(Integer jobArea) {
		this.jobArea = jobArea;
	}

	public BigDecimal getCanReturnQuantity() {
		return canReturnQuantity;
	}

	public void setCanReturnQuantity(BigDecimal canReturnQuantity) {
		this.canReturnQuantity = canReturnQuantity;
	}
	
}
