package com.rograndec.feijiayun.chain.business.system.opening.vo;

import com.rograndec.feijiayun.chain.common.Page;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：
 * Created by ST on 2017/12/4 14:02
 */

public class Page2OpeningInventory<T> extends Page<T> {
    public Page2OpeningInventory(Integer pageNo, Integer pageSize) {
        super(pageNo, pageSize);
    }

    @ApiModelProperty("剩余条数")
    private Integer remainCount;

    public Integer getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(Integer remainCount) {
        this.remainCount = remainCount;
    }
}