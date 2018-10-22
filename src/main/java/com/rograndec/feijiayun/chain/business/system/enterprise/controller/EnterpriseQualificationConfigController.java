package com.rograndec.feijiayun.chain.business.system.enterprise.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseQualificationConfigService;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseQualificationConfigBean;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseQualificationConfigVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.QualificationValidateVO;
import com.rograndec.feijiayun.chain.business.system.set.service.ScopeQualificationService;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;



@Api(value = "enterpriseQualificationConfig", description = "系统管理-企业信息-企业资质服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/enterpriseQualificationConfig")
public class EnterpriseQualificationConfigController {
	
	private static final Log logger = LogFactory.getLog(EnterpriseQualificationConfigController.class);

	@Autowired
	EnterpriseQualificationConfigService enterpriseQualificationConfigService;
	
	@Autowired
	EnterpriseService enterpriseService;

	@Autowired
	ScopeQualificationService scopeQualificationService;


	@ApiOperation(value="获取企业资质信息", notes = "获取企业资质信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEnterpriseQualificationConfig", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<EnterpriseQualificationConfigVO>> getEnterpriseQualificationConfig(HttpServletRequest request){
		Result<List<EnterpriseQualificationConfigVO>> result = new Result<List<EnterpriseQualificationConfigVO>>();
        try{
        	
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	Enterprise enterprise = enterpriseService.queryEnterpriseById(loginUser.getEnterpriseId(), "enterprise");
        	if(enterprise == null){
        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, null);
        		return result;
        	}
        	
        	List<EnterpriseQualificationConfigVO> list  = enterpriseQualificationConfigService
        			.selectEnterpriseQualificationByEnterpriseId(enterprise);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取企业资质详细信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取企业资质验证信息", notes = "获取企业资质验证信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEntrepriseQualificationValidate", method = RequestMethod.GET)
	@ResponseBody
	public Result<QualificationValidateVO> getEntrepriseQualificationValidate(HttpServletRequest request,
																			  @ApiParam(value = "资质ID", required = true) @RequestParam String qualificationId,
																			  @ApiParam(value = "资质类型（员工资质传user企业资质传enterprise）", required = true) @RequestParam String type){
		Result<QualificationValidateVO> result = new Result<QualificationValidateVO>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	if(loginUser == null){
        		loginUser = new UserVO();
        		loginUser.setEnterpriseId(1L);
        	}
        	//这里应该有系统默认存在资质以及手动新增为必选的资质。  saas_manage_config  saas_entreprise_qualification
			QualificationValidateVO bean = new QualificationValidateVO();
			if ("user".equals(type)){
				bean = scopeQualificationService
						.queryUserQualificationValidateByEnterpriseIdAndId(loginUser.getEnterpriseId(), qualificationId);
			}else if ("enterprise".equals(type)){
				bean = enterpriseQualificationConfigService
						.queryEnterpriseQualificationValidateByEnterpriseIdAndId(loginUser.getEnterpriseId(), qualificationId);
			}


        	if(bean == null){
        		
        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, bean);
        		return result;
        	}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, bean);
        }catch(Exception e){
			logger.error("获取企业资质验证信息:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value="获取企业所有可选的资质信息-资质描述下拉", notes = "获取企业所有可选的资质信息-资质描述下拉 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEntrepriseOptionalQualification", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getEntrepriseOptionalQualification(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	if(loginUser == null){
        		loginUser = new UserVO();
        		loginUser.setEnterpriseId(1L);
        	}
        	
        	//这里应该有系统默认存在资质以及手动新增为必选的资质。
        	Enterprise enterprise = enterpriseService.queryEnterpriseById(loginUser.getEnterpriseId(), "enterprise");
        	if(enterprise == null){
        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, null);
        		return result;
        	}
        	
        	List<SelectBean> enQualificationList = enterpriseQualificationConfigService.selectEntrepriseOptionalQualification(enterprise);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, enQualificationList);
        }catch(Exception e){
        	logger.error("获取企业所有可选的资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="保存企业资质信息 ", notes = "保存企业资质信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/saveEnterpriseQualificationConfig", method = RequestMethod.POST)
	public Result<String> saveEnterpriseQualificationConfig(HttpServletRequest request,
			@ApiParam(value = "企业资质信息", required = true) @RequestBody EnterpriseQualificationConfigBean config) {
		Result<String> result = new Result<String>();
		try{
			//资质哪些字段是否必输取决于是否开启了基础信息质量管理开关以及资质本身的属性来控制。
			//保存修改记录
			HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	Enterprise enterprise = enterpriseService.queryEnterpriseById(loginUser.getEnterpriseId(), "enterprise");
        	if(!config.getEnterpriseId().equals(enterprise.getEnterpriseId()) || 
        			!config.getParentId().equals(enterprise.getParentId())){
        		result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), "企业ID或父级ID错误！", "");
        		return result;
        	}
        	
        	String msg = enterpriseQualificationConfigService.saveOrUpdate(config, enterprise, loginUser);
			if(StringUtils.isNotBlank(msg)){
				result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), msg, "");
			}else{
				result.setBizCodeSuccessInfo(SysCode.SUCCESS, "");
			}
		}catch(Exception e){
			logger.error("保存企业资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	
	@ApiOperation(value="删除企业资质信息 ", notes = "删除企业资质信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/deleteEnterpriseQualificationConfig", method = RequestMethod.POST)
	public Result<String> deleteEnterpriseQualificationConfig(HttpServletRequest request,
			@ApiParam(value = "企业资质配置ID", required = true) @RequestParam String id) {
		Result<String> result = new Result<String>();
		try{
			
			if((StringUtils.isBlank(id))){
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
				return result;
			}
			if("0".equals(id)){
				result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), "该资质必填！", "");
				return result;
			}
			
			HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
			
			int count = enterpriseQualificationConfigService.deleteEnterpriseQualificationConfig(id, loginUser);
			if(count == 1){
				result.setBizCodeSuccessInfo(SysCode.SUCCESS,"");
			}else {
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,"");
			}
		}catch(Exception e){
			logger.error("删除企业资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
}
