package com.rograndec.feijiayun.chain.business.storage.lot.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_lot_adjust_detail
 * 
 * 
 * @author kexinhao
 * 
 * 2017-09-28
 */
public class LotAdjustDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 单据类型（5321）
     */
    @ApiModelProperty(value = "单据类型（5321）")
    private Integer orderType;

    /**
     * 调整日期
     */
    @ApiModelProperty(value = "调整日期")
    private Date adjustDate;

    /**
     * 调整单号
     */
    @ApiModelProperty(value = "调整单号")
    private String code;

    /**
     * 单据（批号调整单）ID
     */
    @ApiModelProperty(value = "单据（批号调整单）ID")
    private Long adjustId;

    /**
     * 单据（批号调整单）编码
     */
    @ApiModelProperty(value = "单据（批号调整单）编码")
    private String adjustCode;

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
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    
    /**
     * 通用名称
     */
    @ApiModelProperty(value = "通用名称")
	private String goodsGenericName;
    
    /**
     * 剂型名称
     * */
	@ApiModelProperty(value = "剂型名称")
	private String dosageName;
	
	/**
	 * 计量单位名称
	 * */
    @ApiModelProperty(value="计量单位名称")
    private String unitName;
    
    /**
	 * 规格
	 * */
	@ApiModelProperty(value = "规格")
	private String goodsSpecification;
	
	/**
	 * 生产厂商名称
	 * */
	@ApiModelProperty(value = "生产厂商名称")
	private String manufacturer;
	
	/**
	 * 产地
	 * */
    @ApiModelProperty(value="产地")
    private String goodsPlace;
    
    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID")
    private Long lotId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNum;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期至
     */
    @ApiModelProperty(value = "有效期至")
    private Date validUntil;

    /**
     * 新批号
     */
    @ApiModelProperty(value = "新批号")
    private String newLotNum;

    /**
     * 新生产日期
     */
    @ApiModelProperty(value = "新生产日期")
    private Date newProductDate;

    /**
     * 新有效期至
     */
    @ApiModelProperty(value = "新有效期至")
    private Date newValidUntil;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 新增字段
     * */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;
    
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
    
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;
    
    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;
    
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;
    
    @ApiModelProperty(value = "不含税单价")
    private BigDecimal notaxPrice;
    
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxAmount;
    
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;
    
    @ApiModelProperty(value = "货位列表")
    private List<LotAdjustShelfVO> LotAdjustShelfVOList;
    /**
     * saas_lot_adjust_detail
     */
    private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Date getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getAdjustId() {
		return adjustId;
	}

	public void setAdjustId(Long adjustId) {
		this.adjustId = adjustId;
	}

	public String getAdjustCode() {
		return adjustCode;
	}

	public void setAdjustCode(String adjustCode) {
		this.adjustCode = adjustCode;
	}

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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public String getNewLotNum() {
		return newLotNum;
	}

	public void setNewLotNum(String newLotNum) {
		this.newLotNum = newLotNum;
	}

	public Date getNewProductDate() {
		return newProductDate;
	}

	public void setNewProductDate(Date newProductDate) {
		this.newProductDate = newProductDate;
	}

	public Date getNewValidUntil() {
		return newValidUntil;
	}

	public void setNewValidUntil(Date newValidUntil) {
		this.newValidUntil = newValidUntil;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Long getTaxRateId() {
		return taxRateId;
	}

	public void setTaxRateId(Long taxRateId) {
		this.taxRateId = taxRateId;
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

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<LotAdjustShelfVO> getLotAdjustShelfVOList() {
		return LotAdjustShelfVOList;
	}

	public void setLotAdjustShelfVOList(List<LotAdjustShelfVO> lotAdjustShelfVOList) {
		LotAdjustShelfVOList = lotAdjustShelfVOList;
	}
	
	

}