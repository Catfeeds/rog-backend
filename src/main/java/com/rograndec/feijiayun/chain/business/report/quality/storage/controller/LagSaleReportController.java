package com.rograndec.feijiayun.chain.business.report.quality.storage.controller;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.report.quality.storage.service.LagSaleReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.LagSaleGoodsRequestParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.LagSaleGoodsTotalVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_storage_lagSale",description = "报表-质量管理-存储与养护-滞销商品")
@RestController
@RequestMapping("report/quality/storage/lagSale")
@Validated
public class LagSaleReportController {

	private static final Logger logger = LoggerFactory.getLogger(LagSaleReportController.class);
	
	@Autowired
	private LagSaleReportService lagSaleReportService;


	@ApiOperation(value = "查询", notes = "查询滞销商品列表 | 开发者 yuting.li | 已联调",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value="/getLagSaleList", method=RequestMethod.POST)
    public Result<Page<LagSaleGoodsTotalVO>> getLagSaleList(HttpSession session,@Valid @RequestBody LagSaleGoodsRequestParamVO paramVO){
		Result<Page<LagSaleGoodsTotalVO>> result = new Result<>();
		if (paramVO.getPageNo() <= 0 || paramVO.getPageSize() <= 0) {
            throw new BusinessException("分页参数不对");
        }
		try {
            UserVO loginUser = (UserVO) session.getAttribute("user");
            paramVO.setEnterpriseId(loginUser.getEnterpriseId());
            if(0 != loginUser.getParentId() && null != loginUser.getParentId()) {
            	paramVO.setParentId(loginUser.getParentId());
            }
            Page<LagSaleGoodsTotalVO> page = lagSaleReportService.getLagSaleGoods(paramVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		} catch (Exception e) {
			logger.error("获取滞销商品数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
		return result;
	}
	
	@ApiOperation(value = "导出Excel", notes = "滞销商品列表导出至Excel | 开发者 yuting.li | 已联调")
	@RequestMapping(value="/exportExcel", method=RequestMethod.POST,produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void exportExcel(HttpSession session,HttpServletResponse response,LagSaleGoodsRequestParamVO paramVO){
		String name = "滞销商品";
        try {
        	//这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        	response.setContentType("application/msexcel;charset=UTF-8");
	        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
	        UserVO loginUser = (UserVO) session.getAttribute("user");
	        paramVO.setEnterpriseId(loginUser.getEnterpriseId());
            if(0 != loginUser.getParentId() && null != loginUser.getParentId()) {
            	paramVO.setParentId(loginUser.getParentId());
            }
	        //输出Excel文件
	        OutputStream output = response.getOutputStream();
	        lagSaleReportService.exportExcel(output, paramVO, loginUser);
            output.close();
            output.flush();
        } catch (Exception e) {
        	logger.error("导出滞销商品错误:",e);
        }
	}
	
}
