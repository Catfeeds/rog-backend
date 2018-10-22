package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_receivable_invoice_info
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-08
 */
public class ReceivableInvoiceInfo implements Serializable {
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
     * 应收发票总单ID
     */
    @ApiModelProperty(value = "应收发票总单ID")
    private Long invoiceId;

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
     * 购货单位纳税人识别号
     */
    @ApiModelProperty(value = "购货单位纳税人识别号")
    private String taxpayerIdCode;

    /**
     * 购货单位开户户名
     */
    @ApiModelProperty(value = "购货单位开户户名")
    private String accountName;

    /**
     * 购货单位开户银行
     */
    @ApiModelProperty(value = "购货单位开户银行")
    private String accountBank;

    /**
     * 购货单位开户账号
     */
    @ApiModelProperty(value = "购货单位开户账号")
    private String account;

    /**
     * 购货单位地址
     */
    @ApiModelProperty(value = "购货单位地址")
    private String address;

    /**
     * 购货单位电话
     */
    @ApiModelProperty(value = "购货单位电话")
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

    /**
     * 附件ID
     */
    @ApiModelProperty(value = "附件ID")
    private Long fileId;

    /**
     * saas_receivable_invoice_info
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

    /**
     * 应收发票总单ID
     * @return invoice_id 应收发票总单ID
     */
    public Long getInvoiceId() {
        return invoiceId;
    }

    /**
     * 应收发票总单ID
     * @param invoiceId 应收发票总单ID
     */
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * 发票类型（0-增值税发票；1-普通发票；2-票据）
     * @return invoice_type 发票类型（0-增值税发票；1-普通发票；2-票据）
     */
    public Integer getInvoiceType() {
        return invoiceType;
    }

    /**
     * 发票类型（0-增值税发票；1-普通发票；2-票据）
     * @param invoiceType 发票类型（0-增值税发票；1-普通发票；2-票据）
     */
    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * 发票代码
     * @return invoice_code 发票代码
     */
    public String getInvoiceCode() {
        return invoiceCode;
    }

    /**
     * 发票代码
     * @param invoiceCode 发票代码
     */
    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode == null ? null : invoiceCode.trim();
    }

    /**
     * 发票号码
     * @return invoice_number 发票号码
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * 发票号码
     * @param invoiceNumber 发票号码
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber == null ? null : invoiceNumber.trim();
    }

    /**
     * 检验码
     * @return check_code 检验码
     */
    public String getCheckCode() {
        return checkCode;
    }

    /**
     * 检验码
     * @param checkCode 检验码
     */
    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode == null ? null : checkCode.trim();
    }

    /**
     * 购货单位纳税人识别号
     * @return taxpayer_id_code 购货单位纳税人识别号
     */
    public String getTaxpayerIdCode() {
        return taxpayerIdCode;
    }

    /**
     * 购货单位纳税人识别号
     * @param taxpayerIdCode 购货单位纳税人识别号
     */
    public void setTaxpayerIdCode(String taxpayerIdCode) {
        this.taxpayerIdCode = taxpayerIdCode == null ? null : taxpayerIdCode.trim();
    }

    /**
     * 购货单位开户户名
     * @return account_name 购货单位开户户名
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 购货单位开户户名
     * @param accountName 购货单位开户户名
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     * 购货单位开户银行
     * @return account_bank 购货单位开户银行
     */
    public String getAccountBank() {
        return accountBank;
    }

    /**
     * 购货单位开户银行
     * @param accountBank 购货单位开户银行
     */
    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank == null ? null : accountBank.trim();
    }

    /**
     * 购货单位开户账号
     * @return account 购货单位开户账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 购货单位开户账号
     * @param account 购货单位开户账号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 购货单位地址
     * @return address 购货单位地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 购货单位地址
     * @param address 购货单位地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 购货单位电话
     * @return telephone 购货单位电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 购货单位电话
     * @param telephone 购货单位电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * 企业名称
     * @return company_name 企业名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 企业名称
     * @param companyName 企业名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 企业地址
     * @return company_address 企业地址
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * 企业地址
     * @param companyAddress 企业地址
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    /**
     * 企业电话
     * @return company_telephone 企业电话
     */
    public String getCompanyTelephone() {
        return companyTelephone;
    }

    /**
     * 企业电话
     * @param companyTelephone 企业电话
     */
    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone == null ? null : companyTelephone.trim();
    }

    /**
     * 企业纳税人识别号
     * @return company_taxpayer_id_code 企业纳税人识别号
     */
    public String getCompanyTaxpayerIdCode() {
        return companyTaxpayerIdCode;
    }

    /**
     * 企业纳税人识别号
     * @param companyTaxpayerIdCode 企业纳税人识别号
     */
    public void setCompanyTaxpayerIdCode(String companyTaxpayerIdCode) {
        this.companyTaxpayerIdCode = companyTaxpayerIdCode == null ? null : companyTaxpayerIdCode.trim();
    }

    /**
     * 企业开户户名
     * @return company_account_name 企业开户户名
     */
    public String getCompanyAccountName() {
        return companyAccountName;
    }

    /**
     * 企业开户户名
     * @param companyAccountName 企业开户户名
     */
    public void setCompanyAccountName(String companyAccountName) {
        this.companyAccountName = companyAccountName == null ? null : companyAccountName.trim();
    }

    /**
     * 企业开户银行
     * @return company_account_bank 企业开户银行
     */
    public String getCompanyAccountBank() {
        return companyAccountBank;
    }

    /**
     * 企业开户银行
     * @param companyAccountBank 企业开户银行
     */
    public void setCompanyAccountBank(String companyAccountBank) {
        this.companyAccountBank = companyAccountBank == null ? null : companyAccountBank.trim();
    }

    /**
     * 企业开户账号
     * @return company_account 企业开户账号
     */
    public String getCompanyAccount() {
        return companyAccount;
    }

    /**
     * 企业开户账号
     * @param companyAccount 企业开户账号
     */
    public void setCompanyAccount(String companyAccount) {
        this.companyAccount = companyAccount == null ? null : companyAccount.trim();
    }

    /**
     * 附件ID
     * @return file_id 附件ID
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 附件ID
     * @param fileId 附件ID
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
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
        sb.append(", invoiceId=").append(invoiceId);
        sb.append(", invoiceType=").append(invoiceType);
        sb.append(", invoiceCode=").append(invoiceCode);
        sb.append(", invoiceNumber=").append(invoiceNumber);
        sb.append(", checkCode=").append(checkCode);
        sb.append(", taxpayerIdCode=").append(taxpayerIdCode);
        sb.append(", accountName=").append(accountName);
        sb.append(", accountBank=").append(accountBank);
        sb.append(", account=").append(account);
        sb.append(", address=").append(address);
        sb.append(", telephone=").append(telephone);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyAddress=").append(companyAddress);
        sb.append(", companyTelephone=").append(companyTelephone);
        sb.append(", companyTaxpayerIdCode=").append(companyTaxpayerIdCode);
        sb.append(", companyAccountName=").append(companyAccountName);
        sb.append(", companyAccountBank=").append(companyAccountBank);
        sb.append(", companyAccount=").append(companyAccount);
        sb.append(", fileId=").append(fileId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}