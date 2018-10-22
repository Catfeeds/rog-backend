package com.rograndec.feijiayun.chain.business.report.retail.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.rograndec.feijiayun.chain.business.report.retail.service.SaleFlowReportBranchService;
import com.rograndec.feijiayun.chain.business.report.retail.service.SaleFlowReportParentService;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateQueryParentVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateResultBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDoubleClickQueryDtlVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDoubleClickQuerySumVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlResultBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumResultBranchVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_retail_saleFlow",description = "报表-零售管理-销售流水")
@RestController
@RequestMapping("report/retail/saleFlow")
@Validated
public class SaleFlowReportController {

	private static final Log logger = LogFactory.getLog(SaleFlowReportController.class);
	
	@Autowired
	private SaleFlowReportBranchService saleFlowReportBranchService;
	
	@Autowired
	private SaleFlowReportParentService saleFlowReportParentService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按日结标识、日期查询销售流水列表（分店）", notes = "按日结标识、日期查询销售流水列表（分店） | 开发者 刘群 | 已联调")
    @RequestMapping(value="/querySaleFlowListByDateForBranch", method=RequestMethod.POST)
	@ResponseBody
    public Result<Page<SaleFlowListDateResultBranchTotalVO>> querySaleFlowListByDateForBranch(HttpServletRequest request,
			@RequestBody SaleFlowListDateQueryBranchVO vo){
		
		Result<Page<SaleFlowListDateResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			if(vo == null || vo.getDailySettlementFlag() == null || 
					(vo.getDailySettlementFlag() != 0 && vo.getDailySettlementFlag() != 1)){
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR, "参数错误！");
				return result;
			}

			Page page = saleFlowReportBranchService.selectSaleFlowPageByDateForBranch(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("按日结标识、日期查询销售流水列表（分店）错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value = "导出Excel", notes = "按日结标识、日期查询销售流水列表（分店）导出至Excel | 开发者 刘群 | 已联调")
    @RequestMapping(value="/exportExcelSaleFlowListByDateForBranch",method= RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "按日结标识、日期查询销售流水列表（分店）", required = false, dataType = "SaleFlowListDateQueryBranchVO")
	})
	public void exportExcelSaleFlowListByDateForBranch(HttpServletRequest request, HttpServletResponse response, 
    		SaleFlowListDateQueryBranchVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-分店日结按日期";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<SaleFlowListDateResultBranchVO> resultBranchVOList = saleFlowReportBranchService.selectSaleFlowListByDateForBranch(vo, loginUser);
            saleFlowReportBranchService.exportExcelSaleFlowListByDateForBranch(output, resultBranchVOList, loginUser);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按日结标识、汇总查询销售流水列表（分店）", notes = "按日结标识、汇总查询销售流水列表（分店） | 开发者 刘群 | 已联调")
    @RequestMapping(value="/querySaleFlowListBySumForBranch", method=RequestMethod.POST)
	@ResponseBody
    public Result<Page<SaleFlowListSumResultBranchTotalVO>> querySaleFlowListBySumForBranch(HttpServletRequest request,
			@RequestBody SaleFlowListSumQueryBranchVO vo){
		Result<Page<SaleFlowListSumResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			if(vo == null || vo.getDailySettlementFlag() == null || 
					(vo.getDailySettlementFlag() != 0 && vo.getDailySettlementFlag() != 1)){
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR, "参数错误！");
				return result;
			}

			Page page = saleFlowReportBranchService.selectSaleFlowPageBySumForBranch(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("按日结标识、汇总查询销售流水列表（分店）错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	
	@ApiOperation(value = "导出Excel", notes = "按日结标识、汇总查询销售流水列表（分店）导出至Excel | 开发者 刘群 | 已联调")
    @RequestMapping(value="/exportExcelSaleFlowListBySumForBranch",method= RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "按日结标识、汇总查询销售流水列表（分店）", required = false, dataType = "SaleFlowListSumQueryBranchVO")
	})
	public void exportExcelSaleFlowListBySumForBranch(HttpServletRequest request, HttpServletResponse response, 
    		SaleFlowListSumQueryBranchVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-分店日结按汇总";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<SaleFlowListSumResultBranchVO> resultBranchVOList = saleFlowReportBranchService.selectSaleFlowListBySumForBranch(vo, loginUser);
            saleFlowReportBranchService.exportExcelSaleFlowListBySumForBranch(output, resultBranchVOList, loginUser, vo);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按日结标识、明细查询销售流水列表（分店）", notes = "按日结标识、明细查询销售流水列表（分店） | 开发者 刘群 | 已联调")
    @RequestMapping(value="/querySaleFlowListByDtlForBranch", method=RequestMethod.POST)
    public Result<Page<SaleFlowListDtlResultBranchTotalVO>> querySaleFlowListByDtlForBranch(HttpServletRequest request,
			@RequestBody SaleFlowListDtlQueryBranchVO vo){
		
		Result<Page<SaleFlowListDtlResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			if(vo == null || vo.getDailySettlementFlag() == null || 
					(vo.getDailySettlementFlag() != 0 && vo.getDailySettlementFlag() != 1)){
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR, "参数错误！");
				return result;
			}

			Page page = saleFlowReportBranchService.selectSaleFlowPageByDtlForBranch(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("按日结标识、明细查询销售流水列表（分店）错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	
	@ApiOperation(value = "导出Excel", notes = "按日结标识、汇总查询销售流水列表（分店）导出至Excel | 开发者 刘群 | 已联调")
    @RequestMapping(value="/exportExcelSaleFlowListByDtlForBranch",method= RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "按日结标识、汇总查询销售流水列表（分店）", required = false, dataType = "SaleFlowListDtlQueryBranchVO")
	})
	public void exportExcelSaleFlowListByDtlForBranch(HttpServletRequest request, HttpServletResponse response, 
    		SaleFlowListDtlQueryBranchVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-分店日结按明细";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<SaleFlowListDtlResultBranchVO> resultBranchVOList = saleFlowReportBranchService.selectSaleFlowListByDtlForBranch(vo, loginUser);
            saleFlowReportBranchService.exportExcelSaleFlowListByDtlForBranch(output, resultBranchVOList, loginUser, vo);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按日结标识、日期查询销售流水列表（总部）", notes = "按日结标识、日期查询销售流水列表（总部） | 开发者 刘群 | 已联调")
    @RequestMapping(value="/querySaleFlowListByDateForParent", method=RequestMethod.POST)
	@ResponseBody
    public Result<Page<SaleFlowListDateResultBranchTotalVO>> querySaleFlowListByDateForParent(HttpServletRequest request,
			@RequestBody SaleFlowListDateQueryParentVO vo){
		
		Result<Page<SaleFlowListDateResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			if(vo == null || vo.getDailySettlementFlag() == null || 
					(vo.getDailySettlementFlag() != 0 && vo.getDailySettlementFlag() != 1)){
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR, "参数错误！");
				return result;
			}

			Page page = saleFlowReportParentService.selectSaleFlowPageByDateForParent(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("按日结标识、日期查询销售流水列表（分店）错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value = "导出Excel", notes = "按日结标识、日期查询销售流水列表（总部）导出至Excel | 开发者 刘群 | 已联调")
    @RequestMapping(value="/exportExcelSaleFlowListByDateForParent",method= RequestMethod.POST, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "按日结标识、日期查询销售流水列表（总部）", required = false, dataType = "SaleFlowListDateQueryParentVO")
	})
	public void exportExcelSaleFlowListByDateForParent(HttpServletRequest request, HttpServletResponse response, 
    		SaleFlowListDateQueryParentVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-分店日结按日期";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<SaleFlowListDateResultBranchVO> resultBranchVOList = saleFlowReportParentService.selectSaleFlowListByDateForParent(vo, loginUser);
            saleFlowReportParentService.exportExcelSaleFlowListByDateForParent(output, resultBranchVOList, loginUser);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按日结标识、汇总查询销售流水列表（总部）", notes = "按日结标识、汇总查询销售流水列表（总部） | 开发者 刘群 | 已联调")
    @RequestMapping(value="/querySaleFlowListBySumForParent", method=RequestMethod.POST)
	@ResponseBody
    public Result<Page<SaleFlowListSumResultBranchTotalVO>> querySaleFlowListBySumForParent(HttpServletRequest request,
			@RequestBody SaleFlowListSumQueryBranchVO vo){
		Result<Page<SaleFlowListSumResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			if(vo == null || vo.getDailySettlementFlag() == null || 
					(vo.getDailySettlementFlag() != 0 && vo.getDailySettlementFlag() != 1)){
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR, "参数错误！");
				return result;
			}

			Page page = saleFlowReportParentService.selectSaleFlowPageBySumForParent(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("按日结标识、汇总查询销售流水列表（分店）错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}

	
	@ApiOperation(value = "导出Excel", notes = "按日结标识、汇总查询销售流水列表（总部）导出至Excel | 开发者 刘群 | 已联调")
    @RequestMapping(value="/exportExcelSaleFlowListBySumForParent",method= RequestMethod.POST, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "按日结标识、汇总查询销售流水列表（总部）", required = false, dataType = "SaleFlowListSumQueryBranchVO")
	})
	public void exportExcelSaleFlowListBySumForParent(HttpServletRequest request, HttpServletResponse response, 
    		SaleFlowListSumQueryBranchVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-分店日结按日期";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<SaleFlowListSumResultBranchVO> resultBranchVOList = saleFlowReportParentService.selectSaleFlowListBySumForParent(vo, loginUser);
            saleFlowReportParentService.exportExcelSaleFlowListBySumForParent(output, resultBranchVOList, loginUser, vo);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按日结标识、明细查询销售流水列表（总部）", notes = "按日结标识、明细查询销售流水列表（总部） | 开发者  刘群| 已联调")
    @RequestMapping(value="/querySaleFlowListByDtlForParent", method=RequestMethod.POST)
    public Result<Page<SaleFlowListDtlResultBranchTotalVO>> querySaleFlowListByDtlForParent(HttpServletRequest request,
			@RequestBody SaleFlowListDtlQueryBranchVO vo){
		
		Result<Page<SaleFlowListDtlResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			if(vo == null || vo.getDailySettlementFlag() == null || 
					(vo.getDailySettlementFlag() != 0 && vo.getDailySettlementFlag() != 1)){
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR, "参数错误！");
				return result;
			}

			Page page = saleFlowReportParentService.selectSaleFlowPageByDtlForParent(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("按日结标识、明细查询销售流水列表（总部）错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	
	@ApiOperation(value = "导出Excel", notes = "按日结标识、明细查询销售流水列表（总部）导出至Excel | 开发者 刘群 | 已联调")
    @RequestMapping(value="/exportExcelSaleFlowListByDtlForParent",method= RequestMethod.POST, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "vo", value = "按日结标识、明细查询销售流水列表（总部）", required = false, dataType = "SaleFlowListDtlQueryBranchVO")
	})
	public void exportExcelSaleFlowListByDtlForParent(HttpServletRequest request, HttpServletResponse response, 
    		SaleFlowListDtlQueryBranchVO vo){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"-分店日结按明细";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<SaleFlowListDtlResultBranchVO> resultBranchVOList = saleFlowReportParentService.selectSaleFlowListByDtlForBranch(vo, loginUser);
            saleFlowReportParentService.exportExcelSaleFlowListByDtlForBranch(output, resultBranchVOList, loginUser, vo);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按日期列表双击展示订单", notes = "按日期列表双击展示订单 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/querySaleFlowListDoubleClickBySum", method=RequestMethod.POST)
	@ResponseBody
    public Result<Page<SaleFlowListSumResultBranchTotalVO>> querySaleFlowListDoubleClickBySum(HttpServletRequest request,
			@RequestBody SaleFlowListDoubleClickQuerySumVO vo){
		
		Result<Page<SaleFlowListSumResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			if(vo == null || vo.getDailySettlementFlag() == null || 
					(vo.getDailySettlementFlag() != 0 && vo.getDailySettlementFlag() != 1)){
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR, "参数错误！");
				return result;
			}

			Page page = saleFlowReportBranchService.selectSaleFlowListDoubleClickBySum(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("按日期列表双击展示订单错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按汇总列表双击展示订单明细", notes = "按汇总列表双击展示订单明细 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/querySaleFlowListDoubleClickByDtl", method=RequestMethod.POST)
	@ResponseBody
    public Result<Page<SaleFlowListDtlResultBranchTotalVO>> querySaleFlowListDoubleClickByDtl(HttpServletRequest request,
			@RequestBody SaleFlowListDoubleClickQueryDtlVO vo){
		
		Result<Page<SaleFlowListDtlResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			if(vo == null || vo.getId() == null || 
					vo.getId() == 0){
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR, "参数错误！");
				return result;
			}

			Page page = saleFlowReportBranchService.selectSaleFlowListDoubleClickByDtl(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("按日期列表双击展示订单错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
}
