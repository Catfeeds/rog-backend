package com.rograndec.feijiayun.chain.business.purchase.plan.vo;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 历史单价VO
 * 
 * @author dongyang.du 2017年09月13日16:27:26
 *
 */
public class HistoricalUnitPriceVO {

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID")
	private Long id;

	/**
	 * 入库单编号
	 */
	@ApiModelProperty(value = "入库单编号")
	private String inStorageCode;

	/**
	 * 入库日期
	 */
	@ApiModelProperty(value = "入库日期")
	private Date inStorageDate;

	/**
	 * 单价
	 */
	@ApiModelProperty(value = "单价")
	private BigDecimal realPrice;

	/**
	 * 供货单位编码
	 */
	@ApiModelProperty(value = "供货单位编码")
	private String supplierCode;

	/**
	 * 供货单位名称
	 */
	@ApiModelProperty(value = "供货单位名称")
	private String supplierName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInStorageCode() {
		return inStorageCode;
	}

	public void setInStorageCode(String inStorageCode) {
		this.inStorageCode = inStorageCode;
	}

	public Date getInStorageDate() {
		return inStorageDate;
	}

	public void setInStorageDate(Date inStorageDate) {
		this.inStorageDate = inStorageDate;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
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

	@Override
	public String toString() {
		return "HistoricalUnitPriceVO [id=" + id + ", inStorageCode=" + inStorageCode + ", inStorageDate=" + inStorageDate + ", realPrice="
				+ realPrice + ", supplierCode=" + supplierCode + ", supplierName=" + supplierName + "]";
	}

}
