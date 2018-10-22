package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class SupplierGroupIncludeVO implements Serializable {
    @ApiModelProperty(value = "分组ID")
    private Long groupId;

    private List<SupplierGroupConnectVO> list;

    public Long getGroupId() {
        return groupId;
    }

    public List<SupplierGroupConnectVO> getList() {
        return list;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public void setList(List<SupplierGroupConnectVO> list) {
        this.list = list;
    }

}