package com.rograndec.feijiayun.chain.business.finance.commission.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaleCommissionRule implements Serializable {

    private Long Id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 方法（0-按销售数量提成；1-按销售金额提成；2-按利润额提成；3-按利润率提成）
     */
    private Integer method;

    /**
     * 方式（0-按比例；1-按金额）
     */
    private Integer mode;

    /**
     * 0-全额提成；1-盈余提成
     */
    private Integer range;

    /**
     * 状态（0-禁用；1-启用）
     */
    private Integer status;

    private List<SaleCommissionRange> saleCommissionRanges = new ArrayList<>();


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SaleCommissionRange> getSaleCommissionRanges() {
        return saleCommissionRanges;
    }

    public void setSaleCommissionRanges(List<SaleCommissionRange> saleCommissionRanges) {
        this.saleCommissionRanges = saleCommissionRanges;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}