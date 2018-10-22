package com.rograndec.feijiayun.chain.business.purchase.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class PurchaseOrderDetailRequestVO implements Serializable {
	 /**
     * 主键ID
     */
	@ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 商品ID
     */
	@ApiModelProperty(value = "商品ID",required =true)
    private Long goodsId;

	@ApiModelProperty(value = "商品编码",required =true)
    private String goodsCode;
    /**
     * 数量
     */
	@ApiModelProperty(value = "数量",required=true)
    private BigDecimal quantity;

    /**
     * 单价
     */
	@ApiModelProperty(value = "单价",required=true)
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
	@ApiModelProperty(value = "行折扣（%）",required=true)
    private BigDecimal lineDiscount;

    /**
     * 金额（整单优惠前金额）
     */
	@ApiModelProperty(value = "金额（整单优惠前金额）",required=true)
    private BigDecimal amount;

    /**
     * 整单折扣（%）
     */
	@ApiModelProperty(value = "整单折扣（%）",required=true)
    private BigDecimal wholeDiscount;

    /**
     * 行优惠（整单优惠分摊到行的金额）
     */
	@ApiModelProperty(value = "行优惠（整单优惠分摊到行的金额）")
    private BigDecimal lineDiscountAmount;

    /**
     * 实际单价（实际金额/数量）
     */
	@ApiModelProperty(value = "实际单价（实际金额/数量）",required=true)
    private BigDecimal realPrice;

    /**
     * 实际金额
     */
	@ApiModelProperty(value = "实际金额",required=true)
    private BigDecimal realAmount;

    /**
     * 进项税
     */
	@ApiModelProperty(value = "税率")
    private BigDecimal taxRate;
	
	/**
     * 进项税
     */
	@ApiModelProperty(value = "税率ID",required=true)
    private Long taxRateId;
    /**
     * 不含税实际单价
     */
	@ApiModelProperty(value = "不含税实际单价",required=true)
    private BigDecimal notaxRealPrice;

    /**
     * 不含税实际金额
     */
	@ApiModelProperty(value = "不含税实际金额",required=true)
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
	@ApiModelProperty(value = "税额",required=true)
    private BigDecimal taxAmount;

    /**
     * 行号
     */
	@ApiModelProperty(value = "行号",required=true)
    private Integer lineNum;


    /**
     * 备注
     */
	@ApiModelProperty(value = "备注")
    private String remark;
	//保存草稿的时候保存的字段
	//begin 
	@ApiModelProperty(value = "通用名称")
	private String genericName;
	 /**
     * 商品通用名称
     */
	@ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;
	@ApiModelProperty(value = "商品名称")
	private String name;
	@ApiModelProperty(value = "剂型名称")
	private String dosageName;
	@ApiModelProperty(value = "规格")
	private String specification;
	 /**
     * 商品规格
     */
	@ApiModelProperty(value = "商品规格")
    private String goodsSpecification;
	@ApiModelProperty(value="单位ID")
    private Long unitId;
    @ApiModelProperty(value="单位名称")
    private String unitName;
	@ApiModelProperty(value = "生产厂商ID")
    private String manufacturerId;
	@ApiModelProperty(value = "生产厂商名称")
	private String manufacturer;
	@ApiModelProperty(value="零售基价")
    private BigDecimal retailPrice;
    @ApiModelProperty(value="会员基价")
    private BigDecimal memberPrice;
    @ApiModelProperty(value="配货基价")
    private BigDecimal distrPrice;
    //end

    /**
     * saas_purchase_order_detail
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 数量
     * @return quantity 数量
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * 数量
     * @param quantity 数量
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * 单价
     * @return unit_price 单价
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 单价
     * @param unitPrice 单价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 行折扣（%）
     * @return line_discount 行折扣（%）
     */
    public BigDecimal getLineDiscount() {
        return lineDiscount;
    }

    /**
     * 行折扣（%）
     * @param lineDiscount 行折扣（%）
     */
    public void setLineDiscount(BigDecimal lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    /**
     * 金额（整单优惠前金额）
     * @return amount 金额（整单优惠前金额）
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 金额（整单优惠前金额）
     * @param amount 金额（整单优惠前金额）
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 整单折扣（%）
     * @return whole_discount 整单折扣（%）
     */
    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    /**
     * 整单折扣（%）
     * @param wholeDiscount 整单折扣（%）
     */
    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    /**
     * 行优惠（整单优惠分摊到行的金额）
     * @return line_discount_amount 行优惠（整单优惠分摊到行的金额）
     */
    public BigDecimal getLineDiscountAmount() {
        return lineDiscountAmount;
    }

    /**
     * 行优惠（整单优惠分摊到行的金额）
     * @param lineDiscountAmount 行优惠（整单优惠分摊到行的金额）
     */
    public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
        this.lineDiscountAmount = lineDiscountAmount;
    }

    /**
     * 实际单价（实际金额/数量）
     * @return real_price 实际单价（实际金额/数量）
     */
    public BigDecimal getRealPrice() {
        return realPrice;
    }

    /**
     * 实际单价（实际金额/数量）
     * @param realPrice 实际单价（实际金额/数量）
     */
    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    /**
     * 实际金额
     * @return real_amount 实际金额
     */
    public BigDecimal getRealAmount() {
        return realAmount;
    }

    /**
     * 实际金额
     * @param realAmount 实际金额
     */
    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    /**
     * 进项税
     * @return tax_rate 进项税
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * 进项税
     * @param taxRate 进项税
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * 不含税实际单价
     * @return notax_real_price 不含税实际单价
     */
    public BigDecimal getNotaxRealPrice() {
        return notaxRealPrice;
    }

    /**
     * 不含税实际单价
     * @param notaxRealPrice 不含税实际单价
     */
    public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
        this.notaxRealPrice = notaxRealPrice;
    }

    /**
     * 不含税实际金额
     * @return notax_real_amount 不含税实际金额
     */
    public BigDecimal getNotaxRealAmount() {
        return notaxRealAmount;
    }

    /**
     * 不含税实际金额
     * @param notaxRealAmount 不含税实际金额
     */
    public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
        this.notaxRealAmount = notaxRealAmount;
    }

    /**
     * 税额
     * @return tax_amount 税额
     */
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * 税额
     * @param taxAmount 税额
     */
    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     * 行号
     * @return line_num 行号
     */
    public Integer getLineNum() {
        return lineNum;
    }

    /**
     * 行号
     * @param lineNum 行号
     */
    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
    
    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public Long getTaxRateId() {
		return taxRateId;
	}

	public void setTaxRateId(Long taxRateId) {
		this.taxRateId = taxRateId;
	}

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
		this.goodsGenericName = genericName;
		this.genericName = genericName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		this.goodsSpecification = specification;
		this.specification = specification;
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

	public String getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public BigDecimal getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(BigDecimal memberPrice) {
		this.memberPrice = memberPrice;
	}

	public BigDecimal getDistrPrice() {
		return distrPrice;
	}

	public void setDistrPrice(BigDecimal distrPrice) {
		this.distrPrice = distrPrice;
	}

	public String getGoodsGenericName() {
		return goodsGenericName;
	}

	public void setGoodsGenericName(String goodsGenericName) {
		this.goodsGenericName = goodsGenericName;
	}

	public String getGoodsSpecification() {
		return goodsSpecification;
	}

	public void setGoodsSpecification(String goodsSpecification) {
		this.goodsSpecification = goodsSpecification;
	}
    
}