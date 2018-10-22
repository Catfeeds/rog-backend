package com.rograndec.feijiayun.chain.business.auth.menu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 新增和修改菜单bean
 */


@ApiModel
public class MenuFormVO {

    /**
     * 主键ID
     */
    @ApiModelProperty("菜单主键id,修改时候需要传递")
    private Long id;

    /**
     * 功能编码
     */
    @ApiModelProperty(value = "功能编码 ,可新增不可修改" )
    private String code;

    /**
     * 功能名称
     */
    @ApiModelProperty(value = "功能名称",required = true)
    @NotNull(message = "功能名称,不能为空")
    @Size(min = 1,message = "功能名称,不能为空")
    private String name;

    /**
     * URL
     */
    @ApiModelProperty(value = "URL",required = true)
    @Size(min = 1,message = "URL,不能为空")
    @NotNull(message = "URL,不能为空")
    private String url;


    /**
     * 类型（0-系统菜单；1-管理菜单）
     */
    @ApiModelProperty("类型（0-系统菜单；1-管理菜单）默认为0 ,可新增不可修改")
    private Integer type = 0;

    /**
     * 上级功能（模块）ID
     */
    @ApiModelProperty("上级功能（模块）ID ,可新增不可修改")
    private Long parentId;

    /**
     *用于总部（0-否；1-是）
     */
    private Integer forParent;

    /**
     *用于自营店（0-否；1-是）
     */
    private Integer forBranch;

    /**
     *用于加盟店（0-否；1-是）
     */
    private Integer forLeague;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getForParent() {
        return forParent;
    }

    public void setForParent(Integer forParent) {
        this.forParent = forParent;
    }

    public Integer getForBranch() {
        return forBranch;
    }

    public void setForBranch(Integer forBranch) {
        this.forBranch = forBranch;
    }

    public Integer getForLeague() {
        return forLeague;
    }

    public void setForLeague(Integer forLeague) {
        this.forLeague = forLeague;
    }
}