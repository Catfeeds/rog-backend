package com.rograndec.feijiayun.chain.business.report.quality.user.controller;

import com.rograndec.feijiayun.chain.business.report.quality.user.service.UserReportService;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserLicenseExcelVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserLicenseReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserReportExcelVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserReportPageVO;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@Api(value = "report_quality_user_userLicense",description = "报表-质量管理-人员与培训-员工资质")
@RestController
@RequestMapping("report/quality/user/userLicense")
@Validated
public class UserLicenseReportController {

    private static final Log logger = LogFactory.getLog(UserLicenseReportController.class);

    @Autowired
    private UserReportService userReportService;

    @ApiOperation(value = "按条件搜索员工page信息", notes = "按条件搜索员工page信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getUserLicensePage", method = RequestMethod.GET)
    public Result<Page<List<UserLicenseReportPageVO>>> getUserLicensePage(HttpServletRequest request,
                                                                               @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                               @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                               @ApiParam(value = "编码/检索码/名称", required = false) @RequestParam(required = false) String param,
                                                                               @ApiParam(value = "组织机构类型", required = false) @RequestParam(required = false) Integer chainType,
                                                                               @ApiParam(value = "组织机构编码", required = false) @RequestParam(required = false) String enterpriseCode,
                                                                               @ApiParam(value = "组织机构名称", required = false) @RequestParam(required = false) String enterpriseName,
                                                                               @ApiParam(value = "部门", required = false) @RequestParam(required = false) String deptIds,
                                                                               @ApiParam(value = "岗位", required = false) @RequestParam(required = false) String positionIds,
                                                                               @ApiParam(value = "角色", required = false) @RequestParam(required = false) String roleIds,
                                                                               @ApiParam(value = "按某一列排序(enterpriseCode或者code)", required = false) @RequestParam(required = false) String order,
                                                                               @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required = false) String sort) {
        Result<Page<List<UserLicenseReportPageVO>>> result = new Result<Page<List<UserLicenseReportPageVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = userReportService.getUserLicensePage(page, param, chainType, enterpriseCode, enterpriseName, deptIds, positionIds, roleIds, order, sort, loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("按条件搜索员工page信息:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "员工资质报表导出", notes = "员工资质报表导出 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/exportUserLicense",method= RequestMethod.GET)
    public void exportUserLicense(HttpServletResponse response, HttpServletRequest request,
                                 @ApiParam(value = "编码/检索码/名称", required = false) @RequestParam(required = false) String param,
                                 @ApiParam(value = "组织机构类型", required = false) @RequestParam(required = false) Integer chainType,
                                 @ApiParam(value = "组织机构编码", required = false) @RequestParam(required = false) String enterpriseCode,
                                 @ApiParam(value = "组织机构名称", required = false) @RequestParam(required = false) String enterpriseName,
                                 @ApiParam(value = "部门", required = false) @RequestParam(required = false) String deptIds,
                                 @ApiParam(value = "岗位", required = false) @RequestParam(required = false) String positionIds,
                                 @ApiParam(value = "角色", required = false) @RequestParam(required = false) String roleIds,
                                 @ApiParam(value = "按某一列排序(enterpriseCode或者code)", required = false) @RequestParam(required = false) String order,
                                 @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required = false) String sort) throws Exception {

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "员工资质";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<UserLicenseExcelVO> userLicenseExcelVO = userReportService.getUserLicenseExcelForm(param, chainType, enterpriseCode, enterpriseName, deptIds, positionIds, roleIds, order, sort, loginUser);
            userReportService.exportLicense(output,userLicenseExcelVO,loginUser);
        }catch(Exception e){
            logger.error("员工资质报表导出:"+e.getMessage(),e);
        }

    }
}
