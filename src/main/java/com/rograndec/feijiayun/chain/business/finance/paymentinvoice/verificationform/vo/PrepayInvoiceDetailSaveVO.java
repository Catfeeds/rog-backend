package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class PrepayInvoiceDetailSaveVO implements Serializable {

    @ApiModelProperty(required = true, value = "id")
    private Long id;

    @ApiModelProperty(required = true, value = "商品id")
    private Long goodsId;

    @ApiModelProperty(value = "数量")
    private BigDecimal quantity = BigDecimal.ONE;
    /**
     * 最新一次入库单价
     */
    @ApiModelProperty(required = true, value = "单价")
    private BigDecimal price;

    /**
     *最新采购税率ID
     */
    @ApiModelProperty(required = true, value = "税率id")
    private BigDecimal taxRateId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(BigDecimal taxRateId) {
        this.taxRateId = taxRateId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
