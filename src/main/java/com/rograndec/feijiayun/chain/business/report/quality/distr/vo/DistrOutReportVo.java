package com.rograndec.feijiayun.chain.business.report.quality.distr.vo;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsDetailVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DistrOutReportVo extends BaseGoodsDetailVO implements Serializable{
	/**
	 * 配货日期
	 */
	@ApiModelProperty(value = "配货出库日期")
	private Date outDate;
	private String outDateStr;
	/**
	 * 缺配单号
	 */
	@ApiModelProperty(value = "出库单号")
	private String code;
	/**
	 * 要货单位编码
	 */
	@ApiModelProperty(value = "要货单位编码")
	private String requestUnitCode;

	/**
	 * 要货单位名称
	 */
	@ApiModelProperty(value = "要货单位名称")
	private String requestUnitName;
	/**
	 * 出库人员名称
	 */
	@ApiModelProperty(value = "出库人员名称")
	private String outManName;
	/**
	 * 复核人员名称
	 */
	@ApiModelProperty(value = "复核人员名称")
	private String checkerName;
	/**
	 * 流通监管码
	 */
	@ApiModelProperty(value = "流通监管码")
	private String flowCode;

	/**
	 * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
	 */
	@ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
	private Integer distrType;
	private String distrTypeStr;

	/**
	 * 基础单据编码
	 */
	@ApiModelProperty(value = "基础单据编码-配货单号")
	private String baseOrderCode;
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
	private String productDateStr;

	/**
	 * 有效期
	 */
	@ApiModelProperty(value = "有效期")
	private Date validDate;
	private String validDateStr;

	/**
	 * 货位名称
	 */
	@ApiModelProperty(value = "货位名称")
	private String shelfName;

	/**
	 * 货位质量状态描述
	 */
	@ApiModelProperty(value = "货位质量状态描述")
	private String shelfStatusDesc;
	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量")
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
	 * 税率
	 */
	@ApiModelProperty(value = "税率")
	private BigDecimal taxRate;

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
	 * 状态
	 */
	@ApiModelProperty(value ="状态（33-待复核；34-已完成）")
	private Integer status;
	private String statusStr;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRequestUnitCode() {
		return requestUnitCode;
	}

	public void setRequestUnitCode(String requestUnitCode) {
		this.requestUnitCode = requestUnitCode;
	}

	public String getRequestUnitName() {
		return requestUnitName;
	}

	public void setRequestUnitName(String requestUnitName) {
		this.requestUnitName = requestUnitName;
	}

	public Integer getDistrType() {
		return distrType;
	}

	public void setDistrType(Integer distrType) {
		this.distrType = distrType;
	}

	public String getDistrTypeStr() {
		if(this.distrType==null){
			return "";
		}
		//配货类型（0-总部配送；3-分店间调剂；4-直调配送）
		if(this.distrType==0){
			return "总部配送";
		}
		if(this.distrType==3){
			return "分店间调剂";
		}
		if(this.distrType==4){
			return "直调配送";
		}
		return distrTypeStr;
	}

	public void setDistrTypeStr(String distrTypeStr) {
		this.distrTypeStr = distrTypeStr;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusStr() {
		//"33-待复核；34-已完成
		if(this.status==null){
			return "";
		}
		if(this.status==33){
			return "待复核";
		}
		if(this.status==34){
			return "已完成";
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getBaseOrderCode() {
		return baseOrderCode;
	}

	public void setBaseOrderCode(String baseOrderCode) {
		this.baseOrderCode = baseOrderCode;
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

	public BigDecimal getWholeDiscount() {
		return wholeDiscount;
	}

	public void setWholeDiscount(BigDecimal wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}

	public BigDecimal getLineDiscountAmount() {
		return lineDiscountAmount;
	}

	public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
		this.lineDiscountAmount = lineDiscountAmount;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getNotaxRealPrice() {
		return notaxRealPrice;
	}

	public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
		this.notaxRealPrice = notaxRealPrice;
	}

	public BigDecimal getNotaxRealAmount() {
		return notaxRealAmount;
	}

	public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
		this.notaxRealAmount = notaxRealAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getOutDateStr() {
		return DateUtils.DateToString(this.outDate,"yyyy-MM-dd");
	}

	public void setOutDateStr(String outDateStr) {
		this.outDateStr = outDateStr;
	}

	public String getOutManName() {
		return outManName;
	}

	public void setOutManName(String outManName) {
		this.outManName = outManName;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
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

	public String getProductDateStr() {
		return DateUtils.DateToString(this.productDate,"yyyy-MM-dd");
	}

	public void setProductDateStr(String productDateStr) {
		this.productDateStr = productDateStr;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getValidDateStr() {
		return DateUtils.DateToString(this.validDate,"yyyy-MM-dd");
	}

	public void setValidDateStr(String validDateStr) {
		this.validDateStr = validDateStr;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public String getShelfStatusDesc() {
		return shelfStatusDesc;
	}

	public void setShelfStatusDesc(String shelfStatusDesc) {
		this.shelfStatusDesc = shelfStatusDesc;
	}
}
