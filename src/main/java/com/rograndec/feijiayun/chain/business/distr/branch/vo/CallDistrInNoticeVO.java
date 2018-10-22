package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CallDistrInNoticeVO implements Serializable {

    /**
     * 配进订单主键ID
     */
    @ApiModelProperty(value = "配进订单主键ID")
    private Long id;

    /**
     * 配进订单日期
     */
    @ApiModelProperty(value = "配进订单日期")
    private Date orderDate;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID")
    private Long outboundUnitId;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码")
    private String outboundUnitCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称")
    private String outboundUnitName;

    @ApiModelProperty(value = "配货单位ID")
    private Long distrUnitId;

    @ApiModelProperty(value = "配货单位编码")
    private String distrUnitCode;

    @ApiModelProperty(value = "配货单位名称")
    private String distrUnitName;

    /**
     * 配进人员ID
     */
    @ApiModelProperty(value = "配进人员ID")
    private Long storageManId;

    /**
     * 配进人员
     */
    @ApiModelProperty(value = "配进人员")
    private String storageManName;


    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount;

    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    List<GoodsInNoticeVO> goodsInNoticeVOS;

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

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<GoodsInNoticeVO> getGoodsInNoticeVOS() {
        return goodsInNoticeVOS;
    }

    public void setGoodsInNoticeVOS(List<GoodsInNoticeVO> goodsInNoticeVOS) {
        this.goodsInNoticeVOS = goodsInNoticeVOS;
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

    @Override
    public String toString() {
        return "CallDistrInNoticeVO[" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", outboundUnitId=" + outboundUnitId +
                ", outboundUnitCode='" + outboundUnitCode + '\'' +
                ", outboundUnitName='" + outboundUnitName + '\'' +
                ", distrUnitId=" + distrUnitId +
                ", distrUnitCode='" + distrUnitCode + '\'' +
                ", distrUnitName='" + distrUnitName + '\'' +
                ", storageManId=" + storageManId +
                ", storageManName='" + storageManName + '\'' +
                ", distrType=" + distrType +
                ", quantityTotal=" + quantityTotal +
                ", amountTotal=" + amountTotal +
                ", wholeDiscount=" + wholeDiscount +
                ", wholeDiscountAmount=" + wholeDiscountAmount +
                ", realAmountTotal=" + realAmountTotal +
                ", notaxRealAmountTotal=" + notaxRealAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", remark='" + remark + '\'' +
                ", goodsInNoticeVOS=" + goodsInNoticeVOS +
                ']';
    }
}
