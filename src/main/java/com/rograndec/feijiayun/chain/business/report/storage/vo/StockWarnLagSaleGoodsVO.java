package com.rograndec.feijiayun.chain.business.report.storage.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StockWarnLagSaleGoodsVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月25日 下午1:08:03 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;

	
	@ApiModelProperty(value = "门店编码")
	private String storeCode;
	
	@ApiModelProperty(value = "门店名称")
	private String storeName;
	
	@ApiModelProperty(value = "商品编码")
    private String code;
	
	@ApiModelProperty(value = "通用名称")
    private String genericName;
	
	@ApiModelProperty(value = "剂型")
	private String dosageName;

	@ApiModelProperty(value = "规格")
    private String specification;
    
	@ApiModelProperty(value = "生产厂商")
    private String manufacturer;
    
	@ApiModelProperty(value = "单位")
    private String unitName;
	
	@ApiModelProperty(value = "产地")
    private String place;
	
	@ApiModelProperty(value = "库存数量")
    private BigDecimal stockQuantity = BigDecimal.ZERO;
    
	@ApiModelProperty(value = "可用数量")
    private BigDecimal usableQuantity = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "上次销售日期")
	@JsonFormat(pattern="yyyy-MM-dd")
    private Date lastSaleDate;
	
	@ApiModelProperty(value = "滞销周期")
	private Integer lagSaleCycle;
	
	@ApiModelProperty(value = "滞销天数")
	private Integer lagSaleDays;
	
	@ApiModelProperty(value = "品种类别")
	private Integer businessVariety;
	
	@ApiModelProperty(value = "品种类别名称")
	private String businessVarietyName;
	
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public BigDecimal getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(BigDecimal stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public BigDecimal getUsableQuantity() {
		return usableQuantity;
	}

	public void setUsableQuantity(BigDecimal usableQuantity) {
		this.usableQuantity = usableQuantity;
	}

	public Date getLastSaleDate() {
		return lastSaleDate;
	}

	public void setLastSaleDate(Date lastSaleDate) {
		this.lastSaleDate = lastSaleDate;
	}

	public Integer getLagSaleCycle() {
		return lagSaleCycle;
	}

	public void setLagSaleCycle(Integer lagSaleCycle) {
		this.lagSaleCycle = lagSaleCycle;
	}

	public Integer getLagSaleDays() {
		return lagSaleDays;
	}

	public void setLagSaleDays(Integer lagSaleDays) {
		this.lagSaleDays = lagSaleDays;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
