package com.rograndec.feijiayun.chain.business.report.quality.retail.vo;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleOutORreturnTotalVO", description = "销售出库/退货合计信息")
public class SaleOutORreturnTotalVO{

	

    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;
    @ApiModelProperty(value = "金额（整单优惠前金额）合计")
    private BigDecimal amountTotal;
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;
    @ApiModelProperty(value = "不含税实际金额合计")
    private BigDecimal notaxRealAmountTotal;
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;
    
    @ApiModelProperty(value = "售出库/退货list")
    private List<SaleOutORreturnVO> list;

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	public BigDecimal getRealAmountTotal() {
		return realAmountTotal;
	}

	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
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

	public List<SaleOutORreturnVO> getList() {
		return list;
	}

	public void setList(List<SaleOutORreturnVO> list) {
		this.list = list;
	}
    
    
}
