package com.rograndec.feijiayun.chain.business.purchase.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class GoodsOrderVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "商品ID")
	private Long id;
	@ApiModelProperty(value = "商品编码")
	private String code;
	@ApiModelProperty(value = "通用名称")
	private String genericName;
	@ApiModelProperty(value = "商品名称")
	private String name;
	@ApiModelProperty(value = "剂型名称")
	private String dosageName;
	@ApiModelProperty(value = "规格")
	private String specification;
	@ApiModelProperty(value="单位ID")
    private Long unitId;
    @ApiModelProperty(value="单位名称")
    private String unitName;
	@ApiModelProperty(value = "生产厂商ID")
    private String manufacturerId;
	@ApiModelProperty(value = "生产厂商名称")
	private String manufacturer;
	@ApiModelProperty(value = "单价")
	private BigDecimal unitPrice;
	@ApiModelProperty(value = "税率")
	private String purchaseTaxRate;
	@ApiModelProperty(value = "税率ID")
	private String purchaseTaxRateID;
    @ApiModelProperty(value="零售基价")
    private BigDecimal retailPrice;
    @ApiModelProperty(value="会员基价")
    private BigDecimal memberPrice;
    @ApiModelProperty(value="配货基价")
    private BigDecimal distrPrice;
    @ApiModelProperty(value="产地")
    private String place;
    
    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value="特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private Integer specialDrug;
    
    /**
     * 默认货位ID
     */
    private Long defaultShelfId;

    /**
     * 默认货位名称
     */
    private String defaultShelfName;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGenericName() {
		return genericName;
	}
	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getPurchaseTaxRate() {
		return purchaseTaxRate;
	}
	public void setPurchaseTaxRate(String purchaseTaxRate) {
		this.purchaseTaxRate = purchaseTaxRate;
	}
	public String getPurchaseTaxRateID() {
		return purchaseTaxRateID;
	}
	public void setPurchaseTaxRateID(String purchaseTaxRateID) {
		this.purchaseTaxRateID = purchaseTaxRateID;
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
	public BigDecimal getDistrPrice() {
		return distrPrice;
	}
	public void setDistrPrice(BigDecimal distrPrice) {
		this.distrPrice = distrPrice;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
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
	public Integer getSpecialDrug() {
		return specialDrug;
	}
	public void setSpecialDrug(Integer specialDrug) {
		this.specialDrug = specialDrug;
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

}
