package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Asze on 2017/9/20.
 */
public class ConclusionVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", required = true)
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ConclusionVO[" +
                "id=" + id +
                ", description='" + description + '\'' +
                ']';
    }
}
