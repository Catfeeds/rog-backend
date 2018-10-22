package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApiModel(value = "SaleOutORreturnVO", description = "实销实结---销售清单VO")
public class SaleOutORreturnVO {

    @ApiModelProperty(value = "批号")
    private String lotNumber;
	@ApiModelProperty(value = "销售数量")
	private BigDecimal quantity;

	public BigDecimal getVerificationQuantity() {
		return verificationQuantity;
	}

	public void setVerificationQuantity(BigDecimal verificationQuantity) {
		this.verificationQuantity = verificationQuantity;
	}

	@ApiModelProperty(value = "已经核销的数量")
	private BigDecimal verificationQuantity;

	@ApiModelProperty(value = "自动核销的数量")
	private BigDecimal autoVerificationQuantity;

	public BigDecimal getAutoVerificationQuantity() {
		return autoVerificationQuantity;
	}

	public void setAutoVerificationQuantity(BigDecimal autoVerificationQuantity) {
		this.autoVerificationQuantity = autoVerificationQuantity;
	}

	public List<PurchaseInStorageReportPageVO> getInstorageList() {
		return instorageList;
	}

	public void setInstorageList(List<PurchaseInStorageReportPageVO> instorageList) {
		this.instorageList = instorageList;
	}

	private List<PurchaseInStorageReportPageVO> instorageList;

	/**
	 * 商品ID
	 */
	@ApiModelProperty(value = "商品ID")
	private Long goodsId;

	/**
	 * 商品编码
	 */
	@ApiModelProperty(value = "商品编码")
	private String goodsCode;

	/**
	 * 条形码
	 */
	@ApiModelProperty(value = "条形码")
	private String barcode;

	/**
	 * 商品名称
	 */
	@ApiModelProperty(value = "商品名称")
	private String goodsName;

	/**
	 * 商品通用名称
	 */
	@ApiModelProperty(value = "商品通用名称")
	private String goodsGenericName;

	/**
	 * 剂型ID
	 */
	@ApiModelProperty(value = "剂型ID",hidden=true)
	private Long dosageId;

	/**
	 * 剂型名称
	 */
	@ApiModelProperty(value = "剂型名称")
	private String dosageName;

	/**
	 * 单位ID
	 */
	@ApiModelProperty(value = "单位ID",hidden=true)
	private Long unitId;

	/**
	 * 单位名称
	 */
	@ApiModelProperty(value = "单位名称")
	private String unitName;

	/**
	 * 商品规格
	 */
	@ApiModelProperty(value = "商品规格")
	private String goodsSpecification;


	/**
	 * 生产日期
	 */
	@ApiModelProperty(value = "生产日期", required = true)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date productDate;

	/**
	 * 有效期
	 */
	@ApiModelProperty(value = "有效期", required = true)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date validDate;


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

	
	public String getBarcode() {
		return barcode;
	}

	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	
	public String getGoodsName() {
		return goodsName;
	}

	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	
	public String getGoodsGenericName() {
		return goodsGenericName;
	}

	
	public void setGoodsGenericName(String goodsGenericName) {
		this.goodsGenericName = goodsGenericName;
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

	public BigDecimal getUnverificationQuantity() {
		return unverificationQuantity;
	}

	public void setUnverificationQuantity(BigDecimal unverificationQuantity) {
		this.unverificationQuantity = unverificationQuantity;
	}

	@ApiModelProperty(value = "未核对的数量")
	private BigDecimal unverificationQuantity;

	
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	/**
	 * 生产厂商ID
	 */
	@ApiModelProperty(value = "生产厂商ID",hidden=true)

	private Long manufacturerId;

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

	/**
	 * 生产厂商
	 */

	@ApiModelProperty(value = "生产厂商")
	private String manufacturer;

	/**
	 * 商品产地
	 */
	@ApiModelProperty(value = "商品产地")
	private String goodsPlace;

	/**
	 * 批准文号
	 */
	@ApiModelProperty(value = "批准文号")
	private String approvalNumber;


	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
}
