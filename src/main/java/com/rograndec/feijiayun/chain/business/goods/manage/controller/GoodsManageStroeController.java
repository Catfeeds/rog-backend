package com.rograndec.feijiayun.chain.business.goods.manage.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.business.goods.manage.service.GoodsManageStoreService;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.EnterpriseHeadquartersVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsHeadquartersVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsStoreDetailVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsStorePageVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;



@Api(value = "goodsManageStore", description = "商品管理-商品管理-分店服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/goodsManage/goodsManageStore")
public class GoodsManageStroeController {
	
	private static final Log logger = LogFactory.getLog(GoodsManageStroeController.class);
	
    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;
	@Autowired
	GoodsManageStoreService goodsManageStoreService;
	
	@Autowired
	EnterpriseService enterpriseService;
	
	@ApiOperation(value="分店-分页获取商品管理门店数据", notes = "分页获取商品管理门店数据 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getGoodsManagePage", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page<List<GoodsStorePageVO>>> getGoodsManagePage(HttpServletRequest request,
			@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
			@ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize, 
			@ApiParam(value = "页签类型 9-全部，0-待上架，2-出售中，3-已售罄，4-已下架", required = false) @RequestParam(required=false) Integer type,
			@ApiParam(value = "页面搜索框", required = false) @RequestParam(required=false) String key,
			@ApiParam(value = "按某一列排序", required = false) @RequestParam(required=false) String order,
			@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
		Result<Page<List<GoodsStorePageVO>>> result = new Result<Page<List<GoodsStorePageVO>>>();
        try{
        	if(pageNo <= 0 || pageSize <= 0){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	
        	Page page = new Page(pageNo, pageSize);
        	
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	List<GoodsStorePageVO> goodsStoreList = goodsManageStoreService
        			.selectGoodsManagePage(pageNo, pageSize, loginUser.getEnterpriseId(), type, key, order, sort, page);
        	  //控制开关
        	  EnterpriseBusiness enterpriseBusiness=enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
        	for(GoodsStorePageVO vo:goodsStoreList){
        			if(loginUser.getChainType().equals(ChainType.Selfoperatedshop.getCode())){//自营店
        				if(enterpriseBusiness.getReasonableStock().equals(0)){//0-总部控制；1-自主控制
        					vo.setUpdateFlag(false);
        				}else{
        					vo.setUpdateFlag(true);
        				}
        			}else{//加盟店
        				if(vo.getChainType().equals("0")){//总部的商品
        					if(enterpriseBusiness.getReasonableStock().equals(0)){//0-总部控制；1-自主控制
            					vo.setUpdateFlag(false);
            				}else{
            					vo.setUpdateFlag(true);
            				}
        				}else{//加盟店自己的商品
        					vo.setUpdateFlag(true);
        				}
        			}
        	}
        	page.setResult(goodsStoreList);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("分店-分页获取商品管理门店数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	/* 从页面获取不调接口
	 * @ApiOperation(value="按组织机构-修改,获取页面信息", notes = "按组织机构-修改,获取页面信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getGoodsStoreDetailByGoodsId", method = RequestMethod.GET)
	@ResponseBodyEntity
	public Result<GoodsStoreDetailVO> getGoodsStoreDetailByGoodsId(HttpServletRequest request,
			@ApiParam(value = "商品ID", required = true) @RequestParam Long goodsId){
		Result<GoodsStoreDetailVO> result = new Result<GoodsStoreDetailVO>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	GoodsStoreDetailVO vo = goodsManageStoreService
        			.queryGoodsStoreDetailVO(goodsId, loginUser.getEnterpriseId());
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
        	logger.error("按组织机构-修改,获取页面信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}*/
	
	
	@ApiOperation(value="分店-操作修改保存数据", notes = "分店-操作修改保存数据 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/saveGoodsStoreDetail", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> saveGoodsStoreDetail(HttpServletRequest request,
			@RequestBody GoodsStoreDetailVO vo){
		Result<String> result = new Result<String>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	String msg = goodsManageStoreService.saveGoodsStoreDetail(vo, loginUser);
        	if(StringUtils.isNotBlank(msg)){
        		result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS, msg);
        		return result;
        	}
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, "");
        }catch(Exception e){
        	logger.error("分店-操作修改保存数据错误:"+e.getMessage(),e);
        	result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), e.getMessage(), null);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="批量上架接口", notes = "批量上架接口 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/batchOnShelfByGoodsId", method = RequestMethod.GET)
	@ResponseBody
	public Result<String> batchOnShelfByGoodsId(HttpServletRequest request,
			@ApiParam(value = "商品管理ID,多个时以数组传输", required = true) @RequestParam(required=false) String[] ids){
		Result<String> result = new Result<String>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	Map<String, String> map = goodsManageStoreService
        			.batchOnShelfByIds(ids, loginUser);
        	
        	if(map != null && StringUtils.isNotBlank(map.get("errMsg"))){
        		result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS, map.get("errMsg"));
        		return result;
        	}
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, 
        			new StringBuilder().append("您已成功上架了").append(map.get("total")).append("条商品").toString());
        }catch(Exception e){
        	logger.error("调用批量上架接口错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="批量下架接口", notes = "批量下架接口 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/batchOffShelfByGoodsId", method = RequestMethod.GET)
	@ResponseBody
	public Result<String> batchOffShelfByGoodsId(HttpServletRequest request,
			@ApiParam(value = "商品管理ID,多个时以数组传输", required = true) @RequestParam(required=false) String[] ids){
		Result<String> result = new Result<String>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	Map<String, String> map = goodsManageStoreService
        			.batchOffShelfByIds(ids, loginUser);
        	
        	if(map != null && StringUtils.isNotBlank(map.get("errMsg"))){
        		result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS, map.get("errMsg"));
        		return result;
        	}
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, 
        			new StringBuilder().append("您已成功下架了").append(map.get("total")).append("条商品").toString());
        }catch(Exception e){
        	logger.error("调用批量下架接口错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}

	
}
