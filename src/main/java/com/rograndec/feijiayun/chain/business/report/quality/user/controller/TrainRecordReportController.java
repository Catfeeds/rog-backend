package com.rograndec.feijiayun.chain.business.report.quality.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.report.quality.user.service.TrainPlanReportService;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseTrainPlanDetailVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_user_trainRecord",description = "报表-质量管理-人员与培训-培训记录")
@RestController
@RequestMapping("report/quality/user/trainRecord")
@Validated
public class TrainRecordReportController {

 private static final Log logger = LogFactory.getLog(TrainPlanReportController.class);
	 
	 @Autowired
	 TrainPlanReportService trainPlanReportService;
	 
	@ApiOperation(value = "查询培训记录明细列表", notes = "根据培训记录ID查询培训记录明细列表 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getTrainRecordReportDtlList", method=RequestMethod.GET)
    public Result<List<ResponseTrainPlanDetailVO>> getTrainRecordReportDtlList(HttpSession session,@ApiIgnore UserVO userVO,
    		/* @ApiParam(value = "页码", required = true) @RequestParam Integer pageNo,
             @ApiParam(value = "每页显示的记录数", required = true) @RequestParam Integer pageSize,*/
             @ApiParam(value = "培训记录id", required = true) @RequestParam Long id){
		Result<List<ResponseTrainPlanDetailVO>> result = new Result<>();
		try{
			/*UserVO user=(UserVO) session.getAttribute("user");*/
			//Page<List<ResponseTrainPlanDetailVO>> page = new Page<>(pageNo, pageSize);
			//page.setTotalRecord(trainPlanReportService.getTrainRecordReportDtlListTotalNum(id, user));
			List<ResponseTrainPlanDetailVO> list=trainPlanReportService.getTrainRecordReportDtlList(id,null,null,userVO);
			//page.setResult(list);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
			return result;
		}catch(Exception e){
			logger.error("查询培训记录明细列表异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "按照模版将培训记录明细导出至Excel", notes = "按照模版将培训记录明细导出至Excel | 开发者 张东东 | 已联调")
	@ApiImplicitParam(name = "id", value = "培训记录id", required = true, paramType = "path")
    @RequestMapping(value="/exportPlanRecordDetlExcel/{id}", method=RequestMethod.GET)
    public void exportPlanRecordDetlExcel( HttpServletResponse response,@ApiIgnore UserVO userVO,HttpSession session,@PathVariable Long id){
		OutputStream output=null;
		try{
		/*	UserVO user=(UserVO) session.getAttribute("user");*/
			output=response.getOutputStream();
			//这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name="培训记录";
			response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            trainPlanReportService.exportPlanRecordDetlExcel(userVO,output,id);
		}catch(Exception e){
			logger.error("按照模版将培训记录明细导出至Excel异常:" + e.getMessage(), e);
		}
		
	}
}
