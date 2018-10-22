package com.rograndec.feijiayun.chain.business.goods.manage.controller;

import com.rograndec.feijiayun.chain.business.basic.warehouse.service.WarehouseService;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseVO;
import com.rograndec.feijiayun.chain.business.goods.manage.service.GoodsManageHeadquartersService;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.EnterpriseHeadquartersDetailVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.EnterpriseHeadquartersVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsHeadquartersDetailVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsHeadquartersVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.QualityCondition;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;



@Api(value = "goodsManageHeadquarters", description = "商品管理-商品管理-总部服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/goodsManage/goodsManageHeadquarters")
public class GoodsManageHeadquartersController {
	
	private static final Log logger = LogFactory.getLog(GoodsManageHeadquartersController.class);

	@Autowired
    private WarehouseService warehouseService;
	
	@Autowired
	private GoodsManageHeadquartersService goodsManageHeadquartersService;
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@ApiOperation(value="分页获取按商品展示列表信息", notes = "分页获取按商品展示列表信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getGoodsManagePageByGoods", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page<List<GoodsHeadquartersVO>>> getGoodsManagePageByGoods(HttpServletRequest request,
			@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
			@ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize, 
			@ApiParam(value = "页面搜索框", required = false) @RequestParam(required=false) String key,
			@ApiParam(value = "按某一列排序", required = false) @RequestParam(required=false) String order,
			@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
		Result<Page<List<GoodsHeadquartersVO>>> result = new Result<Page<List<GoodsHeadquartersVO>>>();
        try{
        	if(pageNo <= 0 || pageSize <= 0){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	
        	Page page = new Page(pageNo, pageSize);
        	
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	//@xinhao
        	List<GoodsHeadquartersVO> goodsVoList = goodsManageHeadquartersService
        			.selectGoodsHeadquartersVOPage(pageNo, pageSize, loginUser.getEnterpriseId(),key, order, sort, page,loginUser);
        	for(GoodsHeadquartersVO vo:goodsVoList){
        		if(vo.getChainType().equals("0")){//商品属于总部的可以修改
        				vo.setUpdateFlag(true);
        		}else{//加盟店
        				vo.setUpdateFlag(true);
        		}
        	}
        	page.setResult(goodsVoList);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("分页获取按商品展示列表信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	


	@ApiOperation(value="获取定位门店类型信息", notes = "获取定位门店类型信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getChainType", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getChainType(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	SelectBean bean = new SelectBean(); 
        	bean.setId(String.valueOf(3));
        	bean.setText("全部");
        	list.add(bean);
        	for (ChainType s : ChainType.values()){ 
        		bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getCode()));
        		bean.setText(String.valueOf(s.getName()));
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取定位门店类型信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取按商品展示明细列表信息", notes = "获取按商品展示明细列表信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getGoodsManageDetailByGoods", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<GoodsHeadquartersDetailVO>> getGoodsManageDetailByGoods(HttpServletRequest request,
			@ApiParam(value = "商品ID", required = true) @RequestParam(required=true) Long goodsId,
			@ApiParam(value = "定位下拉框", required = false) @RequestParam(required=false) Integer chainType,
			@ApiParam(value = "页面搜索框", required = false) @RequestParam(required=false) String key,
			@ApiParam(value = "按某一列排序", required = false) @RequestParam(required=false) String order,
			@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
		Result<List<GoodsHeadquartersDetailVO>> result = new Result<List<GoodsHeadquartersDetailVO>>();
        try{
        	
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<GoodsHeadquartersDetailVO> goodsDetailVoList = goodsManageHeadquartersService
        			.selectGoodsHeadquartersDetail(loginUser.getEnterpriseId(), goodsId,
        					chainType, key, order, sort);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsDetailVoList);
        }catch(Exception e){
        	logger.error("获取按商品展示明细列表信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value = "仓库-》库区-》货区-》货位--树形展示（含空树）", notes = "仓库-》库区-》货区-》货位--树形展示（如果子级没有数据，则父级不显示，PS:仓库A下的库区A货区A,如果货区A下没有子集那么仓库A库区A货区A都“显示”,） 开发者 | 孙腾")
    @RequestMapping(value = "/getWarehouseTreeIncludeNullByParam", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<TreePOJO<WarehouseVO>>> getWarehouseTreeIncludeNullByParam(HttpServletRequest request,
    		@ApiParam(value = "查询状态（0/禁用；1/启用；2/全部）", required = true) @RequestParam(required=true) Integer status,
    		@ApiParam(value = "药店ID", required = true) @RequestParam(required=true) Long enterpriseId) {
        Result<List<TreePOJO<WarehouseVO>>> result = new Result<>();
        try {
        	if(enterpriseId == null || enterpriseId == 0){
        		HttpSession session = request.getSession(true);
        		UserVO loginUser = (UserVO) session.getAttribute("user");
        		enterpriseId = loginUser.getEnterpriseId();

        	}
        	
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, warehouseService.getWarehouseShelfTreeByParam(enterpriseId, 
            		status == 2 ? null : status, QualityCondition.NONCONFORMING_PRODUCT.getCode()));
        } catch (Exception e) {
            logger.error("仓库-》库区-》货区-》货位--树形展示（含空树）:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	
	@ApiOperation(value="按商品明细列表保存接口", notes = "按商品明细列表保存接口 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/saveGoodsManageDetailByGoods", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> saveGoodsManageDetailByGoods(HttpServletRequest request,
			@RequestBody List<GoodsHeadquartersDetailVO> goodsDetailList){
		Result<String> result = new Result<String>();
        try{
        	
        	if(goodsDetailList == null || goodsDetailList.size() == 0){
        		result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS, "保存数据为空");
        		return result;
        	}
        	
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	String msg = goodsManageHeadquartersService
        			.saveGoodsHeadquartersDetail(loginUser, goodsDetailList);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, msg);
        }catch(Exception e){
        	logger.error("按商品明细列表保存信息错误:"+e.getMessage(),e);
        	result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), e.getMessage(), null);
        	return result;
        }
		return result;
	}

	
	
	
	@ApiOperation(value="获取按组织机构列表下拉框信息", notes = "获取按组织机构列表下拉框信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEnterpriseSelectBean", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getEnterpriseSelectBean(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	SelectBean bean = new SelectBean(); 
        	bean.setId(String.valueOf(0));
        	bean.setText("商品");
        	list.add(bean);
        	bean = new SelectBean(); 
        	bean.setId(String.valueOf(1));
        	bean.setText("组织机构");
        	list.add(bean);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
        	logger.error("获取按组织机构列表下拉框信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="分页获取按组织机构展示列表信息", notes = "分页获取按组织机构展示列表信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getGoodsManagePageByEnterprise", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page<List<EnterpriseHeadquartersVO>>> getGoodsManagePageByEnterprise(HttpServletRequest request,
			@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
			@ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize, 
			@ApiParam(value = "下拉框(0-商品，1-组织机构)", required = true) @RequestParam(required=true) Integer type,
			@ApiParam(value = "页面搜索框", required = false) @RequestParam(required=false) String key,
			@ApiParam(value = "按某一列排序", required = false) @RequestParam(required=false) String order,
			@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
		Result<Page<List<EnterpriseHeadquartersVO>>> result = new Result<Page<List<EnterpriseHeadquartersVO>>>();
        try{
        	if(pageNo <= 0 || pageSize <= 0){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	
        	Page page = new Page(pageNo, pageSize);
        	
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	//@xinhao
        	List<EnterpriseHeadquartersVO> goodsVoList = goodsManageHeadquartersService
        			.selectEnterpriseHeadquartersVOPage(pageNo, pageSize, loginUser.getEnterpriseId(),type, key, order, sort, page);
        	for(EnterpriseHeadquartersVO vo:goodsVoList){
        		if(vo.getChainType().equals(ChainType.Headquarters.getCode())){//总部
        				vo.setUpdateFlag(true);
        		}else{//加盟店
        				vo.setUpdateFlag(false);
        		}
        	}
        	page.setResult(goodsVoList);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("分页获取按组织机构展示列表信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	/* 删除接口，，直接从列表获取
	 * @ApiOperation(value="按组织机构，获取修改页面信息", notes = "按组织机构，获取修改页面信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getGoodsManageDetailByEnterprise", method = RequestMethod.GET)
	@ResponseBodyEntity
	public Result<EnterpriseHeadquartersDetailVO> getGoodsManageDetailByEnterprise(HttpServletRequest request,
			@ApiParam(value = "商品管理ID", required = true) @RequestParam Long id){
		Result<EnterpriseHeadquartersDetailVO> result = new Result<EnterpriseHeadquartersDetailVO>();
        try{
        	
        	EnterpriseHeadquartersDetailVO vo = goodsManageHeadquartersService
        			.queryEnterpriseHeadquartersDetailVO(id);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
        	logger.error("按组织机构，获取修改页面信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}*/
	
	
	@ApiOperation(value="按组织机构保存明细 ", notes = "按组织机构保存明细 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/saveGoodsManageDetailByEnterprise", method = RequestMethod.POST)
	public Result<String> saveGoodsManageDetailByEnterprise(HttpServletRequest request,
			@RequestBody EnterpriseHeadquartersDetailVO vo) {
		Result<String> result = new Result<String>();
		try{
			HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	String msg = goodsManageHeadquartersService.saveGoodsDetailByEnterprise(vo, loginUser);
			
        	if(StringUtils.isNotBlank(msg)){
				result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS, msg);
				return result;
			}
        	
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,"");
		}catch(Exception e){
			logger.error("按组织机构保存明细信息错误:"+e.getMessage(),e);
			result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), e.getMessage(), null);
			return result;
		}
		return result;
	}
	
}
