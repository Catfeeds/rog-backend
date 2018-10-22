package com.rograndec.feijiayun.chain.business.purchase.receive.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by madong on 2017/9/14.
 */
public class PurchaseReceiveInfoVO implements Serializable{
    private PurchaseReceiveVO purchaseReceiveVO;
    private List<PurchaseReceiveDetailVO> purchaseReceiveDetailVOs;
    private PurchaseReceiveOrderVO purchaseReceiveOrderVO;

    public PurchaseReceiveVO getPurchaseReceiveVO() {
        return purchaseReceiveVO;
    }

    public void setPurchaseReceiveVO(PurchaseReceiveVO purchaseReceiveVO) {
        this.purchaseReceiveVO = purchaseReceiveVO;
    }

    public List<PurchaseReceiveDetailVO> getPurchaseReceiveDetailVOs() {
        return purchaseReceiveDetailVOs;
    }

    public void setPurchaseReceiveDetailVOs(List<PurchaseReceiveDetailVO> purchaseReceiveDetailVOs) {
        this.purchaseReceiveDetailVOs = purchaseReceiveDetailVOs;
    }

    public PurchaseReceiveOrderVO getPurchaseReceiveOrderVO() {
        return purchaseReceiveOrderVO;
    }

    public void setPurchaseReceiveOrderVO(PurchaseReceiveOrderVO purchaseReceiveOrderVO) {
        this.purchaseReceiveOrderVO = purchaseReceiveOrderVO;
    }

    @Override
    public String toString() {
        return "PurchaseReceiveInfoVO{" +
                "purchaseReceiveVO=" + purchaseReceiveVO +
                ", purchaseReceiveDetailVOs=" + purchaseReceiveDetailVOs +
                ", purchaseReceiveOrderVO=" + purchaseReceiveOrderVO +
                '}';
    }
}
