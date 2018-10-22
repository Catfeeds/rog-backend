package com.rograndec.feijiayun.chain.business.basic.equipment.controller;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.Equipment;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentMaintance;
import com.rograndec.feijiayun.chain.business.basic.equipment.service.EquipmentMaintanceService;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by dudy on 2017-10-13.
 */
@Api(value = "equipment", description = "设备管理-设备检查、清洁和维护接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/basic/equipment")
@Validated
public class EquipmentMaintanceController {


    @Autowired
    private EquipmentMaintanceService maintanceService;

    @ApiOperation(value="获取设备检查、清洁和维护列表", notes = "获取设备检查、清洁和维护列表  | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/maintance/getPageList", method = RequestMethod.POST)
    public Result<Page<List<EquipmentMaintanceVO>>> getPageList(@RequestBody EquipmentMaintanceRequestVO requestVO, HttpServletRequest request){

        Result<Page<List<EquipmentMaintanceVO>>> result = new Result<>();
        if (requestVO.getPageNo() <= 0 || requestVO.getPageSize() <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Page page = maintanceService.getPageList(requestVO,loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

        return result;

    }


    @ApiOperation(value = "根据设备类型获取设备", notes = "根据设备类型获取设备 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getEquipmentSimpleByTypeId", method = RequestMethod.GET)
    public Result<List<EquipmentSimpleVO>> getEquipmentByParam(HttpSession session, @RequestParam("typeId") Long typeId,@RequestParam("enterpriseId")Long enterpriseId) throws Exception {
        Result<List<EquipmentSimpleVO>> result = new Result<>();

        // 当前登录用户数据
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, maintanceService.getEquipmentSimpleVOList(userVO,enterpriseId,typeId));
        return result;
    }



    @ApiOperation(value="保存设备检查、清洁和维护", notes = "保存设备检查、清洁和维护 | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/maintance/save", method = RequestMethod.POST)
    public Result<String> save(@RequestBody EquipmentMaintanceVO maintanceVO, HttpServletRequest request) throws Exception{
        Result<String> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        maintanceService.save(maintanceVO,loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,"保存成功");
        return  result;
    }

    @ApiOperation(value="修改设备检查、清洁和维护", notes = "修改设备检查、清洁和维护 | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/maintance/update", method = RequestMethod.POST)
    public Result<String>  update(@RequestBody EquipmentMaintanceVO maintanceVO, HttpServletRequest request) throws Exception{
        Result<String> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        maintanceService.update(maintanceVO,loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,"修改成功");
        return  result;
    }

    @RequestMapping(value = "/maintance/delete", method = RequestMethod.GET)
    @ApiOperation(value="删除设备检查、清洁和维护", notes = "删除设备检查、清洁和维护 | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> delete(@ApiParam(value = "主键ID", required = true) @RequestParam Long id){
        Result<String> result = new Result<>();
        maintanceService.delete(id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,"删除成功");
        return  result;
    }


    @ApiOperation(value="查看设备检查、清洁和维护", notes = "获取设备检查、清洁和维护 | 开发者: 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/maintance/get", method = RequestMethod.GET)
    public Result<EquipmentMaintanceVO> get(@ApiParam(value = "主键ID", required = true) @RequestParam Long id){

        Result<EquipmentMaintanceVO> result =new Result();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,maintanceService.get(id));
        return result;
    }

}
