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

import com.rograndec.feijiayun.chain.business.report.quality.distr.service.DistrInReportService;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrInCheckReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrInCheckVO;
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
@Api(value = "report_quality_distr_distrInCheck",description = "报表-质量管理-运输与配送-配进验收")
@RestController
@RequestMapping("report/quality/distr/distrInCheck")
@Validated
public class DistrInCheckReportController {

	private static final Logger logger = LoggerFactory.getLogger(DistrInCheckReportController.class);
	
	@Autowired
	DistrInReportService distrInReportService;
	
	
	@ApiOperation(value="查询配进验收单明细列表", notes = "查询配进验收单明细列表 | 开发者 张东东| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "requestDistrInCheckVO", value = "检索条件", required = true, dataType = "RequestDistrInCheckVO")
	@RequestMapping(value = "/getReqPlanOrderList", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page<DistrReportVo<DistrInCheckReportVO>>> getDistrInCheckReportList(HttpServletRequest request,@ApiIgnore UserVO userVO,
														 @RequestBody RequestDistrInCheckVO requestDistrInCheckVO){
		Result<Page<DistrReportVo<DistrInCheckReportVO>>> result = new Result<>();
		try{
			requestDistrInCheckVO.setStartCheckDate(StartAndEndDate.getStartTime(requestDistrInCheckVO.getStartCheckDate()));
			requestDistrInCheckVO.setEndCheckDate(StartAndEndDate.getEndTime(requestDistrInCheckVO.getEndCheckDate()));
			Integer pageNo=requestDistrInCheckVO.getPageNo();
			Integer pageSize=requestDistrInCheckVO.getPageSize();
			if(pageNo==null || pageSize==null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"分页参数必填");
    			return result;
        	}
        	if(pageNo <= 0 || pageSize <= 0){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"分页参数不合法");
    			return result;
        	}
			Page<DistrReportVo<DistrInCheckReportVO>> page = new Page<>(pageNo, pageSize);
			
			distrInReportService.getDistrInCheckReportList(page, userVO, requestDistrInCheckVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询配进验收单明细列表异常:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "配进验收单明细列表导出数据", notes = "配进验收单明细列表导出数据 | 开发者 张东东 | 已联调")
	@ApiImplicitParam(name = "requestDistrInCheckVO", value = "检索条件", required = true, dataType = "RequestDistrInCheckVO")
	@RequestMapping(value = "/exportDistrInCheckReportListExcel", method = RequestMethod.POST,produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void exportDistrInCheckReportListExcel(HttpServletRequest request, HttpServletResponse response,@ApiIgnore UserVO userVO,
						RequestDistrInCheckVO requestDistrInCheckVO) throws Exception {
		String name = "配进验收单导出";
		try {
			requestDistrInCheckVO.setStartCheckDate(StartAndEndDate.getStartTime(requestDistrInCheckVO.getStartCheckDate()));
			requestDistrInCheckVO.setEndCheckDate(StartAndEndDate.getEndTime(requestDistrInCheckVO.getEndCheckDate()));
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			OutputStream output = response.getOutputStream();
			
			distrInReportService.exportDistrInCheckReportListExcel(output, userVO, requestDistrInCheckVO);
		} catch (Exception e) {
			logger.error("配进验收单导出异常:" + e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
}
