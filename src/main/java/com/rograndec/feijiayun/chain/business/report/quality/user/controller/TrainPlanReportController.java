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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.report.quality.user.service.TrainPlanReportService;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.RequestTraimPlanVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseTrainPlanVO;
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
@Api(value = "report_quality_user_trainPlan",description = "报表-质量管理-人员与培训-培训计划")
@RestController
@RequestMapping("report/quality/user/trainPlan")
@Validated
public class TrainPlanReportController {

	 private static final Log logger = LogFactory.getLog(TrainPlanReportController.class);
	 
	 @Autowired
	 TrainPlanReportService trainPlanReportService;
	 
	 
	    @ApiOperation(value = "分页获取培训计划或培训记录列表信息", notes = "分页获取培训计划列表信息 | 开发者 张东东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(value = "/getTrainPlanOrRecordReport", method = RequestMethod.GET)
	    @ResponseBody
	    public Result<Page<List<ResponseTrainPlanVO>>> getTrainPlanOrRecordReport(HttpServletRequest request,@ApiIgnore UserVO userVO,
	      @ApiParam(value = "获取培训计划或记录的标识       0-为培训计划  1-为培训记录", required = true) @RequestParam Integer sign,
          @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
          @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
          @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
          @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
          @ApiParam(value = "组织结构类型", required = false) @RequestParam(required = false) Integer chainType,
	      @ApiParam(value = "组织机构编码", required = false) @RequestParam(required = false) String enterpriseCode,
	      @ApiParam(value = "组织结构名称", required = false) @RequestParam(required = false) String enterpriseName,
          @ApiParam(value = "计划编号", required = false) @RequestParam(required = false) String code,
          @ApiParam(value = "计划人员", required = false) @RequestParam(required = false) String plannerName,
          @ApiParam(value = "计划年度", required = false) @RequestParam(required = false) Integer planYear,
          @ApiParam(value = "计划标题", required = false) @RequestParam(required = false) String planTitle,
          @ApiParam(value = "培训类型（0-岗前培训；1-继续培训）", required = false) @RequestParam(required = false) Integer trainType,
          @ApiParam(value = "培训内容（0-法律法规；1-药品专业知识及技能；2-质量管理制度；3-职责及岗位操作规程）", required = false) @RequestParam(required = false) Integer trainContent,
          @ApiParam(value = "按组织机构编码排序    0-倒序，1-顺序,默认为0（门店为0即可）", required = false) @RequestParam(required = false) Integer enterpriseCodeOrder,
          @ApiParam(value = "按时间排序  0-倒序，1-顺序,默认为0", required = false) @RequestParam(required = false) Integer planDateOrder) 
	  {
	        Result<Page<List<ResponseTrainPlanVO>>> result = new Result<>();
	        enterpriseCodeOrder=enterpriseCodeOrder==null?0:enterpriseCodeOrder;
	        planDateOrder=planDateOrder==null?0:planDateOrder;
	        startTime=StartAndEndDate.getStartTime(startTime);
        	endTime=StartAndEndDate.getEndTime(endTime);
	        try {
	            if (pageNo <= 0 || pageSize <= 0) {
	                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
	                return result;
	            }
	            Page<List<ResponseTrainPlanVO>> page = new Page<>(pageNo, pageSize);
	           /* HttpSession session = request.getSession(true);
	            UserVO user = (UserVO) session.getAttribute("user");*/
	            trainPlanReportService.getTrainPlanReport(userVO.getEnterpriseId(),
	            		startTime,endTime, chainType, enterpriseCode, enterpriseName, code, plannerName, planYear, planTitle,
	            		trainType, trainContent, enterpriseCodeOrder, planDateOrder, page,userVO,sign);
	            result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
	        } catch (Exception e) {
	            logger.error("分页获取培训计划列表信息错误:" + e.getMessage(), e);
	            result.setBizCodeSuccessInfo(SysCode.FAIL);
	            return result;
	        }
	        return result;
	    }
	    
	 /*   @ApiOperation(value = "按照模版将培训计划导出至Excel", notes = "按照模版将培训计划导出至Excel | 开发者 张东东 | 已完成")
	    @RequestMapping(value="/exportPlanExcel", method=RequestMethod.GET)
	    public void exportPlanExcel( HttpServletResponse response,HttpSession session,
             @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
             @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
             @ApiParam(value = "组织结构类型", required = false) @RequestParam(required = false) Integer chainType,
	   	     @ApiParam(value = "组织机构编码", required = false) @RequestParam(required = false) String enterpriseCode,
	   	     @ApiParam(value = "组织结构名称", required = false) @RequestParam(required = false) String enterpriseName,
             @ApiParam(value = "计划编号", required = false) @RequestParam(required = false) String code,
             @ApiParam(value = "计划人员", required = false) @RequestParam(required = false) String plannerName,
             @ApiParam(value = "计划年度", required = false) @RequestParam(required = false) Long planYear,
             @ApiParam(value = "计划标题", required = false) @RequestParam(required = false) String planTitle,
             @ApiParam(value = "培训类型（0-岗前培训；1-继续培训）", required = false) @RequestParam(required = false) Long trainType,
             @ApiParam(value = "培训内容（0-法律法规；1-药品专业知识及技能；2-质量管理制度；3-职责及岗位操作规程）", required = false) @RequestParam(required = false) Long trainContent,
             @ApiParam(value = "按组织机构编码排序    0-倒序，1-顺序,默认为0", required = false) @RequestParam(required = false) Integer enterpriseCodeOrder,
             @ApiParam(value = "按时间排序  0-倒序，1-顺序,默认为0", required = false) @RequestParam(required = false) Integer planDateOrder){
			OutputStream output=null;
			try{
				enterpriseCodeOrder=enterpriseCodeOrder==null?0:enterpriseCodeOrder;
		        planDateOrder=planDateOrder==null?0:planDateOrder;
				UserVO user=(UserVO) session.getAttribute("user");
				output=response.getOutputStream();
				//这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
	            String name="培训计划";
				response.setContentType("application/octet-stream;charset=utf-8");
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
	            trainPlanReportService.exportPlanExcel(startTime, endTime, chainType,
	            		enterpriseCode, enterpriseName, code, plannerName, planYear, planTitle, trainType, 
	            		trainContent, enterpriseCodeOrder, planDateOrder, user,output);
			}catch(Exception e){
				logger.error("按照模版将培训计划导出至Excel异常:" + e.getMessage(), e);
			}
			
		}*/
	    
	    @ApiOperation(value = "按照模版将培训计划导出至Excel", notes = "按照模版将培训计划导出至Excel | 开发者 张东东 | 已联调")
	    @ApiImplicitParam(name = "requestTraimPlanVO", value = "检索条件", required = true, dataType = "RequestTraimPlanVO")
		@RequestMapping(value = "/exportPlanExcel", method = RequestMethod.POST,produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	    public void exportPlanExcel( HttpServletResponse response,@ApiIgnore UserVO userVO,HttpSession session,RequestTraimPlanVO requestTraimPlanVO){
			OutputStream output=null;
			try{
				requestTraimPlanVO.setStartTime(StartAndEndDate.getStartTime(requestTraimPlanVO.getStartTime()));
				requestTraimPlanVO.setEndTime(StartAndEndDate.getEndTime(requestTraimPlanVO.getEndTime()));
				/*UserVO user=(UserVO) session.getAttribute("user");*/
				output=response.getOutputStream();
				//这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
	            String name="培训计划";
				response.setContentType("application/octet-stream;charset=utf-8");
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
	            trainPlanReportService.exportPlanExcelNew(requestTraimPlanVO, userVO, output);
			}catch(Exception e){
				logger.error("按照模版将培训计划导出至Excel异常:" + e.getMessage(), e);
			}
			
		}
	    
}
