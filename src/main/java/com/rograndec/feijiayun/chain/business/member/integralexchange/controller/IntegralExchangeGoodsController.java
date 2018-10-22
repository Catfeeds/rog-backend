package com.rograndec.feijiayun.chain.business.member.integralexchange.controller;

import com.rograndec.feijiayun.chain.business.member.integralexchange.service.IntegralExchangeGoodsService;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeGoodsRequestVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeGoodsVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.StockGoodsVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author dongyang.du 2017-09-19 15:52:00
 */
@Api(value = "member-integralExchange", description = "会员管理-积分兑换-积分商品", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/member/integralExchangeGoods")
public class IntegralExchangeGoodsController {

    private static final Log logger = LogFactory.getLog(IntegralExchangeGoodsController.class);


    @Autowired
    private IntegralExchangeGoodsService exchangeGoodsService;

    @ApiOperation(value = "搜索积分商品", notes = "搜索积分商品 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/searchIntegralGoods", method = RequestMethod.GET)
    public Result<List<IntegralExchangeGoodsVO>> search(@ApiParam(value = "页面输入框，允许按商品编码、条形码精确检索，按商品名称、通用名称、批准文号模糊检索", required = false)
                                                        @RequestParam(required = false) String key, HttpServletRequest request) {


        Result<List<IntegralExchangeGoodsVO>> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, exchangeGoodsService.searchIntegralGoods(key, loginUser));

        return result;


    }


    @ApiOperation(value = "搜索商品带库存", notes = "搜索商品带库存 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/searchStockGoods", method = RequestMethod.GET)
    public Result<List<StockGoodsVO>> searchStockGoods(HttpServletRequest request, @ApiParam(value = "页面输入框，允许按商品编码、条形码精确检索，按商品名称、通用名称、批准文号模糊检索", required = false)
    @RequestParam(required = false) String key) {

        Result<List<StockGoodsVO>> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, exchangeGoodsService.searchStockGoodsVO(key, loginUser));

        return result;


    }


    @ApiOperation(value = "获取积分商品列表", notes = "获取积分商品列表 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result<List<IntegralExchangeGoodsVO>> get(HttpServletRequest request) {


        Result<List<IntegralExchangeGoodsVO>> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, exchangeGoodsService.selectAll(loginUser));

        return result;


    }

    @ApiOperation(value = "删除积分商品", notes = "删除积分商品 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result<String> delete(@ApiParam(value = "需要删除的积分商品ID", required = true) @RequestParam Long id) {


        Result<String> result = new Result<String>();
        exchangeGoodsService.delete(id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除积分商品成功");


        return result;
    }

    @ApiOperation(value = "新增积分商品", notes = "新增积分商品 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/batchSave", method = RequestMethod.POST)
    public Result<List<String>> batchSave(@RequestBody List<IntegralExchangeGoodsRequestVO> requestVOS, HttpServletRequest request) {

        Result<List<String>> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");


        result.setBizCodeSuccessInfo(SysCode.SUCCESS, exchangeGoodsService.batchSave(requestVOS, loginUser));

        return result;


    }
}