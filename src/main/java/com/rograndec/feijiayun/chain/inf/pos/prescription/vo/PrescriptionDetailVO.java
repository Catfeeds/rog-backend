package com.rograndec.feijiayun.chain.inf.pos.prescription.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PrescriptionDetailVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月7日 下午1:53:37 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 处方明细ID
     */
    @ApiModelProperty(value = "处方明细主键ID")
    private Long detailId;
    
    /**
     * 货位明细ID
     */
    @ApiModelProperty(value = "货位明细主键ID")
    private Long shelfTableId;
    
    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;
    
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
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;
    
    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

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
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;
    
    /**
     * 单剂数量
     */
    @ApiModelProperty(value = "单剂数量")
    private BigDecimal singleDose;

    /**
     * 总数量
     */
    @ApiModelProperty(value = "总数量")
    private BigDecimal quantity;
    
    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    @ApiModelProperty(value = "行折扣（%）")
    private BigDecimal lineDiscount;
    
    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(value = "金额（整单优惠前金额）")
    private BigDecimal amount;
    
    /**
     * 用法ID
     */
    @ApiModelProperty(value = "用法ID,常规模式使用处方登记下必填")
    private Long usageId;
    
    /**
     * 用法名称
     */
    @ApiModelProperty(value = "用法名称,常规模式使用处方登记下必填")
    private String usageName;
    
    /**
     * 用量ID
     */
    @ApiModelProperty(value = "用量ID,常规模式使用处方登记下必填")
    private Long useQtyId;
    
    /**
     * 用量名称
     */
    @ApiModelProperty(value = "用量名称,常规模式使用处方登记下必填")
    private String useQtyName;
    
    /**
     * 单次剂量ID
     */
    @ApiModelProperty(value = "单次剂量ID,常规模式使用处方登记下必填")
    private Long timeDoseId;
    
    /**
     * 单次剂量名称
     */
    @ApiModelProperty(value = "单次剂量名称,常规模式使用处方登记下必填")
    private String timeDoseName;
    
    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;
    
    /**
     * 生产日期
     */
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "货位名")
    private String shelfName;


	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public Long getShelfId() {
		return shelfId;
	}

	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public BigDecimal getSingleDose() {
		return singleDose;
	}

	public void setSingleDose(BigDecimal singleDose) {
		this.singleDose = singleDose;
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

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Long getUsageId() {
		return usageId;
	}

	public void setUsageId(Long usageId) {
		this.usageId = usageId;
	}

	public String getUsageName() {
		return usageName;
	}

	public void setUsageName(String usageName) {
		this.usageName = usageName;
	}

	public Long getUseQtyId() {
		return useQtyId;
	}

	public void setUseQtyId(Long useQtyId) {
		this.useQtyId = useQtyId;
	}

	public String getUseQtyName() {
		return useQtyName;
	}

	public void setUseQtyName(String useQtyName) {
		this.useQtyName = useQtyName;
	}

	public Long getTimeDoseId() {
		return timeDoseId;
	}

	public void setTimeDoseId(Long timeDoseId) {
		this.timeDoseId = timeDoseId;
	}

	public String getTimeDoseName() {
		return timeDoseName;
	}

	public Long getShelfTableId() {
		return shelfTableId;
	}

	public void setShelfTableId(Long shelfTableId) {
		this.shelfTableId = shelfTableId;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public void setTimeDoseName(String timeDoseName) {
		this.timeDoseName = timeDoseName;
	}
	
}
