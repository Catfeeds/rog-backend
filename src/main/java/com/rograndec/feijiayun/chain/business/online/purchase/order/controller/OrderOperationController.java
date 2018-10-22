package com.rograndec.feijiayun.chain.business.online.purchase.order.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.service.UserManagerService;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.PlaceOrderDataVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


@Api(value = "orderOperation", description = "我的订单-订单操作", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/order/operation")
public class OrderOperationController {
	
	private static final Log logger = LogFactory.getLog(OrderOperationController.class);

	@Value("${mph.impl.url}")
	private String mphImplUrl;
	
	@Value("${mph.user.url}")
	private String mphUserUrl;
	
	@Autowired
	private UserManagerService userManagerService;
	
	@ApiOperation(value="查看订单操作-获取请求MPH数据", notes = "查看订单操作-获取请求MPH数据 | 开发者:刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getOrderViewParam", method = RequestMethod.GET)
	@ResponseBody
	public Result<PlaceOrderDataVO> getOrderViewParam(HttpServletRequest request,
			@ApiParam(value = "mph订单ID", required = true) @RequestParam(required=true) Long mphOrderId,
			@ApiParam(value = "mph订单号", required = true) @RequestParam(required=true) String mphOrderCode){
		Result<PlaceOrderDataVO> result = new Result<PlaceOrderDataVO>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	PlaceOrderDataVO vo = new PlaceOrderDataVO();
        	vo.setUrl(mphImplUrl+"saas/auth.html");
        	User user = userManagerService.queryUserByUserId(loginUser.getUserId());
        	if(user == null){
        		result.setBizCodeFallInfo(SysCode.FAIL);
        		return result;
        	}
        	vo.setCode(user.getRgtUserId().longValue());
        	vo.setCallbackUrl(mphUserUrl+"purchase/order/ddxq.html?oId="+mphOrderId);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
        	logger.error("查看订单操作-获取数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="立即支付操作-获取请求MPH数据", notes = "立即支付操作-获取请求MPH数据 | 开发者:刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getGoPaymentParam", method = RequestMethod.GET)
	@ResponseBody
	public Result<PlaceOrderDataVO> getGoPaymentParam(HttpServletRequest request,
			@ApiParam(value = "mph订单ID", required = true) @RequestParam(required=true) Long mphOrderId,
			@ApiParam(value = "mph订单号", required = true) @RequestParam(required=true) String mphOrderCode){
		Result<PlaceOrderDataVO> result = new Result<PlaceOrderDataVO>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	PlaceOrderDataVO vo = new PlaceOrderDataVO();
        	
        	User user = userManagerService.queryUserByUserId(loginUser.getUserId());
        	if(user == null){
        		result.setBizCodeFallInfo(SysCode.FAIL);
        		return result;
        	}
        	
        	StringBuilder sb = new StringBuilder();
        	sb.append(mphImplUrl).append("saas/auth.html?code=").append(
        			user.getRgtUserId().longValue()).append("&callbackUrl=").append(
        					"/orderSuccessPurchase.html?oId=").append(mphOrderId);
        	
        	vo.setUrl(sb.toString());
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
        	logger.error("立即支付操作-获取请求MPH数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
}

