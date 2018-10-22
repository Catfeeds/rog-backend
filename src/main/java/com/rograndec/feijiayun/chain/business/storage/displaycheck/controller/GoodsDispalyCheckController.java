package com.rograndec.feijiayun.chain.business.storage.displaycheck.controller;

import com.rograndec.feijiayun.chain.business.storage.displaycheck.service.GoodsDispalyCheckService;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.vo.GoodsDisplayCheckInfoVO;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.vo.ShowDisplayCheckVO;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.vo.ShowGoodsDisplayCheckVO;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.vo.GoodsDisplayCheckDetailVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;
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
@Api(value = "storage_displayCheck",description = "储存管理-陈列检查")
@RestController
@RequestMapping("storage/displayCheck")
@Validated
public class GoodsDispalyCheckController {

    private static final Log logger = LogFactory.getLog(GoodsDispalyCheckController.class);

    @Autowired
    GoodsDispalyCheckService goodsDispalyCheckService;

	@ApiOperation(value = "保存陈列检查单", notes = "保存陈列检查单（创建检查单据） | 开发者:马东 | 已完成")
    @RequestMapping(value="/saveDisplayCheckOrder", method=RequestMethod.POST)
    public Result<String> saveDisplayCheckOrder(HttpServletRequest request,
           @ApiParam(value = "待保存陈列检查单据",name = "goodsDisplayCheckInfoVO",required = true) @RequestBody GoodsDisplayCheckInfoVO goodsDisplayCheckInfoVO){
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            goodsDispalyCheckService.saveDisplayCheckOrder(loginUser,goodsDisplayCheckInfoVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"保存养护单成功");
        }catch (Exception e){
            logger.error("查询养护措施列表/养护结论列表/处置措施列表/问题原因列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;}

	@ApiOperation(value = "取消陈列检查单", notes = "取消陈列检查单（取消） | 开发者:马东 | 已完成")
    @RequestMapping(value="/cancelDisplayCheckOrder", method=RequestMethod.GET)
    public Result<String> cancelDisplayCheckOrder(HttpServletRequest request,
           @ApiParam(value = "待取消的陈列检查单id",required = true) @RequestParam Long id){
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            goodsDispalyCheckService.cancelDisplayCheckOrder(loginUser,id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"取消成功");
        }catch (Exception e){
            logger.error("取消陈列检查单错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "完成陈列检查", notes = "完成陈列检查 | 开发者:马东 | 已完成")
    @RequestMapping(value="/finishCheckOrder", method=RequestMethod.POST)
    public Result<String> finishCheckOrder(HttpServletRequest request,
//           @ApiParam(value = "待完成陈列检查总单ID",name = "id",required = true) @RequestParam Long id,
           @ApiParam(value = "待完成检查细单集合",name = "goodsMaintanceDetailVOS",required = true) @RequestBody List<GoodsDisplayCheckDetailVO> goodsDisplayCheckDetailVOS){
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            goodsDispalyCheckService.finishCheckOrder(loginUser,goodsDisplayCheckDetailVOS);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"完成陈列检查成功");
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch (Exception e){
            logger.error("完成陈列检查错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询待检查单据列表", notes = "根据状待检查养护单据列表 | 开发者:马东 | 已完成")
    @RequestMapping(value="/getWillCheckOrderList", method=RequestMethod.GET)
    public Result<Page<List<ShowDisplayCheckVO>>> getWillCheckOrderList(HttpServletRequest request,
           @ApiParam(value = "待排序字段名",name = "orderName") @RequestParam(required = false) String orderName,
           @ApiParam(value = "排序方式",name = "orderType") @RequestParam(required = false) String orderType,
           @ApiParam(value = "起始日期",name = "startDate") @RequestParam(required = false) Date startDate,
           @ApiParam(value = "截止日期",name = "endDate") @RequestParam(required = false) Date endDate,
           @ApiParam(value = "库区ID",name = "warehouseAreaId") @RequestParam(required = false) Long warehouseAreaId,
           @ApiParam(value = "养护类型",name = "maintanceType") @RequestParam(required = false) Integer maintanceType,
           @ApiParam(value = "药品类型",name = "goodsType",required = true) @RequestParam Integer goodsType,
           @ApiParam(value = "页容量",name = "pageSize",required = true) @RequestParam Integer pageSize,
           @ApiParam(value = "页码",name = "pageNo",required = true) @RequestParam Integer pageNo){

        Result<Page<List<ShowDisplayCheckVO>>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsDispalyCheckService.getWillCheckOrderList(loginUser,orderName,orderType,startDate,endDate,warehouseAreaId,maintanceType,goodsType,pageNo,pageSize));
        }catch (Exception e){
            logger.error("查询待检查单据列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "查询陈列检查单列表", notes = "根据状态查询陈列检查单列表 | 开发者:马东 | 已完成")
    @RequestMapping(value="/getDisplayCheckOrderList", method=RequestMethod.GET)
    public Result<Page<List<ShowGoodsDisplayCheckVO>>> getDisplayCheckOrderList(HttpServletRequest request,
           @ApiParam(value = "养护状态 0:计划中,1:养护中,2:已完成,3:已取消",name = "checkOrderType",required = true) @RequestParam Integer checkOrderType,
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
        Result<Page<List<ShowGoodsDisplayCheckVO>>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsDispalyCheckService.getDisplayCheckOrderList(loginUser,checkOrderType,orderName,orderType,startDate,endDate,warehouseAreaId,maintanceType,goodsType,code,pageNo,pageSize));
        }catch (Exception e){
            logger.error("查询陈列检查单列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }
	
	@ApiOperation(value = "查询陈列检查单明细列表", notes = "根据陈列检单ID查询陈列检查单据明细列表 | 开发者:马东 | 已完成")
    @RequestMapping(value="/getDisplayCheckOrderDtlList", method=RequestMethod.GET)
    public Result<List<GoodsDisplayCheckDetailVO>> getDisplayCheckOrderDtlList(HttpServletRequest request,
           @ApiParam(value = "养护单ID",name = "ids",required = true) @RequestParam String ids){
//           @ApiParam(value = "养护状态ID 0:计划中,1:养护中,2:已完成,3:已取消",name = "checkOrderType",required = true) @RequestParam Integer checkOrderType,
//           @ApiParam(value = "养护类型ID",name = "maintanceType",required = true) @RequestParam Integer maintanceType,
//           @ApiParam(value = "库区ID",name = "warehouseAreaId",required = true) @RequestParam Long warehouseAreaId,
//           @ApiParam(value = "药品类型ID",name = "goodsType",required = true) @RequestParam Integer goodsType){
        Result<List<GoodsDisplayCheckDetailVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
//            result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsDispalyCheckService.getDisplayCheckOrderDtlList(loginUser,ids,checkOrderType,maintanceType,warehouseAreaId,goodsType));
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsDispalyCheckService.getDisplayCheckOrderDtlList(loginUser,ids));
        }catch (Exception e){
            logger.error("查询陈列检查单明细列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }
	
	@ApiOperation(value = "导出Excel", notes = "按照模版将陈列检查单导出至Excel | 开发者:马东 | 开发中")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前导出的陈列检查单据ID", required = true, dataType = "long", paramType="path")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            StringBuilder name = new StringBuilder();
            name.append("陈列检查");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name.toString(), "UTF-8")+".xlsx");
            goodsDispalyCheckService.exportExcel(output,loginUser,id);
        }catch(Exception e){
            logger.error("导出Excel错误:"+e.getMessage(),e);
        }

    }
	
	
}
