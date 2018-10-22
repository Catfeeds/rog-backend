package com.rograndec.feijiayun.chain.business.goods.pharmacy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zeshi.sun on 2017/9/9.
 */
public class PharmacySetForPrescVO implements Serializable {

    /**
     * ID
     */
    @ApiModelProperty(required = true, value = "ID")
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


}
