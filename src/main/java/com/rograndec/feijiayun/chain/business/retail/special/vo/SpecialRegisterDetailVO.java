package com.rograndec.feijiayun.chain.business.retail.special.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: SpecialRegisterDetailVO
 * @Description:  零售管理-专管登记单品种明细
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-22 16:34:07
 */
@ApiModel(value = "SpecialRegisterDetailVO", description = "零售管理-专管登记单品种明细")
public class SpecialRegisterDetailVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;
	
	/**
     * 企业ID
     */
	@ApiModelProperty(required = true, value = "企业ID")
	private Long enterpriseId;
	
	/**
     * 上级企业ID
     */
	@ApiModelProperty(required = true, value = "上级企业ID")
	private Long parentId;
	
	/**
     * 单据（专管登记单单）ID
     */
	@ApiModelProperty(required = true, value = "单据（专管登记单单）ID")
	private Long registerId;
	
	/**
     * 单据（专管登记单）类型
     */
	@ApiModelProperty(required = true, value = "单据（专管登记单）类型")
	private Integer orderType;
	
	/**
     * 单据（专管登记单）编码
     */
	@ApiModelProperty(required = true, value = "单据（专管登记单）编码")
	private String registerCode;
	
	/**
     * 单据（专管登记单）日期
     */
	@ApiModelProperty(required = true, value = "单据（专管登记单）日期")
	private Date registerDate;
	
	/**
     * 商品ID
     */
	@ApiModelProperty(required = true, value = "商品ID")
	private Long goodsId;
	
	/**
     * 商品编码
     */
	@ApiModelProperty(required = true, value = "商品编码")
	private String goodsCode;
	
	/**
     * 条形码
     */
	@ApiModelProperty(required = false, value = "条形码")
	private String barcode;
	
	/**
     * 商品名称
     */
	@ApiModelProperty(required = true, value = "商品名称")
	private String goodsName;
	
	/**
     * 商品通用名称
     */
	@ApiModelProperty(required = false, value = "商品通用名称")
	private String goodsGenericName;
	
	/**
     * 剂型ID
     */
	@ApiModelProperty(required = true, value = "剂型ID")
	private Long dosageId;
	
	/**
     * 剂型名称
     */
	@ApiModelProperty(required = true, value = "剂型名称")
	private String dosageName;
	
	/**
     * 单位ID
     */
	@ApiModelProperty(required = true, value = "单位ID")
	private Long unitId;
	
	/**
     * 单位名称
     */
	@ApiModelProperty(required = true, value = "单位名称")
	private String unitName;
	
	/**
     * 商品规格
     */
	@ApiModelProperty(required = false, value = "商品规格")
	private String goodsSpecification;
	
	/**
     * 生产厂商ID
     */
	@ApiModelProperty(required = true, value = "生产厂商ID")
	private Long manufacturerId;
	
	/**
     * 生产厂商
     */
	@ApiModelProperty(required = true, value = "生产厂商")
	private String manufacturer;
	
	/**
     * 商品产地
     */
	@ApiModelProperty(required = false, value = "商品产地")
	private String goodsPlace;
	
	/**
     * 批准文号
     */
	@ApiModelProperty(required = true, value = "批准文号")
	private String approvalNumber;
	
	/**
     * 数量
     */
	@ApiModelProperty(required = true, value = "数量")
	private BigDecimal quantity;
	
	/**
     * 单剂数量
     */
	@ApiModelProperty(required = false, value = "单剂数量")
	private BigDecimal singleDose;
	
	/**
     * 总数量
     */
	@ApiModelProperty(required = true, value = "总数量")
	private BigDecimal totalQuantity;
	
	/**
     * 单价
     */
	@ApiModelProperty(required = true, value = "单价")
	private BigDecimal unitPrice;
	
	/**
     * 行折扣（%）
     */
	@ApiModelProperty(required = true, value = "行折扣（%）")
	private BigDecimal lineDiscount;
	
	/**
     * 金额（整单优惠前金额）
     */
	@ApiModelProperty(required = true, value = "金额（整单优惠前金额）")
	private BigDecimal amount;
	
	/**
     * 整单折扣（%）
     */
	@ApiModelProperty(required = true, value = "整单折扣（%）")
	private BigDecimal wholeDiscount;
	
	/**
     * 行优惠（整单优惠分摊到行的金额）
     */
	@ApiModelProperty(required = true, value = "行优惠（整单优惠分摊到行的金额）")
	private BigDecimal lineDiscountAmount;
	
	/**
     * 实际单价（实际金额/数量）
     */
	@ApiModelProperty(required = true, value = "实际单价（实际金额/数量）")
	private BigDecimal realPrice;
	
	/**
     * 实际金额
     */
	@ApiModelProperty(required = true, value = "实际金额")
	private BigDecimal realAmount;
	
	/**
     * 进项税
     */
	@ApiModelProperty(required = true, value = "进项税")
	private BigDecimal taxRate;
	
	/**
     * 不含税实际单价
     */
	@ApiModelProperty(required = true, value = "不含税实际单价")
	private BigDecimal notaxRealPrice;
	
	/**
     * 不含税实际金额
     */
	@ApiModelProperty(required = true, value = "不含税实际金额")
	private BigDecimal notaxRealAmount;
	
	/**
     * 税额
     */
	@ApiModelProperty(required = true, value = "税额")
	private BigDecimal taxAmount;
	
	/**
     * 利润金额
     */
	@ApiModelProperty(required = true, value = "利润金额")
	private BigDecimal profit;
	
	/**
     * 不含税利润金额
     */
	@ApiModelProperty(required = true, value = "不含税利润金额")
	private BigDecimal notaxProfit;
	
	/**
     * 利润率
     */
	@ApiModelProperty(required = true, value = "利润率")
	private BigDecimal profitRate;
	
	/**
     * 不含税利润率
     */
	@ApiModelProperty(required = true, value = "不含税利润率")
	private BigDecimal notaxProfitRate;
	
	/**
     * 未清数量
     */
	@ApiModelProperty(required = true, value = "未清数量")
	private BigDecimal unclearQuantity;
	
	/**
     * 已清数量
     */
	@ApiModelProperty(required = true, value = "已清数量")
	private BigDecimal clearQuantity;
	
	/**
     * 行号
     */
	@ApiModelProperty(required = true, value = "行号")
	private Integer lineNum;
	
	/**
     * 状态
     */
	@ApiModelProperty(required = true, value = "状态")
	private Integer status;
	
	/**
     * 备注
     */
	@ApiModelProperty(required = false, value = "备注")
	private String remark;

	@ApiModelProperty(required = true,value = "货位信息")
	private List<SpecialRegisterShelfVO> specialRegisterShelfVOList;

	 public List<SpecialRegisterShelfVO> getSpecialRegisterShelfVOList() {
		 return specialRegisterShelfVOList;
	 }

	 public void setSpecialRegisterShelfVOList(List<SpecialRegisterShelfVO> specialRegisterShelfVOList) {
		 this.specialRegisterShelfVOList = specialRegisterShelfVOList;
	 }

	 /**
	 * 主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 主键ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 企业ID
	 */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	/**
	 * 企业ID
	 */
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	/**
	 * 上级企业ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 上级企业ID
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * 单据（专管登记单单）ID
	 */
	public void setRegisterId(Long registerId) {
		this.registerId = registerId;
	}
	
	/**
	 * 单据（专管登记单单）ID
	 */
	public Long getRegisterId() {
		return registerId;
	}
	
	/**
	 * 单据（专管登记单）类型
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * 单据（专管登记单）类型
	 */
	public Integer getOrderType() {
		return orderType;
	}
	
	/**
	 * 单据（专管登记单）编码
	 */
	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}
	
	/**
	 * 单据（专管登记单）编码
	 */
	public String getRegisterCode() {
		return registerCode;
	}
	
	/**
	 * 单据（专管登记单）日期
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	/**
	 * 单据（专管登记单）日期
	 */
	public Date getRegisterDate() {
		return registerDate;
	}
	
	/**
	 * 商品ID
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	/**
	 * 商品ID
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	
	/**
	 * 商品编码
	 */
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	/**
	 * 商品编码
	 */
	public String getGoodsCode() {
		return goodsCode;
	}
	
	/**
	 * 条形码
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	/**
	 * 条形码
	 */
	public String getBarcode() {
		return barcode;
	}
	
	/**
	 * 商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	/**
	 * 商品名称
	 */
	public String getGoodsName() {
		return goodsName;
	}
	
	/**
	 * 商品通用名称
	 */
	public void setGoodsGenericName(String goodsGenericName) {
		this.goodsGenericName = goodsGenericName;
	}
	
	/**
	 * 商品通用名称
	 */
	public String getGoodsGenericName() {
		return goodsGenericName;
	}
	
	/**
	 * 剂型ID
	 */
	public void setDosageId(Long dosageId) {
		this.dosageId = dosageId;
	}
	
	/**
	 * 剂型ID
	 */
	public Long getDosageId() {
		return dosageId;
	}
	
	/**
	 * 剂型名称
	 */
	public void setDosageName(String dosageName) {
		this.dosageName = dosageName;
	}
	
	/**
	 * 剂型名称
	 */
	public String getDosageName() {
		return dosageName;
	}
	
	/**
	 * 单位ID
	 */
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	
	/**
	 * 单位ID
	 */
	public Long getUnitId() {
		return unitId;
	}
	
	/**
	 * 单位名称
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	/**
	 * 单位名称
	 */
	public String getUnitName() {
		return unitName;
	}
	
	/**
	 * 商品规格
	 */
	public void setGoodsSpecification(String goodsSpecification) {
		this.goodsSpecification = goodsSpecification;
	}
	
	/**
	 * 商品规格
	 */
	public String getGoodsSpecification() {
		return goodsSpecification;
	}
	
	/**
	 * 生产厂商ID
	 */
	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	
	/**
	 * 生产厂商ID
	 */
	public Long getManufacturerId() {
		return manufacturerId;
	}
	
	/**
	 * 生产厂商
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	/**
	 * 生产厂商
	 */
	public String getManufacturer() {
		return manufacturer;
	}
	
	/**
	 * 商品产地
	 */
	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
	}
	
	/**
	 * 商品产地
	 */
	public String getGoodsPlace() {
		return goodsPlace;
	}
	
	/**
	 * 批准文号
	 */
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
	
	/**
	 * 批准文号
	 */
	public String getApprovalNumber() {
		return approvalNumber;
	}
	
	/**
	 * 数量
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * 数量
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}
	
	/**
	 * 单剂数量
	 */
	public void setSingleDose(BigDecimal singleDose) {
		this.singleDose = singleDose;
	}
	
	/**
	 * 单剂数量
	 */
	public BigDecimal getSingleDose() {
		return singleDose;
	}
	
	/**
	 * 总数量
	 */
	public void setTotalQuantity(BigDecimal totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	
	/**
	 * 总数量
	 */
	public BigDecimal getTotalQuantity() {
		return totalQuantity;
	}
	
	/**
	 * 单价
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	/**
	 * 单价
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	
	/**
	 * 行折扣（%）
	 */
	public void setLineDiscount(BigDecimal lineDiscount) {
		this.lineDiscount = lineDiscount;
	}
	
	/**
	 * 行折扣（%）
	 */
	public BigDecimal getLineDiscount() {
		return lineDiscount;
	}
	
	/**
	 * 金额（整单优惠前金额）
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	/**
	 * 金额（整单优惠前金额）
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * 整单折扣（%）
	 */
	public void setWholeDiscount(BigDecimal wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}
	
	/**
	 * 整单折扣（%）
	 */
	public BigDecimal getWholeDiscount() {
		return wholeDiscount;
	}
	
	/**
	 * 行优惠（整单优惠分摊到行的金额）
	 */
	public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
		this.lineDiscountAmount = lineDiscountAmount;
	}
	
	/**
	 * 行优惠（整单优惠分摊到行的金额）
	 */
	public BigDecimal getLineDiscountAmount() {
		return lineDiscountAmount;
	}
	
	/**
	 * 实际单价（实际金额/数量）
	 */
	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}
	
	/**
	 * 实际单价（实际金额/数量）
	 */
	public BigDecimal getRealPrice() {
		return realPrice;
	}
	
	/**
	 * 实际金额
	 */
	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}
	
	/**
	 * 实际金额
	 */
	public BigDecimal getRealAmount() {
		return realAmount;
	}
	
	/**
	 * 进项税
	 */
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	
	/**
	 * 进项税
	 */
	public BigDecimal getTaxRate() {
		return taxRate;
	}
	
	/**
	 * 不含税实际单价
	 */
	public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
		this.notaxRealPrice = notaxRealPrice;
	}
	
	/**
	 * 不含税实际单价
	 */
	public BigDecimal getNotaxRealPrice() {
		return notaxRealPrice;
	}
	
	/**
	 * 不含税实际金额
	 */
	public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
		this.notaxRealAmount = notaxRealAmount;
	}
	
	/**
	 * 不含税实际金额
	 */
	public BigDecimal getNotaxRealAmount() {
		return notaxRealAmount;
	}
	
	/**
	 * 税额
	 */
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	/**
	 * 税额
	 */
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	
	/**
	 * 利润金额
	 */
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	
	/**
	 * 利润金额
	 */
	public BigDecimal getProfit() {
		return profit;
	}
	
	/**
	 * 不含税利润金额
	 */
	public void setNotaxProfit(BigDecimal notaxProfit) {
		this.notaxProfit = notaxProfit;
	}
	
	/**
	 * 不含税利润金额
	 */
	public BigDecimal getNotaxProfit() {
		return notaxProfit;
	}
	
	/**
	 * 利润率
	 */
	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}
	
	/**
	 * 利润率
	 */
	public BigDecimal getProfitRate() {
		return profitRate;
	}
	
	/**
	 * 不含税利润率
	 */
	public void setNotaxProfitRate(BigDecimal notaxProfitRate) {
		this.notaxProfitRate = notaxProfitRate;
	}
	
	/**
	 * 不含税利润率
	 */
	public BigDecimal getNotaxProfitRate() {
		return notaxProfitRate;
	}
	
	/**
	 * 未清数量
	 */
	public void setUnclearQuantity(BigDecimal unclearQuantity) {
		this.unclearQuantity = unclearQuantity;
	}
	
	/**
	 * 未清数量
	 */
	public BigDecimal getUnclearQuantity() {
		return unclearQuantity;
	}
	
	/**
	 * 已清数量
	 */
	public void setClearQuantity(BigDecimal clearQuantity) {
		this.clearQuantity = clearQuantity;
	}
	
	/**
	 * 已清数量
	 */
	public BigDecimal getClearQuantity() {
		return clearQuantity;
	}
	
	/**
	 * 行号
	 */
	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}
	
	/**
	 * 行号
	 */
	public Integer getLineNum() {
		return lineNum;
	}
	
	/**
	 * 状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 状态
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 备注
	 */
	public String getRemark() {
		return remark;
	}
	

}