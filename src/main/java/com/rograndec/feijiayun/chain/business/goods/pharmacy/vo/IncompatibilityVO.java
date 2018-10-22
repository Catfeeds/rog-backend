package com.rograndec.feijiayun.chain.business.goods.pharmacy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zeshi.sun on 2017/9/9.
 */

public class IncompatibilityVO implements Serializable {

    /**
     * ID
     */
    @ApiModelProperty(required = true, value = "ID")
    private Long id;

    /**
     * 配伍药品ID集合
     */
    @ApiModelProperty(required = true, value = "配伍药品ID集合")
    private String compatibilityGoodsIds;

    /**
     * 配伍药品名称集合
     */
    @ApiModelProperty(required = true, value = "配伍药品名称集合")
    private String compatibilityGoodsNames;

    /**
     * 配伍结果
     */
    @ApiModelProperty(required = true, value = "配伍结果")
    private String compatibilityResult;

    /**
     * 配伍药品名称集合
     */
    @ApiModelProperty(required = true, value = "配伍药品编码集合")
    private String compatibilityGoodsCodes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    @Override
    public String toString() {
        return "IncompatibilityVO[" +
                "id=" + id +
                ", compatibilityGoodsIds='" + compatibilityGoodsIds + '\'' +
                ", compatibilityGoodsNames='" + compatibilityGoodsNames + '\'' +
                ", compatibilityResult='" + compatibilityResult + '\'' +
                ", compatibilityGoodsCodes='" + compatibilityGoodsCodes + '\'' +
                ']';
    }
}
