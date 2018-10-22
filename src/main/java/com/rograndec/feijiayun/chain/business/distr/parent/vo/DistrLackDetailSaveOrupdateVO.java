package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: DistrLackDetailSaveOrupdateVO
 * @Description:  总部-配货出库-缺配单明细-Rest接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-07 15:59:05
 */
@ApiModel(value = "DistrLackDetailSaveOrupdateVO", description = "总部-配货出库-缺配单明细")
public class DistrLackDetailSaveOrupdateVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;
	
	/**
     * 上级企业ID
     */
	@ApiModelProperty(required = false, value = "上级企业ID")
	private Long parentId;
	
	/**
     * 缺配单ID
     */
	@NotNull(message="缺配单ID不能为空!")
	@ApiModelProperty(required = true, value = "缺配单ID")
	private Long lackId;
	
	/**
     * 单据（缺配单）类型（5402）
     */
	@NotNull(message="单据（缺配单）类型（5402）不能为空!")
	@ApiModelProperty(required = true, value = "单据（缺配单）类型（5402）")
	private Integer orderType;
	
	/**
     * 缺配单单号
     */
	@NotNull(message="缺配单单号不能为空!")
	@ApiModelProperty(required = true, value = "缺配单单号")
	private String lackCode;
	
	/**
     * 缺配单日期
     */
	@NotNull(message="缺配单日期不能为空!")
	@ApiModelProperty(required = true, value = "缺配单日期")
	private Date lackDate;
	
	/**
     * 基础单据明细ID
     */
	@ApiModelProperty(required = false, value = "基础单据明细ID")
	private Long baseOrderDtlId;
	
	/**
     * 基础单据ID
     */
	@ApiModelProperty(required = false, value = "基础单据ID")
	private Long baseOrderId;
	
	/**
     * 基础单据类型
     */
	@ApiModelProperty(required = false, value = "基础单据类型")
	private Integer baseOrderType;
	
	/**
     * 基础单据编码
     */
	@ApiModelProperty(required = false, value = "基础单据编码")
	private String baseOrderCode;
	
	/**
     * 基础单据日期
     */
	@ApiModelProperty(required = false, value = "基础单据日期")
	private Date baseOrderDate;
	
	/**
     * 商品ID
     */
	@NotNull(message="商品ID不能为空!")
	@ApiModelProperty(required = true, value = "商品ID")
	private Long goodsId;
	
	/**
     * 商品编码
     */
	@NotNull(message="商品编码不能为空!")
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
	@NotNull(message="商品名称不能为空!")
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
	@NotNull(message="剂型ID不能为空!")
	@ApiModelProperty(required = true, value = "剂型ID")
	private Long dosageId;
	
	/**
     * 剂型名称
     */
	@NotNull(message="剂型名称不能为空!")
	@ApiModelProperty(required = true, value = "剂型名称")
	private String dosageName;
	
	/**
     * 单位ID
     */
	@NotNull(message="单位ID不能为空!")
	@ApiModelProperty(required = true, value = "单位ID")
	private Long unitId;
	
	/**
     * 单位名称
     */
	@NotNull(message="单位名称不能为空!")
	@ApiModelProperty(required = true, value = "单位名称")
	private String unitName;
	
	/**
     * 商品规格
     */
	@NotNull(message="商品规格不能为空!")
	@ApiModelProperty(required = true, value = "商品规格")
	private String goodsSpecification;
	
	/**
     * 生产厂商ID
     */
	@NotNull(message="生产厂商ID不能为空!")
	@ApiModelProperty(required = true, value = "生产厂商ID")
	private Long manufacturerId;
	
	/**
     * 生产厂商
     */
	@NotNull(message="生产厂商不能为空!")
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
	@NotNull(message="批准文号不能为空!")
	@ApiModelProperty(required = true, value = "批准文号")
	private String approvalNumber;
	
	/**
     * 要货数量
     */
	@ApiModelProperty(required = false, value = "要货数量")
	private BigDecimal requestQuantity;
	
	/**
     * 配货数量
     */
	@ApiModelProperty(required = false, value = "配货数量")
	private BigDecimal sendQuantity;
	
	/**
     * 缺配数量
     */
	@NotNull(message="缺配数量不能为空!")
	@ApiModelProperty(required = true, value = "缺配数量")
	private BigDecimal lackQuantity;
	
	/**
     * 单价
     */
	@NotNull(message="单价不能为空!")
	@ApiModelProperty(required = true, value = "单价")
	private BigDecimal unitPrice;
	
	/**
     * 行折扣（%）
     */
	@NotNull(message="行折扣（%）不能为空!")
	@ApiModelProperty(required = true, value = "行折扣（%）")
	private BigDecimal lineDiscount;
	
	/**
     * 金额（整单优惠前金额）
     */
	@NotNull(message="金额（整单优惠前金额）不能为空!")
	@ApiModelProperty(required = true, value = "金额（整单优惠前金额）")
	private BigDecimal amount;
	
	/**
     * 整单折扣（%）
     */
	@NotNull(message="整单折扣（%）不能为空!")
	@ApiModelProperty(required = true, value = "整单折扣（%）")
	private BigDecimal wholeDiscount;
	
	/**
     * 行优惠（整单优惠分摊到行的金额）
     */
	@NotNull(message="行优惠（整单优惠分摊到行的金额）不能为空!")
	@ApiModelProperty(required = true, value = "行优惠（整单优惠分摊到行的金额）")
	private BigDecimal lineDiscountAmount;
	
	/**
     * 实际单价（实际金额/数量）
     */
	@NotNull(message="实际单价（实际金额/数量）不能为空!")
	@ApiModelProperty(required = true, value = "实际单价（实际金额/数量）")
	private BigDecimal realPrice;
	
	/**
     * 实际金额
     */
	@NotNull(message="实际金额不能为空!")
	@ApiModelProperty(required = true, value = "实际金额")
	private BigDecimal realAmount;
	
	/**
     * 税率ID
     */
	@NotNull(message="税率ID不能为空!")
	@ApiModelProperty(required = true, value = "税率ID")
	private Long taxRateId;
	
	/**
     * 税率
     */
	@NotNull(message="税率不能为空!")
	@ApiModelProperty(required = true, value = "税率")
	private BigDecimal taxRate;
	
	/**
     * 不含税实际单价
     */
	@NotNull(message="不含税实际单价不能为空!")
	@ApiModelProperty(required = true, value = "不含税实际单价")
	private BigDecimal notaxRealPrice;
	
	/**
     * 不含税实际金额
     */
	@NotNull(message="不含税实际金额不能为空!")
	@ApiModelProperty(required = true, value = "不含税实际金额")
	private BigDecimal notaxRealAmount;
	
	/**
     * 税额
     */
	@NotNull(message="税额不能为空!")
	@ApiModelProperty(required = true, value = "税额")
	private BigDecimal taxAmount;
	
	/**
     * 未清数量
     */
	@NotNull(message="未清数量不能为空!")
	@ApiModelProperty(required = true, value = "未清数量")
	private BigDecimal unclearQuantity;
	
	/**
     * 已清数量
     */
	@NotNull(message="已清数量不能为空!")
	@ApiModelProperty(required = true, value = "已清数量")
	private BigDecimal clearQuantity;
	
	/**
     * 行号
     */
	@NotNull(message="行号不能为空!")
	@ApiModelProperty(required = true, value = "行号")
	private Integer lineNum;
	
	/**
     * 状态
     */
	@NotNull(message="状态不能为空!")
	@ApiModelProperty(required = true, value = "状态")
	private Integer status;
	
	/**
     * 备注
     */
	@ApiModelProperty(required = false, value = "备注")
	private String remark;
	

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
	 * 缺配单ID
	 */
	public void setLackId(Long lackId) {
		this.lackId = lackId;
	}
	
	/**
	 * 缺配单ID
	 */
	public Long getLackId() {
		return lackId;
	}
	
	/**
	 * 单据（缺配单）类型（5402）
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * 单据（缺配单）类型（5402）
	 */
	public Integer getOrderType() {
		return orderType;
	}
	
	/**
	 * 缺配单单号
	 */
	public void setLackCode(String lackCode) {
		this.lackCode = lackCode;
	}
	
	/**
	 * 缺配单单号
	 */
	public String getLackCode() {
		return lackCode;
	}
	
	/**
	 * 缺配单日期
	 */
	public void setLackDate(Date lackDate) {
		this.lackDate = lackDate;
	}
	
	/**
	 * 缺配单日期
	 */
	public Date getLackDate() {
		return lackDate;
	}
	
	/**
	 * 基础单据明细ID
	 */
	public void setBaseOrderDtlId(Long baseOrderDtlId) {
		this.baseOrderDtlId = baseOrderDtlId;
	}
	
	/**
	 * 基础单据明细ID
	 */
	public Long getBaseOrderDtlId() {
		return baseOrderDtlId;
	}
	
	/**
	 * 基础单据ID
	 */
	public void setBaseOrderId(Long baseOrderId) {
		this.baseOrderId = baseOrderId;
	}
	
	/**
	 * 基础单据ID
	 */
	public Long getBaseOrderId() {
		return baseOrderId;
	}
	
	/**
	 * 基础单据类型
	 */
	public void setBaseOrderType(Integer baseOrderType) {
		this.baseOrderType = baseOrderType;
	}
	
	/**
	 * 基础单据类型
	 */
	public Integer getBaseOrderType() {
		return baseOrderType;
	}
	
	/**
	 * 基础单据编码
	 */
	public void setBaseOrderCode(String baseOrderCode) {
		this.baseOrderCode = baseOrderCode;
	}
	
	/**
	 * 基础单据编码
	 */
	public String getBaseOrderCode() {
		return baseOrderCode;
	}
	
	/**
	 * 基础单据日期
	 */
	public void setBaseOrderDate(Date baseOrderDate) {
		this.baseOrderDate = baseOrderDate;
	}
	
	/**
	 * 基础单据日期
	 */
	public Date getBaseOrderDate() {
		return baseOrderDate;
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
	 * 要货数量
	 */
	public void setRequestQuantity(BigDecimal requestQuantity) {
		this.requestQuantity = requestQuantity;
	}
	
	/**
	 * 要货数量
	 */
	public BigDecimal getRequestQuantity() {
		return requestQuantity;
	}
	
	/**
	 * 配货数量
	 */
	public void setSendQuantity(BigDecimal sendQuantity) {
		this.sendQuantity = sendQuantity;
	}
	
	/**
	 * 配货数量
	 */
	public BigDecimal getSendQuantity() {
		return sendQuantity;
	}
	
	/**
	 * 缺配数量
	 */
	public void setLackQuantity(BigDecimal lackQuantity) {
		this.lackQuantity = lackQuantity;
	}
	
	/**
	 * 缺配数量
	 */
	public BigDecimal getLackQuantity() {
		return lackQuantity;
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
	 * 税率ID
	 */
	public void setTaxRateId(Long taxRateId) {
		this.taxRateId = taxRateId;
	}
	
	/**
	 * 税率ID
	 */
	public Long getTaxRateId() {
		return taxRateId;
	}
	
	/**
	 * 税率
	 */
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	
	/**
	 * 税率
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