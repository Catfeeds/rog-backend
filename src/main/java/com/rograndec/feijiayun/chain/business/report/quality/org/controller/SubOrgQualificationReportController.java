package com.rograndec.feijiayun.chain.business.report.quality.org.controller;

import com.rograndec.feijiayun.chain.business.report.quality.org.service.SubOrgQualificationReportService;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.QualificationConfigVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.RequestParamOrgReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@Api(value = "report_quality_sub_org_orgQualification",description = "报表-质量管理-门店组织机构与质量管理职责-企业资质/资质预警")
@RestController
@RequestMapping("report/quality/sub/org/orgQualification")
@Validated
public class SubOrgQualificationReportController {

    @Autowired
    private SubOrgQualificationReportService subOrgQualificationReportService;

    @ApiOperation(value = "门店组织机构与质量管理职责-企业资质分页查询", notes = "门店组织机构与质量管理职责-企业资质分页查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getSubOrgQualificationReport",method= RequestMethod.POST)
    @ApiImplicitParam(name = "paramForListVO", value = "检索条件", required = true, dataType = "RequestParamOrgReportVO")
    public Result<Page<List<QualificationConfigVO>>> getSubOrgQualificationReport(HttpSession session, @RequestBody RequestParamOrgReportVO paramForListVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<QualificationConfigVO>>> result = new Result<>();
        Page<List<QualificationConfigVO>> page = new Page<>(paramForListVO.getPageNo(),paramForListVO.getPageSize());
        subOrgQualificationReportService.getEnterpriseQualificationReportList(page,paramForListVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }



    @ApiOperation(value = "导出企业资质Excel", notes = "导出门店组织机构与质量管理职责-企业资质Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportExcel",method= RequestMethod.GET)
    public void exportExcel(HttpServletResponse response, HttpSession session) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "企业资质";
        String suffix = ".xlsx";
        String fileName = file + suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        subOrgQualificationReportService.export(output,userVO);
        output.close();
        output.flush();
    }

    @ApiOperation(value = "门店组织机构与质量管理职责-资质预警分页查询", notes = "门店组织机构与质量管理职责-资质预警分页查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getSubOrgQualificationWarnReport",method= RequestMethod.POST)
    public Result<List<QualificationConfigVO>> getSubOrgQualificationWarnReport(HttpSession session){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<List<QualificationConfigVO>> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,subOrgQualificationReportService.getEnterpriseQualificationWarnReportList(userVO));
        return result;
    }



    @ApiOperation(value = "导出资质预警Excel", notes = "导出门店组织机构与质量管理职责-资质预警Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportWarnExcel",method= RequestMethod.GET)
    public void exportWarnExcel(HttpServletResponse response, HttpSession session) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "企业资质预警";
        String suffix = ".xlsx";
        String fileName = file + suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        subOrgQualificationReportService.exportWarn(output,userVO);
        output.close();
        output.flush();
    }




}
