package com.rograndec.feijiayun.chain.business.basic.equipment.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class EquipmentSimpleVO implements Serializable{


    /**
     * 主键ID
     */
    @ApiModelProperty(required = true, value = "主键ID")
    private Long id;

    /**
     * 编码
     */
    @NotNull(message="编码不能为空!")
    @ApiModelProperty(required = true, value = "编码")
    private String code;

    /**
     * 名称
     */
    @NotNull(message="名称不能为空!")
    @ApiModelProperty(required = true, value = "名称")
    private String name;


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
}
