package com.rograndec.feijiayun.chain.business.distr.branch.controller;

import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInReceiveService;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReceiveDetailSaveVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReceiveSaveVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReceiveVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lizhongyi
 *
 */
@Api(value = "distr_in_receive",description = "配送管理-分店-配进入库-配进收货单接口服务")
@RestController
@RequestMapping("distr/in/receive")
@Validated
public class DistrInReceiveController {

    private static final Log logger = LogFactory.getLog(DistrInReceiveController.class);

    @Autowired
    DistrInReceiveService distrInReceiveService;

	@ApiOperation(value = "查询配进收货单列表", notes = "根据状态查询配进收货单列表 | 开发者:马东 | 已完成")
    @RequestMapping(value="/getDistrInReceiveOrderList", method=RequestMethod.GET)
    public Result<Page<List<DistrInReceiveVO>>> getDistrInReceiveOrderList(HttpServletRequest request,
           @ApiParam(value = "开始日期",name = "startDate") @RequestParam(required = false) Date startDate,
           @ApiParam(value = "结束日期",name = "endDate") @RequestParam(required = false) Date endDate,
           @ApiParam(value = "配货单位编码",name = "distrUnitCode") @RequestParam(required = false) String distrUnitCode,
           @ApiParam(value = "配货货单位名称",name = "distrUnitName") @RequestParam(required = false) String distrUnitName,
           @ApiParam(value = "配后退回收货单号",name = "code") @RequestParam(required = false) String code,
           @ApiParam(value = "配货类型(0-总部配送；3-分店间调剂；4-直调配送)",name = "distrType") @RequestParam(required = false) Integer distrType,
           @ApiParam(value = "收货状态(已收货(待验收):31,已验收:32)",name = "status") @RequestParam(required = false) Integer status,
           @ApiParam(value = "收货人员1",name = "receiverName") @RequestParam(required = false) String receiverName,
           @ApiParam(value = "收货人员2",name = "secondReceiverName") @RequestParam(required = false) String secondReceiverName,
           @ApiParam(value = "待排序字段名",name = "orderName") @RequestParam(required = false) String orderName,
           @ApiParam(value = "待排序方式ASC/DESC",name = "orderType") @RequestParam(required = false) String orderType,
           @ApiParam(value = "页码",name = "pageNo",required = true) @RequestParam Integer pageNo,
           @ApiParam(value = "页容量",name = "pageSize",required = true) @RequestParam Integer pageSize){
        Result<Page<List<DistrInReceiveVO>>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrInReceiveService.getDistrInReceiveOrderList(loginUser,startDate,endDate,distrUnitCode,distrUnitName,code,distrType,status,receiverName,secondReceiverName,orderName,orderType,pageNo,pageSize));
        }catch(Exception e){
            logger.error("查询配进收货单列表失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }

	@ApiOperation(value = "查询配进收货单明细列表", notes = "根据配进收货单ID查询配进收货单列表 | 开发者:马东 | 已完成")
    @RequestMapping(value="/getDistrInReceiveOrderDtlList", method=RequestMethod.GET)
    public Result<DistrInReceiveVO> getDistrInReceiveOrderDtlList(HttpServletRequest request,
           @ApiParam(value = "收货单总单id",name = "id",required = true) @RequestParam Long id){
        Result<DistrInReceiveVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrInReceiveService.getDistrInReceiveOrderDtlList(loginUser,id));
        }catch(Exception e){
            logger.error("查询配进收货单明细列表失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

	@ApiOperation(value = "保存配进收货单", notes = "保存配进收货单 | 开发者:马东 | 已完成")
    @RequestMapping(value="/saveDistrInReceiveOrder", method=RequestMethod.POST)
    public Result<String> saveDistrInReceiveOrder(HttpServletRequest request,
           @ApiParam(value = "待保存的收货单据",required = true) @RequestBody DistrInReceiveSaveVO distrInReceiveSaveVO){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            for(DistrInReceiveDetailSaveVO distrInReceiveDetailSaveVO :distrInReceiveSaveVO.getDistrInReceiveDetailSaveVOS()){
                if((distrInReceiveDetailSaveVO.getReceiveQuantity().add(distrInReceiveDetailSaveVO.getRefuseQuantity()).compareTo(distrInReceiveDetailSaveVO.getArrivalQuantity())) != 0){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "收货数量+拒收数量不等于到货数量!");
                    return result;
                }
            }
            if(!distrInReceiveService.checkReceived(loginUser,distrInReceiveSaveVO)){
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"该单据已收货完成!~");
                return result;
            }
            String resultInfo = distrInReceiveService.saveDistrInReceiveOrder(loginUser,distrInReceiveSaveVO);
            if(resultInfo.equals("")) {
                result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存配进收货单成功");
            }else {
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(), resultInfo);
            }
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
        }catch(Exception e){
            logger.error("保存配进收货单失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }

    @ApiOperation(value = "待保存配进收货单接口", notes = "待保存配进收货单接口 | 开发者:马东 | 已完成")
    @RequestMapping(value = "/showWillSaveList", method=RequestMethod.GET)
    public Result<DistrInReceiveVO> showWillSaveList(HttpServletRequest request,
           @ApiParam(value = "配进订单ID",required = true) @RequestParam Long id){
        Result<DistrInReceiveVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrInReceiveService.showWillSaveList(loginUser,id));
        }catch(Exception e){
            logger.error("查询待保存配进收货单失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

	@ApiOperation(value = "导出Excel", notes = "按照模版将配进收货单导出至Excel | 开发者:马东 | 已完成")
    @RequestMapping(value="/exportExcel",method= RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
           @ApiParam(value = "收货单id",name = "id",required = true) @RequestParam Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "配进收货单-已收货";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            distrInReceiveService.exportExcel(output,id,loginUser);
        }catch(Exception e){
            logger.error("导出门店配进收货单信息错误:"+e.getMessage(),e);
        }

    }

}
