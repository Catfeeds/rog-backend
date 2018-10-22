package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * 过期商品报表数量统计
 * @author kexinhao
 * 2017-09-28
 */
public class GoodsExpireCountReportVO implements Serializable {
	
	
	@ApiModelProperty(value = "过期数量统计")
	private BigDecimal quantityTotal;	
	
    List<GoodsExpireReportVO> goodsExpireReportVOList;

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public List<GoodsExpireReportVO> getGoodsExpireReportVOList() {
		return goodsExpireReportVOList;
	}

	public void setGoodsExpireReportVOList(List<GoodsExpireReportVO> goodsExpireReportVOList) {
		this.goodsExpireReportVOList = goodsExpireReportVOList;
	}

}   