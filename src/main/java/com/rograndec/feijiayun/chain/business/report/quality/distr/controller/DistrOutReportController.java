package com.rograndec.feijiayun.chain.business.report.quality.distr.controller;

import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrOutService;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrOutReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrOut;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_distr_distrOut",description = "报表-质量管理-运输与配送-配货出库")
@RestController
@RequestMapping("/report/quality/distr/distrOut")
@Validated
public class DistrOutReportController {

	private static final Log logger = LogFactory.getLog(DistrOutReportController.class);

	@Autowired
	private DistrOutService distrOutService;
	@ApiOperation(value="查询配货出库单列表", notes = "查询配货出库单列表 | 开发者 金正斌| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getReqPlanOrderList", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page<OrderReportVo<DistrOutReportVo>>> getReqPlanOrderList(HttpServletRequest request,
																			 @Valid @RequestBody RequestDistrOut requestDistrOut){
		Result<Page<OrderReportVo<DistrOutReportVo>>> result = new Result<>();
		Integer pageNo=requestDistrOut.getPageNo();
		Integer pageSize=requestDistrOut.getPageSize();
		try{
			Page<OrderReportVo<DistrOutReportVo>> page = new Page<>(pageNo, pageSize);
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			requestDistrOut.setEnterpriseId(loginUser.getEnterpriseId());
			distrOutService.getDistrOutList(page, requestDistrOut);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询配货单列表数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	@ApiOperation(value = "配货出库单-导出数据", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,
							RequestDistrOut requestDistrOut) throws Exception {
		String name = "配货出库单导出";
//		RequestDistrOut requestDistrOut=new RequestDistrOut();
		try {
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			OutputStream output = response.getOutputStream();
			HttpSession session = request.getSession(true);
			UserVO userVO = (UserVO) session.getAttribute("user");
			Integer pageNo=requestDistrOut.getPageNo();
			Integer pageSize=requestDistrOut.getPageSize();
			Page<OrderReportVo<DistrOutReportVo>> page = new Page<>(pageNo, pageSize);
			requestDistrOut.setPageSize(null);
			requestDistrOut.setPageNo(null);
			distrOutService.getDistrOutList(page, requestDistrOut);
			List<DistrOutReportVo> distrOutReportVos=page.getResult().getDataList();
			distrOutService.excelExportReport(output, distrOutReportVos, userVO);
		} catch (Exception e) {
			logger.error("导出配货单错误:" + e.getMessage(), e);
			e.printStackTrace();
		}
	}
}
