package com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ExcessSaleResultTotalVO implements Serializable{
	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月16日 下午3:39:13 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value = "销售数量")
    private BigDecimal saleQuantity = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "库存数量")
    private BigDecimal stockQuantity = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "可用数量")
    private BigDecimal usableQuantity = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "超量数量")
    private BigDecimal excessSaleQuantity = BigDecimal.ZERO;
    
    private List<ExcessSaleResultVO> resultList;

	public BigDecimal getSaleQuantity() {
		return saleQuantity;
	}

	public void setSaleQuantity(BigDecimal saleQuantity) {
		this.saleQuantity = saleQuantity;
	}

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

	public BigDecimal getExcessSaleQuantity() {
		return excessSaleQuantity;
	}

	public void setExcessSaleQuantity(BigDecimal excessSaleQuantity) {
		this.excessSaleQuantity = excessSaleQuantity;
	}

	public List<ExcessSaleResultVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<ExcessSaleResultVO> resultList) {
		this.resultList = resultList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
