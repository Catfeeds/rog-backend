package com.rograndec.feijiayun.chain.business.basic.warehouse.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.Warehouse;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.basic.warehouse.service.WarehouseService;
import com.rograndec.feijiayun.chain.business.basic.warehouse.valid.WarehouseAreaValid;
import com.rograndec.feijiayun.chain.business.basic.warehouse.valid.WarehouseCargoAreaValid;
import com.rograndec.feijiayun.chain.business.basic.warehouse.valid.WarehouseValid;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CodeComponent;
import com.rograndec.feijiayun.chain.common.component.UserComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ST on 2017/8/21.
 */
@Api(value = "warehouse", description = "基础资料管理-库房管理")
@RestController
@RequestMapping("basic/warehouse")
@Validated
public class WarehouseController {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private CodeComponent codeComponent;

    @Autowired
    private UserComponent userComponent;

    @ApiOperation(value = "仓库-》库区-》货区-》货位--树形展示", notes = "仓库-》库区-》货区-》货位--树形展示（如果子级没有数据，则父级不显示，PS:仓库A下的库区A货区A,如果货区A下没有子集那么仓库A库区A货区A都“不显示”,） | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/getWarehouseExclusiveNullTreeByParam/{type}/{status}", method = RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "type 0=仓库；1=库区；2=货区；3=货位； 根据type展示以type为最高级的级联树", required = true, paramType = "path"),
            @ApiImplicitParam(name = "status", value = "查询状态（0/禁用；1/启用；2/全部）", required = true, paramType = "path")})
    public Result<List<TreePOJO<WarehouseVO>>> getWarehouseExclusiveNullTreeByParam(HttpSession session,@PathVariable Integer type,@PathVariable Integer status) {
        Result<List<TreePOJO<WarehouseVO>>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, warehouseService.getWarehouseExclusiveNullTreeByParam(type,userVO.getEnterpriseId(),userVO.getParentId(),status == 2 ? null : status));
        return result;
    }
    @ApiOperation(value = "仓库-》库区-》货区-》货位--树形展示（含空树）", notes = "仓库-》库区-》货区-》货位--树形展示（如果子级没有数据，则父级不显示，PS:仓库A下的库区A货区A,如果货区A下没有子集那么仓库A库区A货区A都“显示”,） | 开发者 孙腾 | 已废弃")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "type 0=仓库；1=库区；2=货区；3=货位； 根据type展示以type为最高级的级联树", required = true, paramType = "path"),
            @ApiImplicitParam(name = "status", value = "查询状态（0/禁用；1/启用；2/全部）", required = true, paramType = "path")})
    @RequestMapping(value = "/getWarehouseTreeIncludeNullByParam/{type}/{status}", method = RequestMethod.GET)
    public Result<List<TreePOJO<WarehouseVO>>> getWarehouseTreeIncludeNullByParam(HttpSession session,@PathVariable Integer type,@PathVariable(required = false) Integer status) {
        Result<List<TreePOJO<WarehouseVO>>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, warehouseService.getWarehouseTreeIncludeNullByParam(type, userVO.getEnterpriseId(),userVO.getParentId(),status == 2 ? null : status));
        return result;
    }


    //########################################################################################################//
    //                                         仓库操作                                                       //
    //########################################################################################################//
    @ApiOperation(value = "根据企业id,上级企业id,分页查询仓库", notes = "根据企业id,上级企业id, 查询分页仓库 | 开发者 孙腾 | 已联调")
    @ApiImplicitParams({@ApiImplicitParam(name = "status", value = "状态（0-禁用/1-启用/2-所有状态）", required = true, paramType = "path"),
        @ApiImplicitParam(name = "sortOrder", value = "编码排序（desc 倒序 asc 正序）", required = true, paramType = "path")})
    @RequestMapping(value = "/getWarehouse/{status}/{pageNo}/{pageSize}/{sortOrder}",method = RequestMethod.GET)
    public Result<Page<List<ResponseWarehouseVO>>> getWarehousePage(HttpSession session, @PathVariable("status") Integer status,@PathVariable("pageNo") Integer pageNo,
                                                                @PathVariable("pageSize") Integer pageSize,@PathVariable("sortOrder") String sortOrder){
        Result<Page<List<ResponseWarehouseVO>>> result = new Result<>();
        if(pageNo < 0 || pageSize <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page<List<ResponseWarehouseVO>> page = new Page(pageNo, pageSize);
        UserVO userVO = (UserVO) session.getAttribute("user");
        warehouseService.getWarehouseByParam(page,userVO,status == 2 ? null : status,sortOrder);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "根据企业id,上级企业id,查询仓库", notes = "根据企业id,上级企业id, 查询仓库 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/getWarehouseList",method = RequestMethod.GET)
    public Result<List<ResponseWarehouseVO>> getWarehouseList(HttpSession session){
        Result<List<ResponseWarehouseVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        List<ResponseWarehouseVO> list = warehouseService.getWarehouseList(userVO.getEnterpriseId(),userVO.getParentId(), EnableStatus.ENABLE.getStatus());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
        return result;
    }

    @ApiOperation(value = "仓库添加", notes = "仓库添加 | 开发者 孙腾 | 已联调")
    @ApiImplicitParam(name = "warehouseVO", value = "", required = true, dataType = "RequestWarehouseVO")
    @RequestMapping(value = "/addWarehouse",method = RequestMethod.POST)
    public Result<String> addWarehouse(HttpSession session, @RequestBody @WarehouseValid RequestWarehouseVO warehouseVO) throws Exception {
        Result<String> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(warehouseVO,warehouse);
        Warehouse warehouse1 = (Warehouse) EntityUtils.reflectAddSetDefaultValue(warehouse.getClass(),userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(warehouse1,warehouse);
        warehouse.setSysType(0);
        warehouseService.addWarehouse(warehouse);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "仓库修改", notes = "仓库修改 | 开发者 孙腾 | 已联调")
    @ApiImplicitParam(name = "requestWarehouseVO", value = "", required = true, dataType = "RequestWarehouseUpdateVO")
    @RequestMapping(value = "/updateWarehouse",method = RequestMethod.POST)
    public Result<String> updateWarehouse(HttpSession session,@RequestBody RequestWarehouseUpdateVO requestWarehouseVO) throws Exception {
        Result<String> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(requestWarehouseVO,warehouse);
        Warehouse warehouse1 = (Warehouse) EntityUtils.reflectUpdateSetDefaultValue(warehouse.getClass(),userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(warehouse1,warehouse);
        warehouseService.updateWarehouse(warehouse);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "仓库删除", notes = "仓库删除 | 开发者 孙腾 | 已联调")
    @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "path")
    @RequestMapping(value = "/deleteWarehouse/{id}",method = RequestMethod.DELETE)
    public Result deleteWarehouse(@PathVariable Long id){
        Result result = new Result<>();
//        try{
            int count = warehouseService.getAreaCountByHouseId(id);
            if(count > 0){
                result.setBizCodeFallInfo(SysCode.FAIL,"已绑定数据不能删除");
                return result;
            }
            warehouseService.deleteWarehouse(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
//        }catch (Exception e){
//            logger.error("仓库删除:" + e.getMessage(), e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//            return result;
//        }
        return result;
    }


    @ApiOperation(value = "获取总部默认仓库的编码规则", notes = "获取总部默认仓库的编码规则 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/getDefaultCodeRule",method = RequestMethod.GET)
    public Result<Warehouse> getDefaultCodeRule(@ApiIgnore UserVO userVO){
        Result<Warehouse> result = new Result<>();

        if(ChainType.Headquarters.getCode() == userVO.getChainType()){
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,warehouseService.getDefaultWarehouse(userVO.getEnterpriseId(),userVO.getEnterpriseCode()));
        } else {
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,warehouseService.getDefaultWarehouse(userVO.getParentId(),userVO.getParentCode()));
        }

        return result;
    }



    //########################################################################################################//
    //                                         库区操作                                                       //
    //########################################################################################################//
    @ApiOperation(value = "根据企业id,上级企业id,仓库id 查询分页库区", notes = "根据企业id,上级企业id,仓库id 查询分页库区 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/getWarehouseArea",method = RequestMethod.GET)
    public Result<Page<List<ResponseWarehouseAreaVO>>> getWarehouseArea(HttpSession session, RequestQueryWarehouseAreaVO requestWarehouseCommonVO){
        Result<Page<List<ResponseWarehouseAreaVO>>> result = new Result<>();
        if(requestWarehouseCommonVO.getPageNo() < 0 || requestWarehouseCommonVO.getPageSize() <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page<List<ResponseWarehouseAreaVO>> page = new Page(requestWarehouseCommonVO.getPageNo(), requestWarehouseCommonVO.getPageSize());
        UserVO userVO = (UserVO) session.getAttribute("user");
        warehouseService.getWarehouseAreaByParam(page,userVO.getEnterpriseId(),userVO.getParentId(),requestWarehouseCommonVO.getWarehouseId(),userVO.getEnterpriseCode(),requestWarehouseCommonVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "根据企业id,上级企业id,仓库id 查询库区", notes = "根据企业id,上级企业id,仓库id 查询库区 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/getWarehouseAreaList",method = RequestMethod.GET)
    public Result<List<ResponseWarehouseAreaVO>> getWarehouseAreaList(HttpSession session,RequestQueryWarehouseAreaVO requestWarehouseCommonVO){
        Result<List<ResponseWarehouseAreaVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        List<ResponseWarehouseAreaVO> list = warehouseService.getWarehouseAreaList(userVO.getEnterpriseId(),userVO.getParentId(),requestWarehouseCommonVO.getWarehouseId(),requestWarehouseCommonVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
        return result;
    }

    @ApiOperation(value = "库区添加", notes = "库区添加 | 开发者 孙腾 | 已联调")
    @ApiImplicitParam(name = "warehouseAreaVO", value = "", required = true, dataType = "RequestWarehouseAreaVO")
    @RequestMapping(value = "/addWarehouseArea",method = RequestMethod.POST)
    public Result<String> addWarehouseArea(HttpSession session,@WarehouseAreaValid @RequestBody RequestWarehouseAreaVO warehouseAreaVO) throws Exception {
        Result<String> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");

        WarehouseArea warehouseArea = new WarehouseArea();
        BeanUtils.copyProperties(warehouseAreaVO,warehouseArea);
        WarehouseArea warehouseArea1 = (WarehouseArea) EntityUtils.reflectAddSetDefaultValue(warehouseArea.getClass(),userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(warehouseArea1,warehouseArea);
        warehouseArea.setSysType(0);
        warehouseService.addWarehouseArea(warehouseArea);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "库区修改", notes = "库区修改 | 开发者 孙腾 | 已联调")
    @ApiImplicitParam(name = "warehouseAreaVO", value = "", required = true, dataType = "RequestWarehouseAreaUpdateVO")
    @RequestMapping(value = "/updateWarehouseArea",method = RequestMethod.POST)
    public Result<String> updateWarehouseArea(HttpSession session,@RequestBody RequestWarehouseAreaUpdateVO warehouseAreaVO) throws Exception {
        Result<String> result = new Result<>();

        UserVO userVO = (UserVO) session.getAttribute("user");
        WarehouseArea warehouseArea = new WarehouseArea();
        BeanUtils.copyProperties(warehouseAreaVO,warehouseArea);
        WarehouseArea warehouseArea1 = (WarehouseArea) EntityUtils.reflectUpdateSetDefaultValue(warehouseArea.getClass(),userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(warehouseArea1,warehouseArea);
        warehouseService.updateWarehouseArea(warehouseArea);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "库区删除", notes = "库区删除 | 开发者 孙腾 | 已联调")
    @ApiImplicitParam(name = "id", value = "", required = true, paramType = "path")
    @RequestMapping(value = "/deleteWarehouseArea/{id}",method = RequestMethod.DELETE)
    public Result deleteWarehouseArea(@PathVariable Long id){
        Result result = new Result();
        int count = warehouseService.getCargoCountByAreaId(id);
        if(count > 0){
            result.setBizCodeFallInfo(SysCode.FAIL,"已绑定数据不能删除");
            return result;
        }
        warehouseService.deleteWarehouseArea(id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    //########################################################################################################//
    //                                         货区操作                                                       //
    //########################################################################################################//
    @ApiOperation(value = "根据企业id,上级企业id,库区id 分页查询货区", notes = "根据企业id,上级企业id,库区id 分页查询货区 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/getWarehouseCargoArea",method = RequestMethod.GET)
    public Result<Page<List<ResponseWarehouseCargoAreaVO>>> getWarehouseCargoArea(HttpSession session,RequestQueryWarehouseCargoAreaVO commonVO){
        Result<Page<List<ResponseWarehouseCargoAreaVO>>> result = new Result<>();
        if(commonVO.getPageNo() < 0 || commonVO.getPageSize() <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page<List<ResponseWarehouseCargoAreaVO>> page = new Page(commonVO.getPageNo(), commonVO.getPageSize());
        UserVO userVO = (UserVO) session.getAttribute("user");
        warehouseService.getWarehouseCargoAreaByParam(page,userVO.getEnterpriseId(),userVO.getParentId(),commonVO.getWarehouseAreaId(),userVO.getEnterpriseCode(),commonVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }


    @ApiOperation(value = "根据企业id,上级企业id,库区id 查询货区", notes = "根据企业id,上级企业id,库区id 查询货区 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/getWarehouseCargoAreaList",method = RequestMethod.GET)
    public Result<List<ResponseWarehouseCargoAreaVO>> getWarehouseCargoAreaList(HttpSession session, RequestQueryWarehouseCargoAreaVO commonVO){
        Result<List<ResponseWarehouseCargoAreaVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        List<ResponseWarehouseCargoAreaVO> list = warehouseService.getWarehouseCargoAreaByParam(userVO.getEnterpriseId(),userVO.getParentId(),commonVO.getWarehouseAreaId(),commonVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
        return result;
    }

    @ApiOperation(value = "货区添加", notes = "货区添加 | 开发者 孙腾 | 已联调")
    @ApiImplicitParam(name = "warehouseCargoAreaVO", value = "", required = true, dataType = "RequestWarehouseCargoAreaVO")
    @RequestMapping(value = "/addWarehouseCargoArea",method = RequestMethod.POST)
    public Result<String> addWarehouseCargoArea(HttpSession session,@WarehouseCargoAreaValid @RequestBody RequestWarehouseCargoAreaVO warehouseCargoAreaVO) throws Exception {
        Result<String> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        WarehouseCargoArea warehouseCargoArea = new WarehouseCargoArea();
        BeanUtils.copyProperties(warehouseCargoAreaVO,warehouseCargoArea);
        WarehouseCargoArea warehouseCargoArea1 = (WarehouseCargoArea) EntityUtils.reflectAddSetDefaultValue(warehouseCargoArea.getClass(),userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(warehouseCargoArea1,warehouseCargoArea);
        warehouseCargoArea.setSysType(0);
        warehouseService.addWarehouseCargoArea(warehouseCargoArea,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "货区修改", notes = "货区修改 | 开发者 孙腾 | 已联调")
    @ApiImplicitParam(name = "warehouseCargoAreaVO", value = "", required = true, dataType = "RequestWarehouseCargoAreaVO")
    @RequestMapping(value = "/updateWarehouseCargoArea",method = RequestMethod.POST)
    public Result<String> updateWarehouseCargoArea(HttpSession session, @RequestBody RequestWarehouseCargoAreaVO warehouseCargoAreaVO) throws Exception {
        Result<String> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        WarehouseCargoArea warehouseCargoArea = new WarehouseCargoArea();
        BeanUtils.copyProperties(warehouseCargoAreaVO,warehouseCargoArea);
        WarehouseCargoArea warehouseCargoArea1 = (WarehouseCargoArea) EntityUtils.reflectUpdateSetDefaultValue(warehouseCargoArea.getClass(),userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(warehouseCargoArea1,warehouseCargoArea);
        warehouseService.updateWarehouseCargoArea(warehouseCargoArea,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "货区删除", notes = "货区删除 | 开发者 孙腾 | 已联调")
    @ApiImplicitParam(name = "id", value = "", required = true, paramType = "path")
    @RequestMapping(value = "/deleteWarehouseCargoArea/{id}",method = RequestMethod.DELETE)
    public Result deleteWarehouseCargoArea(@PathVariable Long id){
        Result result = new Result<>();
        int count = warehouseService.getShelfCountByCargoId(id);
        if(count > 0){
            result.setBizCodeFallInfo(SysCode.FAIL,"已绑定数据不能删除");
            return result;
        }
        warehouseService.deleteWarehouseCargoArea(id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }


    //########################################################################################################//
    //                                         货位操作                                                       //
    //########################################################################################################//
    @ApiOperation(value = "根据货位id 查询货位", notes = "根据货位id 查询货位 | 开发者 孙腾 | 已联调")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "货位id", required = true, paramType = "path")})
    @RequestMapping(value = "/getWarehouseShelfByI/{id}",method = RequestMethod.GET)
    public Result<ResponseWarehouseShelfVO> getWarehouseShelf(@PathVariable Long id){
        Result<ResponseWarehouseShelfVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,warehouseService.getWarehouseShelf(id));
        return result;
    }



    @ApiOperation(value = "货位添加", notes = "货位添加 | 开发者 孙腾 | 已联调")
    @ApiImplicitParam(name = "warehouseShelfVO", value = "", required = true, dataType = "RequestWarehouseShelfVO")
    @RequestMapping(value = "/addWarehouseShelf",method = RequestMethod.POST)
    public Result<String> addWarehouseShelf(HttpSession session,@RequestBody RequestWarehouseShelfVO warehouseShelfVO) throws Exception {
        Result<String> result = new Result<>();

        UserVO userVO = (UserVO) session.getAttribute("user");
        WarehouseShelf warehouseShelf = new WarehouseShelf();
        BeanUtils.copyProperties(warehouseShelfVO,warehouseShelf);
        WarehouseShelf warehouseShelf1 = (WarehouseShelf) EntityUtils.reflectAddSetDefaultValue(warehouseShelf.getClass(),userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(warehouseShelf1,warehouseShelf);
        warehouseShelf.setSysType(0);
        warehouseService.addWarehouseShelf(warehouseShelf);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "货位修改", notes = "货位修改 | 开发者 孙腾 | 已联调")
    @ApiImplicitParam(name = "warehouseShelfVO", value = "", required = true, dataType = "RequestWarehouseShelfUpdateVO")
    @RequestMapping(value = "/updateWarehouseShelf",method = RequestMethod.POST)
    public Result<String> updateWarehouseCargoArea(HttpSession session,@RequestBody RequestWarehouseShelfUpdateVO warehouseShelfVO) throws Exception {
        Result<String> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        WarehouseShelf warehouseShelf = new WarehouseShelf();
        BeanUtils.copyProperties(warehouseShelfVO,warehouseShelf);
        WarehouseShelf warehouseShelf1 = (WarehouseShelf) EntityUtils.reflectUpdateSetDefaultValue(warehouseShelf.getClass(),userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(warehouseShelf1,warehouseShelf);
        warehouseService.updateWarehouseShelf(warehouseShelf);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "货位删除", notes = "货位删除 | 开发者 孙腾 | 已联调")
    @ApiImplicitParam(name = "id", value = "", required = true, paramType = "path")
    @RequestMapping(value = "/deleteWarehouseShelf/{id}",method = RequestMethod.DELETE)
    public Result<WarehouseShelf> deleteWarehouseShelf(@PathVariable Long id){
        Result<WarehouseShelf> result = new Result<>();

        Integer count = warehouseService.getStockCountByShelfId(id);
        if(count > 0){
            result.setBizCodeFallInfo(SysCode.FAIL,"已绑定数据不能删除");
            return result;
        }
        warehouseService.deleteWarehouseShelf(id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "获取库区/货区/货位的编码", notes = "根据条件获取库区/货区/货位的编码 | 开发者 孙腾 | 已联调")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0-库区；1-货区；2-货位", required = true, paramType = "path"),
            @ApiImplicitParam(name = "id", value = "当type=0,id表示仓库id;当type=1,id表示库区id;当type=2,id表示货区id", required = true, paramType = "path")})
    @RequestMapping(value = "/getCodeByType/{type}/{id}", method = RequestMethod.GET)
    public Result<String> getCodeByType(HttpSession session, @PathVariable Integer type, @PathVariable  Long id) throws Exception {
        Result<String> result = new Result<>();
        String codeName = "";
        UserVO userVO = (UserVO) session.getAttribute("user");
        Long enterpriseId = userVO.getEnterpriseId();
        if (type == 0) {
            //获取库区编码
            Warehouse warehouse = warehouseService.getWarehouseByKey(id);//仓库对象
            String spacer = warehouse.getSpacer();
            codeName = WarehouseArea.class.getSimpleName();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,codeComponent.generateWarehouseCode(codeName, warehouse.getAreaCodeLength(), enterpriseId,warehouse.getCode(),spacer));
        } else if (type == 1) {
            //获取货区的编码
            codeName = WarehouseCargoArea.class.getSimpleName();
            WarehouseArea warehouseArea = warehouseService.getWarehouseAreaByKey(id);//库区对象
            if(warehouseArea != null){
                Warehouse warehouse = warehouseService.getWarehouseByKey(warehouseArea.getWarehouseId());//仓库对象
                if(warehouse != null){
                    result.setBizCodeSuccessInfo(SysCode.SUCCESS,codeComponent.generateWarehouseCode(codeName, warehouse.getCargoAreaCodeLength(), enterpriseId,warehouseArea.getCode(),warehouse.getSpacer()));
                }
            }
        } else if (type == 2) {
            //获取货位的编码
            codeName = WarehouseShelf.class.getSimpleName();
            WarehouseCargoArea warehouseCargoArea = warehouseService.getWarehouseCargoAreaByKey(id);//货区对象
            if(warehouseCargoArea != null){
                Warehouse warehouse = warehouseService.getWarehouseByKey(warehouseCargoArea.getWarehouseId());//仓库对象
                if(warehouse != null){
                    result.setBizCodeSuccessInfo(SysCode.SUCCESS,codeComponent.generateWarehouseCode(codeName, warehouse.getShelfCodeLength(), enterpriseId,warehouseCargoArea.getCode(),warehouse.getSpacer()));
                }
            }
        }
        return result;

    }

    @ApiOperation(value = "获取起始/结束货位的编码", notes = "批量生成货位功能中获取起始/结束货位编码的 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/getBatchCode", method = RequestMethod.GET)
    public Result<ResponseBatchCodeVO> getBatchCode(HttpSession session,  RequestBatchCodeVO batchCodeVO) throws Exception {
        Result<ResponseBatchCodeVO> result = new Result<>();
        String codeName = "";

        UserVO userVO = (UserVO) session.getAttribute("user");
        Long enterpriseId = userVO.getEnterpriseId();
        Warehouse warehouse = warehouseService.getWarehouseByKey(batchCodeVO.getWarehouseId());//仓库对象
        String spacer = warehouse.getSpacer();
        ResponseBatchCodeVO responseBatchCodeVO = new ResponseBatchCodeVO();
        Integer count = batchCodeVO.getCount();
        if(count > 0){
            codeName = WarehouseShelf.class.getSimpleName();
            //起始编码
            String startCode = codeComponent.generate(codeName, warehouse.getShelfCodeLength(), enterpriseId);
            String code = "";
            for(int i = 0; i < count; i++){
                code = codeComponent.generate(codeName, warehouse.getShelfCodeLength(), enterpriseId);
            }
            String endCode = code;

            responseBatchCodeVO.setStartCode(batchCodeVO.getPrefix() + spacer + startCode);
            responseBatchCodeVO.setEndCode(batchCodeVO.getPrefix() + spacer + endCode);
        }

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,responseBatchCodeVO);

        return result;

    }

    @ApiOperation(value = "批量添加货位", notes = "批量添加货位 | 开发者 孙腾 | 已联调")
    @ApiImplicitParam(name = "warehouseShelfVO", value = "生批量添加货位", required = true, dataType = "RequestWarehouseShelfBatchAddVO")
    @RequestMapping(value = "/saveBatchShelf", method = RequestMethod.POST)
    public Result<ResponseBatchCodeVO> saveBatchShelf(HttpSession session, @RequestBody RequestWarehouseShelfBatchAddVO warehouseShelfVO) throws Exception {
        Result<ResponseBatchCodeVO> result = new Result<>();

        UserVO userVO = (UserVO) session.getAttribute("user");
        WarehouseShelf warehouseShelf = new WarehouseShelf();
        BeanUtils.copyProperties(warehouseShelfVO,warehouseShelf);
        Warehouse warehouse = warehouseService.getWarehouseByKey(warehouseShelf.getWarehouseId());//仓库对象
        WarehouseShelf warehouseShelf1 = (WarehouseShelf) EntityUtils.reflectAddSetDefaultValue(warehouseShelf.getClass(),userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(warehouseShelf1,warehouseShelf);
        //货位的前缀0000-01-01（依次:仓库,库区，货区）
        String prefix = warehouseShelf.getPrefix();

        //货区的编码长度
        int shelfCodeLength = warehouse.getShelfCodeLength();
        String start = warehouseShelf.getStartCode().substring(prefix.length(),warehouseShelf.getStartCode().length());

        String end = warehouseShelf.getEndCode().substring(prefix.length(),warehouseShelf.getEndCode().length());

        Integer startNum = Integer.parseInt(start);
        Integer endNum = Integer.parseInt(end);

        //间隔符
        String spacer = warehouse.getSpacer();
        List<WarehouseShelf> list = new ArrayList<>();
        for(int i = startNum ; i < endNum; i++){
            WarehouseShelf warehouseShelfTmp = new WarehouseShelf();
            BeanUtils.copyProperties(warehouseShelf,warehouseShelfTmp);
            warehouseShelfTmp.setCode(prefix + spacer + i);
            warehouseShelfTmp.setName(prefix + spacer + i);
            warehouseShelfTmp.setSysType(0);//批量添加为用户自定义类型
            list.add(warehouseShelfTmp);
        }

        warehouseService.addBatchWarehouseShelf(list);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;

    }


    @ApiOperation(value="根据企业id获取用户列表信息", notes = "根据企业ID获取获取用户列表信息 | 开发者 孙腾 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE )
    @ApiImplicitParam(name = "enterpriseId", value = "企业id", required = true, paramType = "path")
    @RequestMapping(value = "/getUserByEnterpriseId/{enterpriseId}", method = RequestMethod.GET)
    public Result<List<User>> getUserByEnterpriseId(@PathVariable("enterpriseId")Long enterpriseId){
        Result<List<User>> result = new Result<>();
        List<User> list = userComponent.findUserByEnterpriseId(enterpriseId);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        return result;
    }




}
