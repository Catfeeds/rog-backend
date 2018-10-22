package com.rograndec.feijiayun.chain.business.report.quality.storage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsStorageReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsMaintanceCountReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestTemperatureHumidityReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.TemperatureHumidityReportVO;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.LotAdjustVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_storage_temperatureHumidity",description = "报表-质量管理-存储与养护-温湿度监控记录")
@RestController
@RequestMapping("report/quality/storage/temperatureHumidity")
@Validated
public class TemperatureHumidityReportController {

    private static final Log logger = LogFactory.getLog(TemperatureHumidityReportController.class);
    @Autowired
    private GoodsStorageReportService goodsStorageReportService;
    @ApiOperation(value="按条件搜索温湿度记录报表信息", notes = "按条件搜索温湿度记录报表信息 | 开发者 孙帮祥 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/gettemperatureHumidity", method = RequestMethod.POST)
    public Result<Page<List<TemperatureHumidityReportVO>>> gettemperatureHumidity(HttpServletRequest request,@RequestBody RequestTemperatureHumidityReportVO requestTemperatureHumidityReportVO){
        Result<Page<List<TemperatureHumidityReportVO>>> result = new Result<Page<List<TemperatureHumidityReportVO>>>();
        try{
        	Page<List<TemperatureHumidityReportVO>> page = new Page<List<TemperatureHumidityReportVO>>(requestTemperatureHumidityReportVO.getPageNo(), requestTemperatureHumidityReportVO.getPageSize());
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
        	if(requestTemperatureHumidityReportVO.getPageNo()==1){
        		requestTemperatureHumidityReportVO.setPageNo(0);
        	}else{
        		requestTemperatureHumidityReportVO.setPageNo(requestTemperatureHumidityReportVO.getPageNo()-1);
        		requestTemperatureHumidityReportVO.setPageNo(requestTemperatureHumidityReportVO.getPageNo()*requestTemperatureHumidityReportVO.getPageSize());
        	}
        	if(loginUser.getChainType()!=null){
				if(loginUser.getChainType().equals(0)){
					map.put("parentId", loginUser.getEnterpriseId());
				}else{
					map.put("parentId", loginUser.getParentId());
				}
			}
        	map.put("enterpriseId", loginUser.getEnterpriseId());
        	map.put("pageNo", requestTemperatureHumidityReportVO.getPageNo());
        	map.put("pageSize", requestTemperatureHumidityReportVO.getPageSize());
        	map.put("chainType", requestTemperatureHumidityReportVO.getChainType());//组织机构类型
        	map.put("enterpriseCode", requestTemperatureHumidityReportVO.getEnterpriseCode());//组织机构编码
        	map.put("enterpriseName", requestTemperatureHumidityReportVO.getEnterpriseName());//组织结构名称
        	map.put("sortField", requestTemperatureHumidityReportVO.getSortField());
        	map.put("sort", requestTemperatureHumidityReportVO.getSort());
        	map.put("startDate", requestTemperatureHumidityReportVO.getStartDate());
        	map.put("endDate", requestTemperatureHumidityReportVO.getEndDate());
        	goodsStorageReportService.getTemperatureHumidityList(page, map);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("按条件搜索温湿度记录信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
/*    @ApiOperation(value="导出温湿度记录列表Excel", notes = "导出Excel | 开发者 孙帮祥 | 已联调", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBodyEntity
    @RequestMapping(value = "/exportExcelList", method = RequestMethod.POST)
    public void exportExcelList(HttpServletRequest request,HttpServletResponse response,RequestTemperatureHumidityReportVO requestTemperatureHumidityReportVO)throws Exception{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
        	if(loginUser.getChainType()!=null){
				if(loginUser.getChainType().equals(0)){
					map.put("parentId", loginUser.getEnterpriseId());
				}else{
					map.put("parentId", loginUser.getParentId());
				}
			}
        	map.put("enterpriseId", loginUser.getEnterpriseId());
        	map.put("chainType", requestTemperatureHumidityReportVO.getChainType());//组织机构类型
        	map.put("enterpriseCode", requestTemperatureHumidityReportVO.getEnterpriseCode());//组织机构编码
        	map.put("enterpriseName", requestTemperatureHumidityReportVO.getEnterpriseName());//组织结构名称
        	map.put("sortField", requestTemperatureHumidityReportVO.getSortField());
        	map.put("sort", requestTemperatureHumidityReportVO.getSort());
        	map.put("startDate", requestTemperatureHumidityReportVO.getStartDate());
        	map.put("endDate", requestTemperatureHumidityReportVO.getEndDate());
        	String name = "温湿度记录报表";
    		// 输出Excel文件
    		UserVO userVO = (UserVO) session.getAttribute("user");
    		OutputStream output = response.getOutputStream();
    		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
    		response.setContentType("application/octet-stream;charset=utf-8");
    		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        	goodsStorageReportService.temperatureHumidityExcelExport(output, loginUser, map);
    }*/
	@ApiOperation(value = "查询温湿度监控记录", notes = "查询温湿度监控记录明细列表 | 开发者 孙帮祥 | 开发完成")
    @RequestMapping(value="/getDtlList/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "查询批号调整单", required = true,paramType = "path")
    public Result<TemperatureHumidityReportVO> getDtlList(@PathVariable Long id){
        Result<TemperatureHumidityReportVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsStorageReportService.getTempHumidityRecordDtlList(id));
        return result;
    }
    @ApiOperation(value="导出温湿度记录详情Excel", notes = "导出Excel | 开发者 孙帮祥 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/exportDetailExcel/{id}", method = RequestMethod.GET)
    public void exportDetailExcel(HttpServletRequest request,HttpServletResponse response,@PathVariable("id") Long id)throws Exception{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
    		OutputStream output = response.getOutputStream();
        	String name = "Detail";
    		// 输出Excel文件
    		response.setContentType("application/octet-stream;charset=utf-8");
    		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        	goodsStorageReportService.temperatureHumidityDetailExcelExport(output, loginUser, id); 
    }
}
