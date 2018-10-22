package com.rograndec.feijiayun.chain.business.system.enterprise.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;



@Api(value = "enterpriseBusiness", description = "系统管理-企业信息-企业业务信息服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/enterpriseBusiness")
public class EnterpriseBusinessController {
	
	private static final Log logger = LogFactory.getLog(EnterpriseBusinessController.class);

	@Autowired
	EnterpriseBusinessService enterpriseBusinessService;
	
	@Autowired
	EnterpriseService enterpriseService;
	
	@ApiOperation(value="获取企业业务信息", notes = "获取企业业务信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEnterpriseBusiness", method = RequestMethod.POST)
	@ResponseBody
	public Result<EnterpriseBusiness> getEnterpriseBusiness(HttpServletRequest request){
		Result<EnterpriseBusiness> result = new Result<EnterpriseBusiness>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	/* 总部无企业业务信息验证
        	 * Enterprise enterprise = enterpriseService.queryEnterpriseById(loginUser.getEnterpriseId(), "enterprise");
        	if(enterprise == null){
        		
        		result.setBizCodeSuccessInfo(SysCode.FAIL, null);
        		return result;
        	}
        	
        	if(enterprise.getParentId() == null || enterprise.getParentId() == 0){
        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, null);
        		return result;
        	}*/
        	
        	EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, enterpriseBus);
        }catch(Exception e){
        	logger.error("获取企业业务信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
}
