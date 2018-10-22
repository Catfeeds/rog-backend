package com.rograndec.feijiayun.chain.business.purchase.receive.vo;

import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderDetailVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by madong on 2017/9/18.
 */
public class PurchaseOrderRequestVO implements Serializable {
    PurchaseReceiveOrderVO purchaseReceiveOrderVO;
    List<PurchaseOrderDetailVO> purchaseOrderDetailVOS;

    public PurchaseReceiveOrderVO getPurchaseReceiveOrderVO() {
        return purchaseReceiveOrderVO;
    }

    public void setPurchaseReceiveOrderVO(PurchaseReceiveOrderVO purchaseReceiveOrderVO) {
        this.purchaseReceiveOrderVO = purchaseReceiveOrderVO;
    }

    public List<PurchaseOrderDetailVO> getPurchaseOrderDetailVOS() {
        return purchaseOrderDetailVOS;
    }

    public void setPurchaseOrderDetailVOS(List<PurchaseOrderDetailVO> purchaseOrderDetailVOS) {
        this.purchaseOrderDetailVOS = purchaseOrderDetailVOS;
    }

    @Override
    public String toString() {
        return "PurchaseOrderRequestVO{" +
                "purchaseReceiveOrderVO=" + purchaseReceiveOrderVO +
                ", purchaseOrderDetailVOS=" + purchaseOrderDetailVOS +
                '}';
    }
}
