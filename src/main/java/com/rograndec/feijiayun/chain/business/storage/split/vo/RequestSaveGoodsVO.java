package com.rograndec.feijiayun.chain.business.storage.split.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/30 <br/>
 * 描述：保存商品拆零请求传参-商品信息
 */
public class RequestSaveGoodsVO {

    @ApiModelProperty(value = "整盒商品ID", required = true)
    private Long goodsId;

    @ApiModelProperty(value = "整盒商品批号id", required = true)
    private Long lotId;

    @ApiModelProperty(value = "整盒商品货位ID", required = true)
    private Long shelfId;

    @ApiModelProperty(value = "拆零商品ID", required = true)
    private Long splitGoodsId;

    @ApiModelProperty(value = "拆零商品货位ID", required = true)
    private Long splitShelfId;

    @ApiModelProperty(value = "整盒商品数量", required = true)
    private BigDecimal quantity;

    @ApiModelProperty(value = "行号", required = true)
    private Integer lineNum;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public Long getSplitGoodsId() {
        return splitGoodsId;
    }

    public void setSplitGoodsId(Long splitGoodsId) {
        this.splitGoodsId = splitGoodsId;
    }

    public Long getSplitShelfId() {
        return splitShelfId;
    }

    public void setSplitShelfId(Long splitShelfId) {
        this.splitShelfId = splitShelfId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}
