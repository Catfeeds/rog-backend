package com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//购物车商品
public class CentralizedCartGoodVO implements Serializable{

	private static final long serialVersionUID = 5357046965932378185L;
    //活动id
	@ApiModelProperty(value = "活动id")
    private Integer activityId;
    //商品id
	@ApiModelProperty(value = "商品id")
	private String gId;
    //商品名称
	@ApiModelProperty(value = "商品名称")
    private String gName;
    //拼音码
	@ApiModelProperty(value = "拼音码")
    private String pinyinCode;
    //规格
	@ApiModelProperty(value = "规格")
    private String gSpecification;
    //单位
	@ApiModelProperty(value = "单位")
    private String unitName;
    //生产厂家
	@ApiModelProperty(value = "生产厂家")
    private String gManufacturer;
    //零售价
	@ApiModelProperty(value = "零售价")
    private BigDecimal price;
	//秒杀价
	@ApiModelProperty(value = "秒杀价")
    private BigDecimal retailPrice;
    //成本价
	@ApiModelProperty(value = "成本价")
    private BigDecimal gcost;
    //集采价
//	@ApiModelProperty(value = "集采价")
//    private BigDecimal centralPurchasingPrice;
    //库存数量
	@ApiModelProperty(value = "库存数量")
    private Integer inventoryQuantity;
    //专属标签
	@ApiModelProperty(value = "专属标签")
    private String label;
    //图片路径
	@ApiModelProperty(value = "图片路径")
    private String picturePath;
    //批准文号
	@ApiModelProperty(value = "批准文号")
    private String registrationCode;
    // 数量
	@ApiModelProperty(value = "数量")
    private Integer quantity = 0;
    // 小计
	@ApiModelProperty(value = "小计")
    private BigDecimal subtotal;
    //限购数量
	@ApiModelProperty(value = "限购数量")
    private Integer restrictions;
    //是否拆包销售
	@ApiModelProperty(value = "是否拆包销售 0:否 1:是")
    private Integer gCanSplit;
    //中包装数量
	@ApiModelProperty(value = "中包装数量")
    private Integer gMiddlePackage;
	//药店ID
	private Long entrepriseId;
	//是否选中 默认false
	@ApiModelProperty(value = "是否选中 默认false")
	private Boolean checked = false;
	//创建人
	@ApiModelProperty(value = "添加人ID")
	private Long createrId;
	//创建人名称
	@ApiModelProperty(value = "添加人")
	private String createrName;
	//存入时间
	@ApiModelProperty(value = "添加时间")
	private Date addCartTime;
	
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getPinyinCode() {
		return pinyinCode;
	}
	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getgId() {
		return gId;
	}
	public void setgId(String gId) {
		this.gId = gId;
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
	public String getgManufacturer() {
		return gManufacturer;
	}
	public void setgManufacturer(String gManufacturer) {
		this.gManufacturer = gManufacturer;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}
	public BigDecimal getGcost() {
		return gcost;
	}
	public void setGcost(BigDecimal gcost) {
		this.gcost = gcost;
	}
	public Integer getInventoryQuantity() {
		return inventoryQuantity;
	}
	public void setInventoryQuantity(Integer inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getRegistrationCode() {
		return registrationCode;
	}
	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public Integer getRestrictions() {
		return restrictions;
	}
	public void setRestrictions(Integer restrictions) {
		this.restrictions = restrictions;
	}
	public Date getAddCartTime() {
		return addCartTime;
	}
	public void setAddCartTime(Date addCartTime) {
		this.addCartTime = addCartTime;
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
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getCreaterId() {
		return createrId;
	}
	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	
}
