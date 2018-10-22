package com.rograndec.feijiayun.chain.business.goods.pharmacy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zeshi.sun on 2017/9/9.
 */

public class IncompatibilityVO2 implements Serializable {

    /**
     * ID
     */
    @ApiModelProperty(required = true, value = "ID")
    private Long id;

    /**
     * 药品ID集合
     */
    @ApiModelProperty(required = true, value = "药品ID集合")
    private String goodsIds;

    /**
     * 药品名称集合
     */
    @ApiModelProperty(required = true, value = "药品名称集合")
    private String goodsNames;

    /**
     * 药品编码集合
     */
    @ApiModelProperty(required = true, value = "药品编码集合")
    private String goodsCodes;


    private List<IncompatibilityVO> compatibilityGoodsList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    public String getGoodsNames() {
        return goodsNames;
    }

    public void setGoodsNames(String goodsNames) {
        this.goodsNames = goodsNames;
    }

    public String getGoodsCodes() {
        return goodsCodes;
    }

    public void setGoodsCodes(String goodsCodes) {
        this.goodsCodes = goodsCodes;
    }

    public List<IncompatibilityVO> getCompatibilityGoodsList() {
        return compatibilityGoodsList;
    }

    public void setCompatibilityGoodsList(List<IncompatibilityVO> compatibilityGoodsList) {
        this.compatibilityGoodsList = compatibilityGoodsList;
    }

    @Override
    public String toString() {
        return "IncompatibilityVO2[" +
                "id=" + id +
                ", goodsIds='" + goodsIds + '\'' +
                ", goodsNames='" + goodsNames + '\'' +
                ", goodsCodes='" + goodsCodes + '\'' +
                ", compatibilityGoodsList=" + compatibilityGoodsList +
                ']';
    }
}
