package com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述：
 * Created by ST on 2017/10/10 15:31
 */

public class GoodsSimpleVO {
    /**
     * 商品ID
     */
    @ApiModelProperty(required = true, value = "商品ID")
    private Long goodsId;


    /**
     * 批号ID
     */
    @ApiModelProperty(required = true, value = "批号ID")
    private Long lotId;

    /**
     * 批号
     */
    @ApiModelProperty(required = true, value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(required = true, value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(required = true, value = "有效期")
    private Date validDate;

    /**
     * 数量
     */
    @ApiModelProperty(required = true, value = "数量")
    private BigDecimal quantity;

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

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}