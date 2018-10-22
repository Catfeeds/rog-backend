package com.rograndec.feijiayun.chain.business.retail.pricing.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_sale_pricing_detail
 * 
 * 
 * @author liyut
 * 
 * 2017-09-18
 */
public class SalePricingDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 单据（销售划价单单）ID
     */
    @ApiModelProperty(value = "单据（销售划价单单）ID")
    private Long pricingId;
    
    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID",hidden=true)
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID",hidden=true)
    private Long parentId;
    
    /**
     * 单据（销售划价单）类型
     */
    @ApiModelProperty(value = "单据（销售划价单）类型")
    private Integer orderType;

    /**
     * 单据（销售划价单）编码
     */
    @ApiModelProperty(value = "单据（销售划价单）编码")
    private String pricingCode;

    /**
     * 单据（销售划价单）日期
     */
    @ApiModelProperty(value = "单据（销售划价单）日期")
    private Date pricingDate;

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
    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
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
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

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

    /**
     * 数量
     */
    @NotNull(message="数量不能为空！")
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 单剂数量
     */
    @NotNull(message="单剂数量不能为空！")
    @ApiModelProperty(value = "单剂数量")
    private BigDecimal singleDose;

    /**
     * 总数量
     */
    @ApiModelProperty(value = "总数量")
    private BigDecimal totalQuantity;

    /**
     * 单价
     */
    @NotNull(message="单价不能为空！")
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
    
    @ApiModelProperty(value="零售单价")
    private BigDecimal retailPrice;
    @ApiModelProperty(value="会员单价")
    private BigDecimal memberPrice;

    /**
     * 行折扣（%）
     */
    @NotNull(message="行折扣不能为空！")
    @ApiModelProperty(value = "行折扣（%）")
    private BigDecimal lineDiscount;

    /**
     * 金额（整单优惠前金额）
     */
    @NotNull(message="金额（整单优惠前金额）不能为空！")
    @ApiModelProperty(value = "金额（整单优惠前金额）")
    private BigDecimal amount;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 行优惠（整单优惠分摊到行的金额）
     */
    @ApiModelProperty(value = "行优惠（整单优惠分摊到行的金额）")
    private BigDecimal lineDiscountAmount;

    /**
     * 实际单价（实际金额/数量）
     */
    @ApiModelProperty(value = "实际单价（实际金额/数量）")
    private BigDecimal realPrice;

    /**
     * 实际金额
     */
    @ApiModelProperty(value = "实际金额")
    private BigDecimal realAmount;

    /**
     * 进项税
     */
    @ApiModelProperty(value = "进项税",hidden=true)
    private BigDecimal taxRate;
    
    /**
     * 进项税
     */
    @NotNull(message="税率ID不能为空！")
	@ApiModelProperty(value = "税率ID",required=true)
    private Long taxRateId;

    /**
     * 不含税实际单价
     */
    @ApiModelProperty(value = "不含税实际单价")
    private BigDecimal notaxRealPrice;

    /**
     * 不含税实际金额
     */
    @ApiModelProperty(value = "不含税实际金额")
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    /**
     * 利润金额
     */
    @ApiModelProperty(value = "利润金额")
    private BigDecimal profit;

    /**
     * 不含税利润金额
     */
    @ApiModelProperty(value = "不含税利润金额")
    private BigDecimal notaxProfit;

    /**
     * 利润率
     */
    @ApiModelProperty(value = "利润率",hidden=true)
    private BigDecimal profitRate;

    /**
     * 不含税利润率
     */
    @ApiModelProperty(value = "不含税利润率",hidden=true)
    private BigDecimal notaxProfitRate;

    /**
     * 未清数量
     */
    @ApiModelProperty(value = "未清数量" ,hidden=true)
    private BigDecimal unclearQuantity;

    /**
     * 已清数量
     */
    @ApiModelProperty(value = "已清数量" ,hidden=true)
    private BigDecimal clearQuantity;

    /**
     * 行号
     */
    @NotNull(message="行号不能为空！")
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    //货位 批号
    private List<SalePricingShelfVO> salePricingShelfList;
    
	public List<SalePricingShelfVO> getSalePricingShelfList() {
		return salePricingShelfList;
	}

	public void setSalePricingShelfList(List<SalePricingShelfVO> salePricingShelfList) {
		this.salePricingShelfList = salePricingShelfList;
	}

	/**
     * saas_sale_pricing_detail
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
     * 单据（销售划价单单）ID
     * @return pricing_id 单据（销售划价单单）ID
     */
    public Long getPricingId() {
        return pricingId;
    }

    /**
     * 单据（销售划价单单）ID
     * @param pricingId 单据（销售划价单单）ID
     */
    public void setPricingId(Long pricingId) {
        this.pricingId = pricingId;
    }

    /**
     * 单据（销售划价单）类型
     * @return order_type 单据（销售划价单）类型
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据（销售划价单）类型
     * @param orderType 单据（销售划价单）类型
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 单据（销售划价单）编码
     * @return pricing_code 单据（销售划价单）编码
     */
    public String getPricingCode() {
        return pricingCode;
    }

    /**
     * 单据（销售划价单）编码
     * @param pricingCode 单据（销售划价单）编码
     */
    public void setPricingCode(String pricingCode) {
        this.pricingCode = pricingCode == null ? null : pricingCode.trim();
    }

    /**
     * 单据（销售划价单）日期
     * @return pricing_date 单据（销售划价单）日期
     */
    public Date getPricingDate() {
        return pricingDate;
    }

    /**
     * 单据（销售划价单）日期
     * @param pricingDate 单据（销售划价单）日期
     */
    public void setPricingDate(Date pricingDate) {
        this.pricingDate = pricingDate;
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
     * 商品编码
     * @return goods_code 商品编码
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 商品编码
     * @param goodsCode 商品编码
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     * 条形码
     * @return barcode 条形码
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * 条形码
     * @param barcode 条形码
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    /**
     * 商品名称
     * @return goods_name 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 商品名称
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 商品通用名称
     * @return goods_generic_name 商品通用名称
     */
    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    /**
     * 商品通用名称
     * @param goodsGenericName 商品通用名称
     */
    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName == null ? null : goodsGenericName.trim();
    }

    /**
     * 剂型ID
     * @return dosage_id 剂型ID
     */
    public Long getDosageId() {
        return dosageId;
    }

    /**
     * 剂型ID
     * @param dosageId 剂型ID
     */
    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    /**
     * 剂型名称
     * @return dosage_name 剂型名称
     */
    public String getDosageName() {
        return dosageName;
    }

    /**
     * 剂型名称
     * @param dosageName 剂型名称
     */
    public void setDosageName(String dosageName) {
        this.dosageName = dosageName == null ? null : dosageName.trim();
    }

    /**
     * 单位ID
     * @return unit_id 单位ID
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * 单位ID
     * @param unitId 单位ID
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * 单位名称
     * @return unit_name 单位名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 单位名称
     * @param unitName 单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    /**
     * 商品规格
     * @return goods_specification 商品规格
     */
    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    /**
     * 商品规格
     * @param goodsSpecification 商品规格
     */
    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification == null ? null : goodsSpecification.trim();
    }

    /**
     * 生产厂商ID
     * @return manufacturer_id 生产厂商ID
     */
    public Long getManufacturerId() {
        return manufacturerId;
    }

    /**
     * 生产厂商ID
     * @param manufacturerId 生产厂商ID
     */
    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * 生产厂商
     * @return manufacturer 生产厂商
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 生产厂商
     * @param manufacturer 生产厂商
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    /**
     * 商品产地
     * @return goods_place 商品产地
     */
    public String getGoodsPlace() {
        return goodsPlace;
    }

    /**
     * 商品产地
     * @param goodsPlace 商品产地
     */
    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace == null ? null : goodsPlace.trim();
    }

    /**
     * 批准文号
     * @return approval_number 批准文号
     */
    public String getApprovalNumber() {
        return approvalNumber;
    }

    /**
     * 批准文号
     * @param approvalNumber 批准文号
     */
    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber == null ? null : approvalNumber.trim();
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
     * 单剂数量
     * @return single_dose 单剂数量
     */
    public BigDecimal getSingleDose() {
        return singleDose;
    }

    /**
     * 单剂数量
     * @param singleDose 单剂数量
     */
    public void setSingleDose(BigDecimal singleDose) {
        this.singleDose = singleDose;
    }

    /**
     * 总数量
     * @return total_quantity 总数量
     */
    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    /**
     * 总数量
     * @param totalQuantity 总数量
     */
    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
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
     * 利润金额
     * @return profit 利润金额
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     * 利润金额
     * @param profit 利润金额
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    /**
     * 不含税利润金额
     * @return notax_profit 不含税利润金额
     */
    public BigDecimal getNotaxProfit() {
        return notaxProfit;
    }

    /**
     * 不含税利润金额
     * @param notaxProfit 不含税利润金额
     */
    public void setNotaxProfit(BigDecimal notaxProfit) {
        this.notaxProfit = notaxProfit;
    }

    /**
     * 利润率
     * @return profit_rate 利润率
     */
    public BigDecimal getProfitRate() {
        return profitRate;
    }

    /**
     * 利润率
     * @param profitRate 利润率
     */
    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    /**
     * 不含税利润率
     * @return notax_profit_rate 不含税利润率
     */
    public BigDecimal getNotaxProfitRate() {
        return notaxProfitRate;
    }

    /**
     * 不含税利润率
     * @param notaxProfitRate 不含税利润率
     */
    public void setNotaxProfitRate(BigDecimal notaxProfitRate) {
        this.notaxProfitRate = notaxProfitRate;
    }

    /**
     * 未清数量
     * @return unclear_quantity 未清数量
     */
    public BigDecimal getUnclearQuantity() {
        return unclearQuantity;
    }

    /**
     * 未清数量
     * @param unclearQuantity 未清数量
     */
    public void setUnclearQuantity(BigDecimal unclearQuantity) {
        this.unclearQuantity = unclearQuantity;
    }

    /**
     * 已清数量
     * @return clear_quantity 已清数量
     */
    public BigDecimal getClearQuantity() {
        return clearQuantity;
    }

    /**
     * 已清数量
     * @param clearQuantity 已清数量
     */
    public void setClearQuantity(BigDecimal clearQuantity) {
        this.clearQuantity = clearQuantity;
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
    
	
    

}