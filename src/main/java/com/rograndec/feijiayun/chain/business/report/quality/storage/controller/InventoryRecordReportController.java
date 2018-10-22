package com.rograndec.feijiayun.chain.business.report.quality.storage.controller;

import com.rograndec.feijiayun.chain.business.report.quality.storage.service.InventoryRecordReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.InventoryRecordReportTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestReportInventoryRecordVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.controller.GoodsLockController;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lizhongyi
 */
@Api(value = "report_quality_storage_inventoryRecord", description = "报表-质量管理-存储与养护-盘点记录")
@RestController
@RequestMapping("report/quality/storage/inventoryRecord")
@Validated
public class InventoryRecordReportController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsLockController.class);

    @Autowired
    private InventoryRecordReportService inventoryRecordReportService;

    @ApiOperation(value = "报表-质量管理-存储与养护-盘点记录", notes = "获取数据 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getInventoryRecordByParam", method = RequestMethod.POST)
    public Result<Page<InventoryRecordReportTotalVO>> getInventoryRecordByParam(HttpSession session,
                                                                               @Valid @RequestBody RequestReportInventoryRecordVO requestVO) throws Exception {
        Result<Page<InventoryRecordReportTotalVO>> result = new Result<>();
        Integer pageNo = requestVO.getPageNo();
        Integer pageSize = requestVO.getPageSize();
        try {
            Page page = new Page(pageNo, pageSize);
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            page = inventoryRecordReportService.getInventoryRecordReportList(requestVO, page, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("报表-质量管理-存储与养护-盘点记录:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "报表-质量管理-存储与养护-盘点记录导出数据", notes = "导出数据 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
                            RequestReportInventoryRecordVO requestVO) throws Exception {
        String name = "盘点记录导出";
        try {
            //导出不需要分页
            requestVO.setPageNo(null);
            requestVO.setPageSize(null);
            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            Integer pageNo = requestVO.getPageNo();
            Integer pageSize = requestVO.getPageSize();

            Page<InventoryRecordReportTotalVO> inventoryRecordReportTotalVOPage = inventoryRecordReportService.getInventoryRecordReportList(requestVO, new Page(pageNo, pageSize), userVO);
            inventoryRecordReportService.excelExport(output, inventoryRecordReportTotalVOPage.getResult(), userVO);
        } catch (Exception e) {
            logger.error("导出盘点记录导出数据报表错误:" + e.getMessage(), e);
            e.printStackTrace();
        }
    }


}
