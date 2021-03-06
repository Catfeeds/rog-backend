package com.rograndec.feijiayun.chain.business.report.quality.distr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReturnReceiveReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrReturnReceiveVO;
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
@Api(value = "report_quality_distr_distrReturnReceive",description = "报表-质量管理-运输与配送-配后退回收货")
@RestController
@RequestMapping("report/quality/distr/distrReturnReceive")
@Validated
public class DistrReturnReceiveReportController {


	private static final Logger logger = LoggerFactory.getLogger(DistrInCheckReportController.class);
	
	@Autowired
	private DistrReturnReportService distrReturnReportService;
	
	
	@ApiOperation(value="查询配后退回收货单明细列表", notes = "查询配后退回收货单明细列表 | 开发者 张东东| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "requestDistrReturnReceiveVO", value = "检索条件", required = true, dataType = "RequestDistrReturnReceiveVO")
	@RequestMapping(value = "/getDistrReturnReceiveReport", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page<DistrReportVo<DistrReturnReceiveReportVO>>> getDistrReturnReceiveReport(HttpServletRequest request,@ApiIgnore UserVO userVO,
			 @RequestBody RequestDistrReturnReceiveVO requestDistrReturnReceiveVO){
		Result<Page<DistrReportVo<DistrReturnReceiveReportVO>>> result = new Result<>();
		try{
			requestDistrReturnReceiveVO.setStartDate(StartAndEndDate.getStartTime(requestDistrReturnReceiveVO.getStartDate()));
			requestDistrReturnReceiveVO.setEndDate(StartAndEndDate.getEndTime(requestDistrReturnReceiveVO.getEndDate()));
			Integer pageNo=requestDistrReturnReceiveVO.getPageNo();
			Integer pageSize=requestDistrReturnReceiveVO.getPageSize();
			if(pageNo==null || pageSize==null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"分页参数必填");
    			return result;
        	}
        	if(pageNo <= 0 || pageSize <= 0){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"分页参数不合法");
    			return result;
        	}
			Page<DistrReportVo<DistrReturnReceiveReportVO>> page = new Page<>(pageNo, pageSize);
			/*HttpSession session = request.getSession(true);
			UserVO user = (UserVO) session.getAttribute("user");*/
			distrReturnReportService.getDistrReturnReceiveReport(page, userVO, requestDistrReturnReceiveVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询配后退回收货单明细列表异常:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	
	
	@ApiOperation(value = "配后退回收货单明细列表导出数据", notes = "查询配后退回收货单明细列表导出数据 | 开发者 张东东 | 已联调")
	@ApiImplicitParam(name = "requestDistrReturnReceiveVO", value = "检索条件", required = true, dataType = "RequestDistrReturnNoticeVO")
	@RequestMapping(value = "/exportDistrReturnReceiveReportListExcel", method = RequestMethod.POST,produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void exportDistrReturnReceiveReportListExcel(HttpServletRequest request, HttpServletResponse response,@ApiIgnore UserVO userVO,
			  RequestDistrReturnReceiveVO requestDistrReturnReceiveVO) throws Exception {
		String name = "配后退回收货单导出";
		try {
			requestDistrReturnReceiveVO.setStartDate(StartAndEndDate.getStartTime(requestDistrReturnReceiveVO.getStartDate()));
			requestDistrReturnReceiveVO.setEndDate(StartAndEndDate.getEndTime(requestDistrReturnReceiveVO.getEndDate()));
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			OutputStream output = response.getOutputStream();
			/*HttpSession session = request.getSession(true);
			UserVO user = (UserVO) session.getAttribute("user");*/
			distrReturnReportService.exportDistrReturnReceiveReportListExcel(output, userVO, requestDistrReturnReceiveVO);
			output.flush();
			output.close();
		} catch (Exception e) {
			logger.error("导出配后退回收货单明细列表异常:" + e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
}
