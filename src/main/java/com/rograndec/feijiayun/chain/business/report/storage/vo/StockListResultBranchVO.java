package com.rograndec.feijiayun.chain.business.report.storage.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class StockListResultBranchVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月24日 下午8:41:45 
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
    
	@ApiModelProperty(value = "库存数量")
    private BigDecimal stockQuantity = BigDecimal.ZERO;
    
	@ApiModelProperty(value = "可用数量")
    private BigDecimal usableQuantity = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "经营状态（2-出售中；3-已售罄；4-已下架；9-全部）")
	private Integer manageStatus;
    
    @ApiModelProperty(value = "经营状态")
	private String manageStatusName;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
