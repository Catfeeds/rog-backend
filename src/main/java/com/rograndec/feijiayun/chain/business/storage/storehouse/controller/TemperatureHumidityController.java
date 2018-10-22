package com.rograndec.feijiayun.chain.business.storage.storehouse.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.storage.storehouse.service.TemperatureHumidityService;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.RequsetTempHumUpdateVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.ResponseTempHumVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.TemperatureHumidityVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.TemperatureRecordVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.WarehouseAreaVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.WarehouseVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.StartAndEndDate;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "storage_storehouse_temperatureHumidity",description = "储存管理-库房管理-温湿度记录")
@RestController
@RequestMapping("storage/storehouse/temperatureHumidity")
@Validated
public class TemperatureHumidityController {
	
	  private static final Logger logger = LoggerFactory.getLogger(TemperatureHumidityController.class);
	@Autowired
	TemperatureHumidityService temperatureHumidityService;

	@ApiOperation(value = "查询仓库下拉列表", notes = "查询仓库列表 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getWarehouseList", method=RequestMethod.GET)
    public Result<List<WarehouseVO>> getWarehouseList(HttpSession session,@ApiIgnore UserVO userVO){
		Result<List<WarehouseVO>> result=new Result<List<WarehouseVO>>();
		try{
			/*UserVO user=(UserVO) session.getAttribute("user");*/
			System.out.println(userVO.getUserId());
			long enterpriseId=userVO.getEnterpriseId();
			List<WarehouseVO> list=temperatureHumidityService.getWarehouseList(enterpriseId);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
			return result;
		}catch(Exception e){
			logger.error("查询仓库下拉列表异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "查询库区下拉列表", notes = "查询库区列表 | 开发者 张东东 | 已联调")
	@RequestMapping(value="/getWarehouseAreaList/{warehouseId}", method=RequestMethod.GET)
	@ApiImplicitParam(name = "warehouseId", value = "仓库的id", required = true, paramType = "path")
    public Result<List<WarehouseAreaVO>> getWarehouseAreaList(@PathVariable Long warehouseId){
		Result<List<WarehouseAreaVO>> result=new Result<List<WarehouseAreaVO>>();
		try{
			List<WarehouseAreaVO> list=temperatureHumidityService.getWarehouseAreaList(warehouseId);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
			return result;
		}catch(Exception e){
			logger.error("查询库区下拉列表异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "保存温湿度记录", notes = "保存温湿度记录 | 开发者 张东东 | 已联调")
	@ApiImplicitParam(name = "temperatureHumidityVO", value = "", required = true, dataType = "TemperatureHumidityVO")
    @RequestMapping(value="/saveTempHumidityRecord", method=RequestMethod.POST)
    public Result<String> saveTempHumidityRecord(HttpSession session,@ApiIgnore UserVO userVO, @RequestBody TemperatureHumidityVO temperatureHumidityVO){
		Result<String> result=new Result<String>();
		try{
			/*UserVO user=(UserVO) session.getAttribute("user");*/
			String warehouseName=temperatureHumidityService.getWarehouseNameById(userVO.getEnterpriseId(), 
					temperatureHumidityVO.getWarehouseId());
			if(warehouseName==null){
				result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"仓库不存在");
				return result;
			}
			String warehouseAreaName=temperatureHumidityService.getWarehouseAreaNameById(userVO.getEnterpriseId(),
					temperatureHumidityVO.getWarehouseId(), temperatureHumidityVO.getWarehouseAreaId());
			if(warehouseAreaName==null){
				result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"库区不存在");
				return result;
			}
			temperatureHumidityVO.setWarehouseAreaName(warehouseAreaName);
			temperatureHumidityVO.setWarehouseName(warehouseName);
			String sign=temperatureHumidityService.insert(temperatureHumidityVO,userVO);
			if(sign!=null){
				result.setBizCodeFallInfo(SysCode.FAIL.getCode(),sign);
			}else{
				result.setBizCodeSuccessInfo(SysCode.SUCCESS);
			}
			return result;
		}catch(Exception e){
			logger.error("添加仓库温湿度记录异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "修改温湿度记录", notes = "修改温湿度记录 | 开发者 张东东 | 已联调")
	@ApiImplicitParam(name = "requsetTempHumUpdateVO", value = "", required = true, dataType = "RequsetTempHumUpdateVO")
    @RequestMapping(value="/updateTempHumidityRecord", method=RequestMethod.POST)
    public Result<String>  updateTempHumidityRecord(HttpSession session,@ApiIgnore UserVO userVO,@RequestBody RequsetTempHumUpdateVO requsetTempHumUpdateVO){
		Result<String> result=new Result<String>();
		try{
			/*UserVO user=(UserVO) session.getAttribute("user");*/
			String msg=temperatureHumidityService.updateTempHumidityRecord(requsetTempHumUpdateVO,userVO);
			if(msg==null){
				result.setBizCodeSuccessInfo(SysCode.SUCCESS);
				return result;
			}
			result.setBizCodeFallInfo(SysCode.FAIL.getCode(),msg);
	        return result;
		}catch(Exception e){
			logger.error("更改仓库温湿度记录异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "查询温湿度记录列表", notes = "查询温湿度记录列表 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getTempHumidityRecordList", method=RequestMethod.GET)
    public Result<Page<List<TemperatureRecordVO>>>  getTempHumidityRecordList(HttpSession session,@ApiIgnore UserVO userVO,
    		@ApiParam(value = "页码", required = true) @RequestParam Integer pageNo,
    		@ApiParam(value = "每页显示的记录数", required = true) @RequestParam Integer pageSize,
    		@ApiParam(value = "记录人员", required = false) @RequestParam(required=false) String recordManName,
    		@ApiParam(value = "仓库id", required = false) @RequestParam(required=false) Integer warehouseId,
    		@ApiParam(value = "库区id", required = false) @RequestParam(required=false) Integer warehouseAreaId,
    		@ApiParam(value = "筛选条件开始时间", required = false) @RequestParam(required=false) Date startTime,
    		@ApiParam(value = "筛选条件截止时间  ", required = false) @RequestParam(required=false) Date endTime,
    		@ApiParam(value = "时间排序  0 倒叙  1 顺序  默认为0  ", required = false) @RequestParam(required=false) Integer dateOrder){
		Result<Page<List<TemperatureRecordVO>>> result = new Result<>();
		try{
			//默认为倒序
			dateOrder=dateOrder==null?0:dateOrder;
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("endTime",StartAndEndDate.getEndTime(endTime));
			map.put("startTime", StartAndEndDate.getStartTime(startTime));
			map.put("warehouseId", warehouseId);
			map.put("warehouseAreaId", warehouseAreaId);
			map.put("recordManName", recordManName);
			map.put("pageSize", pageSize);
			map.put("dateOrder", dateOrder);
			/*UserVO user=(UserVO) session.getAttribute("user");*/
			if(pageNo < 0 || pageSize <= 0){
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
				return result;
			}
			Page<List<TemperatureRecordVO>> page = new Page<>(pageNo, pageSize);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,temperatureHumidityService.getTempHumidityRecordList(page, userVO.getEnterpriseId(),map));
			return result;
		}catch(Exception e){
			logger.error("查询温湿度记录列表异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "查询温湿度记录明细列表", notes = "根据温湿度记录ID查询温湿度记录明细列表 | 开发者 张东东 | 已联调")
	@ApiImplicitParam(name = "recordId", value = "温湿度记录id", required = true, paramType = "path")
    @RequestMapping(value="/getTempHumidityRecordDtlList/{recordId}", method=RequestMethod.GET)
    public Result<ResponseTempHumVO> getTempHumidityRecordDtlList(@PathVariable Long recordId){
		Result<ResponseTempHumVO> result=new Result<>();
		try{
			ResponseTempHumVO responseVO=temperatureHumidityService.getTempHumidityRecordDtlList(recordId);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,responseVO);
			return result;
		}catch(Exception e){
			logger.error("添加仓库温湿度记录异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "导出Excel", notes = "按照模版将温湿度记录导出至Excel | 开发者 张东东 | 已联调")
	@ApiImplicitParam(name = "recordId", value = "温湿度记录id", required = true, paramType = "path")
    @RequestMapping(value="/exportExcel/{recordId}", method=RequestMethod.GET)
    public void exportExcel( HttpServletResponse response,HttpSession session,@ApiIgnore UserVO userVO,@PathVariable Long recordId){
		ResponseTempHumVO responseVO=temperatureHumidityService.getTempHumidityRecordDtlList(recordId);
		OutputStream output=null;
		try{
			/*UserVO user=(UserVO) session.getAttribute("user");*/
			output=response.getOutputStream();
			//这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name="温湿度记录";
			response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			temperatureHumidityService.exportExcel(output, responseVO,userVO);
		}catch(Exception e){
			logger.error("仓库的温湿度记录导出表异常:" + e.getMessage(), e);
		}
		
	}
	
	@ApiOperation(value = "删除温湿度记录", notes = "根据温湿度记录单id删除温湿度记录 | 开发者 张东东 | 已联调")
	@ApiImplicitParam(name = "recordId", value = "温湿度记录单id", required = true, dataType = "Long")
    @RequestMapping(value="/deleteTempHumidityRecord", method=RequestMethod.POST)
    public Result<String> deleteTempHumidityRecord( @RequestBody Long recordId){
		Result<String> result=new Result<>();
		try{
			int row=temperatureHumidityService.deleteTempHumidityRecord(recordId);
			if(row>0){
				result.setBizCodeSuccessInfo(SysCode.SUCCESS.getCode(),"删除成功",row+"");
			}else{
				result.setBizCodeSuccessInfo(SysCode.SUCCESS.getCode(),"该记录已删除",row+"");
			}
			return result;
		}catch(Exception e){
			logger.error("仓库的温湿度记录导出表异常:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
}
