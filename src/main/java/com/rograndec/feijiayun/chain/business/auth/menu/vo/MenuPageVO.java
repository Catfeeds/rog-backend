package com.rograndec.feijiayun.chain.business.auth.menu.vo;

import com.rograndec.feijiayun.chain.business.system.set.entity.Tree;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


@ApiModel
public class MenuPageVO {

    @ApiModelProperty(value = "系统菜单" )
    private List<Tree> sysMenus;

    @ApiModelProperty(value = "管理菜单" )
    private List<Tree> managerMenus;

    public List<Tree> getSysMenus() {
        return sysMenus;
    }

    public void setSysMenus(List<Tree> sysMenus) {
        this.sysMenus = sysMenus;
    }

    public List<Tree> getManagerMenus() {
        return managerMenus;
    }

    public void setManagerMenus(List<Tree> managerMenus) {
        this.managerMenus = managerMenus;
    }

    public MenuPageVO() {
    }

    public MenuPageVO(List<Tree> sysMenus, List<Tree> managerMenus) {
        this.sysMenus = sysMenus;
        this.managerMenus = managerMenus;
    }
}
