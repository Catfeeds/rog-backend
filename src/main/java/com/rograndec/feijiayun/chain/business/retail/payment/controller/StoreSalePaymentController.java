package com.rograndec.feijiayun.chain.business.retail.payment.controller;

import java.util.List;

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

import com.rograndec.feijiayun.chain.business.retail.payment.service.StoreSalePaymentService;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.DynamicColumnVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.PaymentVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.StoreAlreadyPageVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.StoreAlreadySearchConditionVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.StoreStaySearchConditionVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.StoreStayViewSearchConditionVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


@Api(value = "storeSalePayment", description = "零售管理-零售缴款-门店", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/salePayment/store")
public class StoreSalePaymentController {
	
	private static final Log logger = LogFactory.getLog(StoreSalePaymentController.class);
	
	@Autowired
	private StoreSalePaymentService storeSalePaymentService;
	
	@ApiOperation(value="获取零售缴款各模块动态显示列数据", notes = "获取零售缴款各模块动态显示列数据 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getDynamicColumn", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<DynamicColumnVO>> getDynamicColumn(HttpServletRequest request,
			@ApiParam(value = "来源 0-门店待缴款分页列表，1-门店待缴款查看列表，2-总部待缴款分页列表，3-总部待缴款查看列表", required = true) @RequestParam Integer source
			){
		Result<List<DynamicColumnVO>> result = new Result<List<DynamicColumnVO>>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<DynamicColumnVO> list = storeSalePaymentService.selectDynamicColumnBySource(source,loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
        	logger.error("分页获取零售缴款-待缴款数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取款台下拉信息", notes = "获取款台下拉信息（/store/getChainType获取门店类型） | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPosStandSelectBean", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getPosStandSelectBean(HttpServletRequest request
			){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<SelectBean> list = storeSalePaymentService.selectPosStandSelectBeanByUserVO(loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取门店类型信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取班组下拉信息", notes = "获取班组下拉信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPosTeamSelectBean", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getPosTeamSelectBean(HttpServletRequest request
			){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<SelectBean> list = storeSalePaymentService.selectPosTeamSelectBeanByUserVO(loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取门店类型信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页获取门店-零售缴款-待缴款数据", notes = "分页获取门店-零售缴款-待缴款数据 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStayPaymentPage", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page> getStayPaymentPage(HttpServletRequest request,
			@RequestBody StoreStaySearchConditionVO condition
			){
		Result<Page> result = new Result<Page>();
        try{
        	if(condition == null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	Page page = new Page(condition.getPageNo(), condition.getPageSize());
        	
        	storeSalePaymentService.getStayPaymentPageByParams(condition, page, loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("分页获取零售缴款-待缴款数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页获取门店-零售缴款-待缴款-查看列表数据", notes = "分页获取门店-零售缴款-待缴款-查看列表数据 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStayPaymentViewPage", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page> getStayPaymentViewPage(HttpServletRequest request,
			@RequestBody StoreStayViewSearchConditionVO condition){
		Result<Page> result = new Result<Page>();
        try{
        	if(condition == null || condition.getId() == null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	Page page = new Page(condition.getPageNo(), condition.getPageSize());
        	storeSalePaymentService.getStayPaymentViewPage(condition, page, loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("分页获取零售缴款-待缴款数据-查看列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="获取门店-零售缴款-待缴款-缴款信息", notes = "获取门店-零售缴款-待缴款-缴款信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStayPaymentInfo", method = RequestMethod.GET)
	@ResponseBody
	public Result<PaymentVO> getStayPaymentInfo(HttpServletRequest request,
			@ApiParam(value = "交接班ID", required = true) @RequestParam(required=true) Long shiftId){
		Result<PaymentVO> result = new Result<PaymentVO>();
        try{
        	
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");

        	PaymentVO pay = storeSalePaymentService.queryPaymentVOByShiftId(shiftId, loginUser);
        	if(pay == null){
        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, pay);
        		return result;
        	}
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, pay);
        }catch(Exception e){
        	logger.error("获取零售缴款-待缴款-缴款信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="门店-零售缴款-待缴款-保存缴款信息 ", notes = "门店-零售缴款-待缴款-保存缴款信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/saveStayPaymentInfo", method = RequestMethod.POST)
	public Result<String> saveStayPaymentInfo(HttpServletRequest request,
			@RequestBody PaymentVO paymentVO) {
		Result<String> result = new Result<String>();
		try{
			HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	String msg = storeSalePaymentService.saveSalePayment(paymentVO, loginUser);
        	if(StringUtils.isNotBlank(msg)){
        		result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS, msg);
        		return result;
        	}
        	
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, "");
		}catch(Exception e){
			logger.error("保存门店信息错误:"+e.getMessage(),e);
			result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage(), "");
			return result;
		}
		return result;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="分页获取门店-零售缴款-已缴款数据", notes = "分页获取门店-零售缴款-已缴款数据 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getAlreadyPaymentPage", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page> getAlreadyPaymentPage(HttpServletRequest request,
			@RequestBody StoreAlreadySearchConditionVO condition){
		Result<Page> result = new Result<Page>();
        try{
        	if(condition == null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	Page page = new Page(condition.getPageNo(), condition.getPageSize());
        	List<StoreAlreadyPageVO> list = storeSalePaymentService.selectAlreadyPayment(condition, loginUser.getEnterpriseId(), page);
        	
        	page.setResult(list);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("页获取门店-零售缴款-已缴款数据:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取门店-零售缴款-已缴款-查看", notes = "获取门店-零售缴款-已缴款-查看 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getAlreadyPaymentInfo", method = RequestMethod.GET)
	@ResponseBody
	public Result<PaymentVO> getAlreadyPaymentInfo(HttpServletRequest request,
			@ApiParam(value = "零售缴款ID", required = true) @RequestParam(required=true) Long id){
		Result<PaymentVO> result = new Result<PaymentVO>();
        try{
        	
        	PaymentVO vo = storeSalePaymentService.queryAlreadyPaymentInfoById(id);
        	
        	if(vo == null){
        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, vo);
        		return result;
        	}
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
        	logger.error("获取门店-零售缴款-已缴款-缴款信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
}
