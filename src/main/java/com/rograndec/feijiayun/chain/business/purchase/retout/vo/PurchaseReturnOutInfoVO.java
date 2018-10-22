package com.rograndec.feijiayun.chain.business.purchase.retout.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：购进退出出库实体
 * Created by ST on 2017/9/16 10:25
 */

public class PurchaseReturnOutInfoVO implements Serializable {


    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "采购入库ID,只有勾选采购入库才会传值")
    private Long inStorageId;

    /**
     * 出库人员ID
     */
    @ApiModelProperty(value = "出库人员ID")
    private Long outManId;

    @ApiModelProperty(value = "出库日期")
    private Date outDate;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID")
    private Long supplierId;
    @ApiModelProperty(value = "供货单位编码")
    private String supplierCode;
    @ApiModelProperty(value = "供货单位名称")
    private String supplierName;

    @ApiModelProperty(value = "供货单位销售人员ID")
    private Long supplierSalerId;
    @ApiModelProperty(value = "供货单位销售人员编码")
    private String supplierSalerCode;
    @ApiModelProperty(value = "供货单位销售人员名称")
    private String supplierSalerName;
    @ApiModelProperty(value = "供货单位销售人员联系电话")
    private String supplierSalerPhone;

    @ApiModelProperty(value = "退货类型（0-质量问题退货；1-非质量问题退货）")
    private Integer returnType;
    @ApiModelProperty(value = "退货人员Id")
    private Long returnManId;
    @ApiModelProperty(value = "退货日期")
    private Date returnDate;
    @ApiModelProperty(value = "联系电话")
    private String telPhone;

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
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount;

    @ApiModelProperty(value = "订单明细")
    private List<PurchaseReturnDetailOutVO> purchaseReturnDetailVOList;
    @ApiModelProperty(value = "配送和结算")
    private PurchaseReturnOutOtherVO purchaseReturnOtherVO;


    public List<PurchaseReturnDetailOutVO> getPurchaseReturnDetailVOList() {
        return purchaseReturnDetailVOList;
    }

    public void setPurchaseReturnDetailVOList(List<PurchaseReturnDetailOutVO> purchaseReturnDetailVOList) {
        this.purchaseReturnDetailVOList = purchaseReturnDetailVOList;
    }

    public PurchaseReturnOutOtherVO getPurchaseReturnOtherVO() {
        return purchaseReturnOtherVO;
    }

    public void setPurchaseReturnOtherVO(PurchaseReturnOutOtherVO purchaseReturnOtherVO) {
        this.purchaseReturnOtherVO = purchaseReturnOtherVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOutManId() {
        return outManId;
    }

    public void setOutManId(Long outManId) {
        this.outManId = outManId;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getSupplierSalerId() {
        return supplierSalerId;
    }

    public void setSupplierSalerId(Long supplierSalerId) {
        this.supplierSalerId = supplierSalerId;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public Long getReturnManId() {
        return returnManId;
    }

    public void setReturnManId(Long returnManId) {
        this.returnManId = returnManId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public Long getInStorageId() {
        return inStorageId;
    }

    public void setInStorageId(Long inStorageId) {
        this.inStorageId = inStorageId;
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
}