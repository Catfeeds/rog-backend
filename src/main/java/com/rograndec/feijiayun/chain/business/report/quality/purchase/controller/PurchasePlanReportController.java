package com.rograndec.feijiayun.chain.business.report.quality.purchase.controller;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchasePlanReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PlanPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchasePlanReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.TotalReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_purchase_purchasePlan",description = "报表-质量管理-采购-采购计划")
@RestController
@RequestMapping("report/quality/purchase/purchasePlan")
@Validated
public class PurchasePlanReportController {
    private static final Log logger = LogFactory.getLog(PurchasePlanReportController.class);
    @Autowired
    PurchasePlanReportService purchasePlanReportService;

    @ApiOperation(value = "获取采购计划", notes = "获取采购计划 | 开发者:马东 | 已完成" )
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public Result<Page<TotalReportVO<PurchasePlanReportVO>>> getPurchasePlan(HttpServletRequest request,
           @ApiParam(name = "pageNo", value = "当前页码", required = true) @RequestBody PlanPageVO planPageVO){

        Result<Page<TotalReportVO<PurchasePlanReportVO>>> result = new Result<>();
        try {
            Page<TotalReportVO<PurchasePlanReportVO>> page = new Page<>(planPageVO.getPageNo(), planPageVO.getPageSize());
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = purchasePlanReportService.getPurchasePlanPage(loginUser,planPageVO,page);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("获取采购计划错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    @ApiOperation(value = "导出Excel", notes = "按照模版将采购计划列表信息导出至Excel | 开发者:马东 | 已完成")
    @RequestMapping(value="/exportExcel", method=RequestMethod.POST, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, PlanPageVO planPageVO){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "采购计划";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            purchasePlanReportService.exportExcel(output,loginUser,planPageVO);
        }catch(Exception e){
            logger.error("导出采购计划信息错误:"+e.getMessage(),e);
        }

    }
	
}
