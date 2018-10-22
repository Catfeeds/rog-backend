package com.rograndec.feijiayun.chain.business.report.quality.purchase.controller;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchaseOrderReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.OrderPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseOrderReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.TotalReportVO;
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
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_purchase_purchaseOrder",description = "报表-质量管理-采购-采购订单")
@RestController
@RequestMapping("report/quality/purchase/purchaseOrder")
@Validated
public class PurchaseOrderReportController {

    private static final Log logger = LogFactory.getLog(PurchaseOrderReportController.class);

    @Autowired
    PurchaseOrderReportService purchaseOrderReportService;

    @ApiOperation(value = "获取采购订单", notes = "获取采购订单 | 开发者:马东 | 已完成" )
    @RequestMapping(value = "/getPurchaseOrder", method = RequestMethod.POST)
    public Result<Page<TotalReportVO<PurchaseOrderReportVO>>> getPurchaseOrder(HttpServletRequest request,
           @ApiParam(name = "pageNo", value = "当前页码", required = true) @RequestBody OrderPageVO orderPageVO){

        Result<Page<TotalReportVO<PurchaseOrderReportVO>>> result = new Result<>();
        try {
            Page<TotalReportVO<PurchaseOrderReportVO>> page = new Page<>(orderPageVO.getPageNo(), orderPageVO.getPageSize());
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = purchaseOrderReportService.getPurchaseOrderPage(loginUser,orderPageVO,page);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("获取采购订单错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    @ApiOperation(value = "导出Excel", notes = "按照模版将采购订单列表信息导出至Excel | 开发者:马东 | 已完成")
    @RequestMapping(value="/exportExcel", method=RequestMethod.POST, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, OrderPageVO orderPageVO){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "采购订单";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            purchaseOrderReportService.exportExcel(output,loginUser,orderPageVO);
        }catch(Exception e){
            logger.error("导出采购订单信息错误:"+e.getMessage(),e);
        }
    }
}
