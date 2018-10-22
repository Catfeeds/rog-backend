package com.rograndec.feijiayun.chain.business.report.retail.controller;

import com.rograndec.feijiayun.chain.business.report.retail.service.ReportSaleGroupService;
import com.rograndec.feijiayun.chain.business.report.retail.service.TimeSaleReportService;
import com.rograndec.feijiayun.chain.business.report.retail.vo.GoodsCategorySaleVo;
import com.rograndec.feijiayun.chain.business.report.retail.vo.ReportCabinetSaleVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.ReportSaleAnalysisTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.ReportTeamSaleVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.RequestCabinetSaleVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.RequestGoodsCategorySaleVo;
import com.rograndec.feijiayun.chain.business.report.retail.vo.RequestTeamSaleVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.controller.GoodsLockController;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author lizhongyi
 */
@Api(value = "report_retail_saleAnalysis", description = "报表-零售管理-销售分析")
@RestController
@RequestMapping("report/retail/saleAnalysis")
@Validated
public class SaleAnalysisReportController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsLockController.class);

    @Autowired
    private ReportSaleGroupService reportSaleGroupService;
    @Autowired
    private TimeSaleReportService timeSaleReportService;


    // 柜组销售
    @ApiOperation(value = "查询柜组销售列表", notes = "查询柜组销售列表 | 开发者 蓝兴建 | 已完成")
    @RequestMapping(value = "/queryCabinetSaleList", method = RequestMethod.POST)
    public Result<Page<ReportSaleAnalysisTotalVO<ReportCabinetSaleVO>>> queryCabinetSaleList(HttpSession session,
                                                                                             @Valid @RequestBody RequestCabinetSaleVO requestVO) {
        Result<Page<ReportSaleAnalysisTotalVO<ReportCabinetSaleVO>>> result = new Result<>();
        try {
            Page page = new Page();
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            page = reportSaleGroupService.getCabinetSaleList(requestVO, page, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("查询柜组销售列表:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    // 柜组销售
    @ApiOperation(value = "查询柜组销售明细列表", notes = "查询柜组销售明细 | 开发者 蓝兴建 | 已完成")
    @RequestMapping(value = "/queryCabinetSaleDetailList", method = RequestMethod.GET)
    public Result<ReportSaleAnalysisTotalVO<ReportCabinetSaleVO>> queryCabinetSaleDetailList(HttpServletRequest request,
                                                                                                   @ApiParam(value = "搜索类型：1-按销售日期；2-按日结日期", required = true) @RequestParam Integer type,
                                                                                                   @ApiParam(value = "门店ID", required = true) @RequestParam Long enterpriseId,
                                                                                                   @ApiParam(value = "柜组ID", required = true) @RequestParam Long cargoAreaId,
                                                                                                   @ApiParam(value = "日期：根据type传销售日期或者日结日期", required = true) @RequestParam String date) {
        Result<ReportSaleAnalysisTotalVO<ReportCabinetSaleVO>> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) request.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, reportSaleGroupService.getCabinetSaleDetailList(enterpriseId, cargoAreaId, type, date));
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("查询柜组销售列表:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出Excel", notes = "按照模版将柜组销售信息导出至Excel | 开发者 蓝兴建 | 已完成", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @RequestMapping(value = "/exportExcelForCabinetSale", method = RequestMethod.POST)
    public void exportExcelForCabinetSale(HttpServletRequest request, HttpServletResponse response,
                                          RequestCabinetSaleVO requestVO) {
        String name = "";
        try {
            //导出不需要分页
            requestVO.setPageNo(null);
            requestVO.setPageSize(null);

            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            if (userVO.getParentId() == 0) {
                name = "总部柜组销售";
            } else {
                name = "分店柜组销售";
            }

            if (requestVO.getType() == 1) {
                name += "按销售日期";
            } else {
                name += "按日结日期";
            }

            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            Page<ReportSaleAnalysisTotalVO<ReportCabinetSaleVO>> totalPageVO = reportSaleGroupService.getCabinetSaleList(requestVO, new Page(), userVO);
            reportSaleGroupService.excelExportCabinetSale(output, totalPageVO.getResult(), requestVO.getType(), userVO);
        } catch (Exception e) {
            logger.error("导出商品销毁报表错误:" + e.getMessage(), e);
            e.printStackTrace();
        }
    }

    // 班组销售
    @ApiOperation(value = "查询班组销售列表", notes = "查询班组销售列表 | 开发者 蓝兴建 | 已完成")
    @RequestMapping(value = "/queryTeamSaleList", method = RequestMethod.POST)
    public Result<Page<ReportSaleAnalysisTotalVO<ReportTeamSaleVO>>> queryTeamSaleList(HttpSession session,
                                                                                       @Valid @RequestBody RequestTeamSaleVO requestVO) {
        Result<Page<ReportSaleAnalysisTotalVO<ReportTeamSaleVO>>> result = new Result<>();
        try {
            Page page = new Page();
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            page = reportSaleGroupService.getTeamSaleList(requestVO, page, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("查询班组销售列表:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    // 柜组销售
    @ApiOperation(value = "查询班组销售明细列表", notes = "查询班组销售明细 | 开发者 蓝兴建 | 已完成")
    @RequestMapping(value = "/queryTeamSaleDetailList", method = RequestMethod.GET)
    public Result<ReportSaleAnalysisTotalVO<ReportTeamSaleVO>> queryTeamSaleDetailList(HttpServletRequest request,
                                                                                             @ApiParam(value = "搜索类型：1-按销售日期；2-按日结日期", required = true) @RequestParam Integer type,
                                                                                             @ApiParam(value = "门店ID", required = true) @RequestParam Long enterpriseId,
                                                                                             @ApiParam(value = "班组ID", required = true) @RequestParam Long teamId,
                                                                                             @ApiParam(value = "日期：根据type传销售日期或者日结日期", required = true) @RequestParam String date) {
        Result<ReportSaleAnalysisTotalVO<ReportTeamSaleVO>> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) request.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, reportSaleGroupService.getTeamSaleDetailList(enterpriseId, teamId, type, date));
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("查询班组销售明细列表:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出Excel", notes = "按照模版将班组销售信息导出至Excel | 开发者 蓝兴建 | 已完成", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @RequestMapping(value = "/exportExcelForTeamSale", method = RequestMethod.POST)
    public void exportExcelForTeamSale(HttpServletRequest request, HttpServletResponse response,
                                       RequestTeamSaleVO requestVO) {
        String name = "";
        try {
            //导出不需要分页
            requestVO.setPageNo(null);
            requestVO.setPageSize(null);
            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            if (userVO.getParentId() == 0) {
                name = "总部班组销售";
            } else {
                name = "分店班组销售";
            }

            if (requestVO.getType() == 1) {
                name += "按销售日期";
            } else {
                name += "按日结日期";
            }

            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            Page<ReportSaleAnalysisTotalVO<ReportTeamSaleVO>> totalPageVO = reportSaleGroupService.getTeamSaleList(requestVO, new Page(), userVO);
            reportSaleGroupService.excelExportTeamSale(output, totalPageVO.getResult(), requestVO.getType(), userVO);
        } catch (Exception e) {
            logger.error("导出商品销毁报表错误:" + e.getMessage(), e);
            e.printStackTrace();
        }
    }

    // 收款人员销售
    @ApiOperation(value = "查询收款人员销售列表", notes = "查询收款人员销售列表 | 开发者 李中义 | 开发中")
    @RequestMapping(value = "/queryPayeeSaleList", method = RequestMethod.GET)
    public void queryPayeeSaleList() {
    }

    @ApiOperation(value = "查询收款人员列表", notes = "查询收款人员列表 | 开发者 李中义 | 开发中")
    @RequestMapping(value = "/queryPayeeList", method = RequestMethod.GET)
    public void queryPayeeList() {
    }

    @ApiOperation(value = "导出Excel", notes = "按照模版将收款人员销售信息导出至Excel | 开发者 李中义 | 开发中")
    @RequestMapping(value = "/exportExcelForPayeeSale", method = RequestMethod.GET)
    public void exportExcelForPayeeSale() {
    }

    // 营业人员销售
    @ApiOperation(value = "查询营业人员销售列表", notes = "查询营业人员销售列表 | 开发者 李中义 | 开发中")
    @RequestMapping(value = "/queryClerkSaleList", method = RequestMethod.GET)
    public void queryClerkSaleList() {
    }

    @ApiOperation(value = "查询营业人员列表", notes = "查询营业人员列表 | 开发者 李中义 | 开发中")
    @RequestMapping(value = "/queryClerkList", method = RequestMethod.GET)
    public void queryClerkList() {
    }

    @ApiOperation(value = "导出Excel", notes = "按照模版将营业人员销售信息导出至Excel | 开发者 李中义 | 开发中")
    @RequestMapping(value = "/exportExcelForClerkSale", method = RequestMethod.GET)
    public void exportExcelForClerkSale() {
    }

    // 商品分类销售
    @ApiOperation(value = "查询商品分类销售列表", notes = "查询商品分类销售列表 | 开发者 金正斌 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/queryGoodsCategorySaleList", method = RequestMethod.POST)
    public Result<List<GoodsCategorySaleVo>> queryGoodsCategorySaleList(HttpSession session,
                                                                        @Valid @RequestBody RequestGoodsCategorySaleVo requestGoodsCategorySaleVo) {
        Result<List<GoodsCategorySaleVo>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            if (userVO.getParentId() != 0 && userVO.getParentId() != null) {
                requestGoodsCategorySaleVo.setEnterpriseId(userVO.getParentId());
                requestGoodsCategorySaleVo.setParent(1);
                requestGoodsCategorySaleVo.setEid(userVO.getEnterpriseId());
            } else {
                requestGoodsCategorySaleVo.setParent(2);
                requestGoodsCategorySaleVo.setEid(userVO.getEnterpriseId());
                requestGoodsCategorySaleVo.setEnterpriseId(userVO.getEnterpriseId());
            }
            //如果是总部人员登陆，chainType由前端传，如果登陆人员是分店的那么自己加进去
            if (userVO.getChainType() != ChainType.Headquarters.getCode()){
                requestGoodsCategorySaleVo.setChainType(userVO.getChainType());
            }
            List<GoodsCategorySaleVo> goodsCategorySaleVos = timeSaleReportService.listGoodsCategorySaleVos(requestGoodsCategorySaleVo);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsCategorySaleVos);
        } catch (Exception e) {
            logger.error("查询商品分类销售列表失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }

    @ApiOperation(value = "商品分类销售导出Excel", notes = "按照模版将商品分类销售信息导出至Excel | 开发者 金正斌 | 已联调", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @RequestMapping(value = "/exportExcelForGoodsCategorySale", method = RequestMethod.GET)
    public void exportExcelForGoodsCategorySale(HttpServletRequest request, HttpServletResponse response,
                                                RequestGoodsCategorySaleVo requestGoodsCategorySaleVo) {
        String name = "商品分类销售";
        try {
            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            if (userVO.getParentId() != 0 && userVO.getParentId() != null) {
                requestGoodsCategorySaleVo.setEnterpriseId(userVO.getParentId());
                requestGoodsCategorySaleVo.setParent(1);
                requestGoodsCategorySaleVo.setEid(userVO.getEnterpriseId());
            } else {
                requestGoodsCategorySaleVo.setParent(2);
                requestGoodsCategorySaleVo.setEid(userVO.getEnterpriseId());
                requestGoodsCategorySaleVo.setEnterpriseId(userVO.getEnterpriseId());
            }
            timeSaleReportService.exportExcelForGoodsCategorySale(output, requestGoodsCategorySaleVo, userVO);
        } catch (Exception e) {
            logger.error("导出商品分类销售错误:" + e.getMessage(), e);
            e.printStackTrace();
        }
    }


}
