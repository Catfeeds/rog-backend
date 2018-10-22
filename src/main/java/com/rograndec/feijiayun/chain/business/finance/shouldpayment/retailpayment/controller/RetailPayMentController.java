package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.controller;

import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.service.RetailPayMentService;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo.*;
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
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Api(value="RetailPayMentController",description = "应收缴款-零售缴款")
@RestController
@RequestMapping("/finance/shoulepayment/payment")
public class RetailPayMentController {

    private static final Log logger = LogFactory.getLog(RetailPayMentController.class);

    @Autowired
    private RetailPayMentService retailPayMentService;

    @ApiOperation(value = "分页查询缴款单据列表", notes = "分页查询缴款单据列表 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getPayMentByParam", method = RequestMethod.POST)
    public Result<Page<RetailPayMentResponseVO>> getPayMentByParam(HttpSession session,
           @ApiParam(value = "分页参数",required = true) @RequestBody RetailPayMentRageParamVO paramVO){
        Result<Page<RetailPayMentResponseVO>> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, retailPayMentService.getPayMentByParam(paramVO,loginUser));
        } catch (BusinessException e){
            logger.error("分页查询缴款单据列表失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("分页查询缴款单据列表失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询缴款单据明细", notes = "查询缴款单据明细 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getPayMentDetail", method = RequestMethod.GET)
    public Result<RetailPaymentVO> getPayMentDetail(HttpSession session,
           @ApiParam(value = "缴款id",required = true) @RequestParam Long id){
        Result<RetailPaymentVO> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, retailPayMentService.getPayMentDetail(id,loginUser));
        } catch (BusinessException e){
            logger.error("查询缴款单据明细失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("查询缴款单据明细失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

//    @ApiOperation(value = "修改缴款单据", notes = "修改缴款单据 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    @RequestMapping(value = "/updatePayMent", method = RequestMethod.POST)
//    public Result<String> updatePayMent(HttpSession session,
//           @ApiParam(value = "修改的缴款单据",required = true) @RequestBody RetailPaymentVO paymentVO){
//        Result<String> result = new Result<>();
//        try {
//            // 当前登录用户数据
//            UserVO loginUser = (UserVO) session.getAttribute("user");
//            String message = retailPayMentService.updatePayMent(paymentVO,loginUser);
//            result.setBizCodeSuccessInfo(SysCode.SUCCESS, message);
//        } catch (BusinessException e){
//            logger.error("修改缴款单据错误:", e);
//            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
//            return result;
//        } catch (Exception e) {
//            logger.error("修改缴款单据错误:", e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//            return result;
//        }
//        return result;
//    }

    @ApiOperation(value = "冲销缴款单据", notes = "冲销缴款单据 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/chargrAgainstPayMent", method = RequestMethod.POST)
    public Result<String> chargrAgainstPayMent(HttpSession session,
           @ApiParam(value = "待冲销的日结单据ID集合",required = true) @RequestBody List<Long> ids){
        Result<String> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String message = retailPayMentService.chargrAgainstPayMent(ids,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, message);
        } catch (BusinessException e){
            logger.error("冲销缴款单据错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("冲销缴款单据错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "根据门店获取待缴款单据", notes = "根据门店获取待缴款单据 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getWillPayMentByStore", method = RequestMethod.GET)
    public Result<List<RetailPaymentItemVO>> getWillPayMentByStore(HttpSession session,
           @ApiParam(value = "门店ID,分店登录不用传") @RequestParam(required = false) Long storeId){
        Result<List<RetailPaymentItemVO>> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, retailPayMentService.getWillPayMentByStore(storeId,loginUser));
        } catch (BusinessException e){
            logger.error("根据门店获取待缴款单据错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("根据门店获取待缴款单据错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }

    @ApiOperation(value = "新增/修改缴款单据", notes = "新增/修改缴款单据 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/savePayMent", method = RequestMethod.POST)
    public Result<String> savePayMent(HttpSession session,
           @ApiParam(value = "待新增/修改缴款单据",required = true) @RequestBody RetailPaymentVO paymentVO){
        Result<String> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String message = "";
            if(paymentVO.getId() == null) {
                message = retailPayMentService.savePayMent(paymentVO, loginUser);
            }else {
                message = retailPayMentService.updatePayMent(paymentVO,loginUser);
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, message);
        } catch (BusinessException e){
            logger.error("新增/修改缴款单据错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("新增/修改缴款单据错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看缴款修改记录", notes = "查看缴款修改记录 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getPayMentModifyRecord", method = RequestMethod.GET)
    public Result<Page<List<RetailPaymentModifyRecordVO>>> getPayMentModifyRecord(HttpSession session,
           @ApiParam(value = "页码",required = true) @RequestParam Integer pageNo,
           @ApiParam(value = "页容量",required = true) @RequestParam Integer pageSize,
           @ApiParam(value = "日结ID",required = true) @RequestParam Long id){
        Result<Page<List<RetailPaymentModifyRecordVO>>> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, retailPayMentService.getPayMentModifyRecord(pageNo,pageSize,id,loginUser));
        } catch (BusinessException e){
            logger.error("查看缴款修改错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("查看缴款修改错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "导出Excel", notes = "按照模版将日结单导出至Excel | 开发者:马东 | 已完成")
    @RequestMapping(value="/exportExcel",method= RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = "缴款id",name = "id",required = true) @RequestParam Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "零售缴款";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            retailPayMentService.exportExcel(output,id,loginUser);
        }catch(Exception e){
            logger.error("导出缴款信息错误:"+e.getMessage(),e);
        }

    }

    @ApiOperation(value = "导出修改记录Excel", notes = "按照模版将缴款单修改记录导出至Excel | 开发者:马东 | 已完成")
    @RequestMapping(value="/exportExcelModifyRecord",method= RequestMethod.GET)
    public void exportExcelModifyRecord(HttpServletRequest request, HttpServletResponse response,
                                        @ApiParam(value = "缴款id",name = "id",required = true) @RequestParam Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "零售缴款修改记录";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            retailPayMentService.exportExcelModifyRecord(output,id,loginUser);
        }catch(Exception e){
            logger.error("导出日结信息修改记录错误:"+e.getMessage(),e);
        }

    }

    @ApiOperation(value = "缴款单据-获取打印数据", notes = "获取数据 | 开发者:马东 | 开发完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getPayMentPrint", method = RequestMethod.GET, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Result<RetailPaymentExportVO> getPayMentPrint(HttpSession session,
           @ApiParam(value = "缴款id",name = "id",required = true) @RequestParam Long id) throws Exception {
        Result<RetailPaymentExportVO> result = new Result<>();
        try {
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, retailPayMentService.getPayMentPrint(loginUser, id));
        } catch (Exception e) {
            logger.error("获取日结打印数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }



//    @ApiOperation(value = "查看缴款草稿缓存", notes = "查看缴款草稿缓存 | 开发者 马东 | 已完成")
//    @RequestMapping(value = "/daftCache", method = RequestMethod.GET)
//    public Result<List<DraftCacheVO>> daftCacheVO(@ApiIgnore UserVO userVO) {
//        Result<List<DraftCacheVO>> result = new Result<>();
//        try {
//            List<DraftCacheVO> draftCacheVO = retailPayMentService.getDraftCacheVO(userVO);
//
//            result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);
//        } catch (BusinessException e) {
//            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
//        } catch (Exception e) {
//            logger.error("查看缴款草稿缓存错误:" + e.getMessage(), e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//        }
//        return  result;
//    }
//
//    @ApiOperation(value = "保存缴款草稿缓存", notes = "保存缴款草稿缓存 | 开发者 马东 | 已完成")
//    @RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
//    public Result<DraftCacheVO> saveDraftCache(@ApiIgnore UserVO userVO,@RequestBody DraftCacheVO<RetailPaymentVO> draftCacheVO) {
//
//        Result<DraftCacheVO> result = new Result<>();
//        try {
//
//            result.setBizCodeSuccessInfo(SysCode.SUCCESS,retailPayMentService.saveDraftCache(userVO,draftCacheVO));
//
//        } catch (ApprovalFlowBizException e){
//            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
//            return result;
//        }catch (BusinessException e) {
//            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
//        } catch (Exception e) {
//            logger.error("保存缴款草稿缓存错误:" + e.getMessage(), e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//        }
//
//        return result;
//    }
//
//    @ApiOperation(value = "删除缴款草稿缓存", notes = "删除缴款草稿缓存 | 开发者 马东 | 已完成")
//    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}", method = RequestMethod.DELETE)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
//                    , required = true, paramType="path")
//    })
//    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String redisKeyValue) {
//
//        Result result = new Result<>();
//        try {
//
//            retailPayMentService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.RETAIL_PAYMENT.getCodePrefix(),redisKeyValue);
//
//            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
//        } catch (BusinessException e) {
//            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
//        } catch (Exception e) {
//            logger.error("删除缴款草稿缓存错误:" + e.getMessage(), e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//        }
//        return  result;
//    }

}
