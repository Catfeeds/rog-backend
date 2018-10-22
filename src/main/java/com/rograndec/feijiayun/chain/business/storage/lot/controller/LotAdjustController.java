package com.rograndec.feijiayun.chain.business.storage.lot.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.rograndec.feijiayun.chain.business.storage.goodshandle.controller.GoodsDestroyController;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsDestroyRVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsDestroyVO;
import com.rograndec.feijiayun.chain.business.storage.lot.service.LotAdjustService;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.GoodsLotNumberVO;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.LotAdjustVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "storage_lotAdjust",description = "储存管理-批号管理-批号调整")
@RestController
@RequestMapping("storage/lotAdjust")
@Validated
public class LotAdjustController {
	private static final Logger logger = LoggerFactory.getLogger(LotAdjustController.class);
	@Autowired
	private LotAdjustService lotAdjustService;
	 @ApiOperation(value = "查询商品列表", notes = "根据编码/条形码/检索码/商品名称/通用名称/批准文号 搜索商品 | 开发者 孙帮祥 | 已联调")
	 @RequestMapping(value="/getGoodsList/{param}",method= RequestMethod.GET)
	    public Result<List<GoodsLotNumberVO>> getGoodsList(HttpSession session,@ApiParam(value = "关键字", required = true) @PathVariable String param){
	        Result<List<GoodsLotNumberVO>> result = new Result<List<GoodsLotNumberVO>>();
	        UserVO userVO = (UserVO) session.getAttribute("user");
			Map<String, Object> map = new HashMap<>();
			if(userVO.getChainType()!=null){
				if(userVO.getChainType().equals(0)){
					map.put("enterpriseId", userVO.getEnterpriseId());
					map.put("parentId", userVO.getEnterpriseId());
					map.put("param", param);
				}else{
					map.put("parentId", userVO.getParentId());
					map.put("enterpriseId", userVO.getEnterpriseId());
					map.put("param", param);
				}
			}
			result.setBizCodeSuccessInfo(SysCode.SUCCESS);
			result.setData(lotAdjustService.getGoodsList(map));
	        return result;
	    }
	        @ApiOperation(value="查询批号调整单列表", notes = "分页获取批号调整单列表| 开发者 孙帮祥|已联调", produces = MediaType.APPLICATION_JSON_VALUE)
			@RequestMapping(value = "/getLotNumberAdjustOrderList", method = RequestMethod.GET)
			@ResponseBody
			public Result<Page<LotAdjustVO>> getLotNumberAdjustOrderList(HttpServletRequest request,
					@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
					@ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize, 
					@ApiParam(value = "开始日期", required = false) @RequestParam(required=false) Date startDate,
					@ApiParam(value = "结束日期", required = false) @RequestParam(required=false) Date endDate,
					@ApiParam(value = "调整单号", required = false) @RequestParam(required=false) String code,
					@ApiParam(value = "调整人员", required = false) @RequestParam(required=false) String adjustManName,
					@ApiParam(value = "排序字段", required = false) @RequestParam(required=false) String sortField,
					@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
				Result<Page<LotAdjustVO>> result = new Result<Page<LotAdjustVO>>();
		        try{
		        	if(pageNo <= 0 || pageSize <= 0){
		        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
		    			return result;
		        	}
		        	Page<LotAdjustVO> page = new Page<LotAdjustVO>(pageNo, pageSize);
		        	HttpSession session = request.getSession(true);
		        	UserVO loginUser = (UserVO) session.getAttribute("user");
		        	Long enterpriseId=loginUser.getEnterpriseId();
		        	Map map=new HashMap();
		        	if(sortField!=null){
		        	  if(sortField.equals("adjustDate")){
		        		sortField="adjust_date";
		        	  }
		        	}
		        	if(pageNo==1){
		        		pageNo=0;
		        	}else{
		        		pageNo=pageNo-1;
		        		pageNo=pageNo*pageSize;
		        	}
		        	map.put("enterpriseId", enterpriseId);
		        	map.put("pageNo", pageNo);
		        	map.put("pageSize", pageSize);
		        	map.put("startDate", startDate);
		        	map.put("endDate", endDate);
		        	map.put("code", code);
		        	map.put("adjustManName", adjustManName);
		        	map.put("sortField", sortField);
		        	map.put("sort", sort);
		        	
		        	lotAdjustService.getLotAdjustList(page, map);
		        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		        }catch(Exception e){
		        	logger.error("批号管理列表分页查询错误:"+e.getMessage(),e);
					result.setBizCodeFallInfo(SysCode.FAIL);
					return result;
		        }
				return result;
			}
	@ApiOperation(value = "查询批号调整明细明细", notes = "查询批号调整单明细列表 | 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/getLotNumberAdjustOrderDtlList/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "查询批号调整单", required = true,paramType = "path")
    public Result<LotAdjustVO> getLotNumberAdjustOrderDtlList(@PathVariable Long id){
        Result<LotAdjustVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,lotAdjustService.getLotAdjustById(id));
        return result;
    }

	@ApiOperation(value = "保存批号调整单", notes = "保存商品销毁单 | 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/saveLotNumberAdjustOrder", method=RequestMethod.POST)
    public Result<Object> saveLotNumberAdjustOrder(HttpSession session,@ApiParam(value = "批号调整单对象", required = true) @RequestBody LotAdjustVO lotAdjustVO){
		 Result<Object> result = new Result<Object>();
	        UserVO userVO = (UserVO) session.getAttribute("user");
			Map<String, Object> map = new HashMap<>();
			try {
				lotAdjustService.saveLotAdjust(userVO, lotAdjustVO);
				result.setBizCodeSuccessInfo(SysCode.SUCCESS);
			}catch (BusinessException e) {
				result.setBizCodeFallInfo("200100",e.getMessage());
				logger.error("批号调整单添加数据错误:"+e.getMessage(),e);
			}catch (Exception e) {
				result.setBizCodeFallInfo(SysCode.FAIL);
				logger.error("批号调整单添加数据错误:"+e.getMessage(),e);
			}
			return result;
	}
	@ApiOperation(value = "导出Excel", notes = "按照模版将批号调整单导出至Excel | 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/exportExcel", method=RequestMethod.GET)
    public void exportExcel(HttpSession session,HttpServletResponse response, @RequestParam("id") Long id) throws IOException{
		String name = "批号调整";
		// 输出Excel文件
		UserVO userVO = (UserVO) session.getAttribute("user");
		OutputStream output = response.getOutputStream();
		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
		response.setContentType("application/octet-stream;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
		lotAdjustService.exportExcel(output, id, userVO);
	}

}
