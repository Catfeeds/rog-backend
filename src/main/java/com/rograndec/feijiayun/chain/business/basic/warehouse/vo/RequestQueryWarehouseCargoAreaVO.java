package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ST on 2017/9/5.
 */
public class RequestQueryWarehouseCargoAreaVO extends RequestWarehouseCommonVO {

    @ApiModelProperty(value = "库区id，不传这个值表示查询所有的货区")
    private Long warehouseAreaId;

    @ApiModelProperty(value = "编码(0/倒序；1/正序；")
    private Integer areaNameOrder;



    public Long getWarehouseAreaId() {
        return warehouseAreaId;
    }

    public void setWarehouseAreaId(Long warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
    }

    public Integer getAreaNameOrder() {
        if(areaNameOrder == null){
            return 1;
        }
        return areaNameOrder;
    }

    public void setAreaNameOrder(Integer areaNameOrder) {
        this.areaNameOrder = areaNameOrder;
    }


}