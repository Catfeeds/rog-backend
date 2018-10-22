package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.StartSaleShelf;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dudy on 2017/9/28.
 */
public class StartSaleDtlRequestVO  implements Serializable{

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 解停原因
     */
    @ApiModelProperty(value = "解停原因")
    private String startReason;


    @ApiModelProperty(value = "货位明细")
    private List<StartSaleShelfRequestVO> startSaleShelfRequestVOList;


    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public String getStartReason() {
        return startReason;
    }

    public void setStartReason(String startReason) {
        this.startReason = startReason;
    }

    public List<StartSaleShelfRequestVO> getStartSaleShelfRequestVOList() {
        return startSaleShelfRequestVOList;
    }

    public void setStartSaleShelfRequestVOList(List<StartSaleShelfRequestVO> startSaleShelfRequestVOList) {
        this.startSaleShelfRequestVOList = startSaleShelfRequestVOList;
    }

}
