package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by ST on 2017/8/31.
 */
public class WarehouseCargoAreaSimpleVO {

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

    @ApiModelProperty(value = "货位id")
    private List<Long> shelfIdList;

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

    public List<Long> getShelfIdList() {
        return shelfIdList;
    }

    public void setShelfIdList(List<Long> shelfIdList) {
        this.shelfIdList = shelfIdList;
    }
}