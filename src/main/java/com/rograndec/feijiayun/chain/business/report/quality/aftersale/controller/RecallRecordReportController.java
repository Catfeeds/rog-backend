package com.rograndec.feijiayun.chain.business.report.quality.aftersale.controller;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsReportTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.service.RecoverRecordReportService;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.RecallRecordReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.RecallRecordReportReqVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.RecoverRecordReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.RecoverRecordReportReqVO;
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

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_aftersale_recallRecord",description = "报表-质量管理-售后管理-召回记录")
@RestController
@RequestMapping("report/quality/aftersale/recallRecord")
@Validated
public class RecallRecordReportController {




    @Autowired
    private RecoverRecordReportService recoverRecordReportService;


    @ApiOperation(value="获取追回记录列表", notes = "获取追回记录列表  | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPageList", method = RequestMethod.POST)
    public Result<Page<BaseGoodsReportTotalVO<RecallRecordReportPageVO>>> getPageList(@RequestBody RecallRecordReportReqVO requestVO, HttpServletRequest request){

        Result<Page<BaseGoodsReportTotalVO<RecallRecordReportPageVO>>> result = new Result<>();
        if (requestVO.getPageNo() <= 0 || requestVO.getPageSize() <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Page page = recoverRecordReportService.getRecallPageList(requestVO,loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

        return result;

    }



    @ApiOperation(value="导出追回记录列表", notes = "导出追回记录列表 | 开发者: 杜东阳 | 已联调")
    @RequestMapping(value = "/export", method = RequestMethod.POST,produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void export(RecallRecordReportReqVO requestVO, HttpServletResponse response, HttpServletRequest request) throws Exception {


        String name = "召回记录";
        // 输出Excel文件
        OutputStream output =
                response.getOutputStream();
        // 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        recoverRecordReportService.exportRecall(requestVO,loginUser,output);
        output.close();
        output.flush();
    }
}
