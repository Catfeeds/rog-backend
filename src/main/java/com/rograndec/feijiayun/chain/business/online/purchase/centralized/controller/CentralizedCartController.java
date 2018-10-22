package com.rograndec.feijiayun.chain.business.online.purchase.centralized.controller;

import com.rograndec.feijiayun.chain.business.online.purchase.centralized.service.CentralizedCartService;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartBusiness2GoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.PlaceOrderDataVO;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api(value = "online_pruchase_centralized_cart", description = "集采管理-购物车服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/online/pruchase/centralized/cart")
@Validated
public class CentralizedCartController {

    private static final Log logger = LogFactory.getLog(CentralizedCartController.class);

    @Autowired
    private CentralizedCartService centralizedCartService;

    @ApiOperation(value="获收购物车的数据", notes = "获收购物车的数据 | 开发者:马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCentralizedCart", method = RequestMethod.GET)
    @ResponseBody
    public Result<CentralizedCartVO> getCentralizedCart(HttpServletRequest request){
        Result<CentralizedCartVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, centralizedCartService.getCentralizedCart(loginUser));
        }catch(Exception e){
            logger.error("获收购物车的数据失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

//    @ApiOperation(value="修改购物车的数据", notes = "修改购物车的数据 | 开发者:马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
//    @RequestMapping(value = "/updateCentralizedCart", method = RequestMethod.GET)
//    @ResponseBody
//    public Result<String> updateCentralizedCart(HttpServletRequest request,
//           @ApiParam(value = "修改的供应商ID", name = "mphSupplierId", required = true) @RequestParam Long mphSupplierId,
//           @ApiParam(value = "修改的商品ID", name = "goodsId", required = true) @RequestParam String goodsId,
//           @ApiParam(value = "修改的商品数量", name = "amount", required = true) @RequestParam Integer amount){
//        Result<String> result = new Result<>();
//        try{
//            HttpSession session = request.getSession(true);
//            UserVO loginUser = (UserVO) session.getAttribute("user");
//            centralizedCartService.updateCentralizedCart(loginUser,mphSupplierId,goodsId,amount);
//            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
//        }catch (BusinessException e){
//            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
//            return result;
//        }catch(Exception e){
//            logger.error("修改购物车的数据失败:"+e.getMessage(),e);
//            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
//            return result;
//        }
//        return result;
//    }

    @ApiOperation(value="保存购物车的数据状态", notes = "保存购物车的数据状态 | 开发者:马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveCentralizedCart", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveCentralizedCart(HttpServletRequest request,
           @ApiParam(value = "购物车数据",name = "centralizedCartVO", required = true) @RequestBody CentralizedCartVO centralizedCartVO){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            centralizedCartService.saveCentralizedCart(loginUser,centralizedCartVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("保存购物车的数据状态失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }
        return result;
    }


    @ApiOperation(value="删除购物车的数据", notes = "删除购物车的数据 | 开发者:马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteCentralizedCart", method = RequestMethod.POST)
    @ResponseBody
    public Result<CentralizedCartVO> deleteCentralizedCart(HttpServletRequest request,
           @ApiParam(value = "修改参数", name = "business2GoodsVOS", required = true) @RequestBody List<CentralizedCartBusiness2GoodsVO> business2GoodsVOS){
        Result<CentralizedCartVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            result.setBizCodeSuccessInfo(SysCode.SUCCESS,centralizedCartService.deleteCentralizedCart(loginUser,business2GoodsVOS));
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("删除购物车的数据失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="去下单", notes = "去下单 | 开发者:马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/dealOrder", method = RequestMethod.POST)
    @ResponseBody
    public Result<PlaceOrderDataVO> dealOrder(HttpServletRequest request){
        Result<PlaceOrderDataVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,centralizedCartService.dealOrder(loginUser));
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
