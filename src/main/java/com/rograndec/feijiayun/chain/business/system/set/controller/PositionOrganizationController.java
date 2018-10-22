/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.controller;

import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import com.rograndec.feijiayun.chain.business.system.set.entity.Tree;
import com.rograndec.feijiayun.chain.business.system.set.service.OrganizationService;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**

 * @Description:系统管理-系统设置-组织机构服务接口

 * @author:LeiSu

 * @time:2017年8月21日 下午8:33:49

 */

@Api(description="系统管理-系统设置-组织机构服务接口")
@RestController
@RequestMapping("/system/set/positionOrganization")
public class PositionOrganizationController {

private static final Log logger = LogFactory.getLog(PositionOrganizationController.class);
	
	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private CommonComponent commonComponent;

	@ApiOperation(value="获取岗位组织机构信息", notes = "根据当前登录用户对应的企业ID获取岗位组织机构信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPositionOrganization", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<Tree>> getPositionOrganization(HttpServletRequest request,@ApiIgnore UserVO userVO){
		Result<List<Tree>> result = new Result<List<Tree>>();
        try{
    		List<Position> position = organizationService.getPositionOrganization(userVO);
    		List<Department> department = organizationService.getDepartMentOrganization(userVO);
    		List<Tree> departMentTree = commonComponent.structureTree(department, userVO);
    		List<Tree> tree = organizationService.structurePositionTree(departMentTree,position,userVO);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, tree);
        }catch(Exception e){
        	logger.error("获取岗位组织机构信息:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="修改岗位组织机构信息", notes = "根据当前登录用户对应的企业ID修改岗位组织机构信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "position", value = "岗位信息", required = true, dataType = "Position")
	@RequestMapping(value = "/updatePositionOrganization", method = RequestMethod.POST)
	@ResponseBody
	public Result<Position> updatePositionOrganization(HttpServletRequest request,@RequestBody Position position,@ApiIgnore UserVO userVO){
		Result<Position> result = new Result<Position>();
        try{
        	organizationService.updatePositionOrganization(position,userVO);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
			logger.error("修改岗位组织机构信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("修改岗位组织机构信息:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="增加岗位组织机构信息", notes = "根据当前登录用户对应的企业ID增加岗位组织机构信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "position", value = "岗位信息", required = true, dataType = "Position")
	@RequestMapping(value = "/addPositionOrganization", method = RequestMethod.POST)
	@ResponseBody
	public Result<Position> addPositionOrganization(HttpServletRequest request,@RequestBody Position position,@ApiIgnore UserVO userVO){
		Result<Position> result = new Result<Position>();
        try{
        	organizationService.addPositionOrganization(position,userVO);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
			logger.error("增加岗位组织机构信息:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("增加岗位组织机构信息:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="删除岗位组织机构信息", notes = "根据当前登录用户对应的企业ID删除岗位组织机构信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "id", value = "岗位ID", required = true, dataType = "Long", paramType="path")
	@RequestMapping(value = "/deletePositionOrganization/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result<Position> deletePositionOrganization(HttpServletRequest request,@PathVariable Long id,@ApiIgnore UserVO userVO){
		Result<Position> result = new Result<Position>();
        try{
        	/**
        	 * 预留接口删除岗位信息,判断是否与其他表关联再来定夺是否删除
        	 */
        	int status = organizationService.deletePositionOrganization(id,userVO);
        	if (status == 0){
        		result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        	}else if (status == 1){
        		result.setBizCodeFallInfo(SysCode.FAIL,"当前岗位还有角色信息，无法删除！");
        	} else if (status == 2){
				result.setBizCodeFallInfo(SysCode.FAIL,"当前岗位被员工引用，无法删除！");
			}
        	
        }catch (BusinessException e) {
			logger.error("删除岗位组织机构信息:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("删除岗位组织机构信息:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="岗位下拉框接口", notes = "岗位下拉框接口 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPositionSelector", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<Position>> getPositionSelector(HttpServletRequest request,@ApiIgnore UserVO userVO){
		Result<List<Position>> result = new Result<List<Position>>();
        try{
    		List<Position> position = organizationService.getPositionOrganization(userVO);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, position);
        }catch(Exception e){
        	logger.error("获取岗位组织机构信息:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}


}
