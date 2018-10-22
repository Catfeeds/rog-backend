package com.rograndec.feijiayun.chain.business.report.finance.tax.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.service.FinanceReportService;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceBalanceQueryVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceBalanceResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceDetailDtlResultPrintVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceDetailDtlResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceDetailQueryVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceVoucherQueryVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceVoucherResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.GeneralLedgerDeatilResultPrintVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.GeneralLedgerDeatilResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.GeneralLedgerQueryVO;
import com.rograndec.feijiayun.chain.common.NewSelectBean;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.GoodsComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


@Api(value = "report_finance_financeReport",description = "报表-财务管理-财务报表")
@RestController
@RequestMapping("report/finance")
@Validated
public class FinanceReportController {
	
	private static final Log logger = LogFactory.getLog(FinanceReportController.class);

	@Autowired
	private FinanceReportService financeReportService;
	
	@Autowired
    private GoodsComponent goodsComponent;

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询税余额列表", notes = "查询税余额列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryFinanceBalanceList", method=RequestMethod.POST)
    public Result<Page<List<FinanceBalanceResultVO>>> queryFinanceBalanceList(HttpServletRequest request,
			@RequestBody FinanceBalanceQueryVO vo){
		Result<Page<List<FinanceBalanceResultVO>>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = financeReportService.selectFinanceBalanceList(vo, loginUser);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询税余额列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value = "导出Excel", notes = "税余额列表导出至Excel | 开发者 刘群 | 已完成")
    @RequestMapping(value = "/exportFinanceBalanceList", method = RequestMethod.GET)
    public void exportFinanceBalanceList(HttpServletRequest request, HttpServletResponse response) {
        try {
            //导出不需要分页
            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            String name = "税余额";
            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            financeReportService.exportExcelFinanceBalance(output, userVO);
        } catch (Exception e) {
            logger.error("导出税余额报表错误:" + e.getMessage(), e);
            e.printStackTrace();
        }
    }
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询税总账列表", notes = "查询税总账列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryGeneralLedgerList", method=RequestMethod.POST)
    public Result<Page<List<GeneralLedgerDeatilResultVO>>> queryGeneralLedgerList(HttpServletRequest request,
			@RequestBody GeneralLedgerQueryVO vo){
		Result<Page<List<GeneralLedgerDeatilResultVO>>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = financeReportService.selectGeneralLedgerList(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询税总账列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value = "打印税总账", notes = "税总账打印 | 开发者 刘群 | 已完成")
    @RequestMapping(value = "/printGeneralLedgerList", method = RequestMethod.GET)
    public Result<List<GeneralLedgerDeatilResultPrintVO>> printGeneralLedgerList(HttpServletRequest request, HttpServletResponse response,
    		@ApiParam(value = "开始时间", required = false) @RequestParam(required=false) Date startDate,
    		@ApiParam(value = "截止时间", required = false) @RequestParam(required=false) Date endDate) {
		Result<List<GeneralLedgerDeatilResultPrintVO>> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<GeneralLedgerDeatilResultPrintVO> list = financeReportService.selectGeneralLedgerPrintList(startDate, endDate, userVO);
            
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("打印税总账报表错误:" + e.getMessage(), e);
            e.printStackTrace();
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "导出Excel", notes = "税总账列表导出至Excel | 开发者 刘群 | 已完成")
    @RequestMapping(value = "/exportGeneralLedgerList", method = RequestMethod.GET)
    public void exportGeneralLedgerList(HttpServletRequest request, HttpServletResponse response,
    		@ApiParam(value = "开始时间", required = false) @RequestParam(required=false) Date startDate,
    		@ApiParam(value = "截止时间", required = false) @RequestParam(required=false) Date endDate) {
        String name = "税总账";
        try {

            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");

            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            financeReportService.exportExcelGeneralLedger(output, startDate, endDate, userVO);
        } catch (Exception e) {
            logger.error("导出税总账报表错误:" + e.getMessage(), e);
            e.printStackTrace();
        }
    }
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询税明细列表", notes = "查询税明细列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryFinanceDetailList", method=RequestMethod.POST)
    public Result<Page<List<FinanceDetailDtlResultVO>>> queryFinanceDetailList(HttpServletRequest request,
			@RequestBody FinanceDetailQueryVO vo){
		Result<Page<List<FinanceDetailDtlResultVO>>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = financeReportService.selectFinanceDetailNewList(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询税明细列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value = "打印税明细列表", notes = "打印税明细列表 | 开发者 刘群 | 已完成")
    @RequestMapping(value = "/printFinanceDetailList", method = RequestMethod.GET)
    public Result<List<FinanceDetailDtlResultPrintVO>> printFinanceDetailList(HttpServletRequest request, HttpServletResponse response,
    		@ApiParam(value = "开始时间", required = false) @RequestParam(required=false) Date startDate,
    		@ApiParam(value = "截止时间", required = false) @RequestParam(required=false) Date endDate,
    		@ApiParam(value = "税类型", required = false) @RequestParam(required=false) String taxType,
    		@ApiParam(value = "税率ID", required = false) @RequestParam(required=false) Long taxId) {
		Result<List<FinanceDetailDtlResultPrintVO>> result = new Result<>();
        try {
            //导出不需要分页
            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");

            FinanceDetailQueryVO vo = new FinanceDetailQueryVO(taxType, taxId, startDate, endDate);
            List<FinanceDetailDtlResultPrintVO> list = financeReportService.selectFinanceDetailNewPrintList(vo, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("导出税明细报表错误:" + e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }
	
	
	@ApiOperation(value = "导出Excel", notes = "税明细列表导出至Excel | 开发者 刘群 | 已完成")
    @RequestMapping(value = "/exportFinanceDetailList", method = RequestMethod.GET)
    public void exportFinanceDetailList(HttpServletRequest request, HttpServletResponse response,
    		@ApiParam(value = "开始时间", required = false) @RequestParam(required=false) Date startDate,
    		@ApiParam(value = "截止时间", required = false) @RequestParam(required=false) Date endDate,
    		@ApiParam(value = "税类型", required = false) @RequestParam(required=false) String taxType,
    		@ApiParam(value = "税率ID", required = false) @RequestParam(required=false) Long taxId) {
		String name = "税明细账";
        try {
            //导出不需要分页
            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");

            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            
            FinanceDetailQueryVO vo = new FinanceDetailQueryVO(taxType, taxId, startDate, endDate);
            financeReportService.exportExcelFinanceDetail(output, vo, userVO);
        } catch (Exception e) {
            logger.error("导出税明细报表错误:" + e.getMessage(), e);
            e.printStackTrace();
        }
    }
	
	
	@ApiOperation(value="税明细报表获取税类型下拉", notes = "税明细报表获取税类型下拉 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPaymentPeriodUnit", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getPaymentPeriodUnit(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = financeReportService.getFinanceAccountSelectBean();
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取税类型下拉信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value = "获取税率下拉", notes = "获取税率下拉 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getTax",method= RequestMethod.GET)
    public Result<List<GoodsTaxRateVO>> getTax(HttpSession session){
        Result<List<GoodsTaxRateVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsComponent.getGoodsTaxRateVO(ChainType.getHeadEnterpriseIdAndDivisionEID(userVO), EnableStatus.ENABLE.getStatus()));
        return result;
    }
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询交易日记账凭证列表", notes = "查询交易日记账凭证列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryFinanceVoucherList", method=RequestMethod.POST)
    public Result<Page<List<FinanceVoucherResultVO>>> queryFinanceVoucherList(HttpServletRequest request,
			@RequestBody FinanceVoucherQueryVO vo){
		Result<Page<List<FinanceVoucherResultVO>>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			
			Page page = financeReportService.selectFinanceVoucherList(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("查询税明细列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value="交易日记账凭证报表获取单据类型下拉", notes = "交易日记账凭证报表获取单据类型下拉 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getOrderType", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<NewSelectBean>> getOrderType(HttpServletRequest request){
		Result<List<NewSelectBean>> result = new Result<List<NewSelectBean>>();
        try{
        	HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
        	List<NewSelectBean> list = financeReportService.getOrderTypeSelectBean(loginUser);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("交易日记账凭证报表获取单据类型下拉信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value = "导出Excel", notes = "交易日记账凭证列表导出至Excel | 开发者 刘群 | 已完成")
    @RequestMapping(value = "/exportFinanceVoucherList", method = RequestMethod.GET)
    public void exportFinanceVoucherList(HttpServletRequest request, HttpServletResponse response,
    		@ApiParam(value = "开始时间", required = false) @RequestParam(required=false) Date startDate,
    		@ApiParam(value = "截止时间", required = false) @RequestParam(required=false) Date endDate,
    		@ApiParam(value = "凭证号码", required = false) @RequestParam(required=false) String orderCode,
    		@ApiParam(value = "单据号码", required = false) @RequestParam(required=false) String baseOrderCode,
    		@ApiParam(value = "单据类型", required = false) @RequestParam(required=false) Integer baseOrderType,
    		@ApiParam(value = "操作人员", required = false) @RequestParam(required=false) String baseOperatorName,
    		@ApiParam(value = "科目编码", required = false) @RequestParam(required=false) String accountCode,
    		@ApiParam(value = "科目名称", required = false) @RequestParam(required=false) String accountName,
    		@ApiParam(value = "明细科目编码", required = false) @RequestParam(required=false) String subAccountCode,
    		@ApiParam(value = "明细科目名称", required = false) @RequestParam(required=false) String subAccountName) {
        String name = "交易日记账凭证";
        try {

            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");

            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            //输出Excel文件
            
            FinanceVoucherQueryVO vo = new FinanceVoucherQueryVO(orderCode, baseOrderCode, baseOrderType, baseOperatorName, 
            		accountCode, accountName, subAccountCode, subAccountName, startDate, endDate);
            OutputStream output = response.getOutputStream();
            financeReportService.exportExcelFinanceVoucherList(output, vo, userVO);
        } catch (Exception e) {
            logger.error("导出交易日记账凭证报表错误:" + e.getMessage(), e);
            e.printStackTrace();
        }
    }

}
