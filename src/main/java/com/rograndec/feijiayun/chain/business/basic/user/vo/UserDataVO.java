package com.rograndec.feijiayun.chain.business.basic.user.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class UserDataVO implements Serializable {

    /**
     * 人员ID
     */
    @ApiModelProperty(value = "人员ID", required = false)
    private Long id;

    /**
     * 人员编码
     */
    @ApiModelProperty(value = "人员编码", required = false)
    private String code;

    /**
     * 人员名称
     */
    @ApiModelProperty(value = "人员名称", required = false)
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
        return "UserDataVO[" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ']';
    }
}
