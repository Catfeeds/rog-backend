package com.rograndec.feijiayun.chain.business.auth.menu.controller;

import com.rograndec.feijiayun.chain.business.auth.menu.service.MenuManagerService;
import com.rograndec.feijiayun.chain.business.auth.menu.vo.EnterpriseMenuFormVO;
import com.rograndec.feijiayun.chain.business.auth.menu.vo.MenuFormVO;
import com.rograndec.feijiayun.chain.business.auth.menu.vo.MenuPageVO;
import com.rograndec.feijiayun.chain.business.auth.menu.vo.RoleMenuFormVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.business.system.set.service.PermissionSettingsService;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 *
 */
@Api(value = "auth_menu",description = "菜单管理")
@RestController
@RequestMapping("auth/menu/")
@Validated
public class MenuMangerContoller {

    @Autowired
    private MenuManagerService menuManagerService;

    @Autowired
    private PermissionSettingsService permissionSettingsService;

    @ApiOperation(value = "新增和修改角色菜单", notes = "新增和修改角色菜单 | 开发者 翟伟 | 开发中")
	@RequestMapping(value = "/maintain/modify", method = RequestMethod.POST)
    public Result modify(
                HttpSession session,
                @ApiParam(value = "菜单信息实体", required = true)
                @RequestBody
                @Valid MenuFormVO menuFormVO) throws Exception {

        Result result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");

        menuManagerService.modify(userVO, menuFormVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return result;
    }


    @ApiOperation(value = "删除菜单", notes = "删除菜单 | 开发者 翟伟 | 开发中")
    @RequestMapping(value = "/maintain/remove/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单id"
                    , required = true, paramType="path")
    })
    public Result remove(
            HttpSession session,
            @PathVariable("id") Long id) throws Exception {

        Result result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");

        menuManagerService.remove(id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return result;
    }


    @ApiOperation(value = "菜单tree查询", notes = "根据登录用户id查询菜单tree | 开发者 翟伟 | 开发中")
    @RequestMapping(value = "/maintain/menuTree", method = RequestMethod.GET)
    public Result<MenuPageVO> query(
            HttpSession session) throws Exception {

        Result<MenuPageVO> result = new Result<>();

        MenuPageVO menuPageVO = menuManagerService.cascadeQueryAction();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,menuPageVO);

        return result;
    }

    @ApiOperation(value = "设置菜单适配的企业类型", notes = "设置菜单适配的企业类型 | 开发者 翟伟 | 开发中")
    @RequestMapping(value = "/maintain/enterprise/setMenus", method = RequestMethod.POST)
    public Result setMenus(
            HttpSession session,
            @ApiParam(value = "设置菜单适配的企业类型实体", required = true)
            @RequestBody
            @Valid List<EnterpriseMenuFormVO> enterpriseMenuFormVOs) throws Exception {

        Result result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");

        menuManagerService.setMenus2Enterprise(enterpriseMenuFormVOs);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return result;
    }

    @ApiOperation(value="根据获取系统角色树形结构", notes = "根据获取系统角色树形结构 | 开发者:翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/systemRoleTree", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<TreePOJO<SysRole>>> getTreeSystemRole(HttpServletRequest request) throws Exception {
        Result<List<TreePOJO<SysRole>>> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, permissionSettingsService.getRoleTree(loginUser));
        return result;
    }


    @ApiOperation(value = "设置角色的权限", notes = "设置角色的权限 | 开发者 翟伟 | 开发中")
    @RequestMapping(value = "/maintain/role/setMenus", method = RequestMethod.POST)
    public Result setMenus2Role(
            HttpSession session,
            @ApiParam(value = "菜单信息实体", required = true)
            @RequestBody
            @Valid RoleMenuFormVO roleMenuFormVO) throws Exception {

        Result result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");

        permissionSettingsService.setMenus2Role(roleMenuFormVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return result;
    }
}
