/**
 *
 */
package com.rograndec.feijiayun.chain.business.system.set.controller;

import com.rograndec.feijiayun.chain.business.system.set.entity.*;
import com.rograndec.feijiayun.chain.business.system.set.service.PermissionSettingsService;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**

 * @Description:系统管理-系统设置-权限设置服务接口

 * @author:LeiSu,Dong.ma

 * @time:2017年8月21日 下午7:57:48

 */

@Api(description="系统管理-系统设置-权限设置服务接口")
@RestController
@RequestMapping("/system/set/permissionSettings")
public class PermissionSettingsController {

	private static final Log logger = LogFactory.getLog(PermissionSettingsController.class);

	@Autowired
	private PermissionSettingsService permissionSettingsService;

	/**
	 * 修改时获取角色对应的功能信息
	 * @param request
	 * @return
	 */
	@ApiOperation(value="根据角色获取勾选信息", notes = "根据角色获取勾选信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getChooseActions", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<SysRoleAction>> getChooseActions(HttpServletRequest request,
														@ApiParam(value = "选取的角色ID", required = true) @RequestParam Long roleId){
		Result<List<SysRoleAction>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			Long enterpriseId = loginUser.getEnterpriseId();
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, permissionSettingsService.getChooseActions(enterpriseId,roleId));
		}catch(Exception e){
			logger.error("根据角色获取勾选信息:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value="根据角色删除权限", notes = "根据角色删除权限 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/deleteActions", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> deleteActions(HttpServletRequest request,
										@ApiParam(value = "选取的角色ID", required = true) @RequestParam Long roleId){
		Result<String> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			Long enterpriseId = loginUser.getEnterpriseId();
			permissionSettingsService.deleteActions(enterpriseId,roleId);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, "根据角色删除权限成功");
		}catch (BusinessException e){
			result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
			return result;

		}catch(Exception e){
			logger.error("根据角色获取勾选信息:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

//	/**
//	 * 界面右边所有功能展示接口
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value="获取系统所有的功能", notes = "根据当前用户获得所有功能 | 开发者:马东", produces = MediaType.APPLICATION_JSON_VALUE)
//	@RequestMapping(value = "/getSystemActions", method = RequestMethod.GET)
//	@ResponseBodyEntity
//	public Result<Map<Map<String,Long>,List<SysAction>>> getSystemActions(HttpServletRequest request){
//		Result<Map<Map<String,Long>,List<SysAction>>> result = new Result<>();
//		try{
//			HttpSession session = request.getSession(true);
//			UserVO loginUser = (UserVO) session.getAttribute("user");
//			Long enterpriseId = loginUser.getEnterpriseId();
//			result.setBizCodeSuccessInfo(SysCode.SUCCESS, permissionSettingsService.getAction(enterpriseId));
//		}catch(Exception e){
//			logger.error("获取系统所有的功能信息错误:"+e.getMessage(),e);
//			result.setBizCodeFallInfo(SysCode.FAIL);
//			return result;
//		}
//		return result;
//	}

	/**
	 * 界面右边所有功能展示接口
	 * @param request
	 * @return
	 */
	@ApiOperation(value="根据获取系统所有的功能树形结构", notes = "根据当前用户获得所有功能 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getTreeSystemActions", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<TreePOJO<SysAction>>> getTreeSystemActions(HttpServletRequest request,
			@ApiParam(value = "角色ID,没有就传-1", required = true) @RequestParam Long roleId,
			@ApiParam(value = "是否选中", required = true) @RequestParam Boolean checked){
		Result<List<TreePOJO<SysAction>>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, permissionSettingsService.getTree(loginUser,roleId,checked));
		}catch(Exception e){
			logger.error("获取系统所有的功能信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value="根据获取系统角色树形结构", notes = "根据获取系统角色树形结构 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getTreeSystemRole", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<TreePOJO<SysRole>>> getTreeSystemRole(HttpServletRequest request){
		Result<List<TreePOJO<SysRole>>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, permissionSettingsService.getRoleTree(loginUser));
		}catch(Exception e){
			logger.error("根据获取系统角色树形结构错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value="权限修改接口", notes = "根据当前用户修改对应角色的功能 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/updateSystemActions", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> updateSystemActions(HttpServletRequest request,
		   @ApiParam(value = "需要修改的记录和角色的信息", required = true) @RequestBody RoleActionsVO roleactions){
		Result<String> result = new Result<String>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			permissionSettingsService.updateAll(loginUser,roleactions.getRole(),roleactions.getRoleActions());
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改对应角色的功能信息成功");
		}catch(Exception e){
			logger.error("修改对应角色的功能信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value="保存角色和功能", notes = "保存新增的角色和功能 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/saveAll", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> saveAll(HttpServletRequest request,
			@ApiParam(value = "需要保存的角色和功能信息", required = true) @RequestBody SaasRoleActionsPOJO roleactions){
		Result<String> result = new Result<String>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			SysRole role = roleactions.getRole();
//			if(permissionSettingsService.checkExistsCodeName(role,loginUser)){
//				result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "角色编码或角色名称重复!");
//				return result;
//			}
			if(role.getName() == null){
				result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "名称不能为空");
				return result;
			}
			else if (role.getName().trim().equals("")){
				result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "名称不能为空");
				return result;
			}
			if(role.getCode() == null){
				result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "编码不能为空");
				return result;
			}
			else if (role.getCode().trim().equals("")){
				result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "编码不能为空");
				return result;
			}else {
				if(ChineseString.isChinese(role.getCode())){
					result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "编码不能含有中文字符");
					return result;
				}
			}
			List<SysAction> actions = roleactions.getActions();
//			Iterator<SysAction> it = actions.iterator();
//			while(it.hasNext()){
//				String code = it.next().getCode();
//				if(code == null){
//					result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "编码不能为空");
//					return result;
//				}
//				else if (code.trim().equals("")){
//					result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "编码不能为空");
//					return result;
//				}else {
//					if(ChineseString.isChinese(code)){
//						result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "编码不能含有中文字符");
//						return result;
//					}
//				}
//			}
			permissionSettingsService.saveAll(loginUser,role,actions);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存角色和功能成功");
		}catch (BusinessException e){
			result.setBizCodeFallInfo(SysCode.FAIL.getCode(), e.getMessage());
			return result;
		}catch(Exception e){
			logger.error("保存对应角色的功能信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	/**
	 * 级联查询部门
	 * @param request
	 * @return
	 */
	@ApiOperation(value="获取所有部门", notes = "显示总部或者分店的所有部门信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getDepartment", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<Department>> getDepartment(HttpServletRequest request){
		Result<List<Department>> result = new Result();
		try {
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			List<Department> departments = permissionSettingsService.getDepartment(loginUser);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,departments);
		} catch (Exception e) {
			logger.error("获取所有部门信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}

	/**
	 * 级联查询岗位
	 * @param request
	 * @param deptId
	 * @return
	 */
	@ApiOperation(value="获取所有岗位", notes = "根据选择的部门级联显示角色信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPosition", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<Position>> getPosition(HttpServletRequest request,
											  @ApiParam(value = "部门ID", required = true) @RequestParam Long deptId){
		Result<List<Position>> result = new Result();
		try {
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			List<Position> positions = permissionSettingsService.getPosition(loginUser,deptId);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,positions);

		} catch (Exception e) {
			logger.error("获取所有岗位:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}

	@ApiOperation(value="根据角色ID获取角色信息", notes = "根据角色ID获取角色信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getRole", method = RequestMethod.GET)
	@ResponseBody
	public Result<SysRole> getRole(HttpServletRequest request,
								   @ApiParam(value = "角色ID", required = true) @RequestParam Long roleId){
		Result<SysRole> result = new Result();
		try {
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			Long enterpriseId = loginUser.getEnterpriseId();
			SysRole sysRole = permissionSettingsService.getRole(enterpriseId,roleId);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,sysRole);

		} catch (Exception e) {
			logger.error("根据角色ID获取角色信息:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}
	@ApiOperation(value="权限是否可删除", notes = "权限是否可删除 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/checkDeleteRoleAction", method = RequestMethod.GET)
	@ResponseBody
	public Result<String> checkDeleteRoleAction(HttpServletRequest request,
			@ApiParam(value = "角色ID", required = true) @RequestParam Long roleId){
		Result<String> result = new Result();
		try {
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			Long enterpriseId = loginUser.getEnterpriseId();
			Boolean isDelete = permissionSettingsService.checkDeleteRoleAction(enterpriseId,roleId);
			if(isDelete){
				result.setBizCodeSuccessInfo(SysCode.SUCCESS,isDelete+",可以删除");
			}else{
				result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"已有人员对应角色权限,不能删除!");
			}
		} catch (Exception e) {
			logger.error("权限是否可删除错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}

	@ApiOperation(value="获取所有有权限的角色信息", notes = "获取所有有权限的角色信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getAllRoles", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<SysRole>> getAllRoles(HttpServletRequest request){

		Result<List<SysRole>> result = new Result();
		try {
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			Long enterpriseId = loginUser.getEnterpriseId();
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,permissionSettingsService.getAllRoles(enterpriseId));

		} catch (Exception e) {
			logger.error("获取所有有权限的角色信息:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}



}
