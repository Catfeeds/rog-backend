package com.rograndec.feijiayun.chain.business.report.member.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberSaleVO", description = "会员销售报表列表")
public class MemberSaleVO extends MemberIntegralExchangeVO{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年10月17日 上午11:25:22 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "批号")
	private String lotNumber;
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "生产日期")
	private Date productDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "有效期")
	private Date validUntil;
	@ApiModelProperty(value = "货位名称")
	private String shelfName;
	@ApiModelProperty(value = "数量")
	private BigDecimal quantity;
	@ApiModelProperty(value="单价")
	private BigDecimal unitPrice;
	@ApiModelProperty(value="金额")
	private BigDecimal amount;
	@ApiModelProperty(value="积分")
	private BigDecimal currentIntegral;
	
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public Date getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
	public String getShelfName() {
		return shelfName;
	}
	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getCurrentIntegral() {
		return currentIntegral;
	}
	public void setCurrentIntegral(BigDecimal currentIntegral) {
		this.currentIntegral = currentIntegral;
	}
	
	
}
