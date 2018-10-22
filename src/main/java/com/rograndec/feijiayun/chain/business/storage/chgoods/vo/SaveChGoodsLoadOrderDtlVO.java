package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaveChGoodsLoadOrderDtlVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "商品ID")
    private Long goodsId;

    @ApiModelProperty(required = true, value = "批号ID")
    private Long lotId;

    @ApiModelProperty(required = true, value = "源货位ID")
    private Long srcShelfId;

    @ApiModelProperty(required = true, value = "目标货位ID")
    private Long targetShelfId;

    @ApiModelProperty(required = true, value = "数量")
    private BigDecimal quantity;

    @ApiModelProperty(required = true, value = "行号")
    private Integer lineNum;

    @ApiModelProperty(required = false, value = "备注")
    private String remark;

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

    public Long getSrcShelfId() {
        return srcShelfId;
    }

    public void setSrcShelfId(Long srcShelfId) {
        this.srcShelfId = srcShelfId;
    }

    public Long getTargetShelfId() {
        return targetShelfId;
    }

    public void setTargetShelfId(Long targetShelfId) {
        this.targetShelfId = targetShelfId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Override
    public String toString() {
        return "SaveChGoodsLoadOrderDtlVO[" +
                "goodsId=" + goodsId +
                ", lotId=" + lotId +
                ", srcShelfId=" + srcShelfId +
                ", targetShelfId=" + targetShelfId +
                ", quantity=" + quantity +
                ", lineNum=" + lineNum +
                ", remark='" + remark + '\'' +
                ']';
    }
}
