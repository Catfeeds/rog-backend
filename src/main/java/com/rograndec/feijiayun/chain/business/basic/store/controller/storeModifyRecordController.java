package com.rograndec.feijiayun.chain.business.basic.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseModifyRecordWithBLOBs;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseModifyRecordService;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;



@Api(value = "storeModifyRecord", description = "基础资料-门店管理-门店信息-门店修改记录服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/storeModifyRecord")
public class storeModifyRecordController {
	
	private static final Log logger = LogFactory.getLog(storeModifyRecordController.class);

	@Autowired
	EnterpriseModifyRecordService enterpriseModifyRecordService;
	
	@Autowired
	EnterpriseService enterpriseService;
	
	@ApiOperation(value="获取企业修改记录", notes = "获取企业修改记录 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEnterpriseModifyRecord", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page> getEnterpriseModifyRecord(HttpServletRequest request,
			@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
			@ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
			@ApiParam(value = "门店ID", required = true) @RequestParam Long enterpriseId){
		Result<Page> result = new Result<Page>();
        try{
        	if(pageNo <= 0 || pageSize <= 0){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	Enterprise enterprise = enterpriseService.queryEnterpriseById(enterpriseId, "enterprise");
        	if(enterprise == null || enterprise.getParentId() == null 
        			|| !enterprise.getParentId().equals(loginUser.getEnterpriseId())){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	
        	Page page = new Page(pageNo, pageSize);
        	
        	List<EnterpriseModifyRecordWithBLOBs> modifyRecordList = enterpriseModifyRecordService
        			.selectEnterpriseModifyRecordPage(pageNo, pageSize, enterpriseId, page);
        	page.setResult(modifyRecordList);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("获取企业修改记录信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}

	
}
