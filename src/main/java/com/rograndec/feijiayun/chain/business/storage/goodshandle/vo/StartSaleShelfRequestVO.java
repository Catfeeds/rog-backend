package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by dudy on 2017/9/28.
 */
public class StartSaleShelfRequestVO  implements Serializable{

    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID(必传)")
    private Long lotId;

    /**
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID(必传)")
    private Long shelfId;


    /**
     * 数量
     */
    @ApiModelProperty(value = "数量(必传)")
    private BigDecimal quantity;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号(必传)")
    private Integer lineNum;


    @ApiModelProperty(value = "锁定单ID")
    private Long lockId;

    public Long getLockId() {
        return lockId;
    }

    public void setLockId(Long lockId) {
        this.lockId = lockId;
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
