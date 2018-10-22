package com.rograndec.feijiayun.chain.business.report.quality.purchase.controller;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.SupplierQualificationReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.RequestParamSupplierReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierQualificationReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "report_quality_purchase_supplierQualificationWarn",description = "报表-质量管理-采购-供货单位资质预警")
@RestController
@RequestMapping("report/quality/purchase/supplierQualificationWarn")
@Validated
public class SupplierQualificationWarnReportController {

    @Autowired
    private SupplierQualificationReportService supplierQualificationReportService;

    @ApiOperation(value = "报表-质量管理-采购-供货单位资质预警分页查询", notes = "报表-质量管理-采购-供货单位资质预警分页查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getEnterpriseOrgQualificationReport",method= RequestMethod.POST)
    @ApiImplicitParam(name = "paramForListVO", value = "检索条件", required = true, dataType = "RequestParamSupplierReportVO")
    public Result<Page<List<SupplierQualificationReportVO>>> getEnterpriseOrgQualificationReport(HttpSession session, @RequestBody RequestParamSupplierReportVO paramForListVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<SupplierQualificationReportVO>>> result = new Result<>();
        Page<List<SupplierQualificationReportVO>> page = new Page<>(paramForListVO.getPageNo(),paramForListVO.getPageSize());
        supplierQualificationReportService.getSupplierQualificationWarnReportList(page,paramForListVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }



    @ApiOperation(value = "导出Excel", notes = "导出供货单位资质预警Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportExcel",method= RequestMethod.GET)
    public void exportExcel(HttpServletResponse response, HttpSession session, @ApiParam(value = "供货单位编码、名称、检索码", required = false) @RequestParam(required = false) String param,
                            @ApiParam(value = "企业类型排序0/倒序；1/正序;默认1", required = false) @RequestParam(required = false) Integer natureOrder,
                            @ApiParam(value = "组织机构编码0/倒序；1/正序;默认1", required = false) @RequestParam(required = false) Integer supplierCodeOrder) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "供货单位资质预警";
        String suffix = ".xlsx";
        String fileName = file + suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();

        RequestParamSupplierReportVO paramForListVO = new RequestParamSupplierReportVO();
        paramForListVO.setSupplierCodeOrder(supplierCodeOrder);
        paramForListVO.setNatureOrder(natureOrder);
        paramForListVO.setParam(param);
        supplierQualificationReportService.exportWarn(output,userVO,paramForListVO);

        output.close();
        output.flush();
    }
	
}
