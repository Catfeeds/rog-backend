package com.rograndec.feijiayun.chain.business.report.quality.purchase.controller;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchaseCheckReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseCheckReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.RequestParamForCheckReportListVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.TotalPurchaseCheckReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_purchase_purchaseCheck",description = "报表-质量管理-收货与验收-采购验收")
@RestController
@RequestMapping("report/quality/purchase/purchaseCheck")
@Validated
public class PurchaseCheckReportController {

    private static final Log logger = LogFactory.getLog(PurchaseCheckReportController.class);

    @Autowired
    PurchaseCheckReportService purchaseCheckReportService;

    @ApiOperation(value = "分页获取报表-质量管理-收货与验收-采购验收信息", notes = "分页获取报表-质量管理-收货与验收-采购验收信息 | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value="/getPurchaseCheckReportPage", method=RequestMethod.POST)
    public Result<Page<TotalPurchaseCheckReportVO<PurchaseCheckReportPageVO>>> getPurchaseCheckReportPage(HttpSession session,
                                                                                                          @ApiParam(name = "pageNo", value = "当前页码", required = true) @RequestBody RequestParamForCheckReportListVO param
                                                                                    ){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<TotalPurchaseCheckReportVO<PurchaseCheckReportPageVO>>> result = new Result<>();
        Page<TotalPurchaseCheckReportVO<PurchaseCheckReportPageVO>> page = new Page<>(param.getPageNo(),param.getPageSize());
        page = purchaseCheckReportService.getPurchaseCheckReportPage(page,param,userVO.getEnterpriseId());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }


    @ApiOperation(value = "导出Excel", notes = "按照模版将采购验收列表信息导出至Excel | 开发者:zeshi.sun | 已联调")
    @RequestMapping(value="/exportExcel", method=RequestMethod.POST,produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,RequestParamForCheckReportListVO param){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "采购验收";
            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            purchaseCheckReportService.exportExcel(output,loginUser,param);
        }catch(Exception e){
            logger.error("导出采购验收信息错误:"+e.getMessage(),e);
        }

    }

	
}
