package com.rograndec.feijiayun.chain.business.report.quality.org.controller;

import com.rograndec.feijiayun.chain.business.report.quality.org.service.DepartmentReportService;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import com.rograndec.feijiayun.chain.business.system.set.entity.Tree;
import com.rograndec.feijiayun.chain.business.system.set.service.OrganizationService;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
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
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author zhaiwei
 *
 */
@Api(value = "report_quality_org_position",description = "报表-质量管理-组织机构与质量管理职责-岗位")
@RestController
@RequestMapping("report/quality/org/position")
@Validated
public class PositionReportController {

    @Autowired
    private DepartmentReportService departmentReportService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private CommonComponent commonComponent;

    @ApiOperation(value="获取岗位组织机构信息 | 开发者 翟伟 | 已完成", notes = "根据当前登录用户对应的企业ID获取岗位组织机构信息 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/positionOrganizationTree", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Tree>> getPositionOrganization(HttpServletRequest request){
        Result<List<Tree>> result = new Result<List<Tree>>();
        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("user");
        if (user == null) {
            user = new UserVO();
        }
        List<Position> position = organizationService.getPositionOrganization(user);
        List<Department> department = organizationService.getDepartMentOrganization(user);
        List<Tree> departMentTree = commonComponent.structureTree(department, user);
        List<Tree> tree = organizationService.structurePositionTree(departMentTree,position,user);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, tree);
        return result;
    }

    @ApiOperation(value="导出 | 开发者 翟伟 | 已完成", notes = "岗位组织机构信息导出  | 开发者 翟伟 | 已完成"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    @ResponseBody
    public void exportExcel(HttpServletResponse response, HttpServletRequest request
    ) throws Exception {

        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("user");

        String file = "岗位导出";
        String suffix = ".xlsx";
        String fileName = file+suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));

        //输出Excel文件
        OutputStream output = response.getOutputStream();
        Long enterpriseId = user.getEnterpriseId();
        //加盟店只检索系统默认和本企业的信息
        if(user.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
            enterpriseId = user.getParentId();
        }

        organizationService.exportExcel(output,user,enterpriseId);

        output.close();
        output.flush();
    }
}
