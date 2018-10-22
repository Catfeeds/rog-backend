package com.rograndec.feijiayun.chain.business.retail.pricing.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: SalePricingVO
 * @Description:  零售管理-划价单
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 15:56:13
 */
@ApiModel(value = "SalePricingVO", description = "零售管理-划价单")
public class SalePricingVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;

	
	/**
     * 划价日期
     */
	@ApiModelProperty(required = true, value = "划价日期")
	private String pricingDate;
	
	/**
     * 划价单号
     */
	@ApiModelProperty(required = true, value = "划价单号")
	private String code;
	
	/**
     * 创建人名称
     */
    @ApiModelProperty(value = "划价人员")
    private String createrName;

	
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
     * 金额合计（整单优惠前的金额合计）
     */
	@ApiModelProperty(required = true, value = "金额合计（整单优惠前的金额合计）")
	private BigDecimal amountTotal;
	
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
     * 状态（92-待支付,33-已完成,34-已取消）
     */
	@ApiModelProperty(required = true, value = "状态（92-待支付,33-已完成,34-已取消）")
	private Integer status;
	
	@ApiModelProperty(required = true, value = "状态（92-待支付,33-已完成,34-已取消）")
	private String showStatus;
	
	@ApiModelProperty(required = true, value = "销售单号")
	private String saleCode;
	
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
	
	/**
	 * 状态（0-禁用；1-启用）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 状态（0-禁用；1-启用）
	 */
	public Integer getStatus() {
		return status;
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

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	

}