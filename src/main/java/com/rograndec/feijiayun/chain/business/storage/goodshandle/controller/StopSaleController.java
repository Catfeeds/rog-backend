package com.rograndec.feijiayun.chain.business.storage.goodshandle.controller;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsDestroyService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.StopSaleService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dongyang.du  搜索的是商品锁定的商品接口
 */
@Api(value = "storage_goodsHandle_stopSale", description = "储存管理-商品处理-停售通知")
@RestController
@RequestMapping("storage/goodsHandle/stopSale")
@Validated
public class StopSaleController {

    private static final Log logger = LogFactory.getLog(StopSaleController.class);


    @Autowired
    private StopSaleService stopSaleService;

    @Autowired
    private GoodsDestroyService goodsDestroyService;


    @ApiOperation(value = "查询商品合格货位", notes = "根据商品Id查询货位 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getGoodsStockList/{goodsId}/{lotId}", method = RequestMethod.GET)
    public Result<List<StockDestroyVO>> getGoodsStockList(HttpSession session, @ApiParam(value = "商品id查询货位", required = true) @PathVariable Long goodsId, @ApiParam(value = "批号ID", required = true) @PathVariable Long lotId) {
        Result<List<StockDestroyVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", userVO.getEnterpriseId());
        map.put("goodsId", goodsId);
        map.put("lotId", lotId);
        map.put("jobArea", 0);
        List<StockDestroyVO> goodsList = goodsDestroyService.getStockList(map);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsList);
        return result;
    }



    @ApiOperation(value = "保存停售通知单", notes = "保存停售通知单 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/saveStopSaleOrder", method = RequestMethod.POST)
    public Result<String> saveStopSaveOrder(HttpServletRequest request, @RequestBody StopSaleRequestVO stopSaleRequestVO) throws Exception {

        Result<String> result = new Result<>();
        HttpSession session = request.getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        result = stopSaleService.saveStopSale(userVO, stopSaleRequestVO);
        return result;


    }

    @ApiOperation(value = "查询停售通知单列表", notes = "根据状态查询停售通知单列表 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getStopSaleOrderList", method = RequestMethod.POST)
    public Result<Page<List<StopSalePageVO>>> getStopSaleOrderList(@RequestBody SaleOrderRequestVO requestVO, HttpServletRequest request) {


        Result<Page<List<StopSalePageVO>>> result = new Result<>();
        if (requestVO.getPageNo() <= 0 || requestVO.getPageSize() <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Page page = stopSaleService.selectStopSalePage(requestVO, loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

        return result;


    }

    @ApiOperation(value = "查询商品停售通知单明细列表", notes = "根据停售通知单ID查询停售通知单明细列表 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getStopSaleOrderDtlList", method = RequestMethod.GET)
    public Result<StopSaleReturnVO> getStopSaleOrderDtlList(@RequestParam(required = true) @ApiParam(name = "id", value = "停售通知单主键ID", required = true) Long id) {

        Result result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, stopSaleService.selectDetailById(id));

        return result;
    }

    @ApiOperation(value = "导出Excel", notes = "按照模版停售通知单导出至Excel | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(@RequestParam(required = true) @ApiParam(name = "id", value = "停售通知单主键ID", required = true) Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {


        String name = "停售通知";
        // 输出Excel文件
        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");
        OutputStream output = response.getOutputStream();
        // 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");

        stopSaleService.exportExcel(output, userVO, id);

    }

}
