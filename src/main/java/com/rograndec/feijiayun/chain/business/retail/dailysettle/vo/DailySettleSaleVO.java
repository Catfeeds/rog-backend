package com.rograndec.feijiayun.chain.business.retail.dailysettle.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_sale
 * 
 * 
 * @author Administrator
 * 
 * 2017-09-21
 */
public class DailySettleSaleVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "零售ID",required = true)
    private Long id;

    /**
     * 交班明细ID
     */
    @ApiModelProperty(value = "交班明细ID",required = true)
    private Long shiftDetailId;

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
     * 销售模式（0-常规；1-中药）
     */
    @ApiModelProperty(value = "销售模式（0-常规；1-中药）")
    private Integer saleMode;

    /**
     * 销售模式名称
     */
    @ApiModelProperty(value = "销售模式名称")
    private String saleModeName;

    /**
     * 销售类型（0-销售；1-销退）
     */
    @ApiModelProperty(value = "销售类型（0-销售；1-销退）")
    private Integer saleType;

    /**
     * 销售类型名称
     */
    @ApiModelProperty(value = "销售类型名称 ")
    private String saleTypeName;

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
     * 利润合计
     */
    @ApiModelProperty(value = "利润合计")
    private BigDecimal profitTotal;

    /**
     * 不含税利润合计
     */
    @ApiModelProperty(value = "不含税利润合计")
    private BigDecimal notaxProfitTotal;

    /**
     * 利润率
     */
    @ApiModelProperty(value = "利润率")
    private BigDecimal profitRate;

    /**
     * 不含税利润率
     */
    @ApiModelProperty(value = "不含税利润率")
    private BigDecimal notaxProfitRate;

    /**
     * 超量销售标识（0-否；1-是；2-已处理）
     */
    @ApiModelProperty(value = "超量销售标识（0-否；1-是；2-已处理）")
    private Integer excessiveSale;

    /**
     * saas_sale
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
     * 销售日期
     * @return sale_date 销售日期
     */
    public Date getSaleDate() {
        return saleDate;
    }

    /**
     * 销售日期
     * @param saleDate 销售日期
     */
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    /**
     * 销售单号
     * @return code 销售单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 销售单号
     * @param code 销售单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
    /**
     * 款台编码
     * @return stand_code 款台编码
     */
    public String getStandCode() {
        return standCode;
    }

    /**
     * 款台编码
     * @param standCode 款台编码
     */
    public void setStandCode(String standCode) {
        this.standCode = standCode == null ? null : standCode.trim();
    }

    /**
     * 销售模式（0-常规；1-中药）
     * @return sale_mode 销售模式（0-常规；1-中药）
     */
    public Integer getSaleMode() {
        return saleMode;
    }

    /**
     * 销售模式（0-常规；1-中药）
     * @param saleMode 销售模式（0-常规；1-中药）
     */
    public void setSaleMode(Integer saleMode) {
        this.saleMode = saleMode;
    }

    /**
     * 销售类型（0-销售；1-销退）
     * @return sale_type 销售类型（0-销售；1-销退）
     */
    public Integer getSaleType() {
        return saleType;
    }

    /**
     * 销售类型（0-销售；1-销退）
     * @param saleType 销售类型（0-销售；1-销退）
     */
    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @return amount_total 金额合计（整单优惠前的金额合计）
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @param amountTotal 金额合计（整单优惠前的金额合计）
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 整单折扣（%）
     * @return whole_discount 整单折扣（%）
     */
    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    /**
     * 整单折扣（%）
     * @param wholeDiscount 整单折扣（%）
     */
    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    /**
     * 整单优惠金额
     * @return whole_discount_amount 整单优惠金额
     */
    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    /**
     * 整单优惠金额
     * @param wholeDiscountAmount 整单优惠金额
     */
    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    /**
     * 实际金额合计
     * @return real_amount_total 实际金额合计
     */
    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    /**
     * 实际金额合计
     * @param realAmountTotal 实际金额合计
     */
    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_real_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxRealAmountTotal 不含税金额合计
     */
    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    /**
     * 税额合计
     * @return tax_amount_total 税额合计
     */
    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    /**
     * 税额合计
     * @param taxAmountTotal 税额合计
     */
    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    /**
     * 利润合计
     * @return profit_total 利润合计
     */
    public BigDecimal getProfitTotal() {
        return profitTotal;
    }

    /**
     * 利润合计
     * @param profitTotal 利润合计
     */
    public void setProfitTotal(BigDecimal profitTotal) {
        this.profitTotal = profitTotal;
    }

    /**
     * 不含税利润合计
     * @return notax_profit_total 不含税利润合计
     */
    public BigDecimal getNotaxProfitTotal() {
        return notaxProfitTotal;
    }

    /**
     * 不含税利润合计
     * @param notaxProfitTotal 不含税利润合计
     */
    public void setNotaxProfitTotal(BigDecimal notaxProfitTotal) {
        this.notaxProfitTotal = notaxProfitTotal;
    }

    /**
     * 利润率
     * @return profit_rate 利润率
     */
    public BigDecimal getProfitRate() {
        return profitRate;
    }

    /**
     * 利润率
     * @param profitRate 利润率
     */
    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    /**
     * 不含税利润率
     * @return notax_profit_rate 不含税利润率
     */
    public BigDecimal getNotaxProfitRate() {
        return notaxProfitRate;
    }

    /**
     * 不含税利润率
     * @param notaxProfitRate 不含税利润率
     */
    public void setNotaxProfitRate(BigDecimal notaxProfitRate) {
        this.notaxProfitRate = notaxProfitRate;
    }

    public Long getShiftDetailId() {
        return shiftDetailId;
    }

    public void setShiftDetailId(Long shiftDetailId) {
        this.shiftDetailId = shiftDetailId;
    }

    public String getSaleModeName() {
        return saleModeName;
    }

    public void setSaleModeName(String saleModeName) {
        this.saleModeName = saleModeName;
    }

    public String getSaleTypeName() {
        return saleTypeName;
    }

    public void setSaleTypeName(String saleTypeName) {
        this.saleTypeName = saleTypeName;
    }

    public Integer getExcessiveSale() {
        return excessiveSale;
    }

    public void setExcessiveSale(Integer excessiveSale) {
        this.excessiveSale = excessiveSale;
    }

    @Override
    public String toString() {
        return "DailySettleSaleVO{" +
                "id=" + id +
                ", shiftDetailId=" + shiftDetailId +
                ", saleDate=" + saleDate +
                ", code='" + code + '\'' +
                ", standCode='" + standCode + '\'' +
                ", saleMode=" + saleMode +
                ", saleModeName='" + saleModeName + '\'' +
                ", saleType=" + saleType +
                ", saleTypeName='" + saleTypeName + '\'' +
                ", amountTotal=" + amountTotal +
                ", wholeDiscount=" + wholeDiscount +
                ", wholeDiscountAmount=" + wholeDiscountAmount +
                ", realAmountTotal=" + realAmountTotal +
                ", notaxRealAmountTotal=" + notaxRealAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", profitTotal=" + profitTotal +
                ", notaxProfitTotal=" + notaxProfitTotal +
                ", profitRate=" + profitRate +
                ", notaxProfitRate=" + notaxProfitRate +
                ", excessiveSale=" + excessiveSale +
                '}';
    }
}