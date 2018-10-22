package com.rograndec.feijiayun.chain.business.report.storage.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class StockListResultBranchTotalVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月24日 下午8:41:45 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "库存数量")
    private BigDecimal stockQuantity = BigDecimal.ZERO;
    
	@ApiModelProperty(value = "可用数量")
    private BigDecimal usableQuantity = BigDecimal.ZERO;
    
	private List<StockListResultBranchVO> voList;

	public BigDecimal getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(BigDecimal stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public BigDecimal getUsableQuantity() {
		return usableQuantity;
	}

	public void setUsableQuantity(BigDecimal usableQuantity) {
		this.usableQuantity = usableQuantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<StockListResultBranchVO> getVoList() {
		return voList;
	}

	public void setVoList(List<StockListResultBranchVO> voList) {
		this.voList = voList;
	}
	

}
