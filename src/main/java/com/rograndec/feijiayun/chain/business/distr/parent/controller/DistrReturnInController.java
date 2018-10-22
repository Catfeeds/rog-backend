package com.rograndec.feijiayun.chain.business.distr.parent.controller;

import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrReturnInService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.*;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.transfer.DistrReturnCheckListParam;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author zhaiwei
 *
 */
@Api(value = "distr_return_in",description = "配送管理-总部-配后退回入库单接口服务")
@RestController
@RequestMapping("distr/return/in")
@Validated
public class DistrReturnInController {

    @Autowired
    private DistrReturnInService distrReturnInService;


//	@ApiOperation(value = "查询配后退回验收单列表", notes = "查询待入库的配后退回验收单列表 | 开发者 翟伟 | 已完成")
//    @RequestMapping(value="/getDistrReturnCheckOrderList", method=RequestMethod.GET)
//    public Result<Page<List<DistrReturnCheck>>>  getDistrReturnCheckOrderList(
//            HttpSession session,
//            @ApiParam(value = "页码", required = true) @RequestParam Integer pageNo,
//            @ApiParam(value = "每页显示的记录数", required = true) @RequestParam Integer pageSize,
//            @ApiParam(value = "筛选条件开始时间", required = false) @RequestParam(required=false) Date startTime,
//            @ApiParam(value = "筛选条件截止时间  ", required = false) @RequestParam(required=false) Date endTime,
//            @ApiParam(value = "要货单位编码  ", required = false) @RequestParam(required=false) String  requestUnitCode,
//            @ApiParam(value = "要货单位名称  ", required = false) @RequestParam(required=false) String requestUnitName,
//            @ApiParam(value = "配后退回验收单号  ", required = false) @RequestParam(required=false) String code,
//            @ApiParam(value = "配货类型（0-总部配送；1-分店间调剂；2-直调配送  ", required = false) @RequestParam(required=false) Integer distrType,
//            @ApiParam(value = "验收人员名称1  ", required = false) @RequestParam(required=false) String checkerName,
//            @ApiParam(value = "第二验收人员名称  ", required = false) @RequestParam(required=false) String secondCheckerName
//    ){
//        Result<Page<List<DistrReturnCheck>>> result = new Result<>();
//        DistrReturnCheckListParam distrReturnCheckListParam = new DistrReturnCheckListParam();
//        UserVO userVO = (UserVO) session.getAttribute("user");
//        distrReturnCheckListParam.setUserVO(userVO);
//        distrReturnCheckListParam.setRequestUnitCode(requestUnitCode);
//        distrReturnCheckListParam.setRequestUnitName(requestUnitName);
//        distrReturnCheckListParam.setCode(code);
//        distrReturnCheckListParam.setDistrType(distrType);
//        distrReturnCheckListParam.setCheckerName(checkerName);
//        distrReturnCheckListParam.setSecondCheckerName(secondCheckerName);
//        distrReturnCheckListParam.setStartTime(startTime);
//        distrReturnCheckListParam.setEndTime(endTime);
//
//        Page page = new Page(pageNo, pageSize);
//        List<DistrReturnCheck> distrReturnChecks = distrReturnInService.getDistrReturnChecks(distrReturnCheckListParam, page);
//        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
//        return result;
//    }
//
//	@ApiOperation(value = "查询配后退回验收单明细列表", notes = "根据配后退回单ID查询配后退回验收单明细列表 | 开发者 翟伟 | 已完成")
//    @RequestMapping(value="/getDistrReturnCheckOrderDtlList", method=RequestMethod.GET)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "配后退回验收单id"
//                    , required = true, paramType="path")
//    })
//    public Result<DistrReturnCheck4ReturnInVO> getDistrReturnCheckOrderDtlList(
//            @PathVariable("id") Long id
//    ){
//
//        Result<DistrReturnCheck4ReturnInVO> result = new Result<>();
//        DistrReturnCheck4ReturnInVO distrReturnCheck4ReturnInVO = distrReturnInService.getDistrReturnCheck4ReturnInVO(id);
//        result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrReturnCheck4ReturnInVO);
//        return result;
//    }
	
	@ApiOperation(value = "查询配后退回入库单列表", notes = "查询配后退回入库单列表 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/getDistrReturnInOrderList", method=RequestMethod.GET)
    public Result<Page<DistrReturnInPageTotalVO>> getDistrReturnInOrderList(
            HttpSession session,
            @ApiParam(value = "页码", required = false) @RequestParam(required=false) Integer pageNo,
            @ApiParam(value = "每页显示的记录数", required = false) @RequestParam(required=false) Integer pageSize,
            @ApiParam(value = "筛选条件开始时间", required = false) @RequestParam(required=false) Date startTime,
            @ApiParam(value = "筛选条件截止时间  ", required = false) @RequestParam(required=false) Date endTime,
            @ApiParam(value = "要货单位编码  ", required = false) @RequestParam(required=false) String  requestUnitCode,
            @ApiParam(value = "要货单位名称  ", required = false) @RequestParam(required=false) String requestUnitName,
            @ApiParam(value = "配后退回入库单号  ", required = false) @RequestParam(required=false) String code,
            @ApiParam(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送  ", required = false) @RequestParam(required=false) Integer distrType,
            @ApiParam(value = "入库人员  ", required = false) @RequestParam(required=false) String checkerName,
            @ApiParam(value = "按某一列排序", required = false)
            @RequestParam(required=false) String orderName,
            @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false)
            @RequestParam(required=false) String orderType
    ){

        Result<Page<DistrReturnInPageTotalVO>> result = new Result<>();

        DistrReturnCheckListParam distrReturnCheckListParam = new DistrReturnCheckListParam();
        UserVO userVO = (UserVO) session.getAttribute("user");
        distrReturnCheckListParam.setUserVO(userVO);
        distrReturnCheckListParam.setRequestUnitCode(requestUnitCode);
        distrReturnCheckListParam.setRequestUnitName(requestUnitName);
        distrReturnCheckListParam.setCode(code);
        distrReturnCheckListParam.setDistrType(distrType);
        distrReturnCheckListParam.setCheckerName(checkerName);
        distrReturnCheckListParam.setStartTime(startTime);
        distrReturnCheckListParam.setEndTime(endTime);
        distrReturnCheckListParam.setOrderName(orderName);
        distrReturnCheckListParam.setOrderType(orderType);
        Page page = new Page(pageNo, pageSize);
        List<DistrReturnInPageVO> distrReturnInPageVOs = distrReturnInService.getDistrReturnInPageVOs(distrReturnCheckListParam, page);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

        return result;
    }
	
	@ApiOperation(value = "查询配后退回入库单明细", notes = "根据配后退回入库单ID查询配后退回入库单明细 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/getDistrReturnInOrderDtlList/{id}", method=RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "配后退回入库单id"
                    , required = true, paramType="path")
    })
    public Result<DistrReturnInPageVO> getDistrReturnInOrderDtlList(
            @PathVariable("id") Long id
    ){
        Result<DistrReturnInPageVO> result = new Result<>();
        DistrReturnInPageVO distrReturnInPageVO = distrReturnInService.getDistrReturnInPageVO(id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrReturnInPageVO);
        return result;
    }

    @ApiOperation(value = "初始化配后退回入库数据", notes = "根据配后退回校验单ID初始化配后退回入库数据 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/initDistrReturnInOrderDtlList/{id}", method=RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "配后退回校验单id"
                    , required = true, paramType="path")
    })
    public Result<DistrReturnInPageVO> initDistrReturnInOrderDtlList(
            HttpSession session,
            @PathVariable("id") Long id
    ){
        Result<DistrReturnInPageVO> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        DistrReturnInPageVO distrReturnInPageVO = distrReturnInService.getDistrReturnInPageVO4Check(userVO,id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrReturnInPageVO);
        return result;
    }
	
	@ApiOperation(value = "保存配后退回入库单", notes = "保存配后退回入库单 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/saveDistrReturnInOrder", method=RequestMethod.POST)
    public Result saveDistrReturnReceiveOrder(
            HttpSession session,
            @ApiParam(value = "保存配后退回入库单", required = true) @RequestBody DistrReturnInFormVO distrReturnInFormVO) throws Exception {
        Result result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        distrReturnInService.saveDistrReturnIn(userVO,distrReturnInFormVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }
	

	@ApiOperation(value = "导出Excel", notes = "按照模版将配后退入库单信息导出至Excel | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "配后退回入库单id"
                    , required = true, paramType="path")
    })
    public void exportExcel(HttpServletResponse response, @PathVariable("id") Long id) throws IOException {


        String name = "配后退回入库单";
        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        // 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");

        distrReturnInService.exportDetail(output, id);
        output.close();
        output.flush();
    }


    @ApiOperation(value = "调用配后退回", notes = "配进入库调用配后退回通知 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value="/getDistrReturnNoticeList", method=RequestMethod.GET)
    public Result<Page<DistrReturnNoticePageVO>> getDistrReturnNoticeList(HttpServletRequest request,
                                                                          @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                          @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                          @ApiParam(value = "起始时间", required = false) @RequestParam(required = false) Date startTime,
                                                                          @ApiParam(value = "终止时间", required = false) @RequestParam(required = false) Date endTime,
                                                                          @ApiParam(value = "按某一列排序(noticeDate或者code)", required = false) @RequestParam(required=false) String order,
                                                                          @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort) {
        Result<Page<DistrReturnNoticePageVO>> result = new Result<>();
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = distrReturnInService.getDistrReturnNoticeList(page, loginUser.getEnterpriseId(), startTime, endTime,order,sort);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        return result;
    }


    @ApiOperation(value = "查询配后退回明细列表", notes = "根据采购入库单ID查询采购入库单明细列表 | 开发者 杜东阳 | 已联调")
    @ApiImplicitParam(name = "id", value = "配后退回ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/getDistrReturnNoticeDtlList/{id}", method = RequestMethod.GET)
    public Result<Page<List<DistrReturnNoticeDtlVO>>> getDistrReturnNoticeDtlList(HttpServletRequest request,

                                                                            @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                            @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                            @PathVariable("id") @NotNull Long id) {
        Result<Page<List<DistrReturnNoticeDtlVO>>> result = new Result<>();

        if (pageNo <= 0 || pageSize <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }

        Page page = new Page(pageNo, pageSize);
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        page =  distrReturnInService.getDistrReturnNoticeDtlList(loginUser,id,page);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        return result;
    }


    @ApiOperation(value = "初始化一键入库数据", notes = "根据采购入库单ID查询采购入库单明细列表 | 开发者 杜东阳 | 已联调")
    @ApiImplicitParam(name = "id", value = "配后退回ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/initReturnInFromNoticeVO/{id}", method = RequestMethod.GET)
    public Result<DistrReturnInFromNoticeVO> initReturnInFromNoticeVO(HttpServletRequest request,
                                                                         @PathVariable("id") @NotNull Long id) {
        Result<DistrReturnInFromNoticeVO> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrReturnInService.initReturnInFromNoticeVO(loginUser,id));
        return result;
    }



    @ApiOperation(value = "调用配后退回保存", notes = "调用配后退回保存 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value="/saveDistrReturnInFromNotice", method=RequestMethod.POST)
    public Result<List<String>> saveDistrReturnInFromNotice(HttpServletRequest request,
                                                      @ApiParam(value = "调用配后退回保存", required = true) @RequestBody DistrReturnInFromNoticeVO distrReturnInFromNoticeVO) throws Exception {
        Result<List<String>> result = new Result<>();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");


        result.setBizCodeSuccessInfo(SysCode.SUCCESS,  distrReturnInService.saveDistrReturnInFromNotice(loginUser,distrReturnInFromNoticeVO));

        return result;
    }

}
