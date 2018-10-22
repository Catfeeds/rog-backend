package com.rograndec.feijiayun.chain.business.auth.menu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 新增和修改菜单bean
 */


@ApiModel
public class EnterpriseMenuFormVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "菜单主键id" ,required = true)
    @NotNull(message = "菜单主键id,不能为空")
    private Long id;

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


    public static List<Long> getMenuIds(List<EnterpriseMenuFormVO> enterpriseMenuFormVOS){

        List<Long> menuIds = enterpriseMenuFormVOS.stream().map(enterpriseMenuFormVO -> enterpriseMenuFormVO.getId()).collect(Collectors.toList());

        return menuIds;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}