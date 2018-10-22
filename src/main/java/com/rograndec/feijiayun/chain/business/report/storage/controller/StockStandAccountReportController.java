package com.rograndec.feijiayun.chain.business.report.storage.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_storage_stockStandAccount",description = "报表-储存管理-进销存台账")
@RestController
@RequestMapping("report/storage/stockStandAccount")
@Validated
public class StockStandAccountReportController {

	@ApiOperation(value = "查询组织机构列表", notes = "查询组织机构列表 | 开发者 李中义 | 开发中")
    @RequestMapping(value="/getEnterpriseList", method=RequestMethod.GET)
    public void getEnterpriseList(){}
	
	@ApiOperation(value = "查询进销存台账列表", notes = "查询进销存台账列表 | 开发者 李中义 | 开发中")
    @RequestMapping(value="/queryStockStandAccountList", method=RequestMethod.GET)
    public void queryStockStandAccountList(){}
	
	@ApiOperation(value = "导出Excel", notes = "按照模版将进销存台账列表导出至Excel | 开发者 李中义 | 开发中")
    @RequestMapping(value="/exportExcel", method=RequestMethod.GET)
    public void exportExcel(){}
	
}
