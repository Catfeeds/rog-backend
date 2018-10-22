package com.rograndec.feijiayun.chain.business.report.quality.retail.controller;

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

import com.rograndec.feijiayun.chain.business.report.quality.retail.service.SalePricingReportService;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.PrescriptionReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.RequestSpecialRegisterVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SpecialRegisterReportVO;
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
@Api(value = "report_quality_retail_specialRegister",description = "报表-质量管理-销售-专管登记")
@RestController
@RequestMapping("report/quality/retail/specialRegister")
@Validated
public class SpecialRegisterReportController {

private static final Logger logger = LoggerFactory.getLogger(SpecialRegisterReportController.class);
	
	@Autowired
	private SalePricingReportService salePricingReportService;
	
	
	@ApiOperation(value="查询专管登记明细列表", notes = "查询专管登记明细列表 | 开发者 张东东| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "requestSpecialRegisterVO", value = "检索条件", required = true, dataType = "RequestSpecialRegisterVO")
	@RequestMapping(value = "/getSpecialRegisterReportList", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page<PrescriptionReportVO<SpecialRegisterReportVO>>> getSpecialRegisterReportList(HttpServletRequest request,@ApiIgnore UserVO userVO,
			 @RequestBody RequestSpecialRegisterVO requestSpecialRegisterVO){
		Result<Page<PrescriptionReportVO<SpecialRegisterReportVO>>> result = new Result<>();
		try{
			requestSpecialRegisterVO.setStartDate(StartAndEndDate.getStartTime(requestSpecialRegisterVO.getStartDate()));
			requestSpecialRegisterVO.setEndDate(StartAndEndDate.getEndTime(requestSpecialRegisterVO.getEndDate()));
			Integer pageNo=requestSpecialRegisterVO.getPageNo();
			Integer pageSize=requestSpecialRegisterVO.getPageSize();
			if(pageNo==null || pageSize==null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"分页参数必填");
    			return result;
        	}
        	if(pageNo <= 0 || pageSize <= 0){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"分页参数不合法");
    			return result;
        	}
			Page<PrescriptionReportVO<SpecialRegisterReportVO>> page = new Page<>(pageNo, pageSize);
			/*HttpSession session = request.getSession(true);
			UserVO user = (UserVO) session.getAttribute("user");*/
			salePricingReportService.getSpecialRegisterReportList(page, userVO, requestSpecialRegisterVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询专管登记明细列表异常:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "专管登记明细列表导出数据", notes = "专管登记明细列表导出数据 | 开发者 张东东 | 已联调")
	@ApiImplicitParam(name = "requestSpecialRegisterVO", value = "检索条件", required = true, dataType = "RequestSpecialRegisterVO")
	@RequestMapping(value = "/exportSpecialRegisterReportListExcel", method = RequestMethod.POST,produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void exportSpecialRegisterReportListExcel(HttpServletRequest request, HttpServletResponse response,@ApiIgnore UserVO userVO,
			RequestSpecialRegisterVO requestSpecialRegisterVO) throws Exception {
		String name = "专管登记导出";
		OutputStream output =null;
		try {
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			output = response.getOutputStream();
			/*HttpSession session = request.getSession(true);
			UserVO user = (UserVO) session.getAttribute("user");*/
			salePricingReportService.exportSpecialRegisterReportListExcel(output, userVO, requestSpecialRegisterVO);
		} catch (Exception e) {
			logger.error("专管登记导出异常:" + e.getMessage(), e);
			e.printStackTrace();
		}finally{
			if(output!=null){
				output.flush();
				output.close();
			}
		}
	}
	
}
