package com.rograndec.feijiayun.chain.business.basic.store.controller;

import com.rograndec.feijiayun.chain.business.basic.store.entity.StoreLevel;
import com.rograndec.feijiayun.chain.business.basic.store.service.StoreLevelService;
import com.rograndec.feijiayun.chain.business.basic.store.vo.SaveVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
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

/**
 * Created by madong on 2017/8/28.
 */

@Api(value = "store", description = "门店管理-门店级别服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/basic/level")
@Validated
public class StoreLevelController {
    private static final Log logger = LogFactory.getLog(StoreLevelController.class);
    @Autowired
    StoreLevelService storeLevelService;

    @ApiOperation(value="获取所有门店级别信息", notes = "获取所有门店级别信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAllStoreLevel", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<TreePOJO>> getAllStoreLevel(HttpServletRequest request){
        Result<List<TreePOJO>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0)
                throw new Exception("当前登录人员不是总部角色!");
            List<TreePOJO> storeLevels = storeLevelService.getAllStoreLevel(loginUser.getEnterpriseId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, storeLevels);
        }catch(Exception e){
            logger.error("获取所有门店级别信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="新增门店级别", notes = "新增门店级别 | 开发者:马东 | 已联调"+
            "参数说明 : " +
            "\"code\": \"必填\",\n" +
            "  \"name\": \"必填\",\n" +
            "  \"remark\": \"选填\",\n" +
            "  \"saleTaskAmount\": 必填,\n" +
            "  \"saleTaskUnit\": 必填,\n" +
            "  \"status\": 必填,\n" +
            "  \"storeIds\": \"必填\"", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/insertStoreLevel", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> insertStoreLevel(HttpServletRequest request,
                                           @ApiParam(value = "新增商业区",required = true) @RequestBody StoreLevel storeLevel){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0){
                throw new BusinessException("当前登录人员不是总部角色!");
            }
            if(StringUtils.isBlank(storeLevel.getName())){
                throw new BusinessException("门店级别名字不能为空!");
            }
            if(StringUtils.isBlank(storeLevel.getCode())){
                throw new BusinessException("门店级别编码不能为空!");
            }else if(ChineseString.isChinese(storeLevel.getCode())){
                throw new BusinessException("门店级别编码不能含有中文!");
            }
            if(storeLevelService.checkCodeExists(loginUser,storeLevel)){
                throw new BusinessException("门店级别编码或名称重复!");
            }
            storeLevelService.insertStoreLevel(loginUser,storeLevel);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "新增门店级别成功");
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("新增门店级别错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="保存门店级别对应门店", notes = "保存门店级别对应门店 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveStoreLevelShops", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveStoreLevelShops(HttpServletRequest request,
           @ApiParam(value = "门店级别", name = "saveVO", required = true) @RequestBody SaveVO saveVO){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0)
                throw new Exception("当前登录人员不是总部角色!");
            storeLevelService.saveStoreLevelShops(loginUser,saveVO.getId(),saveVO.getShopIds());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存商业区对应门店成功");
        }catch(Exception e){
            logger.error("保存商业区对应门店错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取门店", notes = "获取所有门店,该级别已有的门店不在显示 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getNoChooseShops", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<StoreVO>> getNoChooseShops(HttpServletRequest request){
        Result<List<StoreVO>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0)
                throw new Exception("当前登录人员不是总部角色!");
            List<StoreVO> shops = storeLevelService.getNoChooseShops(loginUser.getEnterpriseId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, shops);
        }catch(Exception e){
            logger.error("获取门店错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="修改级别信息", notes = "修改级别信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateStoreLevel", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateStoreLevel(HttpServletRequest request,
           @ApiParam(value = "需要修改的级别信息", required = true) @RequestBody StoreLevel storeLevel){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0){
                throw new BusinessException("当前登录人员不是总部角色!");
            }
            if(StringUtils.isBlank(storeLevel.getName())){
                throw new BusinessException("门店级别名字不能为空!");
            }
            if(StringUtils.isBlank(storeLevel.getCode())){
                throw new BusinessException("门店级别编码不能为空!");
            }else if(ChineseString.isChinese(storeLevel.getCode())){
                throw new BusinessException("门店级别编码不能含有中文!");
            }
            if(storeLevelService.checkCodeExists(loginUser,storeLevel)){
                throw new BusinessException("门店级别编码或名称重复!");
            }
            storeLevelService.updateStoreLevel(loginUser,storeLevel);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改级别信息成功");
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("修改级别信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="移除门店", notes = "移除门店 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/removeStoreLevelShop", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> removeStoreLevelShop(HttpServletRequest request,
           @ApiParam(value = "级别ID",required = true) @RequestParam Long id,
           @ApiParam(value = "门店ID,如果移除的是级别下的所有门店,则传-1,否则传门店对应id",required = true) @RequestParam Long shopId){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0)
                throw new Exception("当前登录人员不是总部角色!");
            if(shopId == -1l)
                storeLevelService.removeAllStoreLevelShop(loginUser,id);
            if(shopId != -1l)
                storeLevelService.removeStoreLevelShop(loginUser,id,shopId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "移除门店成功");
        }catch(Exception e){
            logger.error("移除门店错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="删除门店级别", notes = "删除门店级别 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteStoreLevel", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> deleteStoreLevel(HttpServletRequest request,
           @ApiParam(value = "需要删除的门店级别ID",required = true) @RequestParam Long id){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0)
                throw new Exception("当前登录人员不是总部角色!");
            if(storeLevelService.canDelete(loginUser,id)){
                int count = storeLevelService.deleteStoreLevel(id);
                if(count != 0)
                    result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除门店级别成功!");
                else
                    result.setBizCodeSuccessInfo(SysCode.FAIL, "该门店级别已经不存在!");
            }else{
                result.setBizCodeSuccessInfo(SysCode.FAIL, "当前级别下已关联门店,不能删除!若要删除,请先移除当前级别下的所有门店~");
            }
        }catch(Exception e){
            logger.error("删除门店级别错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="导出门店级别信息", notes = "导出门店级别信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/exportStoreLevel", method = RequestMethod.GET)
    @ResponseBody
    public void exportStoreLevel(HttpServletRequest request, HttpServletResponse response){
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(loginUser.getParentId() != 0)
                throw new Exception("当前登录人员不是总部角色!");
            String name = "门店级别导出结果.xlsx";
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            storeLevelService.excelExport(loginUser,output);
            output.close();
            output.flush();
        }catch(Exception e){
            logger.error("导出门店级别信息错误:"+e.getMessage(),e);
        }
    }

}
