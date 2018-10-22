package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 
 * saas_prepay_invoice
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
@ApiModel
public class PrepayInvoiceResponseTotalVO implements Serializable {

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal = BigDecimal.ZERO;
    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxAmountTotal = BigDecimal.ZERO;
    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "列表数据集合")
    private List<PrepayInvoiceResponseVO> prepayInvoiceResponseVOS = Collections.emptyList();

    public static PrepayInvoiceResponseTotalVO getPrepayInvoiceResponseTotalVO(List<PrepayInvoiceResponseVO> prepayInvoiceResponseVOS){

        PrepayInvoiceResponseTotalVO responseTotalVO = new PrepayInvoiceResponseTotalVO();

        BigDecimal amountTotal = prepayInvoiceResponseVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getAmountTotal()).map(PrepayInvoiceResponseVO::getAmountTotal).reduce(BigDecimal.ZERO,BigDecimal::add);

        BigDecimal notaxAmountTotal = prepayInvoiceResponseVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getNotaxAmountTotal()).map(PrepayInvoiceResponseVO::getNotaxAmountTotal).reduce(BigDecimal.ZERO,BigDecimal::add);

        BigDecimal taxAmountTotal = prepayInvoiceResponseVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getTaxAmountTotal()).map(PrepayInvoiceResponseVO::getTaxAmountTotal).reduce(BigDecimal.ZERO,BigDecimal::add);

        responseTotalVO.setAmountTotal(amountTotal);
        responseTotalVO.setNotaxAmountTotal(notaxAmountTotal);
        responseTotalVO.setTaxAmountTotal(taxAmountTotal);

        return responseTotalVO;

    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public List<PrepayInvoiceResponseVO> getPrepayInvoiceResponseVOS() {
        return prepayInvoiceResponseVOS;
    }

    public void setPrepayInvoiceResponseVOS(List<PrepayInvoiceResponseVO> prepayInvoiceResponseVOS) {
        this.prepayInvoiceResponseVOS = prepayInvoiceResponseVOS;
    }
}