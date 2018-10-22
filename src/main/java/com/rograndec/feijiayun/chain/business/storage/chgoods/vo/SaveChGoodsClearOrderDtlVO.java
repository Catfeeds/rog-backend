package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class SaveChGoodsClearOrderDtlVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "商品ID")
    private Long goodsId;

    @ApiModelProperty(required = true, value = "数量")
    private BigDecimal quantity;

    @ApiModelProperty(required = true, value = "行号")
    private Integer lineNum;

    @ApiModelProperty(required = false, value = "备注")
    private String remark;

    List<SaveChGoodsClearOrderDtlOneVO> SaveChGoodsClearOrderDtlOne;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SaveChGoodsClearOrderDtlOneVO> getSaveChGoodsClearOrderDtlOne() {
        return SaveChGoodsClearOrderDtlOne;
    }

    public void setSaveChGoodsClearOrderDtlOne(List<SaveChGoodsClearOrderDtlOneVO> saveChGoodsClearOrderDtlOne) {
        SaveChGoodsClearOrderDtlOne = saveChGoodsClearOrderDtlOne;
    }

    @Override
    public String toString() {
        return "SaveChGoodsClearOrderDtlVO[" +
                "goodsId=" + goodsId +
                ", quantity=" + quantity +
                ", lineNum=" + lineNum +
                ", remark='" + remark + '\'' +
                ", SaveChGoodsClearOrderDtlOneVO=" + SaveChGoodsClearOrderDtlOne +
                ']';
    }
}
