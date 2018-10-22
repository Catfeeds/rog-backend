package com.rograndec.feijiayun.chain.business.keytable.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述：
 * Created by ST on 2017/9/15 21:02
 */

public class StockVO {


    /**
     * 可用数量
     */
    @ApiModelProperty(value = "可用数量")
    private BigDecimal usableQuantity;

    /**
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNum;
    @ApiModelProperty(value = "批号id")
    private Long lotId;

    /**
     * 批次号
     */
    @ApiModelProperty(value = "批次号")
    private String batchNum;

    /**
     * 库存数量
     */
    @ApiModelProperty(value = "库存数量")
    private BigDecimal quantity;

    /**
     * 占用数量
     */
    @ApiModelProperty(value = "占用数量")
    private BigDecimal lockQuantity;

    @ApiModelProperty(value = "生成日期")
    private Date productDate;


    public BigDecimal getUsableQuantity() {
        return usableQuantity;
    }

    public void setUsableQuantity(BigDecimal usableQuantity) {
        this.usableQuantity = usableQuantity;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(BigDecimal lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }
}