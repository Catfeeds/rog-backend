package com.rograndec.feijiayun.chain.business.retail.shift.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zeshi.sun on 2017/9/22.
 */
public class SelectPosStandVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(required = true, value = "主键ID")
    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(required = true, value = "编码")
    private String code;

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

    @Override
    public String toString() {
        return "SelectPosStandVO[" +
                "id=" + id +
                ", code='" + code + '\'' +
                ']';
    }
}
