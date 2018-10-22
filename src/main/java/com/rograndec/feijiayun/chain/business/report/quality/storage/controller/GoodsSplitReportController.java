package com.rograndec.feijiayun.chain.business.report.quality.storage.controller;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsSplitService;
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
@Api(value = "report_quality_storage_goodsSplit",description = "报表-质量管理-存储与养护-商品拆零")
@RestController
@RequestMapping("report/quality/storage/goodsSplit")
@Validated
public class GoodsSplitReportController {

    private static final Log logger = LogFactory.getLog(GoodsSplitReportController.class);

    @Autowired
    private GoodsSplitService goodsSplitService;

    @ApiOperation(value="按条件搜索商品拆零报表信息", notes = "按条件搜索商品拆零报表信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getGoodsSplitPage", method = RequestMethod.POST)
    @ApiImplicitParam(name = "requestGoodsSplitVO", value = "检索条件", required = true, dataType = "RequestGoodsSplitVO")
    public Result<GoodsSplitReportVO> getGoodsSplitPage(HttpServletRequest request, @RequestBody RequestGoodsSplitVO requestGoodsSplitVO){
        Result<GoodsSplitReportVO> result = new Result<GoodsSplitReportVO>();
        try{
            if(requestGoodsSplitVO.getPageNo() <= 0 || requestGoodsSplitVO.getPageSize() <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(requestGoodsSplitVO.getPageNo(), requestGoodsSplitVO.getPageSize());
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            GoodsSplitReportVO vo  = goodsSplitService.getGoodsSpiltPage(page,requestGoodsSplitVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
            logger.error("按条件搜索商品拆零报表信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "商品拆零报表导出", notes = "商品拆零报表导出 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/exportGoodsSplit",method = RequestMethod.POST,produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiImplicitParam(name = "requestGoodsSplitVO", value = "检索条件", required = true, dataType = "RequestGoodsSplitVO")
    public void exportGoodsSplit(HttpServletResponse response, HttpServletRequest request,RequestGoodsSplitVO requestGoodsSplitVO) throws Exception {

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "商品拆零信息";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            GoodsSplitReportExcelPageVO goodsSplitReportExcelPageVO = goodsSplitService.getExcelForm(requestGoodsSplitVO,loginUser);
            goodsSplitService.export(output,goodsSplitReportExcelPageVO,loginUser);
        }catch(Exception e){
            logger.error("商品拆零信息报表导出:"+e.getMessage(),e);
        }

    }
	
}
