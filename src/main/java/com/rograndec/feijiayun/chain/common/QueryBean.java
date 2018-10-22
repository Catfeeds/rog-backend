package com.rograndec.feijiayun.chain.common;

import com.rograndec.feijiayun.chain.business.purchase.plan.vo.SupplierVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: SelectBean   
 * @Description: 页面下拉框、checkbox
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月23日 下午4:27:42
 */
@ApiModel(value = "selectBean", description = "下拉框id-text")
public class QueryBean implements Serializable{
	/**
	 * serialVersionUID : <功能描述>
	 */
	@ApiModelProperty(value = "下拉框id")
	private  Long id;
	@ApiModelProperty(value = "下拉框text")
	private  String text;
	@ApiModelProperty(value = "数据code")
	private  String code;
	@ApiModelProperty(value = "数据name")
	private  String name;
	@ApiModelProperty(value = "数据类型,默认为价格清单:0,1:企业,2:供应商")
	private Integer type = 0;

	/**
	 * 发票类型（0-增值税发票；1-普通发票；2-票据）
	 */
	@ApiModelProperty(value = "发票类型（0-增值税发票；1-普通发票；2-票据）")
	private Integer invoiceType;

	/**
	 * 发票代码
	 */
	@ApiModelProperty(value = "发票代码")
	private String invoiceCode;

	/**
	 * 发票号码
	 */
	@ApiModelProperty(value = "发票号码")
	private String invoiceNumber;

	/**
	 * 检验码
	 */
	@ApiModelProperty(value = "检验码")
	private String checkCode;

	/**
	 * 供货单位纳税人识别号
	 */
	@ApiModelProperty(value = "供货单位纳税人识别号")
	private String taxpayerIdCode;

	/**
	 * 供货单位开户户名
	 */
	@ApiModelProperty(value = "供货单位开户户名")
	private String accountName;

	/**
	 * 供货单位开户银行
	 */
	@ApiModelProperty(value = "供货单位开户银行")
	private String accountBank;

	/**
	 * 供货单位开户账号
	 */
	@ApiModelProperty(value = "供货单位开户账号")
	private String account;

	/**
	 * 供货单位地址
	 */
	@ApiModelProperty(value = "供货单位地址")
	private String address;

	/**
	 * 供货单位电话
	 */
	@ApiModelProperty(value = "供货单位电话")
	private String telephone;


	/**
	 * 企业名称
	 */
	@ApiModelProperty(value = "企业名称")
	private String companyName;

	/**
	 * 企业地址
	 */
	@ApiModelProperty(value = "企业地址")
	private String companyAddress;

	/**
	 * 企业电话
	 */
	@ApiModelProperty(value = "企业电话")
	private String companyTelephone;

	/**
	 * 企业纳税人识别号
	 */
	@ApiModelProperty(value = "企业纳税人识别号")
	private String companyTaxpayerIdCode;

	/**
	 * 企业开户户名
	 */
	@ApiModelProperty(value = "企业开户户名")
	private String companyAccountName;

	/**
	 * 企业开户银行
	 */
	@ApiModelProperty(value = "企业开户银行")
	private String companyAccountBank;

	/**
	 * 企业开户账号
	 */
	@ApiModelProperty(value = "企业开户账号")
	private String companyAccount;



	public static QueryBean getQueryBean(SupplierVO supplierVO){

		QueryBean queryBean = new QueryBean();
		queryBean.setId(supplierVO.getId());
		queryBean.setText(supplierVO.getName()+"_"+supplierVO.getCode());
		queryBean.setCode(supplierVO.getCode());
		queryBean.setName(supplierVO.getName());
		queryBean.setType(2);

		return queryBean;
	}

	public static QueryBean getQueryBean(Enterprise enterprise){

		QueryBean queryBean = new QueryBean();
		queryBean.setId(enterprise.getId());
		queryBean.setText(enterprise.getName()+"-"+enterprise.getCode());
		queryBean.setCode(enterprise.getCode());
		queryBean.setName(enterprise.getName());
		queryBean.setType(1);

		return queryBean;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

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
}
