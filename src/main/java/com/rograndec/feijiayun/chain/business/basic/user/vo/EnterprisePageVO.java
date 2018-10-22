package com.rograndec.feijiayun.chain.business.basic.user.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class EnterprisePageVO implements Serializable {

    /**
     * 组织机构ID
     */
    @ApiModelProperty(value = "组织机构ID", required = false)
    private Long id;

    /**
     * 组织机构编码
     */
    @ApiModelProperty(value = "组织机构编码", required = false)
    private String code;

    /**
     * 组织机构名称
     */
    @ApiModelProperty(value = "组织机构名称", required = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "EnterprisePageVO[" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ']';
    }
}
