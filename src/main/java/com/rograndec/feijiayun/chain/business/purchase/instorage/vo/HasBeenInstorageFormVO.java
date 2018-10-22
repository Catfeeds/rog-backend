package com.rograndec.feijiayun.chain.business.purchase.instorage.vo;

import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApiModel(value = "HasBeenInstorageFormVO", description = "采购管理-采购入库-已入库查看表单对象")
public class HasBeenInstorageFormVO implements Serializable{

    private static final long serialVersionUID = 1L;

    //采购入库基础信息
    /**
     * 主键ID
     */
    @ApiModelProperty(required = true, value = "主键ID")
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
     * 供货单位销售人员名称
     */
    @ApiModelProperty(required = true, value = "供货单位销售人员名称")
    private String supplierSalerName;

    /**
     * 供货单位销售人员联系电话
     */
    @ApiModelProperty(required = true, value = "供货单位销售人员联系电话")
    private String supplierSalerPhone;

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
     * 流通监管码
     */
    @ApiModelProperty(required = true, value = "流通监管码")
    private String flowCode;

    /**
     * 入库明细相关信息
     */
    @ApiModelProperty(required = true, value = "入库明细相关信息")
    private List<PurchaseInstorageDetailVO> purchaseInstorageDetailVO;

    /**
     * 配送和结算相关信息
     */
    @ApiModelProperty(required = true, value = "配送和结算相关信息")
    private PurchaseOrderOtherVO purchaseOrderOtherVO;

    /**
     * 右下角订单金额相关信息（采购入库一级表的信息）
     */

    /**
     * 数量合计
     */
    @ApiModelProperty(required = true, value = "品种数量")
    private BigDecimal quantityTotal;

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
     * 扣除折扣后金额
     */
    @ApiModelProperty(required = true, value = "扣除折扣后金额")
    private BigDecimal wholeDiscountAfter;

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

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public PurchaseOrderOtherVO getPurchaseOrderOtherVO() {
        return purchaseOrderOtherVO;
    }

    public void setPurchaseOrderOtherVO(PurchaseOrderOtherVO purchaseOrderOtherVO) {
        this.purchaseOrderOtherVO = purchaseOrderOtherVO;
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

    public List<PurchaseInstorageDetailVO> getPurchaseInstorageDetailVO() {
        return purchaseInstorageDetailVO;
    }

    public void setPurchaseInstorageDetailVO(List<PurchaseInstorageDetailVO> purchaseInstorageDetailVO) {
        this.purchaseInstorageDetailVO = purchaseInstorageDetailVO;
    }

    public BigDecimal  getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public BigDecimal getWholeDiscountAfter() {
        return wholeDiscountAfter;
    }

    public void setWholeDiscountAfter(BigDecimal wholeDiscountAfter) {
        this.wholeDiscountAfter = wholeDiscountAfter;
    }

    @Override
    public String toString() {
        return "HasBeenInstorageFormVO{" +
                "id=" + id +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierSalerName='" + supplierSalerName + '\'' +
                ", supplierSalerPhone='" + supplierSalerPhone + '\'' +
                ", storageManName='" + storageManName + '\'' +
                ", inStorageDate=" + inStorageDate +
                ", flowCode='" + flowCode + '\'' +
                ", purchaseOrderOtherVO=" + purchaseOrderOtherVO +
                ", amountTotal=" + amountTotal +
                ", wholeDiscount=" + wholeDiscount +
                ", wholeDiscountAmount=" + wholeDiscountAmount +
                ", realAmountTotal=" + realAmountTotal +
                ", notaxRealAmountTotal=" + notaxRealAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                '}';
    }

    public static HasBeenInstorageFormVO convertToPurchaseInStorageVO(PurchaseInStorage purchase) {
        HasBeenInstorageFormVO vo = new HasBeenInstorageFormVO();
        vo.setId(purchase.getId());
        vo.setSupplierCode(purchase.getSupplierCode());
        vo.setSupplierName(purchase.getSupplierName());
        vo.setSupplierSalerName(purchase.getSupplierSalerName());
        vo.setSupplierSalerPhone(purchase.getSupplierSalerPhone());
        vo.setStorageManName(purchase.getStorageManName());
        vo.setInStorageDate(purchase.getInStorageDate());
        vo.setFlowCode(purchase.getFlowCode());
        vo.setAmountTotal(purchase.getAmountTotal());
        vo.setWholeDiscount(purchase.getWholeDiscount());
        vo.setWholeDiscountAmount(purchase.getWholeDiscountAmount());
        vo.setRealAmountTotal(purchase.getRealAmountTotal());
        vo.setNotaxRealAmountTotal(purchase.getNotaxRealAmountTotal());
        vo.setTaxAmountTotal(purchase.getTaxAmountTotal());
        vo.setQuantityTotal(purchase.getQuantityTotal());
        BigDecimal percent = new BigDecimal(100);
        vo.setWholeDiscountAfter(purchase.getAmountTotal()
                .subtract(purchase.getAmountTotal()
                        .multiply(purchase.getWholeDiscount()
                                .divide(percent,2, BigDecimal.ROUND_HALF_UP))));
        return vo;
    }
}
