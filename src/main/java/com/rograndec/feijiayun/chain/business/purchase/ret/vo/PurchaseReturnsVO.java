package com.rograndec.feijiayun.chain.business.purchase.ret.vo;

import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnInVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturn;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnDetail;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnOther;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/9/15 15:09
 */

public class PurchaseReturnsVO implements Serializable{

    private PurchaseReturn purchaseReturn;

    private List<PurchaseReturnDetail> purchaseReturnDetails;

    private PurchaseReturnOther purchaseReturnOther;

    private Long inOutManId;

    private DistrReturnInVO distrReturnInVO;

    public Long getInOutManId() {
        return inOutManId;
    }

    public void setInOutManId(Long inOutManId) {
        this.inOutManId = inOutManId;
    }

    public PurchaseReturn getPurchaseReturn() {
        return purchaseReturn;
    }

    public void setPurchaseReturn(PurchaseReturn purchaseReturn) {
        this.purchaseReturn = purchaseReturn;
    }

    public List<PurchaseReturnDetail> getPurchaseReturnDetails() {
        return purchaseReturnDetails;
    }

    public void setPurchaseReturnDetails(List<PurchaseReturnDetail> purchaseReturnDetails) {
        this.purchaseReturnDetails = purchaseReturnDetails;
    }

    public PurchaseReturnOther getPurchaseReturnOther() {
        return purchaseReturnOther;
    }

    public void setPurchaseReturnOther(PurchaseReturnOther purchaseReturnOther) {
        this.purchaseReturnOther = purchaseReturnOther;
    }

    public DistrReturnInVO getDistrReturnInVO() {
        return distrReturnInVO;
    }

    public void setDistrReturnInVO(DistrReturnInVO distrReturnInVO) {
        this.distrReturnInVO = distrReturnInVO;
    }
}