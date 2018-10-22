package com.rograndec.feijiayun.chain.business.retail.dailysettle.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by madong on 2017/9/21.
 */
public class WillDailySettleVO implements Serializable{
    /**
     * 款员交接班总单ID集合,多个用逗号分隔开
     */
    @ApiModelProperty(value = "款员交接班总单ID集合,多个用逗号分隔开")
    private String ids;
    /**
     * 款员接班日期
     */
    @ApiModelProperty(value = "款员接班日期")
    private Date shiftTime;
    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal saleAmount;
    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxRealAmountTotal;
    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmountTotal;
    /**
     * 门店ID
     */
    @ApiModelProperty(value = "门店ID")
    private Long storeID;
    /**
     * 门店名称
     */
    @ApiModelProperty(value = "门店名称")
    private String storeName;
    /**
     * 门店编码
     */
    @ApiModelProperty(value = "门店编码")
    private String storeCode;

    private static final long serialVersionUID = 1L;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Date getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(Date shiftTime) {
        this.shiftTime = shiftTime;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
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

    public Long getStoreID() {
        return storeID;
    }

    public void setStoreID(Long storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    @Override
    public String toString() {
        return "WillDailySettleVO{" +
                "ids='" + ids + '\'' +
                ", shiftTime=" + shiftTime +
                ", saleAmount=" + saleAmount +
                ", notaxRealAmountTotal=" + notaxRealAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", storeID=" + storeID +
                ", storeName='" + storeName + '\'' +
                ", storeCode='" + storeCode + '\'' +
                '}';
    }
}
