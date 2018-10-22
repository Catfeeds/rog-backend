package com.rograndec.feijiayun.chain.business.report.quality.distr.controller;

import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrSendService;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrSendReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrSend;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "report_quality_distr_distrSend",description = "报表-质量管理-运输与配送-配货单")
@RestController
@RequestMapping("/report/quality/distr/distrSend")
@Validated
public class DistrSendReportController {

	private static final Logger logger = LoggerFactory.getLogger(DistrSendReportController.class);

	@Autowired
	private DistrSendService distrSendService;
	@ApiOperation(value="查询配货单列表", notes = "查询配货单列表 | 开发者 金正斌| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getReqPlanOrderList", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page<OrderReportVo<DistrSendReportVo>>> getReqPlanOrderList(HttpServletRequest request,
																			  @Valid @RequestBody RequestDistrSend requestDistrSend){
		Result<Page<OrderReportVo<DistrSendReportVo>>> result = new Result<>();
		Integer pageNo=requestDistrSend.getPageNo();
		Integer pageSize=requestDistrSend.getPageSize();
		try{
			Page<OrderReportVo<DistrSendReportVo>> page = new Page<>(pageNo, pageSize);
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			requestDistrSend.setEnterpriseId(loginUser.getEnterpriseId());
			distrSendService.getDistrSendList(page, requestDistrSend);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询配货单列表数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "配货单-导出数据", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,
							RequestDistrSend requestDistrSend) throws Exception {
		String name = "配货单导出";
//		RequestDistrSend requestDistrSend=new RequestDistrSend();
		try {
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			OutputStream output = response.getOutputStream();
			HttpSession session = request.getSession(true);
			UserVO userVO = (UserVO) session.getAttribute("user");
			Integer pageNo=requestDistrSend.getPageNo();
			Integer pageSize=requestDistrSend.getPageSize();
			Page<OrderReportVo<DistrSendReportVo>> page = new Page<>(pageNo, pageSize);
			requestDistrSend.setPageSize(null);
			requestDistrSend.setPageNo(null);
			distrSendService.getDistrSendList(page, requestDistrSend);
			List<DistrSendReportVo> distrLackReportVos=page.getResult().getDataList();
			distrSendService.excelExportReport(output, distrLackReportVos, userVO);
		} catch (Exception e) {
			logger.error("导出配货单错误:" + e.getMessage(), e);
			e.printStackTrace();
		}
	}

}
