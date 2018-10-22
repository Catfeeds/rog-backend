package com.rograndec.feijiayun.chain.business.purchase.retout.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
 
/**
 * 
 * saas_purchase_return_out_other
 * 
 * 
 * @author ST
 * 
 * 2017-09-14
 */
public class PurchaseReturnOutOther implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 购进退出单ID
     */
    @ApiModelProperty(value = "购进退出单ID")
    private Long outId;

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

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * saas_purchase_return_out_other
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
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getOutId() {
        return outId;
    }

    public void setOutId(Long outId) {
        this.outId = outId;
    }

    /**
     * 结算方式（0-现结；1-账期）
     * @return settlement_type 结算方式（0-现结；1-账期）
     */
    public Integer getSettlementType() {
        return settlementType;
    }

    /**
     * 结算方式（0-现结；1-账期）
     * @param settlementType 结算方式（0-现结；1-账期）
     */
    public void setSettlementType(Integer settlementType) {
        this.settlementType = settlementType;
    }

    /**
     * 结算单位
     * @return settlement_unit 结算单位
     */
    public String getSettlementUnit() {
        return settlementUnit;
    }

    /**
     * 结算单位
     * @param settlementUnit 结算单位
     */
    public void setSettlementUnit(String settlementUnit) {
        this.settlementUnit = settlementUnit == null ? null : settlementUnit.trim();
    }

    /**
     * 结算单位电话
     * @return settlement_unit_phone 结算单位电话
     */
    public String getSettlementUnitPhone() {
        return settlementUnitPhone;
    }

    /**
     * 结算单位电话
     * @param settlementUnitPhone 结算单位电话
     */
    public void setSettlementUnitPhone(String settlementUnitPhone) {
        this.settlementUnitPhone = settlementUnitPhone == null ? null : settlementUnitPhone.trim();
    }

    /**
     * 结算单位地址
     * @return settlement_unit_address 结算单位地址
     */
    public String getSettlementUnitAddress() {
        return settlementUnitAddress;
    }

    /**
     * 结算单位地址
     * @param settlementUnitAddress 结算单位地址
     */
    public void setSettlementUnitAddress(String settlementUnitAddress) {
        this.settlementUnitAddress = settlementUnitAddress == null ? null : settlementUnitAddress.trim();
    }

    /**
     * 发票类型（0-普通发票；1-增值税发票）
     * @return invoice_type 发票类型（0-普通发票；1-增值税发票）
     */
    public Integer getInvoiceType() {
        return invoiceType;
    }

    /**
     * 发票类型（0-普通发票；1-增值税发票）
     * @param invoiceType 发票类型（0-普通发票；1-增值税发票）
     */
    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * 纳税人识别号
     * @return taxpayer_code 纳税人识别号
     */
    public String getTaxpayerCode() {
        return taxpayerCode;
    }

    /**
     * 纳税人识别号
     * @param taxpayerCode 纳税人识别号
     */
    public void setTaxpayerCode(String taxpayerCode) {
        this.taxpayerCode = taxpayerCode == null ? null : taxpayerCode.trim();
    }

    /**
     * 开户户名
     * @return account_name 开户户名
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 开户户名
     * @param accountName 开户户名
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     * 开户账号
     * @return account 开户账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 开户账号
     * @param account 开户账号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 开户银行
     * @return bank 开户银行
     */
    public String getBank() {
        return bank;
    }

    /**
     * 开户银行
     * @param bank 开户银行
     */
    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    /**
     * 订单ID
     * @return order_id 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 订单ID
     * @param orderId 订单ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 订单单号
     * @return order_code 订单单号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 订单单号
     * @param orderCode 订单单号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
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
     * 运输方式（0-陆运；1-空运；2-海运）
     * @return transport_mode 运输方式（0-陆运；1-空运；2-海运）
     */
    public Integer getTransportMode() {
        return transportMode;
    }

    /**
     * 运输方式（0-陆运；1-空运；2-海运）
     * @param transportMode 运输方式（0-陆运；1-空运；2-海运）
     */
    public void setTransportMode(Integer transportMode) {
        this.transportMode = transportMode;
    }

    /**
     * 温控方式（0-冷藏车；1-冷藏箱；2-保温箱）
     * @return temp_control_mode 温控方式（0-冷藏车；1-冷藏箱；2-保温箱）
     */
    public Integer getTempControlMode() {
        return tempControlMode;
    }

    /**
     * 温控方式（0-冷藏车；1-冷藏箱；2-保温箱）
     * @param tempControlMode 温控方式（0-冷藏车；1-冷藏箱；2-保温箱）
     */
    public void setTempControlMode(Integer tempControlMode) {
        this.tempControlMode = tempControlMode;
    }

    /**
     * 承运方式（0-配送；1-委托运输；2-自提）
     * @return carriage_mode 承运方式（0-配送；1-委托运输；2-自提）
     */
    public Integer getCarriageMode() {
        return carriageMode;
    }

    /**
     * 承运方式（0-配送；1-委托运输；2-自提）
     * @param carriageMode 承运方式（0-配送；1-委托运输；2-自提）
     */
    public void setCarriageMode(Integer carriageMode) {
        this.carriageMode = carriageMode;
    }

    /**
     * 承运单位
     * @return carriage_unit 承运单位
     */
    public String getCarriageUnit() {
        return carriageUnit;
    }

    /**
     * 承运单位
     * @param carriageUnit 承运单位
     */
    public void setCarriageUnit(String carriageUnit) {
        this.carriageUnit = carriageUnit == null ? null : carriageUnit.trim();
    }

    /**
     * 委托经办人
     * @return agent 委托经办人
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 委托经办人
     * @param agent 委托经办人
     */
    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    /**
     * 随货通行单号
     * @return freight_pass 随货通行单号
     */
    public String getFreightPass() {
        return freightPass;
    }

    /**
     * 随货通行单号
     * @param freightPass 随货通行单号
     */
    public void setFreightPass(String freightPass) {
        this.freightPass = freightPass == null ? null : freightPass.trim();
    }

    /**
     * 发货地址
     * @return send_address 发货地址
     */
    public String getSendAddress() {
        return sendAddress;
    }

    /**
     * 发货地址
     * @param sendAddress 发货地址
     */
    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress == null ? null : sendAddress.trim();
    }

    /**
     * 发货人员
     * @return sender 发货人员
     */
    public String getSender() {
        return sender;
    }

    /**
     * 发货人员
     * @param sender 发货人员
     */
    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    /**
     * 件数
     * @return number 件数
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 件数
     * @param number 件数
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 车牌号
     * @return car_number 车牌号
     */
    public String getCarNumber() {
        return carNumber;
    }

    /**
     * 车牌号
     * @param carNumber 车牌号
     */
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    /**
     * 驾驶员
     * @return driver 驾驶员
     */
    public String getDriver() {
        return driver;
    }

    /**
     * 驾驶员
     * @param driver 驾驶员
     */
    public void setDriver(String driver) {
        this.driver = driver == null ? null : driver.trim();
    }

    /**
     * 驾驶证号
     * @return driver_license_code 驾驶证号
     */
    public String getDriverLicenseCode() {
        return driverLicenseCode;
    }

    /**
     * 驾驶证号
     * @param driverLicenseCode 驾驶证号
     */
    public void setDriverLicenseCode(String driverLicenseCode) {
        this.driverLicenseCode = driverLicenseCode == null ? null : driverLicenseCode.trim();
    }

    /**
     * 到货时限
     * @return limit_time 到货时限
     */
    public Integer getLimitTime() {
        return limitTime;
    }

    /**
     * 到货时限
     * @param limitTime 到货时限
     */
    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }

    /**
     * 到货时限单位（0-小时；1-天）
     * @return limit_time_unit 到货时限单位（0-小时；1-天）
     */
    public Integer getLimitTimeUnit() {
        return limitTimeUnit;
    }

    /**
     * 到货时限单位（0-小时；1-天）
     * @param limitTimeUnit 到货时限单位（0-小时；1-天）
     */
    public void setLimitTimeUnit(Integer limitTimeUnit) {
        this.limitTimeUnit = limitTimeUnit;
    }

    /**
     * 启运时间
     * @return shipment_time 启运时间
     */
    public Date getShipmentTime() {
        return shipmentTime;
    }

    /**
     * 启运时间
     * @param shipmentTime 启运时间
     */
    public void setShipmentTime(Date shipmentTime) {
        this.shipmentTime = shipmentTime;
    }

    /**
     * 启运温度（℃）
     * @return shipment_temp 启运温度（℃）
     */
    public Integer getShipmentTemp() {
        return shipmentTemp;
    }

    /**
     * 启运温度（℃）
     * @param shipmentTemp 启运温度（℃）
     */
    public void setShipmentTemp(Integer shipmentTemp) {
        this.shipmentTemp = shipmentTemp;
    }

    /**
     * 启运湿度（%）
     * @return shipment_humidity 启运湿度（%）
     */
    public Integer getShipmentHumidity() {
        return shipmentHumidity;
    }

    /**
     * 启运湿度（%）
     * @param shipmentHumidity 启运湿度（%）
     */
    public void setShipmentHumidity(Integer shipmentHumidity) {
        this.shipmentHumidity = shipmentHumidity;
    }

    /**
     * 到货时间
     * @return arrival_time 到货时间
     */
    public Date getArrivalTime() {
        return arrivalTime;
    }

    /**
     * 到货时间
     * @param arrivalTime 到货时间
     */
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * 在途温度起始值（℃）
     * @return transit_temp_start 在途温度起始值（℃）
     */
    public Integer getTransitTempStart() {
        return transitTempStart;
    }

    /**
     * 在途温度起始值（℃）
     * @param transitTempStart 在途温度起始值（℃）
     */
    public void setTransitTempStart(Integer transitTempStart) {
        this.transitTempStart = transitTempStart;
    }

    /**
     * 在途温度结束值（℃）
     * @return transit_temp_end 在途温度结束值（℃）
     */
    public Integer getTransitTempEnd() {
        return transitTempEnd;
    }

    /**
     * 在途温度结束值（℃）
     * @param transitTempEnd 在途温度结束值（℃）
     */
    public void setTransitTempEnd(Integer transitTempEnd) {
        this.transitTempEnd = transitTempEnd;
    }

    /**
     * 在途湿度起始值（%）
     * @return transit_humidity_start 在途湿度起始值（%）
     */
    public Integer getTransitHumidityStart() {
        return transitHumidityStart;
    }

    /**
     * 在途湿度起始值（%）
     * @param transitHumidityStart 在途湿度起始值（%）
     */
    public void setTransitHumidityStart(Integer transitHumidityStart) {
        this.transitHumidityStart = transitHumidityStart;
    }

    /**
     * 在途湿度结束值（%）
     * @return transit_humidity_end 在途湿度结束值（%）
     */
    public Integer getTransitHumidityEnd() {
        return transitHumidityEnd;
    }

    /**
     * 在途湿度结束值（%）
     * @param transitHumidityEnd 在途湿度结束值（%）
     */
    public void setTransitHumidityEnd(Integer transitHumidityEnd) {
        this.transitHumidityEnd = transitHumidityEnd;
    }

    /**
     * 到货温度（℃）
     * @return arrival_temp 到货温度（℃）
     */
    public Integer getArrivalTemp() {
        return arrivalTemp;
    }

    /**
     * 到货温度（℃）
     * @param arrivalTemp 到货温度（℃）
     */
    public void setArrivalTemp(Integer arrivalTemp) {
        this.arrivalTemp = arrivalTemp;
    }

    /**
     * 到货湿度（%）
     * @return arrival_ humidity 到货湿度（%）
     */
    public Integer getArrivalHumidity() {
        return arrivalHumidity;
    }

    /**
     * 到货湿度（%）
     * @param arrivalHumidity 到货湿度（%）
     */
    public void setArrivalHumidity(Integer arrivalHumidity) {
        this.arrivalHumidity = arrivalHumidity;
    }



    /**
     * 收货地址
     * @return receive_address 收货地址
     */
    public String getReceiveAddress() {
        return receiveAddress;
    }

    /**
     * 收货地址
     * @param receiveAddress 收货地址
     */
    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress == null ? null : receiveAddress.trim();
    }

    /**
     * 收货人员
     * @return receiver 收货人员
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 收货人员
     * @param receiver 收货人员
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    /**
     * 收货人员电话
     * @return receive_phone 收货人员电话
     */
    public String getReceivePhone() {
        return receivePhone;
    }

    /**
     * 收货人员电话
     * @param receivePhone 收货人员电话
     */
    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone == null ? null : receivePhone.trim();
    }

    /**
     * 全程温湿度数据（附件ID）
     * @return file_id 全程温湿度数据（附件ID）
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 全程温湿度数据（附件ID）
     * @param fileId 全程温湿度数据（附件ID）
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
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

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", outId=").append(outId);
        sb.append(", settlementType=").append(settlementType);
        sb.append(", settlementUnit=").append(settlementUnit);
        sb.append(", settlementUnitPhone=").append(settlementUnitPhone);
        sb.append(", settlementUnitAddress=").append(settlementUnitAddress);
        sb.append(", invoiceType=").append(invoiceType);
        sb.append(", taxpayerCode=").append(taxpayerCode);
        sb.append(", accountName=").append(accountName);
        sb.append(", account=").append(account);
        sb.append(", bank=").append(bank);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", transportMode=").append(transportMode);
        sb.append(", tempControlMode=").append(tempControlMode);
        sb.append(", carriageMode=").append(carriageMode);
        sb.append(", carriageUnit=").append(carriageUnit);
        sb.append(", agent=").append(agent);
        sb.append(", freightPass=").append(freightPass);
        sb.append(", sendAddress=").append(sendAddress);
        sb.append(", sender=").append(sender);
        sb.append(", number=").append(number);
        sb.append(", carNumber=").append(carNumber);
        sb.append(", driver=").append(driver);
        sb.append(", driverLicenseCode=").append(driverLicenseCode);
        sb.append(", limitTime=").append(limitTime);
        sb.append(", limitTimeUnit=").append(limitTimeUnit);
        sb.append(", shipmentTime=").append(shipmentTime);
        sb.append(", shipmentTemp=").append(shipmentTemp);
        sb.append(", shipmentHumidity=").append(shipmentHumidity);
        sb.append(", arrivalTime=").append(arrivalTime);
        sb.append(", transitTempStart=").append(transitTempStart);
        sb.append(", transitTempEnd=").append(transitTempEnd);
        sb.append(", transitHumidityStart=").append(transitHumidityStart);
        sb.append(", transitHumidityEnd=").append(transitHumidityEnd);
        sb.append(", arrivalTemp=").append(arrivalTemp);
        sb.append(", arrivalHumidity=").append(arrivalHumidity);
        sb.append(", receiveAddress=").append(receiveAddress);
        sb.append(", receiver=").append(receiver);
        sb.append(", receivePhone=").append(receivePhone);
        sb.append(", fileId=").append(fileId);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}