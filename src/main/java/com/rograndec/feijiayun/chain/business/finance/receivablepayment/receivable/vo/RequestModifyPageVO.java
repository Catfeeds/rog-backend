package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class RequestModifyPageVO implements Serializable{

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码",required = true)
    private Integer pageNo;

    /**
     * 显示条数
     */
    @ApiModelProperty(value = "显示条数",required = true)
    private Integer pageSize;

    /**
     * 收款单/付款单主键ID
     */
    @ApiModelProperty(value = "收款单/付款单主键ID",required = true)
    private Long id;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
