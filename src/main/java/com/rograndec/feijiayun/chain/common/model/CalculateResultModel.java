package com.rograndec.feijiayun.chain.common.model;

import java.math.BigDecimal;

/**
 * 
 * @ClassName: CalculateResultModel 
 * @Description: 计算结果model
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @date 2017年9月18日 下午7:15:28 
 *
 */
public class CalculateResultModel {

	private BigDecimal price;//单价
	private BigDecimal amount;// 金额（整单折扣前金额）：数量*单价*行折扣
	private BigDecimal lineRoundOff;// 行舍入：整单舍入*（行金额/整单金额合计）
	private BigDecimal realAmount;// 实际金额：数量*单价*行折扣*整单折扣-行舍入
	private BigDecimal realPrice;// 实际单价：实际金额/数量
	private BigDecimal notaxAmount;// 不含税金额：金额/(1+税率)
	private BigDecimal notaxPrice;// 不含税单价：金额/(1+税率)
	private BigDecimal taxAmount;// 税额：金额-不含税金额

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAmount() {
		if(BigDecimal.ZERO.compareTo(price) >= 0) return BigDecimal.ZERO;
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getLineRoundOff() {
		if(BigDecimal.ZERO.compareTo(price) >= 0) return BigDecimal.ZERO;
		return lineRoundOff;
	}
	
	public void setLineRoundOff(BigDecimal lineRoundOff) {
		this.lineRoundOff = lineRoundOff;
	}
	
	public BigDecimal getRealAmount() {
		if(BigDecimal.ZERO.compareTo(price) >= 0) return BigDecimal.ZERO;
		return realAmount;
	}
	
	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}
	
	public BigDecimal getRealPrice() {
		if(BigDecimal.ZERO.compareTo(price) >= 0) return BigDecimal.ZERO;
		return realPrice;
	}
	
	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}
	
	public BigDecimal getNotaxAmount() {
		if(BigDecimal.ZERO.compareTo(price) >= 0) return BigDecimal.ZERO;
		return notaxAmount;
	}
	
	public void setNotaxAmount(BigDecimal notaxAmount) {
		this.notaxAmount = notaxAmount;
	}
	
	public BigDecimal getNotaxPrice() {
		if(BigDecimal.ZERO.compareTo(price) >= 0) return BigDecimal.ZERO;
		return notaxPrice;
	}
	
	public void setNotaxPrice(BigDecimal notaxPrice) {
		this.notaxPrice = notaxPrice;
	}
	
	public BigDecimal getTaxAmount() {
		if(BigDecimal.ZERO.compareTo(price) >= 0) return BigDecimal.ZERO;
		return taxAmount;
	}
	
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	
}
