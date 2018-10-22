package com.rograndec.feijiayun.chain.business.retail.shift.controller;

import com.rograndec.feijiayun.chain.business.retail.payment.vo.DynamicColumnVO;
import com.rograndec.feijiayun.chain.business.retail.shift.servcie.PayeeOpeningShiftService;
import com.rograndec.feijiayun.chain.business.retail.shift.servcie.PayeeOpeningShiftStoreService;
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
@Api(value = "PayeeOpeningShift", description = "门店 零售管理-款员交班服务接口")
@RestController
@RequestMapping("retail/shift")
public class PayeeOpeningShiftStoreController {

    private static final Log logger = LogFactory.getLog(PayeeOpeningShiftStoreController.class);

    @Autowired
    PayeeOpeningShiftStoreService payeeOpeningShiftStoreService;

    @Autowired
    PayeeOpeningShiftService payeeOpeningShiftService;

    @ApiOperation(value = "门店-分页查询款员交班", notes = "门店-分页查询款员交班 | 开发者:zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPayeeOpeningShiftStore", method = RequestMethod.GET)
    @ResponseBody
    public Result<ResultPageVo> getWillReceiveStore(HttpServletRequest request,
                                            @ApiParam(value = "分页参数,页码", required = true) @RequestParam Integer pageNo,
                                            @ApiParam(value = "分页参数,页容量", required = true) @RequestParam Integer pageSize,
                                            @ApiParam(value = "待排序字段") @RequestParam(required = false) String orderName,
                                            @ApiParam(value = "排序方式ASC/DESC") @RequestParam(required = false) String orderType,
                                            @ApiParam(value = "搜索起始日期") @RequestParam(required = false) Date startTime,
                                            @ApiParam(value = "搜索截止日期") @RequestParam(required = false) Date endTime,
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
            payeeOpeningShiftStoreService.getPayeeOpeningShiftStore(pageNo, pageSize, loginUser, page, startTime, endTime, orderName, orderType, standCode, teamId, payeeName);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value = "门店-分页点击查看按钮", notes = "门店-分页点击查看按钮 | 开发者:zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPayeeOpeningShiftStoreSelect", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page> getPayeeOpeningShiftStoreSelect(HttpServletRequest request,
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
            payeeOpeningShiftStoreService.getPayeeOpeningShiftStoreSelect(pageNo, pageSize, loginUser, page, orderName, orderType, shiftId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出门店款员交班信息", notes = "导出门店款员交班信息 | 开发者:zeshi.sun | 已联调 ")
    @RequestMapping(value = "/exportPurchaseReceiveStore", method = RequestMethod.GET)
    public void exportPurchaseReceiveStore(HttpServletRequest request, HttpServletResponse response,
                                           @ApiParam(value = "待排序字段") @RequestParam(required = false) String orderName,
                                           @ApiParam(value = "排序方式ASC/DESC") @RequestParam(required = false) String orderType,
                                           @ApiParam(value = "搜索起始日期") @RequestParam(required = false) Date startTime,
                                           @ApiParam(value = "搜索截止日期") @RequestParam(required = false) Date endTime,
                                           @ApiParam(value = "款台编码") @RequestParam(required = false) String standCode,
                                           @ApiParam(value = "班组ID") @RequestParam(required = false) Long teamId,
                                           @ApiParam(value = "收款人员") @RequestParam(required = false) String payeeName
    ) {
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
            List<DynamicColumnVO> list = payeeOpeningShiftService.selectDynamicColumnBySource(0,
                    loginUser);

            List<Map> map = payeeOpeningShiftStoreService.getPayeeOpeningShiftStore(pageNo, pageSize, loginUser, page, startTime, endTime, orderName, orderType, standCode, teamId, payeeName);

            payeeOpeningShiftStoreService.exportExcel(output, list, loginUser, map, "款员交班");

        } catch (Exception e) {
            logger.error("导出门店款员交班信息错误:" + e.getMessage(), e);
        }
    }

    @ApiOperation(value = "导出门店款员交班信息-查看按钮", notes = "导出门店款员交班信息-查看按钮 | 开发者:zeshi.sun | 已联调 ")
    @RequestMapping(value = "/exportPurchaseReceiveStoreSelect", method = RequestMethod.GET)
    public void exportPurchaseReceiveStoreSelect(HttpServletRequest request, HttpServletResponse response,
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
            List<DynamicColumnVO> list = payeeOpeningShiftService.selectDynamicColumnBySource(1,
                    loginUser);

            List<Map> map = payeeOpeningShiftStoreService.getPayeeOpeningShiftStoreSelect(pageNo, pageSize, loginUser, page, orderName, orderType, shiftId);

            payeeOpeningShiftStoreService.exportExcel(output, list, loginUser, map, "款员交班明细");

        } catch (Exception e) {

        }
    }

}
