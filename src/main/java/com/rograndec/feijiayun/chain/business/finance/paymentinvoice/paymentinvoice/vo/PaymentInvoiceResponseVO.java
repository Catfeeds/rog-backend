package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.common.constant.status.FinancePaymentStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_prepay_invoice
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
@ApiModel
public class PaymentInvoiceResponseVO implements Serializable {
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
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 单据类型（2201）
     */
    @ApiModelProperty(value = "单据类型（2201）")
    private Integer orderType;

    @ApiModelProperty(value = "0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税率")
    private Integer financeAccountType;

    /**
     * 开票日期
     */
    @ApiModelProperty(value = "开票日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date billDate;

    /**
     * 开票人员ID
     */
    @ApiModelProperty(value = "开票人员ID")
    private Long billManId;

    /**
     * 开票人员编码
     */
    @ApiModelProperty(value = "开票人员编码")
    private String billManCode;

    /**
     * 开票人员名称
     */
    @ApiModelProperty(value = "开票人员名称")
    private String billManName;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID")
    private Long supplierId;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码")
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称")
    private String supplierName;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 已清金额合计
     */
    @ApiModelProperty(value = "已清金额合计")
    private BigDecimal clearAmountTotal;

    /**
     * 未清金额合计
     */
    @ApiModelProperty(value = "未清金额合计")
    private BigDecimal unclearAmountTotal;



    /**
     * 金额差额
     */
    @ApiModelProperty(value = "金额差额合计")
    private BigDecimal diffAmountTotal;

    /**
     * 不含税金额差额
     */
    @ApiModelProperty(value = "不含税金额差额合计")
    private BigDecimal diffNotaxAmountTotal;

    /**
     * 税额差额
     */
    @ApiModelProperty(value = "税额差额合计")
    private BigDecimal diffTaxAmountTotal;

    /**
     * 已清数量
     */
    @ApiModelProperty(value = "已清数量合计")
    private BigDecimal clearQuantityTotal;

    /**
     * 未清数量
     */
    @ApiModelProperty(value = "未清数量合计")
    private BigDecimal unclearQuantityTotal;


    /**
     * 状态（0-待付款；1-部分付款；2-已付款；3-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待付款；1-部分付款；2-已付款；3-已冲销）")
    private Integer status;

    @ApiModelProperty(value = "状态（0-待付款；1-部分付款；2-已付款；3-已冲销）")
    private String statusDesc;

    @ApiModelProperty(value = "修改原因")
    private String reason;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 预付发票明细行
     */
    @ApiModelProperty(value = "预付发票明细行")
    private List<PaymentInvoiceDetailResponseVO> paymentInvoiceDetails;

    /**
     * 发票信息
     */
    @ApiModelProperty(value = "发票信息")
    private PaymentInvoiceInfoResponseVO paymentInvoiceInfo;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Long getBillManId() {
        return billManId;
    }

    public void setBillManId(Long billManId) {
        this.billManId = billManId;
    }

    public String getBillManCode() {
        return billManCode;
    }

    public void setBillManCode(String billManCode) {
        this.billManCode = billManCode;
    }

    public String getBillManName() {
        return billManName;
    }

    public void setBillManName(String billManName) {
        this.billManName = billManName;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public BigDecimal getClearAmountTotal() {
        return clearAmountTotal;
    }

    public void setClearAmountTotal(BigDecimal clearAmountTotal) {
        this.clearAmountTotal = clearAmountTotal;
    }

    public BigDecimal getUnclearAmountTotal() {
        return unclearAmountTotal;
    }

    public void setUnclearAmountTotal(BigDecimal unclearAmountTotal) {
        this.unclearAmountTotal = unclearAmountTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return FinancePaymentStatus.getStatusDesc(status);
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PaymentInvoiceDetailResponseVO> getPaymentInvoiceDetails() {
        return paymentInvoiceDetails;
    }

    public void setPaymentInvoiceDetails(List<PaymentInvoiceDetailResponseVO> paymentInvoiceDetails) {
        this.paymentInvoiceDetails = paymentInvoiceDetails;
    }

    public PaymentInvoiceInfoResponseVO getPaymentInvoiceInfo() {
        return paymentInvoiceInfo;
    }

    public void setPaymentInvoiceInfo(PaymentInvoiceInfoResponseVO paymentInvoiceInfo) {
        this.paymentInvoiceInfo = paymentInvoiceInfo;
    }

    public BigDecimal getDiffAmountTotal() {
        return diffAmountTotal;
    }

    public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
        this.diffAmountTotal = diffAmountTotal;
    }

    public BigDecimal getDiffNotaxAmountTotal() {
        return diffNotaxAmountTotal;
    }

    public void setDiffNotaxAmountTotal(BigDecimal diffNotaxAmountTotal) {
        this.diffNotaxAmountTotal = diffNotaxAmountTotal;
    }

    public BigDecimal getDiffTaxAmountTotal() {
        return diffTaxAmountTotal;
    }

    public void setDiffTaxAmountTotal(BigDecimal diffTaxAmountTotal) {
        this.diffTaxAmountTotal = diffTaxAmountTotal;
    }

    public BigDecimal getClearQuantityTotal() {
        return clearQuantityTotal;
    }

    public void setClearQuantityTotal(BigDecimal clearQuantityTotal) {
        this.clearQuantityTotal = clearQuantityTotal;
    }

    public BigDecimal getUnclearQuantityTotal() {
        return unclearQuantityTotal;
    }

    public void setUnclearQuantityTotal(BigDecimal unclearQuantityTotal) {
        this.unclearQuantityTotal = unclearQuantityTotal;
    }

    public Integer getFinanceAccountType() {
        return financeAccountType;
    }

    public void setFinanceAccountType(Integer financeAccountType) {
        this.financeAccountType = financeAccountType;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}