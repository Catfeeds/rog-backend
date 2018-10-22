package com.rograndec.feijiayun.chain.business.retail.royalty.vo.transfer;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.retail.royalty.entity.SaleRoyaltyTotal;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.SaveOrUpdateSaleRoyaltyDetailVO;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by zhaiwei on 2017/9/25.
 */
public class SaveOrUpdateDetailVO {

    private UserVO userVO;

    private SaveOrUpdateSaleRoyaltyDetailVO saveOrUpdateSaleRoyaltyDetailVO;

    private SaleRoyaltyTotal saleRoyaltyTotal;

    private Sale sale;

    private User clerk;


    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public SaveOrUpdateSaleRoyaltyDetailVO getSaveOrUpdateSaleRoyaltyDetailVO() {
        return saveOrUpdateSaleRoyaltyDetailVO;
    }

    public void setSaveOrUpdateSaleRoyaltyDetailVO(SaveOrUpdateSaleRoyaltyDetailVO saveOrUpdateSaleRoyaltyDetailVO) {
        this.saveOrUpdateSaleRoyaltyDetailVO = saveOrUpdateSaleRoyaltyDetailVO;
    }

    public SaleRoyaltyTotal getSaleRoyaltyTotal() {
        return saleRoyaltyTotal;
    }

    public void setSaleRoyaltyTotal(SaleRoyaltyTotal saleRoyaltyTotal) {
        this.saleRoyaltyTotal = saleRoyaltyTotal;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public User getClerk() {
        return clerk;
    }

    public void setClerk(User clerk) {
        this.clerk = clerk;
    }
}
