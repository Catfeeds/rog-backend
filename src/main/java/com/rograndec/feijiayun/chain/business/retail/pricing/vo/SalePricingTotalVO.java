package com.rograndec.feijiayun.chain.business.retail.pricing.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: SalePricingVO
 * @Description:  零售管理-划价单
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 15:56:13
 */
@ApiModel(value = "SalePricingTotalVO", description = "零售管理-划价单")
public class SalePricingTotalVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;
	
	private List<SalePricingVO> pricingList;

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	public BigDecimal getNotaxRealAmountTotal() {
		return notaxRealAmountTotal;
	}

	public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
		this.notaxRealAmountTotal = notaxRealAmountTotal;
	}

	public BigDecimal getTaxAmountTotal() {
		return taxAmountTotal;
	}

	public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
		this.taxAmountTotal = taxAmountTotal;
	}

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public List<SalePricingVO> getPricingList() {
		return pricingList;
	}

	public void setPricingList(List<SalePricingVO> pricingList) {
		this.pricingList = pricingList;
	}
	
	

}