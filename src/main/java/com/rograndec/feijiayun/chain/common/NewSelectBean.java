package com.rograndec.feijiayun.chain.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "NewSelectBean", description = "下拉框id-text")
public class NewSelectBean implements Serializable {
    /**
     * serialVersionUID : <功能描述>
     */
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "下拉框id")
    private  Long id;
    @ApiModelProperty(value = "下拉框text")
    private  String text;
    @ApiModelProperty(value = "下拉框text")
    private  String name;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
