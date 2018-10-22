package com.rograndec.feijiayun.chain.business.retail.pricing.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

 /**
 * 
 * @ClassName: SalePricingVO
 * @Description:  零售管理-划价单
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 15:56:13
 */
public class SalePricingExcel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private String goodsCode;
    private String genericName;
    private String dosageName;
    private String specification;
    private String unitName;
    private String manufacturer;
    private String lotNumber;
    private Date productDate;
    private String productDateStr;
    private Date validDate;
    private String validDateStr;
    private String shelfName;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal lineDiscount;
    private BigDecimal amount;
    private BigDecimal taxRate;
    private BigDecimal notaxRealPrice;
    private BigDecimal notaxRealAmount;
    private BigDecimal taxAmount;
    private String remark;
    
    
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
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
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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
	public String getProductDateStr() {
		return productDateStr;
	}
	public void setProductDateStr(String productDateStr) {
		this.productDateStr = productDateStr;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public String getValidDateStr() {
		return validDateStr;
	}
	public void setValidDateStr(String validDateStr) {
		this.validDateStr = validDateStr;
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
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getLineDiscount() {
		return lineDiscount;
	}
	public void setLineDiscount(BigDecimal lineDiscount) {
		this.lineDiscount = lineDiscount;
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
	public BigDecimal getNotaxRealPrice() {
		return notaxRealPrice;
	}
	public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
		this.notaxRealPrice = notaxRealPrice;
	}
	public BigDecimal getNotaxRealAmount() {
		return notaxRealAmount;
	}
	public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
		this.notaxRealAmount = notaxRealAmount;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    

}