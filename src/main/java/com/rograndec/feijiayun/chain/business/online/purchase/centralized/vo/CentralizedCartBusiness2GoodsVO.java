package com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class CentralizedCartBusiness2GoodsVO implements Serializable{

    private static final long serialVersionUID = 44854208901275489L;

    @ApiModelProperty(value = "供应商ID")
    private Long mphSupplierId;

    @ApiModelProperty(value = "商品ID集合")
    private List<String> goodsIdList;

    public Long getMphSupplierId() {
        return mphSupplierId;
    }

    public void setMphSupplierId(Long mphSupplierId) {
        this.mphSupplierId = mphSupplierId;
    }

    public List<String> getGoodsIdList() {
        return goodsIdList;
    }

    public void setGoodsIdList(List<String> goodsIdList) {
        this.goodsIdList = goodsIdList;
    }

    @Override
    public String toString() {
        return "CentralizedCartBusiness2GoodsVO{" +
                "mphSupplierId=" + mphSupplierId +
                ", goodsIdList='" + goodsIdList + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CentralizedCartBusiness2GoodsVO that = (CentralizedCartBusiness2GoodsVO) o;

        if (mphSupplierId != null ? !mphSupplierId.equals(that.mphSupplierId) : that.mphSupplierId != null)
            return false;
        return goodsIdList != null ? goodsIdList.equals(that.goodsIdList) : that.goodsIdList == null;
    }

    @Override
    public int hashCode() {
        int result = mphSupplierId != null ? mphSupplierId.hashCode() : 0;
        result = 31 * result + (goodsIdList != null ? goodsIdList.hashCode() : 0);
        return result;
    }
}
