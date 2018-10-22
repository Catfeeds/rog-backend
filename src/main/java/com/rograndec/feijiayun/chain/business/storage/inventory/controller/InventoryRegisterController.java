package com.rograndec.feijiayun.chain.business.storage.inventory.controller;

import com.rograndec.feijiayun.chain.business.storage.inventory.constant.RegisterType;
import com.rograndec.feijiayun.chain.business.storage.inventory.exception.InventoryBizException;
import com.rograndec.feijiayun.chain.business.storage.inventory.service.InventoryOrderService;
import com.rograndec.feijiayun.chain.business.storage.inventory.valid.AddUpdateInventoryRegisterValid;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.InventoryForOrderDetailVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.excel.GoodsShelfForRegisterOKVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.excel.ResponseGoodsRegisterExcelVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryForRegisterVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryForRegisterVO2;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryGoodsInfoVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.RequestParamForHadRegisterListVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.valid.RequestParamValid;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "storage_inventory_inventoryRegister",description = "储存管理-盘点管理-盘点登记")
@RestController
@RequestMapping("storage/inventory/inventoryRegister")
@Validated
public class InventoryRegisterController {

    private Logger logger = LoggerFactory.getLogger(InventoryRegisterController.class);

    @Autowired
    private InventoryOrderService inventoryOrderService;
	
	@ApiOperation(value = "查询已登记列表", notes = "查询已登记列表 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getHadRegisterInventoryOrderList", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "param", value = "请求参数", required = true, dataType="RequestParamForHadRegisterListVO")})
    public Result<Page<List<InventoryForRegisterVO2>>> getHadRegisterInventoryOrderList(HttpSession session, @RequestParamValid @RequestBody RequestParamForHadRegisterListVO param){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<InventoryForRegisterVO2>>> result = new Result<>();
        Page<List<InventoryForRegisterVO2>> page = new Page<>(param.getPageNo(),param.getPageSize());
        param.setDiff(1);
        inventoryOrderService.getHadRegisterInventoryOrderList(page,param,userVO.getEnterpriseId());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "点击登记按钮查询待登记详情", notes = "点击登记按钮查询待登记详情 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getInventoryOrderDetail/{id}", method=RequestMethod.GET)
    public Result<InventoryForOrderDetailVO> getInventoryOrderDetail(HttpSession session, @PathVariable("id")Long id){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<InventoryForOrderDetailVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,inventoryOrderService.getInventoryOrderDetail(userVO,id,1));
        return result;
    }



    @ApiOperation(value = "查询已登记详情", notes = "查询已登记详情 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getHadRegisterInventoryOrderDetail/{id}", method=RequestMethod.GET)
    public Result<InventoryForRegisterVO2> getHadRegisterInventoryOrderDetail(HttpSession session, @PathVariable("id")Long id){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<InventoryForRegisterVO2> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,inventoryOrderService.getHadRegisterInventoryOrderDetail(userVO,id));
        return result;
    }

    @ApiOperation(value = "盘点单登记", notes = "盘点单登记 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/updateRegisterInventory", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "inventoryForRegisterVO", value = "请求参数", required = true, dataType="InventoryForRegisterVO")})
    public Result updateRegisterInventory(HttpSession session,@AddUpdateInventoryRegisterValid @RequestBody InventoryForRegisterVO inventoryForRegisterVO) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result();
        inventoryOrderService.updateRegisterInventory(userVO,inventoryForRegisterVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }



	
	@ApiOperation(value = "导出Excel", notes = "按照模版将盘点登记单导出至Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "盘点登记单id"
                    , required = true, paramType="path")
    })
    public void exportExcel(
            HttpSession session,
            HttpServletResponse response,
            @PathVariable
                    Long id
    ) throws IOException {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "盘点登记单";
        String suffix = ".xlsx";
        String fileName = file+suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();

        inventoryOrderService.export4HadRegisterInventory(output,id,userVO);

        output.close();
        output.flush();
    }



    @ApiOperation(value = "商品Excel导入", notes = "商品Excel导入 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/importGoods",method= RequestMethod.POST)
    public Result<ResponseGoodsRegisterExcelVO> importGoods(HttpServletRequest request) throws Exception {
        Result<ResponseGoodsRegisterExcelVO> result = new Result<>();
        ResponseGoodsRegisterExcelVO responseGoodsRegisterExcelVO = null;
        try {
            responseGoodsRegisterExcelVO = inventoryOrderService.excelImport(request);
        } catch (Exception e) {
            logger.warn(e.getMessage(),e);
            if(e instanceof InventoryBizException){
                result.setBizCodeFallInfo("111111",e.getMessage());
            } else {
                result.setBizCodeFallInfo(SysCode.FAIL);
            }
          return result;
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, responseGoodsRegisterExcelVO);
        return result;
    }

    @ApiOperation(value = "导入成功数据", notes = "导入成功数据 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getSuccessData/{invId}/{registerType}/{key}",method= RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "invId", value = "盘点单id", required = true, paramType="path"),
            @ApiImplicitParam(name = "registerType", value = "登记方法（0-按账面等价；1-按实物登记）", required = true, paramType="path"),
            @ApiImplicitParam(name = "key", value = "key值", required = true, paramType="path")}
            )
    public Result<List<GoodsShelfForRegisterOKVO>> getSuccessData(HttpSession session,@PathVariable("invId")Long invId,@PathVariable("registerType")Integer registerType,@PathVariable("key") String key) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<List<GoodsShelfForRegisterOKVO>> result = new Result<>();
        Integer count = inventoryOrderService.getInventoryCountData(userVO, invId);//盘点单实际商品数量
        if(count == null){
            result.setBizCodeFallInfo("111111", "盘点单数据异常");
            return result;
        }
        List<GoodsShelfForRegisterOKVO> successData = inventoryOrderService.getSuccessData(key);
        if(successData != null){
            int size = successData.size();
            if(registerType == RegisterType.BOOK_REGISTER.getCode()){
                 if(count != size){
                     result.setBizCodeFallInfo(SysCode.FAIL,"按账面盘点导入成功数据与盘点实际数量不一致");
                     return result;
                 }
            } else if(registerType == RegisterType.PHYSICAL_REGISTER.getCode()){
                if(count < size){
                    result.setBizCodeFallInfo(SysCode.FAIL,"按实物盘点导入成功数据与盘点实际数量不一致");
                    return result;
                }
            } else {
                result.setBizCodeFallInfo(SysCode.FAIL,"盘点方法不存在");
                return result;
            }
        } else {
            result.setBizCodeFallInfo(SysCode.FAIL,"数据不存在");
            return result;
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, successData);
        return result;
    }

    @ApiOperation(value = "盘点登记导出失败/成功Excel", notes = "盘点登记导出失败/成功Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportGoodsExcel/{type}/{invType}/{key}", method=RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "type", value = "2/失败数据；1/成功数据", required = true, paramType="path"),
            @ApiImplicitParam(name = "invType", value = "0/按货位；1/按商品", required = true, paramType="path"),
            @ApiImplicitParam(name = "key", value = "key值", required = true, paramType="path")})
    public void exportGoodsExcel(HttpServletResponse response,HttpSession session, @PathVariable("type")Integer type,@PathVariable("invType")Integer invType,@PathVariable("key")String key) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "";
        if(type == 2){
            file = "失败的商品数据";
        } else {
            file = "成功的商品数据";
        }
        String suffix = ".xlsx";
        String fileName = file+suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();

        inventoryOrderService.exportGoods(output,key,type,invType);

        output.close();
        output.flush();
    }

    @ApiOperation(value = "盘点登记商品搜索", notes = "盘点登记商品搜索 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getGoodsInfoInInventory", method=RequestMethod.GET)
    public Result<List<InventoryGoodsInfoVO>> getGoodsInfoInInventory(HttpSession session,
                                        @ApiParam(value = "盘点单id") @RequestParam(value = "invId",required = true)Long invId,
                                        @ApiParam(value = "商品搜索条件")@RequestParam(value = "param",required = false)String param){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<List<InventoryGoodsInfoVO>> result = new Result<>();
        List<InventoryGoodsInfoVO> goodsInfoInInventory = inventoryOrderService.getGoodsInfoInInventory(userVO, invId, param);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsInfoInInventory);
        return result;
    }


    @ApiOperation(value = "查看盘点登记草稿缓存", notes = "查看盘点登记草稿缓存 | 开发者 孙腾 | 已完成")
    @RequestMapping(value = "/daftCache/{baseOrderId}", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "baseOrderId", value = "上游单据id", required = true, paramType="path")
           })
    public Result<DraftCacheVO> daftCacheVO(@ApiIgnore UserVO userVO,@PathVariable("baseOrderId") Long baseOrderId) {

        Result<DraftCacheVO> result = new Result<>();

        DraftCacheVO draftCacheVO = inventoryOrderService.getDraftCacheVO(userVO,baseOrderId);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);

        return  result;
    }

    @ApiOperation(value = "保存盘点登记草稿缓存", notes = "保存盘点登记草稿缓存 | 开发者 孙腾 | 已完成")
    @RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
    public Result<DraftCacheVO<InventoryForRegisterVO2>> saveDraftCache(@ApiIgnore UserVO userVO,@RequestBody DraftCacheVO<InventoryForRegisterVO2> draftCacheVO) {

        Result<DraftCacheVO<InventoryForRegisterVO2>> result = new Result<>();

        draftCacheVO = inventoryOrderService.saveDraftCache(userVO, draftCacheVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);

        return  result;
    }

    @ApiOperation(value = "删除盘点登记草稿缓存", notes = "删除盘点登记草稿缓存 | 开发者 孙腾 | 已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}", method = RequestMethod.POST)
    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String redisKeyValue) {

        Result result = new Result<>();

        inventoryOrderService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.INVENTORY.getCodePrefix(),redisKeyValue);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return  result;
    }

    @ApiOperation(value = "导出待盘点登记Excel", notes = "导出待盘点登记Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportWaitRegisterExcel/{id}", method=RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "盘点单id"
                    , required = true, paramType="path")
    })
    public void exportExcel(
            HttpServletResponse response,HttpSession session, @PathVariable("id")Long id
    ) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "盘点待登记单";
        String suffix = ".xlsx";
        String fileName = file+suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();

        inventoryOrderService.export(output,id,userVO,0);

        output.close();
        output.flush();

    }
}
