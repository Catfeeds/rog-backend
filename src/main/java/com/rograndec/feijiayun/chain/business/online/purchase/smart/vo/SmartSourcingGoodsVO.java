package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SmartSourcingGoodsVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年11月20日 下午7:37:28 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 3130960940418723257L;

	//saas商品ID
	@ApiModelProperty(value = "saas商品ID")
	private Long goodsId;
	
	//saas商品编码
	@ApiModelProperty(value = "saas商品编码")
	private String goodsCode;

	//saas商品通用名称
	@ApiModelProperty(value = "saas商品通用名称")
	private String goodsGenericName;
	
	//saas商品名称
	@ApiModelProperty(value = "saas商品名称")
	private String goodsName;

	//saas商品产地
	@ApiModelProperty(value = "saas商品产地")
	private String goodsPlace;
	
	//saas商品规格
	@ApiModelProperty(value = "saas商品规格")
	private String goodsSpecification;

	//saas品种类别
	@ApiModelProperty(value = "saas品种类别")
	private Integer businessVariety;
	
	//saas品种类别
	@ApiModelProperty(value = "saas品种类别")
	private String businessVarietyName;
	
	//saas生产企业ID
	@ApiModelProperty(value = "saas生产企业ID")
	private Long manufacturerId;
	
	//saas生产企业
	@ApiModelProperty(value = "saas生产企业")
	private String manufacturer;
	
	//saas 批准文号
	@ApiModelProperty(value = "saas 批准文号")
	private String registrationCode;
	
	//saas 剂型
	@ApiModelProperty(value = "saas 剂型ID")
	private Long dosageId;
	
	//saas 剂型
	@ApiModelProperty(value = "saas 剂型")
	private String dosageName;

	//购物车数量
	@ApiModelProperty(value = "购物车数量")
	private Integer quantity;

	//单价
	@ApiModelProperty(value = "单价")
	private BigDecimal retailPrice;

	//是否选中，0是1否
	@ApiModelProperty(value = "是否选中，0是1否")
	private Boolean checked = false;

	//小计
	@ApiModelProperty(value = "小计")
	private BigDecimal subtotal;
	
	//saas 单位
	@ApiModelProperty(value = "saas 单位")
	private Long unitId;
	
	//saas 单位
	@ApiModelProperty(value = "saas 单位")
	private String unitName;
	
	//MPH商品ID
	@ApiModelProperty(value = "MPH商品ID")
	private Long gId;

	//MPH生产企业
	@ApiModelProperty(value = "MPH生产企业")
	private String gManufacturer;

	//mph商品名称
	@ApiModelProperty(value = "mph商品名称")
	private String gName;

	//mph商品规格
	@ApiModelProperty(value = "mph商品规格")
	private String gSpecification;
	
	//mph库存数量
	@ApiModelProperty(value = "mph库存数量")
	private Integer inventoryQuantity;
	
	//mph 是否拆包销售(0否,1是)
	@ApiModelProperty(value = "mph 是否拆包销售(0否,1是)")
	private Integer gCanSplit;
	
	//mph中包装
	@ApiModelProperty(value = "mph中包装")
	private Integer gMiddlePackage;
	
	//药店ID
	@ApiModelProperty(value = "药店ID")
	private Long entrepriseId;
	
	//创建人
	@ApiModelProperty(value = "创建人")
	private Long createrId;

	//创建人名称
	@ApiModelProperty(value = "创建人名称")
	private String createrName;
	
	//存入时间
	@ApiModelProperty(value = "存入时间")
	private Date addCartTime;

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

	public String getGoodsPlace() {
		return goodsPlace;
	}

	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
	}

	public String getGoodsSpecification() {
		return goodsSpecification;
	}

	public void setGoodsSpecification(String goodsSpecification) {
		this.goodsSpecification = goodsSpecification;
	}

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

	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
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

	public Long getgId() {
		return gId;
	}

	public void setgId(Long gId) {
		this.gId = gId;
	}

	public String getgManufacturer() {
		return gManufacturer;
	}

	public void setgManufacturer(String gManufacturer) {
		this.gManufacturer = gManufacturer;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getgSpecification() {
		return gSpecification;
	}

	public void setgSpecification(String gSpecification) {
		this.gSpecification = gSpecification;
	}

	public Integer getInventoryQuantity() {
		return inventoryQuantity;
	}

	public void setInventoryQuantity(Integer inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	public Integer getgCanSplit() {
		return gCanSplit;
	}

	public void setgCanSplit(Integer gCanSplit) {
		this.gCanSplit = gCanSplit;
	}

	public Integer getgMiddlePackage() {
		return gMiddlePackage;
	}

	public void setgMiddlePackage(Integer gMiddlePackage) {
		this.gMiddlePackage = gMiddlePackage;
	}

	public Long getEntrepriseId() {
		return entrepriseId;
	}

	public void setEntrepriseId(Long entrepriseId) {
		this.entrepriseId = entrepriseId;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public Date getAddCartTime() {
		return addCartTime;
	}

	public void setAddCartTime(Date addCartTime) {
		this.addCartTime = addCartTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}