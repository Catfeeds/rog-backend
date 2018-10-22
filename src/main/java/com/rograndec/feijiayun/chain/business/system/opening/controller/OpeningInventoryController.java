package com.rograndec.feijiayun.chain.business.system.opening.controller;

import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierService;
import com.rograndec.feijiayun.chain.business.basic.warehouse.service.WarehouseService;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseVO;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningInventory;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningInventoryDetail;
import com.rograndec.feijiayun.chain.business.system.opening.service.OpeningInventoryService;
import com.rograndec.feijiayun.chain.business.system.opening.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.QueryBean;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.TwoTuple;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ST on 2017/8/21.
 */
@Api(value = "opening_inventory", description = "系统管理-期初库存-期初库存服务接口")
@RestController
@RequestMapping("system/opening")
public class OpeningInventoryController {

    private static final Logger logger = LoggerFactory.getLogger(OpeningInventoryController.class);


    @Autowired
    private OpeningInventoryService openingInventoryService;
    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CommonComponent commonComponent;

    @ApiOperation(value = "获取期初库存记录(分页)", notes = "获取全部期初库存数据 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/getInventoryRecord", method = RequestMethod.GET)
    public Result<TwoTuple<OpeningInventory,Page<List<OpeningInventoryDetail>>>> getInventoryRecord(HttpSession session,
            @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
            @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize) {
        Result<TwoTuple<OpeningInventory,Page<List<OpeningInventoryDetail>>>> result = new Result<TwoTuple<OpeningInventory,Page<List<OpeningInventoryDetail>>>>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            if(pageNo < 0 || pageSize <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page<List<OpeningInventoryDetail>> page = new Page(pageNo, pageSize);
            TwoTuple<OpeningInventory,Page<List<OpeningInventoryDetail>>> twoTuple = openingInventoryService.getInventoryRecord(userVO.getEnterpriseId(),userVO.getParentId(),page);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, twoTuple);
        } catch (Exception e) {
            logger.error("获取期初库存记录:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    @ApiOperation(value = "保存期初商品", notes = "保存期初商品到数据库中 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/saveGoodsToInventory", method = RequestMethod.POST)
    @ApiImplicitParam(name = "openingGoodsVO", value = "期初库存的商品信息", required = true, dataType = "OpeningGoodsInfoVO")
    public Result<String> saveGoodsToInventory(HttpSession session, @RequestBody OpeningGoodsInfoVO openingGoodsVO) throws Exception {
        Result<String> result = new Result<String>();
        UserVO userVO = (UserVO) session.getAttribute("user");
//        commonComponent.validationAccountingDate(DateUtils.DateToString(openingGoodsVO.getStorageDate(), DateStyle.YYYY_MM_DD),userVO);

        Integer inventoryCount = openingInventoryService.getOpeningInventoryCount(userVO.getEnterpriseId());
        if(inventoryCount > 0) throw new BusinessException("该企业已做过期初，不能重复做！");
//
//        executorService.execute(() -> {
//        	try {
//        		openingInventoryService.saveGoodsToInventory(openingGoodsVO, userVO);
//                result.setBizCodeSuccessInfo(SysCode.SUCCESS, "");
//			} catch (Exception e) {
//				logger.error("保存期初数据错误", e);
//			}
//            //return result;
//        });
//        result.setBizCodeSuccessInfo(SysCode.SUCCESS,"后台服务正在处理中，请稍后查询数据！");
//        return result;
        openingInventoryService.saveGoodsToInventory(openingGoodsVO, userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "");
        return result;
    }

    @ApiOperation(value = "导入期初商品", notes = "导入期初商品并校验格式 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/excelImportGoods", method = RequestMethod.POST)
    public Result<ResponseOpeningGoodsExcelVO> excelImportGoods(HttpServletRequest request) {
        Result<ResponseOpeningGoodsExcelVO> result = new Result<>();
        try {
            ResponseOpeningGoodsExcelVO openingGoodsVO = openingInventoryService.excelOpeningImport(request);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, openingGoodsVO);
        } catch (Exception e) {
            logger.error("导入期初商品:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导入成功数据", notes = "导入成功数据 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/importSuccessGoods/{key}/{pageNo}/{pageSize}",method= RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "key", value = "获取数据的key值", required = true,paramType = "path"),
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true,paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条目数,默认50", required = true,paramType = "path")})
    public Result<Page2OpeningInventory<List<OpeningGoodsExcelVO>>> importSuccessGoods(HttpSession session, @PathVariable("key")String key, @PathVariable("pageNo")Integer pageNo, @PathVariable("pageSize")Integer pageSize) throws Exception {
        if(pageNo <=0 || pageSize <= 0){
            throw new BusinessException("分页参数错误");
        }
        Result<Page2OpeningInventory<List<OpeningGoodsExcelVO>>> result = new Result<>();
        Page2OpeningInventory<List<OpeningGoodsExcelVO>> page = new Page2OpeningInventory<>(pageNo,pageSize);
        UserVO userVO = (UserVO) session.getAttribute("user");
        openingInventoryService.importSuccessGoods(userVO,key,page);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "期初商品导出(导出成功数据/导出失败数据)", notes = "商品导出(导出成功数据/导出失败数据) | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportGoodsSuccessAndFail/{type}/{key}",method= RequestMethod.GET)
    @ApiImplicitParams ({
            @ApiImplicitParam(name = "type", value = "1--导出成功数据；2--导出失败数据", required = true,paramType = "path"),
            @ApiImplicitParam(name = "key", value = "获取数据的key值", required = true,paramType = "path")
    })
    public void exportGoodsSuccessAndFail(@PathVariable("type")Integer type,@PathVariable String key, HttpServletResponse response) throws Exception {
        type =  type == null ? 1 : type;
        String name = "成功的期初商品信息表单";
        if (type == 2) {
            name = "失败期初商品信息表单";
        }
        name += ".xlsx";
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        openingInventoryService.exportOpeningGoods(output,key,type);
        output.close();
        output.flush();
    }



    @ApiOperation(value = "检查能否进行期初库存操作", notes = "检查能否进行期初库存操作 0 否 ；1是 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/checkCanImport", method = RequestMethod.GET)
    public Result<Integer> checkCanImport(HttpSession session){
        Result<Integer> result = new Result<>();
        try{
            UserVO userVO = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,openingInventoryService.getOpeningInventoryCount(userVO.getEnterpriseId()) == 0 ? 1 : 0);
        }catch (Exception e){
            logger.error("检查能否进行期初库存操作:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "期初商品搜索", notes = "期初商品搜索 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getGoodsByParam/{param}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "param", value = "检索条件", required = true, dataType = "String",paramType = "path")
    public Result<List<OpeningGoodsGet>> getGoodsByParam(HttpSession session, @PathVariable String param){
        Result<List<OpeningGoodsGet>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,openingInventoryService.getGoodsListByParam(param,userVO));
        return result;
    }


    @ApiOperation(value = "期初货位选择树", notes = "仓库-》库区-》货区-》货位--树形展示 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/getWarehouseTreeByParam", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<TreePOJO<WarehouseVO>>> getWarehouseTreeIncludeNullByParam(HttpServletRequest request,HttpSession session ) {
        Result<List<TreePOJO<WarehouseVO>>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, warehouseService.getWarehouseShelfTreeByParam(userVO.getEnterpriseId(), EnableStatus.ENABLE.getStatus(), null));
        } catch (Exception e) {
            logger.error("期初-货位选择树:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "获取供货单位", notes = "获取供货单位信息 | 开发者 孙腾 | 已联调 ")
    @RequestMapping(value="/getSupplier",method= RequestMethod.GET)
    public Result<List<QueryBean>> getSupplier(@ApiIgnore UserVO userVO) throws Exception {
        Result<List<QueryBean>> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,commonComponent.getOpeningInventorySupplier(userVO,null));
        return result;
    }

    @ApiOperation(value = "期初模板下载", notes = "期初模板下载  | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportGoods",method= RequestMethod.GET)
    public void exportGoods(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String name = "期初库存";
        name += ".xlsx";
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        openingInventoryService.exportGoodsInfo(output,userVO);
        output.close();
        output.flush();
    }

}
