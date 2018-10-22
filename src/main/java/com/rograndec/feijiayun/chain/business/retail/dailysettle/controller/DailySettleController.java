package com.rograndec.feijiayun.chain.business.retail.dailysettle.controller;

import com.rograndec.feijiayun.chain.business.retail.dailysettle.service.DailySettleService;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.vo.DailySettleStoreVO;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.vo.DailySettleVO;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.vo.SaveShowDailyInfo;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.vo.WillDailySettleVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by madong on 2017/9/19.
 */
@Api(value = "dailySettle",description = "零售管理-销售日结-总部", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/daily/settle")
public class DailySettleController {
    private static final Log logger = LogFactory.getLog(DailySettleController.class);
    @Autowired
    DailySettleService dailySettleService;

//    @ApiOperation(value="总部-获取待日结列表", notes = "总部获取待日结列表 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
//    @RequestMapping(value = "/getWillDailySettle", method = RequestMethod.GET)
//    public Result<Page<List<WillDailySettleVO>>> getWillDailySettle(HttpServletRequest request,
//           @ApiParam(value = "分页参数,页码",required = true) @RequestParam Integer pageNo,
//           @ApiParam(value = "分页参数,页容量",required = true) @RequestParam Integer pageSize,
//           @ApiParam(value = "待排序字段") @RequestParam(required = false) String orderName,
//           @ApiParam(value = "排序方式ASC/DESC") @RequestParam(required = false) String orderType,
//           @ApiParam(value = "搜索起始日期") @RequestParam(required = false) String startTime,
//           @ApiParam(value = "搜索截止日期") @RequestParam(required = false) String endTime){
//        Result<Page<List<WillDailySettleVO>>> result = new Result();
//        try {
//            HttpSession session = request.getSession(true);
//            UserVO loginUser = (UserVO) session.getAttribute("user");
//            if(loginUser.getParentId() == 0l)
//                result.setBizCodeSuccessInfo(SysCode.SUCCESS,dailySettleService.getWillDailySettle(loginUser,pageNo,pageSize,orderName,orderType,startTime,endTime));
//            else
//                result.setBizCodeSuccessInfo(SysCode.SUCCESS,dailySettleService.getWillDailySettleStore(loginUser,pageNo,pageSize,orderName,orderType,startTime,endTime));
//        }catch (Exception e){
//            logger.error("总部获取待日结列表错误:"+e.getMessage(),e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//            return result;
//        }
//        return result;
//    }

//    @ApiOperation(value="总部-获取已日结列表", notes = "总部获取已日结列表 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
//    @RequestMapping(value = "/getDailySettle", method = RequestMethod.GET)
//    public Result<Page<List<DailySettleVO>>> getDailySettle(HttpServletRequest request,
//           @ApiParam(value = "分页参数,页码",required = true) @RequestParam Integer pageNo,
//           @ApiParam(value = "分页参数,页容量",required = true) @RequestParam Integer pageSize,
//           @ApiParam(value = "待排序字段") @RequestParam(required = false) String orderName,
//           @ApiParam(value = "排序方式ASC/DESC") @RequestParam(required = false) String orderType,
//           @ApiParam(value = "搜索起始日期") @RequestParam(required = false) String startTime,
//           @ApiParam(value = "搜索截止日期") @RequestParam(required = false) String endTime){
//        Result<Page<List<DailySettleVO>>> result = new Result();
//        try {
//            HttpSession session = request.getSession(true);
//            UserVO loginUser = (UserVO) session.getAttribute("user");
//            if (loginUser.getParentId() == 0l)
//                result.setBizCodeSuccessInfo(SysCode.SUCCESS,dailySettleService.getDailySettle(loginUser,pageNo,pageSize,orderName,orderType,startTime,endTime));
//            else
//                result.setBizCodeSuccessInfo(SysCode.SUCCESS,dailySettleService.getDailySettleStore(loginUser,pageNo,pageSize,orderName,orderType,startTime,endTime));
//        }catch (Exception e){
//            logger.error("总部获取已日结列表错误:"+e.getMessage(),e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//            return result;
//        }
//        return result;
//    }

    @ApiOperation(value="门店-获取待日结列表", notes = "门店获取待日结列表 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getWillDailySettleStore", method = RequestMethod.GET)
    public Result<Page<List<WillDailySettleVO>>> getWillDailySettleStore(HttpServletRequest request,
                  @ApiParam(value = "分页参数,页码",required = true) @RequestParam Integer pageNo,
                  @ApiParam(value = "分页参数,页容量",required = true) @RequestParam Integer pageSize,
                  @ApiParam(value = "待排序字段") @RequestParam(required = false) String orderName,
                  @ApiParam(value = "排序方式ASC/DESC") @RequestParam(required = false) String orderType,
                  @ApiParam(value = "搜索起始日期") @RequestParam(required = false) String startTime,
                  @ApiParam(value = "搜索截止日期") @RequestParam(required = false) String endTime){
        Result<Page<List<WillDailySettleVO>>> result = new Result();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() == 0l)
                result.setBizCodeSuccessInfo(SysCode.SUCCESS,dailySettleService.getWillDailySettle(loginUser,pageNo,pageSize,orderName,orderType,startTime,endTime));
            else
                result.setBizCodeSuccessInfo(SysCode.SUCCESS,dailySettleService.getWillDailySettleStore(loginUser,pageNo,pageSize,orderName,orderType,startTime,endTime));
        }catch (Exception e){
            logger.error("总部获取待日结列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="门店-获取已日结列表", notes = "门店获取已日结列表 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getDailySettleStore", method = RequestMethod.GET)
    public Result<Page<List<DailySettleStoreVO>>> getDailySettleStore(HttpServletRequest request,
           @ApiParam(value = "分页参数,页码",required = true) @RequestParam Integer pageNo,
           @ApiParam(value = "分页参数,页容量",required = true) @RequestParam Integer pageSize,
           @ApiParam(value = "待排序字段") @RequestParam(required = false) String orderName,
           @ApiParam(value = "排序方式ASC/DESC") @RequestParam(required = false) String orderType,
           @ApiParam(value = "搜索起始日期") @RequestParam(required = false) String startTime,
           @ApiParam(value = "搜索截止日期") @RequestParam(required = false) String endTime){
        Result<Page<List<DailySettleStoreVO>>> result = new Result();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if (loginUser.getParentId() == 0l)
                result.setBizCodeSuccessInfo(SysCode.SUCCESS,dailySettleService.getDailySettle(loginUser,pageNo,pageSize,orderName,orderType,startTime,endTime));
            else
                result.setBizCodeSuccessInfo(SysCode.SUCCESS,dailySettleService.getDailySettleStore(loginUser,pageNo,pageSize,orderName,orderType,startTime,endTime));
        }catch (Exception e){
            logger.error("门店获取已日结列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看待日结/已日结单据", notes = "查看待日结/已日结单据 | 开发者:马东 | 已联调 ")
    @RequestMapping(value="/showDailySettle",method= RequestMethod.GET)
    public Result<List<SaveShowDailyInfo>> showDailySettle(HttpServletRequest request,
           @ApiParam(value = "当前需要查看的待日结单据ID集合",required = true) @RequestParam String ids,
           @ApiParam(value = "待日结/已日结标志,待日结传0,已日结传1",required = true) @RequestParam Long settleType,
           @ApiParam(value = "如果是总部登录,传对应待日结门店的id",required = false) @RequestParam(required = false) Long enterpriseId,
           @ApiParam(value = "待排序字段") @RequestParam(required = false) String orderName,
           @ApiParam(value = "排序方式ASC/DESC") @RequestParam(required = false) String orderType){
        Result<List<SaveShowDailyInfo>> result = new Result();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,dailySettleService.showDailySettle(loginUser,ids,settleType,enterpriseId,orderName,orderType));
        }catch (Exception e){
            logger.error("查看待日结单据错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "日结操作", notes = "日结操作 | 开发者:马东 | 已联调 ")
    @RequestMapping(value="/saveDailySettle",method= RequestMethod.POST)
    public Result<String> saveDailySettle(HttpServletRequest request,
           @ApiParam(value = "待日结单据集合",required = true) @RequestBody List<SaveShowDailyInfo> saveShowDailyInfos){
        Result<String> result = new Result();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            dailySettleService.saveDailySettle(loginUser,saveShowDailyInfos);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"日结成功");
        }catch (Exception e){
            logger.error("日结错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "导出日结信息", notes = "导出日结信息 | 开发者:马东 | 已联调 ")
    @RequestMapping(value="/export/{ids}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "ids", value = "当前需要查看的日结单据ID集合", required = true, dataType = "String", paramType="path")
    public void export(HttpServletRequest request, HttpServletResponse response, @PathVariable String ids){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            StringBuilder name = new StringBuilder();
            if(loginUser.getParentId()==0l)
                name.append("总部日结明细");
            else
                name.append("分店日结明细");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name.toString(), "UTF-8")+".xlsx");
            dailySettleService.export(output,loginUser,ids);
        }catch(Exception e){
            logger.error("导出日结信息错误:"+e.getMessage(),e);
        }
    }

}
