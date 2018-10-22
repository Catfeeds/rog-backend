package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class PrepayInvoiceInStoreResponseTotalVO implements Serializable {

    @ApiModelProperty(value = "预付发票明细行id")
    private Long id;

    /**
     * 入库数量
     */
    @ApiModelProperty(value = "入库数量合计")
    private BigDecimal quantityTotal;

    /**
     * 未清数量
     */
    @ApiModelProperty(value = "未清数量合计")
    private BigDecimal unclearQuantityTotal;

    /**
     * 已清数量
     */
    @ApiModelProperty(value = "已清数量合计")
    private BigDecimal clearQuantityTotal;

    /**
     * 本次对账数量
     */
    @ApiModelProperty(value = "本次对账数量合计")
    private BigDecimal accountQuantityTotal = BigDecimal.ZERO;

    /**
     * 本次对账金额
     */
    @ApiModelProperty(value = "本次对账金额")
    private BigDecimal accountAmountTotal = BigDecimal.ZERO;

    /**
     * 本次对账不含税金额
     */
    @ApiModelProperty(value = "本次对账不含税金额")
    private BigDecimal accountNotaxAmountTotal = BigDecimal.ZERO;

    /**
     * 本次对账税额
     */
    @ApiModelProperty(value = "本次对账税额")
    private BigDecimal accountTaxAmountTotal = BigDecimal.ZERO;



    @ApiModelProperty(value = "入库清单列表")
    private List<PrepayInvoiceInStoreResponseVO> prepayInvoiceInStoreResponseVOS;

    public static void setPrepayInvoiceInStoreResponseTotalVO(PrepayInvoiceInStoreResponseTotalVO prepayInvoiceInStoreResponseTotalVO,List<PrepayInvoiceInStoreResponseVO> purchaseInStorageDetails){

        BigDecimal quantityTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getQuantity()).map(PrepayInvoiceInStoreResponseVO::getQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);
        /**
         * 入库数量
         */
        prepayInvoiceInStoreResponseTotalVO.setQuantityTotal(quantityTotal);

        /**
         * 未清数量
         */

        BigDecimal unclearQuantityTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getUnclearQuantity()).map(PrepayInvoiceInStoreResponseVO::getUnclearQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceInStoreResponseTotalVO.setUnclearQuantityTotal(unclearQuantityTotal);

        /**
         * 已清数量
         */

        BigDecimal clearQuantityTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getClearQuantity()).map(PrepayInvoiceInStoreResponseVO::getClearQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceInStoreResponseTotalVO.setClearQuantityTotal(clearQuantityTotal);

        /**
         * 本次对账数量
         */
        BigDecimal accountQuantityTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getAccountQuantity()).map(PrepayInvoiceInStoreResponseVO::getAccountQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceInStoreResponseTotalVO.setAccountQuantityTotal(accountQuantityTotal);

        /**
         * 本次对账金额
         */
        BigDecimal accountAmountTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getAccountAmount()).map(PrepayInvoiceInStoreResponseVO::getAccountAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceInStoreResponseTotalVO.setAccountAmountTotal(accountAmountTotal);

        /**
         * 本次对账不含税金额
         */
        BigDecimal accountNotaxAmountTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getAccountNotaxAmount()).map(PrepayInvoiceInStoreResponseVO::getAccountNotaxAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceInStoreResponseTotalVO.setAccountNotaxAmountTotal(accountNotaxAmountTotal);

        /**
         * 本次对账税额
         */
        BigDecimal accountTaxAmountTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getAccountTaxAmount()).map(PrepayInvoiceInStoreResponseVO::getAccountTaxAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceInStoreResponseTotalVO.setAccountTaxAmountTotal(accountTaxAmountTotal);



        prepayInvoiceInStoreResponseTotalVO.setPrepayInvoiceInStoreResponseVOS(purchaseInStorageDetails);

    }

    public static PrepayInvoiceInStoreResponseTotalVO getPrepayInvoiceInStoreResponseTotalVO(List<PrepayInvoiceInStoreResponseVO> purchaseInStorageDetails){

        PrepayInvoiceInStoreResponseTotalVO prepayInvoiceInStoreResponseTotalVO = new PrepayInvoiceInStoreResponseTotalVO();

        BigDecimal quantityTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getQuantity()).map(PrepayInvoiceInStoreResponseVO::getQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);
        /**
         * 入库数量
         */
        prepayInvoiceInStoreResponseTotalVO.setQuantityTotal(quantityTotal);

        /**
         * 未清数量
         */

        BigDecimal unclearQuantityTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getUnclearQuantity()).map(PrepayInvoiceInStoreResponseVO::getUnclearQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceInStoreResponseTotalVO.setUnclearQuantityTotal(unclearQuantityTotal);

        /**
         * 已清数量
         */

        BigDecimal clearQuantityTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getClearQuantity()).map(PrepayInvoiceInStoreResponseVO::getClearQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceInStoreResponseTotalVO.setClearQuantityTotal(clearQuantityTotal);

        /**
         * 本次对账数量
         */
        BigDecimal accountQuantityTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getAccountQuantity()).map(PrepayInvoiceInStoreResponseVO::getAccountQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceInStoreResponseTotalVO.setAccountQuantityTotal(accountQuantityTotal);

        /**
         * 本次对账金额
         */
        BigDecimal accountAmountTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getAccountAmount()).map(PrepayInvoiceInStoreResponseVO::getAccountAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceInStoreResponseTotalVO.setAccountAmountTotal(accountAmountTotal);

        /**
         * 本次对账不含税金额
         */
        BigDecimal accountNotaxAmountTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getAccountNotaxAmount()).map(PrepayInvoiceInStoreResponseVO::getAccountNotaxAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceInStoreResponseTotalVO.setAccountNotaxAmountTotal(accountNotaxAmountTotal);

        /**
         * 本次对账税额
         */
        BigDecimal accountTaxAmountTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getAccountTaxAmount()).map(PrepayInvoiceInStoreResponseVO::getAccountTaxAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceInStoreResponseTotalVO.setAccountTaxAmountTotal(accountTaxAmountTotal);

        prepayInvoiceInStoreResponseTotalVO.setPrepayInvoiceInStoreResponseVOS(purchaseInStorageDetails);

        return prepayInvoiceInStoreResponseTotalVO;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public BigDecimal getUnclearQuantityTotal() {
        return unclearQuantityTotal;
    }

    public void setUnclearQuantityTotal(BigDecimal unclearQuantityTotal) {
        this.unclearQuantityTotal = unclearQuantityTotal;
    }

    public BigDecimal getClearQuantityTotal() {
        return clearQuantityTotal;
    }

    public void setClearQuantityTotal(BigDecimal clearQuantityTotal) {
        this.clearQuantityTotal = clearQuantityTotal;
    }

    public List<PrepayInvoiceInStoreResponseVO> getPrepayInvoiceInStoreResponseVOS() {
        return prepayInvoiceInStoreResponseVOS;
    }

    public void setPrepayInvoiceInStoreResponseVOS(List<PrepayInvoiceInStoreResponseVO> prepayInvoiceInStoreResponseVOS) {
        this.prepayInvoiceInStoreResponseVOS = prepayInvoiceInStoreResponseVOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAccountQuantityTotal() {
        return accountQuantityTotal;
    }

    public void setAccountQuantityTotal(BigDecimal accountQuantityTotal) {
        this.accountQuantityTotal = accountQuantityTotal;
    }

    public BigDecimal getAccountAmountTotal() {
        return accountAmountTotal;
    }

    public void setAccountAmountTotal(BigDecimal accountAmountTotal) {
        this.accountAmountTotal = accountAmountTotal;
    }

    public BigDecimal getAccountNotaxAmountTotal() {
        return accountNotaxAmountTotal;
    }

    public void setAccountNotaxAmountTotal(BigDecimal accountNotaxAmountTotal) {
        this.accountNotaxAmountTotal = accountNotaxAmountTotal;
    }

    public BigDecimal getAccountTaxAmountTotal() {
        return accountTaxAmountTotal;
    }

    public void setAccountTaxAmountTotal(BigDecimal accountTaxAmountTotal) {
        this.accountTaxAmountTotal = accountTaxAmountTotal;
    }
}