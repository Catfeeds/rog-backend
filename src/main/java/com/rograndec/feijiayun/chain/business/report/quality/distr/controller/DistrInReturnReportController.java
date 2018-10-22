package com.rograndec.feijiayun.chain.business.report.quality.distr.controller;

import com.rograndec.feijiayun.chain.business.report.quality.distr.service.DistrInReturnReportService;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inReturn.InReturnTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inReturn.RequestGetInReturnParamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.valid.RequestParamValid;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_distr_distrInReturn",description = "报表-质量管理-门店-收货与验收-配进退出")
@RestController
@RequestMapping("report/quality/distr/distrInReturn")
@Validated
public class DistrInReturnReportController {

    @Autowired
    private DistrInReturnReportService distrInReturnReportService;

    @ApiOperation(value = "报表-质量管理-门店-收货与验收-配进退出分页查询", notes = "报表-质量管理-门店-收货与验收-配进退出分页查询 | 开发者 孙腾 | 开发中")
    @RequestMapping(value="/getDistrInReturnGoodsList",method= RequestMethod.POST)
    @ApiImplicitParam(name = "paramForListVO", value = "检索条件", required = true, dataType = "RequestGetInReturnParamVO")
    public Result<Page<InReturnTotalVO>> getDistrInReturnGoodsList(HttpSession session, @RequestParamValid @RequestBody RequestGetInReturnParamVO paramForListVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<InReturnTotalVO>> result = new Result<>();
        Page<InReturnTotalVO> page = new Page<>(paramForListVO.getPageNo(),paramForListVO.getPageSize());
        distrInReturnReportService.getInReturnGoodsList(page,paramForListVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "导出配进退出单", notes = "导出配进退出单 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/excelInReturnGoodsList", method = RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "paramForListVO", value = "检索条件", required = true, dataType = "RequestGetInReturnParamVO")
    })
    public void excelInReturnGoodsList(RequestGetInReturnParamVO paramForListVO, HttpSession session, HttpServletResponse response) throws Exception {

        UserVO userVO = (UserVO) session.getAttribute("user");
        String name = "配进退出单";
        name += ".xlsx";
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        //输出Excel文件
        OutputStream output = null;
        output = response.getOutputStream();
        distrInReturnReportService.exportInReturnGoodsList(output,userVO,paramForListVO);
        output.close();
        output.flush();

    }
	
}
