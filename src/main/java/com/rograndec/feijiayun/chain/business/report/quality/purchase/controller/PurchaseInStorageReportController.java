package com.rograndec.feijiayun.chain.business.report.quality.purchase.controller;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchaseInStorageReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseInStorageReportListVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseInStorageReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.RequestParamForCheckReportListVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.TotalPurchaseInStorageReportVO;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@Api(value = "report_quality_purchase_purchaseInStorage",description = "报表-质量管理-收货与验收-采购入库")
@RestController
@RequestMapping("report/quality/purchase/purchaseInStorage")
@Validated
public class PurchaseInStorageReportController {

    private static final Log logger = LogFactory.getLog(PurchaseInStorageReportController.class);

    @Autowired
    PurchaseInStorageReportService purchaseInStorageReportService;

    @ApiOperation(value = "分页获取报表-质量管理-收货与验收-采购入库信息", notes = "分页获取报表-质量管理-收货与验收-采购入库信息 | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value="/getPurchaseInStorageReportPage", method= RequestMethod.POST)
    public Result<Page<TotalPurchaseInStorageReportVO<PurchaseInStorageReportPageVO>>> getPurchaseInStorageReportPage(HttpSession session,
                                                                                                                      @ApiParam(name = "pageNo", value = "当前页码", required = true) @RequestBody PurchaseInStorageReportListVO param
    ){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<TotalPurchaseInStorageReportVO<PurchaseInStorageReportPageVO>>> result = new Result<>();
        Page<TotalPurchaseInStorageReportVO<PurchaseInStorageReportPageVO>> page = new Page<>(param.getPageNo(),param.getPageSize());
        page = purchaseInStorageReportService.getPurchaseInStorageReportPage(page,param,userVO.getEnterpriseId());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "导出Excel", notes = "按照模版将采购入库列表信息导出至Excel | 开发者:zeshi.sun | 已联调")
    @RequestMapping(value="/exportExcel", method=RequestMethod.POST,produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,PurchaseInStorageReportListVO param){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "采购入库";
            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            purchaseInStorageReportService.exportExcel(output,loginUser,param);
        }catch(Exception e){
            logger.error("导出采购入库信息错误:"+e.getMessage(),e);
        }

    }
	
}
