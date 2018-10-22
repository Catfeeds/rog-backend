package com.rograndec.feijiayun.chain.inf.pos.report.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleCodeVO", description = "按单据")
public class SaleCodeVO {
	
	@ApiModelProperty(value = "销售id")
	private Long id;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "销售日期")
	private Date saleDate;
	
	@ApiModelProperty(value = "销售单据")
	private String saleCode;
	
	@ApiModelProperty(value = "类型")
	private String saleMode;
	
	@ApiModelProperty(value = "模式")
	private String saleType;
	
	@ApiModelProperty(value = "款台")
	private String standCode;

    @ApiModelProperty(value = "收款员名称")
    private String payeeName;
    
    @ApiModelProperty(value = "会员卡号")
    private String memberCardCode;
    
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "整单折扣")
    private BigDecimal wholeDiscount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "抹零")
    private BigDecimal wholeDiscountAmount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "应收")
    private BigDecimal realAmountTotal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    @ApiModelProperty(value = "剂量")
    private BigDecimal dose;
    
    @ApiModelProperty(value = "会员名称")
    private String memberName;
    
    @ApiModelProperty(value = "会员当前积分")
    private BigDecimal memberIntegral;
    
    @ApiModelProperty(value = "本次销费积分合计")
    private BigDecimal thisIntegralTotal;
    
    private List<SaleGoodsVO> goodsVO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(String saleMode) {
		this.saleMode = saleMode;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getStandCode() {
		return standCode;
	}

	public void setStandCode(String standCode) {
		this.standCode = standCode;
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

	public List<SaleGoodsVO> getGoodsVO() {
		return goodsVO;
	}

	public void setGoodsVO(List<SaleGoodsVO> goodsVO) {
		this.goodsVO = goodsVO;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getDose() {
		return dose;
	}

	public void setDose(BigDecimal dose) {
		this.dose = dose;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public BigDecimal getMemberIntegral() {
		return memberIntegral;
	}

	public void setMemberIntegral(BigDecimal memberIntegral) {
		this.memberIntegral = memberIntegral;
	}

	public BigDecimal getThisIntegralTotal() {
		return thisIntegralTotal;
	}

	public void setThisIntegralTotal(BigDecimal thisIntegralTotal) {
		this.thisIntegralTotal = thisIntegralTotal;
	}
    
    
    
}
