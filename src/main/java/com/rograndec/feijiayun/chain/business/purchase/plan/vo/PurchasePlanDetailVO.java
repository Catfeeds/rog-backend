package com.rograndec.feijiayun.chain.business.purchase.plan.vo;

import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author dongyang.du 2017年09月13日16:53:42
 *
 */
public class PurchasePlanDetailVO implements Serializable {

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID")
	private Long id;

	/**
	 * 计划ID
	 */
	@ApiModelProperty(value = "计划ID")
	private Long planId;

	/**
	 * 企业（总部）ID
	 */
	@ApiModelProperty(value = "企业（总部）ID")
	private Long enterpriseId;

	/**
	 * 商品ID
	 */
	@ApiModelProperty(value = "商品ID (必传)")
	private Long goodsId;

	/**
	 * 商品编码
	 */
	@ApiModelProperty(value = "商品编码")
	private String goodsCode;
	//
	// /**
	// * 条形码
	// */
	// private String barcode;
	//
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
	@ApiModelProperty(value = "剂型ID")
	private Long dosageId;

	/**
	 * 剂型名称
	 */
	@ApiModelProperty(value = "剂型名称")
	private String dosageName;
	//
	// /**
	// * 单位ID
	// */
	// private Long unitId;
	//
	 /**
	 * 单位名称
	 */
	 private String unitName;
	//
	/**
	 * 商品规格
	 */
	@ApiModelProperty(value = "商品规格")
	private String goodsSpecification;
	//
	// /**
	// * 生产厂商ID
	// */
	// private String manufacturerId;
	//
	/**
	 * 生产厂商
	 */
	@ApiModelProperty(value = "生产厂商")
	private String manufacturer;
	//
	// /**
	// * 商品产地
	// */
	// private String goodsPlace;

	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量（必传）")
	private BigDecimal quantity;

	/**
	 * 单价
	 */
	@ApiModelProperty(value = "单价 (必传)")
	private BigDecimal unitPrice;

	/**
	 * 金额
	 */
	@ApiModelProperty(value = "金额")
	private BigDecimal amount;


	/**
	 * 税率ID
	 */
	@ApiModelProperty(value = "税率ID (必传)")
	private Long taxRateId;

	/**
	 * 税率
	 */
	@ApiModelProperty(value = "税率")
	private BigDecimal taxRate;

	/**
	 * 不含税单价
	 */
	@ApiModelProperty(value = "不含税单价")
	private BigDecimal notaxPrice;

	/**
	 * 不含税金额
	 */
	@ApiModelProperty(value = "不含税金额")
	private BigDecimal notaxAmount;

	/**
	 * 税额
	 */
	@ApiModelProperty(value = "税额")
	private BigDecimal taxAmout;

	/**
	 * 供货单位ID
	 */
	@ApiModelProperty(value = "供货单位ID (必传)")
	private Long supplierId;

	/**
	 * 供货位单位编码
	 */
	@ApiModelProperty(value = "供货位单位编码 (必传)")
	private String supplierCode;

	/**
	 * 供货单位名称
	 */
	@ApiModelProperty(value = "供货单位名称 (必传)")
	private String supplierName;

	/**
	 * 承运方式（0-配送；1-委托运输；2-自提）
	 */
	@ApiModelProperty(value = "承运方式（0-配送；1-委托运输；2-自提）(必传)")
	private Integer carriageMode;

	private String carriageName;

	@ApiModelProperty(value = "对应供货单位下拉列表")
	private List<SupplierVO> supplierVOList;


	public List<SupplierVO> getSupplierVOList() {
		return supplierVOList;
	}

	public void setSupplierVOList(List<SupplierVO> supplierVOList) {
		this.supplierVOList = supplierVOList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

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

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getNotaxPrice() {
		return notaxPrice;
	}

	public void setNotaxPrice(BigDecimal notaxPrice) {
		this.notaxPrice = notaxPrice;
	}

	public BigDecimal getNotaxAmount() {
		return notaxAmount;
	}

	public void setNotaxAmount(BigDecimal notaxAmount) {
		this.notaxAmount = notaxAmount;
	}

	public BigDecimal getTaxAmout() {
		return taxAmout;
	}

	public void setTaxAmout(BigDecimal taxAmout) {
		this.taxAmout = taxAmout;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getCarriageMode() {
		return carriageMode;
	}

	public void setCarriageMode(Integer carriageMode) {
		this.carriageMode = carriageMode;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
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

	public String getCarriageName() {

		if(carriageMode == null) return "";
		return DistributionType.getName(carriageMode);
	}

	public void setCarriageName(String carriageName) {
		this.carriageName = carriageName;
	}

	public Long getTaxRateId() {
		return taxRateId;
	}

	public void setTaxRateId(Long taxRateId) {
		this.taxRateId = taxRateId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Override
	public String toString() {
		return "PurchasePlanDetailVO{" +
				"id=" + id +
				", planId=" + planId +
				", enterpriseId=" + enterpriseId +
				", goodsId=" + goodsId +
				", goodsCode='" + goodsCode + '\'' +
				", goodsName='" + goodsName + '\'' +
				", goodsGenericName='" + goodsGenericName + '\'' +
				", dosageId=" + dosageId +
				", dosageName='" + dosageName + '\'' +
				", goodsSpecification='" + goodsSpecification + '\'' +
				", manufacturer='" + manufacturer + '\'' +
				", quantity=" + quantity +
				", unitPrice=" + unitPrice +
				", amount=" + amount +
				", taxRateId=" + taxRateId +
				", taxRate=" + taxRate +
				", unitName=" + unitName +
				", notaxPrice=" + notaxPrice +
				", notaxAmount=" + notaxAmount +
				", taxAmout=" + taxAmout +
				", supplierId=" + supplierId +
				", supplierCode='" + supplierCode + '\'' +
				", supplierName='" + supplierName + '\'' +
				", carriageMode=" + carriageMode +
				", carriageName='" + carriageName + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PurchasePlanDetailVO that = (PurchasePlanDetailVO) o;

		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
