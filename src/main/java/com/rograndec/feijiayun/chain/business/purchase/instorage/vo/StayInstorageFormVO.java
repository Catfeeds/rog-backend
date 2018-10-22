package com.rograndec.feijiayun.chain.business.purchase.instorage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheck;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "StayInstoragePageVO", description = "采购管理-采购入库-待入库分页对象")
public class StayInstorageFormVO implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
     * 验收单ID
     */
	@ApiModelProperty(required = true, value = "验收单ID")
    private Long id;
	
    /**
     * 供货单位编码
     */
	@ApiModelProperty(required = true, value = "供货单位编码")
    private String supplierCode;
	
	/**
     * 供货单位名称
     */
	@ApiModelProperty(required = true, value = "供货单位名称")
    private String supplierName;
    
	/**
     * 入库人员ID
     */
	@ApiModelProperty(required = true, value = "入库人员ID")
    private Long storageManId;
	
    /**
     * 入库人员名称
     */
	@ApiModelProperty(required = true, value = "入库人员名称")
    private String storageManName;
	
	/**
	 * 入库日期
	 */
	@ApiModelProperty(required = true, value = "入库日期")
    private Date inStorageDate;
	
	/**
     * 供货单位销售人员ID
     */
	@ApiModelProperty(required = true, value = "供货单位销售人员名称")
    private Long supplierSalerId;
	
	/**
     * 供货单位销售人员名称
     */
	@ApiModelProperty(required = true, value = "供货单位销售人员名称")
    private String supplierSalerName;
	
	/**
     * 供货单位销售人员电话
     */
	@ApiModelProperty(required = true, value = "供货单位销售人员电话")
    private String supplierSalerPhone;
	
	/**
	 * 流通监管码
	 */
	@ApiModelProperty(required = true, value = "流通监管码")
    private String flowCode;
	
	/**
	 * 数量总计
	 */
	@ApiModelProperty(required = true, value = "数量总计")
	private BigDecimal quantityTotal;
	
	/**
	 * 金额合计
	 */
	@ApiModelProperty(required = true, value = "总商品金额")
	private BigDecimal amountTotal;
	
	/**
	 * 整单折扣
	 */
	@ApiModelProperty(required = true, value = "整单折扣")
	private BigDecimal wholeDiscount;
	
	/**
	 * 整单折扣金额
	 */
	@ApiModelProperty(required = true, value = "整单折扣金额")
	private BigDecimal wholeDiscountMoney;
	
	/**
	 * 整单优惠金额
	 */
	@ApiModelProperty(required = true, value = "整单优惠金额")
	private BigDecimal wholeDiscountAmount;
	
	/**
	 * 实际金额合计
	 */
	@ApiModelProperty(required = true, value = "金额")
	private BigDecimal realAmountTotal;
	
	/**
	 * 不含税金额合计
	 */
	@ApiModelProperty(required = true, value = "不含税总额")
	private BigDecimal notaxRealAmountTotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getStorageManId() {
		return storageManId;
	}

	public void setStorageManId(Long storageManId) {
		this.storageManId = storageManId;
	}

	public String getStorageManName() {
		return storageManName;
	}

	public void setStorageManName(String storageManName) {
		this.storageManName = storageManName;
	}

	public Date getInStorageDate() {
		return inStorageDate;
	}

	public void setInStorageDate(Date inStorageDate) {
		this.inStorageDate = inStorageDate;
	}

	public Long getSupplierSalerId() {
		return supplierSalerId;
	}

	public void setSupplierSalerId(Long supplierSalerId) {
		this.supplierSalerId = supplierSalerId;
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

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
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
	
	public BigDecimal getWholeDiscountMoney() {
		return wholeDiscountMoney;
	}

	public void setWholeDiscountMoney(BigDecimal wholeDiscountMoney) {
		this.wholeDiscountMoney = wholeDiscountMoney;
	}

	public static StayInstorageFormVO setVoByPurchaseCheck(PurchaseCheck check, UserVO loginUser) {
		StayInstorageFormVO vo = new StayInstorageFormVO();
		
		vo.setId(check.getId());
		vo.setSupplierCode(check.getSupplierCode());
		vo.setSupplierName(check.getSupplierName());
		vo.setStorageManId(loginUser.getUserId());
		vo.setStorageManName(loginUser.getUserName());
		vo.setInStorageDate(new Date());
		vo.setSupplierSalerId(check.getSupplierSalerId());
		vo.setSupplierSalerName(check.getSupplierSalerName());
		vo.setSupplierSalerPhone(check.getSupplierSalerPhone());
		vo.setFlowCode("");
		vo.setQuantityTotal(check.getQuantityTotal());
		
		return vo;
	}
	
	
}
