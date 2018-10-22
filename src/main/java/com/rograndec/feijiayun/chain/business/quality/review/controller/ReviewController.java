package com.rograndec.feijiayun.chain.business.quality.review.controller;

import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportVO;
import com.rograndec.feijiayun.chain.business.quality.review.service.ReviewService;
import com.rograndec.feijiayun.chain.business.quality.review.vo.GoodsReviewVO;
import com.rograndec.feijiayun.chain.business.quality.review.vo.ReviewPageVO;
import com.rograndec.feijiayun.chain.business.quality.review.vo.ReviewRequestVO;
import com.rograndec.feijiayun.chain.business.quality.review.vo.ReviewVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.param.ParamUtils;
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
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dudy on 2017-10-18 .
 */
@Api(value = "quality-review", description = "质量管理-药品质量评审", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/quality/review")
@Validated
public class ReviewController {


    @Autowired
    private ReviewService reviewService;


    @ApiOperation(value = "简单搜索商品", notes = "根据编码/条形码/检索码···搜索商品 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getGoodsByParam", method = RequestMethod.GET)
    public Result<List<GoodsReviewVO>> getGoodsByParam(HttpSession session, @ApiParam(value = "检索关键字", required = true) @RequestParam("param") String param) {
        Result<List<GoodsReviewVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        CommonParamSupplierAndGoods paramVO = new CommonParamSupplierAndGoods();
        ParamUtils.packParam(userVO,paramVO);
//        map.put("enterpriseId", ChainType.getHeadEnterpriseId(userVO));
        map.put("param", param);
        map.put("paramVO",paramVO);
        List<GoodsReviewVO> goodsList = reviewService.getGoods(map);
        result.setData(goodsList);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }


    @ApiOperation(value = "获取商品统计信息", notes = "获取商品统计信息 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getStatistics", method = RequestMethod.GET)
    public Result<ReviewVO> getStatistics(HttpServletRequest request,@ApiParam(value = "商品ID", required = true)@RequestParam("goodsId") Long goodsId,
                                          @ApiParam(value = "评审开始时间", required = true)@RequestParam("startDate") String startDate,
                                          @ApiParam(value = "评审结束时间", required = true)@RequestParam("endDate") String endDate){
        Result<ReviewVO> result =new Result();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        Map<String,Object> param = new HashMap<>();
        param.put("eId",loginUser.getEnterpriseId());
        param.put("goodsId",goodsId);
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,reviewService.getStatistics(goodsId,startDate,endDate,loginUser));
        return result;

    }

    @ApiOperation(value = "保存药品质量评审", notes = "保存药品质量评审 | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<String> save(@RequestBody ReviewVO reviewVO, HttpServletRequest request) throws Exception {

        Result<String> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        reviewService.save(reviewVO, loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存成功");
        return result;

    }


    @ApiOperation(value = "修改药品质量评审", notes = "修改药品质量评审| 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<String> update(@RequestBody ReportVO report, HttpServletRequest request) throws Exception {
        Result<String> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        reviewService.update(report, loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改成功");
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除药品质量评审", notes = "删除药品质量评审 | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> delete(@ApiParam(value = "主键ID", required = true) @RequestParam Long id) {
        Result<String> result = new Result<>();
        reviewService.delete(id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除成功");
        return result;
    }

    @ApiOperation(value="查看药品质量评审", notes = "查看药品质量评审 | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result<ReviewVO> get(@ApiParam(value = "主键ID", required = true) @RequestParam Long id) throws InvocationTargetException, IllegalAccessException {

        Result<ReviewVO> result =new Result();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,reviewService.get(id));
        return result;
    }


    @ApiOperation(value="导出药品质量评审", notes = "导出药品质量评审 | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@ApiParam(value = "主键ID", required = true) @RequestParam Long id, HttpServletResponse response,HttpServletRequest request) throws Exception {


        String name = "药品质量评审";
        // 输出Excel文件
        OutputStream output =
                response.getOutputStream();
        // 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");


        reviewService.export(id,output,loginUser);
        output.close();
        output.flush();
    }


    @ApiOperation(value="获取药品质量评审列表", notes = "获取药品质量评审列表  | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPageList", method = RequestMethod.POST)
    public Result<Page<List<ReviewPageVO>>> getPageList(@RequestBody ReviewRequestVO requestVO, HttpServletRequest request){

        Result<Page<List<ReviewPageVO>>> result = new Result<>();
        if (requestVO.getPageNo() <= 0 || requestVO.getPageSize() <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Page page = reviewService.getPageList(requestVO,loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

        return result;

    }
    @ApiOperation(value="导出药品质量评审列表", notes = "导出药品质量评审列表 | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/exportList", method = RequestMethod.GET)
    public void exportList(@RequestParam(value = "param",required = false)@ApiParam(value = "搜索关键字", required = false)String param,
                           HttpServletRequest request,HttpServletResponse response) throws Exception{

        String name = "药品质量评审列表";
        // 输出Excel文件
        OutputStream output =
                response.getOutputStream();
        // 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        ReviewRequestVO requestVO = new ReviewRequestVO();
        requestVO.setParam(param);
        reviewService.exportList(requestVO,loginUser,output);
        output.close();
        output.flush();
    }



}
