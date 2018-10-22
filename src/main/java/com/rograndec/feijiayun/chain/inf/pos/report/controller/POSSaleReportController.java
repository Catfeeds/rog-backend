package com.rograndec.feijiayun.chain.inf.pos.report.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.pos.report.service.POSSaleReportService;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.ReceiptParamVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.ReceiptVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleCodeTotalVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleDateTotalVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleGoodsTotalVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleParamVO;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName: POSSaleReportController   
 * @Description: POS销售数据报表查询 单据、明细
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月13日 下午4:10:24
 */
@Api(value="POSLoginContoller",description = "POS查看销售报表")
@RestController
@RequestMapping("/inf/pos/report/sale")
public class POSSaleReportController {
	
	private static Logger logger = LoggerFactory.getLogger(POSSaleReportController.class);
	
	@Autowired
	private POSSaleReportService pOSSaleReportService;
	
	@ApiOperation(value = "按日期获取", notes = "按日期获取 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getSaleDate", method = RequestMethod.POST)
    public Result<SaleDateTotalVO> getSaleDate(HttpSession session,@Valid @RequestBody SaleParamVO paramVO) {
		Result<SaleDateTotalVO> result = new Result<>();
		checkDate(paramVO);
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            paramVO.setEnterpriseId(userVO.getEnterpriseId());
            SaleDateTotalVO total = pOSSaleReportService.getSaleDate(paramVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, total);
		} catch (Exception e) {
			logger.error("POS获取按日期获取数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
        return result;
    }
	
	@ApiOperation(value = "按单据获取", notes = "按单据获取 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getSaleCode", method = RequestMethod.POST)
    public Result<SaleCodeTotalVO> getSaleCode(HttpSession session,@Valid @RequestBody SaleParamVO paramVO) {
		Result<SaleCodeTotalVO> result = new Result<>();
		checkDate(paramVO);
		try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            paramVO.setEnterpriseId(userVO.getEnterpriseId());
            SaleCodeTotalVO total = pOSSaleReportService.getSaleCode(paramVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, total);
		} catch (Exception e) {
			logger.error("POS按单据获取数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
        return result;
    }
	
	@ApiOperation(value = "按明细获取", notes = "按明细获取 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getSaleGoods", method = RequestMethod.POST)
    public Result<SaleGoodsTotalVO> getSaleGoods(HttpSession session,@Valid @RequestBody SaleParamVO paramVO) {
		Result<SaleGoodsTotalVO> result = new Result<>();
		checkDate(paramVO);
		try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            paramVO.setEnterpriseId(userVO.getEnterpriseId());
            SaleGoodsTotalVO total = pOSSaleReportService.getSaleGoods(paramVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, total);
		} catch (Exception e) {
			logger.error("POS按明细获取数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
        return result;
    }
	
	@ApiOperation(value = "获取收款单", notes = "获取收款单 | 开发者 yuting.li | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getReceipt", method = RequestMethod.POST)
    public Result<ReceiptVO> getReceipt(HttpSession session,@Valid @RequestBody ReceiptParamVO paramVO) {
		Result<ReceiptVO> result = new Result<>();
		try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            ReceiptVO receiptVO = pOSSaleReportService.getReceiptData(paramVO.getSaleId(), userVO.getEnterpriseId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, receiptVO);
		} catch (Exception e) {
			logger.error("POS获取收款单数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
        return result;
    }
	
	private void checkDate(SaleParamVO paramVO) {
		String sd = paramVO.getStartDate();
		String se = paramVO.getEndDate();
		if(StringUtils.isBlank(sd) && StringUtils.isBlank(se)) {
			throw new BusinessException("开始、结束日期不能为空");
		} else {
			boolean bsd = DateUtils.isDateStr(sd);
			boolean bse = DateUtils.isDateStr(se);
			if(bsd == false || bse == false) {
				throw new BusinessException("输入正确的日期格式");
			}
		}
		Date start = DateUtils.StringToDate(sd, DateStyle.YYYY_MM_DD);
		Date end = DateUtils.StringToDate(se, DateStyle.YYYY_MM_DD);
		Long days = DateUtils.diffInDays(start, end);
		if(days > 30) {
			throw new BusinessException("检索日期为一个月");
		}
		if(days < 0) {
			throw new BusinessException("开始日期不能大于结束日期");
		}
	}
	

}
