package com.rograndec.feijiayun.chain.business.basic.store.controller;

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
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;



@Api(value = "storeQualificationConfig", description = "门店管理-门店信息-门店资质服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/storeQualificationConfig")
public class StoreQualificationConfigController {
	
	private static final Log logger = LogFactory.getLog(StoreQualificationConfigController.class);

	@Autowired
	EnterpriseQualificationConfigService enterpriseQualificationConfigService;
	
	@Autowired
	EnterpriseService enterpriseService;
	
	@ApiOperation(value="获取门店资质信息", notes = "获取门店资质信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStoreQualificationConfig", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<EnterpriseQualificationConfigVO>> getStoreQualificationConfig(HttpServletRequest request,
			@ApiParam(value = "门店ID", required = true) @RequestParam Long enterpriseId){
		Result<List<EnterpriseQualificationConfigVO>> result = new Result<List<EnterpriseQualificationConfigVO>>();
        try{
        	
        	if(enterpriseId == 0){
        		HttpSession session = request.getSession(true);
            	UserVO loginUser = (UserVO) session.getAttribute("user");
            	if(loginUser != null){
            		enterpriseId = loginUser.getEnterpriseId();
            	}
        	}
        	
        	Enterprise enterprise = enterpriseService.queryEnterpriseById(enterpriseId, "enterprise");
        	if(enterprise == null){
        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, null);
        		return result;
        	}
        	
        	List<EnterpriseQualificationConfigVO> list  = enterpriseQualificationConfigService
        			.selectEnterpriseQualificationByEnterpriseId(enterprise);
        	if(list != null && list.size() > 0){
        		result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	}else{
        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, null);
        	}
        }catch(Exception e){
        	logger.error("获取企业资质详细信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	
	@ApiOperation(value="获取门店所有可选的资质信息", notes = "获取门店所有可选的资质信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEntrepriseOptionalQualification", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getEntrepriseOptionalQualification(HttpServletRequest request,
			@ApiParam(value = "门店ID", required = true) @RequestParam Long enterpriseId){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	
        	if(enterpriseId == 0){
        		HttpSession session = request.getSession(true);
            	UserVO loginUser = (UserVO) session.getAttribute("user");
            	if(loginUser != null){
            		enterpriseId = loginUser.getEnterpriseId();
            	}
        	}
        	
        	//这里应该有系统默认存在资质以及手动新增为必选的资质。
        	Enterprise enterprise = enterpriseService.queryEnterpriseById(enterpriseId, "enterprise");
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
	
	
	@ApiOperation(value="新增门店-保存企业资质信息 ", notes = "保存企业资质信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/saveStoreEnterpriseQualificationConfig", method = RequestMethod.POST)
	public Result<Object> saveStoreEnterpriseQualificationConfig(HttpServletRequest request,
			@ApiParam(value = "企业资质信息", required = true) @RequestBody EnterpriseQualificationConfigBean config) {
		Result<Object> result = new Result<Object>();
		try{
			//资质哪些字段是否必输取决于是否开启了基础信息质量管理开关以及资质本身的属性来控制。
			//保存修改记录
			HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	Enterprise enterprise = enterpriseService.queryEnterpriseById(loginUser.getEnterpriseId(), "enterprise");
        	
        	String msg = enterpriseQualificationConfigService.validateEnterpriseQualificationConfig(config, enterprise, loginUser);
        	
        	if(StringUtils.isNotBlank(msg)){
				result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS, msg);
				return result;
			}
        	
        	EnterpriseQualificationConfigVO vo = enterpriseQualificationConfigService.transformationConfigToVo(config);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
		}catch(Exception e){
			logger.error("保存企业资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value="修改门店-保存企业资质信息 ", notes = "修改门店-保存企业资质信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/updateStoreEnterpriseQualificationConfig", method = RequestMethod.POST)
	public Result<String> updateStoreEnterpriseQualificationConfig(HttpServletRequest request,
			@ApiParam(value = "企业资质信息", required = true) @RequestBody EnterpriseQualificationConfigBean config) {
		Result<String> result = new Result<String>();
		try{
			//资质哪些字段是否必输取决于是否开启了基础信息质量管理开关以及资质本身的属性来控制。
			//保存修改记录
			HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	Enterprise enterprise = enterpriseService.queryEnterpriseById(config.getEnterpriseId(), "enterprise");
        	if(enterprise == null){
        		result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS, "企业ID错误！");
        		return result;
        	}
        	
        	String msg = enterpriseQualificationConfigService.saveOrUpdate(config, enterprise, loginUser);
        	if(StringUtils.isNotBlank(msg)){
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, msg);
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
			
			if((StringUtils.isBlank(id)||"0".equals(id))){
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,"");
				return result;
			}
			
			HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
			
			int count = enterpriseQualificationConfigService.deleteEnterpriseQualificationConfig(id, loginUser);
			if(count == 1){
				result.setBizCodeSuccessInfo(SysCode.SUCCESS,"");
			}else if(count == 2){
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,"");
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
