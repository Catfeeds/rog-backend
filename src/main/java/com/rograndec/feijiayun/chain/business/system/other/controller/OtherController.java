package com.rograndec.feijiayun.chain.business.system.other.controller;

import com.rograndec.feijiayun.chain.business.system.other.entity.SysLog;
import com.rograndec.feijiayun.chain.business.system.other.service.OtherService;
import com.rograndec.feijiayun.chain.business.system.other.vo.DataBackupVO;
import com.rograndec.feijiayun.chain.business.system.other.vo.SysLogParamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * Created by ST on 2017/8/21.
 */
@Api(value = "other",description = "系统管理-数据备份，系统日志、修改密码服务接口")
@RestController
@RequestMapping("system/other")
public class OtherController {

    private static Logger logger = LoggerFactory.getLogger(OtherController.class);

    @Autowired
    private OtherService otherService;

    @ApiOperation(value = "查看数据备份", notes = "查看数据备份 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getDataBackupRecord",method= RequestMethod.GET)
    public Result<List<DataBackupVO>> getDataBackupRecord(HttpSession session){
        Result<List<DataBackupVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,otherService.getDataBackup(userVO.getEnterpriseId(),userVO.getParentId()));
        return result;
    }

    @ApiOperation(value = "查看系统日志", notes = "查看系统日志 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getSysLog",method= RequestMethod.POST)
    @ApiImplicitParam(name = "sysLogParamVO", value = "查询条件", required = true, dataType = "SysLogParamVO")
    public Result<Page<List<SysLog>>> getSysLog(HttpSession session, @RequestBody SysLogParamVO sysLogParamVO){
        Result<Page<List<SysLog>>> result = new Result<>();


        if (sysLogParamVO.getPageNo() <= 0 || sysLogParamVO.getPageSize() <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        UserVO userVO = (UserVO) session.getAttribute("user");
        sysLogParamVO.setEnterpriseId(userVO.getEnterpriseId());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,otherService.getSystemLogList(sysLogParamVO));

        return result;
    }


    @ApiOperation(value = "导出系统日志", notes = "导出系统日志 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/excelExportSystemLog", method = RequestMethod.GET,produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void excelExportGoods(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String name = "系统日志.xlsx";
        if(StringUtils.isBlank(startDate)|| StringUtils.isBlank(endDate) || "null".equals(startDate) || "null".equals(endDate)){
            String newStartDate = DateUtils.getBeforeAfterDateByDateParam(new Date(),-5);
            endDate = DateUtils.getBeforeAfterDateByDateParam(new Date(),1);
            startDate = newStartDate;
        }
        logger.info("startDate={},endDate={}",startDate,endDate);
        name = startDate + "--" + endDate + name;
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        //输出Excel文件
        OutputStream output = null;
        try {
            SysLogParamVO sysLogParamVO = new SysLogParamVO();
            sysLogParamVO.setStartDate(startDate);
            sysLogParamVO.setEndDate(endDate);
            UserVO userVO = (UserVO) session.getAttribute("user");
            sysLogParamVO.setEnterpriseId(userVO.getEnterpriseId());
            output = response.getOutputStream();
            otherService.excelExport(output,sysLogParamVO);
            output.close();
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
