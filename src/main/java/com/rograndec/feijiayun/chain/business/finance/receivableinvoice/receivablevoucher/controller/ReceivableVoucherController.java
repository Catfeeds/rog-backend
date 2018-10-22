package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.controller;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.service.ReceivableVoucherService;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.vo.*;
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
 * 功能描述：应收发票-应收贷项凭证
 * Created by zeshi.sun on 2018/1/13 13:39
 */
@Api(value = "receivable_voucher", description = "应收发票-应收贷项凭证接口服务")
@RestController
@RequestMapping("finance/receivableinvoice/receivablevoucher")
@Validated
public class ReceivableVoucherController {

    private static final Log logger = LogFactory.getLog(ReceivableVoucherController.class);

    @Autowired
    private ReceivableVoucherService receivableVoucherService;

    @ApiOperation(value = "分页获取应收贷项凭证信息", notes = "分页获取应收贷项凭证信息 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getReceivableVoucherPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<TabableTotalVoucherVO>> getReceivableVoucherPage(HttpServletRequest request,
                                                                        @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                        @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                        @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
                                                                        @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
                                                                        @ApiParam(value = "购货单位编码", required = false) @RequestParam(required = false) String purchaseUnitCode,
                                                                        @ApiParam(value = "购货单位名称", required = false) @RequestParam(required = false) String purchaseUnitName,
                                                                        @ApiParam(value = "过账单号", required = false) @RequestParam(required = false) String code,
                                                                        @ApiParam(value = "过账人员", required = false) @RequestParam(required = false) String postManName,
                                                                        @ApiParam(value = "单据状态", required = false) @RequestParam(required = false) Integer status,
                                                                        @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                                                        @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<TabableTotalVoucherVO>> result = new Result<Page<TabableTotalVoucherVO>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            TabableTotalVoucherVO receivableVoucherPages = receivableVoucherService
                    .getReceivableVoucherPage(pageNo, pageSize, loginUser, page, startTime, endTime, purchaseUnitCode, purchaseUnitName, code, postManName,
                            status, orderName, orderType);
            page.setResult(receivableVoucherPages);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页获取应收贷项凭证信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "保存应收贷项凭证单据", notes = "保存应收贷项凭证单据 | 开发者 zeshi.sun| 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveReveivableVoucher", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveReveivableVoucher(HttpServletRequest request,
                                                @ApiParam(value = "保存应收贷项凭证单据", required = true) @RequestBody SaveReveivableVoucherVO saveReveivableVoucherVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, receivableVoucherService.saveReveivableVoucher(loginUser, saveReveivableVoucherVO));

        } catch (BusinessException e) {
            logger.error("保存应收贷项凭证单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("保存应收贷项凭证单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看应收贷项凭证", notes = "查看应收贷项凭证 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getSaveReveivableVoucher", method = RequestMethod.GET)
    public Result<SaveReveivableVoucherVO> getPurchaseUnit(HttpSession session,
                                                           @ApiParam(value = "应收贷项凭证ID", required = true) @RequestParam Long id) throws Exception {
        Result<SaveReveivableVoucherVO> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            SaveReveivableVoucherVO saveReveivableVoucher = receivableVoucherService.getSaveReveivableVoucher(userVO, id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, saveReveivableVoucher);
        } catch (BusinessException e) {
            logger.error("查看应收贷项凭证失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("查看应收贷项凭证失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看应收贷项凭证草稿缓存", notes = "查看应收贷项凭证草稿缓存 | 开发者 zeshi.sun | 已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierId", value = "购货单位id"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/daftCache/{supplierId}", method = RequestMethod.GET)
    public Result<List<DraftCacheVO>> daftCacheVO(@PathVariable Long supplierId, @ApiIgnore UserVO userVO) {
        Result<List<DraftCacheVO>> result = new Result<>();
        try {
            List<DraftCacheVO> draftCacheVO = receivableVoucherService.getDraftCacheVO(userVO,supplierId);

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, draftCacheVO);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("查看应收贷项凭证草稿缓存:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "保存应收贷项凭证草稿缓存", notes = "保存应收贷项凭证草稿缓存 | 开发者 zeshi.sun | 已完成")
    @RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
    public Result<DraftCacheVO> saveDraftCache(@ApiIgnore UserVO userVO, @RequestBody DraftCacheVO draftCacheVO) {

        Result<DraftCacheVO> result = new Result<>();
        try {

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, receivableVoucherService.saveDraftCache(userVO, draftCacheVO));

        } catch (ApprovalFlowBizException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
            return result;
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("保存应收贷项凭证草稿缓存:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }

        return result;
    }

    @ApiOperation(value = "删除应收贷项凭证草稿缓存", notes = "删除应收贷项凭证草稿缓存 | 开发者 zeshi.sun | 已完成")
    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}/{supplierId}", method = RequestMethod.DELETE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
                    , required = true, paramType="path"),
            @ApiImplicitParam(name = "supplierId", value = "购货单位id"
                    , required = true, paramType="path")
    })
    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String redisKeyValue,@PathVariable(required = true) Long supplierId) {

        Result result = new Result<>();
        try {

            receivableVoucherService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.RECEIVABLE_VOUCHER.getCodePrefix(), redisKeyValue, supplierId);

            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("删除应收贷项凭证草稿缓存:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "分页获取修改记录列表信息", notes = "分页获取修改记录列表信息 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getVoucherModifyRecordPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<VoucherModifyRecordPageVO>>> getVoucherModifyRecordPage(HttpServletRequest request,
                                                                                    @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                                    @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                                    @ApiParam(value = "应收贷项凭证ID", required = true) @RequestParam Long id
    ) {
        Result<Page<List<VoucherModifyRecordPageVO>>> result = new Result<Page<List<VoucherModifyRecordPageVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<VoucherModifyRecordPageVO> voucherModifyRecordPageVOS = receivableVoucherService
                    .getVoucherModifyRecordPage(pageNo, pageSize, loginUser, page, id);
            page.setResult(voucherModifyRecordPageVOS);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页获取修改记录列表信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出应收贷项凭证信息", notes = "导出应收贷项凭证信息 | 开发者:szeshi.sun | 已完成 ")
    @RequestMapping(value="/exportReveivableVoucher/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的应收贷项凭证ID", required = true, dataType = "Long", paramType="path")
    public void exportReveivableVoucher(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"应收贷项凭证";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            SaveReveivableVoucherVO saveReveivableVoucher = receivableVoucherService.getSaveReveivableVoucher(loginUser, id);
            receivableVoucherService.exportExcel(output,saveReveivableVoucher,loginUser);
        }catch(Exception e){
            logger.error("导出应收贷项凭证信息错误:"+e.getMessage(),e);
        }
    }

    @ApiOperation(value = "导出应收贷项凭证修改记录信息", notes = "导出应收贷项凭证修改记录信息 | 开发者:szeshi.sun | 已完成 ")
    @RequestMapping(value="/exportReveivableInvoiceModifyRecord/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的应收贷项凭证ID", required = true, dataType = "Long", paramType="path")
    public void exportReveivableInvoiceModifyRecord(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"应收贷项凭证";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<VoucherModifyRecordPageVO> voucherModifyRecordPageVOS = receivableVoucherService.getVoucherModifyRecordPageList(loginUser, id);
            receivableVoucherService.exportExcelModifyRecord(output,voucherModifyRecordPageVOS,loginUser);
        }catch(Exception e){
            logger.error("导出应收贷项凭证修改记录信息错误:"+e.getMessage(),e);
        }
    }

    @ApiOperation(value = "分页获取退入商品信息", notes = "分页获取退入商品信息 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getDistrReturnInTotal", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<DistrReturnInTotalVO>> getDistrOutPage(HttpServletRequest request,
                                                              @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                              @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                              @ApiParam(value = "购货单位ID", required = true) @RequestParam Long purchaseUnitId,
                                                              @ApiParam(value = "查询条件", required = false) @RequestParam(required = false) String key
    ) {
        Result<Page<DistrReturnInTotalVO>> result = new Result<Page<DistrReturnInTotalVO>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            DistrReturnInTotalVO distrReturnInPageTotalVO = receivableVoucherService
                    .getDistrReturnInTotal(pageNo, pageSize, loginUser, page, purchaseUnitId, key);
            page.setResult(distrReturnInPageTotalVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页获取退入商品信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "分页获取调用配后退回入库列表信息", notes = "分页获取调用配后退回入库列表信息 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCallDistrReturnInPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<CallDistrReturnInPageVO>>> getCallDistrReturnInPage(HttpServletRequest request,
                                                                                @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                                @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                                @ApiParam(value = "购货单位ID", required = true) @RequestParam Long supplierId,
                                                                                @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
                                                                                @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
                                                                                @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                                                                @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<List<CallDistrReturnInPageVO>>> result = new Result<Page<List<CallDistrReturnInPageVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<CallDistrReturnInPageVO> callDistrReturnInPage = receivableVoucherService
                    .getCallDistrReturnInPage(pageNo, pageSize, loginUser, page, startTime, endTime, orderName, orderType, supplierId);
            page.setResult(callDistrReturnInPage);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页获取调用配后退回入库列表信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "通过配后退回入库ID集合获取可调用单据信息 ", notes = "通过配后退回入库ID集合获取可调用单据信息 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCallDistrReturnInDeatil", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<DistrReturnInPageListVO>> getCallDistrReturnInDeatil(HttpServletRequest request,
                                                              @RequestBody(required = false)
                                                              @ApiParam(name = "ids[]", value = "配后退回入库ID数组", required = true)
                                                              @Valid
                                                              @NotNull(message = "配后退回入库ID数组不能为空")
                                                              @Size(min = 1, message = "配后退回入库ID数组不能为空")
                                                                      List<Long> ids
    ) throws Exception {
        Result<List<DistrReturnInPageListVO>> result = new Result();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        List<DistrReturnInPageListVO> distrReturnInPageListVOS = receivableVoucherService.getCallDistrReturnInDeatil(loginUser, ids);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrReturnInPageListVOS);
        return result;
    }

    @ApiOperation(value = "冲销应收贷项凭证", notes = "冲销应收贷项凭证 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getAlreadyWriteVoucher", method = RequestMethod.POST)
    public Result getAlreadyWriteVoucher(HttpSession session,
                                         @RequestBody(required = false)
                                         @ApiParam(name = "id", value = "应收贷项凭证ID", required = true)
                                         @Valid
                                         @NotNull(message = "应收贷项凭证ID不能为空")
                                                 Long id
    ) throws Exception {
        Result result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            receivableVoucherService.getAlreadyWriteVoucher(userVO, id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (BusinessException e) {
            logger.error("冲销应收贷项凭证失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("冲销应收贷项凭证失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

}
