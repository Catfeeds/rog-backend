package com.rograndec.feijiayun.chain.business.storage.chgoods.controller;

import com.rograndec.feijiayun.chain.business.storage.chgoods.service.ChGoodsClearService;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author zeshi.sun
 *
 */
@Api(value = "storage_chgoods_clear",description = "储存管理-中药管理-中药饮片清斗")
@RestController
@RequestMapping("storage/chgoods/clear")
@Validated
public class ChGoodsClearController {

    private static final Log logger = LogFactory.getLog(ChGoodsClearController.class);

    @Autowired
    ChGoodsClearService chGoodsClearService;
	
	@ApiOperation(value = "查询中药清斗列表", notes = "查询中药清斗单列表 | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value="/getChGoodsClearOrderList", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result<Page<List<ChGoodsClearOrderListVO>>> getChGoodsClearOrderList(HttpServletRequest request,
                                                                              @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                              @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                              @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
                                                                              @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
                                                                              @ApiParam(value = "清斗单号", required = false) @RequestParam(required = false) String code,
                                                                              @ApiParam(value = "清斗人员", required = false) @RequestParam(required = false) String clearManName,
                                                                              @ApiParam(value = "复核人员", required = false) @RequestParam(required = false) String auditManName,
                                                                              @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                                                              @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<List<ChGoodsClearOrderListVO>>> result = new Result<Page<List<ChGoodsClearOrderListVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<ChGoodsClearOrderListVO> purchaseCheckVoList = chGoodsClearService
                    .getChGoodsClearOrderList(pageNo, pageSize, loginUser.getEnterpriseId(), page, startTime, endTime, code, clearManName, auditManName, orderName, orderType);
            page.setResult(purchaseCheckVoList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("查询中药清斗列表错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "查询中药清斗明细列表", notes = "根据中药清斗ID查询中药清斗明细列表 | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value="/getChGoodsClearOrderDtlList", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result<ChGoodsClearOrderDtlListVO> getChGoodsClearOrderDtlList(HttpServletRequest request,
                                                                          @ApiParam(value = "查看中药清斗列表ID", required = true) @RequestParam Long id) {

        Result<ChGoodsClearOrderDtlListVO> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, chGoodsClearService.getChGoodsClearOrderDtlList(loginUser.getEnterpriseId(),id));
        } catch (Exception e) {
            logger.error("查询中药装斗明细列表错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出Excel", notes = "按照模版将中药清斗单导出至Excel | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value="/exportExcel/{id}",method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "当前需要查看的中药饮片清斗单据ID", required = true, dataType = "Long", paramType="path")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,@PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"中药饮片清斗";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            ChGoodsClearOrderDtlListVO chGoodsClearOrderDtlListVO = chGoodsClearService.getChGoodsClearOrderDtlList(loginUser.getEnterpriseId(),id);
            chGoodsClearService.exportExcel(output,chGoodsClearOrderDtlListVO,loginUser);
        }catch(Exception e){
            logger.error("导出中药饮片清斗错误:"+e.getMessage(),e);
        }
    }

    @ApiOperation(value = "保存中药清斗单", notes = "保存中药清斗单 | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value="/saveChGoodsClearOrder", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result<String> saveChGoodsClearOrder(HttpServletRequest request,
                                               @ApiParam(value = "保存中药清斗单", required = true) @RequestBody SaveChGoodsClearOrderVO saveChGoodsClearOrderVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            chGoodsClearService.saveChGoodsClearOrder(loginUser, saveChGoodsClearOrderVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存中药清斗单成功");

        }  catch (BusinessException e) {
            logger.error("保存中药清斗单错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("保存中药清斗单错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

}
