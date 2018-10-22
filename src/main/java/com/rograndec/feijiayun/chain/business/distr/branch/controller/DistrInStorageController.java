package com.rograndec.feijiayun.chain.business.distr.branch.controller;

import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInCheckService;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInNoticeService;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInStorageService;
import com.rograndec.feijiayun.chain.business.distr.branch.valid.AddInstorageVOValid;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInstorageFormVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInstoragePageVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.GoodsInNoticeVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheck2DetailVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheck2ListVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.RequestParamForListVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.instorage.DistrInstorageVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.AddInstorageVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.valid.RequestParamValid;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author LEISU
 *
 */
@Api(value = "distr_in_storage",description = "配送管理-分店-配进入库-配进入库单接口服务")
@RestController
@RequestMapping("distr/in/storage")
@Validated
public class DistrInStorageController {

    @Autowired
    private DistrInStorageService distrInStorageService;

    @Autowired
    private DistrInCheckService distrInCheckService;

    private static final Log logger = LogFactory.getLog(DistrInStorageController.class);

    //#################################################已入库相关接口##########################################################
    //32---待入库   33--已入库
    @ApiOperation(value="按条件搜索分店--配进入库--已入库page信息", notes = "按条件搜索分店--配进入库--已入库page信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInstorageHasBeenPage", method = RequestMethod.GET)
    public Result<Page<List<DistrInstoragePageVO>>> getInstorageHasBeenPage(HttpServletRequest request,
                                                                        @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                        @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                        @ApiParam(value = "起始日期", required = false) @RequestParam(required=false) Date startTime,
                                                                        @ApiParam(value = "终止日期", required = false) @RequestParam(required=false) Date endTime,
                                                                        @ApiParam(value = "配货单位编码", required = false) @RequestParam(required=false) String distrUnitCode,
                                                                        @ApiParam(value = "配货单位名称", required = false) @RequestParam(required=false) String distrUnitName,
                                                                        @ApiParam(value = "配进入库单号", required = false) @RequestParam(required=false) String code, @ApiParam(value = "入库人员", required = false) @RequestParam(required=false) String storageManName,
                                                                        @ApiParam(value = "配货类型0-总部配送；3-分店间调剂；4-直调配送", required = false) @RequestParam(required=false) Integer distrType,
                                                                        @ApiParam(value = "按某一列排序(inDate或者code)", required = false) @RequestParam(required=false) String order,
                                                                        @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
        Result<Page<List<DistrInstoragePageVO>>> result = new Result<Page<List<DistrInstoragePageVO>>>();
        try{
            if(pageNo <= 0 || pageSize <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = distrInStorageService.getInstorageHasBeenPage(page,startTime,endTime,distrUnitCode,distrUnitName,code,storageManName,distrType,order,sort,loginUser.getEnterpriseId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("按条件搜索分店--配进入库--已入库page信息:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看按钮--查看分店配进入库（已入库）详细信息", notes = "查看按钮--查看分店配进入库（已入库）详细信息 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/getInstorageHasBeenForm", method=RequestMethod.GET)
    public Result<DistrInstorageFormVO> getInstorageHasBeenDeatil(HttpServletRequest request,
                                                              @ApiParam(value = "当前需要查看的ID", required = true) @RequestParam Long id){
        Result<DistrInstorageFormVO> result = new Result<DistrInstorageFormVO>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            DistrInstorageFormVO distrInstorageFormVO = distrInStorageService.getInstorageHasBeenDeatil(loginUser.getEnterpriseId(),id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInstorageFormVO);
        }catch(Exception e){
            logger.error("查看按钮--查看分店配进入库（已入库）详细信息:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "分店配进入库（已入库）导出", notes = "分店配进入库（已入库）导出 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/export/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的配进入库信息", required = true, dataType = "Long", paramType="path")
    public void exportMemberSuccessAndFail(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @PathVariable Long id) throws Exception {

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "分店配进入库单";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            DistrInstorageFormVO distrInstorageFormVO = distrInStorageService.getInstorageHasBeenDeatil(loginUser.getEnterpriseId(),id);
            distrInStorageService.export(output,distrInstorageFormVO,loginUser);
        }catch(Exception e){
            logger.error("导出配进入库信息错误:"+e.getMessage(),e);
        }

    }

    //#################################################待入库相关接口##########################################################
    @ApiOperation(value = "按条件搜索待入库列表Page信息", notes = "按条件搜索待入库列表Page信息 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/getDistrInCheckList", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "param", value = "请求参数", required = true, dataType="RequestParamForListVO")})
    public Result<Page<List<DistrInCheck2ListVO>>> getDistrInCheckList(HttpSession session, @RequestParamValid @RequestBody RequestParamForListVO param){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<DistrInCheck2ListVO>>> result = new Result<>();
        Page<List<DistrInCheck2ListVO>> page = new Page<>(param.getPageNo(),param.getPageSize());
        distrInStorageService.getDistrInCheckList(page,param,userVO.getEnterpriseId());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "选择商品(右上角的按钮)", notes = "选择商品(右上角的按钮) | 开发者 lei.su | 已完成")
    @RequestMapping(value = "/getGoodsByParam", method = RequestMethod.GET)
    public Result<Page<List<GoodsInNoticeVO>>> getGoodsByParam(HttpServletRequest request,
                                                         @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                         @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                         @ApiParam(value = "供货单位ID", required = true) @RequestParam Long supplierId,
                                                         @ApiParam(value = "搜索条件", required = false) @RequestParam(required = false) String param) {
        Result<Page<List<GoodsInNoticeVO>>> result = new Result<>();
        if(pageNo <= 0 || pageSize <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page page = new Page(pageNo, pageSize);
        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");
        page = distrInStorageService.getGoodsByParam(page,userVO,supplierId,param);
        result.setData(page);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }


    @ApiOperation(value = "查看待入库详情---查看按钮", notes = "查看待入库详情---查看按钮 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/getInCheckDetail/{id}", method=RequestMethod.GET)
    public Result<DistrInCheck2DetailVO> getInCheckDetail(HttpSession session, @PathVariable("id")Long id){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<DistrInCheck2DetailVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInCheckService.getInCheckDetail(userVO,id));
        return result;
    }

    @ApiOperation(value="待入库-入库按钮-form表单数据", notes = "待入库-入库按钮-form表单数据 | 开发者:苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getStayInstorageForm", method = RequestMethod.GET)
    @ResponseBody
    public Result<DistrInstorageFormVO> getStayInstorageForm(HttpServletRequest request,
                                                            @ApiParam(value = "验收单ID", required = true) @RequestParam(required=false) Long id){
        Result<DistrInstorageFormVO> result = new Result<DistrInstorageFormVO>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            DistrInstorageFormVO vo = distrInStorageService.queryStayInstorageFormByCheckId(id, loginUser);
            //测试方法规约
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
            logger.error("待入库-入库按钮-form表单数据错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="待入库-入库按钮-保存表单数据", notes = "待入库-入库按钮-保存表单数据 | 开发者:苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveStayInstorageForm", method = RequestMethod.POST)
    @ApiImplicitParam(name = "distrInstorageFormVO", value = "需要保存的入库实体", required = true, dataType = "DistrInstorageFormVO")
    @ResponseBody
    public Result<Object> saveStayInstorageForm(HttpServletRequest request,@RequestBody DistrInstorageFormVO distrInstorageFormVO){
        Result<Object> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInStorageService.saveStayInstorageForm(distrInstorageFormVO, loginUser));
        }catch(BusinessException e){
            logger.error("待入库-入库按钮-保存表单数据:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("待入库-入库按钮-保存表单数据:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    //直调配送新增

    @ApiOperation(value="新增入库单,调用配货单,要货计划单生成配进入库单", notes = "新增入库单,调用配货单,要货计划单生成配进入库单 | 开发者:孙腾 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveInstorage", method = RequestMethod.POST)
    @ApiImplicitParam(name = "addInstorageVO", value = "需要保存的入库实体", required = true, dataType = "AddInstorageVO")
    @ResponseBody
    public Result<Object> saveInstorage(HttpServletRequest request, @AddInstorageVOValid @RequestBody AddInstorageVO addInstorageVO) throws Exception {
        Result<Object> result = new Result<>();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInStorageService.saveInstorage(addInstorageVO, loginUser));
        return result;
    }

    @ApiOperation(value="要货计划调用生成入库的实体", notes = "要货计划调用生成入库的实体 | 开发者:孙腾 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInStorageInfoByPlan/{planId}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "planId", value = "要货计划调用生产入库的实体", required = true, paramType = "path")
    @ResponseBody
    public Result<DistrInstorageVO> getInStorageInfoByPlan(@PathVariable("planId")Long planId, @ApiIgnore UserVO userVO){
        Result<DistrInstorageVO> result = new Result<>();
        DistrInstorageVO info = distrInStorageService.getInStorageInfoByPlan(planId, userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,info);
        return result;
    }

    @ApiOperation(value="配进订单调用生成入库的实体", notes = "配进订单调用生成入库的实体 | 开发者:孙腾 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInStorageInfoByNotice/{noticeId}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "planId", value = "配进订单调用生成入库的实体", required = true, paramType = "path")
    @ResponseBody
    public Result<DistrInstorageVO> getInStorageInfoByNotice(@PathVariable("noticeId")Long noticeId, @ApiIgnore UserVO userVO){
        Result<DistrInstorageVO> result = new Result<>();
        DistrInstorageVO info = distrInStorageService.getInStorageInfoByNoticeId(noticeId, userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,info);
        return result;
    }

}
