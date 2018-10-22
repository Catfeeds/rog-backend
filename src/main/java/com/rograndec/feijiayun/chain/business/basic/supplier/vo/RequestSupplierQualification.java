package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class RequestSupplierQualification implements Serializable{

    /**
     * 新增时不需要传入，修改时需要传入当前供货单位ID
     */
    @ApiModelProperty(value = "新增时不需要传入，修改时需要传入当前供货单位ID")
    private Long id;

    @ApiModelProperty(value = "经营品种Id集合")
    private List<Long> idList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }
}
