package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dudy on 2017/9/28.
 */
public class StopSaleDtlRequestVO implements Serializable{

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 解停原因
     */
    @ApiModelProperty(value = "解停原因")
    private String stopReason;


    @ApiModelProperty(value = "货位明细")
    private List<StopSaleShelfRequestVO> stopSaleShelfRequestVOList;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public String getStopReason() {
        return stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    public List<StopSaleShelfRequestVO> getStopSaleShelfRequestVOList() {
        return stopSaleShelfRequestVOList;
    }

    public void setStopSaleShelfRequestVOList(List<StopSaleShelfRequestVO> stopSaleShelfRequestVOList) {
        this.stopSaleShelfRequestVOList = stopSaleShelfRequestVOList;
    }
}
