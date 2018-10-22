package com.rograndec.feijiayun.chain.business.retail.royalty.vo.transfer;

import com.rograndec.feijiayun.chain.business.retail.royalty.entity.SaleRoyaltyDetail;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleDetail;

/**
 * Created by zhaiwei on 2017/9/26.
 */
public class DetailVO {

    private Sale sale;
    private SaleDetail saleDetail;
    private SaleRoyaltyDetail saleRoyaltyDetail;
//    private RoyaltyGoods royaltyGoods;

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public SaleDetail getSaleDetail() {
        return saleDetail;
    }

    public void setSaleDetail(SaleDetail saleDetail) {
        this.saleDetail = saleDetail;
    }

    public SaleRoyaltyDetail getSaleRoyaltyDetail() {
        return saleRoyaltyDetail;
    }

    public void setSaleRoyaltyDetail(SaleRoyaltyDetail saleRoyaltyDetail) {
        this.saleRoyaltyDetail = saleRoyaltyDetail;
    }

}
