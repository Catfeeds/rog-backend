package com.rograndec.feijiayun.chain.business.distr.branch.controller;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlan;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrReqPlanService;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.*;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLack;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsDestroyService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.UserDestroyVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.StartAndEndDate;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReqPlanStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "distr_req_plan",description = "配送管理-分店-配进入库-要货计划单接口服务")
@RestController
@RequestMapping("distr/req/plan")
@Validated
public class DistrReqPlanController {
	private static final Logger logger = LoggerFactory.getLogger(DistrReqPlanController.class);
	@Autowired
	private DistrReqPlanService distrReqPlanService;
	@Autowired
	private GoodsDestroyService goodsDestroyService;


	@ApiOperation(value = "查询缺配单明细列表", notes = "根据缺配单ID查询缺配单明细列表 | 开发者 杜东阳 | 已联调")
	@ApiImplicitParam(name = "id", value = "缺配单ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "/getDistrLackOrderDtlList/{id}", method = RequestMethod.GET)
	public Result<DistrLack> getDistrLackOrderDtlList(HttpServletRequest request,
													  @PathVariable("id") @NotNull Long id) {
		Result<DistrLack> result = new Result<>();
		try {
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrReqPlanService.getDistrLackDetailList(id,loginUser));
		} catch (Exception e) {
			logger.error("查询缺配单明细列表:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}




	@ApiOperation(value = "查询人员信息列表", notes = "获取收货人员列表| 开发者 孙帮祥 | 已联调")
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	public Result<List<UserDestroyVO>> getUserList(HttpSession session) {
		Result<List<UserDestroyVO>> result = new Result<List<UserDestroyVO>>();
	    UserVO userVO = (UserVO) session.getAttribute("user");
		try {
			Map map=new HashMap();
			map.put("enterpriseId", userVO.getEnterpriseId());
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsDestroyService.getUserList(map));
		} catch (Exception e) {
			logger.error("获取人员信息列表:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	 @ApiOperation(value = "查询配货单位列表", notes = "查询配货单位列表 | 开发者 孙帮祥 | 已联调")
	 @RequestMapping(value="/getDistrUnitList",method= RequestMethod.GET)
	    public Result<List<EnterpriseReqPlanVO>> getDistrUnitList(HttpSession session){
		Result<List<EnterpriseReqPlanVO>> result = new Result<List<EnterpriseReqPlanVO>>();
		UserVO userVO = (UserVO) session.getAttribute("user");
		try {
				Map<String, Object> map = new HashMap<>();
				if (userVO.getChainType().equals(0)) {// 总部
					map.put("parentId", userVO.getEnterpriseId());
				} else {
					map.put("parentId", userVO.getParentId());
				}
				List<EnterpriseReqPlanVO> goodsList = distrReqPlanService.getEnterprise(map);
				result.setData(goodsList);
				result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		}catch (BusinessException e) {
			logger.error("查询配货单位列表:"+e.getMessage(),e);
			result.setBizCodeFallInfo(e.getErrorCode(),e.getMessage());
		} catch (Exception e) {
			logger.error("查询配货单位列表:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	    }
	 @ApiOperation(value = "查询商品库存列表", notes = "根据编码/条形码/检索码/商品名称/通用名称/批准文号 搜索商品 | 开发者 孙帮祥 | 已联调")
	 @RequestMapping(value="/getGoodsList",method= RequestMethod.GET)
	    public Result<List<GoodsDistrReqPlanVO>> getGoodsList(HttpSession session,
	    		@ApiParam(value = "关键字", required = false) @RequestParam(required=false) String param,
	    		@ApiParam(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）", required = true)
	    		@RequestParam(required=true) Integer requestType,
	    		@ApiParam(value = "调出单位或供货单位ID(当配货类型为门店间调剂或直调配送时必填)", required = false)
	    		@RequestParam(required=false) Long supplierId
	    		){
	        Result<List<GoodsDistrReqPlanVO>> result = new Result<List<GoodsDistrReqPlanVO>>();
	        UserVO userVO = (UserVO) session.getAttribute("user");
	        try {
	        	if(requestType !=  DistributionType.DISTRIBUTION_HEAD.getCode()) {
	        		if(supplierId == null) throw new BusinessException("缺少供货单位");
	        	}
	        	//若是总部配送或分店间调剂则查询商品及库存信息
	        	if(requestType == DistributionType.DISTRIBUTION_HEAD.getCode() || requestType == DistributionType.SWAP_BETWEEN_STORES.getCode()) {
	        		Map<String, Object> map = new HashMap<>();
	    			if(userVO.getChainType()!=null){
	    				if(userVO.getChainType().equals(0)){
	    					map.put("enterpriseId", userVO.getEnterpriseId());
	    					map.put("parentId", userVO.getEnterpriseId());
	    				}else{
	    					map.put("parentId", userVO.getParentId());
	    					map.put("enterpriseId", userVO.getEnterpriseId());
	    				}
	    			}
	    			map.put("distrUnitId", userVO.getParentId());
	    			if(requestType == DistributionType.SWAP_BETWEEN_STORES.getCode()) map.put("distrUnitId", supplierId);
	    			map.put("param", param);
	    			map.put("requestType", requestType);
	    	        List<GoodsDistrReqPlanVO> goodsList=distrReqPlanService.getGoodsList(map,userVO);
	    	        result.setData(goodsList);
	    	        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
	        	} else {
	        		List<GoodsDistrReqPlanVO> goodsList=distrReqPlanService.getGoodsListByDistrType(userVO, requestType,supplierId,param);
	        		result.setData(goodsList);
		    	    result.setBizCodeSuccessInfo(SysCode.SUCCESS);
	        	}
	        }catch (BusinessException e) {
				logger.error("查询商品库存列表:"+e.getMessage(),e);
				result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
			} catch (Exception e) {
				logger.error("查询商品库存列表:" + e.getMessage(), e);
				result.setBizCodeFallInfo(SysCode.FAIL);
			}
			return result;
	    }
	 @ApiOperation(value = "分页搜索商品", notes = "分页获取商品 | 开发者 孙帮祥 | 已联调")
	 @RequestMapping(value="/getGoodsPageByParam",method= RequestMethod.GET)
	 @ResponseBody
	    public Result<Page<List<GoodsDistrReqPlanVO>>> getGoodsPageByParam(HttpSession session,
				@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
				@ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize, 
				@ApiParam(value = "关键字", required = false) @RequestParam(required=false) String param,
	    		@ApiParam(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）", required = true)@RequestParam(required=true) Integer requestType,
	    		@ApiParam(value = "调出单位或供货单位ID(当配货类型为门店间调剂或直调配送时必填)", required = false)@RequestParam(required=false) Long supplierId,
				@ApiParam(value = "排序字段", required = false) @RequestParam(required=false) String sortField,
				@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort) throws ParseException{
         	Result<Page<List<GoodsDistrReqPlanVO>>> result = new Result<Page<List<GoodsDistrReqPlanVO>>>();    
         	Page<List<GoodsDistrReqPlanVO>> page = new Page<List<GoodsDistrReqPlanVO>>(pageNo, pageSize);
	        UserVO userVO = (UserVO) session.getAttribute("user");
			Map<String, Object> map = new HashMap<>();
			if(requestType == DistributionType.DISTRIBUTION_HEAD.getCode() || requestType == DistributionType.SWAP_BETWEEN_STORES.getCode()) {
    			if(userVO.getChainType()!=null){
    				if(userVO.getChainType().equals(0)){
    					map.put("enterpriseId", userVO.getEnterpriseId());
    					map.put("parentId", userVO.getEnterpriseId());
    				}else{
    					map.put("parentId", userVO.getParentId());
    					map.put("enterpriseId", userVO.getEnterpriseId());
    				}
    			}
    			map.put("distrUnitId", userVO.getParentId());
    			if(requestType == DistributionType.SWAP_BETWEEN_STORES.getCode()) map.put("distrUnitId", supplierId);
    			map.put("requestType", requestType);
    			map.put("pageNo", pageNo);
    			map.put("pageStart",(pageNo - 1) * pageSize);
    			map.put("pageSize", pageSize);
    			map.put("sortField", sortField);
    			map.put("sort", sort);
    			if(param!=null){
    				map.put("param", param);
    			}
    	        distrReqPlanService.getGoodsByPage(page, map,userVO);
    	        result.setData(page);
    	        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        	}else {
        		distrReqPlanService.getGoodsListByDistrTypePage(page,pageNo,pageSize,sortField,sort,userVO, requestType,supplierId,param);
        		result.setData(page);
	    	    result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        	}
	        return result;
	    }
	 @ApiOperation(value = "查询配货分析数据列表", notes = "根据选择的条件分析获取商品列表,选中传参为true，不选为false或是不传参 | 开发者 孙帮祥 | 已联调")
	 @RequestMapping(value="/getDistrAnalysisList",method= RequestMethod.POST)
	    public Result<AnalysisVO> getDistrAnalysisList(HttpSession session,
	    		@ApiParam(value = "商品销毁单对象", required = true)  @Valid @RequestBody AnalyseStockVO analyseStockVO){
	        Result<AnalysisVO> result = new Result<AnalysisVO>();
	        try {
		        UserVO userVO = (UserVO) session.getAttribute("user");
				Map<String, Object> map = new HashMap<String,Object>();
				//配货类型必填
				map.put("requestType", analyseStockVO.getRequestType());
				map.put("supplierId", analyseStockVO.getOutboundUnitId());
				if(userVO.getChainType()!=null){
					if(userVO.getChainType().equals(0)){
						map.put("parentId", userVO.getEnterpriseId());
					}else{
						map.put("parentId", userVO.getParentId());
					}
				}
				map.put("enterpriseId", userVO.getEnterpriseId());
				if(analyseStockVO.getSafety()!=null){
				map.put("safety", analyseStockVO.getSafety());
				}
				if(analyseStockVO.getLack()!=null){
					map.put("lack", analyseStockVO.getLack());
				}
				if(analyseStockVO.getDynamicStock()!=null){
				map.put("dynamicStock", analyseStockVO.getDynamicStock());
				}
				//默认考虑在途库存
				analyseStockVO.setOnPassage("true");
				if(analyseStockVO.getOnPassage()!=null){
				map.put("onPassage", analyseStockVO.getOnPassage());
				}
				if(analyseStockVO.getLackQuantity()!=null){
					map.put("lackQuantity", analyseStockVO.getLackQuantity());
				}
				if(analyseStockVO.getDynamicStockQuantity()!=null){
					map.put("dynamicStockQuantity", analyseStockVO.getDynamicStockQuantity());
				}
				if(analyseStockVO.getUndynamicStockQuantity()!=null){
					map.put("undynamicStockQuantity", analyseStockVO.getUndynamicStockQuantity());
				}
				List<GoodsDistrReqPlanAnalysisVO> goodsDistrReqPlanVOList=distrReqPlanService.getGoodsDistrReqPlanVOlist(map,analyseStockVO);
		       
				AnalysisVO analysisVO=distrReqPlanService.getAnalysisVO(goodsDistrReqPlanVOList, userVO, analyseStockVO);
				
				result.setData(analysisVO);
		        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
	        }catch (BusinessException e) {
				result.setBizCodeFallInfo("200100",e.getMessage());
				logger.error("要货计划单添加数据错误:"+e.getMessage(),e);
			}catch (Exception e) {
				result.setBizCodeFallInfo(SysCode.FAIL);
				logger.error("要货计划单添加数据错误:"+e.getMessage(),e);
			}
	        return result;
	    }
	@ApiOperation(value = "保存要货计划单", notes = "保存要货计划单 | 开发者孙帮祥 | 已联调")
    @RequestMapping(value="/saveReqPlanOrder", method=RequestMethod.POST)
    public Result<Object> saveReqPlanOrder(HttpSession session,@ApiParam(value = "要货计划单对象", required = true) @RequestBody DistrReqPlanVO distrReqPlanVO){
		 Result<Object> result = new Result<Object>();
	        UserVO userVO = (UserVO) session.getAttribute("user");
			Map<String, Object> map = new HashMap<>();
			try {
				   distrReqPlanService.save(userVO, distrReqPlanVO);
				   result.setBizCodeSuccessInfo(SysCode.SUCCESS);
			}catch (BusinessException e) {
				result.setBizCodeFallInfo("200100",e.getMessage());
				logger.error("要货计划单添加数据错误:"+e.getMessage(),e);
			}catch (Exception e) {
				result.setBizCodeFallInfo(SysCode.FAIL);
				logger.error("要货计划单添加数据错误:"+e.getMessage(),e);
			}
			return result;
	}
	@ApiOperation(value = "修改要货计划单", notes = "修改要货计划单 | 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/updateReqPlanOrder", method=RequestMethod.POST)
	public Result<Object> updateReqPlanOrder(HttpSession session,@ApiParam(value = "商品销毁单对象", required = true) @RequestBody DistrReqPlanVO distrReqPlanVO){
		 Result<Object> result = new Result<Object>();
	        UserVO userVO = (UserVO) session.getAttribute("user");
			Map<String, Object> map = new HashMap<>();
			try {
				   distrReqPlanService.update(userVO, distrReqPlanVO);
				   result.setBizCodeSuccessInfo(SysCode.SUCCESS);
			}catch (BusinessException e) {
				result.setBizCodeFallInfo("200100",e.getMessage());
				logger.error("要货计划单修改数据错误:"+e.getMessage(),e);
			}catch (Exception e) {
				result.setBizCodeFallInfo(SysCode.FAIL);
				logger.error("要货计划单修改数据错误:"+e.getMessage(),e);
			}
			return result;
	}
	
	@ApiOperation(value="根据状态查询要货计划单列表", notes = "根据状态查询要货计划单列表 | 开发者 孙帮祥|已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getReqPlanOrderList", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page<DistrReqPlanVO>> getReqPlanOrderList(HttpServletRequest request,
				@ApiParam(value = "页码", required = true) @RequestParam Integer pageNo,
				@ApiParam(value = "每页显示的记录数", required = true) @RequestParam Integer pageSize,
				@ApiParam(value = "开始日期", required = false) @RequestParam(required=false) Date startDate,
				@ApiParam(value = "结束日期", required = false) @RequestParam(required=false) Date endDate,
				@ApiParam(value = "要货计划单号", required = false) @RequestParam(required=false) String code,
				@ApiParam(value = "配货类型，要货类型（0-总部配送；3-分店间调剂；4-直调配送）", required = false) @RequestParam(required=false) String requestType,
				@ApiParam(value = "要货人员", required = false) @RequestParam(required=false) String plannerName,
				@ApiParam(value = "配货单位编码", required = false) @RequestParam(required=false) String distrUnitCode,
				@ApiParam(value = "配货单位名称", required = false) @RequestParam(required=false) String distrUnitName,
				@ApiParam(value = "状态     21-待审核 22-审核通过 23-审核驳回 31-待配货 33-已配货 34-已取消", required = false) @RequestParam(required=false) String status,
				@ApiParam(value = "排序字段", required = false) @RequestParam(required=false) String sortField,
				@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
	    	Result<Page<DistrReqPlanVO>> result = new Result<Page<DistrReqPlanVO>>();
	        try{
	        	if(pageNo <= 0 || pageSize <= 0){
	        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
	    			return result;
	        	}
	        	Page<DistrReqPlanVO> page = new Page<DistrReqPlanVO>(pageNo, pageSize);
	        	HttpSession session = request.getSession(true);
	        	UserVO loginUser = (UserVO) session.getAttribute("user");
	        	Long enterpriseId=loginUser.getEnterpriseId();
	        	if(sortField!=null){
	        		if(sortField.equals("planDate")){
	        			sortField="plan_date";
	        		}
	        	}

	        	int status_=0;
	         	 if(status!=null && status!=""){
	            	status_=Integer.valueOf(status);
	          	 }
	        	Map map=new HashMap();
	        	map.put("enterpriseId", enterpriseId);
	        	map.put("start", page.getStart());
	        	map.put("pageSize", page.getPageSize());
	        	startDate=StartAndEndDate.getStartTime(startDate);
	        	endDate=StartAndEndDate.getEndTime(endDate);
	        	map.put("startDate", startDate);
	        	map.put("endDate", endDate);
	        	map.put("code", code);
	        	map.put("requestType", requestType);
	        	map.put("distrUnitCode", distrUnitCode);
	           	map.put("distrUnitName", distrUnitName);
	           	map.put("plannerName", plannerName);
	        	map.put("sortField", sortField);
	        	map.put("sort", sort);
	        	map.put("status", status_);
	        	distrReqPlanService.getList(page, map);
	        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
	        }catch(Exception e){
	        	logger.error("根据状态查询要货计划单列表错误:"+e.getMessage(),e);
				result.setBizCodeFallInfo(SysCode.FAIL);
				return result;
	        }
			return result;
		}
	@ApiOperation(value = "查询要货计划单明细", notes = "查询要货计划单明细 | 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/getReqPlanOrderDtl/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "要货计划主键", required = true,paramType = "path")
    public Result<DistrReqPlanVO> getReqPlanOrderDtlList(HttpServletRequest request,
    		@PathVariable Long id){
        Result<DistrReqPlanVO> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrReqPlanService.getById(id, loginUser));
        return result;
    }


	@ApiOperation(value = "根据要货计划主单id集合查询要货计划单明细", notes = "根据要货计划主单id集合查询要货计划单明细, | 开发者 孙腾 | 已联调")
	@RequestMapping(value="/getReqPlanOrderDtlListByIds",method= RequestMethod.GET)
	@ApiImplicitParam(name = "id", value = "要货计划主键", required = true,paramType = "path")
	public Result<List<DistrReqPlanDetailVO>> getReqPlanOrderDtlListByIds(@ApiParam(value = "ids以逗号分隔", required = true)@RequestParam String ids, @ApiIgnore UserVO userVO){
		Result<List<DistrReqPlanDetailVO>> result = new Result<>();
		result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrReqPlanService.getReqPlanDtlByIds(ids,userVO));
		return result;
	}


	@ApiOperation(value = "取消要货计划单", notes = "取消要货计划单 | 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/cancelReqPlanOrder/{id}", method=RequestMethod.GET)
	@ApiImplicitParam(name = "id", value = "要货计划主键", required = true,paramType = "path")
    public Result<String> cancelReqPlanOrder(HttpSession session,@PathVariable Long id){
	     Result<String> result = new Result<>();
	     DistrReqPlan plan=new DistrReqPlan();
		 UserVO userVO = (UserVO) session.getAttribute("user");
	     plan.setId(id);
	     plan.setStatus(DistrReqPlanStatus.CANCELED);
	     try {
			distrReqPlanService.changeStatus(userVO, plan);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		} catch (Exception e) {
			result.setBizCodeFallInfo(SysCode.FAIL);
			e.printStackTrace();
		}
	     return result;
	}
	@ApiOperation(value = "导出Excel", notes = "按照模版将要货计划单导出至Excel | 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
    public void exportExcel(HttpSession session,HttpServletResponse response, @PathVariable("id") Long id) throws IOException{
		String name = "要货计划";
		// 输出Excel文件
		  UserVO userVO = (UserVO) session.getAttribute("user");
		OutputStream output = response.getOutputStream();
		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
		response.setContentType("application/octet-stream;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
		distrReqPlanService.exportExcel(output, id, userVO);
	}
	
	@ApiOperation(value = "查询供货或调出单位列表", notes = "查询供货或调出单位列表 | 开发者 张东东 | 已完成")
	@RequestMapping(value="/getSupplierList/{requestType}",method= RequestMethod.GET)
	@ApiImplicitParam(name = "requestType", value = "配送类型 （3-分店间调剂；4-直调配送）", required = true,paramType = "path")
	public Result<List<DistrReqPlanOutVO>> getSupplierList(HttpSession session,@PathVariable("requestType") Integer requestType){
		Result<List<DistrReqPlanOutVO>> result = new Result<>();
		UserVO userVO = (UserVO) session.getAttribute("user");
		try {
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrReqPlanService.getSupplierList(userVO,requestType));
		}catch (BusinessException e) {
			logger.error("查询供货或调出单位列表:"+e.getMessage(),e);
			result.setBizCodeFallInfo(e.getErrorCode(),e.getMessage());
		} catch (Exception e) {
			logger.error("查询供货或调出单位列表:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}
	
	@ApiOperation(value = "查看门店是否有 直调配送    0禁止直购  1允许直购", notes = "查看门店是否有 直调配送 | 开发者 张东东 | 已完成")
	@RequestMapping(value="/getEnterpriseDistrType",method= RequestMethod.GET)
	public Result<Integer> getEnterpriseDistrType(HttpSession session){
		Result<Integer> result = new Result<>();
		UserVO userVO = (UserVO) session.getAttribute("user");
		try {
			// 0禁止直购  1允许直购
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrReqPlanService.getEnterpriseDistrType(userVO));
		} catch (BusinessException e) {
			logger.error("查看门店是否有 直调配送:"+e.getMessage(),e);
			result.setBizCodeFallInfo(e.getErrorCode(),e.getMessage());
		} catch (Exception e) {
			logger.error("查看门店是否有 直调配送:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}

    @ApiOperation(value = "查看要货计划草稿缓存", notes = "查看要货计划草稿缓存 | 开发者 孙帮祥 | 已完成")
    @RequestMapping(value = "/daftCache", method = RequestMethod.GET)
    public Result<List<DraftCacheVO>> daftCacheVO(@ApiIgnore UserVO userVO) {

        Result<List<DraftCacheVO>> result = new Result<>();

        List<DraftCacheVO> draftCacheVO = distrReqPlanService.getDraftCacheVO(userVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);

        return  result;
    }

    @ApiOperation(value = "保存要货计划草稿缓存", notes = "保存要货计划草稿缓存 | 开发者 孙帮祥 | 已完成")
    @RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
    public Result<DraftCacheVO<DistrReqPlanVO> > saveDraftCache(@ApiIgnore UserVO userVO,@RequestBody DraftCacheVO<DistrReqPlanVO> draftCacheVO) {
        Result<DraftCacheVO<DistrReqPlanVO>> result = new Result();
        try{
			DraftCacheVO<DistrReqPlanVO> distrReqPlanVODraftCacheVO = distrReqPlanService.saveDraftCache(userVO, draftCacheVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrReqPlanVODraftCacheVO);
        	  
        }catch (BusinessException e) {
			result.setBizCodeFallInfo(e.getErrorCode(),e.getMessage());
			
		} catch (Exception e) {
			result.setBizCodeFallInfo(SysCode.FAIL);
			
		}
        return  result;
    }

    @ApiOperation(value = "删除要货计划草稿缓存", notes = "删除要货计划草稿缓存 | 开发者 孙帮祥 | 已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}", method = RequestMethod.POST)
    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String redisKeyValue) {

        Result result = new Result<>();

        distrReqPlanService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.REQUIRE_PLAN.getCodePrefix(),redisKeyValue);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return  result;
    }
	
}
