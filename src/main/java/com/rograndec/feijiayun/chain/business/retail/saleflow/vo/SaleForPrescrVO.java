package com.rograndec.feijiayun.chain.business.retail.saleflow.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_sale
 * 
 * 处方补登vo
 * @author sunteng
 * 
 * 2017-09-21
 */
public class SaleForPrescrVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 销售日期
     */
    @ApiModelProperty(value = "销售日期")
    private Date saleDate;

    /**
     * 销售单号
     */
    @ApiModelProperty(value = "销售单号")
    private String code;

    /**
     * 款台编码
     */
    @ApiModelProperty(value = "款台编码")
    private String standCode;


    /**
     * 收款人名称
     */
    @ApiModelProperty(value = "收款人名称")
    private String payeeName;

    /**
     * 销售模式（0-常规；1-中药）
     */
    @ApiModelProperty(value = "销售模式（0-常规；1-中药）")
    private Integer saleMode;

    /**
     * 销售类型（0-销售；1-销退）
     */
    @ApiModelProperty(value = "销售类型（0-销售；1-销退）")
    private Integer saleType;


    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "金额")
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStandCode() {
        return standCode;
    }

    public void setStandCode(String standCode) {
        this.standCode = standCode;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public Integer getSaleMode() {
        return saleMode;
    }

    public void setSaleMode(Integer saleMode) {
        this.saleMode = saleMode;
    }

    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
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
}