package com.rograndec.feijiayun.chain.business.report.quality.distr.controller;

import com.rograndec.feijiayun.chain.business.report.quality.distr.service.DistrInStorageReportService;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inStorage.InStorageTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inStorage.RequestGetInStorageParamVO;
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
@Api(value = "report_quality_distr_distrInStorage",description = "报表-质量管理-门店-收货与验收-配进入库")
@RestController
@RequestMapping("report/quality/distr/distrInStorage")
@Validated
public class DistrInStorageReportController {

    @Autowired
    private DistrInStorageReportService distrInStorageReportService;

    @ApiOperation(value = "报表-质量管理-门店-收货与验收-配进入库分页查询", notes = "报表-质量管理-门店-收货与验收-配进入库分页查询 | 开发者 孙腾 | 开发中")
    @RequestMapping(value="/getDistrInStorageGoodsList",method= RequestMethod.POST)
    @ApiImplicitParam(name = "paramForListVO", value = "检索条件", required = true, dataType = "RequestGetInStorageParamVO")
    public Result<Page<InStorageTotalVO>> getDistrInStorageGoodsList(HttpSession session, @RequestParamValid @RequestBody RequestGetInStorageParamVO paramForListVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<InStorageTotalVO>> result = new Result<>();
        Page<InStorageTotalVO> page = new Page<>(paramForListVO.getPageNo(),paramForListVO.getPageSize());
        distrInStorageReportService.getInStorageGoodsList(page,paramForListVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "导出配进入库单", notes = "导出配进入库单 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/excelInStorageGoodsList", method = RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "paramForListVO", value = "检索条件", required = true, dataType = "RequestGetInStorageParamVO")
    })
    public void excelInStorageGoodsList(RequestGetInStorageParamVO paramForListVO, HttpSession session, HttpServletResponse response) throws Exception {

        UserVO userVO = (UserVO) session.getAttribute("user");
        String name = "配进入库单";
        name += ".xlsx";
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        //输出Excel文件
        OutputStream output = null;
        output = response.getOutputStream();
        distrInStorageReportService.exportInStorageGoodsList(output,userVO,paramForListVO);
        output.close();
        output.flush();

    }
	
}
