package com.rograndec.feijiayun.chain.business.online.purchase.order.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.rograndec.feijiayun.chain.business.online.purchase.order.service.MphOrderService;
import com.rograndec.feijiayun.chain.business.online.purchase.order.vo.MphOrderVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.valid.RequestParamValid;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.mph.CancelSaasOrder;
import com.rograndec.feijiayun.chain.inf.mph.GetOrderGoodsLatestInfo;

/**
 * 
 * @ClassName: MphOrderController   
 * @Description: 我的订单数据 --- 从MPH获取
 * @author yuting.li
 * @version 1.0 
 * @date 2017年11月23日 上午11:32:50
 */

@Api(value = "mphOrder", description = "我的订单列表", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/order")
public class MphOrderController {
	
	
	private static Logger logger = LoggerFactory.getLogger(MphOrderController.class);
	
	@Autowired
	private MphOrderService mphOrderService;
	@Autowired
	private CancelSaasOrder cancelMphOrder;
	@Autowired
	private GetOrderGoodsLatestInfo latestInfo;
	
	@ApiOperation(value="获取我的订单数据", notes = "获取我的订单数据 | 开发者:yuting.li | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getMphOrder", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page<List<MphOrderVO>>> getMphOrder(@ApiIgnore UserVO userVO,
			@ApiParam(name = "pageNo", value = "当前页码", required = true) @RequestParamValid @RequestParam(required = true)Integer pageNo,
            @ApiParam(name = "pageSize", value = "显示条数", required = true) @RequestParamValid @RequestParam(required = true)Integer pageSize,
            @ApiParam(name = "search", value = "检索条件:订单号/供应商", required = false) @RequestParam(required = false)String search){
		Result<Page<List<MphOrderVO>>> result = new Result<>();
        try{
        	//userVO.setRgtUserId(5965082);
        	//userVO.setRgtEnterpriseId(2603996);
        	Page<List<MphOrderVO>> page = mphOrderService.getMphOrder(pageNo, pageSize, search, userVO);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        }catch(Exception e){
        	logger.error("获取我的订单数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}

	@ApiOperation(value="取消我的订单数据", notes = "取消我的订单数据 | 开发者:马东 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/cancelMphOrder", method = RequestMethod.GET)
	@ResponseBody
	public Result<String> cancelMphOrder(@ApiIgnore UserVO userVO,
			@ApiParam(name="osn",value = "订单号",required = true) @RequestParamValid @RequestParam String osn){
		Result<String> result = new Result<>();
		try{
			cancelMphOrder.getOrderGoodsLatestInfoByOid(userVO.getRgtUserId().longValue(),osn);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,"取消成功");
		}catch(Exception e){
			logger.error("取消我的订单数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value="再次购买", notes = "再次购买 | 开发者:马东 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/reBuy", method = RequestMethod.GET)
	@ResponseBody
	public Result<String> reBuy(@ApiIgnore UserVO userVO,
			@ApiParam(value = "再次下单的订单ID",required = true) @RequestParamValid @RequestParam String oid){
		Result<String> result = new Result<>();
		try{
			String mphResult = latestInfo.getOrderGoodsLatestInfoByOid(Long.valueOf(userVO.getRgtUserId()),Long.valueOf(oid));
			mphOrderService.reBuy(userVO,mphResult);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,"再次购买成功");
		}catch(BusinessException e){
			result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
			logger.error("再次购买错误:"+e.getMessage(),e);
		}catch(Exception e){
			logger.error("再次购买错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}


	
	
	/*@ApiOperation(value = "导出我的订单数据", notes = "导出我的订单数据 | 开发者 yuting.li | 已完成")
    @RequestMapping(value = "/excelExport", method = RequestMethod.GET, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void excelExport(HttpServletRequest request, HttpServletResponse response,
    		@ApiParam(name = "search", value = "检索条件:订单号/供应商", required = false) @RequestParam(required = false)String search) throws FileNotFoundException {
        String name = "我的订单数据";
        try {
	        response.setContentType("application/msexcel;charset=utf-8");
	        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
	        //输出Excel文件
	        OutputStream output = response.getOutputStream();

            output.close();
            output.flush();
        } catch (Exception e) {
        	logger.error("导出我的订单数据错误:"+e.getMessage(),e);
            e.printStackTrace();
        }
        
    }*/

}
