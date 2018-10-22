package com.rograndec.feijiayun.chain.business.finance.set.period.controller;

import com.rograndec.feijiayun.chain.business.finance.set.period.service.AccountingPeriodService;
import com.rograndec.feijiayun.chain.business.finance.set.period.valid.AccountingPeriodCheck;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.AccountingFreshVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.AccountingPeriodFreshVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.AccountingPeriodVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.RequestAccountingPeriodVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.YearAndMonthVO;
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
 * @ClassName: AccountingPeriodController
 * @Description: 财务管理-设置-会计期间
 * @author dongdong.zhang
 * @version 1.0
 * @date 2018年1月4日 上午10:32
 */
@Api(value = "accounting_period",description = "财务管理-设置-会计期间")
@RestController
@RequestMapping("finance/set/period")
public class AccountingPeriodController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountingPeriodService accountingPeriodService;
    
    @ApiOperation(value = "新增会计期间", notes = "新增会计期间 | 开发者 张东东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PostMapping(value = "/addPeriod")
    @ApiImplicitParam(name = "requestAccountingPeriodVO", value = "会计期间实体对象", required = true, dataType = "RequestAccountingPeriodVO")
    public Result<String> addPeriod(@ApiIgnore UserVO userVO, @Valid @RequestBody RequestAccountingPeriodVO requestAccountingPeriodVO){
        Result<String> result = new Result<>();
        try{
        	accountingPeriodService.addPeriod(userVO, requestAccountingPeriodVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("新增/修改收款详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("新增/修改收款详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    
    @ApiOperation(value = "修改会计期间", notes = "修改会计期间 | 开发者 张东东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PostMapping(value = "/updatePeriod")
    @ApiImplicitParam(name = "requestAccountingPeriodVO", value = "会计期间实体对象", required = true, dataType = "RequestAccountingPeriodVO")
    public Result<String> updatePeriod(@ApiIgnore UserVO userVO, @Valid @RequestBody RequestAccountingPeriodVO requestAccountingPeriodVO){
        Result<String> result = new Result<>();
        try{
        	accountingPeriodService.updatePeriod(userVO, requestAccountingPeriodVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("新增/修改收款详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("新增/修改收款详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "会计期间分页列表", notes = "会计期间分页列表 | 开发者 张东东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @GetMapping(value = "/getPeriodPage")
    public Result<Page<List<AccountingPeriodVO>>> getPeriodPage(@ApiIgnore UserVO userVO,@ApiParam(value = "页码", required = true) @RequestParam Integer pageNo
    		,@ApiParam(value = "每页显示条数", required = true) @RequestParam Integer pageSize){
        Result<Page<List<AccountingPeriodVO>>> result = new Result<>();
        try{
        	if(pageNo <= 0 || pageSize <=0) throw new BusinessException("分页参数不合法");
        	Page<List<AccountingPeriodVO>> page = new Page<>(pageNo,pageSize);
        	accountingPeriodService.getPeriodPage(userVO, page);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        }catch(BusinessException e){
            logger.error("会计期间分页列表查询错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("会计期间分页列表查询错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "会计期间查看详细", notes = "会计期间查看详细 | 开发者 张东东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @GetMapping(value = "/getPeriodDetail")
    public Result<AccountingPeriodVO> getPeriodDetail(@ApiIgnore UserVO userVO, @ApiParam(value = "当前需要查看的ID", required = true) @RequestParam Long id){
        Result<AccountingPeriodVO> result = new Result<>();
        try{
        	AccountingPeriodVO accountingPeriodVO = accountingPeriodService.getPeriodDetail(userVO, id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,accountingPeriodVO);
        }catch(BusinessException e){
            logger.error("会计期间查看详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("会计期间查看详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "删除会计期间", notes = "删除会计期间| 开发者 张东东 | 已联调")
    @ApiImplicitParam(name = "id", value = "要删除的会计期间id", required = true, paramType="path")
    @RequestMapping(value = "/removePeriod/{id}", method = RequestMethod.POST)
    public Result<String> removePeriod(@ApiIgnore UserVO userVO, @PathVariable(value = "id", required = true) Long id) {

        Result<String> result = new Result<>();
        try{
        	accountingPeriodService.removePeriod(userVO, id);
        }catch(BusinessException e){
            logger.error("会计期间查看详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("会计期间查看详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return  result;
    }
    
   /* @ApiOperation(value = "获取新增会计期间年度", notes = "获取新增会计期间年度 | 开发者 张东东 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PostMapping(value = "/getPeriodYear")
    public Result<String> getPeriodYear(@ApiIgnore UserVO userVO){
        Result<String> result = new Result<>();
        try{
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,accountingPeriodService.getPeriodYear(userVO)+"");
        }catch(BusinessException e){
            logger.error("获取新增会计期间年度错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("获取新增会计期间年度错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    
    @ApiOperation(value = "获取新增会计期间默认开始日期", notes = "获取新增会计期间默认开始日期 | 开发者 张东东 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PostMapping(value = "/getPeriodYearStartDate")
    public Result<String> getPeriodYearStartDate(@ApiIgnore UserVO userVO){
        Result<String> result = new Result<>();
        try{
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,accountingPeriodService.getPeriodYearStartDate(userVO));
        }catch(BusinessException e){
            logger.error("获取新增会计期间年度错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("获取新增会计期间年度错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }*/
    
    @ApiOperation(value = "会计期间详情刷新", notes = "会计期间详情刷新 | 开发者 张东东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PostMapping(value = "/getPeriodDetailForFresh")
    @ApiImplicitParam(name = "accountingFreshVO", value = "会计期间实体对象", required = true, dataType = "AccountingFreshVO")
    public Result<AccountingFreshVO> getPeriodDetailForFresh(@ApiIgnore UserVO userVO, 
    		@Valid @RequestBody AccountingFreshVO accountingFreshVO){
        Result<AccountingFreshVO> result = new Result<>();
        try{
        	accountingPeriodService.getPeriodDetailForFresh(userVO, accountingFreshVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,accountingFreshVO);
        }catch(BusinessException e){
            logger.error("会计期间详情刷新:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("会计期间详情刷新:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    
    @ApiOperation(value = "获取新增会计期间年度和开始日期", notes = "获取新增会计期间年度和开始日期 | 开发者 张东东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @GetMapping(value = "/getPeriodYearAndMonth")
    public Result<YearAndMonthVO> getPeriodYearAndMonth(@ApiIgnore UserVO userVO){
        Result<YearAndMonthVO> result = new Result<>();
        try{
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,accountingPeriodService.getPeriodYearAndMonth(userVO));
        }catch(BusinessException e){
            logger.error("获取新增会计期间年度错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("获取新增会计期间年度错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
}
