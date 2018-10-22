package com.rograndec.feijiayun.chain.business.purchase.retout.vo;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInShelf;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockShelfVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturn;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnDetail;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnOther;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.PurchaseReturnsVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述：购进退出出库实体
 * Created by ST on 2017/9/16 10:25
 */

public class RequestPurchaseReturnOutInfoVO implements Serializable {


    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 出库人员ID
     */
    @ApiModelProperty(value = "出库人员ID")
    private Long outManId;

    @ApiModelProperty(value = "出库日期")
    private Date outDate;

    @ApiModelProperty(value = "流通监管码")
    private String flowCode;
    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;
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

    @ApiModelProperty(value = "订单明细")
    private List<PurchaseReturnDetailOutVO> purchaseReturnDetailVOList;
    @ApiModelProperty(value = "配送和结算")
    private PurchaseReturnOutOtherVO purchaseReturnOtherVO;

    public static RequestPurchaseReturnOutInfoVO getRequestPurchaseReturnOutInfoVO(PurchaseReturnsVO purchaseReturnsVO){

        PurchaseReturn purchaseReturn = purchaseReturnsVO.getPurchaseReturn();

        List<PurchaseReturnDetail> purchaseReturnDetails = purchaseReturnsVO.getPurchaseReturnDetails();

        PurchaseReturnOther purchaseReturnOther = purchaseReturnsVO.getPurchaseReturnOther();

        List<DistrReturnInShelf> distrReturnInShelfs = purchaseReturnsVO.getDistrReturnInVO().getDistrReturnInShelfs();

        RequestPurchaseReturnOutInfoVO requestPurchaseReturnOutInfoVO = new RequestPurchaseReturnOutInfoVO();


        /**
         * 主键ID
         */
        requestPurchaseReturnOutInfoVO.setId(purchaseReturn.getId());

        /**
         * 出库人员ID
         */
        requestPurchaseReturnOutInfoVO.setOutManId(purchaseReturnsVO.getInOutManId());

        requestPurchaseReturnOutInfoVO.setOutDate(new Date());

        /**
         * 数量合计
         */
        requestPurchaseReturnOutInfoVO.setQuantityTotal(purchaseReturn.getQuantityTotal());

        /**
         * 品种数量
         */
        requestPurchaseReturnOutInfoVO.setVarietiesQuantity(purchaseReturn.getVarietiesQuantity());

        /**
         * 金额合计（整单优惠前的金额合计）
         */
        requestPurchaseReturnOutInfoVO.setAmountTotal(purchaseReturn.getAmountTotal());

        /**
         * 整单折扣（%）
         */
        requestPurchaseReturnOutInfoVO.setWholeDiscount(purchaseReturn.getWholeDiscount());

        /**
         * 整单优惠金额
         */
        requestPurchaseReturnOutInfoVO.setWholeDiscountAmount(purchaseReturn.getWholeDiscountAmount());


        /**
         * 配送和结算信息
         */
        PurchaseReturnOutOtherVO purchaseReturnOutOtherVO = PurchaseReturnOutOtherVO.getPurchaseReturnOutOtherVO(purchaseReturnOther);
        requestPurchaseReturnOutInfoVO.setPurchaseReturnOtherVO(purchaseReturnOutOtherVO);

        /**
         * 购进退出出库明细
         */
        List<PurchaseReturnDetailOutVO> purchaseReturnDetailOutVOS = purchaseReturnDetails.stream().map(purchaseReturnDetail -> {

            PurchaseReturnDetailOutVO purchaseReturnDetailOutVO = new PurchaseReturnDetailOutVO();

            /**
             * 主键ID
             */
            purchaseReturnDetailOutVO.setId(purchaseReturnDetail.getId());

            /**
             * 商品ID
             */
            purchaseReturnDetailOutVO.setGoodsId(purchaseReturnDetail.getGoodsId());

            /**
             * 数量
             */
            purchaseReturnDetailOutVO.setQuantity(purchaseReturnDetail.getQuantity());

            /**
             * 单价
             */
            purchaseReturnDetailOutVO.setUnitPrice(purchaseReturnDetail.getUnitPrice());

            /**
             * 行折扣（%）
             */
            purchaseReturnDetailOutVO.setLineDiscount(purchaseReturnDetail.getLineDiscount());

            /**
             * 进项税id
             */
            purchaseReturnDetailOutVO.setTaxRateId(purchaseReturnDetail.getTaxRateId());

            /**
             * 进项税
             */
            purchaseReturnDetailOutVO.setTaxRate(purchaseReturnDetail.getTaxRate());

            /**
             * 退货原因ID集合，多个用逗号隔开
             */
            purchaseReturnDetailOutVO.setReturnReasonIds(purchaseReturnDetail.getReturnReasonIds());

            /**
             * 行号
             */
            purchaseReturnDetailOutVO.setLineNum(purchaseReturnDetail.getLineNum());

            List<StockShelfVO> stockShelfVOS = distrReturnInShelfs.stream().map(distrReturnInShelf -> {
                if (distrReturnInShelf.getGoodsId().equals(purchaseReturnDetail.getGoodsId()) && distrReturnInShelf.getLotNumber().equals(purchaseReturnDetail.getLotNumber())) {

                    StockShelfVO stockShelfVO = new StockShelfVO();

                    /**
                     * 数量
                     */
                    stockShelfVO.setQuality(distrReturnInShelf.getQuantity());

                    /**
                     * 可用数量
                     */
                    stockShelfVO.setUsableQuantity(distrReturnInShelf.getQuantity());

                    /**
                     * 批号id
                     */
                    stockShelfVO.setLotId(purchaseReturnDetail.getLotId());

                    /**
                     * 批号
                     */
                    stockShelfVO.setLotNumber(purchaseReturnDetail.getLotNumber());

                    /**
                     * 生产日期
                     */
                    stockShelfVO.setProductDate(distrReturnInShelf.getProductDate());

                    /**
                     * 有效期
                     */
                    stockShelfVO.setValidDate(distrReturnInShelf.getValidDate());

                    /**
                     * 货位ID
                     */
                    stockShelfVO.setShelfId(distrReturnInShelf.getShelfId());

                    /**
                     * 货位名称
                     */
                    stockShelfVO.setShelfName(distrReturnInShelf.getShelfName());

                    /**
                     * 质量状况
                     */
                    stockShelfVO.setQualityState("合格品");

                    return stockShelfVO;
                }
                return null;
            }).filter(d -> null != d).collect(Collectors.toList());

            purchaseReturnDetailOutVO.setStockShelfVOList(stockShelfVOS);

            return purchaseReturnDetailOutVO;
        }).filter(pdr -> null != pdr).collect(Collectors.toList());


        requestPurchaseReturnOutInfoVO.setPurchaseReturnDetailVOList(purchaseReturnDetailOutVOS);

        return requestPurchaseReturnOutInfoVO;
    }


    public List<PurchaseReturnDetailOutVO> getPurchaseReturnDetailVOList() {
        return purchaseReturnDetailVOList;
    }

    public void setPurchaseReturnDetailVOList(List<PurchaseReturnDetailOutVO> purchaseReturnDetailVOList) {
        this.purchaseReturnDetailVOList = purchaseReturnDetailVOList;
    }

    public PurchaseReturnOutOtherVO getPurchaseReturnOtherVO() {
        return purchaseReturnOtherVO;
    }

    public void setPurchaseReturnOtherVO(PurchaseReturnOutOtherVO purchaseReturnOtherVO) {
        this.purchaseReturnOtherVO = purchaseReturnOtherVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOutManId() {
        return outManId;
    }

    public void setOutManId(Long outManId) {
        this.outManId = outManId;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }
}