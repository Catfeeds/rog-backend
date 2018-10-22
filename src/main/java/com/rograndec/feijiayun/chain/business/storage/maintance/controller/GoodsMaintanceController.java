package com.rograndec.feijiayun.chain.business.storage.maintance.controller;

import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseAreaVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.business.storage.maintance.service.GoodsMaintanceService;
import com.rograndec.feijiayun.chain.business.storage.maintance.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "storage_goodsMaintance",description = "储存管理-药品养护")
@RestController
@RequestMapping("storage/goodsMaintance")
@Validated
public class GoodsMaintanceController {

    private static final Log logger = LogFactory.getLog(GoodsMaintanceController.class);

    @Autowired
    GoodsMaintanceService goodsMaintanceService;

	@ApiOperation(value = "查询库区列表", notes = "查询库区列表 | 开发者:马东 | 已联调")
    @RequestMapping(value="/getWarehouseAreaList", method=RequestMethod.GET)
    public Result<List<WarehouseAreaVO>> getWarehouseAreaList(HttpServletRequest request){
        Result<List<WarehouseAreaVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsMaintanceService.getWarehouseAreaList(loginUser));
        }catch (Exception e){
            logger.error("查询库区列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "查询商品库存列表", notes = "查询商品库存列表 | 开发者:马东 | 已联调")
    @RequestMapping(value="/getGoodsStockList", method=RequestMethod.GET)
    public Result<List<SelectMaintanceGoodsVO>> getGoodsStockList(HttpServletRequest request,
           @ApiParam(name = "param", value = "检索条件:商品编码/条形码/检索码/名称/通用名称/批准文号") @RequestParam(required = false)String param,
           @ApiParam(name = "warehouseAreaId", value = "库区ID",required = true) @RequestParam Long warehouseAreaId,
           @ApiParam(value = "养护类型ID",name = "maintanceType") @RequestParam(required = false) Integer maintanceType,
           @ApiParam(value = "药品类型ID",name = "goodsType") @RequestParam(required = false) Integer goodsType,
           @ApiParam(value = "养护 :0 陈列检查 : 1",name = "type",required = true) @RequestParam Integer type){
        Result<List<SelectMaintanceGoodsVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsMaintanceService.getGoodsStockList(loginUser,param,warehouseAreaId,maintanceType,goodsType,type));
        }catch (Exception e){
            logger.error("查询商品库存列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "查询养护措施列表/养护结论列表/处置措施列表/问题原因列表", notes = "查询养护措施列表/养护结论列表/处置措施列表/问题原因列表 | 开发者:马东 | 已联调")
    @RequestMapping(value="/getMaintanceMeasuresList", method=RequestMethod.GET)
    public Result<List<QualitySetVO>> getMaintanceMeasuresList(HttpServletRequest request,
           @ApiParam(value = "查询类型:养护措施:7 养护结论:6 处置措施:3 问题原因:1",required = true) @RequestParam Integer setType,
           @ApiParam(value = "养护 :0 陈列检查 : 1",name = "type",required = true) @RequestParam Integer type){
        Result<List<QualitySetVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsMaintanceService.getMaintanceMeasuresList(loginUser,setType,type));
        }catch (Exception e){
            logger.error("查询养护措施列表/养护结论列表/处置措施列表/问题原因列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

	@ApiOperation(value = "保存养护单", notes = "保存养护单（创建养护单据） | 开发者:马东 | 已联调")
    @RequestMapping(value="/saveMaintanceOrder", method=RequestMethod.POST)
    public Result<String> saveMaintanceOrder(HttpServletRequest request,
           @ApiParam(value = "待保存养护单据",name = "goodsMaintacneInfoVO",required = true) @RequestBody GoodsMaintacneInfoVO goodsMaintacneInfoVO){
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            goodsMaintanceService.saveMaintanceOrder(loginUser,goodsMaintacneInfoVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"保存养护单成功");
        }catch (Exception e){
            logger.error("保存养护单错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "完成养护单", notes = "完成养护单 | 开发者:马东 | 已联调")
    @RequestMapping(value="/finishMaintanceOrder", method=RequestMethod.POST)
    public Result<String> finishMaintanceOrder(HttpServletRequest request,
//           @ApiParam(value = "待完成养护总单ID",name = "id",required = true) @RequestParam Long id,
           @ApiParam(value = "待完成养护细单集合",name = "goodsMaintanceDetailVOS",required = true) @RequestBody List<GoodsMaintanceDetailVO> goodsMaintanceDetailVOS){
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
//            goodsMaintanceService.finishMaintanceOrder(loginUser,id,detailIds);
            goodsMaintanceService.finishMaintanceOrder(loginUser,goodsMaintanceDetailVOS);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"完成养护单成功");
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch (Exception e){
            logger.error("完成养护单错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "取消养护单", notes = "取消养护单（取消） | 开发者:马东 | 已联调")
    @RequestMapping(value="/cancelMaintanceOrder", method=RequestMethod.GET)
    public Result<String> cancelMaintanceOrder(HttpServletRequest request,
           @ApiParam(value = "待取消的养护单id",required = true) @RequestParam Long id){
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            goodsMaintanceService.cancelMaintanceOrder(loginUser,id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"取消成功");
        }catch (Exception e){
            logger.error("取消养护单错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询待养护单据列表", notes = "根据状态查询养护单据列表 | 开发者:马东 | 已联调")
    @RequestMapping(value="/getWillMaintanceOrderList", method=RequestMethod.GET)
    public Result<Page<List<ShowMaintanceVO>>> getWillMaintanceOrderList(HttpServletRequest request,
           @ApiParam(value = "待排序字段名",name = "orderName") @RequestParam(required = false) String orderName,
           @ApiParam(value = "排序方式",name = "orderType") @RequestParam(required = false) String orderType,
           @ApiParam(value = "起始日期",name = "startDate") @RequestParam(required = false) Date startDate,
           @ApiParam(value = "截止日期",name = "endDate") @RequestParam(required = false) Date endDate,
           @ApiParam(value = "库区ID",name = "warehouseAreaId") @RequestParam(required = false) Long warehouseAreaId,
           @ApiParam(value = "养护类型",name = "maintanceType") @RequestParam(required = false) Integer maintanceType,
           @ApiParam(value = "药品类型",name = "goodsType",required = true) @RequestParam Integer goodsType,
           @ApiParam(value = "页容量",name = "pageSize",required = true) @RequestParam Integer pageSize,
           @ApiParam(value = "页码",name = "pageNo",required = true) @RequestParam Integer pageNo){

        Result<Page<List<ShowMaintanceVO>>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsMaintanceService.getWillMaintanceOrderList(loginUser,orderName,orderType,startDate,endDate,warehouseAreaId,maintanceType,goodsType,pageNo,pageSize));
        }catch (Exception e){
            logger.error("查询养护单据列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询养护单据列表", notes = "根据状态查询养护单据列表 | 开发者:马东 | 已联调")
    @RequestMapping(value="/getMaintanceOrderList", method=RequestMethod.GET)
    public Result<Page<List<ShowGoodsMaintanceVO>>> getMaintanceOrderList(HttpServletRequest request,
           @ApiParam(value = "养护状态 1:养护中,2:已完成,3:已取消",name = "maintanceOrderType",required = true) @RequestParam Integer maintanceOrderType,
           @ApiParam(value = "待排序字段名",name = "orderName") @RequestParam(required = false) String orderName,
           @ApiParam(value = "排序方式",name = "orderType") @RequestParam(required = false) String orderType,
           @ApiParam(value = "起始日期",name = "startDate") @RequestParam(required = false) Date startDate,
           @ApiParam(value = "截止日期",name = "endDate") @RequestParam(required = false) Date endDate,
           @ApiParam(value = "库区ID",name = "warehouseAreaId") @RequestParam(required = false) Long warehouseAreaId,
           @ApiParam(value = "养护类型（0-重点养护；1-常规养护）",name = "maintanceType") @RequestParam(required = false) Integer maintanceType,
           @ApiParam(value = "药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）",name = "goodsType") @RequestParam(required = false) Integer goodsType,
           @ApiParam(value = "养护单号",name = "code") @RequestParam(required = false) String code,
           @ApiParam(value = "页容量",name = "pageSize",required = true) @RequestParam Integer pageSize,
           @ApiParam(value = "页码",name = "pageNo",required = true) @RequestParam Integer pageNo){

        Result<Page<List<ShowGoodsMaintanceVO>>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsMaintanceService.getMaintanceOrderList(loginUser,maintanceOrderType,orderName,orderType,startDate,endDate,warehouseAreaId,maintanceType,goodsType,code,pageSize,pageNo));
        }catch (Exception e){
            logger.error("查询养护单据列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "查询养护单据明细列表", notes = "根据养护单ID查询养护单据明细列表 | 开发者:马东 | 已联调")
    @RequestMapping(value="/getMaintanceOrderDtlList", method=RequestMethod.GET)
    public Result<List<GoodsMaintanceDetailVO>> getMaintanceOrderDtlList(HttpServletRequest request,
           @ApiParam(value = "养护单ID",name = "ids",required = true) @RequestParam String ids){
        Result<List<GoodsMaintanceDetailVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsMaintanceService.getMaintanceOrderDtlList(loginUser,ids));
        }catch (Exception e){
            logger.error("查询养护单据明细列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "导出Excel", notes = "按照模版将养护单导出至Excel | 开发者:马东 | 已联调")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前导出的养护单据ID", required = true, dataType = "long", paramType="path")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            StringBuilder name = new StringBuilder();
            name.append("药品养护");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name.toString(), "UTF-8")+".xlsx");
            goodsMaintanceService.exportExcel(output,loginUser,id);
        }catch(Exception e){
            logger.error("导出Excel错误:"+e.getMessage(),e);
        }

    }
}
