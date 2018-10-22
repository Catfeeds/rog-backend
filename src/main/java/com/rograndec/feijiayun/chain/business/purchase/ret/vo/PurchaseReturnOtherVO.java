package com.rograndec.feijiayun.chain.business.purchase.ret.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述：
 * Created by ST on 2017/9/15 15:59
 */

public class PurchaseReturnOtherVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 购进退出单ID
     */
    @ApiModelProperty(value = "购进退出单ID")
    private Long returnId;

    /**
     * 结算方式（0-现结；1-账期）
     */
    @ApiModelProperty(value = "结算方式（0-现结；1-账期）")
    private Integer settlementType;

    /**
     * 结算单位
     */
    @ApiModelProperty(value = "结算单位")
    private String settlementUnit;

    /**
     * 结算单位电话
     */
    @ApiModelProperty(value = "结算单位电话")
    private String settlementUnitPhone;

    /**
     * 结算单位地址
     */
    @ApiModelProperty(value = "结算单位地址")
    private String settlementUnitAddress;

    /**
     * 开户户名
     */
    @ApiModelProperty(value = "开户户名")
    private String accountName;

    /**
     * 开户账号
     */
    @ApiModelProperty(value = "开户账号")
    private String account;

    /**
     * 开户银行
     */
    @ApiModelProperty(value = "开户银行")
    private String bank;

    /**
     * 发票类型（0-普通发票；1-增值税发票）
     */
    @ApiModelProperty(value = "发票类型（0-普通发票；1-增值税发票）")
    private Integer invoiceType;

    /**
     * 纳税人识别号
     */
    @ApiModelProperty(value = "纳税人识别号")
    private String taxpayerCode;

    /**
     * 承运方式（0-配送；1-委托运输；2-自提）
     */
    @ApiModelProperty(value = "承运方式（0-配送；1-委托运输；2-自提）")
    private Integer carriageMode;

    /**
     * 承运单位
     */
    @ApiModelProperty(value = "承运单位")
    private String carriageUnit;

    /**
     * 运输方式（0-陆运；1-空运；2-海运）
     */
    @ApiModelProperty(value = "运输方式（0-陆运；1-空运；2-海运）")
    private Integer transportMode;

    /**
     * 温控方式（0-冷藏车；1-冷藏箱；2-保温箱）
     */
    @ApiModelProperty(value = "温控方式（0-冷藏车；1-冷藏箱；2-保温箱）")
    private Integer tempControlMode;

    /**
     * 收货单位
     */
    @ApiModelProperty(value = "收货单位")
    private String receiveUnit;

    /**
     * 收货人员
     */
    @ApiModelProperty(value = "收货人员")
    private String receiver;

    /**
     * 收货人员电话
     */
    @ApiModelProperty(value = "收货人员电话")
    private String receivePhone;

    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址")
    private String receiveAddress;

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 订单单号
     */
    @ApiModelProperty(value = "订单单号")
    private String orderCode;

    /**
     * 订单日期
     */
    @ApiModelProperty(value = "订单日期")
    private Date orderDate;

    /**
     * 出库标志（0-未出库；1-已出库）
     */
    @ApiModelProperty(value = "出库标志（0-未出库；1-已出库）")
    private Integer outFlag;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public Integer getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(Integer settlementType) {
        this.settlementType = settlementType;
    }

    public String getSettlementUnit() {
        return settlementUnit;
    }

    public void setSettlementUnit(String settlementUnit) {
        this.settlementUnit = settlementUnit;
    }

    public String getSettlementUnitPhone() {
        return settlementUnitPhone;
    }

    public void setSettlementUnitPhone(String settlementUnitPhone) {
        this.settlementUnitPhone = settlementUnitPhone;
    }

    public String getSettlementUnitAddress() {
        return settlementUnitAddress;
    }

    public void setSettlementUnitAddress(String settlementUnitAddress) {
        this.settlementUnitAddress = settlementUnitAddress;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getTaxpayerCode() {
        return taxpayerCode;
    }

    public void setTaxpayerCode(String taxpayerCode) {
        this.taxpayerCode = taxpayerCode;
    }

    public Integer getCarriageMode() {
        return carriageMode;
    }

    public void setCarriageMode(Integer carriageMode) {
        this.carriageMode = carriageMode;
    }

    public String getCarriageUnit() {
        return carriageUnit;
    }

    public void setCarriageUnit(String carriageUnit) {
        this.carriageUnit = carriageUnit;
    }

    public Integer getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(Integer transportMode) {
        this.transportMode = transportMode;
    }

    public Integer getTempControlMode() {
        return tempControlMode;
    }

    public void setTempControlMode(Integer tempControlMode) {
        this.tempControlMode = tempControlMode;
    }

    public String getReceiveUnit() {
        return receiveUnit;
    }

    public void setReceiveUnit(String receiveUnit) {
        this.receiveUnit = receiveUnit;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOutFlag() {
        return outFlag;
    }

    public void setOutFlag(Integer outFlag) {
        this.outFlag = outFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}