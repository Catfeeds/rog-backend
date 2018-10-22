package com.rograndec.feijiayun.chain.business.aftersale.adverse.controller;

import com.rograndec.feijiayun.chain.business.aftersale.adverse.service.AdverseReactionReportService;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.GoodsReportVO;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportPageVO;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportRequestVO;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dudy on 2017-10-13.
 */
@Api(value = "aftersale-adverse", description = "售后管理-不良反应报告", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/aftersale/adverse")
@Validated
public class ReactionReportController {

    @Autowired
    private AdverseReactionReportService reportService;


    @ApiOperation(value = "简单搜索商品(没有限制条件)", notes = "根据编码/条形码/检索码搜索商品 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value="/getGoodsByParam",method= RequestMethod.GET)
    public Result<List<GoodsReportVO>> getGoodsByParam(HttpSession session, @ApiParam(value = "检索关键字", required = true)@RequestParam("param") String param){
        Result<List<GoodsReportVO>> result = new Result<List<GoodsReportVO>>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();

        map.put("enterpriseId", ChainType.getHeadEnterpriseId(userVO));
        map.put("param", param);
        List<GoodsReportVO> goodsList=reportService.getGoods(map);
        result.setData(goodsList);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }


    @ApiOperation(value="获取不良反应报告列表", notes = "获取不良反应报告列表  | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/report/getPageList", method = RequestMethod.POST)
    public Result<Page<List<ReportPageVO>>> getPageList(@RequestBody ReportRequestVO requestVO, HttpServletRequest request){

        Result<Page<List<ReportPageVO>>> result = new Result<>();
        if (requestVO.getPageNo() <= 0 || requestVO.getPageSize() <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Page page = reportService.getPageList(requestVO,loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

        return result;

    }


    @ApiOperation(value="查看不良反应报告", notes = "查看不良反应报告 | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/report/get", method = RequestMethod.GET)
    public Result<ReportVO> get(@ApiParam(value = "主键ID", required = true) @RequestParam Long id){

        Result<ReportVO> result =new Result();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,reportService.get(id));
        return result;
    }


    @ApiOperation(value="保存不良反应报告", notes = "保存不良反应报告 | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/report/save", method = RequestMethod.POST)
    public Result<String> save(@RequestBody @Valid ReportVO report, HttpServletRequest request) throws Exception{
        Result<String> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        reportService.save(report,loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,"保存成功");
        return  result;
    }


    @ApiOperation(value="修改不良反应报告", notes = "修改不良反应报告 | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/report/update", method = RequestMethod.POST)
    public Result<String>  update(@RequestBody @Valid ReportVO report, HttpServletRequest request) throws Exception{
        Result<String> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        reportService.update(report,loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,"修改成功");
        return  result;
    }

    @RequestMapping(value = "/report/delete", method = RequestMethod.GET)
    @ApiOperation(value="删除不良反应报告", notes = "删除不良反应报告 | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> delete(@ApiParam(value = "主键ID", required = true) @RequestParam Long id){
        Result<String> result = new Result<>();
        reportService.delete(id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,"删除成功");
        return  result;
    }


    @ApiOperation(value="导出不良反应报告", notes = "导出不良反应报告 | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/report/export", method = RequestMethod.GET)
    public void export(@ApiParam(value = "主键ID", required = true) @RequestParam Long id, HttpServletResponse response,HttpServletRequest request) throws Exception {


        String name = "不良反应报告";
        // 输出Excel文件
        OutputStream output =
                response.getOutputStream();
        // 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");


        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        reportService.export(id,output,loginUser);
        output.close();
        output.flush();
    }

}
