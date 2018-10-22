package com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.controller;

import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrEnterpriseVO;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.entity.RetailDailySettleDetail;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.service.RetailDailySettleService;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.vo.*;
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

@Api(value="RetailDailySettleController",description = "应收缴款-零售日结")
@RestController
@RequestMapping("/finance/shoulepayment/dailysettel")
public class RetailDailySettleController {

    private static final Log logger = LogFactory.getLog(RetailDailySettleController.class);

    @Autowired
    private RetailDailySettleService retailDailySettleService;

    @ApiOperation(value = "分页查询日结单据列表", notes = "分页查询日结单据列表 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getDailySettleByParam", method = RequestMethod.POST)
    public Result<Page<RetailDailySettleReponseVO>> getDailySettleByParam(HttpSession session,
           @ApiParam(value = "分页参数",required = true) @RequestBody DailtSettleRequestPageParamVO paramVO){
        Result<Page<RetailDailySettleReponseVO>> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, retailDailySettleService.getDailySettleByParam(paramVO,loginUser));
        } catch (BusinessException e){
            logger.error("分页查询日结单据列表失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("分页查询日结单据列表失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询日结单据明细", notes = "查询日结单据明细 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getDailySettleDetail", method = RequestMethod.GET)
    public Result<RetailDailySettleVO> getDailySettleDetail(HttpSession session,
           @ApiParam(value = "日结id",required = true) @RequestParam Long settleId){
        Result<RetailDailySettleVO> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, retailDailySettleService.getDailySettleDetail(settleId,loginUser));
        } catch (BusinessException e){
            logger.error("查询日结单据明细失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("查询日结单据明细失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

//    @ApiOperation(value = "修改日结单据", notes = "修改日结单据 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    @RequestMapping(value = "/updateDailySettle", method = RequestMethod.POST)
//    public Result<String> updateDailySettle(HttpSession session,
//           @ApiParam(value = "修改的日结单据",required = true) @RequestBody RetailDailySettleVO dailySettleVO){
//        Result<String> result = new Result<>();
//        try {
//            // 当前登录用户数据
//            UserVO loginUser = (UserVO) session.getAttribute("user");
//            String message = retailDailySettleService.updateDailySettle(dailySettleVO,loginUser);
//            result.setBizCodeSuccessInfo(SysCode.SUCCESS, message);
//        } catch (BusinessException e){
//            logger.error("修改日结单据错误:", e);
//            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
//            return result;
//        } catch (Exception e) {
//            logger.error("修改日结单据错误:", e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//            return result;
//        }
//        return result;
//    }

    @ApiOperation(value = "冲销日结单据", notes = "冲销日结单据 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/chargrAgainstDailySettle", method = RequestMethod.POST)
    public Result<String> chargrAgainstDailySettle(HttpSession session,
           @ApiParam(value = "待冲销的日结单据ID集合",required = true) @RequestBody List<Long> settleId){
        Result<String> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String message = retailDailySettleService.chargrAgainstDailySettle(settleId,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, message);
        } catch (BusinessException e){
            logger.error("冲销日结单据错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("冲销日结单据错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "根据门店获取待日结单据", notes = "根据门店获取待日结单据 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getWillDailySettleByStore", method = RequestMethod.GET)
    public Result<List<RetailDailySettleDetailVO>> getWillDailySettleByStore(HttpSession session,
           @ApiParam(value = "门店ID,分店登录不用传") @RequestParam(required = false) Long storeId){
        Result<List<RetailDailySettleDetailVO>> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, retailDailySettleService.getWillDailySettleByStore(storeId,loginUser));
        } catch (BusinessException e){
            logger.error("根据门店获取待日结单据错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("根据门店获取待日结单据错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "获取总部下的所有直营店", notes = "获取总部下的所有直营店 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getDirectSaleStore", method = RequestMethod.GET)
    public Result<List<DistrEnterpriseVO>> getDirectSaleStore(HttpSession session){
        Result<List<DistrEnterpriseVO>> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, retailDailySettleService.getDirectSaleStore(loginUser));
        } catch (BusinessException e){
            logger.error("获取总部下的所有直营店错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("获取总部下的所有直营店错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }

    @ApiOperation(value = "新增/修改日结单据", notes = "新增/修改日结单据 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/saveDailySettle", method = RequestMethod.POST)
    public Result<String> saveDailySettle(HttpSession session,
           @ApiParam(value = "待新增/修改日结单据",required = true) @RequestBody RetailDailySettleVO dailySettleVO){
        Result<String> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String message = "";
            if(dailySettleVO.getId() == null) {
                message = retailDailySettleService.saveDailySettle(dailySettleVO, loginUser);
            }else {
                message = retailDailySettleService.updateDailySettle(dailySettleVO,loginUser);
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, message);
        } catch (BusinessException e){
            logger.error("新增/修改日结单据错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("新增/修改日结单据错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看日结修改记录", notes = "查看日结修改记录 | 开发者 马东 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getDailySettleModifyRecord", method = RequestMethod.GET)
    public Result<Page<List<RetailDailySettleModifyRecordVO>>> getDailySettleModifyRecord(HttpSession session,
           @ApiParam(value = "页码",required = true) @RequestParam Integer pageNo,
           @ApiParam(value = "页容量",required = true) @RequestParam Integer pageSize,
           @ApiParam(value = "日结ID",required = true) @RequestParam Long id){
        Result<Page<List<RetailDailySettleModifyRecordVO>>> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, retailDailySettleService.getDailySettleModifyRecord(pageNo,pageSize,id,loginUser));
        } catch (BusinessException e){
            logger.error("查看日结修改记录错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("查看日结修改记录错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "导出Excel", notes = "按照模版将日结单导出至Excel | 开发者:马东 | 已完成")
    @RequestMapping(value="/exportExcel",method= RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
           @ApiParam(value = "日结id",name = "id",required = true) @RequestParam Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "零售日结";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            retailDailySettleService.exportExcel(output,id,loginUser);
        }catch(Exception e){
            logger.error("导出日结信息错误:"+e.getMessage(),e);
        }

    }

    @ApiOperation(value = "导出修改记录Excel", notes = "按照模版将日结单修改记录导出至Excel | 开发者:马东 | 已完成")
    @RequestMapping(value="/exportExcelModifyRecord",method= RequestMethod.GET)
    public void exportExcelModifyRecord(HttpServletRequest request, HttpServletResponse response,
           @ApiParam(value = "日结id",name = "id",required = true) @RequestParam Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "零售日结修改记录";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            retailDailySettleService.exportExcelModifyRecord(output,id,loginUser);
        }catch(Exception e){
            logger.error("导出日结信息修改记录错误:"+e.getMessage(),e);
        }

    }

    @ApiOperation(value = "日结单据-获取打印数据", notes = "获取数据 | 开发者:马东 | 开发完成")
    @ResponseBody
    @RequestMapping(value = "/getDailySettlePrint", method = RequestMethod.GET)
    public Result<RetailDailySettleExportVO> getDailySettlePrint(HttpSession session,
           @ApiParam(value = "日结id",name = "id",required = true) @RequestParam Long id) throws Exception {
        Result<RetailDailySettleExportVO> result = new Result<>();
        try {
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, retailDailySettleService.getDailySettlePrint(loginUser, id));
        } catch (Exception e) {
            logger.error("获取日结打印数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }



//    @ApiOperation(value = "查看日结草稿缓存", notes = "查看日结草稿缓存 | 开发者 马东 | 已完成")
//    @RequestMapping(value = "/daftCache", method = RequestMethod.GET)
//    public Result<List<DraftCacheVO>> daftCacheVO(@ApiIgnore UserVO userVO) {
//        Result<List<DraftCacheVO>> result = new Result<>();
//        try {
//            List<DraftCacheVO> draftCacheVO = retailDailySettleService.getDraftCacheVO(userVO);
//
//            result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);
//        } catch (BusinessException e) {
//            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
//        } catch (Exception e) {
//            logger.error("查看日结草稿缓存错误:" + e.getMessage(), e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//        }
//        return  result;
//    }
//
//    @ApiOperation(value = "保存日结草稿缓存", notes = "保存日结草稿缓存 | 开发者 马东 | 已完成")
//    @RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
//    public Result<DraftCacheVO> saveDraftCache(@ApiIgnore UserVO userVO,@RequestBody DraftCacheVO<RetailDailySettleVO> draftCacheVO) {
//
//        Result<DraftCacheVO> result = new Result<>();
//        try {
//
//            result.setBizCodeSuccessInfo(SysCode.SUCCESS,retailDailySettleService.saveDraftCache(userVO,draftCacheVO));
//
//        } catch (ApprovalFlowBizException e){
//            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
//            return result;
//        }catch (BusinessException e) {
//            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
//        } catch (Exception e) {
//            logger.error("保存日结草稿缓存错误:" + e.getMessage(), e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//        }
//
//        return result;
//    }
//
//    @ApiOperation(value = "删除日结草稿缓存", notes = "删除日结草稿缓存 | 开发者 马东 | 已完成")
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
//            retailDailySettleService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.RETAIL_DAILY_SETTLE.getCodePrefix(),redisKeyValue);
//
//            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
//        } catch (BusinessException e) {
//            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
//        } catch (Exception e) {
//            logger.error("删除日结草稿缓存错误:" + e.getMessage(), e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//        }
//        return  result;
//    }



}
