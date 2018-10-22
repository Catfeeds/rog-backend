package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaveChGoodsClearOrderDtlOneVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "批号ID")
    private Long lotId;

    @ApiModelProperty(required = true, value = "货位ID")
    private Long shelfId;

    @ApiModelProperty(required = true, value = "货位名称")
    private String shelfName;

    @ApiModelProperty(required = true, value = "质量状况")
    private String shelfStatusDesc;

    @ApiModelProperty(required = true, value = "数量")
    private BigDecimal quantity;

    @ApiModelProperty(required = true, value = "行号")
    private Integer lineNum;

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

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
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

    @Override
    public String toString() {
        return "SaveChGoodsClearOrderDtlOneVO[" +
                "lotId=" + lotId +
                ", shelfId=" + shelfId +
                ", shelfName='" + shelfName + '\'' +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                ", quantity=" + quantity +
                ", lineNum=" + lineNum +
                ']';
    }
}
