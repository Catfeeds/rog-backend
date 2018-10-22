package com.rograndec.feijiayun.chain.business.storage.inventory.vo;

import com.rograndec.feijiayun.chain.common.vo.RequestBaseParamVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/9/29 19:08
 */

public class RequestGoodsStockVOPage extends RequestBaseParamVO {
    /**
     * 盘点方法（0-按货位；1-按商品）
     */
    @ApiModelProperty(value = "盘点方法（0-按货位；1-按商品）",required = true)
    private Integer invType;

    @ApiModelProperty(value = "搜索的关键字")
    private String param;

    @ApiModelProperty(value = "仓库id")
    private Long warehouseId;

    @ApiModelProperty(value = "库区id集合")
    private List<Long> warehouseAreaIds;

    @ApiModelProperty(value = "货区id集合")
    private List<Long> warehouseCargoAreaIds;

    @ApiModelProperty(value = "默认为0，全选 0-否；1-是；")
    private Integer selectAll;

    public Integer getInvType() {
        return invType;
    }

    public void setInvType(Integer invType) {
        this.invType = invType;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public List<Long> getWarehouseAreaIds() {
        return warehouseAreaIds;
    }

    public void setWarehouseAreaIds(List<Long> warehouseAreaIds) {
        this.warehouseAreaIds = warehouseAreaIds;
    }

    public List<Long> getWarehouseCargoAreaIds() {
        return warehouseCargoAreaIds;
    }

    public void setWarehouseCargoAreaIds(List<Long> warehouseCargoAreaIds) {
        this.warehouseCargoAreaIds = warehouseCargoAreaIds;
    }

    public Integer getSelectAll() {
        return selectAll;
    }

    public void setSelectAll(Integer selectAll) {
        this.selectAll = selectAll;
    }
}