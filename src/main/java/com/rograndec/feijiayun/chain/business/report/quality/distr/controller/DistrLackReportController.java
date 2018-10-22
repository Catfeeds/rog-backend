package com.rograndec.feijiayun.chain.business.report.quality.distr.controller;

import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrLackService;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrLackReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrLack;
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
@Api(value = "report_quality_distr_distrLack",description = "报表-质量管理-运输与配送-缺配单")
@RestController
@RequestMapping("/report/quality/distr/distrLack")
@Validated
public class DistrLackReportController {

	private static final Logger logger = LoggerFactory.getLogger(DistrLackReportController.class);

	@Autowired
	private DistrLackService distrLackService;
	@ApiOperation(value="查询缺配单列表", notes = "查询缺配单列表 | 开发者 金正斌| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getReqPlanOrderList", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page<OrderReportVo<DistrLackReportVo>>> getReqPlanOrderList(HttpServletRequest request,
																			  @Valid @RequestBody RequestDistrLack requestDistrLack){
		Result<Page<OrderReportVo<DistrLackReportVo>>> result = new Result<>();
		Integer pageNo=requestDistrLack.getPageNo();
		Integer pageSize=requestDistrLack.getPageSize();
		try{
			Page<OrderReportVo<DistrLackReportVo>> page = new Page<>(pageNo, pageSize);
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			requestDistrLack.setEnterpriseId(loginUser.getEnterpriseId());
			distrLackService.getDistrlackList(page, requestDistrLack);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询缺配单列表数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "缺配单-导出数据", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,
							RequestDistrLack requestDistrLack) throws Exception {
		String name = "缺配单导出";
		try {
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			OutputStream output = response.getOutputStream();
			HttpSession session = request.getSession(true);
			UserVO userVO = (UserVO) session.getAttribute("user");
			Integer pageNo=requestDistrLack.getPageNo();
			Integer pageSize=requestDistrLack.getPageSize();
			Page<OrderReportVo<DistrLackReportVo>> page = new Page<>(pageNo, pageSize);
			requestDistrLack.setPageSize(null);
			requestDistrLack.setPageNo(null);
			distrLackService.getDistrlackList(page, requestDistrLack);
			List<DistrLackReportVo> distrLackReportVos=page.getResult().getDataList();
			distrLackService.excelExportReport(output, distrLackReportVos, userVO);
		} catch (Exception e) {
			logger.error("导出要货计划错误:" + e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
}
