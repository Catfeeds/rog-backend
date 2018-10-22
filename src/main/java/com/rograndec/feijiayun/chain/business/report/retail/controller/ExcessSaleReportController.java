package com.rograndec.feijiayun.chain.business.report.retail.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.report.retail.service.ExcessSaleReportService;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkResultBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleResultTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleToOtherInVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherInDetailVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_retail_excessSale",description = "报表-零售管理-超量销售")
@RestController
@RequestMapping("report/retail/excessSale")
@Validated
public class ExcessSaleReportController {
	
	private static final Log logger = LogFactory.getLog(SaleAnalysisReportClerkController.class);
	
	@Autowired
	private ExcessSaleReportService excessSaleReportService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询超量销售列表", notes = "查询超量销售列表 | 开发者 刘群 | 已完成")
    @RequestMapping(value="/queryExcessSaleList", method=RequestMethod.POST)
    public Result<Page<ExcessSaleResultTotalVO>> queryExcessSaleList(HttpServletRequest request,
			@RequestBody ExcessSaleQueryVO vo){
		Result<Page<ExcessSaleResultTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = excessSaleReportService.selectExcessSalePageByParam(vo, loginUser);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询超量销售列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	
	@ApiOperation(value = "点击入库获取超量销售数据", notes = "点击入库获取超量销售数据 | 开发者 刘群 | 已完成")
    @RequestMapping(value="/queryExcessSaleData", method=RequestMethod.POST)
    public Result<List<OtherInDetailVO>> queryExcessSaleData(HttpServletRequest request,
			@RequestBody ExcessSaleQueryVO vo){
		Result<List<OtherInDetailVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			List<OtherInDetailVO> list = excessSaleReportService.selectExcessSaleDataByParam(vo, loginUser);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
		}catch(Exception e){
			logger.error("查询超量销售列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	
	
	@ApiOperation(value = "导出Excel", notes = "按照模版超量销售列表导出至Excel | 开发者 刘群 | 已完成")
    @RequestMapping(value="/exportExcel",method= RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "门店超量销售导出列表", required = false, dataType = "ExcessSaleQueryVO")
	})
	public void exportExcelBranchClerkSaleListByDailyTime(HttpServletRequest request, HttpServletResponse response, 
			ExcessSaleQueryVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-超量销售";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            excessSaleReportService.exportExcel(output, vo, loginUser, loginUser);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	
}
