package com.rograndec.feijiayun.chain.business.purchase.ret.vo;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageDetail;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturn;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.math.BigDecimal;


/**
 * Created by zhaiwei on 2017/9/20.
 */
public class SaveOrUpdateDetailBzVO {

    private UserVO userVO;

    private PurchaseReturnDetailSaveOrUpdateVO detailVO;

    private PurchaseInStorageDetail purchaseInStorageDetail;

    private Goods goods;

    private LotNumber lotNumber;


    private PurchaseReturn purchaseReturn;
//    /**
//     * 头信息的wholeDiscount
//     */
//    private BigDecimal wholeDiscount;
//    /**
//     * 头信息的wholeDiscount
//     */
//    private BigDecimal amountTotal;

    /**
     * 利润金额
     */
    private BigDecimal profit = new BigDecimal(0);

    /**
     * 不含税利润金额
     */
    private BigDecimal notaxProfit = new BigDecimal(0);

    /**
     * 利润率
     */
    private BigDecimal profitRate = new BigDecimal(0);

    /**
     * 不含税利润率
     */
    private BigDecimal notaxProfitRate = new BigDecimal(0);


    private GoodsTaxRate taxRates;

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public PurchaseReturnDetailSaveOrUpdateVO getDetailVO() {
        return detailVO;
    }

    public void setDetailVO(PurchaseReturnDetailSaveOrUpdateVO detailVO) {
        this.detailVO = detailVO;
    }

    public PurchaseInStorageDetail getPurchaseInStorageDetail() {
        return purchaseInStorageDetail;
    }

    public void setPurchaseInStorageDetail(PurchaseInStorageDetail purchaseInStorageDetail) {
        this.purchaseInStorageDetail = purchaseInStorageDetail;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public LotNumber getLotNumber() {

        return lotNumber;
    }

    public void setLotNumber(LotNumber lotNumber) {
        this.lotNumber = lotNumber;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getNotaxProfit() {
        return notaxProfit;
    }

    public void setNotaxProfit(BigDecimal notaxProfit) {
        this.notaxProfit = notaxProfit;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public BigDecimal getNotaxProfitRate() {
        return notaxProfitRate;
    }

    public void setNotaxProfitRate(BigDecimal notaxProfitRate) {
        this.notaxProfitRate = notaxProfitRate;
    }

    public PurchaseReturn getPurchaseReturn() {
        return purchaseReturn;
    }

    public void setPurchaseReturn(PurchaseReturn purchaseReturn) {
        this.purchaseReturn = purchaseReturn;
    }

    public GoodsTaxRate getTaxRates() {
        return taxRates;
    }

    public void setTaxRates(GoodsTaxRate taxRates) {
        this.taxRates = taxRates;
    }

    //    public BigDecimal getWholeDiscount() {
//        return wholeDiscount;
//    }

//    public void setWholeDiscount(BigDecimal wholeDiscount) {
//        this.wholeDiscount = wholeDiscount;
//    }
//
//    public BigDecimal getAmountTotal() {
//        return amountTotal;
//    }
//
//    public void setAmountTotal(BigDecimal amountTotal) {
//        this.amountTotal = amountTotal;
//    }
}
