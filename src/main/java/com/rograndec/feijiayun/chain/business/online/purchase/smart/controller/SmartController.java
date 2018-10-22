package com.rograndec.feijiayun.chain.business.online.purchase.smart.controller;

import com.netflix.discovery.converters.Auto;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartBusiness2GoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.service.SmartService;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.PlaceOrderDataVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.RequestDeleteScartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartPurchasingPlanVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingCartVO;
import com.rograndec.feijiayun.chain.business.purchase.plan.service.PurchasePlanService;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.IntellectPurchaseReqVO;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanGoodsVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Api(value = "smart_plan", description = "智能采购-采购计划", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/smart/plan")
public class SmartController {

    private static final Log logger = LogFactory.getLog(SmartController.class);

    @Autowired
    private PurchasePlanService planService;

    @Autowired
    private SmartService smartService;

    @ApiOperation(value = "智能采购订单查询", notes = "智能采购订单查询 | 开发者 苏磊 | 已联调")
    @RequestMapping(value = "/selectSmartPlan", method = RequestMethod.POST)
    public Result<List<SmartPurchasingPlanVO>> selectSmartPlan (@ApiIgnore UserVO userVO) {
        Result<List<SmartPurchasingPlanVO>> result = new Result<>();
        try{
            List<SmartPurchasingPlanVO> list = smartService.selectSmartPlan(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
            logger.error("智能采购订单查询:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "智能采购", notes = "智能采购 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/intelligentPurchase", method = RequestMethod.POST)
    public Result<List<SmartPurchasingPlanVO>> intelligentPurchase(@RequestBody IntellectPurchaseReqVO intellectPurchaseReqVO,
                                                                 HttpServletRequest request, @ApiIgnore UserVO userVO) throws Exception {
        Result<List<SmartPurchasingPlanVO>> result = new Result<>();
        List<PurchasePlanGoodsVO> purchasePlanGoodsVOS = planService.intelligentPurchase(intellectPurchaseReqVO, userVO);
        /**
         * 转换VO并且去重商品
         */
        List<SmartPurchasingPlanVO> list = smartService.convertToListWithDistinct(purchasePlanGoodsVOS,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
        return result;
    }

    @ApiOperation(value = "查询商品", notes = "查询商品 | 开发者:苏磊 | 已联调")
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.GET)
    public Result<List<PurchasePlanGoodsVO>> getGoodsList(HttpServletRequest request,@ApiParam(value = "搜索关键字",required = true) @RequestParam("param") String param){

        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<List<PurchasePlanGoodsVO>> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS ,planService.getGoodsList(param,userVO));
        return result;
    }

    @ApiOperation(value = "新增普通查询出来的商品", notes = "新增普通查询出来的商品 | 开发者 苏磊 | 已联调")
    @RequestMapping(value = "/insertSerachGoodsList", method = RequestMethod.POST)
    public Result<List<SmartPurchasingPlanVO>> insertSerachGoodsList(@RequestBody List<SmartPurchasingPlanVO> list,@ApiIgnore UserVO userVO) throws Exception {
        Result<List<SmartPurchasingPlanVO>> result = new Result<>();
        try{
            List<SmartPurchasingPlanVO> returnList = smartService.insertSerachGoodsList(list,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,returnList);
        }catch(BusinessException e){
            logger.error("新增普通查询出来的商品:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("新增普通查询出来的商品:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "修改商品数量", notes = "修改商品数量 | 开发者 苏磊 | 已联调")
    @RequestMapping(value = "/updateGoodsCount", method = RequestMethod.GET)
    public Result<List<SmartPurchasingPlanVO>> updateGoodsCount(@ApiParam(value = "记录ID", required = true) @RequestParam Long tid,
                                           @ApiParam(value = "修改后数量", required = true) @RequestParam BigDecimal quantity,
                                           @ApiIgnore UserVO userVO) throws Exception {
        Result<List<SmartPurchasingPlanVO>> result = new Result<>();
        try{
            List<SmartPurchasingPlanVO> list = smartService.updateGoodsCount(tid,quantity,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
        }catch(Exception e){
            logger.error("修改商品数量:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "批量删除商品", notes = "批量删除商品 | 开发者 苏磊 | 已联调")
    @RequestMapping(value = "/deleteGoodsList", method = RequestMethod.POST)
    public Result<List<SmartPurchasingPlanVO>> deleteGoodsList(@ApiParam(value = "记录ID集合", required = true) @RequestBody List<Long> list,
                                           @ApiIgnore UserVO userVO) throws Exception {
        Result<List<SmartPurchasingPlanVO>> result = new Result<>();
        try{
            List<SmartPurchasingPlanVO> returnList = smartService.deleteGoodsList(list,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,returnList);
        }catch(Exception e){
            logger.error("修改商品数量:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "提交购物车", notes = "提交购物车 | 开发者 苏磊 | 已联调")
    @RequestMapping(value = "/submitCart", method = RequestMethod.POST)
    public Result<Object> submitCart(@ApiParam(value = "记录ID集合", required = true) @RequestBody List<Long> list,
                                          @ApiIgnore UserVO userVO) throws Exception {
        Result<Object> result = new Result<>();
        try{
            smartService.submitCart(list,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(Exception e){
            logger.error("提交购物车:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="获收购物车的数据", notes = "获收购物车的数据 | 开发者:苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSmartCart", method = RequestMethod.GET)
    @ResponseBody
    public Result<SmartSourcingCartVO> getSmartCart(HttpServletRequest request){
        Result<SmartSourcingCartVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            SmartSourcingCartVO cartVO = smartService.getSmartCart(loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,cartVO);
        }catch(Exception e){
            logger.error("获收购物车的数据失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="删除(移除)购物车的数据", notes = "删除(移除)购物车的数据 | 开发者:苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteSmartCart", method = RequestMethod.POST)
    @ResponseBody
    public Result<SmartSourcingCartVO> deleteSmartCart(HttpServletRequest request,
                                                           @ApiParam(value = "修改参数", name = "requestDeleteScartVO", required = true) @RequestBody List<RequestDeleteScartVO> requestDeleteScartVO){
        Result<SmartSourcingCartVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            result.setBizCodeSuccessInfo(SysCode.SUCCESS,smartService.deleteSmartCart(loginUser,requestDeleteScartVO));
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("删除(移除)购物车的数据失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="修改购物车的数据", notes = "修改购物车的数据 | 开发者:苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateSmartCart", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateSmartCart(HttpServletRequest request,
                                          @ApiParam(value = "购物车数据",name = "smartSourcingCartVO", required = true) @RequestBody SmartSourcingCartVO smartSourcingCartVO){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            smartService.updateSmartCart(loginUser,smartSourcingCartVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("修改购物车的数据失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="去下单", notes = "去下单 | 开发者:苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/dealOrder", method = RequestMethod.POST)
    @ResponseBody
    public Result<PlaceOrderDataVO> dealOrder(HttpServletRequest request){
        Result<PlaceOrderDataVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,smartService.dealOrder(loginUser));
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
    }catch(Exception e){
        logger.error("下单失败:"+e.getMessage(),e);
        result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
        return result;
    }
        return result;
    }



}
