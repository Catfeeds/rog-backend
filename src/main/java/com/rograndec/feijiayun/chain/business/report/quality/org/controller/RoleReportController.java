package com.rograndec.feijiayun.chain.business.report.quality.org.controller;

import com.rograndec.feijiayun.chain.business.system.set.entity.SysAction;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.business.system.set.service.PermissionSettingsService;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_org_role",description = "报表-质量管理-组织机构与质量管理职责-角色")
@RestController
@RequestMapping("report/quality/org/role")
@Validated
public class RoleReportController {

    @Autowired
    private PermissionSettingsService permissionSettingsService;


    @ApiOperation(value="根据获取系统所有的功能树形结构", notes = "根据当前用户获得所有功能 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getTreeSystemActions", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<TreePOJO<SysAction>>> getTreeSystemActions(HttpServletRequest request,
                                                                  @ApiParam(value = "角色ID,没有就传-1", required = true) @RequestParam Long roleId,
                                                                  @ApiParam(value = "是否选中", required = true) @RequestParam Boolean checked) throws Exception {
        Result<List<TreePOJO<SysAction>>> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, permissionSettingsService.getTree(loginUser,roleId,checked));
        return result;
    }

    @ApiOperation(value="获取系统角色树形结构", notes = "获取系统角色树形结构 | 开发者:翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/systemRoleTree", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<TreePOJO<SysRole>>> getTreeSystemRole(HttpServletRequest request) throws Exception {
        Result<List<TreePOJO<SysRole>>> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Long enterpriseId = loginUser.getEnterpriseId();

        //加盟店只检索系统默认和本企业的信息
        if(loginUser.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
            enterpriseId = loginUser.getParentId();
        }

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, permissionSettingsService.getRoleTree4Report(enterpriseId));
        return result;
    }


    @ApiOperation(value="导出 | 开发者 翟伟 | 已完成", notes = "角色组织机构信息导出  | 开发者 翟伟 | 已完成"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    @ResponseBody
    public void exportExcel(HttpServletResponse response, HttpServletRequest request
    ) throws Exception {

        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("user");

        String file = "角色导出";
        String suffix = ".xlsx";
        String fileName = file+suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));

        //输出Excel文件
        OutputStream output = response.getOutputStream();
        Long enterpriseId = user.getEnterpriseId();
        //加盟店只检索系统默认和本企业的信息
        if(user.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
            enterpriseId = user.getParentId();
        }

        permissionSettingsService.exportRoleExcel(output,user,enterpriseId);

        output.close();
        output.flush();
    }



}
