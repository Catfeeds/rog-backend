package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class GoodsShelfStatusDescVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "货位ID")
    private Long id;

    @ApiModelProperty(required = true, value = "货位质量状态")
    private String shelfStatusDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    @Override
    public String toString() {
        return "GoodsShelfStatusDescVO[" +
                "id=" + id +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                ']';
    }
}
