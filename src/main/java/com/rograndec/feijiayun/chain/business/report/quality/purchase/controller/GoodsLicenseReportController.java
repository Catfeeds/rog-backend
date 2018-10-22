package com.rograndec.feijiayun.chain.business.report.quality.purchase.controller;

import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.GoodsLicenseReportService;
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
@Api(value = "report_quality_purchase_goodsLicense",description = "报表-质量管理-采购-品种资质")
@RestController
@RequestMapping("report/quality/purchase/goodsLicense")
@Validated
public class GoodsLicenseReportController {

    private static final Log logger = LogFactory.getLog(GoodsLicenseReportController.class);
    @Autowired
    GoodsLicenseReportService goodsLicenseReportService;

    @ApiOperation(value = "品种资质列表", notes = "获取品种资质列表 | 开发者:马东 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getQualificationGoodsInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<FirstGoodsVO>>> getQualificationGoodsInfo(HttpServletRequest request,
           @ApiParam(value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）",name = "businessVariety") @RequestParam(required = false) Integer businessVariety,
           @ApiParam(value = "商品搜索条件",name = "param") @RequestParam(required = false)  String param,
           @ApiParam(value = "待排序字段",name = "orderName") @RequestParam(required = false) String orderName,
           @ApiParam(value = "排序方式ASC/DESC",name = "orderType") @RequestParam(required = false) String orderType,
           @ApiParam(value = "起始页",name = "pageNo", required = true) @RequestParam Integer pageNo,
           @ApiParam(value = "页容量",name = "pageSize", required = true) @RequestParam Integer pageSize){
        Result<Page<List<FirstGoodsVO>>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsLicenseReportService.getQualificationGoodsInfo(loginUser,businessVariety, param,orderName,orderType,pageNo,pageSize));
        } catch (Exception e) {
            logger.error("获取品种资质列表错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "品种资质查看", notes = "获取品种资质明细 | 开发者:马东 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getQualificationGoodsDtlInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<GoodsVO> getQualificationGoodsDtlInfo(HttpServletRequest request,
           @ApiParam(value = "商品ID",name = "goodsId", required = true) @RequestParam Long goodsId){
        Result<GoodsVO> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsLicenseReportService.getQualificationGoodsDtlInfo(loginUser,goodsId));
        } catch (Exception e) {
            logger.error("获取品种资质明细错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出Excel", notes = "按照模版将品种资质信息导出至Excel | 开发者:马东 | 已联调")
    @RequestMapping(value="/exportExcel", method=RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
           @ApiParam(value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）",name = "businessVariety") @RequestParam(required = false) Integer businessVariety,
           @ApiParam(value = "商品搜索条件",name = "param") @RequestParam(required = false)  String param,
           @ApiParam(value = "待排序字段",name = "orderName") @RequestParam(required = false) String orderName,
           @ApiParam(value = "排序方式ASC/DESC",name = "orderType") @RequestParam(required = false) String orderType) {
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "品种资质";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            goodsLicenseReportService.exportExcel(output, loginUser, param, orderName, orderType, businessVariety);
        } catch (Exception e) {
            logger.error("导出品种资质错误:" + e.getMessage(), e);
        }
    }


}
