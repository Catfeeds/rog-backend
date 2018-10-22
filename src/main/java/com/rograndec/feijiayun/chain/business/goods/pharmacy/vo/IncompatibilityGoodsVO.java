package com.rograndec.feijiayun.chain.business.goods.pharmacy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "IncompatibilityGoodsVO", description = "药学-禁忌的商品和结果")
public class IncompatibilityGoodsVO implements Serializable {
    /**
     * 配伍药品ID
     */
    @ApiModelProperty(required = true, value = "药品ID")
    private String compatibilityGoodsId;

    public String getCompatibilityGoodsId() {
        return compatibilityGoodsId;
    }

    public void setCompatibilityGoodsId(String compatibilityGoodsId) {
        this.compatibilityGoodsId = compatibilityGoodsId;
    }

    public String getCompatibilityResult() {
        return compatibilityResult;
    }

    public void setCompatibilityResult(String compatibilityResult) {
        this.compatibilityResult = compatibilityResult;
    }

    /**

     * 配伍结果
     */
    @ApiModelProperty(required = true, value = "结果")
    private String compatibilityResult;

}
