package com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "PurchaseOrderDataVO", description = "采购管理-采购入库-调用采购订单对象")
public class PurchaseOrderDataVO implements Serializable{

	
	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年11月29日 上午11:40:13 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "采购订单主键ID")
    private Long id;
	
	@ApiModelProperty(value = "入库日期")
	private Date orderDate;
	
	@ApiModelProperty(value = "订单编码")
	private String code;
	
	@ApiModelProperty(value = "供货单位ID")
    private Long supplierId;
	
	@ApiModelProperty(value = "供货位单位编码")
    private String supplierCode;
	
	@ApiModelProperty(value = "供货单位名称")
    private String supplierName;
	
	@ApiModelProperty(value = "采购人ID")
    private Long purchaserId;
	
	@ApiModelProperty(value = "采购人名称")
    private String purchaserName;
	
	@ApiModelProperty(value = "采购日期")
    private Date purchaserDate;

	@ApiModelProperty(value = "收货日期")
    private Date receiveDate;
	
	@ApiModelProperty(value = "验收日期")
    private Date checkDate;
	
	@ApiModelProperty(value = "供货单位销售人员ID")
    private Long supplierSalerId;
	
	@ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "配送类型 0-配送；1-委托运输；2-自提")
	private Integer carriageMode;
	
	@ApiModelProperty(value = "配送类型-前台展示用")
	private String carriageModeDesc;
	
	@ApiModelProperty(value = "配送相关信息")
	private PurchaseOrderOtherVO orderOtherVO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Long getPurchaserId() {
		return purchaserId;
	}

	public void setPurchaserId(Long purchaserId) {
		this.purchaserId = purchaserId;
	}

	public String getPurchaserName() {
		return purchaserName;
	}

	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}

	public Date getPurchaserDate() {
		return purchaserDate;
	}

	public void setPurchaserDate(Date purchaserDate) {
		this.purchaserDate = purchaserDate;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Long getSupplierSalerId() {
		return supplierSalerId;
	}

	public void setSupplierSalerId(Long supplierSalerId) {
		this.supplierSalerId = supplierSalerId;
	}

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
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

	public Integer getCarriageMode() {
		return carriageMode;
	}

	public void setCarriageMode(Integer carriageMode) {
		this.carriageMode = carriageMode;
	}

	public String getCarriageModeDesc() {
		if(null != carriageMode) {
			switch (carriageMode) {
			case 0:
				carriageModeDesc = "总部配送";
				break;
			case 1:
				carriageModeDesc = "委托运输";
				break;
			case 2:
				carriageModeDesc = "自提";
				break;	
			default:
				break;
			}
		}
		return carriageModeDesc;
	}

	public void setCarriageModeDesc(String carriageModeDesc) {
		this.carriageModeDesc = carriageModeDesc;
	}

	public PurchaseOrderOtherVO getOrderOtherVO() {
		return orderOtherVO;
	}

	public void setOrderOtherVO(PurchaseOrderOtherVO orderOtherVO) {
		this.orderOtherVO = orderOtherVO;
	}
	

}
