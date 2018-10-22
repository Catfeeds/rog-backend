package com.rograndec.feijiayun.chain.business.report.finance.account.controller;

import com.rograndec.feijiayun.chain.business.report.finance.account.service.PendingInvoicingService;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.PendingInvoicingPrintVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.PendingInvoicingVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 财务管理-应付/应收账款-应付/应收待开票据
 *
 * @author zhangyu
 * @create 2018-01-11
 */
@Api(value = "PendingInvoicingController", description = "财务管理-应付/应收账款-应付/应收待开票据")
@RestController
@RequestMapping("finance/accounts/pending")
public class PendingInvoicingController {

    private Logger logger = LoggerFactory.getLogger(PendingInvoicingController.class);
    @Autowired
    private PendingInvoicingService pendingInvoicingService;


    @ApiOperation(value = "应付/应收待开票据分页列表", notes = "获取分页列表 | 开发者 张宇 | 已完成")
    @ResponseBody
    @RequestMapping(value = "getPageList", method = RequestMethod.GET)
    public Result<Page<List<PendingInvoicingVO>>> getPageList(
            @ApiIgnore UserVO userVO,
            @ApiParam(name = "pageNo", value = "当前页码", required = true) @RequestParam Integer pageNo,
            @ApiParam(name = "pageSize", value = "显示条数", required = true) @RequestParam Integer pageSize,
            @ApiParam(name = "accountType", value = "0:应付,1:应收", required = true) @RequestParam Integer accountType,
            @ApiParam(name = "startDate", value = "开始日期 yyyy-MM-dd", required = true) @RequestParam Date startDate,
            @ApiParam(name = "endDate", value = "结束日期 yyyy-MM-dd", required = true) @RequestParam Date endDate,
            @ApiParam(name = "code", value = "供货（购货）单位编码/检索码") @RequestParam(required = false) String code,
            @ApiParam(name = "invoiceName", value = "供货（购货）单位名称") @RequestParam(required = false) String invoiceName,
            @ApiParam(name = "orderType", value = "单据类型: accountType 0 应付 -- 总部 0：采购入库，1：购进退出出库; 加盟店 0：配进入库，1：配进退出出库；accountType 1 应收 -- 0：配货出库，1：配后退回入库")
            @RequestParam(required = false) Integer orderType) {
        Result<Page<List<PendingInvoicingVO>>> result = new Result<>();
        try {
            if (pageNo <= 0 || pageSize <= 0 || null == startDate || null == endDate || startDate.compareTo(endDate) > 0 ) {
                result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, null);
                return result;
            }
            Page<List<PendingInvoicingVO>> page = new Page<>(pageNo, pageSize);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, pendingInvoicingService.getPageList(userVO, page, accountType, startDate, endDate, code, invoiceName, orderType));
        } catch (BusinessException e) {
            logger.error("应付/应收待开票据分页列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("应付/应收待开票据分页列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "应付/应收待开票据打印数据", notes = "获取打印数据 | 开发者 张宇 | 已完成")
    @ResponseBody
    @RequestMapping(value = "getPrintList", method = RequestMethod.GET)
    public Result<PendingInvoicingPrintVO> getPrintList(
            @ApiIgnore UserVO userVO,
            @ApiParam(name = "accountType", value = "0:应付,1:应收", required = true) @RequestParam Integer accountType,
            @ApiParam(name = "startDate", value = "开始日期 yyyy-MM-dd", required = true) @RequestParam Date startDate,
            @ApiParam(name = "endDate", value = "结束日期 yyyy-MM-dd", required = true) @RequestParam Date endDate,
            @ApiParam(name = "code", value = "供货（购货）单位编码/检索码") @RequestParam(required = false) String code,
            @ApiParam(name = "invoiceName", value = "供货（购货）单位名称") @RequestParam(required = false) String invoiceName,
            @ApiParam(name = "orderType", value = "单据类型: accountType 0 应付 -- 总部 0：采购入库，1：购进退出出库; 加盟店 0：配进入库，1：配进退出出库；accountType 1 应收 -- 0：配货出库，1：配后退回入库")
            @RequestParam(required = false) Integer orderType) {
        Result<PendingInvoicingPrintVO> result = new Result<>();
        try {
            if (null == startDate || null == endDate || startDate.compareTo(endDate) > 0) {
                result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, null);
                return result;
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, pendingInvoicingService.getPrintList(userVO, accountType, startDate, endDate, code, invoiceName, orderType));
        } catch (BusinessException e) {
            logger.error("应付/应收待开票据打印:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("应付/应收待开票据打印:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出应付/应收待开票据", notes = "导出数据 | 开发者 张宇 | 已完成")
    @ResponseBody
    @RequestMapping(value = "excelExport", method = RequestMethod.GET)
    public void excelExport(
            @ApiIgnore UserVO userVO,
            @ApiParam(name = "accountType", value = "0:应付,1:应收", required = true) @RequestParam Integer accountType,
            @ApiParam(name = "startDate", value = "开始日期 yyyy-MM-dd", required = true) @RequestParam Date startDate,
            @ApiParam(name = "endDate", value = "结束日期 yyyy-MM-dd", required = true) @RequestParam Date endDate,
            @ApiParam(name = "code", value = "供货（购货）单位编码/检索码") @RequestParam(required = false) String code,
            @ApiParam(name = "invoiceName", value = "供货（购货）单位名称") @RequestParam(required = false) String invoiceName,
            @ApiParam(name = "orderType", value = "单据类型: accountType 0 应付 -- 总部 0：采购入库，1：购进退出出库; 加盟店 0：配进入库，1：配进退出出库；accountType 1 应收 -- 0：配货出库，1：配后退回入库")
            @RequestParam(required = false) Integer orderType,
            HttpServletResponse response) throws IOException {
        String name = "应付待开票单据.xlsx";
        if (accountType == 1) {
            name = "应收待开票单据.xlsx";
        }
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        OutputStream output = response.getOutputStream();
        pendingInvoicingService.excelExport(output, userVO, accountType, startDate, endDate, code, invoiceName, orderType);
        output.close();
        output.flush();
    }
}
