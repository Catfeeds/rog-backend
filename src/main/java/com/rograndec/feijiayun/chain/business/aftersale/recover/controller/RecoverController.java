package com.rograndec.feijiayun.chain.business.aftersale.recover.controller;

import com.rograndec.feijiayun.chain.business.aftersale.recover.service.RecoverPlanService;
import com.rograndec.feijiayun.chain.business.aftersale.recover.service.RecoverRecordService;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.AfterSaleChooseGoodsVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverPlanSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverPlanVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverRecordSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverRecordVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author xingjian.lan
 * @version 1.0
 * @ClassName: RecoverPlanController
 * @Description: 售后管理-追回管理-追回计划接口
 * @date 2017-10-16 17:51:12
 */
@Api(value = "RecoverController", description = "售后管理-追回管理-追回计划")
@RestController
@RequestMapping("aftersale/recover")
public class RecoverController {

    private static final Logger logger = LoggerFactory.getLogger(RecoverController.class);

    @Autowired
    private RecoverPlanService recoverPlanService;

    @Autowired
    private RecoverRecordService recoverRecordService;

    @ApiOperation(value = "售后管理-召回/追回-选择商品", notes = "获取数据 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAfterSaleGoodsList", method = RequestMethod.GET)
    public Result<List<AfterSaleChooseGoodsVO>> getAfterSaleGoodsList(HttpServletRequest request,
                                                                      @ApiParam(value = "搜索关键字", required = false) @RequestParam(required = false) String param) {
        Result<List<AfterSaleChooseGoodsVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, recoverPlanService.getAfterSaleGoodsList(loginUser.getEnterpriseId(), param));
        } catch (Exception e) {
            logger.error("获取售后管理-追回管理-追回计划数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "售后管理-追回操作-选择商品", notes = "获取数据 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getRecoverGoodsList", method = RequestMethod.GET)
    public Result<List<AfterSaleChooseGoodsVO>> getRecoverGoodsList(HttpServletRequest request,
                                                                    @ApiParam(value = "计划id",name = "id",required = true) @RequestParam Long id,
                                                                    @ApiParam(value = "搜索商品关键字",name = "param") @RequestParam(required = false) String param) {
        Result<List<AfterSaleChooseGoodsVO>> result = new Result<>();
        try {
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, recoverPlanService.getRecoverGoodsList(id, param));
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("获取售后管理-追回管理-追回计划数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "售后管理-追回管理-追回计划(待追回)分页列表", notes = "获取追回计划数据 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getRecoverPlanPage", method = RequestMethod.GET)
    public Result<Page<RecoverPlanVO>> getRecoverPlanPage(HttpServletRequest request,
                                                          @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                          @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                          @ApiParam(value = "起始时间", required = false) @RequestParam(required = false) Date startTime,
                                                          @ApiParam(value = "终止时间", required = false) @RequestParam(required = false) Date endTime,
                                                          @ApiParam(value = "追回单号", required = false) @RequestParam(required = false) String code,
                                                          @ApiParam(value = "通知人员", required = false) @RequestParam(required = false) String planManName,
                                                          @ApiParam(value = "追回负责人", required = false) @RequestParam(required = false) String rocoverMan,
                                                          @ApiParam(value = "按日期排序0-升序 1-降序", required = false) @RequestParam(required = false) Integer sortDate,
                                                          @ApiParam(value = "按单号排序0-升序 1-降序", required = false) @RequestParam(required = false) Integer sortCode) {
        Result<Page<RecoverPlanVO>> result = new Result<>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            Map<String, Object> map = new HashMap<>();
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("code", code);
            map.put("planManName", planManName);
            map.put("rocoverMan", rocoverMan);
            map.put("sortDate", sortDate);
            map.put("sortCode", sortCode);
            map.put("enterpriseId", loginUser.getEnterpriseId());

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, recoverPlanService.getRecoverPlanPage(page, map));
        } catch (Exception e) {
            logger.error("获取售后管理-追回管理-追回计划数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "售后管理-追回管理-追回记录(已追回)分页列表", notes = "获取追回记录列表数据 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getRecoverRecordPage", method = RequestMethod.GET)
    public Result<Page<RecoverRecordVO>> getRecoverRecordPage(HttpServletRequest request,
                                                              @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                              @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                              @ApiParam(value = "起始时间", required = false) @RequestParam(required = false) Date startTime,
                                                              @ApiParam(value = "终止时间", required = false) @RequestParam(required = false) Date endTime,
                                                              @ApiParam(value = "追回单号", required = false) @RequestParam(required = false) String code,
                                                              @ApiParam(value = "追回人员", required = false) @RequestParam(required = false) String recoverManName,
                                                              @ApiParam(value = "追回处理（0-退回供货单位；1-销毁）", required = false) @RequestParam(required = false) Integer handleMeasures,
                                                              @ApiParam(value = "按日期排序0-升序 1-降序", required = false) @RequestParam(required = false) Integer sortDate,
                                                              @ApiParam(value = "按单号排序0-升序 1-降序", required = false) @RequestParam(required = false) Integer sortCode) {
        Result<Page<RecoverRecordVO>> result = new Result<>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            Map<String, Object> map = new HashMap<>();
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("code", code);
            map.put("recoverManName", recoverManName);
            map.put("handleMeasures", handleMeasures);
            map.put("sortDate", sortDate);
            map.put("sortCode", sortCode);
            map.put("enterpriseId", loginUser.getEnterpriseId());

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, recoverRecordService.getRecoverRecordPage(page, map));
        } catch (Exception e) {
            logger.error("获取售后管理-追回管理-追回记录数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看售后管理-追回管理-追回计划明细", notes = "查看数据 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "追回计划ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/getRecoverPlanDetail/{id}", method = RequestMethod.GET)
    public Result<RecoverPlanVO> getRecoverPlanDetail(HttpServletRequest request,
                                                    @PathVariable("id") @NotNull Long id) {
        Result<RecoverPlanVO> result = new Result<>();
        try {
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, recoverPlanService.getRecoverPlanDetail(id));
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("查看售后管理-追回管理-追回计划数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看售后管理-追回管理-追回记录明细", notes = "查看数据 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "追回记录ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/getRecoverRecordDetail/{id}", method = RequestMethod.GET)
    public Result<RecoverRecordVO> getRecoverRecordDetail(HttpServletRequest request,
                                                    @PathVariable("id") @NotNull Long id) {
        Result<RecoverRecordVO> result = new Result<>();
        try {
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, recoverRecordService.getRecoverRecordDetail(id));
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("查看售后管理-追回管理-追回计划数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "添加售后管理-追回管理-添加追回计划", notes = "添加追回计划 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePlan", method = RequestMethod.POST)
    public Result savePlan(HttpSession session, @Valid @RequestBody RecoverPlanSaveOrupdateVO recoverPlan) {
        Result result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            recoverPlanService.save(recoverPlan, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "添加追回计划成功");
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("添加售后管理-追回管理-追回计划数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "添加售后管理-追回管理-添加追回记录", notes = "添加追回记录 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveRecord", method = RequestMethod.POST)
    public Result saveRecord(HttpSession session, @Valid @RequestBody RecoverRecordSaveOrupdateVO recoverRecord) {
        Result result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");

            recoverRecordService.save(recoverRecord, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "添加追回记录成功");
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("添加售后管理-追回管理-追回记录数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "更新售后管理-追回管理-修改追回计划", notes = "更新追回计划 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updatePlan", method = RequestMethod.POST)
    public Result updatePlan(HttpSession session, @Valid @RequestBody RecoverPlanSaveOrupdateVO recoverPlan) {
        Result result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            int upresult = recoverPlanService.update(recoverPlan, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, upresult == 1 ? "更新成功" : "更新数据不存在");
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("更新售后管理-追回管理-追回计划数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "更新售后管理-追回管理-修改追回记录", notes = "更新追回记录 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateRecord", method = RequestMethod.POST)
    public Result updateRecord(HttpSession session, @Valid @RequestBody RecoverRecordSaveOrupdateVO recoverRecord) {
        Result result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            int upresult = recoverRecordService.update(recoverRecord, userVO);

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, upresult == 1 ? "更新追回记录成功" : "更新数据不存在");
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("更新售后管理-追回管理-追回记录数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "根据ID删除追回计划/追回记录", notes = "根据ID删除追回计划/追回记录 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletePlanOrRecord", method = RequestMethod.GET)
    public Result removePlanOrRecord(HttpServletRequest request,
                             @ApiParam(value = "根据id删除", required = true) @RequestParam(name = "id", required = true) Long id,
                             @ApiParam(value = "状态：1-待追回；2-已追回", required = true) @RequestParam(name = "status", required = true) Integer status) {
        Result result = new Result<>();
        try {
            // 当前登录用户数据
            int delresult = 0;
            if (status == 1) {
                delresult = recoverPlanService.delete(id);
            } else if (status == 2) {
                delresult = recoverRecordService.delete(id);
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, delresult == 1 ? "删除成功" : "删除数据不存在");
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("删除售后管理-追回管理-追回计划/记录数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出excel-追回管理-待追回/已追回", notes = "导出数据 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/excelExport/{status}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void excelExport(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = "单据类型：1-待追回；2-已追回", required = true) @PathVariable("status") Integer status,
                            @ApiParam(value = "单据id", required = true) @PathVariable("id") Long id){
        String name = "药品追回";
        try {
            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            if (status == 1) {//待追回
                recoverPlanService.excelExport(output, id, userVO);
            } else if (status == 2) {//已追回
                recoverRecordService.excelExport(output, id, userVO);
            }
        } catch (Exception e) {
            logger.error("导出excel-追回管理-待追回/已追回:" + e.getMessage(), e);
            e.printStackTrace();
        }
    }

}
