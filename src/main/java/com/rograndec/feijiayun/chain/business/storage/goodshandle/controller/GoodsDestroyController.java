package com.rograndec.feijiayun.chain.business.storage.goodshandle.controller;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroy;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsDestroyService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsDestroyRVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsDestroyVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StockDestroyVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.UserDestroyVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.status.GoodsDestroyStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 孙帮祥
 *
 */
@Api(value = "storage_goodsHandle_goodsDestroy",description = "储存管理-商品销毁-商品销毁")
@RestController
@RequestMapping("storage/goodsHandle/goodsDestroy")
@Validated
public class GoodsDestroyController {
	private static final Logger logger = LoggerFactory.getLogger(GoodsDestroyController.class);
	@Autowired
	private GoodsDestroyService goodsDestroyService;
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
	 @ApiOperation(value = "查询商品库存列表", notes = "根据编码/条形码/检索码/商品名称/通用名称/批准文号 搜索商品 | 开发者 孙帮祥 | 已联调")
	 @RequestMapping(value="/getGoodsList/{param}/{specialDrug}",method= RequestMethod.GET)
	    public Result<List<GoodsDestroyVO>> getGoodsList(HttpSession session,@ApiParam(value = "关键字", required = true) @PathVariable String param,@ApiParam(value = "销毁品种(1为特殊管理药品，0为常规药品)", required = false) @PathVariable String specialDrug){
	        Result<List<GoodsDestroyVO>> result = new Result<List<GoodsDestroyVO>>();
	        UserVO userVO = (UserVO) session.getAttribute("user");
	        int special=0;
	        if(specialDrug!=null){
	        	if(specialDrug.equals("1")){
	        		special=1;
	        	}
	        }
			Map<String, Object> map = new HashMap<>();
			if(userVO.getChainType().equals(0)){
				map.put("parentId", userVO.getEnterpriseId());
			}else{
				map.put("parentId", userVO.getParentId());
			}
			map.put("enterpriseId", userVO.getEnterpriseId());
			map.put("param", param);
			map.put("specialDrug", special);
			map.put("jobArea", 2);
	        List<GoodsDestroyVO> goodsList=goodsDestroyService.getGoodsList(map);
	        result.setData(goodsList);
	        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
	        return result;
	    }
	 @ApiOperation(value = "根据商品查询货位", notes = "根据商品Id查询货位 | 开发者 孙帮祥 | 已联调")
	@RequestMapping(value="/getGoodsStockList/{goodsId}/{lotId}",method= RequestMethod.GET)
	public Result<List<StockDestroyVO>> getGoodsStockList(HttpSession session,@ApiParam(value = "商品id查询货位", required = true) @PathVariable Long goodsId,@ApiParam(value = "批号ID", required = true) @PathVariable Long lotId){
		Result<List<StockDestroyVO>> result = new Result<List<StockDestroyVO>>();
		UserVO userVO = (UserVO) session.getAttribute("user");
		Map<String, Object> map = new HashMap<>();
		map.put("enterpriseId", userVO.getEnterpriseId());
		map.put("goodsId", goodsId);
		map.put("lotId", lotId);
		map.put("jobArea", 2);
		List<StockDestroyVO> goodsList=goodsDestroyService.getStockList(map);
		//result.setData(goodsList);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsList);
		return result;
	}
	@ApiOperation(value = "新增商品销毁单", notes = "保存商品销毁单 | 开发者孙帮祥 | 已联调")
    @RequestMapping(value="/addGoodsDestroyOrder", method=RequestMethod.POST)
    public Result<Object> addGoodsDestroyOrder(HttpSession session,@ApiParam(value = "商品销毁单对象", required = true) @RequestBody GoodsDestroyRVO goodsDestroyVO){
		 Result<Object> result = new Result<Object>();
	        UserVO userVO = (UserVO) session.getAttribute("user");
			Map<String, Object> map = new HashMap<>();
			try {
				   goodsDestroyService.saveGoodsDestroy(userVO, goodsDestroyVO);
				   result.setBizCodeSuccessInfo(SysCode.SUCCESS);
			}catch (BusinessException e) {
				result.setBizCodeFallInfo("200100",e.getMessage());
				logger.error("商品销毁单添加数据错误:"+e.getMessage(),e);
			}catch (Exception e) {
				result.setBizCodeFallInfo(SysCode.FAIL);
				logger.error("商品销毁单添加数据错误:"+e.getMessage(),e);
			}
			return result;
	}
/*	@ApiOperation(value = "修改商品销毁单", notes = "保存商品销毁单 | 开发者 李中义 | 开发完")
    @RequestMapping(value="/updateGoodsDestroyOrder", method=RequestMethod.POST)
    public Result<Object> updateGoodsDestroyOrder(HttpSession session,@ApiParam(value = "商品销毁单对象", required = true) @RequestBody GoodsDestroyRVO goodsDestroyVO){
		 Result<Object> result = new Result<Object>();
	        UserVO userVO = (UserVO) session.getAttribute("user");
			Map<String, Object> map = new HashMap<>();
			try {
				   goodsDestroyService.updateGoodsDestroy(userVO, goodsDestroyVO);
			}catch (BusinessException e) {
				result.setBizCodeFallInfo("200100",e.getMessage());
				logger.error("商品销毁单修改数据错误:"+e.getMessage(),e);
			}catch (Exception e) {
				result.setBizCodeFallInfo(SysCode.FAIL);
				logger.error("商品销毁单修改数据错误:"+e.getMessage(),e);
			}
			return result;
	}*/
	  @ApiOperation(value="查询商品销毁单列表", notes = "分页获取商品销毁单列表| 开发者 孙帮祥|已联调", produces = MediaType.APPLICATION_JSON_VALUE)
		@RequestMapping(value = "/getGoodsDestroyOrderList", method = RequestMethod.GET)
		@ResponseBody
		public Result<Page<GoodsDestroyRVO>> getPurchaseOrderPage(HttpServletRequest request,
				@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
				@ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize, 
				@ApiParam(value = "开始日期", required = false) @RequestParam(required=false) Date startDate,
				@ApiParam(value = "结束日期", required = false) @RequestParam(required=false) Date endDate,
				@ApiParam(value = "销毁单号", required = false) @RequestParam(required=false) String code,
				@ApiParam(value = "状态(21-待审核23-审核驳回33-已销毁34-已取消)", required = false)@RequestParam(required=false) String status,
				@ApiParam(value = "销毁人员", required = false) @RequestParam(required=false) String destoryManName,
				@ApiParam(value = "销毁品种", required = false) @RequestParam(required=false) String destoryGoods,
				@ApiParam(value = "销毁原因", required = false) @RequestParam(required=false) String destoryReason,
				@ApiParam(value = "排序字段", required = false) @RequestParam(required=false) String sortField,
				@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
			Result<Page<GoodsDestroyRVO>> result = new Result<Page<GoodsDestroyRVO>>();
	        try{
	        	if(pageNo <= 0 || pageSize <= 0){
	        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
	    			return result;
	        	}
	        	Page<GoodsDestroyRVO> page = new Page<GoodsDestroyRVO>(pageNo, pageSize);
	        	HttpSession session = request.getSession(true);
	        	UserVO loginUser = (UserVO) session.getAttribute("user");
	        	Long enterpriseId=loginUser.getEnterpriseId();
	        	if(sortField!=null){
	        		if(sortField.equals("destroyDate")){
	        			sortField="destroy_date";
	        		}
	        	}
	        	if(startDate!=null){
	        		if(startDate.equals("")){
	        			startDate=null;
		        	}
	        	}
	        	if(endDate!=null){
	        		if(endDate.equals("")){
	        			endDate=null;
		        	}
	        	}
	        	if(code!=null){
	        		if(code.equals("")){
	        			code=null;
		        	}
	        	}
	        	if(destoryManName!=null){
	        		if(destoryManName.equals("")){
	        			destoryManName=null;
		        	}
	        	}
	        	if(destoryGoods!=null){
	        		if(destoryGoods.equals("")){
	        			destoryGoods=null;
		        	}
	        	}
	        	if(destoryReason!=null){
	        		if(destoryReason.equals("")){
	        			destoryReason=null;
		        	}
	        	}
	        	if(sortField!=null){
	        		if(sortField.equals("")){
	        			sortField=null;
		        	}
	        	}
	        	if(sort!=null){
	        		if(sort.equals("")){
	        			sort=null;
		        	}
	        	}
	        	if(pageNo==1){
	        		pageNo=0;
	        	}else{
	        		pageNo=pageNo-1;
	        		pageNo=pageNo*pageSize;
	        	}
	        	Map map=new HashMap();
	        	map.put("enterpriseId", enterpriseId);
	        	map.put("pageNo", pageNo);
	        	map.put("pageSize", pageSize);

				if(!StringUtils.isEmpty(status)){
					map.put("status", status);
				}

	        	map.put("startDate", startDate);
	        	map.put("endDate", endDate);
	        	map.put("code", code);
	        	map.put("destroyManName", destoryManName);
	        	map.put("destroyGoods", destoryGoods);
	        	map.put("destroyReason", destoryReason);
	        	map.put("sortField", sortField);
	        	map.put("sort", sort);
	        	goodsDestroyService.getGoodsDestroyList(page, map);
	        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
	        }catch(Exception e){
	        	logger.error("商品销毁单列表分页查询错误:"+e.getMessage(),e);
				result.setBizCodeFallInfo(SysCode.FAIL);
				return result;
	        }
			return result;
		}
	@ApiOperation(value = "查询商品销毁单明细列表", notes = "根据商品销毁单ID查询商品销毁单明细列表 | 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/getGoodsDestroyOrder/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "商品销毁单主键", required = true,paramType = "path")
    public Result<GoodsDestroyRVO> getGoodsDetailById(@PathVariable Long id){
        Result<GoodsDestroyRVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsDestroyService.getGoodsDestroyById(id));
        return result;
    }
	@ApiOperation(value = "导出Excel", notes = "按照模版将商品销毁单导出至Excel | 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/exportExcel", method=RequestMethod.GET)
    public void exportExcel(HttpSession session,HttpServletResponse response, @RequestParam("id") Long id) throws IOException{
		String name = "商品销毁";
		// 输出Excel文件
		  UserVO userVO = (UserVO) session.getAttribute("user");
		OutputStream output = response.getOutputStream();
		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
		response.setContentType("application/octet-stream;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
		goodsDestroyService.exportExcel(output, id, userVO);
	}
	@ApiOperation(value = "取消销毁单", notes = "取消销毁单| 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/cancelDestroyOrder/{id}", method=RequestMethod.GET)
	@ApiImplicitParam(name = "id", value = "销毁单主键", required = true,paramType = "path")
    public Result<String> cancelReqPlanOrder(HttpSession session,@PathVariable Long id){
	     Result<String> result = new Result<>();
	     GoodsDestroy destroy=new GoodsDestroy();
		 UserVO userVO = (UserVO) session.getAttribute("user");
		 destroy.setId(id);
		 destroy.setStatus(GoodsDestroyStatus.CANCELED);
	     try {
	    	goodsDestroyService.changeStatus(userVO, destroy);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		} catch (Exception e) {
			result.setBizCodeFallInfo(SysCode.FAIL);
			e.printStackTrace();
		}
	     return result;
	}
}
