package com.rograndec.feijiayun.chain.business.report.storage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.report.storage.service.StockWarnReportService;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListQueryVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnExpireGoodsTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnGoodsMaintanceTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnLackGoodsTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnLagSaleGoodsTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnNearPeriodGoodsTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnStoreGoodsTotalVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_storage_stockWarn",description = "报表-储存管理-库存预警")
@RestController
@RequestMapping("report/storage/stockWarn")
@Validated
public class StockWarnReportController {
	
	private static final Log logger = LogFactory.getLog(StockWarnReportController.class);

	@Autowired
	private StockWarnReportService stockWarnReportService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询过期商品列表", notes = "查询过期商品列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryExpireGoodsList", method=RequestMethod.POST)
    public Result<Page<StockWarnExpireGoodsTotalVO>> queryExpireGoodsList(HttpServletRequest request,
			@RequestBody StockListQueryVO vo){
		Result<Page<StockWarnExpireGoodsTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = stockWarnReportService.selectExpireGoodsList(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询过期商品列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询近效期商品列表", notes = "查询近效期商品列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryNearPeriodGoodsList", method=RequestMethod.POST)
    public Result<Page<StockWarnNearPeriodGoodsTotalVO>> queryNearPeriodGoodsList(HttpServletRequest request,
			@RequestBody StockListQueryVO vo){
		Result<Page<StockWarnNearPeriodGoodsTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = stockWarnReportService.selectNearPeriodGoodsList(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询近效期商品列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询缺货商品列表", notes = "查询缺货商品列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryLackGoodsList", method=RequestMethod.POST)
    public Result<Page<StockWarnLackGoodsTotalVO>> queryLackGoodsList(HttpServletRequest request,
			@RequestBody StockListQueryVO vo){
		Result<Page<StockWarnLackGoodsTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = stockWarnReportService.selectLackGoodsList(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询缺货商品列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询积货商品列表", notes = "查询积货商品列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryStoreGoodsList", method=RequestMethod.POST)
    public Result<Page<StockWarnStoreGoodsTotalVO>> queryStoreGoodsList(HttpServletRequest request,
			@RequestBody StockListQueryVO vo){
		Result<Page<StockWarnStoreGoodsTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = stockWarnReportService.selectStoreGoodsList(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询积货商品列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询滞销商品列表", notes = "查询滞销商品列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryLagSaleGoodsList", method=RequestMethod.POST)
    public Result<Page<StockWarnLagSaleGoodsTotalVO>> queryLagSaleGoodsList(HttpServletRequest request,
			@RequestBody StockListQueryVO vo){
		Result<Page<StockWarnLagSaleGoodsTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = stockWarnReportService.selectLagSaleGoodsList(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询滞销商品列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询药品养护列表", notes = "查询药品养护列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryGoodsMaintanceList", method=RequestMethod.POST)
    public Result<Page<StockWarnGoodsMaintanceTotalVO>> queryGoodsMaintanceList(HttpServletRequest request,
			@RequestBody StockListQueryVO vo){
		Result<Page<StockWarnGoodsMaintanceTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = stockWarnReportService.selectGoodsMaintanceList(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询滞销商品列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询门店陈列检查列表", notes = "查询门店陈列检查列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryGoodsDisplayCheckList", method=RequestMethod.POST)
    public Result<Page<StockWarnGoodsMaintanceTotalVO>> queryGoodsDisplayCheckList(HttpServletRequest request,
			@RequestBody StockListQueryVO vo){
		Result<Page<StockWarnGoodsMaintanceTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = stockWarnReportService.selectGoodsDisplayCheckList(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询滞销商品列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
}
