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
@ApiModel(value = "MemberRankingTotalVO", description = "会员排行列表")
public class MemberRankingTotalVO {
	
	@ApiModelProperty(value = "会员id",hidden=true)
	private Long id;
	
	@ApiModelProperty(value = "消费金额合计")
	private BigDecimal saleAmountTotal = BigDecimal.ZERO;
	@ApiModelProperty(value = "累计数据合计")
	private BigDecimal totalDataTotal = BigDecimal.ZERO;
	@ApiModelProperty(value = "当前数据合计")
	private BigDecimal currentDataTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "会员排行list")
	private List<MemberRankingVO> list;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSaleAmountTotal() {
		return saleAmountTotal;
	}

	public void setSaleAmountTotal(BigDecimal saleAmountTotal) {
		this.saleAmountTotal = saleAmountTotal;
	}

	public BigDecimal getTotalDataTotal() {
		return totalDataTotal;
	}

	public void setTotalDataTotal(BigDecimal totalDataTotal) {
		this.totalDataTotal = totalDataTotal;
	}

	public BigDecimal getCurrentDataTotal() {
		return currentDataTotal;
	}

	public void setCurrentDataTotal(BigDecimal currentDataTotal) {
		this.currentDataTotal = currentDataTotal;
	}

	public List<MemberRankingVO> getList() {
		return list;
	}

	public void setList(List<MemberRankingVO> list) {
		this.list = list;
	}

}
