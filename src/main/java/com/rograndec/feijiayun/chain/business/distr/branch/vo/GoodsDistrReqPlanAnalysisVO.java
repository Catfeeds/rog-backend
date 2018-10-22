package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品列表
 * @author 孙帮祥
 * */
import io.swagger.annotations.ApiModelProperty;

public class GoodsDistrReqPlanAnalysisVO implements Serializable{
	
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
    @ApiModelProperty(value="条形码")
    private String barcode;
    @ApiModelProperty(value="配货单位库存可用量")
    private BigDecimal orgUsableQuantity = BigDecimal.ZERO;
    @ApiModelProperty(value="门店库存可用量")
    private BigDecimal usableQuantity = BigDecimal.ZERO;
	@ApiModelProperty(value = "缺货数量")
	private BigDecimal quantity;
	@ApiModelProperty(value="销售总量")
	private BigDecimal saleQuantityTotal = BigDecimal.ZERO;
	@ApiModelProperty(value="库存剩余总量")
	private BigDecimal stockQuantityTotal = BigDecimal.ZERO;
	@ApiModelProperty(value="安全库存用量")
	private BigDecimal safetyStock;
	
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getSaleQuantityTotal() {
		return saleQuantityTotal;
	}
	public void setSaleQuantityTotal(BigDecimal saleQuantityTotal) {
		this.saleQuantityTotal = saleQuantityTotal;
	}
	public BigDecimal getStockQuantityTotal() {
		return stockQuantityTotal;
	}
	public void setStockQuantityTotal(BigDecimal stockQuantityTotal) {
		this.stockQuantityTotal = stockQuantityTotal;
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
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public BigDecimal getOrgUsableQuantity() {
		return orgUsableQuantity;
	}
	public void setOrgUsableQuantity(BigDecimal orgUsableQuantity) {
		this.orgUsableQuantity = orgUsableQuantity;
	}
	public BigDecimal getUsableQuantity() {
		return usableQuantity;
	}
	public void setUsableQuantity(BigDecimal usableQuantity) {
		this.usableQuantity = usableQuantity;
	}
	public BigDecimal getSafetyStock() {
		return safetyStock;
	}
	public void setSafetyStock(BigDecimal safetyStock) {
		this.safetyStock = safetyStock;
	}

}
