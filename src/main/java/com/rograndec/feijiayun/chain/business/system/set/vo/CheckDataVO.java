package com.rograndec.feijiayun.chain.business.system.set.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class CheckDataVO implements Serializable {

    @ApiModelProperty(value="ID",required=true)
    private Long id;

    @ApiModelProperty(value="类型",required=true)
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CheckDataVO[" +
                "id=" + id +
                ", type='" + type + '\'' +
                ']';
    }
}
