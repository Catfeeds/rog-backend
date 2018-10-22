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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.report.finance.account.service.LedgerService;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.LedgerGroupDateVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.LedgerVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.FinanceAccount;
import com.rograndec.feijiayun.chain.common.constant.FinanceAccountType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @ClassName: LedgerController   
 * @Description: 财务管理-应付/应收账款-应付/应收账款总账
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月2日 下午4:03:54
 */
@Api(value = "LedgerController",description = "财务管理-应付/应收账款-应付/应收账款总账")
@RestController
@RequestMapping("finance/accounts/ledger")
public class LedgerController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LedgerService ledgerService;
	
	@ApiOperation(value = "应付/应收账款总账分页列表", notes = "获取数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@GetMapping(value = "/getLedger")
	public Result<Page<List<LedgerVO>>> getLedger(@ApiIgnore UserVO userVO,
			@ApiParam(name = "pageNo", value = "当前页码", required = true) @RequestParam(required = true)Integer pageNo,
            @ApiParam(name = "pageSize", value = "显示条数", required = true) @RequestParam(required = true)Integer pageSize,
            @ApiParam(name = "accountType", value = "0:应付,1:应收", required = true) @RequestParam(required = true)Integer accountType,
			@ApiParam(name = "startDate", value = "开始日期", required = true) @RequestParam(required = true)Date startDate,
			@ApiParam(name = "endDate", value = "结束日期", required = true) @RequestParam(required = true)Date endDate){
		Result<Page<List<LedgerVO>>> result = new Result<>();
		try{
			if(pageNo <= 0 || pageSize <= 0){
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,null);
				return result;
	    	}
			Page<List<LedgerVO>> page = new Page<>(pageNo, pageSize);
			Map<String,Object> map = setParam(0, userVO, page, startDate, endDate,accountType);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, ledgerService.getLedger(map));
		}catch(BusinessException b) {
			throw new BusinessException(b.getMessage());
		}catch(Exception e){
			logger.error("应付/应收账款余额错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@ApiOperation(value = "应付/应收账款总账打印数据", notes = "获取打印数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@GetMapping(value = "/getPrintLedger")
	public Result<List<LedgerGroupDateVO>> getPrintLedger(@ApiIgnore UserVO userVO,
            @ApiParam(name = "accountType", value = "0:应付,1:应收", required = true) @RequestParam(required = true)Integer accountType,
			@ApiParam(name = "startDate", value = "开始日期", required = true) @RequestParam(required = true)Date startDate,
			@ApiParam(name = "endDate", value = "结束日期", required = true) @RequestParam(required = true)Date endDate){
		Result<List<LedgerGroupDateVO>> result = new Result<>();
		try{
			Map<String,Object> map = setParam(1, userVO, null, startDate, endDate,accountType);
			List<LedgerGroupDateVO> list = ledgerService.groupData(map);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
		}catch(BusinessException b) {
			throw new BusinessException(b.getMessage());
		}catch(Exception e){
			logger.error("应付/应收账款余额错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@ApiOperation(value = "导出应付/应收账款总账", notes = "导出数据 | 开发者 yuting.li | 已联调")
	@GetMapping(value = "/excelExport", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void excelExport(HttpServletRequest request, HttpServletResponse response,@ApiIgnore UserVO userVO,
    		@ApiParam(name = "startDate", value = "开始日期", required = true) @RequestParam(required = true)Date startDate,
			@ApiParam(name = "endDate", value = "结束日期", required = true) @RequestParam(required = true)Date endDate,
            @ApiParam(name = "accountType", value = "0:应付,1:应收", required = true) @RequestParam(required = true)Integer accountType) throws Exception {
        String name = "应付账款总账";
        if(accountType == 1) {
        	name = "应收账款总账";
        }
        try {
        	Map<String,Object> map = setParam(1, userVO, null, startDate, endDate,accountType);
	        response.setContentType("application/msexcel;charset=utf-8");
	        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
	        //输出Excel文件
	        OutputStream output = response.getOutputStream();
	        ledgerService.exportExcel(output, map);
            output.close();
            output.flush();
        } catch (Exception e) {
        	logger.error("导出应付/应收账款总账错误:"+e.getMessage(),e);
            e.printStackTrace();
        }
    }
	
	private Map<String,Object> setParam(int opType,UserVO userVO,Page<?> page,Date startDate,Date endDate,Integer accountType){
		Map<String,Object> map = new HashMap<>(10);
		if(accountType != 0 && accountType != 1) {
			throw new BusinessException("accountType参数不正确，0:应付,1:应收");
		}
		// 分页参数
		if(opType == 0 && page != null) {
			map.put("start", page.getStart());
			map.put("pageSize", page.getPageSize());
		}
		map.put("enterpriseId", userVO.getEnterpriseId());
		map.put("parentId", userVO.getParentId());
		// 应付：220201,应收:112201
		map.put("accountType", accountType);
		map.put("accountCode", accountType == 0 ? FinanceAccount.PAYMENT_ACCOUNT.getAccountCode() : FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode());
		map.put("subAccountType", accountType == 0 ? FinanceAccountType.SUPPLIER.getType() : FinanceAccountType.PURCHASE_COMPANY.getType());
		map.put("startDate", startDate != null ? DateUtils.DateToString(startDate, DateStyle.YYYY_MM_DD) : null);
		map.put("endDate", endDate != null ? DateUtils.DateToString(endDate, DateStyle.YYYY_MM_DD) : null);
		return map;
	}
	
}
