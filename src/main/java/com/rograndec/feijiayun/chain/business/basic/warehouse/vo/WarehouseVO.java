package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by ST on 2017/8/31.
 */
public class WarehouseVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


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

    private List<WarehouseAreaVO> warehouseAreaVOList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<WarehouseAreaVO> getWarehouseAreaVOList() {
        return warehouseAreaVOList;
    }

    public void setWarehouseAreaVOList(List<WarehouseAreaVO> warehouseAreaVOList) {
        this.warehouseAreaVOList = warehouseAreaVOList;
    }
}