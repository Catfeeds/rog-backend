package com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class ExcessSaleResultDetailVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月24日 下午1:19:44 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 商品ID
     */
    @ApiModelProperty(value = "订单ID")
    private Long saleId;
	/**
     * 单据（销售／销退单）编码
     */
    @ApiModelProperty(value = "单据（销售／销退单）编码")
    private String saleCode;
    
    @ApiModelProperty(value = "销售数量")
    private BigDecimal saleQuantity = BigDecimal.ZERO;

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public BigDecimal getSaleQuantity() {
		return saleQuantity;
	}

	public void setSaleQuantity(BigDecimal saleQuantity) {
		this.saleQuantity = saleQuantity;
	}
	
	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
