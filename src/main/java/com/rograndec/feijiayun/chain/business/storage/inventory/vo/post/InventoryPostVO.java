package com.rograndec.feijiayun.chain.business.storage.inventory.vo.post;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/10/7 13:31
 */

public class InventoryPostVO {


    @ApiModelProperty(value = "所有损益金额合计")
    private BigDecimal diffAmountTotalAll;


    @ApiModelProperty(value = "所有不含税损益金额合计")
    private BigDecimal diffNotaxAmountTotalAll;


    @ApiModelProperty(value = "所有损益税额合计")
    private BigDecimal diffTaxAmountTotalAll;

    private List<InventoryForPostVO> inventoryForPostVOList;


    public BigDecimal getDiffAmountTotalAll() {
        return diffAmountTotalAll;
    }

    public void setDiffAmountTotalAll(BigDecimal diffAmountTotalAll) {
        this.diffAmountTotalAll = diffAmountTotalAll;
    }

    public BigDecimal getDiffNotaxAmountTotalAll() {
        return diffNotaxAmountTotalAll;
    }

    public void setDiffNotaxAmountTotalAll(BigDecimal diffNotaxAmountTotalAll) {
        this.diffNotaxAmountTotalAll = diffNotaxAmountTotalAll;
    }

    public BigDecimal getDiffTaxAmountTotalAll() {
        return diffTaxAmountTotalAll;
    }

    public void setDiffTaxAmountTotalAll(BigDecimal diffTaxAmountTotalAll) {
        this.diffTaxAmountTotalAll = diffTaxAmountTotalAll;
    }

    public List<InventoryForPostVO> getInventoryForPostVOList() {
        return inventoryForPostVOList;
    }

    public void setInventoryForPostVOList(List<InventoryForPostVO> inventoryForPostVOList) {
        this.inventoryForPostVOList = inventoryForPostVOList;
    }
}