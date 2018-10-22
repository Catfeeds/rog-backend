package com.rograndec.feijiayun.chain.business.report.quality.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.report.quality.user.service.HealthCheckReportService;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseHealthCheckDetailVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseHealthCheckVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.StartAndEndDate;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_user_healthCheck",description = "报表-质量管理-人员与培训-健康检查")
@RestController
@RequestMapping("report/quality/user/healthCheck")
@Validated
public class HealthCheckReportController {
	
	private static final Log logger = LogFactory.getLog(HealthCheckReportController.class);
	@Autowired
	HealthCheckReportService healthCheckReportService;

	@ApiOperation(value="查询健康检查单列表", notes = "查询健康检查单列表 | 开发者 张东东| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getHealthCheckList", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page<List<ResponseHealthCheckVO>>> getHealthCheckReportList(HttpSession session,@ApiIgnore UserVO userVO,
		@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
		@ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize, 
		@ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
        @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
		@ApiParam(value = "组织结构类型", required = false) @RequestParam(required = false) Integer chainType,
	    @ApiParam(value = "组织机构编码", required = false) @RequestParam(required = false) String enterpriseCode,
	    @ApiParam(value = "组织结构名称", required = false) @RequestParam(required = false) String enterpriseName,
		@ApiParam(value = "检查编号", required = false) @RequestParam(required=false) String code,
		@ApiParam(value = "计划人员", required = false) @RequestParam(required=false) String planManName,
		@ApiParam(value = "检查年度", required = false) @RequestParam(required=false) String planYear,
		@ApiParam(value = "检查类型（0-岗前检查；1-年度检查）", required = false) @RequestParam(required=false) String checkType,
		@ApiParam(value = "按组织机构编码排序    0-倒序，1-顺序,默认为0（门店为0即可）", required = false) @RequestParam(required = false) Integer enterpriseCodeOrder,
        @ApiParam(value = "按时间排序  0-倒序，1-顺序,默认为0", required = false) @RequestParam(required = false) Integer planDateOrder){
	    	Result<Page<List<ResponseHealthCheckVO>>> result = new Result<>();
	        try{
	        	if(pageNo <= 0 || pageSize <= 0){
	        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
	    			return result;
	        	}
	        	startTime=StartAndEndDate.getStartTime(startTime);
	        	endTime=StartAndEndDate.getEndTime(endTime);
	        	System.out.println(startTime);
	        	System.out.println(endTime);
	        	enterpriseCodeOrder=enterpriseCodeOrder==null?0:enterpriseCodeOrder;
		        planDateOrder=planDateOrder==null?0:planDateOrder;
	        	Page<List<ResponseHealthCheckVO>> page = new Page<>(pageNo, pageSize);
	        	/*UserVO user=(UserVO) session.getAttribute("user");*/
	        	page.setTotalRecord(healthCheckReportService.getHealthCheckReportListTotalNum(chainType,
	        			enterpriseCode, enterpriseName, code, planManName, planYear, checkType, enterpriseCodeOrder, 
	        			planDateOrder, userVO, startTime, endTime));
	        	page.setResult(healthCheckReportService.getHealthCheckReportList(page.getStart(), pageSize, chainType,
	        			enterpriseCode, enterpriseName, code, planManName, planYear, checkType, enterpriseCodeOrder,
	        			planDateOrder, userVO,startTime,endTime));
	        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
	        	return result;
	        }catch(Exception e){
	        	logger.error("查询健康检查单列表异常:"+e.getMessage(),e);
				result.setBizCodeFallInfo(SysCode.FAIL);
				return result;
	        }
		}
	
	@ApiOperation(value = "查询健康检查明细列表", notes = "根据培训记录ID查询健康检查明细列表 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getHealthCheckReportDetlList", method=RequestMethod.GET)
    public Result<List<ResponseHealthCheckDetailVO>> getHealthCheckReportDetlList(HttpSession session,@ApiIgnore UserVO userVO,
    		/* @ApiParam(value = "页码", required = true) @RequestParam Integer pageNo,
             @ApiParam(value = "每页显示的记录数", required = true) @RequestParam Integer pageSize,*/
             @ApiParam(value = "健康检查记录id", required = true) @RequestParam Long id){
		Result<List<ResponseHealthCheckDetailVO>> result = new Result<>();
		try{
			/*UserVO user=(UserVO) session.getAttribute("user");*/
			//Page<List<ResponseHealthCheckDetailVO>> page = new Page<>(pageNo, pageSize);
			//page.setTotalRecord(healthCheckReportService.getHealthCheckReportDetlListTotalNum(id, user));
			List<ResponseHealthCheckDetailVO> list=healthCheckReportService.getHealthCheckReportDetlList(id,null,null,userVO);
		//	page.setResult(list);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
			return result;
		}catch(Exception e){
			logger.error("查询健康检查明细列表异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "按照模版将健康检查明细列表导出至Excel", notes = "按照模版将健康检查明细列表导出至Excel | 开发者 张东东 | 已联调")
	@ApiImplicitParam(name = "id", value = "健康检查记录id", required = true, paramType = "path")
    @RequestMapping(value="/exportHealthCheckReportDetlListExcel/{id}", method=RequestMethod.GET)
    public void exportHealthCheckReportDetlListExcel( HttpServletResponse response,@ApiIgnore UserVO userVO,HttpSession session,@PathVariable Long id){
		OutputStream output=null;
		try{
			/*UserVO user=(UserVO) session.getAttribute("user");*/
			output=response.getOutputStream();
			//这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name="健康检查";
			response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            healthCheckReportService.exportHealthCheckReportDetlListExcel(userVO,output,id);
		}catch(Exception e){
			logger.error("按照模版将健康检查明细列表导出至Excel异常:" + e.getMessage(), e);
		}
		
	}
}
