package com.rograndec.feijiayun.chain.business.retail.shift.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zeshi.sun on 2017/9/22.
 */
public class SelectPosTeamVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(required = true, value = "主键ID")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(required = true, value = "名称")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SelectPosTeamVO[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ']';
    }
}
