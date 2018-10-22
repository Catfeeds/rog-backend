package com.rograndec.feijiayun.chain.business.auth.menu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@ApiModel
public class RoleMenuFormVO {

    @ApiModelProperty(value = "角色id",required = true)
    @NotNull(message = "角色id,不能为空")
    private Long id;

    @ApiModelProperty(value = "菜单id集合",required = true)
    @NotNull(message = "菜单id集合,不能为空")
    @Size(min = 1,message = "菜单id集合,不能为空")
    private List<Long> menuIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }
}