package com.rograndec.feijiayun.chain.business.goods.sale.controller;

import com.rograndec.feijiayun.chain.business.goods.sale.service.CommissionGoodsService;
import com.rograndec.feijiayun.chain.business.goods.sale.vo.CommissionGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
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
import java.util.Map;

/**
 * Created by madong on 2017/9/5.
 */

@Api(value = "goods_sale_royalty_goods", description = "商品管理-销售管理-提成商品服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/goods/sale/royalty/goods")
@Validated
public class CommissionGoodsController {
    private static final Log logger = LogFactory.getLog(CommissionGoodsController.class);
    @Autowired
    CommissionGoodsService royaltyGoodsService;

    @ApiOperation(value="保存提成商品", notes = "保存提成商品 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveRoyaltyGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result saveRoyaltyGoods(HttpServletRequest request,
           @ApiParam(value = "提成商品信息集合",required = true) @RequestBody List<CommissionGoodsVO> commissionGoodsVOS){
        Result result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            royaltyGoodsService.saveRoyaltyGoods(commissionGoodsVOS,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存提成商品成功!");
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("保存提成商品错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="修改提成商品", notes = "修改提成商品 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateRoyaltyGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result updateRoyaltyGoods(HttpServletRequest request,
           @ApiParam(value = "提成商品信息",required = true) @RequestBody CommissionGoodsVO commissionGoodsVO){
        Result result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            royaltyGoodsService.updateRoyaltyGoods(commissionGoodsVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改提成商品成功!");
        }catch(Exception e){
            logger.error("修改提成商品错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="删除提成商品", notes = "删除提成商品 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteRoyaltyGoods", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> deleteRoyaltyGoods(HttpServletRequest request,
           @ApiParam(value = "提成商品ID",required = true) @RequestParam Long id){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            int count = royaltyGoodsService.deleteRoyaltyGoods(id);
            if (count>0)
                result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除提成商品成功!");
            else
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "该提成商品已经不存在于系统中!");
        }catch(Exception e){
            logger.error("删除提成商品错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取提成信息", notes = "获取提成信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getRoyaltyInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Map>> getRoyaltyInfo(HttpServletRequest request){
        Result<List<Map>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, royaltyGoodsService.getRoyaltyInfo(loginUser));
        }catch(Exception e){
            logger.error("获取提成信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取提成商品", notes = "获取提成商品 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getRoyaltyGoods", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page> getRoyaltyGoods(HttpServletRequest request,
           @ApiParam(value = "商品的搜索信息", required = false) @RequestParam(required=false) String goodsInfo,
           @ApiParam(value = "提成策略ID", required = false) @RequestParam(required=false) String strategyId,
           @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required=false) String orderName,
           @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required=false) String orderType,
           @ApiParam(value = "分页参数,页码",required = true) @RequestParam Integer pageNo,
           @ApiParam(value = "分页参数,页容量",required = true) @RequestParam Integer pageSize){
        Result<Page> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, royaltyGoodsService.getRoyaltyGoods(loginUser,goodsInfo,strategyId,orderName,orderType,pageNo,pageSize));
        }catch(Exception e){
            logger.error("获取提成商品错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

}