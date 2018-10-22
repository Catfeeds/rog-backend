package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ST on 2017/9/1.
 */
public class ResponseWarehouseCargoAreaVO extends RequestWarehouseCargoAreaVO{

    @ApiModelProperty(value = "仓库Name")
    private String warehouseName;
    @ApiModelProperty(value = "库区Name")
    private String warehouseAreaName;


    @ApiModelProperty(value = "是否可以删除0/否;1/是")
    private int isDelete;

    @ApiModelProperty(value = "是否可以修改为禁用的状态,0/否;1/是")
    private Integer isDisabled ;

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseAreaName() {
        return warehouseAreaName;
    }

    public void setWarehouseAreaName(String warehouseAreaName) {
        this.warehouseAreaName = warehouseAreaName;
    }

    public Integer getIsDisabled() {
        if(isDisabled == null){
            return 0;
        }
        return isDisabled;
    }

    public void setIsDisabled(Integer isDisabled) {
        this.isDisabled = isDisabled;
    }
}