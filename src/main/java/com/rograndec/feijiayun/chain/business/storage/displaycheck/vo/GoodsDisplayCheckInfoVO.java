package com.rograndec.feijiayun.chain.business.storage.displaycheck.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class GoodsDisplayCheckInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 陈列检查总单
     */
    @ApiModelProperty("陈列检查总单")
    private GoodsDisplayCheckVO goodsDisplayCheckVO;
    /**
     * 陈列检查细单集合
     */
    @ApiModelProperty("陈列检查细单集合")
    private List<GoodsDisplayCheckDetailVO> displayCheckDetailVOList;

    public GoodsDisplayCheckVO getGoodsDisplayCheckVO() {
        return goodsDisplayCheckVO;
    }

    public void setGoodsDisplayCheckVO(GoodsDisplayCheckVO goodsDisplayCheckVO) {
        this.goodsDisplayCheckVO = goodsDisplayCheckVO;
    }

    public List<GoodsDisplayCheckDetailVO> getDisplayCheckDetailVOList() {
        return displayCheckDetailVOList;
    }

    public void setDisplayCheckDetailVOList(List<GoodsDisplayCheckDetailVO> displayCheckDetailVOList) {
        this.displayCheckDetailVOList = displayCheckDetailVOList;
    }

    @Override
    public String toString() {
        return "GoodsDisplayCheckInfoVO{" +
                "goodsDisplayCheckVO=" + goodsDisplayCheckVO +
                ", displayCheckDetailVOList=" + displayCheckDetailVOList +
                '}';
    }
}
