package com.rograndec.feijiayun.chain.business.distr.parent.controller;

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
@Api(value = "distr_return",description = "配送管理-总部-配后退货-配后退回单接口服务")
@RestController
@RequestMapping("distr/return")
@Validated
public class DistrReturnController {
	
	@ApiOperation(value = "查询配后退回单列表", notes = "查询配后退回单列表 | 开发者 李中义 | 开发中")
    @RequestMapping(value="/getDistrReturnOrderList", method=RequestMethod.GET)
    public void getDistrReturnOrderList(){}
	
	@ApiOperation(value = "查询配后退回单明细列表", notes = "查询配后退回单明细列表 | 开发者 李中义 | 开发中")
    @RequestMapping(value="/getDistrReturnOrderDtlList", method=RequestMethod.GET)
    public void getDistrReturnOrderDtlList(){}
	
	@ApiOperation(value = "导出Excel", notes = "按照模版将配货退回单信息导出至Excel | 开发者 李中义 | 开发中")
    @RequestMapping(value="/exportExcel", method=RequestMethod.GET)
    public void exportExcel(){}
	
}
