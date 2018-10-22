package com.rograndec.feijiayun.chain.business.member.set.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by dudy on 2017/9/23.
 */
public class MemberSimpleCardLevelVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
