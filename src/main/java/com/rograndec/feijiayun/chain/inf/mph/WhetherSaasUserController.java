package com.rograndec.feijiayun.chain.inf.mph;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
import com.rograndec.feijiayun.chain.inf.mph.service.WhetherSaasUserService;

@Api(value = "removeSaasCart", description = "判断是否saas用户", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/fast/loan")
public class WhetherSaasUserController {

	private static final Log logger = LogFactory.getLog(WhetherSaasUserController.class);
	
	@Autowired
	private WhetherSaasUserService whetherSaasUserService;

	
	@ApiOperation(value="判断是否saas用户", notes = "判断是否saas用户 | 开发者:刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/isSaasUser", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> isSaasUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Result<String> result = new Result<String>();
		try {
			String dataJson = request.getParameter("dataJson") == null ? "" : request.getParameter("dataJson").toString();
//			dataJson="{\"uId\":100896891,\"eId\":6450925}";
			logger.error("----判断是否saas用户参数----" + dataJson);
			if(StringUtils.isNotBlank(dataJson)){
				
				JSONObject ob = JSONObject.parseObject(dataJson);
				String flag = whetherSaasUserService.isSaasUser(ob);
				//flag:0是，1否
				result.setBizCodeSuccessInfo(SysCode.SUCCESS, flag);
			}
		}  catch (Exception e) {
			result.setBizCodeSuccessInfo(SysCode.FAIL, "");
			e.printStackTrace();
			logger.error("判断是否saas用户出错，错误信息：" + e.getMessage(), e);
		}
		return result;
	}
}
