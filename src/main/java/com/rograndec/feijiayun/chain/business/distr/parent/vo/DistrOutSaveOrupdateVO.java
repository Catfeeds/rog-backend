package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

 /**
 * 
 * @ClassName: DistrOutSaveOrupdateVO
 * @Description:  总部-配货出库-配货出库单-Rest接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-07 15:57:42
 */
@ApiModel(value = "DistrOutSaveOrupdateVO", description = "总部-配货出库-配货出库单")
public class DistrOutSaveOrupdateVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;
	
	/**
     * 上级企业ID
     */
	@NotNull(message="上级企业ID不能为空!")
	@ApiModelProperty(required = true, value = "上级企业ID")
	private Long parentId;
	
	/**
     * 单据类型（5412）
     */
	@NotNull(message="单据类型（5412）不能为空!")
	@ApiModelProperty(required = true, value = "单据类型（5412）")
	private Integer orderType;
	
	/**
     * 配货出库单号
     */
	@NotNull(message="配货出库单号不能为空!")
	@ApiModelProperty(required = true, value = "配货出库单号")
	private String code;
	
	/**
     * 配货出库日期
     */
	@NotNull(message="配货出库日期不能为空!")
	@ApiModelProperty(required = true, value = "配货出库日期")
	private Date outDate;
	
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
     * 要货单位ID
     */
	@NotNull(message="要货单位ID不能为空!")
	@ApiModelProperty(required = true, value = "要货单位ID")
	private Long requestUnitId;
	
	/**
     * 要货单位编码
     */
	@NotNull(message="要货单位编码不能为空!")
	@ApiModelProperty(required = true, value = "要货单位编码")
	private String requestUnitCode;
	
	/**
     * 要货单位名称
     */
	@NotNull(message="要货单位名称不能为空!")
	@ApiModelProperty(required = true, value = "要货单位名称")
	private String requestUnitName;
	
	/**
     * 配货人员ID
     */
	@NotNull(message="配货人员ID不能为空!")
	@ApiModelProperty(required = true, value = "配货人员ID")
	private Long sendManId;
	
	/**
     * 配货人员编码
     */
	@NotNull(message="配货人员编码不能为空!")
	@ApiModelProperty(required = true, value = "配货人员编码")
	private String sendManCode;
	
	/**
     * 配货人员名称
     */
	@NotNull(message="配货人员名称不能为空!")
	@ApiModelProperty(required = true, value = "配货人员名称")
	private String sendManName;
	

	@NotNull(message="配送类型（0-总部配送；3-分店间调剂；4-直调配送）不能为空!")
	@ApiModelProperty(required = true, value = "配送类型（0-总部配送；3-分店间调剂；4-直调配送）")
	private Integer distrType;
	
	/**
     * 出库人员ID
     */
	@NotNull(message="出库人员ID不能为空!")
	@ApiModelProperty(required = true, value = "出库人员ID")
	private Long outManId;
	
	/**
     * 出库人员编码
     */
	@NotNull(message="出库人员编码不能为空!")
	@ApiModelProperty(required = true, value = "出库人员编码")
	private String outManCode;
	
	/**
     * 出库人员名称
     */
	@NotNull(message="出库人员名称不能为空!")
	@ApiModelProperty(required = true, value = "出库人员名称")
	private String outManName;
	
	/**
     * 复核人员ID
     */
	@ApiModelProperty(required = false, value = "复核人员ID")
	private Long checkerId;
	
	/**
     * 复核人员编码
     */
	@ApiModelProperty(required = false, value = "复核人员编码")
	private String checkerCode;
	
	/**
     * 复核人员名称
     */
	@ApiModelProperty(required = false, value = "复核人员名称")
	private String checkerName;
	
	/**
     * 流通监管码
     */
	@ApiModelProperty(required = false, value = "流通监管码")
	private String flowCode;
	
	/**
     * 数量合计
     */
	@NotNull(message="数量合计不能为空!")
	@ApiModelProperty(required = true, value = "数量合计")
	private BigDecimal quantityTotal;
	
	/**
     * 品种数量
     */
	@NotNull(message="品种数量不能为空!")
	@ApiModelProperty(required = true, value = "品种数量")
	private Integer varietiesQuantity;
	
	/**
     * 金额合计（整单优惠前的金额合计）
     */
	@NotNull(message="金额合计（整单优惠前的金额合计）不能为空!")
	@ApiModelProperty(required = true, value = "金额合计（整单优惠前的金额合计）")
	private BigDecimal amountTotal;
	
	/**
     * 整单折扣（%）
     */
	@NotNull(message="整单折扣（%）不能为空!")
	@ApiModelProperty(required = true, value = "整单折扣（%）")
	private BigDecimal wholeDiscount;
	
	/**
     * 整单优惠金额
     */
	@NotNull(message="整单优惠金额不能为空!")
	@ApiModelProperty(required = true, value = "整单优惠金额")
	private BigDecimal wholeDiscountAmount;
	
	/**
     * 实际金额合计
     */
	@NotNull(message="实际金额合计不能为空!")
	@ApiModelProperty(required = true, value = "实际金额合计")
	private BigDecimal realAmountTotal;
	
	/**
     * 不含税金额合计
     */
	@NotNull(message="不含税金额合计不能为空!")
	@ApiModelProperty(required = true, value = "不含税金额合计")
	private BigDecimal notaxRealAmountTotal;
	
	/**
     * 税额合计
     */
	@NotNull(message="税额合计不能为空!")
	@ApiModelProperty(required = true, value = "税额合计")
	private BigDecimal taxAmountTotal;
	
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
	 * 单据类型（5412）
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * 单据类型（5412）
	 */
	public Integer getOrderType() {
		return orderType;
	}
	
	/**
	 * 配货出库单号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 配货出库单号
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 配货出库日期
	 */
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	
	/**
	 * 配货出库日期
	 */
	public Date getOutDate() {
		return outDate;
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
	 * 要货单位ID
	 */
	public void setRequestUnitId(Long requestUnitId) {
		this.requestUnitId = requestUnitId;
	}
	
	/**
	 * 要货单位ID
	 */
	public Long getRequestUnitId() {
		return requestUnitId;
	}
	
	/**
	 * 要货单位编码
	 */
	public void setRequestUnitCode(String requestUnitCode) {
		this.requestUnitCode = requestUnitCode;
	}
	
	/**
	 * 要货单位编码
	 */
	public String getRequestUnitCode() {
		return requestUnitCode;
	}
	
	/**
	 * 要货单位名称
	 */
	public void setRequestUnitName(String requestUnitName) {
		this.requestUnitName = requestUnitName;
	}
	
	/**
	 * 要货单位名称
	 */
	public String getRequestUnitName() {
		return requestUnitName;
	}
	
	/**
	 * 配货人员ID
	 */
	public void setSendManId(Long sendManId) {
		this.sendManId = sendManId;
	}
	
	/**
	 * 配货人员ID
	 */
	public Long getSendManId() {
		return sendManId;
	}
	
	/**
	 * 配货人员编码
	 */
	public void setSendManCode(String sendManCode) {
		this.sendManCode = sendManCode;
	}
	
	/**
	 * 配货人员编码
	 */
	public String getSendManCode() {
		return sendManCode;
	}
	
	/**
	 * 配货人员名称
	 */
	public void setSendManName(String sendManName) {
		this.sendManName = sendManName;
	}
	
	/**
	 * 配货人员名称
	 */
	public String getSendManName() {
		return sendManName;
	}
	
	/**
	 * 配送类型（0-总部配送；1-分店间调剂；2-直调配送）
	 */
	public void setDistrType(Integer distrType) {
		this.distrType = distrType;
	}
	
	/**
	 * 配送类型（0-总部配送；1-分店间调剂；2-直调配送）
	 */
	public Integer getDistrType() {
		return distrType;
	}
	
	/**
	 * 出库人员ID
	 */
	public void setOutManId(Long outManId) {
		this.outManId = outManId;
	}
	
	/**
	 * 出库人员ID
	 */
	public Long getOutManId() {
		return outManId;
	}
	
	/**
	 * 出库人员编码
	 */
	public void setOutManCode(String outManCode) {
		this.outManCode = outManCode;
	}
	
	/**
	 * 出库人员编码
	 */
	public String getOutManCode() {
		return outManCode;
	}
	
	/**
	 * 出库人员名称
	 */
	public void setOutManName(String outManName) {
		this.outManName = outManName;
	}
	
	/**
	 * 出库人员名称
	 */
	public String getOutManName() {
		return outManName;
	}
	
	/**
	 * 复核人员ID
	 */
	public void setCheckerId(Long checkerId) {
		this.checkerId = checkerId;
	}
	
	/**
	 * 复核人员ID
	 */
	public Long getCheckerId() {
		return checkerId;
	}
	
	/**
	 * 复核人员编码
	 */
	public void setCheckerCode(String checkerCode) {
		this.checkerCode = checkerCode;
	}
	
	/**
	 * 复核人员编码
	 */
	public String getCheckerCode() {
		return checkerCode;
	}
	
	/**
	 * 复核人员名称
	 */
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	
	/**
	 * 复核人员名称
	 */
	public String getCheckerName() {
		return checkerName;
	}
	
	/**
	 * 流通监管码
	 */
	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}
	
	/**
	 * 流通监管码
	 */
	public String getFlowCode() {
		return flowCode;
	}
	
	/**
	 * 数量合计
	 */
	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}
	
	/**
	 * 数量合计
	 */
	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}
	
	/**
	 * 品种数量
	 */
	public void setVarietiesQuantity(Integer varietiesQuantity) {
		this.varietiesQuantity = varietiesQuantity;
	}
	
	/**
	 * 品种数量
	 */
	public Integer getVarietiesQuantity() {
		return varietiesQuantity;
	}
	
	/**
	 * 金额合计（整单优惠前的金额合计）
	 */
	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}
	
	/**
	 * 金额合计（整单优惠前的金额合计）
	 */
	public BigDecimal getAmountTotal() {
		return amountTotal;
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
	 * 整单优惠金额
	 */
	public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
		this.wholeDiscountAmount = wholeDiscountAmount;
	}
	
	/**
	 * 整单优惠金额
	 */
	public BigDecimal getWholeDiscountAmount() {
		return wholeDiscountAmount;
	}
	
	/**
	 * 实际金额合计
	 */
	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
	}
	
	/**
	 * 实际金额合计
	 */
	public BigDecimal getRealAmountTotal() {
		return realAmountTotal;
	}
	
	/**
	 * 不含税金额合计
	 */
	public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
		this.notaxRealAmountTotal = notaxRealAmountTotal;
	}
	
	/**
	 * 不含税金额合计
	 */
	public BigDecimal getNotaxRealAmountTotal() {
		return notaxRealAmountTotal;
	}
	
	/**
	 * 税额合计
	 */
	public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
		this.taxAmountTotal = taxAmountTotal;
	}
	
	/**
	 * 税额合计
	 */
	public BigDecimal getTaxAmountTotal() {
		return taxAmountTotal;
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