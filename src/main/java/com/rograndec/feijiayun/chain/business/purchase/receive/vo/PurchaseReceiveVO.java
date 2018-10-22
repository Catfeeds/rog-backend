package com.rograndec.feijiayun.chain.business.purchase.receive.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PurchaseReceiveVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(required = true, value = "验收验收单ID")
    private Long id;

    /**
     * 单据类型（5212）
     */
    @ApiModelProperty(required = true, value = "单据类型")
    private Integer orderType;

    /**
     * 收货单号
     */
    @ApiModelProperty(required = true, value = "收货单号")

    private String code;

    /**
     * 收货日期
     */
    @ApiModelProperty(required = true, value = "收货日期")
    private Date receiveDate;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(required = true, value = "基础单据ID")
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(required = true, value = "基础单据类型")
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(required = true, value = "基础单据编码")
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(required = true, value = "基础单据日期")
    private Date baseOrderDate;

    /**
     * 企业名称
     */
    @ApiModelProperty(required = true, value = "企业名称")
    private String enterpriseName;
    
    /**
     * 供货单位ID
     */
    @ApiModelProperty(required = true, value = "供货单位ID")
    private Long supplierId;

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
     * 收货人2ID
     */
    @ApiModelProperty(required = true, value = "收货人2ID")
    private Long secondReceiverId;

    /**
     * 收货人2编码
     */
    @ApiModelProperty(required = true, value = "收货人2编码")
    private String secondReceiverCode;

    /**
     * 收货人2名称
     */
    @ApiModelProperty(required = true, value = "收货人2名称")
    private String secondReceiverName;

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
     * 状态
     */
    @ApiModelProperty(required = true, value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(required = true, value = "备注")
    private String remark;

    /**
     * 状态名
     */
    @ApiModelProperty(required = true, value = "状态名")
    private String statusName;

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
     * 结算单位是否是加盟店（0-否  1-是）
     */
    @ApiModelProperty(required = true, value = "结算单位是否是加盟店（0-否  1-是）")
    private Integer franchisedStoreFlag;
    
    /**
     * 到货数量
     */
    @ApiModelProperty(required = true, value = "到货数量合计")
    private BigDecimal arrivalQuantityTotal;

    /**
     * 收货数量
     */
    @ApiModelProperty(required = true, value = "收货数量合计")
    private BigDecimal receiveQuantityTotal;

    /**
     * 拒收数量
     */
    @ApiModelProperty(required = true, value = "拒收数量合计")
    private BigDecimal refuseQuantityTotal;


    /**
     * saas_purchase_receive
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
     * 单据类型（5212）
     * @return order_type 单据类型（5212）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5212）
     * @param orderType 单据类型（5212）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 收货单号
     * @return code 收货单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 收货单号
     * @param code 收货单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 收货日期
     * @return receive_date 收货日期
     */
    public Date getReceiveDate() {
        return receiveDate;
    }

    /**
     * 收货日期
     * @param receiveDate 收货日期
     */
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * 基础单据ID
     * @return base_order_id 基础单据ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 基础单据ID
     * @param baseOrderId 基础单据ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 基础单据类型
     * @return base_order_type 基础单据类型
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 基础单据类型
     * @param baseOrderType 基础单据类型
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 基础单据编码
     * @return base_order_code 基础单据编码
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 基础单据编码
     * @param baseOrderCode 基础单据编码
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 基础单据日期
     * @return base_order_date 基础单据日期
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 基础单据日期
     * @param baseOrderDate 基础单据日期
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
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
     * 供货单位编码
     * @return supplier_code 供货单位编码
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * 供货单位编码
     * @param supplierCode 供货单位编码
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
     * 收货人员ID
     * @return receiver_id 收货人员ID
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * 收货人员ID
     * @param receiverId 收货人员ID
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * 收货人员编码
     * @return receiver_code 收货人员编码
     */
    public String getReceiverCode() {
        return receiverCode;
    }

    /**
     * 收货人员编码
     * @param receiverCode 收货人员编码
     */
    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode == null ? null : receiverCode.trim();
    }

    /**
     * 收货人员名称
     * @return receiver_name 收货人员名称
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 收货人员名称
     * @param receiverName 收货人员名称
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    /**
     * 收货人2ID
     * @return second_receiver_id 收货人2ID
     */
    public Long getSecondReceiverId() {
        return secondReceiverId;
    }

    /**
     * 收货人2ID
     * @param secondReceiverId 收货人2ID
     */
    public void setSecondReceiverId(Long secondReceiverId) {
        this.secondReceiverId = secondReceiverId;
    }

    /**
     * 收货人2编码
     * @return second_receiver_code 收货人2编码
     */
    public String getSecondReceiverCode() {
        return secondReceiverCode;
    }

    /**
     * 收货人2编码
     * @param secondReceiverCode 收货人2编码
     */
    public void setSecondReceiverCode(String secondReceiverCode) {
        this.secondReceiverCode = secondReceiverCode == null ? null : secondReceiverCode.trim();
    }

    /**
     * 收货人2名称
     * @return second_receiver_name 收货人2名称
     */
    public String getSecondReceiverName() {
        return secondReceiverName;
    }

    /**
     * 收货人2名称
     * @param secondReceiverName 收货人2名称
     */
    public void setSecondReceiverName(String secondReceiverName) {
        this.secondReceiverName = secondReceiverName == null ? null : secondReceiverName.trim();
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
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public Integer getFranchisedStoreFlag() {
        return franchisedStoreFlag;
    }

    public void setFranchisedStoreFlag(Integer franchisedStoreFlag) {
        this.franchisedStoreFlag = franchisedStoreFlag;
    }
    
    public BigDecimal getArrivalQuantityTotal() {
		return arrivalQuantityTotal;
	}

	public void setArrivalQuantityTotal(BigDecimal arrivalQuantityTotal) {
		this.arrivalQuantityTotal = arrivalQuantityTotal;
	}

	public BigDecimal getReceiveQuantityTotal() {
		return receiveQuantityTotal;
	}

	public void setReceiveQuantityTotal(BigDecimal receiveQuantityTotal) {
		this.receiveQuantityTotal = receiveQuantityTotal;
	}

	public BigDecimal getRefuseQuantityTotal() {
		return refuseQuantityTotal;
	}

	public void setRefuseQuantityTotal(BigDecimal refuseQuantityTotal) {
		this.refuseQuantityTotal = refuseQuantityTotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	/**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "PurchaseReceiveVO{" +
                "id=" + id +
                ", orderType=" + orderType +
                ", code='" + code + '\'' +
                ", receiveDate=" + receiveDate +
                ", baseOrderId=" + baseOrderId +
                ", baseOrderType=" + baseOrderType +
                ", baseOrderCode='" + baseOrderCode + '\'' +
                ", baseOrderDate=" + baseOrderDate +
                ", supplierId=" + supplierId +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierSalerId=" + supplierSalerId +
                ", supplierSalerCode='" + supplierSalerCode + '\'' +
                ", supplierSalerName='" + supplierSalerName + '\'' +
                ", supplierSalerPhone='" + supplierSalerPhone + '\'' +
                ", receiverId=" + receiverId +
                ", receiverCode='" + receiverCode + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", secondReceiverId=" + secondReceiverId +
                ", secondReceiverCode='" + secondReceiverCode + '\'' +
                ", secondReceiverName='" + secondReceiverName + '\'' +
                ", quantityTotal=" + quantityTotal +
                ", varietiesQuantity=" + varietiesQuantity +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", statusName='" + statusName + '\'' +
                ", carriageMode=" + carriageMode +
                ", carriageModeName='" + carriageModeName + '\'' +
                ", franchisedStoreFlag=" + franchisedStoreFlag +
                '}';
    }
}