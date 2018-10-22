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

@ApiModel(value = "SaleFlowListDtlResultBranchVO", description = "按日结标识、明细查询销售流水列表（分店）查询结果列表对象")
public class SaleFlowListDtlResultBranchVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月16日 下午3:39:13
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;

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

	@ApiModelProperty(value = "商品编码")
    private String goodsCode;

	@ApiModelProperty(value = "通用名称")
    private String goodsGenericName;

	@ApiModelProperty(value = "剂型名称")
    private String dosageName;

	@ApiModelProperty(value = "商品规格")
    private String goodsSpecification;

	@ApiModelProperty(value = "单位名称")
    private String unitName;

	@ApiModelProperty(value = "生产厂商")
    private String manufacturer;

	@ApiModelProperty(value = "数量")
    private BigDecimal quantity;

	@ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

	@ApiModelProperty(value = "行折扣（%）")
    private BigDecimal lineDiscount;

	@ApiModelProperty(value = "金额（整单优惠前金额）")
    private BigDecimal amount;

	@ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

	@ApiModelProperty(value = "行优惠（整单优惠分摊到行的金额）")
    private BigDecimal lineDiscountAmount;

	@ApiModelProperty(value = "实际单价（实际金额/数量）")
    private BigDecimal realPrice;

	@ApiModelProperty(value = "总额/实际金额")
    private BigDecimal realAmount;

	@ApiModelProperty(value = "税率")
	private BigDecimal taxRate;

	@ApiModelProperty(value = "不含税总额")
    private BigDecimal notaxRealAmount;

	@ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

	@ApiModelProperty(value = "款台")
	private String standCode;

	@ApiModelProperty(value = "班组")
	private String teamName;

	@ApiModelProperty(value = "收款人员")
	private String payeeName;

	@ApiModelProperty(value = "营业员名称")
    private String clerkName;

	@ApiModelProperty(value = "会员卡号")
	private String memberCardCode;

	@ApiModelProperty(value = "批号ID")
	private Long lotId;

	@ApiModelProperty(value = "批号")
	private String lotNumber;

	@ApiModelProperty(value = "生产日期")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date productDate;

	@ApiModelProperty(value = "有效期")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date validDate;

	@ApiModelProperty(value = "用于判断该商品属于总部还是加盟店    0-总部；1-自营店；2-加盟店")
	private Integer chainType = 0;

	@ApiModelProperty(value="用于判断该商品是否属于加盟店 0 - 否  1- 是")
	private Integer franchisedStoreFlag = 0;

	/**
	 * 商品所属企业的id
	 */
	private Long enterpriseId;

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

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsGenericName() {
		return goodsGenericName;
	}

	public void setGoodsGenericName(String goodsGenericName) {
		this.goodsGenericName = goodsGenericName;
	}

	public String getDosageName() {
		return dosageName;
	}

	public void setDosageName(String dosageName) {
		this.dosageName = dosageName;
	}

	public String getGoodsSpecification() {
		return goodsSpecification;
	}

	public void setGoodsSpecification(String goodsSpecification) {
		this.goodsSpecification = goodsSpecification;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getLineDiscount() {
		return lineDiscount;
	}

	public void setLineDiscount(BigDecimal lineDiscount) {
		this.lineDiscount = lineDiscount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getWholeDiscount() {
		return wholeDiscount;
	}

	public void setWholeDiscount(BigDecimal wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}

	public BigDecimal getLineDiscountAmount() {
		return lineDiscountAmount;
	}

	public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
		this.lineDiscountAmount = lineDiscountAmount;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
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

	public String getClerkName() {
		return clerkName;
	}

	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}

	public String getMemberCardCode() {
		return memberCardCode;
	}

	public void setMemberCardCode(String memberCardCode) {
		this.memberCardCode = memberCardCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getNotaxRealAmount() {
		return notaxRealAmount;
	}

	public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
		this.notaxRealAmount = notaxRealAmount;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public Long getLotId() {
		return lotId;
	}

	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getFranchisedStoreFlag() {
		return franchisedStoreFlag;
	}

	public void setFranchisedStoreFlag(Integer franchisedStoreFlag) {
		this.franchisedStoreFlag = franchisedStoreFlag;
	}

	public void receveList(
			List<SaleFlowListDtlResultBranchVO> resultBranchVOList) {
		if(resultBranchVOList != null){
			for (SaleFlowListDtlResultBranchVO saleFlowListDtlResultBranchVO : resultBranchVOList) {
				if(saleFlowListDtlResultBranchVO.getSaleType() == 1){
					saleFlowListDtlResultBranchVO.setAmount(saleFlowListDtlResultBranchVO.getAmount().multiply(new BigDecimal(-1)));
					saleFlowListDtlResultBranchVO.setLineDiscountAmount(saleFlowListDtlResultBranchVO.getLineDiscountAmount().multiply(new BigDecimal(-1)));
					saleFlowListDtlResultBranchVO.setRealAmount(saleFlowListDtlResultBranchVO.getRealAmount().multiply(new BigDecimal(-1)));
					saleFlowListDtlResultBranchVO.setNotaxRealAmount(saleFlowListDtlResultBranchVO.getNotaxRealAmount().multiply(new BigDecimal(-1)));
					saleFlowListDtlResultBranchVO.setTaxAmount(saleFlowListDtlResultBranchVO.getTaxAmount().multiply(new BigDecimal(-1)));
				}
				saleFlowListDtlResultBranchVO.setSaleTypeName(SaleGenre.getName(saleFlowListDtlResultBranchVO.getSaleType()));
				saleFlowListDtlResultBranchVO.setSaleModeName(SaleMode.getName(saleFlowListDtlResultBranchVO.getSaleMode()));
			}
		}
	}

}
