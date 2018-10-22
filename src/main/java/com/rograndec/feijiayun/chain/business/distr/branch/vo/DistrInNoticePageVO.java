package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by dudy on 2017/10/8.
 */
public class DistrInNoticePageVO  implements Serializable {


    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 配进订单单号
     */
    @ApiModelProperty(value = "配进订单单号")
    private String code;

    /**
     * 配进订单日期
     */
    @ApiModelProperty(value = "配进订单日期")
    private Date orderDate;

    /**
     * 配货单位ID
     */
    @ApiModelProperty(value = "配货单位ID")
    private Long distrUnitId;

    /**
     * 配货单位编码
     */
    @ApiModelProperty(value = "配货单位编码")
    private String distrUnitCode;

    /**
     * 配货单位名称
     */
    @ApiModelProperty(value = "配货单位名称")
    private String distrUnitName;

    /**
     * 配进人员ID
     */
    @ApiModelProperty(value = "配进人员ID")
    private Long storageManId;


    @ApiModelProperty(value = "配进人员名称")
    private String storageManName;

    /**
     *配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;

    @ApiModelProperty(value = "配货类型名称")
    private String distrTypeName;


    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;


    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;


    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;


    @ApiModelProperty(value = "状态: 21-待审核,22-审核通过,23-审核驳回,30-待收货,31-待验收（已收货）,32-待入库（已验收）,33-已完成（已入库）,34-已取消")
    private Integer status;

    @ApiModelProperty(value = "状态描述")
    private String  statusName;

    /**
     * 供货单位ID(调出单位ID)
     */
    @ApiModelProperty(value = "供货单位ID(调出单位ID)")
    private Long outboundUnitId;

    /**
     * 供货单位编码(调出单位编码)
     */
    @ApiModelProperty(value = "供货单位编码(调出单位编码)")
    private String outboundUnitCode;

    /**
     * 供货单位名称(调出单位名称)
     */
    @ApiModelProperty(value = "供货单位名称(调出单位名称)")
    private String outboundUnitName;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getDistrUnitId() {
        return distrUnitId;
    }

    public void setDistrUnitId(Long distrUnitId) {
        this.distrUnitId = distrUnitId;
    }

    public String getDistrUnitCode() {
        return distrUnitCode;
    }

    public void setDistrUnitCode(String distrUnitCode) {
        this.distrUnitCode = distrUnitCode;
    }

    public String getDistrUnitName() {
        return distrUnitName;
    }

    public void setDistrUnitName(String distrUnitName) {
        this.distrUnitName = distrUnitName;
    }

    public Long getStorageManId() {
        return storageManId;
    }

    public void setStorageManId(Long storageManId) {
        this.storageManId = storageManId;
    }

    public String getStorageManName() {
        return storageManName;
    }

    public void setStorageManName(String storageManName) {
        this.storageManName = storageManName;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public String getDistrTypeName() {
        return distrTypeName;
    }

    public void setDistrTypeName(String distrTypeName) {
        this.distrTypeName = distrTypeName;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getOutboundUnitId() {
        return outboundUnitId;
    }

    public void setOutboundUnitId(Long outboundUnitId) {
        this.outboundUnitId = outboundUnitId;
    }

    public String getOutboundUnitCode() {
        return outboundUnitCode;
    }

    public void setOutboundUnitCode(String outboundUnitCode) {
        this.outboundUnitCode = outboundUnitCode;
    }

    public String getOutboundUnitName() {
        return outboundUnitName;
    }

    public void setOutboundUnitName(String outboundUnitName) {
        this.outboundUnitName = outboundUnitName;
    }
}
