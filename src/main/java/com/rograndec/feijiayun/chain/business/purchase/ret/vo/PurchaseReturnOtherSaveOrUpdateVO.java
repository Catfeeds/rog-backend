package com.rograndec.feijiayun.chain.business.purchase.ret.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 
 * saas_purchase_return_other
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-09-15
 */
@ApiModel
public class PurchaseReturnOtherSaveOrUpdateVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "构建退出单配送和结算信息id,修改时需要传递,新增不需要")
    private Long id;

    /**
     * 结算方式（0-现结；1-账期）
     */
    @ApiModelProperty(value = "结算方式（0-现结；1-账期）" ,required = true)
    @NotNull(message = "结算方式不能为空")
    private Integer settlementType;
    
    /**
     * 结算单位ID
     */
    @ApiModelProperty(value = "结算单位ID")
    private Long settlementUnitId;

    /**
     * 结算单位
     */
    @ApiModelProperty(value = "结算单位" ,required = true)
    @NotNull(message = "结算单位不能为空")
    @Size(min = 1,message = "结算单位不能为空")
    private String settlementUnit;

    /**
     * 结算单位电话
     */
    @ApiModelProperty(value = "结算单位电话" ,required = true)
    @NotNull(message = "结算单位电话不能为空")
    @Size(min = 1,message = "结算单位电话不能为空")
    private String settlementUnitPhone;

    /**
     * 结算单位地址
     */
    @ApiModelProperty(value = "结算单位地址" ,required = true)
    @NotNull(message = "结算单位地址不能为空")
    @Size(min = 1,message = "结算单位地址不能为空")
    private String settlementUnitAddress;

    /**
     * 开户户名
     */
    @ApiModelProperty(value = "开户户名")
    private String accountName;

    /**
     * 开户账号
     */
    @ApiModelProperty(value = "开户账号不能为空")
    private String account;

    /**
     * 开户银行
     */
    @ApiModelProperty(value = "开户银行" )
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
     * 承运方式（0-配送；1-委托运输；2-自提）注：承运方式改名为  配货类型   0-总部配送；1-委托运输；2-自提  ；4-直调配送
     */
    @ApiModelProperty(value = " 配货类型  （ 0-总部配送；1-委托运输；2-自提  ；4-直调配送）" )
    @NotNull(message = "配货类型  不能为空")
    private Integer carriageMode;

    /**
     * 承运单位
     */
    @ApiModelProperty(value = "承运单位")
    private String carriageUnit;

    /**
     * 运输方式（0-陆运；1-空运；2-海运）
     */
    @ApiModelProperty(value = "运输方式（0-陆运；1-空运；2-海运）" )
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public Long getSettlementUnitId() {
		return settlementUnitId;
	}

	public void setSettlementUnitId(Long settlementUnitId) {
		this.settlementUnitId = settlementUnitId;
	}

}