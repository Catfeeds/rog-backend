package com.rograndec.feijiayun.chain.business.report.retail.controller;

import com.rograndec.feijiayun.chain.business.report.retail.service.TimeSaleReportService;
import com.rograndec.feijiayun.chain.business.report.retail.vo.QuerySaleYVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.RequestRetailTimeSale;
import com.rograndec.feijiayun.chain.business.report.retail.vo.RetailTimeSaleVo;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosSalePeriodVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author lizhongyi
 */
@Api(value = "report_retail_timeSale", description = "报表-零售管理-时段销售")
@RestController
@RequestMapping("/report/retail/timeSale")
@Validated
public class TimeSaleReportController {

	private static final Logger logger = LoggerFactory.getLogger(TimeSaleReportController.class);
	@Autowired
	private TimeSaleReportService timeSaleReportService;

	@ApiOperation(value = "查询销售列表", notes = "按日期、金额/客流查询销售列表 | 开发者 金正斌 | 已作废", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/querySaleListByAmount", method = RequestMethod.POST)
	@ResponseBody
	public Result<RetailTimeSaleVo> querySaleList(HttpServletRequest request,
												  @Valid @RequestBody RequestRetailTimeSale requestRetailTimeSale) {
		Result<RetailTimeSaleVo> result = new Result<>();
		try {
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			RetailTimeSaleVo retailTimeSaleVo = timeSaleReportService.getRetailTimeSaleVo(loginUser,requestRetailTimeSale);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, retailTimeSaleVo);
		} catch (Exception e) {
			logger.error("查询配货单列表数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "导出Excel", notes = "导出Excel | 开发者 金正斌 | 已作废")
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,
							RequestRetailTimeSale requestRetailTimeSale) {
		String name="";
		if(requestRetailTimeSale.getSaleDate()==null||"".equals(requestRetailTimeSale.getSaleDate())){
			name="当日销售";
		}else{
			name="历史销售";
		}
		try {
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			OutputStream output = null;
			HttpSession session = request.getSession(true);
			UserVO userVO = (UserVO) session.getAttribute("user");
			output = response.getOutputStream();
			timeSaleReportService.exportExcel(userVO,output,requestRetailTimeSale);
		}catch (Exception e){
			logger.error("导出Excel失败:", e);
		}
	}
    @ApiOperation(value = "查询销售列表X轴坐标与数值", notes = "查询销售列表X轴坐标与数值 | 开发者 zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/querySaleX", method = RequestMethod.GET)
    public Result<List<QuerySaleYVO>> querySaleX(HttpSession session,
                                                 @ApiParam(value = "类型, 1-按金额,2-按客流", required = true)@RequestParam(name = "saleType", required = true) Integer saleType,
												 @ApiParam(value = "销售日期", required = false) @RequestParam(required = false) String saleDate) throws Exception {
        Result<List<QuerySaleYVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
			List<QuerySaleYVO> querySaleYVO = timeSaleReportService.querySaleX(userVO, saleType, saleDate);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, querySaleYVO);
        } catch (Exception e) {
            logger.error("查询销售列表Y轴数值失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
}
