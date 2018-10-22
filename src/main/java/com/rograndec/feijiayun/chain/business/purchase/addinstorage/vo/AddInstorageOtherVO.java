package com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: AddInstorageOtherVO   
 * @Description: 采购管理-采购入库-配送和结算对象
 * @author yuting.li
 * @version 1.0 
 * @date 2017年11月28日 下午8:27:09
 */
@Validated
@ApiModel(value = "AddInstorageOtherVO", description = "采购管理-采购入库-配送和结算对象")
public class AddInstorageOtherVO implements Serializable{
	
	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年11月29日 下午2:46:02 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;

	//采购订单---------------------------------------------
	@ApiModelProperty(value = "合同单号")
    private String contractCode;
	
	@NotNull(message="结算方式不能为空")
	@ApiModelProperty(value = "结算方式（0-现结；1-账期)")
    private Integer settlementType;
	
	@NotNull(message="结算单位ID不能为空")
	@ApiModelProperty(value = "结算单位ID")
    private Long settlementUnitId;
	
	@ApiModelProperty(value = "结算单位地址")
    private String settlementUnitAddress;
	
	@ApiModelProperty(value = "结算单位电话")
    private String settlementUnitPhone;
	
	@NotNull(message="发票类型不能为空")
	@ApiModelProperty(value = "发票类型（0-普通发票；1-增值税发票）")
    private Integer invoiceType;
	
	@ApiModelProperty(value = "纳税人识别号")
    private String taxpayerCode;
	
	@ApiModelProperty(value = "开户户名")
    private String accountName;
	
	@ApiModelProperty(value = "开户账号")
    private String account;
	
	@ApiModelProperty(value = "开户银行")
    private String bank;
	
	@ApiModelProperty(value = "运输方式（0-陆运；1-空运；2-海运）")
    private Integer transportMode;
	
	@ApiModelProperty(value = "温控方式（0-冷藏车；1-冷藏箱；2-保温箱）")
    private Integer tempControlMode;
	
	//@Range(min=0,max=0,message="配送类型默认为总部配送")
	@ApiModelProperty(required = true, value = "配送类型（0-总部配送；1-委托运输；2-自提,3-门店间调剂，4-直调配送）")
    private Integer carriageMode;
	
	@ApiModelProperty(value = "承运单位")
    private String carriageUnit;
	
	@ApiModelProperty(required = true, value = "委托经办人")
    private String agent;
	
	@ApiModelProperty(required = true, value = "随货通行单号")
    private String freightPass;
	
	//采购收货---------------------------------------------
	@ApiModelProperty(required = true, value = "发货地址")
    private String sendAddress;
	
	@ApiModelProperty(required = true, value = "发货人员")
    private String sender;
	
	@ApiModelProperty(required = true, value = "件数")
    private Integer number;
	
	@ApiModelProperty(required = true, value = "车牌号")
    private String carNumber;
	
	@ApiModelProperty(required = true, value = "驾驶员")
    private String driver;
	
	@ApiModelProperty(required = true, value = "驾驶证号")
    private String driverLicenseCode;
	
	@ApiModelProperty(required = true, value = "到货时限")
    private Integer limitTime;
	
	@ApiModelProperty(required = true, value = "到货时限单位（0-小时；1-天）")
    private Integer limitTimeUnit;
	
	@ApiModelProperty(required = true, value = "启运时间")
    private Date shipmentTime;

	@ApiModelProperty(required = true, value = "启运温度（℃）")
    private Integer shipmentTemp;
	
	@ApiModelProperty(required = true, value = "启运湿度（%）")
    private Integer shipmentHumidity;
	
	@ApiModelProperty(required = true, value = "到货时间")
    private Date arrivalTime;
	
	@ApiModelProperty(required = true, value = "在途温度起始值（℃）")
    private Integer transitTempStart;
	
	@ApiModelProperty(required = true, value = "在途温度结束值（℃）")
    private Integer transitTempEnd;
	
	@ApiModelProperty(required = true, value = "在途湿度起始值（%）")
    private Integer transitHumidityStart;
	
	@ApiModelProperty(required = true, value = "在途湿度结束值（%）")
	private Integer transitHumidityEnd;
	
	@ApiModelProperty(required = true, value = "到货温度（℃)")
    private Integer arrivalTemp;
	
	@ApiModelProperty(required = true, value = "到货湿度（%")
    private Integer arrivalHumidity;


	@ApiModelProperty(required = true, value = "收货单位Id")
	private Long receiveUnitId;

	@ApiModelProperty(required = true, value = "收货单位")
    private String receiveUnit;
	
	@ApiModelProperty(value = "收货地址")
    private String receiveAddress;
	
	@ApiModelProperty(required = true, value = "全程温湿度数据（附件ID")
    private Long fileId;

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public Integer getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(Integer settlementType) {
		this.settlementType = settlementType;
	}

	public Long getSettlementUnitId() {
		return settlementUnitId;
	}

	public void setSettlementUnitId(Long settlementUnitId) {
		this.settlementUnitId = settlementUnitId;
	}

	public String getSettlementUnitAddress() {
		return settlementUnitAddress;
	}

	public void setSettlementUnitAddress(String settlementUnitAddress) {
		this.settlementUnitAddress = settlementUnitAddress;
	}

	public String getSettlementUnitPhone() {
		return settlementUnitPhone;
	}

	public void setSettlementUnitPhone(String settlementUnitPhone) {
		this.settlementUnitPhone = settlementUnitPhone;
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

	public String getReceiveUnit() {
		return receiveUnit;
	}

	public void setReceiveUnit(String receiveUnit) {
		this.receiveUnit = receiveUnit;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Long getReceiveUnitId() {
		return receiveUnitId;
	}

	public void setReceiveUnitId(Long receiveUnitId) {
		this.receiveUnitId = receiveUnitId;
	}
}
