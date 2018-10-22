package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * 不合格商品数量统计
 * @author kexinhao
 * 2017-09-28
 */
public class GoodsUnqualifiedCountReportVO implements Serializable {
	
	
	@ApiModelProperty(value = "不合格商品数量统计")
	private BigDecimal quantityTotal;	
	
    List<GoodsUnqualifiedReportVO> goodsUnqualifiedReportVOList;

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public List<GoodsUnqualifiedReportVO> getGoodsUnqualifiedReportVOList() {
		return goodsUnqualifiedReportVOList;
	}

	public void setGoodsUnqualifiedReportVOList(List<GoodsUnqualifiedReportVO> goodsUnqualifiedReportVOList) {
		this.goodsUnqualifiedReportVOList = goodsUnqualifiedReportVOList;
	}


}   