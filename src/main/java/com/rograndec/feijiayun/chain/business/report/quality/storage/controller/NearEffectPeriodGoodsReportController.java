package com.rograndec.feijiayun.chain.business.report.quality.storage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.report.quality.storage.service.NearEffectPeriodGoodsReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.NearEffectPeriodGoodsVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.NearEffectReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestNearEffectExportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestNearEffectVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_storage_nearEffectPeriodGoods",description = "报表-质量管理-存储与养护-近效期商品")
@RestController
@RequestMapping("report/quality/storage/nearEffectPeriodGoods")
@Validated
public class NearEffectPeriodGoodsReportController {

	private static final Log logger = LogFactory.getLog(NearEffectPeriodGoodsReportController.class);

	@Autowired
	NearEffectPeriodGoodsReportService nearEffectPeriodGoodsReportService;

	@ApiOperation(value="查询近效期商品列表", notes = "查询近效期商品列表 | 开发者 张东东| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "requestNearEffectVO", value = "检索条件", required = true, dataType = "RequestNearEffectVO")
	@RequestMapping(value = "/getNearEffectPeriodGoodsList", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page<NearEffectReportVO<NearEffectPeriodGoodsVO>>> getNearEffectPeriodGoodsList(HttpSession session,@ApiIgnore UserVO userVO,
			@RequestBody RequestNearEffectVO requestNearEffectVO){
	    	Result<Page<NearEffectReportVO<NearEffectPeriodGoodsVO>>> result = new Result<>();
	        try{
	        	Integer pageNo=requestNearEffectVO.getPageNo();
				Integer pageSize=requestNearEffectVO.getPageSize();
				if(pageNo==null || pageSize==null){
	        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"分页参数必填");
	    			return result;
	        	}
	        	if(pageNo <= 0 || pageSize <= 0){
	        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"分页参数不合法");
	    			return result;
	        	}
	        	Page<NearEffectReportVO<NearEffectPeriodGoodsVO>> page = new Page<>(pageNo,pageSize);
	        	page.setTotalRecord(0);
	        	String sign=nearEffectPeriodGoodsReportService.getNearEffectPeriodGoodsListNew(userVO, page, requestNearEffectVO);
	        	if(sign!=null){
	        		result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), sign);
	        	}
	        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
	        	return result;
	        }catch(Exception e){
	        	logger.error("查询近效期商品列表异常:"+e.getMessage(),e);
				result.setBizCodeFallInfo(SysCode.FAIL);
				return result;
	        }
		}
	
	@ApiOperation(value = "按照模版将近效期商品列表导出至Excel", notes = "按照模版将近效期商品列表导出至Excel | 开发者 张东东 | 已联调")
	@ApiImplicitParam(name = "requestNearEffectExportVO", value = "检索条件", required = true, dataType = "RequestNearEffectExportVO")
	@RequestMapping(value = "/exportNearEffectPeriodGoodsListExcel", method = RequestMethod.POST,produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void exportNearEffectPeriodGoodsListExcel( HttpServletResponse response,@ApiIgnore UserVO userVO,HttpSession session,RequestNearEffectExportVO requestNearEffectExportVO){
		OutputStream output=null;
		try{
			output=response.getOutputStream();
			//这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name="近效期商品";
			response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            nearEffectPeriodGoodsReportService.exportNearEffectPeriodGoodsListExcel(userVO,output,requestNearEffectExportVO);
		}catch(Exception e){
			logger.error("按照模版将近效期明细列表导出至Excel异常:" + e.getMessage(), e);
		}
		
	}
}
