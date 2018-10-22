package com.rograndec.feijiayun.chain.business.online.purchase.smart.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.rograndec.feijiayun.chain.business.online.purchase.smart.service.SmartSpecificPriceService;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.ManualSearchQueryVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SelectMphSupplierRequestVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSpecificPriceVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


@Api(value = "smartSpecificPrice", description = "智能采购-购物车-匹配供应商/全网比价", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/smartSpecificPrice")
public class SmartSpecificPriceController {
	
	private static final Log logger = LogFactory.getLog(SmartSpecificPriceController.class);
	
	@Autowired
    private SmartSpecificPriceService smartSpecificPriceService;
	
	@ApiOperation(value = "全网比价/匹配供应商接口", notes = "全网比价/匹配供应商接口 | 开发者 刘群 | 已联调")
    @RequestMapping(value = "/selectSmartSpecificPrice", method = RequestMethod.GET)
    public Result<List<SmartSpecificPriceVO>> selectSmartSpecificPrice (@ApiIgnore UserVO userVO,
    		@ApiParam(value = "供应商ID", required = true) @RequestParam(required=false) Long supplierId,
    		@ApiParam(value = "商品ID", required = true) @RequestParam(required=false) Long goodsId,
    		@ApiParam(value = "MPH商品ID", required = true) @RequestParam(required=false) Long gId) {
        Result<List<SmartSpecificPriceVO>> result = new Result<>();
        try{
            List<SmartSpecificPriceVO> list = smartSpecificPriceService.selectSmartSpecificPrice(userVO, supplierId, goodsId, gId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
            logger.error("全网比价/匹配供应商接口异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	
	@ApiOperation(value = "手动搜索接口", notes = "手动搜索接口 | 开发者 刘群 | 已联调")
    @RequestMapping(value = "/selectManualSearch", method = RequestMethod.POST)
    public Result<Page<List<SmartSpecificPriceVO>>> selectManualSearch (@ApiIgnore UserVO userVO,
    		@RequestBody ManualSearchQueryVO vo) {
        Result<Page<List<SmartSpecificPriceVO>>> result = new Result<>();
        try{
        	Page page = smartSpecificPriceService.selectManualSearch(userVO, vo);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("手动搜索接口异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	
	@ApiOperation(value = "选择MPH供应商接口", notes = "选择MPH供应商接口 | 开发者 刘群 | 已联调")
    @RequestMapping(value = "/selectMphSupplier", method = RequestMethod.POST)
    public Result<String> selectMphSupplier (@ApiIgnore UserVO userVO, 
    		@RequestBody SelectMphSupplierRequestVO vo) {
        Result<String> result = new Result<>();
        try{
        	String msg = smartSpecificPriceService.selectMphSupplier(userVO, vo);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, msg);
        }catch(Exception e){
            logger.error("选择MPH供应商接口异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	
}
