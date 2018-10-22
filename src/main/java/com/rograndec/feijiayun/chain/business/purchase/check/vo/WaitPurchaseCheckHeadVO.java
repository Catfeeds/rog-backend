package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zeshi.sun on 2017/9/16.
 */
public class WaitPurchaseCheckHeadVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码", required = true)
    private String supplierCode;

    /**
     * 供货单位销售人员
     */
    @ApiModelProperty(value = "供货单位销售人员", required = true)
    private Long supplierSalerId;

    /**
     * 供货单位销售人员
     */
    @ApiModelProperty(value = "供货单位销售人员", required = true)
    private String supplierSalerName;


    /**
     * 收货单号
     */
    @ApiModelProperty(value = "收货单号", required = true)
    private String code;

    /**
     * 收货人员1
     */
    @ApiModelProperty(value = "收货人员1", required = true)
    private String receiverName;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称", required = true)
    private String supplierName;

    /**
     * 供货单位销售人员联系电话
     */
    @ApiModelProperty(value = "联系电话", required = true)
    private String supplierSalerPhone;

    /**
     * 收货日期
     */
    @ApiModelProperty(value = "收货日期", required = true)
    private Date receiveDate;

    /**
     * 收货人员2
     */
    @ApiModelProperty(value = "收货人员2", required = true)
    private String secondReceiverName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Long getSupplierSalerId() {
        return supplierSalerId;
    }

    public void setSupplierSalerId(Long supplierSalerId) {
        this.supplierSalerId = supplierSalerId;
    }

    public String getSupplierSalerName() {
        return supplierSalerName;
    }

    public void setSupplierSalerName(String supplierSalerName) {
        this.supplierSalerName = supplierSalerName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierSalerPhone() {
        return supplierSalerPhone;
    }

    public void setSupplierSalerPhone(String supplierSalerPhone) {
        this.supplierSalerPhone = supplierSalerPhone;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getSecondReceiverName() {
        return secondReceiverName;
    }

    public void setSecondReceiverName(String secondReceiverName) {
        this.secondReceiverName = secondReceiverName;
    }

    @Override
    public String toString() {
        return "WaitPurchaseCheckHeadVO[" +
                "id=" + id +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierSalerId=" + supplierSalerId +
                ", supplierSalerName='" + supplierSalerName + '\'' +
                ", code='" + code + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierSalerPhone='" + supplierSalerPhone + '\'' +
                ", receiveDate=" + receiveDate +
                ", secondReceiverName='" + secondReceiverName + '\'' +
                ']';
    }
}
