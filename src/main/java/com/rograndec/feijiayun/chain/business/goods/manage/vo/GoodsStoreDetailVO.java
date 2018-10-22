package com.rograndec.feijiayun.chain.business.goods.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel(value = "GoodsStoreDetailVO", description = "商品管理-分店-修改明细基本对象")
public class GoodsStoreDetailVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年8月28日 下午3:43:32 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * ID
     */
	@ApiModelProperty(required = true, value = "商品管理ID")
    private Long id;
	
	/**
     * 商品ID
     */
	@ApiModelProperty(required = true, value = "商品ID")
    private Long goodsId;
	
	/**
     * 默认货位ID
     */
	@ApiModelProperty(required = false, value = "默认货位ID")
	private Long defaultShelfId;
	
	/**
	 * 默认货位名称
	 */
	@ApiModelProperty(required = false, value = "默认货位名称")
	private String defaultShelfName;
	
	/**
	 * 库存上限
	 */
	@ApiModelProperty(required = false, value = "库存上限")
	private BigDecimal inventoryUp;
	
	/**
	 * 安全库存
	 */
	@ApiModelProperty(required = false, value = "安全库存")
	private BigDecimal safetyStock;
	
	/**
	 * 库存下限
	 */
	@ApiModelProperty(required = false, value = "库存下限")
	private BigDecimal inventoryDown;
	
	/**
	 * 零售单价
	 */
	@ApiModelProperty(required = false, value = "零售单价")
	private BigDecimal retailPrice;
	
	/**
	 * 会员单价
	 */
	@ApiModelProperty(required = false, value = "会员单价")
	private BigDecimal memberPrice;
	
	@ApiModelProperty(required = true, value = "销项税ID")
	private Long saleTaxRateId;
	/**
	 * 销项税
	 */
	@ApiModelProperty(required = false, value = "销项税")
	private BigDecimal saleTaxRate;
	
	/**
	 * 不含税零售单价
	 */
	@ApiModelProperty(required = false, value = "不含税零售单价")
	private BigDecimal notaxRetailPrice;
	
	/**
	 * 不含税会员单价
	 */
	@ApiModelProperty(required = false, value = "不含税会员单价")
	private BigDecimal notaxMemberPrice;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getDefaultShelfId() {
		return defaultShelfId;
	}

	public void setDefaultShelfId(Long defaultShelfId) {
		this.defaultShelfId = defaultShelfId;
	}

	public String getDefaultShelfName() {
		return defaultShelfName;
	}

	public void setDefaultShelfName(String defaultShelfName) {
		this.defaultShelfName = defaultShelfName;
	}

	public BigDecimal getInventoryUp() {
		return inventoryUp;
	}

	public void setInventoryUp(BigDecimal inventoryUp) {
		this.inventoryUp = inventoryUp;
	}

	public BigDecimal getSafetyStock() {
		return safetyStock;
	}

	public void setSafetyStock(BigDecimal safetyStock) {
		this.safetyStock = safetyStock;
	}

	public BigDecimal getInventoryDown() {
		return inventoryDown;
	}

	public void setInventoryDown(BigDecimal inventoryDown) {
		this.inventoryDown = inventoryDown;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public BigDecimal getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(BigDecimal memberPrice) {
		this.memberPrice = memberPrice;
	}

	public BigDecimal getSaleTaxRate() {
		return saleTaxRate;
	}

	public void setSaleTaxRate(BigDecimal saleTaxRate) {
		this.saleTaxRate = saleTaxRate;
	}

	public BigDecimal getNotaxRetailPrice() {
		return notaxRetailPrice;
	}

	public void setNotaxRetailPrice(BigDecimal notaxRetailPrice) {
		this.notaxRetailPrice = notaxRetailPrice;
	}

	public BigDecimal getNotaxMemberPrice() {
		return notaxMemberPrice;
	}

	public void setNotaxMemberPrice(BigDecimal notaxMemberPrice) {
		this.notaxMemberPrice = notaxMemberPrice;
	}
	
	public Long getSaleTaxRateId() {
		return saleTaxRateId;
	}

	public void setSaleTaxRateId(Long saleTaxRateId) {
		this.saleTaxRateId = saleTaxRateId;
	}

	@Override
	public String toString() {
		return "GoodsStoreDetailVO [goodsId=" + goodsId + ", defaultShelfId=" + defaultShelfId
				+ ", defaultShelfName=" + defaultShelfName + ", inventoryUp="
				+ inventoryUp + ", safetyStock=" + safetyStock
				+ ", inventoryDown=" + inventoryDown + ", retailPrice="
				+ retailPrice + ", memberPrice=" + memberPrice
				+ ", saleTaxRate=" + saleTaxRate + ", notaxRetailPrice="
				+ notaxRetailPrice + ", notaxMemberPrice=" + notaxMemberPrice
				+ "]";
	}
	

}
