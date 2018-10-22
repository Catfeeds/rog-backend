package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author dongdong.zhang
 * 2017-10-12
 */
@ApiModel
public class DistrInReturnOutAddFormShelfVO implements Serializable {

	/**
	 * 货位ID
	 */
	@ApiModelProperty(value = "货位ID",required = true)
	private Long shelfId;
	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量",required = true)
	private BigDecimal quantity;

	@ApiModelProperty(value = "货位质量状态描述",required = true)
	private String shelfStatusDesc;


	/**
	 * 行号
	 */
	@ApiModelProperty(value = "行号",required = true)
	private Integer lineNum;

	public Long getShelfId() {
		return shelfId;
	}

	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getShelfStatusDesc() {
		return shelfStatusDesc;
	}

	public void setShelfStatusDesc(String shelfStatusDesc) {
		this.shelfStatusDesc = shelfStatusDesc;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}
}