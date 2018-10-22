package com.rograndec.feijiayun.chain.business.purchase.addinstorage.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.purchase.addinstorage.service.PurchaseAddInStorageService;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.AddInstorageVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.PurchaseOrderDataVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.PurchaseOrderGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @ClassName: PurchaseAddInStorageController   
 * @Description: 采购入库-新增调用操作
 * @author yuting.li
 * @version 1.0 
 * @date 2017年11月29日 上午10:54:34
 */
@Validated
@Api(value = "purchase_order",description = "采购入库-新增调用操作",produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/instorage/operation")
public class PurchaseAddInStorageController {
	
	private static Logger logger = LoggerFactory.getLogger(PurchaseAddInStorageController.class);
	
	@Autowired
	private PurchaseAddInStorageService purchaseAddInStorageService;
	
	@ApiOperation(value="采购入库-新增", notes = "采购入库-新增 | 开发者 yuting.li | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<String>> saveInstorage(@ApiIgnore UserVO userVO,@Valid @RequestBody AddInstorageVO addInstorageVO){
		Long start = System.currentTimeMillis();
		Result<List<String>> result = new Result<>();
		try{
			List<String> msgList = purchaseAddInStorageService.saveReturnMsg(userVO, addInstorageVO);
			logger.debug("采购入库-新增保存接口耗时："+(System.currentTimeMillis() - start)+"ms");
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, msgList);
		}catch(BusinessException b) {
			throw new BusinessException(b.getMessage());
		}catch(Exception e){
			logger.error("采购入库-新增数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@ApiOperation(value="采购入库-调用入库单", notes = "采购入库-调用入库单 | 开发者 yuting.li | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPurchaseOrder", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page<List<PurchaseOrderDataVO>>> getPurchaseOrder(@ApiIgnore UserVO userVO,
			@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
            @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
            @ApiParam(value = "起始时间", required = false) @RequestParam(required = false) Date startTime,
            @ApiParam(value = "终止时间", required = false) @RequestParam(required = false) Date endTime){
		Result<Page<List<PurchaseOrderDataVO>>> result = new Result<>();
		if(pageNo <= 0 || pageSize <= 0){
			result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,null);
			return result;
    	}
		try{
			Page<?> page = new Page<>(pageNo, pageSize);
			Page<List<PurchaseOrderDataVO>> pageData = purchaseAddInStorageService.getPurchaseOrder(page, userVO, startTime, endTime);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, pageData);
		}catch(Exception e){
			logger.error("采购入库-调用入库单数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@ApiOperation(value="采购入库-调用入库单-获取商品", notes = "采购入库-调用入库单-获取商品 | 开发者 yuting.li | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getGoodsVO", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page<List<PurchaseOrderGoodsVO>>> getGoodsVO(@ApiIgnore UserVO userVO,
			@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
            @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
			@ApiParam(value = "入库单ID", required = true) @RequestParam Long orderId){
		Result<Page<List<PurchaseOrderGoodsVO>>> result = new Result<>();
		if(pageNo <= 0 || pageSize <= 0){
			result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,null);
			return result;
    	}
		try{
			Page<?> page = new Page<>(pageNo, pageSize);
			Page<List<PurchaseOrderGoodsVO>> pageData = purchaseAddInStorageService.getGoodsVO(page, userVO, orderId);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, pageData);
		}catch(BusinessException b) {
			throw new BusinessException(b.getMessage());
		}catch(Exception e){
			logger.error("采购入库-调用入库单-获取商品数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@ApiOperation(value="采购入库-调用入库单-新增", notes = "采购入库-调用入库单-新增 | 开发者 yuting.li | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/saveByPurchaseOrder", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<String>> saveByPurchaseOrder(@ApiIgnore UserVO userVO,@Valid @RequestBody AddInstorageVO addInstorageVO){
		Long start = System.currentTimeMillis();
		Result<List<String>> result = new Result<>();
		try{
			List<String> msgList = purchaseAddInStorageService.saveByPurchaseOrderReturnMsg(userVO, addInstorageVO);
			logger.debug("采购入库-调用入库单-新增保存接口耗时："+(System.currentTimeMillis() - start)+"ms");
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, msgList);
		}catch(BusinessException b) {
			throw new BusinessException(b.getMessage());
		}catch(Exception e){
			logger.error("采购入库-调用入库单-新增数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

}
