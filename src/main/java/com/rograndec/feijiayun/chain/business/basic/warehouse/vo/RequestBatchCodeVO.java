package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;


/**
 * 查询批量生成code
 * Created by ST on 2017/8/30.
 */
public class RequestBatchCodeVO {

    @ApiModelProperty(value = "仓库id",required = true)
    private Long warehouseId;

    @ApiModelProperty(value = "生产数量",required = true)
    private Integer count;

    @ApiModelProperty(value = "编码前缀",required = true)
    private String prefix;


    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }


}