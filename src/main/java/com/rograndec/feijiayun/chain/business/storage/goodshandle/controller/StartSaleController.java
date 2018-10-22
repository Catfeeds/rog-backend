package com.rograndec.feijiayun.chain.business.storage.goodshandle.controller;

import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.StartSale;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.StartSaleService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
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
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dongyang.du 搜索的是商品处理的接口
 */
@Api(value = "storage_goodsHandle_startSale", description = "储存管理-商品处理-解停通知")
@RestController
@RequestMapping("storage/goodsHandle/startSale")
@Validated
public class StartSaleController {


    private static final Log logger = LogFactory.getLog(StartSaleController.class);


    @Autowired
    private StartSaleService startSaleService;


    @ApiOperation(value = "查询锁定状态货位", notes = "根据商品Id查询货位 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getLockGoodsStockList/{goodsId}/{lotId}/{lockId}", method = RequestMethod.GET)
    public Result<List<StockDestroyVO>> getLockGoodsStockList(HttpSession session, @ApiParam(value = "商品id查询货位", required = true) @PathVariable Long goodsId,
                                                              @ApiParam(value = "批号ID", required = true) @PathVariable Long lotId,
                                                              @ApiParam(value = "锁定商品ID", required = true) @PathVariable Long lockId) {
        Result<List<StockDestroyVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", userVO.getEnterpriseId());
        map.put("goodsId", goodsId);
        map.put("parentId",userVO.getParentId());
        map.put("lotId", lotId);
        map.put("baseOrderId",lockId);
        map.put("baseOrderType", OrderRule.LOCK.getType());
        List<StockDestroyVO> goodsList = startSaleService.getLockStockList(map);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsList);
        return result;
    }


    @ApiOperation(value = "保存解停通知单", notes = "保存解停通知单 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/saveStartSaleOrder", method = RequestMethod.POST)
    public Result<String> saveStartSaveOrder(HttpServletRequest request, @RequestBody StartSaleRequestVO startSaleRequestVO) throws Exception {

        Result<String> result = new Result<>();
        HttpSession session = request.getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");
        return startSaleService.saveStartSale(userVO, startSaleRequestVO);

    }

    @ApiOperation(value = "查询解停通知单列表", notes = "根据状态查询解停通知单列表 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getStartSaleOrderList", method = RequestMethod.POST)
    public Result<Page<List<StartSalePageVO>>> getStartSaleOrderList(HttpServletRequest request, @RequestBody SaleOrderRequestVO requestVO) {

        Result<Page<List<StartSalePageVO>>> result = new Result<>();
        if (requestVO.getPageNo() <= 0 || requestVO.getPageSize() <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Page page = startSaleService.selectStartSalePage(requestVO, loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

        return result;

    }

    @ApiOperation(value = "查询商品解停通知单明细列表", notes = "根据解停通知单ID查询解停通知单明细列表 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getStartSaleOrderDtlList", method = RequestMethod.GET)
    public Result<StartSaleReturnVO> getStartSaleOrderDtlList(@RequestParam(required = true) @ApiParam(name = "id", value = "解停通知单主键ID", required = true) Long id) {

        Result<StartSaleReturnVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, startSaleService.selectDetailById(id));

        return result;

    }

    @ApiOperation(value = "导出Excel", notes = "按照模版解停通知单导出至Excel | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(@RequestParam(required = true) @ApiParam(name = "id", value = "停售通知单主键ID", required = true) Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String name = "解停通知";
        // 输出Excel文件
        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");
        OutputStream output = null;
        output = response.getOutputStream();
        // 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");

        startSaleService.exportExcel(output, userVO, id);

    }

}
