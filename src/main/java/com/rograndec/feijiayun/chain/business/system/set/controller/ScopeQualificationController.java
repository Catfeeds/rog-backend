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
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.business.system.set.service.ScopeQualificationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**

 * @Description:系统管理-系统设置-范围和资质服务接口

 * @author:LeiSu

 * @time:2017年8月21日 下午6:31:55

 */

@Api(description="系统管理-系统设置-范围和资质服务接口")
@RestController
@RequestMapping("/system/set/scopeQualification")
public class ScopeQualificationController {
	
	private static final Log logger = LogFactory.getLog(ScopeQualificationController.class);
	
	@Autowired
	private ScopeQualificationService scopeQualificationService;
	
	@ApiOperation(value="查看经营范围信息", notes = "根据当前登录用户对应的企业ID查看经营范围信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getScopeQualification", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<BusinessScope>> getScopeQualification(HttpServletRequest request,@ApiIgnore UserVO userVO){
		Result<List<BusinessScope>> result = new Result<List<BusinessScope>>();
        try{
        	List<BusinessScope> businessScope = scopeQualificationService.getScopeQualification(userVO);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, businessScope);
        }catch(Exception e){
        	logger.error("查看经营范围信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="修改经营范围信息", notes = "根据当前登录用户对应的企业ID修改经营范围信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "businessScope", value = "修改经营范围", required = true, dataType = "BusinessScope")
	@RequestMapping(value = "/updateQualityUnqualified", method = RequestMethod.POST)
	@ResponseBody
	public Result<BusinessScope> updateQualityUnqualified(HttpServletRequest request,@RequestBody BusinessScope businessScope,@ApiIgnore UserVO userVO){
		Result<BusinessScope> result = new Result<BusinessScope>();
        try{
        	scopeQualificationService.updateQualityUnqualified(businessScope,userVO);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
			logger.error("修改经营范围信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("修改经营范围信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="增加经营范围信息", notes = "根据当前登录用户对应的企业ID增加经营范围信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "businessScope", value = "增加经营范围信息", required = true, dataType = "BusinessScope")
	@RequestMapping(value = "/addQualityUnqualified", method = RequestMethod.POST)
	@ResponseBody
	public Result<BusinessScope> addQualityUnqualified(HttpServletRequest request,@RequestBody BusinessScope businessScope,@ApiIgnore UserVO userVO){
		Result<BusinessScope> result = new Result<BusinessScope>();
        try{
        	scopeQualificationService.addQualityUnqualified(businessScope,userVO);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
			logger.error("增加经营范围信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("增加经营范围信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="删除经营范围信息", notes = "根据当前登录用户对应的企业ID删除经营范围信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "id", value = "删除经营范围信息", required = true, dataType = "Long", paramType="path")
	@RequestMapping(value = "/deleteQualityUnqualified/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result<BusinessScope> deleteQualityUnqualified(HttpServletRequest request,@PathVariable Long id){
		Result<BusinessScope> result = new Result<BusinessScope>();
        try{
        	scopeQualificationService.deleteQualityUnqualified(id);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(Exception e){
        	logger.error("删除经营范围信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}

}
