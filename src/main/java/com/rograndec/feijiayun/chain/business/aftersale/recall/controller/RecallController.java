package com.rograndec.feijiayun.chain.business.aftersale.recall.controller;

import com.rograndec.feijiayun.chain.business.aftersale.recall.service.RecallService;
import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.RecallPlanPageParamVO;
import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.RecallPlanVO;
import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.RecallRecordPageParamVO;
import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.RecallRecordVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.AfterSaleChooseGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Api(value = "aftersale_recall",description = "售后管理-药品召回")
@RestController
@RequestMapping("aftersale/recall")
@Validated
public class RecallController {
    private static final Log logger = LogFactory.getLog(RecallController.class);
    @Autowired
    RecallService recallService;

    @ApiOperation(value = "获取药品召回计划列表", notes = "获取药品召回计划列表 | 开发者:马东 | 已联调")
    @RequestMapping(value="/getRecallPlanList", method= RequestMethod.POST)
    public Result<Page<List<RecallPlanVO>>> getRecallPlanList(HttpServletRequest request,
                                                              @ApiParam(value = "药品召回计划查询参数",name = "recallPlanPageParamVO",required = true) @RequestBody RecallPlanPageParamVO recallPlanPageParamVO){
        Result<Page<List<RecallPlanVO>>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, recallService.getRecallPlanList(loginUser,recallPlanPageParamVO));
        }catch (Exception e){
            logger.error("获取药品召回计划列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "获取药品召回记录列表", notes = "获取药品召回记录列表 | 开发者:马东 | 已联调")
    @RequestMapping(value="/getRecallRecordList", method= RequestMethod.POST)
    public Result<Page<List<RecallRecordVO>>> getRecallRecordList(HttpServletRequest request,
           @ApiParam(value = "药品召回记录查询参数",name = "recallRecordPageParamVO",required = true) @RequestBody RecallRecordPageParamVO recallRecordPageParamVO){
        Result<Page<List<RecallRecordVO>>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, recallService.getRecallRecordList(loginUser,recallRecordPageParamVO));
        }catch (Exception e){
            logger.error("获取药品召回计划列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "删除药品召回计划", notes = "删除药品召回计划 | 开发者:马东 | 已联调")
    @RequestMapping(value="/deleteRecallPlan", method= RequestMethod.GET)
    public Result<String> deleteRecallPlan(HttpServletRequest request,
           @ApiParam(value = "药品召回计划id",name = "id",required = true) @RequestParam Long id){
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(!recallService.isDeleteRecallPlan(loginUser,id)){
                result.setBizCodeSuccessInfo(SysCode.FAIL, "该药品召回计划已生成召回记录,不能删除!");
                return result;
            }
            int deleteCount = recallService.deleteRecallPlan(loginUser,id);
            if(deleteCount>0)
                result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除药品召回计划成功!");
            else result.setBizCodeSuccessInfo(SysCode.FAIL,"该计划已不存在于系统中!");
        }catch (Exception e){
            logger.error("删除药品召回计划错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "删除药品召回记录", notes = "删除药品召回记录 | 开发者:马东 | 已联调")
    @RequestMapping(value="/deleteRecallRecord", method= RequestMethod.GET)
    public Result<String> deleteRecallRecord(HttpServletRequest request,
           @ApiParam(value = "药品召回记录id",name = "id",required = true) @RequestParam Long id){
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            int deleteCount = recallService.deleteRecallRecord(loginUser,id);
            if(deleteCount>0)
                result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除药品召回记录成功!");
            else result.setBizCodeSuccessInfo(SysCode.FAIL,"该记录已不存在于系统中!");
        }catch (Exception e){
            logger.error("删除药品召回记录错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看药品召回计划明细", notes = "查看药品召回计划明细 | 开发者:马东 | 已联调")
    @RequestMapping(value = "/showRecallPlanDetail", method = RequestMethod.GET)
    public Result<RecallPlanVO> showRecallPlanDetail(HttpServletRequest request,
           @ApiParam(value = "药品召回计划id",name = "id",required = true) @RequestParam Long id) {
        Result<RecallPlanVO> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, recallService.showRecallPlanDetail(loginUser,id));
        } catch (Exception e) {
            logger.error("查看药品召回计划明细错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看药品召回记录明细", notes = "查看药品召回记录明细 | 开发者:马东 | 已联调")
    @RequestMapping(value = "/showRecallRecordDetail", method = RequestMethod.GET)
    public Result<RecallRecordVO> showRecallRecordDetail(HttpServletRequest request,
           @ApiParam(value = "药品召回记录id",name = "id",required = true) @RequestParam Long id) {
        Result<RecallRecordVO> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, recallService.showRecallRecordDetail(loginUser,id));
        } catch (Exception e) {
            logger.error("查看药品召回记录明细错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "保存药品召回计划", notes = "保存药品召回计划 | 开发者:马东 | 已联调")
    @RequestMapping(value = "/saveRecallPlan", method = RequestMethod.POST)
    public Result<String> saveRecallPlan(HttpServletRequest request,
           @ApiParam(value = "待保存药品召回计划",name = "recallPlanVO",required = true) @RequestBody RecallPlanVO recallPlanVO) {
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            recallService.saveRecallPlan(loginUser,recallPlanVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存药品召回计划成功");
        } catch (Exception e) {
            logger.error("保存药品召回计划错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "保存药品召回记录", notes = "保存药品召回记录 | 开发者:马东 | 已联调")
    @RequestMapping(value = "/saveRecallRecord", method = RequestMethod.POST)
    public Result<String> saveRecallRecord(HttpServletRequest request,
           @ApiParam(value = "待保存药品召回记录",name = "recallRecordVO",required = true) @RequestBody RecallRecordVO recallRecordVO) {
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            recallService.saveRecallRecord(loginUser,recallRecordVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存药品召回记录成功");
        } catch (Exception e) {
            logger.error("保存药品召回记录错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "修改药品召回计划", notes = "修改药品召回计划 | 开发者:马东 | 已联调")
    @RequestMapping(value = "/updateRecallPlan", method = RequestMethod.POST)
    public Result<String> updateRecallPlan(HttpServletRequest request,
           @ApiParam(value = "待修改药品召回计划",name = "recallPlanVO",required = true) @RequestBody RecallPlanVO recallPlanVO) {
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(!recallService.isDeleteRecallPlan(loginUser,recallPlanVO.getId())){
                result.setBizCodeSuccessInfo(SysCode.FAIL, "该药品召回计划已生成召回记录,不能修改!");
                return result;
            }
            recallService.updateRecallPlan(loginUser,recallPlanVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改药品召回计划成功");
        } catch (Exception e) {
            logger.error("修改药品召回计划错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "修改药品召回记录", notes = "修改药品召回记录 | 开发者:马东 | 已联调")
    @RequestMapping(value = "/updateRecallRecord", method = RequestMethod.POST)
    public Result<String> updateRecallRecord(HttpServletRequest request,
           @ApiParam(value = "待修改药品召回记录",name = "recallRecordVO",required = true) @RequestBody RecallRecordVO recallRecordVO) {
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            recallService.updateRecallRecord(loginUser,recallRecordVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改药品召回记录成功");
        } catch (Exception e) {
            logger.error("修改药品召回记录错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "售后管理-招回管理-选择商品", notes = "获取数据 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getRecordGoodsList", method = RequestMethod.GET)
    public Result<List<AfterSaleChooseGoodsVO>> getRecordGoodsList(HttpServletRequest request,
           @ApiParam(value = "计划id",name = "id",required = true) @RequestParam Long id,
           @ApiParam(value = "搜索商品关键字",name = "param") @RequestParam(required = false) String param) {
        Result<List<AfterSaleChooseGoodsVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, recallService.getRecordGoodsList(id,loginUser,param));
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("获取售后管理-招回管理-召回计划失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出Excel", notes = "按照模版将召回信息导出至Excel | 开发者:马东 | 已联调")
    @RequestMapping(value="/exportExcel", method=RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
           @ApiParam(value = "总单id",name = "id",required = true) @RequestParam Long id,
           @ApiParam(value = "待导出模式,0-待召回,1-已召回",name = "type",required = true) @RequestParam Integer type){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "";
            if(type == 0)
                name = "召回管理-待召回";
            else if(type == 1)
                name = "召回管理-已召回";
            else throw new Exception("未知的召回方式");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            recallService.exportExcel(output,id,loginUser,type);
        }catch(Exception e){
            logger.error("导出召回单信息错误:"+e.getMessage(),e);
        }

    }


}
