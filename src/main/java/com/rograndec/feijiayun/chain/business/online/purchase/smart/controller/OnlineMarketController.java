package com.rograndec.feijiayun.chain.business.online.purchase.smart.controller;

import com.rograndec.feijiayun.chain.business.online.purchase.component.CommonQueryConfiguration;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.service.OnlineMarketService;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.mph.GetSaasOrderDetail;
import com.rograndec.feijiayun.chain.inf.search.MphGoodsSearchServiceClient;
import com.rograndec.feijiayun.chain.inf.search.vo.SearchMphGroupSupplierGoodsResult;
import com.rograndec.feijiayun.chain.inf.search.vo.SearchMphSupplierGoodsResult;

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

import java.util.Date;
import java.util.List;

@Api(value = "OnlineMarket", description = "集采管理-客户端调取运营后台集采活动服务接口")
@RestController
@RequestMapping("online/purchase/smart/onlineMarket")
public class OnlineMarketController {

    @Autowired
    MphGoodsSearchServiceClient mphGoodsSearchServiceClient;
    @Autowired
    OnlineMarketService onlineMarketService;
    @Autowired
    GetSaasOrderDetail getSaasOrderDetail;
    @Autowired
    CommonQueryConfiguration queryConfiguration;

    private static final Log logger = LogFactory.getLog(OnlineMarketController.class);

    @ApiOperation(value = "返回线上商场数据", notes = "返回线上商场数据 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getOnlineMarket", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<SelectSmartEntrepriseVO>> getOnlineMarket(HttpSession session,
                                                                       @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                       @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                       @ApiParam(value = "搜索栏条件", required = false) @RequestParam(required = false) String searchValues) {
        Result<Page<SelectSmartEntrepriseVO>> result = new Result<>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            UserVO userVO = (UserVO) session.getAttribute("user");
            OnlineMarketVO onlineMarketVO = onlineMarketService.selectOnlineMarket(userVO, pageNo, pageSize, searchValues);
            SearchMphGroupSupplierGoodsResult searchMphGroupSupplierGoodsResult = mphGoodsSearchServiceClient.searchGroupBySupplier(
            		onlineMarketVO.getKeyword(),onlineMarketVO.getSupplierId(),
                    onlineMarketVO.getUserId(),onlineMarketVO.getEnterpriseId(),
                    onlineMarketVO.getStart(),onlineMarketVO.getRows(),
                    onlineMarketVO.getOrderSupplierIds());
            SelectSmartEntrepriseVO selectSmartEntrepriseVO = onlineMarketService.getOnlineMarket(searchMphGroupSupplierGoodsResult,page);
            page.setResult(selectSmartEntrepriseVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("返回线上商场数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "我的订单跳转收货界面", notes = "我的订单跳转收货界面 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/geteceiptRData", method = RequestMethod.GET)
    @ResponseBody
    public Result<GetReceiptDataVO> geteceiptRData(HttpSession session,
                                                   @ApiParam(value = "订单日期", required = true) @RequestParam Date oaddTime,
                                                   @ApiParam(value = "订单ID", required = true) @RequestParam Long orderId,
                                                   @ApiParam(value = "订单编码", required = true) @RequestParam String orderCode,
                                                   @ApiParam(value = "供货商", required = true) @RequestParam String supplyName) {
        Result<GetReceiptDataVO> result = new Result<>();
        try {
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String returnJsonData = getSaasOrderDetail.GetSaasOrderPageByParam( Long.valueOf(loginUser.getRgtUserId()), Long.valueOf(loginUser.getRgtEnterpriseId()), orderId, orderCode);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, onlineMarketService.geteceiptRData(returnJsonData, orderId, orderCode,supplyName,loginUser,oaddTime));
        }  catch (BusinessException e) {
            logger.error("我的订单跳转收货界面错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("我的订单跳转收货界面错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "修改供应商信息(临时暂存)", notes = "修改供应商信息 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateSupplier", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateOrder(HttpSession session,
                                                       @ApiParam(value = "页面对象", required = true) @RequestBody GetReceiptDataVO getReceiptDataVO){
        Result<String> result = new Result<>();
        try {
            UserVO loginUser = (UserVO) session.getAttribute("user");
            onlineMarketService.updateOrder(getReceiptDataVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (BusinessException e) {
            logger.error("修改供应商信息(临时暂存):" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL,e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("修改供应商信息(临时暂存):" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看商品绑定列表", notes = "查看商品绑定列表 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/boundGoods", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<SelectBoundGoodsVO>>> boundGoods(HttpSession session,
                                                       @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                       @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                       @ApiParam(value = "搜索商品(默认MPH商品名)",required = false) @RequestParam(required = false) String mphGoodsName,
                                                       @ApiParam(value = "搜索生产厂商(MPH商品生产厂商)", required = false) @RequestParam(required = false)String mphGoodsManufacturer,
                                                       @ApiParam(value = "柜组", required = false) @RequestParam(required = false) Long cargoAreaId) {
        Result<Page<List<SelectBoundGoodsVO>>> result = new Result<>();
        try {
            if(pageNo <= 0 || pageSize <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, onlineMarketService.boundGoods(page,loginUser, mphGoodsName,mphGoodsManufacturer,cargoAreaId));
        } catch (Exception e) {
            logger.error("查看商品绑定列表错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    
    
    @ApiOperation(value = "获取商品默认货位", notes = "获取商品默认货位 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getGoodsDefaultShelt", method = RequestMethod.GET)
    @ResponseBody
    public Result<SelectBoundGoodsVO> getGoodsDefaultShelt(HttpSession session,
                                                       @ApiParam(value = "商品ID", required = true) @RequestParam(required = true) Long goodsId) {
        Result<SelectBoundGoodsVO> result = new Result<>();
        try {
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, onlineMarketService.getGoodsDefaultShelt(loginUser, goodsId));
        } catch (Exception e) {
            logger.error("查看商品绑定列表错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "保存商品绑定", notes = "保存商品绑定 | 开发者 zeshi.sun| 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveBoundGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveBoundGoods(HttpServletRequest request,
                                    @ApiParam(value = "保存商品绑定", required = true) @RequestBody SaveBoundGoodsVO saveBoundGoods) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            onlineMarketService.saveBoundGoods(loginUser, saveBoundGoods);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存商品绑定成功");
        }  catch (BusinessException e) {
            logger.error("保存商品绑定错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("保存商品绑定错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "商品解绑", notes = "商品解绑 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/unBoundGoods", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> unBoundGoods(HttpSession session,
                                                       @ApiParam(value = "MPH商品ID", required = true) @RequestParam String mphGoodsId,
                                                       @ApiParam(value = "saas商品ID", required = true) @RequestParam Long goodsId) {
        Result<String> result = new Result<>();
        try {
            UserVO loginUser = (UserVO) session.getAttribute("user");
            onlineMarketService.unBoundGoods(loginUser,mphGoodsId,goodsId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "商品解绑成功");
        }  catch (BusinessException e) {
            logger.error("商品解绑错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("商品解绑错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看供应商绑定列表", notes = "查看供应商绑定列表 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/boundSupply", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SelectBoundSupplyVO>> boundSupply(HttpSession session,
                                                       @ApiParam(value = "搜索企业名称(默认MPH供应商名)", required = true) @RequestParam String mphSupplyName) {
        Result<List<SelectBoundSupplyVO>> result = new Result<>();
        try {
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, onlineMarketService.boundSupply(loginUser, mphSupplyName));
        } catch (Exception e) {
            logger.error("查看供应商绑定列表错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "供应商解绑", notes = "供应商解绑 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/unBoundSupply", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> unBoundSupply(HttpSession session,
                                       @ApiParam(value = "MPH供应商ID", required = true) @RequestParam String mphSupplyId,
                                       @ApiParam(value = "saas供应商ID", required = true) @RequestParam Long supplyId) {
        Result<String> result = new Result<>();
        try {
            UserVO loginUser = (UserVO) session.getAttribute("user");
            onlineMarketService.unBoundSupply(loginUser,mphSupplyId,supplyId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "供应商解绑成功");
        }  catch (BusinessException e) {
            logger.error("供应商解绑错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("供应商解绑错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "保存供应商绑定", notes = "保存供应商绑定 | 开发者 zeshi.sun| 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveBoundSupply", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveBoundSupply(HttpServletRequest request,
                                         @ApiParam(value = "保存供应商绑定", required = true) @RequestBody SaveBoundSupplyVO saveBoundSupply) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            onlineMarketService.saveBoundSupply(loginUser, saveBoundSupply);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存供应商绑定成功");
        }  catch (BusinessException e) {
            logger.error("保存供应商绑定错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("保存供应商绑定错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }



    @ApiOperation(value="添加购物车", notes = "添加购物车 | 开发者:马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/insertCart", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> insertCart(HttpServletRequest request, @ApiParam(value = "添加的商品", required = true) @RequestBody SelectSmartSupplierVO supplierVO) {
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            onlineMarketService.insertCart(loginUser,supplierVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"添加购物车成功");
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("获取线上的全部数据失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取线上的全部数据", notes = "获取线上的全部数据 | 开发者:马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getOnlineMarketAll", method = RequestMethod.GET)
    @ResponseBody
    public Result<SelectSmartEntrepriseVO> getOnlineMarketAll(HttpServletRequest request,
           @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
           @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
           @ApiParam(value = "供应商ID", required = true) @RequestParam int supplierId,
           @ApiParam(value = "一级分类") @RequestParam(required = false) String gcName1S,
           @ApiParam(value = "一级分类编号是否展开 0-不展开  1-展开", required = true) @RequestParam Long gcName1Type,
           @ApiParam(value = "二级分类") @RequestParam(required = false) String gcName2S,
           @ApiParam(value = "二级分类编号是否展开 0-不展开  1-展开", required = true) @RequestParam Long gcName2Type,
           @ApiParam(value = "生产厂家") @RequestParam(required = false) String manufacturer,
           @ApiParam(value = "生产厂家是否展开 0-不展开  1-展开", required = true) @RequestParam Long manufacturerType,
           @ApiParam(value = "剂型分类") @RequestParam(required = false) String dosageNames,
           @ApiParam(value = "剂型分类是否展开 0-不展开  1-展开", required = true) @RequestParam Long dosageNamesType,
           @ApiParam(value = "搜索栏条件") @RequestParam(required = false) String searchValues) {
        Result<SelectSmartEntrepriseVO> result = new Result<>();
        try{
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            SearchMphSupplierGoodsResult searchMphSupplierGoodsResult =
                    mphGoodsSearchServiceClient.searchBySupplierAndCanPurchased(searchValues,gcName1S,gcName2S,manufacturer,dosageNames,
                            supplierId,loginUser.getRgtUserId(),loginUser.getEnterpriseId().intValue(),
                            queryConfiguration.getOffsetByPageNoAndPageSize(pageNo,pageSize),pageSize);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,onlineMarketService.getOnlineMarketAll(loginUser,searchMphSupplierGoodsResult,gcName1Type,gcName2Type,manufacturerType,dosageNamesType));
        }catch(Exception e){
            logger.error("获取线上的全部数据失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 完成收货
     */
    @ApiOperation(value = "收货按钮", notes = "收货按钮 | 开发者 lei.su | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/reward", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> reward(@ApiIgnore UserVO loginUser,
                                                   @RequestBody GetReceiptDataVO getReceiptDataVO) {
        Result<String> result = new Result<>();
        try {
            String msg = onlineMarketService.reward(getReceiptDataVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, msg);
        }  catch (BusinessException e) {
            logger.error("收货错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("收货错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


}
