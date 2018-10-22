package com.rograndec.feijiayun.chain.business.report.quality.aftersale.controller;

import com.rograndec.feijiayun.chain.business.aftersale.complain.vo.ComplainPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.service.ComplainReportService;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.ComplainReqVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "report_quality_aftersale_complain",description = "报表-质量管理-售后管理-投诉管理")
@RestController
@RequestMapping("report/quality/aftersale/complain")
@Validated
public class ComplainReportController {



    @Autowired
    private ComplainReportService complainReportService;


    @ApiOperation(value = "分页获取投诉管理列表信息", notes = "分页获取(待培训/分页获取投诉管理列表信息)列表信息 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getComplainPage", method = RequestMethod.POST)
    public Result<Page<List<ComplainPageVO>>> getComplainPage(HttpServletRequest request,@RequestBody ComplainReqVO reqVO) {
        Result<Page<List<ComplainPageVO>>> result = new Result<Page<List<ComplainPageVO>>>();
        if (reqVO.getPageNo() <= 0 || reqVO.getPageSize() <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        Page page = complainReportService.getPageList(reqVO,loginUser);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        return result;
    }

    @ApiOperation(value="导出投诉管理分页列表", notes = "导出投诉管理分页列表 | 开发者: 杜东阳 | 已联调")
    @RequestMapping(value = "/export", method = RequestMethod.POST,produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void export(ComplainReqVO reqVO, HttpServletResponse response,HttpServletRequest request) throws Exception {


        String name = "投诉管理";
        // 输出Excel文件
        OutputStream output =
                response.getOutputStream();
        // 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        complainReportService.export(reqVO,loginUser,output);
        output.close();
        output.flush();
    }
	
}
