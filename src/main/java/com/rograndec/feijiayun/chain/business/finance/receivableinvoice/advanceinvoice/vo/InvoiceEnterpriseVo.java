package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo;

import java.io.Serializable;

import com.rograndec.feijiayun.chain.common.valid.annotation.ValidMax;

import io.swagger.annotations.ApiModelProperty;

public class InvoiceEnterpriseVo implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 主键ID
     */
	@ApiModelProperty(value = "购货id")
    private Long id;
	/**
     * 企业名称
     */
	@ApiModelProperty(value = "购货企业名称")
    private String name;

    /**
     * 编码
     */
	@ApiModelProperty(value = "购货编码")
    private String code;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
}
