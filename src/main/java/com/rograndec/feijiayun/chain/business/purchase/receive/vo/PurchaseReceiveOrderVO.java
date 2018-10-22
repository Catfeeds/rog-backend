package com.rograndec.feijiayun.chain.business.purchase.receive.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PurchaseReceiveOrderVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(required = true, value = "订单ID")
    private Long id;

    /**
     * 订单类型（5211）
     */
    @ApiModelProperty(required = true, value = "订单类型（5211）")
    private Integer orderType;

    /**
     * 订单日期
     */
    @ApiModelProperty(required = true, value = "订单日期")
    private Date orderDate;

    /**
     * 订单单号
     */
    @ApiModelProperty(required = true, value = "订单单号")
    private String code;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(required = true, value = "供货单位ID")
    private Long supplierId;

    /**
     * 供货位单位编码
     */
    @ApiModelProperty(required = true, value = "供货位单位编码")
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(required = true, value = "供货单位名称")
    private String supplierName;

    /**
     * 供货单位销售人员ID
     */
    @ApiModelProperty(required = true, value = "供货单位销售人员ID")
    private Long supplierSalerId;

    /**
     * 供货单位销售人员编码
     */
    @ApiModelProperty(required = true, value = "供货单位销售人员编码")
    private String supplierSalerCode;

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
     * 利润合计
     */
    @ApiModelProperty(required = true, value = "利润合计")
    private BigDecimal profitTotal;

    /**
     * 不含税利润合计
     */
    @ApiModelProperty(required = true, value = "不含税利润合计")
    private BigDecimal notaxProfitTotal;

    /**
     * 利润率
     */
    @ApiModelProperty(required = true, value = "利润率")
    private BigDecimal profitRate;

    /**
     * 不含税利润率
     */
    @ApiModelProperty(required = true, value = "不含税利润率")
    private BigDecimal notaxProfitRate;

    /**
     * 采购人ID
     */
    @ApiModelProperty(required = true, value = "采购人ID")
    private Long purchaserId;

    /**
     * 采购人编码
     */
    @ApiModelProperty(required = true, value = "采购人编码")
    private String purchaserCode;

    /**
     * 采购人名称
     */
    @ApiModelProperty(required = true, value = "采购人名称")
    private String purchaserName;

    /**
     * 预计到货日期
     */
    @ApiModelProperty(required = true, value = "预计到货日期")
    private Date arrivalTime;

    /**
     * 收货标志（0-未收获；1-已收货）
     */
    @ApiModelProperty(required = true, value = "收货标志（0-未收获；1-已收货）")
    private Integer receiveFlag;

    /**
     * 单据状态
     */
    @ApiModelProperty(required = true, value = "单据状态")
    private Integer status;

    /**
     * 单据状态名
     */
    @ApiModelProperty(required = true, value = "单据状态名")
    private String statusName;

    /**
     * 备注
     */
    @ApiModelProperty(required = true, value = "备注")
    private String remark;

    /**
     * GSP管控
     */
    @ApiModelProperty(required = true, value = "GSP管控")
    private Boolean gspFlag;

    /**
     * 收货人员ID
     */
    @ApiModelProperty(required = true, value = "收货人员ID")
    private Long receiverId;

    /**
     * 收货人员编码
     */
    @ApiModelProperty(required = true, value = "收货人员编码")
    private String receiverCode;

    /**
     * 收货人员名称
     */
    @ApiModelProperty(required = true, value = "收货人员名称")
    private String receiverName;

    /**
     * 收货人员ID
     */
    @ApiModelProperty(required = true, value = "收货人员ID2")
    private Long secondReceiverId;

    /**
     * 收货人员编码
     */
    @ApiModelProperty(required = true, value = "收货人员编码2")
    private String secondReceiverCode;

    /**
     * 收货人员名称
     */
    @ApiModelProperty(required = true, value = "收货人员名称2")
    private String secondReceiverName;


    /**
     * 是否为特殊管理药品：（0-是  1-否）
     */
    @ApiModelProperty(value = "是否为特殊管理药品：（0:不是,1是特殊药品）", required = true)
    private Integer specialDrug = 0;


    public Integer getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
    }
    /**
     * 配货类型（0-配送；1-委托运输；2-自提；3-直配）
     */
    @ApiModelProperty(required = true, value = "配货类型（0-配送；1-委托运输；2-自提；3-直配）")
    private Integer carriageMode;

    /**
     * 配货类型名
     */
    @ApiModelProperty(required = true, value = "配货类型名")
    private String carriageModeName;


    /**
     * saas_purchase_order
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 订单类型（5211）
     * @return order_type 订单类型（5211）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 订单类型（5211）
     * @param orderType 订单类型（5211）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 订单日期
     * @return order_date 订单日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * 订单日期
     * @param orderDate 订单日期
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 订单单号
     * @return code 订单单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 订单单号
     * @param code 订单单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 供货单位ID
     * @return supplier_id 供货单位ID
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * 供货单位ID
     * @param supplierId 供货单位ID
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * 供货位单位编码
     * @return supplier_code 供货位单位编码
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * 供货位单位编码
     * @param supplierCode 供货位单位编码
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode == null ? null : supplierCode.trim();
    }

    /**
     * 供货单位名称
     * @return supplier_name 供货单位名称
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * 供货单位名称
     * @param supplierName 供货单位名称
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    /**
     * 供货单位销售人员ID
     * @return supplier_saler_id 供货单位销售人员ID
     */
    public Long getSupplierSalerId() {
        return supplierSalerId;
    }

    /**
     * 供货单位销售人员ID
     * @param supplierSalerId 供货单位销售人员ID
     */
    public void setSupplierSalerId(Long supplierSalerId) {
        this.supplierSalerId = supplierSalerId;
    }

    /**
     * 供货单位销售人员编码
     * @return supplier_saler_code 供货单位销售人员编码
     */
    public String getSupplierSalerCode() {
        return supplierSalerCode;
    }

    /**
     * 供货单位销售人员编码
     * @param supplierSalerCode 供货单位销售人员编码
     */
    public void setSupplierSalerCode(String supplierSalerCode) {
        this.supplierSalerCode = supplierSalerCode == null ? null : supplierSalerCode.trim();
    }

    /**
     * 供货单位销售人员名称
     * @return supplier_saler_name 供货单位销售人员名称
     */
    public String getSupplierSalerName() {
        return supplierSalerName;
    }

    /**
     * 供货单位销售人员名称
     * @param supplierSalerName 供货单位销售人员名称
     */
    public void setSupplierSalerName(String supplierSalerName) {
        this.supplierSalerName = supplierSalerName == null ? null : supplierSalerName.trim();
    }

    /**
     * 供货单位销售人员联系电话
     * @return supplier_saler_phone 供货单位销售人员联系电话
     */
    public String getSupplierSalerPhone() {
        return supplierSalerPhone;
    }

    /**
     * 供货单位销售人员联系电话
     * @param supplierSalerPhone 供货单位销售人员联系电话
     */
    public void setSupplierSalerPhone(String supplierSalerPhone) {
        this.supplierSalerPhone = supplierSalerPhone == null ? null : supplierSalerPhone.trim();
    }

    /**
     * 数量合计
     * @return quantity_total 数量合计
     */
    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    /**
     * 数量合计
     * @param quantityTotal 数量合计
     */
    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    /**
     * 品种数量
     * @return varieties_quantity 品种数量
     */
    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    /**
     * 品种数量
     * @param varietiesQuantity 品种数量
     */
    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @return amount_total 金额合计（整单优惠前的金额合计）
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @param amountTotal 金额合计（整单优惠前的金额合计）
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 整单折扣（%）
     * @return whole_discount 整单折扣（%）
     */
    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    /**
     * 整单折扣（%）
     * @param wholeDiscount 整单折扣（%）
     */
    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    /**
     * 整单优惠金额
     * @return whole_discount_amount 整单优惠金额
     */
    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    /**
     * 整单优惠金额
     * @param wholeDiscountAmount 整单优惠金额
     */
    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    /**
     * 实际金额合计
     * @return real_amount_total 实际金额合计
     */
    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    /**
     * 实际金额合计
     * @param realAmountTotal 实际金额合计
     */
    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_real_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxRealAmountTotal 不含税金额合计
     */
    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    /**
     * 税额合计
     * @return tax_amount_total 税额合计
     */
    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    /**
     * 税额合计
     * @param taxAmountTotal 税额合计
     */
    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    /**
     * 利润合计
     * @return profit_total 利润合计
     */
    public BigDecimal getProfitTotal() {
        return profitTotal;
    }

    /**
     * 利润合计
     * @param profitTotal 利润合计
     */
    public void setProfitTotal(BigDecimal profitTotal) {
        this.profitTotal = profitTotal;
    }

    /**
     * 不含税利润合计
     * @return notax_profit_total 不含税利润合计
     */
    public BigDecimal getNotaxProfitTotal() {
        return notaxProfitTotal;
    }

    /**
     * 不含税利润合计
     * @param notaxProfitTotal 不含税利润合计
     */
    public void setNotaxProfitTotal(BigDecimal notaxProfitTotal) {
        this.notaxProfitTotal = notaxProfitTotal;
    }

    /**
     * 利润率
     * @return profit_rate 利润率
     */
    public BigDecimal getProfitRate() {
        return profitRate;
    }

    /**
     * 利润率
     * @param profitRate 利润率
     */
    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    /**
     * 不含税利润率
     * @return notax_profit_rate 不含税利润率
     */
    public BigDecimal getNotaxProfitRate() {
        return notaxProfitRate;
    }

    /**
     * 不含税利润率
     * @param notaxProfitRate 不含税利润率
     */
    public void setNotaxProfitRate(BigDecimal notaxProfitRate) {
        this.notaxProfitRate = notaxProfitRate;
    }

    /**
     * 采购人ID
     * @return purchaser_id 采购人ID
     */
    public Long getPurchaserId() {
        return purchaserId;
    }

    /**
     * 采购人ID
     * @param purchaserId 采购人ID
     */
    public void setPurchaserId(Long purchaserId) {
        this.purchaserId = purchaserId;
    }

    /**
     * 采购人编码
     * @return purchaser_code 采购人编码
     */
    public String getPurchaserCode() {
        return purchaserCode;
    }

    /**
     * 采购人编码
     * @param purchaserCode 采购人编码
     */
    public void setPurchaserCode(String purchaserCode) {
        this.purchaserCode = purchaserCode == null ? null : purchaserCode.trim();
    }

    /**
     * 采购人名称
     * @return purchaser_name 采购人名称
     */
    public String getPurchaserName() {
        return purchaserName;
    }

    /**
     * 采购人名称
     * @param purchaserName 采购人名称
     */
    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName == null ? null : purchaserName.trim();
    }

    /**
     * 预计到货日期
     * @return arrival_time 预计到货日期
     */
    public Date getArrivalTime() {
        return arrivalTime;
    }

    /**
     * 预计到货日期
     * @param arrivalTime 预计到货日期
     */
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * 收货标志（0-未收获；1-已收货）
     * @return receive_flag 收货标志（0-未收获；1-已收货）
     */
    public Integer getReceiveFlag() {
        return receiveFlag;
    }

    /**
     * 收货标志（0-未收获；1-已收货）
     * @param receiveFlag 收货标志（0-未收获；1-已收货）
     */
    public void setReceiveFlag(Integer receiveFlag) {
        this.receiveFlag = receiveFlag;
    }

    /**
     * 单据状态
     * @return status 单据状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 单据状态
     * @param status 单据状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Boolean getGspFlag() {
        return gspFlag;
    }

    public void setGspFlag(Boolean gspFlag) {
        this.gspFlag = gspFlag;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverCode() {
        return receiverCode;
    }

    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Integer getCarriageMode() {
        return carriageMode;
    }

    public void setCarriageMode(Integer carriageMode) {
        this.carriageMode = carriageMode;
    }

    public String getCarriageModeName() {
        return carriageModeName;
    }

    public void setCarriageModeName(String carriageModeName) {
        this.carriageModeName = carriageModeName;
    }

    public Long getSecondReceiverId() {
        return secondReceiverId;
    }

    public void setSecondReceiverId(Long secondReceiverId) {
        this.secondReceiverId = secondReceiverId;
    }

    public String getSecondReceiverCode() {
        return secondReceiverCode;
    }

    public void setSecondReceiverCode(String secondReceiverCode) {
        this.secondReceiverCode = secondReceiverCode;
    }

    public String getSecondReceiverName() {
        return secondReceiverName;
    }

    public void setSecondReceiverName(String secondReceiverName) {
        this.secondReceiverName = secondReceiverName;
    }

    @Override
    public String toString() {
        return "PurchaseReceiveOrderVO{" +
                "id=" + id +
                ", orderType=" + orderType +
                ", orderDate=" + orderDate +
                ", code='" + code + '\'' +
                ", supplierId=" + supplierId +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierSalerId=" + supplierSalerId +
                ", supplierSalerCode='" + supplierSalerCode + '\'' +
                ", supplierSalerName='" + supplierSalerName + '\'' +
                ", supplierSalerPhone='" + supplierSalerPhone + '\'' +
                ", quantityTotal=" + quantityTotal +
                ", varietiesQuantity=" + varietiesQuantity +
                ", amountTotal=" + amountTotal +
                ", wholeDiscount=" + wholeDiscount +
                ", wholeDiscountAmount=" + wholeDiscountAmount +
                ", realAmountTotal=" + realAmountTotal +
                ", notaxRealAmountTotal=" + notaxRealAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", profitTotal=" + profitTotal +
                ", notaxProfitTotal=" + notaxProfitTotal +
                ", profitRate=" + profitRate +
                ", notaxProfitRate=" + notaxProfitRate +
                ", purchaserId=" + purchaserId +
                ", purchaserCode='" + purchaserCode + '\'' +
                ", purchaserName='" + purchaserName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", receiveFlag=" + receiveFlag +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", remark='" + remark + '\'' +
                ", gspFlag=" + gspFlag +
                ", receiverId=" + receiverId +
                ", receiverCode='" + receiverCode + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", carriageMode='" + carriageMode + '\'' +
                ", carriageModeName='" + carriageModeName + '\'' +
                '}';
    }
}