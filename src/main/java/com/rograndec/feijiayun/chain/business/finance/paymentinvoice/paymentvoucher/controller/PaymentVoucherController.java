package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.controller;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.entity.PaymentVoucherModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.service.PaymentVoucherService;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.valid.RequestParamValid;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 功能描述：应付贷项凭证
 * Created by ST on 2018/1/8 14:05
 */
@Api(value = "finance/payment_voucher",description = "应付贷项凭证接口服务")
@RequestMapping("paymentVoucher")
@RestController
public class PaymentVoucherController {


    private static Logger logger = LoggerFactory.getLogger(PaymentVoucherController.class);

    @Autowired
    private PaymentVoucherService paymentVoucherService;


    @ApiOperation(value = "应付贷项凭证列表", notes = "应付贷项凭证列表 | 开发者 孙腾 | 开发中")
    @RequestMapping(value="/getPaymentVoucherListPage",method= RequestMethod.POST)
    @ApiImplicitParam(name = "requestVoucherParamVO", value = "检索条件", required = true, dataType = "RequestVoucherParamVO")
    public Result<Page<PaymentVoucherTotalVO>> getPaymentVoucherListPage(@ApiIgnore UserVO userVO,@RequestParamValid @RequestBody RequestVoucherParamVO requestVoucherParamVO){
        Result<Page<PaymentVoucherTotalVO>> result = new Result<>();
        Page<PaymentVoucherTotalVO> page = new Page<>(requestVoucherParamVO.getPageNo(),requestVoucherParamVO.getPageSize());
        paymentVoucherService.getPaymentVoucherListPage(page,requestVoucherParamVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }


    @ApiOperation(value = "应付贷项凭证详情", notes = "应付贷项凭证详情 | 开发者 孙腾 | 开发中")
    @RequestMapping(value="/getPaymentVoucherDetailById/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "应付贷项凭证ID", required = true, dataType = "Long",paramType = "path")
    public Result<PaymentVoucherVO> getPaymentVoucherDetailById(@ApiIgnore UserVO userVO,@PathVariable("id") Long id){
        Result<PaymentVoucherVO> result = new Result<>();
        PaymentVoucherVO paymentVoucherVO = paymentVoucherService.getPaymentVoucherDetailById(id, userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,paymentVoucherVO);
        return result;
    }


    @ApiOperation(value = "保存和修改应付贷项凭证", notes = "保存和修改应付贷项凭证 | 开发者 孙腾 | 开发中")
    @RequestMapping(value="/saveAndUpdatePaymentVoucher",method= RequestMethod.POST)
    @ApiImplicitParam(name = "paymentVoucherVO", value = "检索条件", required = true, dataType = "PaymentVoucherVO")
    public Result  saveAndUpdatePaymentVoucher(@ApiIgnore UserVO userVO,@RequestBody PaymentVoucherVO paymentVoucherVO) throws Exception {
        Result result = new Result();
        String code = paymentVoucherService.saveAndUpdatePaymentVoucher(paymentVoucherVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,"单号:" + code);
        return result;

    }

    @ApiOperation(value = "应付贷项凭证冲销", notes = "应付贷项凭证冲销 | 开发者 孙腾 | 开发中")
    @RequestMapping(value="/reversal/{id}",method= RequestMethod.POST)
    @ApiImplicitParam(name = "id", value = "应付贷项凭证ID", required = true, dataType = "String",paramType = "path")
    public Result  reversal(@ApiIgnore UserVO userVO,@PathVariable("id") Long id) throws Exception {
        Result result = new Result();
        paymentVoucherService.reversal(id,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }


    @ApiOperation(value = "应付贷项凭证--应付贷项凭证草稿", notes = "应付贷项凭证--应付贷项凭证草稿 | 开发者 孙腾 | 开发中")
    @RequestMapping(value="/getPaymentVoucherDraftInfo/{supplierId}",method= RequestMethod.GET)
    public Result<List<DraftCacheVO>>  getPaymentVoucherDraftInfo(@ApiIgnore UserVO userVO,@PathVariable("supplierId")Long supplierId){
        Result<List<DraftCacheVO>> result = new Result<>();
        List<DraftCacheVO> draftCacheVOList = paymentVoucherService.getDraftCacheVO(userVO,supplierId);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVOList);
        return result;
    }

    @ApiOperation(value = "保存应付贷项凭证草稿", notes = "保存应付贷项凭证草稿 | 开发者 孙腾 | 开发中")
    @RequestMapping(value="/savePaymentVoucherDraft",method= RequestMethod.POST)
    public Result<DraftCacheVO<PaymentVoucherVO>>  savePaymentVoucherDraft(@ApiIgnore UserVO userVO,@RequestBody DraftCacheVO<PaymentVoucherVO> paymentVoucherVO){
        Result<DraftCacheVO<PaymentVoucherVO>> result = new Result<>();
        paymentVoucherVO = paymentVoucherService.saveDraftCache(userVO,paymentVoucherVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,paymentVoucherVO);
        return  result;
    }

    @ApiOperation(value = "删除应付贷项凭证草稿缓存", notes = "删除应付贷项凭证草稿缓存 | 开发者 孙腾 | 已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}/{supplierId}", method = RequestMethod.POST)
    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(name = "redisKeyValue") String redisKeyValue,
                                  @PathVariable("supplierId")Long supplierId) {

        Result result = new Result<>();

        if(StringUtils.isBlank(redisKeyValue)){
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"删除失败，参数错误！");
            return result;
        }
        paymentVoucherService.removeDraftCache(supplierId,userVO.getEnterpriseId(), OrderRule.PAYMENT_VOUCHER.getCodePrefix(),redisKeyValue);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return  result;
    }

    @ApiOperation(value = "商品搜索", notes = "商品搜索 | 开发者 孙腾 | 开发中")
    @RequestMapping(value="/getGoodsInfoByParam",method= RequestMethod.GET)
    public Result<Page<List<PurchaseAndDistrOutShelfVO>>>  getGoodsInfoByParam(@ApiIgnore UserVO userVO,
                                                                         @RequestParam(required = false) @ApiParam(name = "param",value = "商品编码，名称，检索码，通用名称",required = true) String param,
                                                                         @RequestParam(required = false) @ApiParam(name = "supplierId",value = "供货单位Id",required = true) Long supplierId,
                                                                         @RequestParam(required = false) @ApiParam(name = "pageNo",value = "页码",required = true)Integer pageNo,
                                                                         @RequestParam(required = false)@ApiParam(name = "pageSize",value = "每页显示数据",required = true) Integer pageSize){
        Result<Page<List<PurchaseAndDistrOutShelfVO>>> result = new Result<>();
        if(pageNo == null || pageNo <= 0 || pageSize == null || pageSize <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page<List<PurchaseAndDistrOutShelfVO>> page = new Page<>();

        paymentVoucherService.getGoodsInfoByParam(userVO,page,param,supplierId);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        return result;
    }


    @ApiOperation(value = "调用购进退出出库单/配进退出出库单", notes = "调用购进退出出库单/配进退出出库单 | 开发者 孙腾 | 开发中")
    @RequestMapping(value="/getPurchaseAndDistrOutInfo",method= RequestMethod.GET)
    public Result<Page<List<PurchaseAndDistrOutVO>>>  getPurchaseAndDistrOutInfo(@ApiIgnore UserVO userVO,
                                                                     @RequestParam(required = false) @ApiParam(name = "supplierId",value = "供货单位id",required = true) Long supplierId,
                                                                     @RequestParam(required = false) @ApiParam(name = "startDate",value = "开始日期",required = false) String startDate,
                                                                     @RequestParam(required = false) @ApiParam(name = "endDate",value = "结束日期",required = false) String endDate,
                                                                     @RequestParam(required = false) @ApiParam(name = "codeOrder",value = "编码排序0/倒序；1/正序;默认0",required = true) Integer codeOrder,
                                                                     @RequestParam(required = false) @ApiParam(name = "dateOrder",value = "时间排序0/倒序；1/正序;默认0",required = true)Integer dateOrder,
                                                                     @RequestParam(required = false) @ApiParam(name = "pageNo",value = "页码",required = true)Integer pageNo,
                                                                     @RequestParam(required = false)@ApiParam(name = "pageSize",value = "每页显示数据",required = true) Integer pageSize
                                                                     ){

        Result<Page<List<PurchaseAndDistrOutVO>>> result = new Result<>();
        if(pageNo == null || pageNo <= 0 || pageSize == null || pageSize <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        RequestPurDistrOutParamVO purDistrOutParamVO = new RequestPurDistrOutParamVO();
        purDistrOutParamVO.setStartDate(startDate);
        purDistrOutParamVO.setEndDate(endDate);
        purDistrOutParamVO.setCodeOrder(codeOrder);
        purDistrOutParamVO.setDateOrder(dateOrder);
        purDistrOutParamVO.setSupplierId(supplierId);
        Page<List<PurchaseAndDistrOutVO>> page = new Page<>(pageNo,pageSize);
        purDistrOutParamVO.setStart(page.getStart());
        paymentVoucherService.getPurchaseAndDistrOutInfo(userVO,page,purDistrOutParamVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "调用购进退出出库单/配进退出出库单 明细", notes = "调用购进退出出库单/配进退出出库单 明细 | 开发者 孙腾 | 开发中")
    @RequestMapping(value="/getPurchaseAndDistrOutDetailInfo",method= RequestMethod.GET)
    public Result<List<PurchaseAndDistrOutShelfVO>>  getPurchaseAndDistrOutDetailInfo(@ApiIgnore UserVO userVO,
                                                                                      @ApiParam(name = "ids",value = "购进退出出库或者配进退出出库单的id集合" ,required = true) @RequestParam(required = false) String ids) throws Exception {

        Result<List<PurchaseAndDistrOutShelfVO>> result = new Result<>();
        if(StringUtils.isBlank(ids)){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        List<PurchaseAndDistrOutShelfVO> purReturnOutDetailInfo = paymentVoucherService.getPurReturnOutDetailInfo(userVO, ids);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,purReturnOutDetailInfo);
        return result;
    }


    @ApiOperation(value = "查询应付贷项凭证的修改记录", notes = "查询应付贷项凭证的修改记录 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getPaymentVoucherModifyRecord/{paymentId}/{pageNo}/{pageSize}",method= RequestMethod.GET)
    public Result<Page<List<PaymentVoucherModifyRecord>>> getPaymentVoucherModifyRecord(@ApiIgnore UserVO userVO,
                                                                                        @ApiParam(value = "应付贷项凭证id", required = true) @PathVariable Long paymentId,
                                                                                        @ApiParam(value = "页码", required = true) @PathVariable int pageNo,
                                                                                        @ApiParam(value = "每页显示的记录数", required = true) @PathVariable int pageSize){
        Result<Page<List<PaymentVoucherModifyRecord>>> result = new Result<>();
        if(pageNo <= 0 || pageSize <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page<List<PaymentVoucherModifyRecord>> page = new Page<List<PaymentVoucherModifyRecord>>(pageNo, pageSize);

        paymentVoucherService.getPaymentVoucherModifyRecord(userVO,page,paymentId);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "应付贷项凭证导出Excel", notes = "应付贷项凭证导出Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportExcel",method= RequestMethod.GET)
    public void exportExcel(HttpServletResponse response, HttpSession session,
                            @ApiParam(value = "应付贷项凭证的id", required = false) @RequestParam(required = false) Long id
                            ) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "应付贷项凭证";
        String suffix = ".xlsx";
        String fileName = file + suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        paymentVoucherService.export(output,userVO,id);
        output.close();
        output.flush();
    }

    @ApiOperation(value = "修改记录导出", notes = "修改记录导出 | 开发者 孙腾 | 开发中")
    @RequestMapping(value="/exportUpdateRecord/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "应付贷项凭证id", required = true, dataType = "Long", paramType="path")
    public void exportUpdateRecord(HttpServletResponse response,
                                   @PathVariable Long id,@ApiIgnore UserVO userVO) throws Exception {

        OutputStream output = response.getOutputStream();
        String name = "应付贷项凭证修改信息";
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
        paymentVoucherService.exportUpdateRecord(output,id,userVO);
        output.close();
        output.flush();
    }


}