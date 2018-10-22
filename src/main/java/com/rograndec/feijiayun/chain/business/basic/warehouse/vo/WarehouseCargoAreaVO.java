package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by ST on 2017/8/31.
 */
public class WarehouseCargoAreaVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 仓库ID
     */
    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;

    @ApiModelProperty(value = "仓库Name")
    private String warehouseName;

    /**
     * 库区ID
     */
    @ApiModelProperty(value = "库区ID")
    private Long warehouseAreaId;

    @ApiModelProperty(value = "库区Name")
    private String warehouseAreaName;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;


    private List<WarehouseShelfVO> warehouseShelfVOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Long getWarehouseAreaId() {
        return warehouseAreaId;
    }

    public void setWarehouseAreaId(Long warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
    }

    public String getWarehouseAreaName() {
        return warehouseAreaName;
    }

    public void setWarehouseAreaName(String warehouseAreaName) {
        this.warehouseAreaName = warehouseAreaName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WarehouseShelfVO> getWarehouseShelfVOList() {
        return warehouseShelfVOList;
    }

    public void setWarehouseShelfVOList(List<WarehouseShelfVO> warehouseShelfVOList) {
        this.warehouseShelfVOList = warehouseShelfVOList;
    }
}