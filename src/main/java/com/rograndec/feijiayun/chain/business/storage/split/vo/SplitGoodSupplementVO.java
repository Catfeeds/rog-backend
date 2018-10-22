package com.rograndec.feijiayun.chain.business.storage.split.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/28 <br/>
 * 描述：拆零商品启用状态及商品编码
 */
public class SplitGoodSupplementVO implements Serializable {

    private static final long serialVersionUID = 7437190857175507226L;
    /**
     * 商品ID
     */
    @ApiModelProperty(value="商品ID")
    private Long goodsId;
    /**
     * 商品编码
     */
    @ApiModelProperty(value="商品编码")
    private String goodsCode;

    /**
     * 启用状态
     */
    @ApiModelProperty(value="启用状态")
    private int status;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SplitGoodSupplementVO{" +
                "goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", status=" + status +
                '}';
    }
}
