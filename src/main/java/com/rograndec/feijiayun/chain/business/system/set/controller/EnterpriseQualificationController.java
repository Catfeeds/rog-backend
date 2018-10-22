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
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import com.rograndec.feijiayun.chain.business.system.set.service.ScopeQualificationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**

 * @Description:系统管理-系统设置-范围和资质服务接口

 * @author:LeiSu

 * @time:2017年8月21日 下午7:29:39

 */

@Api(description="系统管理-系统设置-范围和资质服务接口")
@RestController
@RequestMapping("/system/set/enterpriseQualification")
public class EnterpriseQualificationController {

	private static final Log logger = LogFactory.getLog(EnterpriseQualificationController.class);
	
	@Autowired
	private ScopeQualificationService scopeQualificationService;
	
	@ApiOperation(value="查看企业资质信息", notes = "根据当前用户查看企业资质信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEnterpriseQualification", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<EnterpriseQualification>> getEnterpriseQualification(HttpServletRequest request){
		Result<List<EnterpriseQualification>> result = new Result<List<EnterpriseQualification>>();
        try{
        	HttpSession session = request.getSession();
    		UserVO user = (UserVO) session.getAttribute("user");
    		if (user == null) {
    			user = new UserVO();
    		}
    		List<EnterpriseQualification> enterpriseQualification = scopeQualificationService.getEnterpriseQualification(user);   	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, enterpriseQualification);
        }catch(Exception e){
        	logger.error("查看企业资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="修改企业资质信息", notes = "根据当前用户修改企业资质信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "enterpriseQualification", value = "修改企业资质", required = true, dataType = "EnterpriseQualification")
	@RequestMapping(value = "/updateEnterpriseQualification", method = RequestMethod.POST)
	@ResponseBody
	public Result<EnterpriseQualification> updateEnterpriseQualification(HttpServletRequest request,@RequestBody EnterpriseQualification enterpriseQualification){
		Result<EnterpriseQualification> result = new Result<EnterpriseQualification>();
        try{
        	HttpSession session = request.getSession();
    		UserVO user = (UserVO) session.getAttribute("user");
    		if (user == null) {
    			user = new UserVO();
    		}
        	scopeQualificationService.updateEnterpriseQualification(enterpriseQualification,user);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
			logger.error("修改企业资质信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("修改企业资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="增加企业资质信息", notes = "根据当前用户增加企业资质信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "enterpriseQualification", value = "增加企业资质", required = true, dataType = "EnterpriseQualification")
	@RequestMapping(value = "/addEnterpriseQualification", method = RequestMethod.POST)
	@ResponseBody
	public Result<EnterpriseQualification> addEnterpriseQualification(HttpServletRequest request,@RequestBody EnterpriseQualification enterpriseQualification){
		Result<EnterpriseQualification> result = new Result<EnterpriseQualification>();
        try{
        	HttpSession session = request.getSession();
			UserVO user = (UserVO) session.getAttribute("user");
    		if (user == null) {
    			user = new UserVO();
    		}
        	scopeQualificationService.addEnterpriseQualification(enterpriseQualification,user);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, enterpriseQualification);
        }catch (BusinessException e) {
			logger.error("增加企业资质信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("增加企业资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="删除企业资质信息", notes = "根据当前用户删除企业资质信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "id", value = "企业资质ID", required = true, dataType = "Long",paramType="path")
	@RequestMapping(value = "/deleteEnterpriseQualification/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<EnterpriseQualification> deleteEnterpriseQualification(HttpServletRequest request,@PathVariable Long id){
		Result<EnterpriseQualification> result = new Result<EnterpriseQualification>();
        try{
        	scopeQualificationService.deleteEnterpriseQualification(id);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		}catch (BusinessException e) {
			logger.error("删除企业资质信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
        }catch(Exception e){
        	logger.error("删除企业资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
}
