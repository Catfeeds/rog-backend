package com.rograndec.feijiayun.chain.business.goods.sale.controller;

import com.rograndec.feijiayun.chain.business.goods.sale.service.SpecialPriceGoodsService;
import com.rograndec.feijiayun.chain.business.goods.sale.vo.SpecialPriceGoodsVO;
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
@Api(value = "goods_sale_special_goods", description = "商品管理-销售管理-特价商品服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/goods/sale/special/goods")
@Validated
public class SpecialPriceGoodsContorller {
    private static final Log logger = LogFactory.getLog(SpecialPriceGoodsContorller.class);
    @Autowired
    SpecialPriceGoodsService specialPriceGoodsService;

    @ApiOperation(value="保存特价商品", notes = "保存特价商品 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveSpecialPriceGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result saveSpecialPriceGoods(HttpServletRequest request,
           @ApiParam(value = "特价商品信息集合",required = true) @RequestBody List<SpecialPriceGoodsVO> specialPriceGoodsVOs){
        Result result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            specialPriceGoodsService.saveSpecialPriceGoods(specialPriceGoodsVOs,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存特价商品成功!");
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("保存特价商品错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="修改特价商品", notes = "修改特价商品 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateSpecialPriceGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result updateSpecialPriceGoods(HttpServletRequest request,
           @ApiParam(value = "特价商品信息",required = true) @RequestBody SpecialPriceGoodsVO specialPriceGoodsVO){
        Result result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            specialPriceGoodsService.updateSpecialPriceGoods(specialPriceGoodsVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改特价商品成功!");
        }catch(Exception e){
            logger.error("修改特价商品错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="删除特价商品", notes = "删除特价商品 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteSpecialPriceGoods", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> deleteSpecialPriceGoods(HttpServletRequest request,
           @ApiParam(value = "特价商品ID",required = true) @RequestParam Long id){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            int count = specialPriceGoodsService.deleteSpecialPriceGoods(id);
            if (count>0)
                result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除特价商品成功!");
            else
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "该特价商品已经不存在于系统!");
        }catch(Exception e){
            logger.error("删除特价商品错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取特价信息", notes = "获取特价信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSpecialPriceInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Map>> getSpecialPriceInfo(HttpServletRequest request){
        Result<List<Map>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, specialPriceGoodsService.getSpecialPriceInfo(loginUser));
        }catch(Exception e){
            logger.error("获取特价信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取特价商品", notes = "获取特价商品 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSpecialgoods", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page> getSpecialgoods(HttpServletRequest request,
        @ApiParam(value = "商品的搜索信息", required = false) @RequestParam(required = false) String goodsInfo,
        @ApiParam(value = "策略ID", required = false) @RequestParam(required = false) String strategyId,
        @ApiParam(value = "排序参数", required = false) @RequestParam(required = false) String orderName,
        @ApiParam(value = "排序方式", required = false) @RequestParam(required = false) String orderType,
        @ApiParam(value = "分页参数,页码",required = true) @RequestParam Integer pageNo,
        @ApiParam(value = "分页参数,页容量",required = true) @RequestParam Integer pageSize){
            Result<Page> result = new Result<>();
            try{
                HttpSession session = request.getSession(true);
                UserVO loginUser = (UserVO) session.getAttribute("user");
                result.setBizCodeSuccessInfo(SysCode.SUCCESS, specialPriceGoodsService.getSpecialgoods(loginUser,goodsInfo,strategyId,orderName,orderType,pageNo,pageSize));
            }catch(Exception e){
                logger.error("获取特价商品错误:"+e.getMessage(),e);
                result.setBizCodeFallInfo(SysCode.FAIL);
                return result;
            }
            return result;
    }
}