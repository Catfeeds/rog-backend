package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.controller;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.service.ReceivableInvoiceService;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.vo.*;
import com.rograndec.feijiayun.chain.business.system.approval.exception.ApprovalFlowBizException;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：应收发票-应收发票
 * Created by ST on 2018/1/8 14:39
 */
@Api(value = "receivable_invoice", description = "应收发票-应收发票接口服务")
@RestController
@RequestMapping("finance/receivableinvoice/reveivableinvoice")
@Validated
public class ReceivableInvoiceController {

    private static final Log logger = LogFactory.getLog(ReceivableInvoiceController.class);

    @Autowired
    private ReceivableInvoiceService receivableInvoiceService;

    @ApiOperation(value = "分页获取应收发票信息", notes = "分页获取应收发票信息 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getReveivableInvoicePage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<TabableTotalVO>> getReveivableInvoicePage(HttpServletRequest request,
                                                                 @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                 @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                 @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
                                                                 @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
                                                                 @ApiParam(value = "购货单位编码", required = false) @RequestParam(required = false) String purchaseUnitCode,
                                                                 @ApiParam(value = "购货单位名称", required = false) @RequestParam(required = false) String purchaseUnitName,
                                                                 @ApiParam(value = "开票单号", required = false) @RequestParam(required = false) String code,
                                                                 @ApiParam(value = "开票人员", required = false) @RequestParam(required = false) String billManName,
                                                                 @ApiParam(value = "单据状态", required = false) @RequestParam(required = false) Integer status,
                                                                 @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                                                 @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<TabableTotalVO>> result = new Result<Page<TabableTotalVO>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            TabableTotalVO reveivableinvoicePageVOS = receivableInvoiceService
                    .getReveivableInvoicePage(pageNo, pageSize, loginUser, page, startTime, endTime, purchaseUnitCode, purchaseUnitName, code, billManName,
                            status, orderName, orderType);
            page.setResult(reveivableinvoicePageVOS);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页获取应收发票信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "保存应收发票单据", notes = "保存应收发票单据 | 开发者 zeshi.sun| 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveReveivableInvoice", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveReveivableInvoice(HttpServletRequest request,
                                                @ApiParam(value = "保存应收发票单据", required = true) @RequestBody SaveReveivableInvoiceVO saveReveivableInvoiceVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, receivableInvoiceService.saveReveivableInvoice(loginUser, saveReveivableInvoiceVO));

        } catch (BusinessException e) {
            logger.error("保存应收发票单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("保存应收发票单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "分页获取出库商品信息", notes = "分页获取出库商品信息 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getDistrOutPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<DistrOutPageTotalVO>> getDistrOutPage(HttpServletRequest request,
                                                             @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                             @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                             @ApiParam(value = "购货单位ID", required = true) @RequestParam Long purchaseUnitId,
                                                             @ApiParam(value = "查询条件", required = false) @RequestParam(required = false) String key
    ) {
        Result<Page<DistrOutPageTotalVO>> result = new Result<Page<DistrOutPageTotalVO>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            DistrOutPageTotalVO distrOutPageTotalVO = receivableInvoiceService
                    .getDistrOutPage(pageNo, pageSize, loginUser, page, purchaseUnitId, key);
            page.setResult(distrOutPageTotalVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页获取出库商品信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "获取购货单位", notes = "获取购货单位(只有加盟店信息) | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getPurchaseUnit", method = RequestMethod.GET)
    public Result<List<PurchaseUnitVO>> getPurchaseUnit(HttpSession session,
                                                        @ApiParam(value = "搜索条件", required = false) @RequestParam(required = false) String key) throws Exception {
        Result<List<PurchaseUnitVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<PurchaseUnitVO> purchaseUnit = receivableInvoiceService.getPurchaseUnit(userVO, key);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseUnit);
        } catch (BusinessException e) {
            logger.error("获取购货单位失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("获取购货单位失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看应收发票", notes = "查看应收发票 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getSaveReveivableInvoice", method = RequestMethod.GET)
    public Result<SaveReveivableInvoiceVO> getSaveReveivableInvoice(HttpSession session,
                                                                    @ApiParam(value = "应收发票ID", required = true) @RequestParam Long id) throws Exception {
        Result<SaveReveivableInvoiceVO> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            SaveReveivableInvoiceVO saveReveivableinvoice = receivableInvoiceService.getSaveReveivableInvoice(userVO, id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, saveReveivableinvoice);
        } catch (BusinessException e) {
            logger.error("查看应收发票失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("查看应收发票失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看应收发票草稿缓存", notes = "查看应收发票草稿缓存 | 开发者 zeshi.sun | 已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierId", value = "购货单位id"
                    , required = true, paramType = "path")
    })
    @RequestMapping(value = "/daftCache/{supplierId}", method = RequestMethod.GET)
    public Result<List<DraftCacheVO>> daftCacheVO(@PathVariable Long supplierId, @ApiIgnore UserVO userVO) {
        Result<List<DraftCacheVO>> result = new Result<>();
        try {
            List<DraftCacheVO> draftCacheVO = receivableInvoiceService.getDraftCacheVO(userVO, supplierId);

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, draftCacheVO);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("查看应收发票草稿缓存:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "保存应收发票草稿缓存", notes = "保存应收发票草稿缓存 | 开发者 zeshi.sun | 已完成")
    @RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
    public Result<DraftCacheVO> saveDraftCache(@ApiIgnore UserVO userVO, @RequestBody DraftCacheVO draftCacheVO) {

        Result<DraftCacheVO> result = new Result<>();
        try {

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, receivableInvoiceService.saveDraftCache(userVO, draftCacheVO));

        } catch (ApprovalFlowBizException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
            return result;
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("保存应收发票草稿缓存:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }

        return result;
    }

    @ApiOperation(value = "删除应收发票草稿缓存", notes = "删除应收发票草稿缓存 | 开发者 zeshi.sun | 已完成")
    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}/{supplierId}", method = RequestMethod.DELETE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
                    , required = true, paramType = "path"),
            @ApiImplicitParam(name = "supplierId", value = "购货单位id"
                    , required = true, paramType = "path")
    })
    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String redisKeyValue, @PathVariable(required = true) Long supplierId) {

        Result result = new Result<>();
        try {

            receivableInvoiceService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.RECEIVABLE_INVOICE.getCodePrefix(), redisKeyValue, supplierId);

            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("删除应收发票草稿缓存:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "冲销应收发票", notes = "冲销应收发票 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getAlreadyWriteInvoice", method = RequestMethod.POST)
    public Result getAlreadyWriteInvoice(HttpSession session,
                                         @RequestBody(required = false)
                                         @ApiParam(name = "id", value = "应收发票ID", required = true)
                                         @Valid
                                         @NotNull(message = "应收发票ID不能为空")
                                                 Long id
    ) throws Exception {
        Result result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            receivableInvoiceService.getAlreadyWriteInvoice(userVO, id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (BusinessException e) {
            logger.error("冲销应收发票失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("冲销应收发票失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "分页获取调用配货出库列表信息", notes = "分页获取调用配货出库列表信息 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCallDistrOutPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<CallDistrOutPageVO>>> getCallDistrOutPage(HttpServletRequest request,
                                                                      @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                      @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                      @ApiParam(value = "购货单位ID", required = true) @RequestParam Long supplierId,
                                                                      @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
                                                                      @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
                                                                      @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                                                      @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<List<CallDistrOutPageVO>>> result = new Result<Page<List<CallDistrOutPageVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<CallDistrOutPageVO> callDistrOutPage = receivableInvoiceService
                    .getCallDistrOutPage(pageNo, pageSize, loginUser, page, startTime, endTime, orderName, orderType, supplierId);
            page.setResult(callDistrOutPage);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页获取调用配货出库列表信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "通过配货出库ID集合获取可调用单据信息 ", notes = "通过配货出库ID集合获取可调用单据信息 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCallDistrOutDeatil", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<DistrOutPageVO>> getCallDistrOutDeatil(HttpServletRequest request,
                                                              @RequestBody(required = false)
                                                              @ApiParam(name = "ids[]", value = "配货出库ID数组", required = true)
                                                              @Valid
                                                              @NotNull(message = "配货出库ID数组不能为空")
                                                              @Size(min = 1, message = "配货出库ID数组不能为空")
                                                                      List<Long> ids
    ) throws Exception {
        Result<List<DistrOutPageVO>> result = new Result();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        List<DistrOutPageVO> distrOutPageVOS = receivableInvoiceService.getCallDistrOutDeatil(loginUser, ids);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrOutPageVOS);
        return result;
    }

    @ApiOperation(value = "分页获取修改记录列表信息", notes = "分页获取修改记录列表信息 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInvoiceModifyRecordPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<InvoiceModifyRecordPageVO>>> getInvoiceModifyRecordPage(HttpServletRequest request,
                                                                                    @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                                    @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                                    @ApiParam(value = "应收发票ID", required = true) @RequestParam Long id
    ) {
        Result<Page<List<InvoiceModifyRecordPageVO>>> result = new Result<Page<List<InvoiceModifyRecordPageVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<InvoiceModifyRecordPageVO> invoiceModifyRecordPage = receivableInvoiceService
                    .getInvoiceModifyRecordPage(pageNo, pageSize, loginUser, page, id);
            page.setResult(invoiceModifyRecordPage);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页获取修改记录列表信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出应收发票信息", notes = "导出应收发票信息 | 开发者:szeshi.sun | 已完成 ")
    @RequestMapping(value = "/exportReveivableInvoice/{id}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的应收发票ID", required = true, dataType = "Long", paramType = "path")
    public void exportReveivableInvoice(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName() + "应收发票";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            SaveReveivableInvoiceVO saveReveivableinvoice = receivableInvoiceService.getSaveReveivableInvoice(loginUser, id);
            receivableInvoiceService.exportExcel(output, saveReveivableinvoice, loginUser);
        } catch (Exception e) {
            logger.error("导出应收发票信息错误:" + e.getMessage(), e);
        }
    }

    @ApiOperation(value = "导出应收发票修改记录信息", notes = "导出应收发票修改记录信息 | 开发者:szeshi.sun | 已完成 ")
    @RequestMapping(value = "/exportReveivableInvoiceModifyRecord/{id}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的应收发票ID", required = true, dataType = "Long", paramType = "path")
    public void exportReveivableInvoiceModifyRecord(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName() + "应收发票修改记录";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            List<InvoiceModifyRecordPageVO> invoiceModifyRecordPage = receivableInvoiceService.getInvoiceModifyRecord(loginUser, id);
            receivableInvoiceService.exportExcelModifyRecord(output, invoiceModifyRecordPage, loginUser);
        } catch (Exception e) {
            logger.error("导出应收发票修改记录信息错误:" + e.getMessage(), e);
        }
    }

    @ApiOperation(value = "计算应收发票单据金额", notes = "计算应收发票单据金额 | 开发者 zeshi.sun| 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/calculationReveivableInvoice", method = RequestMethod.POST)
    @ResponseBody
    public Result<SaveReveivableInvoiceVO> calculationReveivableInvoice(HttpServletRequest request,
                                                                        @ApiParam(value = "计算应收发票单据金额", required = true) @RequestBody SaveReveivableInvoiceVO saveReveivableInvoiceVO) {
        Result<SaveReveivableInvoiceVO> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, receivableInvoiceService.calculationReveivableInvoice(loginUser, saveReveivableInvoiceVO));

        } catch (BusinessException e) {
            logger.error("计算应收发票单据金额错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("计算应收发票单据金额错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "获取应收发票的开票信息", notes = "获取应收发票的开票信息 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getSaveReveivableInvoiceInfo", method = RequestMethod.GET)
    public Result<SaveReceivableInvoiceInfoVO> getSaveReveivableInvoiceInfo(HttpSession session,
                                                                            @ApiParam(value = "购货单位ID", required = false) @RequestParam(required = false) Long supplierId
    ) throws Exception {
        Result<SaveReceivableInvoiceInfoVO> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            SaveReceivableInvoiceInfoVO saveReceivableInvoiceInfoVO = receivableInvoiceService.getSaveReveivableInvoiceInfo(userVO, supplierId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, saveReceivableInvoiceInfoVO);
        } catch (BusinessException e) {
            logger.error("获取应收发票的开票信息失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("获取应收发票的开票信息失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

}