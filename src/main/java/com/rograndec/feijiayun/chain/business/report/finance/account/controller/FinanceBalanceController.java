package com.rograndec.feijiayun.chain.business.report.finance.account.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.report.finance.account.service.FinanceBalanceService;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.FinanceBalanceTotalVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.FinanceAccount;
import com.rograndec.feijiayun.chain.common.constant.FinanceAccountType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @ClassName: FinancebalanceController   
 * @Description: 财务管理-应付/应收账款-应付/应收账款余额
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月2日 下午4:03:54
 */
@Api(value = "FinancebalanceController",description = "财务管理-应付/应收账款-应付/应收账款余额")
@RestController
@RequestMapping("finance/accounts/balance")
public class FinanceBalanceController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FinanceBalanceService financeBalanceService;
	
	@ApiOperation(value = "应付/应收账款余额分页列表", notes = "获取数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@GetMapping(value = "/getBalance")
	public Result<Page<FinanceBalanceTotalVO>> getBalance(@ApiIgnore UserVO userVO,
			@ApiParam(name = "pageNo", value = "当前页码", required = true) @RequestParam(required = true)Integer pageNo,
            @ApiParam(name = "pageSize", value = "显示条数", required = true) @RequestParam(required = true)Integer pageSize,
            @ApiParam(name = "order", value = "排序字段", required = false) @RequestParam(required = false)String order,
            @ApiParam(name = "sort", value = "升序（asc）、降序（desc）", required = false) @RequestParam(required = false)String sort,
            @ApiParam(name = "accountType", value = "0:应付,1:应收", required = true) @RequestParam(required = true)Integer accountType,
			@ApiParam(name = "param", value = "检索条件:编码/名称", required = false) @RequestParam(required = false)String param){
		Result<Page<FinanceBalanceTotalVO>> result = new Result<>();
		try{
			if(pageNo <= 0 || pageSize <= 0){
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,null);
				return result;
	    	}
			Page<FinanceBalanceTotalVO> page = new Page<>(pageNo, pageSize);
			Map<String,Object> map = setParam(0, userVO, page, order, sort, param, accountType);
			page = financeBalanceService.getBalanceList(map);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(BusinessException b) {
			throw new BusinessException(b.getMessage());
		}catch(Exception e){
			logger.error("应付/应收账款余额错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@ApiOperation(value = "应付/应收账款余额打印数据", notes = "获取打印数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@GetMapping(value = "/getPrintBalance")
	public Result<FinanceBalanceTotalVO> getPrintBalance(@ApiIgnore UserVO userVO,
            @ApiParam(name = "accountType", value = "0:应付,1:应收", required = true) @RequestParam(required = true)Integer accountType,
			@ApiParam(name = "param", value = "检索条件:编码/名称", required = false) @RequestParam(required = false)String param){
		Result<FinanceBalanceTotalVO> result = new Result<>();
		try{
			Map<String,Object> map = setParam(1, userVO, null, null, null, param, accountType);
			Page<FinanceBalanceTotalVO> page = financeBalanceService.getBalanceList(map);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page.getResult());
		}catch(BusinessException b) {
			throw new BusinessException(b.getMessage());
		}catch(Exception e){
			logger.error("应付/应收账款余额错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@ApiOperation(value = "导出应付/应收账款余额", notes = "导出数据 | 开发者 yuting.li | 已联调")
	@GetMapping(value = "/excelExport", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void excelExport(HttpServletRequest request, HttpServletResponse response,
    		@ApiIgnore UserVO userVO,
    		@ApiParam(name = "param", value = "检索条件:编码/名称", required = false) @RequestParam(required = false)String param,
    		@ApiParam(name = "accountType", value = "0:应付,1:应收", required = true) @RequestParam(required = true)Integer accountType
    		) throws Exception {
        String name = "应付账款余额";
        if(accountType == 1) {
        	name = "应收账款余额";
        }
        try {
        	Map<String,Object> map = setParam(1, userVO, null, null, null, param, accountType);
	        response.setContentType("application/msexcel;charset=utf-8");
	        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
	        //输出Excel文件
	        OutputStream output = response.getOutputStream();
	        financeBalanceService.exportExcel(output, map);
            output.close();
            output.flush();
        } catch (Exception e) {
        	logger.error("导出应付/应收账款余额错误:"+e.getMessage(),e);
        }
    }
	
	private Map<String,Object> setParam(int opType,UserVO userVO,Page<?> page,String order,String sort,String param,Integer accountType){
		Map<String,Object> map = new HashMap<>(10);
		if(accountType != 0 && accountType != 1) {
			throw new BusinessException("accountType参数不正确，0:应付,1:应收");
		}
		// 分页参数
		if(opType == 0 && page != null) {
			map.put("order", order);
			map.put("sort", sort);
			map.put("start", page.getStart());
			map.put("pageSize", page.getPageSize());
		}
		map.put("chainType", userVO.getChainType());
		map.put("enterpriseId", userVO.getEnterpriseId());
		map.put("parentId", userVO.getParentId());
		map.put("param", StringUtils.isBlank(param) ? null : param.trim());
		// 应付：220201,应收:112201
		map.put("accountType", accountType);
		map.put("accountCode", accountType == 0 ? FinanceAccount.PAYMENT_ACCOUNT.getAccountCode() : FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode());
		map.put("subAccountType", accountType == 0 ? FinanceAccountType.SUPPLIER.getType() : FinanceAccountType.PURCHASE_COMPANY.getType());
		return map;
	}

}
