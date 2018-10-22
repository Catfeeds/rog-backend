package com.rograndec.feijiayun.chain.business.purchase.retout.vo;

import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnOther;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述：
 * Created by ST on 2017/9/15 15:59
 */

public class PurchaseReturnOutOtherVO implements Serializable {
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
     * 委托经办人
     */
    @ApiModelProperty(value = "委托经办人")
    private String agent;

    /**
     * 随货通行单号
     */
    @ApiModelProperty(value = "随货通行单号")
    private String freightPass;

    /**
     * 发货地址
     */
    @ApiModelProperty(value = "发货地址")
    private String sendAddress;

    /**
     * 发货人员
     */
    @ApiModelProperty(value = "发货人员")
    private String sender;

    /**
     * 件数
     */
    @ApiModelProperty(value = "件数")
    private Integer number;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String carNumber;

    /**
     * 驾驶员
     */
    @ApiModelProperty(value = "驾驶员")
    private String driver;

    /**
     * 驾驶证号
     */
    @ApiModelProperty(value = "驾驶证号")
    private String driverLicenseCode;

    /**
     * 到货时限
     */
    @ApiModelProperty(value = "到货时限")
    private Integer limitTime;

    /**
     * 到货时限单位（0-小时；1-天）
     */
    @ApiModelProperty(value = "到货时限单位（0-小时；1-天）")
    private Integer limitTimeUnit;

    /**
     * 启运时间
     */
    @ApiModelProperty(value = "启运时间")
    private Date shipmentTime;

    /**
     * 启运温度（℃）
     */
    @ApiModelProperty(value = "启运温度（℃）")
    private Integer shipmentTemp;

    /**
     * 启运湿度（%）
     */
    @ApiModelProperty(value = "启运湿度（%）")
    private Integer shipmentHumidity;

    /**
     * 到货时间
     */
    @ApiModelProperty(value = "到货时间")
    private Date arrivalTime;

    /**
     * 在途温度起始值（℃）
     */
    @ApiModelProperty(value = "在途温度起始值（℃）")
    private Integer transitTempStart;

    /**
     * 在途温度结束值（℃）
     */
    @ApiModelProperty(value = "在途温度结束值（℃）")
    private Integer transitTempEnd;

    /**
     * 在途湿度起始值（%）
     */
    @ApiModelProperty(value = "在途湿度起始值（%）")
    private Integer transitHumidityStart;

    /**
     * 在途湿度结束值（%）
     */
    @ApiModelProperty(value = "在途湿度结束值（%）")
    private Integer transitHumidityEnd;

    /**
     * 到货温度（℃）
     */
    @ApiModelProperty(value = "到货温度（℃）")
    private Integer arrivalTemp;

    /**
     * 到货湿度（%）
     */
    @ApiModelProperty(value = "到货湿度（%）")
    private Integer arrivalHumidity;


    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址")
    private String receiveAddress;

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
     * 全程温湿度数据（附件ID）
     */
    @ApiModelProperty(value = "全程温湿度数据（附件ID）")
    private Long fileId;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public static PurchaseReturnOutOtherVO getPurchaseReturnOutOtherVO(PurchaseReturnOther purchaseReturnOther){

        PurchaseReturnOutOtherVO purchaseReturnOutOtherVO = new PurchaseReturnOutOtherVO();
        /**
         * 主键ID
         */
        purchaseReturnOutOtherVO.setId(purchaseReturnOther.getId());

        /**
         * 购进退出单ID
         */
        purchaseReturnOutOtherVO.setReturnId(purchaseReturnOther.getReturnId());

        /**
         * 结算方式（0-现结；1-账期）
         */
        purchaseReturnOutOtherVO.setSettlementType(purchaseReturnOther.getSettlementType());

        /**
         * 结算单位
         */
        purchaseReturnOutOtherVO.setSettlementUnit(purchaseReturnOther.getSettlementUnit());

        /**
         * 结算单位电话
         */
        purchaseReturnOutOtherVO.setSettlementUnitPhone(purchaseReturnOther.getSettlementUnitPhone());

        /**
         * 结算单位地址
         */
        purchaseReturnOutOtherVO.setSettlementUnitAddress(purchaseReturnOther.getSettlementUnitAddress());

        /**
         * 收货地址
         */
        purchaseReturnOutOtherVO.setReceiveAddress(purchaseReturnOther.getReceiveAddress());

        /**
         * 收货人员
         */
        purchaseReturnOutOtherVO.setReceiver(purchaseReturnOther.getReceiver());

        /**
         * 收货人员电话
         */
        purchaseReturnOutOtherVO.setReceivePhone(purchaseReturnOther.getReceivePhone());

        return purchaseReturnOutOtherVO;

    }

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

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getFreightPass() {
        return freightPass;
    }

    public void setFreightPass(String freightPass) {
        this.freightPass = freightPass;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriverLicenseCode() {
        return driverLicenseCode;
    }

    public void setDriverLicenseCode(String driverLicenseCode) {
        this.driverLicenseCode = driverLicenseCode;
    }

    public Integer getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }

    public Integer getLimitTimeUnit() {
        return limitTimeUnit;
    }

    public void setLimitTimeUnit(Integer limitTimeUnit) {
        this.limitTimeUnit = limitTimeUnit;
    }

    public Date getShipmentTime() {
        return shipmentTime;
    }

    public void setShipmentTime(Date shipmentTime) {
        this.shipmentTime = shipmentTime;
    }

    public Integer getShipmentTemp() {
        return shipmentTemp;
    }

    public void setShipmentTemp(Integer shipmentTemp) {
        this.shipmentTemp = shipmentTemp;
    }

    public Integer getShipmentHumidity() {
        return shipmentHumidity;
    }

    public void setShipmentHumidity(Integer shipmentHumidity) {
        this.shipmentHumidity = shipmentHumidity;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getTransitTempStart() {
        return transitTempStart;
    }

    public void setTransitTempStart(Integer transitTempStart) {
        this.transitTempStart = transitTempStart;
    }

    public Integer getTransitTempEnd() {
        return transitTempEnd;
    }

    public void setTransitTempEnd(Integer transitTempEnd) {
        this.transitTempEnd = transitTempEnd;
    }

    public Integer getTransitHumidityStart() {
        return transitHumidityStart;
    }

    public void setTransitHumidityStart(Integer transitHumidityStart) {
        this.transitHumidityStart = transitHumidityStart;
    }

    public Integer getTransitHumidityEnd() {
        return transitHumidityEnd;
    }

    public void setTransitHumidityEnd(Integer transitHumidityEnd) {
        this.transitHumidityEnd = transitHumidityEnd;
    }

    public Integer getArrivalTemp() {
        return arrivalTemp;
    }

    public void setArrivalTemp(Integer arrivalTemp) {
        this.arrivalTemp = arrivalTemp;
    }

    public Integer getArrivalHumidity() {
        return arrivalHumidity;
    }

    public void setArrivalHumidity(Integer arrivalHumidity) {
        this.arrivalHumidity = arrivalHumidity;
    }


    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
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

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}