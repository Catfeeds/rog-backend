package com.rograndec.feijiayun.chain.business.report.quality.purchase.controller;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.FirstGoodsReviewReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.FirstGoodsReviewPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.FirstGoodsVO;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_purchase_firstGoodsReview",description = "报表-质量管理-采购-首营品种评审")
@RestController
@RequestMapping("report/quality/purchase/firstGoodsReview")
@Validated
public class FirstGoodsReviewReportController {
    private static final Log logger = LogFactory.getLog(FirstGoodsReviewReportController.class);

    @Autowired
    FirstGoodsReviewReportService firstGoodsReviewReportService;

    @ApiOperation(value = "获取首营商品列表", notes = "获取首营商品列表 | 开发者:马东 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getGoodsInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result<Page<List<FirstGoodsVO>>> getGoodsInfo(HttpServletRequest request,
                                                         @ApiParam(value = "分页查询参数",name = "pageVO", required = true) @RequestBody FirstGoodsReviewPageVO pageVO){
        Result<Page<List<FirstGoodsVO>>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, firstGoodsReviewReportService.getGoodsInfo(loginUser,pageVO));
        } catch (Exception e) {
            logger.error("获取首营商品列表错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "获取首营商品列表明细", notes = "获取首营商品列表明细 | 开发者:马东 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getGoodsInfoDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result<FirstGoodsVO> getGoodsInfoDetail(HttpServletRequest request,
           @ApiParam(value = "商品信息",name = "goodsId", required = true) @RequestParam Long goodsId){
        Result<FirstGoodsVO> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, firstGoodsReviewReportService.getGoodsInfoDetail(loginUser,goodsId));
        } catch (Exception e) {
            logger.error("获取首营商品列表错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "首营商品列表信息导出Excel", notes = "按照模版将首营商品列表信息导出至Excel | 开发者:马东 | 已完成")
    @RequestMapping(value="/exportExcel", method=RequestMethod.POST,produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, FirstGoodsReviewPageVO pageVO){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "首营商品";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            firstGoodsReviewReportService.exportExcel(output,loginUser,pageVO);
        }catch(Exception e){
            logger.error("导出首营商品列表息错误:"+e.getMessage(),e);
        }

    }

    @ApiOperation(value = "首营商品明细信息导出Excel", notes = "按照模版将首营商品明细信息导出至Excel | 开发者:马东 | 已完成")
    @RequestMapping(value="/exportDetailExcel", method=RequestMethod.GET)
    public void exportDetailExcel(HttpServletRequest request, HttpServletResponse response,
           @ApiParam(value = "商品id",name = "goodsId", required = true) @RequestParam Long goodsId){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "首营商品明细";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            firstGoodsReviewReportService.exportDetailExcel(output,loginUser,goodsId);
        }catch(Exception e){
            logger.error("导出首营商品明细息错误:"+e.getMessage(),e);
        }

    }


}
