package com.rograndec.feijiayun.chain.business.keytable.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述：
 * Created by zhaiwei on 2017/9/30 16:29
 */
@ApiModel
public class StockLockRecordVO {

    @ApiModelProperty("锁定库存表id")
    private Long stockLockRecordId;

    @ApiModelProperty("商品id")
    private Long goodsId;

    @ApiModelProperty("商品编码")
    private String goodsCode;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("生产日期")
    private Date productDate;

    @ApiModelProperty("有效期")
    private Date validDate;

    @ApiModelProperty("批号ID")
    private Long lotId;

    @ApiModelProperty("批号")
    private String lotNum;

    @ApiModelProperty("货位id")
    private Long shelfId;

    @ApiModelProperty("货位名称")
    private String shelfName;

    @ApiModelProperty("可用数量")
    private BigDecimal usableQuantity;

    @ApiModelProperty("库存数量")
    private BigDecimal quantity;

    @ApiModelProperty("货位质量状态描述")
    private String shelfStatusDesc;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
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

    public BigDecimal getUsableQuantity() {
        return usableQuantity;
    }

    public void setUsableQuantity(BigDecimal usableQuantity) {
        this.usableQuantity = usableQuantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    public Long getStockLockRecordId() {
        return stockLockRecordId;
    }

    public void setStockLockRecordId(Long stockLockRecordId) {
        this.stockLockRecordId = stockLockRecordId;
    }
}