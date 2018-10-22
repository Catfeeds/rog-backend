package com.rograndec.feijiayun.chain.business.system.enterprise.controller;

import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseBusinessVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EntrepriseValidateVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.UserComponent;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.EconomyType;
import com.rograndec.feijiayun.chain.common.constant.ModeOperation;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;


@Api(value = "enterprise", description = "系统管理-企业信息-企业基本信息服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/enterprise")
@Validated
public class EnterpriseController {
	
	private static final Log logger = LogFactory.getLog(EnterpriseController.class);

	@Autowired
	EnterpriseService enterpriseService;

	@Autowired
	UserComponent userComponent;
	
	@ApiOperation(value="获取企业基本信息", notes = "获取企业基本信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEnterprise", method = RequestMethod.POST)
	@ResponseBody
	public Result<Enterprise> getEnterprise(HttpServletRequest request){
		Result<Enterprise> result = new Result<Enterprise>();
        try{
        	
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");

        	Enterprise enterprise = enterpriseService.queryEnterpriseById(loginUser.getEnterpriseId(), "enterprise");
        	
        	enterprise = enterpriseService.queryEnterpriseAuxiliary(enterprise);//获取辅助信息
        	
        	if(enterprise == null){
        		
        		result.setBizCodeSuccessInfo(SysCode.FAIL, enterprise);
        		
        		return result;
        	}
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, enterprise);
        }catch(Exception e){
        	logger.error("获取企业基本信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取企业基本信息验证接口", notes = "获取企业基本信息验证接口 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEntrepriseValidate", method = RequestMethod.POST)
	@ResponseBody
	public Result<EntrepriseValidateVO> getEntrepriseValidate(HttpServletRequest request){
		Result<EntrepriseValidateVO> result = new Result<EntrepriseValidateVO>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	//这里应该有系统默认存在资质以及手动新增为必选的资质。  saas_manage_config  saas_entreprise_qualification
        	EntrepriseValidateVO bean = enterpriseService
        			.queryEnterpriseValidateByEnterpriseId(loginUser.getEnterpriseId());
        	if(bean == null){
        		
        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, bean);
        		return result;
        	}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, bean);
        }catch(Exception e){
        	logger.error("获取企业基本信息验证接口错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="保存企业信息 ", notes = "保存企业信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/saveEnterprise", method = RequestMethod.POST)
	public Result<String> saveEnterprise(HttpServletRequest request,
			@RequestBody EnterpriseVO enterpriseVO,
			@ApiParam(value = "修改原因", required = true) @RequestParam String reason) {
		Result<String> result = new Result<String>();
		try{
			HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");

        	EnterpriseBusinessVO enterpriseBusinessVO = enterpriseVO.getEnterpriseBusinessVO();
        	
			String msg = enterpriseService.saveEnterprise(enterpriseVO, enterpriseBusinessVO, loginUser, reason);
			
			if(StringUtils.isNotBlank(msg)){
				result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), msg, "");
				return result;
			}
			
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,"");
		}catch(Exception e){
			logger.error("保存企业信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value="根据经营品种获取企业经营范围信息", notes = "根据经营品种获取企业经营范围信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getBusinessScope", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<BusinessScope>> getBusinessScope(HttpServletRequest request,
			@ApiParam(value = "品种类别以,隔开", required = true) @RequestParam String businessVariety){
		Result<List<BusinessScope>> result = new Result<List<BusinessScope>>();
        try{
        	/*if(StringUtils.isBlank(businessVariety)){
        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, null);
        		return result;
        	}*/
        	//如果不传参数,不要报错--传给前台空串
			if(StringUtils.isBlank(businessVariety)){
				result.setBizCodeSuccessInfo(SysCode.SUCCESS, new ArrayList<>());
			}
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");

			List<BusinessScope> scopeList = enterpriseService.queryBusinessScopeByBusinessVariety(loginUser,businessVariety);
        	
        	/*if(scopeList == null || scopeList.size() == 0){
        		
        		result.setBizCodeSuccessInfo(SysCode.BIS_MEMBER_LEVE_ID_NOT_FIND, scopeList);
        		return result;
        	}*/
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, scopeList);
        }catch(Exception e){
        	logger.error("品种类别以,隔开:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}


	@ApiOperation(value="获取经营品种信息", notes = "获取经营品种信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getBusinessVariety", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getBusinessVariety(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	for (BusinessVariety s : BusinessVariety.values()){  
        		SelectBean bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getCode()));
        		bean.setText(String.valueOf(s.getName()));
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取经营品种信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取经济类型信息", notes = "获取经济类型信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEconomicType", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getEconomicType(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	for (EconomyType s : EconomyType.values()){  
        		SelectBean bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getCode()));
        		bean.setText(String.valueOf(s.getName()));
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取经营品种信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取经营方式信息", notes = "获取经营方式信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getBusinessMode", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getBusinessMode(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	for (ModeOperation s : ModeOperation.values()){  
        		SelectBean bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getCode()));
        		bean.setText(String.valueOf(s.getName()));
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取经营品种信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
}
