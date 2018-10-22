package com.rograndec.feijiayun.chain.business.retail.royalty.vo.transfer;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.retail.royalty.entity.SaleRoyalty;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.SaveOrUpdateSaleRoyaltyTotalVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by zhaiwei on 2017/9/25.
 */
public class SaveOrUpdateTotalVO {

    private UserVO userVO;

    private SaveOrUpdateSaleRoyaltyTotalVO saveOrUpdateSaleRoyaltyTotalVO;

    private SaleRoyalty saleRoyalty;

    private User clerk;

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public SaveOrUpdateSaleRoyaltyTotalVO getSaveOrUpdateSaleRoyaltyTotalVO() {
        return saveOrUpdateSaleRoyaltyTotalVO;
    }

    public void setSaveOrUpdateSaleRoyaltyTotalVO(SaveOrUpdateSaleRoyaltyTotalVO saveOrUpdateSaleRoyaltyTotalVO) {
        this.saveOrUpdateSaleRoyaltyTotalVO = saveOrUpdateSaleRoyaltyTotalVO;
    }

    public SaleRoyalty getSaleRoyalty() {
        return saleRoyalty;
    }

    public void setSaleRoyalty(SaleRoyalty saleRoyalty) {
        this.saleRoyalty = saleRoyalty;
    }

    public User getClerk() {
        return clerk;
    }

    public void setClerk(User clerk) {
        this.clerk = clerk;
    }
}
