package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.business.purchase.ret.constant.ReturnType;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.ReportCommonGoodsVO;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述:
 * Created by ST on 2017/10/21 19:47
 */

public class PurchaseReturnOutGoodsReportVO extends ReportCommonGoodsVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 购进退出单号
     */
    @ApiModelProperty(value = "购进退出出库单号")
    private String code;

    /**
     * 购进退出日期
     */
    @ApiModelProperty(value = "购进退出出库日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date outDate;


    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID" )
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型")
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "购进退出单号")
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date baseOrderDate;

    /**
     * 退货类型（0-质量问题退货；1-非质量问题退货）
     */
    @ApiModelProperty(value = "退货类型（0-质量问题退货；1-非质量问题退货）")
    private Integer returnType;

    @ApiModelProperty(value = "退货类型（0-质量问题退货；1-非质量问题退货）")
    private String returnTypeName;

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

    @ApiModelProperty(value = "出库人员名称")
    private String outManName;

    @ApiModelProperty(value = "供货单位销售人编码")
    private String supplierSalerCode;
    @ApiModelProperty(value = "供货单位销售人员姓名")
    private String supplierSalerName;
    @ApiModelProperty(value = "供货单位销售人员电话")
    private String supplierSalerPhone;

    /**
     * 流通监管码
     */
    @ApiModelProperty(value = "流通监管码")
    private String flowCode;



    @ApiModelProperty(value = "订单单号编码")
    private String orderCode;
    @ApiModelProperty(value = "订单单号ID")
    private Long purchaseOrderId;

    private Integer status;
    private String statusName;



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


    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getSupplierSalerCode() {
        return supplierSalerCode;
    }

    public void setSupplierSalerCode(String supplierSalerCode) {
        this.supplierSalerCode = supplierSalerCode;
    }

    public String getSupplierSalerName() {
        return supplierSalerName;
    }

    public void setSupplierSalerName(String supplierSalerName) {
        this.supplierSalerName = supplierSalerName;
    }

    public String getSupplierSalerPhone() {
        return supplierSalerPhone;
    }

    public void setSupplierSalerPhone(String supplierSalerPhone) {
        this.supplierSalerPhone = supplierSalerPhone;
    }

    public String getOutManName() {
        return outManName;
    }

    public void setOutManName(String outManName) {
        this.outManName = outManName;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        if(status != null){
            return PurchaseStatus.getName(status);
        }
        return "";
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    public String getReturnTypeName() {
        return  returnType == null ? "" : ReturnType.getValue(returnType).getValue();
    }

    public void setReturnTypeName(String returnTypeName) {
        this.returnTypeName = returnTypeName;
    }

}