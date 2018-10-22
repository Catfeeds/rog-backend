package com.rograndec.feijiayun.chain.inf.pos.goods.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.goods.service.POSGoodsService;
import com.rograndec.feijiayun.chain.inf.pos.goods.vo.SelectPOSGoodsLotNumVO;
import com.rograndec.feijiayun.chain.inf.pos.goods.vo.SelectPOSGoodsParamVO;
import com.rograndec.feijiayun.chain.inf.pos.goods.vo.SelectPOSGoodsVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="POSGoodsController",description = "POS商品接口")
@RestController
@RequestMapping("/inf/pos/goods")
public class POSGoodsController {
	
	private static final Logger logger = LoggerFactory.getLogger(POSGoodsController.class);
	
	@Autowired
	private POSGoodsService pOSGoodsService;
	
	@ApiOperation(value = "POS商品搜索", notes = "POS商品搜索 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/searchGoods", method = RequestMethod.POST)
    public Result<List<SelectPOSGoodsVO>> searchGoods(HttpSession session,@ApiParam(required = false, value = "商品搜索")@RequestBody SelectPOSGoodsParamVO param) {
		Result<List<SelectPOSGoodsVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            param.setEnterpriseId(userVO.getEnterpriseId());
            List<SelectPOSGoodsVO> page = pOSGoodsService.selectGoods(param);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("POS商品搜索数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "POS中药商品搜索", notes = "POS中药商品搜索 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/searchGoodsAttribute", method = RequestMethod.POST)
    public Result<List<SelectPOSGoodsVO>> searchGoodsAttribute(HttpSession session,@ApiParam(required = false, value = "商品搜索")@RequestBody SelectPOSGoodsParamVO param) {
		Result<List<SelectPOSGoodsVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            param.setEnterpriseId(userVO.getEnterpriseId());
            List<SelectPOSGoodsVO> page = pOSGoodsService.selectGoodsAttribute(param);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("POS中药商品搜索数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "POS商品选择批号", notes = "POS商品选择批号 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/selectGoodsLotNum", method = RequestMethod.POST)
    public Result<List<SelectPOSGoodsLotNumVO>> selectGoodsLotNum(HttpSession session,@ApiParam(required = true, value = "商品Id")@RequestBody Long goodsId) {
		Result<List<SelectPOSGoodsLotNumVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            List<SelectPOSGoodsLotNumVO> list = pOSGoodsService.selectGoodsLotNum(userVO.getEnterpriseId(), goodsId,1);//不展示0库存
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("POS商品选择批号数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "POS商品选择批号(显示有0库存的)", notes = "POS商品选择批号(显示有0库存的) | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/selectGoodsLotNumZero", method = RequestMethod.POST)
    public Result<List<SelectPOSGoodsLotNumVO>> selectGoodsLotNumZero(HttpSession session,@ApiParam(required = true, value = "商品Id")@RequestBody Long goodsId) {
		Result<List<SelectPOSGoodsLotNumVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            List<SelectPOSGoodsLotNumVO> list = pOSGoodsService.selectGoodsLotNum(userVO.getEnterpriseId(), goodsId,0);//展示0库存
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("POS商品选择批号数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
}
