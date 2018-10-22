package com.rograndec.feijiayun.chain.business.purchase.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import io.swagger.annotations.ApiModelProperty;

public class PurchaseOrderResponseVO implements Serializable {
	 /**
     * 主键ID
     */
	@ApiModelProperty(value = "主键ID")
    private Long id;
	 /**
     * 订单日期
     */
	@ApiModelProperty(value = "订单日期")
    private Date orderDate;
	
	/**
	 * 企业名称
	 */
	@ApiModelProperty(value = "企业名称")
	private String enterpriseName;
	
    /**
     * 订单编码
     * */
	@ApiModelProperty(value="订单编码")
	private String code;
    /**
     * 单据状态
     */
	@ApiModelProperty(value = "单据状态")
    private Integer status;

	@ApiModelProperty(value = "单据状态名称")
	private String  statusName;
    /**
     * 供货单位ID
     */
	@ApiModelProperty(value = "备注")
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
	@ApiModelProperty(value = "供货单位销售人员ID")
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
	@ApiModelProperty(value = " 供货单位销售人员联系电话")
    private String supplierSalerPhone;

    /**
     * 数量合计
     */
	@ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
	@ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 金额合计（整单优惠前的金额合计）
     */
	@ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
	@ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;
	
	/**
     * 整单折扣金额
     */
	@ApiModelProperty(value = "整单折扣金额")
    private BigDecimal wholeDisAmount;

    /**
     * 整单优惠金额
     */
	@ApiModelProperty(value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount;

    /**
     * 实际金额合计
     */
	@ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
	@ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
	@ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 采购人ID
     */
	@ApiModelProperty(value = "采购人ID")
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
     * 订单详情
     * */
	@ApiModelProperty(value = "订单详情")
    private List<PurchaseOrderDetailResponseVO> purchaseOrderDetailResponseVO;
    /**
     * 采购订单配送和结算
     * */
	@ApiModelProperty(value = "采购订单配送和结算")
    private PurchaseOrderOtherResponseVO purchaseOrderOtherResponseVO;

	/**
	 * 配送类型描述（总部配送；委托运输；自提）
	 */
	@ApiModelProperty(value = "配送类型描述（总部配送；委托运输；自提）")
	private String carriageModeName;


	/**
     * saas_purchase_order
     */
    private static final long serialVersionUID = 1L;


	public String getStatusName() {
		return PurchaseStatus.getName(status);
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

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

	public List<PurchaseOrderDetailResponseVO> getPurchaseOrderDetailResponseVO() {
		return purchaseOrderDetailResponseVO;
	}

	public void setPurchaseOrderDetailResponseVO(List<PurchaseOrderDetailResponseVO> purchaseOrderDetailResponseVO) {
		this.purchaseOrderDetailResponseVO = purchaseOrderDetailResponseVO;
	}

	public PurchaseOrderOtherResponseVO getPurchaseOrderOtherResponseVO() {
		return purchaseOrderOtherResponseVO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setPurchaseOrderOtherResponseVO(PurchaseOrderOtherResponseVO purchaseOrderOtherResponseVO) {
		this.purchaseOrderOtherResponseVO = purchaseOrderOtherResponseVO;
	}

	public String getCarriageModeName() {
		return carriageModeName;
	}

	public void setCarriageModeName(String carriageModeName) {
		this.carriageModeName = carriageModeName;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public BigDecimal getWholeDisAmount() {
		return wholeDisAmount;
	}

	public void setWholeDisAmount(BigDecimal wholeDisAmount) {
		this.wholeDisAmount = wholeDisAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}