package com.rograndec.feijiayun.chain.business.report.finance.account.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.report.finance.account.service.AdjustPriceAnalysisService;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AdjustPriceDetail;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AdjustPriceTotalVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AdjustPriceVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.status.FinancePaymentStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @ClassName: AdjustPriceAnalysisController   
 * @Description: 财务管理-应付/应收调价-应付/应收调价分析
 * @author dongdong.zhang
 * @version 1.0 
 * @date 2018年1月12日 上午10:40:54
 */
@Api(value = "report_finance_account_adjust",description = "财务管理-应付/应收调价-应付/应收调价分析")
@RestController
@RequestMapping("report/finance/account/adjust")
public class AdjustPriceAnalysisController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	AdjustPriceAnalysisService adjustPriceAnalysisService;
	
	@ApiOperation(value = "应付/应收调价分析分页列表", notes = "获取数据 | 开发者 张东东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@GetMapping(value = "/getAdjustList")
	public Result<Page<AdjustPriceTotalVO>> getAdjustList(@ApiIgnore UserVO userVO,
			@ApiParam(name = "sign", value = "请求类型，0-应付   1-应收", required = true) @RequestParam(required = true)Integer sign,
			@ApiParam(name = "pageNo", value = "当前页码", required = true) @RequestParam(required = true)Integer pageNo,
            @ApiParam(name = "pageSize", value = "显示条数", required = true) @RequestParam(required = true)Integer pageSize,
            @ApiParam(name = "startDate", value = "开始日期", required = true) @RequestParam(required = false) Date startDate,
			@ApiParam(name = "endDate", value = "结束日期", required = true) @RequestParam(required = false) Date endDate,
            @ApiParam(name = "order", value = "排序字段（orderDate-单据日期,orderCode-单据编号）", required = false) @RequestParam(required = false)String order,
            @ApiParam(name = "sort", value = "升序（asc）、降序（desc）", required = false) @RequestParam(required = false)String sort,
			@ApiParam(name = "code", value = "单位编码", required = false) @RequestParam(required = false)String code,
			@ApiParam(name = "name", value = "单位名称", required = false) @RequestParam(required = false)String name,
			@ApiParam(name = "type", value = "单据类型(2201-预付发票,2202-应付发票,2203-应付贷项凭证;2301-预收发票,2302-应收发票,2303-应收贷项凭证)", required = false) 
			@RequestParam(required = false)Integer type){
		Result<Page<AdjustPriceTotalVO>> result = new Result<>();
		try{
			
			if(pageNo <= 0 || pageSize <= 0){
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,null);
				return result;
	    	}
			Page<AdjustPriceTotalVO> page = new Page<>(pageNo, pageSize);
			Map<String,Object> map = new HashMap<>(9);
			map.put("order", order);
			map.put("sort", sort);
			map.put("code", code);
			map.put("name", name);
			map.put("type", type);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			map.put("start", page.getStart());
			map.put("pageSize",pageSize);
			map.put("enterpriseId",userVO.getEnterpriseId());
			map.put("status", FinancePaymentStatus.WARITE_OF);
			if(sign != 0 && sign != 1) throw new BusinessException("请求类型不符合条件");
			if(sign == 0) {
				adjustPriceAnalysisService.getPayAdjustList(userVO, page, map, type,sign);
			}else {
				adjustPriceAnalysisService.getReceiveAdjustList(userVO, page, map, type,sign);
			}
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
		}catch(BusinessException e) {
			logger.error("应付/应收调价分析分页列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(e.getErrorCode(),e.getMessage());
		}catch(Exception e){
			logger.error("应付/应收调价分析分页列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@ApiOperation(value = "应付/应收调价分析明细", notes = "获取数据 | 开发者 张东东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@GetMapping(value = "/getAdjustDtlList")
	public Result<List<AdjustPriceDetail>> getAdjustDtlList(@ApiIgnore UserVO userVO,
			@ApiParam(name = "sign", value = "请求类型，0-应付   1-应收", required = true) @RequestParam(required = true)Integer sign,
			@ApiParam(name = "id", value = "主表id", required = true) @RequestParam(required = true) Long id,
			@ApiParam(name = "type", value = "单据类型(2201-预付发票,2202-应付发票,2203-应付贷项凭证;2301-预收发票,2302-应收发票,2303-应收贷项凭证)", required = true) 
			@RequestParam(required = true)Integer type){
		Result<List<AdjustPriceDetail>> result = new Result<>();
		try{
			List<AdjustPriceDetail> resList = null;
			if(sign == 0) {
				resList = adjustPriceAnalysisService.getPayAdjustDetail(userVO, id, type,sign);
			}else {
				resList = adjustPriceAnalysisService.getReceiveAdjustDetail(userVO, id, type,sign);
			}
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,resList);
		}catch(BusinessException e) {
			logger.error("应付/应收调价分析明细错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(e.getErrorCode(),e.getMessage());
		}catch(Exception e){
			logger.error("应付/应收调价分析明细错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@ApiOperation(value = "导出应付/应收调价分析分页列表", notes = "导出数据 | 开发者 张东东 | 已联调")
	@GetMapping(value = "/excelExport", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	/*@ApiImplicitParam(name = "sign", value = "请求类型，0-应付   1-应收", required = true, paramType="path")*/
    public void excelExport(HttpServletRequest request, HttpServletResponse response,@ApiIgnore UserVO userVO,
    		/*@PathVariable("sign")Integer sign,*/
    		@ApiParam(name = "sign", value = "请求类型，0-应付   1-应收", required = true) @RequestParam(required = true)Integer sign,
    		@ApiParam(name = "startDate", value = "开始日期", required = true) @RequestParam(required = false) Date startDate,
			@ApiParam(name = "endDate", value = "结束日期", required = true) @RequestParam(required = false) Date endDate,
    		@ApiParam(name = "order", value = "排序字段（orderDate-单据日期,orderCode-单据编号）", required = false) @RequestParam(required = false)String order,
            @ApiParam(name = "sort", value = "升序（asc）、降序（desc）", required = false) @RequestParam(required = false)String sort,
  			@ApiParam(name = "code", value = "单位编码", required = false) @RequestParam(required = false)String code,
  			@ApiParam(name = "name", value = "单位名称", required = false) @RequestParam(required = false)String name,
  			@ApiParam(name = "type", value = "单据类型(2201-预付发票,2202-应付发票,2203-应付贷项凭证;2301-预收发票,2302-应收发票,2303-应收贷项凭证)", required = false) 
  			@RequestParam(required = false)Integer type) throws Exception {
        try {
        	if(sign != 0 && sign != 1) throw new BusinessException("请求类型不符合条件");
        	String nameExcel = "应付调价分析";
        	if(sign == 1) nameExcel = "应收调价分析";
	        response.setContentType("application/msexcel;charset=utf-8");
	        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(nameExcel, "UTF-8")+".xlsx");
	        //输出Excel文件
	        OutputStream output = response.getOutputStream();
	        Map<String,Object> map = new HashMap<>(9);
			map.put("order", order);
			map.put("sort", sort);
			map.put("code", code);
			map.put("name", name);
			map.put("type", type);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			map.put("enterpriseId",userVO.getEnterpriseId());
			map.put("status", FinancePaymentStatus.WARITE_OF);
			adjustPriceAnalysisService.excelExport(output, userVO, map,type, sign);
	        output.flush();
            output.close();
        } catch (Exception e) {
        	logger.error("导出应付/应收账款明细账错误:"+e.getMessage(),e);
            e.printStackTrace();
        }
    }

}
