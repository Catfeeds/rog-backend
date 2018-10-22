package com.rograndec.feijiayun.chain.inf.mph;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.inf.mph.service.RemoveSaasCartService;

@Api(value = "removeSaasCart", description = "医药网下单后删除Saas购物车", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/online/purchase")
public class RemoveSaasCartController {

	private static final Log logger = LogFactory.getLog(RemoveSaasCartController.class);
	
	@Autowired
	private RemoveSaasCartService removeSaasCartService;

	
	@ApiOperation(value="删除Saas购物车数据", notes = "删除Saas购物车数据 | 开发者:刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/deleteCartByMph", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> deleteCartByMph(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Result<String> result = new Result<String>();
		try {
			String dataJson = request.getParameter("dataJson") == null ? "" : request.getParameter("dataJson").toString();
//			dataJson="{\"gid\":[9285428,9285429],\"uId\":10089689,\"eId\":6450925,\"saasType\":4}";
//			dataJson="{\"gid\":[11838581,11710946,11710945,9069614,11838608],\"uId\":9405487,\"saasType\":\"3\",\"eId\":6007379}";
//			logger.info("----删除Saas购物车参数----" + dataJson);
			if(StringUtils.isNotBlank(dataJson)){
				
				JSONObject ob = JSONObject.parseObject(dataJson);
				removeSaasCartService.deleteCartByMph(ob);
				result.setBizCodeSuccessInfo(SysCode.SUCCESS, "");
			}
		}  catch (Exception e) {
			e.printStackTrace();
			logger.error("删除Saas购物车数据出错，错误信息：" + e.getMessage(), e);
		}
		return result;
	}
}
