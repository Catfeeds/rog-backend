package com.rograndec.feijiayun.chain.business.report.quality.distr.controller;

import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInReceiveService;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrInReceiveReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrInReceive;
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
@Api(value = "report_quality_distr_distrInReceive",description = "报表-质量管理-运输与配送-配进收货")
@RestController
@RequestMapping("/report/quality/distr/distrInReceive")
@Validated
public class DistrInReceiveReportController {

	private static final Log logger = LogFactory.getLog(DistrInNoticeReportController.class);

	@Autowired
	private DistrInReceiveService distrInReceiveService;

	@ApiOperation(value="查询配进收货列表", notes = "查询配进收货列表 | 开发者 金正斌| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getReqPlanOrderList", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page<OrderReportVo<DistrInReceiveReportVo>>> getReqPlanOrderList(HttpServletRequest request,
																				   @Valid @RequestBody RequestDistrInReceive requestDistrInReceive){
		Result<Page<OrderReportVo<DistrInReceiveReportVo>>> result = new Result<>();
		Integer pageNo=requestDistrInReceive.getPageNo();
		Integer pageSize=requestDistrInReceive.getPageSize();
		try{
			Page<OrderReportVo<DistrInReceiveReportVo>> page = new Page<>(pageNo, pageSize);
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			requestDistrInReceive.setEnterpriseId(loginUser.getEnterpriseId());
			requestDistrInReceive.setParentId(loginUser.getParentId());
			distrInReceiveService.getDistrInReceiveList(page, requestDistrInReceive);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询配进收货数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	@ApiOperation(value = "配进收货-导出数据", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,
							RequestDistrInReceive requestDistrInReceive) throws Exception {
		String name = "配进收货单导出";
//		RequestDistrInReceive requestDistrInReceive=new RequestDistrInReceive();
		try {
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			OutputStream output = response.getOutputStream();
			HttpSession session = request.getSession(true);
			UserVO userVO = (UserVO) session.getAttribute("user");
			Integer pageNo=requestDistrInReceive.getPageNo();
			Integer pageSize=requestDistrInReceive.getPageSize();
			Page<OrderReportVo<DistrInReceiveReportVo>> page = new Page<>(pageNo, pageSize);
			requestDistrInReceive.setPageSize(null);
			requestDistrInReceive.setPageNo(null);
			distrInReceiveService.getDistrInReceiveList(page, requestDistrInReceive);
			List<DistrInReceiveReportVo> distrInReceiveReportVos=page.getResult().getDataList();
			distrInReceiveService.excelExportReport(output, distrInReceiveReportVos, userVO);
		} catch (Exception e) {
			logger.error("配进收货单导出错误:" + e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
}
