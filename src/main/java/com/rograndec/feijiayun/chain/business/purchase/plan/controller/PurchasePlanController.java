package com.rograndec.feijiayun.chain.business.purchase.plan.controller;

import com.rograndec.feijiayun.chain.business.purchase.plan.service.PurchasePlanModifyRecordService;
import com.rograndec.feijiayun.chain.business.purchase.plan.service.PurchasePlanService;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
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
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @author dongyang.du 2017年09月13日11:37:12
 */
@Api(value = "purchase_plan", description = "采购管理-采购计划", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/purchase/plan")
public class PurchasePlanController {

    private static final Log logger = LogFactory.getLog(PurchasePlanController.class);

    @Autowired
    private PurchasePlanService planService;

    @Autowired
    private PurchasePlanModifyRecordService recordService;


    @ApiOperation(value = "查询商品", notes = "查询商品 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.GET)
    public Result<List<PurchasePlanGoodsVO>> getGoodsList(HttpServletRequest request,@ApiParam(value = "搜索关键字",required = true) @RequestParam("param") String param){

        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");

        Result<List<PurchasePlanGoodsVO>> result = new Result<>();

        result.setBizCodeSuccessInfo(SysCode.SUCCESS ,planService.getGoodsList(param,userVO));

        return  result;
    }



    @ApiOperation(value = "查询商品带分页", notes = "查询商品带分页 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/getGoodsListPage", method = RequestMethod.GET)
    public Result<Page<List<PurchasePlanGoodsVO>>> getGoodsListPage(HttpServletRequest request,
                                                                    @ApiParam(value = "搜索关键字",required = false) @RequestParam(value = "param",required = false) String param,
                                                                    @RequestParam(required = true) @ApiParam(name = "pageNo", value = "当前页码", required = true) Integer pageNo,
                                                                    @RequestParam(required = true) @ApiParam(name = "pageSize", value = "显示条数", required = true) Integer pageSize){

        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");

        Result<Page<List<PurchasePlanGoodsVO>>> result = new Result<>();

        Page<List<PurchasePlanGoodsVO>> page = new Page<List<PurchasePlanGoodsVO>>(pageNo, pageSize);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS ,planService.getGoodsListPage(page,param,userVO));

        return  result;
    }


    @ApiOperation(value = "获取修改记录", notes = "获取修改记录 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/modifyRecord/get", method = RequestMethod.GET)
    public Result<Page<List<PurchasePlanModifyRecordVO>>> getPurchasePlan(HttpServletRequest request,
                                                                          @RequestParam(required = true) @ApiParam(name = "pageNo", value = "当前页码", required = true) Integer pageNo,
                                                                          @RequestParam(required = true) @ApiParam(name = "pageSize", value = "显示条数", required = true) Integer pageSize,
                                                                          @RequestParam(required = true) @ApiParam(name = "planId", value = "计划ID", required = true) Long planId) {

        Result<Page<List<PurchasePlanModifyRecordVO>>> result = new Result<>();
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page<List<PurchasePlanModifyRecordVO>> page = new Page<List<PurchasePlanModifyRecordVO>>(pageNo, pageSize);
            page = recordService.getModifyRecordPage(page, planId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

        return result;

    }

    @ApiOperation(value = "获取采购计划", notes = "获取采购计划 | 开发者:杜东阳 | 已联调" + " 状态说明：\n" + " 41-待订购," + "51-已订购 ," + "34-已取消 ," + "21-待审核,"
            + "23-审核拒绝")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result<Page<PurchasePlanReturnVO>> getPurchasePlan(HttpServletRequest request,
                                                              @RequestParam(required = true) @ApiParam(name = "pageNo", value = "当前页码", required = true) Integer pageNo,
                                                              @RequestParam(required = true) @ApiParam(name = "pageSize", value = "显示条数", required = true) Integer pageSize,
                                                              @RequestParam(required = false) @ApiParam(value = "开始时间", required = false) String startDate,
                                                              @RequestParam(required = false) @ApiParam(value = "结束时间", required = false) String endDate,
                                                              @RequestParam(required = false) @ApiParam(name = "status", value = "状态", required = false) Integer status,
                                                              @RequestParam(required = false) @ApiParam(name = "code", value = "计划单号", required = false) String code,
                                                              @RequestParam(required = false) @ApiParam(name = "pannerName", value = "计划人员", required = false) String pannerName,
                                                              @RequestParam(required = false) @ApiParam(value = "按某一列排序(日期：planDate,单号:code )", required = false) String order,
                                                              @RequestParam(required = false) @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) String sort) {

        Result<Page<PurchasePlanReturnVO>> result = new Result<>();

        Date start = DateUtils.StringToDate(startDate,DateUtils.FMT_DATE);
        Date end = DateUtils.addDay(DateUtils.StringToDate(endDate,DateUtils.FMT_DATE), 1);

        if (pageNo <= 0 || pageSize <= 0) {
            result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page<PurchasePlanReturnVO> page = new Page<PurchasePlanReturnVO>(pageNo, pageSize);
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        page = planService.getPurchasePlanPage(page, status, code, pannerName, start, end, order, sort, loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

        return result;
    }

    @ApiOperation(value = "获取采购计划-查看明细", notes = "获取采购计划-查看明细| 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/getPurchasePlanDetail", method = RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "planId", value = "采购计划id", required = true, paramType = "query")})
    public Result<PurchasePlanVO> getPlanDetial(@RequestParam("planId") Long planId,HttpServletRequest request) {

        Result<PurchasePlanVO> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, planService.selectDetailByPlanId(planId,userVO) );
        }  catch (BusinessException e) {
            logger.error("获取采购计划-查看明细错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("获取采购计划-查看明细错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }

        return result;

    }

    @ApiOperation(value = "导出采购计划明细", notes = "导出采购计划明细 |开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "planId", value = "计划ID", required = false, paramType = "query")})
    public void exportPlanDetail(HttpServletResponse response, HttpServletRequest request,@RequestParam("planId") Long planId) throws Exception {
        String name = "PlanDetail";
        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        // 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");


        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");
        planService.exportDetail(output, planId,userVO);
        output.close();
        output.flush();
    }

    @ApiOperation(value = "取消采购计划", notes = "取消采购计划 |开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "planId", value = "计划ID", required = false, paramType = "query")})
    public Result<String> cancelPlan(@RequestParam("planId") Long planId, HttpServletRequest request) {

        Result<String> result = new Result<String>();
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            planService.cancelPlan(planId, loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "采购计划-取消成功");

        return result;

    }

    @ApiOperation(value = "更新采购计划", notes = "更新采购计划 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<String> updatePlan(HttpServletRequest request,
                                     @ApiParam(value = "采购计划-计划明细", required = true) @RequestBody PurchasePlanVO purchasePlanVO) throws Exception {
        Result<String> result = new Result<String>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        planService.update(purchasePlanVO, loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "采购计划-更新成功");

        return result;
    }

    @ApiOperation(value = "新增采购计划", notes = "新增采购计划|  开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<String> savePlan(HttpServletRequest request,
                                   @ApiParam(value = "采购计划-计划明细", required = true) @RequestBody PurchasePlanVO purchasePlanVO) throws Exception {
        Result<String> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        result = planService.save(purchasePlanVO, loginUser);
        return result;
    }

    @ApiOperation(value = "删除采购计划明细", notes = "删除采购计划明细 |开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<String> deletePlan(@ApiParam(value = "明细ID", required = true) @RequestParam("planDetailId") Long planDetailId,
                                     HttpServletRequest request) {

        Result<String> result = new Result<String>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        planService.delete(planDetailId, loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "采购计划-删除成功");

        return result;
    }

    @ApiOperation(value = "生成采购订单", notes = "生成采购订单|  开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public Result<List<String>> createOrder(@ApiParam(value = "计划ID", required = true) @RequestParam("planId") Long planId,
                                            HttpServletRequest request) throws Exception {
        Result<List<String>> result = new Result<>();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        List<String> list = planService.createOrder(planId, loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);

        return result;
    }

    @ApiOperation(value = "历史单价", notes = "历史单价 |开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/getHistoricaUnitPrice", method = RequestMethod.GET)
    public Result<List<HistoricalUnitPriceVO>> getHistoricaUnitPrice(
            @ApiParam(value = "商品ID", required = true) @RequestParam("goodsId") Long goodsId,
            @RequestParam("limit") @ApiParam(value = "条数限制", required = true, defaultValue = "10") Integer limit) {

        Result<List<HistoricalUnitPriceVO>> result = new Result<>();
        List<HistoricalUnitPriceVO> historyUnitPrice = planService.selectHistoryUnitPrice(goodsId, limit);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, historyUnitPrice);

        return result;
    }

    @ApiOperation(value = "获取供货单位基本信息", notes = "查询条件获取供货单位基本信息 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getSupplier", method = RequestMethod.GET)
    public Result<List<SupplierVO>> getBasicSupplier(HttpSession session) {
        Result<List<SupplierVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, planService.getBasicSupplier(userVO));
        return result;
    }


    @ApiOperation(value = "根据商品的经营范围获取供货单位", notes = "根据商品的经营范围获取供货单位 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getBasicSupplierByBusinessScope", method = RequestMethod.GET)
    public Result<List<SupplierVO>> getBasicSupplierByBusinessScope(HttpSession session,
                                                                    @ApiParam(value = "商品ID", required = true) @RequestParam("goodsId") Long goodsId) {
        Result<List<SupplierVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, planService.getBasicSupplierByBusinessScope(userVO,goodsId));
        return result;
    }



    @ApiOperation(value = "智能采购", notes = "智能采购 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/intelligentPurchase", method = RequestMethod.POST)
    public Result<List<PurchasePlanGoodsVO>> intelligentPurchase(@RequestBody IntellectPurchaseReqVO intellectPurchaseReqVO,
                                                      HttpServletRequest request,@ApiIgnore UserVO userVO) {

        Result<List<PurchasePlanGoodsVO>> result = new Result<>();

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,planService.intelligentPurchase(intellectPurchaseReqVO,userVO));
        return result;

    }

    @ApiOperation(value = "调用缺配单", notes = "调用缺配单 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getByDistrlack", method = RequestMethod.GET)
    public Result<List<PurchasePlanGoodsVO>> getLack(@ApiParam(value = "缺配单IDS", required = true) @RequestParam("lackIds") String lackIds,
                                          HttpServletRequest request) {

        Result<List<PurchasePlanGoodsVO>> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,planService.getByDistrLack(lackIds,loginUser));

        return  result;
    }


    @ApiOperation(value = "查看采购计划草稿缓存", notes = "查看采购计划草稿缓存 | 开发者 翟伟 | 已完成")
    @RequestMapping(value = "/daftCache", method = RequestMethod.GET)
    public Result<List<DraftCacheVO>> daftCacheVO(@ApiIgnore UserVO userVO) {

        Result<List<DraftCacheVO>> result = new Result<>();

        List<DraftCacheVO> draftCacheVO = planService.getDraftCacheVO(userVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);

        return  result;
    }

    @ApiOperation(value = "保存采购计划草稿缓存", notes = "保存采购计划草稿缓存 | 开发者 翟伟 | 已完成")
    @RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
    public Result<DraftCacheVO<PurchasePlanDraftCacheVO>> saveDraftCache(@ApiIgnore UserVO userVO,@RequestBody DraftCacheVO<PurchasePlanDraftCacheVO> draftCacheVO) {

        Result<DraftCacheVO<PurchasePlanDraftCacheVO>> result = new Result<>();

        DraftCacheVO<PurchasePlanDraftCacheVO> purchasePlanDraftCacheVODraftCacheVO = planService.saveDraftCache(userVO, draftCacheVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchasePlanDraftCacheVODraftCacheVO);

        return  result;
    }

    @ApiOperation(value = "删除采购计划草稿缓存", notes = "删除采购计划草稿缓存 | 开发者 翟伟 | 已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "redis map key"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/remove/daftCache/{id}", method = RequestMethod.DELETE)
    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String id) {

        Result result = new Result<>();

        planService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.PURCHASE_PLAN.getCodePrefix(),id);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return  result;
    }

}
