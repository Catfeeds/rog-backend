package com.rograndec.feijiayun.chain.business.report.quality.distr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.report.quality.distr.service.DistrReturnReportService;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReturnCheckReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrReturnCheckVO;
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
@Api(value = "report_quality_distr_distrReturnCheck",description = "报表-质量管理-运输与配送-配后退回验收")
@RestController
@RequestMapping("report/quality/distr/distrReturnCheck")
@Validated
public class DistrReturnCheckReportController {


	private static final Logger logger = LoggerFactory.getLogger(DistrInCheckReportController.class);
	
	@Autowired
	private DistrReturnReportService distrReturnReportService;
	
	
	@ApiOperation(value="查询配后退回验收单明细列表", notes = "查询配后退回验收单明细列表 | 开发者 张东东| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "requestDistrReturnCheckVO", value = "检索条件", required = true, dataType = "RequestDistrReturnCheckVO")
	@RequestMapping(value = "/getDistrReturnCheckReport", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page<DistrReportVo<DistrReturnCheckReportVO>>> getDistrReturnCheckReport(HttpServletRequest request,@ApiIgnore UserVO userVO,
			 @RequestBody RequestDistrReturnCheckVO requestDistrReturnCheckVO){
		Result<Page<DistrReportVo<DistrReturnCheckReportVO>>> result = new Result<>();
		try{
			requestDistrReturnCheckVO.setStartDate(StartAndEndDate.getStartTime(requestDistrReturnCheckVO.getStartDate()));
			requestDistrReturnCheckVO.setEndDate(StartAndEndDate.getEndTime(requestDistrReturnCheckVO.getEndDate()));
			Integer pageNo=requestDistrReturnCheckVO.getPageNo();
			Integer pageSize=requestDistrReturnCheckVO.getPageSize();
			if(pageNo==null || pageSize==null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"分页参数必填");
    			return result;
        	}
        	if(pageNo <= 0 || pageSize <= 0){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"分页参数不合法");
    			return result;
        	}
			Page<DistrReportVo<DistrReturnCheckReportVO>> page = new Page<>(pageNo, pageSize);
			/*HttpSession session = request.getSession(true);
			UserVO user = (UserVO) session.getAttribute("user");*/
			distrReturnReportService.getDistrReturnCheckReport(page, userVO, requestDistrReturnCheckVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询配后退回验收单明细列表异常:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	
	
	@ApiOperation(value = "配后退回验收单明细列表导出数据", notes = "查询配后退回验收单明细列表导出数据 | 开发者 张东东 | 已联调")
	@ApiImplicitParam(name = "requestDistrReturnCheckVO", value = "检索条件", required = true, dataType = "RequestDistrReturnCheckVO")
	@RequestMapping(value = "/exportDistrReturnCheckReportListExcel", method = RequestMethod.POST,produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void exportDistrReturnCheckReportListExcel(HttpServletRequest request, HttpServletResponse response,@ApiIgnore UserVO userVO,
			RequestDistrReturnCheckVO requestDistrReturnCheckVO) throws Exception {
		String name = "配后退回验收单导出";
		try {
			requestDistrReturnCheckVO.setStartDate(StartAndEndDate.getStartTime(requestDistrReturnCheckVO.getStartDate()));
			requestDistrReturnCheckVO.setEndDate(StartAndEndDate.getEndTime(requestDistrReturnCheckVO.getEndDate()));
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			OutputStream output = response.getOutputStream();
			/*HttpSession session = request.getSession(true);
			UserVO user = (UserVO) session.getAttribute("user");*/
			distrReturnReportService.exportDistrReturnCheckReportListExcel(output, userVO, requestDistrReturnCheckVO);
			output.flush();
			output.close();
		} catch (Exception e) {
			logger.error("导出配后退回验收单明细列表异常:" + e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
}
