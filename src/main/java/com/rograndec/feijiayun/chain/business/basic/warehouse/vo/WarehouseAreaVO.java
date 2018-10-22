package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ST on 2017/8/31.
 */
public class WarehouseAreaVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID(库区ID)")
    private Long id;

    /**
     * 仓库ID
     */
    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;

    /**
     * 仓库Name
     */
    @ApiModelProperty(value = "仓库Name")
    private String warehouseName;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称",required = true)
    private String name;

    private static final long serialVersionUID = 1L;


    private List<WarehouseCargoAreaVO> warehouseCargoAreaVOList;

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

    public List<WarehouseCargoAreaVO> getWarehouseCargoAreaVOList() {
        return warehouseCargoAreaVOList;
    }

    public void setWarehouseCargoAreaVOList(List<WarehouseCargoAreaVO> warehouseCargoAreaVOList) {
        this.warehouseCargoAreaVOList = warehouseCargoAreaVOList;
    }
}