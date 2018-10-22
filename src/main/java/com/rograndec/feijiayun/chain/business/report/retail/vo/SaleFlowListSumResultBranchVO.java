package com.rograndec.feijiayun.chain.business.report.retail.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.common.constant.SaleGenre;
import com.rograndec.feijiayun.chain.common.constant.SaleMode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleFlowListSumResultBranchVO", description = "按日结标识、汇总查询销售流水列表（分店）查询结果列表对象")
public class SaleFlowListSumResultBranchVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月16日 下午3:39:13 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "销售单ID")
	private Long id;
	
	@ApiModelProperty(value = "门店编码")
	private String storeCode;

	@ApiModelProperty(value = "门店名称")
	private String storeName;
	
	@ApiModelProperty(value = "日结日期")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dailyDate;
	
	@ApiModelProperty(value = "销售时间")
	private Date saleDate;
	
	@ApiModelProperty(value = "销售单号")
	private String saleCode;
	
	private Integer saleType;
	
	@ApiModelProperty(value = "销售类型")
	private String saleTypeName;

	private Integer saleMode;
	
	@ApiModelProperty(value = "销售模式")
	private String saleModeName;
	
	@ApiModelProperty(value = "款台")
	private String standCode;
	
	@ApiModelProperty(value = "班组")
	private String teamName;
	
	@ApiModelProperty(value = "收款人员")
	private String payeeName;
	
	@ApiModelProperty(value = "会员卡号")
	private String memberCardCode;
	
	@ApiModelProperty(value = "总计/金额合计（整单优惠前的金额合计）")
	private BigDecimal amountTotal;
	
	@ApiModelProperty(value = "整单折扣")
	private BigDecimal wholeDiscount;
	
	@ApiModelProperty(value = "抹零合计/整单优惠金额合计")
	private BigDecimal wholeDiscountAmount;
	
	@ApiModelProperty(value = "总额/实际金额合计")
	private BigDecimal realAmountTotal;
	
	@ApiModelProperty(value = "不含税金额合计")
	private BigDecimal notaxRealAmountTotal;
	
	@ApiModelProperty(value = "税额合计")
	private BigDecimal taxAmountTotal;

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Date getDailyDate() {
		return dailyDate;
	}

	public void setDailyDate(Date dailyDate) {
		this.dailyDate = dailyDate;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public Integer getSaleType() {
		return saleType;
	}

	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	public String getSaleTypeName() {
		return saleTypeName;
	}

	public void setSaleTypeName(String saleTypeName) {
		this.saleTypeName = saleTypeName;
	}

	public Integer getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(Integer saleMode) {
		this.saleMode = saleMode;
	}

	public String getSaleModeName() {
		return saleModeName;
	}

	public void setSaleModeName(String saleModeName) {
		this.saleModeName = saleModeName;
	}

	public String getStandCode() {
		return standCode;
	}

	public void setStandCode(String standCode) {
		this.standCode = standCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getMemberCardCode() {
		return memberCardCode;
	}

	public void setMemberCardCode(String memberCardCode) {
		this.memberCardCode = memberCardCode;
	}

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	public BigDecimal getWholeDiscount() {
		return wholeDiscount;
	}

	public void setWholeDiscount(BigDecimal wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}

	public BigDecimal getWholeDiscountAmount() {
		return wholeDiscountAmount;
	}

	public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
		this.wholeDiscountAmount = wholeDiscountAmount;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void receveList(List<SaleFlowListSumResultBranchVO> resultBranchVOList){
		if(resultBranchVOList != null){
			for (SaleFlowListSumResultBranchVO saleFlowListSumResultBranchVO : resultBranchVOList) {
				if(saleFlowListSumResultBranchVO.getSaleType() == 1){
					saleFlowListSumResultBranchVO.setAmountTotal(saleFlowListSumResultBranchVO.getAmountTotal().multiply(new BigDecimal(-1)));
					saleFlowListSumResultBranchVO.setWholeDiscountAmount(saleFlowListSumResultBranchVO.getWholeDiscountAmount().multiply(new BigDecimal(-1)));
					saleFlowListSumResultBranchVO.setRealAmountTotal(saleFlowListSumResultBranchVO.getRealAmountTotal().multiply(new BigDecimal(-1)));
					saleFlowListSumResultBranchVO.setNotaxRealAmountTotal(saleFlowListSumResultBranchVO.getNotaxRealAmountTotal().multiply(new BigDecimal(-1)));
					saleFlowListSumResultBranchVO.setTaxAmountTotal(saleFlowListSumResultBranchVO.getTaxAmountTotal().multiply(new BigDecimal(-1)));
				}
				saleFlowListSumResultBranchVO.setSaleTypeName(SaleGenre.getName(saleFlowListSumResultBranchVO.getSaleType()));
				saleFlowListSumResultBranchVO.setSaleModeName(SaleMode.getName(saleFlowListSumResultBranchVO.getSaleMode()));
			}
		}
	}
	
}
