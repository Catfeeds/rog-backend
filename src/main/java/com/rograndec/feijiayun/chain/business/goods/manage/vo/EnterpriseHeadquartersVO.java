package com.rograndec.feijiayun.chain.business.goods.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel(value = "EnterpriseHeadquartersVO", description = "商品管理-总部按组织机构分页展示基本对象")
public class EnterpriseHeadquartersVO implements Serializable{

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
	 * 药店类型（0-总部；1-自营店；2-加盟店）
	 */
	@ApiModelProperty(required = true, value = "药店类型")
	private Integer chainType;
	
	/**
	 * 药店类型名称
	 */
	@ApiModelProperty(required = true, value = "药店类型名称")
	private String chainTypeName;
	
	/**
	 * 药店ID
	 */
	@ApiModelProperty(required = true, value = "药店ID")
	private Long enterpriseId;
	
	/**
	 * 药店编码
	 */
	@ApiModelProperty(required = true, value = "组织机构编码")
	private String enterpriseCode;
	
	/**
	 * 组织机构
	 */
	@ApiModelProperty(required = true, value = "组织机构名称")
	private String enterpriseName;
	
	/**
     * 品种类别
     */
	@ApiModelProperty(required = true, value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private Integer businessVariety;
	
	/**
     * 品种类别名称
     */
	@ApiModelProperty(required = true, value = "品种类别名称")
    private String businessVarietyName;
	
	/**
     * 商品ID
     */
	@ApiModelProperty(required = true, value = "商品ID")
    private Long goodsId;
	
	/**
     * 商品编码
     */
	@ApiModelProperty(required = true, value = "商品编码")
    private String goodsCode;
	
	/**
     * 通用名称
     */
	@ApiModelProperty(required = false, value = "通用名称")
    private String genericName;
	
	/**
     * 剂型ID
     */
	@ApiModelProperty(required = false, value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
	@ApiModelProperty(required = false, value = "剂型名称")
    private String dosageName;
	
	/**
     * 规格
     */
	@ApiModelProperty(required = false, value = "规格")
    private String specification;
	
	/**
     * 生产厂商ID
     */
	@ApiModelProperty(required = true, value = "生产厂商ID")
    private Long manufacturerId;
	
	/**
     * 生产厂商名称
     */
	@ApiModelProperty(required = true, value = "生产厂商名称")
    private String manufacturer;
	
	/**
	 * 单位ID
	 */
	@ApiModelProperty(required = true, value = "单位ID")
	private Long unitId;
	
	/**
	 * 单位名称
	 */
	@ApiModelProperty(required = true, value = "单位名称")
	private String unitName;
	
	@ApiModelProperty(required = true, value = "产地")
	private String goodsPlace;
	
	/**
     * 默认货位ID
     */
	@ApiModelProperty(required = true, value = "默认货位ID")
	private Long defaultShelfId;
	
	/**
	 * 默认货位名称
	 */
	@ApiModelProperty(required = true, value = "默认货位名称")
	private String defaultShelfName;
	
	/**
	 * 库存数量
	 */
	@ApiModelProperty(required = true, value = "库存数量")
	private BigDecimal stockQuantity;
	
	/**
	 * 安全库存ID
	 */
	@ApiModelProperty(required = false, value = "安全库存ID")
	private Long safetyStockId;
	
	/**
	 * 价格清单明细ID
	 */
	@ApiModelProperty(required = false, value = "价格清单明细ID")
	private Long priceOrderDtlId;
	
	/**
	 * 库存上限
	 */
	@ApiModelProperty(required = true, value = "库存上限")
	private BigDecimal inventoryUp;
	
	/**
	 * 安全库存
	 */
	@ApiModelProperty(required = true, value = "安全库存")
	private BigDecimal safetyStock;

	/**
	 * 库存下限
	 */
	@ApiModelProperty(required = true, value = "库存下限")
	private BigDecimal inventoryDown;
	
	 /**
     * 加盟店可编辑标识（true-可编辑 false-不可编辑）
     */
    @ApiModelProperty(required = false, value = "加盟店可编辑标识 true-可编辑 false-不可编辑")
    private boolean updateFlag;
	/**
	 * 配货单价
	 *//*
	@ApiModelProperty(required = true, value = "配货单价,与张红运确认,列表暂不显示该字段,保存时暂不传")
	private BigDecimal distrPrice;
	
	*//**
	 * 配货税率
	 *//*
	@ApiModelProperty(required = true, value = "配货税率,与张红运确认,列表暂不显示该字段,保存时暂不传")
	private BigDecimal distrTaxRate;
	
	*//**
	 * 不含税配货单价
	 *//*
	@ApiModelProperty(required = true, value = "不含税配货单价,与张红运确认,列表暂不显示该字段,保存时暂不传")
	private BigDecimal notaxDistrPrice;*/
	
	/**
	 * 零售单价
	 */
	@ApiModelProperty(required = true, value = "零售单价")
	private BigDecimal retailPrice;
	
	/**
	 * 会员单价
	 */
	@ApiModelProperty(required = true, value = "会员单价")
	private BigDecimal memberPrice;
	
	@ApiModelProperty(required = true, value = "销项税ID")
	private Long saleTaxRateId;
	/**
	 * 销项税
	 */
	@ApiModelProperty(required = true, value = "销项税")
	private BigDecimal saleTaxRate;
	
	/**
	 * 不含税零售单价
	 */
	@ApiModelProperty(required = true, value = "不含税零售单价")
	private BigDecimal notaxRetailPrice;
	
	/**
	 * 不含税会员单价
	 */
	@ApiModelProperty(required = true, value = "不含税会员单价")
	private BigDecimal notaxMemberPrice;
	
	/**
	 * 经营状态
	 */
	@ApiModelProperty(required = false, value = "经营状态（0-待上架；1-已上架；2-出售中；3-已售罄；4-已下架）")
	private Integer manageStatus;
	
	/**
	 * 经营状态名称
	 */
	@ApiModelProperty(required = false, value = "经营状态名称")
	private String manageStatusName;
	

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

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public Integer getBusinessVariety() {
		return businessVariety;
	}

	public void setBusinessVariety(Integer businessVariety) {
		this.businessVariety = businessVariety;
	}

	public String getBusinessVarietyName() {
		return businessVarietyName;
	}

	public void setBusinessVarietyName(String businessVarietyName) {
		this.businessVarietyName = businessVarietyName;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public Long getDosageId() {
		return dosageId;
	}

	public void setDosageId(Long dosageId) {
		this.dosageId = dosageId;
	}

	public String getDosageName() {
		return dosageName;
	}

	public void setDosageName(String dosageName) {
		this.dosageName = dosageName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
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

	/*public BigDecimal getDistrPrice() {
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
	}*/

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

	public Integer getManageStatus() {
		return manageStatus;
	}

	public void setManageStatus(Integer manageStatus) {
		this.manageStatus = manageStatus;
	}

	public String getManageStatusName() {
		return manageStatusName;
	}

	public void setManageStatusName(String manageStatusName) {
		this.manageStatusName = manageStatusName;
	}
	
	public Long getSafetyStockId() {
		return safetyStockId;
	}

	public void setSafetyStockId(Long safetyStockId) {
		this.safetyStockId = safetyStockId;
	}

	public Long getPriceOrderDtlId() {
		return priceOrderDtlId;
	}

	public void setPriceOrderDtlId(Long priceOrderDtlId) {
		this.priceOrderDtlId = priceOrderDtlId;
	}
	

	public String getGoodsPlace() {
		return goodsPlace;
	}

	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
	}
	
	public Long getSaleTaxRateId() {
		return saleTaxRateId;
	}

	public void setSaleTaxRateId(Long saleTaxRateId) {
		this.saleTaxRateId = saleTaxRateId;
	}

	public boolean getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	@Override
	public String toString() {
		return "GoodsHeadquartersVO [goodsId=" + goodsId + ", goodsCode="
				+ goodsCode + ", businessVariety=" + businessVariety
				+ ", businessVarietyName=" + businessVarietyName
				+ ", genericName=" + genericName + ", dosageId=" + dosageId
				+ ", dosageName=" + dosageName + ", specification="
				+ specification + ", manufacturerId=" + manufacturerId
				+ ", manufacturer=" + manufacturer + ", unitId=" + unitId
				+ ", unitName=" + unitName + "]";
	}


}
