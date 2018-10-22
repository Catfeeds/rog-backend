package com.rograndec.feijiayun.chain.business.report.storage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.basic.warehouse.service.WarehouseService;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.PosWarehouseCargoAreaVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseAreaVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseVO;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetCategoryService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsCategoryVO;
import com.rograndec.feijiayun.chain.business.report.storage.service.StockListReportService;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListQueryVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListResultBranchDtlTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.storage.maintance.service.GoodsMaintanceService;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_storage_stockList",description = "报表-储存管理-库存清单")
@RestController
@RequestMapping("report/storage/stockList")
@Validated
public class StockListReportController {
	
	private static final Log logger = LogFactory.getLog(StockListReportController.class);
	
	@Autowired
	private StockListReportService stockListReportService;
	
	@Autowired
	private SetCategoryService setCategoryService;
	
	@Autowired
	private GoodsMaintanceService goodsMaintanceService;
	
	@Autowired
	private WarehouseService warehouseService;
	

	@ApiOperation(value = "查询商品分类列表", notes = "查询商品分类列表,不可维护 | 开发者 刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getGoodsCategoryList", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<TreePOJO<GoodsCategoryVO>>> getGoodsCategoryList(HttpServletRequest request,
			@ApiParam(value = "企业ID", required = false) @RequestParam(required=false)Long enterpriseId) {
		Result<List<TreePOJO<GoodsCategoryVO>>> result = new Result<>();
		try {

			if(enterpriseId == null || enterpriseId == 0){
        		HttpSession session = request.getSession(true);
    			UserVO loginUser = (UserVO) session.getAttribute("user");
    			enterpriseId = loginUser.getEnterpriseId();
        	}
			
			List<TreePOJO<GoodsCategoryVO>> goodsCategorys = setCategoryService.getClassify(enterpriseId, false);
			if (goodsCategorys == null) {
				result.setBizCodeSuccessInfo(SysCode.FAIL, goodsCategorys);
				return result;
			}
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsCategorys);
		} catch (Exception e) {
			logger.error("查询商品分类列表错误:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@ApiOperation(value = "查询库区列表", notes = "查询库区列表 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value="/getWarehouseAreaList", method=RequestMethod.GET)
    public Result<List<WarehouseAreaVO>> getWarehouseAreaList(HttpServletRequest request,
    		@ApiParam(value = "企业ID", required = false) @RequestParam(required=false)Long enterpriseId){
        Result<List<WarehouseAreaVO>> result = new Result<>();
        try {
        	
        	if(enterpriseId == null || enterpriseId == 0){
        		HttpSession session = request.getSession(true);
    			UserVO loginUser = (UserVO) session.getAttribute("user");
    			enterpriseId = loginUser.getEnterpriseId();
        	}
        	
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsMaintanceService.getWarehouseAreaListByEnterpriseId(enterpriseId));
        }catch (Exception e){
            logger.error("查询库区列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "查询货区", notes = "查询货区 | 开发者 刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getWarehouseCargoAreaList", method = RequestMethod.GET)
	public Result<List<PosWarehouseCargoAreaVO>> getWarehouseCargoAreaList(HttpServletRequest request,
			@ApiParam(value = "企业ID", required = false) @RequestParam(required=false)Long enterpriseId) throws Exception {
		Result<List<PosWarehouseCargoAreaVO>> result = new Result<>();
        try {
        	
        	if(enterpriseId == null || enterpriseId == 0){
        		HttpSession session = request.getSession(true);
    			UserVO loginUser = (UserVO) session.getAttribute("user");
    			enterpriseId = loginUser.getEnterpriseId();
        	}
        	
            List<PosWarehouseCargoAreaVO> list = warehouseService.findByEnterpriseIdPosgz(enterpriseId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("查看柜组数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	
	@ApiOperation(value = "报表-货位选择树", notes = "仓库-》库区-》货区-》货位--树形展示 开发者:刘群 | 已联调")
    @RequestMapping(value = "/getWarehouseTreeByParam", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<TreePOJO<WarehouseVO>>> getWarehouseTreeIncludeNullByParam(HttpServletRequest request,
			@ApiParam(value = "企业ID", required = false) @RequestParam(required=false)Long enterpriseId) {
        Result<List<TreePOJO<WarehouseVO>>> result = new Result<>();
        try {
        	
        	if(enterpriseId == null || enterpriseId == 0){
        		HttpSession session = request.getSession(true);
    			UserVO loginUser = (UserVO) session.getAttribute("user");
    			enterpriseId = loginUser.getEnterpriseId();
        	}
        	
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, warehouseService.getWarehouseShelfTreeByParam(enterpriseId, EnableStatus.ENABLE.getStatus(), null));
        } catch (Exception e) {
            logger.error("待入库-入库-入库明细数据-货位选择树:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按商品汇总查询库存清单列表", notes = "按商品汇总查询库存清单列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryStockListBySum", method=RequestMethod.POST)
    public Result<Page<StockListResultBranchTotalVO>> queryStockListBySum(HttpServletRequest request,
			@RequestBody StockListQueryVO vo){
		Result<Page<StockListResultBranchTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = stockListReportService.selectStockListBySum(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("按商品汇总查询库存清单列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按货位明细查询库存清单列表", notes = "按货位明细查询库存清单列表 | 开发者 刘群 | 已联调")
    @RequestMapping(value="/queryStockListByDtl", method=RequestMethod.POST)
    public Result<Page<StockListResultBranchDtlTotalVO>> queryStockListByDtl(HttpServletRequest request,
			@RequestBody StockListQueryVO vo){
		Result<Page<StockListResultBranchDtlTotalVO>> result = new Result<>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			Page page = stockListReportService.selectStockDtlListBySum(vo, loginUser);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}catch(Exception e){
			logger.error("按货位明细查询库存清单列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
		
	}
	
}
