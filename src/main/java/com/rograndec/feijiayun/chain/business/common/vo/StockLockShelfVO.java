package com.rograndec.feijiayun.chain.business.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaiwei on 2017/8/23.
 */
@ApiModel
public class StockLockShelfVO implements Serializable {
    @ApiModelProperty(value = "商品id", required = false)
    private Long goodsId;
    @ApiModelProperty(value = "批号", required = false)
    private String lotNum;
    @ApiModelProperty(value = "批号id集合", required = false)
    private List<Long> lotIds;
    @ApiModelProperty(value = "基础单据id", required = false)
    private Long baseOrderId;

    @ApiModelProperty(value = "基础单据code", required = false)
    private String code;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public List<Long> getLotIds() {
        return lotIds;
    }

    public void setLotIds(List<Long> lotIds) {
        this.lotIds = lotIds;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
