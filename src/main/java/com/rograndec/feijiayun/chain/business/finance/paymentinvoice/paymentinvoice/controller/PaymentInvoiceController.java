
package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.controller;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.service.PaymentInvoiceService;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vaild.PaymentinvoiceSaveVaild;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vaild.PaymentinvoiceWriteOffVaild;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 功能描述：
 * Created by zhaiwei on 2019/1/10 21:29
 */
@Api(value = "payment_invoice_controller", description = "应付发票")
@RequestMapping("payment/invoice/")
@RestController
@Validated
public class PaymentInvoiceController {

	private static final Log logger = LogFactory.getLog(PaymentInvoiceController.class);

	@Autowired
	private PaymentInvoiceService paymentInvoiceService;

	@ApiOperation(value="根据条件查询商品-应付发票", notes = "根据条件查询商品-应付发票 | 开发者:翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/paymentInvoiceGoodses", method = RequestMethod.GET)
	public Result<Page<List<PaymentInvoiceGoodsVO>>> paymentInvoiceGoodses(@ApiIgnore UserVO userVO,
											   @ApiParam(value = "1:企业,2:供应商", required = true) @RequestParam("type") Integer type,
											   @ApiParam(value = "供应商或者总部id", required = true) @RequestParam("supplierId") Long supplierId,
											   @ApiParam(value = "查询条件", required = false) @RequestParam(value = "param",required = false) String param,
											   @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo",required = false) Integer pageNo,
											   @ApiParam(value = "每页显示的记录数", required = false ) @RequestParam(value = "pageSize",required = false) Integer pageSize
	){
		Result<Page<List<PaymentInvoiceGoodsVO>>> result = new Result<>();
		Page<List<PaymentInvoiceGoodsVO>> page = new Page(pageNo, pageSize);
		List<PaymentInvoiceGoodsVO> paymentInvoiceGoodsVOS = paymentInvoiceService.queryPaymentInvoiceGoods(userVO, supplierId, type, param, page);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

		return result;
	}

	@ApiOperation(value="调用采购入库或者配进入库-应付发票", notes = "调用采购入库或者配进入库-应付发票 | 开发者:翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/paymentInvoiceInStorages", method = RequestMethod.GET)
	public Result<Page<List<PaymentInvoiceInStorageVO>>> paymentInvoiceInStorages(@ApiIgnore UserVO userVO,
																		  @ApiParam(value = "1:企业,2:供应商", required = true) @RequestParam("type") Integer type,
																		  @ApiParam(value = "供应商或者总部id", required = true) @RequestParam("supplierId") Long supplierId,
																		  @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo",required = false) Integer pageNo,
																		  @ApiParam(value = "每页显示的记录数", required = false ) @RequestParam(value = "pageSize",required = false) Integer pageSize,
																		  @ApiParam(value = "开始日期字符串", required = false) @PathVariable(value = "startDateStr",required = false) String startDateStr,
																		  @ApiParam(value = "结束日期字符串", required = false) @PathVariable(value = "endDateStr",required = false) String endDateStr
	){
		Result<Page<List<PaymentInvoiceInStorageVO>>> result = new Result<>();
		Page<List<PaymentInvoiceInStorageVO>> page = new Page(pageNo, pageSize);
		List<PaymentInvoiceInStorageVO> paymentInvoiceInStorageVOS = paymentInvoiceService.queryInStores(userVO, supplierId, startDateStr, endDateStr, type, page);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

		return result;
	}

	@ApiOperation(value="根据入库单id查询商品-应付发票", notes = "根据入库单id查询商品-应付发票 | 开发者:翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/paymentInvoiceOrderGoodses", method = RequestMethod.POST)
	public Result<List<PaymentInvoiceGoodsVO>> paymentInvoiceOrderGoodses(@ApiIgnore UserVO userVO,
																		   @RequestBody
																		   @ApiParam(name = "ids[]", value = "入库单id数组", required = true)
																		   @Valid
																		   @NotNull(message = "入库单不能为空")
																		   @Size(min = 1,message = "入库单不能为空") List<Long> ids
	){
		Result<List<PaymentInvoiceGoodsVO>> result = new Result<>();
		List<PaymentInvoiceGoodsVO> paymentInvoiceGoodsVOS = paymentInvoiceService.queryPaymentInvoiceGoods(userVO, ids);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, paymentInvoiceGoodsVOS);

		return result;
	}


	@ApiOperation(value = "应付发票新增/修改", notes = "应付发票新增/修改 | 开发者 翟伟 | 已完成")
	@RequestMapping(value="/save", method= RequestMethod.POST)
	public Result<PaymentInvoice> save(
			@ApiParam(value = "应付发票新增实体", required = true)
			@RequestBody
					@Valid @PaymentinvoiceSaveVaild PaymentInvoiceSaveVO paymentInvoiceSaveVO, @ApiIgnore UserVO userVO
	) throws Exception {

		Result<PaymentInvoice> result = new Result<>();
		if(null == paymentInvoiceSaveVO.getId()) {
			PaymentInvoice save = paymentInvoiceService.save(userVO, paymentInvoiceSaveVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,save);
		}
		else {
			PaymentInvoice update = paymentInvoiceService.update(userVO, paymentInvoiceSaveVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,update);
		}

		return result;
	}


	@ApiOperation(value = "查询应付发票单据列表", notes = "查询应付发票单据列表 | 开发者 翟伟 | 已完成")
	@RequestMapping(value="/list", method= RequestMethod.GET)
	public Result<Page<List<PaymentInvoiceResponseVO>>> list(
			@ApiParam(value = "供货单位编码", required = false) @RequestParam(required = false) String supplierCode,
			@ApiParam(value = "供货单位名称", required = false) @RequestParam(required = false) String supplierName,
			@ApiParam(value = "单据编码", required = false) @RequestParam(required = false) String code,
			@ApiParam(value = "开票人员名称", required = false) @RequestParam(required = false) String billManName,
			@ApiParam(value = "单据状态(0-待付款；1-部分付款；2-已付款；3-已冲销)", required = false) @RequestParam(required = false) Integer status,
			@RequestParam(required = false)
			@ApiParam(name = "pageNo", value = "当前页码", required = false)
					Integer pageNo,
			@RequestParam(required = false)
			@ApiParam(name = "pageSize", value = "显示条数", required = false)
					Integer pageSize,

			@RequestParam(required = false)
			@ApiParam(name = "orderName", value = "按某一列排序", required = false)
					String  orderName,
			@RequestParam(required = false)
			@ApiParam(name = "orderType", value = "排序方式（升序：asc,降序：desc", required = false)
					String orderType,

			@RequestParam(required = false)
			@ApiParam(name = "startDate", value = "开始时间", required = false)
					String  startDate,
			@RequestParam(required = false)
			@ApiParam(name = "endDate", value = "结束时间", required = false)
					String endDate,
			@ApiIgnore UserVO userVO
	) throws Exception {

		Result<Page<List<PaymentInvoiceResponseVO>>> result = new Result<>();
		PaymentInvoiceQueryParamVO paymentInvoiceQueryParamVO = new PaymentInvoiceQueryParamVO(
				supplierCode,
				supplierName,
				code,
				billManName,
				status,
				orderName,orderType,startDate,endDate);
		Page page = new Page(pageNo,pageSize);
		List<PaymentInvoiceResponseVO> paymentInvoiceResponseVOS = paymentInvoiceService.queryPaymentInvoiceResponseVOs(userVO, paymentInvoiceQueryParamVO, page);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
		return result;
	}


	@ApiOperation(value = "查询应付发票单据详情", notes = "查询应付发票单据详情 | 开发者 翟伟 | 已完成")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "应付发票id"
					, required = true, paramType="path")
	})
	@RequestMapping(value="/paymentInvoice/{id}", method= RequestMethod.GET)
	public Result<PaymentInvoiceResponseVO> paymentInvoices(
			@PathVariable Long id,
			@ApiIgnore UserVO userVO
	) throws Exception {

		Result<PaymentInvoiceResponseVO> result = new Result<>();
		PaymentInvoiceResponseVO paymentInvoiceResponseVO = paymentInvoiceService.queryPaymentInvoiceResponseVO(userVO, id);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS,paymentInvoiceResponseVO);
		return result;
	}

	@ApiOperation(value = "查看应付发票草稿", notes = "查看应付发票草稿 | 开发者 翟伟 | 已完成")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "supplierId", value = "供货单位id"
					, required = true, paramType="path")
	})
	@RequestMapping(value = "/daftCache/{supplierId}", method = RequestMethod.GET)
	public Result<List<DraftCacheVO>> daftCacheVO(@PathVariable Long supplierId , @ApiIgnore UserVO userVO) {

		Result<List<DraftCacheVO>> result = new Result<>();

		List<DraftCacheVO> draftCacheVO = paymentInvoiceService.getDraftCacheVO(supplierId,userVO);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);

		return  result;
	}

	@ApiOperation(value = "保存应付发票草稿", notes = "保存应付发票草稿 | 开发者 翟伟 | 已完成")
	@RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
	public Result<DraftCacheVO> saveDraftCache(@ApiIgnore UserVO userVO, @RequestBody DraftCacheVO draftCacheVO) {
		Result<DraftCacheVO> result = new Result();
		DraftCacheVO distrReqPlanVODraftCacheVO = paymentInvoiceService.saveDraftCache(userVO, draftCacheVO);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrReqPlanVODraftCacheVO);

		return  result;
	}


	@ApiOperation(value = "删除应付发票草稿", notes = "删除应付发票草稿 | 开发者 翟伟 | 已完成")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
					, required = true, paramType="path"),
			@ApiImplicitParam(name = "supplierId", value = "supplierId"
					, required = true, paramType="path")
	})
	@RequestMapping(value = "/remove/daftCache/{redisKeyValue}/{supplierId}", method = RequestMethod.DELETE)
	public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String redisKeyValue, @PathVariable(required = true) Long supplierId) {

		Result result = new Result<>();

		paymentInvoiceService.removeDraftCach(supplierId,userVO.getEnterpriseId(), OrderRule.PAYMENT_INVOICE.getCodePrefix(),redisKeyValue);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS);

		return  result;
	}


	@ApiOperation(value = "查看修改记录", notes = "查看修改记录 | 开发者 翟伟 | 已完成")
	@RequestMapping(value = "/modifyRecords/{id}", method = RequestMethod.GET)
	@ApiImplicitParam(name = "id", value = "预付发票id", required = true, dataType = "Long", paramType="path")
	public Result<Page<List<PaymentInvoiceModifyRecordResponseVO>>> modifyRecords(
			@ApiIgnore UserVO userVO,
			@PathVariable Long id,
			@RequestParam(required = false)
			@ApiParam(name = "pageNo", value = "当前页码", required = false)
					Integer pageNo,
			@RequestParam(required = false)
			@ApiParam(name = "pageSize", value = "显示条数", required = false)
					Integer pageSize
	) {

		Result<Page<List<PaymentInvoiceModifyRecordResponseVO>>> result = new Result<>();

		Page page = new Page(pageNo,pageSize);

		List<PaymentInvoiceModifyRecordResponseVO> paymentInvoiceModifyRecordResponseVOS = paymentInvoiceService.queryModifyRecords(userVO, id, page);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);

		return  result;
	}


	@ApiOperation(value = "应付发票详情信息导出", notes = "应付发票详情信息导出 | 开发者 翟伟 | 已完成")
	@RequestMapping(value="/exportReceiveDetail/{id}",method= RequestMethod.GET)
	@ApiImplicitParam(name = "id", value = "应付发票id", required = true, dataType = "Long", paramType="path")
	public void exportReceiveDetail(HttpServletResponse response,
									@PathVariable Long id,@ApiIgnore UserVO userVO) throws Exception {

		OutputStream output = response.getOutputStream();
		String name = "应付发票";
		response.setContentType("application/octet-stream;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
		PaymentInvoiceResponseVO paymentInvoiceResponseVO = paymentInvoiceService.queryPaymentInvoiceResponseVO(userVO, id);
		paymentInvoiceService.export(output, paymentInvoiceResponseVO);
	}

	@ApiOperation(value = "修改记录导出", notes = "修改记录导出 | 开发者 翟伟 | 已完成")
	@RequestMapping(value="/exportUpdateRecord/{id}",method= RequestMethod.GET)
	@ApiImplicitParam(name = "id", value = "应付发票id", required = true, dataType = "Long", paramType="path")
	public void exportUpdateRecord(HttpServletResponse response,
								   @PathVariable Long id,@ApiIgnore UserVO userVO) throws Exception {

		OutputStream output = response.getOutputStream();
		String name = "应付发票修改信息";
		response.setContentType("application/octet-stream;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
		List<PaymentInvoiceModifyRecordResponseVO> paymentInvoiceModifyRecordResponseVOS = paymentInvoiceService.queryModifyRecords(userVO, id, null);
		paymentInvoiceService.exportUpdateRecord(output,paymentInvoiceModifyRecordResponseVOS,userVO);

	}


	@ApiOperation(value = "冲销", notes = "冲销 | 开发者 翟伟 | 已完成")
	@RequestMapping(value="/wariteOff/{id}",method= RequestMethod.PUT)
	@ApiImplicitParam(name = "id", value = "应付发票id", required = true, dataType = "Long", paramType="path")
	public void wariteOff(@PathVariable @Valid @PaymentinvoiceWriteOffVaild Long id, @ApiIgnore UserVO userVO) throws Exception {

		Result result = new Result<>();

		paymentInvoiceService.wariteOff(userVO,id);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS);

	}

}