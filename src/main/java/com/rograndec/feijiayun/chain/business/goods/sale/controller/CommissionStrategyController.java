package com.rograndec.feijiayun.chain.business.goods.sale.controller;

import com.rograndec.feijiayun.chain.business.goods.sale.service.CommissionStrategyService;
import com.rograndec.feijiayun.chain.business.goods.sale.vo.CommissionStrategyInfoVO;
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
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by madong on 2017/9/5.
 */
@Api(value = "goods_sale_strategy", description = "商品管理-销售管理-提成设置服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/goods/sale/strategy")
@Validated
public class CommissionStrategyController {
    private static final Log logger = LogFactory.getLog(CommissionStrategyController.class);
    @Autowired
    CommissionStrategyService royaltyStrategyService;

    @ApiOperation(value="保存提成设置", notes = "保存提成设置 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveRoyaltyStrategy", method = RequestMethod.POST)
    @ResponseBody
    public Result saveRoyaltyStrategy(HttpServletRequest request,
           @ApiParam(value = "提成设置信息跟明细信息集合",required = true) @RequestBody CommissionStrategyInfoVO royaltyStrategyInfoVO){
        Result result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(StringUtils.isBlank(royaltyStrategyInfoVO.getCommissionStrategyVO().getCode())){
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"提成设置编码不能为空!");
                return result;
            }else if(ChineseString.isChinese(royaltyStrategyInfoVO.getCommissionStrategyVO().getCode())){
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"提成设置编码不能输入中文!");
                return result;
            }
            royaltyStrategyService.saveRoyaltyStrategy(royaltyStrategyInfoVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存提成设置成功!");
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("保存提成设置错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="修改提成设置", notes = "修改提成设置 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateRoyaltyStrategy", method = RequestMethod.POST)
    @ResponseBody
    public Result updateRoyaltyStrategy(HttpServletRequest request,
           @ApiParam(value = "提成设置信息跟明细信息集合",required = true) @RequestBody CommissionStrategyInfoVO royaltyStrategyInfoVO){
        Result result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            royaltyStrategyService.updateRoyaltyStrategy(royaltyStrategyInfoVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改提成设置成功!");
        }catch(Exception e){
            logger.error("修改提成设置错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="删除提成设置", notes = "删除提成设置 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteRoyaltyStrategy", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> deleteRoyaltyStrategy(HttpServletRequest request,
           @ApiParam(value = "提成设置信息id",required = true) @RequestParam Long id){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(royaltyStrategyService.canDeleteRoyaltyStrategy(id,loginUser)){
                int count = royaltyStrategyService.deleteRoyaltyStrategy(id,loginUser);
                if(count > 0)
                    result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除提成设置成功!");
                else
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "该提成设置已经不再系统中!");
            }else
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "该提成设置已关联商品,不能删除!");
        }catch(Exception e){
            logger.error("删除提成设置错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取所有提成设置", notes = "获取所有提成设置 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getRoyaltyStrategy", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<CommissionStrategyInfoVO>> getRoyaltyStrategy(HttpServletRequest request,
           @ApiParam(value = "排序字段名.多个字段请用','隔开",required = false) @RequestParam(required = false) String orderName,
           @ApiParam(value = "排序方式,ASC或者DESC",required = false) @RequestParam(required = false) String orderType){
        Result<List<CommissionStrategyInfoVO>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<CommissionStrategyInfoVO> royaltyStrategyInfoVOS = royaltyStrategyService.getRoyaltyStrategy(orderName,orderType,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, royaltyStrategyInfoVOS);
        }catch(Exception e){
            logger.error("获取所有提成设置错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="检查提成设置编码是否重复", notes = "检查提成设置编码是否重复 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkExistsCode", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> checkExistsCode(HttpServletRequest request,
           @ApiParam(value = "待检查编码",required = true) @RequestParam String code){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            Long count = royaltyStrategyService.checkExistsCode(loginUser,code);
            if(count.equals(0L))
                result.setBizCodeSuccessInfo(SysCode.SUCCESS, "检查通过");
            else
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"该编码已经存在,请重新输入!");
        }catch(Exception e){
            logger.error("检查提成设置编码是否重复:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="检查提成设置名称是否重复", notes = "检查提成设置名称是否重复 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkExistsName", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> checkExistsName(HttpServletRequest request,
           @ApiParam(value = "待检查名称",required = true) @RequestParam String name){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            Long count = royaltyStrategyService.checkExistsName(loginUser,name);
            if(count.equals(0L))
                result.setBizCodeSuccessInfo(SysCode.SUCCESS, "检查通过");
            else
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"该名称已经存在,请重新输入!");
        }catch(Exception e){
            logger.error("检查提成设置名称是否重复:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
}
