
package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.controller;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity.VerificationFormModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.service.VerificationFormService;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.PurchaseInStorageReportPageVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.SaleOutORreturnVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.VerificationFormCountVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.VerificationFormVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
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
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：
 * VerificationForm
 * <p>
 * Created by jiaruifeng on
 */

@Api(value = "verification_form_controller", description = "核销单")
@RequestMapping("verification/form/")
@RestController
@Validated
public class VerificationFormController {

    private static final Log logger = LogFactory.getLog(VerificationFormController.class);

    @Autowired
    private VerificationFormService verificationFormService;

    @ApiOperation(value = "根据核销单id查询核销单 开发者 | 贾瑞丰| 未调试", notes = "根据核销单id查询核销单 开发者 | 贾瑞丰| 未调试")
    @RequestMapping(value = "/getVerificationFormVOById/{id}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "核销单id", required = true, paramType = "path")
    public Result<VerificationFormVO> getVerificationFormVOById(@ApiIgnore UserVO userVO, @PathVariable Long id) {
        Result<VerificationFormVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, verificationFormService.getVerificationFormVOById(id));
        return result;
    }

    @ApiOperation(value = "根据核销单id冲销核销单 开发者 | 贾瑞丰| 未调试", notes = "根据核销单id冲销核销单 开发者 | 贾瑞丰| 未调试")
    @RequestMapping(value = "/writeOffVerificationFormById/{id}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "核销单id", required = true, paramType = "path")
    public Result<String> writeOffVerificationFormById(@ApiIgnore UserVO userVO, @PathVariable Long id) {
        Result<String> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, verificationFormService.writeOffVerificationFormById(userVO,id));
        return result;
    }

    @ApiOperation(value = "新增 | 开发者 贾瑞丰 | 未联调", notes = "新增 | 开发者 贾瑞丰 | 未联调")
    @RequestMapping(value = "/addVerificationForm", method = RequestMethod.POST)
    public Result<String> addVerificationForm(@ApiIgnore UserVO userVO,
                                              @ApiParam(value = "实销实结核销单", required = true)
                                              @RequestBody
                                              @Valid VerificationFormVO verificationFormVO
    ) throws Exception {
        Result<String> result = new Result<String>();
        try {
            verificationFormService.save(userVO, verificationFormVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (BusinessException e) {
            logger.error("核销单添加数据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        } catch (Exception e) {
            logger.error("核销单添加数据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "修改核销单 | 开发者 贾瑞丰 | 未联调", notes = "修改核销单 | 开发者 贾瑞丰 | 未联调")
    @RequestMapping(value = "/modifyVerificationForm", method = RequestMethod.POST)
    public Result<String> modifyVerificationForm(@ApiIgnore UserVO userVO,
                                                 @ApiParam(value = "实销实结核销单", required = true)
                                                 @RequestBody
                                                 @Valid VerificationFormVO verificationFormVO, @ApiParam(value = "修改原因", required = true) @RequestParam(required = true) String reason
    ) throws Exception {
        Result<String> result = new Result<String>();
        try {
            verificationFormService.modifyVerificationForm(userVO, verificationFormVO, reason);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (BusinessException e) {
            logger.error("核销单添加数据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        } catch (Exception e) {
            logger.error("核销单添加数据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }


    @ApiOperation(value = "核销单分页 | 开发者 贾瑞丰 | 未联调", notes = "核销单分页 | 开发者 贾瑞丰 | 未联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @GetMapping(value = "/getVerificationFormPage")
    public Result<Page<VerificationFormCountVO>> getPeriodPage(@ApiIgnore UserVO userVO,
                                                               @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                               @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                               @ApiParam(value = "核销单编码精确匹配", required = false) @RequestParam(required = false) String code,
                                                               @ApiParam(value = "供货单位编码精确匹配", required = false) @RequestParam(required = false) String supplierCode,
                                                               @ApiParam(value = "供货单位名称模糊匹配（like '%supplierName%' ）", required = false) @RequestParam(required = false) String supplierName,
                                                               @ApiParam(value = "核销人员姓名精确匹配", required = false) @RequestParam(required = false) String operatorName,
                                                               @ApiParam(value = "开始日期", required = false) @RequestParam(required = false) Date startDate,
                                                               @ApiParam(value = "结束日期", required = false) @RequestParam(required = false) Date endDate,
                                                               @ApiParam(value = "状态", required = false) @RequestParam(required = false) Long status,
                                                               @ApiParam(value = "排序字段", required = false) @RequestParam(required = false) String sortField,
                                                               @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required = false) String sort
    ) {
        Result<Page<VerificationFormCountVO>> result = new Result<>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page<VerificationFormCountVO> page = new Page<>(pageNo, pageSize);

//            Long enterpriseId = userVO.getEnterpriseId();
            verificationFormService.getVerificationFormVOPage(page, userVO, supplierCode, supplierName, operatorName, code, sort, status, sortField, startDate, endDate);

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("核销单列表分页查询错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "获取企业的实销实结的供货单位列表 | 开发者 贾瑞丰 | 未联调", notes = "获取企业的实销实结的供货单位列表 | 开发者 贾瑞丰 | 未联调")
    @RequestMapping(value = "/suppliers", method = RequestMethod.GET)
    public Result<List<Supplier>> getActualSalesSettlementSuppliers(@ApiIgnore UserVO userVO){
        Result<List<Supplier>> result = new Result<List<Supplier>>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, verificationFormService.getActualSalesSettlementSuppliers(userVO));
        return result;
    }

    @ApiOperation(value = "获取核销单修改记录 | 开发者 贾瑞丰 | 未联调", notes = "获取核销单修改记录 | 开发者 贾瑞丰 | 未联调")
    @RequestMapping(value = "/getModifyRecord/{id}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "核销单id", required = true, paramType = "path")
    public Result<Page<List<VerificationFormModifyRecord>>> getModifyRecord(@PathVariable Long id,
                                                                            @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                            @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize
    ) throws Exception {
        Result<Page<List<VerificationFormModifyRecord>>> result = new Result<>();
        if (pageNo <= 0 || pageSize <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page<List<VerificationFormModifyRecord>> page = new Page<>(pageNo, pageSize);
        verificationFormService.getModifyRecord(id,page);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }


    @ApiOperation(value = "核销单--入库清单查询 | 开发者 贾瑞丰 | 未联调", notes = "核销单入库清单 | 开发者 贾瑞丰 | 未联调")
    @RequestMapping(value = "/getPurchaseInStorageReportPage", method = RequestMethod.GET)
    public Result<List<PurchaseInStorageReportPageVO>> getPurchaseInStorageReportPage
            (
                    @ApiIgnore UserVO userVO,
                    @ApiParam(value = "供货单位ID(总店用24加盟店用20)", required = true) @RequestParam(required = true) String supplierId,
                    @ApiParam(value = "批号(2018010603002)", required = true) @RequestParam(required = true) String lotNumber,
                    @ApiParam(value = "商品ID(3666)", required = true) @RequestParam(required = true) Long goodsId) {
        Result<List<PurchaseInStorageReportPageVO>> result = new Result<>();
        List<PurchaseInStorageReportPageVO> list = verificationFormService.getPurchaseInStorageReportPage(userVO, supplierId, lotNumber, goodsId);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        return result;
    }


    @ApiOperation(value = "核销单--销售清单查询 | 开发者 贾瑞丰 | 未联调", notes = "核销单--销售清单查询 | 开发者 贾瑞丰 | 未联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getSaleOutList", method = RequestMethod.GET)
    public Result<List<SaleOutORreturnVO>> getSaleOutList(@ApiIgnore UserVO userVO,
                                                          @ApiParam(value = "供货单位ID(总店用24加盟店用20)", required = true) @RequestParam(required = true) String supplierId,
                                                          @ApiParam(value = "开始日期(2017-01-13)", required = true) @RequestParam(required = true) Date startDate,
                                                          @ApiParam(value = "结束日期(2019-01-01)", required = true) @RequestParam(required = true) Date endDate
    ) {
        Result<List<SaleOutORreturnVO>> result = new Result<>();
        try {
            // 结束日期取整天的时候，时分秒被舍去，所以endDate需要+1
            List<SaleOutORreturnVO> list = verificationFormService.getSaleOutORreturn(userVO, supplierId, startDate, DateUtils.addDay(endDate,1));
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("获取销售出库数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "核销单--自动核销 | 开发者 贾瑞丰 | 未联调", notes = "核销单--自动核销 | 开发者 贾瑞丰 | 未联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/antoSaleOutORreturn", method = RequestMethod.GET)
    public Result<List<SaleOutORreturnVO>> antoSaleOutORreturn(@ApiIgnore UserVO userVO,
                                                          @ApiParam(value = "供货单位ID(总店用24加盟店用20)", required = true) @RequestParam(required = true) String supplierId,
                                                          @ApiParam(value = "开始日期(2017-01-13)", required = true) @RequestParam(required = true) Date startDate,
                                                          @ApiParam(value = "结束日期(2019-01-01)", required = true) @RequestParam(required = true) Date endDate
    ) {
        Result<List<SaleOutORreturnVO>> result = new Result<>();
        try {
            // 结束日期取整天的时候，时分秒被舍去，所以endDate需要+1
            List<SaleOutORreturnVO> list = verificationFormService.antoSaleOutORreturn(userVO, supplierId, startDate, DateUtils.addDay(endDate,1));
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("获取销售出库数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "保存核销单到草稿", notes = "保存应核销单到草稿 | 开发者 贾瑞丰| 未完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/save/draft", method = RequestMethod.POST)
    @ResponseBody
    public Result<DraftCacheVO> saveVerificationFormVOForDraft(@ApiIgnore UserVO userVO,
                                                               @ApiParam(value = "保存应核销单到草稿", required = true) @RequestBody DraftCacheVO draftCacheVO) {
        Result<DraftCacheVO> result = new Result<>();
        try {
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, verificationFormService.saveDraft(userVO, draftCacheVO));

        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("保存核销单草稿缓存:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }

        return result;
    }

    @ApiOperation(value = "删除核销单草稿", notes = "删除核销单草稿 | 开发者 贾瑞丰 | 未完成")
    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}", method = RequestMethod.DELETE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
                    , required = true, paramType = "path")
    })
    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String redisKeyValue) {

        Result result = new Result<>();
        try {

            verificationFormService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.VERIFICATION_FORM.getCodePrefix(), redisKeyValue);

            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("删除核销单草稿缓存:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "查看核销单草稿缓存", notes = "查看核销单草稿缓存 | 开发者 贾瑞丰 | 未完成")
    @RequestMapping(value = "/daftCache", method = RequestMethod.GET)
    public Result<List<DraftCacheVO>> daftCacheVO(@ApiIgnore UserVO userVO) {
        Result<List<DraftCacheVO>> result = new Result<>();
        try {
            List<DraftCacheVO> draftCacheVO = verificationFormService.getDraftCacheVO(userVO);

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, draftCacheVO);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("查看核销单草稿缓存:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }



    @ApiOperation(value="导出核销单 | 开发者 贾瑞丰 | 未完成", notes = "导出核销单  | 开发者 贾瑞丰 | 未完成"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value="/exportExcel/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看核销单单据ID", required = true, dataType = "Long", paramType="path")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,@PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "实销实结核销单";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            VerificationFormVO vo = verificationFormService.getVerificationFormVOById(id);
            verificationFormService.exportExcel(output,vo,loginUser);
            output.close();
        }catch(Exception e){
            logger.error("导出核销单信息错误:"+e.getMessage(),e);
        }
    }

    @ApiOperation(value = "导出核销单改记录信息", notes = "导出核销单改记录信息 | 开发者 贾瑞丰 | 已完成 ")
    @RequestMapping(value="/exportModifyRecord/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看核销单单据ID", required = true, dataType = "Long", paramType="path")
    public void exportModifyRecord(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"核销单改记录信息";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<VerificationFormModifyRecord>  verificationFormModifyRecordList= verificationFormService.getVerificationFormModifyRecord(id);
            verificationFormService.exportExcelModifyRecord(output,verificationFormModifyRecordList,loginUser);
        }catch(Exception e){
            logger.error("导出核销单改记录信息:"+e.getMessage(),e);
        }
    }

}