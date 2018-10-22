package com.rograndec.feijiayun.chain.business.basic.store.controller;

import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleArea;
import com.rograndec.feijiayun.chain.business.basic.store.service.SaleAreaService;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by madong on 2017/8/28.
 */
@Api(value = "store", description = "门店管理-销售片区服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/basic/area")
@Validated
public class SaleAreaController {
    private static final Log logger = LogFactory.getLog(SaleAreaController.class);
    @Autowired
    SaleAreaService saleAreaService;

    @ApiOperation(value="获取销售片区上级区域", notes = "获取销售片区上级区域 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSaleAreaParent", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<SaleArea>> getSaleAreaParent(HttpServletRequest request){
        Result<List<SaleArea>> result = new Result<>();
        try{

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            List<SaleArea> saleArea = saleAreaService.getSaleAreaParent(loginUser.getEnterpriseId());
            if(saleArea == null){

                result.setBizCodeSuccessInfo(SysCode.FAIL, saleArea);

                return result;
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, saleArea);
        }catch(Exception e){
            logger.error("获取销售片区基本信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
	@ApiOperation(value="获取销售片区基本信息", notes = "获取销售片区基本信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSaleArea", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<TreePOJO>> getSaleArea(HttpServletRequest request){
        Result<List<TreePOJO>> result = new Result<>();
        try{

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            List<TreePOJO> saleArea = saleAreaService.getSaleArea(loginUser.getEnterpriseId());
            if(saleArea == null){

                result.setBizCodeSuccessInfo(SysCode.FAIL, saleArea);

                return result;
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, saleArea);
        }catch(Exception e){
            logger.error("获取销售片区基本信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取总部下所有门店信息", notes = "获取总部下所有门店信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getShops", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<StoreVO>> getShops(HttpServletRequest request,
            @ApiParam(value = "片区id",required = true) @RequestParam Long id){
        Result<List<StoreVO>> result = new Result<>();
        try{

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0){
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"当前登录人员不是总部角色!");
                return result;
            }
            List<StoreVO> shops = saleAreaService.getShops(id,loginUser.getEnterpriseId());
            if(shops == null){
                result.setBizCodeSuccessInfo(SysCode.FAIL, shops);
                return result;
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, shops);
        }catch(Exception e){
            logger.error("获取总部下所有门店信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="删除销售片区", notes = "删除销售片区信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteSaleArea", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> deleteSaleArea(HttpServletRequest request,
            @ApiParam(value = "销售片区ID", required = true) @RequestParam Long id){
        Result<String> result = new Result<>();
        try{

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0){
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"当前登录人员不是总部角色!");
                return result;
            }
            if(saleAreaService.canDelete(loginUser,id)) {
                int count = saleAreaService.deleteSaleArea(id, loginUser.getEnterpriseId());//分别删除saas_sale_area表和saas_sale_area_store表
                if (count > 0)
                    result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除销售片区成功!");
                else
                    result.setBizCodeSuccessInfo(SysCode.FAIL, "该销售片区已经不存在于系统中!");
            }else {
                result.setBizCodeSuccessInfo(SysCode.FAIL, "该销售片区已关联门店,请先移除关联的门店再删除!");
            }
        }catch(Exception e){
            logger.error("删除销售片区信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="移除销售片区下的所有门店信息", notes = "移除销售片区下的所有门店信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/removeSaleAreaShop", method = RequestMethod.POST)
    @ResponseBody
    //有门店才能有移除按钮
    public Result<String> removeSaleAreaShop(HttpServletRequest request,
           @ApiParam(value = "销售片区ID", required = true) @RequestParam Long id,
           @ApiParam(value = "需要移除的门店ID,如果要移除整个销售片区,传-1", required = true) @RequestParam Long shopId){
        Result<String> result = new Result<>();
        try{

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0){
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"当前登录人员不是总部角色!");
                return result;
            }
            saleAreaService.removeSaleAreaShop(id,loginUser.getEnterpriseId(), shopId);//分别删除saas_sale_area表和saas_sale_area_store表
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "移除销售片区下的所有门店信息成功");
        }catch(Exception e){
            logger.error("移除销售片区下的所有门店信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="保存新增的销售片区", notes = "保存新增的销售片区 | 开发者:马东 | 已联调 " +
            "参数说明: " +
            "  \"code\": \"必填\",\n" +
            "  \"name\": \"必填\",\n" +
            "  \"parentAreaId\": 必填,\n" +
            "  \"remark\": \"选填\",\n" +
            "  \"status\": 必填,\n" +
            "  \"storeIds\": \"必填\"", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveSaleArea", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveSaleArea(HttpServletRequest request,
        @ApiParam(value = "销售片区", required = true) @RequestBody SaleArea saleArea){
        Result<String> result = new Result<>();
        try{

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0){
                throw new BusinessException("当前登录人员不是总部角色!");
            }
            if(StringUtils.isBlank(saleArea.getName())){
                throw new BusinessException("销售片区名字不能为空!");
            }
            if(StringUtils.isBlank(saleArea.getCode())){
                throw new BusinessException("销售片区编码不能为空!");
            }else if(ChineseString.isChinese(saleArea.getCode())){
                throw new BusinessException("销售片区编码不能含有中文!");
            }
            if(saleAreaService.checkCodeExists(loginUser,saleArea)){
                throw new BusinessException("销售片区编码或名称重复!");
            }
            saleAreaService.saveSaleArea(saleArea,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存新增的销售片区成功");
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
        }catch(Exception e){
            logger.error("保存新增的销售片区错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

//    @ApiOperation(value="保存新增的销售片区下的门店集合", notes = "保存新增的销售片区下的门店集合 | 开发者:马东", produces = MediaType.APPLICATION_JSON_VALUE)
//    @RequestMapping(value = "/saveSaleAreaShops", method = RequestMethod.POST)
//    @ResponseBodyEntity
//    public Result<String> saveSaleAreaShops(HttpServletRequest request,
//           @ApiParam(value = "门店集合" , required = true) @RequestBody List<Enterprise> shops){
//        Result<String> result = new Result<>();
//        try{
//
//            HttpSession session = request.getSession(true);
//            UserVO loginUser = (UserVO) session.getAttribute("user");
//            if(loginUser.getParentId() != 0)
//                throw new Exception("当前登录人员不是总部角色!");
//            saleAreaService.saveSaleAreaShops(shops,loginUser);
//            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存新增的销售片区下的门店集合成功");
//        }catch(Exception e){
//            logger.error("保存新增的销售片区下的门店集合错误:"+e.getMessage(),e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//            return result;
//        }
//        return result;
//    }

    @ApiOperation(value="修改销售片区", notes = "修改销售片区 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateSaleArea", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateSaleArea(HttpServletRequest request,
            @ApiParam(value = "销售片区" , required = true) @RequestBody SaleArea saleArea){
        Result<String> result = new Result<>();
        try{

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0){
                throw new BusinessException("当前登录人员不是总部角色!");
            }
            if(StringUtils.isBlank(saleArea.getName())){
                throw new BusinessException("销售片区名字不能为空!");
            }
            if(StringUtils.isBlank(saleArea.getCode())){
                throw new BusinessException("销售片区编码不能为空!");
            }else if(ChineseString.isChinese(saleArea.getCode())){
                throw new BusinessException("销售片区编码不能含有中文!");
            }
            if(saleAreaService.checkCodeExists(loginUser,saleArea)){
                throw new BusinessException("销售片区编码或名称重复!");
            }
            saleAreaService.updateSaleArea(saleArea,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改销售片区成功");
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
        }catch(Exception e){
            logger.error("修改销售片区错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    @ApiOperation(value="修改销售片区对应的门店集合", notes = "修改销售片区对应的门店集合 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateSaleAreaShops", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateSaleAreaShops(HttpServletRequest request,
            @ApiParam(value = "销售片区ID" , required = true) @RequestParam Long id,
            @ApiParam(value = "门店集合ID" , required = true) @RequestParam List<Long> shopIds){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0){
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"当前登录人员不是总部角色!");
                return result;
            }
            saleAreaService.updateSaleAreaShops(id,shopIds,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"修改销售片区对应的门店集合成功");
        }catch (Exception e){
            logger.error("修改销售片区对应的门店集合错误"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="导出销售片区信息", notes = "导出销售片区信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/exportSaleArea", method = RequestMethod.GET)
    @ResponseBody
    public void exportSaleArea(HttpServletRequest request,HttpServletResponse response){
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0)
                throw new Exception("当前登录人员不是总部角色!");
            String name = "销售片区导出结果.xlsx";
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            saleAreaService.excelExport(loginUser,output);
            output.close();
            output.flush();
        }catch(Exception e){
            logger.error("导出销售片区信息错误:"+e.getMessage(),e);
        }
    }

}
