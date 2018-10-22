/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.controller;

import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.business.system.set.service.WarnSetService;
import com.rograndec.feijiayun.chain.business.system.set.vo.WarnSetVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
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

 * @Description:系统管理-系统设置-预警设置服务接口

 * @author:LeiSu,Dong.ma

 * @time:2017年8月21日 下午7:46:30

 */

@Api(description="系统管理-系统设置-预警设置服务接口")
@RestController
@RequestMapping("/system/set/warnSet")
public class WarnSetController {
	
	private static final Log logger = LogFactory.getLog(WarnSetController.class);
	
	@Autowired
	private WarnSetService warnSetService;
	
	@ApiOperation(value="查看预警设置信息", notes = "根据当前登录用户以及预警类型查看预警设置信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getWarnSet", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<WarnSetVO>> getWarnSet(HttpServletRequest request,
											@ApiParam(value = "查看类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）",required = true) @RequestParam Integer setType){
		Result<List<WarnSetVO>> result = new Result<>();
        try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			List<WarnSetVO> warnSets = warnSetService.getWarnSet(loginUser,setType);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, warnSets);
        }catch(Exception e){
        	logger.error("查看预警设置信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="修改预警设置信息", notes = "根据当前登录用户以及预警类型修改预警设置信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/updateWarnSet", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> updateWarnSet(HttpServletRequest request,
										 @ApiParam(value = "预警设置信息集合", required = true)
										 @RequestBody List<WarnSet> warnSets){
		Result<String> result = new Result<String>();
        try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			warnSetService.updateWarnSet(loginUser,warnSets);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改预警设置信息成功");
        }catch(Exception e){
        	logger.error("修改预警设置信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}

	@ApiOperation(value = "获取预警角色信息", notes = "根据部门显示角色信息,未勾选部门,显示全部角色 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getRole",method = RequestMethod.GET)
	@ResponseBody
	public Result getRole(HttpServletRequest request, @ApiParam(value = "部门ID,没有传-1", required = true) @RequestParam Long deptId){
		Result<List<SysRole>> result = new Result();
		try {
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			List<SysRole> roles = warnSetService.getRole(deptId,loginUser);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,roles);
		} catch (Exception e) {
			logger.error("获取预警角色信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}

	@ApiOperation(value = "获取预警部门信息", notes = "显示所有部门信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getDepartment",method = RequestMethod.GET)
	@ResponseBody
	public Result getDepartment(HttpServletRequest request){
		Result<List<Department>> result = new Result();
		try {
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			List<Department> departments = warnSetService.getDepartment(loginUser);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,departments);
		} catch (Exception e) {
			logger.error("获取预警角色信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}

}
