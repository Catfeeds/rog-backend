package com.rograndec.feijiayun.chain.business.retail.payment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.rograndec.feijiayun.chain.business.retail.payment.service.HeadquartersSalePaymentService;
import com.rograndec.feijiayun.chain.business.retail.payment.service.StoreSalePaymentService;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.HeadquartersAlreadySearchConditionVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.HeadquartersStaySearchConditionVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.PaymentVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.StoreStayViewSearchConditionVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Api(value = "storeSalePayment", description = "零售管理-零售缴款-总部", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/salePayment/headquarters")
public class HeadquartersSalePaymentController {
	
	private static final Log logger = LogFactory.getLog(HeadquartersSalePaymentController.class);
	
	@Autowired
	private StoreSalePaymentService storeSalePaymentService;
	
	@Autowired
	private HeadquartersSalePaymentService headquartersSalePaymentService;

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页获取总部-零售缴款-待缴款数据", notes = "分页获取总部-零售缴款-待缴款数据 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStayPaymentPage", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page> getStayPaymentPage(HttpServletRequest request,
			@RequestBody HeadquartersStaySearchConditionVO condition){
		Result<Page> result = new Result<Page>();
        try{
        	if(condition == null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	Page page = new Page(condition.getPageNo(), condition.getPageSize());
        	headquartersSalePaymentService.getStayPaymentPage(condition, page, loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("分页获取总部零售缴款-待缴款数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页获取总部-零售缴款-待缴款-查看列表数据", notes = "分页获取总部-零售缴款-待缴款-查看列表数据 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStayPaymentViewPage", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page> getStayPaymentViewPage(HttpServletRequest request,
			@RequestBody StoreStayViewSearchConditionVO condition
			){
		Result<Page> result = new Result<Page>();
        try{
        	if(condition == null || condition.getId() == null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	Page page = new Page(condition.getPageNo(), condition.getPageSize());
        	headquartersSalePaymentService.getStayPaymentViewPage(condition, page, loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("分页获取零售缴款-待缴款数据-查看列表错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="获取总部-零售缴款-待缴款-缴款信息", notes = "获取总部-零售缴款-待缴款-缴款信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@ApiOperation(value="保存总部-零售缴款-待缴款-缴款信息 ", notes = "保存总部-零售缴款-待缴款-缴款信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
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
			logger.error("保存总部信息错误:"+e.getMessage(),e);
			result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage(), "");
			return result;
		}
		return result;
	}
	
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页获取总部-零售缴款-已缴款数据", notes = "分页获取总部-零售缴款-已缴款数据 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getAlreadyPaymentPage", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page> getAlreadyPaymentPage(HttpServletRequest request,
			@RequestBody HeadquartersAlreadySearchConditionVO condition
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
        	headquartersSalePaymentService.getAlreadyPaymentPage(condition, page, loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("页获取总部-零售缴款-已缴款数据:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取总部-零售缴款-已缴款-查看", notes = "获取总部-零售缴款-已缴款-查看 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getAlreadyPaymentInfo", method = RequestMethod.GET)
	@ResponseBody
	public Result<PaymentVO> getAlreadyPaymentInfo(HttpServletRequest request,
			@ApiParam(value = "零售缴款ID", required = true) @RequestParam(required=true) Long id){
		Result<PaymentVO> result = new Result<PaymentVO>();
        try{
        	
        	PaymentVO vo = storeSalePaymentService.queryAlreadyPaymentInfoById(id);
        	
        	if(vo == null){
        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, vo);
        	}
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
        	logger.error("获取总部-零售缴款-已缴款-缴款信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
}
