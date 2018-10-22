package com.rograndec.feijiayun.chain.business.report.member.vo;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: MemberRankingVO   
 * @Description: 会员排行
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月19日 上午11:05:29
 */
@ApiModel(value = "MemberLivenessTotalVO", description = "会员活跃度合计列表")
public class MemberLivenessTotalVO {
	
	@ApiModelProperty(value = "消费频次合计")
	private BigDecimal saleCountTotal = BigDecimal.ZERO;

	@ApiModelProperty(value = "消费金额合计")
	private BigDecimal saleAmountTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "消费金额合计")
	private List<MemberLivenessVO> list;

	
	
	public BigDecimal getSaleCountTotal() {
		return saleCountTotal;
	}

	public void setSaleCountTotal(BigDecimal saleCountTotal) {
		this.saleCountTotal = saleCountTotal;
	}

	public BigDecimal getSaleAmountTotal() {
		return saleAmountTotal;
	}

	public void setSaleAmountTotal(BigDecimal saleAmountTotal) {
		this.saleAmountTotal = saleAmountTotal;
	}

	public List<MemberLivenessVO> getList() {
		return list;
	}

	public void setList(List<MemberLivenessVO> list) {
		this.list = list;
	}

}
