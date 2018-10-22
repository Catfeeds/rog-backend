package com.rograndec.feijiayun.chain.business.storage.chgoods.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.service.WarehouseService;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseVO;
import com.rograndec.feijiayun.chain.business.storage.chgoods.service.ChGoodsLoadService;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @author zeshi.sun
 */
@Api(value = "storage_chgoods_load", description = "储存管理-中药管理-中药饮片装斗")
@RestController
@RequestMapping("storage/chgoods/load")
@Validated
public class ChGoodsLoadController {

    private static final Log logger = LogFactory.getLog(ChGoodsLoadController.class);

    @Autowired
    ChGoodsLoadService chGoodsLoadService;

    @Autowired
    private WarehouseService warehouseService;

    @ApiOperation(value = "查询复核人员列表", notes = "查询复核人员列表 | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value = "/getAuditManList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result<List<SimpleUserVO>> getAuditManList(HttpServletRequest request) {
        Result<List<SimpleUserVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, chGoodsLoadService.getAuditMan(loginUser));
        } catch (Exception e) {
            logger.error("获取验收人员错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询商品库存列表（装斗和清斗功能都可使用）", notes = "查询商品库存列表（装斗和清斗功能都可使用） | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value = "/getGoodsStockList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result<List<GoodsStockListVO>> getGoodsStockList(HttpServletRequest request,
                                                            @ApiParam(value = "使用场景功能ID （0-装斗，1-清斗）",required = true) @RequestParam Long type,
                                                            @ApiParam(value = "五合一搜索条件 （商品编码/条形码/检索码/商品名称/通用名称/批准文号）") @RequestParam(required = false) String param

    ) {
        Result<List<GoodsStockListVO>> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            Long enterpriseId = loginUser.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, chGoodsLoadService.getGoodsStockList(enterpriseId,param,type));
        } catch (Exception e) {
            logger.error("查询商品库存列表错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "通过货位ID查询货位质量状态", notes = "通过货位ID查询货位质量状态 | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value = "/getGoodsShelfStatusDesc", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result<GoodsShelfStatusDescVO> getGoodsShelfStatusDesc(HttpServletRequest request,
                                                                  @ApiParam(value = "货位ID",required = true) @RequestParam Long shelfId

    ) {
        Result<GoodsShelfStatusDescVO> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, chGoodsLoadService.getGoodsShelfStatusDesc(loginUser.getEnterpriseId(),shelfId));
        } catch (Exception e) {
            logger.error("通过货位ID查询货位质量状态错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询货位列表", notes = "查询货位列表 | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value = "/getShelfList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result<List<TreePOJO<WarehouseVO>>> getShelfList(HttpServletRequest request) {
        Result<List<TreePOJO<WarehouseVO>>> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, warehouseService.getWarehouseShelfTreeByParam(loginUser.getEnterpriseId(), EnableStatus.ENABLE.getStatus(), 0));
        } catch (Exception e) {
            logger.error("查询货位列表-货位选择树:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }



    @ApiOperation(value = "保存中药装斗单", notes = "保存中药装斗单 | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value = "/saveChGoodsLoadOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result<String> saveChGoodsLoadOrder(HttpServletRequest request,
                                    @ApiParam(value = "保存中药装斗单", required = true) @RequestBody SaveChGoodsLoadOrderVO saveChGoodsLoadOrderVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            chGoodsLoadService.saveChGoodsLoadOrder(loginUser, saveChGoodsLoadOrderVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存中药装斗单成功");

        }  catch (BusinessException e) {
            logger.error("保存中药装斗单错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("保存中药装斗单错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "查询中药装斗列表", notes = "查询中药装斗单列表 | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value = "/getChGoodsLoadOrderList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result<Page<List<ChGoodsLoadOrderListVO>>> getChGoodsLoadOrderList(HttpServletRequest request,
                                                                              @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                              @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                              @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
                                                                              @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
                                                                              @ApiParam(value = "装斗单号", required = false) @RequestParam(required = false) String code,
                                                                              @ApiParam(value = "装斗人员", required = false) @RequestParam(required = false) String loadManName,
                                                                              @ApiParam(value = "复核人员", required = false) @RequestParam(required = false) String auditManName,
                                                                              @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                                                              @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<List<ChGoodsLoadOrderListVO>>> result = new Result<Page<List<ChGoodsLoadOrderListVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<ChGoodsLoadOrderListVO> purchaseCheckVoList = chGoodsLoadService
                    .getChGoodsLoadOrderList(pageNo, pageSize, loginUser.getEnterpriseId(), page, startTime, endTime, code, loadManName, auditManName, orderName, orderType);
            page.setResult(purchaseCheckVoList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("查询中药装斗列表错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询中药装斗明细列表", notes = "根据中药装斗ID查询中药装斗明细列表 | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value = "/getChGoodsLoadOrderDtlList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ChGoodsLoadOrderDtlListVO> getChGoodsLoadOrderDtlList(HttpServletRequest request,
                                                                              @ApiParam(value = "查看中药装斗列表ID", required = true) @RequestParam Long id) {

        Result<ChGoodsLoadOrderDtlListVO> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, chGoodsLoadService.getChGoodsLoadOrderDtlList(loginUser.getEnterpriseId(),id));
        } catch (Exception e) {
            logger.error("查询中药装斗明细列表错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出Excel", notes = "按照模版将中药装斗单导出至Excel | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value="/exportExcel/{id}",method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "当前需要查看的中药饮片装斗单据ID", required = true, dataType = "Long", paramType="path")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"中药饮片装斗";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            ChGoodsLoadOrderDtlListVO chGoodsLoadOrderDtlListVO = chGoodsLoadService.getChGoodsLoadOrderDtlList(loginUser.getEnterpriseId(),id);
            chGoodsLoadService.exportExcel(output,chGoodsLoadOrderDtlListVO,loginUser);
        }catch(Exception e){
            logger.error("导出中药饮片装斗错误:"+e.getMessage(),e);
        }
    }

}
