package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
@Validated
public class PrepayInvoiceDetailSaveVO implements Serializable {

    @ApiModelProperty(value = "id , 新增不需要,修改需要传递")
    private Long id;

    @ApiModelProperty(required = true, value = "商品id")
    @NotNull(message = "商品不能为空")
    private Long goodsId;

    @ApiModelProperty(required = true, value = "数量")
    @NotNull(message = "数量不能为空")
    private BigDecimal quantity = BigDecimal.ONE;
    /**
     * 最新一次入库单价
     */
    @NotNull(message = "单价不能为空")
    @ApiModelProperty(required = true, value = "单价")
    private BigDecimal unitPrice;

    /**
     *最新采购税率ID
     */
    @NotNull(message = "税率不能为空")
    @ApiModelProperty(required = true, value = "税率id")
    private Long taxRateId;

    /**
     * 行号
     */
    @NotNull(message = "行号不能为空")
    @ApiModelProperty(required = true, value = "行号")
    private Integer lineNum;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public static List<Long> getGoodsIds(List<PrepayInvoiceDetailSaveVO> prepayInvoiceDetailSaveVOS){

        return prepayInvoiceDetailSaveVOS.stream().map(PrepayInvoiceDetailSaveVO::getGoodsId).collect(Collectors.toList());

    }

    public static List<Long> getGoodsTaxRateIds(List<PrepayInvoiceDetailSaveVO> prepayInvoiceDetailSaveVOS){
        return prepayInvoiceDetailSaveVOS.stream().map(PrepayInvoiceDetailSaveVO::getTaxRateId).collect(Collectors.toList());

    }

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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
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

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}
