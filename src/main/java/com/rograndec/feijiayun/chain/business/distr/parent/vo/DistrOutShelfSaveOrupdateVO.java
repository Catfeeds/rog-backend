package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: DistrOutShelfSaveOrupdateVO
 * @Description:  总部-配货出库-配货出库货位明细-Rest接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-07 15:58:23
 */
@ApiModel(value = "DistrOutShelfSaveOrupdateVO", description = "总部-配货出库-配货出库货位明细")
public class DistrOutShelfSaveOrupdateVO implements Serializable {
	
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
     * 配货出库明细ID
     */
	@NotNull(message="配货出库明细ID不能为空!")
	@ApiModelProperty(required = true, value = "配货出库明细ID")
	private Long dtlId;
	
	/**
     * 配货出库单ID
     */
	@NotNull(message="配货出库单ID不能为空!")
	@ApiModelProperty(required = true, value = "配货出库单ID")
	private Long outId;
	
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
     * 商品名称
     */
	@NotNull(message="商品名称不能为空!")
	@ApiModelProperty(required = true, value = "商品名称")
	private String goodsName;
	
	/**
     * 批号ID
     */
	@NotNull(message="批号ID不能为空!")
	@ApiModelProperty(required = true, value = "批号ID")
	private Long lotId;
	
	/**
     * 批号
     */
	@NotNull(message="批号不能为空!")
	@ApiModelProperty(required = true, value = "批号")
	private String lotNumber;
	
	/**
     * 生产日期
     */
	@NotNull(message="生产日期不能为空!")
	@ApiModelProperty(required = true, value = "生产日期")
	private Date productDate;
	
	/**
     * 有效期
     */
	@NotNull(message="有效期不能为空!")
	@ApiModelProperty(required = true, value = "有效期")
	private Date validDate;
	
	/**
     * 货位ID
     */
	@NotNull(message="货位ID不能为空!")
	@ApiModelProperty(required = true, value = "货位ID")
	private Long shelfId;
	
	/**
     * 货位名称
     */
	@NotNull(message="货位名称不能为空!")
	@ApiModelProperty(required = true, value = "货位名称")
	private String shelfName;
	
	/**
     * 货位质量状态描述
     */
	@NotNull(message="货位质量状态描述不能为空!")
	@ApiModelProperty(required = true, value = "货位质量状态描述")
	private String shelfStatusDesc;
	
	/**
     * 数量
     */
	@NotNull(message="数量不能为空!")
	@ApiModelProperty(required = true, value = "数量")
	private BigDecimal quantity;
	
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
	 * 配货出库明细ID
	 */
	public void setDtlId(Long dtlId) {
		this.dtlId = dtlId;
	}
	
	/**
	 * 配货出库明细ID
	 */
	public Long getDtlId() {
		return dtlId;
	}
	
	/**
	 * 配货出库单ID
	 */
	public void setOutId(Long outId) {
		this.outId = outId;
	}
	
	/**
	 * 配货出库单ID
	 */
	public Long getOutId() {
		return outId;
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
	 * 批号ID
	 */
	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}
	
	/**
	 * 批号ID
	 */
	public Long getLotId() {
		return lotId;
	}
	
	/**
	 * 批号
	 */
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	
	/**
	 * 批号
	 */
	public String getLotNumber() {
		return lotNumber;
	}
	
	/**
	 * 生产日期
	 */
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	
	/**
	 * 生产日期
	 */
	public Date getProductDate() {
		return productDate;
	}
	
	/**
	 * 有效期
	 */
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	
	/**
	 * 有效期
	 */
	public Date getValidDate() {
		return validDate;
	}
	
	/**
	 * 货位ID
	 */
	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}
	
	/**
	 * 货位ID
	 */
	public Long getShelfId() {
		return shelfId;
	}
	
	/**
	 * 货位名称
	 */
	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
	
	/**
	 * 货位名称
	 */
	public String getShelfName() {
		return shelfName;
	}
	
	/**
	 * 货位质量状态描述
	 */
	public void setShelfStatusDesc(String shelfStatusDesc) {
		this.shelfStatusDesc = shelfStatusDesc;
	}
	
	/**
	 * 货位质量状态描述
	 */
	public String getShelfStatusDesc() {
		return shelfStatusDesc;
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