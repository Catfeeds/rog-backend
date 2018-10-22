package com.rograndec.feijiayun.chain.business.purchase.order.controller;

import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierService;
import com.rograndec.feijiayun.chain.business.goods.info.service.GoodsService;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.order.service.PurchaseOrderDetailService;
import com.rograndec.feijiayun.chain.business.purchase.order.service.PurchaseOrderOtherService;
import com.rograndec.feijiayun.chain.business.purchase.order.service.PurchaseOrderService;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.*;
import com.rograndec.feijiayun.chain.business.purchase.plan.service.PurchasePlanService;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.HistoricalUnitPriceVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.exception.InventoryBizException;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import io.swagger.annotations.*;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.*;

@Api(value = "purchase_order",description = "采购订单接口服务",produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/purchase/order")
public class PurchaseOrderController {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);
    @Autowired 
    private PurchaseOrderService purchaseOrderService;
    @Autowired
    private PurchaseOrderDetailService purchaseOrderDetailService;
    @Autowired
    private PurchaseOrderOtherService purchaseOrderOtherService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SupplierService supplierService;
	@Autowired
	private PurchasePlanService planService;
   /* @ApiOperation(value = "简单搜索商品", notes = "根据编码/条形码/检索码搜索商品 | 开发者 孙帮祥 | 开发完")
    @RequestMapping(value="/getGoodsByParam/{param}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "param", value = "检索条件", required = true, dataType = "String",paramType = "path")
    public Result<List<GoodsVO>> getGoodsByParam(HttpSession session,@PathVariable String param){
        Result<List<GoodsVO>> result = new Result<List<GoodsVO>>();
        Page page = new Page(1,99999999);
        UserVO userVO = (UserVO) session.getAttribute("user");
        RequestPlanGoodsVO requestGoodsVO = new RequestPlanGoodsVO();
        requestGoodsVO.setPageNo(1);
        requestGoodsVO.setPageSize(999999999);
        requestGoodsVO.setParam(param);
        requestGoodsVO.setEnterpriseId(userVO.getEnterpriseId());
        Page<List<GoodsVO>> pageList= goodsService.getGoodsListByParam(requestGoodsVO,page);
        List<GoodsVO> goodsList=pageList.getResult();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsList);
        return result;
    }
*/    
	 @ApiOperation(value = "简单搜索商品", notes = "根据编码/条形码/检索码搜索商品 | 开发者 孙帮祥 | 已联调")
	 @RequestMapping(value="/getGoodsByParam/{param}/{supplierId}/{salerId}",method= RequestMethod.GET)
	 @ApiImplicitParam(name = "param", value = "检索条件", required = true, dataType = "String",paramType = "path")
	    public Result<List<GoodsOrderVO>> getGoodsByParam(HttpSession session,@PathVariable String param,@PathVariable Long supplierId,@PathVariable Long salerId){
	        Result<List<GoodsOrderVO>> result = new Result<List<GoodsOrderVO>>();
	        try {
		        UserVO userVO = (UserVO) session.getAttribute("user");
				Map<String, Object> map = new HashMap<>();
	
				map.put("param", param);
				map.put("supplierId", supplierId);
				map.put("salerId", salerId);
			 	Long headEnterpriseId = ChainType.getHeadEnterpriseId(userVO);
			 	map.put("enterpriseId", headEnterpriseId);
			 	map.put("ownerId", headEnterpriseId);
			 	List<GoodsOrderVO> goodsList=purchaseOrderService.getGoods(map);
		        result.setData(goodsList);
		        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
	        } catch (Exception e) {
	        	logger.error("简单搜索商品数据错误:"+e.getMessage(),e);
	        	if(e instanceof BusinessException){
	                result.setBizCodeFallInfo("111111",e.getMessage());
	            } else {
					result.setBizCodeFallInfo(SysCode.FAIL);
	            }
	        }    
	        return result;
	    }
	@ApiOperation(value = "根据采购订单id查询订单详情列表和配送结算", notes = "根据订单id查询订单详情 开发者 | 孙帮祥| 已联调")
    @RequestMapping(value="/getPurchaseOrderById/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "采购订单主键", required = true,paramType = "path")
    public Result<PurchaseOrderResponseVO> getGoodsDetailById(HttpSession session,@PathVariable Long id){
        Result<PurchaseOrderResponseVO> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseOrderService.selectById(id, userVO));
        return result;
    }
    @ApiOperation(value = "分页搜索商品", notes = "分页获取商品 | 开发者 孙帮祥 | 已联调")
	 @RequestMapping(value="/getGoodsPageByParam",method= RequestMethod.GET)
	 @ResponseBody
	    public Result<Page<List<GoodsOrderVO>>> getGoodsPageByParam(HttpSession session,
				@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
				@ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize, 
				@ApiParam(value = "编码/条形码/检索码", required = false) @RequestParam(required=false) String param,
				@ApiParam(value = "供货单位ID", required = true) @RequestParam(required=true) String supplierId,
				@ApiParam(value = "销售人员ID", required = true) @RequestParam(required=true) String salerId,
				@ApiParam(value = "排序字段", required = false) @RequestParam(required=false) String sortField,
				@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort) throws ParseException{
        	Result<Page<List<GoodsOrderVO>>> result = new Result<Page<List<GoodsOrderVO>>>();    
        	Page<List<GoodsOrderVO>> page = new Page<List<GoodsOrderVO>>(pageNo, pageSize);
        	try {
		        UserVO userVO = (UserVO) session.getAttribute("user");
				Map<String, Object> map = new HashMap<>();
				map.put("start",page.getStart());
				map.put("pageSize", pageSize);
				map.put("sortField", sortField);
				map.put("sort", sort);
				Long headEnterpriseId = ChainType.getHeadEnterpriseId(userVO);
				map.put("enterpriseId", headEnterpriseId);
				map.put("ownerId", headEnterpriseId);
				if(param!=null){
					map.put("param", param);
				}
				map.put("supplierId", supplierId);
				map.put("salerId", salerId);
				/* if (ChainType.Headquarters.getCode() == userVO.getChainType()) {
	
				 }else if (ChainType.Selfoperatedshop.getCode() == userVO.getChainType()) {
					 map.put("parentId", userVO.getParentId());
				 }if (ChainType.Division.getCode() == userVO.getChainType()) {
				 	map.put("parentId", userVO.getParentId());
				 }*/
		        purchaseOrderService.getGoodsByPage(page, map);
		        result.setData(page);
		        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
			} catch (Exception e) {
	        	logger.error("简单搜索商品数据错误:"+e.getMessage(),e);
	        	if(e instanceof BusinessException){
	                result.setBizCodeFallInfo("111111",e.getMessage());
	            } else {
					result.setBizCodeFallInfo(SysCode.FAIL);
	            }
	        }    
	        return result;
	    }
    @ApiOperation(value="获取采购订单列表", notes = "分页获取采购订单列表| 开发者 孙帮祥|开发完", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPurchaseOrderPage", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page<PurchaseOrderCountVO>> getPurchaseOrderPage(HttpServletRequest request,
			@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
			
			@ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize, 
			@ApiParam(value = "供货单位编码", required = false) @RequestParam(required=false) String supplierCode,
			@ApiParam(value = "供货单位名称", required = false) @RequestParam(required=false) String supplierName,
			@ApiParam(value = "订单单号", required = false) @RequestParam(required=false) String code,
			@ApiParam(value = "采购人员", required = false) @RequestParam(required=false) String purchaserName,
			@ApiParam(value = "开始日期", required = false) @RequestParam(required=false) Date startDate,
			@ApiParam(value = "结束日期", required = false) @RequestParam(required=false) Date endDate,
			@ApiParam(value = "排序字段", required = false) @RequestParam(required=false) String sortField,
			@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort,
			@ApiParam(value = "状态（待审核-21，审核被驳回-23，待收货-51，待验收-61，待入库-71，已完成-33，已取消-34）", required = false) @RequestParam(required=false) String status){
		Result<Page<PurchaseOrderCountVO>> result = new Result<Page<PurchaseOrderCountVO>>();
        try{
        	if(pageNo <= 0 || pageSize <= 0){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	if(pageNo==1){
        		pageNo=0;
        	}else{
        		pageNo=pageNo-1;
        		pageNo=pageNo*pageSize;
        	}
        	Page<PurchaseOrderCountVO> page = new Page<PurchaseOrderCountVO>(pageNo, pageSize);
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	purchaseOrderService.selectByEnterpriseId(page,enterpriseId,supplierCode,supplierName,code,purchaserName,sort,status,sortField,startDate,endDate);
     /*   	PurchaseOrderCountVO orderCountVO =purchaseOrderService.selectCount();*/
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("采购订单列表分页查询错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
    
    
    
    @ApiOperation(value = "采购订单添加", notes = "采购订单添加 开发者 | 孙帮祥 | 已联调")
    @RequestMapping(value="/addPurchaseOrder",method= RequestMethod.POST)
    @ResponseBody
    public Result<String> addPurchaseOrder(HttpSession session,@ApiParam(value = "采购订单对象", required = true) @RequestBody PurchaseOrderReqVO purchaseOrderRequestVO) throws Exception {
        Result<String> result = new Result<String>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        try {
        	 purchaseOrderService.addOrder(userVO,purchaseOrderRequestVO);
        	 result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		}catch (BusinessException e) {
			logger.error("采购订单添加数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}catch (Exception e) {
			logger.error("采购订单添加数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
        return result;
    }
    @ApiOperation(value = "采购订单修改", notes = "采购订单修改 开发者 | 孙帮祥 | 已联调")
    @RequestMapping(value="/updatePurchaseOrder",method= RequestMethod.POST)
    @ApiImplicitParam(name = "purchaseOrderRequestVO", value = "采购订单对象", required = true, dataType = "PurchaseOrderReqVO")
    @ResponseBody
    public Result updatePurchaseOrder(HttpSession session, @RequestBody PurchaseOrderReqVO purchaseOrderRequestVO) throws Exception {
        Result result = new Result();
        UserVO userVO = (UserVO) session.getAttribute("user");
        try {
        	 purchaseOrderService.updateOrder(userVO,purchaseOrderRequestVO);
        	 result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		} catch (BusinessException e) {
			logger.error("采购订单添加数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		} catch (Exception e) {
			logger.error("采购订单修改数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
        return result;
    }
	@ApiOperation(value = "采购订单导出", notes = "采购订单导出 |开发者:孙帮祥 | 已联调")
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "订单id", required = false, paramType = "query") })
	public void exportOrderDetail(HttpSession session,HttpServletResponse response, @RequestParam("id") Long id) throws Exception {
		String name = "采购订单";
		// 输出Excel文件
		  UserVO userVO = (UserVO) session.getAttribute("user");
		OutputStream output = response.getOutputStream();
		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
		response.setContentType("application/octet-stream;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
		purchaseOrderService.exportExcel(output, id, userVO);
	}

	@ApiOperation(value = "取消采购订单", notes = "取消采购订单 |开发者:孙帮祥 | 已联调")
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(name = "orderId", value = "订单ID", required = false, paramType = "query") })
	public Result<String> cancelPlan(HttpSession session,@RequestParam("orderId") Long orderId, HttpServletRequest request) {
		Result<String> result = new Result<String>();
		try {
			UserVO userVO = (UserVO) session.getAttribute("user");
		    PurchaseOrder order=new PurchaseOrder();
		    order.setId(orderId);
		    order.setStatus(PurchaseStatus.getCode("已取消"));
			purchaseOrderService.changeStatus(userVO,order);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, "采购计划-取消成功");
		} catch (Exception e) {
			logger.error("取消采购计划:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;

	}
  /*  @ApiOperation(value = "采购订单暂存", notes = "采购订单暂存 开发者 | 孙帮祥 | 已联调")
    @RequestMapping(value="/purchaseSave",method= RequestMethod.POST)
    @ResponseBody
    public Result<String> purchaseSave(HttpSession session,@ApiParam(value = "采购订单对象", required = true) @RequestBody PurchaseOrderReqVO purchaseOrderRequestVO) throws Exception {
        Result<String> result = new Result<String>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        try {
        	 purchaseOrderService.temporarySave(userVO,purchaseOrderRequestVO);
        	 result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		}catch (BusinessException e) {
			logger.error("采购订单添加数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}catch (Exception e) {
			logger.error("采购订单添加数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
        return result;
    }
    @ApiOperation(value = "获取暂存采购订单", notes = "获取暂存的采购订单 开发者 | 孙帮祥 | 已联调")
    @RequestMapping(value="/purchaseGet",method= RequestMethod.POST)
    @ResponseBody
    public Result<List<PurchaseOrderReqVO>> purchaseGet(HttpSession session) throws Exception {
        Result<List<PurchaseOrderReqVO>> result = new Result<List<PurchaseOrderReqVO>>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        try {
        	 List<PurchaseOrderReqVO> purchaseList=purchaseOrderService.temporaryGet(userVO);
        	 result.setData(purchaseList);
        	 result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		}catch (BusinessException e) {
			logger.error("采购订单添加数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}catch (Exception e) {
			logger.error("采购订单添加数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
        return result;
    }*/

	@ApiOperation(value = "获取供货单位列表", notes = "获取供货单位列表 | 开发者 孙帮祥 | 已联调")
	@RequestMapping(value = "/getSupplier", method = RequestMethod.GET)
	public Result<List<SupplierOrderVO>> getSupplier(@ApiIgnore UserVO userVO) {
		Result<List<SupplierOrderVO>> result = new Result<List<SupplierOrderVO>>();
		try {
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseOrderService.getSupplier(userVO));
		} catch (Exception e) {
			logger.error("获取供货单位:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	@ApiOperation(value = "根据供货单位ID获取供货单位销售人员", notes = "根据供货单位ID获取供货单位销售人员 | 开发者 孙帮祥 | 已联调")
	@RequestMapping(value = "/getSaleMan", method = RequestMethod.GET)
	public Result<List<SaleManOrderVO>> getSaleMan(HttpSession session,@RequestParam("supplierId") Long supplierId) {
		Result<List<SaleManOrderVO>> result = new Result<List<SaleManOrderVO>>();
		try {
			UserVO userVO = (UserVO) session.getAttribute("user");
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseOrderService.getSaleMan(supplierId));
		} catch (Exception e) {
			logger.error("获取供货单位销售人员:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	@ApiOperation(value = "获取收货单位列表", notes = "获取收货单位列表 | 开发者 孙帮祥 | 已联调")
	@RequestMapping(value = "/getEnterprise", method = RequestMethod.GET)
	public Result<List<EnterpriseOrderVO>> getEnterprise(HttpSession session) {
		Result<List<EnterpriseOrderVO>> result = new Result<List<EnterpriseOrderVO>>();
		try {
			EnterpriseOrderVO ent=new EnterpriseOrderVO();
			UserVO userVO = (UserVO) session.getAttribute("user");
			Long enterpriseId=0L;
			if(userVO.getChainType().equals(0)){//总部
				enterpriseId=userVO.getEnterpriseId();
				ent.setId(userVO.getEnterpriseId());
				ent.setName(userVO.getEnterpriseName());
			}else{
				enterpriseId=userVO.getParentId();
				ent.setId(userVO.getParentId());
				ent.setName(userVO.getParentName());
			}
			List<EnterpriseOrderVO> enterpriseList=new ArrayList<EnterpriseOrderVO>();
			enterpriseList=purchaseOrderService.getEnterprise(enterpriseId);
			if(CollectionUtils.isNotEmpty(enterpriseList)){
				enterpriseList.add(ent);
			}
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,enterpriseList);
		} catch (Exception e) {
			logger.error("获取收货单位:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	@ApiOperation(value = "获取收货人员列表", notes = "获取收货人员列表| 开发者 孙帮祥 | 已联调")
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public Result<List<UserOrderVO>> getUser(@ApiParam(value = "企业ID", required = true) @RequestParam Long enterpriseId) {
		Result<List<UserOrderVO>> result = new Result<List<UserOrderVO>>();
		try {
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseOrderService.getUser(enterpriseId));
		} catch (Exception e) {
			logger.error("获取供货人员:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	@ApiOperation(value = "历史单价", notes = "历史单价 |开发者:孙帮祥 | 已联调")
	@RequestMapping(value = "/getHistoricaUnitPrice", method = RequestMethod.GET)
	public Result<List<HistoricalUnitPriceVO>> getHistoricaUnitPrice(
			@ApiParam(value = "商品ID", required = true) @RequestParam("goodsId") Long goodsId,
			@RequestParam("limit") @ApiParam(value = "条数限制", required = false, defaultValue = "10") Integer limit) {

		Result<List<HistoricalUnitPriceVO>> result = new Result<List<HistoricalUnitPriceVO>>();
		try {
			List<HistoricalUnitPriceVO> historyUnitPrice = planService.selectHistoryUnitPrice(goodsId, limit);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, historyUnitPrice);

		} catch (Exception e) {
			logger.error("获取历史单价:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	@ApiOperation(value = "获取采购人员列表", notes = "获取采购人员列表| 开发者 孙帮祥 | 已联调")
	@RequestMapping(value = "/getPurchaseUser", method = RequestMethod.GET)
	public Result<List<PurchaseUserOrderVO>> getPurchaseUser(@ApiIgnore UserVO userVO){
		Result<List<PurchaseUserOrderVO>> result = new Result<List<PurchaseUserOrderVO>>();
		try {
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseOrderService.getPurchaseUser(userVO.getEnterpriseId()));
		} catch (Exception e) {
			logger.error("获取供货人员:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	@ApiOperation(value = "获取订单修改记录列表", notes = "获取订单修改记录| 开发者 孙帮祥 | 已联调")
	@RequestMapping(value = "/getRecord", method = RequestMethod.GET)
	public Result<List<RecordVO>> getRecord(HttpSession session,@ApiParam(value = "采购订单ID", required = true) @RequestParam Long orderId){
		Result<List<RecordVO>> result = new Result<List<RecordVO>>();
		Map map=new HashMap();
		try {
			UserVO userVO = (UserVO) session.getAttribute("user");
			map.put("keyId", orderId);
			map.put("enterpriseId", userVO.getEnterpriseId());
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseOrderService.getRecord(map));
		} catch (Exception e) {
			logger.error("获取订单修改记录:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	@ApiOperation(value = "获取企业默认收货人员", notes = "获取企业默认收货人员 | 开发者 孙帮祥 | 开发完成")
	@RequestMapping(value = "/getDefaultReciver", method = RequestMethod.GET)
	public Result<UserOrderVO> getDefaultReciver(HttpSession session,@ApiParam(value = "企业ID", required = true) @RequestParam Long id){
		Result<UserOrderVO> result = new Result<UserOrderVO>();
		try {
			UserVO userVO = (UserVO) session.getAttribute("user");
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseOrderService.getManageConfigByEnterpriseId(id));
		} catch (Exception e) {
			logger.error("获取企业默认收货人员失败:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	

    @ApiOperation(value = "查看采购订单草稿缓存", notes = "查看采购订单草稿缓存 | 开发者 孙帮祥 | 已完成")
    @RequestMapping(value = "/daftCache", method = RequestMethod.GET)
    public Result<List<DraftCacheVO>> daftCacheVO(@ApiIgnore UserVO userVO) {

        Result<List<DraftCacheVO>> result = new Result<>();

        List<DraftCacheVO> draftCacheVO = purchaseOrderService.getDraftCacheVO(userVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);

        return  result;
    }

    @ApiOperation(value = "保存采购订单草稿缓存", notes = "保存采购订单草稿缓存 | 开发者 孙帮祥 | 已完成")
    @RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
    public Result<DraftCacheVO<PurchaseOrderReqVO>> saveDraftCache(@ApiIgnore UserVO userVO,@RequestBody DraftCacheVO<PurchaseOrderReqVO> draftCacheVO) {

        Result<DraftCacheVO<PurchaseOrderReqVO>> result = new Result<>();
        
		DraftCacheVO<PurchaseOrderReqVO> purchaseOrderReqVODraftCacheVO = purchaseOrderService.saveDraftCache(userVO, draftCacheVO);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseOrderReqVODraftCacheVO);
        return  result;
    }

    @ApiOperation(value = "删除采购订单草稿缓存", notes = "删除采购订单草稿缓存 | 开发者 孙帮祥 | 已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}", method = RequestMethod.POST)
    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String redisKeyValue) {

        Result result = new Result<>();

        purchaseOrderService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.PURCHASE_ORDER.getCodePrefix(),redisKeyValue);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return  result;
    }
}
