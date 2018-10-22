package com.rograndec.feijiayun.chain.common.advice;

import com.rograndec.feijiayun.chain.business.auth.login.exception.LoginBizException;
import com.rograndec.feijiayun.chain.business.auth.register.exception.RegisterBizException;
import com.rograndec.feijiayun.chain.business.basic.user.exception.UserManagerBizException;
import com.rograndec.feijiayun.chain.business.distr.branch.exception.DistrInReturnBizException;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.exception.PrepayInvoiceBizException;
import com.rograndec.feijiayun.chain.business.goods.info.exception.GoodsBizException;
import com.rograndec.feijiayun.chain.business.goods.price.exception.PriceOrderBizException;
import com.rograndec.feijiayun.chain.business.system.approval.exception.ApprovalFlowBizException;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.rogrand.exception.RGTBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 全局response 的exception拦截返回封装
 * Created by zhaiwei on 2017/8/26.
 */
@ControllerAdvice
public class ResponseExceptionAdvice {

    private Logger logger = LoggerFactory.getLogger(ResponseExceptionAdvice.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.error(String.format("remote host %s ,uri %s , referer %s", request.getRemoteHost(), request.getRequestURI(), request.getHeader(HttpHeaders.REFERER)));
        logger.error(e.getMessage(), e);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> listFieldError = bindingResult.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(FieldError fieldError : listFieldError){
            if(i > 0){
                sb.append(",");
            }
            sb.append(fieldError.getDefaultMessage());
            i++;
        }
        Result<Object> result = new Result<Object>();
        result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,sb.toString());
        return result;
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result constraintViolationException(ConstraintViolationException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.error(String.format("remote host %s ,uri %s , referer %s", request.getRemoteHost(), request.getRequestURI(), request.getHeader(HttpHeaders.REFERER)));
        logger.error(e.getMessage(), e);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        Set<ConstraintViolation<?>> constrainviolationSet = e.getConstraintViolations();
        StringBuffer sb = new StringBuffer();
        for(ConstraintViolation<?> constraintViolation : constrainviolationSet){
            sb.append(constraintViolation.getMessageTemplate());
        }
        Result<Object> result = new Result<Object>();
        result.setBizCodeFallInfo(SysCode.FAIL,sb.toString());
        return result;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result handleException(Throwable e, HttpServletResponse response) throws IOException {
        logger.error(e.getMessage(), e);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        Result<Object> result = new Result<Object>();

        if(e.getCause() instanceof LoginBizException || e instanceof LoginBizException) {
            result.setBizCodeFallInfo(SysCode.FORBIDDEN.getCode(), e.getMessage());
        }else if(e.getCause() instanceof ValidationException || e instanceof ValidationException){
            result.setBizCodeFallInfo(SysCode.FAIL,e.getCause().getMessage());
        }else if(e.getCause() instanceof RGTBusinessException || e instanceof RGTBusinessException){
            result.setBizCodeFallInfo(SysCode.FAIL,StringUtils.isEmpty(e.getMessage()) ? "融贯通异常" : e.getMessage());
        }else if(e.getCause() instanceof RegisterBizException || e instanceof RegisterBizException){
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
        }else if(e.getCause() instanceof DuplicateKeyException || e instanceof DuplicateKeyException){
            result.setBizCodeFallInfo(SysCode.FAIL,"编码或者名称重复!");
        }else if(e.getCause() instanceof UserManagerBizException || e instanceof UserManagerBizException){
            result.setBizCodeFallInfo(SysCode.FAIL, StringUtils.isEmpty(e.getMessage()) ? e.getCause().getMessage() : e.getMessage());
        }else if(e.getCause() instanceof GoodsBizException || e instanceof GoodsBizException){
            result.setBizCodeFallInfo(SysCode.FAIL, StringUtils.isEmpty(e.getMessage()) ? e.getCause().getMessage() : e.getMessage());
        }else if(e.getCause() instanceof ApprovalFlowBizException || e instanceof ApprovalFlowBizException){
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
        }else if(e.getCause() instanceof DistrInReturnBizException || e instanceof DistrInReturnBizException){
            result.setBizCodeFallInfo(SysCode.FAIL, StringUtils.isEmpty(e.getMessage()) ? e.getCause().getMessage() : e.getMessage());
        }else if(e.getCause() instanceof PrepayInvoiceBizException || e instanceof PrepayInvoiceBizException){
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
        }else if(e.getCause() instanceof PriceOrderBizException || e instanceof PriceOrderBizException){
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
        }else if(e.getCause() instanceof BusinessException || e instanceof BusinessException){
            result.setBizCodeFallInfo(SysCode.FAIL,StringUtils.isEmpty(e.getMessage()) ? e.getCause().getMessage() : e.getMessage());
        }else{
            result.setBizCodeFallInfo(SysCode.FAIL,"系统异常!!!");
        }

        return result;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result businessException(BusinessException e, HttpServletResponse response) throws IOException {
        logger.error(e.getMessage(), e);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        Result<Object> result = new Result<Object>();
        if(e.getCause() instanceof BusinessException || e instanceof BusinessException){
        	result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
        }else{
            result.setBizCodeFallInfo(SysCode.FAIL,"系统异常!!!");
        }
        return result;
    }




}

