package com.rograndec.feijiayun.chain.business.aftersale.complain.controller;

import com.rograndec.feijiayun.chain.business.aftersale.complain.service.ComplainService;
import com.rograndec.feijiayun.chain.business.aftersale.complain.vo.*;
import com.rograndec.feijiayun.chain.business.basic.user.vo.CheckTrainPlanVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.CheckWaitTrainPlanVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.ExportCheckTrainPlanVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.service.IncompatibilityService;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.SelectGoodsVO;
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
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@Api(value = "complain", description = "售后管理-投诉管理")
@RestController
@RequestMapping("complain")
@Validated
public class ComplainController {

    private static final Log logger = LogFactory.getLog(ComplainController.class);

    @Autowired
    ComplainService complainService;
    @Autowired
    IncompatibilityService incompatibilityService;

    @ApiOperation(value = "分页获取投诉管理列表信息", notes = "分页获取(待培训/分页获取投诉管理列表信息)列表信息 | 开发者 zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getComplainPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<ComplainPageVO>>> getComplainPage(HttpServletRequest request,
                                                                        @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                        @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                        @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
                                                                        @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
                                                                        @ApiParam(value = "投诉单号", required = false) @RequestParam(required = false) String code,
                                                                        @ApiParam(value = "受理人员", required = false) @RequestParam(required = false) String acceptManName,
                                                                        @ApiParam(value = "投诉人员", required = false) @RequestParam(required = false) String name,
                                                                        @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                                                        @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<List<ComplainPageVO>>> result = new Result<Page<List<ComplainPageVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<ComplainPageVO> ComplainPageVoList = complainService
                    .getComplainPage(pageNo, pageSize, loginUser.getEnterpriseId(), page, startTime, endTime, code, acceptManName, name, orderName, orderType);
            page.setResult(ComplainPageVoList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页获取投诉管理列表信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看投诉管理列表数据", notes = "查看投诉管理列表数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCheckComplain", method = RequestMethod.GET)
    @ResponseBody
    public Result<CheckComplainVO> getCheckComplain(HttpSession session,
                                                    @ApiParam(value = "投诉管理ID", required = true) @RequestParam Long id
    ) {
        Result<CheckComplainVO> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, complainService.getCheckComplain(enterpriseId, id, userVO));
        } catch (Exception e) {
            logger.error("查看投诉管理列表数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "修改投诉管理单据", notes = "修改投诉管理单据 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateComplain", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateComplain(HttpServletRequest request,
                                              @ApiParam(value = "修改投诉管理单据", required = true) @RequestBody ModifyComplainVO modifyComplainVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            complainService.updateComplain(loginUser, modifyComplainVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改投诉管理单据成功");

        } catch (BusinessException e) {
            logger.error("修改投诉管理单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("修改投诉管理单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "删除投诉管理列表数据", notes = "删除投诉管理列表数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getDeleteComplain", method = RequestMethod.GET)
    @ResponseBody
    public Result getDeleteComplain(HttpSession session,
                                         @ApiParam(value = "投诉管理单据ID", required = true) @RequestParam Long id
    ) {
        Result result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            complainService.getDeleteComplain(enterpriseId, id, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除投诉管理列表数据成功");
        } catch (Exception e) {
            logger.error("删除投诉管理列表数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "保存投诉管理单据", notes = "保存投诉管理单据 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveTrainPlan", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveTrainPlan(HttpServletRequest request,
                                          @ApiParam(value = "保存投诉管理单据", required = true) @RequestBody SaveTrainPlanVO saveTrainPlanVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            complainService.saveTrainPlan(loginUser, saveTrainPlanVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存投诉管理单据成功");

        } catch (BusinessException e) {
            logger.error("保存投诉管理单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("保存投诉管理单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询商品列表数据", notes = "查询商品列表数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getGoodsVoPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<GetGoodsVO>> getGoodsVoPage(HttpSession session,
                                                         @ApiParam(value = "页面输入框，包含编码、检索码、名称", required = false) @RequestParam(required = false) String key
    ) {
        Result<List<GetGoodsVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            //Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, complainService
                    .getGoodsVoPage(key, userVO));
        } catch (Exception e) {
            logger.error("查询商品列表数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出Excel", notes = "按照模版将投诉管理单据导出至Excel | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value="/exportExcel/{id}",method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "当前投诉管理单据ID", required = true, dataType = "Long", paramType="path")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"投诉管理单据";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            CheckComplainVO checkComplainVO  = complainService.getCheckComplain(loginUser.getEnterpriseId(), id, loginUser);
            complainService.exportExcel(output,checkComplainVO,loginUser);
        }catch(Exception e){
            logger.error("导出投诉管理单据错误:"+e.getMessage(),e);
        }
    }
}
