package com.rograndec.feijiayun.chain.business.goods.sale.controller;

import com.rograndec.feijiayun.chain.business.goods.sale.service.SpecialPriceStrategyService;
import com.rograndec.feijiayun.chain.business.goods.sale.vo.SpecialPriceStrategyVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
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
@Api(value = "goods_sale_special", description = "商品管理-销售管理-特价设置服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/goods/sale/special")
@Validated
public class SpecialPriceStrategyController {
    private static final Log logger = LogFactory.getLog(SpecialPriceStrategyController.class);
    @Autowired
    SpecialPriceStrategyService specialPriceStrategyService;

    @ApiOperation(value="保存特价设置", notes = "保存特价设置 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveSpecialPrice", method = RequestMethod.POST)
    @ResponseBody
    public Result saveSpecialPrice(HttpServletRequest request,
           @ApiParam(value = "特价设置信息",required = true) @RequestBody SpecialPriceStrategyVO specialPriceStrategyVO){
        Result result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(StringUtils.isBlank(specialPriceStrategyVO.getCode())){
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"特价设置编码不能为空!");
                return result;
            }else if(ChineseString.isChinese(specialPriceStrategyVO.getCode())){
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"特价设置编码不能输入中文!");
                return result;
            }
            specialPriceStrategyService.saveSpecialPrice(specialPriceStrategyVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存特价设置成功!");
        }catch(Exception e){
            logger.error("保存特价设置错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="修改特价设置", notes = "修改特价设置 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateSpecialPrice", method = RequestMethod.POST)
    @ResponseBody
    public Result updateSpecialPrice(HttpServletRequest request,
           @ApiParam(value = "特价设置信息",required = true) @RequestBody SpecialPriceStrategyVO specialPriceStrategyVO){
        Result result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            specialPriceStrategyService.updateSpecialPrice(specialPriceStrategyVO, loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改特价设置成功!");
        }catch(Exception e){
            logger.error("修改特价设置错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value="删除特价设置", notes = "删除特价设置 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteSpecialPrice", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> deleteSpecialPrice(HttpServletRequest request,
           @ApiParam(value = "需要删除的特价设置信息id",required = true) @RequestParam Long id){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if(specialPriceStrategyService.canDeleteSpecialPrice(id, loginUser)){
                int count = specialPriceStrategyService.deleteSpecialPrice(id, loginUser);
                if(count == 0)
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "该特价设置已经不再系统中!");
                else
                    result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除特价设置成功!");
            }else {
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "该特价设置已管理商品,不能删除!");
            }

        }catch(Exception e){
            logger.error("删除特价设置错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取所有特价设置", notes = "获取所有特价设置 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSpecialPrice", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SpecialPriceStrategyVO>> getSpecialPrice(HttpServletRequest request,
           @ApiParam(value = "待排序字段",required = false) @RequestParam(required = false) String orderName,
           @ApiParam(value = "排序方式 升序ASC,降序DESC",required = false) @RequestParam(required = false) String orderType){
        Result<List<SpecialPriceStrategyVO>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<SpecialPriceStrategyVO> specialPriceStrategyVOList = specialPriceStrategyService.getSpecialPrice(loginUser,orderName,orderType);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, specialPriceStrategyVOList);
        }catch(Exception e){
            logger.error("删除特价设置错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="检查特价设置编码是否重复", notes = "检查特价设置编码是否重复 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkExistsCode", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> checkExistsCode(HttpServletRequest request,
           @ApiParam(value = "待检查编码",required = true) @RequestParam String code){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            Long count = specialPriceStrategyService.checkExistsCode(loginUser,code);
            if(count.equals(0l))
                result.setBizCodeSuccessInfo(SysCode.SUCCESS, "检查通过");
            else
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"该编码已经存在,请重新输入!");
        }catch(Exception e){
            logger.error("检查特价设置编码是否重复:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

}
