package com.rograndec.feijiayun.chain.business.report.quality.storage.controller;

import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsClearReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsLoadReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
@Api(value = "report_quality_storage_goodsClear",description = "报表-质量管理-存储与养护-中药清斗")
@RestController
@RequestMapping("report/quality/storage/goodsClear")
@Validated
public class GoodsClearReportController {

    private static final Log logger = LogFactory.getLog(GoodsClearReportController.class);

    @Autowired
    private GoodsClearReportService goodsClearReportService;

    @ApiOperation(value="按条件搜索中药清斗报表信息", notes = "按条件搜索中药清斗报表信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getGoodClearPage", method = RequestMethod.POST)
    @ApiImplicitParam(name = "requestGoodClearVO", value = "检索条件", required = true, dataType = "RequestGoodClearVO")
    public Result<GoodsClearReportVO> getGoodClearPage(HttpServletRequest request, @RequestBody RequestGoodClearVO requestGoodClearVO){
        Result<GoodsClearReportVO> result = new Result<GoodsClearReportVO>();
        try{
            if(requestGoodClearVO.getPageNo() <= 0 || requestGoodClearVO.getPageSize() <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(requestGoodClearVO.getPageNo(), requestGoodClearVO.getPageSize());
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            GoodsClearReportVO vo  = goodsClearReportService.getGoodClearPage(page,requestGoodClearVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
            logger.error("按条件搜索中药清斗报表信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "中药清斗信息报表导出", notes = "中药清斗信息报表导出 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/exportGoodsClear",method = RequestMethod.POST,produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiImplicitParam(name = "requestGoodClearVO", value = "检索条件", required = true, dataType = "RequestGoodClearVO")
    public void exportGoodsClear(HttpServletResponse response, HttpServletRequest request,RequestGoodClearVO requestGoodClearVO) throws Exception {

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "中药清斗信息";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            GoodsClearReportExcelPageVO goodsClearReportExcelPageVO = goodsClearReportService.getExcelForm(requestGoodClearVO,loginUser);
            goodsClearReportService.export(output,goodsClearReportExcelPageVO,loginUser);
        }catch(Exception e){
            logger.error("中药装斗信息报表导出:"+e.getMessage(),e);
        }

    }
	
}
