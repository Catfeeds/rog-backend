package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品列表
 * @author 孙帮祥
 * */
import io.swagger.annotations.ApiModelProperty;

public class GoodsDestroyVO implements Serializable{
	
	@ApiModelProperty(value = "商品ID")
	private Long goodsId;
	
	@ApiModelProperty(value = "商品编码")
	private String goodsCode;
	
	@ApiModelProperty(value = "通用名称")
	private String goodsGenericName;
	
	@ApiModelProperty(value = "商品名称")
	private String goodsName;
	
	@ApiModelProperty(value = "剂型名称")
	private String dosageName;
	
	@ApiModelProperty(value = "剂型ID")
	private Long dosageId;
	
    @ApiModelProperty(value="计量单位ID")
    private Long unitId;
    
    @ApiModelProperty(value="计量单位名称")
    private String unitName;
    
	@ApiModelProperty(value = "规格")
	private String goodsSpecification;
	
	@ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;
	
	@ApiModelProperty(value = "生产厂商名称")
	private String manufacturer;
	
    @ApiModelProperty(value="产地")
    private String goodsPlace;
    
    @ApiModelProperty(value="批准文号")
    private String approvalNumber;
    
    @ApiModelProperty(value="生产日期")
    private Date productDate;
    
    @ApiModelProperty(value="有效期")
    private Date validDate;
    
    @ApiModelProperty(value="条形码")
    private String barcode;
    
    @ApiModelProperty(value="批号ID")
    private Long lotId;
    
    @ApiModelProperty(value="批号")
    private String lotNum;
    
    @ApiModelProperty(value="货位ID")
    private Long shelfId;
    
    @ApiModelProperty(value="货位名称")
    private String shelfName;
    
    @ApiModelProperty(value="数量")
    private BigDecimal quantity;
    
    @ApiModelProperty(value="质量描述")
    private String shelfStatusDesc;
    
    @ApiModelProperty(value="可用数量")
    private BigDecimal usableQuantity;
    
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
	public String getGoodsGenericName() {
		return goodsGenericName;
	}
	public void setGoodsGenericName(String goodsGenericName) {
		this.goodsGenericName = goodsGenericName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getDosageName() {
		return dosageName;
	}
	public void setDosageName(String dosageName) {
		this.dosageName = dosageName;
	}
	public Long getDosageId() {
		return dosageId;
	}
	public void setDosageId(Long dosageId) {
		this.dosageId = dosageId;
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
	public String getGoodsSpecification() {
		return goodsSpecification;
	}
	public void setGoodsSpecification(String goodsSpecification) {
		this.goodsSpecification = goodsSpecification;
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
	
	public String getGoodsPlace() {
		return goodsPlace;
	}
	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
	}
	public String getApprovalNumber() {
		return approvalNumber;
	}
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
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
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Long getLotId() {
		return lotId;
	}
	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}
	public String getLotNum() {
		return lotNum;
	}
	public void setLotNum(String lotNum) {
		this.lotNum = lotNum;
	}
	public Long getShelfId() {
		return shelfId;
	}
	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
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
	public String getShelfStatusDesc() {
		return shelfStatusDesc;
	}
	public void setShelfStatusDesc(String shelfStatusDesc) {
		this.shelfStatusDesc = shelfStatusDesc;
	}
	public BigDecimal getUsableQuantity() {
		return usableQuantity;
	}
	public void setUsableQuantity(BigDecimal usableQuantity) {
		this.usableQuantity = usableQuantity;
	}
    
}
