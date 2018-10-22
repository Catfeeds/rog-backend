package com.rograndec.feijiayun.chain.business.report.finance.account.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.rograndec.feijiayun.chain.business.report.finance.account.service.AgingService;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AgingGroupVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AgingVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
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
 * @ClassName: AgingController   
 * @Description: 财务管理-应付/应收账款-应付/应收账龄分析
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月2日 下午4:03:54
 */
@Api(value = "AgingController",description = "财务管理-应付/应收账款-应付/应收账龄分析")
@RestController
@RequestMapping("finance/accounts/aging")
public class AgingController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AgingService agingService;
	
	@ApiOperation(value = "应付/应收账龄分析分页列表", notes = "获取数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@GetMapping(value = "/getAging")
	public Result<Page<List<AgingVO>>> getAging(@ApiIgnore UserVO userVO,
			@ApiParam(name = "accountType", value = "0:应付,1:应收", required = true) @RequestParam(required = true)Integer accountType,
			@ApiParam(name = "pageNo", value = "当前页码", required = true) @RequestParam(required = true)Integer pageNo,
            @ApiParam(name = "pageSize", value = "显示条数", required = true) @RequestParam(required = true)Integer pageSize,
			@ApiParam(name = "startDate", value = "开始日期", required = true) @RequestParam(required = true)Date startDate,
			@ApiParam(name = "endDate", value = "结束日期", required = true) @RequestParam(required = true)Date endDate,
			@ApiParam(name = "companyCode", value = "购货单位编码", required = false) @RequestParam(required = false)String companyCode,
			@ApiParam(name = "companyName", value = "购货单位名称", required = false) @RequestParam(required = false)String companyName){
		Result<Page<List<AgingVO>>> result = new Result<>();
		try{
			if(pageNo <= 0 || pageSize <= 0){
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,null);
				return result;
	    	}
			Page<List<AgingVO>> page = new Page<>(pageNo, pageSize);
			Map<String,Object> map = setParam(0, userVO,page,startDate,endDate, companyCode, companyName, accountType);
			page = agingService.getAging(map);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(BusinessException b) {
			throw new BusinessException(b.getMessage());
		}catch(Exception e){
			logger.error("应付/应收账龄分析错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@ApiOperation(value = "应付/应收账龄分析打印数据", notes = "获取打印数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@GetMapping(value = "/getPrintAging")
	public Result<List<AgingGroupVO>> getPrintAging(@ApiIgnore UserVO userVO,
			@ApiParam(name = "accountType", value = "0:应付,1:应收", required = true) @RequestParam(required = true)Integer accountType,
			@ApiParam(name = "startDate", value = "开始日期", required = true) @RequestParam(required = true)Date startDate,
			@ApiParam(name = "endDate", value = "结束日期", required = true) @RequestParam(required = true)Date endDate,
			@ApiParam(name = "companyCode", value = "购货单位编码", required = false) @RequestParam(required = false)String companyCode,
			@ApiParam(name = "companyName", value = "购货单位名称", required = false) @RequestParam(required = false)String companyName){
		Result<List<AgingGroupVO>> result = new Result<>();
		try{
			Map<String,Object> map = setParam(1, userVO,null,startDate,endDate, companyCode, companyName, accountType);
			List<AgingGroupVO> list = agingService.groupData(map);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
		}catch(BusinessException b) {
			throw new BusinessException(b.getMessage());
		}catch(Exception e){
			logger.error("应付/应收账龄分析错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@ApiOperation(value = "导出应付/应收账龄分析", notes = "导出数据 | 开发者 yuting.li | 已联调")
	@GetMapping(value = "/excelExport", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void excelExport(HttpServletRequest request, HttpServletResponse response,@ApiIgnore UserVO userVO,
    		@ApiParam(name = "accountType", value = "0:应付,1:应收", required = true) @RequestParam(required = true)Integer accountType,
    		@ApiParam(name = "startDate", value = "开始日期", required = true) @RequestParam(required = true)Date startDate,
			@ApiParam(name = "endDate", value = "结束日期", required = true) @RequestParam(required = true)Date endDate,
			@ApiParam(name = "companyCodes", value = "购货单位编码", required = false) @RequestParam(required = false)String companyCode,
			@ApiParam(name = "companyName", value = "购货单位名称", required = false) @RequestParam(required = false)String companyName) throws Exception {
        String name = "应付账龄分析";
        if(accountType == 1) {
        	name = "应收账龄分析";
        }
        try {
	        response.setContentType("application/msexcel;charset=utf-8");
	        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
	        //输出Excel文件
	        OutputStream output = response.getOutputStream();
	        Map<String,Object> map = setParam(1, userVO,null,startDate,endDate, companyCode, companyName, accountType);
	        agingService.exportExcel(output, map);
            output.close();
            output.flush();
        } catch (Exception e) {
        	logger.error("导出应付/应收账龄分析错误:"+e.getMessage(),e);
            e.printStackTrace();
        }
    }
	
	private Map<String,Object> setParam(int opType,UserVO userVO,Page<?> page,Date startDate,Date endDate,String companyCode,String companyName,Integer accountType){
		Map<String,Object> map = new HashMap<>(10);
		if(accountType != 0 && accountType != 1) {
			throw new BusinessException("accountType参数不正确，0:应付,1:应收");
		}
		// 分页参数
		if(opType == 0 && page != null) {
			map.put("start", page.getStart());
			map.put("pageSize", page.getPageSize());
		}
		map.put("chainType", userVO.getChainType());
		map.put("enterpriseId", userVO.getEnterpriseId());
		map.put("parentId", userVO.getParentId());
		map.put("startDate", startDate != null ? DateUtils.DateToString(startDate, DateStyle.YYYY_MM_DD) : null);
		map.put("endDate", endDate != null ? DateUtils.DateToString(endDate, DateStyle.YYYY_MM_DD) : null);
		map.put("companyCode", StringUtils.isNotBlank(companyCode) ? companyCode.trim() : null);
		map.put("companyName", StringUtils.isNotBlank(companyName) ? companyName.trim() : null);
		// 应付：220201,应收:112201
		map.put("accountType", accountType);
		return map;
	}

}
