package com.rograndec.feijiayun.chain.inf.search.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MPH商品
 *
 */
public class MphGoods implements Serializable {
	private static final long serialVersionUID = 6153667167049753300L;

	private int id;

	private String name;

	private String image;

	private String specification;

	/*@JsonProperty("dosage_form_name")
	private String dosageFormName;*/

	private String manufacturer;

	@JsonProperty("purchase_price")
	private double purchasePrice;

	@JsonProperty("retail_price")
	private double retailPrice;

	@JsonProperty("inventory_quantity")
	private double inventoryQuantity;

	@JsonProperty("can_purchased")
	private int canPurchased;

	@JsonProperty("category_1_Name")
	private String category1Name;

	@JsonProperty("category_2_Name")
	private String category2Name;

	@JsonProperty("approval_number")
	private String approvalNumber;

	@JsonProperty("expiry_date")
	private String expiryDate;

	@JsonProperty("batch_no")
	private String batchNo;

	@JsonProperty("promotion_type")
	private Integer promotionType;

	@JsonProperty("promotion_price")
	private Double promotionPrice;
	
	// 是否拆零
	@JsonProperty("g_can_split")
	private Integer gCanSplit;

	// 中包装
	@JsonProperty("g_middle_package")
	private Integer gMiddlePackage;

	@JsonProperty("promotion_descripition")
	private List<String> promotionDescription;

	private MphSupplier supplier;

	/**
	 * 获取ID
	 * 
	 * @return ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置ID
	 * 
	 * @param id
	 *            ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取图片
	 * 
	 * @return 图片
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 设置图片
	 * 
	 * @param image
	 *            图片
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 获取规格
	 * 
	 * @return 规格
	 */
	public String getSpecification() {
		return specification;
	}

	/**
	 * 设置规格
	 * 
	 * @param specification
	 *            规格
	 */
	public void setSpecification(String specification) {
		this.specification = specification;
	}

	/**
	 * 获取剂型名称
	 * 
	 * @return 剂型名称
	 */
	/*public String getDosageFormName() {
		return dosageFormName;
	}*/

	/**
	 * 设置剂型名称
	 * 
	 * @param dosageFormName
	 *            剂型名称
	 */
	/*public void setDosageFormName(String dosageFormName) {
		this.dosageFormName = dosageFormName;
	}*/

	/**
	 * 获取生产厂商
	 * 
	 * @return 生产厂商
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * 设置生产厂商
	 * 
	 * @param manufacturer
	 *            生产厂商
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * 获取采购价
	 * 
	 * @return 采购价
	 */
	public double getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * 设置采购价
	 * 
	 * @param purchasePrice
	 *            采购价
	 */
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * 获取零售价
	 * 
	 * @return 零售价
	 */
	public double getRetailPrice() {
		return retailPrice;
	}

	/**
	 * 设置零售价
	 * 
	 * @param retailPrice
	 *            零售价
	 */
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	/**
	 * 获取库存数量
	 * 
	 * @return 库存数量
	 */
	public double getInventoryQuantity() {
		return inventoryQuantity;
	}

	/**
	 * 设置库存数量
	 * 
	 * @param inventoryQuantity
	 *            库存数量
	 */
	public void setInventoryQuantity(double inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	/**
	 * 获取是否可以采购，0：不可以采购，1：可以采购
	 * 
	 * @return 是否可以采购
	 */
	public int getCanPurchased() {
		return canPurchased;
	}

	/**
	 * 设置是否可以采购，0：不可以采购，1：可以采购
	 * 
	 * @param canPurchased
	 *            是否可以采购
	 */
	public void setCanPurchased(int canPurchased) {
		this.canPurchased = canPurchased;
	}

	/**
	 * 获取一级分类名称
	 * 
	 * @return 一级分类名称
	 */
	public String getCategory1Name() {
		return category1Name;
	}

	/**
	 * 设置一级分类名称
	 * 
	 * @param category1Name
	 *            一级分类名称
	 */
	public void setCategory1Name(String category1Name) {
		this.category1Name = category1Name;
	}

	/**
	 * 设置二级分类名称
	 * 
	 * @return 二级分类名称
	 */
	public String getCategory2Name() {
		return category2Name;
	}

	/**
	 * 设置二级分类名称
	 * 
	 * @param category2Name
	 *            二级分类名称
	 */
	public void setCategory2Name(String category2Name) {
		this.category2Name = category2Name;
	}

	/**
	 * 获取批准文号
	 * 
	 * @return 批准文号
	 */
	public String getApprovalNumber() {
		return approvalNumber;
	}

	/**
	 * 设置批准文号
	 * 
	 * @param approvalNumber
	 *            批准文号
	 */
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	/**
	 * 获取效期
	 * 
	 * @return 效期
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * 设置效期
	 * 
	 * @param expiryDate
	 *            效期
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * 获取批次号
	 * 
	 * @return 批次号
	 */
	public String getBatchNo() {
		return batchNo;
	}

	/**
	 * 设置批次号
	 * 
	 * @param batchNo
	 *            批次号
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * 获取促销类型
	 * 
	 * @return 促销类型
	 */
	public Integer getPromotionType() {
		return promotionType;
	}

	/**
	 * 设置促销类型 1:折扣类 2:买赠类 3:单品关联优惠类 4.业务类型优惠类,5:秒杀
	 * 
	 * @param promotionType
	 *            促销类型
	 */
	public void setPromotionType(Integer promotionType) {
		this.promotionType = promotionType;
	}

	/**
	 * 获取促销价格
	 * 
	 * @return 促销价格
	 */
	public Double getPromotionPrice() {
		return promotionPrice;
	}

	/**
	 * 设置促销价格
	 * 
	 * @param promotionPrice
	 *            促销价格
	 */
	public void setPromotionPrice(Double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	/**
	 * 获取促销描述
	 * 
	 * @return 促销描述
	 */
	public List<String> getPromotionDescription() {
		return promotionDescription;
	}

	/**
	 * 设置促销描述
	 * 
	 * @param promotionDescription
	 *            促销描述
	 */
	public void setPromotionDescription(List<String> promotionDescription) {
		this.promotionDescription = promotionDescription;
	}

	/**
	 * 获取供应商
	 * 
	 * @return 供应商
	 */
	public MphSupplier getSupplier() {
		return supplier;
	}

	/**
	 * 设置供应商
	 * 
	 * @param supplier
	 *            供应商
	 */
	public void setSupplier(MphSupplier supplier) {
		this.supplier = supplier;
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

}
