/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.UserQualification;
import com.rograndec.feijiayun.chain.business.system.set.service.ScopeQualificationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**

 * @Description:系统管理-系统设置-范围和资质服务接口

 * @author:LeiSu

 * @time:2017年8月21日 下午7:34:21

 */

@Api(description="系统管理-系统设置-范围和资质服务接口")
@RestController
@RequestMapping("/system/set/userQualification")
public class UserQualificationController {
	
	private static final Log logger = LogFactory.getLog(UserQualificationController.class);
	
	@Autowired
	private ScopeQualificationService scopeQualificationService;
	
	@ApiOperation(value="查看员工资质信息", notes = "根据当前登录用户查看员工资质信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getUserQualification", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<UserQualification>> getUserQualification(HttpServletRequest request){
		Result<List<UserQualification>> result = new Result<List<UserQualification>>();
        try{
        	HttpSession session = request.getSession();
    		UserVO user = (UserVO) session.getAttribute("user");
    		if (user == null) {
    			user = new UserVO();
    		}
    		List<UserQualification> userQualification = scopeQualificationService.getUserQualification(user);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, userQualification);
        }catch(Exception e){
        	logger.error("查看员工资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="修改员工资质信息", notes = "根据当前登录用户修改员工资质信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "userQualification", value = "修改员工资质", required = true, dataType = "UserQualification")
	@RequestMapping(value = "/updateUserQualification", method = RequestMethod.POST)
	@ResponseBody
	public Result<UserQualification> updateUserQualification(HttpServletRequest request,@RequestBody UserQualification userQualification){
		Result<UserQualification> result = new Result<UserQualification>();
        try{
        	HttpSession session = request.getSession();
    		UserVO user = (UserVO) session.getAttribute("user");
    		if (user == null) {
    			user = new UserVO();
    		}
        	scopeQualificationService.updateUserQualification(userQualification,user);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
			logger.error("修改员工资质信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("修改员工资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="增加员工资质信息", notes = "根据当前登录用户增加员工资质信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "userQualification", value = "增加企业资质", required = true, dataType = "UserQualification")
	@RequestMapping(value = "/addUserQualification", method = RequestMethod.POST)
	@ResponseBody
	public Result<UserQualification> addUserQualification(HttpServletRequest request,@RequestBody UserQualification userQualification){
		Result<UserQualification> result = new Result<UserQualification>();
        try{
        	HttpSession session = request.getSession();
    		UserVO user = (UserVO) session.getAttribute("user");
    		if (user == null) {
    			user = new UserVO();
    		}
        	scopeQualificationService.addUserQualification(userQualification,user);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
			logger.error("增加员工资质信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("增加员工资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="删除员工资质信息", notes = "根据当前登录用户删除员工资质信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "id", value = "删除员工资质", required = true, dataType = "Long",paramType="path")
	@RequestMapping(value = "/deleteUserQualification/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<UserQualification> deleteUserQualification(HttpServletRequest request,@PathVariable Long id){
		Result<UserQualification> result = new Result<UserQualification>();
        try{
        	scopeQualificationService.deleteUserQualification(id);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		}catch (BusinessException e) {
			logger.error("删除员工资质信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
        }catch(Exception e){
        	logger.error("删除员工资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}

}
