package com.rograndec.feijiayun.chain.business.purchase.instorage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanDraftCacheVO;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.service.WarehouseService;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.service.StayInstorageService;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.StayInstorageDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.StayInstorageFormVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.StayInstorageOtherVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.StayInstorageSaveVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import springfox.documentation.annotations.ApiIgnore;


@Api(value = "stayInstorage", description = "采购管理-采购入库-待入库", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/instorage/stayInstorage")
public class StayInstorageController {
	
	private static final Log logger = LogFactory.getLog(StayInstorageController.class);
	
	@Autowired
	private StayInstorageService stayInstorageService;
	
	@Autowired
    private WarehouseService warehouseService;
	
	//基础数据质量控制取EnterpriseController.getEntrepriseValidate
	
	@ApiOperation(value="待入库-入库-form及页脚数据", notes = "待入库-入库-form及页脚数据 | 开发者:刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStayInstorageForm", method = RequestMethod.GET)
	@ResponseBody
	public Result<StayInstorageFormVO> getStayInstorageForm(HttpServletRequest request,
			@ApiParam(value = "验收单ID", required = true) @RequestParam(required=false) Long id){
		Result<StayInstorageFormVO> result = new Result<StayInstorageFormVO>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	StayInstorageFormVO vo = stayInstorageService.queryStayInstorageFormByCheckId(id, loginUser);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
        	logger.error("获取待入库-入库-form及页脚数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="待入库-入库-入库明细数据", notes = "待入库-入库-入库明细数据 | 开发者:刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStayInstorageDetailList", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<StayInstorageDetailVO>> getStayInstorageDetailList(HttpServletRequest request,
			@ApiParam(value = "验收单ID", required = true) @RequestParam(required=false) Long id){
		Result<List<StayInstorageDetailVO>> result = new Result<List<StayInstorageDetailVO>>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<StayInstorageDetailVO> voList = stayInstorageService.selectStayInstorageDetailListByCheckId(id, loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, voList);
        	
        }catch(Exception e){
        	logger.error("获取待入库-入库-入库明细数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="待入库-入库-配送和结算数据", notes = "待入库-入库-配送和结算数据 | 开发者:刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStayInstorageOther", method = RequestMethod.GET)
	@ResponseBody
	public Result<StayInstorageOtherVO> getStayInstorageOther(HttpServletRequest request,
			@ApiParam(value = "验收单ID", required = true) @RequestParam(required=false) Long id){
		Result<StayInstorageOtherVO> result = new Result<StayInstorageOtherVO>();
        try{
        	
        	StayInstorageOtherVO vo = stayInstorageService.queryStayInstorageOther(id);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
        	logger.error("获取待入库-入库-form及页脚数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value = "待入库-入库-入库明细数据-货位选择树", notes = "仓库-》库区-》货区-》货位--树形展示 开发者:刘群 | 已完成")
    @RequestMapping(value = "/getWarehouseTreeByParam", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<TreePOJO<WarehouseVO>>> getWarehouseTreeIncludeNullByParam(HttpServletRequest request,
    		@ApiParam(value = "质量状况0-合格品，2不合格品", required = true) @RequestParam(required=true) Integer jobArea) {
        Result<List<TreePOJO<WarehouseVO>>> result = new Result<>();
        try {
        	
    		HttpSession session = request.getSession(true);
    		UserVO loginUser = (UserVO) session.getAttribute("user");
        	
    		if(jobArea == null || (jobArea !=0 && jobArea != 2)){
    			result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,null);
    			return result;
    		}
    		
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, warehouseService.getWarehouseShelfTreeByParam(loginUser.getEnterpriseId(), EnableStatus.ENABLE.getStatus(), jobArea));
        } catch (Exception e) {
            logger.error("待入库-入库-入库明细数据-货位选择树:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	
	@ApiOperation(value="待入库-入库-保存数据", notes = "待入库-入库-保存数据 | 开发者:刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/saveStayInstorage", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> saveStayInstorage(HttpServletRequest request,
			@RequestBody StayInstorageSaveVO vo){
		Result<String> result = new Result<String>();
		try{
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			if(vo.getStayInstorageDetailSaveVO() == null || vo.getStayInstorageDetailSaveVO().size() == 0){
				result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS, "入库明细为空！");
				return result;
			}

			String msg = stayInstorageService.saveStayInstorage(vo, loginUser);

			if(StringUtils.isNotBlank(msg)){
				result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), msg, "");
				return result;
			}

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, "");
		}catch(Exception e){
			logger.error("待入库-入库-保存数据错误:"+e.getMessage(),e);
			result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), e.getMessage(), null);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "查看采购入库草稿缓存", notes = "查看采购入库草稿缓存 | 开发者 苏磊 | 已完成")
	@RequestMapping(value = "/daftCache/{baseOrderId}", method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(name = "baseOrderId", value = "上游单据id", required = true, paramType="path")
	})
	public Result<DraftCacheVO> daftCacheVO(@ApiIgnore UserVO userVO,@PathVariable("baseOrderId") Long baseOrderId) {

		Result<DraftCacheVO> result = new Result<>();

		DraftCacheVO draftCacheVO = stayInstorageService.getDraftCacheVO(userVO,baseOrderId);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);

		return  result;
	}

	@ApiOperation(value = "保存采购入库草稿缓存", notes = "保存采购入库草稿缓存 | 开发者 苏磊 | 已完成")
	@RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
	public Result<DraftCacheVO<StayInstorageSaveVO>> saveDraftCache(@ApiIgnore UserVO userVO,@RequestBody DraftCacheVO<StayInstorageSaveVO> draftCacheVO) throws Exception {

		Result<DraftCacheVO<StayInstorageSaveVO>> result = new Result<>();
		try{
			draftCacheVO = stayInstorageService.saveDraftCache(userVO,draftCacheVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);
			return  result;
		}catch(BusinessException e){
			logger.error("保存采购入库草稿缓存错误:"+e.getMessage(),e);
			result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), e.getMessage(),null);
			return result;
		}catch(Exception e){
			logger.error("保存采购入库草稿缓存错误:"+e.getMessage(),e);
			result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), e.getMessage(), null);
			return result;
		}
	}

	@ApiOperation(value = "删除采购入库草稿缓存", notes = "删除采购入库草稿缓存 | 开发者 苏磊 | 已完成")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
					, required = true, paramType="path")
	})
	@RequestMapping(value = "/remove/daftCache/{redisKeyValue}", method = RequestMethod.POST)
	public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(value = "redisKeyValue", required = true) String redisKeyValue) {

		Result result = new Result<>();

		stayInstorageService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.PURCHASE_IN.getCodePrefix(),redisKeyValue);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS);

		return  result;
	}

	/*@ApiOperation(value="入库按钮--查询草稿内容", notes = "入库按钮--查询草稿内容 | 开发者:苏磊 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStayInstorageDraft", method = RequestMethod.GET)
	@ResponseBody
	public Result<StayInstorageSaveVO> getStayInstorageDraft(HttpServletRequest request,
															  @ApiParam(value = "验收单ID", required = true) @RequestParam(required=false) Long id,
															  @ApiIgnore UserVO userVO){
		Result<StayInstorageSaveVO> result = new Result<StayInstorageSaveVO>();
		try{
			StayInstorageSaveVO vo = stayInstorageService.getStayInstorageDraft(id,userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
		}catch(Exception e){
			logger.error("入库按钮--查询草稿内容数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}*/



	
}
