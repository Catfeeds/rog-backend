package com.rograndec.feijiayun.chain.business.distr.parent.controller;

import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrReturnReceiveService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnReceiveDetailSaveVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnReceiveSaveVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnReceiveVO;
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
 * @author lizhongyi
 *
 */
@Api(value = "distr_return_receive",description = "配送管理-总部-配后退货-配进收货单接口服务")
@RestController
@RequestMapping("distr/return/receive")
@Validated
public class DistrReturnReceiveController {

    @Autowired
    DistrReturnReceiveService distrReturnReceiveService;

    private static final Log logger = LogFactory.getLog(DistrReturnReceiveController.class);

	@ApiOperation(value = "查询配后退回收货单列表", notes = "查询已收货的配后退回收货单列表 | 开发者:马东 | 已完成")
    @RequestMapping(value="/getDistrReturnReceiveOrderList", method=RequestMethod.GET)
    public Result<Page<List<DistrReturnReceiveVO>>> getDistrReturnReceiveOrderList(HttpServletRequest request,
           @ApiParam(value = "开始日期",name = "startDate") @RequestParam(required = false) Date startDate,
           @ApiParam(value = "结束日期",name = "endDate") @RequestParam(required = false) Date endDate,
           @ApiParam(value = "要货单位编码",name = "requestUnitCode") @RequestParam(required = false) String requestUnitCode,
           @ApiParam(value = "要货单位名称",name = "requestUnitName") @RequestParam(required = false) String requestUnitName,
           @ApiParam(value = "配后退回收货单号",name = "code") @RequestParam(required = false) String code,
           @ApiParam(value = "配货类型(0-总部配送；3-分店间调剂；4-直调配送)",name = "distrType") @RequestParam(required = false) Integer distrType,
           @ApiParam(value = "收货状态(已收货(待验收):31,已验收:32)",name = "status") @RequestParam(required = false) Integer status,
           @ApiParam(value = "收货人员1",name = "receiverName") @RequestParam(required = false) String receiverName,
           @ApiParam(value = "收货人员2",name = "secondReceiverName") @RequestParam(required = false) String secondReceiverName,
           @ApiParam(value = "待排序字段名",name = "orderName") @RequestParam(required = false) String orderName,
           @ApiParam(value = "待排序方式ASC/DESC",name = "orderType") @RequestParam(required = false) String orderType,
           @ApiParam(value = "页码",name = "pageNo",required = true) @RequestParam Integer pageNo,
           @ApiParam(value = "页容量",name = "pageSize",required = true) @RequestParam Integer pageSize){
        Result<Page<List<DistrReturnReceiveVO>>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrReturnReceiveService.getDistrReturnReceiveOrderList(loginUser,startDate,endDate,requestUnitCode,requestUnitName,code,distrType,status,receiverName,secondReceiverName,orderName,orderType,pageNo,pageSize));
        }catch(Exception e){
            logger.error("查询配后退回收货单列表失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "查询配后退回收货单明细列表", notes = "根据配后退回单收货单ID查询配后退回收货单明细列表 | 开发者:马东 | 已完成")
    @RequestMapping(value="/getDistrReturnReceiveOrderDtlList", method=RequestMethod.GET)
    public Result<DistrReturnReceiveVO> getDistrReturnReceiveOrderDtlList(HttpServletRequest request,
           @ApiParam(value = "收货单总单ID",name = "receiveId",required = true) @RequestParam Long receiveId){
        Result<DistrReturnReceiveVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrReturnReceiveService.getDistrReturnReceiveOrderDtlList(loginUser,receiveId));
        }catch(Exception e){
            logger.error("查询配后退回收货单列表失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }

	@ApiOperation(value = "(收货)保存配后退回收货单", notes = "保存配后退回收货单 | 开发者:马东 | 已完成")
    @RequestMapping(value="/saveDistrReturnReceiveOrder", method=RequestMethod.POST)
    public Result<String> saveDistrReturnReceiveOrder(HttpServletRequest request,
                                                      @ApiParam(value = "待保存的收货单据",required = true) @RequestBody DistrReturnReceiveSaveVO distrReturnReceiveSaveVO,
                                                      @ApiIgnore UserVO userVO
                                                      ){
        Result<String> result = new Result<>();
        try{
            for(DistrReturnReceiveDetailSaveVO distrReturnReceiveDetailSaveVO : distrReturnReceiveSaveVO.getDistrReturnReceiveDetailSaveVOS()){
                if((distrReturnReceiveDetailSaveVO.getRefuseQuantity().add(distrReturnReceiveDetailSaveVO.getReceiveQuantity())).compareTo(distrReturnReceiveDetailSaveVO.getArrivalQuantity())!=0){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "收货数量+拒收数量不等于到货数量!");
                    return result;
                }
            }
            if(!distrReturnReceiveService.checkReceived(userVO,distrReturnReceiveSaveVO)){
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"该单据已收货完成!~");
                return result;
            }
            distrReturnReceiveService.saveDistrReturnReceiveOrder(userVO,distrReturnReceiveSaveVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
        }catch(Exception e){
            logger.error("保存配后退回收货单失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "待保存配后退回收货单接口", notes = "待保存配后退回收货单接口 | 开发者:马东 | 已完成")
    @RequestMapping(value = "/showWillSaveList", method=RequestMethod.GET)
    public Result<DistrReturnReceiveVO> showWillSaveList(HttpServletRequest request,
           @ApiParam(value = "配后退回订单ID",required = true) @RequestParam Long id){
        Result<DistrReturnReceiveVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrReturnReceiveService.showWillSaveList(loginUser,id));
        }catch(Exception e){
            logger.error("待保存配后退回收货单失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

	@ApiOperation(value = "导出Excel", notes = "按照模版将配后退回收货单信息导出至Excel | 开发者:马东 | 已完成")
    @RequestMapping(value="/exportExcel", method=RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
           @ApiParam(value = "配后退回收货单id",name = "id",required = true) @RequestParam Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = "配后退回收货单-已收货";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            distrReturnReceiveService.exportExcel(output,id,loginUser);
        }catch(Exception e){
            logger.error("导出总部配后退回收货单信息错误:"+e.getMessage(),e);
        }

    }

    @ApiOperation(value = "特管药品判断", notes = "特管药品判断 | 开发者:马东 | 已完成")
    @RequestMapping(value = "/isSpecialDrug", method=RequestMethod.GET)
    public Result<Boolean> isSpecialDrug(HttpServletRequest request,
           @ApiParam(value = "配后退回订单ID",required = true) @RequestParam Long id){
        Result<Boolean> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrReturnReceiveService.isSpecialDrug(loginUser,id));
        }catch(Exception e){
            logger.error("特管药品判断失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

}
