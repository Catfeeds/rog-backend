package com.rograndec.feijiayun.chain.business.report.storage.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class StockWarnLackGoodsTotalVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月25日 下午1:08:03 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;

	
	@ApiModelProperty(value = "库存数量")
    private BigDecimal stockQuantity = BigDecimal.ZERO;
    
	@ApiModelProperty(value = "可用数量")
    private BigDecimal usableQuantity = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "缺货数量")
    private BigDecimal lackQuantity = BigDecimal.ZERO;
	
	private List<StockWarnLackGoodsVO> voList;

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

	public List<StockWarnLackGoodsVO> getVoList() {
		return voList;
	}

	public void setVoList(List<StockWarnLackGoodsVO> voList) {
		this.voList = voList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getLackQuantity() {
		return lackQuantity;
	}

	public void setLackQuantity(BigDecimal lackQuantity) {
		this.lackQuantity = lackQuantity;
	}

}
