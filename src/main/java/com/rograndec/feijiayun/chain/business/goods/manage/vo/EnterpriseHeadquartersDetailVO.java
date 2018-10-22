package com.rograndec.feijiayun.chain.business.goods.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel(value = "EnterpriseHeadquartersDetailVO", description = "商品管理-总部按组织机构-修改明细分页展示基本对象")
public class EnterpriseHeadquartersDetailVO implements Serializable{

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
     * 商品编码
     */
	@ApiModelProperty(required = false, value = "商品编码")
    private String goodsCode;
	
	/**
     * 商品名称
     */
	@ApiModelProperty(required = false, value = "商品通用名称")
    private String genericName;
	
	/**
	 * 药店类型（0-总部；1-自营店；2-加盟店）
	 */
	@ApiModelProperty(required = false, value = "药店类型")
	private Integer chainType;
	
	/**
	 * 药店类型名称
	 */
	@ApiModelProperty(required = false, value = "药店类型名称")
	private String chainTypeName;
	
	/**
	 * 药店ID
	 */
	@ApiModelProperty(required = true, value = "药店ID")
	private Integer enterpriseId;
	
	/**
	 * 组织机构
	 */
	@ApiModelProperty(required = false, value = "组织机构/药店名称")
	private String enterpriseName;
	
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
	 * 库存数量
	 */
	@ApiModelProperty(required = false, value = "库存数量")
	private BigDecimal stockQuantity;
	
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
	 * 配货单价
	 */
	@ApiModelProperty(required = false, value = "配货单价")
	private BigDecimal distrPrice;
	
	/**
	 * 配货税率
	 */
	@ApiModelProperty(required = false, value = "配货税率")
	private BigDecimal distrTaxRate;
	
	/**
	 * 不含税配货单价
	 */
	@ApiModelProperty(required = false, value = "不含税配货单价")
	private BigDecimal notaxDistrPrice;
	
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

	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public String getChainTypeName() {
		return chainTypeName;
	}

	public void setChainTypeName(String chainTypeName) {
		this.chainTypeName = chainTypeName;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
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

	public BigDecimal getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(BigDecimal stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public BigDecimal getInventoryUp() {
		return inventoryUp;
	}

	public void setInventoryUp(BigDecimal inventoryUp) {
		this.inventoryUp = inventoryUp;
	}

	public BigDecimal getInventoryDown() {
		return inventoryDown;
	}

	public void setInventoryDown(BigDecimal inventoryDown) {
		this.inventoryDown = inventoryDown;
	}

	public BigDecimal getDistrPrice() {
		return distrPrice;
	}

	public void setDistrPrice(BigDecimal distrPrice) {
		this.distrPrice = distrPrice;
	}

	public BigDecimal getDistrTaxRate() {
		return distrTaxRate;
	}

	public void setDistrTaxRate(BigDecimal distrTaxRate) {
		this.distrTaxRate = distrTaxRate;
	}

	public BigDecimal getNotaxDistrPrice() {
		return notaxDistrPrice;
	}

	public void setNotaxDistrPrice(BigDecimal notaxDistrPrice) {
		this.notaxDistrPrice = notaxDistrPrice;
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

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public BigDecimal getSafetyStock() {
		return safetyStock;
	}

	public void setSafetyStock(BigDecimal safetyStock) {
		this.safetyStock = safetyStock;
	}

	public Long getSaleTaxRateId() {
		return saleTaxRateId;
	}

	public void setSaleTaxRateId(Long saleTaxRateId) {
		this.saleTaxRateId = saleTaxRateId;
	}

	@Override
	public String toString() {
		return "GoodsHeadquartersDetailVO [chainType=" + chainType
				+ ", chainTypeName=" + chainTypeName + ", enterpriseId="
				+ enterpriseId + ", enterpriseName=" + enterpriseName
				+ ", defaultShelfId=" + defaultShelfId + ", defaultShelfName="
				+ defaultShelfName + ", stockQuantity=" + stockQuantity
				+ ", inventoryUp=" + inventoryUp + ", inventoryDown="
				+ inventoryDown + ", distrPrice=" + distrPrice
				+ ", distrTaxRate=" + distrTaxRate + ", notaxDistrPrice="
				+ notaxDistrPrice + ", retailPrice=" + retailPrice
				+ ", memberPrice=" + memberPrice + ", saleTaxRate="
				+ saleTaxRate + ", notaxRetailPrice=" + notaxRetailPrice
				+ ", notaxMemberPrice=" + notaxMemberPrice + "]";
	}

	
}
