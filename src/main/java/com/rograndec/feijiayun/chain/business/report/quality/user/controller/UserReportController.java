package com.rograndec.feijiayun.chain.business.report.quality.user.controller;

import com.rograndec.feijiayun.chain.business.basic.user.service.EducationMajorService;
import com.rograndec.feijiayun.chain.business.basic.user.vo.EducationMajorReturnVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.service.UserReportService;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserReportExcelVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserReportPageVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.*;
import com.rograndec.feijiayun.chain.business.system.set.service.OrganizationService;
import com.rograndec.feijiayun.chain.business.system.set.service.PermissionSettingsService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author leisu
 *
 */
@Api(value = "report_quality_user_user",description = "报表-质量管理-人员与培训-员工信息")
@RestController
@RequestMapping("report/quality/user/user")
@Validated
public class UserReportController {

    private static final Log logger = LogFactory.getLog(UserReportController.class);

    @Autowired
    private UserReportService userReportService;

    @Autowired
    private EducationMajorService educationMajorService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private PermissionSettingsService permissionSettingsService;

    @ApiOperation(value = "按条件搜索员工page信息", notes = "按条件搜索员工page信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getUserReportPage", method = RequestMethod.GET)
    public Result<Page<List<UserReportPageVO>>> getInstorageHasBeenPage(HttpServletRequest request,
                                                                        @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                        @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                        @ApiParam(value = "编码/检索码/名称", required = false) @RequestParam(required = false) String param,
                                                                        @ApiParam(value = "组织机构类型", required = false) @RequestParam(required = false) Integer chainType,
                                                                        @ApiParam(value = "组织机构编码", required = false) @RequestParam(required = false) String enterpriseCode,
                                                                        @ApiParam(value = "组织机构名称", required = false) @RequestParam(required = false) String enterpriseName,
                                                                        @ApiParam(value = "部门", required = false) @RequestParam(required = false) String deptIds,
                                                                        @ApiParam(value = "岗位", required = false) @RequestParam(required = false) String positionIds,
                                                                        @ApiParam(value = "角色", required = false) @RequestParam(required = false) String roleIds,
                                                                        @ApiParam(value = "学历", required = false) @RequestParam(required = false) Long educationId,
                                                                        @ApiParam(value = "专业", required = false) @RequestParam(required = false) Long majorId,
                                                                        @ApiParam(value = "用工性质", required = false) @RequestParam(required = false) Long useNature,
                                                                        @ApiParam(value = "状态", required = false) @RequestParam(required = false) Integer status,
                                                                        @ApiParam(value = "按某一列排序(enterpriseCode或者code)", required = false) @RequestParam(required = false) String order,
                                                                        @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required = false) String sort) {
        Result<Page<List<UserReportPageVO>>> result = new Result<Page<List<UserReportPageVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = userReportService.getUserReportPage(page, param, chainType, enterpriseCode, enterpriseName, deptIds, positionIds, roleIds, educationId, majorId, useNature, status, order, sort, loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("按条件搜索员工page信息:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询学历或者专业 | 开发者 苏磊 | 已完成", notes = "查询学历或者专业 | 开发者 苏磊 | 已联调"
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "0-学历；1-专业"
                    , required = true, paramType = "path"),

    })
    @RequestMapping(value = "{type}/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<EducationMajorReturnVO>> queryEducationOrMajors(
            HttpServletRequest request, @PathVariable String type) {
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Result<List<EducationMajorReturnVO>> result = new Result<>();
        List<EducationMajorReturnVO> educationMajorReturnVOS = educationMajorService.queryEducationMajorByUser(loginUser, type);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, educationMajorReturnVOS);
        return result;
    }

    @ApiOperation(value = "获取部门组织机构信息", notes = "根据当前登录用户对应的企业ID获取配置列表信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getDepartmentOrganization", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<ConnectTree>> getDepartmentOrganization(HttpServletRequest request) {
        Result<List<ConnectTree>> result = new Result<List<ConnectTree>>();
        try {
            HttpSession session = request.getSession();
            UserVO user = (UserVO) session.getAttribute("user");
            if (user == null) {
                user = new UserVO();
            }
            List<Department> department = organizationService.getDepartMentOrganization(user);
            List<ConnectTree> tree = commonComponent.structureTreeWithoutDeleteAndUpdate(department);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, tree);
        } catch (Exception e) {
            logger.error("获取部门组织机构信息错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "根据部门数组查询岗位信息 | 开发者 苏磊 | 已完成", notes = "根据部门数组查询岗位信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/positions/list/get", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<ConnectTree>> queryPositions(
            HttpServletRequest request,
            @RequestBody(required = false)
            @ApiParam(name = "ids[]", value = "部门数组", required = true)
            @Valid
            @NotNull(message = "部门数组不能为空")
            @Size(min = 1, message = "部门数组不能为空")
                    List<Long> ids
    ) {
        Result<List<ConnectTree>> result = new Result<List<ConnectTree>>();
        try {
            HttpSession session = request.getSession();
            UserVO user = (UserVO) session.getAttribute("user");
            if (user == null) {
                user = new UserVO();
            }
            /**
             * 获得当前获得的部门list
             * */
            List<Department> departments = organizationService.getDepartMentOrganizationByIds(ids);
            List<Position> positions = organizationService.getPositions4DeptIdAndEnterpriseIds(ids, user.getEnterpriseId());
            List<ConnectTree> departMentTree = commonComponent.structureTreeWithoutDeleteAndUpdate(departments);
            List<ConnectTree> tree = organizationService.structurePositionTreeWithOutDeleteAndUpdate(departMentTree, positions);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, tree);
        } catch (Exception e) {
            logger.error("获取部门组织机构信息错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="岗位数组的角色信息 | 开发者 苏磊 | 已完成", notes = "岗位数组的角色信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/position/roles/list/get", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<ConnectTreePOJO<SysRole>>> getRoleByPositions(
            @RequestBody(required = false)
            @ApiParam(name = "ids[]", value = "岗位数组", required = true)
            @Valid
            @NotNull(message = "岗位数组不能为空")
            @Size(min = 1,message = "岗位数组不能为空")
                    List<Long> ids
    ) throws Exception {
        Result<List<ConnectTreePOJO<SysRole>>> result = new Result<List<ConnectTreePOJO<SysRole>>>();
        try{
            /**
             * 构建角色树
             */
            List<ConnectTreePOJO<SysRole>> list = permissionSettingsService.getConnectRoleByPositionIds(ids);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
            logger.error("根据获取系统角色树形结构错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
		return result;
    }

    @ApiOperation(value = "员工信息报表导出", notes = "员工信息报表导出 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/exportMemberInfo",method= RequestMethod.GET)
    public void exportMemberInfo(HttpServletResponse response,HttpServletRequest request,
                                           @ApiParam(value = "编码/检索码/名称", required = false) @RequestParam(required = false) String param,
                                           @ApiParam(value = "组织机构类型", required = false) @RequestParam(required = false) Integer chainType,
                                           @ApiParam(value = "组织机构编码", required = false) @RequestParam(required = false) String enterpriseCode,
                                           @ApiParam(value = "组织机构名称", required = false) @RequestParam(required = false) String enterpriseName,
                                           @ApiParam(value = "部门", required = false) @RequestParam(required = false) String deptIds,
                                           @ApiParam(value = "岗位", required = false) @RequestParam(required = false) String positionIds,
                                           @ApiParam(value = "角色", required = false) @RequestParam(required = false) String roleIds,
                                           @ApiParam(value = "学历", required = false) @RequestParam(required = false) Long educationId,
                                           @ApiParam(value = "专业", required = false) @RequestParam(required = false) Long majorId,
                                           @ApiParam(value = "用工性质", required = false) @RequestParam(required = false) Long useNature,
                                           @ApiParam(value = "状态", required = false) @RequestParam(required = false) Integer status,
                                           @ApiParam(value = "按某一列排序(enterpriseCode或者code)", required = false) @RequestParam(required = false) String order,
                                           @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required = false) String sort) throws Exception {

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "员工信息";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<UserReportExcelVO> userReportExcelVO = userReportService.getExcelForm(param, chainType, enterpriseCode, enterpriseName, deptIds, positionIds, roleIds, educationId, majorId, useNature, status, order, sort, loginUser);
            userReportService.export(output,userReportExcelVO,loginUser);
        }catch(Exception e){
            logger.error("员工信息报表导出:"+e.getMessage(),e);
        }

    }


}
