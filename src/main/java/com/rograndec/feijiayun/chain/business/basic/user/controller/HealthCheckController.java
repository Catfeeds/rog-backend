package com.rograndec.feijiayun.chain.business.basic.user.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.basic.user.service.HealthCheckService;
import com.rograndec.feijiayun.chain.business.basic.user.vo.HealthCheckUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.HealthCheckVO;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlan;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrReqPlanService;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.AnalyseStockVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.AnalysisVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrReqPlanVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.EnterpriseReqPlanVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.GoodsDistrReqPlanAnalysisVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.GoodsDistrReqPlanVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsDestroyService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.UserDestroyVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReqPlanStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author 孙帮祥
 *
 */
@Api(value = "basic_health_check",description = "基础资料-健康检查服务接口")
@RestController
@RequestMapping("basic/health/check")
@Validated
public class HealthCheckController {
	private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);
	@Autowired
	private GoodsDestroyService goodsDestroyService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private HealthCheckService healthCheckService;
	@ApiOperation(value = "查询计划人员列表", notes = "获取计划人员列表 | 开发者 孙帮祥 | 已联调")
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	public Result<List<UserDestroyVO>> getUserList(HttpSession session) {
		Result<List<UserDestroyVO>> result = new Result<List<UserDestroyVO>>();
	    UserVO userVO = (UserVO) session.getAttribute("user");
		try {
			Map map=new HashMap();
			map.put("enterpriseId", userVO.getEnterpriseId());
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsDestroyService.getUserList(map));
		} catch (Exception e) {
			logger.error("获取人员信息列表:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	 @ApiOperation(value = "查询员工信息列表", notes = "查询员工信息列表 | 开发者 孙帮祥 | 已联调")
	 @RequestMapping(value="/getEmpInfo",method= RequestMethod.GET)
	    public Result<List<HealthCheckUserVO>> getEmpInfo(HttpSession session,
														  @ApiParam(value = "组织机构ID", required = false) @RequestParam(required = false) Long enterpriseId
														  ){
	        Result<List<HealthCheckUserVO>> result = new Result<List<HealthCheckUserVO>>();
	        try{
	        List<HealthCheckUserVO> userList=healthCheckService.getUserMessage(enterpriseId);
	        result.setBizCodeSuccessInfo(SysCode.SUCCESS,userList);
			}catch (BusinessException e) {
				result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS,e.getMessage());
				logger.error("查询员工信息列表错误:"+e.getMessage(),e);
			}catch (Exception e) {
				result.setBizCodeFallInfo(SysCode.FAIL);
				logger.error("查询员工信息列表错误:"+e.getMessage(),e);
			}
	        return result;
	    }
	@ApiOperation(value = "保存健康检查单", notes = "保存健康检查单 | 开发者孙帮祥 | 已联调")
    @RequestMapping(value="/saveHealthCheck", method=RequestMethod.POST)
    public Result<Object> saveHealthCheck(HttpSession session,@ApiParam(value = "健康检查对象", required = true) @RequestBody HealthCheckVO healthCheckVO){
		 Result<Object> result = new Result<Object>();
	        UserVO userVO = (UserVO) session.getAttribute("user");
			Map<String, Object> map = new HashMap<>();
			try {
				   healthCheckService.save(userVO, healthCheckVO);
				   result.setBizCodeSuccessInfo(SysCode.SUCCESS);
			}catch (BusinessException e) {
				result.setBizCodeFallInfo("200100",e.getMessage());
				logger.error("健康检查单添加数据错误:"+e.getMessage(),e);
			}catch (Exception e) {
				result.setBizCodeFallInfo(SysCode.FAIL);
				logger.error("健康检查单添加数据错误:"+e.getMessage(),e);
			}
			return result;
	}
	@ApiOperation(value = "修改", notes = "修改要货计划单 | 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/updateHealthCheck", method=RequestMethod.POST)
	public Result<Object> updateHealthCheck(HttpSession session,@ApiParam(value = "健康检查对象", required = true) @RequestBody HealthCheckVO healthCheckVO){
		 Result<Object> result = new Result<Object>();
	        UserVO userVO = (UserVO) session.getAttribute("user");
			Map<String, Object> map = new HashMap<>();
			try {
				healthCheckService.update(userVO, healthCheckVO);
				   result.setBizCodeSuccessInfo(SysCode.SUCCESS);
			}catch (BusinessException e) {
				result.setBizCodeFallInfo("200100",e.getMessage());
				logger.error("健康检查单修改数据错误:"+e.getMessage(),e);
			}catch (Exception e) {
				result.setBizCodeFallInfo(SysCode.FAIL);
				logger.error("健康检查单修改数据错误:"+e.getMessage(),e);
			}
			return result;
	}
	
	@ApiOperation(value="查询健康检查单列表", notes = "查询健康检查单列表 | 开发者 孙帮祥|已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getHealthCheckList", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page<HealthCheckVO>> getHealthCheckList(HttpServletRequest request,
				@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
				@ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize, 
				@ApiParam(value = "开始日期", required = false) @RequestParam(required=false) Date startDate,
				@ApiParam(value = "结束日期", required = false) @RequestParam(required=false) Date endDate,
				@ApiParam(value = "检查编号", required = false) @RequestParam(required=false) String code,
				@ApiParam(value = "计划人员", required = false) @RequestParam(required=false) String planManName,
				@ApiParam(value = "检查年度", required = false) @RequestParam(required=false) String planYear,
				@ApiParam(value = "检查类型", required = false) @RequestParam(required=false) String checkType,
				@ApiParam(value = "排序字段", required = false) @RequestParam(required=false) String sortField,
				@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
	    	Result<Page<HealthCheckVO>> result = new Result<Page<HealthCheckVO>>();
	        try{
	        	if(pageNo <= 0 || pageSize <= 0){
	        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
	    			return result;
	        	}
	        	Page<HealthCheckVO> page = new Page<HealthCheckVO>(pageNo, pageSize);
	        	HttpSession session = request.getSession(true);
	        	UserVO loginUser = (UserVO) session.getAttribute("user");
	        	Long enterpriseId=loginUser.getEnterpriseId();
	        	if(sortField!=null){
	        		if(sortField.equals("planDate")){
	        			sortField="plan_date";
	        		}else if(sortField.equals("enterpriseCode")) {
	        			sortField = "enterprise_code";
	        		}else {
	        			sortField = null;
	        		}
	        	}
	        	if(pageNo==1){
	        		pageNo=0;
	        	}else{
	        		pageNo=pageNo-1;
	        		pageNo=pageNo*pageSize;
	        	}
	        	Map map=new HashMap();
	        	map.put("enterpriseId", enterpriseId);
	        	map.put("pageNo", pageNo);
	        	map.put("pageSize", pageSize);
	        	map.put("code", code);
	        	map.put("planManName", planManName);
	           	map.put("startDate", startDate);
	           	map.put("endDate", endDate);
	        	map.put("planYear", planYear);
	           	map.put("checkType", checkType);
	        	map.put("sortField", sortField);
	        	map.put("sort", sort);
	        	healthCheckService.getList(page, map);
	        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
	        }catch(Exception e){
	        	logger.error("健康检查列表分页查询错误:"+e.getMessage(),e);
				result.setBizCodeFallInfo(SysCode.FAIL);
				return result;
	        }
			return result;
		}
	@ApiOperation(value = "查询健康检查单明细", notes = "查询健康检查单明细 | 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/getHealthCheckDetail/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "健康检查单主键", required = true,paramType = "path")
    public Result<HealthCheckVO> getHealthCheckDetail(@PathVariable Long id){
        Result<HealthCheckVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,healthCheckService.getById(id));
        return result;
    }
	@ApiOperation(value = "删除健康检查单", notes = "删除健康检查单| 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/deleteHealthCheck/{id}", method=RequestMethod.GET)
	@ApiImplicitParam(name = "id", value = "要货计划主键", required = true,paramType = "path")
    public Result<String> deleteHealthCheck(HttpSession session,@PathVariable Long id){
	     Result<String> result = new Result<>();
	     DistrReqPlan plan=new DistrReqPlan();
		 UserVO userVO = (UserVO) session.getAttribute("user");
	     plan.setId(id);
	     plan.setStatus(DistrReqPlanStatus.CANCELED);
	     try {
	    	 healthCheckService.deleteByCheckId(id);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		} catch (Exception e) {
			result.setBizCodeFallInfo(SysCode.FAIL);
			e.printStackTrace();
		}
	     return result;
	}
	@ApiOperation(value = "导出Excel", notes = "按照模版将要货计划单导出至Excel | 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
    public void exportExcel(HttpSession session,HttpServletResponse response, @PathVariable("id") Long id) throws IOException{
		String name = "健康检查";
		// 输出Excel文件
		  UserVO userVO = (UserVO) session.getAttribute("user");
		OutputStream output = response.getOutputStream();
		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
		response.setContentType("application/octet-stream;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
		healthCheckService.exportExcel(output, id, userVO);
	}
}
