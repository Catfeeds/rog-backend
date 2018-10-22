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

import com.rograndec.feijiayun.chain.business.report.retail.service.SaleAnalysisReportPayeeService;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeBranchDoubleClickQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeBranchDoubleClickResultTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeBranchQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeResultBranchVO;
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
@Api(value = "report_retail_saleAnalysis",description = "报表-零售管理-销售分析-收款人员")
@RestController
@RequestMapping("report/retail/saleAnalysis/payee")
@Validated
public class SaleAnalysisReportPayeeController {
	
	private static final Log logger = LogFactory.getLog(SaleAnalysisReportPayeeController.class);
	
	@Autowired
	private SaleAnalysisReportPayeeService saleAnalysisReportPayeeService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "门店按日结查询收款人员销售列表", notes = "门店按日结查询收款人员销售列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryBranchPayeeSaleListByDailyTime", method=RequestMethod.POST)
    public Result<Page<SaleAnalyPayeeResultBranchTotalVO>> queryBranchPayeeSaleListByDailyTime(HttpServletRequest request,
			@RequestBody SaleAnalyPayeeBranchQueryVO vo){
		Result<Page<SaleAnalyPayeeResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = saleAnalysisReportPayeeService.selectBranchPayeeSalePageByDailyTime(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("门店按日结查询收款人员销售列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	@ApiOperation(value = "导出Excel", notes = "门店按日结查询收款人员销售列表导出至Excel | 开发者 刘群 | 已联调")
    @RequestMapping(value="/exportExcelBranchPayeeSaleListByDailyTime",method= RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "门店按日结查询收款人员销售列表", required = false, dataType = "SaleAnalyPayeeBranchQueryVO")
	})
	public void exportExcelBranchPayeeSaleListByDailyTime(HttpServletRequest request, HttpServletResponse response, 
			SaleAnalyPayeeBranchQueryVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-分店收款人员销售";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<SaleAnalyPayeeResultBranchVO> resultBranchVOList = saleAnalysisReportPayeeService.selectBranchPayeeSaleListByDailyTime(vo, loginUser);
            saleAnalysisReportPayeeService.exportExcelBranchPayeeSaleListByDailyTime(output, resultBranchVOList, loginUser);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "门店按销售查询收款人员销售列表", notes = "门店按销售查询收款人员销售列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryBranchPayeeSaleListBySaleTime", method=RequestMethod.POST)
    public Result<Page<SaleAnalyPayeeResultBranchTotalVO>> queryBranchPayeeSaleListBySaleTime(HttpServletRequest request,
			@RequestBody SaleAnalyPayeeBranchQueryVO vo){
		Result<Page<SaleAnalyPayeeResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = saleAnalysisReportPayeeService.selectBranchPayeeSalePageBySaleTime(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("门店按日结查询收款人员销售列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	@ApiOperation(value = "导出Excel", notes = "门店按销售查询收款人员销售列表导出至Excel | 开发者 刘群 | 已联调")
    @RequestMapping(value="/exportExcelBranchPayeeSaleListBySaleTime",method= RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "门店按日结查询收款人员销售列表", required = false, dataType = "SaleAnalyPayeeBranchQueryVO")
	})
	public void exportExcelBranchPayeeSaleListBySaleTime(HttpServletRequest request, HttpServletResponse response, 
			SaleAnalyPayeeBranchQueryVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-分店收款人员销售";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<SaleAnalyPayeeResultBranchVO> resultBranchVOList = saleAnalysisReportPayeeService.selectBranchPayeeSaleListBySaleTime(vo, loginUser);
            saleAnalysisReportPayeeService.exportExcelBranchPayeeSaleListByDailyTime(output, resultBranchVOList, loginUser);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	
	
	
	// 总部收款人员销售
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "总部按日结查询收款人员销售列表", notes = "总部按日结查询收款人员销售列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryParentPayeeSaleListByDailyTime", method=RequestMethod.POST)
    public Result<Page<SaleAnalyPayeeResultBranchTotalVO>> queryParentPayeeSaleListByDailyTime(HttpServletRequest request,
			@RequestBody SaleAnalyPayeeBranchQueryVO vo){
		Result<Page<SaleAnalyPayeeResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = saleAnalysisReportPayeeService.selectParentPayeeSalePageByDailyTime(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("总部按日结查询收款人员销售列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	@ApiOperation(value = "导出Excel", notes = "总部按日结查询收款人员销售列表导出至Excel | 开发者 刘群 | 已联调")
    @RequestMapping(value="/exportExcelParentPayeeSaleListByDailyTime",method= RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "总部按日结查询收款人员销售列表", required = false, dataType = "SaleAnalyPayeeBranchQueryVO")
	})
	public void exportExcelParentPayeeSaleListByDailyTime(HttpServletRequest request, HttpServletResponse response, 
			SaleAnalyPayeeBranchQueryVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-总部收款人员销售";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<SaleAnalyPayeeResultBranchVO> resultBranchVOList = saleAnalysisReportPayeeService.selectParentPayeeSaleListByDailyTime(vo, loginUser);
            saleAnalysisReportPayeeService.exportExcelParentPayeeSaleListByDailyTime(output, resultBranchVOList, loginUser);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	
	
	
	// 收款人员销售
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "总部按销售查询收款人员销售列表", notes = "总部按销售查询收款人员销售列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryParentPayeeSaleListBySaleTime", method=RequestMethod.POST)
    public Result<Page<SaleAnalyPayeeResultBranchTotalVO>> queryParentPayeeSaleListBySaleTime(HttpServletRequest request,
			@RequestBody SaleAnalyPayeeBranchQueryVO vo){
		Result<Page<SaleAnalyPayeeResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = saleAnalysisReportPayeeService.selectParentPayeeSalePageBySaleTime(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("总部按日结查询收款人员销售列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	@ApiOperation(value = "导出Excel", notes = "总部按销售查询收款人员销售列表导出至Excel | 开发者 刘群 | 已联调")
    @RequestMapping(value="/exportExcelParentPayeeSaleListBySaleTime",method= RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "总部按日结查询收款人员销售列表", required = false, dataType = "SaleAnalyPayeeBranchQueryVO")
	})
	public void exportExcelParentPayeeSaleListBySaleTime(HttpServletRequest request, HttpServletResponse response, 
			SaleAnalyPayeeBranchQueryVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-总部收款人员销售";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<SaleAnalyPayeeResultBranchVO> resultBranchVOList = saleAnalysisReportPayeeService.selectParentPayeeSaleListBySaleTime(vo, loginUser);
            saleAnalysisReportPayeeService.exportExcelParentPayeeSaleListByDailyTime(output, resultBranchVOList, loginUser);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按日结查询收款人员销售列表双击显示明细", notes = "按日结查询收款人员销售列表双击显示明细 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryBranchDoubleClickPayeeSaleListByDailyTime", method=RequestMethod.POST)
    public Result<Page<SaleAnalyPayeeBranchDoubleClickResultTotalVO>> queryBranchDoubleClickPayeeSaleListByDailyTime(HttpServletRequest request,
			@RequestBody SaleAnalyPayeeBranchDoubleClickQueryVO vo){
		Result<Page<SaleAnalyPayeeBranchDoubleClickResultTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = saleAnalysisReportPayeeService.selectDoubleClickPayeeSalePageByDailyTime(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("分店按日结查询收款人员销售列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按销售查询收款人员销售列表双击显示明细", notes = "按销售查询收款人员销售列表双击显示明细 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryBranchDoubleClickPayeeSaleListBySaleDate", method=RequestMethod.POST)
    public Result<Page<SaleAnalyPayeeBranchDoubleClickResultTotalVO>> queryBranchDoubleClickPayeeSaleListBySaleDate(HttpServletRequest request,
			@RequestBody SaleAnalyPayeeBranchDoubleClickQueryVO vo){
		Result<Page<SaleAnalyPayeeBranchDoubleClickResultTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = saleAnalysisReportPayeeService.selectDoubleClickPayeeSalePageBySaleDate(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("按销售查询收款人员销售列表双击显示明细错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
}
