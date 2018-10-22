package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo;

import java.io.Serializable;

import com.rograndec.feijiayun.chain.common.valid.annotation.ValidMax;
import com.rograndec.feijiayun.chain.common.valid.annotation.ValidNotNull;

import io.swagger.annotations.ApiModelProperty;

public class AdvanceReceivableInvoiceInfoVo implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
     * 发票类型（0-增值税发票；1-普通发票；2-票据）
     */
    @ApiModelProperty(value = "发票类型（0-增值税发票；1-普通发票；2-票据）")
    @ValidNotNull()
    private Integer invoiceType;

    /**
     * 发票代码
     */
    @ApiModelProperty(value = "发票代码")
    @ValidMax(20)
    private String invoiceCode;

    /**
     * 发票号码
     */
    @ApiModelProperty(value = "发票号码")
    @ValidMax(20)
    private String invoiceNumber;

    /**
     * 检验码
     */
    @ApiModelProperty(value = "检验码")
    @ValidMax(20)
    private String checkCode;

    /**
     * 购货单位纳税人识别号
     */
    @ApiModelProperty(value = "购货单位纳税人识别号")
    @ValidMax(50)
    private String taxpayerIdCode;

    /**
     * 购货单位开户户名
     */
    @ApiModelProperty(value = "购货单位开户户名")
    @ValidMax(50)
    private String accountName;

    /**
     * 购货单位开户银行
     */
    @ApiModelProperty(value = "购货单位开户银行")
    @ValidMax(50)
    private String accountBank;

    /**
     * 购货单位开户账号
     */
    @ApiModelProperty(value = "购货单位开户账号")
    @ValidMax(50)
    private String account;

    /**
     * 购货单位地址
     */
    @ApiModelProperty(value = "购货单位地址")
    @ValidMax(100)
    private String address;

    /**
     * 购货单位电话
     */
    @ApiModelProperty(value = "购货单位电话")
    @ValidMax(20)
    private String telephone;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    @ValidMax(100)
    private String companyName;

    /**
     * 企业地址
     */
    @ApiModelProperty(value = "企业地址")
    @ValidMax(100)
    private String companyAddress;

    /**
     * 企业电话
     */
    @ApiModelProperty(value = "企业电话")
    @ValidMax(20)
    private String companyTelephone;

    /**
     * 企业纳税人识别号
     */
    @ApiModelProperty(value = "企业纳税人识别号")
    @ValidMax(50)
    private String companyTaxpayerIdCode;

    /**
     * 企业开户户名
     */
    @ApiModelProperty(value = "企业开户户名")
    @ValidMax(50)
    private String companyAccountName;

    /**
     * 企业开户银行
     */
    @ApiModelProperty(value = "企业开户银行")
    @ValidMax(50)
    private String companyAccountBank;

    /**
     * 企业开户账号
     */
    @ApiModelProperty(value = "企业开户账号")
    @ValidMax(50)
    private String companyAccount;

    /**
     * 附件ID
     */
    @ApiModelProperty(value = "附件ID")
    private Long fileId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @ValidMax(200)
    private String remark;

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getTaxpayerIdCode() {
		return taxpayerIdCode;
	}

	public void setTaxpayerIdCode(String taxpayerIdCode) {
		this.taxpayerIdCode = taxpayerIdCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyTelephone() {
		return companyTelephone;
	}

	public void setCompanyTelephone(String companyTelephone) {
		this.companyTelephone = companyTelephone;
	}

	public String getCompanyTaxpayerIdCode() {
		return companyTaxpayerIdCode;
	}

	public void setCompanyTaxpayerIdCode(String companyTaxpayerIdCode) {
		this.companyTaxpayerIdCode = companyTaxpayerIdCode;
	}

	public String getCompanyAccountName() {
		return companyAccountName;
	}

	public void setCompanyAccountName(String companyAccountName) {
		this.companyAccountName = companyAccountName;
	}

	public String getCompanyAccountBank() {
		return companyAccountBank;
	}

	public void setCompanyAccountBank(String companyAccountBank) {
		this.companyAccountBank = companyAccountBank;
	}

	public String getCompanyAccount() {
		return companyAccount;
	}

	public void setCompanyAccount(String companyAccount) {
		this.companyAccount = companyAccount;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
