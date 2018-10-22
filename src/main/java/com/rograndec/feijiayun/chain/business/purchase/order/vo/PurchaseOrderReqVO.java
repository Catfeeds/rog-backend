package com.rograndec.feijiayun.chain.business.purchase.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class PurchaseOrderReqVO implements Serializable {
	 /**
     * 主键ID
     */
	@ApiModelProperty(value = "主键ID")
    private Long id;
    /**
     * 订单日期
     */
	@ApiModelProperty(value = "订单日期",required = true)
    private Date orderDate;

    /**
     * 供货单位ID
     */
	@ApiModelProperty(value = "供货单位ID",required = true)
    private Long supplierId;

    /**
     * 供货位单位编码
     */
	@ApiModelProperty(value = "供货位单位编码")
    private String supplierCode;

    /**
     * 供货单位名称
     */
	@ApiModelProperty(value = "供货单位名称")
    private String supplierName;

    /**
     * 供货单位销售人员ID
     */
	@ApiModelProperty(value = "供货单位销售人员ID",required = true)
    private Long supplierSalerId;

    /**
     * 供货单位销售人员编码
     */
	@ApiModelProperty(value = "供货单位销售人员编码")
    private String supplierSalerCode;

    /**
     * 供货单位销售人员名称
     */
	@ApiModelProperty(value = "供货单位销售人员名称")
    private String supplierSalerName;

    /**
     * 供货单位销售人员联系电话
     */
	@ApiModelProperty(value = "供货单位销售人员联系电话")
    private String supplierSalerPhone;

    /**
     * 数量合计
     */
	@ApiModelProperty(value = "数量合计",required = true)
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
	@ApiModelProperty(value = "品种数量",required=true)
    private Integer varietiesQuantity;

    /**
     * 金额合计（整单优惠前的金额合计）
     */
	@ApiModelProperty(value = "金额合计（整单优惠前的金额合计）",required = true)
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
	@ApiModelProperty(value = "整单折扣（%）",required = true)
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
	@ApiModelProperty(value = "整单优惠金额",required = true)
    private BigDecimal wholeDiscountAmount;

    /**
     * 实际金额合计
     */
	@ApiModelProperty(value = "实际金额合计",required = true)
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
	@ApiModelProperty(value = "不含税金额合计",required = true)
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
	@ApiModelProperty(value = "税额合计",required = true)
    private BigDecimal taxAmountTotal;

    /**
     * 采购人ID
     */
	@ApiModelProperty(value = "采购人ID",required = true)
    private Long purchaserId;

    /**
     * 采购人编码
     */
	@ApiModelProperty(value = "采购人编码")
    private String purchaserCode;

    /**
     * 采购人名称
     */
	@ApiModelProperty(value = "采购人名称")
    private String purchaserName;

    /**
     * 预计到货日期
     */
	@ApiModelProperty(value = "预计到货日期")
    private Date arrivalTime;


    /**
     * 备注
     */
	@ApiModelProperty(value = "备注")
    private String remark;


    /**
     * 订单详情列表
     * */
	@ApiModelProperty(value = "订单详情列表")
    private List<PurchaseOrderDetailRequestVO> purchaseOrderDetailRequestVO;
    /**
     * 采购订单配送和结算
     * */
	@ApiModelProperty(value = "采购订单配送和结算")
    private PurchaseOrderOtherRequestVO PurchaseOrderOtherRequestVO;
    
    /**
     * 修改原因
     * */
	@ApiModelProperty(value = "修改原因")
    private String reason;
	/**
	 * 草稿ID
	 * */
	@ApiModelProperty(value = "草稿ID")
	private String redisKeyValue;
    /**
     * saas_purchase_order
     */
    private static final long serialVersionUID = 1L;

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Long getSupplierSalerId() {
		return supplierSalerId;
	}

	public void setSupplierSalerId(Long supplierSalerId) {
		this.supplierSalerId = supplierSalerId;
	}

	public String getSupplierSalerCode() {
		return supplierSalerCode;
	}

	public void setSupplierSalerCode(String supplierSalerCode) {
		this.supplierSalerCode = supplierSalerCode;
	}

	public String getSupplierSalerName() {
		return supplierSalerName;
	}

	public void setSupplierSalerName(String supplierSalerName) {
		this.supplierSalerName = supplierSalerName;
	}

	public String getSupplierSalerPhone() {
		return supplierSalerPhone;
	}

	public void setSupplierSalerPhone(String supplierSalerPhone) {
		this.supplierSalerPhone = supplierSalerPhone;
	}

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public Integer getVarietiesQuantity() {
		return varietiesQuantity;
	}

	public void setVarietiesQuantity(Integer varietiesQuantity) {
		this.varietiesQuantity = varietiesQuantity;
	}

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	public BigDecimal getWholeDiscount() {
		return wholeDiscount;
	}

	public void setWholeDiscount(BigDecimal wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}

	public BigDecimal getWholeDiscountAmount() {
		return wholeDiscountAmount;
	}

	public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
		this.wholeDiscountAmount = wholeDiscountAmount;
	}

	public BigDecimal getRealAmountTotal() {
		return realAmountTotal;
	}

	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
	}

	public BigDecimal getNotaxRealAmountTotal() {
		return notaxRealAmountTotal;
	}

	public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
		this.notaxRealAmountTotal = notaxRealAmountTotal;
	}

	public BigDecimal getTaxAmountTotal() {
		return taxAmountTotal;
	}

	public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
		this.taxAmountTotal = taxAmountTotal;
	}

	public Long getPurchaserId() {
		return purchaserId;
	}

	public void setPurchaserId(Long purchaserId) {
		this.purchaserId = purchaserId;
	}

	public String getPurchaserCode() {
		return purchaserCode;
	}

	public void setPurchaserCode(String purchaserCode) {
		this.purchaserCode = purchaserCode;
	}

	public String getPurchaserName() {
		return purchaserName;
	}

	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<PurchaseOrderDetailRequestVO> getPurchaseOrderDetailRequestVO() {
		return purchaseOrderDetailRequestVO;
	}

	public void setPurchaseOrderDetailRequestVO(List<PurchaseOrderDetailRequestVO> purchaseOrderDetailRequestVO) {
		this.purchaseOrderDetailRequestVO = purchaseOrderDetailRequestVO;
	}

	public PurchaseOrderOtherRequestVO getPurchaseOrderOtherRequestVO() {
		return PurchaseOrderOtherRequestVO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPurchaseOrderOtherRequestVO(PurchaseOrderOtherRequestVO purchaseOrderOtherRequestVO) {
		PurchaseOrderOtherRequestVO = purchaseOrderOtherRequestVO;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRedisKeyValue() {
		return redisKeyValue;
	}

	public void setRedisKeyValue(String redisKeyValue) {
		this.redisKeyValue = redisKeyValue;
	}
	

}