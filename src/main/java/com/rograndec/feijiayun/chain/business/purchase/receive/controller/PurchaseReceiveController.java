package com.rograndec.feijiayun.chain.business.purchase.receive.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderOtherRequestVO;
import com.rograndec.feijiayun.chain.business.purchase.receive.service.PurchaseReceiveService;
import com.rograndec.feijiayun.chain.business.purchase.receive.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
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
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by madong on 2017/9/13.
 */

@Api(value = "purchase_receive", description = "采购管理-采购收货服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/purchase/receive")
@Validated
public class PurchaseReceiveController {
    private static final Log logger = LogFactory.getLog(PurchaseReceiveController.class);
    @Autowired
    PurchaseReceiveService purchaseReceiveService;

    @ApiOperation(value="获收货单据", notes = "获收货单据 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getWillReceive", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page> getWillReceive(HttpServletRequest request,
           @ApiParam(value = "分页参数,页码",required = true) @RequestParam Integer pageNo,
           @ApiParam(value = "分页参数,页容量",required = true) @RequestParam Integer pageSize,
           @ApiParam(value = "收货状态,0待收货,1已收货",required = true) @RequestParam Integer isReceive,
           @ApiParam(value = "待排序字段") @RequestParam(required = false) String orderName,
           @ApiParam(value = "排序方式ASC/DESC") @RequestParam(required = false) String orderType,
           @ApiParam(value = "搜索起始日期") @RequestParam(required = false) String startTime,
           @ApiParam(value = "搜索截止日期") @RequestParam(required = false) String endTime,
           @ApiParam(value = "供货单位编码") @RequestParam(required = false) String supplierCode,
           @ApiParam(value = "供货单位名称") @RequestParam(required = false) String supplierName,
           @ApiParam(value = "收货单号") @RequestParam(required = false) String code,
           @ApiParam(value = "收货人员") @RequestParam(required = false) String receiverName){

        Result<Page> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseReceiveService
                    .getReceive(loginUser,pageNo,isReceive,pageSize,orderName,orderType,
                            startTime,endTime,supplierCode,supplierName,code,receiverName));
        }catch(Exception e){
            logger.error("获收货单据失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取待收货单据明细", notes = "获取待收货单据明细 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getOrderDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result<PurchaseOrderRequestVO> getOrderDetail(HttpServletRequest request,
                                                         @ApiParam(value = "订单id",required = true) @RequestParam Long id,
                                                         @ApiIgnore UserVO userVO){
        Result<PurchaseOrderRequestVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseReceiveService.getOrderDetail(userVO,id));
        }catch(Exception e){
            logger.error("获取待收货单据明细失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取待收货配送和结算信息", notes = "获取待收货配送和结算信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getOrderSet", method = RequestMethod.GET)
    @ResponseBody
    public Result<PurchaseOrderOtherRequestVO> getOrderSet(HttpServletRequest request,
           @ApiParam(value = "订单id",required = true) @RequestParam Long id){
        Result<PurchaseOrderOtherRequestVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseReceiveService.getOrderSet(loginUser,id));
        }catch(Exception e){
            logger.error("获取待收货配送和结算信息失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取收货单据明细", notes = "获取收货单据明细 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getReceiveDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result<PurchaseReceiveRequestVO> getReceiveDetail(HttpServletRequest request,
           @ApiParam(value = "订单id",required = true) @RequestParam Long id){
        Result<PurchaseReceiveRequestVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseReceiveService.getReceiveDetail(loginUser,id));
        }catch(Exception e){
            logger.error("获取收货单据明细失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取收货配送和结算信息", notes = "获取收货配送和结算信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getReceiveSet", method = RequestMethod.GET)
    @ResponseBody
    public Result<PurchaseReceiveOtherVO> getReceiveSet(HttpServletRequest request,
           @ApiParam(value = "订单id",required = true) @RequestParam Long id){
        Result<PurchaseReceiveOtherVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseReceiveService.getReceiveSet(loginUser,id));
        }catch(Exception e){
            logger.error("获取收货配送和结算信息失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="收货", notes = "收货处理接口 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveReceive", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveReceive(HttpServletRequest request,
           @ApiParam(value = "收货信息",required = true) @RequestBody SavePurchaseReceiveVO savePurchaseReceiveVO, @ApiIgnore UserVO userVO){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            /*UserVO loginUser = (UserVO) session.getAttribute("user");*/
            for(PurchaseReceiveDetailReturnVO purchaseReceiveDetailReturnVO : savePurchaseReceiveVO.getPurchaseReceiveDetailReturnVOs()){
                if((purchaseReceiveDetailReturnVO.getReceiveQuantity().add(purchaseReceiveDetailReturnVO.getRefuseQuantity()).compareTo(purchaseReceiveDetailReturnVO.getArrivalQuantity())) != 0){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "收货数量+拒收数量不等于到货数量!");
                    return result;
                }
            }
            if(purchaseReceiveService.checkReceived(userVO,savePurchaseReceiveVO)){
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"该单据已收货完成!~");
                return result;
            }
            String resultInfo = purchaseReceiveService.saveReceive(userVO,savePurchaseReceiveVO);
            if(resultInfo.equals("")){
                result.setBizCodeSuccessInfo(SysCode.SUCCESS, "收货完成");
            }else {
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),resultInfo);
            }
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
        }catch(Exception e){
            logger.error("获取收货配送和结算信息失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取收货人员", notes = "获取收货人员,当质量开关关闭时,该接口可调用 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getReceiver", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SimpleUserVO>> getReceiver(HttpServletRequest request){
        Result<List<SimpleUserVO>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseReceiveService.getReceiver(loginUser));
        }catch(Exception e){
            logger.error("获取收货人员失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取拒收原因", notes = "获取拒收原因 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getRefuseReason", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<QualitySetVO>> getRefuseReason(HttpServletRequest request){
        Result<List<QualitySetVO>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseReceiveService.getRefuseReason(loginUser));
        }catch(Exception e){
            logger.error("获取拒收原因失败:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

//    @ApiOperation(value="获取收货单位", notes = "获取收货单位 | 开发者:马东", produces = MediaType.APPLICATION_JSON_VALUE)
//    @RequestMapping(value = "/getReceiveUnit", method = RequestMethod.GET)
//    @ResponseBodyEntity
//    public Result<Map<String,String>> getReceiveUnit(HttpServletRequest request){
//        Result<Map<String,String>> result = new Result<>();
//        try{
//            HttpSession session = request.getSession(true);
//            UserVO loginUser = (UserVO) session.getAttribute("user");
//            result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseReceiveService.getReceiveUnit(loginUser));
//        }catch(Exception e){
//            logger.error("获取拒收原因失败:"+e.getMessage(),e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//            return result;
//        }
//        return result;
//    }

    @ApiOperation(value = "导出采购收货单信息", notes = "导导出采购收货单信息 | 开发者:马东 | 已联调 ")
    @RequestMapping(value="/exportPurchaseReceive/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的已收货单据ID", required = true, dataType = "Long", paramType="path")
    public void exportPurchaseReceive(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"采购收货单";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            PurchaseReceiveRequestVO purchaseReceiveRequestVO = purchaseReceiveService.getReceiveDetail(loginUser,id);
            purchaseReceiveService.exportExcel(output,purchaseReceiveRequestVO,loginUser);
        }catch(Exception e){
            logger.error("导出采购收货单信息错误:"+e.getMessage(),e);
        }
    }
}
