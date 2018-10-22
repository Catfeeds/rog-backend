package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

 /**
 * 
 * @ClassName: DistrLackVO
 * @Description:  总部-配货出库-缺配单
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-07 15:58:51
 */
@ApiModel(value = "DistrLackVO", description = "总部-配货出库-缺配单")
public class DistrLackVO implements Serializable {
	
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
     * 单据类型（5402）
     */
	@ApiModelProperty(required = true, value = "单据类型（5402）")
	private Integer orderType;
	
	/**
     * 缺配单号
     */
	@ApiModelProperty(required = true, value = "缺配单号")
	private String code;
	
	/**
     * 配货日期
     */
	@ApiModelProperty(required = true, value = "配货日期")
	private Date sendDate;
	
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
	@ApiModelProperty(required = true, value = "要货单位ID")
	private Long requestUnitId;
	
	/**
     * 要货单位编码
     */
	@ApiModelProperty(required = true, value = "要货单位编码")
	private String requestUnitCode;
	
	/**
     * 要货单位名称
     */
	@ApiModelProperty(required = true, value = "要货单位名称")
	private String requestUnitName;
	
	/**
     * 要货人员ID
     */
	@ApiModelProperty(required = false, value = "要货人员ID")
	private Long requesterId;
	
	/**
     * 要货人员编码
     */
	@ApiModelProperty(required = false, value = "要货人员编码")
	private String requesterCode;
	
	/**
     * 要货人员名称
     */
	@ApiModelProperty(required = false, value = "要货人员名称")
	private String requesterName;

	 /**
	  * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
	  */
	@ApiModelProperty(required = true, value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
	private Integer distrType;
	
	/**
     * 要货计划ID
     */
	@ApiModelProperty(required = false, value = "要货计划ID")
	private Long requestPlanId;
	
	/**
     * 要货计划单号
     */
	@ApiModelProperty(required = false, value = "要货计划单号")
	private String requestPlanCode;
	
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
     * 数量合计
     */
	@ApiModelProperty(required = true, value = "数量合计")
	private BigDecimal quantityTotal;
	
	/**
     * 品种数量
     */
	@ApiModelProperty(required = true, value = "品种数量")
	private Integer varietiesQuantity;
	
	/**
     * 金额合计（整单优惠前的金额合计）
     */
	@ApiModelProperty(required = true, value = "金额合计（整单优惠前的金额合计）")
	private BigDecimal amountTotal;
	
	/**
     * 整单折扣（%）
     */
	@ApiModelProperty(required = true, value = "整单折扣（%）")
	private BigDecimal wholeDiscount;
	
	/**
     * 整单优惠金额
     */
	@ApiModelProperty(required = true, value = "整单优惠金额")
	private BigDecimal wholeDiscountAmount;
	
	/**
     * 实际金额合计
     */
	@ApiModelProperty(required = true, value = "实际金额合计")
	private BigDecimal realAmountTotal;
	
	/**
     * 不含税金额合计
     */
	@ApiModelProperty(required = true, value = "不含税金额合计")
	private BigDecimal notaxRealAmountTotal;
	
	/**
     * 税额合计
     */
	@ApiModelProperty(required = true, value = "税额合计")
	private BigDecimal taxAmountTotal;
	
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
	 * 单据类型（5402）
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * 单据类型（5402）
	 */
	public Integer getOrderType() {
		return orderType;
	}
	
	/**
	 * 缺配单号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 缺配单号
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 配货日期
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	/**
	 * 配货日期
	 */
	public Date getSendDate() {
		return sendDate;
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
	 * 要货人员ID
	 */
	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}
	
	/**
	 * 要货人员ID
	 */
	public Long getRequesterId() {
		return requesterId;
	}
	
	/**
	 * 要货人员编码
	 */
	public void setRequesterCode(String requesterCode) {
		this.requesterCode = requesterCode;
	}
	
	/**
	 * 要货人员编码
	 */
	public String getRequesterCode() {
		return requesterCode;
	}
	
	/**
	 * 要货人员名称
	 */
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}
	
	/**
	 * 要货人员名称
	 */
	public String getRequesterName() {
		return requesterName;
	}
	
	/**
	 * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
	 */
	public void setDistrType(Integer distrType) {
		this.distrType = distrType;
	}
	
	/**
	 * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
	 */
	public Integer getDistrType() {
		return distrType;
	}
	
	/**
	 * 要货计划ID
	 */
	public void setRequestPlanId(Long requestPlanId) {
		this.requestPlanId = requestPlanId;
	}
	
	/**
	 * 要货计划ID
	 */
	public Long getRequestPlanId() {
		return requestPlanId;
	}
	
	/**
	 * 要货计划单号
	 */
	public void setRequestPlanCode(String requestPlanCode) {
		this.requestPlanCode = requestPlanCode;
	}
	
	/**
	 * 要货计划单号
	 */
	public String getRequestPlanCode() {
		return requestPlanCode;
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