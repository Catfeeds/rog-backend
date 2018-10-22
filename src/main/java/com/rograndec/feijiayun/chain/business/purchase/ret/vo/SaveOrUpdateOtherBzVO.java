package com.rograndec.feijiayun.chain.business.purchase.ret.vo;

import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturn;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by zhaiwei on 2017/9/20.
 */
public class SaveOrUpdateOtherBzVO {

    private UserVO userVO;

    private PurchaseReturnOtherSaveOrUpdateVO otherVO;

    private PurchaseReturn purchaseReturn;

    private PurchaseOrder purchaseOrder;

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public PurchaseReturnOtherSaveOrUpdateVO getOtherVO() {
        return otherVO;
    }

    public void setOtherVO(PurchaseReturnOtherSaveOrUpdateVO otherVO) {
        this.otherVO = otherVO;
    }

    public PurchaseReturn getPurchaseReturn() {
        return purchaseReturn;
    }

    public void setPurchaseReturn(PurchaseReturn purchaseReturn) {
        this.purchaseReturn = purchaseReturn;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
}
