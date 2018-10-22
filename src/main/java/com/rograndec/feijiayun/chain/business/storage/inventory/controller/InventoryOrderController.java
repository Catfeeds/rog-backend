package com.rograndec.feijiayun.chain.business.storage.inventory.controller;

import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseAreaVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseCargoAreaSimpleVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseVO;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsStockInfoVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.service.InventoryOrderService;
import com.rograndec.feijiayun.chain.business.storage.inventory.valid.AddUpdateInventoryOrderValid;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.valid.RequestParamValid;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author sunteng
 *
 */
@Api(value = "storage_inventory_inventoryOrder",description = "储存管理-盘点管理-盘点单")
@RestController
@RequestMapping("storage/inventory/inventoryOrder")
@Validated
public class InventoryOrderController {

    @Autowired
    private InventoryOrderService inventoryOrderService;
	
	@ApiOperation(value = "查询盘点单", notes = "查询仓库信息列表 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getInventoryList", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "param", value = "请求参数", required = true, dataType="RequestParamForListVO")})
    public Result<Page<List<InventoryForOrderListVO>>> getInventoryList(HttpSession session, @RequestParamValid @RequestBody RequestParamForListVO param){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<InventoryForOrderListVO>>> result = new Result<>();
        Page<List<InventoryForOrderListVO>> page = new Page<>(param.getPageNo(),param.getPageSize());
        inventoryOrderService.getInventoryList(page,param,userVO.getEnterpriseId());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "查询盘点单详情", notes = "查询盘点单列表 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getInventoryOrderDetail/{id}", method=RequestMethod.GET)
    public Result<InventoryForOrderDetailVO> getInventoryOrderDetail(HttpSession session, @PathVariable("id")Long id){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<InventoryForOrderDetailVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,inventoryOrderService.getInventoryOrderDetail(userVO,id,0));
        return result;
    }



    @ApiOperation(value = "根据企业id查询仓库", notes = "根据企业查询仓库 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/getWarehouse",method = RequestMethod.GET)
    public Result<List<WarehouseVO>> getWarehouse(HttpSession session){
        Result<List<WarehouseVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,inventoryOrderService.getWarehouseVO(userVO.getEnterpriseId()));
        return result;
    }


	
	@ApiOperation(value = "查询库区信息列表", notes = "查询库区信息列表 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getWarehouseAreaList/{warehouseId}", method=RequestMethod.GET)
    @ApiImplicitParam(name = "warehouseId", value = "仓库id", required = true, paramType = "path")
    public Result<List<WarehouseAreaVO>> getWarehouseAreaList(HttpSession session,@PathVariable("warehouseId")Long warehouseId){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<List<WarehouseAreaVO>> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,inventoryOrderService.getWarehouseAreaByEnterpriseIdAndHID(userVO.getEnterpriseId(),warehouseId));
        return result;
    }


	@ApiOperation(value = "查询货区／柜组信息列表", notes = "查询货区／柜组信息列表 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getWarehouseCargoAreaList", method=RequestMethod.POST)
//    @ApiImplicitParam(name = "warehouseAreaIds", value = "库区id集合", required = true, dataType = "List")
    public Result<List<WarehouseCargoAreaSimpleVO>> getWarehouseCargoAreaList(HttpSession session, @RequestBody List<Long> warehouseAreaIds){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<List<WarehouseCargoAreaSimpleVO>> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,inventoryOrderService.getWarehouseCargoAreaByEnterpriseIdAndHID(userVO.getEnterpriseId(),warehouseAreaIds));
        return result;
    }
	
	@ApiOperation(value = "查询商品库存列表", notes = "查询商品库存列表 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getGoodsStockList", method=RequestMethod.POST)
    @ApiImplicitParam(name = "goodsStockVO", value = "参数", required = true, dataType = "RequestGoodsStockVO")
    public Result<List<GoodsStockInfoVO>> getGoodsStockList(HttpSession session, @RequestBody RequestGoodsStockVO goodsStockVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        List<GoodsStockInfoVO> goodsInfoForInventory = inventoryOrderService.getGoodsInfoForInventory(userVO.getEnterpriseId(), goodsStockVO);
        Result<List<GoodsStockInfoVO>> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsInfoForInventory);
        return  result;
    }

    @ApiOperation(value = "查询商品库存列表分页", notes = "查询商品库存列表分页 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getGoodsStockListPage", method=RequestMethod.POST)
    @ApiImplicitParam(name = "goodsStockVO", value = "参数", required = true, dataType = "RequestGoodsStockVOPage")
    public Result<Page<List<GoodsStockInfoVO>>> getGoodsStockListPage(HttpSession session, @RequestParamValid @RequestBody RequestGoodsStockVOPage goodsStockVO){
        UserVO userVO = (UserVO) session.getAttribute("user");

        Result<Page<List<GoodsStockInfoVO>>> result = new Result<>();
        Page<List<GoodsStockInfoVO>> page = new Page<>(goodsStockVO.getPageNo(),goodsStockVO.getPageSize());
        inventoryOrderService.getGoodsInfoForInventoryPage(page,goodsStockVO,userVO.getEnterpriseId());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return  result;
    }
	
	@ApiOperation(value = "保存盘点单", notes = "保存盘点单 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/saveInventoryOrder", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "inventoryForAddVO", value = "请求参数", required = true, dataType="InventoryForAddVO")})
    public Result saveInventoryOrder(HttpSession session,@AddUpdateInventoryOrderValid @RequestBody InventoryForAddVO inventoryForAddVO) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result();
        inventoryOrderService.saveInventoryOrder(userVO,inventoryForAddVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }
	
	@ApiOperation(value = "更新盘点单", notes = "更新盘点单 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/updateInventoryOrder", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "inventoryForAddVO", value = "请求参数", required = true, dataType="InventoryForAddVO")})
    public Result updateInventoryOrder(HttpSession session,@AddUpdateInventoryOrderValid @RequestBody InventoryForAddVO inventoryForAddVO) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result();
        inventoryOrderService.updateInventoryOrder(userVO,inventoryForAddVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }
	
	@ApiOperation(value = "取消盘点单", notes = "取消盘点单 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/cancelInventoryOrder/{id}", method=RequestMethod.GET)
    public Result cancelInventoryOrder(HttpSession session,@PathVariable Long id) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result();
        inventoryOrderService.cancelInventoryOrder(userVO,id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }
	

	
	@ApiOperation(value = "导出Excel", notes = "按照模版将盘点单导出至Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "盘点单id"
                    , required = true, paramType="path")
    })
    public void exportExcel(
            HttpServletResponse response,HttpSession session, @PathVariable("id")Long id
    ) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "盘点单";
        String suffix = ".xlsx";
        String fileName = file+suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();

        inventoryOrderService.export(output,id,userVO,1);

        output.close();
        output.flush();
    }

}
