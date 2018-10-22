package com.rograndec.feijiayun.chain.business.report.quality.purchase.controller;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchaseReceiveReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseReceiveReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.ReceivePageVO;
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
@Api(value = "report_quality_purchase_purchaseRejectReceive",description = "报表-质量管理-收货与验收-药品拒收单")
@RestController
@RequestMapping("report/quality/purchase/purchaseRejectReceive")
@Validated
public class PurchaseRejectReceiveReportController {

    private static final Log logger = LogFactory.getLog(PurchaseRejectReceiveReportController.class);

    @Autowired
    PurchaseReceiveReportService purchaseReceiveReportService;

    @ApiOperation(value = "获取采购拒收列表", notes = "获取采购拒收列表 | 开发者:马东 | 已完成" )
    @RequestMapping(value = "/getPurchaseReceive", method = RequestMethod.POST)
    public Result<Page<TotalReportVO<PurchaseReceiveReportVO>>> getPurchaseReceive(HttpServletRequest request,
                                                                                   @ApiParam(name = "pageNo", value = "当前页码", required = true) @RequestBody ReceivePageVO receivePageVO){

        Result<Page<TotalReportVO<PurchaseReceiveReportVO>>> result = new Result<>();
        try {
            Page<TotalReportVO<PurchaseReceiveReportVO>> page = new Page<>(receivePageVO.getPageNo(), receivePageVO.getPageSize());
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = purchaseReceiveReportService.getPurchaseReceive(loginUser,receivePageVO,page);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("获取采购拒收列表错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    @ApiOperation(value = "导出Excel", notes = "按照模版将采购拒收列表信息导出至Excel | 开发者:马东 | 已完成")
    @RequestMapping(value="/exportExcel", method=RequestMethod.POST)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = "分页查询参数",name = "pageVO", required = true) @RequestBody ReceivePageVO receivePageVO){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "采购拒收";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            purchaseReceiveReportService.exportExcel(output,loginUser,receivePageVO);
        }catch(Exception e){
            logger.error("导出采购拒收信息错误:"+e.getMessage(),e);
        }

    }

}
