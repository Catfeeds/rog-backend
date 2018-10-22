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
@Api(value = "report_storage_historyStock",description = "报表-储存管理-历史库存")
@RestController
@RequestMapping("report/storage/historyStock")
@Validated
public class HistoryStockReportController {

	@ApiOperation(value = "查询组织机构列表", notes = "查询组织机构列表 | 开发者 李中义 | 开发中")
    @RequestMapping(value="/getEnterpriseList", method=RequestMethod.GET)
    public void getEnterpriseList(){}
	
	@ApiOperation(value = "查询历史库存列表", notes = "查询历史库存列表 | 开发者 李中义 | 开发中")
    @RequestMapping(value="/queryOnWayStockList", method=RequestMethod.GET)
    public void queryOnWayStockList(){}
	
	@ApiOperation(value = "导出Excel", notes = "按照模版将历史库存列表导出至Excel | 开发者 李中义 | 开发中")
    @RequestMapping(value="/exportExcel", method=RequestMethod.GET)
    public void exportExcel(){}
	
}
