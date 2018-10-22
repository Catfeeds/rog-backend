package com.rograndec.feijiayun.chain.inf.pos.sale.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class POSReceiptDetailVo implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月18日 下午3:47:30 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 支付方式ID
     */
    @ApiModelProperty(value = "支付方式ID")
    private Long payTypeId;
    
    /**
     * 支付方式CODE
     */
    @ApiModelProperty(value = "支付方式code")
    private String payTypeCode;

    /**
     * 支付方式名称
     */
    @ApiModelProperty(value = "支付方式名称")
    private String payTypeName;

    /**
     * 开户行ID
     */
    @ApiModelProperty(value = "开户行ID")
    private Long bankId;

    /**
     * 开户行账号
     */
    @ApiModelProperty(value = "开户行账号")
    private String bankName;

    /**
     * 卡号
     */
    @ApiModelProperty(value = "卡号")
    private String cardAccount;

    /**
     * 收款金额
     */
    @ApiModelProperty(value = "收款金额")
    private BigDecimal amount;

    /**
     * 余额
     */
    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

	public Long getPayTypeId() {
		return payTypeId;
	}

	public void setPayTypeId(Long payTypeId) {
		this.payTypeId = payTypeId;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardAccount() {
		return cardAccount;
	}

	public void setCardAccount(String cardAccount) {
		this.cardAccount = cardAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPayTypeCode() {
		return payTypeCode;
	}

	public void setPayTypeCode(String payTypeCode) {
		this.payTypeCode = payTypeCode;
	}
	
}
