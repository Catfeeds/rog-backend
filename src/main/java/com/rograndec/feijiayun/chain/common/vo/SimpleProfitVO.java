package com.rograndec.feijiayun.chain.common.vo;

import java.math.BigDecimal;

public class SimpleProfitVO {

	 /**
     * 利润金额
     */
    private BigDecimal profit;

    /**
     * 不含税利润金额
     */
    private BigDecimal notaxProfit;

    /**
     * 利润率
     */
    private BigDecimal profitRate;

    /**
     * 不含税利润率
     */
    private BigDecimal notaxProfitRate;

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getNotaxProfit() {
		return notaxProfit;
	}

	public void setNotaxProfit(BigDecimal notaxProfit) {
		this.notaxProfit = notaxProfit;
	}

	public BigDecimal getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}

	public BigDecimal getNotaxProfitRate() {
		return notaxProfitRate;
	}

	public void setNotaxProfitRate(BigDecimal notaxProfitRate) {
		this.notaxProfitRate = notaxProfitRate;
	}
    
	public static SimpleProfitVO build(BigDecimal profit, BigDecimal profitRate, BigDecimal notaxProfit, BigDecimal notaxProfitRate){
		SimpleProfitVO vo = new SimpleProfitVO();
		vo.setProfit(profit);
		vo.setProfitRate(profitRate);
		vo.setNotaxProfit(notaxProfit);
		vo.setNotaxProfitRate(notaxProfitRate);
		return vo;
	} 
	
}
