package com.rograndec.feijiayun.chain.business.report.quality.storage.controller;

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
@Api(value = "report_quality_storage_goodsLoad",description = "报表-质量管理-存储与养护-中药装斗")
@RestController
@RequestMapping("report/quality/storage/goodsLoad")
@Validated
public class GoodsLoadReportController {

    private static final Log logger = LogFactory.getLog(GoodsLoadReportController.class);

    @Autowired
    private GoodsLoadReportService goodsLoadReportService;

    @ApiOperation(value="按条件搜索中药装斗报表信息", notes = "按条件搜索中药装斗报表信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getGoodLoadPage", method = RequestMethod.POST)
    @ApiImplicitParam(name = "requestGoodLoadVO", value = "检索条件", required = true, dataType = "RequestGoodLoadVO")
    public Result<GoodLoadReportVO> getGoodLoadPage(HttpServletRequest request, @RequestBody RequestGoodLoadVO requestGoodLoadVO){
        Result<GoodLoadReportVO> result = new Result<GoodLoadReportVO>();
        try{
            if(requestGoodLoadVO.getPageNo() <= 0 || requestGoodLoadVO.getPageSize() <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(requestGoodLoadVO.getPageNo(), requestGoodLoadVO.getPageSize());
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            GoodLoadReportVO vo  = goodsLoadReportService.getGoodLoadPage(page,requestGoodLoadVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
            logger.error("按条件搜索中药装斗报表信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "中药装斗信息报表导出", notes = "中药装斗信息报表导出 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/exportGoodsLoad",method = RequestMethod.POST,produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiImplicitParam(name = "requestGoodLoadVO", value = "检索条件", required = true, dataType = "RequestGoodLoadVO")
    public void exportGoodsLoad(HttpServletResponse response, HttpServletRequest request,RequestGoodLoadVO requestGoodLoadVO) throws Exception {

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "中药装斗信息";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            GoodsLoadReportExcelPageVO goodsLoadReportExcelPageVO = goodsLoadReportService.getExcelForm(requestGoodLoadVO,loginUser);
            goodsLoadReportService.export(output,goodsLoadReportExcelPageVO,loginUser);
        }catch(Exception e){
            logger.error("中药装斗信息报表导出:"+e.getMessage(),e);
        }

    }
	
}
