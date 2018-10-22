package com.rograndec.feijiayun.chain.business.system.enterprise.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.system.enterprise.service.LocationService;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.SelectBeanVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;



@Api(value = "location", description = "系统管理-企业信息-行政区域服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/location")
public class LocationController {
	
	private static final Log logger = LogFactory.getLog(LocationController.class);

	@Autowired
	LocationService locationService;
	
	@ApiOperation(value="获取省级行政区域信息", notes = "获取省级行政区域信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getProvinceLocation", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBeanVO>> getProvinceLocation(HttpServletRequest request){
		Result<List<SelectBeanVO>> result = new Result<List<SelectBeanVO>>();
        try{
        	List<SelectBeanVO> list = locationService.selectProvinceLocation();
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
        	logger.error("获取省级行政区域信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取市级行政区域信息", notes = "获取市级行政区域信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getCityLocation", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBeanVO>> getCityLocation(HttpServletRequest request,
			@ApiParam(value = "省级Id", required = true) @RequestParam  String provinceId){
		Result<List<SelectBeanVO>> result = new Result<List<SelectBeanVO>>();
        try{
        	if(StringUtils.isBlank(provinceId)){
        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, null);
        		return result;
        	}
        	
        	List<SelectBeanVO> list = locationService.selectCityLocationByProvinceId(provinceId);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取省级行政区域信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取区级行政区域信息", notes = "获取区级行政区域信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getAreaLocation", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBeanVO>> getAreaLocation(HttpServletRequest request,
			@ApiParam(value = "市级Id", required = true) @RequestParam  String cityId){
		Result<List<SelectBeanVO>> result = new Result<List<SelectBeanVO>>();
        try{
        	if(StringUtils.isBlank(cityId)){
        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, null);
        		return result;
        	}
        	
        	List<SelectBeanVO> list = locationService.selectAreaLocationByCityId(cityId);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
        	logger.error("获取省级行政区域信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
}
