package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GoodsLotShelfVO implements Serializable {

    @ApiModelProperty(value = "货品ID", required = true)
    private Long goodsId;

    @ApiModelProperty(value = "批号ID", required = true)
    private Long lotId;

    @ApiModelProperty(value = "批号", required = true)
    private String lotNum;

    @ApiModelProperty(value = "生产日期", required = true)
    private Date productDate;

    @ApiModelProperty(value = "有效期至", required = true)
    private Date validUntil;

    @ApiModelProperty(value = "货位ID", required = true)
    private Long shelfId;

    @ApiModelProperty(value = "货位", required = true)
    private String shelfName;

    @ApiModelProperty(value = "库存可用", required = true)
    private BigDecimal usableQuantity;

    @ApiModelProperty(value = "出库数量", required = true)
    private BigDecimal outQuantity;

    @ApiModelProperty(value = "货位状态", required = true)
    private String shelfStatusDesc;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
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

    public BigDecimal getOutQuantity() {
        return outQuantity;
    }

    public void setOutQuantity(BigDecimal outQuantity) {
        this.outQuantity = outQuantity;
    }

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    @Override
    public String toString() {
        return "GoodsLotShelfVO[" +
                "goodsId=" + goodsId +
                ", lotId=" + lotId +
                ", lotNum='" + lotNum + '\'' +
                ", productDate=" + productDate +
                ", validUntil=" + validUntil +
                ", shelfId=" + shelfId +
                ", shelfName='" + shelfName + '\'' +
                ", usableQuantity=" + usableQuantity +
                ", outQuantity=" + outQuantity +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                ']';
    }
}
