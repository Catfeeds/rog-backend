package com.rograndec.feijiayun.chain.business.distr.parent.vo.pick;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PickOrderDtlExcelVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "商品编码")
    private String goodsCode;

    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;
    
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;

    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    @ApiModelProperty(value = "商品产地")
    private String goodsPlace;
    
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    @ApiModelProperty(value = "生产日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date productDate;

    @ApiModelProperty(value = "有效期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validDate;

    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getGoodsSpecification() {
		return goodsSpecification;
	}

	public void setGoodsSpecification(String goodsSpecification) {
		this.goodsSpecification = goodsSpecification;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getGoodsPlace() {
		return goodsPlace;
	}

	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
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

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
