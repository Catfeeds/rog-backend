package com.rograndec.feijiayun.chain.business.basic.store.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class SaveVO implements Serializable{
    /**
     * 主键ID(级别/商圈ID)
     */
    @ApiModelProperty(value = "主键ID(级别/商圈ID)")
    private Long id;

    /**
     * 门店ID集合
     */
    @ApiModelProperty(value = "门店ID集合")
    private List<Long> shopIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getShopIds() {
        return shopIds;
    }

    public void setShopIds(List<Long> shopIds) {
        this.shopIds = shopIds;
    }

    @Override
    public String toString() {
        return "SaveVO{" +
                "id=" + id +
                ", shopIds=" + shopIds +
                '}';
    }
}
