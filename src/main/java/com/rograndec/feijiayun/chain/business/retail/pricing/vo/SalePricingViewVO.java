package com.rograndec.feijiayun.chain.business.retail.pricing.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: SalePricingVO
 * @Description:  零售管理-划价单 查看操作VO
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 15:56:13
 */
@ApiModel(value = "SalePricingViewVO", description = "零售管理-划价单")
public class SalePricingViewVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;
	
	
	/**
     * 划价单类型（6201）
     */
	@ApiModelProperty(required = true, value = "划价单类型（6201）")
	private Integer orderType;
	
	/**
     * 划价日期
     */
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(required = true, value = "划价日期")
	private String pricingDate;
	
	@ApiModelProperty(required = true, value = "划价人员")
	private String createrName;
	
	/**
     * 划价单号
     */
	@ApiModelProperty(required = true, value = "划价单号")
	private String code;
	
	/**
     * 会员卡号
     */
	@ApiModelProperty(required = false, value = "会员卡号")
	private String memberCardCode;
	
	/**
     * 购药者姓名
     */
	@ApiModelProperty(required = false, value = "购药者姓名")
	private String consumerName;
	
	/**
     * 剂量
     */
	@ApiModelProperty(required = false, value = "剂量")
	private BigDecimal dose;
	
	/**
     * 数量合计
     */
	@ApiModelProperty(required = true, value = "数量合计")
	private BigDecimal quantityTotal;
	
	/**
     * 品种数量
     */
	@ApiModelProperty(required = true, value = "品种数量")
	private Integer varietiesQuantity;
	
	/**
     * 金额合计（整单优惠前的金额合计）
     */
	@ApiModelProperty(required = true, value = "金额合计（整单优惠前的金额合计）")
	private BigDecimal amountTotal;
	
	/**
     * 整单折扣（%）
     */
	@ApiModelProperty(required = true, value = "整单折扣（%）")
	private BigDecimal wholeDiscount;
	
	/**
     * 整单优惠金额
     */
	@ApiModelProperty(required = true, value = "整单优惠金额")
	private BigDecimal wholeDiscountAmount;
	
	/**
     * 实际金额合计
     */
	@ApiModelProperty(required = true, value = "实际金额合计")
	private BigDecimal realAmountTotal;
	
	/**
     * 不含税金额合计
     */
	@ApiModelProperty(required = true, value = "不含税金额合计")
	private BigDecimal notaxRealAmountTotal;
	
	/**
     * 税额合计
     */
	@ApiModelProperty(required = true, value = "税额合计")
	private BigDecimal taxAmountTotal;
	
	/**
     * 利润合计
     */
	@ApiModelProperty(required = true, value = "利润合计")
	private BigDecimal profitTotal;
	
	/**
     * 不含税利润合计
     */
	@ApiModelProperty(required = true, value = "不含税利润合计")
	private BigDecimal notaxProfitTotal;
	
	/**
     * 利润率
     */
	@ApiModelProperty(required = true, value = "利润率")
	private BigDecimal profitRate;
	
	/**
     * 不含税利润率
     */
	@ApiModelProperty(required = true, value = "不含税利润率")
	private BigDecimal notaxProfitRate;
	
	/**
     * 创建人ID
     */
    @ApiModelProperty(value = "划价人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "划价人编码",hidden=true)
    private String createrCode;
	
	// 明细信息
	private List<SalePricingDetailVO> salePricingDetailList;


	public List<SalePricingDetailVO> getSalePricingDetailList() {
		return salePricingDetailList;
	}

	public void setSalePricingDetailList(List<SalePricingDetailVO> salePricingDetailList) {
		this.salePricingDetailList = salePricingDetailList;
	}

	/**
	 * 主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 主键ID
	 */
	public Long getId() {
		return id;
	}
	
	
	/**
	 * 划价日期
	 */
	public void setPricingDate(String pricingDate) {
		this.pricingDate = pricingDate;
	}
	
	/**
	 * 划价日期
	 */
	public String getPricingDate() {
		return pricingDate;
	}
	
	/**
	 * 划价单号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 划价单号
	 */
	public String getCode() {
		return code;
	}
	
	
	/**
	 * 购药者姓名
	 */
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	
	/**
	 * 购药者姓名
	 */
	public String getConsumerName() {
		return consumerName;
	}
	
	/**
	 * 剂量
	 */
	public void setDose(BigDecimal dose) {
		this.dose = dose;
	}
	
	/**
	 * 剂量
	 */
	public BigDecimal getDose() {
		return dose;
	}
	
	
	/**
	 * 金额合计（整单优惠前的金额合计）
	 */
	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}
	
	/**
	 * 金额合计（整单优惠前的金额合计）
	 */
	public BigDecimal getAmountTotal() {
		return amountTotal;
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

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getMemberCardCode() {
		return memberCardCode;
	}

	public void setMemberCardCode(String memberCardCode) {
		this.memberCardCode = memberCardCode;
	}

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public Integer getVarietiesQuantity() {
		return varietiesQuantity;
	}

	public void setVarietiesQuantity(Integer varietiesQuantity) {
		this.varietiesQuantity = varietiesQuantity;
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

	public BigDecimal getProfitTotal() {
		return profitTotal;
	}

	public void setProfitTotal(BigDecimal profitTotal) {
		this.profitTotal = profitTotal;
	}

	public BigDecimal getNotaxProfitTotal() {
		return notaxProfitTotal;
	}

	public void setNotaxProfitTotal(BigDecimal notaxProfitTotal) {
		this.notaxProfitTotal = notaxProfitTotal;
	}

	public BigDecimal getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}

	public BigDecimal getNotaxProfitRate() {
		return notaxProfitRate;
	}

	public void setNotaxProfitRate(BigDecimal notaxProfitRate) {
		this.notaxProfitRate = notaxProfitRate;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public String getCreaterCode() {
		return createrCode;
	}

	public void setCreaterCode(String createrCode) {
		this.createrCode = createrCode;
	}

	

}