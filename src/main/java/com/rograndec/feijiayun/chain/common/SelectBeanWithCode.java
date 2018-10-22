package com.rograndec.feijiayun.chain.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "SelectBeanWithCode", description = "下拉框id-code-name")
public class SelectBeanWithCode implements Serializable{
    /**
     * serialVersionUID : <功能描述>
     */
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id")
    private  Long id;

    @ApiModelProperty(value = "编码")
    private  String code;

    @ApiModelProperty(value = "名称")
    private  String name;

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
