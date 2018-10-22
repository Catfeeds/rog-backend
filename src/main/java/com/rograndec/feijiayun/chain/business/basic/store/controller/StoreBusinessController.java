package com.rograndec.feijiayun.chain.business.basic.store.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreEnterpriseBusinessResponseVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;

@Api(value = "storeBusiness", description = "门店管理-门店信息-门店业务信息服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/storeBusiness")
public class StoreBusinessController {

	private static final Log logger = LogFactory.getLog(StoreBusinessController.class);
	
	@Autowired
	EnterpriseBusinessService enterpriseBusinessService;
	
	@ApiOperation(value="获取门店业务信息", notes = "获取门店业务信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEnterpriseBusiness", method = RequestMethod.POST)
	@ResponseBody
	public Result<StoreEnterpriseBusinessResponseVO> getEnterpriseBusiness(HttpServletRequest request,
			@ApiParam(value = "门店ID", required = true) @RequestParam Long enterpriseId){
		Result<StoreEnterpriseBusinessResponseVO> result = new Result<StoreEnterpriseBusinessResponseVO>();
        try{
        	
        	/* 总部无企业业务信息验证
        	 * Enterprise enterprise = enterpriseService.queryEnterpriseById(enterpriseId, "enterprise");
        	if(enterprise == null){
        		
        		result.setBizCodeSuccessInfo(SysCode.FAIL, null);
        		return result;
        	}
        	
        	if(enterprise.getParentId() == null || enterprise.getParentId() == 0){
        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, null);
        		return result;
        	}*/
        	
        	EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
        	
        	StoreEnterpriseBusinessResponseVO vo = enterpriseBusinessService.transfromationResponseEnterpriseBusiness(enterpriseBus);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
        	logger.error("获取门店业务信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
}
