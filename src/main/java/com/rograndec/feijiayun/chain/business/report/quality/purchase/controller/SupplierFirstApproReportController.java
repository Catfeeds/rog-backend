package com.rograndec.feijiayun.chain.business.report.quality.purchase.controller;

import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierDetailVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.SupplierFirstApproReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.RequestParamSupplierReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierFirstApproReportVO;
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
@Api(value = "report_quality_purchase_supplierFist",description = "报表-质量管理-采购-首营企业评审")
@RestController
@RequestMapping("report/quality/purchase/supplierFirst")
@Validated
public class SupplierFirstApproReportController {

    @Autowired
    private SupplierFirstApproReportService supplierFirstApproReportService;

    @ApiOperation(value = "报表-质量管理-采购-首营企业评审分页查询", notes = "报表-质量管理-采购-首营企业评审分页查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getSupplierFirstApproList",method= RequestMethod.POST)
    @ApiImplicitParam(name = "paramForListVO", value = "检索条件", required = true, dataType = "RequestParamSupplierReportVO")
    public Result<Page<List<SupplierDetailVO>>> getSupplierFirstApproList(HttpSession session, @RequestBody RequestParamSupplierReportVO paramForListVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<SupplierDetailVO>>> result = new Result<>();
        Page<List<SupplierDetailVO>> page = new Page<>(paramForListVO.getPageNo(),paramForListVO.getPageSize());
        supplierFirstApproReportService.getSupplierFirstApproList(page,paramForListVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "报表-质量管理-采购-首营企业评审详情查询", notes = "报表-质量管理-采购-首营企业评审详情查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getSupplierFirstApproDetail/{supplierId}",method= RequestMethod.POST)
    @ApiImplicitParam(name = "supplierId", value = "检索条件", required = true, paramType = "path")
    public Result<SupplierFirstApproReportVO> getSupplierFirstApproDetail(HttpSession session, @PathVariable("supplierId") Long supplierId){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<SupplierFirstApproReportVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,supplierFirstApproReportService.getSupplierFirstApproDetail(userVO,supplierId));
        return result;
    }


    @ApiOperation(value = "导出供货单位质量档案列表Excel", notes = "导出供货单位质量档案列表Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportSupplierFirstApproList",method= RequestMethod.GET)
    public void exportSupplierFirstApproList(HttpServletResponse response, HttpSession session, @ApiParam(value = "供货单位编码、名称、检索码", required = false) @RequestParam(required = false) String param,
                            @ApiParam(value = "企业类型排序0/倒序；1/正序;默认1", required = false) @RequestParam(required = false) Integer natureOrder,
                            @ApiParam(value = "组织机构编码0/倒序；1/正序;默认1", required = false) @RequestParam(required = false) Integer supplierCodeOrder) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "首营企业审批";
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
        supplierFirstApproReportService.exportSupplierFirstApproList(output,userVO,paramForListVO);

        output.close();
        output.flush();
    }


    @ApiOperation(value = "导出首营企业审批查看Excel", notes = "导出首营企业审批查看Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportSupplierFirstDetail",method= RequestMethod.GET)
    public void exportSupplierFirstDetail(HttpServletResponse response, HttpSession session, @ApiParam(value = "供货商id", required = false) @RequestParam(required = false) Long supplierId
                                       ) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "首营企业审批";
        String suffix = ".xlsx";
        String fileName = file + suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();


        supplierFirstApproReportService.exportSupplierFirstDetail(output,userVO,supplierId);

        output.close();
        output.flush();
    }


}
