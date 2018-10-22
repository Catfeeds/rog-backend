package com.rograndec.feijiayun.chain.business.report.quality.retail.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import java.io.OutputStream;
import java.net.URLEncoder;

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

import com.rograndec.feijiayun.chain.business.report.quality.retail.service.SalePricingReportService;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.PrescriptionReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.RequestSalePricingVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SalePricingShelfVO;
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
@Api(value = "report_quality_retail_salePricing",description = "报表-质量管理-销售-划价单")
@RestController
@RequestMapping("report/quality/retail/salePricing")
@Validated
public class SalePricingReportController {


	private static final Logger logger = LoggerFactory.getLogger(SalePricingReportController.class);
	
	@Autowired
	private SalePricingReportService salePricingReportService;
	
	
	@ApiOperation(value="查询划价单明细列表", notes = "查询划价单明细列表 | 开发者 张东东| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "requestSalePricingVO", value = "检索条件", required = true, dataType = "RequestSalePricingVO")
	@RequestMapping(value = "/getSalePricingReportList", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page<PrescriptionReportVO<SalePricingShelfVO>>> getSalePricingReportList(HttpServletRequest request,@ApiIgnore UserVO userVO,
			 @RequestBody RequestSalePricingVO requestSalePricingVO){
		Result<Page<PrescriptionReportVO<SalePricingShelfVO>>> result = new Result<>();
		try{
			requestSalePricingVO.setStartDate(StartAndEndDate.getStartTime(requestSalePricingVO.getStartDate()));
			requestSalePricingVO.setEndDate(StartAndEndDate.getEndTime(requestSalePricingVO.getEndDate()));
			Integer pageNo=requestSalePricingVO.getPageNo();
			Integer pageSize=requestSalePricingVO.getPageSize();
			if(pageNo==null || pageSize==null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"分页参数必填");
    			return result;
        	}
        	if(pageNo <= 0 || pageSize <= 0){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"分页参数不合法");
    			return result;
        	}
			Page<PrescriptionReportVO<SalePricingShelfVO>> page = new Page<>(pageNo, pageSize);
			/*HttpSession session = request.getSession(true);
			UserVO user = (UserVO) session.getAttribute("user");*/
			salePricingReportService.getSalePricingReportList(page, userVO, requestSalePricingVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询划价单明细列表异常:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "划价单明细列表导出数据", notes = "划价单明细列表导出数据 | 开发者 张东东 | 已联调")
	@ApiImplicitParam(name = "requestSalePricingVO", value = "检索条件", required = true, dataType = "RequestSalePricingVO")
	@RequestMapping(value = "/exportSalePricingReportListExcel", method = RequestMethod.POST,produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void exportSalePricingReportListExcel(HttpServletRequest request, HttpServletResponse response,@ApiIgnore UserVO userVO,
			RequestSalePricingVO requestSalePricingVO) throws Exception {
		String name = "划价单导出";
		OutputStream output =null;
		try {
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			output = response.getOutputStream();
			/*HttpSession session = request.getSession(true);
			UserVO user = (UserVO) session.getAttribute("user");*/
			salePricingReportService.exportSalePricingReportListExcel(output, userVO, requestSalePricingVO);
		} catch (Exception e) {
			logger.error("划价单导出异常:" + e.getMessage(), e);
			e.printStackTrace();
		}finally{
			if(output!=null){
				output.flush();
				output.close();
			}
		}
	}
	
}
