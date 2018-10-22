package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * 药品养护合格与不合格商品数量统计
 * @author kexinhao
 * 2017-09-28
 */
public class GoodsMaintanceCountReportVO implements Serializable {
	
	@ApiModelProperty(value = "合格数量统计")
	private BigDecimal qualifiedTotal;
	
	@ApiModelProperty(value = "不合格数量统计")
	private BigDecimal unQualifiedTotal;	
	
    List<GoodsMaintanceReportVO> goodsMaintanceReportVOList;
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

	public List<GoodsMaintanceReportVO> getGoodsMaintanceReportVOList() {
		return goodsMaintanceReportVOList;
	}

	public void setGoodsMaintanceReportVOList(List<GoodsMaintanceReportVO> goodsMaintanceReportVOList) {
		this.goodsMaintanceReportVOList = goodsMaintanceReportVOList;
	}


	
}   