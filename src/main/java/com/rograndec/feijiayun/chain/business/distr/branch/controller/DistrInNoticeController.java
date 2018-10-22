package com.rograndec.feijiayun.chain.business.distr.branch.controller;

import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInNoticeService;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @author dongyang.du
 */
@Api(value = "distr_in_notice", description = "配送管理-分店-配进入库-配进订单接口服务")
@RestController
@RequestMapping("distr/in/notice")
@Validated
public class DistrInNoticeController {
    private static final Log logger = LogFactory.getLog(DistrInNoticeController.class);

    @Autowired
    private DistrInNoticeService inNoticeService;

    @ApiOperation(value = "查询配进订单列表", notes = "查询配进订单列表 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getDistrInNoticeList", method = RequestMethod.POST)
    public Result<Page<List<DistrInNoticePageVO>>> getDistrInOrderList(HttpServletRequest request, @RequestBody DistrInNoticeRequestVO requestVO) throws Exception{


        Result<Page<List<DistrInNoticePageVO>>> result = new Result<>();
        if (requestVO.getPageNo() <= 0 || requestVO.getPageSize() <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Page page = inNoticeService.getDistrInNoticePageList(requestVO, loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

        return result;

    }

    @ApiOperation(value = "查询配进订单明细列表", notes = "查询配进订单明细列表 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/getDistrNoticeDetailList", method = RequestMethod.GET)
    public Result<Page<List<DistrInNoticeDetailVO>>> getDistrNoticeDetailList(HttpServletRequest request, @RequestParam("ids")@ApiParam(name = "ids", value = "配进订单主单id", required = true)String ids,
                                                                              @RequestParam("pageNo")@ApiParam(name = "pageNo", value = "页码", required = true)Integer pageNo,
                                                                              @RequestParam("pageSize")@ApiParam(name = "pageSize", value = "每页显示数量", required = true)Integer pageSize) {


        Result<Page<List<DistrInNoticeDetailVO>>> result = new Result<>();
        if (pageNo <= 0 || pageSize <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Page<List<DistrInNoticeDetailVO>> page = inNoticeService.getDistrNoticeDetailList(ids,pageNo, pageSize, loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

        return result;

    }

    @ApiOperation(value = "查询配进订单明细列表", notes = "根据配进订单ID查询配进订单明细列表 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getDistrInNoticeDtlList", method = RequestMethod.GET)
    public Result<DistrInNoticeVO> getDistrInOrderDtlList(HttpServletRequest request, @RequestParam(required = true) @ApiParam(name = "id", value = "解停通知单主键ID", required = true) Long id) {

        Result<DistrInNoticeVO> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, inNoticeService.getDistrInOrderDtlList(id, loginUser));
        return result;

    }


	@ApiOperation(value = "导出Excel", notes = "按照模版将配进订单导出至Excel | 开发者 杜东阳 | 已联调")
    @RequestMapping(value="/exportExcel/{orderId}",method= RequestMethod.GET)
    public void exportExcel(HttpServletResponse response, @PathVariable("orderId") Long orderId,HttpServletRequest request) throws IOException {

        String name = "配进订单";
        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        // 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");

        inNoticeService.exportDetail(output, orderId, loginUser);
        output.close();
        output.flush();
    }

    @ApiOperation(value = "保存配进订单单据", notes = "保存配进订单单据 | 开发者 zeshi.sun| 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveDistrInNotice", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveDistrInNotice(HttpServletRequest request,
                                            @ApiParam(value = "保存配进订单单据", required = true) @RequestBody SaveDistrInNoticeVO saveDistrInNoticeVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            inNoticeService.saveDistrInNotice(loginUser, saveDistrInNoticeVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存配进订单单据成功");

        } catch (BusinessException e) {
            logger.error("保存配进订单单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("保存配进订单单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "搜索商品", notes = "搜索商品 | 开发者 zeshi.sun | 已完成")
    @RequestMapping(value = "/getGoodsByParam", method = RequestMethod.GET)
    public Result<List<GoodsInNoticeVO>> getGoodsByParam(HttpServletRequest request,
                                                         @ApiParam(value = "供货单位ID", required = true) @RequestParam Long supplierId,
                                                         @ApiParam(value = "搜索条件", required = false) @RequestParam(required = false) String param) {
        Result<List<GoodsInNoticeVO>> result = new Result<List<GoodsInNoticeVO>>();
        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");
        List<GoodsInNoticeVO> goodsList = inNoticeService.getGoodsByParam(userVO,supplierId,param);
        result.setData(goodsList);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "取消配进订单", notes = "取消配进订单 | 开发者 zeshi.sun | 已完成")
    @RequestMapping(value = "/cancelDistrInNotice", method = RequestMethod.GET)
    public Result<String> cancelDistrInNotice(HttpServletRequest request, @RequestParam(required = true) @ApiParam(name = "id", value = "配进订单ID", required = true) Long id) {

        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            inNoticeService.cancelDistrInNotice(id, loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "取消配进订单成功");
        } catch (BusinessException e) {
            logger.error("取消配进订单错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("取消配进订单错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }

    @ApiOperation(value = "修改配进订单单据", notes = "修改配进订单单据 | 开发者 zeshi.sun| 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateDistrInNotice", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateDistrInNotice(HttpServletRequest request,
                                              @ApiParam(value = "修改配进订单单据", required = true) @RequestBody SaveDistrInNoticeVO saveDistrInNoticeVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            inNoticeService.updateDistrInNotice(loginUser, saveDistrInNoticeVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改配进订单单据成功");

        } catch (BusinessException e) {
            logger.error("修改配进订单单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("修改配进订单单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "分页调用要货计划列表", notes = "分页调用要货计划列表 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getDistrReqPlanPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<DistrReqPlanPageVO>>> getDistrReqPlanPage(HttpServletRequest request,
                                                                      @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                      @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                      @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
                                                                      @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
                                                                      @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                                                      @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<List<DistrReqPlanPageVO>>> result = new Result<Page<List<DistrReqPlanPageVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<DistrReqPlanPageVO> distrReqPlanPageVOS = inNoticeService
                    .getDistrReqPlanPage(pageNo, pageSize, loginUser.getEnterpriseId(), page, startTime, endTime, orderName, orderType);
            page.setResult(distrReqPlanPageVOS);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页调用要货计划列表错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "分页调用要货计划明细列表", notes = "分页调用要货计划明细列表 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getDistrReqPlanDetailPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<DistrReqPlanDetailPageVO>>> getDistrReqPlanDetailPage(HttpServletRequest request,
                                                                                  @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                                  @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                                  @ApiParam(value = "要货计划ID", required = true) @RequestParam Long planId,
                                                                                  @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
                                                                                  @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime

    ) {
        Result<Page<List<DistrReqPlanDetailPageVO>>> result = new Result<Page<List<DistrReqPlanDetailPageVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<DistrReqPlanDetailPageVO> distrReqPlanPageVOS = inNoticeService
                    .getDistrReqPlanDetailPage(pageNo, pageSize, loginUser.getEnterpriseId(), page, startTime, endTime, planId);
            page.setResult(distrReqPlanPageVOS);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页调用要货计划明细列表错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "要货计划插入到配进订单", notes = "要货计划插入到配进订单 | 开发者 zeshi.sun | 已完成")
    @RequestMapping(value = "/callDistrReqPlan", method = RequestMethod.GET)
    public Result<DistrInNoticeVO> callDistrReqPlan(HttpServletRequest request, @RequestParam(required = true) @ApiParam(name = "id", value = "要货计划ID", required = true) Long id) throws Exception{

        Result<DistrInNoticeVO> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, inNoticeService.callDistrReqPlan(id, loginUser));
        } catch (BusinessException e) {
            logger.error("要货计划插入到配进订单错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("要货计划插入到配进订单错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    //	@ApiOperation(value = "查询配货单位列表", notes = "查询配货单位列表 | 开发者 李中义 | 开发中")
//    @RequestMapping(value="/getDistrUnitList",method=RequestMethod.GET)
//    public void getDistrUnitList(){}
//
//	@ApiOperation(value = "查询商品列表", notes = "查询商品列表 | 开发者 李中义 | 开发中")
//    @RequestMapping(value="/getGoodsList", method=RequestMethod.GET)
//    public void getGoodsList(){}
//

//
//    @ApiOperation(value = "取消配进订单", notes = "取消配进订单 | 开发者 李中义 | 开发中")
//    @RequestMapping(value="/cancelReqPlanOrder", method=RequestMethod.GET)
//    public void cancelReqPlanOrder(){}
//
//	@ApiOperation(value = "修改配进订单", notes = "修改配进订单 | 开发者 李中义 | 开发中")
//    @RequestMapping(value="/updateDistrInOrder", method=RequestMethod.POST)
//    public void updateDistrInOrder(){}

}
