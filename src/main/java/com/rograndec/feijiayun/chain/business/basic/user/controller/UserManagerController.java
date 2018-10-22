package com.rograndec.feijiayun.chain.business.basic.user.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.Nation;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.service.NationService;
import com.rograndec.feijiayun.chain.business.basic.user.service.UserManagerService;
import com.rograndec.feijiayun.chain.business.basic.user.valid.QualityControl4UserAdminAndCodeCheck;
import com.rograndec.feijiayun.chain.business.basic.user.valid.QualityControl4UserQualityCheck;
import com.rograndec.feijiayun.chain.business.basic.user.vo.*;
import com.rograndec.feijiayun.chain.business.basic.user.vo.param.UsersParam;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseAndChildrenVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.*;
import com.rograndec.feijiayun.chain.business.system.set.service.OrganizationService;
import com.rograndec.feijiayun.chain.business.system.set.service.PermissionSettingsService;
import com.rograndec.feijiayun.chain.business.system.set.service.ScopeQualificationService;
import com.rograndec.feijiayun.chain.business.system.set.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.DepartmentComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.pinyin.PinYinUtils;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaiwei on 2017/8/23.
 */
@Api(description = "用户管理服务接口")
@RestController
@RequestMapping("basic/user")
@Validated
public class UserManagerController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PermissionSettingsService permissionSettingsService;

	@Autowired
	private DepartmentComponent departmentComponent;

	@Autowired
	private OrganizationService organizationService;

    @Autowired
	private EnterpriseService enterpriseService;

    @Autowired
    private ScopeQualificationService scopeQualificationService;

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private NationService nationService;


    /**
     * 查询员工信息列表
     * @param enterprise
     * @param dept
     * @param position
     * @param role
     * @param education
     * @param major
     * @param useNature
     * @param status
     * @param queryStr
     * @return
     */
    @ApiOperation(value="查询员工信息列表 | 开发者 翟伟 | 已联调", notes = "根据多条件组合查询员工信息  | 开发者 翟伟 | 已联调"
           ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page> users(
            @RequestParam(required = false)
            @ApiParam(name = "pageNo", value = "当前页码", required = false)
                    Integer pageNo,
            @RequestParam(required = false)
            @ApiParam(name = "pageSize", value = "显示条数", required = false)
                    Integer pageSize,
            @RequestParam(required = false)
            @ApiParam(name = "enterprise", value = "组织机构", required = false)
                    Long enterprise,
            @RequestParam(required = false)
            @ApiParam(name = "dept", value = "部门", required = false)
                    Long dept,
            @RequestParam(required = false)
            @ApiParam(name = "position", value = "岗位", required = false)
                    Long position,
            @RequestParam(required = false)
            @ApiParam(name = "role", value = "角色", required = false)
                    Long role,
            @RequestParam(required = false)
            @ApiParam(name = "education", value = "学历", required = false)
                    Long education,
            @RequestParam(required = false)
            @ApiParam(name = "major", value = "专业", required = false)
                    Long major,
            @RequestParam(required = false)
            @ApiParam(name = "useNature", value = "用工性质", required = false)
                    Long useNature,
            @RequestParam(required = false)
            @ApiParam(name = "status", value = "状态", required = false)
                    Long status,
            @RequestParam(required = false)
            @ApiParam(name = "approveStatus", value = "审核状态（查询全部不需要传值;0-待审核；1-已完成 2-审核被驳回;）", required = false)
                    Integer approveStatus,
            @RequestParam(required = false)
            @ApiParam(name = "queryStr", value = "检索字段", required = false)
                    String queryStr,@ApiIgnore UserVO userVO
    ){
        Result<Page> result = new Result<>();

            Page page = new Page(pageNo, pageSize);

            UsersParam usersParam = new UsersParam(enterprise,
                    dept,
                    position,
                    role,
                    education,
                    major,
                    useNature,
                    status,
                    queryStr,
                    approveStatus);

            page = userManagerService.users(userVO,usersParam,page);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        return result;
    }

    @ApiOperation(value = "导出基础资料员工信息 | 开发者 翟伟 | 已联调", notes = "导出基础资料员工信息 | 开发者 翟伟 | 已联调")
    @RequestMapping(value = "/excel/export", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public void excelExportUsers(
            @RequestParam(required = false)
            @ApiParam(name = "enterprise", value = "组织机构", required = false)
                    Long enterprise,
            @RequestParam(required = false)
            @ApiParam(name = "dept", value = "部门", required = false)
                    Long dept,
            @RequestParam(required = false)
            @ApiParam(name = "position", value = "岗位", required = false)
                    Long position,
            @RequestParam(required = false)
            @ApiParam(name = "role", value = "角色", required = false)
                    Long role,
            @RequestParam(required = false)
            @ApiParam(name = "education", value = "学历", required = false)
                    Long education,
            @RequestParam(required = false)
            @ApiParam(name = "major", value = "专业", required = false)
                    Long major,
            @RequestParam(required = false)
            @ApiParam(name = "useNature", value = "用工性质", required = false)
                    Long useNature,
            @RequestParam(required = false)
            @ApiParam(name = "status", value = "状态", required = false)
                    Long status,
            @RequestParam(required = false)
            @ApiParam(name = "queryStr", value = "检索字段", required = false)
                    String queryStr,
            @RequestParam(required = false)
            @ApiParam(name = "approveStatus", value = "审核状态（查询全部不需要传值;0-待审核；1-已完成 2-审核被驳回;）", required = false)
                    Integer approveStatus,
            HttpServletResponse response,@ApiIgnore UserVO userVO
    ) throws IOException {
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式

        String file = "菲加云-员工";
        String suffix = ".xlsx";
        String fileName = file+suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));

        //输出Excel文件
        OutputStream output = null;
        try {

            output = response.getOutputStream();
            userManagerService.excelExport4User(output,userVO,  enterprise,
                    dept,
                    position,
                    role,
                    education,
                    major,
                    useNature,
                    status,
                    queryStr,approveStatus);
        }finally {
            output.close();
            output.flush();
        }
    }


    @ApiOperation(value="查询单个员工信息和行政信息 | 开发者 翟伟 | 已联调", notes = "根据userId查询员工信息  | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id"
                    , required = true, paramType="path")
    })
    @ResponseBody
    public Result<UserReturnVO> user(
            @PathVariable
                    Long id,@ApiIgnore UserVO userVO
    ){
        Result<UserReturnVO> result = new Result<>();

        UserReturnVO userReturnVO = userManagerService.findUser(id);
        UserAdministrationReturnVO userAdministrationReturnVO = userManagerService.findUserAdministration(id,userVO);
        userReturnVO.setUserAdministrationReturnVO(userAdministrationReturnVO);

        List<Long> roleIds = userAdministrationReturnVO.getRoleIds();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, userReturnVO);
        return result;
    }

    @ApiOperation(value="查询员工信息的行政信息 | 开发者 翟伟 | 已联调", notes = "根据userId查询员工的行政信息 | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "administration/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id"
                    , required = true, paramType="path")
    })
    @ResponseBody
    public Result<UserAdministrationReturnVO> administrationUser(
            @PathVariable
                    Long id,@ApiIgnore UserVO userVO
    ){
        Result<UserAdministrationReturnVO> result = new Result<>();


        UserAdministrationReturnVO userAdministrationReturnVO = userManagerService.findUserAdministration(id,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, userAdministrationReturnVO);
        return result;
    }

    @ApiOperation(value="查询员工信息的个人信息 | 开发者 翟伟 | 已联调", notes = "根据userId查询员工的个人信息 | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "personal/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id"
                    , required = true, paramType="path")
    })
    @ResponseBody
    public Result<UserPersonalReturnVO> personalUser(
            @PathVariable
                    Long id,@ApiIgnore UserVO userVO
    ){
        Result<UserPersonalReturnVO> result = new Result<>();

            UserPersonalReturnVO personalReturnVO = userManagerService.findUserPersonalReturnVO(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, personalReturnVO);
        return result;
    }


    @ApiOperation(value="查询员工信息的资质信息 | 开发者 翟伟 | 已联调", notes = "根据userId查询员工的资质信息 | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "qualificationConfig/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id"
                    , required = true, paramType="path")
    })
    @ResponseBody
    public Result< List<UserQualificationConfigReturnVO>> qualificationConfigUser(
            @PathVariable
                    Long id,@ApiIgnore UserVO userVO
    ){
        Result< List<UserQualificationConfigReturnVO>> result = new Result<>();

            List<UserQualificationConfigReturnVO> userQualificationConfigReturnVOs = userManagerService.findUserQualificationConfigReturnVOs(userVO,id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, userQualificationConfigReturnVOs);
        return result;
    }


    @ApiOperation(value="查询修改记录信息 | 开发者 翟伟 | 已联调", notes = "根据userId查询修改记录信息 | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "modifyRecord/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id"
                    , required = true, paramType="path"),
            @ApiImplicitParam(name = "pageNo", value = "当前页码"
                    , required = false, paramType="query"),
            @ApiImplicitParam(name = "pageSize", value = "显示条数"
                    , required = false, paramType="query")
    })
    @ResponseBody
    public Result< Page> modifyRecords(
            @PathVariable
                    Long id,
            @RequestParam(required = false)
                    Integer pageNo,
            @RequestParam(required = false)
                    Integer pageSize,@ApiIgnore UserVO userVO
    ){
        Result< Page> result = new Result<>();


        Page page = new Page(pageNo,pageSize);
        Page userModifyRecordWithReturnVOS = userManagerService.findUserModifyRecordVOs(id,page);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, userModifyRecordWithReturnVOS);
        return result;
    }

    @ApiOperation(value="新增员工 | 开发者 翟伟 | 已联调", notes = "新增员工 | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addUser(
            @ApiParam(name="addUserVO", value="新增员工信息" , required=true)
            @RequestBody
            @Valid
            @QualityControl4UserAdminAndCodeCheck
            @QualityControl4UserQualityCheck
            AddUserVO addUserVO,@ApiIgnore UserVO userVO) throws Exception {
        Result result = new Result<>();
        long startTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++ user save start"+startTime+"+++++++++++++++++++++");
        userManagerService.saveUser(addUserVO,userVO);
        long endTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++ user save end"+endTime+"+++++++++++++++++++++ user save end");
        logger.info("+++++++++++++++++++++ user save start-end"+ (startTime - endTime) + "+++++++++++++++++++++ user save start-end");

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, null);

        return result;
    }

    @ApiOperation(value="修改员工 | 开发者 翟伟 | 已联调", notes = "修改员工 | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUser(
            @ApiParam(name="addUserVO", value="新增员工信息" , required=true)
            @RequestBody
            @Valid
            @QualityControl4UserAdminAndCodeCheck
            @QualityControl4UserQualityCheck
                    AddUserVO addUserVO,@ApiIgnore UserVO userVO) throws Exception {
        Result result = new Result<>();

        userManagerService.updateUser(addUserVO,userVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, null);

        return result;
    }

    @ApiOperation(value="重置密码 | 开发者 翟伟 | 已联调", notes = "重置密码 | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/rest/password", method = RequestMethod.POST)
    @ResponseBody
    public Result restPws(
            @ApiParam(name="userId", value="用户id" , required=true)
            @RequestParam(name = "userId",required = true)
                    Long userId,@ApiIgnore UserVO userVO) throws Exception {
        Result result = new Result<>();

        userManagerService.resetPassword(userVO,userId);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, null);

        return result;
    }

    /**
     * 查询接口类型,根据企业id查询
     */

    @ApiOperation(value="根据登录人企业id,查询该企业下所有部门信息 | 开发者 翟伟 | 已联调", notes = "查询该企业下所有部门信息", produces = MediaType.APPLICATION_JSON_VALUE)

    @RequestMapping(value = "/departments/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<DepartmentVO>> queryDepartment4EnterpriseId(@ApiIgnore UserVO userVO,
            @RequestParam(value = "enterpriseId",required = false) Long enterpriseId){
        Result<List<DepartmentVO>> result = new Result<List<DepartmentVO>>();

        List<Department> departments = departmentComponent.findDepartMent4EnterpriseId(userVO.getEnterpriseId());
        List<DepartmentVO> departmentVOS = DepartmentVO.getDepartmentVOs4Departments(departments);
        result.setData(departmentVOS);


        result.setBizCodeSuccessInfo(SysCode.SUCCESS);


        return result;
    }

    @ApiOperation(value="根据登录人企业id和角色id,查询该企业下该角色的用户信息 | 开发者 翟伟 | 已联调", notes = "根据登录人企业id和角色id,查询该企业下该角色的用户信息", produces = MediaType.APPLICATION_JSON_VALUE)

    @RequestMapping(value = "/role/users/{roleCode}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleCode", value = "角色code;010101:法定代表人;010201:企业负责人;030101:质量负责人"
                    , required = true, paramType="path")
    })
    @ResponseBody
    public Result<List<User>> roleUsers(@ApiIgnore UserVO userVO, @PathVariable
            String roleCode){
        Result<List<User>> result = new Result<>();


        List<User> users = userManagerService.findUserByIdAndRole(userVO, roleCode);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,users);


        return result;
    }

    @ApiOperation(value="根据登录人企业id,查询该企业下该企业负责人,法人,质量负责人角色的用户信息 | 开发者 翟伟 | 已联调", notes = "根据登录人企业id,查询该企业下该企业负责人,法人,质量负责人角色的用户信息", produces = MediaType.APPLICATION_JSON_VALUE)

    @RequestMapping(value = "/roles/users", method = RequestMethod.GET)
    @ResponseBody
    public Result<Map<String,List<User>>> roleUsers(@ApiIgnore UserVO userVO){
        Result<Map<String,List<User>>> result = new Result<>();
        Map<String,List<User>> users = userManagerService.findUserByIdAndRoles(userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,users);


        return result;
    }

    @ApiOperation(value="根据名称获取检索码信息 | 开发者 翟伟 | 已联调", notes = "根据名称获取检索码信息 | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/pinyin", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> getPinYin(HttpServletRequest request,
                                              @ApiParam(value = "名称", required = false) @RequestParam(required = false) String name){
        Result<String> result = new Result<String>();
        try{
            String pinyin = "";

            if(StringUtils.isNotBlank(name)){
                pinyin = PinYinUtils.getFirstSpell(name);
            }

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, pinyin);
        }catch(Exception e){
            logger.error("根据名称获取检索码信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value="根据用户id获取用户角色 | 开发者 翟伟 | 已联调", notes = "根据用户id获取用户角色 | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/user/roles/{id}", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id"
                    , required = true, paramType="path")
    })
    @ResponseBody
    public Result<List<Tree>> userRoles( @PathVariable Long id){
        Result<List<Tree>> result = new Result<List<Tree>>();

        List<Tree> role4User = userManagerService.getRole4User(id);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, role4User);
        return result;
    }


    @ApiOperation(value="根据部门数组查询岗位信息 | 开发者 翟伟 | 已联调", notes = "根据部门数组查询岗位信息 | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/positions/list/get", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<PositionVO>> queryPositions(@ApiIgnore UserVO userVO,
            @RequestBody(required = false)
            @ApiParam(name = "ids[]", value = "部门数组", required = true)
            @Valid
            @NotNull(message = "部门数组不能为空")
            @Size(min = 1,message = "部门数组不能为空")
                    List<Long> ids
    ){
        Result<List<PositionVO>> result = new Result<List<PositionVO>>();
        /**
         * 预留接口删除岗位信息,判断是否与其他表关联再来定夺是否删除
         */
        Long enterpriseId = userVO.getEnterpriseId();
        if (userVO.getChainType() == ChainType.Selfoperatedshop.getCode()){// 分店获取的是总部的
            enterpriseId = userVO.getParentId();
        }
        List<Position> positions = organizationService.getPositions4DeptIdAndEnterpriseIds(ids,enterpriseId);
        List<PositionVO> positionVOS = PositionVO.getPositionsVO4Positions(positions);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        result.setData(positionVOS);
        return result;
    }

//    @ApiOperation(value="岗位下的角色信息", notes = "岗位下的角色信息 | 开发者:翟伟", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiImplicitParam(name = "positionId", value = "岗位id", required = true, dataType = "Long", paramType="path")
//    @RequestMapping(value = "/position/roles/{positionId}/get", method = RequestMethod.GET)
//    @ResponseBodyEntity
//    public Result<List<SysRoleVO>> getRoleByPosition( @PathVariable Long positionId) throws Exception {
//        Result<List<SysRoleVO>> result = new Result();
//
//            List<SysRole> sysRoles = permissionSettingsService.getRoleByPosition(positionId);
////            result.setBizCodeSuccessInfo(SysCode.SUCCESS,permissionSettingsService.getAllRoles(enterpriseId));
//            List<SysRoleVO> sysRoleVOS = SysRoleVO.getSysRoleVOByRole(sysRoles);
//            result.setBizCodeSuccessInfo(SysCode.SUCCESS, sysRoleVOS);
//        return result;
//    }


    @ApiOperation(value="岗位数组的角色信息 | 开发者 翟伟 | 已联调", notes = "岗位数组的角色信息 | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/position/roles/list/get", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<SysRoleVO>> getRoleByPositions(@ApiIgnore UserVO userVO,
            @RequestBody(required = false)
            @ApiParam(name = "ids[]", value = "岗位数组", required = true)
            @Valid
            @NotNull(message = "岗位数组不能为空")
            @Size(min = 1,message = "岗位数组不能为空")
                    List<Long> ids
    ) throws Exception {
        Result<List<SysRoleVO>> result = new Result();
        List<SysRole> sysRoles = permissionSettingsService.getRoleByPositions(ids,userVO);
        List<SysRoleVO> sysRoleVOS = SysRoleVO.getSysRoleVOByRole(sysRoles);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, sysRoleVOS);
        return result;
    }

    @ApiOperation(value="查询民族信息 | 开发者 翟伟 | 已联调", notes = "查询民族信息 | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/nations/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<NationVO>> getRoleByPosition() throws Exception {
        Result<List<NationVO>> result = new Result();

        List<Nation> nations = nationService.queryNationsAll();
        List<NationVO> nationVOS = NationVO.getNationsVO4Nations(nations);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, nationVOS);
        return result;
    }


    @ApiOperation(value="根据登录用户的企业id查询该企业底下所有子公司以及本身数据 | 开发者 翟伟 | 已联调", notes = "根据登录用户的企业id查询该企业底下所有子公司以及本身数据 | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/children/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<EnterpriseAndChildrenVO>> getEnterpriseChildren(@ApiIgnore UserVO userVO){
        Result<List<EnterpriseAndChildrenVO>> result = new Result<List<EnterpriseAndChildrenVO>>();

            List<EnterpriseAndChildrenVO> enterpriseVOS = new ArrayList<>();
            if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
                List<Enterprise> enterprises = enterpriseService.getChildrens(userVO.getEnterpriseId());

                for(Enterprise enterprise : enterprises){
                    EnterpriseAndChildrenVO enterpriseVO = EnterpriseAndChildrenVO.getEnterprise4VO(enterprise);
                    enterpriseVOS.add(enterpriseVO);
                }
            }else {
                Enterprise enterprise = enterpriseService.queryEnterpriseById4StatusEnable(userVO.getEnterpriseId(),"enterprise");
                EnterpriseAndChildrenVO enterpriseVO = EnterpriseAndChildrenVO.getEnterprise4VO(enterprise);
                enterpriseVOS.add(enterpriseVO);
            }


            result.setBizCodeSuccessInfo(SysCode.SUCCESS, enterpriseVOS);
//        }catch(Exception e){
//            logger.error("获取企业基本信息错误:"+e.getMessage(),e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//            return result;
//        }
        return result;
    }


    @ApiOperation(value="查看受限品种范围信息 | 开发者 翟伟 | 已联调", notes = "根据当前登录用户对应的企业ID查看受限品种范围信息 | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/scopeQualification/get", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<BusinessScopeVO>> getScopeQualification(
            @RequestBody(required = false)
            @ApiParam(name = "businessVarietys[]", value = "受限品种", required = true)
            @Valid
            @NotNull(message = "受限品种不能为空")
            BusinessVarietysVO businessVarietys,@ApiIgnore UserVO userVO
    ){
        Result<List<BusinessScopeVO>> result = new Result<List<BusinessScopeVO>>();

        List<BusinessScope> businessScope = scopeQualificationService.getScopeQualificationByIdAndScopes(userVO,businessVarietys);
        List<BusinessScopeVO> businessScopeVOS = BusinessScopeVO.getBusinessScopeVOs4BusinessScopes(businessScope);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, businessScopeVOS);
        return result;
    }

    @ApiOperation(value="查看正常启用的员工资质信息下拉框 | 开发者 翟伟 | 已联调", notes = "查看正常启用的员工资质信息下拉框  | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "enable/userQualification/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<UserQualificationReturnVO>> getUserQualification(@ApiIgnore UserVO userVO) {
        Result<List<UserQualificationReturnVO>> result = new Result<List<UserQualificationReturnVO>>();

            List<UserQualification> userQualification = scopeQualificationService.getUserQualification4Enable(userVO);

            List<UserQualificationReturnVO> userQualificationReturnVOS = UserQualificationReturnVO.getUserQualificationReturnsVO4UserQualifications(userQualification);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, userQualificationReturnVOS);
//        } catch (Exception e) {
//            logger.error("查看员工资质信息错误:" + e.getMessage(), e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//            return result;
//        }
        return result;
    }

    @ApiOperation(value="初始化必填员工资质信息 | 开发者 翟伟 | 已联调", notes = "初始化必填员工资质信息 | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "init/userQualificationConfig/get", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<UserQualificationConfigReturnVO>> initUserQualificationConfig(
            @RequestBody(required = false)
            @ApiParam(name = "ids[]", value = "岗位数组", required = true)
            @Valid
            @NotNull(message = "岗位数组不能为空")
            @Size(min = 1,message = "岗位数组不能为空")
                    List<Long> ids,@ApiIgnore UserVO userVO
    ) {
        Result<List<UserQualificationConfigReturnVO>> result = new Result<List<UserQualificationConfigReturnVO>>();

        List<UserQualificationConfigReturnVO> userQualification = userManagerService.initUserQualificationConfig(userVO,ids);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, userQualification);
//        } catch (Exception e) {
//            logger.error("查看员工资质信息错误:" + e.getMessage(), e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//            return result;
//        }
        return result;
    }

    @ApiOperation(value="必填员工资质信息 | 开发者 翟伟 | 已联调", notes = "必填员工资质信息 | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/must/userQualificationConfigs", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<UserQualificationConfigReturnVO>> mustUserQualificationConfig(@ApiIgnore UserVO userVO
    ) {
        Result<List<UserQualificationConfigReturnVO>> result = new Result<>();

        List<UserQualificationConfigReturnVO> userQualification = userManagerService.initUserQualificationConfig(userVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, userQualification);
        return result;
    }


    @ApiOperation(value="个人修改密码 | 开发者 翟伟 | 已联调", notes = "个人修改密码 | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public Result updatePwd(@RequestBody @Valid UserPwdVO userPwdVO,@ApiIgnore UserVO userVO){

        Result result = new Result<>();
        Long userId = userVO.getUserId();
        userPwdVO.setUserId(userId);
      /*  userPwdVO.setPassword(PasswordUtils.MD5Password(userPwdVO.getPassword()));*/
        int count = userManagerService.getUserCountByUIDPwd(userPwdVO);
        if(count ==  0){
            //密码错误
            result.setBizCodeFallInfo(SysCode.FAIL,"密码错误");
            return result;
        }
        String newPassword = userPwdVO.getNewPassword();
        String affirmPassword = userPwdVO.getAffirmPassword();
        if(StringUtils.isBlank(newPassword) || !newPassword.equals(affirmPassword)){
            //密码不一致
            result.setBizCodeFallInfo(SysCode.FAIL,"密码不一致");
            return result;
        }

        userManagerService.updatePwd(userPwdVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,"修改成功");
        return result;
    }


    @ApiOperation(value="根据受限品种获取受限品种范围信息 | 开发者 翟伟 | 已联调", notes = "根据受限品种获取受限品种范围信息 | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getBusinessScope", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<BusinessScope>> getBusinessScope(HttpServletRequest request,
                                                        @ApiParam(value = "品种类别以,隔开", required = true) @RequestParam String businessVariety
                                                        ,@ApiIgnore UserVO userVO){
        Result<List<BusinessScope>> result = new Result<List<BusinessScope>>();
        if(StringUtils.isBlank(businessVariety)){
            result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, null);
            return result;

        }

        List<BusinessScope> scopeList = enterpriseService.queryBusinessScopeByBusinessVariety(userVO,businessVariety);

        if(scopeList == null || scopeList.size() == 0){

            result.setBizCodeSuccessInfo(SysCode.BIS_MEMBER_LEVE_ID_NOT_FIND, scopeList);
            return result;
        }

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, scopeList);
        return result;
    }


}
