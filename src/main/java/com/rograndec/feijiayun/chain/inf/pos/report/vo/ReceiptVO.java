package com.rograndec.feijiayun.chain.inf.pos.report.vo;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ReceiptVO", description = "收款信息")
public class ReceiptVO {
	
	@ApiModelProperty(value = "主键ID",hidden=true)
	private Long receiptId;
	
	@ApiModelProperty(value = "基础单据（销售单）ID")
    private Long baseOrderId;
	
	@ApiModelProperty(value = "基础单据（销售单）编码")
    private String baseOrderCode;
	
    @ApiModelProperty(value = "应收金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "实收金额")
    private BigDecimal realAmount;

    @ApiModelProperty(value = "找零金额")
    private BigDecimal diffAmount;
    
    @ApiModelProperty(value = "收款明细")
    private List<ReceiptDetailVO> list;
    

	public Long getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
	}

	public Long getBaseOrderId() {
		return baseOrderId;
	}

	public void setBaseOrderId(Long baseOrderId) {
		this.baseOrderId = baseOrderId;
	}

	public String getBaseOrderCode() {
		return baseOrderCode;
	}

	public void setBaseOrderCode(String baseOrderCode) {
		this.baseOrderCode = baseOrderCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public BigDecimal getDiffAmount() {
		return diffAmount;
	}

	public void setDiffAmount(BigDecimal diffAmount) {
		this.diffAmount = diffAmount;
	}

	public List<ReceiptDetailVO> getList() {
		return list;
	}

	public void setList(List<ReceiptDetailVO> list) {
		this.list = list;
	}
	
}
