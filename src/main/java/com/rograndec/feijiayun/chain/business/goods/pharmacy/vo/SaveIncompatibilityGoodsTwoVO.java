package com.rograndec.feijiayun.chain.business.goods.pharmacy.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SaveIncompatibilityGoodsTwoVO implements Serializable {

    /**
     * 配伍药品子表ID
     */
    @ApiModelProperty(required = true, value = "配伍药品子表ID")
    private Long id;

    /**
     * 配伍禁忌药子表ID
     */
    @ApiModelProperty(required = true, value = "配伍禁忌药子表ID")
    private Long oneId;

    /**
     * 配伍禁忌ID
     */
    @ApiModelProperty(required = true, value = "配伍禁忌ID")
    private Long incompatibilityId;

    /**
     * 配伍药品ID集合
     */
    @ApiModelProperty(required = true, value = "配伍药品ID集合")
    private String compatibilityGoodsIds;

    /**
     * 配伍药品名称集合（形如：编码A-药品A,编码B-药品B…）
     */
    @ApiModelProperty(required = true, value = "配伍药品名称集合（形如：编码A-药品A,编码B-药品B…）")
    private String compatibilityGoodsNames;

    /**
     * 配伍结果
     */
    @ApiModelProperty(required = true, value = "配伍结果")
    private String compatibilityResult;

    /**
     * 配伍药品编码集合
     */
    @ApiModelProperty(required = true, value = "配伍药品编码集合")
    private String compatibilityGoodsCodes;

    public Long getOneId() {
        return oneId;
    }

    public void setOneId(Long oneId) {
        this.oneId = oneId;
    }

    public Long getIncompatibilityId() {
        return incompatibilityId;
    }

    public void setIncompatibilityId(Long incompatibilityId) {
        this.incompatibilityId = incompatibilityId;
    }

    public String getCompatibilityGoodsIds() {
        return compatibilityGoodsIds;
    }

    public void setCompatibilityGoodsIds(String compatibilityGoodsIds) {
        this.compatibilityGoodsIds = compatibilityGoodsIds;
    }

    public String getCompatibilityGoodsNames() {
        return compatibilityGoodsNames;
    }

    public void setCompatibilityGoodsNames(String compatibilityGoodsNames) {
        this.compatibilityGoodsNames = compatibilityGoodsNames;
    }

    public String getCompatibilityResult() {
        return compatibilityResult;
    }

    public void setCompatibilityResult(String compatibilityResult) {
        this.compatibilityResult = compatibilityResult;
    }

    public String getCompatibilityGoodsCodes() {
        return compatibilityGoodsCodes;
    }

    public void setCompatibilityGoodsCodes(String compatibilityGoodsCodes) {
        this.compatibilityGoodsCodes = compatibilityGoodsCodes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "compatibilityGoodsList[" +
                "oneId=" + oneId +
                ", id=" + id +
                ", incompatibilityId=" + incompatibilityId +
                ", compatibilityGoodsIds='" + compatibilityGoodsIds + '\'' +
                ", compatibilityGoodsNames='" + compatibilityGoodsNames + '\'' +
                ", compatibilityResult='" + compatibilityResult + '\'' +
                ", compatibilityGoodsCodes='" + compatibilityGoodsCodes + '\'' +
                ']';
    }
}
