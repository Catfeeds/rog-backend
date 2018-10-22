package com.rograndec.feijiayun.chain.inf.pos.base.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName: HeartbeatController   
 * @Description: POS 接口心跳检测
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月6日 下午5:14:45
 */
@Api(value="HeartbeatController",description = "POS接口心跳检测")
@RestController
@RequestMapping("/inf/pos/base")
public class HeartbeatController {
	
	private static final Logger logger = LoggerFactory.getLogger(HeartbeatController.class);
	
	@ApiOperation(value = "POS接口心跳检测", notes = "POS接口心跳检测 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/heartbeat", method = RequestMethod.POST)
    public Result<String> heartbeat(HttpServletRequest request,HttpServletResponse response) {
		Result<String> result = new Result<>();
        try {
        	// POS接口访问需要指定User-Agent SaaSClient-POS
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, DateUtils.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS));
        } catch (Exception e) {
            logger.error("POS接口心跳检测失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

}
