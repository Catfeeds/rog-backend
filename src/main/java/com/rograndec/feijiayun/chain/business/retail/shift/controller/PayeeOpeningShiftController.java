package com.rograndec.feijiayun.chain.business.retail.shift.controller;

import com.rograndec.feijiayun.chain.business.retail.payment.vo.DynamicColumnVO;
import com.rograndec.feijiayun.chain.business.retail.shift.servcie.PayeeOpeningShiftService;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.ResultPageVo;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosPayeeVO;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosStandVO;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosTeamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zeshi.sun on 2017/9/19.
 */
@Api(value = "PayeeOpeningShift", description = "总部 零售管理-款员交班服务接口")
@RestController
@RequestMapping("retail/shift")
public class PayeeOpeningShiftController {

    private static final Log logger = LogFactory.getLog(PayeeOpeningShiftController.class);

    @Autowired
    PayeeOpeningShiftService payeeOpeningShiftService;

    @ApiOperation(value = "获取零售缴款各模块动态显示列数据", notes = "获取零售缴款各模块动态显示列数据 | 开发者:zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getDynamicColumn", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<DynamicColumnVO>> getDynamicColumn(HttpServletRequest request,
                                                          @ApiParam(value = "来源 0-门店交班分页列表，1-门店交班查看分页列表，2-总部交班分页列表，3-总部交班查看分页列表", required = true) @RequestParam Integer source
    ) {
        Result<List<DynamicColumnVO>> result = new Result<List<DynamicColumnVO>>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            List<DynamicColumnVO> list = payeeOpeningShiftService.selectDynamicColumnBySource(source, loginUser);

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("分页获取零售交班数据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "总部-分页查询款员交班", notes = "总部-分页查询款员交班 | 开发者:zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPayeeOpeningShift", method = RequestMethod.GET)
    @ResponseBody
    public Result<ResultPageVo> getPayeeOpeningShift(HttpServletRequest request,
                                             @ApiParam(value = "分页参数,页码", required = true) @RequestParam Integer pageNo,
                                             @ApiParam(value = "分页参数,页容量", required = true) @RequestParam Integer pageSize,
                                             @ApiParam(value = "待排序字段") @RequestParam(required = false) String orderName,
                                             @ApiParam(value = "排序方式ASC/DESC") @RequestParam(required = false) String orderType,
                                             @ApiParam(value = "搜索起始日期") @RequestParam(required = false) Date startTime,
                                             @ApiParam(value = "搜索截止日期") @RequestParam(required = false) Date endTime,
                                             @ApiParam(value = "门店类型 （1-自营店；2-加盟店）") @RequestParam(required = false) String storeType,
                                             @ApiParam(value = "门店编码") @RequestParam(required = false) String storeCode,
                                             @ApiParam(value = "门店名称") @RequestParam(required = false) String StoreName,
                                             @ApiParam(value = "款台编码") @RequestParam(required = false) String standCode,
                                             @ApiParam(value = "班组ID") @RequestParam(required = false) Long teamId,
                                             @ApiParam(value = "收款人员") @RequestParam(required = false) String payeeName
    ) {

        Result<ResultPageVo> result = new Result<>();
        try {

            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            ResultPageVo page = new ResultPageVo(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            payeeOpeningShiftService.getPayeeOpeningShift(pageNo, pageSize, loginUser, page, startTime, endTime, orderName, orderType, standCode, teamId, payeeName, storeType, storeCode, StoreName);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value = "总部-分页点击查看按钮", notes = "总部-分页点击查看按钮 | 开发者:zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPayeeOpeningShiftSelect", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page> getPayeeOpeningShiftSelect(HttpServletRequest request,
                                                   @ApiParam(value = "分页参数,页码", required = true) @RequestParam Integer pageNo,
                                                   @ApiParam(value = "分页参数,页容量", required = true) @RequestParam Integer pageSize,
                                                   @ApiParam(value = "交班ID", required = true) @RequestParam Long shiftId,
                                                   @ApiParam(value = "待排序字段") @RequestParam(required = false) String orderName,
                                                   @ApiParam(value = "排序方式ASC/DESC") @RequestParam(required = false) String orderType
    ) {

        Result<Page> result = new Result<>();
        try {

            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            payeeOpeningShiftService.getPayeeOpeningShiftSelect(pageNo, pageSize, loginUser, page, orderName, orderType, shiftId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出总部款员交班信息", notes = "导出总部款员交班信息 | 开发者:zeshi.sun | 已联调 ")
    @RequestMapping(value = "/exportPurchaseReceive", method = RequestMethod.GET)
    public void exportPurchaseReceive(HttpServletRequest request, HttpServletResponse response,
                                      @ApiParam(value = "待排序字段") @RequestParam(required = false) String orderName,
                                      @ApiParam(value = "排序方式ASC/DESC") @RequestParam(required = false) String orderType,
                                      @ApiParam(value = "搜索起始日期") @RequestParam(required = false) Date startTime,
                                      @ApiParam(value = "搜索截止日期") @RequestParam(required = false) Date endTime,
                                      @ApiParam(value = "门店类型 （0-自营店；1-加盟店）") @RequestParam(required = false) String storeType,
                                      @ApiParam(value = "门店编码") @RequestParam(required = false) String storeCode,
                                      @ApiParam(value = "门店名称") @RequestParam(required = false) String StoreName,
                                      @ApiParam(value = "款台编码") @RequestParam(required = false) String standCode,
                                      @ApiParam(value = "班组ID") @RequestParam(required = false) Long teamId,
                                      @ApiParam(value = "收款人员") @RequestParam(required = false) String payeeName) {
        OutputStream output = null;
        try {

            Result<ResultPageVo> result = new Result<>();

            Integer pageNo = null;
            Integer pageSize = null;
            ResultPageVo page = new ResultPageVo(pageNo, pageSize);
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName() + "款员交班";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            List<DynamicColumnVO> list = payeeOpeningShiftService.selectDynamicColumnBySource(2,
                    loginUser);

            List<Map> map = payeeOpeningShiftService.getPayeeOpeningShift(pageNo, pageSize, loginUser, page, startTime, endTime, orderName, orderType, standCode, teamId, payeeName, storeType, storeCode, StoreName);

            payeeOpeningShiftService.exportExcel(output, list, loginUser, map, "款员交班");
        } catch (Exception e) {

        }
    }

    @ApiOperation(value = "导出总部款员交班信息-查看按钮", notes = "导出总部款员交班信息-查看按钮 | 开发者:zeshi.sun | 已联调 ")
    @RequestMapping(value = "/exportPurchaseReceiveSelect", method = RequestMethod.GET)
    public void exportPurchaseReceiveSelect(HttpServletRequest request, HttpServletResponse response,
                                            @ApiParam(value = "当前需要查看的款员交班ID", required = true) @RequestParam Long shiftId,
                                            @ApiParam(value = "待排序字段") @RequestParam(required = false) String orderName,
                                            @ApiParam(value = "排序方式ASC/DESC") @RequestParam(required = false) String orderType
    ) {
        OutputStream output = null;
        try {

            Result<Page> result = new Result<>();

            Integer pageNo = null;
            Integer pageSize = null;
            Page page = new Page(pageNo, pageSize);
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName() + "款员交班";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            List<DynamicColumnVO> list = payeeOpeningShiftService.selectDynamicColumnBySource(3,
                    loginUser);

            List<Map> map = payeeOpeningShiftService.getPayeeOpeningShiftSelect(pageNo, pageSize, loginUser, page, orderName, orderType, shiftId);

            payeeOpeningShiftService.exportExcel(output, list, loginUser, map, "款员交班明细");
        } catch (Exception e) {

        }
    }


    @ApiOperation(value = "获取款台", notes = "获取款台 | 开发者:zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSelectPosStand", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SelectPosStandVO>> getSelectPosStand(HttpServletRequest request) {
        Result<List<SelectPosStandVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, payeeOpeningShiftService.getSelectPosStand(loginUser));
        } catch (Exception e) {
            logger.error("获取款台错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "获取班组", notes = "获取班组 | 开发者:zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSelectPosTeam", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SelectPosTeamVO>> getSelectPosTeam(HttpServletRequest request) {
        Result<List<SelectPosTeamVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, payeeOpeningShiftService.getSelectPosTeam(loginUser));
        } catch (Exception e) {
            logger.error("获取班组错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "获取收款人员", notes = "获取收款人员 | 开发者:zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSelectPosPayee", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SelectPosPayeeVO>> getSelectPosPayee(HttpServletRequest request) {
        Result<List<SelectPosPayeeVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, payeeOpeningShiftService.getSelectPosPayee(loginUser));
        } catch (Exception e) {
            logger.error("获取收款人员错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


}
