package com.rograndec.feijiayun.chain.inf.pos.report.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_receipt_detail
 * 
 * 
 * @author Administrator
 * 
 * 2017-09-21
 */
public class ReceiptDetailVO implements Serializable {
	

    /**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年12月18日 上午11:05:03 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "支付方式名称")
    private String payTypeName;

    @ApiModelProperty(value = "开户行账号")
    private String bankName;

    @ApiModelProperty(value = "卡号")
    private String cardAccount;

    @ApiModelProperty(value = "收款金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "余额")
    private BigDecimal balance;
    
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    
}