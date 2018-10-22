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

import com.rograndec.feijiayun.chain.business.report.retail.service.SaleAnalysisReportClerkService;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchDoubleClickDetailQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchDoubleClickDetailResultTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchDoubleClickQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchDoubleClickResultTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkResultBranchVO;
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
@Api(value = "report_retail_saleAnalysis",description = "报表-零售管理-销售分析-营业人员")
@RestController
@RequestMapping("report/retail/saleAnalysis/clerk")
@Validated
public class SaleAnalysisReportClerkController {
	
	private static final Log logger = LogFactory.getLog(SaleAnalysisReportClerkController.class);
	
	@Autowired
	private SaleAnalysisReportClerkService saleAnalysisReportClerkService;

	// 营业人员销售
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "门店按日结查询营业人员销售列表", notes = "门店按日结查询营业人员销售列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryBranchClerkSaleListByDailyTime", method=RequestMethod.POST)
    public Result<Page<SaleAnalyClerkResultBranchTotalVO>> queryBranchClerkSaleListByDailyTime(HttpServletRequest request,
			@RequestBody SaleAnalyClerkBranchQueryVO vo){
		Result<Page<SaleAnalyClerkResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = saleAnalysisReportClerkService.selectBranchClerkSalePageByDailyTime(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("分店按日结查询营业人员销售列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	@ApiOperation(value = "导出Excel", notes = "门店按日结查询营业人员销售列表导出至Excel | 开发者 刘群 | 已联调")
    @RequestMapping(value="/exportExcelBranchClerkSaleListByDailyTime",method= RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "门店按日结查询营业人员销售列表", required = false, dataType = "SaleAnalyClerkBranchQueryVO")
	})
	public void exportExcelBranchClerkSaleListByDailyTime(HttpServletRequest request, HttpServletResponse response, 
			SaleAnalyClerkBranchQueryVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-分店营业人员销售";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<SaleAnalyClerkResultBranchVO> resultBranchVOList = saleAnalysisReportClerkService.selectClerkSaleListByDailyTime(vo, loginUser);
            saleAnalysisReportClerkService.exportExcelForClerkSaleListByDailyTime(output, resultBranchVOList, loginUser);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	
	
	
	// 营业人员销售
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "门店按销售查询营业人员销售列表", notes = "门店按销售查询营业人员销售列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryBranchClerkSaleListBySaleTime", method=RequestMethod.POST)
    public Result<Page<SaleAnalyClerkResultBranchTotalVO>> queryBranchClerkSaleListBySaleTime(HttpServletRequest request,
			@RequestBody SaleAnalyClerkBranchQueryVO vo){
		Result<Page<SaleAnalyClerkResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = saleAnalysisReportClerkService.selectBranchClerkSaleListBySaleTime(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("分店按日结查询营业人员销售列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	@ApiOperation(value = "导出Excel", notes = "门店按销售查询营业人员销售列表导出至Excel | 开发者 刘群 | 已联调")
    @RequestMapping(value="/exportExcelBranchClerkSaleListBySaleTime",method= RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "门店按日结查询营业人员销售列表", required = false, dataType = "SaleAnalyClerkBranchQueryVO")
	})
	public void exportExcelBranchClerkSaleListBySaleTime(HttpServletRequest request, HttpServletResponse response, 
			SaleAnalyClerkBranchQueryVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-分店营业人员销售";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<SaleAnalyClerkResultBranchVO> resultBranchVOList = saleAnalysisReportClerkService.selectClerkSaleListBySaleTime(vo, loginUser);
            saleAnalysisReportClerkService.exportExcelForClerkSaleListByDailyTime(output, resultBranchVOList, loginUser);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	
	
	
	// 总部营业人员销售
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "总部按日结查询营业人员销售列表", notes = "总部按日结查询营业人员销售列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryParentClerkSaleListByDailyTime", method=RequestMethod.POST)
    public Result<Page<SaleAnalyClerkResultBranchTotalVO>> queryParentClerkSaleListByDailyTime(HttpServletRequest request,
			@RequestBody SaleAnalyClerkBranchQueryVO vo){
		Result<Page<SaleAnalyClerkResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = saleAnalysisReportClerkService.selectParentClerkSalePageByDailyTime(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("分店按日结查询营业人员销售列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	@ApiOperation(value = "导出Excel", notes = "总部按日结查询营业人员销售列表导出至Excel | 开发者 刘群 | 已联调")
    @RequestMapping(value="/exportExcelParentClerkSaleListByDailyTime",method= RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "总部按日结查询营业人员销售列表", required = false, dataType = "SaleAnalyClerkBranchQueryVO")
	})
	public void exportExcelParentClerkSaleListByDailyTime(HttpServletRequest request, HttpServletResponse response, 
			SaleAnalyClerkBranchQueryVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-总部营业人员销售";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<SaleAnalyClerkResultBranchVO> resultBranchVOList = saleAnalysisReportClerkService.selectParentClerkSaleListByDailyTime(vo, loginUser);
            saleAnalysisReportClerkService.exportExcelParentClerkSaleListByDailyTime(output, resultBranchVOList, loginUser);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	
	
	
	// 营业人员销售
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "总部按销售查询营业人员销售列表", notes = "总部按销售查询营业人员销售列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryParentClerkSaleListBySaleTime", method=RequestMethod.POST)
    public Result<Page<SaleAnalyClerkResultBranchTotalVO>> queryParentClerkSaleListBySaleTime(HttpServletRequest request,
			@RequestBody SaleAnalyClerkBranchQueryVO vo){
		Result<Page<SaleAnalyClerkResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = saleAnalysisReportClerkService.selectParentClerkSalePageBySaleTime(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("分店按日结查询营业人员销售列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	@ApiOperation(value = "导出Excel", notes = "总部按销售查询营业人员销售列表导出至Excel | 开发者 刘群 | 已联调")
    @RequestMapping(value="/exportExcelParentClerkSaleListBySaleTime",method= RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "总部按日结查询营业人员销售列表", required = false, dataType = "SaleAnalyClerkBranchQueryVO")
	})
	public void exportExcelParentClerkSaleListBySaleTime(HttpServletRequest request, HttpServletResponse response, 
			SaleAnalyClerkBranchQueryVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-总部营业人员销售";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<SaleAnalyClerkResultBranchVO> resultBranchVOList = saleAnalysisReportClerkService.selectParentClerkSaleListBySaleTime(vo, loginUser);
            saleAnalysisReportClerkService.exportExcelParentClerkSaleListByDailyTime(output, resultBranchVOList, loginUser);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按日结查询营业人员销售列表双击显示营业人员明细", notes = "按日结查询营业人员销售列表双击显示营业人员明细 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryBranchDoubleClickClerkSaleListByDailyTime", method=RequestMethod.POST)
    public Result<Page<SaleAnalyClerkBranchDoubleClickResultTotalVO>> queryBranchDoubleClickClerkSaleListByDailyTime(HttpServletRequest request,
			@RequestBody SaleAnalyClerkBranchDoubleClickQueryVO vo){
		Result<Page<SaleAnalyClerkBranchDoubleClickResultTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = saleAnalysisReportClerkService.selectDoubleClickClerkSalePageByDailyTime(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("分店按日结查询营业人员销售列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按销售查询营业人员销售列表双击显示营业人员明细", notes = "按销售查询营业人员销售列表双击显示营业人员明细 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryBranchDoubleClickClerkSaleListBySaleDate", method=RequestMethod.POST)
    public Result<Page<SaleAnalyClerkBranchDoubleClickResultTotalVO>> queryBranchDoubleClickClerkSaleListBySaleDate(HttpServletRequest request,
			@RequestBody SaleAnalyClerkBranchDoubleClickQueryVO vo){
		Result<Page<SaleAnalyClerkBranchDoubleClickResultTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = saleAnalysisReportClerkService.selectDoubleClickClerkSalePageBySaleDate(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("分店按日结查询营业人员销售列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按日结查询营业人员销售列表双击显示销售单明细", notes = "按日结查询营业人员销售列表双击显示销售单明细 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryBranchDoubleClickDetailClerkSaleListByDailyTime", method=RequestMethod.POST)
    public Result<Page<SaleAnalyClerkBranchDoubleClickDetailResultTotalVO>> queryBranchDoubleClickDetailClerkSaleListByDailyTime(HttpServletRequest request,
			@RequestBody SaleAnalyClerkBranchDoubleClickDetailQueryVO vo){
		Result<Page<SaleAnalyClerkBranchDoubleClickDetailResultTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = saleAnalysisReportClerkService.selectDoubleClickDetailClerkSalePageByDailyTime(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("分店按日结查询营业人员销售列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
}
