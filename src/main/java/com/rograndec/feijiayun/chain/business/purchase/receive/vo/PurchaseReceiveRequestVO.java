package com.rograndec.feijiayun.chain.business.purchase.receive.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by madong on 2017/9/18.
 */
public class PurchaseReceiveRequestVO implements Serializable {
    private PurchaseReceiveVO purchaseReceiveVO;
    private List<PurchaseReceiveDetailVO> purchaseReceiveDetailVOS;

    public PurchaseReceiveVO getPurchaseReceiveVO() {
        return purchaseReceiveVO;
    }

    public void setPurchaseReceiveVO(PurchaseReceiveVO purchaseReceiveVO) {
        this.purchaseReceiveVO = purchaseReceiveVO;
    }

    public List<PurchaseReceiveDetailVO> getPurchaseReceiveDetailVOS() {
        return purchaseReceiveDetailVOS;
    }

    public void setPurchaseReceiveDetailVOS(List<PurchaseReceiveDetailVO> purchaseReceiveDetailVOS) {
        this.purchaseReceiveDetailVOS = purchaseReceiveDetailVOS;
    }

    @Override
    public String toString() {
        return "PurchaseReceiveRequestVO{" +
                "purchaseReceiveVO=" + purchaseReceiveVO +
                ", purchaseReceiveDetailVOS=" + purchaseReceiveDetailVOS +
                '}';
    }
}
