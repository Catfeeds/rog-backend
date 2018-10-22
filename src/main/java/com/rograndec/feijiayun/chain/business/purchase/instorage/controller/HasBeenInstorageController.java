package com.rograndec.feijiayun.chain.business.purchase.instorage.controller;

import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierBasicExcelVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.service.PurchaseInStorageService;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.HasBeenInstorageFormVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.HasBeenInstoragePageVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.StayInstoragePageVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.StayInstorageSaveVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(value = "hasBeenInstorage", description = "采购管理-采购入库-已入库", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/instorage/hasBeenInstorage")
public class HasBeenInstorageController {

    private static final Log logger = LogFactory.getLog(HasBeenInstorageController.class);

    @Autowired
    private PurchaseInStorageService purchaseInStorageService;

    @ApiOperation(value="已入库-分页获取已入库数据", notes = "分页获取采购已入库数据 | 开发者 苏磊 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getHasBeenInstoragePage", method = RequestMethod.GET)
    public Result<Page<List<HasBeenInstoragePageVO>>> getHasBeenInstoragePage(HttpServletRequest request,
                                             @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                             @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                             @ApiParam(value = "开始时间", required = false) @RequestParam(required=false) Date startDate,
                                             @ApiParam(value = "结束时间", required = false) @RequestParam(required=false) Date endDate,
                                             @ApiParam(value = "供货单位编码", required = false) @RequestParam(required=false) String supplierCode,
                                             @ApiParam(value = "供货单位名称", required = false) @RequestParam(required=false) String supplierName,
                                             @ApiParam(value = "入库单号", required = false) @RequestParam(required=false) String code,
                                             @ApiParam(value = "入库人员", required = false) @RequestParam(required=false) String storageManName,
                                             @ApiParam(value = "按某一列排序(moveDate或者code)", required = false) @RequestParam(required=false) String order,
                                             @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
        Result<Page<List<HasBeenInstoragePageVO>>> result = new Result<Page<List<HasBeenInstoragePageVO>>>();
        try{
            if(pageNo <= 0 || pageSize <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = purchaseInStorageService.getHasBeenInstoragePage(page,
                    startDate,endDate,supplierCode,supplierName,code,storageManName,order,sort,loginUser.getEnterpriseId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("分页获取采购已入库数据错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="已入库-获取已入库表单数据", notes = "获取采购已入库表单数据 | 开发者 苏磊 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getHasBeenInstorageForm", method = RequestMethod.GET)
    public Result<StayInstorageSaveVO> getHasBeenInstorageForm(HttpServletRequest request,
                                                                  @ApiParam(value = "当前需要查看的已入库采购单据ID", required = true) @RequestParam Long id){
        Result<StayInstorageSaveVO> result = new Result<StayInstorageSaveVO>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            StayInstorageSaveVO stayInstorageSaveVO = purchaseInStorageService.getHasBeenInstorage(loginUser.getEnterpriseId(),id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,stayInstorageSaveVO);
        }catch(Exception e){
            logger.error("获取采购已入库数据表单错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出采购入库单信息", notes = "导出采购入库单信息 | 开发者 苏磊 | 已完成 ")
    @RequestMapping(value="/ExportHasBeenInstorage/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的已入库采购单据ID", required = true, dataType = "Long", paramType="path")
    public void ExportHasBeenInstorage(HttpServletRequest request, HttpServletResponse response,@PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "采购入库单";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            HasBeenInstorageFormVO hasBeenInstorageFormVO = purchaseInStorageService.getHasBeenInstorageForm(loginUser.getEnterpriseId(),id);
            purchaseInStorageService.exportExcel(output,hasBeenInstorageFormVO,loginUser);
        }catch(Exception e){
            logger.error("导出采购入库单信息错误:"+e.getMessage(),e);
        }
    }

}
