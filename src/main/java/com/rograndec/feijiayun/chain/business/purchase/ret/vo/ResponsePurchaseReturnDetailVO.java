package com.rograndec.feijiayun.chain.business.purchase.ret.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/9/15 15:44
 */

public class ResponsePurchaseReturnDetailVO extends ResponsePurchaseReturnVO {

    @ApiModelProperty(value = "订单明细")
    private List<PurchaseReturnDetailVO> purchaseReturnDetailVOList;

    @ApiModelProperty(value = "配送和结算")
    private PurchaseReturnOtherVO purchaseReturnOtherVO;

    public List<PurchaseReturnDetailVO> getPurchaseReturnDetailVOList() {
        return purchaseReturnDetailVOList;
    }

    public void setPurchaseReturnDetailVOList(List<PurchaseReturnDetailVO> purchaseReturnDetailVOList) {
        this.purchaseReturnDetailVOList = purchaseReturnDetailVOList;
    }

    public PurchaseReturnOtherVO getPurchaseReturnOtherVO() {
        return purchaseReturnOtherVO;
    }

    public void setPurchaseReturnOtherVO(PurchaseReturnOtherVO purchaseReturnOtherVO) {
        this.purchaseReturnOtherVO = purchaseReturnOtherVO;
    }
}