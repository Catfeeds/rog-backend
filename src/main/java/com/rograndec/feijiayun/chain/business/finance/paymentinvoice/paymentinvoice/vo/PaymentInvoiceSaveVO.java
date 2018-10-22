package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_payment_invoice
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-15
 */
@ApiModel
public class PaymentInvoiceSaveVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 开票日期
     */
    @ApiModelProperty(value = "开票日期",required = true)
    @NotNull(message = "开票日期不能为空")
    private Date billDate;

    /**
     * 开票人员ID
     */
    @ApiModelProperty(value = "开票人员ID",required = true)
    @NotNull(message = "开票人员不能为空")
    private Long billManId;



    @ApiModelProperty(value = "数据类型,默认为价格清单:0,1:企业,2:供应商",required = true)
    @NotNull(message = "供货单位类型不能为空")
    private Integer type = 0;

    /**
     * 草稿ID
     * */
    @ApiModelProperty(value = "草稿ID")
    private String redisKeyValue;


    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID" ,required = true)
    @NotNull(message = "供货单位不能为空")
    private Long supplierId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "修改原因")
    private String reason;

    @ApiModelProperty(value = "应付发票明细")
    private List<PaymentInvoiceDetailSaveVO> paymentInvoiceDetails;

    @ApiModelProperty(value = "应付发票开票信息")
    private PaymentInvoiceInfoSaveVO paymentInvoiceInfo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRedisKeyValue() {
        return redisKeyValue;
    }

    public void setRedisKeyValue(String redisKeyValue) {
        this.redisKeyValue = redisKeyValue;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PaymentInvoiceDetailSaveVO> getPaymentInvoiceDetails() {
        return paymentInvoiceDetails;
    }

    public void setPaymentInvoiceDetails(List<PaymentInvoiceDetailSaveVO> paymentInvoiceDetails) {
        this.paymentInvoiceDetails = paymentInvoiceDetails;
    }

    public PaymentInvoiceInfoSaveVO getPaymentInvoiceInfo() {
        return paymentInvoiceInfo;
    }

    public void setPaymentInvoiceInfo(PaymentInvoiceInfoSaveVO paymentInvoiceInfo) {
        this.paymentInvoiceInfo = paymentInvoiceInfo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}