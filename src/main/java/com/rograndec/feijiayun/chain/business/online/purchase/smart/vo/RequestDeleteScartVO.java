package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartBusiness2GoodsVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class RequestDeleteScartVO implements Serializable{

    private static final long serialVersionUID = 44854208901275489L;

    @ApiModelProperty(value = "供应商ID")
    private Long mphSupplierId;

    @ApiModelProperty(value = "商品ID集合(如果供应商ID是0那么填的是saas的商品ID集合，否则填的是MPH商品的ID集合)")
    private List<Long> goodsIdList;

    public Long getMphSupplierId() {
        return mphSupplierId;
    }

    public void setMphSupplierId(Long mphSupplierId) {
        this.mphSupplierId = mphSupplierId;
    }

    public List<Long> getGoodsIdList() {
        return goodsIdList;
    }

    public void setGoodsIdList(List<Long> goodsIdList) {
        this.goodsIdList = goodsIdList;
    }

    @Override
    public String toString() {
        return "CentralizedCartBusiness2GoodsVO{" +
                "mphSupplierId=" + mphSupplierId +
                ", goodsIdList='" + goodsIdList + '\'' +
                '}';
    }


}
