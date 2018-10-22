package com.rograndec.feijiayun.chain.business.goods.pharmacy.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class SaveIncompatibilityGoodsOneVO implements Serializable {

    /**
     * 配伍禁忌药子表ID
     */
    @ApiModelProperty(required = true, value = "配伍禁忌药子表ID")
    private Long id;

    /**
     * 配伍禁忌ID
     */
    @ApiModelProperty(required = true, value = "配伍禁忌ID")
    private Long incompatibilityId;

    /**
     * 药品ID集合
     */
    @ApiModelProperty(required = true, value = "药品ID集合")
    private String goodsIds;

    /**
     * 药品名称集合（形如：编码A-药品A,编码B-药品B…）
     */
    @ApiModelProperty(required = true, value = "药品名称集合（形如：编码A-药品A,编码B-药品B…）")
    private String goodsNames;

    /**
     * 药品编码集合
     */
    @ApiModelProperty(required = true, value = "药品编码集合")
    private String goodsCodes;

    List<SaveIncompatibilityGoodsTwoVO> compatibilityGoodsList;

    public Long getIncompatibilityId() {
        return incompatibilityId;
    }

    public void setIncompatibilityId(Long incompatibilityId) {
        this.incompatibilityId = incompatibilityId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SaveIncompatibilityGoodsTwoVO> getCompatibilityGoodsList() {
        return compatibilityGoodsList;
    }

    public void setCompatibilityGoodsList(List<SaveIncompatibilityGoodsTwoVO> compatibilityGoodsList) {
        this.compatibilityGoodsList = compatibilityGoodsList;
    }

    @Override
    public String toString() {
        return "goodsList[" +
                "incompatibilityId=" + incompatibilityId +
                ", id='" + id + '\'' +
                ", goodsIds='" + goodsIds + '\'' +
                ", goodsNames='" + goodsNames + '\'' +
                ", goodsCodes='" + goodsCodes + '\'' +
                ", compatibilityGoodsList=" + compatibilityGoodsList +
                ']';
    }
}
