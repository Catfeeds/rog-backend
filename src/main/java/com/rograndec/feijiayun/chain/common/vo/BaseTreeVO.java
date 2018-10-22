package com.rograndec.feijiayun.chain.common.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class BaseTreeVO {

    @ApiModelProperty("菜单id")
    private Long id;
    /**
     * 是否为父节点（0-否；1-是
     */
    @ApiModelProperty("是否为父节点（0-否；1-是")
    private int isParent;

    @ApiModelProperty("菜单父id")
    private Long parentId;

    @ApiModelProperty("菜单编码")
    private String code;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("菜单描述")
    private String remark;

    @ApiModelProperty("菜单图标")
    private String icon;

    /**
     * 系统类型
     */
    @ApiModelProperty("系统类型")
    private Integer sysType = 1;

    /**
     * 菜单用到的url
     */
    @ApiModelProperty("菜单用到的url")
    private String url;

    /**
     * 菜单类型
     */
    @ApiModelProperty("菜单类型")
    private Integer type;

    /**
     *用于总部（0-否；1-是）
     */
    @ApiModelProperty("用于总部（0-否；1-是）")
    private int forParent;

    /**
     *用于自营店（0-否；1-是）
     */
    @ApiModelProperty("用于自营店（0-否；1-是）")
    private int forBranch;

    /**
     *用于加盟店（0-否；1-是）
     */
    @ApiModelProperty("用于加盟店（0-否；1-是）")
    private int forLeague;

    /**
     * tree层级
     */
    @ApiModelProperty("tree层级")
    private int level = 0
            ;

    private List<BaseTreeVO> baseTreeVOS = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
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

    public int getForParent() {
        return forParent;
    }

    public void setForParent(int forParent) {
        this.forParent = forParent;
    }

    public int getForBranch() {
        return forBranch;
    }

    public void setForBranch(int forBranch) {
        this.forBranch = forBranch;
    }

    public int getForLeague() {
        return forLeague;
    }

    public void setForLeague(int forLeague) {
        this.forLeague = forLeague;
    }

    public List<BaseTreeVO> getBaseTreeVOS() {
        return baseTreeVOS;
    }

    public void setBaseTreeVOS(List<BaseTreeVO> baseTreeVOS) {
        this.baseTreeVOS = baseTreeVOS;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIsParent() {
        return isParent;
    }

    public void setIsParent(int isParent) {
        this.isParent = isParent;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
