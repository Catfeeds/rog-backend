package com.rograndec.feijiayun.chain.business.purchase.receive.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by madong on 2017/9/15.
 */
public class SavePurchaseReceiveVO implements Serializable {
    private PurchaseReceiveReturnVO purchaseReceiveReturnVO;
    private List<PurchaseReceiveDetailReturnVO> purchaseReceiveDetailReturnVOs;
    private PurchaseReceiveOtherRetrunVO purchaseReceiveOtherRetrunVO;

    public PurchaseReceiveReturnVO getPurchaseReceiveReturnVO() {
        return purchaseReceiveReturnVO;
    }

    public void setPurchaseReceiveReturnVO(PurchaseReceiveReturnVO purchaseReceiveReturnVO) {
        this.purchaseReceiveReturnVO = purchaseReceiveReturnVO;
    }

    public PurchaseReceiveOtherRetrunVO getPurchaseReceiveOtherRetrunVO() {
        return purchaseReceiveOtherRetrunVO;
    }

    public void setPurchaseReceiveOtherRetrunVO(PurchaseReceiveOtherRetrunVO purchaseReceiveOtherRetrunVO) {
        this.purchaseReceiveOtherRetrunVO = purchaseReceiveOtherRetrunVO;
    }

    public List<PurchaseReceiveDetailReturnVO> getPurchaseReceiveDetailReturnVOs() {
        return purchaseReceiveDetailReturnVOs;
    }

    public void setPurchaseReceiveDetailReturnVOs(List<PurchaseReceiveDetailReturnVO> purchaseReceiveDetailReturnVOs) {
        this.purchaseReceiveDetailReturnVOs = purchaseReceiveDetailReturnVOs;
    }

    @Override
    public String toString() {
        return "SavePurchaseReceiveVO{" +
                "purchaseReceiveReturnVO=" + purchaseReceiveReturnVO +
                ", purchaseReceiveDetailReturnVOs=" + purchaseReceiveDetailReturnVOs +
                ", purchaseReceiveOtherRetrunVO=" + purchaseReceiveOtherRetrunVO +
                '}';
    }
}
