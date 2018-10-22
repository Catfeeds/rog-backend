package com.rograndec.feijiayun.chain.business.finance.finalsettle.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.finance.set.period.service.AccountingPeriodService;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.FinalSettleVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.RequestFinalSettleVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.ResponseFinalSettleVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.*;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 *
 * @ClassName: FinalSettleController
 * @Description: 财务管理-期末结账
 * @author dongdong.zhang
 * @version 1.0
 * @date 2018年1月4日 上午10:32
 */
@Api(value = "accounting_finalsettle",description = "财务管理-期末结账")
@RestController
@RequestMapping("finance/finalsettle")
public class FinalSettleController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountingPeriodService accountingPeriodService;
    
    @ApiOperation(value = "结账", notes = "结账 | 开发者 张东东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PostMapping(value = "/addFinalSettle")
    @ApiImplicitParam(name = "requestFinalSettleVO", value = "结账实体对象", required = true, dataType = "RequestFinalSettleVO")
    public Result<String> addFinalSettle(@ApiIgnore UserVO userVO,@Valid @RequestBody RequestFinalSettleVO requestFinalSettleVO){
        Result<String> result = new Result<>();
        try{
        	accountingPeriodService.addFinalSettle(userVO, requestFinalSettleVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("结账异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("结账异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "结账分页列表", notes = "结账分页列表 | 开发者 张东东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @GetMapping(value = "/getFinalSettlePage")
    public Result<Page<List<ResponseFinalSettleVO>>> getFinalSettlePage(@ApiIgnore UserVO userVO,@ApiParam(value = "页码", required = true)
    @RequestParam Integer pageNo, @ApiParam(value = "每页显示条数", required = true) @RequestParam Integer pageSize){
        Result<Page<List<ResponseFinalSettleVO>>> result = new Result<>();
        try{
        	if(pageNo <= 0 || pageSize <=0) throw new BusinessException("分页参数不合法");
        	Page<List<ResponseFinalSettleVO>> page = new Page<>(pageNo,pageSize);
        	accountingPeriodService.getFinalSettlePage(userVO, page);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        }catch(BusinessException e){
            logger.error("结账分页列表查询错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("结账分页列表查询错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    
    @ApiOperation(value = "结账获取结账信息", notes = "结账获取结账信息 | 开发者 张东东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @GetMapping(value = "/getFinalSettlePeriod")
    public Result<FinalSettleVO> getFinalSettlePeriod(@ApiIgnore UserVO userVO){
        Result<FinalSettleVO> result = new Result<>();
        try{
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,accountingPeriodService.getFinalSettlePeriod(userVO));
        }catch(BusinessException e){
            logger.error("结账分页列表查询错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("结账分页列表查询错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    
    @ApiOperation(value = "获取结账人员信息", notes = "获取结账人员信息 | 开发者 张东东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @GetMapping(value = "/getFinalSettleUser")
    public Result<List<User>> getFinalSettleUser(@ApiIgnore UserVO userVO){
        Result<List<User>> result = new Result<>();
        try{
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,accountingPeriodService.getFinalSettleUser(userVO));
        }catch(BusinessException e){
            logger.error("获取结账人员信息查询错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("获取结账人员信息查询错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

}
