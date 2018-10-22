package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * 过期商品报表
 * @author kexinhao
 * 2017-09-28
 */
public class GoodsDisplayCheckCountReportVO implements Serializable {
	
	@ApiModelProperty(value = "合格数量")
	private BigDecimal qualifiedTotal;
	
	@ApiModelProperty(value = "不合格数量")
	private BigDecimal unQualifiedTotal;	
	
    List<GoodsDisplayCheckReportVO> goodsDisplayCheckReportVOList;
	public BigDecimal getQualifiedTotal() {
		return qualifiedTotal;
	}

	public void setQualifiedTotal(BigDecimal qualifiedTotal) {
		this.qualifiedTotal = qualifiedTotal;
	}

	public BigDecimal getUnQualifiedTotal() {
		return unQualifiedTotal;
	}

	public void setUnQualifiedTotal(BigDecimal unQualifiedTotal) {
		this.unQualifiedTotal = unQualifiedTotal;
	}

	public List<GoodsDisplayCheckReportVO> getGoodsDisplayCheckReportVOList() {
		return goodsDisplayCheckReportVOList;
	}

	public void setGoodsDisplayCheckReportVOList(List<GoodsDisplayCheckReportVO> goodsDisplayCheckReportVOList) {
		this.goodsDisplayCheckReportVOList = goodsDisplayCheckReportVOList;
	}
	
}   