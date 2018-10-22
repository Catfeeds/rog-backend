package com.rograndec.feijiayun.chain.business.goods.info.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by ST on 2017/9/6.
 */
public class ResponseManufacturerVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "生产企业id")
    private Long id;

    /**
     * 企业（总部）ID
     */
    @ApiModelProperty(value = "生产企业关联的企业")
    private Long enterpriseId;

    /**
     * 生产厂商名称
     */
    @ApiModelProperty(value = "生产企业名称")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}