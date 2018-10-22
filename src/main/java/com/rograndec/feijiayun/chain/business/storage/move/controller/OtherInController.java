package com.rograndec.feijiayun.chain.business.storage.move.controller;

import com.rograndec.feijiayun.chain.business.storage.move.service.OtherInService;
import com.rograndec.feijiayun.chain.business.storage.move.vo.*;
import com.rograndec.feijiayun.chain.common.*;
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
 * @author sulei
 *
 */
@Api(value = "storage_move_otherIn",description = "储存管理-货物移动-其它入库")
@RestController
@RequestMapping("storage/move/otherIn")
@Validated
public class OtherInController {

    private static final Log logger = LogFactory.getLog(OtherInController.class);

    @Autowired
    private OtherInService otherInService;

    @ApiOperation(value="按条件搜索其他入库信息", notes = "按条件搜索其他入库信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getOtherInPage", method = RequestMethod.GET)
    public Result<Page<List<OtherInPageVO>>> getOtherInPage(HttpServletRequest request,
                                                              @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                              @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                              @ApiParam(value = "起始日期", required = false) @RequestParam(required=false) Date startTime,
                                                              @ApiParam(value = "终止日期", required = false) @RequestParam(required=false) Date endTime,
                                                              @ApiParam(value = "入库单号", required = false) @RequestParam(required=false) String code,
                                                              @ApiParam(value = "入库人员", required = false) @RequestParam(required=false) String inManName,
                                                              @ApiParam(value = "入库类型", required = false) @RequestParam(required=false) Integer inType,
                                                              @ApiParam(value = "按某一列排序(inDate或者code)", required = false) @RequestParam(required=false) String order,
                                                              @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort,
                                                            @RequestParam(required = false)
                                                                @ApiParam(name = "approveStatus", value = "审核状态（查询全部不需要传值;0-待审核；1-已完成 2-审核被驳回;）", required = false)
                                                                        Integer approveStatus){
        Result<Page<List<OtherInPageVO>>> result = new Result<Page<List<OtherInPageVO>>>();
        try{
            if(pageNo <= 0 || pageSize <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = otherInService.getOtherInPage(page,startTime,endTime,code,inManName,inType,order,sort,loginUser.getEnterpriseId(),approveStatus);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("按条件搜索货位移动信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看按钮--查看其他入库详细信息", notes = "查看按钮--查看其他入库详细信息 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/getOtherInDetail", method=RequestMethod.GET)
    public Result<OtherInFormVO> getOtherInDetail(HttpServletRequest request,
                                                  @ApiParam(value = "当前需要查看的ID", required = true) @RequestParam Long id){
        Result<OtherInFormVO> result = new Result<OtherInFormVO>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            OtherInFormVO otherInFormVO = otherInService.getOtherInDetail(loginUser.getEnterpriseId(),id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,otherInFormVO);
        }catch(Exception e){
            logger.error("查看按钮--查看其他入库详细信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "根据来源单位查找出对应的来源单位详细名称", notes = "根据来源单位查找出对应的来源单位详细名称 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/getSrcUnit", method=RequestMethod.GET)
    public Result<List<SelectBeanWithCode>> getExcessSaleGoodsList(HttpServletRequest request,
                                                                         @ApiParam(value = "0-部门；1-总部；2-门店；3-供货单位", required = true) @RequestParam Integer srcUnitType){
        Result<List<SelectBeanWithCode>> result = new Result<List<SelectBeanWithCode>>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<SelectBeanWithCode> list = otherInService.getSrcUnit(loginUser,srcUnitType);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
        }catch(Exception e){
            logger.error("查询新增时的移动人员错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询新增商品选择列表", notes = "查询新增商品选择列表 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/getGoodsList", method=RequestMethod.GET)
    public Result<List<OtherInDetailVO>> getGoodsList( @ApiParam(value = "当前情况下的商品列别--新增时传add---调用超量销售产品传over[这个比较复杂--等逻辑理通]", required = true) @RequestParam String operation,
                                                      @ApiParam(name = "param", value = "检索条件:商品编码/条形码/检索码/名称/通用名称/批准文号", required = false) @RequestParam(required = false)String param,
                                                      @ApiIgnore UserVO userVO){
        Long startTime = new Date().getTime();

        Result<List<OtherInDetailVO>> result = new Result<List<OtherInDetailVO>>();
        try{
            if ("add".equals(operation) || "over".equals(operation)){
                List<OtherInDetailVO> list = otherInService.getGoodsList(userVO,operation,param);
                result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
            }else {
                result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS,"输入操作信息有误");
            }
        }catch(Exception e){
            logger.error("查询新增商品选择列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }

        Long endTime = new Date().getTime();

        System.out.println("t==================================getGoodsList==================================" + (endTime-startTime));
        return result;
    }

    @ApiOperation(value = "新增其他入库信息", notes = "新增其他入库信息 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/insertOtherIn", method=RequestMethod.POST)
    @ApiImplicitParam(name = "otherInFormVO", value = "需要保存的其他入库实体", required = true, dataType = "OtherInFormVO")
    public Result<Object> insertOtherIn(HttpServletRequest request,@RequestBody OtherInFormVO otherInFormVO){
        Result<Object> result = new Result<Object>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            otherInService.insertOtherIn(loginUser,otherInFormVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"添加成功");
        }catch(BusinessException e){
            logger.error("新增其他入库信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("新增其他入库信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	

	@ApiOperation(value = "导出其他入库Excel", notes = "按照模版将其它入库单导出至Excel | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/exportExcel/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的其他入库信息", required = true, dataType = "Long", paramType="path")
    public void exportMemberSuccessAndFail(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @PathVariable Long id) throws Exception {
        try {
            OutputStream output = null;
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "其他入库";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            OtherInFormVO otherInFormVO = otherInService.getOtherInDetail(loginUser.getEnterpriseId(),id);
            otherInService.export(output,otherInFormVO,loginUser);
        }catch(Exception e){
            logger.error("导出其他入库信息错误:"+e.getMessage(),e);
        }
    }

}
