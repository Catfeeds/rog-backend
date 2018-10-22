package com.rograndec.feijiayun.chain.business.distr.parent.controller;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLack;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnIn;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrSend;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrSendService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.*;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.system.approval.exception.ApprovalFlowBizException;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrSendStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/07 <br/>
 * 描述：配送管理-总部-配送单
 */
@Api(value = "distr_send", description = "配送管理-总部-配货出库-配货单接口服务")
@RestController
@RequestMapping("distr/send")
@Validated
public class DistrSendController {

    private static final Log logger = LogFactory.getLog(DistrSendController.class);

    @Autowired
    private DistrSendService distrSendService;

    @ApiOperation(value = "查询要货单位列表", notes = "查询当前企业的要货单位列表 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getReqUnitList", method = RequestMethod.GET)
    public Result<List<DistrEnterpriseVO>> getReqUnitInfo(HttpServletRequest request,@ApiParam(value = "查询参数")@RequestParam(value = "param",required = false) String param) {
        Result<List<DistrEnterpriseVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrSendService.getDistrEnterpriseList(loginUser.getEnterpriseId(),param));
        } catch (Exception e) {
            logger.error("配送管理-总部-配送单:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询商品库存列表", notes = "查询商品库存列表 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getGoodsStockList", method = RequestMethod.GET)
    public Result<List<DistrSendGoodsVO>> getGoodsStockList(HttpServletRequest request,
                                                            @ApiParam(value = "要货单位id", required = true) @RequestParam Long requestUnitId,
                                                            @ApiParam(value = "商品编码/条形码/检索码/商品名称/通用名称/批准文号", required = false) @RequestParam(required = false) String params) {
        Result<List<DistrSendGoodsVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrSendService.getGoodsStockList(loginUser, requestUnitId, params));
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("配送管理-总部-配送单-商品选择列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "批量查询商品库存列表", notes = "批量查询商品库存列表 | 开发者 马东 | 已联调")
    @RequestMapping(value = "/getBatchGoodsStockList", method = RequestMethod.GET)
    public Result<Page<List<DistrSendGoodsVO>>> getBatchGoodsStockList(HttpServletRequest request,
           @ApiParam(value = "要货单位id", required = true) @RequestParam Long requestUnitId,
           @ApiParam(value = "商品编码/条形码/检索码/商品名称/通用名称/批准文号", required = false) @RequestParam(required = false) String params,
           @ApiParam(value = "页码",name = "pageNo",required = true) @RequestParam Integer pageNo,
           @ApiParam(value = "页容量",name = "pageSize",required = true) @RequestParam Integer pageSize) {
        Result<Page<List<DistrSendGoodsVO>>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            Page<List<DistrSendGoodsVO>> page = new Page<>(pageNo,pageSize);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrSendService.getBatchGoodsStockList(loginUser, requestUnitId, params, page));
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("配送管理-总部-配送单-商品批量选择列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询要货计划列表", notes = "查询要货计划列表 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getReqPlanList", method = RequestMethod.GET)
    public Result<List<DistrSendReqPlanVO>> getReqPlanList(HttpServletRequest request,
                                                           @ApiParam(value = "起始时间", required = false) @RequestParam(required = false) Date startTime,
                                                           @ApiParam(value = "终止时间", required = false) @RequestParam(required = false) Date endTime,
                                                           @ApiParam(value = "按日期排序0-升序 1-降序", required = false) @RequestParam(required = false) Integer sortDate,
                                                           @ApiParam(value = "按单号排序0-升序 1-降序", required = false) @RequestParam(required = false) Integer sortCode) {
        Result<List<DistrSendReqPlanVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrSendService.getReqPlanList(loginUser.getEnterpriseId(), startTime, endTime, sortDate, sortCode));
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("配送管理-总部-配送单-查询要货计划列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "根据多个要货计划ID查询要货商品列表", notes = "根据多个要货计划ID查询要货商品列表 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getReqPlanDetailList", method = RequestMethod.GET)
    public Result<List<DistrReqPlanGoodsVO>> getReqPlanDetailList(HttpServletRequest request,
                                                                  @ApiParam(value = "要货计划ID，多个使用逗号分隔", required = true) @RequestParam String planIds,
                                                                  @ApiParam(value = "配货规则：0-按要货顺序；1-按库存平均；2-按要货数量占比", required = false) @RequestParam(required = false) Integer distrRule,
                                                                  @ApiParam(value = "按要货日期排序：0-升序 1-降序", required = false) @RequestParam(required = false) Integer sortDate) {
        Result<List<DistrReqPlanGoodsVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrSendService.getReqPlanGoodsList(loginUser.getEnterpriseId(), planIds, distrRule, sortDate));
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("配送管理-总部-配送单-查询要货计划明细:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "要货计划生成配货单保存", notes = "要货计划生成配货单保存 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/saveTransferReqPlan", method = RequestMethod.POST)
    public Result saveTransferReqPlan(HttpServletRequest request,
                                      @RequestBody RequestTransferBaseOrderVO requestVO) {
        Result result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            distrSendService.saveTransferReqPlan(userVO, requestVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }  catch (Exception e) {
            logger.error("要货计划生成配货单保存错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询缺配单列表", notes = "查询缺配单列表 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getDistrLackOrderList", method = RequestMethod.GET)
    public Result<Page<DistrLack>> getDistrLackOrderList(HttpServletRequest request,
                                                         @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                         @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                         @ApiParam(value = "起始时间", required = false) @RequestParam(required = false) Date startTime,
                                                         @ApiParam(value = "终止时间", required = false) @RequestParam(required = false) Date endTime) {
        Result<Page<DistrLack>> result = new Result<>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = distrSendService.getDistrLackList(page, loginUser, startTime, endTime);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("查询缺配单列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "查询缺配单明细列表", notes = "根据缺配单ID查询缺配单明细列表 | 开发者 蓝兴建 | 已联调")
    @ApiImplicitParam(name = "id", value = "缺配单ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/getDistrLackOrderDtlList/{id}", method = RequestMethod.GET)
    public Result<DistrLack> getDistrLackOrderDtlList(HttpServletRequest request,
                                                                  @PathVariable("id") @NotNull Long id) {
        Result<DistrLack> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrSendService.getDistrLackDetailList(id,loginUser));
        } catch (Exception e) {
            logger.error("查询缺配单明细列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "查询采购入库单列表", notes = "查询采购入库单列表 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getPurchaseInStorageList", method = RequestMethod.GET)
    public Result<Page<PurchaseInStorage>> getPurchaseInStorageList(HttpServletRequest request,
                                                                    @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                    @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                    @ApiParam(value = "起始时间", required = false) @RequestParam(required = false) Date startTime,
                                                                    @ApiParam(value = "终止时间", required = false) @RequestParam(required = false) Date endTime) {
        Result<Page<PurchaseInStorage>> result = new Result<>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = distrSendService.getPurchaseInStorageList(page, loginUser.getEnterpriseId(), startTime, endTime);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("查询采购入库单列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "查询采购入库单明细列表", notes = "根据采购入库单ID查询采购入库单明细列表 | 开发者 蓝兴建 | 已联调")
    @ApiImplicitParam(name = "id", value = "采购入库单ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/getPurchaseInStorageDtlList/{id}", method = RequestMethod.GET)
    public Result<List<DistrPurchaseInstorageDetailVO>> getPurchaseInStorageDtlList(HttpServletRequest request,
                                                                                    @PathVariable("id") @NotNull Long id) {
        Result<List<DistrPurchaseInstorageDetailVO>> result = new Result<>();
        try {
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrSendService.getPurchaseInStorageDtlList(id));
        } catch (Exception e) {
            logger.error("查询采购入库单明细列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "通过门店ID查询门店可用库存", notes = "通过门店ID查询门店可用库存 | 开发者 zeshi.sun | 已完成")
    @RequestMapping(value = "/getBranchStock", method = RequestMethod.GET)
    public Result<BranchStockVO> getBranchStock(HttpServletRequest request,
                                                @ApiParam(value = "要货企业ID", required = true) @RequestParam Long requestUnitId,
                                                @ApiParam(value = "货品ID", required = true) @RequestParam Long goodsid) {
        Result<BranchStockVO> result = new Result<>();
        try {
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrSendService.getBranchStock(requestUnitId, goodsid));
        } catch (Exception e) {
            logger.error("通过门店ID查询门店可用库存错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "查询配后退回入库单列表", notes = "查询配后退回入库单列表 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getDistrReturnInList", method = RequestMethod.GET)
    public Result<Page<DistrReturnIn>> getDistrReturnInList(HttpServletRequest request,
                                     @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                     @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                     @ApiParam(value = "起始时间", required = false) @RequestParam(required = false) Date startTime,
                                     @ApiParam(value = "终止时间", required = false) @RequestParam(required = false) Date endTime) {
        Result<Page<DistrReturnIn>> result = new Result<>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = distrSendService.getDistrReturnInList(page, loginUser.getEnterpriseId(), startTime, endTime);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("查询配后退回入库单列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "查询配后退回入库单明细列表", notes = "根据配后退回入库单ID查询配后退回入库单明细列表 | 开发者 蓝兴建 | 已联调")
    @ApiImplicitParam(name = "id", value = "配后退回入库单ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/getDistrReturnInDtlList/{id}", method = RequestMethod.GET)
    public Result<List<DistrReturnInDetail>> getDistrReturnInDtlList(HttpServletRequest request,
                                                                     @PathVariable("id") @NotNull Long id) {
        Result<List<DistrReturnInDetail>> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrSendService.getDistrReturnInDetailList(userVO,id));
        } catch (Exception e) {
            logger.error("查询配后退回入库单明细列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "保存配货单（生成配货单）", notes = "保存配货单 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/saveDistrSendOrder", method = RequestMethod.POST)
    public Result saveDistrSendOrder(@ApiIgnore UserVO userVO,
                                     @RequestBody RequestDistrSendVO requestDistrSendVO) throws Exception {
        Result result = new Result<>();
//        try {
            distrSendService.saveAddDistrSend(userVO, requestDistrSendVO, true);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
//        } catch (BusinessException e) {
//            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
//            return result;
//        } catch (Exception e) {
//            logger.error("生成配货单保存错误:" + e.getMessage(), e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//            return result;
//        }
        return result;
    }

    @ApiOperation(value = "配货单商品列表-修改配货数量-刷新价格", notes = "配货单商品列表-修改配货数量-刷新价格 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/calculation", method = RequestMethod.POST)
    public Result<DistrSend> calculation(HttpServletRequest request,
                                         @RequestBody RequestDistrSendVO requestDistrSendVO) {
        Result<DistrSend> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrSendService.calculation(userVO, requestDistrSendVO));
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("配货单商品列表保存错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "修改配货单", notes = "修改配货单 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/updateDistrSendOrder", method = RequestMethod.POST)
    public Result updateDistrSendOrder(HttpServletRequest request,
                                       @RequestBody RequestDistrSendVO requestDistrSendVO) {
        Result result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");

            //参数验证
            if (requestDistrSendVO.getId() == null) {
                result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, "修改配货单错误，请检查参数！");
                return result;
            }

            //配货单日期
            if (requestDistrSendVO.getDistrDate() == null) {
                result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, "请选择日期！");
                return result;
            }

            //配货人员
            if (requestDistrSendVO.getDistrManId() == 0) {
                result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, "请选择配货人员！");
                return result;
            }

            distrSendService.updateDistrSend(userVO, requestDistrSendVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("修改配货单保存错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询配货单列表", notes = "根据状态查询配货单列表 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getDistrSendOrderList", method = RequestMethod.GET)
    public Result<Page<DistrListTotalVO>> getDistrSendOrderList(HttpServletRequest request,
                                                                @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                @ApiParam(value = "状态（1-全部；21-待审核；22-审核通过；23-审核驳回；31-待出库；33-已完成；34-已取消）", required = true) @RequestParam int status,
                                                                @ApiParam(value = "起始时间", required = false) @RequestParam(required = false) Date startTime,
                                                                @ApiParam(value = "终止时间", required = false) @RequestParam(required = false) Date endTime,
                                                                @ApiParam(value = "要货单位编码", required = false) @RequestParam(required = false) String requestUnitCode,
                                                                @ApiParam(value = "要货单位名称", required = false) @RequestParam(required = false) String requestUnitName,
                                                                @ApiParam(value = "配货单号", required = false) @RequestParam(required = false) String distrCode,
                                                                @ApiParam(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）", required = false) @RequestParam(required = false) Integer distrType,
                                                                @ApiParam(value = "配货人员", required = false) @RequestParam(required = false) String distrManName,
                                                                @ApiParam(value = "按日期排序0-升序 1-降序", required = false) @RequestParam(required = false) Integer sortDate,
                                                                @ApiParam(value = "按单号排序0-升序 1-降序", required = false) @RequestParam(required = false) Integer sortCode) {
        Result<Page<DistrListTotalVO>> result = new Result<>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            Map<String, Object> map = new HashMap<>();
            List<Integer> statusList = new ArrayList<>();
            statusList.add(status);
            if(DistrSendStatus.FINISHED == status){// 已完成，待开票，部分开票
                statusList.add(DistrSendStatus.PART_BILL);
                statusList.add(DistrSendStatus.WAIT_BILL);
            }

            if(DistrSendStatus.WAIT_OUT == status){// 待捡货，待出库
                statusList.add(DistrSendStatus.WAIT_PICK);
            }


            map.put("status", status == 1?null:statusList);
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("requestUnitCode", requestUnitCode);
            map.put("requestUnitName", requestUnitName);
            map.put("distrCode", distrCode);
            map.put("distrType", distrType);
            map.put("distrManName", distrManName);
            map.put("sortDate", sortDate);
            map.put("sortCode", sortCode);
            map.put("enterpriseId", loginUser.getEnterpriseId());

            page = distrSendService.getDistrSendPage(page, map);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("查询配货单列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "查询配货单明细列表", notes = "根据状态查询配货单列表 | 开发者 蓝兴建 | 已联调")
    @ApiImplicitParam(name = "id", value = "配送单id", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/getDistrSendDetailList/{id}", method = RequestMethod.GET)
    public Result<DistrSend> getDistrSendDetailList(HttpServletRequest request,
                                                      @PathVariable("id") @NotNull Long id) {
        Result<DistrSend> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            DistrSend distrSend = distrSendService.getDistrSendDetail(id);
//            //@ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
//            if(distrSend.getDistrType().equals(0)){
//                distrSend.setDistrTypeName("总部配送");
//            }else if(distrSend.getDistrType().equals(1)){
//                distrSend.setDistrTypeName("分店间调剂");
//            }else if(distrSend.getDistrType().equals(2)){
//                distrSend.setDistrTypeName("直调配送");
//            }
            //增加整单折扣金额字段
            BigDecimal wholeDiscountValue = distrSend.getAmountTotal().multiply(distrSend.getWholeDiscount());
            distrSend.setWholeDiscountValue(wholeDiscountValue);
            distrSend.setEnterpriseName(loginUser.getEnterpriseName());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrSend);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("查询配货单列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    // 导出
    @ApiOperation(value = "导出Excel", notes = "按照模版将配货单信息导出至Excel | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/exportExcel/{id}", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = "单据id", required = true) @PathVariable("id") Long id) {
        String name = "配货单";
        try {
            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            distrSendService.excelExport(output, id, userVO);
        } catch (Exception e) {
            logger.error("导出Excel:" + e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "取消配货单", notes = "取消配货单 | 开发者 蓝兴建 | 已联调")
    @ApiImplicitParam(name = "id", value = "配送单id", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/cancelOrder/{id}", method = RequestMethod.GET)
    public Result cancelOrder(HttpServletRequest request,
                                                      @PathVariable("id") @NotNull Long id) {
        Result result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            int cancelResult = distrSendService.cancelOrder(id, loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, cancelResult == 1 ? "取消配货单成功" : "取消失败");
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("取消配货单:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "查看配货单草稿缓存", notes = "查看配货单草稿缓存 | 开发者 马东 | 已完成")
    @RequestMapping(value = "/daftCache", method = RequestMethod.GET)
    public Result<List<DraftCacheVO>> daftCacheVO(@ApiIgnore UserVO userVO) {
        Result<List<DraftCacheVO>> result = new Result<>();
        try {
        List<DraftCacheVO> draftCacheVO = distrSendService.getDraftCacheVO(userVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("查看配货单草稿缓存:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return  result;
    }

    @ApiOperation(value = "保存配货单草稿缓存", notes = "保存配货单草稿缓存 | 开发者 马东 | 已完成")
    @RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
    public Result<DraftCacheVO> saveDraftCache(@ApiIgnore UserVO userVO,@RequestBody DraftCacheVO<DistrSendCacheVO> draftCacheVO) {

        Result<DraftCacheVO> result = new Result<>();
        try {

            result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrSendService.saveDraftCache(userVO,draftCacheVO));

        } catch (ApprovalFlowBizException e){
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
            return result;
        }catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("保存配货单草稿缓存:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }

        return result;
    }

    @ApiOperation(value = "删除配货单草稿缓存", notes = "删除配货单草稿缓存 | 开发者 马东 | 已完成")
    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}", method = RequestMethod.DELETE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
                    , required = true, paramType="path")
    })
    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String redisKeyValue) {

        Result result = new Result<>();
        try {

        distrSendService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.DISTR_ORDER.getCodePrefix(),redisKeyValue);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("删除配货单草稿缓存:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return  result;
    }


}
