package com.rograndec.feijiayun.chain.business.report.quality.org.controller;

import com.rograndec.feijiayun.chain.business.report.quality.org.service.OrgReportService;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.EnterpriseReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.RequestParamOrgReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.beanutils.BeanMap;
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
@Api(value = "report_quality_org_org",description = "报表-质量管理-组织机构与质量管理职责-组织机构")
@RestController
@RequestMapping("report/quality/org/org")
@Validated
public class OrgReportController {

    @Autowired
    private OrgReportService orgReportService;

    @ApiOperation(value = "组织机构与质量管理职责--组织机构分页查询", notes = "组织机构与质量管理职责--组织机构分页查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getEnterpriseOrgReport",method= RequestMethod.POST)
    @ApiImplicitParam(name = "paramForListVO", value = "检索条件", required = true, dataType = "RequestParamOrgReportVO")
    public Result<Page<List<EnterpriseReportVO>>> getEnterpriseOrgReport(HttpSession session,  @RequestBody RequestParamOrgReportVO paramForListVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<EnterpriseReportVO>>> result = new Result<>();
        Page<List<EnterpriseReportVO>> page = new Page<>(paramForListVO.getPageNo(),paramForListVO.getPageSize());
        orgReportService.getEnterpriseReportList(page,paramForListVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "组织机构与质量管理职责--门店分组查询", notes = "组织机构与质量管理职责--门店分组查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getGroupByEnterpriseId/{type}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "type", value = "类型（0-自营店；1-加盟店;2-全部）", required = true, paramType = "path")
    public Result<List<BeanMap>> getGroupByEnterpriseId(HttpSession session, @PathVariable("type")Integer type){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<List<BeanMap>> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,orgReportService.getEnterpGroup(userVO.getEnterpriseId(),type));
        return result;
    }

    @ApiOperation(value = "导出Excel", notes = "导出组织机构Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportExcel",method= RequestMethod.GET)
    public void exportExcel(HttpServletResponse response, HttpSession session, @ApiParam(value = "门店编码、门店名称", required = false) @RequestParam(required = false) String param,
                            @ApiParam(value = "分组id", required = false) @RequestParam(required = false) Long groupId,
                            @ApiParam(value = "药店类型排序0/倒序；1/正序;默认1", required = false) @RequestParam(required = false) Integer chainTypeOrder,
                            @ApiParam(value = "分组排序0/倒序；1/正序;默认1", required = false) @RequestParam(required = false) Integer groupCodeOrder,
                            @ApiParam(value = "组织机构编码0/倒序；1/正序;默认1", required = false) @RequestParam(required = false) Integer enterpriseCodeOrder) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "组织机构";
        String suffix = ".xlsx";
        String fileName = file + suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();

        RequestParamOrgReportVO paramForListVO = new RequestParamOrgReportVO();
        paramForListVO.setChainTypeOrder(chainTypeOrder);
        paramForListVO.setEnterpriseCodeOrder(enterpriseCodeOrder);
        paramForListVO.setGroupId(groupId);
        paramForListVO.setGroupCodeOrder(groupCodeOrder);
        paramForListVO.setParam(param);
        orgReportService.export(output,userVO,paramForListVO);

        output.close();
        output.flush();
    }






	
}
