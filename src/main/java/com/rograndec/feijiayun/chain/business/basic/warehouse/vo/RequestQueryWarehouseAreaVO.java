package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ST on 2017/9/5.
 */
public class RequestQueryWarehouseAreaVO extends RequestWarehouseCommonVO {
    @ApiModelProperty(value = "仓库id，不传这个值，表示查询所有的库区")
    private Long warehouseId;

    @ApiModelProperty(value = "类型(0/倒序；1/正序；默认0)")
    private Integer typeOrder;

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getTypeOrder() {
        if(typeOrder == null){
            return 1;
        }
        return typeOrder;
    }

    public void setTypeOrder(Integer typeOrder) {
        this.typeOrder = typeOrder;
    }
}