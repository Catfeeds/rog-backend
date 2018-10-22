package com.rograndec.feijiayun.chain.business.report.quality.purchase.controller;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchaseReturnReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout.PurchaseReturnOutTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout.RequestGetReturnOutParamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.valid.RequestParamValid;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_purchase_purchaseReturnOut",description = "报表-质量管理-收货与验收-购进退出出库")
@RestController
@RequestMapping("report/quality/purchase/purchaseReturnOut")
@Validated
public class PurchaseReturnOutReportController {

    @Autowired
    private PurchaseReturnReportService purchaseReturnReportService;

    @ApiOperation(value = "报表-质量管理-收货与验收-购进退出出库分页查询", notes = "报表-质量管理-收货与验收-购进退出出库分页查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getPurchaseReturnOutGoodsList",method= RequestMethod.POST)
    @ApiImplicitParam(name = "paramForListVO", value = "检索条件", required = true, dataType = "RequestGetReturnOutParamVO")
    public Result<Page<PurchaseReturnOutTotalVO>> getPurchaseReturnOutGoodsList(HttpSession session,@RequestParamValid @RequestBody RequestGetReturnOutParamVO paramForListVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<PurchaseReturnOutTotalVO>> result = new Result<>();
        Page<PurchaseReturnOutTotalVO> page = new Page<>(paramForListVO.getPageNo(),paramForListVO.getPageSize());
        purchaseReturnReportService.getPurchaseReturnOutGoodsList(page,paramForListVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "导出购进退出出库单", notes = "导出购进退出出库单 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/excelPurchaseReturnOutGoodsList", method = RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "paramForListVO", value = "检索条件", required = true, dataType = "RequestGetReturnOutParamVO")
    })
    public void excelPurchaseReturnOutGoodsList(RequestGetReturnOutParamVO paramForListVO, HttpSession session, HttpServletResponse response) throws Exception {

        UserVO userVO = (UserVO) session.getAttribute("user");
        String name = "购进退出出库单";
        name += ".xlsx";
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        //输出Excel文件
        OutputStream output = null;
        output = response.getOutputStream();
        purchaseReturnReportService.exportPurchaseReturnOutGoodsList(output,userVO,paramForListVO);
        output.close();
        output.flush();

    }
	
}
