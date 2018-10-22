package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zeshi.sun on 2017/9/19.
 */
public class PurchaseReceiveReVO implements Serializable {

    /**
     * 收货单ID
     */
    @ApiModelProperty(required = true, value = "收货单ID")
    private Long id;

    /**
     * 收货单号
     */
    @ApiModelProperty(required = true, value = "收货单号")
    private String code;

    /**
     * 收货日期
     */
    @ApiModelProperty(required = true, value = "收货日期")
    private Date receiveDate;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(required = true, value = "供货单位编码")
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(required = true, value = "供货单位名称")
    private String supplierName;

    /**
     * 收货人员名称
     */
    @ApiModelProperty(required = true, value = "收货人员名称")
    private String receiverName;

    /**
     * 状态
     */
    @ApiModelProperty(required = true, value = "状态")
    private String status;

    /**
     * 配送类型
     */
    @ApiModelProperty(required = true, value = "配送类型")
    private String carriageModeName;

    /**
     * 配送方式（0-总部配送；1-委托运输；2-自提；3-门店间调剂；4-直调配送）
     */
    @ApiModelProperty(required = true, value = "配送方式（0-总部配送；1-委托运输；2-自提；3-门店间调剂；4-直调配送）")
    private Integer carriageModeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
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

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarriageModeName() {
        return carriageModeName;
    }

    public void setCarriageModeName(String carriageModeName) {
        this.carriageModeName = carriageModeName;
    }

    public Integer getCarriageModeId() {
        return carriageModeId;
    }

    public void setCarriageModeId(Integer carriageModeId) {
        this.carriageModeId = carriageModeId;
    }

    @Override
    public String toString() {
        return "PurchaseReceiveReVO[" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", receiveDate=" + receiveDate +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", status='" + status + '\'' +
                ", carriageModeName='" + carriageModeName + '\'' +
                ", carriageModeId='" + carriageModeId + '\'' +
                ']';
    }
}
