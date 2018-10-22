package com.rograndec.feijiayun.chain.business.report.quality.org.controller;

import com.rograndec.feijiayun.chain.business.report.quality.org.service.DepartmentReportService;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.Tree;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author zhaiwei
 *
 */
@Api(value = "report_quality_org_department",description = "报表-质量管理-组织机构与质量管理职责-部门")
@RestController
@RequestMapping("report/quality/org/department")
@Validated
public class DepartmentReportController {

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private DepartmentReportService departmentReportService;

    @ApiOperation(value="获取部门组织机构信息", notes = "根据当前登录用户对应的企业ID获取配置列表信息 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/departmentOrganizationTree", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Tree>> getDepartmentOrganization(HttpServletRequest request){
        Result<List<Tree>> result = new Result<List<Tree>>();
        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("user");
        if (user == null) {
            user = new UserVO();
        }
        List<Department> department = departmentReportService.getDepartMentOrganization(user);
        List<Tree> tree = commonComponent.structureTree(department, user);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,tree);
        return result;
    }


    @ApiOperation(value="导出 | 开发者 翟伟 | 已完成", notes = "部门组织机构信息导出  | 开发者 翟伟 | 已完成"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    @ResponseBody
    public void exportExcel(HttpServletResponse response,HttpServletRequest request
    ) throws IOException {

        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("user");

        String file = "部门导出";
        String suffix = ".xlsx";
        String fileName = file+suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));

        //输出Excel文件
        OutputStream output = response.getOutputStream();


        departmentReportService.exportExcel(output,user);

        output.close();
        output.flush();
    }
	
}
