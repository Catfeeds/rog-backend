package com.rograndec.feijiayun.chain.inf.pos.sale.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.service.POSOrderService;
import com.rograndec.feijiayun.chain.inf.pos.sale.service.POSReturnSaleService;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSOrderDetailVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSOrderParamVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSOrderVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSReturnSaleVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.service.POSSaleService;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSSaleVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="POSSaleController",description = "POS销售/销退接口")
@RestController
@RequestMapping("/inf/pos/sale")
public class POSSaleController {
	
	private static final Logger logger = LoggerFactory.getLogger(POSSaleController.class);
	
	@Autowired
	private POSSaleService pOSSaleService; 
	
	@Autowired
	private POSReturnSaleService pOSReturnSaleService; 
	
	@Autowired
	private POSOrderService pOSOrderService; 
	
	@ApiOperation(value = "保存销售数据", notes = "保存销售数据 | 开发者 刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/saveSaleData", method = RequestMethod.POST)
    public Result<String> saveSaleData(HttpSession session,
    		@ApiParam(required = true, value = "销售单数据")@RequestBody List<POSSaleVO> saleVOList) {
		Result<String> result = new Result<>();
		try {
			UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
			String msg = pOSSaleService.saveSaleData(saleVOList, userVO);
			if(StringUtils.isNotBlank(msg)){
				logger.error("POS保存销售数据:"+msg+"-"+userVO.getEnterpriseId());
				result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS, msg);
	            return result;
			}
			result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		} catch (Exception e) {
            logger.error("POS保存销售数据失败:", e);
            result.setBizCodeSuccessInfo(SysCode.FAIL, e.getMessage());
            return result;
        }
        return result;
    }
	
	
	@ApiOperation(value = "保存销退数据", notes = "保存销退数据 | 开发者 刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/saveReturnSaleData", method = RequestMethod.POST)
    public Result<String> saveReturnSaleData(HttpSession session,
    		@ApiParam(required = true, value = "销退单数据")@RequestBody List<POSReturnSaleVO> returnSaleVOList) {
		Result<String> result = new Result<>();
		try {
			UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
			String msg = pOSReturnSaleService.saveReturnSaleData(returnSaleVOList, userVO);
			if(StringUtils.isNotBlank(msg)){
				logger.error("POS保存销退数据:"+msg+"-"+userVO.getEnterpriseId());
				result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS, msg);
	            return result;
			}
			result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		} catch (Exception e) {
            logger.error("POS保存销退数据失败:", e);
            result.setBizCodeSuccessInfo(SysCode.FAIL, e.getMessage());
            return result;
        }
        return result;
    }
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "获取销售单数据", notes = "获取销售单数据 | 开发者 刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getOrderData", method = RequestMethod.POST)
    public Result<Page<List<POSOrderVO>>> getOrderData(HttpSession session,
    		@ApiParam(required = true, value = "销售单数据")@RequestBody POSOrderParamVO param) {
		Result<Page<List<POSOrderVO>>> result = new Result<>();
		try {
			UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
			
			Page page = pOSOrderService.selectOrderDataByParam(param, userVO);
			
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		} catch (Exception e) {
            logger.error("获取销售单数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "获取销售单明细数据", notes = "获取销售单明细数据 | 开发者 刘群 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getOrderDeatilData", method = RequestMethod.POST)
    public Result<Page<List<POSOrderDetailVO>>> getOrderDeatilData(HttpSession session,
    		@ApiParam(required = true, value = "销售单明细数据")@RequestBody POSOrderParamVO param) {
		Result<Page<List<POSOrderDetailVO>>> result = new Result<>();
		try {
			UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
			
			Page page = pOSOrderService.selectOrderDeatilDataByParam(param, userVO);
			
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		} catch (Exception e) {
            logger.error("获取销售单明细数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
}
