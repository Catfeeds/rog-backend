/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.controller;

import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.Tree;
import com.rograndec.feijiayun.chain.business.system.set.service.OrganizationService;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.DepartmentComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DeparmentCondition;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**

 * @Description:系统管理-系统设置-组织机构服务接口

 * @author:LeiSu

 * @time:2017年8月21日 下午8:33:29

 */

@Api(description="系统管理-系统设置-组织机构服务接口")
@RestController
@RequestMapping("/system/set/departmentOrganization")
@Validated
public class DepartmentOrganizationController {

private static final Log logger = LogFactory.getLog(DepartmentOrganizationController.class);
	
	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private DepartmentComponent departmentComponent;

	@Autowired
	private CommonComponent commonComponent;
	
	@ApiOperation(value="获取部门组织机构信息", notes = "根据当前登录用户对应的企业ID获取配置列表信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getDepartmentOrganization", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<Tree>> getDepartmentOrganization(HttpServletRequest request,@ApiIgnore UserVO userVO){
		Result<List<Tree>> result = new Result<List<Tree>>();
        try{
    		List<Department> department = organizationService.getDepartMentOrganization(userVO);
    		List<Tree> tree = commonComponent.structureTree(department,userVO);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS,tree);
        }catch(Exception e){
        	logger.error("获取部门组织机构信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="修改部门组织机构信息", notes = "根据当前登录用户对应的企业ID修改配置列表信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "department", value = "修改部门信息", required = true, dataType = "Department")
	@RequestMapping(value = "/updateDepartmentOrganization", method = RequestMethod.POST)
	@ResponseBody
	public Result<Department> updateDepartmentOrganization(HttpServletRequest request,@RequestBody Department department,@ApiIgnore UserVO userVO){
		Result<Department> result = new Result<Department>();
        try{
        	organizationService.updateDepartMentOrganization(department,userVO);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
			logger.error("修改部门组织机构信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("修改部门组织机构信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="增加部门组织机构信息", notes = "根据当前登录用户对应的企业ID增加部门组织机构信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "department", value = "增加的部门信息", required = true, dataType = "Department")
	@RequestMapping(value = "/addDepartmentOrganization", method = RequestMethod.POST)
	@ResponseBody
	public Result<Department> addDepartmentOrganization(HttpServletRequest request,@RequestBody Department department,@ApiIgnore UserVO userVO){
		Result<Department> result = new Result<Department>();
        try{
        	organizationService.addDepartMentOrganization(department,userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
			logger.error("增加部门组织机构信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("增加部门组织机构信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;

        }
		return result;
	}

	@ApiOperation(value="删除部门组织机构信息", notes = "根据当前登录用户对应的企业ID删除部门组织机构信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/deleteDepartmentOrganization/{id}", method = RequestMethod.POST)
	@ApiImplicitParam(name = "id", value = "部门的ID", required = true, dataType = "Long",paramType = "path")
	@ResponseBody
	public Result<Department> deleteDepartmentOrganization(HttpServletRequest request,@PathVariable("id") @NotNull Long id,@ApiIgnore UserVO userVO){
		Result<Department> result = new Result<Department>();
        try{
        	/**
        	 * 这里预留接口，因为后期加上关联表无法删除
        	 */
    		int status = organizationService.deleteDepartMentOrganization(id,userVO);
    		//返回状态0-操作成功 1-操作失败有返回信息 其他-操作失败
    		if (status == DeparmentCondition.RIGHT.getCode()){
    			result.setBizCodeSuccessInfo(SysCode.SUCCESS);
    		}else if (status == DeparmentCondition.HAS_POSITION.getCode()){
    			result.setBizCodeFallInfo(SysCode.FAIL,DeparmentCondition.HAS_POSITION.getName());
    		}else if (status == DeparmentCondition.HAS_DEPARTMENT.getCode()){
    			result.setBizCodeFallInfo(SysCode.FAIL,DeparmentCondition.HAS_DEPARTMENT.getName());
    		}else if (status == DeparmentCondition.HAS_ROLE.getCode()){
				result.setBizCodeFallInfo(SysCode.FAIL,DeparmentCondition.HAS_ROLE.getName());
			}else if (status == DeparmentCondition.HAS_USER.getCode()){
				result.setBizCodeFallInfo(SysCode.FAIL,DeparmentCondition.HAS_USER.getName());
			}
        }catch (BusinessException e) {
			logger.error("删除部门组织机构信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("删除部门组织机构信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}

	@ApiOperation(value="部门下拉框", notes = "获取当前企业的部门下拉框 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getDepartmentSelector", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<Department>> getDepartmentSelector(HttpServletRequest request,@ApiIgnore UserVO userVO){
		Result<List<Department>> result = new Result<List<Department>>();
        try{
			Long enterpriseId = userVO.getEnterpriseId();

			if (userVO.getChainType() == ChainType.Selfoperatedshop.getCode()){// 分店获取的是总部的
				enterpriseId = userVO.getParentId();
			}
    		List<Department> findDepartMent = departmentComponent.findDepartMent(enterpriseId);
    		result.setBizCodeSuccessInfo(SysCode.SUCCESS,findDepartMent);
        }catch(Exception e){
        	logger.error("删除部门组织机构信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="岗位增加中的部门下拉框", notes = "岗位增加中的部门下拉框 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPositionDepartmentSelector", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<Department>> getPositionDepartmentSelector(HttpServletRequest request,@ApiIgnore UserVO userVO){
		Result<List<Department>> result = new Result<List<Department>>();
        try{
			Long enterpriseId = userVO.getEnterpriseId();

			if (userVO.getChainType() == ChainType.Selfoperatedshop.getCode()){// 分店获取的是总部的
				enterpriseId = userVO.getParentId();
			}
    		List<Department> findDepartMent = departmentComponent.findAllDepartMent(enterpriseId);
    		if (findDepartMent != null && findDepartMent.size() > 0){
				List<Department> copyDepartment = new ArrayList<Department>();
				copyDepartment = findDepartMent;
				Iterator<Department> it = findDepartMent.iterator();
				while (it.hasNext()){
					Department d = it.next();
					for (int j = 0; j < copyDepartment.size(); j++){
						if (d.getId().equals(copyDepartment.get(j).getParentDeptId())){
							it.remove();
						}
					}
				}
			}
    		result.setBizCodeSuccessInfo(SysCode.SUCCESS,findDepartMent);
        }catch(Exception e){
        	logger.error("获取部门信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}

}
